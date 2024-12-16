package cn.sdu.java.exp3interface;

public class Openai_API_chat implements Openai_API //实现子接口 聊天接口
{
	String INTERFACE_NAME = "openai_api_chat 聊天接口";
    String FILE_TYPE = "txt";
    public Openai_API_chat()
    {
    	System.out.println("创建openAI聊天接口");
    }
	@Override
	public void APIkey_input(String APIkey) {
		// TODO Auto-generated method stub
		System.out.println("使用的apikey为："+APIkey);
	}

	@Override
	public void APIfunction1() {
		// TODO Auto-generated method stub
		System.out.println("调用聊天功能功能");
	}

	@Override
	public void APIfunction2() {
		// TODO Auto-generated method stub
		System.out.println("调用文字补全功能");
	}
}
