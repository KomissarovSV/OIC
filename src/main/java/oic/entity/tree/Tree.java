package oic.entity.tree;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Tree {

    private List<Node> nodes = new ArrayList<>();

    public void addNode(Node node){
        if (node.getGrnti().getParent() == 0){
            nodes.add(node);
        }else {
            for (Node rootNode : nodes) {
                if (rootNode.addChild(node)){
                    break;
                }
            }
        }
    }
}
