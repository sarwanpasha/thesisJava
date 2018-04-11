/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thesis1;

import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author Pasha
 */
public class temp_testting {
    static int result=0;
    public static void main(String[] args) throws IOException{
//        int[] temp_Prediction = {1,2,3,4,5};
        double[] temp_Prediction_2_temp = {0.46153846153846156,0.5384615384615384}; //Total probability must sum upto 1
        double[] temp_Prediction_2 = new double[temp_Prediction_2_temp.length];
        for(int i=0; i<temp_Prediction_2_temp.length; i++){
            temp_Prediction_2[i] = Math.floor(temp_Prediction_2_temp[i] * 100) / 100;

//            temp_Prediction_2[i] = roundToFraction(temp_Prediction_2_temp[i], 2);
            System.err.println("temp_Prediction_2 = " + temp_Prediction_2[i]);
        }
        Arrays.sort(temp_Prediction_2);
        int[] temp_Prediction_3 = new int[result];
        temp_Prediction_3 = Normalized_Probabilities(temp_Prediction_2);
        Weighted_Sampe(temp_Prediction_3);
    }
    
    public static double roundToFraction(double x, long fraction) {
    return (double) Math.round(x * fraction) / fraction;
}
    
    
    public static int[] Normalized_Probabilities(double[] temp_Prediction_2){
        double[] temp_Prediction_2_Normalized = new double[temp_Prediction_2.length];
        
        //for(int i=0; i<temp_Prediction_2.length;i++){
            double temp_Normalized;
            int temp_Normalized_100;
            
            temp_Normalized = temp_Prediction_2[0];
            
            
            temp_Normalized_100 = 10;
            //temp_Normalized = temp_Prediction_2[i]* temp_Normalized_100;
            
//            System.err.println("temp_Normalized = " + temp_Normalized);
            //if(temp_Normalized<1 && temp_Normalized!=0){
                while(temp_Normalized<1 && temp_Normalized>0){

                        result = Integer.valueOf(String.valueOf(temp_Normalized_100) + String.valueOf(0));
    //                    String result = temp_Normalized_100+"0";
                        System.err.println("result = " + result);
                        temp_Normalized_100 = result;
                        temp_Normalized = temp_Normalized* temp_Normalized_100;
                        //temp_Prediction_2_Normalized[i] = temp_Prediction_2[i]*temp_Normalized_100;

                }
            //}
            /*else{
                temp_Prediction_2_Normalized[i] = temp_Prediction_2[i]*temp_Normalized_100;
            }*/
            for (int j=0;j<temp_Prediction_2.length;j++)
            {
                temp_Prediction_2[j]*=temp_Normalized_100;
                System.err.println("temp_Prediction_2 = " + temp_Prediction_2[j]);
            }
            //System.err.println("temp_Prediction_2_Normalized = " + temp_Prediction_2_Normalized[i]);
        //}
        int[] temp_Prediction_3 = new int[temp_Normalized_100];
        int k=0;
        for (int j=0;j<temp_Prediction_2.length;j++)
        {
            for(int l=0;l<(int)temp_Prediction_2[j];l++)
            {
//                System.out.println("k : "+k);//<<" k : "<<k<<endl;
                temp_Prediction_3[k]=j;
                k=k+1;
            }
        }
        
        /*int temp_i=0;
        for(int k=0; k<temp_Prediction_2.length; k++){
            for(int j=0; j<((temp_Prediction_2[k])); j++){
                if(k==0){
              temp_Prediction_3[temp_i] = k;  
              System.err.println("First time = " + (temp_i+1) + " = "+  temp_Prediction_3[j]);
                }
                else{
                    temp_Prediction_3[temp_i] = k;
                     System.err.println("Second time = " + (temp_i+1) + " = "+  
                             temp_Prediction_3[(int)((temp_Prediction_2[k]/100)*100)+1 + j]);
                }
                temp_i++;
            }
        }*/
        
        /*for(int m=0; m<result; m++){
            System.err.println("Array = " + (m+1) + " = " + temp_Prediction_3[m]);
        }*/
        return temp_Prediction_3;
    }
      public static void Weighted_Sampe(int[] temp_Prediction){
         
         
//         int [] bin = {1,3,1,3,2}; // 1,3 80%, 2 20%
    int count=0;
    int numChosen = -1;
    int index=-1;
    for (int i=0;i<result;i++) {
      numChosen = temp_Prediction[(int)(Math.random()*temp_Prediction.length)];
      if (numChosen ==3)  
      {
          index = numChosen;
          count++;
      }
    }
    if(index!= -1){
    System.out.println("out of 100 pick, " + index  + " appear "+count+" times");
    }
     }
     
}
