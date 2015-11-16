import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

/**
 * Created by Tom on 15/11/2015.
 */
public class DepthFirstSearch {

    private int maxDepth;
    private int maxNodesExpanded;


    private int nodesVisited = 0;
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

    private void clearStack() {
        expandedUnvistedNodes.clear();
    }

    public void search(){
        while(true){
            currentNode = expandedUnvistedNodes.pop();
            nodesVisited++;
            if(Arrays.equals(currentNode.getPuzzleState().getPuzzleArray(), goal)){
                currentNode.getPuzzleState().printState();
                return;
            }else{
                expandNode(currentNode);
                //visitedNodes.add(currentNode);
            }
            System.out.println("Nodes Visited: " + nodesVisited + " Nodes Expanded: " + nodesExpanded);
        }
    }

    public void expandNode(Node input){
        State inputState = input.getPuzzleState();
        ArrayList<Node> newNodes = new ArrayList<>();
        if (!inputState.isAtLeftBoundary() && input.getLastMove() != 'R'){
            State left = inputState.generateLeftState();
            Node leftNode = new Node(input,left, 'L');
            newNodes.add(leftNode);
            nodesExpanded++;
        }
        if (!inputState.isAtRightBoundary() && input.getLastMove() != 'L'){
            State right = inputState.generateRightState();
            Node rightNode = new Node(input, right, 'R');
            newNodes.add(rightNode);
            nodesExpanded++;
        }
        if (!inputState.isAtTopBoundary() && input.getLastMove() != 'D'){
            State up = inputState.generateUpState();
            Node upNode = new Node(input, up, 'U');
            newNodes.add(upNode);
            nodesExpanded++;
        }
        if (!inputState.isAtBottomBoundary() && input.getLastMove() != 'U'){
            State down = inputState.generateDownState();
            Node downNode = new Node(input, down, 'D');
            newNodes.add(downNode);
            nodesExpanded++;
        }
        Collections.shuffle(newNodes);
        for (Node current: newNodes) {
            expandedUnvistedNodes.push(current);
        }
    }

    public static void main(String[] args) {
        char[] start = {' ', ' ', ' ', ' ',
                        ' ', ' ', ' ', ' ',
                        ' ', ' ', ' ', ' ',
                        'a', 'b', 'X', 'c'};
        State startState = new State(start, 14);
        Node root = new Node(startState,true);
        DepthFirstSearch dfs = new DepthFirstSearch();
        dfs.clearStack();
        dfs.expandedUnvistedNodes.push(root);
        dfs.search();

    }

}
