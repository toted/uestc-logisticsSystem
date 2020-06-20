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
		setTitle("智能物流系统");
		setLayout(new GridLayout(8,8));
		JLabel jl1=new JLabel("欢迎使用!",JLabel.CENTER);
		jl1.setFont(new Font(Font.DIALOG, Font.BOLD, 25));
		jl1.setSize(1200, 1200);
		jl1.setLocation(4, 4);
		add(jl1);
		//jl1.setText("请选择操作");
		JLabel jl2=new JLabel("请选择操作",JLabel.CENTER);
		jl2.setFont(new Font(Font.DIALOG, Font.BOLD, 25));
		jl2.setSize(1200, 1200);
		jl2.setLocation(5, 5);
		add(jl2);
		JButton jb1=new JButton("查询");
		jb1.setSize(40,40);
		jb1.setLocation(4,4);
		add(jb1);
		JButton jb2=new JButton("寄件");
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
							ExDialog exDialog=new ExDialog("有其他用户在写入！");
						}
					}
					else {
						JDialog aDialog=new ExDialog("登录失败");
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					ExDialog exDialog=new ExDialog("检查连接");
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
							ExDialog e1=new ExDialog("有其他用户正在读或写");
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
