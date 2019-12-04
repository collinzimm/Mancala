import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

//Image from
/*
 * Photo by Mitchell Griest on Unsplash
 * 
 */
public class Board extends JPanel {

	static Pit user_pit1 = new Pit(4, "user", 117, 117, 0);
	static Pit user_pit2 = new Pit(4, "user", 117, 117, 1);
	static Pit user_pit3 = new Pit(4, "user", 117, 117, 2);
	static Pit user_pit4 = new Pit(4, "user", 117, 117, 3);
	static Pit user_pit5 = new Pit(4, "user", 117, 117, 4);
	static Pit user_pit6 = new Pit(4, "user", 117, 117, 4);
	static Pit comp_pit1 = new Pit(4, "comp", 117, 117, 7);
	static Pit comp_pit2 = new Pit(4, "comp", 117, 117, 8);
	static Pit comp_pit3 = new Pit(4, "comp", 117, 117, 9);
	static Pit comp_pit4 = new Pit(4, "comp", 117, 117, 10);
	static Pit comp_pit5 = new Pit(4, "comp", 117, 117, 11);
	static Pit comp_pit6 = new Pit(4, "comp", 117, 117, 12);
	static Pit computerMancala = new Pit(0, "pit", 80, 500, 13);
	static Pit userMancala = new Pit(0, "pit", 80, 500, 6);

	JFrameBase mainScreen;

	Image img;

	boolean userMove = false;

	char player;

	static Pit pits[] = { user_pit1, user_pit2, user_pit3, user_pit4, user_pit5, user_pit6, userMancala, comp_pit1,
			comp_pit2, comp_pit3, comp_pit4, comp_pit5, comp_pit6, computerMancala };

	boolean computerWinner = false;
	boolean userWinner = false;

	public Board(JFrameBase curr) {

		mainScreen = curr;

		try {
			img = ImageIO.read(getClass().getResource("Assets/Wood.jpg"));
		} catch (IOException e) {
		}
		// Sets basic attributes of window
		setName(Runner.GAME_TITLE);
		curr.setJMenuBar(new Menubar(curr));

		setLayout(null);
		Dimension size = computerMancala.getPreferredSize();
		computerMancala.setBounds(16, 15, size.width, size.height);
		userMancala.setBounds(895, 15, size.width, size.height);
		size = user_pit1.getPreferredSize();
		comp_pit6.setBounds(123, 15, size.width, size.height);
		comp_pit5.setBounds(250, 15, size.width, size.height);
		comp_pit4.setBounds(379, 15, size.width, size.height);
		comp_pit3.setBounds(505, 15, size.width, size.height);
		comp_pit2.setBounds(632, 15, size.width, size.height);
		comp_pit1.setBounds(760, 15, size.width, size.height);
		user_pit1.setBounds(123, 390, size.width, size.height);
		user_pit2.setBounds(250, 390, size.width, size.height);
		user_pit3.setBounds(379, 390, size.width, size.height);
		user_pit4.setBounds(505, 390, size.width, size.height);
		user_pit5.setBounds(632, 390, size.width, size.height);
		user_pit6.setBounds(760, 390, size.width, size.height);

		ActionListener click = new ClickListener();

		user_pit1.addActionListener(click);
		user_pit2.addActionListener(click);
		user_pit3.addActionListener(click);
		user_pit4.addActionListener(click);
		user_pit5.addActionListener(click);
		user_pit6.addActionListener(click);

		this.add(computerMancala);
		this.add(userMancala);
		this.add(comp_pit1);
		this.add(comp_pit2);
		this.add(comp_pit3);
		this.add(comp_pit4);
		this.add(comp_pit5);
		this.add(comp_pit6);
		this.add(user_pit1);
		this.add(user_pit2);
		this.add(user_pit3);
		this.add(user_pit4);
		this.add(user_pit5);
		this.add(user_pit6);

		curr.update(this);

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				playGame();
			}
		});

	}

	private class ClickListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (player == 'u' && userMove) {
				player = 'c';
				move((Pit) e.getSource());
				userMove = false;
			}
		}
	}

	private void move(Pit curr) {

		Timer timer = new Timer(500, new ActionListener() {
			int j = curr.getID();
			int value = curr.getValue();

			public void actionPerformed(ActionEvent evt) {
				j++;
				System.out.println(j);
				if (j <= (curr.getID() + value)) {
					if ((player == 'c' && j % 13 == 0) || (player == 'u' && j % 13 == 7)) {
						j++;
					}
					for (Pit x : pits) {
						x.colorState = false;
					}
					pits[j % 14].colorState = true;
					incrementCount(pits[j % 14]);
				} else {
					((Timer) evt.getSource()).stop();
					pits[(j - 1) % 14].colorState = false;
					if (((j - 1) % 14) == 6 && player == 'c') {
						player = 'u';
						userMove = true;
					} else if (((j - 1) % 14) == 13 && player == 'u') {
						player = 'c';
					} 
					else if (pits[(j - 1) % 14].getValue() == 1) {
						if (player == 'c' && 0 <= ((j - 1) % 14) && 5 >= ((j - 1) % 14)) {
							updateCount(pits[6], pits[12 - ((j - 1) % 14)].getValue() + 1);
							updateCount(pits[12 - ((j - 1) % 14)], 0);
							updateCount(pits[j - 1 % 14], 0);
						}
						else if (player == 'u' && 7 <= ((j - 1) % 14) && 12 >= ((j - 1) % 14)) {
							updateCount(pits[13], pits[12 - ((j - 1) % 14)].getValue() + 1);
							updateCount(pits[12 - ((j - 1) % 14)], 0);
							updateCount(pits[j - 1 % 14], 0);
						}
					}

					moveHandler(player);
				}
				repaint();
			}
		});
		updateCount(curr, 0);
		timer.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}

	private void updateCount(Pit curr, int value) {
		curr.setValue(value);
	}

	private void incrementCount(Pit curr) {
		curr.setValue(curr.getValue() + 1);
	}

	private void playGame() {
		
		int i;
		
		Random rand = new Random();
		i = rand.nextInt((1+1) - 0);
		
		if (i == 0)
			player = 'c';
		else
			player = 'u';

		moveHandler(player);

	}

	private void moveHandler(char player) {
		if (!emptyRow()) {
			userMove = false;
			// Dialogbox open
			// Quit game
			// Okay button returns main menu build
			return;
		}

		if (player == 'c') {
			computerMove();
			player = 'u';
		} else {
			userMove = true;
			player = 'c';
		}
	}

	private void computerMove() {
		int moveValue = Integer.MAX_VALUE;
		int move = -1;
		for (int i = 7; i < 13; i++) {
			if (((pits[i].getValue() + i) % 13) == 0) {
				move = i;
				break;
			}
		}
		if (move == -1) {
			for (int i = 7; i < 13; i++) {
				if ((moveValue > (pits[i].ID + pits[i].getValue())) && pits[i].getValue() != 0) {
					moveValue = pits[i].ID + pits[i].getValue();
					move = i;
				}
			}
		}
		player = 'u';
		move(pits[move]);
	}

	private boolean emptyRow() {
		for (int i = 0; i < 6; i++) {
			if (pits[i].getValue() > 0) {
				userWinner = true;
			}
		}
		for (int j = 7; j < 13; j++) {
			if (pits[j].getValue() > 0) {
				computerWinner = true;
			}
		}
		return (computerWinner || userWinner);
	}
}
