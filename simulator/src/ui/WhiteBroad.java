package ui;

//import common.entity.Ellipse;
//import common.entity.Line;
//import server.DataBuffer;

import entity.DataBuffers;
import entity.Memory;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class WhiteBroad extends JPanel  {

    private static final long serialVersionUID = 1141241251L;
    Font f;
    WhiteBroad(){
        f=new Font(null,Font.PLAIN,25);
    }

    public void paint(Graphics g) {
        super.paint(g);

        int height = this.getHeight();
        int weight = this.getWidth();

        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(f);

        int x = weight/5;
        int y = height/20;
        int fy = (int)height/DataBuffers.memorySize*5/6;


        for(int i = 0;i< DataBuffers.memoryList.size();i++){
            Memory memory = DataBuffers.memoryList.get(i);
            if(!memory.isUse()){
                g2.setColor(new Color(140,200,145));
            }
            else
                g2.setColor(new Color(200,100,100));

            g2.fillRect(x,y,x*3,fy*memory.getSize());
            g2.setColor(new Color(0,0,0));
            g2.drawRect(x,y,x*3,fy*memory.getSize());

            if(memory.getPid()!=0)
                g2.drawString("PID: "+String.valueOf(memory.getPid())+" "+String.valueOf(memory.getSize())+"(kb)",weight/3,y+fy*memory.getSize()/2);
            else
                g2.drawString(String.valueOf(memory.getSize())+"(kb)",weight/5*2,y+fy*memory.getSize()/2);

            y+=fy*memory.getSize();
        }

    }

}
