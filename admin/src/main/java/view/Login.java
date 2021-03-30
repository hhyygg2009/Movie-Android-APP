package view;

import model.BaseDao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;


public class Login extends JFrame {
    JPanel p;
    JLabel luser;
    JLabel lpwd;
    JTextField tuser;
    JPasswordField tpwd;
    JButton btnConfirm;
    // JButton btnReg;

    public Login() {
        super("用户登录");
        this.setSize(250, 200);
        p = new JPanel(null);


        luser = new JLabel("用户名");
//		System.out.println(Login.class.getResource("/"));
        luser.setIcon(new ImageIcon(Login.class.getResource("/image/usericon.jpg")));
        lpwd = new JLabel("密码");
        lpwd.setIcon(new ImageIcon(Login.class.getResource("/image/passwordicon.jpg")));
        tuser = new JTextField(20);
        tpwd = new JPasswordField(20);
        btnConfirm = new JButton("登录");
        // btnReg=new JButton("注册");

        luser.setBounds(30, 30, 60, 25);
        lpwd.setBounds(30, 60, 60, 25);
        tuser.setBounds(95, 30, 120, 25);
        tpwd.setBounds(95, 60, 120, 25);
        btnConfirm.setBounds(30, 95, 185, 36);
        // btnReg.setBounds(125,90,60,25);
        btnConfirm.addActionListener(new LoginAction(this));
        // btnReg.addActionListener(new LoginAction(this));
        p.add(luser);
        p.add(tuser);
        p.add(lpwd);
        p.add(tpwd);
        p.add(btnConfirm);
        // p.add(btnReg);
        getContentPane().add(p);

        Menu.setCenter(this);
        this.setVisible(true);
    }


}


class LoginAction implements ActionListener {
    JPanel p;
    JLabel luser;
    JLabel lpwd;
    JTextField tuser;
    JPasswordField tpwd;
    JButton btnConfirm;
    // JButton btnReg;


    Login frame;

    public LoginAction(Login frame) {
        this.frame = frame;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "登录") {
            String user = frame.tuser.getText();
            String pwd = String.valueOf(frame.tpwd.getPassword());
            if (!user.equals("") && !pwd.equals("")) {
                if (LoginCheck(user, pwd)) {
                    frame.setVisible(false);
                    MovieAdminMain.menuFrame = new Menu();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(frame.p,
                            "输入信息有误");
                }
            } else {
                javax.swing.JOptionPane.showMessageDialog(frame.p, "输入信息不能为空");
            }
        }
    }

    public boolean LoginCheck(String user, String pwd) {
        BaseDao db = new BaseDao();
        // System.out.println(user+pwd);
        ResultSet rs;
        String sql = "select username,pwd from users where username=? and pwd=?";
        String[] p = new String[2];

        p[0] = user;
        p[1] = pwd;

        try {

            rs = db.executeQuery(sql, p);

            if (rs.next())
                return true;
            db.closeAll();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return false;
    }

}
