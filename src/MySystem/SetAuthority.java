package MySystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

/**
 * @Author: Meredith
 * @CreateDate: 2018-11-08 09:20
 * @Project: ShipManageSystem
 * @Package: System
 * @Description: ����Ȩ��
 **/
public class SetAuthority extends Panel {
	public String rs[] = new String[100];
	private JTextField userText = new JTextField();
	private JLabel userLable = new JLabel("�û���");
	private JButton confirmButton = new JButton("ȷ��");
	private JLabel text = new JLabel("�û�Ȩ�޹���");
	private JCheckBox charge = new JCheckBox("�շ�Ȩ");
	private JCheckBox supervisory = new JCheckBox("�ලȨ");
	private JCheckBox admini = new JCheckBox("����Ա");
	private JComboBox list0 = new JComboBox(rs);
	private JButton flashButton = new JButton("ˢ��");

	public void setAuthority(JPanel panel) {
		Font text1_font = new Font("����", Font.BOLD, 28);
		text.setFont(text1_font);
		panel.setLayout(null);
		text.setBounds(300, 300, 180, 80);
		panel.add(text);

		Font font1 = new Font("����", Font.BOLD, 18);
		userLable.setFont(font1);
		userLable.setBounds(60, 370, 80, 30);
		panel.add(userLable);

		// ��ʼ�������б�
		db.sqlLines = "Select �û�ID from ϵͳ�û���";
		db.pre();
		db.exeSelect();
		for (int i = 0; i < db.col; i++) {
			rs[i] = db.result[1][i];
		}
		JComboBox list = new JComboBox(rs);
		list0 = list;
		list0.setBounds(130, 370, 130, 30);
		panel.add(list0);
		list0.setSelectedIndex(-1);
		list0.addItemListener(new listHandler());

		charge.setBounds(300, 370, 70, 30);
		panel.add(charge);

		supervisory.setBounds(400, 370, 70, 30);
		panel.add(supervisory);

		admini.setBounds(500, 370, 70, 30);
		panel.add(admini);

		confirmButton.setFont(font1);
		confirmButton.setBounds(600, 370, 100, 30);
		panel.add(confirmButton);
		confirmButton.addActionListener(new setAuthorityHandler());

	}

	class listHandler implements ItemListener {
		/**
		 * @description: ͨ��JCheckBox��ʾȨ��
		 * @param
		 * @return
		 * @date: 2018/11/15 20:32 ������һ���޸�
		 */
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				charge.setSelected(false);
				supervisory.setSelected(false);
				admini.setSelected(false);
				String ID = null;
				int t;
				t = list0.getSelectedIndex();
				ID = list0.getItemAt(t).toString();
				db.sqlLines = "select Ȩ�� from ϵͳ�û��� where �û�ID=?";
				db.pre();
				try {
					db.preparedStatement.setString(1, ID);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				db.exeSelect();// ִ��SQL���
				rs[1] = db.result[1][1];
				if ("1".equals(db.result[1][1])) {
					charge.setSelected(true);
				}
				if ("2".equals(db.result[1][1])) {
					supervisory.setSelected(true);
				}
				if ("3".equals(db.result[1][1])) {
					charge.setSelected(true);
					supervisory.setSelected(true);
				}
				if ("4".equals(db.result[1][1])) {
					admini.setSelected(true);
				}
				if ("5".equals(db.result[1][1])) {
					charge.setSelected(true);
					supervisory.setSelected(true);
				}
				if ("6".equals(db.result[1][1])) {
					supervisory.setSelected(true);
					admini.setSelected(true);
				}
				if ("7".equals(db.result[1][1])) {
					charge.setSelected(true);
					supervisory.setSelected(true);
					admini.setSelected(true);
				}
			}

		}
	}

	public void refresh() {
		/**
		 * @description: ˢ�������б�
		 * @param []
		 * @return void
		 * @date: 2018/11/15 20:33
		 */
		db.sqlLines = "Select �û�ID from ϵͳ�û���";
		db.pre();
		db.exeSelect();
		list0.removeAllItems();
		for (int i = 0; i < db.col; i++) {
			list0.addItem(db.result[1][i]);
		}
		list0.setSelectedIndex(-1);// ��Ĭ�ϵ�ѡ��Ϊ��
	}

	class setAuthorityHandler implements ActionListener {
		/**
		 * @description: �޸�Ȩ��
		 * @param
		 * @return
		 * @date: 2018/11/15 20:34
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String ID = list0.getSelectedItem().toString();
			boolean isCharge = charge.isSelected();// 1
			boolean isSup = supervisory.isSelected();// 2
			boolean isAdmini = admini.isSelected();// 4
			int Autho = 0;
			if (isCharge == true) {
				Autho += 1;
			}
			if (isSup == true) {
				Autho += 2;
			}
			if (isAdmini == true) {
				Autho += 4;
			}
			String strAutho = Autho + "";
			// SQL���
			db.sqlLines = "update ϵͳ�û��� set Ȩ��=? where �û�ID=?";
			db.pre();
			try {
				db.preparedStatement.setString(1, strAutho);
				db.preparedStatement.setString(2, ID);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			db.exeSql();// ִ��SQL���
			if (db.effectedLines > 0) {
				JOptionPane.showMessageDialog(null, "����Ȩ�޳ɹ�", "", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "����Ȩ��ʧ��", "", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

}
