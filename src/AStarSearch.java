import java.util.*;

/**
 * Created by tombarton on 23/11/2015.
 */
public class AStarSearch {

    private int expandedNodes;
    private Node currentNode;
    private Node root;

    private  char[] goal = {' ', ' ', ' ', ' ',
                            ' ', 'a', ' ', ' ',
                            ' ', 'b', ' ', ' ',
                            ' ', 'c', ' ', 'X'};

    private PriorityQueue<Node> nodePriorityQueue = new PriorityQueue<>();
    private ArrayList<Node> solution = new ArrayList<>();

    public void setRoot(Node root) {
        this.root = root;
    }

    public void search(){
        //adds root to queue
        nodePriorityQueue.add(root);

        //Infinite loop takes the first element off of the queue. Checks if it is the goal else expands the node
        while(true){
            currentNode = nodePriorityQueue.poll();
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

    //Expand Node takes an input node and if possible produces a new node for each possible node
    //These nodes are added to an ArrayList and then onto the queue. The ArrayList allows the order
    //to be randomised.
    public void expandNode(Node input){
        State inputState = input.getPuzzleState();
        ArrayList<Node> newNodes = new ArrayList<>();
        if (!inputState.isAtLeftBoundary()){
            State left = inputState.generateLeftState();
            Node leftNode = new Node(input,left);
            leftNode.calculateWeight();
            newNodes.add(leftNode);
        }
        if (!inputState.isAtRightBoundary()){
            State right = inputState.generateRightState();
            Node rightNode = new Node(input, right);
            rightNode.calculateWeight();
            newNodes.add(rightNode);
        }
        if (!inputState.isAtTopBoundary()){
            State up = inputState.generateUpState();
            Node upNode = new Node(input, up);
            upNode.calculateWeight();
            newNodes.add(upNode);
        }
        if (!inputState.isAtBottomBoundary()){
            State down = inputState.generateDownState();
            Node downNode = new Node(input, down);
            downNode.calculateWeight();
            newNodes.add(downNode);
        }
        for (Node current: newNodes) {
            nodePriorityQueue.add(current);
        }
    }
}
