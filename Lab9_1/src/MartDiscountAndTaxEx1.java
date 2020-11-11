
import java.util.*;


    class Goods
    {
        public double Price;
        public int Quantity;
        public String kind;
        public String maker;
        public double FinalPrice;
        public int FinalQuantity;
        
        Goods(double Price, int Quantity, String kind, String maker)
        {
        	this.Price = this.FinalPrice = Price;
        	this.Quantity = this.FinalQuantity = Quantity;
        	this.kind = kind;  
        	this.maker = maker;
        }

        public double GetTotal()
        {
        	return FinalPrice * FinalQuantity;
        }
        public void setPrice(CustomerInformation ci)
        {
        	if(ci.level == 0) { level0Price(); }
        	else if(ci.level == 1) { level1Price(); }
        	else if(ci.level == 2) { level2Price(); }
        	else { level3Price(ci); }
        }
        public void level0Price()
        {
        	this.FinalPrice = this.Price;
        }
        public void level1Price()
        {
        	level0Price();
        	if(this.kind.equals("apple") || this.kind.equals("Mario3"))
        	{
        		this.FinalQuantity = this.Quantity - (this.Quantity/3);
        		this.FinalPrice = 
        				this.Price * 0.9;
        	}
        }
        public void level2Price()
        {
        	level0Price();
        	level1Price();
        	if(this.kind.equals("dish") || this.kind.equals("samdasu")) 
        	{
        		this.FinalQuantity = this.Quantity - (this.Quantity/2);
        		this.FinalPrice = this.Price - 2000;
        	}
        }
        public void level3Price(CustomerInformation ci)
        {
        	level0Price();
        	level1Price();
        	level2Price();
        	if(this.kind.equals("jade") || this.kind.equals("jade-best"))
        	{
        		if(ci.member)
        		{
        			this.FinalPrice *= 0.7;
        		}
        	}
        }
    }
    
    class ShopCart
    {
        public Vector<Goods> items;
        CustomerInformation ci;

        public ShopCart(Vector<Goods> items, CustomerInformation ci) 
        {
            this.items = items;
            this.ci = ci;
        }
        
        public double GetTotal()
        {
            double cartTotal = 0;
            for(Goods item : items)
            {
                cartTotal += item.GetTotal();
            }
            return cartTotal;
        }
    }

    class CustomerInformation {
    	public String name;
    	public String card;
    	public boolean member;
    	public int level;
    	
    	CustomerInformation(String name, String card, boolean member, int level) {
    		this.name = name;
    		this.card = card;
    		this.member = member;
    		this.level = level;
    	};
    }
    
    
public class MartDiscountAndTaxEx1 {

	public static void main(String [] args) {
		
		// (īƮ�� ��ǰ����)
		//
		//   ��ǰ��:"blouse",     ������:"CJ",       �ܰ�:50000,  ����:5
		//   ��ǰ��:"apple",      ������:"Nongshim", �ܰ�:20000,  ����:3
		//   ��ǰ��:"dish",       ������:"Cornell",  �ܰ�:5000,   ����:4
		//   ��ǰ��:"Mario3",     ������:"nintendo", �ܰ�:35000,  ����:5
		//   ��ǰ��:"samdasu",    ������:"Hanra",    �ܰ�:4000,   ����:2
		//   ��ǰ��:"jade",       ������:"Jin",      �ܰ�:200000, ����:2
		//   ��ǰ��:"pearl",      ������:"Jin",      �ܰ�:300000, ����:2
		//   ��ǰ��:"jade-best",  ������:"Sun",      �ܰ�:400000, ����:2
		//   ��ǰ��:"pearl-best", ������:"Sun",      �ܰ�:600000, ����:2

		
		// (��ǰ, ī�װ�) ��
		//
		//   ��ǰ��:"blouse",     ī�װ�:"cloth"
		//   ��ǰ��:"apple",      ī�װ�:"food"
		//   ��ǰ��:"dish",       ī�װ�:"kitchen"
	    //   ��ǰ��:"Mario3",     ī�װ�:"game"
	    //   ��ǰ��:"samdasu",    ī�װ�:"drink"
	    //   ��ǰ��:"jade",       ī�װ�:"jewel"
	    //   ��ǰ��:"pearl",      ī�װ�:"jewel"
	    //   ��ǰ��:"jade-best",  ī�װ�:"jewel"
	    //   ��ǰ��:"pearl-best", ī�װ�:"jewel"

		
		// (����īƮ ������)
		//
		//   ����:"ȫ�浿", ����ī��:"�Ｚ", ����ȸ��:true, ������:3

		
		//
		//----------------------------------------
		// (�ǽ�1) ��ǰ �� �⺻ ������å ������ ���� �ݾ� ���
		//----------------------------------------
		//
		//   1) (�⺻��å(t))   ��ǰ�� ������å�� �������� �ʰ� ���� ���� ������
		//   2) (������å1(t1)) ��ǰ 3���� 2���� ���(2+1��å) + �ܰ��� 10% ����
		//   3) (������å2(t2)) ��ǰ 2���� 1���� ���(1+1��å) + �ܰ��� 2õ�� ���� 
		//   4) (������å3(t3)) ���� ȸ�����̸� 30% ����
		//
		//     - ��ǰ("blouse", "pearl", "pearl-best"): �⺻��å ����
		//     - ��ǰ("apple", "Mario3")              : ������å1 ����
		//     - ��ǰ("dish", "samdasu")              : ������å2 ����
		//     - ��ǰ("jade", "jade-best")            : ������å3 ����
		//
		
		
		// �� ��ǰ��  �ش� ī�װ� �� ����
		HashMap<String, String> cate = new HashMap<String, String>();
		cate.put("blouse", "cloth");
		cate.put("apple", "food");
		cate.put("dish", "kitchen");
		cate.put("Mario3", "game");
		cate.put("samdasu", "drink");
		cate.put("jade", "jewel");
		cate.put("pearl", "jewel");
		cate.put("jade-best", "jewel");
		cate.put("pearl-best", "jewel");
		
		// ������ ��ü ����
		CustomerInformation ci= new CustomerInformation("ȫ�浿", "�Ｚ", true, 3);
		
		
		//
		// ����īƮ�� �Ǹ� ���� ��ǰ(Goods) ��ü ����
		//
		
		Vector<Goods> items = new Vector<Goods>();
		items.add(new Goods(50000, 5, "blouse", "CJ"));
		items.add(new Goods(20000, 3, "apple", "Nongshim"));
		items.add(new Goods(5000, 4, "dish", "Cornell"));
		items.add(new Goods(35000, 5, "Mario3", "nintendo"));
		items.add(new Goods(4000, 2, "samdasu", "Hanra"));
		items.add(new Goods(200000, 2, "jade", "Jin"));
		items.add(new Goods(300000, 2, "pearl", "Jin"));
		items.add(new Goods(400000, 2, "jade-best", "Sun"));
		items.add(new Goods(600000, 2, "pearl-best", "Sun"));
		

		// ���峻 4���� ������å ��ü(�⺻��å(t), ������å1(t1), ������å2(t2), ������å3(t3)) �ڵ� ����
		//
		// (��ǰ �� ������å)
		//
		//   1) (�⺻��å(t))   ��ǰ�� ������å�� �������� �ʰ� ���� ���� ������
		//   2) (������å1(t1)) ��ǰ 3���� 2���� ���(2+1��å) + �ܰ��� 10% ����
		//   3) (������å2(t2)) ��ǰ 2���� 1���� ���(1+1��å) + �ܰ��� 2õ�� ���� 
		//   4) (������å3(t3)) ���� ȸ�����̸� 30% ����
		//
		//     - ��ǰ("blouse", "pearl", "pearl-best"): �⺻��å ����
		//     - ��ǰ("apple", "Mario3")              : ������å1 ����
		//     - ��ǰ("dish", "samdasu")              : ������å2 ����
		//     - ��ǰ("jade", "jade-best")            : ������å3 ����
		//		
		


		
		////--------------------------------------------------------------------------------------
        //// <�ǽ�9_1> ������å�� �ڵ�� �����ϴ� ����� �����ϱ� ���Ͽ� ���α׷��� ��� �κе��� �����Ӱ� �߰�/����/������ ��
		////   ��, �ڵ� �ߺ��� �κе��� �ִ��� �����ϱ� ���Ͽ� �����ҵ��� �����(��: �޼ҵ� ��)�� ���� ������ ��
		////--------------------------------------------------------------------------------------

		
		
		
		// ����īƮ(ShopCart) ��ü ����
		ShopCart sc = new ShopCart(items, ci);

		
		// īƮ �� ��ǰ�� �ݾ� �� �ѱݾ� ���
		System.out.println("--------------------------------------------");
		System.out.println(" [�ǽ�9-1] īƮ ��ǰ �� ������å ���� ���� ��ü�ݾ� ���");
		System.out.println("--------------------------------------------\n");
		for(Goods p: items)
		{
			p.setPrice(ci);
			System.out.println(" (��ǰ��) " + p.kind + ",  (�հ�) " + p.GetTotal() + "��");
		}
		System.out.println("\n (�ѱݾ�) " + sc.GetTotal() + "��" + "\n");
			
	}
		
}