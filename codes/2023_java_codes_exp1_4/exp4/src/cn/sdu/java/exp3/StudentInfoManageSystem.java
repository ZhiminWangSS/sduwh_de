package cn.sdu.java.exp3;

import java.awt.*;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Collections;
import java.util.Comparator;

public class StudentInfoManageSystem extends Frame implements ActionListener,TextListener 
{

	TextField t;
	Button b;
	ArrayList<Student> stuList;
	String text="";
	DefaultTableModel model;
	Choice ch1;
	Choice ch2;
	JTextArea classnumArea;
	public StudentInfoManageSystem(String title,ArrayList<Student> stuList) {
		super(title);
		this.stuList = stuList;
		t = new TextField("", 30);
		t.addTextListener(this);
		
		
		b = new Button("查询");
		b.setActionCommand("1");	//设置组件行动命令（暗号）
		b.addActionListener(this);
		
		
		ch1 = new Choice(); //创建choice选择查询模式
		ch1.add("按班级");
		ch1.add("按学号");
		
		
		ch2 = new Choice();
		ch2.add("无排序");
		ch2.add("成绩降序");
		ch2.add("学号升序");
		
		JTable table = new JTable(); //定义Jtable存放查询数据
		table.setFont(new Font("楷体", Font.PLAIN, 12));//字体、颜色、大小
		table.setColumnSelectionAllowed (true);	  //是否允许选中一整列
		table.setRowSelectionAllowed (true);		  //是否允许选中一整行
        // 创建表头
        String[] columnNames = {"姓名", "学号", "班级", "成绩"};
        // 创建一个默认的表格模型
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames); //使用默认的表格布局
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        
        
		classnumArea = new JTextArea();
		classnumArea.setSize(500, 400);
		classnumArea.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        classnumArea.setForeground(Color.BLACK);    //设置组件的背景色
        classnumArea.setFont(new Font("楷体",Font.BOLD,16));    //修改字体样式

		classnumArea.append("--------------提示信息-------------\n");
		show_num(stuList);
		
        
		Panel p = new Panel();
		p.add(t);
		p.add(ch1);
		p.add(ch2);
		p.add(b);
		
		p.add(classnumArea, BorderLayout.CENTER);
		add(scrollPane); //添加自带滚动条的panel
		add(scrollPane,BorderLayout.SOUTH);
		add(p, BorderLayout.NORTH);
		setSize(500, 700);
		setVisible(true);
		
	}

	public static void main(String[] args) {
		ScoreLoader sl = new ScoreLoader();
        String fileLocation="data/student_score.txt";
        ArrayList<Student> stuList = sl.readStudentInfo(fileLocation);

        
		StudentInfoManageSystem sim = new StudentInfoManageSystem("成绩管理系统",stuList);
		System.out.print(stuList.isEmpty());
		sim.addWindowListener(new WindowAdapter(){ //添加窗口监听，这样AWT的frame也可以通过×关闭窗口
		public void windowClosing(WindowEvent e){
		    	System.exit(0);
		    }
		    });
		 	
		
	}
	

	
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String actionCommond=e.getActionCommand();//获取组件行动命令（暗号）
		classnumArea.setText("");
		classnumArea.append("--------------提示信息-------------\n");
		show_num(stuList);//显示各班级人数
        if(actionCommond.equals("1")) { //按键处理
        	model.getDataVector().clear();
        	stuList_sort(stuList);
        	String chString = ch1.getSelectedItem();
        	Pattern pattern = Pattern.compile(text);
        	//对应组件行动命令所需要的操作
			for (Student stu : stuList) {
				if (chString=="按班级") //班级模糊查询
				{
					Matcher matcher = pattern.matcher(stu.classString); //定义matcher查询目标字符串
					if(text == "")//为空时直接输出全部信息
			        {
			        	model.addRow(new Object[]{stu.name, stu.stuID, stu.classString, stu.score});
			        	continue;
			        
			        }
			        if (matcher.find()) {
			        	model.addRow(new Object[]{stu.name, stu.stuID, stu.classString, stu.score});
			        } else {
			        }
				}else if(chString=="按学号")//学号精确查询
				{
					if(text == "")
			        {
			        	model.addRow(new Object[]{stu.name, stu.stuID, stu.classString, stu.score});
			        	continue;
			        
			        }
			        if (text.equals(stu.stuID)) {
			        	model.addRow(new Object[]{stu.name, stu.stuID, stu.classString, stu.score});
			        } else {
//			        	
			        }
			        
				}
		        
			}
			classnumArea.append("当前为"+chString+ch2.getSelectedItem()+"查询\n"); //返回查询到的结果条数
			if(model.getRowCount()==0)
	        {
	        	classnumArea.append("查询无结果\n");
	        }else {
	        	classnumArea.append("查询到"+model.getRowCount()+"条结果\n");
			}
        }else if(actionCommond.equals("2")) {
        	
        }else {
        	
        }
        
//        Component c = (Component) e.getSource();
//		System.out.println("ComponentName:" + c.getName());
//		if (e.getSource() == b) {
//			
//		}
	}
	public void textValueChanged(TextEvent e)
	{
		TextField tf=(TextField) e.getSource();
		text=tf.getText();
		System.out.println(text);
    	//对应组件行动命令所需要的操作
	}
	
	
	public static HashMap<String, Integer> get_classnum(ArrayList<Student> stuList) //使用hashmap键值对结构来存放并计数各个班级的人数
	{
		HashMap<String, Integer> classnum = new HashMap<String, Integer>(); //遍历stulist
		for(Student stu:stuList) {
			String classString = stu.classString;
			if(!classnum.keySet().contains(classString)){ //判断定义的hashmap中是否存在当前读取到的班级字符串
				classnum.put(classString, 0); //如果是不存在的，初始化一个新的班级：数量0的键值对
			}
			classnum.put(classString, classnum.get(classString) + 1); //查询对应班级计数+1
			
		}
		return classnum; //返回最后的键值对
	}
	
	
	private void show_num(ArrayList<Student> stuList)
	{
		HashMap<String, Integer> classnum  = get_classnum(stuList);
		for(String classname:classnum.keySet()) //打印返回的hashmap的键值对
		{
			Integer numInteger = classnum.get(classname);
			classnumArea.append("班级："+classname+" 人数："+numInteger+"\n");
		}
		
	}
	
	
	
	private void stuList_sort (ArrayList<Student> stuList)  //升降序
	{
		String ch2String = ch2.getSelectedItem();
		if(ch2String == "成绩降序")
		{
			Collections.sort(stuList);
		}else if(ch2String == "学号升序")
		{
			Collections.sort(stuList,new ScoreComparator());
			
		}
	}
	
}

class ScoreComparator implements Comparator<Student> //外部比较器
{

	@Override
	public int compare(Student o1, Student o2) {
		// TODO Auto-generated method stub
		String o1ID = o1.stuID.substring(o1.stuID.length() - 9); //去掉前几位相同的字符
		String o2ID = o2.stuID.substring(o2.stuID.length() - 9);
		int result = Integer.valueOf(o1ID).intValue() < Integer.valueOf(o2ID).intValue() ? -1 : (o1.score == o2.score ? 0 : 1);   
        return result;
	}
	
}

