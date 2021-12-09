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
	private DefaultTableModel 	qTableModel;
	private JTable 				logTable;
	private JFrame 				questionLogFrame;
	private JPanel 				questionLogPanel;
	private JScrollPane 		scrollPane;
	
	
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
		
		questionLogFrame.setTitle("Question Log");
		questionLogFrame.setContentPane(questionLogPanel);
		questionLogFrame.setSize(500,500);
		questionLogFrame.setVisible(false);

		String[] columnNames= {"Questions", "Your Answers"};
		this.qTableModel= new DefaultTableModel(columnNames,0);
		logTable= new JTable(this.qTableModel);
		logTable.setModel(qTableModel);
		logTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		logTable.getColumnModel().getColumn(0).setPreferredWidth(300);
		logTable.getColumnModel().getColumn(1).setPreferredWidth(200);
		logTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		scrollPane = new JScrollPane(logTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		questionLogPanel.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "Question Log", TitledBorder.CENTER, TitledBorder.TOP));
		questionLogPanel.add(scrollPane);
		
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