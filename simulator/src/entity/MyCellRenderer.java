package entity;


import javax.swing.*;
import java.awt.*;


//设置在线用户
public class MyCellRenderer extends JLabel implements ListCellRenderer {
    private static final long serialVersionUID = 3460394416991636990L;

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        PCB pcb = (PCB)value;
        String name = pcb.getPID()+" 状态："+pcb.getState();
        setText(name);
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        setEnabled(list.isEnabled());
        setFont(list.getFont());
        setOpaque(true);
        return this;
    }
}
