/**
 * Created by Tom on 15/11/2015.
 */
public class Node implements Comparable<Node>{

    private Node parent;
    private State puzzleState;
    private int depth;
    private boolean root = false;

    private int weight;

    public Node(State state, int depth){
        puzzleState = state;
        this.depth = depth;
    }

    public Node(Node parent, State state){
        this.parent = parent;
        puzzleState = state;
        this.depth = parent.getDepth()+1;
    }

    public Node(State state, boolean root){
        puzzleState = state;
        this.depth = 0;
        this.root = root;
        parent = this;
    }

    public Node(State state){
        puzzleState = state;
    }

    public State getPuzzleState() {
        return puzzleState;
    }

    public int getDepth() {
        return depth;
    }

    public Node getParent() {
        return parent;
    }

    public boolean isRoot(){
        return root;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
       return this.weight - o.getWeight();
    }
}
