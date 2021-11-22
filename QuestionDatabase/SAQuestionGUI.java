package QuestionDatabase;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.util.*;

public class SAQuestionGUI extends JFrame {
	private JFrame myFrame;
	private JPanel myPanel;
	
	public SAQuestionGUI(ShortAnswer theQuestion) {
		initComponents(theQuestion);
	}
	
	private void initComponents(ShortAnswer theQuestion) {
		myFrame.setSize(500, 200);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Question Dialog");
		
		myPanel = new JPanel();
		
		setContentPane(myPanel);
		myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.PAGE_AXIS));
		
		JLabel question = new JLabel();
		question.setText(theQuestion.getQuestion());
		EmptyBorder whitespace = new EmptyBorder(5, 5, 20, 10);
		question.setBorder(whitespace);
		add(question);
		setVisible(true);
		initializeInput(theQuestion);
		
	}
	
	public void initializeInput(ShortAnswer theQuestion) { // Abstract away?
		JTextField input = new JTextField();
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Make it so submit can only be pressed if a radio option is selected/handle error for no selection
				String myAnswer = input.getText();
				boolean isCorrect = theQuestion.processAnswer(myAnswer); // processAnswer will set the Question Object's answeredCorrectly field to true or false, which is how we will send it back to mazeframe
				showAnswerGUI(isCorrect, theQuestion);
			}
		});
		myPanel.add(submitButton);
	}
	
	private void showAnswerGUI(boolean isCorrect, Question theQuestion) { // Abstract away?
		myPanel.removeAll();
		JLabel text = new JLabel();
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
	}
	
}
