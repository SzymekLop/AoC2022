package Thirteenth;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Thirteenth {

    public static Element makeNumbers(LinkedList<Character> letters){

        Numbers create = new Numbers();

        int tmp = 0;
        boolean digit = false;
        boolean add = true;

        while (!letters.isEmpty()){

            char curr = letters.remove();
            if(curr == '['){
                add= false;
                create.addValue(makeNumbers(letters));
            }
            else if(curr == ']'){
                if(add)
                    create.addValue(new Number(tmp));

                return create;
            }
            else if(curr == ','){
                if(add) {
                    create.addValue(new Number(tmp));
                    tmp = 0;
                    digit = false;
                }
            }
            else {
                add = true;
                digit = true;
                tmp *= 10;
                tmp += curr - 48;
            }
        }
        return create;
    }

    public static int solve(String path){

        ArrayList<Boolean> isPairCorrect = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;

            ArrayList<Element> signals = new ArrayList<>();

            while ((line = br.readLine()) != null) {

                if(!line.equals("")) {
                    LinkedList<Character> letters = new LinkedList<>();
                    for(Character letter : line.toCharArray()){
                        letters.add(letter);
                    }
                    signals.add(makeNumbers(letters));
                }

            }

            for(int i = 0; i < signals.size(); i += 2){

                if(signals.get(i).compareTo(signals.get(i + 1)) < 0)
                    isPairCorrect.add(true);

                else
                    isPairCorrect.add(false);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int sum = 0;

        for(int i = 0; i < isPairCorrect.size(); i++){

            if(isPairCorrect.get(i))
                sum += i + 1;
        }

        return sum;
    }

    public static int solve2(String path){

        Numbers find1 = new Numbers();
        Numbers tmp = new Numbers();
        tmp.addValue(new Number(2));
        find1.addValue(tmp);

        Numbers find2 = new Numbers();
        tmp = new Numbers();
        tmp.addValue(new Number(6));
        find2.addValue(tmp);

        ArrayList<Element> signals = new ArrayList<>();
        signals.add(find1);
        signals.add(find2);

        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;


            while ((line = br.readLine()) != null) {

                if(!line.equals("")) {
                    LinkedList<Character> letters = new LinkedList<>();
                    for(Character letter : line.toCharArray()){
                        letters.add(letter);
                    }
                    signals.add(makeNumbers(letters));
                }

            }

            signals.sort(null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int index1 = 0;
        int index2 = 0;

        for(int i = 0; i < signals.size(); i++){

            if(signals.get(i) == find1){
                index1 = i + 1;
            }
            else if(signals.get(i) == find2){
                index2 = i + 1;
            }
        }

        return index1 * index2;
    }

    public static void main(String[] args) {

        System.out.println(solve("distressSignal.txt"));
        System.out.println(solve2("distressSignal.txt"));
    }
}
