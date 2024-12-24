package stu202100800466wangzhimin.GUIsystem;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class DialogGradeTable extends JDialog{
	 JButton jb;
	 JTable table;
	 String cal;
	 DefaultTableModel model;
	 JScrollPane sp1;
	 DialogGradeTable(JFrame f,String cal){
	 super(f,cal);
	 this.setSize(200,100);
		String[] columnNames = {"名次", "学号", "姓名"};
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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	 }
}
