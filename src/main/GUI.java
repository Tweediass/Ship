package main;

import java.awt.EventQueue;

import javax.swing.*;

import Login.*;

/**
 * @Author: Meredith
 * @Date: 2018-10-14 18:42
 * @Program: ShipManageSystem
 * @Description: GUI的调用类
 **/
public class GUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JFrame Frame1 = new JFrame("船只资料管理系统");// 登录窗口
	public static JPanel loginPanel = new JPanel();

	public static JFrame Frame2 = new JFrame("船只资料管理系统");// 登录后的主窗口
	public static JPanel afterloginPanel = new JPanel();

	public void run() {
		// 创建 JFrame 实例
		Frame1.setSize(400, 300);// 设置窗口大小
		Frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 点x退出

		// 登陆层容器
		Login L = new Login();
		L.runLogin(loginPanel);
		Frame1.add(loginPanel);// 添加登陆面板

		// 设置界面可见,这句话要放在最后
		Frame1.setLocationRelativeTo(null);// 设置窗口居中显示
		Frame1.setVisible(true);
		Frame1.requestFocus();
	}

	public void run1() {
		Frame1.dispose();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Management frame = new Management();
					frame.setWarning();
					frame.setLocationRelativeTo(null);// 设置窗口居中显示
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
