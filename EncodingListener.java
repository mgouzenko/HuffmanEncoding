import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.HashMap;

public class EncodingListener implements ActionListener {
/* Fires an encoding of regular text into a bitstring */
    private JTextArea outTextArea; 
    private JTextArea inTextArea;
    private HashMap<Character, String> map; 

    public EncodingListener(JTextArea in, JTextArea out, HashMap<Character, String> m) {
        outTextArea = out;
        inTextArea = in; 
        map = m; 
    }

    public void actionPerformed(ActionEvent ae) {
        String input = inTextArea.getText();
        String output = encode(input); 
        outTextArea.setText(output); 
    }

    private String encode(String in){
        /* @param in: a string representing plain ascii text
         * @return: a bitstring encoding of "in"
         * */
        String out = "";
        char c;
        for(int i=0; i<in.length(); i++){                   //Iterate over length of the bitString. 
            c = in.charAt(i);                               //Get the character at i. 
            if(!map.containsKey(c)) out+="(invalid char)";  //If c is not encoded, print error text.
            else out += map.get(c);                         //Else add bitstring encoding of c. 
        }
        return out;
    }
}

