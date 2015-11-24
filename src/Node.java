
import java.util.ArrayList;

/**
 * Created by Tom on 15/11/2015.
 */

public class Node implements Comparable<Node>{


    private Node parent;
    private State puzzleState;
    private int depth;
    private boolean root = false;
    private int weight;

    //Constructor for BFS has no parent reference to lower space complexity
    //However this means a solution won't be found but the nodes expanded can still be computed
    public Node(State state, int depth){
        puzzleState = state;
        this.depth = depth;
    }

    //Standard Constructor used when expanding nodes in DFS, IDS and A* Search
    public Node(Node parent, State state){
        this.parent = parent;
        puzzleState = state;
        this.depth = parent.getDepth()+1;
    }

    //Constructor used to create the root node. Use flag to mark as root
    public Node(State state, boolean root){
        puzzleState = state;
        this.depth = 0;
        this.root = root;
        parent = this;
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

    //Comparable implementation allows A* Search to prioritise nodes in the queue
    @Override
    public int compareTo(Node o) {
       return this.weight - o.getWeight();
    }

    //Calculates the distance a non blank tile is from it's desired location.
    //The depth plus each manhattan distance forms the weight.
    //Please note this is hardcoded!! and will only work on puzzles of size 4x4.
    public void calculateWeight(){
        char[] currentPuzzle = puzzleState.getPuzzleArray();
        int size = puzzleState.getPuzzleSize();

        //Correct Positions (Hardcoded)
        int xABC = 1;
        int yA = 1;
        int yB = 2;
        int yC = 3;

        //Current Positions
        int xPosA = 0;
        int yPosA = 0;
        int xPosB = 0;
        int yPosB = 0;
        int xPosC = 0;
        int yPosC = 0;
        int xPosX = 0;
        int yPosX = 0;

        for (int i=0; i<currentPuzzle.length; i++) {

            if(currentPuzzle[i] == 'a'){
                xPosA = i % size;
                yPosA = (i - xPosA)/size;
            } else if (currentPuzzle[i] == 'b'){
                xPosB = i % size;
                yPosB = (i - xPosB)/size;
            } else if (currentPuzzle[i] == 'c'){
                xPosC = i % size;
                yPosC = (i - xPosC)/size;
            } else if (currentPuzzle[i] == 'X'){
                xPosX = i % size;
                yPosX = (i - xPosX)/size;
            }
        }

        //Use positions to calculate weights
        int weightA = Math.abs(xABC-xPosA) + Math.abs(yA-yPosA);
        int weightB = Math.abs(xABC-xPosB) + Math.abs(yB-yPosB);
        int weightC = Math.abs(xABC-xPosC) + Math.abs(yC-yPosC);
        int weightX = Math.abs((size-1)-xPosX) + Math.abs((size-1)-yPosX);

        weight = depth + weightA + weightB + weightC + weightX;

    }

}
