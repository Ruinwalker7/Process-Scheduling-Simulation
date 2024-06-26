package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

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

    public static Queue<PCB> createQueue;
    public static Queue<PCB> readyQueue;
    public static List<Memory> memoryList;

    public static Map<Integer,PCB> pcbMap;
    static{
        memorySize=64;
        dispatch = "FCFS";
        memoryAllocation = "Variable Partition";
    }
    private DataBuffers(){}
}
