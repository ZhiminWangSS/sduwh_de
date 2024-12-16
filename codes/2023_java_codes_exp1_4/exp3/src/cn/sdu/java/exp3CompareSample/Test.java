package cn.sdu.java.exp3CompareSample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		Student s1=new Student("s1",22,172);
		Student s2=new Student("s2",24,178);
		Student s3=new Student("s3",23,168);
		List<Student> list = new ArrayList<Student>();
		list.add(s1);
		list.add(s2);
		list.add(s3);
		Collections.sort(list,new StudentComparator());	
		System.out.println(list);
	}
}
