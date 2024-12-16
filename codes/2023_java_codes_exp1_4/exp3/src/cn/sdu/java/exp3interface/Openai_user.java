package cn.sdu.java.exp3interface;



public class Openai_user { //定义用户类，模拟调用接口的过程

	public static void main(String[] args) {
		Openai_user user = new Openai_user();
		Openai_API_audio audio_api = new Openai_API_audio(); //调用音频处理接口
		
		System.out.println("接口名："+audio_api.INTERFACE_NAME);
		System.out.println("接受的文件类型："+audio_api.FILE_TYPE);
		user.API_key_input(audio_api, "123456");
		user.use_APIfuction(audio_api);
		System.out.println("----------------");
		
		Openai_API_chat chat_api = new Openai_API_chat(); //调用文字处理接口
		System.out.println("接口名："+chat_api.INTERFACE_NAME);
		System.out.println("接受的文件类型："+chat_api.FILE_TYPE);
		user.API_key_input(chat_api, "456789");
		user.use_APIfuction(chat_api);
		
	}
	
	public void API_key_input(Openai_API api,String apikey)//用户输入openAIAPIkey
	{
		api.APIkey_input(apikey);
		
	}
	
	public void use_APIfuction(Openai_API api) //用户调用API功能
	{
		api.APIfunction1();
		api.APIfunction2();
	}
}
