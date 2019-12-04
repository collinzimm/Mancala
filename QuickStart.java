import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class QuickStart extends JPanel {

	Image img;

	public QuickStart(JFrameBase curr) {

		try {
			img = ImageIO.read(getClass().getResource("Assets/QuickStartBg.png"));
		} catch (IOException e) {}
		setName("How to Play");
		setLayout(null);
		
		GameButton b1 = new GameButton("Okay", 55, 55);
		
		ActionListener click = new ClickListener(curr);
		
		b1.addActionListener(click);
		
		Dimension size = b1.getPreferredSize();
		b1.setBounds(522, 402, size.width, size.height);
		
		add(b1);
		
		curr.update(this);
	}
	
	private class ClickListener implements ActionListener {
		private JFrameBase currBase;

		public ClickListener(JFrameBase currBase) {
			this.currBase = currBase;
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("Okay"))
				buildGame(currBase);
		}
	}

	@Override
	public void paintComponent(Graphics g)
	{
		g.drawImage(img, 0, 0, null);
	}
	
	private void buildGame(JFrameBase currBase) {
		Board board = new Board(currBase);
	}
}
