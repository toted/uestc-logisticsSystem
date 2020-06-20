package Lab2;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class ExDialog extends JDialog{

	public ExDialog(String info) {
		setLayout(new FlowLayout());
		// TODO Auto-generated constructor stub
		setBounds(200,200, 200, 200);
		setTitle("“Ï≥££°");
		JLabel aJLabel=new JLabel(info);
		setVisible(true);
		add(aJLabel);
	}
	public ExDialog(String info,String title) {
		setLayout(new FlowLayout());
		// TODO Auto-generated constructor stub
		setBounds(200,200, 200, 200);
		setTitle(title);
		JLabel aJLabel=new JLabel(info);
		setVisible(true);
		add(aJLabel);
	}
	public ExDialog(String info,String title,JScrollPane jp) {
		setLayout(new GridLayout());
		// TODO Auto-generated constructor stub
		setBounds(200,200, 200, 200);
		setTitle(title);
		JLabel aJLabel=new JLabel(info);
		setVisible(true);
		add(aJLabel);
		add(jp,BorderLayout.CENTER);
	}
	public ExDialog(String info,String title,ArrayList<String>print) {
		setLayout(new FlowLayout());
		// TODO Auto-generated constructor stub
		setBounds(200,200, 160, 200);
		setTitle(title);
		JLabel aJLabel=new JLabel(info);
		setVisible(true);
		add(aJLabel);
		JLabel j1=new JLabel(print.get(0));
		add(j1);
		JLabel j2=new JLabel(print.get(1));
		add(j2);
		JLabel j3=new JLabel(print.get(2));
		add(j3);
		JLabel j4=new JLabel(print.get(3));
		add(j4);
		JLabel j5=new JLabel(print.get(4));
		add(j5);
		JLabel j6=new JLabel(print.get(5));
		add(j6);
//		JLabel j7=new JLabel(print.get(6));
//		add(j7);
	}
}
