package cn.sdu.java.exp2class;
public class Test {
    // 获取当前日期和时间
	 public static void main(String[] args) {
		 EletronicDevice eletronicDevice = new EletronicDevice("我的设备");
		 System.out.println("——————方法重载——————");
		 eletronicDevice.charge(); //重载函数charge
		 eletronicDevice.charge(4);
		 SmartWatch smartWatch = new SmartWatch("我的手表");
		 System.out.println("——————方法重写——————");
		 eletronicDevice.get_date();//重写函数get_date
		 smartWatch.get_date(); 
		 System.out.println("——————方法重载——————");
		 smartWatch.charge();
		 smartWatch.charge(6);
		 System.out.println("——————类型多态——————");
		 User meUser = new User(smartWatch);
		 meUser.watch_time();
	    }
}

