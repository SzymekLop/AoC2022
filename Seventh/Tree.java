package Seventh;

import java.util.ArrayList;

public class Tree {

    private Node root;
    private Node curr;

    public Tree() {
        this.root = new Node(null, "", 0);
        this.curr = root;
    }

    public int totalValue(){
        return root.kids.get(0).value;
    }

    public void addKid(String name, int value) {

        curr.kids.add(new Node(curr, name, value));
        cout();

    }

    public void moveUp() {
        curr = curr.parent;
    }

    public void moveDown(String name) {
        Node next = curr;
        for (Node kid : curr.kids) {
            if (kid.name.equals(name)) {
                next = kid;
                break;
            }
        }
        curr = next;
    }

    public void cout() {
        root.countSubtree();
        root.value = 0;
    }

    public int sumDirIfLessEq(int max) {

        return sum(max, 0, root);

    }

    public int findClosestBigger(int lookForValue){

        return root.kids.get(0).findClosestBiggerSub(lookForValue);
    }

    private int sum(int max, int sum, Node pointer) {
        int res = sum;
        if (pointer.value <= max && !pointer.isLeaf()) {
            res += pointer.value;
        }
        for (Node kid : pointer.kids) {
            res += sum(max, sum, kid);
        }
        return res;

    }

    class Node {

        private String name;
        private int value;
        private Node parent;
        private ArrayList<Node> kids;


        public Node(Node parent, String name, int value) {
            this.name = name;
            this.parent = parent;
            this.value = value;
            this.kids = new ArrayList<>();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public ArrayList<Node> getKids() {
            return kids;
        }

        public void setKids(ArrayList<Node> kids) {
            this.kids = kids;
        }

        public boolean isRoot() {
            return parent == null;
        }

        public boolean isLeaf() {
            return kids.isEmpty();
        }

        public int countSubtree() {

            if (kids.isEmpty()) {
                return value;
            } else {
                int res = 0;
                for (Node kid : kids) {
                    res += kid.countSubtree();
                }
                value = res;
                return res;
            }
        }

        public int findClosestBiggerSub(int lookForValue){
            return checkDiff(lookForValue, value);
        }

        private int checkDiff(int lookForValue, int minSize){

            int size = minSize;
            if(!this.isLeaf()){

                if(this.value >= lookForValue && this.value < minSize){
                    size = value;
                }
                for(Node kid: this.kids){
                    int kidSize = kid.checkDiff(lookForValue, size);
                    if(kidSize < size){
                        size = kidSize;
                    }
                }
            }
            return size;
        }
    }
}
