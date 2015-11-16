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

    private  char[] goal = {' ', 'a', ' ', ' ',
                            ' ', 'b', ' ', ' ',
                            ' ', 'c', ' ', ' ',
                            ' ', 'X', ' ', ' '};
    private Node rootNode;
    private Node currentNode;

    private Stack<Node> expandedUnvistedNodes = new Stack<>();
    private ArrayList<Node> visitedNodes = new ArrayList<>();

    private ArrayList<Node> solution;

    public  DepthFirstSearch(int maxDepth, int maxNodes, int puzzleSize){
        this.maxDepth = maxDepth;
        this.maxNodesExpanded = maxNodes;
    }

    public DepthFirstSearch(){

    }

    public void search(){
        while(true){
            currentNode = expandedUnvistedNodes.pop();

            if(Arrays.equals(currentNode.getPuzzleState().getPuzzleArray(), goal)){
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
        if (inputState.isAtLeftBoundary() == false){
            State left = inputState.generateLeftState();
            Node leftNode = new Node(input,left);
            expandedUnvistedNodes.push(leftNode);
        }
        if (inputState.isAtRightBoundary() == false){
            State right = inputState.generateRightState();
            Node rightNode = new Node(input, right);
            expandedUnvistedNodes.push(rightNode);
        }
        if (inputState.isAtTopBoundary() == false){

        }
        if (inputState.isAtBottomBoundary() == false){

        }
    }

    public static void main(String[] args) {
        char[] start = {' ', ' ', ' ', ' ',
                        ' ', ' ', ' ', ' ',
                        ' ', ' ', ' ', ' ',
                        'a', 'b', 'c', 'X'};
        State startState = new State(start, 15);
        Node root = new Node(startState,true);
        DepthFirstSearch dfs = new DepthFirstSearch();
        dfs.expandedUnvistedNodes.push(root);
        dfs.search();

    }


}
