/* Mitchell Gouzenko
 * mag2272 
 * */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.HashMap;
import structures.HuffmanTree;

public class DecodingListener implements ActionListener {
    /* Listener that fires a bitstring -> regular string decoding */
    
    private JTextArea outTextArea; /* The JTextArea that will house the results of the decoding. */ 
    private JTextArea inTextArea;  /* The JTextArea that will provide the input string.          */
    private HuffmanTree tree;      /* The HuffmanTree used to decode the bitstring.              */

    public DecodingListener(JTextArea in, JTextArea out, HuffmanTree t) {
        outTextArea = out;
        inTextArea = in; 
        tree = t; 
    }

    public void actionPerformed(ActionEvent ae) {
        String input = inTextArea.getText();  //Get text from the input field.
        String output = decode(input);        //Decode the text. 
        outTextArea.setText(output);          //Put output text in output field.
    }

    private String decode(String in){
        return tree.decode(in); //Use the HuffmanTree's decode method. 
    }
}

