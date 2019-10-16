package creater;

import java.util.Scanner;

public class creater {

	public interface Fraction {

	}

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		long startTime = System.currentTimeMillis();   
		Scanner choose = new Scanner(System.in);
		System.out.println("************************************欢迎来到四则运算生成器******************************************");
		System.out.println("请选择生成题目数量：");
		int questionsNum = choose.nextInt();
		System.out.println("请输入算术范围：");
		int numRange = choose.nextInt();
 
		new FormulaMaker(numRange, questionsNum);
		System.out.println("题目文件生成完毕！");
		long endTime = System.currentTimeMillis();
		System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
	}

}
