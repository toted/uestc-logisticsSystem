package Lab2;

import java.awt.*;
import javax.swing.*;
import Lab2.*;

import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class GUI extends JFrame
{
	public GUI() {
		Container cn=getContentPane();
		setBounds(300, 300, 300, 500);
		setVisible(true);
		setTitle("��������ϵͳ");
		setLayout(new GridLayout(8,8));
		JLabel jl1=new JLabel("��ӭʹ��!",JLabel.CENTER);
		jl1.setFont(new Font(Font.DIALOG, Font.BOLD, 25));
		jl1.setSize(1200, 1200);
		jl1.setLocation(4, 4);
		add(jl1);
		//jl1.setText("��ѡ�����");
		JLabel jl2=new JLabel("��ѡ�����",JLabel.CENTER);
		jl2.setFont(new Font(Font.DIALOG, Font.BOLD, 25));
		jl2.setSize(1200, 1200);
		jl2.setLocation(5, 5);
		add(jl2);
		JButton jb1=new JButton("��ѯ");
		jb1.setSize(40,40);
		jb1.setLocation(4,4);
		add(jb1);
		JButton jb2=new JButton("�ļ�");
		jb2.setSize(40,40);
		jb2.setLocation(6, 4);
		add(jb2);
		validate();
		jb1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//System.out.println();
				try {
					Statement stmt=Operate.Log();
					ResultSet rs=null;
					if(stmt!= null)
					{
						boolean can=Operate.canUse(stmt, rs, "read");
						if(can)
						{
							System.out.println("cg");
							Operate.ApplyAndUseSignal("read", stmt, rs);
							JFrame a=new ReadWindow(stmt, rs, "read");
						}
						else {
							ExDialog exDialog=new ExDialog("�������û���д�룡");
						}
					}
					else {
						JDialog aDialog=new ExDialog("��¼ʧ��");
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					ExDialog exDialog=new ExDialog("�������");
				}
				
			}
		});
		jb2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Statement stmt=Operate.Log();
					ResultSet rs=null;
					if(stmt!=null)
					{
						String way="write";
						boolean can=Operate.canUse(stmt, rs, way);
						if(can)
						{
							Operate.ApplyAndUseSignal(way, stmt, rs);
							WriteWindow window=new WriteWindow(stmt, rs,way);
						}
						else {
							ExDialog e1=new ExDialog("�������û����ڶ���д");
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
}
