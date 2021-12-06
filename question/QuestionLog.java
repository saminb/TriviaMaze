package question;
import javax.swing.*;

import javax.swing.border.TitledBorder;
import javax.swing.table.*;


/**
 *@author samin
 * This class create the functionality of  question log. 
 * It creates a frame with table with the information about questions answered.
 */
public class QuestionLog {
	private DefaultTableModel qTableModel;
	private JTable logTable;
	private JFrame questionLogFrame;
	private JPanel questionLogPanel;
	
	
	/**
	 * Calls initializePanel() to create the questionlog panel
	 */
	public QuestionLog() {
		initializePanel();
	}
	
	/**
	 * Create the questionlog frame and panel
	 */
	private void initializePanel() {
		
		questionLogFrame= new JFrame();
		questionLogPanel= new JPanel();
		
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
		logTable.setModel(qTableModel);
		}
	
	/**
	 * Adds more data to the questionlog table when it is called
	 */
    public void addData(Object[] data)
    {
        logTable.setAutoCreateColumnsFromModel(true);
    	this.qTableModel.addRow(data);
        logTable.setModel(this.qTableModel);
        System.out.println("data is now added to the qTable");
        System.out.println(this.qTableModel.getDataVector());
    }   
    
    public void setVisible() {
    	questionLogFrame.setVisible(true);
    }

	
}