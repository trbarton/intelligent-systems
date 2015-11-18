import java.util.*;

/**
 * Created by tombarton on 16/11/2015.
 */
public class BreadthFirstSearch {

    private int nodesVisited;
    private int nodesInTree;

    private Node currentNode;

    private  char[] goal = {' ', 'a', ' ', ' ',
            ' ', 'b', ' ', ' ',
            ' ', 'c', ' ', ' ',
            ' ', 'X', ' ', ' '};

    private LinkedList<Node> nodeQueue = new LinkedList<>();

    public void search(){
        while(true){
            currentNode = nodeQueue.remove();
            if(Arrays.equals(currentNode.getPuzzleState().getPuzzleArray(), goal)){
                currentNode.getPuzzleState().printState();
                System.out.println("Node Depth: " + currentNode.getDepth() + " Nodes Visited: " + nodesInTree);
                return;
            }else{
                expandNode(currentNode);
                nodesVisited++;

            }
            /*
            if(nodesVisited % 100000 == 0){
                System.out.println("Nodes Visited: " + nodesVisited);
            }
            */
        }
    }

    /*
    public void expandNode(Node input){
        State inputState = input.getPuzzleState();
        ArrayList<Node> newNodes = new ArrayList<>();
        if (!inputState.isAtLeftBoundary() && input.getLastMove() != 'R'){
            State left = inputState.generateLeftState();
            Node leftNode = new Node(input,left, 'L');
            newNodes.add(leftNode);
            nodesInTree++;
        }
        if (!inputState.isAtRightBoundary() && input.getLastMove() != 'L'){
            State right = inputState.generateRightState();
            Node rightNode = new Node(input, right, 'R');
            newNodes.add(rightNode);
            nodesInTree++;
        }
        if (!inputState.isAtTopBoundary() && input.getLastMove() != 'D'){
            State up = inputState.generateUpState();
            Node upNode = new Node(input, up, 'U');
            newNodes.add(upNode);
            nodesInTree++;
        }
        if (!inputState.isAtBottomBoundary() && input.getLastMove() != 'U'){
            State down = inputState.generateDownState();
            Node downNode = new Node(input, down, 'D');
            newNodes.add(downNode);
            nodesInTree++;
        }
        //Collections.shuffle(newNodes);
        for (Node current: newNodes) {
            nodeQueue.add(current);
        }
    }
    */

    public void expandNode(Node input){
        State inputState = input.getPuzzleState();
        ArrayList<Node> newNodes = new ArrayList<>();
        int depthIncrementer = input.getDepth() + 1;
        if (!inputState.isAtLeftBoundary() && input.getLastMove() != 'R'){
            State left = inputState.generateLeftState();
            Node leftNode = new Node(left, 'L', depthIncrementer);
            newNodes.add(leftNode);
            nodesInTree++;
        }
        if (!inputState.isAtRightBoundary() && input.getLastMove() != 'L'){
            State right = inputState.generateRightState();
            Node rightNode = new Node(right, 'R', depthIncrementer);
            newNodes.add(rightNode);
            nodesInTree++;
        }
        if (!inputState.isAtTopBoundary() && input.getLastMove() != 'D'){
            State up = inputState.generateUpState();
            Node upNode = new Node(up, 'U', depthIncrementer);
            newNodes.add(upNode);
            nodesInTree++;
        }
        if (!inputState.isAtBottomBoundary() && input.getLastMove() != 'U'){
            State down = inputState.generateDownState();
            Node downNode = new Node(down, 'D', depthIncrementer);
            newNodes.add(downNode);
            nodesInTree++;
        }
        //Collections.shuffle(newNodes);
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

    //13245797

}
