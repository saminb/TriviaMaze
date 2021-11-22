package QuestionDatabase;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.util.*;

public class TFQuestionGUI extends JFrame {
	private JFrame frame;
	private JPanel panel;
	
	public TFQuestionGUI(TrueFalse theQuestion) {
		initComponents(theQuestion);
	}
	
	private void initComponents(TrueFalse theQuestion) {
		frame.setSize(500, 200);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Question Dialog");
		
		panel = new JPanel();
		
		setContentPane(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		JLabel question = new JLabel();
		question.setText(theQuestion.getQuestion());
		EmptyBorder whitespace = new EmptyBorder(5, 5, 20, 10);
		question.setBorder(whitespace);
		add(question);
		setVisible(true);
		initializeButtons(theQuestion);
		
	}
	
	public void initializeButtons(TrueFalse theQuestion) {
		ButtonGroup group = new ButtonGroup();
		String[] answerChoices = theQuestion.getChoices();
		for (int i = 0; i < answerChoices.length; i++) {
			JRadioButton answer = new JRadioButton(answerChoices[i]);
			group.add(answer);
			panel.add(answer);
			answer.setActionCommand(answerChoices[i]);
		}
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Make it so submit can only be pressed if a radio option is selected/handle error for no selection
				String answer = group.getSelection().getActionCommand();
				boolean isCorrect = theQuestion.processAnswer(answer); // processAnswer will set the Question Object's answeredCorrectly field to true or false, which is how we will send it back to mazeframe
				showAnswerGUI(isCorrect, theQuestion);
			}
		});
		panel.add(submitButton);
	}
	
	private void showAnswerGUI(boolean isCorrect, Question theQuestion) { // Abstract away?
		panel.removeAll();
		JLabel text = new JLabel();
		if (isCorrect) {
			text.setText("Congratulations! You chose the correct answer.");
		} else {
			text.setText("Oops. You chose the wrong answer. The correct answer is: " + theQuestion.getAnswer());
		}
		JButton continueButton = new JButton("Continue");
		continueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
	}
}
