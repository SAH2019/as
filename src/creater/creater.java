package creater;

import java.util.Scanner;

public class creater {

	public interface Fraction {

	}

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		long startTime = System.currentTimeMillis();   
		Scanner choose = new Scanner(System.in);
		System.out.println("************************************��ӭ������������������******************************************");
		System.out.println("��ѡ��������Ŀ������");
		int questionsNum = choose.nextInt();
		System.out.println("������������Χ��");
		int numRange = choose.nextInt();
 
		new FormulaMaker(numRange, questionsNum);
		System.out.println("��Ŀ�ļ�������ϣ�");
		long endTime = System.currentTimeMillis();
		System.out.println("��������ʱ�䣺" + (endTime - startTime) + "ms");    //�����������ʱ��
	}

}
