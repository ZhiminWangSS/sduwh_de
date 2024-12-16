package cn.sdu.java.exp1;
import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;
public class ScoreUtils {
	public static void main(String args[]) {
		Random random = new Random(); //声明并创建Random类对象random
		int[] scores = new int[50]; //开辟整型数组存放成绩
		//这里使用ArrayList而不是array，是为了动态调整数组大小，简化代码
		//声明定义存放5个等级的arraylist
		ArrayList<Integer> a = new ArrayList<>();
		ArrayList<Integer> b = new ArrayList<>();
		ArrayList<Integer> c = new ArrayList<>();
		ArrayList<Integer> d = new ArrayList<>();
		ArrayList<Integer> e = new ArrayList<>();
		for(int i = 0;i < 50;i++) {
			scores[i] = random.nextInt(51)+50; //控制随机数的生成范围在50到100之间比较符合实际情况。
		}
		Arrays.sort(scores); //调用api排序
		int level = 0;//定义等级
		for(int i = 49;i >= 0;i--) { //遍历读取成绩并转换为等级
			level = (scores[i]-1)/10; //注意：给定范围的下边界为开区间，所以要-1保证取整正常
			switch (level) { //依次判断等级，如果使用switch case结构其实排序意义不大
			case 9:
				a.add(scores[i]); //使用add方法动态的添加成绩
				break;
			case 8:
				b.add(scores[i]);
				break;
			case 7:
				c.add(scores[i]);
				break;
				
			case 6:
				d.add(scores[i]);
				break;
			default:
				e.add(scores[i]);
				break;
			}
		}
		//打印
		System.out.println("——————优秀的有以下成绩——————");
		System.out.print(a);
		System.out.print("\n");
		System.out.print("均值为：");
		System.out.printf("%.1f", calculateMean(a));
		System.out.print("\n");
		System.out.println("——————良好的有以下成绩——————");
		System.out.print(b);
		System.out.print("\n");
		System.out.print("均值为：");
		System.out.printf("%.1f", calculateMean(b));
		System.out.print("\n");
		System.out.println("——————一般的有以下成绩——————");
		System.out.print(c);
		System.out.print("\n");
		System.out.print("均值为：");
		System.out.printf("%.1f", calculateMean(c));
		System.out.print("\n");
		System.out.println("——————及格的有以下成绩——————");
		System.out.print(d);
		System.out.print("\n");
		System.out.print("均值为：");
		System.out.printf("%.1f", calculateMean(d));
		System.out.print("\n");
		System.out.println("——————不及格的有以下成绩——————");
		System.out.print(e);
		System.out.print("\n");
		System.out.print("均值为：");
		System.out.printf("%.1f", calculateMean(e));
		
		System.out.print("\n");
		random_select(scores);
	}
	
	//计算平均值，传入ArrayList返回均值
	public static double calculateMean(ArrayList<Integer> arrayList) {
        int sum = 0;
        for (Integer element : arrayList) {
            sum += element;
        }
        return (double) sum / arrayList.size();
    }
	//随机挑选并打印
	public static void random_select(int[] array) {
		Random random = new Random();
		int[] indexs = new int[10];
		for(int i = 0;i < 10;i++) {
			indexs[i] = random.nextInt(50);
		}
		System.out.println("——————随机抽取成绩如下——————");
		for(int i = 0;i < indexs.length;i++) {
			System.out.print(array[indexs[i]]+" ");
		}
	}
	
}
