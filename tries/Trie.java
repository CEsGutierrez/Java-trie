package tries;

import java.util.ArrayList;
import java.util.List;

class Trie {

   Node root;
   TrieAnalyzer analyzer = new TrieAnalyzer();
  public static void main(String args[]) {
    Trie thisTrie = new Trie();
    System.out.println("This is trie.");

  }

  /**
   * Constructor assigns root as empty node
   */
  public void Trie() {
    root = new Node();
  }

  /** 
   * Constructor assigns root as node with a value if one is provided
   */
  public void Trie(String key) {
    root = new Node(key);
  }

  /**
   * Interprets results from analyzer helper class to determine if the input is a substring in the trie
   */
  public boolean containsElementsOf(String input) {
    if (input.length() == 0) {
      return true;
    } else if (input.length() == 1) {
      return (root.equals(input));
    }

    analyzer.searchTrie(input, root);

    // indicates that the entire length of input was traversed and matched 
    
    if (analyzer.lastIndex == (input.length() - 1)) {
      return true;
    } else {
      return false;
    }
  }

 /**
  * Interprets results from analyzer helper class to determine if a string is already stored and not just a substring in the trie using the endOfWord boolean
  */
  public boolean containsExactString(String input) {
    if (input.length() == 0) {
      return true;
    } else if (input.length() == 1) {
      return (root.equals(input));
    }

    analyzer.searchTrie(input, root);

    return analyzer.lastNode.endOfWord;
  }

  /**
   * Adds string to trie if it's not already there, if it's there ands endOfWord flag to the last node
   * Will iterate through a list of new nodes and add them in sequence to the nextNodes collection for each node
   *  String "ABC"
   *  A.nextNodes = [B]
   *  B.nextNodes = [C]
   */
  public void add(String input) {
    if (input.length() == 0) {
      return;
    }
    
    analyzer.searchTrie(input, root);

    Node lastMatchNode = analyzer.lastNode;
    Integer lastMatchValue = analyzer.lastIndex;

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