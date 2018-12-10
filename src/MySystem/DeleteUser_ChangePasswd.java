package MySystem;

import com.MD5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * @Author: Meredith
 * @CreateDate: 2018-11-06 22:03
 * @Project: ShipManageSystem
 * @Package: System
 * @Description: ɾ���û�
 **/
public class DeleteUser_ChangePasswd extends Panel {
	private JTextField userText = new JTextField();
	private JPasswordField passwordText = new JPasswordField();

	JLabel adduserLable = new JLabel("ɾ���û�");
	JLabel userLable = new JLabel("�û���");
	JLabel passwordLabel = new JLabel("����");
	JButton deleteButton = new JButton("ɾ���û�");
	JButton updatePasswdButton = new JButton("�޸�����");

	DeleteUser_ChangePasswd(JPanel panel) {
		Font lable_font = new Font("����", Font.BOLD, 20);
		adduserLable.setFont(lable_font);
		adduserLable.setBounds(400, 80, 100, 90);
		panel.add(adduserLable);

		Font font1 = new Font("����", Font.BOLD, 18);
		userLable.setBounds(500, 50, 80, 80);
		userLable.setFont(font1);
		panel.add(userLable);

		userText.setBounds(580, 80, 130, 20);
		panel.add(userText);

		passwordLabel.setBounds(500, 80, 80, 80);
		passwordLabel.setFont(font1);
		panel.add(passwordLabel);

		passwordText.setBounds(580, 110, 130, 20);
		panel.add(passwordText);

		deleteButton.setBounds(450, 200, 120, 40);
		deleteButton.setFont(font1);
		panel.add(deleteButton);
		deleteButton.addActionListener(new DeleteUserHandler());

		updatePasswdButton.setBounds(600, 200, 120, 40);
		updatePasswdButton.setFont(font1);
		panel.add(updatePasswdButton);
		updatePasswdButton.addActionListener(new UpdatePasswdHandler());

	}

	class UpdatePasswdHandler implements ActionListener {
		/**
		 * @param
		 * @return
		 * @description: �޸�����
		 * @date: 2018/10/27 14:29
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String ID = userText.getText();
			String password = new String(passwordText.getPassword());
			// MD5����
			MD5 md5 = new MD5();
			password = md5.MD5WithoutSalt(password);
			// SQL���
			db.sqlLines = "update ϵͳ�û��� set ����=? where �û�ID=?";
			db.pre();
			try {
				db.preparedStatement.setString(1, password);
				db.preparedStatement.setString(2, ID);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			db.exeSql();// ִ��SQL���
			if (db.effectedLines > 0) {
				JOptionPane.showMessageDialog(null, "�޸�����ɹ�", "", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "�޸�����ʧ��", "", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	class DeleteUserHandler implements ActionListener {
		/**
		 * @param
		 * @return
		 * @description: ɾ���û�
		 * @date: 2018/10/28 20:31
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String ID = userText.getText();
			String password = new String(passwordText.getPassword());

			// SQL���
			db.sqlLines = "delete from ϵͳ�û��� where �û�ID=? and ����=?";
			db.pre();
			MD5 md5 = new MD5();
			password = md5.MD5WithoutSalt(password);
			try {
				db.preparedStatement.setString(1, ID);
				db.preparedStatement.setString(2, password);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			db.exeSql();// ִ��SQL���

			if (db.effectedLines > 0) {
				JOptionPane.showMessageDialog(null, "ɾ���û��ɹ�", "", JOptionPane.INFORMATION_MESSAGE);
				sA.refresh();
			} else {
				JOptionPane.showMessageDialog(null, "ɾ���û�ʧ��", "", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
