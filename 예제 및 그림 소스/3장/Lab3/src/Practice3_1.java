import java.util.Scanner;

public class Practice3_1 {
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		System.out.print("�ҹ��� ���ĺ� �ϳ��� �Է��Ͻÿ�>>");
		String s = scanner.next();
		if(s.length() != 1) {
			System.out.print("���ĺ� �ϳ��� �Է��ؾ� �մϴ�!");
			scanner.close();
			return;
		}
		
		char c = s.charAt(0);
		System.out.println("�Է¹���: " + c);
		if (c < 'a' || c > 'z') {
			System.out.println("�ҹ��� ���ĺ��� �ƴմϴ�.");
			scanner.close();
			return;
		}
		
        // (�ǽ�3-1) �� ��ġ�� ��ø-for������ �ۼ�
		
		int max = (c - 'a') + 1;
		
		for(int i = 0; i < max; i++)
		{
			char temp = 'a';
			
			for(int j = 0; j < max - i; j++)
			{
				System.out.print((char)(temp + j) + " ");
			}
			
			System.out.println("");
		}
		
		scanner.close();
	}
}
