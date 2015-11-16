/**
 * Created by Tom on 15/11/2015.
 */
public class Test {

    public static void main(String[] args) {
        char[] startPuzzle = {' ', ' ', ' ',
                              ' ', ' ', ' ',
                              'a', 'b', 'X'};
        State startState = new State(startPuzzle, (startPuzzle.length-1));
        Node rootNode = new Node(startState, true);
    }
}
