import java.util.Scanner;



abstract class Converter {
	abstract protected double convert(double src, int dir); // �߻� �޼ҵ�
	abstract protected String unit1String(); // �߻� �޼ҵ�
	abstract protected String unit2String(); // �߻� �޼ҵ�
	protected double ratio; // ����
	
	public void run() {
		String src, dest;
		Scanner scanner = new Scanner(System.in);
		System.out.print("��ȣ(1:" + unit1String() + "->" + unit2String() + ", 2:" + unit2String() + "->" + unit1String() + ")�� �Է��ϼ���>> ");
		int dir = scanner.nextInt();
		if(dir==1) { src=unit1String(); dest=unit2String(); }
		else { src=unit2String(); dest=unit1String(); }
		System.out.println(src + "�� " + dest + "�� �ٲߴϴ�.");
		System.out.print(src + "�� �Է��ϼ���>> ");
		double val = scanner.nextDouble();
		double res = convert(val, dir);
		System.out.println("��ȯ ���: " + res + dest + "�Դϴ�");
		scanner.close();
	}
}

class GvsWon extends Converter{
	public GvsWon(double ratio)
	{
		this.ratio = ratio;
	}
	
	@Override
	protected double convert(double src, int dir)
	{
		return (dir==1) ? src*ratio : src/ratio;
	}
	
	@Override
	protected String unit1String() {return "g";}
	
	@Override
	protected String unit2String() {return "��";}
}
 
class KmVsMile extends Converter {
	public KmVsMile(double ratio) {
		this.ratio = ratio;
	}
	
	@Override
	protected double convert(double src, int dir) {
		return (dir==1) ? src/ratio : src*ratio;
	}

	@Override
	protected String unit1String() { return "Km"; }

	@Override
	protected String unit2String() { return "mile"; }
	
	public static void main(String args[]) {
		/*KmVsMile kmVsMile = new KmVsMile(1.6); // 1������ 1.6Km
		kmVsMile.run();*/

		GvsWon gvsWon = new  GvsWon(70000);
		gvsWon.run();
	}
}