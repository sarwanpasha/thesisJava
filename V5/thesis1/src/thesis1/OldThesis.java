/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thesis1;

import com.sun.glass.ui.Size;
import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;
import static oracle.jrockit.jfr.events.Bits.intValue;

/**
 *
 * @author Pasha
 */
public class OldThesis {

    //Caltech Dataest
    static int Node_Count = 770;
    static int edgeList_Array_Count = 33313;
    //Caltech Dataest
    
//     //Rice Dataest
//    static int Node_Count = 4088;
//    static int edgeList_Array_Count = 369657;
//    //Rice Dataest
    
//         //American75 Dataest
//    static int Node_Count = 6387;
//    static int edgeList_Array_Count = 435325;
//    //American75 Dataest
    
    
//    //
//    static int Node_Count = 6;
//    static int edgeList_Array_Count = 6;
////    //

//    //caltech Dataset Attributed Matrix Path
    static String attribute_Path = "E:\\MS Computer Science\\MS Thesis\\Java Code\\thesis1\\src\\thesis1\\caltech_attributes.txt";
//    //caltech Dataset Edge list path
    static String Path = "E:\\MS Computer Science\\MS Thesis\\Java Code\\thesis1\\src\\thesis1\\Caltech36_edgelist2.txt";
    
//        //Rice Dataset Attributed Matrix Path
//    static String attribute_Path = "E:\\MS Computer Science\\MS Thesis\\Java Code\\thesis1\\src\\thesis1\\Rice_attributes.txt";
//    //Rice Dataset Edge list path
//    static String Path = "E:\\MS Computer Science\\MS Thesis\\Java Code\\thesis1\\src\\thesis1\\Rice31_edgelist.txt";
//    
//            //American75 Dataset Attributed Matrix Path
//    static String attribute_Path = "E:\\MS Computer Science\\MS Thesis\\Java Code\\thesis1\\src\\thesis1\\American75_attributes.txt";
////    //American75 Dataset Edge list path
//    static String Path = "E:\\MS Computer Science\\MS Thesis\\Java Code\\thesis1\\src\\thesis1\\American75_edgelist.txt";
//    
    static int[][] attributeMatrix = new int[Node_Count][7];
    static int[][] edgeList_Array = new int[edgeList_Array_Count][2];
    static int[] a1 = new int[Node_Count];
    static int[] a2 = new int[Node_Count];
//    static int[] Hidden_Users = {4};
    static int[] Hidden_Users = IntStream.rangeClosed(1,Node_Count).toArray();
//        static int[] Hidden_Users = IntStream.rangeClosed(1,10).toArray();
//static int[] Hidden_Users = IntStream.rangeClosed(1,100).toArray();

//    static int[] Hidden_Users_range = IntStream.rangeClosed(1, 769).toArray();

    static int Hidden_Count = Hidden_Users.length;

//    static int[] a1_new = new int[Node_Count];
//    static int[] a2_new = new int[Node_Count];

    public static void main(String[] args) 
    {
        
        int outerLoop;
        int innerLoop;
//        Graph G = new Graph("E:\\MS Computer Science\\MS Thesis\\Java Code\\thesis1\\src\\thesis1\\cl.txt");
        Graph G = new Graph(Path);

        int numNodes = G.V();
        int numEdges = G.E();
        System.out.println("Number of vertices in G " + numNodes + "  " + numEdges);

        ArrayList<String> edgeList = G.get_Edge_List();

//           System.out.println("Edge List = " + edgeList);
        String temp = G.toString();
//           System.out.println(temp);

        print_Attributes();

        int attribute1; // Cannot go more then 7 (0 - 6)
        int attribute2; // Cannot go more then 7 (0 - 6)
        int a1_distinct[];
        int a2_distinct[];
        int attributeCount = 1; // Cannot go more then 7
        for (outerLoop = 0; outerLoop < attributeCount; outerLoop++) {
            for (innerLoop = 0; innerLoop < attributeCount; innerLoop++) {
                attribute1 = outerLoop;
                attribute2 = innerLoop;
                attribute1 = 1; // Status
                attribute2 = 0; // Gender
                attributes_Names att = new attributes_Names();
                att.set_attribute_Name(attribute1, attribute2);
                String temp_attr_1 = att.get_attribute_1();
                String temp_attr_2 = att.get_attribute_2();
                System.err.println("Attribute 1 = " + attribute1 + " Which is = " + temp_attr_1);
                System.err.println("Attribute 2 = " + attribute2 + " Which is = " + temp_attr_2);
//                for (int i = 0; i < Node_Count - 1; i++) {
//                    a1_new[i] = Integer.parseInt(attributeMatrix[i][attribute1]);
//                    a2_new[i] = Integer.parseInt(attributeMatrix[i][attribute2]);
//                }
//                int[] a1 = removeZeros(a1_new);
//                int[] a2 = removeZeros(a2_new);
//                System.out.println("AttributeMatrix length = " + attributeMatrix.length);
                for (int i = 0; i < Node_Count; i++) {

                    a1[i] = attributeMatrix[i][attribute1];
                    a2[i] = attributeMatrix[i][attribute2];

//                    System.out.println("A1 = " + a1[i] + " A2 = " + a2[i]);
                }
                a1_distinct = toUniqueArray(a1);
                a2_distinct = toUniqueArray(a2);
                Arrays.sort(a1_distinct);
                Arrays.sort(a2_distinct);
                String temp_a1_distinct = "";
                String temp_a2_distinct = "";
                for(int i=0; i<a1_distinct.length; i++){
                   temp_a1_distinct = temp_a1_distinct + a1_distinct[i] + " ";
                }
                for(int q=0; q<a2_distinct.length; q++){
                       temp_a2_distinct = temp_a2_distinct + a2_distinct[q] + " ";
                   } 
//                System.err.println("a1 distinct = " + temp_a1_distinct);
//                System.err.println("a2 distinct = " + temp_a2_distinct);
                
                int a1_length = a1_distinct.length;
                int a2_length = a2_distinct.length;
//                System.out.println("a1 Length = " + a1.length);
//                System.out.println("a2 Length = " + a2.length);
//                System.out.println("a1_Distinct Length = " + a1_length);
//                System.out.println("a2_Distinct Length = " + a2_length);

                int[][] Mixing_Matrix = new int[a1_length][a2_length];
                double[][] Row_Normalized_Mixing_Matrix = new double[a1_length][a2_length];
                edgeList_Array = G.get_edgeList_Array();
                //calling function to generate mixing matrix
                Mixing_Matrix = Generated_Mixing_Matrix(attribute1, attribute2, a1_length, a2_length, a1_distinct, a2_distinct);

                Row_Normalized_Mixing_Matrix = Normalized_Mixing_Matrix(Mixing_Matrix,a1_length,a2_length,attribute1,attribute2);
                
                for (int i = 0; i < a1_length; i++) {
                    String temp_MM = "";
                    for (int j = 0; j < a2_length; j++) {
                        temp_MM = temp_MM + Row_Normalized_Mixing_Matrix[i][j] + " ";
//                    System.err.println(Row_Normalized_Mixing_Matrix[i][j]);
                    }
                    System.err.println(temp_MM);
                }
                //Calling function to find friends of hidden node
                ArrayList Immediate_friends = new ArrayList();
                ArrayList Friends_of_friend = new ArrayList();
//                Immediate_friends = Friend_Finder(attribute1, attribute2, Hidden_Users);
//                Collections.sort(Immediate_friends);
//              System.out.println("New Contents of friends: " + Immediate_friends);

                //Class wise Computing predicted attribute
                int[] distribution=new int[a2_distinct.length];
                int[] Immediate_Friends_distribution=new int[a2_distinct.length];
                int[] FOF_distribution=new int[a2_distinct.length];
                double[] FOF_distribution_normalized=new double[a2_distinct.length];
                
                double[] Immediate_Friends_distribution_normalized=new double[a2_distinct.length];
//                distribution = computing_attribute_Value(Immediate_friends,a2_distinct);
//                Arrays.sort(distribution);
//                for(int i=0; i<distribution.length; i++){
//                    System.err.println("Gender distribution = " + distribution[i]);
//                }
                int[][] Contingency_Table = new int [a1_distinct.length][a2_distinct.length];
                int first_attribute_index,second_attribute_index;
                int[] predicting_Attribute_of_FOF = new int[a2_length];
                int FOF_Total_Sum = 0;
                double[] first_part_Formula = new double[a2_length];
                
                double[] Final_predicted_Value = new double[Hidden_Count];
                int[] Actual_Value = new int[Hidden_Count];
                
                for (int i = 0; i < Hidden_Count; i++) {
                    double[] Final_FOF_distribution_normalized=new double[a2_distinct.length];
//                        System.err.println("Hidden User = " + a2[Hidden_Users[i]-1]);

                        // Contingency Table Logic Start Here ////////////////////////////////////////////////
                        first_attribute_index = indexOf(a1[Hidden_Users[i]-1], a1_distinct); // -1 is because value start from 0 in java rather than 1
                        second_attribute_index = indexOf(a2[Hidden_Users[i]-1], a2_distinct); // -1 is because value start from 0 in java rather than 1
                        Contingency_Table[first_attribute_index][second_attribute_index]++;
                        // Contingency Table Logic Ends Here ////////////////////////////////////////////////
                        

                        Immediate_friends = Friend_Finder(attribute1, attribute2, (Hidden_Users[i]-1)); // Immediate Friends of Node 3 (Hidden Node) -> y1, y2, ...
                        Collections.sort(Immediate_friends);
//                        System.err.println(" AND Total Immediate Friends of User = " + i + " Are = " + Immediate_friends.size());
                        Immediate_Friends_distribution = computing_attribute_Value_For_First_Attribute(Immediate_friends, a1_distinct);
                        double[][] prediction_Final= new double[a1_length][a2_length];
                        String temp_imm_Friends = "";
                        for(int h=0; h<Immediate_Friends_distribution.length; h++){
                            if(h!=0){
                            temp_imm_Friends = temp_imm_Friends + Immediate_Friends_distribution[h] + " ";
                            }
                        }
//                        System.err.println("Immediate_Friends_distribution of Node" + (Hidden_Users[i]-1)
//                                + " are = " + temp_imm_Friends);
                        double[] temp_predicted_Value = new double[Immediate_friends.size()];
                        int[] Predicting_Attribute_Counter = new int[a2_distinct.length];
                        int index_count;
                        int temp3;
                        double[] vote = new double[a2_length];
                        double[] final_vote = new double[a1_length];
                        
                        String temp_Final_Vote="";
//                        for (int j = 0; j < Immediate_friends.size(); j++) {
                            for(int s=0;s<a1_length;s++){
                            String temp_Vote="";
                            for (int b = 0; b < a2_length; b++) {

                            prediction_Final[s][b] = (Immediate_Friends_distribution[s]
                                    * Row_Normalized_Mixing_Matrix[s][b]);
                                vote[b] = prediction_Final[s][b];
                                temp_Vote = temp_Vote + vote[b] + " ";
                            }
//                            System.err.println("vote for Node " + i + " and friend " + Immediate_friends.get(s) 
//                                    + " = " + temp_Vote);
                            
//                            }
                            
                            final_vote[s] = findLargest(vote);
//                            System.err.println("Max Vote = " + final_vote[s]);
                            temp_Final_Vote = temp_Final_Vote + final_vote[s] + " ";
                        }                   
//                        System.err.println("vote for Node " + i + " = " + temp_Final_Vote);
                        Final_predicted_Value[i] = finding_Mode_value(final_vote);
//                        System.err.println("Final Vote = " + Final_predicted_Value[i]);
//                        double[] temp_Prediction = new double[a2_length];
//                        int sum = IntStream.of(Predicting_Attribute_Counter).sum();
//                        for (int qwe = 1; qwe < Predicting_Attribute_Counter.length; qwe++) {
//                            temp_Prediction[qwe] = (double)(Predicting_Attribute_Counter[qwe]* 100) / sum;
//                        }

                    Actual_Value[i] = a2[Hidden_Users[i]-1]; // True classes
                } // Hidden count loop ends here
                int[] unique_Actual_Values = toUniqueArray(Actual_Value);
                
               int[][] Confusion_Matrix= new int[a2_length][a2_length]; // Initializing Confusion matrix with zero's

                double success = 0;
                double failure = 0;
                double accuracy = 0;
                int row_index;
                int column_index;
                for(int i=0; i<Hidden_Count; i++){
                    
                    row_index = indexOf((int)Final_predicted_Value[i], a2_distinct);
                    column_index = indexOf(Actual_Value[i], a2_distinct);
//                    System.err.println("Predicted Row for user " + i + " = " + row_index);
                    if(row_index>=0 &&column_index>=0){
                    Confusion_Matrix[row_index][column_index]++; 
                    }
//                    System.err.println("Actual Value = " + a2[Hidden_Users[i]-1]);
//                    System.err.println("Predicted Value = " + (int)Final_predicted_Value[i]);
                    if((int)Final_predicted_Value[i] == a2[Hidden_Users[i]-1]){
                        success++;
                    }
                    else{
                        failure++;
                    }
                    
                }
                //Printing Confusion Matrix
                
                for(int i=0; i<a2_length; i++){
                    String Confusion = "";
                    for(int f=0; f<a2_length; f++){
                        if(i!=0 && f!=0){
                        Confusion = Confusion + Confusion_Matrix[i][f] + " ";
                        }
                    
                    }
                    System.out.println("Confusion Matrix = " +  Confusion);
                }
                System.err.println("Success = "  + success + " Failure = " + failure);
                accuracy = (success / (success + failure)) * 100;
                System.err.println("accuracy = "  + accuracy );
                //Printing Contingency Table
                for (int i=0; i<a1_distinct.length; i++)
                {
                    String Contingency = "";
                    for (int f=0; f<a2_distinct.length; f++)
                {
                    if(i!=0 && f!=0){
                    Contingency = Contingency + Contingency_Table[i][f] + " ";
                    }
                    
                }
                    System.err.println("Contingency Table = " + Contingency);
                }
            } // inner loop end here
        } // Outer loop end here
    } // Main function Ends here


    public static int finding_Mode_value(double[] max_array) {
         int maxValue = 0, maxCount = 0;
 
        for (int i = 0; i < max_array.length; ++i) 
        {
            int count = 0;
            for (int j = 0; j < max_array.length; ++j) 
            {
                if (max_array[j] != 0) 
                {
                    if (max_array[j] == max_array[i]) {
                        ++count;
                    }
                }
            }
            if (count > maxCount) 
            {
                maxCount = count;
                maxValue = (int)max_array[i];
            }
        }
 
        return maxValue;
    }
public static void print_array(int[] printing, String Name){
    for(int i=0; i<printing.length; i++){
        System.out.println("Array = " + printing[i]);
    }
}
public static double findLargest(double array[])
{
    double largest = array[0];
    double largestIndex = 0;

    for(int i = 0; i < array.length; i++)
    {
        if(array[i] > largest) {
            largest = array[i]; 
            largestIndex =i;
        }  
    }

    return largestIndex;
}
    public static int[] computing_attribute_Value_For_First_Attribute(ArrayList friends_attribute, int[] a1_distinct){
        int Node;
        int index_Value;
        int[] distribution=new int[a1_distinct.length];
        for(int i=0; i<friends_attribute.size(); i++){
         Node =   Integer.valueOf((int)friends_attribute.get(i));
         if(Node!=0){
         index_Value = indexOf(a1[Node-1], a1_distinct); // -1 is because value start from 0 in java rather than 1
         distribution[index_Value]++;
         }
        }
        return distribution;
    }
    public static int[] computing_attribute_Value(ArrayList friends_attribute, int[] a2_distinct){
        int Node;
        int index_Value;
        int[] distribution=new int[a2_distinct.length];
        for(int i=0; i<friends_attribute.size(); i++){
         Node =   Integer.valueOf((int)friends_attribute.get(i));
         if(Node!=0){
         index_Value = indexOf(a2[Node-1], a2_distinct); // -1 is because value start from 0 in java rather than 1
//         System.err.println("Array length = " + friends_attribute.size() + " Node = " + Node + " Attribute value = "+a2[Node-1]+
//                 " AND Index Value = " + index_Value + " array distinct values = " + a2_distinct[0]
//         + " array distinct values = " + a2_distinct[1]
//         + " array distinct values = " + a2_distinct[2]);
         distribution[index_Value]++;
//         a2_distinct[a2[Node]];
         }
        }
        return distribution;
    }
    public static double[][] Normalized_Mixing_Matrix(int[][] Normalized_Mixing_Matrix, int a1_length, int a2_length, int attribute1, int attribute2) {
        int[] row_sum = new int[a1_length];
        int[][] Row_Normalized_Mixing_Matrix = Normalized_Mixing_Matrix;
        double [][] new_Row_Normalized_Mixing_Matrix = new double [a1_length][a2_length];
        // Loop to compute row sum
        for (int i = 0; i < a1_length; i++) {
            for (int j = 0; j < a2_length; j++) {
                row_sum[i] = Normalized_Mixing_Matrix[i][j] + row_sum[i];
            }
        }
//        for(int i=0; i<a1_length; i++){
//            System.err.println("Row " + i + " sum = " + row_sum[i]);
//        }
        //Loop to divide each value of matrix with its rowsum
        double  temp = 0;
        double  temp2 = 0;
        float twoDigitsF;
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        for (int i = 0; i < a1_length; i++) {
            for (int j = 0; j < a2_length; j++) {
                if (Row_Normalized_Mixing_Matrix[i][j] != 0) {
                    temp = (double)Row_Normalized_Mixing_Matrix[i][j] / row_sum[i];
                    temp2 = Math.round(temp*100) / 100.0; // getting upto 2 decimal points
        
//                    System.err.println("Dividing " + Row_Normalized_Mixing_Matrix[i][j] + " With = " + row_sum[i] + 
//                            " and Answer is = " + temp2);
                    new_Row_Normalized_Mixing_Matrix[i][j] = temp2;
                }
            }
        }
//        for (int i = 0; i < a1_length; i++) {
//            for (int j = 0; j < a2_length; j++) {
//                    System.out.println("Normalized Matrix = " + new_Row_Normalized_Mixing_Matrix[i][j]);
//            }
//        }
        return new_Row_Normalized_Mixing_Matrix;

    }
    
    public static ArrayList Friend_Finder(int attribute1, int attribute2, int Hidden_Node)
    {
        //        Hidden_Users;
//            int[] friends;
          ArrayList friends = new ArrayList();
          for(int i=0; i<edgeList_Array_Count; i++)
          {
              if(edgeList_Array[i][0] != edgeList_Array[i][1])
              {
                  if(edgeList_Array[i][0]==Hidden_Node)
                  {
                      friends.add(edgeList_Array[i][1]);
                  }
                  if(edgeList_Array[i][1]==Hidden_Node)
                  {
                      friends.add(edgeList_Array[i][0]);
                  }
              }
          }
//        System.out.println("Contents of friends: " + friends);
        Set<String> hs = new HashSet<>();
        hs.addAll(friends);
        friends.clear();
        friends.addAll(hs);
//        System.out.println("New Contents of friends: " + friends);
        
        return friends;
    }
    public static int[][] Generated_Mixing_Matrix(int attribute1, int attribute2, int a1_length,
            int a2_length, int[] a1_distinct, int[] a2_distinct) {
        int[][] Mixing_Matrix = new int[a1_length][a2_length];
        int Friend1;
        int Friend2;
        int Friend1_index;
        int Friend2_index;
        int skip = 0;

        for (int i = 0; i < edgeList_Array.length; i++) {

            Friend1 = edgeList_Array[i][0];
            Friend2 = edgeList_Array[i][1];
            if (Friend1 != Friend2) {
                if (Friend1 != 0) {
                    if (Friend2 != 0) {
                        if (a1[Friend1-1] != 0) { // -1 is because value start from 0 in java rather than 1
//                                    System.err.println("Value 1 = "+ edgeList_Array[i][attribute1] + " Value 2 = " + 
//                                 edgeList_Array[i][attribute2]);
                            if (a2[Friend2-1] != 0) { // -1 is because value start from 0 in java rather than 1

                                Friend1_index = indexOf(a1[Friend1-1], a1_distinct); // -1 is because value start from 0 in java rather than 1
                                Friend2_index = indexOf(a2[Friend2-1], a2_distinct); // -1 is because value start from 0 in java rather than 1
//                                        System.err.println("Node 1 = " + Friend1 + " Node 2 = " + Friend2);
//                                        System.err.println("Giving Vote to " + Friend1_index + "," + Friend2_index);
                                Mixing_Matrix[Friend1_index][Friend2_index]++;
//                        Mixing_Matrix[Friend1_index][Friend2_index]++;
                            } else {
//                                    System.err.println("skipped = " + a2[Friend2]);
                                skip++;
                            }
                        } else {
                            skip++;
                        }
                    }
                }
            }
        }
//        System.err.println("skip = " + skip);
        return Mixing_Matrix;
    }

    private static int indexOf(int c, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == c) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Convert the given array to an array with unique values without duplicates
     * and returns it.
     */
    public static int[] toUniqueArray(int[] array) {
        int[] temp = new int[array.length];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = -1; // in case u have value of 0 in the array
        }

        int counter = 0;
        for (int i = 0; i < array.length; i++) {
            if (isUnique(temp, array[i])) {
                temp[counter++] = array[i];
            }
        }

        int[] uniqueArray = new int[counter];
        System.arraycopy(temp, 0, uniqueArray, 0, uniqueArray.length);
        return uniqueArray;
    }

    /**
     * Return true if number num is appeared only once in the array num is
     * unique.
     */
    public static boolean isUnique(int[] array, int num) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == num) {
                return false;
            }
        }
        return true;
    }

    /**
     * It print attribute matrix values
     */
    public static void print_Attributes() {
        Graph G = new Graph();
        G.Read_Attributed_Graph(attribute_Path);

        attributeMatrix = G.get_Attributed_Graph();

        StringBuilder stringBuilder = new StringBuilder();
        String NEWLINE = System.getProperty("line.separator");
        for (int i = 0; i < Node_Count - 1; i++) {
            for (int j = 0; j < 7; j++) {
                stringBuilder.append(attributeMatrix[i][j]);
                stringBuilder.append(" ");
            }
            stringBuilder.append(NEWLINE);
        }
        String finalString = stringBuilder.toString();
//        System.out.println(finalString);
    }

    public static int[] removeZeros(int[] array) {
        int j = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0) {
                array[j++] = array[i];
            }
        }
        int[] newArray = new int[j];
        System.arraycopy(array, 0, newArray, 0, j);
        return newArray;
    }

}
