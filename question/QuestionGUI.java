package question;

import java.awt.event.*;
import java.util.Observable;

import javax.swing.*;
import java.awt.*;

public class QuestionGUI extends Observable {
	private JFrame myFrame;
	private JPanel myBox;
	
	public QuestionGUI(Question theQuestion) {
		initComponents(theQuestion);
	}
	
	private void initComponents(Question theQuestion) {
		
		myFrame = new JFrame("Question");
		myFrame.setMinimumSize(new Dimension(500, 350));
		myFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		myFrame.setLayout(new BorderLayout());
		
		
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
	
	public JTextArea createTitle(String theQuestion) {
		JTextArea newTitle = new JTextArea(theQuestion);
		newTitle.setEditable(false);
		newTitle.setLineWrap(true);
		newTitle.setFont(new Font("Helvetica", Font.BOLD, 20));
		newTitle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		newTitle.setOpaque(false);
		return newTitle;
	}
	
	public void initializeButtons(Question theQuestion, JPanel thePanel) {
		ButtonGroup group = new ButtonGroup();
		JTextField input = new JTextField();
		String type = theQuestion.getType();
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
			}
		}
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
			public synchronized void actionPerformed(ActionEvent e) {
				// Make it so submit can only be pressed if a radio option is selected/handle error for no selection
				String answer;
				if (type.equals("SA")) {
					answer = input.getText();
				} else {
					answer = group.getSelection().getActionCommand();
				}
				boolean isCorrect = theQuestion.processAnswer(answer);
				notifyAll();
				showAnswerGUI(isCorrect, theQuestion);
				
			}

		});
		myBox.add(submitButton, BorderLayout.PAGE_END);
	}
	
	private void showAnswerGUI(boolean isCorrect, Question theQuestion) { // Abstract away?
		myFrame.getContentPane().removeAll();
		myBox = new JPanel();
		myBox.setLayout(new BorderLayout());
	;
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
	}
	
	private void restartFrameForAnswer() {
		myFrame.add(myBox);
		myFrame.setMinimumSize(new Dimension(100, 150));
		myFrame.setMaximumSize(new Dimension(300, 300));
		myFrame.repaint();
		myFrame.pack();
		myFrame.setVisible(true);
	}
	
}
