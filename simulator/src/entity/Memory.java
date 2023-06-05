package entity;

public class Memory implements Comparable<Memory>{
    private int start;
    private int end;
    private int size;
    private boolean isUse;

    public boolean isUse() {
        return isUse;
    }

    public void setUse(boolean use) {
        isUse = use;
    }


    public Memory(int start, int end, int size,boolean isUse) {
        this.start = start;
        this.end = end;
        this.size = size;
        this.isUse = isUse;
    }

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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public int compareTo(Memory e){
        return  e.size - this.size;
    }

}
