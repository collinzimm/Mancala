import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import javax.swing.JButton;

public class MancalaPit extends JButton {

	private boolean overState = false;
	private boolean pressState = false;

	public MancalaPit(String text) {
		super(text);
		setOpaque(false);
		setFocusPainted(false);
		setBorderPainted(false);
	}

	@Override
	public Dimension getPreferredSize() {
		return (new Dimension(80, 500));
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();

		g2.setColor(Runner.COLOR_3);
		// g.fillOval(getWidth()/2 - radius, getHeight()/2 - radius, diameter,
		// diameter);
		g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
		g2.setColor(Runner.COLOR_4);
		g2.drawRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
		// g2.setPaint(Color.BLACK);
		g2.drawString(getText(), getWidth(), getHeight() / 2);
		// g2.drawString(str, x, y);
		g2.dispose();

	}
}