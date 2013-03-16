import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame
{
	private static final int H = 970; // Height of window
	private static final int W = 590; // Width of window
	
	private static final int LM = 10;	//left margin
	private static final int TM = 10; // Top margin
	
	private static final int LM2 = 300;	//Second Left margin (2nd column)
	
	private static final int VM = 10;	//Vertical Margin

	private static final int HSIZE = 250; // Height of sub window
	
	private static final String BUTTON_NAME1 = "Encrypt";
	private static final String BUTTON_NAME2 = "Decrypt";
	
	private JLabel label1 = new JLabel("Enter text to encrypt");
	private JLabel label2 = new JLabel("Generated key");
	private JLabel label3 = new JLabel("Generated text");
	
	private JLabel label4 = new JLabel("Enter text to decrypt");
	private JLabel label5 = new JLabel("Enter key");
	private JLabel label6 = new JLabel("Decrypted text");
	
	private JTextArea theInput1 = new JTextArea();			//First Column
	private JEditorPane theKey1 = new JEditorPane();
	private JEditorPane theOutput1 = new JEditorPane();
	
	private JTextArea theInput2 = new JTextArea();			//Second Column
	private JTextArea theKey2 = new JTextArea();
	private JEditorPane theOutput2 = new JEditorPane();
	
	private JButton theButton1 = new JButton(BUTTON_NAME1);
	private JButton theButton2 = new JButton(BUTTON_NAME2);
	private ButtonPress onBtnPress = new ButtonPress();
	
	private Translator trans = new Translator();

	public GUI()
	{
		Container cp = getContentPane(); // Content pane
		cp.setLayout(null); // No layout manager
		setSize(W, H); // Size of Window
		setTitle("'Ashu' Enc/Dec 0.6 by Almas. Alpha tester - Atheryos");
		Font font = new Font("Monospaced", Font.PLAIN, 12); // Font Used
		
		//ENCRYPTION COLUMN
		
		label1.setBounds(LM, TM, 150, 20);
		label1.setFont(font);
		cp.add(label1);
		
		theInput1.setBounds(LM, TM + VM + 20, 300 - 40, HSIZE);// Input Area Size
		theInput1.setFont(font); // Font
		theInput1.setLineWrap(true);
		cp.add(theInput1); // Add to c. pane
		
		theButton1.setBounds(LM, TM + VM * 2 + 20 + HSIZE, 300 - 40, 40);
		theButton1.addActionListener(onBtnPress);
		cp.add(theButton1);
		
		label2.setBounds(LM, TM + VM * 3 + 20 + HSIZE + 40, 100, 20);
		label2.setFont(font);
		cp.add(label2);

		JScrollPane theSP = new JScrollPane(); // Scrolling window
		theSP.setBounds(LM, TM + VM * 4 + 20 + HSIZE + 40 + 20, 300 - 40, HSIZE); // Size of window
		theSP.setFont(font); // Font
		
		cp.add(theSP); // Add to c. pane
		theOutput1.setFont(font);
		theSP.getViewport().add(theKey1); // Add output area
		
		label3.setBounds(LM, TM + VM * 5 + 20 + HSIZE + 40 + 20 + HSIZE, 150, 20);
		label3.setFont(font);
		cp.add(label3);
		
		JScrollPane theSP3 = new JScrollPane(); // Scrolling window
		theSP3.setBounds(LM, TM + VM * 6 + 20 + HSIZE + 40 + 20 + HSIZE + 20, 300 - 40, HSIZE); // Size of window
		theSP3.setFont(font); // Font

		cp.add(theSP3); // Add to c. pane
		theOutput1.setFont(font);
		theSP3.getViewport().add(theOutput1); // Add output area
			
		//DECRYPTION COLUMN
		
		label4.setBounds(LM2, TM, 150, 20);
		label4.setFont(font);
		cp.add(label4);
		
		theInput2.setBounds(LM2, TM + VM + 20, 300 - 40, HSIZE);// Input Area Size
		theInput2.setFont(font); // Font
		theInput2.setLineWrap(true);
		cp.add(theInput2); // Add to c. pane
		
		label5.setBounds(LM2, TM + VM * 2 + 20 + HSIZE, 100, 20);
		label5.setFont(font);
		cp.add(label5);
		
		theKey2.setBounds(LM2, TM + VM * 3 + 20 + HSIZE + 20, 300 - 40, HSIZE);// Input Area Size
		theKey2.setFont(font); // Font
		theKey2.setLineWrap(true);
		cp.add(theKey2); // Add to c. pane
		
		theButton2.setBounds(LM2, TM + VM * 4 + 20 + HSIZE + 20 + HSIZE, 300 - 40, 40);
		theButton2.addActionListener(onBtnPress);
		cp.add(theButton2);
		
		label6.setBounds(LM2, TM + VM * 5 + 20 + HSIZE + 20 + HSIZE + 40, 100, 20);
		label6.setFont(font);
		cp.add(label6);

		JScrollPane theSP2 = new JScrollPane(); // Scrolling window
		theSP2.setBounds(LM2, TM + VM * 6 + 20 + HSIZE + 20 + HSIZE + 40 + 20, 300 - 40, HSIZE); // Size of window
		theSP2.setFont(font); // Font

		cp.add(theSP2); // Add to c. pane
		theOutput2.setFont(font);
		theSP2.getViewport().add(theOutput2); // Add output area
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);// Exit on close
	}
	
	class ButtonPress implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			String text = "";
			
			switch(ae.getActionCommand())
			{
				case BUTTON_NAME1:
					
					text = theInput1.getText();
					theOutput1.setText(trans.encrypt(text));
					theKey1.setText(trans.getKey());
					break;
					
				case BUTTON_NAME2:
					
					text = theInput2.getText();
					String key = theKey2.getText();
					theOutput2.setText(trans.decrypt(text, key));	
					break;
					
				default:
					break;
			}
		}
	}
}
