import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class Pit extends JButton {

	private boolean overState = false;
	private boolean pressState = false;
	public boolean colorState = false;
	private int value;
	public String type;
	public int x, y, ID;

	public Pit(int value, String type, int x, int y, int ID) {
		super(Integer.toString(value));
		this.value = value;
		this.x = x;
		this.y = y;
		this.ID = ID;
		this.type = type;
		setOpaque(false);
		setFocusPainted(false);
		setBorderPainted(false);
		

		MouseAdapter listener = new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent curr) {
				if (contains(curr.getX(), curr.getY())) {
					pressState = true;
					repaint();
				}
			}

			@Override
			public void mouseReleased(MouseEvent curr) {
				pressState = false;
				repaint();
			}

			@Override
			public void mouseExited(MouseEvent curr) {
				overState = false;
				pressState = false;
				repaint();
			}

			@Override
			public void mouseMoved(MouseEvent curr) {
				overState = contains(curr.getX(), curr.getY());
				repaint();
			}
		};

		if (type.equals("user")) {
			addMouseListener(listener);
			addMouseMotionListener(listener);
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return (new Dimension(x, y));
	}

	public int getValue() {
		return this.value;
	}

	public void setValue(int curr) {
		value = curr;
	}

	public int getID() {
		return ID;
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();

		if ((pressState && value > 0) || colorState) {
			g2.setColor(Runner.COLOR_2);
		} else {
			g2.setColor(Runner.COLOR_3);
		}
		g2.fillOval(0, 0, x, y);
		if (overState) {
			g2.setColor(Runner.COLOR_4);
		} else {
			g2.setColor(Runner.COLOR_5);
		}
		g2.drawOval(0, 0, x, y);
		g2.setPaint(Color.BLACK);
		FontMetrics metrics = g.getFontMetrics(getFont());
		int stringWidth = metrics.stringWidth(getText());
		int stringHeight = metrics.getHeight();
		g2.drawString(Integer.toString(value), getWidth() / 2 - stringWidth / 2, getHeight() / 2 + stringHeight / 4);
		g2.dispose();

	}
}