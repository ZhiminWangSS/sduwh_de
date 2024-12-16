package cn.sdu.java.exp2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author SDDX
 *
 */
public class ScoreLoader {
	
	public static void readfile(String filename) {
		try {
			ArrayList<String> lineList=new ArrayList<>();
			Scanner scanner = new Scanner(new File(filename));
			scanner.useDelimiter(System.getProperty("line.separator"));
			while (scanner.hasNext()) {
				String line = scanner.next();
				System.out.println(line);
				lineList.add(line);
			}
			
			scanner.close();

		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}
	
	public ArrayList<String> readScore(String filename) {
		try {
			ArrayList<String> scoreList=new ArrayList<>();
			File f=new File(filename);
			Scanner scanner = new Scanner(f);
			scanner.useDelimiter(System.getProperty("line.separator"));
			while (scanner.hasNext()) {
				String score = scanner.next();
				scoreList.add(score);
			}
			scanner.close();
			return scoreList;
		} catch (FileNotFoundException e) {
			System.out.println("文件未找到，请重新核对路径");
		}
		return null;
	}

	/* 读取学生信息 */
	public ArrayList<Student> readStudentInfo(String filename) {
		try {
			ArrayList<Student> studentList=new ArrayList<>();
			File f=new File(filename);
			Scanner scanner = new Scanner(f);
			scanner.useDelimiter(System.getProperty("line.separator"));
			while (scanner.hasNext()) {
				String line = scanner.next();
				Student t = parseStudentLine(line);
				studentList.add(t);
			}
			scanner.close();
			return studentList;
		} catch (FileNotFoundException e) {
			System.out.println("文件未找到，请重新核对路径");
		}
		return null;
	}
	private Student parseStudentLine(String line) {
		Scanner linescanner = new Scanner(line);
		linescanner.useDelimiter(",");
		String stuID = linescanner.next();	
		String classString  = linescanner.next();
		String name = linescanner.next();
		String score = linescanner.next();
		//面向对象编程
		Student s = new Student();
		s.stuID = stuID; //补充学号和所在班级的信息读取和存放
		s.classString = classString;
		s.name = name;
		s.score = Integer.parseInt(score);
		linescanner.close();
		return s;
	}
}
