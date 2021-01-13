package tries;

import java.util.ArrayList;
import java.util.List;

import tries.Node.*;

class Trie {

   Node root;
  public static void main(String args[]) {
    System.out.println("This is trie.");
    
    Node node = new Node();
  }

  // constructor makes a root node and assigns it as such, the node is empty
  public void Trie() {
    root = new Node();
  }

  public void Trie(String key) {
    root = new Node(key);
  }

  public boolean search(String input) {
    // short circuit in case input being searched is short
    if (input.length() == 0) {
      return true;
    } else if (input.length() == 1) {
      return (root.equals(input));
    }
    
    if (searchLastMatchValue(input) == (input.length() - 1)) { // indicates that the entire input was matched in the trie
      return true;
    } else {
      return false;
    }
  }

  /** 
   * helper method that actually traverses the trie and returns the last index at which the input matched the trie's levels
   */
  private int searchLastMatchValue(String targetString) {

    // set-up for bfs to search the trie
    int inputCounter = 0;
    List<Node> queue = new ArrayList(){{
      add(targetString.charAt(0));
    }};
    int queueCounter = 0;

    // checks if temp is same as value being searched
    // if there is a match, it replaces the queue's contents with the next elements array from the node, if there are any
    while (queue.size() > 0) {
      Node temp = queue.get(queueCounter);
      if (temp.getValue().equals(targetString.charAt(inputCounter))) {
        queue = temp.getNextNodes();
        inputCounter += 1;
        queueCounter = 0;
      } else {
        queueCounter += 1;
      }
    }
    return inputCounter;
  }

  /** 
   * helper method that actually traverses the trie and returns the last node at which the input matched the trie's levels
   */

  private Node searchLastMatchNode(String targetString) {
        // set-up for bfs to search the trie
        int inputCounter = 0;
        List<Node> queue = new ArrayList(){{
          add(targetString.charAt(0));
        }};
        int queueCounter = 0;
        Node current = queue.get(queueCounter);
    
        // checks if temp is same as value being searched
        // if there is a match, it replaces the queue's contents with the next elements array from the node, if there are any
        while (queue.size() > 0) {
          Node temp = queue.get(queueCounter);
          queue.remove(queueCounter);
          if (temp.getValue().equals(targetString.charAt(inputCounter))) {
            current = temp;
            queue.clear();
            queue = temp.getNextNodes();
            inputCounter += 1;
            queueCounter = 0;

          } else {
            queueCounter += 1;
          }
        }
        return current;
  }

  public void add(String input) {
    if (input.length() == 0) {
      return;
    }
    
    Node lastMatchNode = searchLastMatchNode(input);
    int lastMatchValue = searchLastMatchValue(input);

    // indicates entire string is already contained in the tree
    if (lastMatchValue == input.length() - 1) {
      lastMatchNode.endOfWord = true;
      return;
    }


    // creates a list of nodes for the values that are not already matched
    List<Node> newNodeList = new ArrayList<>();

    while (lastMatchValue < input.length()) {
      String value = String.valueOf(input.charAt(lastMatchValue));
      newNodeList.add(new Node(value));
      lastMatchValue += 1;
    }

    // iterates through the list and adds them in sequence to the nextNodes collection for each node
    //// String "ABC"
    //// A.nextNodes = [B]
    //// B.nextNodes = [C]

    Node current = lastMatchNode;

    while (!newNodeList.isEmpty()) {
      Node temp = newNodeList.get(0);
      current.nextNodes.add(temp); // adds the new node
      newNodeList.remove(0); // removes the node from the collection
      current = temp; // makes current the newly added node
    }

    current.endOfWord = true;

  }

}


// PseudoCode outline for class: 

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