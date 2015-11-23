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
            currentNode = nodePriorityQueue.poll();
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
            nodePriorityQueue.add(current);
        }
    }
}
