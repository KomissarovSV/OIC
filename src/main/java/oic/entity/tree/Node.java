package oic.entity.tree;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Node {
    private GRNTI grnti;
    private String text;
    private List<Node> nodes;
    private State state;
    private Boolean lazyLoad;

    public Node(GRNTI grnti){
        this.grnti = grnti;
        this.text = grnti.getShifr() + " " + grnti.getName();
        this.nodes = new ArrayList<>();
        this.state = new State();
        this.lazyLoad = true;
    }

    public boolean addChild(Node child){
        if (grnti.getId().equals(child.getGrnti().getParent())){
            nodes.add(child);
            return true;
        }else {
            for (Node node : nodes) {
                if (node.addChild(child)){
                    return true;
                }
            }
        }
        return false;
    }
}
