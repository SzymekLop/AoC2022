package Thirteenth;

import java.util.ArrayList;

public class Number extends Element{

    private int value;

    public Number(int value){
        super();
        this.value = value;

    }

    @Override
    public int compareTo(Element other){

        if(other instanceof Number)
            return this.getValue() - ((Number) other).getValue();

        else if(other instanceof Numbers){
            ArrayList<Element> tmpArray = new ArrayList<>();
            tmpArray.add(this);
            Numbers tmp = new Numbers(tmpArray);
            return tmp.compareTo(other);
        }
        else {
            return -1;
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
