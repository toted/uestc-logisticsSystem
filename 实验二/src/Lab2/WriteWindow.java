package Lab2;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class WriteWindow extends JFrame {

	public WriteWindow(Statement stmt, ResultSet rs, String way) {
		setLayout(new GridLayout(8, 8));
		setSize(400, 400);
		setLocation(400, 400);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		Container ctContainer = getContentPane();
		setTitle("寄件系统");
		JLabel jl1 = new JLabel("手机号:");
		jl1.setVisible(true);
		add(jl1);
		jl1.setSize(200, 40);
		JTextField jt1 = new JTextField(20);
		add(jt1);
		JLabel jl2 = new JLabel("发货地");
		jl2.setVisible(true);
		add(jl2);
		JTextField jt2 = new JTextField(20);
		add(jt2);
		jl2.setSize(200, 40);
		JLabel jl3 = new JLabel("目的地");
		jl3.setVisible(true);
		add(jl3);
		JTextField jt3 = new JTextField(20);
		add(jt3);
		jl3.setSize(200, 40);
		JLabel jl4 = new JLabel("重量");
		jl4.setVisible(true);
		add(jl4);
		jl4.setSize(200, 40);
		JTextField jt4 = new JTextField(20);
		add(jt4);
		JLabel jl5 = new JLabel("类型");
		jl5.setVisible(true);
		add(jl5);
		jl5.setSize(200, 40);
		JTextField jt5 = new JTextField(20);
		add(jt5);
		JLabel jl6 = new JLabel("VIP");
		jl6.setVisible(true);
		add(jl6);
		JComboBox jc = new JComboBox();
		jc.addItem("true");
		jc.addItem("false");
		add(jc);
		JButton jb1 = new JButton("提交");
		jb1.setVisible(true);
		add(jb1);
		validate();
		jb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String phone = jt1.getText();
				String departure = jt2.getText();
				String destination = jt3.getText();
				int weight = Integer.parseInt(jt4.getText());
				String name = jt5.getText();
				String time = null;
				try {
					time = Operate.timeString();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String num = MD5.stringToMD5(time);
				boolean vip = Boolean.parseBoolean((String) jc.getSelectedItem());
				String scheme = "最小价格";
				if (vip)
					scheme = "最短物流";
				object a = new object(name, num, time, departure, destination, weight, vip, scheme, phone);
				wuliu.object obj = new wuliu.object(name, Long.parseLong(time), departure, destination, weight, vip);
				wuliu.dijkstra dij = new wuliu.dijkstra();
				if (obj.getvip()) {
					System.out.println("您的货物将尽可能空运");
					dij.print.add("您的货物将尽可能空运");
					dij.dijkstra1(obj.gets(), obj.getdes(), obj.gettime(), wuliu.zuiyouwuliu.flycost,
							wuliu.zuiyouwuliu.money2);
				} else {
					dij.dijkstra1(obj.gets(), obj.getdes(), obj.gettime(), wuliu.zuiyouwuliu.cost,
							wuliu.zuiyouwuliu.money1);
				}
				ArrayList<String>print=dij.print;
				try {
					boolean ex = false;
					ex = true;
					if (ex) {
						boolean b = Operate.write(stmt, rs, a);
						if (b) {
							String info = "您的编号为" + num;
							ExDialog e2 = new ExDialog(info, "寄件成功！",print);
						}
					} else {
						ExDialog exDialog1 = new ExDialog("用户不存在");
					}
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		String arriv = null;
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					Operate.ReleaseSignal(way, stmt, rs);
					stmt.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		// TODO Auto-generated constructor stub
	}

}
