/* Mitchell Gouzenko
 * mag2272
 * */
import java.util.Scanner;  
import java.io.*;
import java.util.HashMap;
import structures.HuffmanTree; 
import structures.HuffmanHeap;

public class Problem1{
    public static void main(String[] args){
        //Ensure correct args size
        if(args.length!=1){
            System.out.println("Usage: ./Problem1 <filename>");
            return;
        }
      
        /* *************VARS***************** */ 
        HashMap<Character, Integer> frequency;    /* A hashmap to calculate frequency of chars in txt file. */
        HuffmanHeap<HuffmanTree> heap;            /* Heap that will be used to build final huffman tree.    */
        HuffmanTree encodingTree;                 /* The final Huffman tree.                                */
        HashMap<Character, String> encodingTable; /* The table that maps characters to bitstrings.          */
        Gui gui; 

        try{
            //Create a file descriptor from the command line args. 
            File f = new File(args[0]);
            //Construct a table of character -> frequency key value pairs. 
            frequency = getFrequency(f);
            //Make a heap of HuffmanTrees out of these frequencies. 
            heap = makeHeap(frequency);
            //Use this heap to create the final HuffmanTree. 
            encodingTree = encodeForest(heap);
            //Encode a table of character -> bitstring key value pairs.
            encodingTable = encodingTree.encodeTable();
            //Print the table in the console. 
            printTable(encodingTable); 
            //Create an instance of GUI with the final HuffmanTree and encodingTable. 
            gui = new Gui(encodingTree, encodingTable); 
            //Activate the GUI. 
            gui.activate();
        } catch(FileNotFoundException e){         /* Abort if no file can be found. */
            System.out.println("File not found."); 
            return; 
        } catch(IOException e){                   /* Abort if there is an error reading the file */
            System.out.println("There was an error while reading the file.");
        }
    }

    public static void printTable(HashMap<Character, String> table){
        /* Prints HashMap of character -> bitstring key value pairs to console. */
        for(char c: table.keySet()) {
            if(c=='\n') System.out.format("[NL]: %s\n", table.get(c));
            else System.out.format("[ %c]: %s\n", c, table.get(c));
        }
    }

    public static HuffmanTree encodeForest(HuffmanHeap<HuffmanTree> heap){
        /* @param heap: a heap of HuffmanTrees that represent individual letters 
         * @return: a unified HuffmanTree.  
         * */

        /* ******VARS****** */
        HuffmanTree t1;        /* First HuffmanTree extracted from the heap.                       */ 
        HuffmanTree t2;        /* Second HuffmanTree extracted from the heap.                      */
        HuffmanTree newTree;   /* We need a third HuffmanTree to merge the other two HuffmanTrees. */ 
        String name;           /* The name of newTree.                                             */
        
        if(heap.isEmpty()) throw new NullPointerException();//We have a problem. 
        
        //Keep going till the heap is empty.
        for(int i=0; true; i++){
            //Name newTree.
            name = "T" + i;
            //Extract a HuffmanTree from the heap. 
            t1 = heap.deleteMin(); 
            //If this is the last HuffmanTree, return it. 
            if(heap.isEmpty()) return t1; 
            //Otherwise, extract another HuffmanTree from the heap. 
            t2 = heap.deleteMin(); 
            //Make a newTree from the two trees that were just extracted.
            newTree = new HuffmanTree(name, t1, t2);
            //Put it back in the heap. 
            heap.insert(newTree);  
        } 
    }

    public static HuffmanHeap<HuffmanTree> makeHeap(HashMap<Character, Integer> frequency){
        /* @param frequency: a table of character -> frequency key value pairs
         * @return: a valid heap of one-node HuffmanTrees.
         * */
        
        //Initialize an array that will contain one-node HuffmanTrees. 
        HuffmanTree[] array = new HuffmanTree[frequency.size()];
        HuffmanTree t; 
        int i = 0; 
        for(char c: frequency.keySet()){
            //Make a new HuffmanTree from the character and corresponding frequency.
            t = new HuffmanTree(c, frequency.get(c));   
            //Put this tree in the array. 
            array[i++] = t; 
        } 
        
        //Return a HuffmanHeap constructed from this array. 
        return new HuffmanHeap<HuffmanTree>(array);
    }

    public static HashMap<Character, Integer> getFrequency(File f) throws FileNotFoundException, IOException{
        /* @param f: File that is supplied as a command line arg.
         * @return: a HashMap of character -> frequency key value pairs 
         * */
        
        //Open the file. 
        FileReader file = new FileReader(f);
        //Construct a new HashMap that maps Characters to Integers.  
        HashMap<Character, Integer> frequencyTable = new HashMap<Character, Integer>();
        
        int in;         /* Container for chars that come out of the file.    */
        int frequency;  /* Frequency of an arbitrary character in the table. */
        char c;         /* Container for casting "in" into a char.           */
        
        //Iterate as long as things come out of this file. 
        while( (in = file.read()) > 0 ){
            //Cast whatever has come out of this file into an ascii char. 
            c = (char) in;
            //If this char is already in the frequency table, increase its frequency.
            if( frequencyTable.containsKey(c)){
                frequency = frequencyTable.get(c); 
                frequencyTable.put(c, frequency+1); 
            
            } else //Else throw it into the table with a frequency of 1. 
                frequencyTable.put(c, 1); 
        } 
        return frequencyTable;
    }
}



