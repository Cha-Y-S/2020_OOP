
public class JavaGrammer2_1 {

	public static void main(String[] args) {

	// (�ǽ�2-1) �Ʒ��� ������ ���Ե� ������ ������ �°� �����Ѵ�.
		
		// (1������) �ĺ���
		System.out.println("\n(1��) -----------------------------------------------\n");
		int 3hi = 3
		double $value#Next = 5.6
		System.out.println("3hi = " + 3hi);
		System.out.println("$value#Next = " + $value#Next);
		
		// (2������) ����, ���ڿ�, ���ͷ�, Ű����(�����)
		System.out.println("\n(2��) -----------------------------------------------\n");
		int radius = 10;
		char c1 = "%";
		char c2 = 'go';
		char c3 = radius;
		String st = "Hello" + true + ", " + case;
		double weight = c3;
		System.out.printf(radius, c1, c2, c3, st, weight);
		
		// (3������) null ����
		System.out.println("\n(3��) -----------------------------------------------\n");
		int n = null;
		String str = null;
		System.out.println("n, str : " + n + ", " + str);

		// (4������) JDK7���� ���ڿ� ��_�� ���, ������ ���� 
		System.out.println("\n(4��) -----------------------------------------------\n");
		int price = 20_100; 
		int x = 15_; 		
		double pi = 3_.14; 
		System.out.println("x, pi : " + x + ", " + pi);
		
		// (5������) var Ű���带 ����Ͽ� ���� Ÿ�� ���� 
		System.out.println("\n(5��) -----------------------------------------------\n");
		var price = 200; 		// price�� int Ÿ������ ����
		var name = "����"; 		// name�� String Ÿ������ ����
		var pi = 3.14; 
		var val; 	
		
		// (6������) ���� Ÿ�� ��ȯ
		System.out.println("\n(6��) -----------------------------------------------\n");
		int i = 1000000;
		short d;
		double s = 3.14;
		byte a;
		d = i;
		a = s;
		System.out.println("d, a = " + d + ", " + a);
				
	}

}
