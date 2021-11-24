package QuestionDatabase;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.*;
import java.awt.*;

public class QuestionLog {
	private DefaultTableModel qTableModel;
	private static JTable logTable;
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
		String[] columnNames= {"Questions", "Your Answers"};
		this.qTableModel= new DefaultTableModel(columnNames,0);
		logTable= new JTable(qTableModel);
		questionLogFrame.setTitle("Question Log");
		questionLogFrame.getContentPane().add(questionLogPanel);
		questionLogFrame.setSize(500,500);
		questionLogFrame.setVisible(true);
		logTable.setBounds(30, 40, 200, 300);
		questionLogPanel.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "Question Log", TitledBorder.CENTER, TitledBorder.TOP));
		questionLogFrame.getContentPane().add(new JScrollPane(logTable));
	}
    public void addData(Object[] data)
    {
//		qTableModel = (DefaultTableModel)logTable.getModel();
    	logTable.setAutoCreateColumnsFromModel(true);
        this.qTableModel.addRow(data);
        System.out.println("data is now added to the qTable");
        System.out.println(qTableModel.getColumnCount());
        logTable.setModel(this.qTableModel);
        
    }   
    public static void main(String[] arg) throws Exception {
    	new QuestionLog();
    }

	
}