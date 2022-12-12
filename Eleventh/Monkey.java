package Eleventh;

import java.util.ArrayList;

public class Monkey {

    private ArrayList<Long> items;
    private int test;
    private int trueIndex;
    private int falseIndex;
    private boolean oldRef;
    private char operator;
    private long ingredient;
    private final int dividor = 1;

    public Monkey(ArrayList<Long> items, int test, int trueIndex,
                  int falseIndex, char operator, long ingredient, boolean oldRef) {
        this.items = items;
        this.test = test;
        this.trueIndex = trueIndex;
        this.falseIndex = falseIndex;
        this.operator = operator;
        this.ingredient = ingredient;
        this.oldRef = oldRef;
    }

    public int throwAll(ArrayList<Monkey> monkeys,long divisorsProduct){

        int noInspections = 0;
        while (!items.isEmpty()){

            if(throwItem(monkeys, divisorsProduct)){
                noInspections++;
            }

        }
        return noInspections;
    }

    public boolean throwItem(ArrayList<Monkey> monkeys, long divisorsProduct){

        if(!items.isEmpty()){

            long newWorryLevel = items.get(0);
            items.remove(0);

            if(oldRef){
                ingredient = newWorryLevel;
            }

            switch (operator){
                case '+':
                    newWorryLevel += ingredient;
                    break;
                case '*':
                    newWorryLevel *= ingredient;
                    break;
                case '-':
                    newWorryLevel -= ingredient;
                case '/':
                    newWorryLevel /= ingredient;
                    break;
                default:
                    newWorryLevel = newWorryLevel;
                    break;
            }
            newWorryLevel /= dividor;
            newWorryLevel %= divisorsProduct;

            if(newWorryLevel%test == 0){
                monkeys.get(trueIndex).addItem(newWorryLevel);
            }
            else{
                monkeys.get(falseIndex).addItem(newWorryLevel);
            }
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Monkey{" +
                "items=" + items +
                ", test=" + test +
                ", trueIndex=" + trueIndex +
                ", falseIndex=" + falseIndex +
                ", oldRef=" + oldRef +
                ", operator=" + operator +
                ", ingredient=" + ingredient +
                ", dividor=" + dividor +
                '}';
    }

    public void addItem(long worryLevel){
        items.add(worryLevel);
    }

    public ArrayList<Long> getItems() {
        return items;
    }

    public void setItems(ArrayList<Long> items) {
        this.items = items;
    }

    public int getTest() {
        return test;
    }

    public void setTest(int test) {
        this.test = test;
    }

    public int getTrueIndex() {
        return trueIndex;
    }

    public void setTrueIndex(int trueIndex) {
        this.trueIndex = trueIndex;
    }

    public int getFalseIndex() {
        return falseIndex;
    }

    public void setFalseIndex(int falseIndex) {
        this.falseIndex = falseIndex;
    }

    public char getOperator() {
        return operator;
    }

    public void setOperator(char operator) {
        this.operator = operator;
    }

    public long getIngredient() {
        return ingredient;
    }

    public void setIngredient(int ingredient) {
        this.ingredient = ingredient;
    }
}
