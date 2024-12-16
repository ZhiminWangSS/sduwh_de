package cn.sdu.java.exp3;

public class Student implements Comparable<Student> //实现内部比较器接口
{
	String stuID;
	String classString; //补充两个信息 一个是所在班级 一个是学号
	String name;
	int score;
	
	@Override
	public int compareTo(Student o) {
		// TODO Auto-generated method stub
		int result = this.score < o.score ? 1 : (this.score == o.score ? 0 : -1);   
        return result;

	}
}
