package question;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.*;
import java.awt.*;

public class QuestionLog {
	private DefaultTableModel qTableModel;
	private JTable logTable;
	private JFrame questionLogFrame;
	private JPanel questionLogPanel;
	/**
	 * Create the panel.
	 */
	
	public QuestionLog() {
		initializePanel();
		
	}
	private void initializePanel() {
		
		questionLogFrame= new JFrame();
		questionLogPanel= new JPanel();
		
		// Testing Question Log
		String[] theChoices = new String[4];
		theChoices[0] = "huh";
		theChoices[1] = "what";
		theChoices[2] = "yup";
		theChoices[3] = "Yes";
		MultipleChoice testQuestion = new MultipleChoice("123", "MC", "HOW?", "Yes", theChoices);
		String[] testData = {testQuestion.getQuestion(), testQuestion.getAnswer()};
		//Test Data created
		
		String[] columnNames= {"Questions", "Your Answers"};
		this.qTableModel= new DefaultTableModel(columnNames,0);
		logTable= new JTable(this.qTableModel);
		questionLogFrame.setTitle("Question Log");
		questionLogFrame.setContentPane(questionLogPanel);
		questionLogFrame.setSize(500,500);
		questionLogFrame.setVisible(false);
		logTable.setBounds(10, 10, 10, 10);
		questionLogPanel.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "Question Log", TitledBorder.CENTER, TitledBorder.TOP));
		questionLogPanel.add(logTable);
		String[] test = {"I hate this?","True"};
		qTableModel.addRow(test);
		qTableModel.addRow(testData);
		logTable.setModel(qTableModel);
		}
    public void addData(Object[] data)
    {
//		qTableModel = (DefaultTableModel)logTable.getModel();
        logTable.setAutoCreateColumnsFromModel(true);
    	this.qTableModel.addRow(data);
        logTable.setModel(this.qTableModel);
        System.out.println("data is now added to the qTable");
        System.out.println(this.qTableModel.getDataVector());
    }   
   /* public static void main(String[] arg) throws Exception {
    	new QuestionLog();
    }*/

	
}