package model;

import util.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author hhyygg2009
 * 如果没连接，自动连接
 */
public class BaseDao {
	public Connection connection = null;
	int x;
	PreparedStatement ps = null;
	ResultSet rs = null;


	public BaseDao() {
		// TODO Auto-generated constructor stub
		if (connection == null)
			getConnection();//
	}

	//

	/**
	 * 从配置文件获取连接数据库信息
	 */
	public void getConnection() {
		try {
			String url, user, pwd;
			url = Config.getValue("url");
			user = Config.getValue("user");
			pwd = Config.getValue("pwd");
			Class.forName(Config.getValue("driver"));
			connection = DriverManager.getConnection(url, user, pwd);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			javax.swing.JOptionPane.showMessageDialog(null,
					"连接数据库错误" + e.toString());
			e.printStackTrace();
		}
		return;

	}


	/**
	 * 关闭连接
	 */
	public void closeAll() {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (connection != null)
				connection.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param sql sql语句
	 * @param p   参数
	 * @return
	 */
	public ResultSet executeQuery(String sql, String[] p) {
		try {
			ps = connection.prepareStatement(sql);
			if (p != null)
				for (int i = 0; i < p.length; i++)
					ps.setString(i + 1, p[i]);
			rs = ps.executeQuery();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * @param sql
	 * @param p
	 * @return sql语句，p参数、返回修改行数
	 */
	public int executeUpdate(String sql, String[] p) {

		try {
			ps = connection.prepareStatement(sql);
			if (p != null)
				for (int i = 0; i < p.length; i++)
					ps.setString(i + 1, p[i]);
			return ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
