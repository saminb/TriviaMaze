import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;


public class MazeFrame extends JFrame implements ActionListener {

    private Maze myMaze;

    private JPanel roomView, textView, textViewL;
    private JLabel flavorText, flavorTextL;
    private JButton upButton, downButton, leftButton, rightButton, player;
    private ImageIcon lock, wall, wrong, blank;
    private ImageIcon[] goDir, playerSprites;
    private JMenuItem newGame, saveGame, loadGame, exit, about, instructions, cheats;

    private boolean gameOver = false;
    private boolean vertMove = false;
    private boolean cheatsOn = false;

    MazeFrame() {
        initializePanels();
        initializeMenu();

        this.setTitle("Trivia Maze");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(600,500);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.add(textView);
        this.add(textViewL);
        this.add(roomView);

        this.setVisible(true);
    }

    private void initializePanels() {
        roomView = new RoomPanel();
        textView = new JPanel();
        textViewL = new JPanel();
        flavorText = new JLabel();
        flavorTextL = new JLabel();

        roomView.setBounds(50,30,500,300);
        roomView.setLayout(null);
        roomView.setBackground(Color.lightGray);

        initializeButtons();

        roomView.add(upButton);
        roomView.add(downButton);
        roomView.add(leftButton);
        roomView.add(rightButton);
        roomView.add(player);

        flavorText.setFont(new Font("Calibri", Font.PLAIN, 16));
        flavorText.setForeground(new Color(100, 100, 150));
        textView.setBounds(30, 365, 540, 25);
        textView.setBackground(Color.lightGray);
        textView.add(flavorText);

        flavorTextL.setFont(new Font("Calibri", Font.PLAIN, 16));
        flavorTextL.setForeground(new Color(100, 100, 150));
        textViewL.setBounds(30, 390, 540, 25);
        textViewL.setBackground(Color.lightGray);
        textViewL.add(flavorTextL);

        beginGame();
    }

    private void initializeButtons() {
        lock = new ImageIcon("gfx/Lock.png");
        wall = new ImageIcon("gfx/Wall.png");
        wrong = new ImageIcon("gfx/Wrong.png");
        blank = new ImageIcon("gfx/Blank.png");

        goDir = new ImageIcon[4];
        goDir[0] = new ImageIcon("gfx/GoUp.png");
        goDir[1] = new ImageIcon("gfx/GoDown.png");
        goDir[2] = new ImageIcon("gfx/GoLeft.png");
        goDir[3] = new ImageIcon("gfx/GoRight.png");

        playerSprites = new ImageIcon[6];
        playerSprites[0] = new ImageIcon("gfx/Sam/PlayerNeutral.png");
        playerSprites[1] = new ImageIcon("gfx/Sam/PlayerPonder.png");
        playerSprites[2] = new ImageIcon("gfx/Sam/PlayerDefeat.png");
        playerSprites[3] = new ImageIcon("gfx/Sam/PlayerVictory.png");
        playerSprites[4] = new ImageIcon("gfx/Sam/PlayerBegin.png");
        playerSprites[5] = new ImageIcon("gfx/Sam/PlayerWonder.png");

        upButton = new JButton();
        upButton.setBounds(225,25,50,50);
        upButton.addActionListener(this);
        upButton.setIcon(lock);

        downButton = new JButton();
        downButton.setBounds(225,225,50,50);
        downButton.addActionListener(this);
        downButton.setIcon(lock);

        leftButton = new JButton();
        leftButton.setBounds(25,125,50,50);
        leftButton.addActionListener(this);
        leftButton.setIcon(lock);

        rightButton = new JButton();
        rightButton.setBounds(425,125,50,50);
        rightButton.addActionListener(this);
        rightButton.setIcon(lock);

        player = new JButton();
        player.setIcon(playerSprites[0]);
        player.setBounds(200, 100, 100, 100);
        player.addActionListener(this);
    }

    private void initializeMenu() {
        JMenuBar menu = new JMenuBar();
        setJMenuBar(menu);

        JMenu file = new JMenu("File");
        JMenu help = new JMenu("Help");

        menu.add(file);
        menu.add(help);

        newGame 		= new JMenuItem("New Game");
        saveGame 		= new JMenuItem("Save Game");
        loadGame 		= new JMenuItem("Load Game");
        exit     		= new JMenuItem("Exit");
        about    		= new JMenuItem("About");
        instructions    = new JMenuItem("Game Instruction");
        cheats           = new JMenuItem("Cheats");

        file.add(newGame);
        file.add(saveGame);
        file.add(loadGame);
        file.add(exit);
        help.add(about);
        help.add(instructions);
        help.add(cheats);

        newGame.addActionListener(this);
        saveGame.addActionListener(this);
        loadGame.addActionListener(this);
        exit.addActionListener(this);
        about.addActionListener(this);
        instructions.addActionListener(this);
        cheats.addActionListener(this);
    }

    private static class RoomPanel extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            g.setColor(Color.lightGray);
            g.fillRect(0,0,500,300);
            g.setColor(Color.black);
            g.drawLine(100,0,100,160);
            g.drawLine(100,160,0,300);
            g.drawLine(100,160,400,160);
            g.drawLine(100,159,400,159);
            g.drawLine(400,0,400,160);
            g.drawLine(400,160,500,300);
        }
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == upButton && !gameOver) {
            vertMove = true;
            attemptMove(0);
        }
        else if (e.getSource() == downButton && !gameOver) {
            vertMove = true;
            attemptMove(1);
        }
        else if (e.getSource() == leftButton && !gameOver) {
            vertMove = false;
            attemptMove(2);
        }
        else if (e.getSource() == rightButton && !gameOver) {
            vertMove = false;
            attemptMove(3);
        }

        else if (e.getSource() == player && !gameOver) {
            player.setIcon(playerSprites[1]);
            cycleText();
        }

        else if (e.getSource() == newGame) {
            beginGame();
        }
        else if (e.getSource() == saveGame) {
            setText("Save current game to a file", "(i'll have to confirm with the professor about serialization)");
        }
        else if (e.getSource() == loadGame) {
            setText("Load game from a file", "(i'll have to confirm with the professor about serialization)");
        }
        else if (e.getSource() == exit) {
            setText("Closing...", "");
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
        else if (e.getSource() == about) {
            setText("Display information about program", "(this feature hasn't been implemented yet)");
        }
        else if (e.getSource() == instructions) {
            setText("Display instructions for the game", "(this feature hasn't been implemented yet)");
        }
        else if (e.getSource() == cheats && !gameOver) {
            if (cheatsOn) {
                cheatsOn = false;
                setText("As suddenly as it appeared, the power seems to fade...", "Cheat mode deactivated.");
                player.setIcon(playerSprites[0]);
            } else {
                cheatsOn = true;
                setText("Just when all hope seemed lost, you felt an incredible power!", "Cheat mode activated.");
                player.setIcon(playerSprites[5]);
            }
        }
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private void attemptMove(int Dir) {
        int myH = myMaze.getPlayerH();
        int myW = myMaze.getPlayerW();
        int state = myMaze.checkDoor(myH, myW, Dir);
        boolean missedAnswer = false;

        if (state == 0) {
            if (cheatsOn) {
                setText("Focusing your power into a laser, you cut through the door in seconds!", "How wondrous...");
                myMaze.setDoor(myH, myW, Dir, true);
            } else {
                setText("For no reason whatsoever, the door suddenly locked.", "");
                missedAnswer = true;
                myMaze.setDoor(myH, myW, Dir, false);
            }
        }
        else if (state == 1) {
            if (Dir == 0) {
                myMaze.setPlayerPosition(myH - 1, myW);
            } else if (Dir == 1) {
                myMaze.setPlayerPosition(myH + 1, myW);
            } else if (Dir == 2) {
                myMaze.setPlayerPosition(myH, myW - 1);
            } else if (Dir == 3) {
                myMaze.setPlayerPosition(myH, myW + 1);
            }
            cycleText();
        }
        else if (state == 2) {
            if (cheatsOn) {
                setText("Focusing your power into a laser, you cut through the door in seconds!", "How wondrous...");
                myMaze.setDoor(myH, myW, Dir, true);
            } else {
                setText("The door locked when you answered its question wrong.", "You'll have to find another way.");
            }
        }
        else {
            setText("That's a wall.", "If only you'd taken that \"Phasing Through Walls\" class in college.");
        }

        if (cheatsOn) {
            player.setIcon(playerSprites[5]);
        } else {
            player.setIcon(playerSprites[0]);
        }

        updateDoorIcons();
        if(myMaze.isSolved()) {
            gameWon();
        } else if (missedAnswer) {
            if (!myMaze.solvable(myH, myW)) {
                gameLost();
            }
        }

    }

    private void updateDoorIcons() {
        if (myMaze == null) {
            setAllDoorIcons(new int[] {-1,-1,-1,-1});
        } else {
            setAllDoorIcons(myMaze.checkAllDoors(myMaze.getPlayerH(), myMaze.getPlayerW()));
        }
    }

    private void setDoorIcon(int Dir, int newState) {
        ImageIcon newImage;
        if (newState == -1) {
            newImage = blank;
        } else if (newState == 0) {
            newImage = lock;
        } else if (newState == 2) {
            newImage = wrong;
        } else if (newState == 3) {
            newImage = wall;
        } else {
            newImage = goDir[Dir];
        }

        if(Dir == 0) {
            upButton.setIcon(newImage);
        } else if (Dir == 1) {
            downButton.setIcon(newImage);
        } else if (Dir == 2) {
            leftButton.setIcon(newImage);
        } else {
            rightButton.setIcon(newImage);
        }
    }

    private void setAllDoorIcons(int[] doorStates) {
        for (int i = 0; i < 4; i++) {
            setDoorIcon(i, doorStates[i]);
        }
    }

    private void setText(String msg, String msgL) {
        flavorText.setText(msg);
        flavorTextL.setText(msgL);
    }

    private void beginGame() {
        gameOver = false;
        cheatsOn = false;

        myMaze = new Maze(5, 6);
        updateDoorIcons();
        player.setIcon(playerSprites[4]);
        setText("You wake up in a maze with a screaming headache...",
                "You must answer questions about your captors' culture and history to escape.");
    }

    private void gameWon() {
        gameOver = true;
        player.setIcon(playerSprites[3]);
        setText("You've escaped! Now you can return to your people on the mothership!",
                "But the burden of useless trivia may never leave you...");
    }

    private void gameLost() {
        gameOver = true;
        player.setIcon(playerSprites[2]);
        setText("You're trapped in the maze... there's nowhere to go from here.",
                "Your captors will continue to test you until you can answer correctly.");
    }

    private void cycleText() {
        //This array contains all the game's flavor text. I'll keep filling this out.
        String[] allText = {
                "You feel like you are likely to be eaten by a grue.", "You're not, but it feels that way",
                "The air in the maze is breathable, but cold.", "Hopefully you can find the exit soon...",
                "You feel like you're being watched.", "Your captors surely have hidden cameras to supervise your progress.",
                "You feel hungry.", "Come to think of it, when was the last time you ate?",
                "You feel like this is a very inappropriate way to treat an ambassador.", "You will lodge a formal complaint as soon as you escape.",
                "5", "5",
                "6", "6",
                "7", "7",
                "8", "8",
                "9", "9",
                "10", "10",
                "11", "11",
                "12", "12",
                "13", "13",
                "14", "14",
                "15", "15",
                "16", "16",
                "17", "17",
                "18", "18",
                "19", "19"};

        //returns a random number from 0 to 29
        int i = (int) (Math.random() * 30);

        //if i is 25 - 29, you will learn some of your current coordinates.
        //if i is 20 - 24, you will learn some of the exit's coordinates.
        //if i is anywhere from 0 to 19, it will display random flavor text.
        if (i >= 25) {
            if (vertMove) {
                setText("Hmmm... you feel like you're in row " + (myMaze.getPlayerH() + 1) + "...", "");
            } else {
                setText("Hmmm... you feel like you're in column " + (myMaze.getPlayerW() + 1) + "...", "");
            }
        }
        else if (i >= 20) {
            if (vertMove) {
                setText("Somehow, you know that the exit is in row " + (myMaze.getGoalH() + 1) + "...", "");
            } else {
                setText("Somehow, you know that the exit is in column " + (myMaze.getGoalW() + 1) + "...", "");
            }
        }
        else {
            setText(allText[i*2], allText[(i*2) + 1]);
        }
    }
}
