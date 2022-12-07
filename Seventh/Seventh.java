package Seventh;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Seventh {

    public static int solve1(String path){

        Tree tree = new Tree();
        tree.addKid("/",0);
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;
            while ((line = br.readLine()) != null){

                if(line.startsWith("$ ls")){}
                else if(line.equals("$ cd ..")){
                    tree.moveUp();
                }
                else if(line.startsWith("$ cd ")){
                    tree.moveDown(line.substring(5));
                }
                else{
                    String[] split = line.split(" ");
                    if(split[0].equals("dir")){
                        tree.addKid(split[1],0);
                    }
                    else{
                        tree.addKid(split[1],Integer.parseInt(split[0]));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        tree.cout();
        return tree.sumDirIfLessEq(100000);
    }

    public static int solve2(String path, int discSize, int spaceNeeded){

        Tree tree = new Tree();
        tree.addKid("/",0);
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;
            while ((line = br.readLine()) != null){

                if(line.startsWith("$ ls")){}
                else if(line.equals("$ cd ..")){
                    tree.moveUp();
                }
                else if(line.startsWith("$ cd ")){
                    tree.moveDown(line.substring(5));
                }
                else{
                    String[] split = line.split(" ");
                    if(split[0].equals("dir")){
                        tree.addKid(split[1],0);
                    }
                    else{
                        tree.addKid(split[1],Integer.parseInt(split[0]));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int lookForSpace = discSize - tree.totalValue();
        lookForSpace = spaceNeeded - lookForSpace;

        return tree.findClosestBigger(lookForSpace);
    }

    public static void main(String[] args) {
        System.out.println(solve1("disc.txt"));
        System.out.println(solve2("disc.txt", 70000000, 30000000));
    }
}
