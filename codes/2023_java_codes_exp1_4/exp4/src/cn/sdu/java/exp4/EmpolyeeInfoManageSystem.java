package cn.sdu.java.exp4;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
public class EmpolyeeInfoManageSystem extends JFrame implements ActionListener,FocusListener{
	

	/**
	 * 
	 */
	ArrayList<Employee> employeeList;
	ArrayList<Employee> selectedemployeeList;
	JPanel p1;
	JScrollPane sp1;
	JTable table;
	DefaultTableModel model;
	String filename = "data/employee.txt";
	JTextField tf1;
	JComboBox<String> cb1;
	JDialog dialog;
	public EmpolyeeInfoManageSystem(String title,ArrayList<Employee> employeeList)
	{
		super(title);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); //设置gui关闭自动终止程序运行
		this.setLayout(new BorderLayout());
		this.employeeList = employeeList;

		
		panelConfig();//页面上方panel 配置
		menuConfig();//页面菜单配置
		tableConfig();//jtable配置
        
		this.add(sp1,BorderLayout.SOUTH);//panel布局设置
		this.add(p1,BorderLayout.NORTH);
		this.setSize(580, 700);
		this.setResizable(false);
		this.setVisible(true);
		
	}
	
	public static void main(String[] args)
	{
		String fileLocation="data/employee.txt";
		ArrayList<Employee> employlList = EmployeeInfoLoader.readEmployeesInfo(fileLocation);//读取文件
		EmpolyeeInfoManageSystem eim = new EmpolyeeInfoManageSystem("员工管理系统", employlList);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String actionCommond=e.getActionCommand();
		
		if(actionCommond.equals("search")) {
			String searchString = tf1.getText();
			String choiceString = (String) cb1.getSelectedItem();
			model.getDataVector().clear();
			selectedemployeeList= searchEmployeeList(choiceString, searchString);
			for (Employee employee:selectedemployeeList)
			{
				model.addRow(new Object[]{employee.name, employee.department,employee.phonenumber, employee.email, employee.isTop});
			}
			int employeecount = table.getRowCount();
			if(employeecount == 0)
			{
				model.getDataVector().clear();
				dialogShow("查询无结果");
			}else {
				dialogShow("查询到"+employeecount+"条结果");
			}
		}
		
		
		if(actionCommond.equals("save"))
		{
			try 
			{
				String  savePath = "data/query_result.txt";
				try (FileWriter writer = new FileWriter(savePath)) {
		            // 写入列名
		            writer.write("姓名,部门,电话,Email,211/985\n");

		            // 写入每个 Employee 对象的属性
		            for (Employee employee : selectedemployeeList) {
		                writer.write(employee.name + "," + employee.department + "," + employee.phonenumber +
		                        "," + employee.email + "," + employee.isTop + "\n");
		            }
		        } catch (IOException exio) {
		            exio.printStackTrace();
		        }
				dialogShow("保存成功！路径为\n/data/query_result.txt");
				
			}catch(NullPointerException ex)
			{
				dialogShow("请先查询再保存!\n（空指针异常）");
			}
			
		}
		
		
		if(actionCommond.equals("help"))
		{
			dialog = new JDialog();
	        dialog.setTitle("帮助文档");
	        dialog.setSize(470, 200);
	        JTextArea ta = new JTextArea();
	        ta.setFont(new Font("微软雅黑", Font.PLAIN, 14));
	        ta.append("————————员工管理系统帮助文档————————\n");
	        ta.append("1.支持三种查询方式：\n(1)自由查询：可对所有信息模糊查询，输入时请保证各项信息同列顺序\n"
	        		+ "(2)按姓名查询：可对姓名进行模糊查询\n"
	        		+"(3)按部门查询：可对部门进行精确查询\n"
	        		+"2.支持查询结果导出，未查询直接保存会有提示信息\n"
	        		+"保存路径为/data/query_result.txt\n"
	        		+"3.支持查询格式灰色字体提示");
	        ta.setEditable(false);
	        dialog.add(ta);	        	        	        
	        // 设置弹窗为模态
	        dialog.setModal(true);
	        // 设置弹窗关闭操作
	        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	        // 设置弹窗相对于主窗口的位置
	        dialog.setLocationRelativeTo(this);	        
	        dialog.setVisible(true);
			
		}
		
		if(actionCommond.equals("choice"))
		{
			String choiceString = (String) cb1.getSelectedItem();
			switch(choiceString) {
			case "自由查询":
				tf1.setForeground(Color.gray); //将提示文字设置为灰色
	            tf1.setText("请按列顺序模糊查询");     //显示提示文字
				break;
			case "按姓名":
				tf1.setForeground(Color.gray); //将提示文字设置为灰色
	            tf1.setText("模糊查询，例：张、张三");     //显示提示文字
				break;
			case "按部门":
				tf1.setForeground(Color.gray); 
	            tf1.setText("精确查询，例：信息部");   
				break;	
			case "姓名部门联合查询":
				tf1.setForeground(Color.gray);
	            tf1.setText("模糊查询,例：张三信息部"); 
				break;
			default:
				break;
			}
		}
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		//得到焦点时，当前文本框的提示文字和创建该对象时的提示文字一样，说明用户正要键入内容
		if(tf1.getText().equals("请按列顺序模糊查询")|tf1.getText().equals("模糊查询，例：张、张三")|
				tf1.getText().equals("精确查询，例：信息部")|tf1.getText().equals("模糊查询,例：张三信息部")) {
	        tf1.setText("");     //将提示文字清空
	        tf1.setForeground(Color.black);  //设置用户输入的字体颜色为黑色
		}
	}
	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
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
		
		String[] options = {"自由查询","按姓名", "按部门","姓名部门联合查询"};
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
		p1.add(label,BorderLayout.SOUTH);
		
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
		String[] columnNames = {"姓名", "部门", "电话", "Email","211/985"};
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
        table.getColumnModel().getColumn(2).setMinWidth(100);
        table.getColumnModel().getColumn(3).setMinWidth(150);
        DefaultTableCellRenderer dc=new DefaultTableCellRenderer();
        dc.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, dc);
        
        sp1 = new JScrollPane(table);
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
	
	private ArrayList<Employee> searchEmployeeList(String choiceString,String searchString)
	{
		ArrayList<Employee> seletedemployeeList = new ArrayList<Employee>();
		String regexString = searchString.replaceAll("(.)", "$1.*");//每个字符中间都添加正则匹配
		Pattern pattern = Pattern.compile(regexString);
		String[] tipStrings = {"请按列顺序模糊查询","模糊查询，例：张、张三","精确查询，例：信息部","模糊查询,例：张三信息部",""};
		List<String> tipsList = Arrays.asList(tipStrings);
		if (tipsList.contains(searchString))
			{
				seletedemployeeList = employeeList;
			}
		else 
			{
				switch (choiceString) {
				case "自由查询":
					for (Employee employee:employeeList)
					{
						String allString = employee.name+ employee.department+employee.phonenumber+ employee.email+employee.isTop;
						Matcher matcher = pattern.matcher(allString);
						if(matcher.find())
						{
							seletedemployeeList.add(employee);
						}
					}					
					break;
					
				case "按姓名":
					for (Employee employee:employeeList)
					{
						Matcher matcher = pattern.matcher(employee.name);
						if(matcher.find())
						{
							seletedemployeeList.add(employee);
						}
						
					}
					break;
					
				case "按部门":
					for (Employee employee:employeeList)
					{
						if(employee.department.equals(searchString))
						
						{
							seletedemployeeList.add(employee);
						}
					}
					break;				
				case "姓名部门联合查询":
				for (Employee employee:employeeList)
				{
					String allString = employee.name+ employee.department;
					Matcher matcher = pattern.matcher(allString);
					if(matcher.find())
					{
						seletedemployeeList.add(employee);
					}
				}				
					break;
					
				default:
					break;
				}
			}
		
		return seletedemployeeList;
	}
	
	
	



}
