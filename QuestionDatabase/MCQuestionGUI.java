package QuestionDatabase;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.util.*;

public class MCQuestionGUI extends JFrame {
	private JFrame frame;
	private JPanel posePanel;
	
	public MCQuestionGUI(MultipleChoice theQuestion) {
		initComponents(theQuestion);
	}
	
	private void initComponents(MultipleChoice theQuestion) {
		frame = new JFrame("Multiple Choice Question");
		frame.setSize(600, 300);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		posePanel = new JPanel();
		posePanel.setLayout(new BoxLayout(posePanel, BoxLayout.PAGE_AXIS));
		
		JLabel question = new JLabel();
		question.setText(theQuestion.getQuestion());
		EmptyBorder whitespace = new EmptyBorder(5, 5, 20, 10);
		question.setBorder(whitespace);
		posePanel.add(question);
		initializeButtons(theQuestion);
		
		frame.add(posePanel);
		frame.setVisible(true);
	}
	
	public void initializeButtons(MultipleChoice theQuestion) {
		ButtonGroup group = new ButtonGroup();
		String[] answerChoices = theQuestion.getChoices();
		for (int i = 0; i < answerChoices.length; i++) {
			JRadioButton answer = new JRadioButton(answerChoices[i]);
			group.add(answer);
			posePanel.add(answer);
			answer.setActionCommand(answerChoices[i]);
		}
		JButton submitButton = new JButton("Submit");
		posePanel.add(Box.createVerticalGlue());
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Make it so submit can only be pressed if a radio option is selected/handle error for no selection
				String answer = group.getSelection().getActionCommand();
				boolean isCorrect = theQuestion.processAnswer(answer);
				showAnswerGUI(isCorrect, theQuestion);
			}
		});
		posePanel.add(submitButton);
	}
	
	private void showAnswerGUI(boolean isCorrect, Question theQuestion) { // Abstract away?
		posePanel.setVisible(false);
		JPanel postPanel = new JPanel();
		postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.PAGE_AXIS));
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
		
		postPanel.add(text);
		postPanel.add(continueButton);
		
		frame.add(postPanel);
		frame.setVisible(true);
	}
	
}
