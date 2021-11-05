import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.util.*;
import java.awt.*;

public class MazeGUI extends JFrame {
		/**
	 * 
	 */
	private static final long serialVersionUID = -5455213345015413807L;
		private JMenuBar menu;
		private JMenu 	 file;
		private JMenu    help;

		
		
		private int[][] maze= 
				{ {1,1,1,1,1,1,1,1,1,1,1,1,1},
				  {1,-1,1,0,1,0,1,0,0,0,0,0,1},
				  {1,0,1,0,0,0,1,0,1,1,1,0,1},
				  {1,0,0,0,1,1,1,0,0,0,0,0,1},
				  {1,0,1,0,0,0,0,0,1,1,1,0,1},
				  {1,0,1,0,1,1,1,0,1,0,0,0,1},
				  {1,0,1,0,1,0,0,0,1,1,1,0,1},
				  {1,0,1,0,1,1,1,0,1,0,1,0,1},
				  {1,0,0,0,0,0,0,0,0,0,1,9,1},
				  {1,1,1,1,1,1,1,1,1,1,1,1,1}

				};
		
		
		
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						MazeGUI frame = new MazeGUI();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		
		public MazeGUI() {
			initComponents();
		}
		
		private void initComponents() {
			
			setSize( 700, 700);
			setResizable(false);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			setLocationRelativeTo(null);
			setTitle("Trivia-Maze");
			newPanel = new mazeGame();
			newPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(newPanel);
			newPanel.setLayout(new BorderLayout(0,0));
			
			menu= new JMenuBar();
			setJMenuBar(menu);
			
			file = new JMenu("File");
			help = new JMenu("Help");
			
			menu.add(file);
			menu.add(help);
			
			JMenuItem saveGame 		= new JMenuItem("Save Game");
			JMenuItem loadGame 		= new JMenuItem("Load Game");
			JMenuItem exit     		= new JMenuItem("Exit");
			JMenuItem about    		= new JMenuItem("About");
			JMenuItem instruction   = new JMenuItem("Game Instruction");
			JMenuItem hints		    = new JMenuItem("Game Hints");
			
			file.add(saveGame);
			file.add(loadGame);
			file.add(exit);
			help.add(about);
			help.add(instruction);
			help.add(hints);
			
			
			
		}
		
		private JPanel	 newPanel;
		
		class mazeGame extends JPanel{
			mazeGame(){
				setPreferredSize(new Dimension(600,600));
			}
			
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			
			g.translate(45, 45);
			
			for(int i=0; i<maze.length; i++) {
				for(int j= 0; j<maze[0].length; j++) {
					Color color;
					switch(maze[i][j]) {
					case -1 : color = Color.GREEN; break;
					case 1  : color = Color.WHITE; break;
					case 9  : color = Color.RED; break;
					default : color = Color.BLACK;
					}
					g.setColor(color);
					g.fillRect(45 * j, 45 * i, 45,45);
					g.setColor(Color.WHITE);
					g.drawRect(45 * j, 45 * i, 45,45);
					
					}
				}
			}
		}
}
		