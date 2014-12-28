Mitchell Gouzenko
mag2272 
Homework 4


Programming problem 1
To compile, go into my submission folder where Makefile is and run: 

$ make 

To test with my text file, run

$ make test

My test file contains a sentence that has every letter of the english
alphabet, so it results in a nice-looking tree. 


Descriptions of classes and methods: 

    Problem1 - class with main function. 

    Gui - class where I orchestrate the main layout of my tree. 

    Tree - class where I generate a component that draws the tree. 

    DecodingListener - listener that is triggered when the decode button is
                       clicked. Actual decoding is done in the HuffmanTree
                       class.

    TreeButtonListener - listener that is triggered when the "Show tree"
                         button is clicked. Simply makes the popup that
                         contains the tree visible.  

    EncodingListener - listener that is triggered when the encode button is
                       clicked. Contains methods to convert ascii to
                       bitstring. 
    
    HuffmanHeap - minimally modified priority queue. 

    HuffmanTree - the actual tree class. Contains many important methods (the
                  two most important are the constructors). 


Not sure how this all looks on ubuntu. I've only tested with Mac OSX. Keeping
my fingers crossed. Brb, going to sleep for a straight month. 

