import java.io.*;

public class Fourth {

    public static boolean oneContains(int beg1, int end1, int beg2, int end2){

        if(beg1 <= beg2 && end1 >= end2){
            return true;
        }
        return beg2 <= beg1 && end2 >= end1;
    }

    public static boolean rangesOverlap(int beg1, int end1, int beg2, int end2){

        if(beg1 <= beg2 && beg2 <= end1){
            return true;
        }
        return beg2 <= beg1 && beg1 <= end2;
    }

    public static int solve1(String path){

        int sum = 0;
        File file = new File(path);
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;

            while ((line = br.readLine()) != null){
                String[] ranges = line.split("[-,]");
                int beg1 = Integer.parseInt(ranges[0]);
                int end1 = Integer.parseInt(ranges[1]);
                int beg2 = Integer.parseInt(ranges[2]);
                int end2 = Integer.parseInt(ranges[3]);
                if(oneContains(beg1,end1,beg2,end2)){
                    sum ++;
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
            String line;

            while ((line = br.readLine()) != null){
                String[] ranges = line.split("[-,]");
                int beg1 = Integer.parseInt(ranges[0]);
                int end1 = Integer.parseInt(ranges[1]);
                int beg2 = Integer.parseInt(ranges[2]);
                int end2 = Integer.parseInt(ranges[3]);
                if(rangesOverlap(beg1,end1,beg2,end2)){
                    sum ++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sum;
    }

    public static void main(String[] args) {
        System.out.println(solve2("sections.txt"));
    }
}
