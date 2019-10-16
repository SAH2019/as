package creater;

public class Fraction {

	int fenzi;
	int fenmu;

	public Fraction(int fenzi, int fenmu) {
		if (fenmu == 0) {
			throw new IllegalArgumentException("��ĸ����Ϊ0");
		}
		this.fenzi = fenzi;
		this.fenmu = fenmu;
	}

	// �����ļӷ�
	public Fraction add(Fraction other) {
		int fm = this.fenmu * other.fenmu;
		int fz = this.fenzi * other.fenmu + other.fenzi * this.fenmu;
		return new Fraction(fz, fm);
	}

	// �����ļ���
	public Fraction sub(Fraction other) {
		int fm = this.fenmu * other.fenmu;
		int fz = this.fenzi * other.fenmu - other.fenzi * this.fenmu;
		return new Fraction(fz, fm);
	}

	// �����ĳ˷�
	public Fraction multiply(Fraction other) {
		int fm = this.fenmu * other.fenmu;
		int fz = this.fenzi * other.fenzi;
		return new Fraction(fz, fm);
	}

	// �����ĳ���
	public Fraction div(Fraction other) {

		int fm = this.fenmu * other.fenzi;
		int fz = this.fenzi * other.fenmu;
		return new Fraction(fz, fm);
	}

	// ��������ʾ
	public String toString() {
		
		
		
		return fenzi + "/" + fenmu;
	}


}