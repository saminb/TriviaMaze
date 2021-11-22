package QuestionDatabase;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.util.*;

public class MCQuestionGUI extends JFrame {
	private JPanel panel;
	
	public MCQuestionGUI(MultipleChoice theQuestion) {
		initComponents(theQuestion);
	}
	
	private void initComponents(MultipleChoice theQuestion) {
		setSize(500, 200);
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
	
	public void initializeButtons(MultipleChoice theQuestion) {
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
				boolean isCorrect = theQuestion.processAnswer(answer);
			}
		});
		panel.add(submitButton);
	}
	
}
