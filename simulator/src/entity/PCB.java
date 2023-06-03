package entity;

import java.util.Date;

public class PCB {
    private int PID;

    private int state;

    private String statename;
    private int memory;

    private int needTime;

    private Date createTime;

    private Date endTime;

    private static int nowPID = 0;

    public PCB(int memory, int needTime) {
        PID = ++nowPID;
        this.statename = "CREATE";
        this.memory = memory;
        this.needTime = needTime;
        createTime = new Date();
    }

    public int getPID() {
        return PID;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        switch (state){
            case 0:
                statename = "CREATE";
            case 1:
                statename = "STOP";
            case 2:
                statename = "READY";
            case 3:
                statename = "BLOCK";
            case 4:
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

    public int getNeedTime() {
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
        return "PID = " + PID +
                "\nstatename = " + statename  +
                "\nmemory = " + memory +
                "\nneedTime = " + needTime +
                "\ncreateTime = " + createTime +
                "\nendTime = " + endTime;
    }
}
