package tries;

import tries.Node.*;

class Trie {

  final 
  public static void main(String args[]) {
    System.out.println("This is trie.");
    
    Node node = new Node();
    System.out.println(node.callOut());
  }
}



//Design a data structure that supports adding new words and finding if a string matches any previously added string.

// PRETEND: ["Angelica", "Angelia", "Martha", "Angel", "Mars", "Mary", "An"]

//TRIE
//tree is empty
//start by assigning empty root 
//begin by adding in first vine 

//will have 3 methods - constructor, add, search

// Add: As we look to add a new word, we iterate over the existing vine's characters and the new word. Either: we run out of one, or they diverge.

//add method: iterate over vine by comparing letter in the vine to the letters in the word in array, node by node, and add diverging nodes as they appear

// until (i = array.length) {
//  temp = array.delete[0]
//   Trie.add(temp, trie)  
//} 

// if trie is empty, add it 
// make a node for each characters
// to the root,
    // root.next = []
    // root.push(C-node) into next array // NODE method not TRIE method
// move on to the next characters ("L)"
// c.push(L-node) into next array
// keep doing until last character (second "A")
// keep adding it, 
// toggle A's endOfWord attribute from false to true