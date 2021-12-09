package question;

import javax.swing.*;

import javax.swing.border.TitledBorder;
import javax.swing.table.*;

/**
 * QuestionLog displays a history of the questions that have been asked so far in the TriviaMaze.
 * @author Joshua Lee, Samin Bahizad, Logan Martison
 * @version
 * 
 */
public class QuestionLog {
	private DefaultTableModel 	myQTableModel;
	private JTable 				myLogTable;
	private JFrame 				myQuestionLogFrame;
	private JPanel 				myQuestionLogPanel;
	private JScrollPane 		myScrollPane;
	
	/**
	 * Calls initializePanel() to initialize the contents of the myQuestionLogPanel.
	 */
	public QuestionLog() {
		initializePanel();
	}
	
	/**
	 * Creates and customizes myQuestionLogFrame and myQuestionLogPanel.
	 */
	private void initializePanel() {
		
		myQuestionLogFrame = new JFrame();
		myQuestionLogPanel = new JPanel();
		
		myQuestionLogFrame.setTitle("Question Log");
		myQuestionLogFrame.setContentPane(myQuestionLogPanel);
		myQuestionLogFrame.setSize(500,500);
		myQuestionLogFrame.setVisible(false);

		String[] columnNames = {"Questions", "Your Answers"};
		this.myQTableModel = new DefaultTableModel(columnNames,0);
		myLogTable = new JTable(this.myQTableModel);
		myLogTable.setModel(myQTableModel);
		myLogTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		myLogTable.getColumnModel().getColumn(0).setPreferredWidth(300);
		myLogTable.getColumnModel().getColumn(1).setPreferredWidth(200);
		myLogTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		myScrollPane = new JScrollPane(myLogTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		myQuestionLogPanel.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "Question Log", TitledBorder.CENTER, TitledBorder.TOP));
		myQuestionLogPanel.add(myScrollPane);
		
		}
	
	/**
	 * Takes data and adds it visually to the QuestionLog.
	 * @param theData to be added.
	 */
    public void addData(Object[] theData)
    {
        myLogTable.setAutoCreateColumnsFromModel(true);
    	this.myQTableModel.addRow(theData);
        myLogTable.setModel(this.myQTableModel);
        System.out.println("data is now added to the qTable");
        System.out.println(this.myQTableModel.getDataVector());
    }   
 
    /**
     * Sets myQuestionLogFrame to be visible to display the question log.
     */
    public void setVisible() {
    	myQuestionLogFrame.setVisible(true);
    }

	
}