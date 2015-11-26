import java.util.*;

/**
 * Created by tombarton on 16/11/2015.
 */
public class BreadthFirstSearch {

    private int expandedNodes = 0;
    private Node currentNode;
    private Node root;

    private  char[] goal = {' ', ' ', ' ', ' ',
                            ' ', 'a', ' ', ' ',
                            ' ', 'b', ' ', ' ',
                            ' ', 'c', ' ', 'X'};

    private LinkedList<Node> nodeQueue = new LinkedList<>();
    private ArrayList<Node> solution = new ArrayList<>();


    public void setRoot(Node root) {
        this.root = root;
    }

    //BFS uses a FIFO queue. An infinite loop takes the first element off the queue checks if it is the solution
    //and returns printing the depth of solution and nodes expanded else expands the node. Unlike other search techniques
    //the parent reference has been removed to save memory and hopefully give an insight into the number of nodes expanded.
    public void search(){
        nodeQueue.add(root);
        while(true){
            currentNode = nodeQueue.poll();
            if(Arrays.equals(currentNode.getPuzzleState().getPuzzleArray(), goal)){
                printSolution(currentNode);
                return;
            }else{
                expandNode(currentNode);
                expandedNodes++;

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
            System.out.println("Move " + n.getDepth());
            n.getPuzzleState().printState();
            System.out.println("\n");
        }
        System.out.println("Nodes Expanded: " + expandedNodes);
        System.out.println("Node Depth: " + goalNode.getDepth());
    }


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
        for (Node current: newNodes) {
            nodeQueue.add(current);
        }
    }
}
