package Twelfth;

import Eleventh.Monkey;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

public class Twelfth {

    public static int solve1(String path){


        ArrayList<Node> shortestPath = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;

            Node source = null;
            Node destination = null;
            Graph graph = new Graph();

            ArrayList<ArrayList<Node>> tmp = new ArrayList<>();

            while ((line = br.readLine()) != null) {

                tmp.add(new ArrayList<Node>());

                char[] letters = line.toCharArray();
                for(char letter:letters){

                    int value = letter;
                    tmp.get(tmp.size() - 1).add(new Node(new ArrayList<Node>(), value));
                }

            }

            for(int i = 0; i < tmp.size(); i++){
                for(int j = 0; j<tmp.get(i).size(); j++){

                    Node curr = tmp.get(i).get(j);
                    if(i - 1 > -1)
                        curr.addConnection(tmp.get(i - 1).get(j));

                    if(i + 1 < tmp.size())
                        curr.addConnection(tmp.get(i + 1).get(j));

                    if(j - 1 > -1)
                        curr.addConnection(tmp.get(i).get(j - 1));

                    if(j + 1 < tmp.get(i).size())
                        curr.addConnection(tmp.get(i).get(j + 1));

                    if(curr.getValue() == 83) {
                        source = curr;
                        curr.setValue(97);
                    }
                    else  if(curr.getValue() == 69) {
                        destination = curr;
                        curr.setValue(122);
                    }
                    graph.addNode(curr);
                }
            }

            shortestPath = graph.shortestPath(source, destination);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return shortestPath.size() - 1;
    }

    public static int solve2(String path){

        int best = -1;

        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;

            ArrayList<Node> possibleSources = new ArrayList<>();
            Node destination = null;
            Graph graph = new Graph();

            ArrayList<ArrayList<Node>> tmp = new ArrayList<>();

            while ((line = br.readLine()) != null) {

                tmp.add(new ArrayList<Node>());

                char[] letters = line.toCharArray();
                for(char letter:letters){

                    int value = letter;
                    tmp.get(tmp.size() - 1).add(new Node(new ArrayList<Node>(), value));
                }

            }

            for(int i = 0; i < tmp.size(); i++){
                for(int j = 0; j<tmp.get(i).size(); j++){

                    Node curr = tmp.get(i).get(j);
                    if(i - 1 > -1)
                        curr.addConnection(tmp.get(i - 1).get(j));

                    if(i + 1 < tmp.size())
                        curr.addConnection(tmp.get(i + 1).get(j));

                    if(j - 1 > -1)
                        curr.addConnection(tmp.get(i).get(j - 1));

                    if(j + 1 < tmp.get(i).size())
                        curr.addConnection(tmp.get(i).get(j + 1));

                    if(curr.getValue() == 83) {
                        curr.setValue(97);
                        possibleSources.add(curr);
                    }
                    else  if(curr.getValue() == 69) {
                        destination = curr;
                        curr.setValue(122);
                    }
                    else if(curr.getValue() == 97)
                        possibleSources.add(curr);
                    graph.addNode(curr);
                }
            }

            for(Node possible : possibleSources){
                ArrayList<Node> currPath = graph.shortestPath(possible, destination);
                if(currPath.get(currPath.size() - 1) == possible) {
                    if (best < 0) {
                        best = currPath.size() - 1;
                    } else if (best > currPath.size() - 1) {
                        best = currPath.size() - 1;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return best;
    }

    public static void main(String[] args) {

        System.out.println(solve1("map.txt"));
        System.out.println(solve2("map.txt"));
    }
}
