package ui;

import com.sun.org.apache.bcel.internal.generic.FALOAD;
import entity.DataBuffers;
import entity.MyCellRenderer;
import entity.PCB;
import entity.PCBListModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class mainFrame extends JFrame {
    /** 进程列表 */
    public static JList pcbList;
    /** 可视化面板*/
    public static WhiteBroad whiteBroad;
    /** 内存空间*/
    public static int[] memory;
    JTextArea cmdField;
    JTextArea resultArea;
    JTextArea pcbText;
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

        memory  = new int[DataBuffers.memorySize];

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
        cmdField = new JTextArea();
        cmdField.setPreferredSize(new Dimension (400,100));
        cmdField.setFont(font1);
        cmd.add(label);
        cmd.add(cmdField);
        cmdPane.add(cmd);

        //信息提示面板
        String s = new String("内存大小："+ DataBuffers.memorySize+"\n内存调度策略："+
                DataBuffers.dispatch +"\n内存分配："+DataBuffers.memoryAllocation+"\n指令语法: \n"+
                "creatproc 内存大小(kb) 运行时长(s)\nkillproc 进程号\niostrartproc 进程号\niofinishproc 进程号\npsproc\nmem");
        JTextArea hintlab = new JTextArea(s);
        hintlab.setFont(font);
        hintlab.setEditable(false);
        hintlab.setPreferredSize(new Dimension(300,300));
        cmdPane.add(hintlab,BorderLayout.NORTH);

        //结果面板
        resultArea = new JTextArea();
        resultArea.setPreferredSize(new Dimension (300,400));
        resultArea.setEditable(false);
        cmdPane.add(resultArea,BorderLayout.SOUTH);
        resultArea.setFont(font);


////
//        JSplitPane splitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
//                cmdPane, pcbPane);
//        splitPane.setDividerLocation(200);
//        splitPane.setDividerSize(10);
//        splitPane.setEnabled(false);
//        splitPane.setOneTouchExpandable(false);

        //pcb面板
        pcbList = new JList<PCB>();
        DataBuffers.pcbListModel = new PCBListModel();
        pcbList = new JList<>(DataBuffers.pcbListModel);
        pcbList.setCellRenderer(new MyCellRenderer());
        pcbList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        pcbPane.add(new JScrollPane(pcbList,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));

        pcbText = new JTextArea();
        pcbText.setFont(font);
        pcbText.setEditable(false);
        pcbText.setPreferredSize(new Dimension(300,300));
        pcbPane.add(pcbText,BorderLayout.SOUTH);

        //可视化面板
        whiteBroad = new WhiteBroad();
        whiteBroad.setPreferredSize(new Dimension(400,100));
        showPane.add(whiteBroad);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                cmdPane , pcbPane);
        splitPane.setDividerLocation(500);
        splitPane.setDividerSize(20);
        splitPane.setEnabled(false);
//        splitPane.setOneTouchExpandable(false);

//        this.add(cmdPane,BorderLayout.WEST);
//        this.add(pcbPane,BorderLayout.CENTER);
        this.add(splitPane,BorderLayout.CENTER);
        this.add(showPane,BorderLayout.EAST);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        //发送文本消息
        cmdField.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode() == Event.ENTER){
                    enterCommand();
                }
            }
        });

        pcbList.addMouseListener(new MouseAdapter() //列表框添加鼠标事件
        {
            public void mousePressed(MouseEvent e)
            {
                pcbText.setText(((PCB)pcbList.getSelectedValue()).toString());
            }
        });
    }

    public void enterCommand(){
        String content =  cmdField.getText();
        String[] argc = content.split("\\s+");
        if(argc[0].equals("creatproc")){
            if(argc.length!=3){
                resultArea.setText("指令无法被解析");
            }else creatproc(argc[1],argc[2]);
        }else if(argc[0].equals("killproc")){
            resultArea.setText(" ");
        }else if(argc[0].equals("iostrartproc")){
            resultArea.setText(" ");
        }else if(argc[0].equals("iofinishproc")){
            resultArea.setText(" ");
        }else if(argc[0].equals("psproc")){
            resultArea.setText(" ");
        }else if(argc[0].equals("mem")){
            resultArea.setText(" ");
        }else if(argc[0].equals("")){
            resultArea.setText(" ");
        }else{
            resultArea.setText("指令无法被解析");
        }

        InputMap inputMap = cmdField.getInputMap();
        ActionMap actionMap = cmdField.getActionMap();
        Object transferTextActionKey = "TRANSFER_TEXT";
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),transferTextActionKey);
        actionMap.put(transferTextActionKey, new AbstractAction() {
            private static final long serialVersionUID = 7041841945830590229L;
            public void actionPerformed(ActionEvent e) {
                cmdField.setText("");
                cmdField.requestFocus();
            }
        });
        cmdField.setText("");
    }

    public void creatproc(String memory,String time){
        int m=0,t=0;
        try{
            m=Integer.valueOf(memory);
            t=Integer.valueOf(time);
        }catch (Exception e){
            resultArea.setText("指令无法被解析");
            return;
        }
        PCB pcb = new PCB(m,t);
        DataBuffers.pcbListModel.addElement(pcb);
        resultArea.setText("进程创建成功!\n"+pcb.toString());
    }
}
