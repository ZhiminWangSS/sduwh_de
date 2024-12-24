package stu202100800466wangzhimin.ClassAndObject;

public class Student extends Person
{

	Student(String ID, String name) {
		super(ID, name);
		// TODO Auto-generated constructor stub
	}

	public  void getScore(String subject) //重写
	{
		System.out.println("【"+subject+"】考试【"+name+"】同学的成绩为XX分");
	};
}
