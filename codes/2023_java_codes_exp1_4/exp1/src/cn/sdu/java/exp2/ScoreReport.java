package cn.sdu.java.exp2;

import java.util.ArrayList;

public class ScoreReport {
	public static void main(String[] args) {
		ScoreLoader sl = new ScoreLoader();
		String fileLocation="bin/file/student_score.txt";
		
		//示例1
		ScoreLoader.readfile(fileLocation);

		//示例2
//		ArrayList<String> scoreList = sl.readScore(fileLocation);
//		循环处理scoreList
//		int studentAmount=scoreList.size();
//		for (int i=0;i<studentAmount;i++) {
//			String s = scoreList.get(i);
//		}
//		for( String score : scoreList) {	//另一种循环方式
//			
//		}

		//示例3
//		ArrayList<Student> stuList = sl.readStudentInfo(fileLocation);
////		循环处理stuList
//		for( Student stu : stuList) {	//另一种循环方式
//			String name = stu.name;
//			int score = stu.score;
//			System.out.println("姓名："+name+",成绩："+score);
////			System.out.println("姓名："+stu.name+",成绩："+stu.score);
//		}
	}
}
