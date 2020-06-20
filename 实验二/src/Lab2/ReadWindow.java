package Lab2;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Lab2.*;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import wuliu.*;
public class ReadWindow extends JFrame {
	public JTable jTable;
	public ReadWindow(Statement stmt, ResultSet rs,String way) {
		setLayout(new FlowLayout(8));
		setTitle("��ѯ");
		// TODO Auto-generated constructor stub
		setBounds(300, 300, 1000, 500);
		setVisible(true);
		JLabel j1=new JLabel("�����Ų�ѯ",JLabel.CENTER);
		j1.setSize(100, 500);
		j1.setFont(new Font(Font.DIALOG, Font.BOLD, 25));
		j1.setVisible(true);
		JButton jb1=new JButton();
		jb1.setText("��������ѯ");
		jb1.setSize(150, 50);
		jb1.setVisible(true);
		JButton jb2=new JButton();
		jb2.setText("��������ѯ");
		jb2.setSize(150, 50);
		jb2.setVisible(true);
		JTextField jt=new JTextField(20);
		
		JComboBox jc=new JComboBox();
		jc.addItem("--��ѡ������--");
		jc.addItem("NUM");
		jc.addItem("PHONE");
		jc.addItem("DEPARTURE");
		jc.addItem("DESTINATION");
		
		add(jc);
		add(jt);
		add(jb1);
		add(jb2);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
		jb1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JButton jb=new JButton("��ϸ��Ϣ");
				// TODO Auto-generated method stub
				String key=jt.getText();
				JButton jb_=new JButton("ȡ������");
//				remove(jTable);
				JButton jbSend=new JButton("ǿ�Ʒ���");
				JButton jbarr=new JButton("ģ���ʹ�");
				validate();
				String constrain=(String) jc.getSelectedItem();
				try {
					ArrayList<object>arrayList=Operate.read(stmt, rs, key,constrain);
					String strlisr[][]=new String[arrayList.size()][110];
					int i=0;
					for(object temp:arrayList)
					{
						strlisr[i][0]=i+1+"";
						strlisr[i][1]=temp.num;//���
						strlisr[i][2]=temp.s;//qidian
						strlisr[i][3]=temp.des;//zhongdian
						strlisr[i][4]=temp.name;//mane
						strlisr[i][5]=temp.vip+"";//vip
						strlisr[i][6]=temp.time;
						strlisr[i][7]=temp.scheme;
						strlisr[i][8]=temp.weight+"";
						strlisr[i][9]=temp.phone+"";
						strlisr[i][10]=Operate.GetStatus(stmt, rs,temp.num);
						i++;
					}
					String headString[]= {"����","���","���","�յ�","����","��Ա","����ʱ��","����","����","�ֻ���","״̬"};
					DefaultTableModel defaultTableModel=new DefaultTableModel(strlisr, headString);
					jTable=new JTable(defaultTableModel);
					jTable.setSize(10000,2000);
					jTable.setVisible(true);
					jTable.getColumnModel().getColumn(1).setPreferredWidth(240);
					jTable.getColumnModel().getColumn(6).setPreferredWidth(200);
					jTable.getColumnModel().getColumn(7).setPreferredWidth(200);
					jTable.getColumnModel().getColumn(9).setPreferredWidth(240);
//					jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					JScrollPane jp = new JScrollPane(jTable);
					jp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
					Container ctContainer=getContentPane();
					ctContainer.add(jp,BorderLayout.CENTER);
					add(jb);
					add(jb_);
					add(jbSend);
					add(jbarr);
					jbarr.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							try {
								int sel=jTable.getSelectedRow();
								String num=(String) jTable.getValueAt(sel, 1);
								boolean i=Operate.arrive(stmt, rs, num);
								if(i)
								{
									ExDialog arr=new ExDialog("���ʹ������Ϣ","���ʹ�");
									jTable.setValueAt("���ʹ�", sel, 10);
									validate();
								}
								else {
									ExDialog arr=new ExDialog("��δ����");
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					});
					jbSend.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							int sel=jTable.getSelectedRow();
							String num=(String) jTable.getValueAt(sel, 1);
							String departure=(String) jTable.getValueAt(sel, 2);
							String time=(String) jTable.getValueAt(sel, 6);
							try {
								boolean i=Operate.sendGood(stmt, rs, num, departure, time);
								if(i)
								{
									ExDialog canDialog=new ExDialog("�Ѹ�����Ϣ","�����ɹ�");
									jTable.setValueAt("�ѷ���", sel, 10);
									validate();
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					});
					jb_.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							try {
								int sel=jTable.getSelectedRow();
								String num=(String) jTable.getValueAt(sel, 1);
								boolean cancel=Operate.cancel(stmt, rs, num);
								if(cancel)
								{
									ExDialog canDialog=new ExDialog("�Ѵ����ݿ���ɾ��","ȡ���ɹ�");
									defaultTableModel.removeRow(sel);
								}
								else {
									ExDialog canDialog=new ExDialog("�ѷ�������ȡ��","ȡ��ʧ��");
									validate();
								}
								validate();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					});
					jb.addActionListener(new AbstractAction() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							try {
								int sel=jTable.getSelectedRow();
								boolean vip=Boolean.parseBoolean((String) jTable.getValueAt(sel, 5));
								SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								Date date=simpleDateFormat.parse((String) jTable.getValueAt(sel, 6));
								simpleDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
								String time=simpleDateFormat.format(date);
								Long lon=Long.parseLong(time);
								String departure=(String) jTable.getValueAt(sel, 2);
								String destination=(String) jTable.getValueAt(sel, 3);
								wuliu.dijkstra dij = new wuliu.dijkstra();
								if (vip) {
									System.out.println("���Ļ��ｫ�����ܿ���");
									dij.print.add("���Ļ��ｫ�����ܿ���");
									dij.dijkstra1(departure, destination, lon, wuliu.zuiyouwuliu.flycost,
											wuliu.zuiyouwuliu.money2);
								} else {
									dij.dijkstra1(departure, destination, lon, wuliu.zuiyouwuliu.cost,
											wuliu.zuiyouwuliu.money1);
								}
								ArrayList<String>print=dij.print;
								ExDialog e1=new ExDialog("", "��ϸ��Ϣ", print);
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						}
					});
					validate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		jb2.addActionListener(new ActionListener() {
			
			private JTable jTable;
			JButton jb=new JButton("��ϸ��Ϣ");
			JButton jb1=new JButton("ȡ������");
			JButton jbSend=new JButton("ǿ�Ʒ���");
			JButton jbarr=new JButton("ģ���ʹ�");
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					validate();
					ArrayList<object>arrayList=Operate.read(stmt, rs);
					String strlisr[][]=new String[arrayList.size()][11];
					int i=0;
					for(object temp:arrayList)
					{
						strlisr[i][0]=i+1+"";
						strlisr[i][1]=temp.num;//���
						strlisr[i][2]=temp.s;//qidian
						strlisr[i][3]=temp.des;//zhongdian
						strlisr[i][4]=temp.name;//name
						strlisr[i][5]=temp.vip+"";//vip
						strlisr[i][6]=temp.time;
						strlisr[i][7]=temp.scheme;
						strlisr[i][8]=temp.weight+"";
						strlisr[i][9]=temp.phone;
						strlisr[i][10]=Operate.GetStatus(stmt, rs, temp.num);
						i++;
					}
					String headString[]= {"����","���","���","�յ�","����","��Ա","����ʱ��","����","����","�ֻ���","״̬"};
					DefaultTableModel defaultTableModel=new DefaultTableModel(strlisr, headString);
					this.jTable=new JTable(defaultTableModel);
					jTable.setSize(10000,20000);
					jTable.setVisible(true);
					jTable.getColumnModel().getColumn(1).setPreferredWidth(240);
					jTable.getColumnModel().getColumn(6).setPreferredWidth(200);
					jTable.getColumnModel().getColumn(7).setPreferredWidth(200);
					jTable.getColumnModel().getColumn(9).setPreferredWidth(240);
//					jTable.setAutoResizeMode(JTable.AUTO_);
//					jTable.getColumnModel().getColumn(6).setPreferredWidth(720);
					JScrollPane jp = new JScrollPane(jTable);
					Container ctContainer=getContentPane();
//					new ExDialog("������Ϣ", "��ѯ���", jp);
					ctContainer.add(jp,BorderLayout.CENTER);
					add(jb);
					add(jb1);
					add(jbSend);
					add(jbarr);
					jbarr.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							try {
								int sel=jTable.getSelectedRow();
								String num=(String) jTable.getValueAt(sel, 1);
								boolean i=Operate.arrive(stmt, rs, num);
								if(i)
								{
									ExDialog arr=new ExDialog("���ʹ������Ϣ","���ʹ�");
									jTable.setValueAt("���ʹ�", sel, 10);
									validate();
								}
								else {
									ExDialog arr=new ExDialog("��δ����");
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					});
					jbSend.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							int sel=jTable.getSelectedRow();
							String num=(String) jTable.getValueAt(sel, 1);
							String departure=(String) jTable.getValueAt(sel, 2);
							String time=(String) jTable.getValueAt(sel, 6);
							try {
								boolean i=Operate.sendGood(stmt, rs, num, departure, time);
								if(i)
								{
									ExDialog canDialog=new ExDialog("�Ѹ�����Ϣ","�����ɹ�");
									jTable.setValueAt("�ѷ���", sel, 10);
									validate();
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					});
					jb1.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							try {
								int sel=jTable.getSelectedRow();
								String num=(String) jTable.getValueAt(sel, 1);
								boolean cancel=Operate.cancel(stmt, rs, num);
								System.out.println(cancel);
								if(cancel)
								{
									System.out.println("111");
									ExDialog canDialog=new ExDialog("�Ѵ����ݿ���ɾ��","ȡ���ɹ�");
									defaultTableModel.removeRow(sel);
									validate();
								}
								else {
									ExDialog canDialog=new ExDialog("�ѷ�������ȡ��","ȡ��ʧ��");
									validate();
								}
								validate();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					});
					jb.addActionListener(new AbstractAction() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							try {
								int sel=jTable.getSelectedRow();
								boolean vip=Boolean.parseBoolean((String) jTable.getValueAt(sel, 5));
								SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								Date date=simpleDateFormat.parse((String) jTable.getValueAt(sel, 6));
								simpleDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
								String time=simpleDateFormat.format(date);
								Long lon=Long.parseLong(time);
								String departure=(String) jTable.getValueAt(sel, 2);
								String destination=(String) jTable.getValueAt(sel, 3);
								wuliu.dijkstra dij = new wuliu.dijkstra();
								if (vip) {
									System.out.println("���Ļ��ｫ�����ܿ���");
									dij.print.add("���Ļ��ｫ�����ܿ���");
									dij.dijkstra1(departure, destination, lon, wuliu.zuiyouwuliu.flycost,
											wuliu.zuiyouwuliu.money2);
								} else {
									dij.dijkstra1(departure, destination, lon, wuliu.zuiyouwuliu.cost,
											wuliu.zuiyouwuliu.money1);
								}
								ArrayList<String>print=dij.print;
								ExDialog e1=new ExDialog("", "��ϸ��Ϣ", print);
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						}
					});
					validate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

}
