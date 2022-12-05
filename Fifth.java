import java.io.*;
import java.util.ArrayList;

public class Fifth {

    public static ArrayList<ArrayList<Character>> crates(String path){

        File file = new File(path);
        ArrayList<ArrayList<Character>> crates = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            int maxSize = 0;
            ArrayList<ArrayList<Character>> tmp = new ArrayList<>();
            while ((line = br.readLine()) != null){
                if(line.startsWith("[")){
                    ArrayList<Character> vertical = new ArrayList<>();
                    String[] parts = line.split("(?<=\\G.{4})");
                    if(parts.length > maxSize){
                        maxSize = parts.length;
                    }
                    for(String part : parts){
                        vertical.add(part.toCharArray()[1]);
                    }
                    tmp.add(vertical);
                }
                else{
                    break;
                }
            }
            for(ArrayList<Character> vert : tmp){
                int size = vert.size();
                if(size < maxSize){
                    for(int i = 0; i < maxSize - size; i++){
                        vert.add(' ');
                    }
                }
            }

            for(int i = 0; i < maxSize; i++){
                ArrayList<Character> horizontal = new ArrayList<>();
                for(int j = tmp.size(); j > 0; j--){
                    Character cTmp = tmp.get(j-1).get(i);
                    if(cTmp != ' ') {
                        horizontal.add(cTmp);
                    }
                }
                crates.add(horizontal);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return crates;
    }

    public static ArrayList<ArrayList<Character>> moveCratesOneByOne
            (int howMany, int from, int to, ArrayList<ArrayList<Character>> crates){

        ArrayList<Character> columnFrom = crates.get(from - 1);
        ArrayList<Character> columnTo = crates.get(to - 1);
        for(int i = 0; i < howMany; i++){
            columnTo.add(columnFrom.remove(columnFrom.size() - 1));
        }

        return crates;
    }

    public static ArrayList<ArrayList<Character>> moveCrates
            (int howMany, int from, int to, ArrayList<ArrayList<Character>> crates){

        ArrayList<Character> columnFrom = crates.get(from - 1);
        ArrayList<Character> columnTo = crates.get(to - 1);
        ArrayList<Character> buffor = new ArrayList<>();
        for(int i = howMany; i > 0; i--){
            buffor.add(columnFrom.remove(columnFrom.size() - i));
        }
        columnTo.addAll(buffor);
        return crates;
    }

    public static String solve1(String path){

        File file = new File(path);
        StringBuilder sb = new StringBuilder();

        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            ArrayList<ArrayList<Character>> crates = crates(path);
            boolean skip = false;
            while ((line = br.readLine()) != null){
                if(skip){
                    String[] words = line.split(" ");
                    crates = moveCratesOneByOne
                            (Integer.parseInt(words[1]),Integer.parseInt(words[3]),Integer.parseInt(words[5]), crates);
                }
                if(line.equals("")){
                    skip = true;
                }
            }
            for(ArrayList<Character> horizontal : crates)
                sb.append(horizontal.get(horizontal.size() - 1));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    public static String solve2(String path){

        File file = new File(path);
        StringBuilder sb = new StringBuilder();

        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            ArrayList<ArrayList<Character>> crates = crates(path);
            boolean skip = false;
            while ((line = br.readLine()) != null){
                if(skip){
                    String[] words = line.split(" ");
                    crates = moveCrates
                            (Integer.parseInt(words[1]),Integer.parseInt(words[3]),Integer.parseInt(words[5]), crates);
                }
                if(line.equals("")){
                    skip = true;
                }
            }
            for(ArrayList<Character> horizontal : crates)
                sb.append(horizontal.get(horizontal.size() - 1));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    public static void main(String[] args) {

        System.out.println(solve1("crates.txt"));
        System.out.println(solve2("crates.txt"));

    }
}
