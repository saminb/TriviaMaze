import javax.swing.*;

import question.Question;
import question.QuestionDatabaseManager;
import question.QuestionLog;

import java.awt.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class MazeFrame extends JFrame implements ActionListener {

    private static final String[] FILENAMES = {"", "File 1", "File 2", "File 3"};
    private Maze myMaze;

    private QuestionDatabaseManager myQuestionManager; //MazeFrame will hold a QDBM instance
    private QuestionLog myQuestionLog; // Maze Frame will hold the QuestionLog GUI, should be as menu item
    private static final String databaseName= "trivia_maze_main.db";

    private JPanel roomView, textView, textViewL;
    private JLabel flavorText, flavorTextL;
    private JButton upButton, downButton, leftButton, rightButton, player;
    private ImageIcon lock, wall, wrong, blank;
    private ImageIcon[] goDir, playerSprites;
    private JMenuItem exit, about, instructions, questionLog, cheats;
    private JMenuItem newEasy, newMedi, newHard, save1, save2, save3, load1, load2, load3;

    private boolean questionUp = false;
    private int questionDir = -1;

    private boolean gameOver = true;
    private boolean vertMove = false;
    private boolean cheatsOn = false;

    MazeFrame() {

        myQuestionManager = new QuestionDatabaseManager(databaseName);
        myQuestionLog = new QuestionLog();
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
        upButton.setIcon(blank);

        downButton = new JButton();
        downButton.setBounds(225,225,50,50);
        downButton.addActionListener(this);
        downButton.setIcon(blank);

        leftButton = new JButton();
        leftButton.setBounds(25,125,50,50);
        leftButton.addActionListener(this);
        leftButton.setIcon(blank);

        rightButton = new JButton();
        rightButton.setBounds(425,125,50,50);
        rightButton.addActionListener(this);
        rightButton.setIcon(blank);

        player = new JButton();
        player.setBounds(200, 100, 100, 100);
        player.addActionListener(this);
        player.setIcon(playerSprites[0]);
    }

    private void initializeMenu() {
        JMenuBar menu = new JMenuBar();
        setJMenuBar(menu);

        JMenu file = new JMenu("File");
        JMenu help = new JMenu("Help");
        JMenu newGame = new JMenu("New Game");
        JMenu saveGame = new JMenu("Save Game");
        JMenu loadGame = new JMenu("Load Game");

        menu.add(file);
        menu.add(help);

        exit     		= new JMenuItem("Exit");
        about    		= new JMenuItem("About");
        instructions    = new JMenuItem("Game Instructions");
        questionLog     = new JMenuItem("Question Log");
        cheats          = new JMenuItem("Cheats");

        newEasy = new JMenuItem("Easy (3 x 4)");
        newMedi = new JMenuItem("Medium (5 x 6)");
        newHard = new JMenuItem("Hard (7 x 8)");
        newGame.add(newEasy);
        newGame.add(newMedi);
        newGame.add(newHard);

        save1 = new JMenuItem("File 1");
        save2 = new JMenuItem("File 2");
        save3 = new JMenuItem("File 3");
        saveGame.add(save1);
        saveGame.add(save2);
        saveGame.add(save3);

        load1 = new JMenuItem("File 1");
        load2 = new JMenuItem("File 2");
        load3 = new JMenuItem("File 3");
        loadGame.add(load1);
        loadGame.add(load2);
        loadGame.add(load3);

        file.add(newGame);
        file.add(saveGame);
        file.add(loadGame);
        file.addSeparator();
        file.add(exit);
        help.add(about);
        help.add(instructions);
        help.add(questionLog);
        help.add(cheats);

        newEasy.addActionListener(this);
        newMedi.addActionListener(this);
        newHard.addActionListener(this);

        save1.addActionListener(this);
        save2.addActionListener(this);
        save3.addActionListener(this);
        load1.addActionListener(this);
        load2.addActionListener(this);
        load3.addActionListener(this);

        saveGame.addActionListener(this);
        loadGame.addActionListener(this);
        exit.addActionListener(this);
        about.addActionListener(this);
        instructions.addActionListener(this);
        questionLog.addActionListener(this);
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

    public void answerQuestion(boolean isCorrect) {
        questionUp = false;
        myMaze.setDoor(myMaze.getPlayerH(), myMaze.getPlayerW(), questionDir, isCorrect);
        updateIcons();
        questionDir = -1;

        if (isCorrect) {
            setText("You got it right!", "The door quietly slides open.");
        } else {
            setText("You got it wrong...", "The touchscreen mounted on the door goes dark.");
            if (!myMaze.solvable()) {
                eventDefeat();
            }
        }
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!questionUp) {
            if (e.getSource() == upButton && !gameOver) {
                eventDoorClick(0);
            }
            else if (e.getSource() == downButton && !gameOver) {
                eventDoorClick(1);
            }
            else if (e.getSource() == leftButton && !gameOver) {
                eventDoorClick(2);
            }
            else if (e.getSource() == rightButton && !gameOver) {
                eventDoorClick(3);
            }

            else if (e.getSource() == player && !gameOver) {
                player.setIcon(playerSprites[1]);
                cycleText();
            }

            //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

            else if (e.getSource() == newEasy) {
                eventBegin(3, 4);
            }
            else if (e.getSource() == newMedi) {
                eventBegin(5, 6);
            }
            else if (e.getSource() == newHard) {
                eventBegin(7, 8);
            }
            else if (e.getSource() == save1 && !gameOver) {
                eventSave(1);
            }
            else if (e.getSource() == save2 && !gameOver) {
                eventSave(2);
            }
            else if (e.getSource() == save3 && !gameOver) {
                eventSave(3);
            }
            else if (e.getSource() == load1) {
                eventLoad(1);
            }
            else if (e.getSource() == load2) {
                eventLoad(2);
            }
            else if (e.getSource() == load3) {
                eventLoad(3);
            }
            else if (e.getSource() == exit) {
                eventExit();
            }

            //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

            else if (e.getSource() == about) {
                eventAbout();
            }
            else if (e.getSource() == instructions) {
                eventInstructions();
            }
            else if (e.getSource() == questionLog) {
                eventLog();
            }
            else if (e.getSource() == cheats && !gameOver) {
                eventCheat();
            }
        }
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private void updateIcons() {
        if (cheatsOn) {
            player.setIcon(playerSprites[5]);
        } else {
            player.setIcon(playerSprites[0]);
        }

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

    private void cycleText() {
        //This array contains all the game's flavor text. I'll keep filling this out.
        String[] allText = {
                "There is a pensive feeling in the air...", "",
                "There is an egotistical feeling in the air...", "",
                "There is a quizzacious feeling in the air...", "",
                "There is a conspiratorial feeling in the air...", "",
                "You feel like you are likely to be eaten by a grue.", "You're not, but it feels that way",
                "You feel like you're being watched.", "Your captors surely have hidden cameras to supervise your progress.",
                "You feel hungry.", "If you knew you were going to be captured, you'd have eaten a bigger lunch.",
                "You feel like it would be wise to identify the edges of the maze.", "",
                "You feel the cool labyrinth air against your bare head.", "\"Hey, where's my hat??\"",
                "You plan to lodge a strongly-worded complaint with your captors.", "After you escape, of course.",
                "You recall a legend you once heard...", "They say some people can unlock superpowers when stressed.",
                "The hear the florescent lights in the ceiling buzz quietly.", "",
                "You notice your headache's going away.", "That's good, at least.",
                "You look around for any vents big enough to crawl through.", "...Nope.",
                "You stare at your hands... it's not often you really look at your hands.", "How long has that mole been there?",
                "You thought, for a second, that you saw a strange glowing orb.", "Probably just your imagination."};

        //returns a random number from 0 to 29
        int i = (int) (Math.random() * 30);

        //if i is 25 - 29, you will learn some of your current coordinates.
        //if i is 20 - 24, you will learn some of the exit's coordinates.
        //if i is 18 - 19, you will learn how far you are from the exit.
        //if i is 17, you will learn how far you are from the exit vertically.
        //if i is 16, you will learn how far you are from the exit vertically.
        //if i is anywhere from 0 to 15, it will display random flavor text.
        if (i >= 25) {
            if (vertMove) {
                setText("You feel like you're in row " + (myMaze.getPlayerH() + 1) + "...", "");
            } else {
                setText("You feel like you're in column " + (myMaze.getPlayerW() + 1) + "...", "");
            }
        }
        else if (i >= 20) {
            if (vertMove) {
                setText("Somehow, you know that the exit is in row " + (myMaze.getGoalH() + 1) + "...", "");
            } else {
                setText("Somehow, you know that the exit is in column " + (myMaze.getGoalW() + 1) + "...", "");
            }
        }
        else if (i >= 18) {
            int distH = Math.abs(myMaze.getPlayerH() - myMaze.getGoalH());
            int distW = Math.abs(myMaze.getPlayerW() - myMaze.getGoalW());
            setText("Somehow, you know that you're " + (distH + distW) + " moves from the exit...", "");
        }
        else if (i == 17) {
            int distH = myMaze.getPlayerH() - myMaze.getGoalH();
            if (distH == 0) {
                setText("Somehow, you know that you're in the same row as the goal.", "");
            } else {
                setText("Somehow, you know that you're " + Math.abs(distH) + " rows away from the goal.", "");
            }
        }
        else if (i == 16) {
            int distW = myMaze.getPlayerW() - myMaze.getGoalW();
            if (distW == 0) {
                setText("Somehow, you know that you're in the same column as the goal.", "");
            } else {
                setText("Somehow, you know that you're " + Math.abs(distW) + " columns away from the goal.", "");
            }
        }
        else {
            setText(allText[i*2], allText[(i*2) + 1]);
        }
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private synchronized void eventDoorClick(int Dir) {
        vertMove = (Dir == 0 || Dir == 1);

        int myH = myMaze.getPlayerH();
        int myW = myMaze.getPlayerW();
        int state = myMaze.checkDoor(myH, myW, Dir);

        if (state == 0) {
            if (cheatsOn) {
                setText("Focusing your power into a laser, you cut through the door in seconds!", "How wondrous...");
                myMaze.setDoor(myH, myW, Dir, true);
                updateIcons();
            } else {
                questionUp = true;
                questionDir = Dir;
                //The logic for calling and answering a question goes here.
                //Right now, it's set up as if you always get the answer wrong.
                myQuestionManager.poseQuestion();
                Question question = myQuestionManager.getLastQuestion(); // imported Question object; other solution?
                String[] questionData = { question.getQuestion(), question.getAnswer() };                answerQuestion(question.getAnsweredResult());
                myQuestionLog.addData(questionData);
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
            updateIcons();
            if(myMaze.isSolved()) {
                eventVictory();
            }
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
    }

    private void eventVictory() {
        gameOver = true;
        player.setIcon(playerSprites[3]);
        if (cheatsOn) {
            setText("Thanks to your Wondrous new Power, you've escaped!",
                    "You decide to devote yourself to using your Power to help your people.");
        } else {
            setText("You've escaped! Now you can return to your people on the mothership!",
                    "But the burden of useless trivia may never leave you...");
        }
    }

    private void eventDefeat() {
        gameOver = true;
        player.setIcon(playerSprites[2]);
        setText("You're trapped in the maze... there's nowhere to go from here.",
                "Your captors will continue to test you until you can answer correctly.");
    }

    private void eventBegin(int myH, int myW) {
        gameOver = false;
        cheatsOn = false;

        myMaze = new Maze(myH, myW);
        updateIcons();
        player.setIcon(playerSprites[4]);
        setText("You wake up in a maze with a screaming headache...",
                "You must answer questions about your captors' culture and history to escape.");
    }

    private void eventSave(int mySave) {
        if (myMaze == null) {
            setText("Cannot save game, no current game found.", "Select new game from the menu to start a new game.");
        } else {
            try {
                FileOutputStream fileOut = new FileOutputStream("saves/" + FILENAMES[mySave] + ".ser");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(myMaze);
                fileOut.close();

                setText("Game successfully saved to " + FILENAMES[mySave] + ".", "");
            } catch(IOException e) {
                setText("Cannot save game, an error has occurred.", "");
                e.printStackTrace();
            }
        }
    }

    private void eventLoad(int mySave)  {
        try {
            myMaze = null;
            FileInputStream fileIn = new FileInputStream("saves/" + FILENAMES[mySave] + ".ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            myMaze = (Maze) in.readObject();
            in.close();
            fileIn.close();

            setText("Game successfully loaded from " + FILENAMES[mySave] + ".", "");
            cheatsOn = false;
            updateIcons();
            player.setIcon(playerSprites[0]);
            gameOver = false;
        } catch(IOException | ClassNotFoundException e) {
            setText("Cannot load game, an error has occurred.", "");
            e.printStackTrace();
        }
    }

    private void eventExit() {
        setText("Closing...", "");
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    private void eventAbout() {
        String ab1 = "Trivia Maze Project for TCSS 360 with Tom Capaul";
        String ab2 = "Joshua Lee, Logan Martinson, Samin Bahizad 2021";
        setText(ab1, ab2);
    }

    private void eventInstructions() {
        String in1 = "Move between rooms to find the exit. Click your character to change flavor text.";
        String in2 = "Click a lock to be asked a question. Answer correctly to open the door.";
        setText(in1, in2);
    }

    private void eventLog() {
        setText("This feature has not been implemented yet.","");
    }

    private void eventCheat() {
        if (cheatsOn) {
            cheatsOn = false;
            setText("As suddenly as it appeared, your power seems to fade...", "Cheat mode deactivated.");
            player.setIcon(playerSprites[0]);
        } else {
            cheatsOn = true;
            setText("Just when all hope seemed lost, you felt an incredible power!", "Cheat mode activated.");
            player.setIcon(playerSprites[5]);
        }
    }
}
