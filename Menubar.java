import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Menubar extends JMenuBar{
	
	String file1Text = "New Game";
	String file2Text = "Current Stats";
	String file3Text = "Quit";
	String file4Text = "Return to Main Menu";
	
	public Menubar(JFrameBase curr) {
		
		ActionListener click = new ClickListener();
		ActionListener click_frame = new ClickListener(curr);
		
		JMenu file = new JMenu("File");
		
		JMenuItem file1 = new JMenuItem(file1Text);
		JMenuItem file2 = new JMenuItem(file2Text);
		JMenuItem file3 = new JMenuItem(file3Text);
		JMenuItem file4 = new JMenuItem(file4Text);
		
		
		file1.addActionListener(click);
		file2.addActionListener(click);
		file3.addActionListener(click);
		file4.addActionListener(click_frame);
		
		file.add(file1);
		file.add(file2);
		file.addSeparator();
		file.add(file4);
		file.add(file3);
		
		this.add(file);
	}
	
	private class ClickListener implements ActionListener {
		private JFrameBase currBase;

		public ClickListener() {};
		
		public ClickListener(JFrameBase currBase) {
			this.currBase = currBase;
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals(file1Text));
			else if (e.getActionCommand().equals(file2Text));
			else if (e.getActionCommand().equals(file3Text)) {
				JOptionPane quitWarning = new JOptionPane("Are you sure you want to quit?", JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
				JDialog dialog = quitWarning.createDialog("Quitting will forfeit your current progress as well as stats.");
				dialog.setAlwaysOnTop(true);
				dialog.setVisible(true);
				if ((int)quitWarning.getValue() == 0)
					System.exit(0);
			}
			else if (e.getActionCommand().equals(file4Text)) {
				JOptionPane quitWarning = new JOptionPane("Are you sure you want to return to the main menu?", JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
				JDialog dialog = quitWarning.createDialog("Quitting will forfeit your current progress as well as stats.");
				dialog.setAlwaysOnTop(true);
				dialog.setVisible(true);
				if ((int)quitWarning.getValue() == 0)
					mainMenu(currBase);
			}

		}
	}
	
	private void mainMenu(JFrameBase curr) {
		MainMenu mainmenu = new MainMenu(curr);
	}

}
