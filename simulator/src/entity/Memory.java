package entity;

public class Memory implements Comparable<Memory>{
    private int start;
    private int end;
    private int size;
    private boolean isUse;
    private int pid;

    public boolean isUse() {
        return isUse;
    }

    public void setUse(boolean use) {
        isUse = use;
    }

    public Memory(int start, int end,boolean isUse) {
        this.start = start;
        this.end = end;
        this.size = end - start+1;
        this.isUse = isUse;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
        this.size =this.end - this.start+1;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
        this.size =this.end - this.start+1;
    }

    public int getSize() {
        return size;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    @Override
    public int compareTo(Memory e){
        return  e.size - this.size;
    }

    @Override
    public String toString() {
        return "Memory{" +
                "start=" + start +
                ", end=" + end +
                ", size=" + size +
                ", isUse=" + isUse +
                '}';
    }
    public String toString1() {
        return "Memory[" + start +
                ", " + end +
                "]\tSize=" + size +
                "\tIsUse=" + isUse+"\n";
    }
}
