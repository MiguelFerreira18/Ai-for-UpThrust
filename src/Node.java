import java.util.ArrayList;
import java.util.Arrays;

public class Node {
    public static final String[][] goal = {{"0", "0", "0", "0"},
            {"1", "2", "3", "4",},
            {"2", "3", "4", "1"},
            {"3", "4", "1", "2"},
            {"4", "1", "2", "1"},
            {"0", "0", "0", "0"},
            {"0", "0", "0", "0"},
            {"0", "0", "0", "0"},
            {"0", "0", "0", "0"},
            {"0", "0", "0", "0"},
            {"0", "0", "0", "0"}};
    private UpthrustGame estado;
    private int depth;
    private Node parent;

    public Node(UpthrustGame estado, Node parent) {
        this.estado = estado;
        this.parent = parent;
        if (getParent() == null) {
            depth = 0;
        } else {
            depth = getParent().getDepth() + 1;
        }
    }

    public UpthrustGame getEstado() {
        return estado;
    }

    public int getDepth() {
        return depth;
    }

    public Node getParent() {
        return parent;
    }

    public ArrayList<Node> suckNode() {
        ArrayList<Node> suck = new ArrayList<Node>();
        ArrayList<UpthrustGame> estados = this.estado.suc();
        for (UpthrustGame unitedStates : estados) {
            suck.add(new Node(unitedStates, this));
        }
        return suck;
    }

    public boolean goal() {
        if (!Arrays.equals(this.getEstado().getMatrizDeJogo(), goal)) {
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        return "Node{" +
                "estado=" + estado +
                ", depth=" + depth +
                ", parent=" + parent +
                '}';
    }
}
