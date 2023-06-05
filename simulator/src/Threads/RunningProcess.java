package Threads;

import entity.DataBuffers;
import entity.Memory;
import entity.PCB;
import entity.ProcessState;

import java.util.Date;

import static ui.mainFrame.pcbList;
import static ui.mainFrame.whiteBroad;

public class RunningProcess extends Thread{
    private PCB pcb;
    private long startTime;
    @Override
    public void run() {
        while (true){
            if(pcb!= null && pcb.getState()== ProcessState.RUNNING){
                if(pcb.getRemainTime()<System.currentTimeMillis()-startTime){

                    //pcb终止
                    pcb.setState(ProcessState.FINISH);
                    pcb.setEndTime(new Date());

                    cleanMemory(pcb);
                    pcb = null;
                }else if(pcb.getState()==ProcessState.BLOCK){
                    pcb.setRemainTime(pcb.getRemainTime()-System.currentTimeMillis()+startTime);
                    System.out.println("剩余时间："+pcb.getRemainTime());
                    pcb = null;
                }else if(pcb.getState()==ProcessState.STOP){
                    pcb.setEndTime(new Date());
                    cleanMemory(pcb);
                    pcb = null;
                }
            }
            else if(!DataBuffers.readyQueue.isEmpty()){
                pcb = DataBuffers.readyQueue.peek();
                if(pcb.getState()==ProcessState.READY){
                    pcb.setState(ProcessState.RUNNING);
                    startTime = System.currentTimeMillis();
                }
                DataBuffers.readyQueue.remove();
            }
        }
    }

    public void cleanMemory(PCB pcb){
        //回收内存
        Memory memory = pcb.getMemory();
        memory.setUse(false);
        int index = DataBuffers.memoryList.indexOf(memory);
        System.out.println(index);
        if(index!=0 && !DataBuffers.memoryList.get(index-1).isUse()){
            memory.setStart(DataBuffers.memoryList.get(index-1).getStart());
            DataBuffers.memoryList.remove(DataBuffers.memoryList.get(index-1));
        }

        index = DataBuffers.memoryList.indexOf(memory);
        System.out.println(index);
        if(index!=DataBuffers.memoryList.size()-1 && !DataBuffers.memoryList.get(index+1).isUse()){
            memory.setEnd(DataBuffers.memoryList.get(index+1).getEnd());
            DataBuffers.memoryList.remove(DataBuffers.memoryList.get(index+1));

        }
        pcb.getMemory().setPid(0);

        whiteBroad.paint(whiteBroad.getGraphics());
    }
}
