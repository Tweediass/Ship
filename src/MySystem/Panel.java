package MySystem;

import com.SQL;

import javax.swing.*;

/**
 * @Author: Meredith
 * @Date: 2018-10-23 09:57
 * @Program: ShipManageSystem
 * @Description: ϵͳ����ģ��������
 **/
public class Panel
{
    SQL db = new SQL();
    static JFrame Frame2 = new JFrame("��ֻ���Ϲ���ϵͳ");
    static JPanel systemPanel = new JPanel();
    static JPanel sysManagePanel = new JPanel();
    static SetAuthority sA=new SetAuthority();
    public Panel()
    {
    	run();
    }
    public static void run()
    {
        // ���� JFrame ʵ��

        Frame2.setSize(800, 600);
        Frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //��½������
        systemPanel.setSize(480,320);
        new AddUser(sysManagePanel);
        new DeleteUser_ChangePasswd(sysManagePanel);

        sA.setAuthority(sysManagePanel);

        Frame2.add(sysManagePanel);
        Frame2.setLocationRelativeTo(null);
        Frame2.setVisible(true);
    }
}
