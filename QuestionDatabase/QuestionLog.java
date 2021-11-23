package QuestionDatabase;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class QuestionLog extends JPanel {
	private String[] columnNames;
	private DefaultTableModel tableModel;

	/**
	 * Create the panel.
	 */
	
	public QuestionLog() {
		this.initializePanel();
	}
	
	private void initializePanel() {
		
		setLayout(null);
		JLabel questionLogLabel = new JLabel("Question Log");
		questionLogLabel.setBounds(195, 5, 59, 13);
		add(questionLogLabel);

		
		this.tableModel = new DefaultTableModel();
		JTable table= new JTable(tableModel);
		columnNames = new String[] {"Questions", "Your Answer"};
	    tableModel.addColumn(columnNames);
		table.setBounds(396, 47, -345, 196);
		
		JScrollPane scrollPane = new JScrollPane(table);
		this.setPreferredSize(new Dimension(500, 500));
        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);
	}
	public void setLog(Object[] log) {
		this.tableModel.insertRow(tableModel.getRowCount(), log);
	}

	
}
