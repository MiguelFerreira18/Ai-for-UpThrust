import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static final String[][] startGame = {{"0", "0", "0", "0"},
            {"0", "0", "0", "0",},
            {"0", "0", "0", "0"},
            {"0", "0", "0", "0"},
            {"0", "0", "0", "0"},
            {"0", "0", "0", "0"},
            {"0", "0", "0", "0"},
            {"1", "2", "3", "4"},
            {"2", "3", "4", "1"},
            {"3", "4", "1", "2"},
            {"4", "1", "2", "1"}};
    public static Node BFS(Node cabeca) {
        ArrayList<Node> lista = new ArrayList<Node>();
        lista.add(cabeca);
        while (lista.get(0).goal()) {
            lista.addAll(lista.get(0).suckNode());
            System.out.println("depth = "+ lista.get(0).getDepth() );
            lista.remove(0);
        }
        return lista.get(0);
    }
    public static void main(String[] args) {
        UpthrustGame newGame = new UpthrustGame(startGame);
        Node startingNode = new Node(newGame,null);
        Node finalNode = BFS(startingNode);
        System.out.println(finalNode.getDepth());
    }
}