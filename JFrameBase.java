import javax.swing.JFrame;
import javax.swing.JPanel;

public class JFrameBase extends JFrame {

	public JFrameBase() {
		// Sets basic attributes of window
		setSize(Runner.BASE_FRAME_WIDTH, Runner.BASE_FRAME_HEIGHT);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		MainMenu mainMenu = new MainMenu(this);
	}
	
	
	public void update (JPanel curr) {
		
		String currWindow = curr.getName();
		
		if(currWindow.equals(Runner.GAME_TITLE))
			setSize(Runner.PLAY_FRAME_WIDTH, Runner.PLAY_FRAME_HEIGHT);
		else {
			setSize(Runner.BASE_FRAME_WIDTH, Runner.BASE_FRAME_HEIGHT);
			setJMenuBar(null);}
		
		setTitle(currWindow);
		setContentPane(curr);
		revalidate();
		repaint();
	}
	
//public void update (JPanel curr, String file) {
//		
//		String currWindow = curr.getName();
//		
//		if(currWindow.equals(Runner.GAME_TITLE))
//			setSize(Runner.PLAY_FRAME_WIDTH, Runner.PLAY_FRAME_HEIGHT);
//		else {
//			setSize(Runner.BASE_FRAME_WIDTH, Runner.BASE_FRAME_HEIGHT);
//			setJMenuBar(null);}
//		
//		setTitle(currWindow);
//		setContentPane(curr);
//		revalidate();
//		repaint();
//	}

}
