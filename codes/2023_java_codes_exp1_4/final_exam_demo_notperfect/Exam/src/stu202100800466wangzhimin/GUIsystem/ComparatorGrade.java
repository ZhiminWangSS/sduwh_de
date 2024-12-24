package stu202100800466wangzhimin.GUIsystem;

import java.util.Comparator;


public class ComparatorGrade implements Comparator<Student>{
	@Override
	public int compare(Student o1, Student o2) {
		// TODO Auto-generated method stub
		double o1ID = o1.grade;//去掉前几位相同的字符
		double o2ID = o2.grade;
		int result = o1ID > o2ID ? -1 : (o1.stuID == o2.stuID ? 0 : 1);   
        return result;
	}
}