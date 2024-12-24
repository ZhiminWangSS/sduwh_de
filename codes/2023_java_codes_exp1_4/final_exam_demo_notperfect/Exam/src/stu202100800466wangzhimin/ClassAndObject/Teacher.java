package stu202100800466wangzhimin.ClassAndObject;

public class Teacher extends Person
{

	Teacher(String ID, String name) {
		super(ID, name);
		// TODO Auto-generated constructor stub
	}

	public  void getScore(String subject) //重写
	{
		System.out.println("【"+subject+"】考试"+"【"+name+"】班级平均成绩为XX分");
	};
	
	public void getScore(Student student) //重载
	{
		System.out.println("【"+name+"】老师的考试【"+student.name+"】同学的成绩为XX分");
	}
}
