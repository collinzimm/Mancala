import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

public class GameButton extends JButton {

	private boolean overState = false;
	private boolean pressState = false;
	private int x;
	private int y;

	public GameButton(String text, int x, int y) {
		super(text);
		this.x = x;
		this.y = y;
		setOpaque(false);
		setFocusPainted(false);
		setBorderPainted(false);

		MouseAdapter listener = new MouseAdapter(){
			
			@Override
			public void mousePressed(MouseEvent curr){
				if(contains(curr.getX(), curr.getY())){
					pressState = true;
					repaint();
				}
			}
			
			@Override
			public void mouseReleased(MouseEvent curr){
				pressState = false;
				repaint();
			}
			
			@Override
			public void mouseExited(MouseEvent curr){
				overState = false;
				pressState = false;
				repaint();
			}
			
			@Override
			public void mouseMoved(MouseEvent curr){
				overState = contains(curr.getX(), curr.getY());
				repaint();
			}	
		};
		
		addMouseListener(listener);
		addMouseMotionListener(listener);		
	}
	
	@Override
	public Dimension getPreferredSize(){
		return (new Dimension(x, y));
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		
		if(pressState){
			g2.setColor(Runner.COLOR_1);
		}
		else{
			g2.setColor(Runner.COLOR_4);
		}
		//g.fillOval(getWidth()/2 - radius, getHeight()/2 - radius, diameter, diameter);
		//g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
		g2.fillOval(0, 0, x, y);
		if(overState){
			g2.setColor(Runner.COLOR_5);
		}
		else{
			g2.setColor(Runner.COLOR_3);
		}
		//g2.drawRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
		//g2.setPaint(Color.BLACK);
		g2.drawOval(0, 0, x, y);
		g2.setColor(Runner.COLOR_5);
		FontMetrics metrics = g.getFontMetrics(getFont());
		int stringWidth = metrics.stringWidth(getText());
		int stringHeight = metrics.getHeight();
		g2.drawString(getText(), getWidth()/2 - stringWidth/2, getHeight()/2 + stringHeight/4);
		//g2.drawString(str, x, y);
		g2.dispose();
		
	}
}