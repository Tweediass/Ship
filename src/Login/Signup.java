package Login;

import javax.swing.*;
import com.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

/**
 * @Author: Meredith
 * @Date: 2018-10-18 08:41
 * @Program: ShipManageSystem
 * @Description: 增加用户
 **/
public class Signup // extends SystemGUI
{
	private JTextField userText0 = new JTextField();
	private JPasswordField passwordText0 = new JPasswordField();
	private JTextField nameText = new JTextField();
	private JTextField phoneText = new JTextField();

	// JLabel text1 = new JLabel("用户管理");
	// JLabel adduserLable = new JLabel("增加用户");
	JLabel userLable = new JLabel("用户名");
	JLabel passwordLabel = new JLabel("密码");
	JRadioButton isAdmin;
	JRadioButton notAdmini;
	JLabel nameLable = new JLabel("员工姓名");
	JLabel phoneLable = new JLabel("联系电话");

	JButton addButton = new JButton("注册");
	JButton cancelButton = new JButton("取消");

	Signup(JPanel panel) {
		// Font text1_font = new Font("宋体", Font.BOLD, 28);
		// text1.setFont(text1_font);
		panel.setLayout(null);
		// text1.setBounds(300, 0, 120, 80);
		// panel.add(text1);

		// Font lable_font = new Font("宋体", Font.BOLD, 20);
		// adduserLable.setFont(lable_font);
		// adduserLable.setBounds(40, 80, 100, 90);
		// panel.add(adduserLable);

		// 用户名标签
		Font font1 = new Font("宋体", Font.BOLD, 18);
		userLable.setBounds(40, 20, 130, 20);
		userLable.setFont(font1);
		panel.add(userLable);
		// 用户名文本域
		userText0.setBounds(130, 20, 130, 20);
		panel.add(userText0);

		// 密码标签
		passwordLabel.setBounds(40, 50, 130, 20);
		passwordLabel.setFont(font1);
		panel.add(passwordLabel);
		// 密码文本域
		passwordText0.setBounds(130, 50, 130, 20);
		panel.add(passwordText0);

		// 姓名标签
		nameLable.setFont(font1);
		nameLable.setBounds(40, 80, 130, 20);
		panel.add(nameLable);
		// 姓名文本域
		nameText.setBounds(130, 80, 130, 20);
		panel.add(nameText);

		// 电话标签
		phoneLable.setFont(font1);
		phoneLable.setBounds(40, 110, 130, 20);
		panel.add(phoneLable);
		// 电话文本域
		phoneText.setBounds(130, 110, 130, 20);
		panel.add(phoneText);

		// 注册按钮
		addButton.setBounds(200, 150, 60, 30);
		// addButton.setFont(font1);
		panel.add(addButton);
		addButton.addActionListener(new AddUserHandler());

		// 取消按钮
		cancelButton.setBounds(120, 150, 60, 30);
		panel.add(cancelButton);
		cancelButton.addActionListener(new CancelHandler());

	}

	class AddUserHandler extends Login implements ActionListener {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			String ID = userText0.getText();
			String password = new String(passwordText0.getPassword());
			String name = nameText.getText();
			String phone = phoneText.getText();
			// SQL语句

			// System.out.println("ID" + ID + " password" + password + " name" + name + "
			// phone" + phone);

			SQL db = new SQL();// 自行增加了一句

			db.sqlLines = "insert into 系统用户表(用户ID,密码,权限,员工姓名,联系电话) values(?,?,0,?,?)";
			db.pre();
			try {
				db.preparedStatement.setString(1, ID);
				db.preparedStatement.setString(2, MD5.MD5WithoutSalt(password));
				db.preparedStatement.setString(3, name);
				db.preparedStatement.setString(4, phone);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			db.exeSql();// 执行SQL语句
			if (db.effectedLines > 0) {
				JOptionPane.showMessageDialog(null, "注册成功", "", JOptionPane.INFORMATION_MESSAGE);
				signupFrame.dispose();
				Frame1.setVisible(true);
				// 注释了这一句
				// sA.refresh();

			} else {
				JOptionPane.showMessageDialog(null, "注册失败", "", JOptionPane.ERROR_MESSAGE);
			}
			// 清空缓存
			userText.setText("");
			passwordText.setText("");
			userText0.setText("");
			passwordText0.setText("");
			nameText.setText("");
			phoneText.setText("");

		}
	}

	class CancelHandler extends Login implements ActionListener {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			signupFrame.dispose();
			Frame1.setVisible(true);

			// 清空缓存
			userText.setText("");
			passwordText.setText("");
			userText0.setText("");
			passwordText0.setText("");
			nameText.setText("");
			phoneText.setText("");

		}

	}
}
