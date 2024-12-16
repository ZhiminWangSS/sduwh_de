package cn.sdu.java.exp3CompareSample;

import java.util.Comparator;

public class StudentComparator implements Comparator<Student> {

	@Override
	public int compare(Student o1, Student o2) {
		if (o1.getHeight() > o2.getHeight()) {
			return 1;
		} else if (o1.getHeight() < o2.getHeight()) {
			return -1;
		}
		return 0;
	}

}
