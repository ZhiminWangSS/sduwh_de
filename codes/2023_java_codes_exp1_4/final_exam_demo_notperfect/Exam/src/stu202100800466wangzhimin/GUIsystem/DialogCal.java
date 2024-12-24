package stu202100800466wangzhimin.GUIsystem;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class DialogCal extends JDialog{
	
	 JButton jb;
	 JTextArea jta;
	 String cal;
	 DialogCal(JFrame f,String cal){
		 super(f,cal);
		 this.setSize(200,100);
		 jta=new JTextArea(7,15);
		 add(jta);
		 add(jb);
		 setBounds(200,200,200,200);
		 setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	 }
}
