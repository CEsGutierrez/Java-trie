package tries;

import java.util.ArrayList;
import java.util.List;

/** 
 * Helper class to analyze a trie, uses state to store results of last search
 */

public class TrieAnalyzer {

  public Node lastNode;
  public Integer lastIndex;

  public void searchTrie(String targetString, Node root){
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

    lastIndex = queueCounter;
    lastNode = current;
  }
}