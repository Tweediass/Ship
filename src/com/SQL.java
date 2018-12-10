package com;

import java.sql.*;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * @Author Meredith
 * @Date 2018/9/7 23:20
 * @Param
 * @Return
 * @Description:
 **/
public class SQL {
	public String sqlLines;
	public String result[][] = new String[100][100];
	public int effectedLines = 0;
	public ResultSet rs = null;
	public PreparedStatement preparedStatement = null;
	Connection connection = null;
	public int row = 0;// 行数
	public int col = 1;// 列数

	public void pre() {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=船只资料;user=Tweedia;password=adsl39547";

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection(url);
			preparedStatement = connection.prepareStatement(sqlLines);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//	public void getColName() {
//		try {
//			rs = preparedStatement.executeQuery(); // 执行sql
//		} catch (Exception e) {
//			e.printStackTrace();
//		} /*
//			 * finally { closeStatAndConnAndResultSet(preparedStatement, connection, rs); }
//			 */
//	}

	public void toTable(SQL db, JTable table) {
		/**
		 * @description: 打表方法
		 * @param [db,
		 *            table] db为传入的SQL对象 table为传入的表
		 * @return void
		 * @date: 2018/11/29 20:33
		 */
		// 将列名插入到Vector
		Vector columnNames;
		columnNames = new Vector();
		// 获取列名
		try {
			for (int i = 1; i < db.rs.getMetaData().getColumnCount() + 1; i++) {
				columnNames.add(db.rs.getMetaData().getColumnName(i));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		// 二维容器
		Vector rowData = new Vector();
		for (int i = 1; i < db.col; i++)// 遍历列
		{
			Vector row = new Vector();
			for (int j = 1; j <= db.row; j++)// 遍历行
			{
				row.add(db.result[j][i]);
			}
			rowData.add(row);// 将行添加到Vector
		}
		table.setModel(new DefaultTableModel(rowData, columnNames));// 打表
		closeStatAndConnAndResultSet(preparedStatement, connection, rs);
	}

	public void exeSelect() {
		try {
			col = 1;
			row = 0;
			rs = preparedStatement.executeQuery(); // 执行sql
			// 获取结果
			row = rs.getMetaData().getColumnCount();// 获取行数
			// 清空数组
			for (int t = 0; t < result.length - 1; t++) {
				for (int t1 = 0; t1 < result[t].length; t1++)
					result[t][t1] = null;
			}

			while (rs.next()) {
				for (int i = 1; i <= row; i++) {
					result[i][col] = rs.getString(i);
				}
				col++;
				// System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} /*
			 * finally { closeStatAndConnAndResultSet(preparedStatement, connection, rs); }
			 */

	}

	public void exeSql() {
		try {
			effectedLines = preparedStatement.executeUpdate(); // 执行sql
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeStatAndConnAndResultSet(preparedStatement, connection, rs);
		}
	}

	public static void closeStatAndConn(Statement stat, Connection conn) {
		if (null != stat) {
			try {
				stat.close(); // 关闭statement对象
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (null != conn) {
			try {
				conn.close(); // 关闭连接
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void closeStatAndConnAndResultSet(Statement stat, Connection conn, ResultSet rs) {
		if (null != rs) {
			try {
				rs.close(); // 关闭ResultSet对象
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (null != stat) {
			try {
				stat.close(); // 关闭statement对象
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (null != conn) {
			try {
				conn.close(); // 关闭连接
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}