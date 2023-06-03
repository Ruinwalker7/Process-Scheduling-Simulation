package entity;

/** 静态变量类*/
public class DataBuffers {
    /** 内存大小*/
    public static int memorySize;
    /** 调度方式*/
    public static String dispatch;
    /** 内存分配算法*/
    public static String memoryAllocation ;
    /** PCBJList的Model */
    public static PCBListModel pcbListModel;

    static{
        memorySize=64;
        dispatch = "FCFS";
        memoryAllocation = "Variable Partition";
    }
    private DataBuffers(){}
}
