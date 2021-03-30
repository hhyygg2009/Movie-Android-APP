package view;

import model.BaseDao;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class userpanel extends JPanel {

    // rowData用来存放行数据
    // columnNames存放列名
    Vector rowData, columnNames;
    JTable jt = null;
    JScrollPane jsp = null;
    ResultSetMetaData rsmd;
    BaseDao db;

    JPanel p2;
    JButton jbaddnew;
    JButton jbdel;
    // JComboBox scorecb;


    public userpanel() {

        db = new BaseDao();
        db.getConnection();
//		p1 = new JPanel();
        p2 = new JPanel();
        // panel.add(new JButton("新增一行"));
        columnNames = new Vector<String>();
        rowData = new Vector<String>();
        tableDataload();

        // 初始化Jtable
        DefaultTableModel jtm = new DefaultTableModel(rowData, columnNames);
        jtm.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                // TODO Auto-generated method stub
                int row = jt.convertRowIndexToView(e.getFirstRow());
                try {
                    // for(int i=1;i<GetInfo.this.rsmd.getColumnCount();i++) {
                    // System.out.println(e.getColumn());
                    String sql = "update moive set "
                            + rsmd.getColumnName(e.getColumn() + 1) + "='"
                            + jt.getValueAt(e.getFirstRow(), e.getColumn())
                            + "' where " + rsmd.getColumnName(1) + "='"
                            + jt.getValueAt(row, 0) + "'";
                    db.executeUpdate(sql, null);
                    // System.out.println(sql);
                    // }
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }

        });
        jt = new JTable(jtm);

        // 初始化 jsp
        jsp = new JScrollPane(jt);

        // 把jsp放入到jframe
        this.add(jsp, "Center");

//		p1 = new JPanel();
        p2 = new JPanel(new GridLayout(3, 1));
        jbdel = new JButton("移除用户");
        jbdel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand() == "移除用户") {
                    if (jt.getSelectedRow() == -1)
//						javax.swing.JOptionPane.showMessageDialog(null,"111");
                        Menu.setStatus("行不能为空");

                    else {

                        String[] p = new String[1];
                        p[0] = "" + jt.getValueAt(jt.getSelectedRow(), 0);

                        del(p);
                        tablereload();

                    }
                }

            }

            public boolean del(String[] p) {
                try {

                    String sql = "delete from users where id=?";

                    if (db.executeUpdate(sql, p) > 0)
                        return true;

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }

        });
        p2.add(jbdel);

        jbaddnew = new JButton("新增用户");
        jbaddnew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand() == "新增用户") {
                    new adduser();
                    tablereload();
                }

            }


        });

        p2.add(jbaddnew);

//		add(p1, "South");
        add(p2, "East");

        this.setSize(400, 300);

//		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		Menu.setCenter(this);
        this.setVisible(true);

    }

    // 构造函数
    void tableDataload() {
        try {
            ResultSet rs;
            String sql = "select * from users";
            rs = db.executeQuery(sql, null);
            rsmd = rs.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                columnNames.add(rsmd.getColumnName(i));
            }
            while (rs.next()) {
                // rowData行数据，可以存放多行
                Vector hang = new Vector();
                for (int i = 1; i <= rsmd.getColumnCount(); i++)
                    hang.add(rs.getString(i));
                // 加入到rowData
                rowData.add(hang);
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void tablereload() {
        columnNames.clear();
        rowData.clear();
        tableDataload();
        jt.revalidate();
    }

}

class adduser extends JDialog {
    private final JButton jbaddnew;
    JTextField[] t;
    JPanel p1;

    //	adduser(JFrame f){
    public adduser() {
        // TODO Auto-generated constructor stub

        super(MovieAdminMain.menuFrame, "新增用户", true);
        p1 = new JPanel();
        String[] tag = {"用户名", "密码"};
        t = new JTextField[tag.length];

        for (int i = 0; i < tag.length; i++) {
            t[i] = new JTextField(10);
            p1.add(new JLabel(tag[i]));
            p1.add(t[i]);
        }

        jbaddnew = new JButton("新增");
        jbaddnew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand() == "新增") {
                    String[] p = new String[t.length];
                    Vector h = new Vector<String>();
                    for (int i = 0; i < t.length; i++) {

                        p[i] = t[i].getText();
                        // hang.add(p[i]);
                    }
                    // jtm.addRow(hang);
                    add(p);
                    dispose();
                }
            }


        });
        p1.add(jbaddnew);
        add(p1);
        setSize(200, 120);
//		Menu.setCenter(this);
//		setLocationRelativeTo(f); 
        setVisible(true);

    }

    public boolean add(String[] p) {
        try {
            BaseDao db = new BaseDao();
            db.getConnection();
            String sql = "insert into users(username,pwd) values(?,?)";

            if (db.executeUpdate(sql, p) > 0)
                return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}