import java.util.ArrayList;

/**
 * Created by Tom on 15/11/2015.
 */
public class State {

    private char[] puzzleArray;
    private int noOfElements;
    private int puzzleSize;

    private int agentIndex;

    private boolean atLeftBoundary = false;
    private boolean atRightBoundary = false;
    private boolean atTopBoundary = false;
    private boolean atBottomBoundary = false;

    private ArrayList<Integer> leftBoundary;
    private ArrayList<Integer> rightBoundary;

    public State(char[] puzzle, int agentIndex){
        puzzleArray = puzzle;
        this.agentIndex = agentIndex;
        puzzleSize();
        calculateLRBoundaries();
        atBoundaries();
    }

    public State(char[] puzzle, int agentIndex, ArrayList<Integer> left, ArrayList<Integer> right){
        puzzleArray = puzzle;
        this.agentIndex = agentIndex;
        puzzleSize();
        leftBoundary = left;
        rightBoundary = right;
        atBoundaries();
    }

    public int getNoOfElements() {
        return noOfElements;
    }

    public int getPuzzleSize() {
        return puzzleSize;
    }

    public void puzzleSize(){
        noOfElements = puzzleArray.length;
        puzzleSize = (int) Math.sqrt(noOfElements);
    }

    public void calculateLRBoundaries(){
        leftBoundary = new ArrayList<>();
        rightBoundary = new ArrayList<>();
        for(int i=0; i<puzzleSize; i++){
            leftBoundary.add(i*puzzleSize);
            rightBoundary.add((puzzleSize*(i+1))-1);
        }
    }

    public void atBoundaries(){
        if(agentIndex < puzzleSize){
            atTopBoundary = true;
        }else if (agentIndex >= (puzzleSize * (puzzleSize-1))){
            atBottomBoundary = true;
        }
        if(leftBoundary.contains(agentIndex)){
            atLeftBoundary = true;
        }else if (rightBoundary.contains(agentIndex)){
            atRightBoundary = true;
        }

    }

    public char[] getPuzzleArray() {
        return puzzleArray;
    }

    public boolean isAtLeftBoundary() {
        return atLeftBoundary;
    }

    public boolean isAtRightBoundary() {
        return atRightBoundary;
    }

    public boolean isAtTopBoundary() {
        return atTopBoundary;
    }

    public boolean isAtBottomBoundary() {
        return atBottomBoundary;
    }

    public State generateLeftState(){
        char[] leftState = puzzleArray.clone();
        leftState[agentIndex] = leftState[agentIndex-1];
        leftState[agentIndex-1] = 'X';
        return new State(leftState,agentIndex-1,leftBoundary,rightBoundary);
    }

    public State generateRightState(){
        char[] rightState = puzzleArray.clone();
        rightState[agentIndex] = rightState[agentIndex+1];
        rightState[agentIndex+1] = 'X';
        return new State(rightState,agentIndex+1,leftBoundary,rightBoundary);
    }

    public State generateUpState(){
        char[] upState = puzzleArray.clone();
        upState[agentIndex] = upState[agentIndex-puzzleSize];
        upState[agentIndex-puzzleSize] = 'X';
        return new State(upState,agentIndex-puzzleSize,leftBoundary,rightBoundary);
    }

    public State generateDownState(){
        char[] downState = puzzleArray.clone();
        downState[agentIndex] = downState[agentIndex+puzzleSize];
        downState[agentIndex+puzzleSize] = 'X';
        return new State(downState,agentIndex+puzzleSize,leftBoundary,rightBoundary);
    }

    public void printState(){
        for(int i=0; i<puzzleArray.length; i++){
            if(i % 4 == 0){
                System.out.print("\n");
            }
            System.out.print("'" + puzzleArray[i] + "'" + " ");

        }
    }
}
