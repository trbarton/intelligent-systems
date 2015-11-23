import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

/**
 * Created by tombarton on 23/11/2015.
 */
public class IterativeDeepeningSearch {

    private int expandedNodes = 0;

    public void setRoot(Node root) {
        this.root = root;
    }

    private Node root;

    private int maxDepth = 0;
    private boolean solutionFound = false;

    private  char[] goal = {' ', ' ', ' ', ' ',
                            ' ', 'a', ' ', ' ',
                            ' ', 'b', ' ', ' ',
                            ' ', 'c', ' ', 'X'};

    private Node currentNode;

    private Stack<Node> expandedUnvistedNodes = new Stack<>();

    private ArrayList<Node> solution = new ArrayList<>();


    private void clearStack() {
        expandedUnvistedNodes.clear();
    }

    public void search(){
        expandedUnvistedNodes.push(root);
        while(!solutionFound){
            currentNode = expandedUnvistedNodes.pop();
            //currentNode.getPuzzleState().printState();
            //System.out.println("\n");

            if(Arrays.equals(currentNode.getPuzzleState().getPuzzleArray(), goal)){
                //currentNode.getPuzzleState().printState();
                printSolution(currentNode);
                solutionFound = true;
                return;
            }else if (currentNode.getDepth() < maxDepth){
                expandNode(currentNode);
                expandedNodes++;
            }
            if(expandedUnvistedNodes.isEmpty() && !solutionFound){
                System.out.println("Depth: " + maxDepth + " Nodes Expanded: " + expandedNodes);

                maxDepth = maxDepth + 1;
                search();
            }
        }
    }

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
        System.out.println("Nodes Expanded: " + expandedNodes);
        //System.out.println("Nodes Seen: " + nodesInTree);
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
        ids.search();

    }

}
