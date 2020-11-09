
public class Rectangle {
	private int x, y, width, height;
	
	public Rectangle(int x, int y, int width, int height) {

		// 4�� �μ���(�簢���� ���� �� �𼭸� ��ǥ(x, y), ����(width), ����(height))�� 
		//  �ش� �Ӽ�(�ʵ�)������ �����ϴ� �ڵ� �߰�
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;		
	}
	
	public void show() {

      // �簢���� ��ġ�� ũ�⸦ ����ϴ� print�� �߰�	
		System.out.printf("(%d,%d)���� ũ�Ⱑ %dx%d�� �簢��\n", this.x, this.y, this.width, this.height);
	}
	
	public int square() { 
		return this.width * this.height; // ���� ���Ͽ� �����ϵ��� ����
	}
	
	public boolean contains(Rectangle r) {
		if((this.x < r.x) && (this.y < r.y) && (this.x + this.width > r.x + r.width) && (this.y + this.height > r.y + r.height))
			return true;
		else
			return false; 
          // �ڽ�(this)�� �簢��(r)�� �����ϸ� true, �ƴϸ� false �����ϵ��� if-else������ �ϼ� 
	}
	
	public static void main(String args[]) {
		Rectangle r = new Rectangle(2, 2, 8, 7);
		Rectangle s = new Rectangle(5, 5, 6, 6);
		Rectangle t = new Rectangle(1, 1, 10, 10);

		r.show();  // ���ڿ� "(2,2)���� ũ�Ⱑ 8x7�� �簢��" �� ��µǵ��� �����ڿ� show() �޼ҵ� �ϼ�
		System.out.println("s�� ������ " + s.square());
		           // ���ڿ� "s�� ������ 36" �� ��µǵ��� �޼ҵ� square() �޼ҵ� �ϼ�
		if(t.contains(r)) System.out.println("t�� r�� �����մϴ�."); 
		           // t.contains(r) �� true�� ���ϵǰ�,
		if(t.contains(s)) System.out.println("t�� s�� �����մϴ�.");
                   // t.contains(s) �� false�� ���ϵǵ��� contains() �޼ҵ� �ϼ�
	}

}
