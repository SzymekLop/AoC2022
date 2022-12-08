import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Eighth {

    public static int coutHidden(ArrayList<ArrayList<Integer>> forest){

        int hiddenCount = 0;

        for(int i = 1; i< forest.size()-1; i++){
            for(int j = 1; j < forest.get(0).size(); j++){
                boolean isHiddenLeft = false;
                boolean isHiddenRight = false;
                boolean isHiddenTop = false;
                boolean isHiddenDown = false;

                Integer curr = forest.get(i).get(j);

                for(int l = j-1; l >= 0; l--){
                    if(forest.get(i).get(l) >= curr){
                        isHiddenLeft = true;
                        break;
                    }
                }
                for(int r = j+1; r < forest.get(i).size(); r++){
                    if(forest.get(i).get(r) >= curr){
                        isHiddenRight = true;
                        break;
                    }
                }
                for(int d = i-1; d >= 0; d--){
                    if(forest.get(d).get(j) >= curr){
                        isHiddenDown = true;
                        break;
                    }
                }
                for(int t = i+1; t < forest.size(); t++){
                    if(forest.get(t).get(j) >= curr){
                        isHiddenTop = true;
                        break;
                    }
                }

                if(isHiddenLeft && isHiddenRight && isHiddenDown && isHiddenTop){
                    hiddenCount++;
                }
            }
        }

        return hiddenCount;
    }

    public static int maxTreeScore(ArrayList<ArrayList<Integer>> forest){

        int maxTreeScore = 0;

        for(int i = 1; i< forest.size()-1; i++){
            for(int j = 1; j < forest.get(0).size(); j++){
                int leftView = 0;
                int rightView = 0;
                int downView = 0;
                int topView = 0;
                Integer curr = forest.get(i).get(j);

                for(int l = j-1; l >= 0; l--){
                    leftView++;
                    if(forest.get(i).get(l) >= curr){
                        break;
                    }
                }
                for(int r = j+1; r < forest.get(i).size(); r++){
                    rightView++;
                    if(forest.get(i).get(r) >= curr){
                        break;
                    }
                }
                for(int d = i-1; d >= 0; d--){
                    downView++;
                    if(forest.get(d).get(j) >= curr){
                        break;
                    }
                }
                for(int t = i+1; t < forest.size(); t++){
                    topView++;
                    if(forest.get(t).get(j) >= curr){
                        break;
                    }
                }

                int currScore = leftView * rightView * topView * downView;

                if(currScore > maxTreeScore){
                    maxTreeScore = currScore;
                }
            }
        }

        return maxTreeScore;
    }

    public static int solve1(String path){

        int treesCount = 0;
        ArrayList<ArrayList<Integer>> forest = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;
            while ((line = br.readLine()) != null){
                ArrayList<Integer> row = new ArrayList<>();
                for(char number: line.toCharArray()){
                    row.add(number - 48);
                    treesCount++;
                }
                forest.add(row);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return treesCount - coutHidden(forest);
    }

    public static int solve2(String path){

        ArrayList<ArrayList<Integer>> forest = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;
            while ((line = br.readLine()) != null){
                ArrayList<Integer> row = new ArrayList<>();
                for(char number: line.toCharArray()){
                    row.add(number - 48);
                }
                forest.add(row);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return maxTreeScore(forest);
    }

    public static void main(String[] args) {

        System.out.println(solve1("forest.txt"));
        System.out.println(solve2("forest.txt"));


    }
}
