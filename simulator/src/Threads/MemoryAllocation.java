package Threads;

import entity.DataBuffers;
import entity.Memory;
import entity.PCB;
import entity.ProcessState;

import java.util.Iterator;

public class MemoryAllocation extends Thread{

    @Override
    public void run() {
        while (true){
            if(!DataBuffers.createQueue.isEmpty()){

                PCB pcb = DataBuffers.createQueue.peek();

                Iterator iter = DataBuffers.memoryList.iterator();

                while (iter.hasNext()){
                    Memory memory = (Memory)iter.next();
                    if(!memory.isUse() && pcb.getMemory()<memory.getSize()){
                        DataBuffers.memoryList.add( DataBuffers.memoryList.indexOf(memory),new Memory(
                                memory.getStart()+ pcb.getMemory(),pcb.getEnd(), memory.getSize() -pcb.getMemory(),false));
                        memory.setEnd(memory.getStart()+ pcb.getMemory()-1);
                        memory.setSize(memory.getSize()- pcb.getMemory());

                        pcb.setStart(memory.getStart());
                        pcb.setEnd(memory.getEnd());
                        pcb.setState(ProcessState.READY);
                        DataBuffers.createQueue.remove();
                        DataBuffers.readyQueue.add(pcb);
                        System.out.println(pcb);
                        break;
                    }
                }
            }
        }
    }
}
