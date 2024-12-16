package cn.sdu.java.exp2class;
import java.util.Date;
import java.text.*;

public class SmartWatch extends EletronicDevice{
	public SmartWatch(String name) { //类型多态
		super(name);
		System.out.println(name);
	}
	static {
//		System.out.println("智能手表静态块");
	}
	
	public void get_date() { //重写get_date函数
		Date date = new Date();
        
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
			 
		System.out.println("设备名："+this.name+": " + ft.format(date));
	}
	


	
}
