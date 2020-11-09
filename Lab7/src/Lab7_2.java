import java.util.*;

public class Lab7_2 {
	private HashMap<String, Integer> map = new HashMap<String, Integer>();
	private Scanner scanner = new Scanner(System.in);
	
	public Lab7_2() { }
	
	public void run() {
		System.out.println("** ����Ʈ ���� ���α׷��Դϴ� **");

		while(true) {
			System.out.print("�̸��� ����Ʈ �Է�>> ");
			String name = scanner.next();
			if(name.equals("�׸�"))
				break;
			int point = scanner.nextInt();
			
			addOrUpdate(name, point); // �̸����� ����Ʈ �˻�.
			                          //�ű԰� �ƴϸ� ����Ʈ ���� ����
			                          // �̸��� ���� ����Ʈ ����
			
			printAll();  // HashMap�� ����� ��� ���� ����Ʈ ���
		}
	}
	
	void addOrUpdate(String name, int point) {
		if(map.containsKey(name))
			map.put(name, point + map.get(name));
		else
			map.put(name, point);
	}
	
	
	void printAll() {
		Set<String> keys = map.keySet();
		Iterator<String> it = keys.iterator();
		while(it.hasNext())
		{
			String name = it.next();
			int point = map.get(name);
			System.out.printf("(" + name + "," + point + ")");
		}
		System.out.println("");
	}
	
	
	public static void main(String[] args) {
		Lab7_2 man = new Lab7_2();
		man.run();
	}
}

