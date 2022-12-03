import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Third {

    public static char commonLetter(String s1, String s2){

        Set<Character> set1 = new HashSet<>();
        Set<Character> set2 = new HashSet<>();

        char[] array1 = s1.toCharArray();
        char[] array2 = s2.toCharArray();

        for(int i = 0; i < s1.length(); i++){
            set1.add(array1[i]);
            set2.add(array2[i]);
        }

        set1.retainAll(set2);

        char res = (char) set1.toArray()[0];
        return res;
    }

    public static char commonLetterOfThree(String s1, String s2, String s3){

        Set<Character> set1 = new HashSet<>();
        Set<Character> set2 = new HashSet<>();
        Set<Character> set3 = new HashSet<>();

        for(char letter : s1.toCharArray()){
            set1.add(letter);
        }
        for(char letter : s2.toCharArray()){
            set2.add(letter);
        }
        for(char letter : s3.toCharArray()){
            set3.add(letter);
        }
        set1.retainAll(set2);
        set1.retainAll(set3);

        char res = (char) set1.toArray()[0];
        return res;
    }

    public static int solve1(String path){

        int sum = 0;
        File file = new File(path);
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;

            while ((line = br.readLine()) != null){
                int length = line.length();
                String s1 = line.substring(0,length/2);
                String s2 = line.substring(length/2);
                char common = commonLetter(s1, s2);
                if(common < 97){
                    sum += common + 27 - 'A';
                }
                else{
                    sum += 1 + common - 'a';
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sum;

    }

    public static int solve2(String path){

        int sum = 0;
        File file = new File(path);
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line1;
            String line2;
            String line3;

            while (true){
                line1 = br.readLine();
                line2 = br.readLine();
                line3 = br.readLine();
                if(line1 == null){
                    break;
                }
                char common = commonLetterOfThree(line1, line2, line3);
                if(common < 97){
                    sum += common + 27 - 'A';
                }
                else{
                    sum += 1 + common - 'a';
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sum;

    }

    public static void main(String[] args){
        System.out.println(solve2("rucksacks.txt"));
    }
}
