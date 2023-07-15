import javax.swing.*;
import java.awt.event.*;

public class TextConverter extends JFrame implements ActionListener {
    private JTextField textField;
    private JButton upperCaseButton;
    private JButton lowerCaseButton;
    
    public TextConverter() {
        // Set up the GUI
        super("Text Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create the text field
        textField = new JTextField(20);
        add(textField);
        
        // Create the upper case button
        upperCaseButton = new JButton("Upper Case");
        upperCaseButton.addActionListener(this);
        add(upperCaseButton);
        
        // Create the lower case button
        lowerCaseButton = new JButton("Lower Case");
        lowerCaseButton.addActionListener(this);
        add(lowerCaseButton);
        
        // Set the layout and show the GUI
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        pack();
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent event) {
        // Get the text from the text field
        String text = textField.getText();
        
        // Determine which button was pressed
        if (event.getSource() == upperCaseButton) {
            // Convert the text to upper case
            text = text.toUpperCase();
        } else if (event.getSource() == lowerCaseButton) {
            // Convert the text to lower case
            text = text.toLowerCase();
        }
        
        // Set the text in the text field
        textField.setText(text);
    }
    
    public static void main(String[] args) {
        // Create the TextConverter object
        TextConverter converter = new TextConverter();
    }
}
