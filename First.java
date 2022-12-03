import java.io.*;
import java.util.ArrayList;

public class First {

    public static Integer mostCalories(String path){

        File file = new File(path);
        int maxSum = 0;
        int secSum = 0;
        int thirdSum = 0;

        try(BufferedReader br = new BufferedReader(new FileReader(file))){

            int tempSum = 0;

            String line;
            while(true){
                line = br.readLine();
                if(line == null || line.equals("")){
                    if(tempSum > maxSum){
                        thirdSum = secSum;
                        secSum = maxSum;
                        maxSum = tempSum;

                    }
                    else{
                        if(tempSum > secSum){
                            thirdSum = secSum;
                            secSum = tempSum;

                        }
                        else{
                            if(tempSum > thirdSum){
                                thirdSum = tempSum;
                            }
                        }
                    }
                    tempSum = 0;
                }
                else{
                    tempSum += Integer.parseInt(line);
                }
                if (line==null){
                    break;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return maxSum + secSum + thirdSum;
    }

    public static void main(String[] args) {

        System.out.println(mostCalories("cals.txt"));
    }
}
