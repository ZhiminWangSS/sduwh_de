package cn.sdu.java.exp2;

import java.util.ArrayList;
import java.util.HashMap;

public class ScoreUtils {
	public void print_id_name(ArrayList<Student> stuList) { //封装一个遍历打印arraylist的方法
		for( Student stu : stuList) {

			System.out.println("姓名："+stu.name+",成绩："+stu.score);
		}
	}
	
	public int getnum(ArrayList<Student> stuList) {
		return stuList.size();
		
	}
	
	public HashMap<String, Integer> get_classnum(ArrayList<Student> stuList) //使用hashmap键值对结构来存放并计数各个班级的人数
	{
		HashMap<String, Integer> classnum = new HashMap<String, Integer>(); //遍历stulist
		for(Student stu:stuList) {
			String classString = stu.classString;
			if(!classnum.keySet().contains(classString)){ //判断定义的hashmap中是否存在当前读取到的班级字符串
				classnum.put(classString, 0); //如果是不存在的，初始化一个新的班级：数量0的键值对
			}
			classnum.put(classString, classnum.get(classString) + 1); //查询对应班级计数+1
			
		}
		return classnum; //返回最后的键值对
	}
	
	public int getfailnum(ArrayList<Student> stuList) { //计数不及格人数的方法
		int fail_num = 0;
		for(Student stu :stuList) { //遍历判断计数
			if(stu.score < 60) {
				fail_num++;
			}
		}
		return fail_num;
	}
	

	public ArrayList<Student> get_improper_header(ArrayList<Student> stuList,String ID_header) { 
		//查询开头不符合指定字符串的学生信息
		ArrayList<Student> improper_stuList = new ArrayList<>(); //初始化一个ArrayList存放不符合的信息
		int improper_num = 0; //用于计数
		for(Student stu:stuList) {
			if(!stu.stuID.contains(ID_header)) { //调用string类的contain方法检查指定字符串是否存在与目标字符串中
				improper_stuList.add(stu);
				improper_num++;
			}
		}
		System.out.println("格式不符合"+ID_header+"开头的有"+improper_num+"个。");
		return improper_stuList; //返回ArrayList方便后续打印
	}
	
	public void change_improper_part(ArrayList<Student> stuList,
			String queryString,String newString) {
//		替换指定部分的方法
		int improper_num = 0;
		for(Student stu:stuList) {
			if(stu.stuID.contains(queryString)) { //先查找到
				improper_num++;
				stu.stuID.replace(queryString, newString); //再替换掉，同样调用string类中的方法replace
			}
		}
		System.out.println("替换了"+improper_num+"个。");
		
	}
}
