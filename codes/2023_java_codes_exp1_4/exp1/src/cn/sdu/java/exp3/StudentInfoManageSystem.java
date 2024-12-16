package cn.sdu.java.exp3;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudentInfoManageSystem extends Frame implements ActionListener {

	TextField t;
	Button b;
	TextArea ta;
//	ArrayList<Student> stuList;
	
	public StudentInfoManageSystem(String title,ArrayList<Student> stuList) {
		super(title);
//		this.stuList = stuList;s
		t = new TextField("enter something", 20);
		b = new Button("按钮");
		ta= new TextArea();
		b.setActionCommand("1");	//设置组件行动命令（暗号）
		b.addActionListener(this);
		Panel p = new Panel();
		p.add(t);p.add(b);
		add(p, BorderLayout.NORTH);
		add(ta, BorderLayout.SOUTH);
		setSize(300, 300);
		setVisible(true);
		
	}

	public static void main(String[] args) {
		ScoreLoader sl = new ScoreLoader();
        String fileLocation="data/student_score.txt";
        ArrayList<Student> stuList = sl.readStudentInfo(fileLocation);
		StudentInfoManageSystem sim = new StudentInfoManageSystem("System",stuList);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommond=e.getActionCommand();//获取组件行动命令（暗号）
        if(actionCommond.equals("1")) {
        	System.out.println("actionCommond为"+actionCommond+"的Action事件在actionPerformed中被处理");
        	//对应组件行动命令所需要的操作
//			for (Student stu : stuList) { // 另一种循环方式
//				String name = stu.name;
//				int score = stu.score;
//				System.out.println("姓名：" + name + ",成绩：" + score);
//			}
        }else if(actionCommond.equals("2")) {
        	
        }else {
        	
        }
        
//        Component c = (Component) e.getSource();
//		System.out.println("ComponentName:" + c.getName());
//		if (e.getSource() == b) {
//			
//		}
	}
}
