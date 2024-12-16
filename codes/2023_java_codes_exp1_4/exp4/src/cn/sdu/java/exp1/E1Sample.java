package cn.sdu.java.exp1;

public class E1Sample {
	
	public static void main(String args[]) {
//		flowFor();
//		breakAndContinue();
//		flowWhile_Fibonacci20byTwoStep();
		flowWhile_Fibonacci20byOneStep();
	}
	public static void flowFor() {
		int sum = 0;
		int i;
		int j;
		for (i = 1; i <= 10; i++) // ����1+3+5+7+9��
		{
			if (i % 2 == 0)
				continue;
			sum = sum + i;
		}
		System.out.println("sum=" + sum);
		
		for (j = 2; j <= 50; j++) // ��50���ڵ�����
		{
			for (i = 2; i <= j / 2; i++) // j/2�����⣿
			{
				if (j % i == 0)
					break;
			} // end of the inner for
			if (i > j / 2) {
				System.out.println("" + j + "������");
			}
		} // end of the outer for
	}

	public static void breakAndContinue() {
		System.out.println("start");
		System.out.println("============");

		for (int i = 1; i <= 8; i++) {
			if (i == 2)
				System.out.println("��continue��Ҳ��break");
			if (i == 4)
				continue; // continue ���²�ִ��
			if (i == 6)
				break; // break �˳�forѭ��
			System.out.println(i);
			System.out.println("============");
		}

		System.out.println("over");
	}
	

	//0 1 1 2 3 5 8 13
	public static void flowWhile_Fibonacci20byTwoStep() {	//ÿ�μ�������
		final int MAX = 20;
		int i = 0; // ---F0
		int j = 1; // ----F1
		int k = 0;
		while (k < MAX) {
			System.out.println(" " + i + " " + j);
			i = i + j; // ---F2=F0+F1
			j = i + j; // ---F3=F2+F1
			k = k + 2;
		}
		System.out.println();
	}
	
	public static void flowWhile_Fibonacci20byOneStep() { //单步斐波那契数列
		final int MAX = 50;
		int i = 0;
		int j = 1;
		int temp = 0;
		while(temp < MAX)	{ //判断条件为50以内数
			System.out.println(" "+i+" ");
			temp = j;
			j = j+i;
			i = temp;
		}
		
	}
}

