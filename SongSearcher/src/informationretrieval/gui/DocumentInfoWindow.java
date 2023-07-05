package informationretrieval.gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Box;

public class DocumentInfoWindow {

	private JFrame frame;
	private Box verticalBox;
	private JLabel lblDocInfo;

	/**
	 * Launch the application.
	 */
	public static void NewScreen(String[] doc) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DocumentInfoWindow window = new DocumentInfoWindow(doc);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DocumentInfoWindow(String[] doc) {
		//documentID = Integer.parseInt(docID) - 1;
		//System.out.println(documentID);
		initialize(doc);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String[] doc) {
		frame = new JFrame();
		frame.setTitle(doc[1].toUpperCase());
		frame.setBounds(150, 150, 1000, 750);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		frame.getContentPane().setBackground(Color.black);

		frame.getContentPane().setLayout(null);
		
		verticalBox = Box.createVerticalBox();
		verticalBox.setBounds(12, 10, 960, 640);
		frame.getContentPane().add(verticalBox);
		
		lblDocInfo = new JLabel("<html>" + doc[2]);
		lblDocInfo.setFont(lblDocInfo.getFont().deriveFont(12));
		lblDocInfo.setForeground(Color.gray);
		frame.setBackground(Color.black);
		verticalBox.add(lblDocInfo);
	}
}
