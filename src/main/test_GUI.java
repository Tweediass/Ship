package main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

class test_GUI {
	public static void main(String[] args) throws Exception {
		String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
		UIManager.setLookAndFeel(lookAndFeel);
		GUI A = new GUI();
		A.run();
	}
}