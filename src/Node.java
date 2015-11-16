import java.util.Stack;

/**
 * Created by Tom on 15/11/2015.
 */
public class Node {

    private Node parent;
    private State puzzleState;
    private int depth;
    private boolean isRoot = false;


    public Node(Node parent, State state, int depth){
        this.parent = parent;
        puzzleState = state;
        this.depth = depth;
    }

    public Node(State state, boolean root){
        puzzleState = state;
        this.depth = 0;
        isRoot = root;
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


}
