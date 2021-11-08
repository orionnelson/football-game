package view;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * This class extends JMenuBar and represents the Menu for 
 * user to pick options.
 *
 */
public class GameMenuBar extends JMenuBar {

	/**
	 * Constructor to initialize the menu bar with items.
	 * @param menubarListener  Listens for keyboard input allows the user to create a
	 * a new game (N), pause (P) , resume (R), or exit (E)
	 */
	public GameMenuBar(ActionListener menubarListener) {
		super();
		JMenu gameMenu = new JMenu("Game");
		gameMenu.add(createMenuItem("New game", "NEW", KeyEvent.VK_N, menubarListener));
		gameMenu.addSeparator();
		gameMenu.add(createMenuItem("Exit", "EXIT", KeyEvent.VK_Q, menubarListener));
		super.add(gameMenu);
		JMenu controlMenu = new JMenu("Control");
		controlMenu.add(createMenuItem("Pause", "PAUSE", KeyEvent.VK_P, menubarListener));
		controlMenu.add(createMenuItem("Resume", "RESUME", KeyEvent.VK_R, menubarListener));
		super.add(controlMenu);
	}

	/**
	 * Helper method to constructor to create the menu.
	 * @param text Used to create generic text for menu
	 * @param actionCommand Action defined for user interaction (in this case, New, Exit, Pause, and Resume)
	 * @param accelerator The keyboard input received by the user
	 * @param listener Required for each menu item as it is used to capture the action event
	 * @return created menu item.
	 */
	private JMenuItem createMenuItem(String text, String actionCommand, int accelerator, ActionListener listener) {
		JMenuItem menuItem = new JMenuItem(text);
		menuItem.setActionCommand(actionCommand);
		menuItem.addActionListener(listener);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(accelerator, 0));
		return menuItem;
	}

}
