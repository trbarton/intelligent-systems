import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

/**
 * Created by tombarton on 23/11/2015.
 */
public class IterativeDeepeningSearch {

    private int nodesVisited = 1;
    private int nodesInTree = 1;

    public void setRoot(Node root) {
        this.root = root;
    }

    private Node root;

    private int maxDepth = 0;
    private boolean solutionFound = false;

    private  char[] goal = {' ', 'a', ' ', ' ',
            ' ', 'b', ' ', ' ',
            ' ', 'c', ' ', ' ',
            ' ', 'X', ' ', ' '};

    private Node currentNode;

    private Stack<Node> expandedUnvistedNodes = new Stack<>();

    private ArrayList<Node> solution = new ArrayList<>();


    private void clearStack() {
        expandedUnvistedNodes.clear();
    }

    public void search(){
        expandedUnvistedNodes.push(root);
        while(true){
            currentNode = expandedUnvistedNodes.pop();
            nodesVisited++;
            if(Arrays.equals(currentNode.getPuzzleState().getPuzzleArray(), goal)){
                currentNode.getPuzzleState().printState();
                printSolution(currentNode);
                solutionFound = true;
                return;
            }else if (currentNode.getDepth() < maxDepth){
                expandNode(currentNode);
            }
            if(expandedUnvistedNodes.isEmpty() && !solutionFound){
                maxDepth = maxDepth + 1;
                search();
            }
        }
    }

    public void printSolution(Node goalNode){
        Node current = goalNode;
        while(!current.getParent().isRoot()){
            solution.add(current);
            current = current.getParent();
        }
        Collections.reverse(solution);
        for(Node n : solution){
            n.getPuzzleState().printState();
            System.out.println("\n");
        }
        System.out.println("Nodes Visited: " + nodesVisited);
        System.out.println("Node Depth: " + goalNode.getDepth());
    }

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
            expandedUnvistedNodes.push(current);
        }
    }

    public static void main(String[] args) {
        char[] start = {' ', ' ', ' ', ' ',
                ' ', ' ', ' ', ' ',
                ' ', ' ', ' ', ' ',
                'a', 'b', 'c', 'X'};
        State startState = new State(start, 15);
        IterativeDeepeningSearch ids = new IterativeDeepeningSearch();
        ids.setRoot(new Node(startState,true));
        //ids.expandedUnvistedNodes.push(root);
        ids.search();

    }

}
