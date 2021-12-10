package question;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * The QuestionGUI class constructs and displays the GUI for TriviaMaze to prompt
 * the user to answer the question. QuestionGUI appears when the user clicks a door
 * to start the process, and changes the display depending on what type of question
 * is called. Question GUI will also update the Question object it is passed with the
 * input from the user.
 * 
 * @author Joshua Lee, Samin Bahizad, Logan Martinson
 * @version
 *
 */
public class QuestionGUI {
	
	/**
	 * The frame for the Question GUI.
	 */
	private JDialog myFrame;
	
	/**
	 * The panel for the GUI.
	 */
	private JPanel myBox;
	
	/**
	 * Status of whether the question processing as concluded.
	 */
	private boolean isFinished = false;
	
	/**
	 * Constructs and displays the question and choices from the given question object
	 * into the Question GUI.
	 * @param theQuestion the Question Object
	 */
	public QuestionGUI(Question theQuestion) {
		initComponents(theQuestion);
	}
	
	/**
	 * Initializes the swing components for the GUI.
	 * @param theQuestion the Question Object
	 */
	private void initComponents(Question theQuestion) {
		
		myFrame = new JDialog(null, Dialog.ModalityType.DOCUMENT_MODAL);
		myFrame.setMinimumSize(new Dimension(500, 350));
		myFrame.setLayout(new BorderLayout());	
		
		myFrame.addWindowListener(new WindowAdapter() {
			@Override
            public void windowClosing(WindowEvent e) {
                if (!isFinished) {
                	theQuestion.processAnswer("");
                	isFinished = true;
                }
                myFrame.dispose();
            
            }
        });
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		JTextArea title = createTitle(theQuestion.getQuestion());
		
		JLabel instr = new JLabel("You have 60 seconds to answer!");
		instr.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		myBox = new JPanel(new BorderLayout());
		myBox.setBorder(BorderFactory.createEmptyBorder(20, 20, 5, 20));
		myBox.add(panel, BorderLayout.PAGE_START);
		
		initializeButtons(theQuestion, panel);
			
		myFrame.add(title, BorderLayout.PAGE_START);
		myFrame.add(myBox, BorderLayout.CENTER);
		myFrame.add(instr, BorderLayout.PAGE_END);
		myFrame.pack();
		myFrame.setVisible(true);
		
	}
	
	/**
	 * Creates and returns a title for the frame with the question. 
	 * @param theQuestion the question asked.
	 * @return The GUI Title
	 */
	public JTextArea createTitle(String theQuestion) {
		JTextArea newTitle = new JTextArea(theQuestion);
		newTitle.setEditable(false);
		newTitle.setWrapStyleWord(true);
		newTitle.setFont(new Font("Helvetica", Font.BOLD, 20));
		newTitle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		newTitle.setOpaque(false);
		return newTitle;
	}
	
	/**
	 * Initializes the buttons to be pressed for the GUI, including the 
	 * answer choices and the submit button. For short answer questions, 
	 * answer choices are not populated; instead, an input text field is
	 * created.
	 * @param theQuestion the Question Object
	 * @param thePanel the panel to hold the buttons.
	 */
	public void initializeButtons(Question theQuestion, JPanel thePanel) {
		ButtonGroup group = new ButtonGroup();
		JTextField input = new JTextField();
		String type = theQuestion.getType();
		JButton submitButton = new JButton("Submit");
		submitButton.setEnabled(false);
		if (type.equals("SA")) {
			thePanel.add(input);
		} else {
			String[] answerChoices;
			if (type.equals("T/F")) {
				answerChoices = ((TrueFalse) theQuestion).getChoices();
			} else {
				answerChoices = ((MultipleChoice) theQuestion).getChoices();
			}
			
			for (int i = 0; i < answerChoices.length; i++) {
				JRadioButton answer = new JRadioButton(answerChoices[i]);
				group.add(answer);
				thePanel.add(answer);
				answer.setActionCommand(answerChoices[i]);
				answer.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						submitButton.setEnabled(true);
					}
				});
			}
		}
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Make it so submit can only be pressed if a radio option is selected/handle error for no selection
				String answer;
				if (type.equals("SA")) {
					answer = input.getText();
				} else if (type.equals("MC")){
					answer = group.getSelection().getActionCommand();
				} else {
					answer = "" + group.getSelection().getActionCommand().charAt(0);
				}
				System.out.println("Your answer: " + answer);
				boolean isCorrect = theQuestion.processAnswer(answer);
				showAnswerGUI(isCorrect, theQuestion);
				restartFrameForAnswer();	
			}
		});
		myBox.add(submitButton, BorderLayout.PAGE_END);
	}
	
	/**
	 * Displays the GUI for the correct answer/post answer section after the user
	 * has submitted their answer. The answer GUI will change contents depending on
	 * whether the user has the correct or incorrect answer.
	 * @param isCorrect Whether the user has answered correctly or not.
	 * @param theQuestion The question object.
	 */
	private void showAnswerGUI(boolean isCorrect, Question theQuestion) { // Abstract away?
		myFrame.getContentPane().removeAll();
		myBox = new JPanel();
		myBox.setLayout(new BorderLayout());
		JTextArea text = new JTextArea();
		text.setOpaque(false);
		if (isCorrect) {
			text.setText("Congratulations! You chose the correct answer.");
		} else {
			text.setText("Oops. You chose the wrong answer. The correct answer is: " + theQuestion.getAnswer());
		}
		JButton continueButton = new JButton("Continue");
		continueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myFrame.dispose();
			}
		});
		myBox.add(text, BorderLayout.CENTER);
		myBox.add(continueButton, BorderLayout.PAGE_END);
		myBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		restartFrameForAnswer();
		isFinished = true;
	}
	
	/**
	 * Restarts the current frame to change from Question display to answer display.
	 */
	private void restartFrameForAnswer() {
		myFrame.add(myBox);
		myFrame.setMinimumSize(new Dimension(100, 150));
		myFrame.setMaximumSize(new Dimension(300, 300));
		myFrame.repaint();
		myFrame.pack();
		myFrame.setVisible(true);
	}
	
	/**
	 * Returns the status of the question to see if the question process has finished.
	 * @return the status
	 */
	public boolean getFinished() {
		return this.isFinished;
	}
	
}
