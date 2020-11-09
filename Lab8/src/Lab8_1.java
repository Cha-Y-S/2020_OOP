import java.io.*;
import java.util.*;

public class Lab8_1 {
	private File targetFile = null;
	Vector<String> lineVector = new Vector<String>();
	
	public Lab8_1() { }
	
	private void readFile(String fileName) {
		targetFile = new File(fileName);
		try {
			Scanner fScanner = new Scanner(new FileReader(targetFile));
			while(fScanner.hasNext()) { // ������ ���� ������ ��� �б�
				String line = fScanner.nextLine(); // �� ���� �а�
				lineVector.add(line); // �� ������ ���Ϳ� ����
			}			
			fScanner.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	private Vector<Integer> searchWord(String word) { // word�� ���Ե� ���� ��ȣ���� ���ͷ� ����
		Vector<Integer> noVector = new Vector<Integer>();

		// lineVector�� ����Ǿ� �ִ� ���� ���ε� �� word�� �����ϰ� �ִ� 
		//  ��� ���ε��� ã�Ƽ� �ش� ���� ��ȣ���� noVector������ �߰��Ѵ�.
		for(int i = 0; i < lineVector.size(); i++)
		{
			if(lineVector.get(i).contains(word))
				noVector.add(i);
		}
		return noVector;
	}

	private void printLines(Vector<Integer> noVector) {

		// noVector�� ��� �ִ� ���� ������ ������ ���Ͽ� �켱 ���� ���� ����ϰ�
		//  �̾ �÷��Ǻ��� lineVector�� �ش� �ε��� ��ġ�� �ִ� ���� ���ڿ��� ����Ѵ�.
		for(int i = 0; i< noVector.size(); i++)
		{
			System.out.println((noVector.get(i) + 1) + ": " + lineVector.get(noVector.get(i)));
		}
	}
	
	public void run() {
		System.out.println("��ü ��θ��� �ƴ� ���� �̸��� �Է��ϴ� ���, ������ ������Ʈ ������ �־�� �մϴ�.");
		System.out.print("��� ���ϸ� �Է�>> ");
		Scanner scanner = new Scanner(System.in);
		String fileName = scanner.nextLine();

		readFile(fileName); // ������ ���� ������ ���ͷ� �о����
		
		while(true) {
			System.out.print("�˻��� �ܾ ����>> ");
			String line = scanner.nextLine();
			if(line.equals("�׸�"))
				break; // ���α׷� ����
			Vector<Integer> noVector = searchWord(line); // line�� �ܾ �����ϴ¸�� ���� ��ȣ ����
			printLines(noVector);
		}
		scanner.close();
		System.out.println("���α׷��� �����մϴ�.");
	}
	
	public static void main(String[] args) {
		Lab8_1 ws = new Lab8_1();
		ws.run();
	}
}
