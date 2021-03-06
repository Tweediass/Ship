/*
 * Created by JFormDesigner on Fri Nov 16 15:02:19 CST 2018
 */

package OperationCtf;

import com.SQL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.print.PrinterException;
import java.sql.SQLException;
/**   
*    
* 项目名称：Ship   
* 类名称：SelectAndModifyData   
* 类描述：   查询与修改营运证书
* 创建人：月明星稀   
* 创建时间：2018年12月10日 上午11:40:27   
* @version        
*/
public class SelectAndModifyData extends JFrame {

	SQL db = new SQL();

	public SelectAndModifyData() {
		initComponents();
		addCheckBoxItems();
	}

	public void addCheckBoxItems() {
		comboBox1.addItem("船只名称");
		comboBox1.addItem("船检登记号");
		comboBox1.addItem("营运证编号");
	}

	private void button1ActionPerformed(ActionEvent e) {
		/**
		 * @description: 按所选择的查询方式打表
		 * @param [e]
		 * @return void
		 * @date: 2018/11/16 15:12
		 */
		db.sqlLines = "select * from 船只营运证书 where 船名=? or 船检登记号=? or 营运证编号=?";
		db.pre();
		try {
			db.preparedStatement.setString(1, textField1.getText());
			db.preparedStatement.setString(2, textField1.getText());
			db.preparedStatement.setString(3, textField1.getText());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		db.exeSelect();
		db.toTable(db, table1);
	}

	private void button2ActionPerformed(ActionEvent e) {
		/**
		 * @description: 由Jtable的数据更新数据库
		 * @param [e]
		 * @return void
		 * @date: 2018/11/16 15:25
		 */
		db.sqlLines = "update 船只营运证书 set 船名=?,船检登记号=?,营运证编号=?,船舶所有人=?,船舶登记号=?,经营人许可证号码=?,管理人许可证号码=?,发证机关=?,营运证使用有效期至=?,发证日期=? where 序号=?";
		db.pre();
		try {
			for (int i = 1; i <= 10; i++) {
				db.preparedStatement.setString(i, table1.getValueAt(table1.getSelectedRow(), i).toString());
			}
			db.preparedStatement.setString(11, table1.getValueAt(table1.getSelectedRow(), 0).toString());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		db.exeSql();
		// 同步修改[各证书有效期]中的值
		db.sqlLines = "update 各证书有效期 set 船名=?,证书有效期至=?,发证日期=? where 船名=? and 证书名=?";
		db.pre();
		try {
			db.preparedStatement.setString(1, table1.getValueAt(0, 1).toString());
			db.preparedStatement.setString(2, table1.getValueAt(0, 9).toString());
			db.preparedStatement.setString(3, table1.getValueAt(0, 10).toString());
			db.preparedStatement.setString(4, table1.getValueAt(0, 1).toString());
			db.preparedStatement.setString(5, "船只营运证书");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		db.exeSql();
		if (db.effectedLines > 0) {
			JOptionPane.showMessageDialog(null, "修改数据成功", "", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "修改数据失败", "", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void button3ActionPerformed(ActionEvent e) {
		/**
		 * @description: 打印查询
		 * @param [e]
		 * @return void
		 * @date: 2018/11/17 10:24
		 */
		try {
			table1.print();
		} catch (PrinterException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		panel1 = new JPanel();
		label1 = new JLabel();
		label2 = new JLabel();
		comboBox1 = new JComboBox();
		textField1 = new JTextField();
		button1 = new JButton();
		button3 = new JButton();
		scrollPane1 = new JScrollPane();
		table1 = new JTable();
		button2 = new JButton();
		label3 = new JLabel();
		// ======== this ========
		setVisible(true);
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		// ======== panel1 ========
		{
			panel1.setLayout(new GridBagLayout());
			((GridBagLayout) panel1.getLayout()).columnWidths = new int[] { 0, 83, 99, 0, 0 };
			((GridBagLayout) panel1.getLayout()).rowHeights = new int[] { 28, 47, 45, 34, 41, 59, 35, 36, 0 };
			((GridBagLayout) panel1.getLayout()).columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0E-4 };
			((GridBagLayout) panel1.getLayout()).rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
					1.0E-4 };
			// ---- label1 ----
			label1.setText("查询营运证书");
			label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 5f));
			panel1.add(label1, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
			// ---- label2 ----
			label2.setText("    \u67e5\u8be2\u65b9\u5f0f");
			label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 3f));
			panel1.add(label2, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
			panel1.add(comboBox1, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
			panel1.add(textField1, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
			// ---- button1 ----
			button1.setText("\u786e\u5b9a");
			button1.addActionListener(e -> button1ActionPerformed(e));
			panel1.add(button1, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 5, 5), 0, 0));
			// ---- button3 ----
			button3.setText("打印");
			button3.setFont(button3.getFont().deriveFont(button3.getFont().getSize() + 4f));
			button3.addActionListener(e -> button3ActionPerformed(e));
			panel1.add(button3, new GridBagConstraints(2, 7, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
					GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0));
		}
		contentPane.add(panel1);
		panel1.setBounds(0, 0, 245, 385);
		// ======== scrollPane1 ========
		{
			// ---- table1 ----
			table1.setModel(new DefaultTableModel(new Object[][] {},
					new String[] { null, null, null, null, null, null, null, null, null, null, null, null, null }));
			scrollPane1.setViewportView(table1);
		}
		contentPane.add(scrollPane1);
		scrollPane1.setBounds(240, 0, 891, 200);
		// ---- button2 ----
		button2.setText("\u786e\u8ba4\u4fee\u6539");
		button2.addActionListener(e -> button2ActionPerformed(e));
		contentPane.add(button2);
		button2.setBounds(870, 320, 98, 40);
		// ---- label3 ----
		label3.setText("修改营运证书");
		label3.setFont(label3.getFont().deriveFont(label3.getFont().getSize() + 5f));
		contentPane.add(label3);
		label3.setBounds(865, 275, 115, 42);
		{ // compute preferred size
			Dimension preferredSize = new Dimension();
			for (int i = 0; i < contentPane.getComponentCount(); i++) {
				Rectangle bounds = contentPane.getComponent(i).getBounds();
				preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
				preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
			}
			Insets insets = contentPane.getInsets();
			preferredSize.width += insets.right;
			preferredSize.height += insets.bottom;
			contentPane.setMinimumSize(preferredSize);
			contentPane.setPreferredSize(preferredSize);
		}
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY //GEN-BEGIN:variables
	private JPanel panel1;
	private JLabel label1;
	private JLabel label2;
	private JComboBox comboBox1;
	private JTextField textField1;
	private JButton button1;
	private JButton button3;
	private JScrollPane scrollPane1;
	private JTable table1;
	private JButton button2;
	private JLabel label3;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
