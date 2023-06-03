package Threads;

import entity.DataBuffers;
import entity.PCB;
import entity.ProcessState;

import java.util.Date;

public class RunningProcess extends Thread{
    private PCB pcb;
    private long startTime;
    @Override
    public void run() {
        while (true){
            if(pcb!= null && pcb.getState()== ProcessState.RUNNING){
                if(pcb.getRemainTime()<System.currentTimeMillis()-startTime){
                    System.out.println(pcb);
                    pcb.setState(ProcessState.STOP);
                    pcb.setEndTime(new Date());
                    pcb = null;
                }
            }
            else if(!DataBuffers.readyQueue.isEmpty()){
                System.out.println(DataBuffers.readyQueue);
                pcb = DataBuffers.readyQueue.peek();
                pcb.setState(ProcessState.RUNNING);
                startTime = System.currentTimeMillis();
                DataBuffers.readyQueue.remove();
            }
        }
    }
}
