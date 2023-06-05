package entity;

import ui.mainFrame;

import java.util.Date;

public class PCB {
    private int PID;
    private ProcessState state;
    private String statename;
    private int memorySize;
    private long needTime;
    private long remainTime;
    private Date createTime;
    private Date endTime;
    private Memory memory;
    private int start;
    private int end;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
    public long getRemainTime() {
        return remainTime;
    }

    public void setRemainTime(long remainTime) {
        this.remainTime = remainTime;
    }
    private static int nowPID = 0;

    public PCB(int memorySize, long needTime) {
        PID = ++nowPID;
        this.state = ProcessState.CREATE;
        this.statename = "CREATE";
        this.memorySize = memorySize;
        this.needTime = needTime;
        createTime = new Date();
        this.start = 0;
        this.end = 0;
        this.remainTime = needTime;
    }

    public int getPID() {
        return PID;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public ProcessState getState() {
        return state;
    }

    public void setState(ProcessState state) {
        this.state = state;
        switch (state){
            case CREATE:
                statename = "CREATE";
                break;
            case STOP:
                statename = "STOP";
                break;
            case READY:
                statename = "READY";
                break;
            case BLOCK:
                statename = "BLOCK";
                break;
            case RUNNING:
                statename = "RUNNING";
                break;
            case FINISH:
                statename = "FINISH";
            default:
                break;
        }
        mainFrame.pcbList.repaint();
    }

    public int getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(int memorySize) {
        this.memorySize = memorySize;
    }

    public Memory getMemory() {
        return memory;
    }

    public long getNeedTime() {
        return needTime;
    }

    public void setNeedTime(int needTime) {
        this.needTime = needTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setMemory(Memory memory) {
        this.memory = memory;
        this.memory.setPid(this.PID);
    }

    @Override
    public String toString() {
        String s =  "PID = " + PID +
                "\nstate = " + state +
                "\nmemorySize = " + memorySize +
                "\nneedTime = " + needTime +
                "\ncreateTime = " + createTime.getTime();

        if(end!=0)
            s+= "\nstart = "+ start+"\nend = "+end;
        if(endTime!=null)
            s+="\nendTime = " + endTime.getTime();
        return s;
    }

    public String toString1() {
        String s =  "PID = " + PID +
                "\tstate = " + state ;
        if(this.state!=ProcessState.CREATE||this.state!=ProcessState.FINISH||this.state!=ProcessState.STOP){
            s +="\tmemory["+start+", "+end+"]\n";
        }

        return s;
    }
}
