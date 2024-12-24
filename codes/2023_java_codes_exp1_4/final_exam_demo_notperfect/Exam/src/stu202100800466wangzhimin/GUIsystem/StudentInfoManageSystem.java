package stu202100800466wangzhimin.GUIsystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class StudentInfoManageSystem extends JFrame implements ActionListener,FocusListener
{

	ArrayList<Student> stuList;
	ArrayList<Student> selectedstuList;
	JPanel p1;
	JScrollPane sp1;
	JTable table;
	DefaultTableModel model;
	String filename = "data/Student.txt";
	JTextField tf1;
	JComboBox<String> cb1;
	JDialog dialog;
	JDialog dialog_cal;
	JDialog dialog_grade;
	JTable  gradetable;
	
	
	StudentInfoManageSystem(String title,ArrayList<Student> stuList)
	{
		super(title);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); //设置gui关闭自动终止程序运行
		this.setLayout(new BorderLayout());
		this.stuList = stuList;

		
		panelConfig();//页面上方panel 配置
		menuConfig();//页面菜单配置
		tableConfig();//jtable配置
        
		this.add(sp1,BorderLayout.SOUTH);//panel布局设置
		this.add(p1,BorderLayout.NORTH);
		this.setSize(600, 700);
		this.setResizable(false);
		this.setVisible(true);

		
	}
	
	
	public static void main(String[] args)
	{
		String filename = "data/student_scores.txt";
		StudentInfoLoader studentInfoLoader = new StudentInfoLoader();
		ArrayList<Student> stuList = studentInfoLoader.readStudentsInfo(filename);
		StudentInfoManageSystem studentInfoManageSystem = new StudentInfoManageSystem("学生管理系统", stuList);
	}
	




	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String actionCommond=e.getActionCommand();
		if(actionCommond.equals("search")) {
			String searchString = tf1.getText();
			String choiceString = (String) cb1.getSelectedItem();
			model.getDataVector().clear();
			selectedstuList= searchstuList(choiceString, searchString);
			for (Student student:selectedstuList)
			{
				model.addRow(new Object[]{student.stuID, student.classString,student.name, student.score_data, 
						student.score_sys,student.score_software,student.score_java,student.score_ML});
			}
			
			int studentcount = table.getRowCount();
			if(studentcount == 0)
			{
				model.getDataVector().clear();
				dialogShow("查询无结果");
			}else {
				dialogShow("查询到"+studentcount+"条结果");
				
			}
			table.repaint();
		}else if (actionCommond.equals("cal")) {
//			dialog_cal=new DialogCal(this, "统计结果");
			int score_max = 0;
			int score_min = 100;
			int score_mid = 0;
			int score_mean = 0;
			int sum = 0;
			for(Student student:stuList) {
				if(student.score_data>score_max)
				{
					score_max = student.score_data;
				}
				
				if(student.score_data<score_max)
				{
					score_min = student.score_data;
				}
				sum = sum +student.score_data;
				
				
			}
			score_mean = sum/52;
			dialogShow("数据结构最高分"+score_max+"最低分"+score_min+"\n均值"+score_min);
		}else if(actionCommond.equals("grade"))
		{
			for(Student student:stuList)
			{
				double grade = 2*student.score_data+3*student.score_sys+3*student.score_software+3.5*student.score_java+4*student.score_ML;
				grade = grade/(2+3+3+3.5+4);
				student.grade = grade;
			}
			Collections.sort(stuList,new ComparatorGrade());
			
			dialog_grade = new JDialog();
	        dialog_grade.setTitle("帮助文档");
	        dialog_grade.setSize(470, 200);
			String[] columnNames1 = {"名次", "学号", "姓名","绩点"};
			JTable table1 = new JTable();
			JFrame f1 = new JFrame("计算绩点");
			table1.setFont(new Font("微软雅黑", Font.PLAIN, 12));//字体、颜色、大小
			table1.setColumnSelectionAllowed (true);	  //是否允许选中一整列
			table1.setRowSelectionAllowed (true);		  //是否允许选中一整行
			DefaultTableModel model1 = new DefaultTableModel();
	        model1.setColumnIdentifiers(columnNames1); //使用默认的表格布局
	        int i =1;
	        for (Student student:stuList)
			{
	        	if(i<11) {
				model1.addRow(new Object[]{i, student.stuID,student.name, student.grade});
				System.out.println(i+" "+student.stuID+" "+student.name+" "+student.grade);
				i++;
				}
			}
	        table1.setModel(model1);
	        table1.getTableHeader().setFont(new Font("微软雅黑", Font.BOLD, 14));  // 设置表头名称字体样式
	        table1.getTableHeader().setResizingAllowed(false);               // 设置不允许手动改变列宽
	        table1.getTableHeader().setReorderingAllowed(false);             // 设置不允许拖动重新排序各列
	        table1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
	        DefaultTableCellRenderer dc1=new DefaultTableCellRenderer();
	        dc1.setHorizontalAlignment(JLabel.CENTER);
	        table1.setDefaultRenderer(Object.class, dc1);
	        JScrollPane  sp2 = new JScrollPane(table1);        	      
	        f1.add(sp2);
	        f1.setSize(400,400);
	        f1.setVisible(true);
	        // 设置弹窗为模态
//	        dialog_grade.setModal(true);
	        // 设置弹窗关闭操作
	        dialog_grade.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	        // 设置弹窗相对于主窗口的位置
	        dialog_grade.setLocationRelativeTo(this);	        
//	        dialog_grade.setVisible(true);
			
			
			
		}
		if(actionCommond.equals("save"))
		{
			try 
			{
				String  savePath = "data/result.txt";
				try (FileWriter writer = new FileWriter(savePath)) {
		            // 写入列名
		            writer.write("学号,班级,姓名,数据结构,操作系统,软件工程,java,机器学习\n");
		            // 写入每个 Employee 对象的属性
		            for (Student employee : selectedstuList) {
		                writer.write(employee.stuID + "," + employee.classString + "," + employee.name +
		                        "," + employee.score_data + "," + employee.score_sys + "," + employee.score_software+ "," + employee.score_java
		                       + "," + employee.score_ML+"\n");
		                System.out.println(employee.score_data);
		            }
		        } catch (IOException exio) {
		            exio.printStackTrace();
		        }
				dialogShow("保存成功！路径为\n/data/result.txt");
				
			}catch(NullPointerException ex)
			{
				dialogShow("请先查询再保存!\n（空指针异常）");
			}
			
		}
		
		
	}
	
	
	
	
	private void panelConfig() {
		JLabel lb1 = new JLabel("请输入查询内容:");
		lb1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		tf1 = new JTextField("",20);
		tf1.addFocusListener(this);
		JButton b1 = new JButton("查询");
		b1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		b1.addActionListener(this);
		b1.setActionCommand("search");
		JButton b2 = new JButton("统计成绩");
		b2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		b2.addActionListener(this);
		b2.setActionCommand("cal");
		JButton b3 = new JButton("计算绩点");
		b3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		b3.addActionListener(this);
		b3.setActionCommand("grade");
		
		String[] options = {"无","按姓名", "按学号"};
		cb1 = new JComboBox<>(options);
		cb1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		cb1.setActionCommand("choice");
		cb1.addActionListener(this);
		ImageIcon icon = new ImageIcon("data/background.png");
		JLabel label = new JLabel(icon);
		

		
		
		p1 = new JPanel();
		
		p1.add(lb1);
		p1.add(tf1);
		p1.add(cb1);
		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		p1.add(label,BorderLayout.SOUTH);
		
		
	}
	
	private void dialogShow(String taString)
	{
		dialog = new JDialog();
        dialog.setTitle("提示");
        dialog.setSize(200, 100);
        JTextArea ta = new JTextArea();
        ta.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        ta.append(taString);
        ta.setEditable(false);
        // 添加弹窗中的组件
        dialog.add(ta,BorderLayout.CENTER);	        	        	   
        

        // 设置弹窗为模态
        dialog.setModal(true);

        // 设置弹窗关闭操作
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        
        
        // 设置弹窗相对于主窗口的位置
        dialog.setLocationRelativeTo(this);
        dialog.setSize(200,150);
        // 显示弹窗
        dialog.setVisible(true);
        
	}
	
	
	
	private void menuConfig()
	{
		// 创建菜单栏
        JMenuBar menuBar = new JMenuBar();

        // 创建菜单
        JMenu fileMenu = new JMenu("文件");
        JMenu helpMenu = new JMenu("帮助");
        // 创建菜单项
        JMenuItem saveItem = new JMenuItem("查询结果保存",KeyEvent.VK_S);
        
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
        saveItem.setActionCommand("save");
        saveItem.addActionListener(this);
        fileMenu.add(saveItem);
        
        
        JMenuItem helpItem = new JMenuItem("帮助文档",KeyEvent.VK_P);
        helpItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
        helpItem.setActionCommand("help");
        helpItem.addActionListener(this);
        helpMenu.add(helpItem);
        
        // 将菜单添加到菜单栏
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        // 将菜单栏设置到主窗口
        this.setJMenuBar(menuBar);
	}
	
	private  void tableConfig()
	{
		String[] columnNames = {"学号", "班级", "姓名", "数据结构","操作系统","软件工程","Java程序设计","机器学习"};
		table = new JTable();
		table.setFont(new Font("微软雅黑", Font.PLAIN, 12));//字体、颜色、大小
		table.setColumnSelectionAllowed (true);	  //是否允许选中一整列
		table.setRowSelectionAllowed (true);		  //是否允许选中一整行
		model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames); //使用默认的表格布局
        
        table.setModel(model);
        table.getTableHeader().setFont(new Font("微软雅黑", Font.BOLD, 14));  // 设置表头名称字体样式
        table.getTableHeader().setResizingAllowed(false);               // 设置不允许手动改变列宽
        table.getTableHeader().setReorderingAllowed(false);             // 设置不允许拖动重新排序各列
        table.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        table.getColumnModel().getColumn(0).setMinWidth(100);
        table.getColumnModel().getColumn(6).setMinWidth(100);
        DefaultTableCellRenderer dc=new DefaultTableCellRenderer();
        dc.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, dc);
        sp1 = new JScrollPane(table);
	}

	
	
	private ArrayList<Student> searchstuList(String choiceString,String searchString)
	{
		selectedstuList = new ArrayList<Student>();
		String regexString = searchString.replaceAll("(.)", "$1.*");//每个字符中间都添加正则匹配
		Pattern pattern = Pattern.compile(regexString);
		String[] tipStrings = {"请按列顺序模糊查询","模糊查询，例：张、张三","精确查询，例：信息部","模糊查询,例：张三信息部",""};
		List<String> tipsList = Arrays.asList(tipStrings);
		if (tipsList.contains(searchString))
			{
				selectedstuList = stuList;
				Collections.sort(selectedstuList,new ComparatorID());
			}
		else 
			{
				switch (choiceString) {
				case "按学号":
					for (Student Student:stuList)
					{
						if(Student.stuID.equals(searchString))
						{
							selectedstuList.add(Student);
						}
					}					
					break;
					
				case "按姓名":
					for (Student Student:stuList)
					{
						Matcher matcher = pattern.matcher(Student.name);
						if(matcher.find())
						{
							selectedstuList.add(Student);
						}
						
					}
					break;
					
				default:
					selectedstuList = stuList;
					Collections.sort(selectedstuList,new ComparatorID());
					break;
				}
			}
		
		return selectedstuList;
	}
}
