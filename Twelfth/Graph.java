package Twelfth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Graph {

    private ArrayList<Node> nodes;
    private Node curr;
    private Node dest;

    public Graph() {
        this.nodes = new ArrayList<Node>();
    }

    public void addNode(Node node){

        nodes.add(node);

    }

    public ArrayList<Node> shortestPath(Node source, Node destination){

        ArrayList<Node> path = new ArrayList<>();

        if(source != destination){

            HashMap<Node,Node> prev = new HashMap<>();
            HashMap<Node,Integer> dist = new HashMap<>();

            LinkedList<Node> queue = new LinkedList<>();

            prev.put(source, null);
            dist.put(source, 0);
            queue.add(source);

            while (!queue.isEmpty()){

                Node curr = queue.remove();

                for(Node neighbour: curr.getConnections()){
                    if(!dist.containsKey(neighbour) && neighbour.getValue() <= curr.getValue() + 1){
                        dist.put(neighbour, dist.get(curr) + 1);
                        prev.put(neighbour, curr);
                        queue.add(neighbour);
                    }
                }
            }
            Node curr = destination;
            while (true){
                path.add(curr);
                if(curr == source || curr == null) {
                    break;
                }
                curr = prev.get(curr);
            }
        }
        return path;
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    public Node getCurr() {
        return curr;
    }

    public void setCurr(Node curr) {
        this.curr = curr;
    }

    public Node getDest() {
        return dest;
    }

    public void setDest(Node dest) {
        this.dest = dest;
    }


}
