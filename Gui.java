/* Mitchell Gouzenko
 * mag2272
 * */

import java.awt.*;
import javax.swing.*;
import structures.*;
import java.util.HashMap;

public class Gui 
{
    private  Tree treeComponent;                  /* A drawing of the encoding tree.   */
    private  JFrame frame;                        /* The enclosing frame.              */
    private  JDialog popup;                       /* The popup containing the tree.    */
    private  HuffmanTree tree;                    /* The HuffmanTree.                  */
    private  HashMap<Character, String> encoding; /* Character -> Bitstring map.       */
    private final int FIELD_WIDTH = 30;           /* Width of TextArea.                */
    private final int FIELD_HEIGHT = 4;           /* Height of TextArea.               */

    public Gui(HuffmanTree t, HashMap<Character, String> map){
        encoding = map; 
        tree = t;
        frame = new JFrame(); 
        popup = new JDialog(); 
        treeComponent = new Tree(t);   //Draw a tree using the encoding tree. 
        JDialog popup = new JDialog(); 
    }
    

    public void activate(){ 
        popup.setLayout(new BorderLayout());               //Set layout of tree popup. 
        JScrollPane pane = new JScrollPane(treeComponent); //New ScrollPane contains tree drawing. 
        popup.add(pane, BorderLayout.CENTER);              //Add new pane to popup. 
        popup.pack();                                      //Pack the popup. 

        /////////////////////// Make the panel that encodes text ///////////////////////////////
        JPanel encodePane = new JPanel();               
        encodePane.setLayout(new FlowLayout(FlowLayout.LEFT));
        JTextArea field = new JTextArea(FIELD_HEIGHT, FIELD_WIDTH);
        JTextArea dfield = new JTextArea(FIELD_HEIGHT, FIELD_WIDTH);
        field.setText("\n\n< Replace this text to encode your own sentence. >\n\n"); 
        encodePane.add(field);
       
        JButton encodeButton = new JButton("Encode!");
        encodeButton.setAlignmentX( Component.LEFT_ALIGNMENT );
        encodeButton.addActionListener(new EncodingListener(field, dfield, encoding));
        encodePane.add(encodeButton); 

        //////////////////////// Make the panel that decodes text ///////////////////////////////
        JPanel decodePane = new JPanel();
        decodePane.setLayout(new FlowLayout(FlowLayout.LEFT));
        dfield.setLineWrap(true); 
        dfield.setText("\n\n< Replace this text to decode your own sentence. >\n\n"); 
        decodePane.add(dfield);
       
        JButton decodeButton = new JButton("Decode!");
        decodeButton.setAlignmentX( Component.LEFT_ALIGNMENT );
        decodeButton.addActionListener(new DecodingListener(dfield, field, tree));
        decodePane.add(decodeButton);

        //////////////////////// Make the panel that houses the tree button //////////////////////
        JPanel treePane = new JPanel(); 
        treePane.setLayout(new FlowLayout(FlowLayout.LEFT)); 
        JButton treeButton = new JButton("Show Huffman Tree"); 
        treeButton.addActionListener(new TreeButtonListener(popup));
        treePane.add(treeButton); 

        ///////////////////////// Make the main JPanel and pack it into the frame ////////////////
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.PAGE_AXIS));
        main.add(encodePane);
        main.add(new JSeparator(SwingConstants.HORIZONTAL));
        main.add(decodePane);
        main.add(new JSeparator(SwingConstants.HORIZONTAL));
        main.add(treePane); 

        frame.setLayout(new FlowLayout());
        frame.setMaximumSize(new Dimension(800, 600));
        frame.add(main);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
		frame.setVisible(true);
    }
}
