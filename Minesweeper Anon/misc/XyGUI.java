// The following is the work of HersheyCactus
// 2020
// AP Computer Science A

//This object was a testing GUI (that has been left unfinished) that was created
//to try and test test fields.
//The majority of this code has been taken from Stack Overflow or the Java Tutorials
//documents.

//This object is not used in the final project, and as such proper documentation
//has not been created.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class XyGUI extends JPanel implements ActionListener{
    //In initialization code:
    private JTextField textField;
    private JTextArea textArea;
    private final static String newline = "\n";

    public XyGUI(boolean first) {
        //i have given up on this program
        
        if(first){
            super(new GridBagLayout());
            createAndShowGUI();
            textField = new JTextField(20);
            textField.addActionListener(this);

            textArea = new JTextArea(5, 20);
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);

            //Add Components to this panel.
            GridBagConstraints c = new GridBagConstraints();
            c.gridwidth = GridBagConstraints.REMAINDER;

            c.fill = GridBagConstraints.HORIZONTAL;
            add(textField, c);

            c.fill = GridBagConstraints.BOTH;
            c.weightx = 1.0;
            c.weighty = 1.0;
            add(scrollPane, c);
        }
    }

    public void actionPerformed(ActionEvent evt) {
        String text = textField.getText();
        textArea.append(text + newline);
        textField.selectAll();

        //Make sure the new text is visible, even if there
        //was a selection in the text area.
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
    /**
     * Create the GUI and show it.  For thread safety, 
     * this method should be invoked from the 
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("TextDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add contents to the window.
        frame.add(new XyGUI(false));

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}