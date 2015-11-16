import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * Created by Tom on 15/11/2015.
 */
public class DepthFirstSearch {

    private int maxDepth;
    private int maxNodesExpanded;

    private int nodesExpanded = 0;

    private State goalState;
    private Node rootNode;
    private Node currentNode;

    private Stack<Node> expandedUnvistedNodes = new Stack<>();
    private ArrayList<Node> visitedNodes = new ArrayList<>();

    private ArrayList<Node> solution;

    public DepthFirstSearch(int maxDepth, int maxNodes, int puzzleSize){
        this.maxDepth = maxDepth;
        this.maxNodesExpanded = maxNodes;
    }

    public void Search(){
        while(nodesExpanded < maxNodesExpanded){
            currentNode = expandedUnvistedNodes.pop();

            if(Arrays.equals(currentNode.getPuzzleState().getPuzzleArray(), goalState.getPuzzleArray())){
                //Trace route back to root node
                return;
            }else{
                expandNode(currentNode);
                visitedNodes.add(currentNode);
            }
        }
    }

    public void expandNode(Node input){
        State inputState = input.getPuzzleState();
    }


}
