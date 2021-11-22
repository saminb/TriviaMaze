package QuestionDatabase;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MCQuestionGUI extends JFrame {
	private JFrame myFrame;
	private JPanel myPanel;
	
	public MCQuestionGUI(MultipleChoice theQuestion) {
		initComponents(theQuestion);
	}
	
	private void initComponents(MultipleChoice theQuestion) {
		myFrame = new JFrame("Multiple Choice Question");
		myFrame.setSize(600, 300);
		myFrame.setResizable(false);
		myFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		myFrame.setLocationRelativeTo(null);
		
		myPanel = new JPanel();
		myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.PAGE_AXIS));
		
		JLabel question = new JLabel();
		question.setText(theQuestion.getQuestion());
		EmptyBorder whitespace = new EmptyBorder(5, 5, 20, 10);
		question.setBorder(whitespace);
		myPanel.add(question);
		initializeButtons(theQuestion);
		
		myFrame.add(myPanel);
		myFrame.setVisible(true);
	}
	
	public void initializeButtons(MultipleChoice theQuestion) {
		ButtonGroup group = new ButtonGroup();
		String[] answerChoices = theQuestion.getChoices();
		for (int i = 0; i < answerChoices.length; i++) {
			JRadioButton answer = new JRadioButton(answerChoices[i]);
			group.add(answer);
			myPanel.add(answer);
			answer.setActionCommand(answerChoices[i]);
		}
		JButton submitButton = new JButton("Submit");
		myPanel.add(Box.createVerticalGlue());
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Make it so submit can only be pressed if a radio option is selected/handle error for no selection
				String answer = group.getSelection().getActionCommand();
				boolean isCorrect = theQuestion.processAnswer(answer);
				showAnswerGUI(isCorrect, theQuestion);
			}
		});
		myPanel.add(submitButton);
	}
	
	private void showAnswerGUI(boolean isCorrect, Question theQuestion) { // Abstract away?
		myPanel.setVisible(false);
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
				myFrame.dispose();
			}
		});
		
		postPanel.add(text);
		postPanel.add(continueButton);
		
		myFrame.add(postPanel);
		myFrame.setVisible(true);
	}
	
}
