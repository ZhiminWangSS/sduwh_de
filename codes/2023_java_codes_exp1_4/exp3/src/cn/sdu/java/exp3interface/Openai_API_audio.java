package cn.sdu.java.exp3interface;

public class Openai_API_audio implements Openai_API //实现子接口 音频接口
{
	String INTERFACE_NAME = "openai_api_audio 音频接口";
    String FILE_TYPE = "mp3/opus/aac/flac";
    public Openai_API_audio()
    {
    	System.out.println("创建openAI音频接口");
    }
	@Override
	public void APIkey_input(String APIkey) {
		// TODO Auto-generated method stub
		System.out.println("使用的apikey为："+APIkey);
		
	}

	@Override
	public void APIfunction1() {
		// TODO Auto-generated method stub
		System.out.println("调用音频转文字功能");
	}

	@Override
	public void APIfunction2() {
		// TODO Auto-generated method stub
		System.out.println("调用音频翻译功能");
	}

}
