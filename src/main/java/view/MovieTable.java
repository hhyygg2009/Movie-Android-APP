package view;

import model.BaseDao;
import model.MovieDAO;

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

public class MovieTable extends JPanel {

	// rowData用来存放行数据
	// columnNames存放列名

	private final JPanel p1_1;
	Vector columnNames;
	JTable jt = null;
	JScrollPane jsp = null;
	ResultSetMetaData rsmd;
	BaseDao db;
	JPanel p1;
	JPanel p2;
	JButton jbaddnew;
	JButton jbdel;
	// JComboBox scorecb;
	JTextField[] t;

	Vector movies;
	MovieDAO movieDAO = new MovieDAO();

	public MovieTable() {
		setLayout(new BorderLayout());

		p1 = new JPanel();
//		p2 = new JPanel();
		// panel.add(new JButton("新增一行"));
		columnNames = new Vector<String>();
		tableDataload();


		// 初始化Jtable
		DefaultTableModel jtm = new DefaultTableModel(movies, columnNames);

		jt = new JTable(jtm);

		// 初始化 jsp
		jsp = new JScrollPane(jt);

		// 把jsp放入到jframe
		this.add(jsp, "Center");


		p1_1 = new JPanel();
		p2 = new JPanel();
		jbdel = new JButton("删除一行");


		p1_1.setLayout(new GridLayout(10, 2, 5, 5));
		jbaddnew = new JButton("新增一行");

		// jt.setVisible(false);
		p1_1.add(jbaddnew);
		p1_1.add(jbdel);
		add(p1_1, BorderLayout.EAST);

		JButton jbreload = new JButton("\u5237\u65B0");
		p1_1.add(jbreload);
//		add(p2, "North");

		this.setSize(800, 600);

//		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		Menu.setStatus("电影信息管理");
		this.setVisible(true);


		jbdel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getActionCommand() == "删除一行") {
					if (jt.getSelectedRow() == -1)
//						javax.swing.JOptionPane.showMessageDialog(null,"111");
						Menu.setStatus("未选择删除的行");

					else {
						int row = jt.getSelectedRow();
						String col = (String) jt.getValueAt(row, 0);
						int id = Integer.valueOf(col);

						if (new MovieDAO().del(id)) {
							Menu.setStatus("删除行成功");
						} else {
							Menu.setStatus("删除行失败");
						}

						tablereload();
					}
				}

			}
//			public boolean del(String p[]) {
//				try {
//
//					String sql = "delete from movie where id=?";
//
//					if (db.executeUpdate(sql, p) > 0)
//						return true;
//
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				return false;
//			}

		});

		jbaddnew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == "新增一行") {
//					String[] p = new String[t.length];
//					Vector h = new Vector();
//					for (int i = 0; i < t.length; i++) {
//
//						p[i] = t[i].getText();
//						// hang.add(p[i]);
//					}
//					// jtm.addRow(hang);
//					if(add(p)) {
//						Menu.setStatus("新增行成功");
//					}
//
//					tablereload();


					MovieAdminTab.tabbedPane.setSelectedIndex(1);
				}

			}


//			public boolean add(String p[]) {
//				try {
//
//					String sql = "insert into moive(title,score,info,img) values(?,?,?,?)";
//
//					if (db.executeUpdate(sql, p) > 0)
//						return true;
//
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				return false;
//			}

		});

		jbreload.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tablereload();
			}
		});

		jtm.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				int row = jt.convertRowIndexToView(e.getFirstRow());
				try {
					// for(int i=1;i<GetInfo.this.rsmd.getColumnCount();i++) {
					// System.out.println(e.getColumn());
					String sql = "update movie set "
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

	}

	public void tablereload() {


		tableDataload();

		jt.revalidate();
	}

	private void tableDataload() {
		// TODO Auto-generated method stub
		db = new BaseDao();
		if (movies == null) {
			movies = new Vector();
		}
		movies.clear();
		try {
			ResultSet rs;
//		String sql = "select id,title,score,title_sub,story,classid,releasetime,duration,regionid,langid from movie";
			String sql = "select * from movie";
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
				movies.add(hang);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}


}

