package entity;

import java.util.Date;

public class PCB {
    private int PID;

    private ProcessState state;



    private String statename;
    private int memory;

    private long needTime;
    private long remainTime;
    private Date createTime;

    private Date endTime;

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

    public PCB(int memory, long needTime) {
        PID = ++nowPID;
        this.state = ProcessState.CREATE;
        this.statename = "CREATE";
        this.memory = memory;
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
            default:
                break;
        }

    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
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

    @Override
    public String toString() {
        if(end!=0)
            return "PID = " + PID +
                    "\nstate = " + state +
                    "\nstatename = " + statename  +
                    "\nmemory = " + memory +
                    "\nneedTime = " + needTime +
                    "\ncreateTime = " + createTime.getTime() +
                    "\nendTime = " + endTime.getTime() +"\nstart = "+ start+"\nend = "+end+"\n";
        else
        return "PID = " + PID +
                "\nstate = " + state +
                "\nstatename = " + statename  +
                "\nmemory = " + memory +
                "\nneedTime = " + needTime +
                "\ncreateTime = " + createTime.getTime() +
                "\nendTime = " + endTime.getTime();
    }
}
