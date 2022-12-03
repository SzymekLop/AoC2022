import java.io.*;

public class Secound {

// rock - A - X - 1
// paper - B - Y - 2
// scissors - C - Z - 3

    public static int battlePoints(char enemyMove, char move) {

        int points = 0;
        if (move == 'X') {
            points = 1;
            if (enemyMove == 'A') {
                points += 3;
            } else if (enemyMove == 'C') {
                points += 6;
            }
        }
        if (move == 'Y') {
            points = 2;
            if (enemyMove == 'A') {
                points += 6;
            } else if (enemyMove == 'B') {
                points += 3;
            }
        }
        if (move == 'Z') {
            points = 3;
            if (enemyMove == 'B') {
                points += 6;
            } else if (enemyMove == 'C') {
                points += 3;
            }
        }
        return points;
    }

    public static int resultPoints(char enemyMove, char result) {

        int points = 0;
        if (result == 'X') {
            points = 0;
            if (enemyMove == 'A') {
                points += 3;
            }
            else if (enemyMove == 'B') {
                points += 1;
            }
            else if (enemyMove == 'C') {
                points += 2;
            }
        }
        if (result == 'Y') {
            points = 3;
            if (enemyMove == 'A') {
                points += 1;
            }
            else if (enemyMove == 'B') {
                points += 2;
            }
            else if (enemyMove == 'C') {
                points += 3;
            }
        }
        if (result == 'Z') {
            points = 6;
            if (enemyMove == 'B') {
                points += 3;
            }
            else if (enemyMove == 'C') {
                points += 1;
            }
            else if (enemyMove == 'A') {
                points += 2;
            }
        }
        return points;
    }

    public static int solveMove(String path){

        int sum = 0;
        File file = new File(path);
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            char[] letters;
            while ((line = br.readLine()) != null){
                letters = line.toCharArray();
                sum += battlePoints(letters[0], letters[2]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sum;
    }

    public static int solveResult(String path){

        int sum = 0;
        File file = new File(path);
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            char[] letters;
            while ((line = br.readLine()) != null){
                letters = line.toCharArray();
                sum += resultPoints(letters[0], letters[2]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sum;
    }

    public static void main(String[] args) {

        System.out.println(solveResult("strategy.txt"));

    }
}
