import java.util.*;

/**
 * Created by tombarton on 23/11/2015.
 */
public class AStarSearch {

    private int expandedNodes;

    private Node currentNode;

    private  char[] goal = {' ', ' ', ' ', ' ',
            ' ', 'a', ' ', ' ',
            ' ', 'b', ' ', ' ',
            ' ', 'c', ' ', 'X'};

    private PriorityQueue<Node> nodePriorityQueue = new PriorityQueue<>();

    public void search(){
        while(true){
            currentNode = nodePriorityQueue.remove();
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


    public void expandNode(Node input){
        State inputState = input.getPuzzleState();
        ArrayList<Node> newNodes = new ArrayList<>();
        int depthIncrementer = input.getDepth() + 1;
        if (!inputState.isAtLeftBoundary() && input.getLastMove() != 'R'){
            State left = inputState.generateLeftState();
            Node leftNode = new Node(left, 'L', depthIncrementer);
            newNodes.add(leftNode);
        }
        if (!inputState.isAtRightBoundary() && input.getLastMove() != 'L'){
            State right = inputState.generateRightState();
            Node rightNode = new Node(right, 'R', depthIncrementer);
            newNodes.add(rightNode);
        }
        if (!inputState.isAtTopBoundary() && input.getLastMove() != 'D'){
            State up = inputState.generateUpState();
            Node upNode = new Node(up, 'U', depthIncrementer);
            newNodes.add(upNode);
        }
        if (!inputState.isAtBottomBoundary() && input.getLastMove() != 'U'){
            State down = inputState.generateDownState();
            Node downNode = new Node(down, 'D', depthIncrementer);
            newNodes.add(downNode);
        }
        for (Node current: newNodes) {
            nodePriorityQueue.add(current);
        }
    }
}
