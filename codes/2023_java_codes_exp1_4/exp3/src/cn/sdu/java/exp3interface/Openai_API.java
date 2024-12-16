package cn.sdu.java.exp3interface;

public interface Openai_API { //定义接口

	String INTERFACE_NAME = "openai_api";
	String FILE_TYPE = "initial_type";
	void APIkey_input(String APIkey);//定义一个输入api key的方法
	void APIfunction1(); //定义两个api支持的功能
	void APIfunction2();
}
