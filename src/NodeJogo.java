import java.util.ArrayList;

public class NodeJogo {
    int depth;
    UpthrustGame state;
    NodeJogo parentNode;

    public NodeJogo( UpthrustGame state, NodeJogo parentNode) {
        this.depth = 0;
        this.state = state;
        this.parentNode = parentNode;
        if(getParentNode()==null){
            depth =0;
        }else {
            depth = parentNode.getDepth()+1;
        }
    }

    public UpthrustGame getState() {
        return state;
    }

    public void setState(UpthrustGame state) {
        this.state = state;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
    public void setParentNode(NodeJogo parentNode) {
        this.parentNode = parentNode;
    }
    public ArrayList<NodeJogo> suckNode(){
        ArrayList<NodeJogo> suck = new ArrayList<NodeJogo>();
        ArrayList<UpthrustGame> estados= this.state.suc();
        for (UpthrustGame unitedStates:estados){
            suck.add(new NodeJogo(unitedStates,this));
        }
        return suck;
    }
}
