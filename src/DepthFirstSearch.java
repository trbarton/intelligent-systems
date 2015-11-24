import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

/**
 * Created by Tom on 15/11/2015.
 */
public class DepthFirstSearch {

    private int nodesVisited = 0;

    private Node root;
    private Node currentNode;

    private  char[] goal = {' ', ' ', ' ', ' ',
                            ' ', 'a', ' ', ' ',
                            ' ', 'b', ' ', ' ',
                            ' ', 'c', ' ', 'X'};

    private Stack<Node> nodeStack = new Stack<>();
    private ArrayList<Node> solution = new ArrayList<>();

    public void setRoot(Node root) {
        this.root = root;
    }

    private void clearStack() {
        nodeStack.clear();
    }

    //DFS makes use of a LIFO stack to store nodes that have been discovered but not expanded
    //Search runs an infinite loop which pops an element off of the stack and check if it is the goal and prints
    // the solution else expands the node
    public void search(){
        nodeStack.push(root);
        while(true){
            currentNode = nodeStack.pop();
            nodesVisited++;
            if(Arrays.equals(currentNode.getPuzzleState().getPuzzleArray(), goal)){
                printSolution(currentNode);
                return;
            }else{
                expandNode(currentNode);
            }
        }
    }

    //Print Solution uses the parent reference of each node to trace from the goal back the
    //root. This is reversed and printed out. The Depth of the solution and nodes expanded is also
    //printed
    public void printSolution(Node goalNode){
        Node current = goalNode;
        while(!current.isRoot()){
            solution.add(current);
            current = current.getParent();
        }
        solution.add(root);
        Collections.reverse(solution);
        for(Node n : solution){
            n.getPuzzleState().printState();
            System.out.println("\n");
        }
        System.out.println("Nodes Visited: " + nodesVisited);
        System.out.println("Node Depth: " + goalNode.getDepth());
    }

    //Expand Node takes an input node and where possible calculates all possible successor nodes.
    //These successor nodes are added to an arraylist shuffled and then added to the stack. The
    //randomisation prevents dfs getting into an infinite loop of moves.
    public void expandNode(Node input){
        State inputState = input.getPuzzleState();
        ArrayList<Node> newNodes = new ArrayList<>();
        if (!inputState.isAtLeftBoundary()){
            State left = inputState.generateLeftState();
            Node leftNode = new Node(input,left);
            newNodes.add(leftNode);
        }
        if (!inputState.isAtRightBoundary()){
            State right = inputState.generateRightState();
            Node rightNode = new Node(input, right);
            newNodes.add(rightNode);
        }
        if (!inputState.isAtTopBoundary()){
            State up = inputState.generateUpState();
            Node upNode = new Node(input, up);
            newNodes.add(upNode);
        }
        if (!inputState.isAtBottomBoundary()){
            State down = inputState.generateDownState();
            Node downNode = new Node(input, down);
            newNodes.add(downNode);
        }
        Collections.shuffle(newNodes);
        for (Node current: newNodes) {
            nodeStack.push(current);
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
        dfs.clearStack();
        dfs.setRoot(root);
        dfs.search();

    }

}
