package creater;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Random;
import java.util.Stack;

public class FormulaMaker {
	private int numRange;
	private int questionsNum;

	public FormulaMaker(int numRange, int questionsNum) {
		this.numRange = numRange;
		this.questionsNum = questionsNum;
		makeFormula();
		HashSet<String> set = makeFormulas();
		Fraction[] arr = getAnswers(set);
		outputFormula(set);
		outputAnswers(arr);

	}

	// ��ȡ�������
	public int getRandomNumber() {
		Random rand = new Random();
		int RandomNum = rand.nextInt(this.numRange);
		if (RandomNum == 0)
			RandomNum = RandomNum + 1;
		return RandomNum;
	}
  //��ȡ�������
	public Fraction getRandomFraction() {
		Fraction a = new Fraction(getRandomNumber(), getRandomNumber());
		return a;

	}

	// ��ȡ����������
	public String getRandomSign() {
		Random rand = new Random();
		String[] operations = { "+", "-", "*", "/" };
		return operations[rand.nextInt(4)];
	}

	public String makeFormula() {
		String formula = "";
		for (int i = 0; i < 4; i++) {
			if (i >= 3) {
				{
					Random rand = new Random();
					int a = rand.nextInt(2);
					switch (a) {
					case 0:
						formula += this.getRandomNumber();
						break;
					case 1:
						formula += getRandomFraction();
						break;
					}
				}
				continue;
			}
			Random rand = new Random();
			int a = rand.nextInt(2);
			switch (a) {
			case 0:
				formula += this.getRandomNumber() + " " + this.getRandomSign() + " ";
				break;
			case 1:
				formula += this.getRandomFraction() + " " + this.getRandomSign() + " ";
				break;
			}

		}
		return formula;
	}

	// ������ʽ����
	public HashSet<String> makeFormulas() {
		HashSet<String> set = new HashSet<String>();
		while (set.size() < this.questionsNum) {
			String formula = this.makeFormula();
			set.add(formula);
		}
		return set;
	}

	
	// �Ƚ��������ȼ�
	public int compare(String operator1, String operator2) {
		int res = 0;
		switch (operator1) {
		case "+":
		case "-":
			if (operator2.equals("+") || operator2.equals("-") || operator2.equals("*") || operator2.equals("/")) {
				res = 1;
			} else {
				res = -1;
			}
			break;
		case "*":
		case "/":
			if (operator2.equals("*") || operator2.equals("/")) {
				res = 1;
			} else {
				res = -1;
			}
			break;
		}
		return res;
	}
	
	
	// ������ʽ���
		public Fraction getAnswer(String formula) {

			int length = 0;
			String[] formulaArr = formula.split(" ");
			String operators = "+-*/";
			Stack<Fraction> opNumbers = new Stack<Fraction>();
			Stack<String> opOperators = new Stack<String>();
			opOperators.add("#");// �ַ�ջ�д洢��#�ţ���ֹջ��
			while (length < formulaArr.length) {
				String op = formulaArr[length++];
				if (operators.indexOf(op) > -1) {// ���������,�ж����ȼ�
					String sign = opOperators.peek();
					int priority = compare(op, sign);// Ҫ��ջ�ĸ�ջ�������
					if (priority >= 0) {// ���Ҫ��ջ��������߻������,��ջ��������,��֮ǰ�������,�����,��������ջ,���ַ���ջ
						opNumbers.add(compute(opOperators, opNumbers));
						opOperators.add(op);
					} else {// ��ջ��������ȼ��ͣ�ֱ����ջ
						opOperators.add(op);
					}
					continue;
				}
				// ��������,����ջ 
				if (op.matches("\\d+/\\d+")) {
					String[] strs = op.split("/"); 
					int fenZi = Integer.parseInt(strs[0]);
					int fenMu = Integer.parseInt(strs[1]);
					opNumbers.add(new Fraction(fenZi, fenMu));
				} else {
					int fenZi = Integer.parseInt(op);
					opNumbers.add(new Fraction(fenZi, 1));
				}

			}
			while (opOperators.peek() != "#") {
				opNumbers.add(compute(opOperators, opNumbers));
			}
				Fraction lastResult = null;
				String resultArr = opNumbers.pop()+"";
			      if (resultArr.matches("-?\\d+/\\d+")) {
				   	String[] strs2 = resultArr.split("/"); 
					int lastFenZi = Integer.parseInt(strs2[0]);
					int lastFenMu = Integer.parseInt(strs2[1]);
					 lastResult=changeFraction(lastFenZi,lastFenMu);
					
		       	 }
			return lastResult;
			
		}

		
     //������Լ��		
		public Fraction changeFraction(int a,int b) {
			      if(a < 0) {
			    	  a = -a;
			    	  int min = a < b ? a : b;
			          for (int i = min;i >= 1;i --) {
			            if (a % i == 0 && b % i == 0) {
			              a=a/i;
			              b=b/i;
			                break;
		          }
			      
		           }
			          return new Fraction(-a,b) ;
		          }
			 
		    	  int min = a < b ? a : b;
		          for (int i = min;i >= 1;i --) {
		            if (a % i == 0 && b % i == 0) {
		              a=a/i;
		              b=b/i;
		                break;
	          }
		      
	           }
		              return new Fraction(a,b) ;
			      
		}
		        
		        
		        
	// ��ʽ��ֵ
	public Fraction compute(Stack<String> opOperators, Stack<Fraction> opNumbers) {
		Fraction num2 = opNumbers.pop();
		Fraction num1 = opNumbers.pop();
		String _op = opOperators.pop();
		Fraction result = null;
		switch (_op) {
		case "+":
			result = num1.add(num2);
			break;
		case "-":
			result = num1.sub(num2);
			break;
		case "*":
			result = num1.multiply(num2);
			break;
		case "/":
			result = num1.div(num2);
			break;
		}
		return result;
		}
	
	
	// ������ʽ�������
	public Fraction[] getAnswers(HashSet<String> set) {
		Fraction[] arr = new Fraction[set.size()];
		int i = 0;
		for (String str : set) {
			arr[i++] = getAnswer(str);
		}
		return arr;
	}

	// �����ʽ���ļ�
	public String outputFormula(HashSet<String> set) {
		File file = new File("Exercises.txt");
		try {
			int b = 1;
			PrintStream ps1 = new PrintStream(file);
			for (String str : set) {

				ps1.println(b + ".  " + str + "=");
				b++;
			}
			ps1.close();
		} catch (Exception e) {
			System.out.println("Error" + e.getMessage());
			System.exit(0);
		}
		return file.getAbsolutePath();
	}

	// ����𰸵��ļ�
	public String outputAnswers(Fraction[] arr) {
		File file = new File("Answers.txt");
		try {
			PrintStream ps2 = new PrintStream(file);
			for (int i = 0; i < arr.length; i++) {
				ps2.print(i + 1 + ". ");
				ps2.println(arr[i]);
			}
			ps2.close();
		} catch (Exception e) {
			System.out.println("Error" + e.getMessage());
			System.exit(0);
		}
		return file.getAbsolutePath();
	}

}
