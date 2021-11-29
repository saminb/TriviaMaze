package QuestionDatabase;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class MCQuestionGUI extends JFrame {
	private JFrame myFrame;
	private JPanel myBox;
	private QuestionLog questionLog;
	
	public MCQuestionGUI(MultipleChoice theQuestion,  QuestionLog questionLog) {
		initComponents(theQuestion, questionLog);
	}
	
	private void initComponents(MultipleChoice theQuestion, QuestionLog questionLog) {
		
		//this.questionLog = questionLog;
		myFrame = new JFrame("Multiple Choice Question");
		myFrame.setMinimumSize(new Dimension(500, 300));
		myFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		myFrame.setLayout(new BorderLayout());
		//myFrame.pack();
		
		
		JPanel panel = new JPanel(new BorderLayout());
		JLabel title = new JLabel(theQuestion.getQuestion());
		title.setFont(new Font("Helvetica", Font.BOLD, 20));
		JLabel instr = new JLabel("You have 60 seconds to answer!");
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		myBox = new JPanel(new BorderLayout());
		Border padding = BorderFactory.createEmptyBorder(20, 20, 5, 20);
		myBox.setBorder(padding);
		
		initializeButtons(theQuestion, panel);
		
		myBox.add(panel, BorderLayout.PAGE_START);
		
		myFrame.add(title, BorderLayout.PAGE_START);
		myFrame.add(myBox, BorderLayout.CENTER);
		myFrame.add(instr, BorderLayout.PAGE_END);
		title.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		instr.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		myFrame.pack();
		myFrame.setVisible(true);
		
	}
	
	public void initializeButtons(MultipleChoice theQuestion, JPanel thePanel) {
		ButtonGroup group = new ButtonGroup();
		String[] answerChoices = theQuestion.getChoices();
		for (int i = 0; i < answerChoices.length; i++) {
			JRadioButton answer = new JRadioButton(answerChoices[i]);
			group.add(answer);
			thePanel.add(answer);
			answer.setActionCommand(answerChoices[i]);
		}
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Make it so submit can only be pressed if a radio option is selected/handle error for no selection
				String answer = group.getSelection().getActionCommand();
				Object[] data= {theQuestion.getQuestion(),answer};
				questionLog.addData(data);
				boolean isCorrect = theQuestion.processAnswer(answer);
				showAnswerGUI(isCorrect, theQuestion);
				
			}

		});
		myBox.add(submitButton, BorderLayout.PAGE_END);
	}
	
	private void showAnswerGUI(boolean isCorrect, Question theQuestion) { // Abstract away?
		myBox.setVisible(false);
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
