package cn.sdu.java.exp3CompareSample;

public class Student implements Comparable<Student> {
	
	public Student(String name, int age, int height) {
		super();
		this.name = name;
		this.age = age;
		this.height = height;
	}



	String name;
	int age;
	int height;
	
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public int compareTo(Student o) {
		int result =  0;
		return result;
	}
	
	public String toString() {
		return "age:" + age + " & height:" + this.height + " & name:" + name + "\r";
	}

}
