package MySystem;

import com.MD5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * @Author: Meredith
 * @Date: 2018-10-18 08:41
 * @Program: ShipManageSystem
 * @Description: �����û�
 **/
public class AddUser extends Panel {
	private JTextField userText = new JTextField();
	private JPasswordField passwordText = new JPasswordField();
	private JTextField nameText = new JTextField();
	private JTextField phoneText = new JTextField();

	JLabel text1 = new JLabel("�û�����");
	JLabel adduserLable = new JLabel("�����û�");
	JLabel userLable = new JLabel("�û���");
	JLabel passwordLabel = new JLabel("����");
	JLabel nameLable = new JLabel("Ա������");
	JLabel phoneLable = new JLabel("��ϵ�绰");

	JButton addButton = new JButton("�����û�");

	AddUser(JPanel panel) {
		Font text1_font = new Font("����", Font.BOLD, 28);
		text1.setFont(text1_font);
		panel.setLayout(null);
		text1.setBounds(300, 0, 120, 80);
		panel.add(text1);

		Font lable_font = new Font("����", Font.BOLD, 20);
		adduserLable.setFont(lable_font);
		adduserLable.setBounds(40, 80, 100, 90);
		panel.add(adduserLable);

		Font font1 = new Font("����", Font.BOLD, 18);
		userLable.setBounds(150, 50, 80, 80);
		userLable.setFont(font1);
		panel.add(userLable);

		userText.setBounds(230, 80, 130, 20);
		panel.add(userText);

		passwordLabel.setBounds(150, 80, 80, 80);
		passwordLabel.setFont(font1);
		panel.add(passwordLabel);

		passwordText.setBounds(230, 110, 130, 20);
		panel.add(passwordText);

		nameLable.setFont(font1);
		nameLable.setBounds(150, 140, 100, 20);
		panel.add(nameLable);

		nameText.setBounds(230, 140, 130, 20);
		panel.add(nameText);

		phoneLable.setFont(font1);
		phoneLable.setBounds(150, 170, 130, 20);
		panel.add(phoneLable);

		phoneText.setBounds(230, 170, 130, 20);
		panel.add(phoneText);

		addButton.setBounds(200, 200, 120, 40);
		addButton.setFont(font1);
		panel.add(addButton);
		addButton.addActionListener(new AddUserHandler());
	}

	class AddUserHandler implements ActionListener {
		/**
		 * @param
		 * @return
		 * @description: �����û�
		 * @date: 2018/10/27 10:22
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String ID = userText.getText();
			String password = new String(passwordText.getPassword());
			String name = nameText.getText();
			String phone = phoneText.getText();
			db.sqlLines = "insert into ϵͳ�û���(�û�ID,����,Ȩ��,Ա������,��ϵ�绰) values(?,?,?,?,?)";
			// MD5����
			MD5 md5 = new MD5();
			password = md5.MD5WithoutSalt(password);

			db.pre();
			try {
				db.preparedStatement.setString(1, ID);
				db.preparedStatement.setString(2, password);
				db.preparedStatement.setString(3, "0");// Ĭ��Ȩ��Ϊ0
				db.preparedStatement.setString(4, name);
				db.preparedStatement.setString(5, phone);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			db.exeSql();// ִ��SQL���
			if (db.effectedLines > 0) {
				JOptionPane.showMessageDialog(null, "�����û��ɹ�", "", JOptionPane.INFORMATION_MESSAGE);
				sA.refresh();
			} else {
				JOptionPane.showMessageDialog(null, "���û����Ѵ���", "", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}