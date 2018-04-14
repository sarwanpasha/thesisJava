/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thesis1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Pasha
 */
public class temp_testting {
    static int result=0;
    public static void main(String[] args) throws IOException{

//    String Path = "C:\\Users\\Pasha\\Desktop\\new 1.txt";
//    double[][] File_Values = new double[769][2];
//    File_Values = read_Column_Of_File_testing(Path);
//    Normalized_Probabilities_testing(File_Values);
    Algo4_With_15_Bins a = new Algo4_With_15_Bins();
    a.Fifteen_Bin_Function();
    
    Algo4_With_20_Bins aa = new Algo4_With_20_Bins();
    aa.Twenty_Bin_Function();
    
    }
    
        public static double[][] read_Column_Of_File_testing(String filename){
        Set<Integer> vertexSet = new HashSet<Integer>();
        ArrayList<String> edgeList= new ArrayList<String>();
        double[][] File_Values = new double[769][2];
        try{
            FileReader filein = new FileReader(filename);
            BufferedReader graphFile = new BufferedReader(filein);
            String inLine;
            int edgeList_Counter = 0;
            int row_Counter=0;
            
            while( (inLine = graphFile.readLine()) != null)
            {
                String[] parts = inLine.split("\\s");
                String temporary = "";
                String[] splitStr = inLine.split("\\s+");
                Double.parseDouble(splitStr[0]);
                for(int i=0; i<parts.length; i++){
                    File_Values[row_Counter][i] = Double.parseDouble(splitStr[i]);
                    temporary = temporary + File_Values[row_Counter][i] +  " ";
                }
//                System.err.println("splitStr[0] = " +  temporary);
                row_Counter++;

            }
           
        }
        catch( IOException e ){
            System.err.println( e );
        }
        return File_Values;
    }
        
   public static void Normalized_Probabilities_testing(double[][] temp_Prediction_2_temp) throws IOException{
        double[] temp_Prediction_2_Normalized = new double[temp_Prediction_2_temp.length  -1];
        double[][] temp_Prediction_2 = new double[769][2];
        

        int temp_Normalized_100 = 100;
        int[] temp_Prediction_3 = new int[temp_Normalized_100];
        int[] Predicted_Array = new int[769];
        for(int i=0; i<769; i++){
          for(int j=0; j<2; j++){
              temp_Prediction_2_temp[i][j] = temp_Prediction_2_temp[i][j] * temp_Normalized_100;
//              System.err.println("temp_Prediction_2_temp : " + temp_Prediction_2_temp[i][j]);
          }  
        }
        temp_Prediction_2 = temp_Prediction_2_temp;
        
       for (int r = 0; r < 769; r++) { //For each node
           int k = 0;
           for (int j = 0; j < 2; j++) {
               for (int l = 0; l < (int) temp_Prediction_2[r][j]; l++) {
                   temp_Prediction_3[k] = j;
//                   System.err.println("temp_Prediction_3 = " + temp_Prediction_3[k]);
                   k = k + 1;
               }
           }
          Predicted_Array[r] =  Weighted_Sample_testing(temp_Prediction_3);
           System.err.println("Predicted_Array = " + Predicted_Array[r]);
       }

        
//        return temp_Prediction_3;
    }
      public static int Weighted_Sample_testing(int[] temp_Prediction) throws IOException {

//        attributes_Names att = new attributes_Names();
//        att.set_attribute_Name(attribute1, attribute2);
//        String temp_attr_1 = att.get_attribute_1();
//        String temp_attr_2 = att.get_attribute_2();
//        String writeFileName1
//                = "E:/MS Computer Science/MS Thesis/Java Code/RandomizedRoundingPrediction/";
//        String writeFileName = writeFileName1 + temp_attr_2 + "_using_" + temp_attr_1 + ".txt";
//
//        FileWriter fileWriter = new FileWriter(writeFileName);
//        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

//         int [] bin = {1,3,1,3,2}; // 1,3 80%, 2 20%
//        int count = 0;
        int numChosen = -1;
//        int index = -1;
//    for (int i=0;i<result;i++) {

//    double randomNumber=Math.random();
        int numChosen_temp = (int) (Math.random() * (temp_Prediction.length));
//        System.err.println("numChosen_temp = " + numChosen_temp);
//    System.err.println("temp_Prediction.length = " + temp_Prediction);
        numChosen = temp_Prediction[numChosen_temp];
        numChosen++;
//          System.err.println("Predicted number = " + numChosen);
//          System.err.println("Predicted number = " + numChosen);
//        try {
//            bufferedWriter.write(numChosen);
//            bufferedWriter.newLine();
//        } catch (IOException ex) {
//            System.out.println("Error writing to file '" + writeFileName + "'");
//        }
        return numChosen;
//      if (numChosen ==3)  
//      {
//          index = numChosen;
//          count++;
//      }
//    }
//    if(index!= -1){
//    System.out.println("out of 100 pick, " + index  + " appear "+count+" times");
//    }
    }
          public static double findSmallest_Other_Then_Zero_testing(double[] arr) {
    double smallest = Integer.MAX_VALUE;
    for(int i=0; i<arr.length; i++) {
        if(arr[i] > 0 && arr[i]<smallest) {
            smallest = arr[i];
        }
    }
    return smallest;
}
}
