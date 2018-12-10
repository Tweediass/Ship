package main;

import java.awt.event.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import Login.*;
import com.*;
import MySystem.*;

import BasicData.*;

public class Management extends Login {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static JFrame Frame = new JFrame("修改密码");
	public static JTextPane textPane = new JTextPane();

	public Management() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 697, 634);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(284, 10, 379, 39);
		contentPane.add(panel);
		panel.setLayout(null);

		// 重新登陆按钮
		JButton btnNewButton = new JButton("\u91CD\u65B0\u767B\u5F55");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 关闭登陆后的界面
				dispose();

				// 重新打开登录界面
				new GUI().run();

				// 清空登录数据的缓存
				userText.setText("");
				passwordText.setText("");
			}
		});
		btnNewButton.setBounds(101, 10, 81, 23);
		panel.add(btnNewButton);

		// 退出系统按钮
		JButton btnNewButton_1 = new JButton("\u9000\u51FA\u7CFB\u7EDF");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 退出系统
				System.exit(0);
			}
		});
		btnNewButton_1.setBounds(10, 10, 81, 23);
		panel.add(btnNewButton_1);

		// 修改密码按钮
		JButton btnNewButton_2 = new JButton("\u4FEE\u6539\u5BC6\u7801");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel changePasswordPanel = new JPanel();

				Frame.setSize(400, 300);
				Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				// 用户操作顶层容器
				ChangePassword cp = new ChangePassword();
				cp.runChangePassword(changePasswordPanel);
				Frame.add(changePasswordPanel);

				// 设置界面可见
				Frame.setLocationRelativeTo(null);
				Frame.setVisible(true);
				Frame.requestFocus();

			}
		});
		btnNewButton_2.setBounds(192, 10, 81, 23);
		panel.add(btnNewButton_2);

		// 消息提示按钮 即警告按钮
		JButton btnNewButton_3 = new JButton("\u6D88\u606F\u63D0\u793A");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_3.setBounds(283, 10, 81, 23);
		panel.add(btnNewButton_3);

		// 判断当前用户权限是否大于等于4(确定是否拥有管理员权限)
		SQL permission = new SQL();
		String id = userText.getText();
		String password = MD5.MD5WithoutSalt(new String(passwordText.getPassword()));
		permission.sqlLines = "select 权限 from 系统用户表 where 密码=? and (用户ID =? or 员工姓名=?) ";
		permission.pre();
		try {
			permission.preparedStatement.setString(1, password);
			permission.preparedStatement.setString(2, id);
			permission.preparedStatement.setString(3, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		permission.exeSelect();
		// System.out.println("权限:" + permission.result[1][1]);
		if (Integer.parseInt(permission.result[1][1]) >= 4) {
			// 系统管理模块按钮(管理员专用)
			JButton btnNewButton_4 = new JButton("\u7CFB\u7EDF\u7BA1\u7406");
			btnNewButton_4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new MySystem.Panel();
				}
			});
			btnNewButton_4.setBounds(547, 78, 93, 31);
			contentPane.add(btnNewButton_4);
		}

		// 资料查询与报表处理模块按钮
		JButton btnNewButton_5 = new JButton("\u8D44\u6599\u67E5\u8BE2\u4E0E\u62A5\u8868\u5904\u7406");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_5.setBounds(67, 152, 177, 60);
		contentPane.add(btnNewButton_5);

		// 船只基本资料管理模块
		JButton btnNewButton_6 = new JButton("\u8239\u53EA\u57FA\u672C\u8D44\u6599\u7BA1\u7406");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// dispose();
				new BasicData.Panel();
			}
		});
		btnNewButton_6.setBounds(340, 152, 177, 60);
		contentPane.add(btnNewButton_6);

		// 船只营运证书资料管理模块
		JButton btnNewButton_7 = new JButton("\u8239\u53EA\u8425\u8FD0\u8BC1\u4E66\u8D44\u6599\u7BA1\u7406");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_7.setBounds(67, 255, 177, 60);
		contentPane.add(btnNewButton_7);

		// 船只检验证书资料管理模块
		JButton btnNewButton_8 = new JButton("\u8239\u53EA\u68C0\u9A8C\u8BC1\u4E66\u8D44\u6599\u7BA1\u7406");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InspectionCtf.Panel();
			}
		});
		btnNewButton_8.setBounds(340, 255, 177, 60);
		contentPane.add(btnNewButton_8);

		// 船只安检证书资料管理模块
		JButton btnNewButton_9 = new JButton("\u8239\u53EA\u5B89\u68C0\u8BC1\u4E66\u8D44\u6599\u7BA1\u7406");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton_9.setBounds(67, 360, 177, 60);
		contentPane.add(btnNewButton_9);

		// 船只国籍配员证书资料管理模块
		JButton btnNewButton_10 = new JButton(
				"\u8239\u53EA\u56FD\u7C4D\u914D\u5458\u8BC1\u4E66\u8D44\u6599\u7BA1\u7406");
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new NationalCtf.Panel();
			}
		});
		btnNewButton_10.setBounds(340, 360, 177, 60);
		contentPane.add(btnNewButton_10);

		// 船只缴纳航道费情况管理模块
		JButton btnNewButton_11 = new JButton("\u8239\u53EA\u7F34\u7EB3\u822A\u9053\u8D39\u60C5\u51B5\u7BA1\u7406");
		btnNewButton_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_11.setBounds(67, 464, 177, 60);
		contentPane.add(btnNewButton_11);

		// 船只缴纳水运费情况管理模块
		JButton btnNewButton_12 = new JButton("\u8239\u53EA\u7F34\u7EB3\u6C34\u8FD0\u8D39\u60C5\u51B5\u7BA1\u7406");
		btnNewButton_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Waterage.Panel();
			}
		});
		btnNewButton_12.setBounds(340, 464, 177, 60);
		contentPane.add(btnNewButton_12);

		textPane.setBounds(486, 534, 185, 61);
		contentPane.add(textPane);
		textPane.setEditable(false);
	}

	public void setWarning() {
		// select那些未过期的证书
		SQL db = new SQL();
		db.sqlLines = "select 证书有效期至,船名,证书名 from 各证书有效期";
		db.pre();
		db.exeSelect();
		Calendar cal = Calendar.getInstance();
		// 获取当前日期 并加一个月
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar nowCal = Calendar.getInstance();
		nowCal.getTime();
		String now = sdf.format(nowCal.getTime());

		Date date = null;
		String text = "                 Warning\n";
		{
			for (int j = 1; j < db.row; j++)// 遍历行
			{
				// 如果当前日期加1个月小于过期日
				try {
					date = sdf.parse(db.result[1][j]);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				cal.setTime(date);
				String dbDate = sdf.format(cal.getTime());

				if (now.compareTo(dbDate) <= 0) {
					nowCal.add(Calendar.MONTH, +1);
					String now1 = sdf.format(nowCal.getTime());
					if (now1.compareTo(dbDate) >= 0)
						text += "船只 [" + db.result[2][j] + "] 的证书 [" + db.result[3][j] + "] 将于一个月内过期\n";
				}

			}
		}
		textPane.setText(text);
	}
}
