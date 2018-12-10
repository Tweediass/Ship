/*
 * Created by JFormDesigner on Thu Nov 15 22:26:34 CST 2018
 */

package Waterway;

import com.SQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

/**   
*    
* 项目名称：Ship   
* 类名称：AddData   
* 类描述：   录入
* 创建人：月明星稀   
* 创建时间：2018年12月10日 下午2:56:06   
* @version        
*/
public class AddData extends JFrame {
	SQL db = new SQL();

	public AddData() {
		initComponents();
	}

	private void button1ActionPerformed(ActionEvent e) {
		/**
		 * @description: 录入数据到表 [航道费纪录表]
		 * @param [e]
		 * @return void
		 * @date: 2018/11/15 22:43
		 */
		// 判断船名,船舶所有人,船舶登记号是否合法
		// db.sqlLines = "select * from 船舶所有权登记证书 where 船名=? and 船舶所有人=? and 船舶登记号=?";
		// db.pre();
		// try {
		// db.preparedStatement.setString(1, textField11.getText());
		// db.preparedStatement.setString(2, textField5.getText());
		// db.preparedStatement.setString(3, textField6.getText());
		// } catch (SQLException e1) {
		// e1.printStackTrace();
		// }
		// db.exeSelect();
		// // 不合法则报错
		// if (db.result[1][1] == null) {
		// JOptionPane.showMessageDialog(null, "船舶所有人 和 船舶登记号 与[船舶所有权登记证书]中的值不匹配,请重新输入",
		// "",
		// JOptionPane.ERROR_MESSAGE);
		// }
		// 将船只的检验证书基本信息录入到表 [船只检验证书]
		db.sqlLines = "insert into 航道费纪录表(船名,船检登记号,[航道费（元/月）],填表日期,航道费合计) " + "values(?,?,?,?,?)";
		db.pre();
		try {
			db.preparedStatement.setString(1, textField11.getText());
			db.preparedStatement.setString(2, textField12.getText());
			db.preparedStatement.setString(3, textField1.getText());
			db.preparedStatement.setString(4, textField2.getText());
			db.preparedStatement.setString(5, textField5.getText());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		db.exeSql();
		if (db.effectedLines > 0) {
			JOptionPane.showMessageDialog(null, "录入数据成功", "", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "录入数据失败", "", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void button2ActionPerformed(ActionEvent e) {

		/**
		 * @description: 录入数据到表 [航道费历史表]
		 * @param [e]
		 * @return void
		 * @date: 2018/11/16 20:33
		 */
		db.sqlLines = "insert into 航道费历史表(船名,航道费用,交付日期,费用有效期) values(?,?,?,?)";
		db.pre();
		try {
			db.preparedStatement.setString(1, textField11.getText());
			db.preparedStatement.setString(2, textField13.getText());
			db.preparedStatement.setString(3, textField14.getText());
			db.preparedStatement.setString(4, textField15.getText());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		db.exeSql();
		if (db.effectedLines > 0) {
			JOptionPane.showMessageDialog(null, "录入数据成功", "", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "录入数据失败", "", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		label2 = new JLabel();
		label15 = new JLabel();
		textField11 = new JTextField();
		label17 = new JLabel();
		label16 = new JLabel();
		textField12 = new JTextField();
		label3 = new JLabel();
		textField1 = new JTextField();
		label9 = new JLabel();
		textField2 = new JTextField();
		label4 = new JLabel();
		textField5 = new JTextField();
		label20 = new JLabel();
		label1 = new JLabel();
		textField13 = new JTextField();
		label8 = new JLabel();
		textField14 = new JTextField();
		label14 = new JLabel();
		textField15 = new JTextField();

		// ======== this ========
		setVisible(true);
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());
		((GridBagLayout) contentPane.getLayout()).columnWidths = new int[] { 66, 116, 157, 71, 115, 171, 41, 0 };
		((GridBagLayout) contentPane.getLayout()).rowHeights = new int[] { 0, 38, 37, 35, 13, 35, 35, 35, 35, 35, 35,
				41, 35, 35, 36, 35, 46, 0 };
		((GridBagLayout) contentPane.getLayout()).columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				1.0E-4 };
		((GridBagLayout) contentPane.getLayout()).rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4 };

		// ---- label2 ----
		label2.setText("             缴纳航道费情况");
		label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 13f));
		contentPane.add(label2, new GridBagConstraints(2, 0, 3, 2, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

		// ---- label15 ----
		label15.setText("\u8239\u53ea\u540d\u79f0");
		label15.setFont(label15.getFont().deriveFont(label15.getFont().getSize() + 4f));
		contentPane.add(label15, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
		contentPane.add(textField11, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

		// ---- label17 ----
		label17.setText("\u2190\u5173\u8054\u2192");
		label17.setFont(label17.getFont().deriveFont(label17.getFont().getSize() + 6f));
		contentPane.add(label17, new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

		// ---- label16 ----
		label16.setText("\u8239\u68c0\u767b\u8bb0\u53f7");
		label16.setFont(label16.getFont().deriveFont(label16.getFont().getSize() + 4f));
		contentPane.add(label16, new GridBagConstraints(4, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
		contentPane.add(textField12, new GridBagConstraints(5, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

		// ---- label3 ----
		label3.setText("航道费（元/月）");
		label3.setFont(label3.getFont().deriveFont(label3.getFont().getSize() + 4f));
		contentPane.add(label3, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
		contentPane.add(textField1, new GridBagConstraints(2, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

		// ---- label9 ----
		label9.setText("填表日期");
		label9.setFont(label9.getFont().deriveFont(label9.getFont().getSize() + 4f));
		contentPane.add(label9, new GridBagConstraints(4, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
		contentPane.add(textField2, new GridBagConstraints(5, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

		// ---- label4 ----
		label4.setText("航道费合计");
		label4.setFont(label4.getFont().deriveFont(label4.getFont().getSize() + 4f));
		contentPane.add(label4, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
		contentPane.add(textField5, new GridBagConstraints(2, 6, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
		button1 = new JButton();

		// ---- button1 ----
		button1.setText("\u786e\u5b9a");
		button1.setFont(button1.getFont().deriveFont(button1.getFont().getSize() + 5f));
		button1.addActionListener(e -> button1ActionPerformed(e));
		contentPane.add(button1, new GridBagConstraints(5, 7, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

		// ---- label20 ----
		label20.setText("        航道费历史表");
		label20.setFont(label20.getFont().deriveFont(label20.getFont().getSize() + 7f));
		contentPane.add(label20, new GridBagConstraints(1, 11, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

		// ---- label1 ----
		label1.setText("航道费用");
		label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 4f));
		contentPane.add(label1, new GridBagConstraints(1, 12, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
		contentPane.add(textField13, new GridBagConstraints(2, 12, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

		// ---- label8 ----
		label8.setText("交付日期");
		label8.setFont(label8.getFont().deriveFont(label8.getFont().getSize() + 4f));
		contentPane.add(label8, new GridBagConstraints(1, 13, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
		contentPane.add(textField14, new GridBagConstraints(2, 13, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
		button2 = new JButton();

		// ---- button2 ----
		button2.setText("\u6dfb\u52a0\u8bb0\u5f55");
		button2.setFont(button2.getFont().deriveFont(button2.getFont().getSize() + 5f));
		button2.addActionListener(e -> button2ActionPerformed(e));
		contentPane.add(button2, new GridBagConstraints(5, 13, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));

		// ---- label14 ----
		label14.setText("费用有效期");
		label14.setFont(label14.getFont().deriveFont(label14.getFont().getSize() + 4f));
		contentPane.add(label14, new GridBagConstraints(1, 14, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
		contentPane.add(textField15, new GridBagConstraints(2, 14, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY //GEN-BEGIN:variables
	private JLabel label2;
	private JLabel label15;
	private JTextField textField11;
	private JLabel label17;
	private JLabel label16;
	private JTextField textField12;
	private JLabel label3;
	private JTextField textField1;
	private JLabel label9;
	private JTextField textField2;
	private JLabel label4;
	private JTextField textField5;
	private JLabel label20;
	private JButton button1;
	private JLabel label1;
	private JTextField textField13;
	private JLabel label8;
	private JTextField textField14;
	private JLabel label14;
	private JTextField textField15;
	private JButton button2;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
