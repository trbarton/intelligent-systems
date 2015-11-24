/**
 * Created by tombarton on 24/11/2015.
 */
public class Test {

    DepthFirstSearch dfs;
    BreadthFirstSearch bfs;
    IterativeDeepeningSearch ids;
    AStarSearch as;

    char[] start = {' ', ' ', ' ', ' ',
            ' ', 'a', ' ', ' ',
            ' ', 'b', ' ', ' ',
            ' ', 'c', 'X', ' '};
    State startState = new State(start, 14);
    Node startNode = new Node(startState, true);

    public void testDFS(){
        System.out.println("TESTING DFS");
        dfs = new DepthFirstSearch();
        dfs.setRoot(startNode);
        dfs.search();
    }

    public void testBFS(){
        System.out.println("TESTING BFS");
        bfs = new BreadthFirstSearch();
        bfs.setRoot(startNode);
        bfs.search();
    }

    public void testIDS(){
        System.out.println("TESTING IDS");
        ids = new IterativeDeepeningSearch();
        ids.setRoot(startNode);
        ids.search();
    }

    public void testAS(){
        System.out.println("TESTING A*S");
        as = new AStarSearch();
        as.setRoot(startNode);
        as.search();
    }

    public static void main(String[] args) {
        Test tester = new Test();
        tester.testDFS();
        tester.testDFS();
        tester.testDFS();
        tester.testIDS();
        tester.testAS();
    }
}
