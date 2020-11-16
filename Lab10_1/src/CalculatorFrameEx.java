import java.awt.*;
import javax.swing.*;

public class CalculatorFrameEx extends JFrame {
	public CalculatorFrameEx() {
		super("ȯ�� ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		
		c.add(new SouthPanel(), BorderLayout.SOUTH);
		c.add(new CenterPanel(), BorderLayout.CENTER);
		c.add(new NorthPanel(), BorderLayout.NORTH);
		setSize(300,300);
		setVisible(true);
	}
	public static void main(String[] args) {
		new CalculatorFrameEx();
	}
}

class SouthPanel extends JPanel {
	public SouthPanel() {
		setBackground(Color.YELLOW);
		setOpaque(true);
		add(new JButton("(��)->(�޷�)"));
		add(new JButton("(�޷�)->(��)"));
	}
}

class CenterPanel extends JPanel {
	public CenterPanel() {
		setBackground(Color.WHITE);
		setLayout(new GridLayout(4,3,4,4));
		for(int i = 1; i < 10; i++)
		{
			add(new JButton(Integer.toString(i)));
		}
		add(new JButton("reset"));
		add(new JButton("0"));
		add(new JButton("��ȯ"));
	}
}

class NorthPanel extends JPanel {
	public NorthPanel() {
		setBackground(Color.LIGHT_GRAY);
		setOpaque(true);
		add(new JLabel("(��)"));
		add(new JTextField(10));
		add(new JLabel("     "));
		add(new JLabel("(�޷�)"));
		add(new JTextField(5));
	}
}