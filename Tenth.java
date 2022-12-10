import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Tenth {


    static int CTRHight = 6;
    static int CTRWidth = 40;

    public static void printCRTWrong(ArrayList<Integer> registerValues){

        boolean[][] ctr = new boolean[CTRHight][CTRWidth];

        int ptr = -1;
        int numberOfView = 1;

        for (int i = 0; i < registerValues.size(); i++){
            int row = (i - 240 * (numberOfView - 1))/40;
            int prevSpritePos = -1;
            if(i != 0){
                prevSpritePos = registerValues.get(i - 1);
            }
            int currSpritePos = registerValues.get(i);

            if(currSpritePos < 0 || currSpritePos > CTRWidth -1){
                ptr++;
            }
            else {
                if (prevSpritePos != currSpritePos) {
                    ptr = -1;
                }
                ctr[row][currSpritePos + ptr] = true;
                ptr++;
                if (ptr == 2) {
                    ptr = -1;
                }
                if (i == 240 * numberOfView) {
                    numberOfView++;
                    System.out.println();
                    for (int c = 0; c < CTRHight; c++) {
                        for (int j = 0; j < CTRWidth; j++) {
                            if (ctr[c][j])
                                System.out.print('#');
                            else
                                System.out.print('.');
                        }
                        System.out.println();
                    }
                    ctr = new boolean[CTRHight][CTRWidth];
                }
            }
        }
        System.out.println();
        for(int i = 0; i<CTRHight; i++){
            for (int j = 0; j < CTRWidth; j++){
                if(ctr[i][j])
                    System.out.print('#');
                else
                    System.out.print('.');
            }
            System.out.println();
        }

    }

    public static void printCRT(ArrayList<Integer> registerValues){

        StringBuilder sb = new StringBuilder();
        int cursor = 0;

        for (int i = 0; i < registerValues.size(); i++) {
            cursor = i%40;
            if(cursor == 0){
                sb.append('\n');
            }
            int spritePos = registerValues.get(i);
            if(cursor == spritePos - 1 || cursor == spritePos || cursor == spritePos + 1){
                sb.append('#');
            }
            else {
                sb.append('.');
            }
        }
        System.out.println(sb);
    }

    public static int solve(String path, int addxCycles){

        ArrayList<Integer> registerValues = new ArrayList<>();
        int res = 0;

        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;
            int currX = 1;

            while ((line = br.readLine()) != null){

                String[] words = line.split(" ");

                if(words[0].equals("noop")){
                    registerValues.add(currX);
                }
                else if(words[0].equals("addx")){
                    for(int i = 0; i < addxCycles; i++){
                        registerValues.add(currX);
                    }
                    currX += Integer.parseInt(words[1]);
                }
            }
            int n = 0;
            for(int i = 0; i < registerValues.size(); i++){

                if(i == (20 + 40 * n) - 1 && i < 220){
                    res += (i + 1) * registerValues.get(i);
                    n++;
                }
            }



        } catch (IOException e) {
            e.printStackTrace();
        }

        printCRT(registerValues);
        return res;
    }

    public static void main(String[] args) {

        System.out.println(solve("signals.txt",2));

    }
}
