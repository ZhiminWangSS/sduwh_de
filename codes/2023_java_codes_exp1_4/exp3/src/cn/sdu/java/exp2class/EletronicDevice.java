package cn.sdu.java.exp2class;

public class EletronicDevice {
	String name;
	static {
//		System.out.println("电子设备静态块");
	}
	
	public EletronicDevice(String name)
	{
		this.name = name; //设备名
	}
	
	public void get_date() {
		System.out.println("设备名："+this.name+" 抱歉,不能提供时间");
	}
	
	public void charge() {
		System.out.println("正在充电。。。");
	}
	
	public int charge(int time) { //重载函数charge
		System.out.println("正在定时充电。。。预计"+time+"小时后充电完毕。");
		return time;
	}
}
