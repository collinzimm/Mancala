import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MainMenu extends JPanel {
	
	String button1Text = "Start Game";
	String button2Text = "How to Play";
	String button3Text = "Statistics";
	String button4Text = "Quit";
	
	Image img;
	

	public MainMenu(JFrameBase curr){
		
		setName(Runner.MAIN_MENU_TITLE);
		
		// Sets layout of panel
//		BoxLayout boxlayout = new BoxLayout(this, BoxLayout.X_AXIS);
		setLayout(null);
		
		ActionListener click = new ClickListener(curr);
		
		
		try {
			img = ImageIO.read(getClass().getResource("Assets/MainBg.png"));
		} catch (IOException e) {}
		

//		JButton b1 = new JButton(button1Text);
//		JButton b2 = new JButton(button2Text);
//		JButton b3 = new JButton(button3Text);
//		JButton b4 = new JButton(button4Text);
//

//		
//		
//		b1.setAlignmentX(CENTER_ALIGNMENT);
//		b2.setAlignmentX(CENTER_ALIGNMENT);
//		b3.setAlignmentX(CENTER_ALIGNMENT);
//		b4.setAlignmentX(CENTER_ALIGNMENT);
		
		GameButton b1 = new GameButton(button1Text, 123, 123);
		GameButton b2 = new GameButton(button2Text, 123, 123);
		GameButton b3 = new GameButton(button3Text, 123, 123);
		GameButton b4 = new GameButton(button4Text, 123, 123);
		
		b1.addActionListener(click);
		b2.addActionListener(click);
		b3.addActionListener(click);
		b4.addActionListener(click);
//		
//		b1.setAlignmentY(5);
//		b1.setAlignmentX(13);
		
		Dimension size = b1.getPreferredSize();
		b1.setBounds(13, 250, size.width, size.height);
		b2.setBounds(163, 250, size.width, size.height);
		b3.setBounds(313, 250, size.width, size.height);
		b4.setBounds(463, 250, size.width, size.height);
		
		add(b1);
		add(b2);
		add(b3);
		add(b4);
		
//		add(b2);
//		add(b3);
//		add(b4);
		
		curr.update(this);

	}


	private class ClickListener implements ActionListener {
		private JFrameBase currBase;

		public ClickListener(JFrameBase currBase) {
			this.currBase = currBase;
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals(button1Text))
				goQuickStart(currBase);
			else if (e.getActionCommand().equals(button2Text))
				rules(currBase);
			else if (e.getActionCommand().equals(button3Text))
				goStats(currBase);
			else if (e.getActionCommand().equals(button4Text))
				System.exit(0);

		}
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		g.drawImage(img, 0, 0, null);
	}
	

	private void rules(JFrameBase curr) {
		Rule rule = new Rule(curr);
	}
	
	private void goQuickStart(JFrameBase curr) {
		QuickStart quickstart = new QuickStart(curr);
	}
	
	private void goStats(JFrameBase curr) {
		StatisticPanel statisticpanel = new StatisticPanel(curr);
	}
}
