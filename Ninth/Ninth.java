package Ninth;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Ninth {

    public static Position followStep(int xWho, int yWho, int xBy, int yBy){

        int vertDiff = yWho - yBy;
        int horDiff = xWho - xBy;
        int diff = Math.abs(vertDiff) + Math.abs(horDiff);

        int x = xBy;
        int y = yBy;

        if(diff > 2){
            if(vertDiff < 0)
                y--;
            else
                y++;
            if(horDiff < 0)
                x--;
            else
                x++;
        }
        else if (diff == 2){
            if(horDiff == 0){
                if(vertDiff<0)
                    y--;
                else
                    y++;
            }
            else if(vertDiff == 0){
                if(horDiff < 0)
                    x--;
                else
                    x++;
            }
        }

        return new Position(x,y);
    }

    public static int solve1(String path){

        HashSet<Position> visited = new HashSet<Position>();

        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;
            int xH = 0;
            int yH = 0;
            int xT = 0;
            int yT = 0;
            while ((line = br.readLine()) != null){

                char direction = line.charAt(0);
                int steps = Integer.parseInt(line.split(" ")[1]);


                for(int i = 0; i < steps; i++){
                    if(direction == 'U'){
                        yH++;
                        if(yH > yT+1){
                            yT++;
                            if(xH != xT){
                                xT = xH;
                            }
                        }
                    }
                    else if(direction == 'D'){
                        yH--;
                        if(yH < yT-1){
                            yT--;
                            if(xH != xT){
                                xT = xH;
                            }
                        }
                    }
                    else if(direction == 'R'){
                        xH++;
                        if(xH > xT+1){
                            xT++;
                            if(yH != yT){
                                yT = yH;
                            }
                        }
                    }
                    else if(direction == 'L'){
                        xH--;
                        if(xH < xT-1){
                            xT--;
                            if(yH != yT){
                                yT = yH;
                            }
                        }
                    }
                    visited.add(new Position(xT, yT));
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return visited.size();
    }

    public static int solve2(String path, int numberOfKnots){

        HashMap<Integer,HashSet<Position>> visited = new HashMap<>();

        try(BufferedReader br = new BufferedReader(new FileReader(path))){

            String line;
            ArrayList<Position> currPositions = new ArrayList<>();
            for(int i = 0; i < numberOfKnots + 1; i++){
                currPositions.add(new Position(0, 0));
                HashSet<Position> currVisited = new HashSet<>();
                currVisited.add(new Position(0 ,0));
                visited.put(i,currVisited);

            }
            while ((line = br.readLine()) != null){

                char direction = line.charAt(0);
                int steps = Integer.parseInt(line.split(" ")[1]);

                for (int i = 0; i < steps; i++) {

                    currPositions.get(0).moveByOne(direction);
                    for(int knotIndx = 1; knotIndx < numberOfKnots + 1; knotIndx++) {

                        Position curr = currPositions.get(knotIndx);
                        Position prev = currPositions.get(knotIndx-1);

                        currPositions.set(knotIndx, followStep(prev.getX(), prev.getY(), curr.getX(), curr.getY()));

                        visited.get(knotIndx).add(new Position(curr.getX(), curr.getY()));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return visited.get(numberOfKnots).size() + 1;
    }

    public static void main(String[] args) {

        System.out.println(solve1("bridge.txt"));
        System.out.println(solve2("bridge.txt", 9));


    }

}
