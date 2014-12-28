/* Mitchell Gouzenko
 * mag2272
 * */

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import structures.HuffmanTree;
import java.lang.Math;

public class Tree extends JComponent {
    private HuffmanTree tree;                   // The tree to be drawn.
    private static final int CSIZE = 100;        // The diameter of each node. 
    private int HEIGHT;                         // Window height.
    private int WIDTH;                          // Window width. 
    private static final int FONT = 10;         // Text font
    private static final int SCALE = 4;         // Scale for shifting labels
    private static final int PADDING = CSIZE*2; // Padding between tree levels. 
    private Cursor c;                           // Cursor pointing to x and y coordinates. 
    
    public Tree(HuffmanTree t) {
        tree=t;
        int maxleaves = (int) Math.pow( 2, tree.getDepth());
        WIDTH = maxleaves * CSIZE;                           //Make window wide enough for leaves.
        if(WIDTH>4000) WIDTH=4000;                           //If too large, cap width. 
        HEIGHT = ( tree.getDepth()+1) * 2*CSIZE;             //Get height based on tree depth.
        c = new Cursor(WIDTH/2, (CSIZE/2) );                 //Make a new cursor in middle of page. 
        setPreferredSize(new Dimension(WIDTH,HEIGHT));       //Set dimensions of components.
    }

    public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;                       
        g2.setFont(new Font("TimesRoman", Font.PLAIN, FONT));
		g2.setColor(Color.BLACK);
		drawTree(g2, tree, c, "", WIDTH/4); 
    }

    private void drawNode(Cursor c, Graphics2D g2, String name, String code){
        g2.draw(new Ellipse2D.Double(c.x-(CSIZE/2), c.y-(CSIZE/2), CSIZE, CSIZE));
        g2.setColor(Color.RED);
        g2.drawString(name, c.x-(FONT*name.length()/SCALE), c.y-(CSIZE/4)); 
        g2.setColor(Color.BLUE);
        g2.drawString(code, c.x-(FONT*code.length()/SCALE), c.y); 
        g2.setColor(Color.BLACK);
    }

    private void drawTree(Graphics2D g2, HuffmanTree t, Cursor cur, String code, int offset){
        /* Recursively draws the entire tree. */ 
        String tempcode = code;   
        String name = t.getName();          //Get name of current node. 
        if(name.length()>1) tempcode = "";  //If this isnt a leaf, empty tempcode.
        if(name.equals("\n")) name = "NL";  //If the name is "\n", rename to NL.
        if(name.equals(" ")) name = "SP";   //If the name is " ", rename to SP.  
        drawNode(cur, g2, name, tempcode);  //Draw the current node using cursor. 
        HuffmanTree left = t.getLeft();     //Get the left tree.
        HuffmanTree right = t.getRight();   //Get the right tree. 
        
        if(left!=null){ //Draw the left tree. 
            //Make a new cursor that shifts left by offset and down by PADDING.
            Cursor cleft = new Cursor(cur.x - offset, cur.y + PADDING);
            //Draw a line to connect this node to the left tree. 
            g2.drawLine(cur.x, cur.y+(CSIZE/2), cleft.x, cleft.y-(CSIZE/2));
            //Recurse
            drawTree(g2, left, cleft, code+"0", offset/2); 
        }

        if(right!=null){
            Cursor cright = new Cursor(cur.x + offset, cur.y + PADDING);
            g2.drawLine(cur.x, cur.y+(CSIZE/2), cright.x, cright.y-(CSIZE/2));
            drawTree(g2, right, cright, code+"1", offset/2); 
        }
    }

    private class Cursor{
        //This class makes it easier to track position on page. 
        int x;  //X-coordinate
        int y;  //Y-coordinate
        
        Cursor(int xcoor, int ycoor){ 
            x=xcoor;
            y=ycoor;
        }

    }

}
