package ui;

import javax.swing.*;
import java.awt.*;

public class mainFrame extends JFrame {
    /** 进程列表 */
    public static JList pcbList;
    /** 可视化面板*/
    public static WhiteBroad whiteBroad;
    public mainFrame(){
        init();
        setVisible(true);
    }

    public void init(){
        this.setTitle("模拟器");//设置服务器启动标题
        this.setResizable(false);

        //设置默认窗体在屏幕中央
        int x = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int y = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setSize(x-400, y-200);
        this.setLocation((x - this.getWidth()) / 2, (y-this.getHeight())/ 2);

        Font font = new Font("黑体",0,20);

        //交互主面板
        JPanel cmdPane = new JPanel();
        cmdPane.setLayout(new BorderLayout());

        //进程主面板
        JPanel pcbPane = new JPanel();
        pcbPane.setLayout(new BorderLayout());

        //可视化主面板
        JPanel showPane = new JPanel();
        showPane.setLayout(new BorderLayout());

        //指令panel
        JPanel cmd = new JPanel();
        cmd.setLayout(new FlowLayout());

        Label label = new Label();
        label.setText("指令：");
        label.setFont(font);


        //指令输入
        Font font1 = new Font("黑体",0,30);
        JTextArea cmdField = new JTextArea();
        cmdField.setPreferredSize(new Dimension (400,100));
        cmdField.setFont(font1);
        cmd.add(label);
        cmd.add(cmdField);
        cmdPane.add(cmd);

        //信息提示面板
        JLabel hintlab = new JLabel("dasjidjasijfa");
        hintlab.setPreferredSize(new Dimension(300,300));
        cmdPane.add(hintlab,BorderLayout.NORTH);

        //结果面板

        JTextArea resultArea = new JTextArea();
        resultArea.setPreferredSize(new Dimension (300,400));
        cmdPane.add(resultArea,BorderLayout.SOUTH);
        resultArea.setFont(font1);

//        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
//                , cmd);
//        splitPane.setDividerLocation(200);
//        splitPane.setDividerSize(10);
//        splitPane.setEnabled(false);
//        splitPane.setOneTouchExpandable(false);
////
//        JSplitPane splitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
//                cmdPane, pcbPane);
//        splitPane.setDividerLocation(200);
//        splitPane.setDividerSize(10);
//        splitPane.setEnabled(false);
//        splitPane.setOneTouchExpandable(false);

        //pcb面板
        pcbList = new JList<>();
        pcbPane.add(new JScrollPane(pcbList,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));

        //可视化面板
        whiteBroad = new WhiteBroad();
        whiteBroad.setPreferredSize(new Dimension(400,100));
        showPane.add(whiteBroad);

        this.add(cmdPane,BorderLayout.WEST);
        this.add(pcbPane,BorderLayout.CENTER);
        this.add(showPane,BorderLayout.EAST);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
