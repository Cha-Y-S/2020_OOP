public class Practice3_2 {
	public static void main (String[] args) {
		int intArray[][] = new int[4][4];

		
		// 2���� �迭�� ��� ���Ҹ� 0���� �ʱ�ȭ 
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 4; j++)
			{
				intArray[i][j] = 0;
			}
		}
		
		// �迭�� 9�� ������ġ�� ������ ���� �����Ͽ� ����
		
		int count = 0;
		while(count < 9)
		{
			int row = (int)(Math.random()*4);
			int col = (int)(Math.random()*4);
			
			if(intArray[row][col] == 0)
			{
				intArray[row][col] = (int)(Math.random()*9 + 1);
				count++;
			}
		}
		
		// 2���� �迭 ���
		System.out.println("---- Random Matrix ----");
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 4; j++)
			{
				System.out.print(intArray[i][j] + "  ");
			}
			System.out.println(" ");
		}		
	}
}
