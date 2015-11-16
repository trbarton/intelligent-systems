import java.util.Stack;

/**
 * Created by Tom on 15/11/2015.
 */
public class Node {

    private Node parent;
    private State puzzleState;
    private int depth;
    private boolean isRoot = false;
    private char lastMove = ' ';


    public Node(Node parent, State state, char createdByMove){
        this.parent = parent;
        puzzleState = state;
        this.depth = parent.getDepth()+1;
        lastMove = createdByMove;
    }

    public Node(State state, boolean root){
        puzzleState = state;
        this.depth = 0;
        isRoot = root;
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

    public char getLastMove() {
        return lastMove;
    }
}
