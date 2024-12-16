package cn.sdu.java.exp4thread;

public class HandingGoods {
	public static void main(String args[])
	{
		Goods goods = new Goods(20);
		Worker worker1 = new Worker(goods, "老王");
		Worker worker2 = new Worker(goods, "老李");
		Worker worker3 = new Worker(goods, "老张");
		worker1.start(); //启动线程
		worker2.start();
		worker3.start();
		try {
			worker1.join(); //检测线程是否结束了
			worker2.join();
			worker3.join();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("----已全部搬完----");
	}
}


class Worker extends Thread{

	private Goods goods;
	public Worker(Goods goods,String name)
	{
		super(name);
		this.goods = goods;
		
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
			while(goods.goods_num > 0 )
			{
				synchronized (goods) { //同步多线程，确保一次只有一个线程能够访问该方法
				if(goods.goods_num>0) {
					
					goods.handing();
					}
				}
				try {
					Thread.sleep(100); //搬运需要时间
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();	
				}
			}

	}

}

class Goods{
	public int goods_num;
	public Goods(int goods_num) {
		this.goods_num = goods_num;
	}
	
	public void handing() {
		System.out.println("工人 " + Thread.currentThread().getName() + " 正在搬运商品");
		goods_num--;
		System.out.println("仓库中还剩余 " + goods_num + " 件商品");
		
	}
}