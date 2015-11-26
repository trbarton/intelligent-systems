/**
 * Created by tombarton on 24/11/2015.
 */
public class Test {

    DepthFirstSearch dfs;
    BreadthFirstSearch bfs;
    IterativeDeepeningSearch ids;
    AStarSearch as;

    private char[] start;
    State startState;
    int agentPos = 15;

    Node startNode;

    public void setStart(char[] start) {
        this.start = start;
    }

    public void setAgentPos(int agentPos) {
        this.agentPos = agentPos;
    }

    public void setStartNode(Node startNode) {
        this.startNode = startNode;
    }

    public Test(char[] start, int agentIndex){
        setStart(start);
        setAgentPos(agentIndex);
        startState = new State(this.start, agentPos);
        setStartState(startState);
        setStartNode(new Node(startState, true));
    }

    public void setStartState(State startState) {
        this.startState = startState;
    }

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
        //Make sure the agent index position is correct or the puzzle will not solve
        char[] startArray = {' ', ' ', ' ', ' ',
                             ' ', ' ', ' ', ' ',
                             ' ', ' ', ' ', ' ',
                             'a', 'b', 'c', 'X'};
        int agentIndexPosition = 15;

        Test tester = new Test(startArray,agentIndexPosition);
        tester.setStart(startArray);
        tester.testDFS();
        tester.testIDS();
        tester.testAS();
        tester.testBFS();
    }
}
