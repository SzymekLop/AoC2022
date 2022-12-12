package Twelfth;

import java.util.ArrayList;

public class Node {

    static int count = 0;
    private ArrayList<Node> connections;
    private int value;
    private int id;

    public Node(ArrayList<Node> connections, int value) {
        this.connections = connections;
        this.value = value;
        count++;
        this.id= count;
    }

    public void addConnection(Node neighbor){
        if(!connections.contains(neighbor)){
            connections.add(neighbor);
            if(!neighbor.connections.contains(this)){
                neighbor.connections.add(this);
            }
        }
    }

    public ArrayList<Node> getConnections() {
        return connections;
    }

    public void setConnections(ArrayList<Node> connections) {
        this.connections = connections;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
