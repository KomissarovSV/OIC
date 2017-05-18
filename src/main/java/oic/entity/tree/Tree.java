package oic.entity.tree;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Tree {

    private List<Node> data = new ArrayList<>();
    private Boolean showBorder = false;
    private Integer levels = 1;

    public void addNode(Node node){
        if (node.getGrnti().getParent() == 0){
            data.add(node);
        }else {
            for (Node rootNode : data) {
                if (rootNode.addChild(node)){
                    break;
                }
            }
        }
    }
}
