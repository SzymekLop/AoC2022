package Eleventh;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

public class Eleventh {


    public static BigInteger solve(String path, int rounds){

        BigInteger res = BigInteger.valueOf(1);

        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;

            ArrayList<Monkey> monkeys = new ArrayList<>();
            ArrayList<Integer> inspections = new ArrayList<>();
            long dividorsProduct = 1;

            ArrayList<Long> items = new ArrayList<>();
            char operator = 'a';
            int ingredient = 0;
            int trueIndex = -1;
            int falseIndex = -1;
            boolean oldRef = false;
            int test = 0;

            while ((line = br.readLine()) != null) {

                if(line.startsWith("  S")){
                    items = new ArrayList<>();
                    line = line.substring(18);
                    for(String s: line.split(", ")){

                        items.add(Long.parseLong(s));
                    }
                }
                else if(line.startsWith("  O")){
                    operator = line.charAt(23);
                    if(line.substring(25).startsWith("old")){
                        oldRef = true;
                    }
                    else{
                        oldRef = false;
                        ingredient = Integer.parseInt(line.substring(25));
                    }
                }
                else if(line.startsWith("  T")){
                    test = Integer.parseInt(line.substring(21));
                }
                else if(line.startsWith("    If t")){
                    trueIndex = Integer.parseInt(line.substring(29));
                }
                else if(line.startsWith("    If f")){
                    falseIndex = Integer.parseInt(line.substring(30));
                }
                else if(line.equals("")){
                    monkeys.add(new Monkey(items, test, trueIndex, falseIndex, operator, ingredient, oldRef));
                    inspections.add(0);
                    dividorsProduct *= test;
                }

            }
            monkeys.add(new Monkey(items, test, trueIndex, falseIndex, operator, ingredient, oldRef));
            inspections.add(0);
            dividorsProduct *= test;

            for(int i = 0; i < rounds; i++){
                for(int j = 0; j < monkeys.size(); j++){
                    Monkey monkey = monkeys.get(j);
                    inspections.set(j, inspections.get(j)+monkey.throwAll(monkeys, dividorsProduct));

                }
            }

            int biggest = 0;
            int next = 0;

            for(Integer noInspections: inspections){
                if(noInspections > biggest){
                    next = biggest;
                    biggest = noInspections;
                }
                else if(noInspections > next){
                    next = noInspections;
                }
            }

            res = BigInteger.valueOf(biggest);
            res = res.multiply(BigInteger.valueOf(next));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }

    public static void main(String[] args) {

        System.out.println(solve("monkeys.txt", 10000));

    }
}
