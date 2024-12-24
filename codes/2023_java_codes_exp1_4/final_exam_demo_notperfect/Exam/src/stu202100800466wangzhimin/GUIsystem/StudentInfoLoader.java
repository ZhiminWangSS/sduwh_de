package stu202100800466wangzhimin.GUIsystem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class StudentInfoLoader 
{
//	public static void main(String args[])
//	{
//		String filename = "data/student_scores.txt";
//		StudentInfoLoader studentInfoLoader = new StudentInfoLoader();
//		ArrayList<Student> stuList = studentInfoLoader.readStudentsInfo(filename);
//		for (Student student:stuList)
//		{
//			System.out.println(student.name);
//		}
//	}
	public  ArrayList<Student> readStudentsInfo(String filename){	
	    try {
	    	ArrayList<Student> StudentList = new ArrayList<>();
	    	Reader reader = new FileReader(filename);
	    	BufferedReader br  = new BufferedReader(reader);
	        String line;
	        br.readLine();
	        while ((line = br.readLine()) != null) {
	        	String[] StudentData = line.split("_");
	        	Student Student = parseStudentLine(StudentData);
	        	StudentList.add(Student);
	        }
	        reader.close();
	        br.close();
	        return StudentList;
	        
	    }catch (FileNotFoundException e) {
			System.out.println("文件未找到，请重新核对路径");
		} 
	    catch (IOException e) {
	        e.printStackTrace();
	    }  
	    return null;
	}
	
	
	private Student parseStudentLine(String[] StudentData) {
		Student Student = new Student();
		Student.stuID = StudentData[0];
		Student.classString = StudentData[1];
		Student.name = StudentData[2];
		Student.score_data = Integer.parseInt(StudentData[3]);
		Student.score_sys = Integer.parseInt(StudentData[4]);
		Student.score_software = Integer.parseInt(StudentData[5]);
		Student.score_java = Integer.parseInt(StudentData[6]);
		Student.score_ML = Integer.parseInt(StudentData[7]);
		return Student;
	}
	
}
