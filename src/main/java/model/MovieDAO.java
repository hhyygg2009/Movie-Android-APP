package model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.List;
import java.util.Vector;

public class MovieDAO {


	ResultSetMetaData rsmd;
	BaseDao db;
	List<Movie> movies;
	ResultSet rs;
	Movie m;
	Vector<String> columnNames;

	public MovieDAO() {
		// TODO Auto-generated constructor stub
		if (db == null)
			db = new BaseDao();
	}


	// 构造函数
	public List<Movie> getMoives() {

		String sql = "select id,title,score,title_sub,story,classid,releasetime,duration,regionid,langid from movie";
		movies = new Vector<Movie>();
//		columnNames=new Vector<String>();

		rs = db.executeQuery(sql, null);
		try {
//			rsmd = rs.getMetaData();
//			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
//				columnNames.add(rsmd.getColumnName(i));
//			}
			while (rs.next()) {


				movies.add(new Movie(String.valueOf(rs.getInt(1)), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)));//Movie对象装入数组

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movies;

	}

	public boolean add(Movie movie) {
		String[] p;
		List<String> moiveList;
		//转换为字符串数组，作为参数
		moiveList = movie.getMoiveList();
		p = moiveList.toArray(new String[moiveList.size()]);

		String sql = "insert into movie(id,title,score,title_sub,story,classid,releasetime,duration,regionid,langid) values(?,?,?,?,?,?,?,?,?,?)";

		try {
			if (db.executeUpdate(sql, p) > 0)
				return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean del(Movie movie) {
		return del(Integer.valueOf(movie.id));
	}

	public boolean del(int id) {
		try {
			String[] p = new String[1];
			p[0] = String.valueOf(id);
			String sql = "delete from movie where id=?";

			if (db.executeUpdate(sql, p) > 0)
				return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	//public class GetInfoAction {
//	jbdel.addActionListener(new ActionListener() {
//
//		@Override
//		public void actionPerformed(ActionEvent e) {
//
//			if (e.getActionCommand() == "删除一行") {
//				if(jt.getSelectedRow()==-1)
////					javax.swing.JOptionPane.showMessageDialog(null,"111");
//					Menu.setStatus("未选择删除的行");
//
//				else {
//				String p[] = new String[1];
//				p[0] = "" + jt.getValueAt(jt.getSelectedRow(), 0);
//
//				if(del(p)) {
//					Menu.setStatus("删除行成功");
//				}
//
//				tablereload();
//				}
//			}
//
//		}
//
//	});
//
//	jbaddnew.addActionListener(new ActionListener() {
//
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			if (e.getActionCommand() == "新增一行") {
//				String[] p = new String[t.length];
//				Vector h = new Vector();
//				for (int i = 0; i < t.length; i++) {
//
//					p[i] = t[i].getText();
//					// hang.add(p[i]);
//				}
//				// jtm.addRow(hang);
//				if(add(p)) {
//					Menu.setStatus("新增行成功");
//				}
//
//				tablereload();
//
//			}
//
//		}
//
//
//	});
//
//}


}
