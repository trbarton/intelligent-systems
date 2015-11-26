import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

/**
 * Created by tombarton on 23/11/2015.
 */
public class IterativeDeepeningSearch {

    private int expandedNodes = 0;
    private Node root;
    private int maxDepth = 0;
    private boolean solutionFound = false;

    private  char[] goal = {' ', ' ', ' ', ' ',
                            ' ', 'a', ' ', ' ',
                            ' ', 'b', ' ', ' ',
                            ' ', 'c', ' ', 'X'};

    private Node currentNode;
    private Stack<Node> nodeStack = new Stack<>();
    private ArrayList<Node> solution = new ArrayList<>();

    public void setRoot(Node root) {
        this.root = root;
    }


    //IDS uses the same underlying functionality of DFS. Except IDS is limited by a maximum depth.
    //Search starts with depth 0 and conducts DFS. If a solution is found then the solution is printed out
    //else the node is expanded. If the stack becomes empty and a solution isn't found then search starts again
    //with a maximum depth of one greater.
    public void search(){
        nodeStack.push(root);
        while(!solutionFound){
            currentNode = nodeStack.pop();
            if(Arrays.equals(currentNode.getPuzzleState().getPuzzleArray(), goal)){
                printSolution(currentNode);
                solutionFound = true;
                return;
            }else if (currentNode.getDepth() < maxDepth){
                expandNode(currentNode);
                expandedNodes++;
            }
            if(nodeStack.isEmpty() && !solutionFound){
                //System.out.println("Depth: " + maxDepth + " Nodes Expanded: " + expandedNodes);
                maxDepth = maxDepth + 1;
                search();
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
            nodeStack.push(current);
        }
    }
}
