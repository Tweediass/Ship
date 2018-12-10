package Login;

import javax.swing.*;
import com.*;
import main.*;
import java.awt.event.*;
import java.sql.SQLException;

/**
 * @Author: Meredith
 * @Date: 2018-10-09 10:54
 * @Program: ShipManageSystem
 * @Description: 登陆界面
 **/
public class Login extends GUI {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JTextField userText = new JTextField();
	public static JPasswordField passwordText = new JPasswordField();
	public static JFrame signupFrame = new JFrame("注册");

	JLabel userLabel = new JLabel("用户名:");
	JLabel passwordLabel = new JLabel("密码:");
	JButton loginButton = new JButton("登陆");
	JButton signupButton = new JButton("注册");

	public void runLogin(JPanel changePasswordPanel) {
		changePasswordPanel.setLayout(null);

		// 创建 JLabel
		userLabel.setBounds(40, 20, 80, 25);
		changePasswordPanel.add(userLabel);
		userText.setBounds(100, 20, 165, 25);
		changePasswordPanel.add(userText);

		// 输入密码的文本域
		passwordLabel.setBounds(40, 50, 80, 25);
		changePasswordPanel.add(passwordLabel);
		passwordText.setBounds(100, 50, 165, 25);
		changePasswordPanel.add(passwordText);
		// 创建登录按钮
		loginButton.setBounds(190, 80, 70, 25);
		changePasswordPanel.add(loginButton);
		changePasswordPanel.setFocusable(true);
		loginButton.addKeyListener(new keyHandler());
		loginButton.addActionListener(new loginHandler());

		// 创建注册按钮
		signupButton.setBounds(100, 80, 70, 25);
		changePasswordPanel.add(signupButton);
		signupButton.addActionListener(new signupHandler());
	}

	// 回车键监听器
	class keyHandler extends Login implements KeyListener {

		/**
		 * 
		 */
		private static final long serialVersionUID = 2508902430148771059L;

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			if (e.getKeyChar() == KeyEvent.VK_ENTER) {
				SQL login = new SQL();
				String ID = userText.getText();
				String password = new String(passwordText.getPassword());
				password = MD5.MD5WithoutSalt(password);
				login.sqlLines = "select 用户ID,密码 from 系统用户表 where 密码=? and (用户ID=? or 员工姓名=?)";// SQL语句
				login.pre();
				try {
					login.preparedStatement.setString(1, password);
					login.preparedStatement.setString(2, ID);
					login.preparedStatement.setString(3, ID);
				} catch (SQLException e0) {
					e0.printStackTrace();
				}
				login.exeSelect();// 执行SQL语句
				if (login.result[1][1] == null) {
					JOptionPane.showMessageDialog(null, "密码错误,请重新输入", "密码错误", JOptionPane.ERROR_MESSAGE);
				} else {
					GUI B = new GUI();
					B.run1();
				}

			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

	}

	class loginHandler extends Login implements ActionListener {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			SQL login = new SQL();
			String ID = userText.getText();
			String password = new String(passwordText.getPassword());
			password = MD5.MD5WithoutSalt(password);
			// System.out.println(password);
			login.sqlLines = "select 用户ID,密码 from 系统用户表 where 密码=? and (用户ID=? or 员工姓名=?)";// SQL语句
			login.pre();
			try {
				login.preparedStatement.setString(1, password);
				login.preparedStatement.setString(2, ID);
				login.preparedStatement.setString(3, ID);
			} catch (SQLException e0) {
				e0.printStackTrace();
			}
			login.exeSelect();// 执行SQL语句
			// System.out.println(login.result[0][1] + "#####" + login.result[0][2]+"
			// "+login.result[1][1]);
			if (login.result[1][1] == null) {
				JOptionPane.showMessageDialog(null, "密码错误,请重新输入", "密码错误", JOptionPane.ERROR_MESSAGE);
			} else {
				GUI B = new GUI();
				B.run1();
			}
		}
	}

	class signupHandler extends Login implements ActionListener {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			Frame1.setVisible(false);
			JPanel signupPanel = new JPanel();

			signupFrame.setSize(400, 300);
			signupFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			new Signup(signupPanel);
			signupFrame.add(signupPanel);

			// 设置界面可见
			signupFrame.setLocationRelativeTo(null);
			signupFrame.setVisible(true);
			signupFrame.requestFocus();
		}
	}
}
