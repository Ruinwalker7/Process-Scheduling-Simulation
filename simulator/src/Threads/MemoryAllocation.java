package Threads;

import entity.DataBuffers;
import entity.Memory;
import entity.PCB;
import entity.ProcessState;

import java.util.Iterator;

import static ui.mainFrame.pcbList;
import static ui.mainFrame.whiteBroad;

public class MemoryAllocation extends Thread{

    @Override
    public void run() {
        while (true){
            if(!DataBuffers.createQueue.isEmpty()){

                PCB pcb = DataBuffers.createQueue.peek();

                Iterator iter = DataBuffers.memoryList.iterator();
                try{
                while (iter.hasNext()){
                    Memory memory = (Memory)iter.next();
                    if(!memory.isUse() && pcb.getMemorySize()<memory.getSize()){
                        memory.setUse(true);
                        DataBuffers.memoryList.add( DataBuffers.memoryList.indexOf(memory)+1,new Memory(
                                memory.getStart() + pcb.getMemorySize(),memory.getEnd(),false));
                        memory.setEnd(memory.getStart()+ pcb.getMemorySize()-1);

                        //设置pcb
                        pcb.setStart(memory.getStart());
                        pcb.setEnd(memory.getEnd());
                        pcb.setState(ProcessState.READY);
                        pcb.setMemory(memory);

                        //从创建队列到就绪队列中
                        DataBuffers.createQueue.remove();
                        DataBuffers.readyQueue.add(pcb);
                        whiteBroad.paint(whiteBroad.getGraphics());
                        break;
                    }
                }}catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
