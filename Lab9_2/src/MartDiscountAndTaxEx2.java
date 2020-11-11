import java.util.*;

    interface SalesLogicForSummation<T>
    {
    	double sum(T p);
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
    
    class DiscountCoupon {
    	String kind;
    	double discount;
    	double rate;
    	
        DiscountCoupon(String kind, double discount, double rate) {
        	this.kind = kind;
        	this.discount = discount;
        	this.rate = rate;
        }
    }
    
    class  SalesPromotion {
    	boolean vip;
    	boolean membercard;
    	public double threshold;
    	public double discount;
    	
    	SalesPromotion(boolean vip, boolean membercard, double threshold, double discount) {
    		this.vip = vip;
    		this.membercard = membercard;
    		this.threshold = threshold;
    		this.discount = discount;
    	}
    }
    
    class TaxInformation {
    	public double basicRate;
    	public HashMap<String, Double> rate;
    	TaxInformation(double basicRate, HashMap<String, Double> rate) {
    		this.basicRate = basicRate;
    		this.rate = rate;
    	}
    	double getRate(String cate) {
    		Double d = rate.get(cate);
    		return d == null ? basicRate : d;
    	}
    }

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
        public double AdjustPrice(SalesLogicForSummation<ShopCart> isp) {  // delete method itself
        	return isp.sum(this);
        }
       
    }

    
public class MartDiscountAndTaxEx2 {

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

		
		// (����īƮ ������)
		//
		//   ����:"ȫ�浿", ����ī��:"�Ｚ", ����ȸ��:true, ������:3

		
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

		
		// (���� �Ѱ��� 2���� ������ ������ ��ü ����)
		//
		//     - "samdasu": ���� 500�� ����
		//     - "Mario3": ���� 5000�� ������ �ݾ׿� 40% �߰�����
		//

		
		// (���庰 �Ǹ� ���� ��ü ����)
		//
		//   ���δ��: VIP��(level 3 �̻�) Ȥ�� ��������ī��("�Ｚ")�� ����ī��� ����
		//   ���αݾ�: 100���� �̻� ����� 20���� ����
		//

		
		// (���� ���� ��ü ����)
		//
		//   �Ϲ� ��ǰ                      : �ΰ��� 10%
		//   ���Ĺ�("food")     : �鼼
		//   ���Ӱ�����ǰ("game") : Ư���Һ� 30%
		//   ������("jewel")    : ��ġ��ǰ�� 40%
		//


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

		
		//
		//--------------------------------------------------------------------------
		// (�ǽ�2) Ư�� ������å �� ������ �����ϴ� ���, ������ ��ǰ�鿡 ���� ������å ���� �� �ѱݾ� ���
		//--------------------------------------------------------------------------
		//
		// (Ư�� ������å �� ���� ����) (������, ī������, ������)�� �Ǹ�����, ����, ���� ���⼼��, ���庰 �Ǹ�����, ���ݰ��
		//
		//   5) (������ �Ǹ�����) ��ǰ1��("food", "drink" ���) �� ����, ��� ��ǰ ��ü�ݾ� 3���� �ʰ��� 10% ����
		//   6) (������ �Ǹ�����) īƮ�� �Ǹ� ��ǰ �� ����������ü ("Jin")�� ��ǰ ���� �հ谡 50���� �̻��̸� 40% ����, �����̸� 20% ����
		//   7) (ī��� �Ǹ�����) �Ｚī��� ����� ��ü�ݾ��� 20% ����
		//   8) (������ �Ǹ�����) īƮ�� �Ǹ� ��ǰ �� ī�װ��� ����("jewel")�� �ش�Ǵ� ��ǰ ��ü�� ���Ͽ� 10% ����
		//   9) (��ǰ ����) ���� ������ �����鿡 ���Ͽ� �ش� ��ǰ ����
		//                  - ��ǰ("samdasu")����: ���� 500�� ����
		//                  - ��ǰ("Mario3")����: ���� 5000�� ������ �ݾ׿� ���� 40% �߰�����
		//  10) (���� ���⼼��) ��ü�ݾ��� 50���� ������ 4���� ����
		//  11) (���庰 �Ǹ� ����) VIP��(level 3 �̻�) Ȥ�� ���� ���� ī��(�Ｚī��) �������� --> 100���� �̻� ����� 20���� ����
		//  12) (���ݰ��) �Ϲ� ��ǰ(�ΰ��� 10%), ���Ĺ���(�鼼), ���Ӱ�����ǰ(Ư���Һ�:30%), ������(����ǰ��:40%)
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
		
		// ���峻 4���� ������å ��ü(�⺻��å(t), ������å1(t1), ������å2(t2), ������å3(t3)) ����
				
		// ����īƮ�� �Ǹ� ���� ��ǰ(Goods) ��ü ���� �� ������å ����
		//
		// (��ǰ �� ������å ����)
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
		Vector<Goods> items = new Vector<Goods>();
		items.add(new Goods(50000, 5, "blouse", "CJ"));  // 9�� ��ǰ �������� ������ �μ� ����
		items.add(new Goods(20000, 3, "apple", "Nongshim"));
		items.add(new Goods(5000, 4, "dish", "Cornell"));
		items.add(new Goods(35000, 5, "Mario3", "nintendo"));
		items.add(new Goods(4000, 2, "samdasu", "Hanra"));
		items.add(new Goods(200000, 2, "jade", "Jin"));
		items.add(new Goods(300000, 2, "pearl", "Jin"));
		items.add(new Goods(400000, 2, "jade-best", "Sun"));
		items.add(new Goods(600000, 2, "pearl-best", "Sun"));
		
		// ����īƮ(ShopCart) ��ü ����
		ShopCart sc = new ShopCart(items, ci);

		// īƮ �� ��ǰ�� �ݾ� �� �ѱݾ� ���
		System.out.println("------------------------------------------");
		System.out.println(" [�ǽ�9-1] īƮ ��ǰ �������� ���� ���� ��ü�ݾ� ���");
		System.out.println("------------------------------------------\n");
		for(Goods p: items)
		{
			p.setPrice(ci);
			System.out.println(" (��ǰ��) " + p.kind + ",  (�հ�) " + p.GetTotal() +  "��");
			//System.out.println("debugging, Quantity:" + p.FinalQuantity + "\tPrice: " + p.FinalPrice);
		}
		System.out.println("\n (�ѱݾ�) " + sc.GetTotal() + "��" + "\n");
			

		
		System.out.println("\n-----------------------------------------");
		System.out.println(" [�ǽ�9-2] Ư��������å/���� ���� ���� ��ü�ݾ� ���");
		System.out.println("-----------------------------------------\n");

		//
		// 5) (������ �Ǹ�����) ��ǰ1��("food", "drink" ���) �� ����, ��� ��ǰ ��ü�ݾ� 3���� �ʰ��� 10% ����
		//
		
		SalesLogicForSummation<ShopCart> isp = s -> { double r = 0;
			for(Goods p : s.items) 
				if(cate.get(p.kind) == "food" || cate.get(p.kind) == "drink")
					r += p.FinalPrice * p.FinalQuantity;
			return (r >= 30000) ? r * 0.9 : r;
		};
		System.out.println(" (������ �Ǹ����� ����) \t" + sc.AdjustPrice(isp) + "��");
		
		
		//
		// 6) (������ �Ǹ����� ����) īƮ�� �Ǹ� ��ǰ �� ����������ü ("Jin")�� ��ǰ ���� �հ谡 50���� �̻��̸� 40% ����, �����̸� 20% ����
		//
		
		isp = s -> {
			double r = 0;
			for(Goods p : s.items)
			{
				if(p.maker.equals("Jin"))
					r += p.GetTotal();
			}
			return (r >= 500000) ? r * 0.6 : r * 0.8;
		};
		System.out.println(" (������ �Ǹ����� ����) \t" + sc.AdjustPrice(isp) + "��");
		
		
		//
		// 7) (ī��� �Ǹ����� ����) �Ｚī��� ����� ��ü�ݾ��� 20% ����
		//
		
		isp = s -> { 
			return ci.card.equals("�Ｚ") ? s.GetTotal() * 0.8 : s.GetTotal();
		};
		System.out.println(" (ī��� �Ǹ����� ����) \t" + sc.AdjustPrice(isp) +"��");
	
		
		//
		// 8) (������ �Ǹ����� ����) īƮ�� �Ǹ� ��ǰ �� ī�װ��� ����("jewel")�� �ش�Ǵ� ��ǰ ��ü�� ���Ͽ� 10% ����
		//
		
		isp = s -> {
			double r = 0;
			for(Goods p : items)
			{
				if(cate.get(p.kind)== "jewel" )
					r += p.GetTotal();
			}
			return r * 0.9;
		};
		System.out.println(" (������ �Ǹ����� ����) \t" + sc.AdjustPrice(isp) + "��");

		
		//
		// 9) (��ǰ ���� ����) ���� ������ �����鿡 ���Ͽ� �ش� ��ǰ ����
		//                  - ��ǰ("samdasu")����: ���� 500�� ����
		//                  - ��ǰ("Mario3")����: ���� 5000�� ������ �ݾ׿� ���� 40% �߰�����
		//
		
		// ���� �÷��� ��ü(cp) ����
		HashMap<String, DiscountCoupon> cp = new HashMap<String, DiscountCoupon>();
		cp.put("samdasu", new DiscountCoupon("samdasu", 500.0, 0.0));
		cp.put("Mario3", new DiscountCoupon("Mario3", 5000, 0.4));
		
        isp = s -> {
        	double r = 0;
        	for(Goods p : items)
        	{
        		if(cp.containsKey(p.kind))
        			r += (1 - cp.get(p.kind).rate) * (p.FinalPrice - cp.get(p.kind).discount) * p.FinalQuantity;
        	}
        	return r;
        };
		System.out.println(" (��ǰ ���� ���� ����) \t" + sc.AdjustPrice(isp) +"��");

		
		//
		// 10) (���� ���⼼��) ��ü�ݾ��� 50���� �̻��̸� 4���� ����
		//
		
		isp = s -> {
			return s.GetTotal() > 500000 ? s.GetTotal() - 40000 : s.GetTotal();
		};
		System.out.println(" (���� ���⼼�� ����) \t" + sc.AdjustPrice(isp) + "��");

		
		//
		// 11) (���庰 �Ǹ����� ����) VIP��(level 3 �̻�) Ȥ�� ���� ���� ī��(�Ｚī��) �������� --> 100���� �̻� ����� 20���� ����
		//
		
		// �Ǹ�������å ��ü(sp) ����
		SalesPromotion sp = new SalesPromotion(true, true, 1000000.0, 200000.0);
		
		isp = s -> {
			if((s.ci.level >= 3 && sp.vip) || (s.ci.card.equals("�Ｚ") && sp.membercard))
				return s.GetTotal() >= sp.threshold ? s.GetTotal() - sp.discount : s.GetTotal();
			return s.GetTotal();
		};
		System.out.println(" (���庰 �Ǹ����� ����) \t" + sc.AdjustPrice(isp) +"��");

		
		//
		// 12) (���� ���� ����) �Ϲ� ��ǰ(�ΰ��� 10%), ���Ĺ���(�鼼), ���Ӱ�����ǰ(Ư���Һ�:30%), ������(����ǰ�Һ�:40%)
		//
		
		// ���� ��ü(tax) ����
		HashMap<String, Double> tax = new HashMap<String, Double>();
		tax.put("jewel", 0.4);
		tax.put("food", 0.0);
		tax.put("game", 0.3);
		TaxInformation ti = new TaxInformation(0.1, tax);
		
		isp = s -> {
			double r = 0;
			for(Goods p : s.items) {
				r += p.GetTotal() * (1+ti.getRate(cate.get(p.kind)));
			}
			return r;
		};
		System.out.println(" (���� ���� �� �Ѿ�) \t\t" + sc.AdjustPrice(isp) + "��");
	}
		
}