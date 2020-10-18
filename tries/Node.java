package tries;
import java.util.ArrayList;

public class Node {

  String value;
  boolean endOfWord;
  ArrayList<Node> nextNodes;

  public Node(String value) {
    this.value = value;
    nextNodes = new ArrayList<Node>();
    endOfWord = false;
  }

  public Node() {
    value = "";
    nextNodes = new ArrayList<Node>();
    endOfWord = false;
  }

  public String getValue() {
    return this.value;
  }
  
  public void setValue(String value) {
    this.value = value;
  }

  public Boolean getEndOfWord() {
    return this.endOfWord;
  }

  public void setEndOfWord(Boolean flag){
    this.endOfWord = flag;
  }

  public void setEndOfWord() {
    this.endOfWord = !endOfWord;
  }

  public ArrayList<Node> getNextNodes() {
    return nextNodes;
  }

  public void addToNextNodes(Node node) {
    this.nextNodes.add(node);
  }
}