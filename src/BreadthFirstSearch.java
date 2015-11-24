import java.util.*;

/**
 * Created by tombarton on 16/11/2015.
 */
public class BreadthFirstSearch {

    private int expandedNodes = 0;
    private Node currentNode;

    private  char[] goal = {' ', ' ', ' ', ' ',
                            ' ', 'a', ' ', ' ',
                            ' ', 'b', ' ', ' ',
                            ' ', 'c', ' ', 'X'};

    private LinkedList<Node> nodeQueue = new LinkedList<>();

    //BFS uses a FIFO queue. An infinite loop takes the first element off the queue checks if it is the solution
    //and returns printing the depth of solution and nodes expanded else expands the node. Unlike other search techniques
    //the parent reference has been removed to save memory and hopefully give an insight into the number of nodes expanded.
    public void search(){
        while(true){
            currentNode = nodeQueue.poll();
            if(Arrays.equals(currentNode.getPuzzleState().getPuzzleArray(), goal)){
                currentNode.getPuzzleState().printState();
                System.out.println("Depth: " + currentNode.getDepth() + " Nodes Expanded: " + expandedNodes);
                return;
            }else{
                expandNode(currentNode);
                expandedNodes++;

            }

        }
    }

    //Expand Node in BFS uses a depth incrementer as there is no reference to the parent to get the previous depth
    //The order they are added to the queue is not random.
    public void expandNode(Node input){
        State inputState = input.getPuzzleState();
        ArrayList<Node> newNodes = new ArrayList<>();
        int depthIncrementer = input.getDepth() + 1;
        if (!inputState.isAtLeftBoundary()){
            State left = inputState.generateLeftState();
            Node leftNode = new Node(left, depthIncrementer);
            newNodes.add(leftNode);
        }
        if (!inputState.isAtRightBoundary()){
            State right = inputState.generateRightState();
            Node rightNode = new Node(right, depthIncrementer);
            newNodes.add(rightNode);
        }
        if (!inputState.isAtTopBoundary()){
            State up = inputState.generateUpState();
            Node upNode = new Node(up, depthIncrementer);
            newNodes.add(upNode);
        }
        if (!inputState.isAtBottomBoundary()){
            State down = inputState.generateDownState();
            Node downNode = new Node(down, depthIncrementer);
            newNodes.add(downNode);
        }
        for (Node current: newNodes) {
            nodeQueue.add(current);
        }
    }



    public static void main(String[] args) {
        char[] start = {' ', ' ', ' ', ' ',
                ' ', ' ', ' ', ' ',
                ' ', ' ', ' ', ' ',
                'a', 'b', 'c', 'X'};
        State startState = new State(start, 15);
        Node root = new Node(startState,true);
        BreadthFirstSearch bfs = new BreadthFirstSearch();
        bfs.nodeQueue.add(root);
        bfs.search();
    }


}
