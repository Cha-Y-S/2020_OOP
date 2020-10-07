import java.util.Scanner;

class Phone {
	private String name;
	private String tel;
	public Phone(String name, String tel) {
		this.name = name; this.tel = tel;
	}
	public String getName() { return name; }
	public String getTel() { return tel; }
}

public class PhoneBook {
	private Scanner scanner;
	private Phone [] pArray;
	public PhoneBook() { 
		scanner = new Scanner(System.in);
	}
	
	void read() {
		System.out.print("�ο���>>");
		int n = scanner.nextInt();
		
		pArray = new Phone[n]; 
		      // �� ������ n���� ������ �� �ִ� ũ����  �迭(pArray) ���������� ����
		
		for(int i=0; i<pArray.length; i++) {
			System.out.print("�̸��� ��ȭ��ȣ(�̸��� ��ȣ�� �� ĭ���� �Է�)>>");			
			String name = scanner.next();
			String tel = scanner.next();
			
			// �� ��ġ�� i-��° ����� �̸��� ��ȭ��ȣ�� �����ϴ� Phone��ü �����ϰ�,
			//   pArray[i]�� �����ϴ� ���� �ۼ�
			Phone temp = new Phone(name, tel);
			pArray[i] = temp;
		}
		
		System.out.println("����Ǿ����ϴ�...");		
	}
	
	String search(String name) {
		
		// �� ��ġ�� for-if���� ����Ͽ� Phone��ü�迭����(pArray) �ȿ�
		// �̸�(name)�� ����Ǿ� ������ �ش� ��ȭ��ȣ�� ����(getTel()���)�ϴ�
		// ������ ����
		for(int i = 0; i < pArray.length; i++)
		{
			//String temp = pArray[i].getName();
			if(name.equals(pArray[i].getName()))
			{
				return pArray[i].getTel();
			}
		}
		return null;
	}
	
	void run() {
		read();
		while(true) {
			System.out.print("�˻��� �̸�>>");
			String name = scanner.next();
			if(name.equals("�׸�"))
				break;
			String tel = search(name);
			if(tel == null)
				System.out.println(name + " �� �����ϴ�.");
			else
				System.out.println(name + "�� ��ȣ�� " + tel + " �Դϴ�.");				
		}	
		
		scanner.close();
	}
	
	public static void main(String[] args) {
		PhoneBook pb = new PhoneBook();
		pb.run();
	}
}
