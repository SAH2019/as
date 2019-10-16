package creater;

public class Fraction {

	int fenzi;
	int fenmu;

	public Fraction(int fenzi, int fenmu) {
		if (fenmu == 0) {
			throw new IllegalArgumentException("分母不能为0");
		}
		this.fenzi = fenzi;
		this.fenmu = fenmu;
	}

	// 分数的加法
	public Fraction add(Fraction other) {
		int fm = this.fenmu * other.fenmu;
		int fz = this.fenzi * other.fenmu + other.fenzi * this.fenmu;
		return new Fraction(fz, fm);
	}

	// 分数的减法
	public Fraction sub(Fraction other) {
		int fm = this.fenmu * other.fenmu;
		int fz = this.fenzi * other.fenmu - other.fenzi * this.fenmu;
		return new Fraction(fz, fm);
	}

	// 分数的乘法
	public Fraction multiply(Fraction other) {
		int fm = this.fenmu * other.fenmu;
		int fz = this.fenzi * other.fenzi;
		return new Fraction(fz, fm);
	}

	// 分数的除法
	public Fraction div(Fraction other) {

		int fm = this.fenmu * other.fenzi;
		int fz = this.fenzi * other.fenmu;
		return new Fraction(fz, fm);
	}

	// 分数的显示
	public String toString() {
		
		
		
		return fenzi + "/" + fenmu;
	}


}