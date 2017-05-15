package oic.entity.tree;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Node {
    private GRNTI grnti;
    private String text;
    private List<Node> nodes = new ArrayList<>();
    private State state = new State();
    private Boolean lazyLoad = false;

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
