
public class JavaGrammer2_1 {

	public static void main(String[] args) {

	// (�ǽ�2-1) �Ʒ��� ������ ���Ե� ������ ������ �°� �����Ѵ�.
		
		// (1������) �ĺ���
		System.out.println("\n(1��) -----------------------------------------------\n");
		int hi = 3;
		double $valueNext = 5.6;
		System.out.println("3hi = " + hi);
		System.out.println("$value#Next = " + $valueNext);
		
		// (2������) ����, ���ڿ�, ���ͷ�, Ű����(�����)
		System.out.println("\n(2��) -----------------------------------------------\n");
		int radius = 10;
		char c1 = '%';			// char���� ''���� �ʱ�ȭ
		String c2 = "go";		// string�� char���� �ʱ�ȭ �Ұ�
		char c3 = (char)radius;	//
		String st = "Hello" + true + ", ";	// case�� �����
		double weight = (double) c3;
		System.out.printf("%d %c %s %c %s %f",radius, c1, c2, c3, st, weight);
		
		// (3������) null ����
		System.out.println("\n(3��) -----------------------------------------------\n");
		int n = 0;	// �⺻ Ÿ���� null�� �ʱ�ȭ �� �� ����
		String str = null;
		System.out.println("n, str : " + n + ", " + str);

		// (4������) JDK7���� ���ڿ� ��_�� ���, ������ ���� 
		System.out.println("\n(4��) -----------------------------------------------\n");
		int price = 20_100; 
		int x = 15;		// ���ͷ� ���� ��� �Ұ� 		
		double pi = 3.14; 	// �Ҽ��� �� �ڿ� ��� �Ұ�
		System.out.println("price: " + price);
		System.out.println("x, pi : " + x + ", " + pi);
		
		
		// (5������) var Ű���带 ����Ͽ� ���� Ÿ�� ���� 
		System.out.println("\n(5��) -----------------------------------------------\n");
		var price1 = 200; 		// price�� int Ÿ������ ����
		var name = "����"; 		// name�� String Ÿ������ ����
		var pi1 = 3.14; 
		// var val; 			// �ʱⰪ �ʿ�
		System.out.println("price: " + price1 + "\t name: " + name + "\t pi1: " + pi1);
		
		// (6������) ���� Ÿ�� ��ȯ
		System.out.println("\n(6��) -----------------------------------------------\n");
		int i = 1000000;
		short d;
		double s = 3.14;
		byte a;
		d = (short)i;
		a = (byte)s;
		System.out.println("d, a = " + d + ", " + a);
				
	}

}
