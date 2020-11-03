package ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import control.Dijkstra;
import control.station;
import model.Beansubway;
import util.BaseException;

public class FrmMain extends JDialog implements ActionListener{
	private JMenuItem menuitemStation=new JMenuItem("最短路径搜索");
	private JLabel label=new JLabel();
	
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnSearch = new Button("Search");
	private JLabel labelstart = new JLabel("起点：");
	private JLabel labelend= new JLabel("终点：");
	private JTextField edtstart = new JTextField(13);
	private JTextField edtend = new JTextField(13);
	private JTextArea edtsubway = new JTextArea(60,80);
	public FrmMain() {
		this.setTitle("北京地铁线路");
		this.setSize(400, 300);
		workPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		menuitemStation.addActionListener(this);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		label=new JLabel("总站点数: "+ station.total);
		toolBar.add(label);
		toolBar.add(labelstart);
		toolBar.add(edtstart);
		toolBar.add(labelend);
		toolBar.add(edtend);
		toolBar.add(btnSearch);	
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		edtsubway.setFont(new Font("Monospaced", Font.BOLD, 14));
		edtsubway.setLineWrap(true);        //激活自动换行功能
		edtsubway.setWrapStyleWord(false); 
		workPane.add(edtsubway);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(800, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		this.validate();
		this.btnSearch.addActionListener(this);	
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				
			}
		});	
			    this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnSearch){
			String s1=edtstart.getText();
			String s2=edtend.getText();
			try {
                int flag1=0,flag2=0;
                for(Beansubway s:station.mapOfStation) {
                    if(s1.equals(s.getStation())) {
                        flag1=1;
                    }
                    if(s2.equals(s.getStation())) {
                        flag2=1;
                    }
                }
                if(flag1==0) throw new BaseException("起点站不存在");
                if(flag2==0) throw new BaseException("终点站不存在");
                edtsubway.setText("");//清空JTextArea
                if(s1.equals(s2))
                	edtsubway.append("起点站不能与终点站相同");
                else {
                	Dijkstra sw = new Dijkstra();
                	Dijkstra.route.clear();//再次运行清空路线
                    sw.calculate(new Beansubway(s1), new Beansubway(s2));
                    for(String s:Dijkstra.route)
                    {
                    	edtsubway.append(s);
                    }
                }
                edtsubway.paintImmediately(edtsubway.getBounds());
	        } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
	            e1.printStackTrace();
	        }
	}
		
	}
	
	
}
