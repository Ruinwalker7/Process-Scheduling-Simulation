package entity;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

//在线用户模型
public class PCBListModel extends AbstractListModel {
    private static final long serialVersionUID = -3903760573171074301L;
    private List<PCB> PCBs;

    public PCBListModel() {
        PCBs = new ArrayList<>();
    }

    public void addElement(Object object) {
        if (PCBs.contains(object)) {
            return;
        }
        int index = PCBs.size();
        PCBs.add((PCB)object);
        fireIntervalAdded(this, index, index);
    }

    public boolean removeElement(Object object) {
        int index = PCBs.indexOf(object);
        if (index >= 0) {
            fireIntervalRemoved(this, index, index);
        }
        return PCBs.remove(object);
    }

    public int getSize() {
        return PCBs.size();
    }

    public Object getElementAt(int i) {
        return PCBs.get(i);
    }

    public List<PCB> getOnlineUsers() {
        return PCBs;
    }
}
