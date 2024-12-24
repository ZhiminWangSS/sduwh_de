package stu202100800466wangzhimin.GUIsystem;

import java.util.Comparator;


public class ComparatorID implements Comparator<Student>{
	@Override
	public int compare(Student o1, Student o2) {
		// TODO Auto-generated method stub
		String o1ID = o1.stuID.substring(o1.stuID.length() - 9); //去掉前几位相同的字符
		String o2ID = o2.stuID.substring(o2.stuID.length() - 9);
		int result = Integer.valueOf(o1ID).intValue() < Integer.valueOf(o2ID).intValue() ? -1 : (o1.stuID == o2.stuID ? 0 : 1);   
        return result;
	}
}
