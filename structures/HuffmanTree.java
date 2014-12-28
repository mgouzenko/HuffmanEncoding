package structures;
import java.lang.Comparable;
import java.util.HashMap;
import java.lang.StringBuffer;

public class HuffmanTree implements Comparable<HuffmanTree>{
    protected String name;       //The name of this node 
    protected int frequency;     //Frequency of this node
    protected HuffmanTree left;  //Left tree
    protected HuffmanTree right; //Right tree

    //Constructor to make leaf nodes from character and frequency value. 
    public HuffmanTree(char c, int f){
        left = null;
        right = null;
        name = String.valueOf(c); 
        frequency = f; 
    }

    //Constructor to create tree from name and two subtrees. 
    public HuffmanTree(String n, HuffmanTree l, HuffmanTree r){
        name = n; 
        left = l; 
        right = r;
        frequency = l.frequency+r.frequency;
    }
    
    public HuffmanTree getRight(){
        return right; 
    }

    public HuffmanTree getLeft(){
        return left; 
    }

    public int getFrequency(){
        return frequency; 
    }

    public String getName(){
        return name; 
    } 
   
    public int getDepth(){
        return getDepth(this);
    }
    
    //Decodes binary bitstring. 
    public String decode(String in){
        //If this is a leaf node, we can't decode anything.
        if(isLetter()) return "invalid Huffman tree\n";
        //Set error string. 
        String error = "malformed bitstring\n"; 
        String out = "";

        HuffmanTree tempTree = this; 
        char c; 
        //If the length of in is 0, we cant' decode it. 
        if(in.length()==0) return out;
        
        //Iterate over length of bitstring
        for(int i = 0; i<in.length(); i++){ 
            c = in.charAt(i); 
            if(c==' ' || c== '\n') continue;    //Skip whitespace. 
            
            if(tempTree.isLetter()) {  //If we're at a letter, go back to top. 
                out+=tempTree.name;
                tempTree = this; 
            }
                         
            if(c!='0' && c!='1') return error; //If the char isn't 0 or 1, return errormsg.

            //If c is 0 and we can't go left, there's a problem. 
            else if(c == '0' && tempTree.left == null) return error;
            //Otherwise, go left.
            else if(c=='0' && tempTree.left!= null) tempTree = tempTree.left;
            else if(c == '1' && tempTree.right == null) return error; 
            else if(c =='1' && tempTree.right != null) tempTree = tempTree.right; 
        }
        //We should be at a letter by the end of this string. 
        if(tempTree.isLetter()){
            out+=tempTree.name; 
        } 
        else return error;
        return out; 
    }

    private boolean isLetter(){
        return name.length()==1;
    }

    private int getDepth(HuffmanTree t){
        //Gets depth of tree recursively. 
        int left = 0; 
        int right = 0; 

        if(t.left==null && t.right == null) return 0; 
        if(t.left!=null) left = getDepth(t.left); 
        if(t.right!=null) right = getDepth(t.right); 

        return left>right?left+1:right+1; 
    }


    public void printTree(){
        //Prints tree. 
        if(this.left==null && this.right == null){
            System.out.format("[%c]: %d\n", name.charAt(0), frequency);
        }
        if(this.left!=null) this.left.printTree();
        if(this.right!=null) this.right.printTree(); 
    }

    public HashMap<Character, String> encodeTable(){
        //Driver for method that returns a char -> bitstring key value map. 
        HashMap<Character, String> map = new HashMap<Character, String>(); 
        encodeTable(this, map, "");
        return map;
    }
   
    private void encodeTable(HuffmanTree t, HashMap<Character, String> map, String buf){
        //If we're at a letter, add it to the map. 
        if(t.left== null && t.right == null){ 
            map.put( t.name.charAt(0), buf);
        }
        //If there are children, recurse on them, adjusting buffer accordingly. 
        if(t.left!=null) encodeTable(t.left, map, buf+"0");
        if(t.right!=null) encodeTable(t.right, map, buf + "1");
    }

    public int compareTo(HuffmanTree t){
        //method needed to be Comparable. 
        if(frequency > t.frequency) return 1; 
        else if(frequency < t.frequency) return -1; 
        return 0;
    }
}


