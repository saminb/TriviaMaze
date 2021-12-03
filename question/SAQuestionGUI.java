package question;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SAQuestionGUI extends JFrame {
	private JFrame myFrame;
	private JPanel myPanel;
	
	public SAQuestionGUI(ShortAnswer theQuestion) {
		initComponents(theQuestion);
	}
	
	private void initComponents(ShortAnswer theQuestion) {
		myFrame = new JFrame("Short Answer Question");
		myFrame.setSize(600, 300);
		myFrame.setResizable(false);
		myFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		myPanel = new JPanel();
		myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.PAGE_AXIS));
		
		JLabel question = new JLabel();
		question.setText(theQuestion.getQuestion());
		EmptyBorder whitespace = new EmptyBorder(5, 5, 20, 10);
		question.setBorder(whitespace);
		myPanel.add(question);
		initializeInput(theQuestion);
		
		myFrame.add(myPanel);
		myFrame.setVisible(true);
		
	}
	
	public void initializeInput(ShortAnswer theQuestion) { // Abstract away?
		JTextField input = new JTextField();
		myPanel.add(input);
		JButton submitButton = new JButton("Submit");
		myPanel.add(Box.createVerticalGlue());
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
