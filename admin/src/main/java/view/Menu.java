package view;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    public static JLabel welcomeJLabel;
    static JLabel status;
    private final JMenuItem itemMenu;
    JMenuBar menubar; // 菜单条
    JMenu menuFile; // 菜单
    // JMenuItem itemOpen, itemSave; //菜单项
    JMenuItem itemExit;
    JButton lookup;
    JButton addinfo;
    JButton userpanel;
    JPanel panel;
    JMenu menuAdmin;

    public Menu() {
        setVisible(true);

        setTitle("菜单");


        menubar = new JMenuBar();
        menuFile = new JMenu("文件(F)");
        menuAdmin = new JMenu("管理(A)");
        itemMenu = new JMenuItem("\u4E3B\u83DC\u5355");
        itemMenu.addActionListener(new MenuAction(this));
        itemExit = new JMenuItem("退出");
        itemExit.addActionListener(new MenuAction(this));
        // itemOpen = new JMenuItem("打开(O)");
        // itemSave = new JMenuItem("保存(S)");
        // menuFile.add(itemOpen);
        // menuFile.addSeparator();
        // menuFile.add(itemSave);
        menuFile.add(itemMenu);
        menuFile.add(itemExit);
        menubar.add(menuFile); // 将菜单添加到菜单条上

        menubar.add(menuAdmin);
        setJMenuBar(menubar);

        JMenuItem[] m1 = new JMenuItem[3];

        String[] tags = {"查询", "用户面板"};
        for (int i = 0; i < tags.length; i++) {
            m1[i] = new JMenuItem(tags[i]);
            menuAdmin.add(m1[i]);
            m1[i].addActionListener(new MenuAction(this));
        }
        status = new JLabel("欢迎管理员进入管理系统");
        status.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        getContentPane().add(status, "South");

        welcomeJLabel = new JLabel("\u6B22\u8FCE\u6765\u5230\u7535\u5F71\u4FE1\u606F\u7BA1\u7406\u7CFB\u7EDF");
        welcomeJLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeJLabel.setFont(new Font("宋体", Font.PLAIN, 24));
        getContentPane().add(welcomeJLabel, BorderLayout.CENTER);

//		lookup = new JButton("查询");
////		addinfo = new JButton("修改信息");
//		userpanel = new JButton("用户面板");

//		itemExit.addActionListener(new MenuAction());
//		lookup.addActionListener(new MenuAction());
////		addinfo.addActionListener(new MenuAction());
//		userpanel.addActionListener(new MenuAction());

//		panel = new JPanel(new GridLayout(1, 3));
//		panel.add(lookup);
////		panel.add(addinfo);
//		panel.add(userpanel);
//		


        setSize(800, 600);
        setCenter(this);
//		pack();


    }

    static void setCenter(JFrame f) {
        // 得到显示器屏幕的宽、高
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        // 得到窗体的宽、高
        int windowsWidth = f.getWidth();
        int windowsHeight = f.getHeight();
        //System.out.println(windowsWidth+","+windowsHeight);
        f.setBounds((width - windowsWidth) / 2, (height - windowsHeight) / 2, windowsWidth, windowsHeight);

    }

    static void setStatus(String str) {
        status.setText(str);
    }
}

class MenuAction implements ActionListener {
    static JPanel p;
    JFrame f;


    MenuAction(JFrame f) {
        this.f = f;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//		f.getContentPane().remove(1);
//		f.getContentPane().CENTER_ALIGNMENT.removeAll();


        if (p != null) {
            f.getContentPane().remove(p);
            p = null;
            Menu.welcomeJLabel.setVisible(true);
        }

        if (e.getActionCommand() == "查询") {
            p = new MovieAdminTab();
            f.add(p);
        }


        if (e.getActionCommand() == "用户面板") {
            p = new userpanel();
            f.add(p);
        }
//		if (e.getActionCommand() == "修改信息") {
//			new addinfo();
//		}
        if (e.getActionCommand().equals("退出")) {
            System.exit(0);
        }
        if (p != null) {
            Menu.welcomeJLabel.setVisible(false);
        }
        f.validate();
        f.repaint();


    }

}
