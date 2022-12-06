import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class sixth {

    public static int firstUniqueSubstring(String s, int length){

        HashMap<Character, Integer> seen = new HashMap<>();

        int start = 0;
        for(int i = 0; i < s.length(); i++){

            if(seen.containsKey(s.charAt(i))){
                start = Math.max(start, seen.get(s.charAt(i)) + 1);
            }
            seen.put(s.charAt(i),i);
            if(i-start + 1 == length){
                return i + 1;
            }
        }

        return -1;
    }

    public static int solve1(String path){

        int res = -1;
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String line = br.readLine();
            res = firstUniqueSubstring(line, 14);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(solve1("signal.txt"));
    }
}
