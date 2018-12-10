package Login;

import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;
import com.*;
import main.*;

public class ChangePassword extends Management {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPasswordField oldPasswordText = new JPasswordField();
	private JPasswordField newPassword1Text = new JPasswordField();
	private JPasswordField newPassword2Text = new JPasswordField();

	JLabel oldPasswordLabel = new JLabel("密码:");
	JLabel newPassword1Label = new JLabel("新密码:");
	JLabel newPassword2Label = new JLabel("确认密码");
	JButton confirmButton = new JButton("确认");
	JButton cancelButton = new JButton("取消");

	public void runChangePassword(JPanel changePasswordPanel) {
		changePasswordPanel.setLayout(null);

		// 创建 JLabel
		oldPasswordLabel.setBounds(40, 20, 80, 25);
		changePasswordPanel.add(oldPasswordLabel);
		oldPasswordText.setBounds(100, 20, 165, 25);
		changePasswordPanel.add(oldPasswordText);

		// "新密码"的Label&文本域
		newPassword1Label.setBounds(40, 50, 80, 25);
		changePasswordPanel.add(newPassword1Label);
		newPassword1Text.setBounds(100, 50, 165, 25);
		changePasswordPanel.add(newPassword1Text);

		// "确认密码"的Label&文本域
		newPassword2Label.setBounds(40, 80, 80, 25);
		changePasswordPanel.add(newPassword2Label);
		newPassword2Text.setBounds(100, 80, 165, 25);
		changePasswordPanel.add(newPassword2Text);

		// 创建按钮
		confirmButton.setBounds(190, 110, 70, 25);
		changePasswordPanel.add(confirmButton);
		confirmButton.addActionListener(new confirmHandler());

		// 创建取消按钮
		cancelButton.setBounds(100, 110, 70, 25);
		changePasswordPanel.add(cancelButton);
		cancelButton.addActionListener(new cancelHandler());
	}

	class confirmHandler extends Login implements ActionListener {

		/**
		 * 
		 */
		private static final long serialVersionUID = 8238638092950733996L;

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			SQL confirm = new SQL();
			String ID = userText.getText();
			String oldPassword = new String(oldPasswordText.getPassword());
			String newPassword1 = new String(newPassword1Text.getPassword());
			String newPassword2 = new String(newPassword2Text.getPassword());
			oldPassword = MD5.MD5WithoutSalt(oldPassword);

			// 修改密码的SQL语句
			if (newPassword1.equals(newPassword2)) {
				confirm.sqlLines = "update 系统用户表  set 密码=? where 密码=? and (用户ID=? or 员工姓名=?)";
				confirm.pre();
				try {
					confirm.preparedStatement.setString(1, MD5.MD5WithoutSalt(newPassword1));
					confirm.preparedStatement.setString(2, oldPassword);
					confirm.preparedStatement.setString(3, ID);
					confirm.preparedStatement.setString(4, ID);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				confirm.exeSql();
				if (confirm.effectedLines > 0) {
					JOptionPane.showMessageDialog(null, "密码修改成功", "成功", JOptionPane.PLAIN_MESSAGE);
					Frame.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "密码错误,请重新输入", "错误", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "两次密码不相同,请重新输入", "错误", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	class cancelHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// 关闭修改密码的窗口
			Frame.dispose();
		}
	}
}