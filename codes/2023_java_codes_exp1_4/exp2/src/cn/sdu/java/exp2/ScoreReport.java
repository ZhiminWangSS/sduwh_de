package cn.sdu.java.exp2;
import java.util.HashMap;
import java.util.ArrayList;

public class ScoreReport {
	 public static void main(String[] args) {
		ScoreLoader sl = new ScoreLoader();
		String fileLocation="bin/file/student_score.txt";
		
		//示例1
//		ScoreLoader.readfile(fileLocation);

		//示例2
//		ArrayList<String> scoreList = sl.readScore(fileLocation);
////		循环处理scoreList
//		int studentAmount=scoreList.size();
//		for (int i=0;i<studentAmount;i++) {
//			String s = scoreList.get(i);
//			System.out.println(s);
//		}
//		for( String score : scoreList) {	//另一种循环方式
//			
//		}

//		//示例3
//		ArrayList<Student> stuList = sl.readStudentInfo(fileLocation);
////		循环处理stuList
//		for( Student stu : stuList) {	//另一种循环方式
//			String name = stu.name;
//			int score = stu.score;
//			System.out.println("姓名："+name+",成绩："+score);
////			System.out.println("姓名："+stu.name+",成绩："+stu.score);
//	 }
		ArrayList<Student> stuList = sl.readStudentInfo(fileLocation); //读取文件存放到arraylist中
		
		ScoreUtils scoreUtils = new ScoreUtils(); //ScoreUtils中封装的用的的成绩处理方法
		
		System.out.println("——————班级人数和不及格人数——————");
		HashMap<String, Integer> classnum  = scoreUtils.get_classnum(stuList);
		for(String classname:classnum.keySet()) //打印返回的hashmap的键值对
		{
			Integer numInteger = classnum.get(classname);
			System.out.println("班级："+classname+" 人数："+numInteger);
		}
		scoreUtils.getfailnum(stuList);
		int fail_num = scoreUtils.getfailnum(stuList); //调用第一个方法，获取不及格人数
		System.out.println("不及格人数："+fail_num);
		
		
		
		System.out.println("——————学号开头不符合——————");
		ArrayList<Student> improper_stuList =  scoreUtils.get_improper_header(stuList, "2019008");
		scoreUtils.print_id_name(improper_stuList);
		
		
		System.out.println("——————替换后结果查看——————");
		scoreUtils.change_improper_part(stuList, "008", "111");
	}
	
	 
	
	
	
}