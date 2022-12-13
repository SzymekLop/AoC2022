package Thirteenth;

import java.util.ArrayList;

public class Numbers extends Element{

    private ArrayList<Element> values;

    public Numbers(){
        super();
        values = new ArrayList<>();

    }

    public Numbers(ArrayList<Element> values){
        super();
        this.values = values;

    }

    @Override
    public int compareTo(Element other){

       if(other instanceof Numbers) {

           int tSize = this.getValues().size();
           int oSize = ((Numbers) other).getValues().size();

           int size = Math.min(tSize, oSize);

           for (int i = 0; i < size; i++) {

               int comparation = this.getValues().get(i).compareTo(((Numbers) other).getValues().get(i));
               if (comparation != 0) {
                   return comparation;
               }
           }
           return tSize - oSize;
       }
        else if(other instanceof Number){
            ArrayList<Element> tmpArray = new ArrayList<>();
            tmpArray.add(other);
            Numbers tmp = new Numbers(tmpArray);
            return this.compareTo(tmp);
        }
        else {
            return -1;
        }
    }

    public void addValue(Element elem){
        values.add(elem);
    }

    public ArrayList<Element> getValues() {
        return values;
    }

    public void setValues(ArrayList<Element> values) {
        this.values = values;
    }
}
