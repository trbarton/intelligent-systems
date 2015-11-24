/**
 * Created by tombarton on 24/11/2015.
 */
public class Test {

    public static void main(String[] args) {
        char[] start = {' ', ' ', ' ', ' ',
                        ' ', ' ', ' ', ' ',
                        ' ', ' ', ' ', ' ',
                        'a', 'b', 'c', 'X'};

        char[] goal = {' ', ' ', ' ', ' ',
                       ' ', 'a', ' ', ' ',
                       ' ', 'b', ' ', ' ',
                       ' ', 'c', ' ', 'X'};

        State startState = new State(start, 15);
        State goalState = new State(goal, 15);
        Node testNode = new Node(startState, true);
        Node goalNode = new Node(goalState,16);

        testNode.calculateWeight();


    }
}
