import java.util.Scanner;

public class StringRotate {

	public static void main(String[] args) {
		System.out.println("���ڿ��� �Է��ϼ���. �� ĭ�̳� �־ �ǰ� ���� �ѱ� ��� �˴ϴ�.");
		Scanner scanner = new Scanner(System.in);
		String text = scanner.nextLine();

		// (�ǽ�6-2) �� ��ġ�� �ǽ� ���������� ä���.
		StringBuffer sb = new StringBuffer(text);
		for(int i = 0; i < text.length(); i++)
		{
			sb.append(Character.toString(text.charAt(i)));
			sb.delete(0, 1);
			System.out.println(sb);
		}
	}
}