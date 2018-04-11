/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thesis1;

import com.sun.glass.ui.Size;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;
import static jdk.nashorn.internal.objects.NativeMath.round;
import static oracle.jrockit.jfr.events.Bits.intValue;

/**
 *
 * @author Pasha
 */
public class Algo4 {

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
//    static int[] Hidden_Users = {767};
    static int[] Hidden_Users = IntStream.rangeClosed(1, Node_Count).toArray();
    
    static int Bin_Low = 25;
    static int Bin_Medium = 50;
    static int Bin_High = 75;
    static int Bin_Very_High = 100;
//static int[] Hidden_Users = IntStream.rangeClosed(1,100).toArray();

//    static int[] Hidden_Users_range = IntStream.rangeClosed(1, 769).toArray();
    static int Hidden_Count = Hidden_Users.length;

    static String[] Bin_Distribution_Array=new String[Hidden_Count];
//    static int[] a1_new = new int[Node_Count];
//    static int[] a2_new = new int[Node_Count];
    public static void main(String[] args) throws IOException {

        int outerLoop;
        int innerLoop;
//        Graph G = new Graph("E:\\MS Computer Science\\MS Thesis\\Java Code\\thesis1\\src\\thesis1\\cl.txt");
        Graph G = new Graph(Path);

        int numNodes = G.V();
        int numEdges = G.E();
        System.out.println("Number of vertices in G " + numNodes + "  " + (numEdges / 2));

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
                attribute1 = 0; // Status
                attribute2 = 1; // Gender
                attributes_Names att = new attributes_Names();
                att.set_attribute_Name(attribute1, attribute2);
                String temp_attr_1 = att.get_attribute_1();
                String temp_attr_2 = att.get_attribute_2();
                System.err.println("Attribute 1 = " + attribute1 + " Which is = " + temp_attr_1);
                System.err.println("Attribute 2 = " + attribute2 + " Which is = " + temp_attr_2);

                String temp_name_str = "Predicting " + temp_attr_2 + " using " + temp_attr_1;
                String writeFileName
                        = "C:/Users/Pasha/Desktop/error results/"+ temp_name_str+ ".txt";
                FileWriter fileWriter = new FileWriter(writeFileName);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
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
                for (int i = 0; i < a1_distinct.length; i++) {
                    temp_a1_distinct = temp_a1_distinct + a1_distinct[i] + " ";
                }
                for (int q = 0; q < a2_distinct.length; q++) {
                    temp_a2_distinct = temp_a2_distinct + a2_distinct[q] + " ";
                }
                System.err.println("a1 distinct = " + temp_a1_distinct);
                System.err.println("a2 distinct = " + temp_a2_distinct);

                int a1_length = a1_distinct.length;
                int a2_length = a2_distinct.length;

                int[][] Mixing_Matrix = new int[a1_length][a2_length];
                double[][] Row_Normalized_Mixing_Matrix = new double[a1_length][a2_length];
                edgeList_Array = G.get_edgeList_Array();
                //calling function to generate mixing matrix
                Mixing_Matrix = Generated_Mixing_Matrix(attribute1, attribute2, a1_length, a2_length, a1_distinct, a2_distinct);

                Row_Normalized_Mixing_Matrix = Normalized_Mixing_Matrix(Mixing_Matrix, a1_length, a2_length, attribute1, attribute2);

                for (int i = 0; i < a1_length; i++) {
                    String temp_MM = "";
                    for (int j = 0; j < a2_length; j++) {
                        temp_MM = temp_MM + Mixing_Matrix[i][j] + " ";
//                    System.err.println(Row_Normalized_Mixing_Matrix[i][j]);
                    }
                    System.err.println(temp_MM);
                }
                //Calling function to find friends of hidden node
                ArrayList Immediate_friends = new ArrayList();
                ArrayList Friends_of_friend = new ArrayList();

                ArrayList hidden_User = new ArrayList();
//                Immediate_friends = Friend_Finder(attribute1, attribute2, Hidden_Users);
//                Collections.sort(Immediate_friends);
//              System.out.println("New Contents of friends: " + Immediate_friends);

                //Class wise Computing predicted attribute
                int[] distribution = new int[a2_distinct.length];
                int[] Immediate_Friends_distribution_a1 = new int[a1_distinct.length];
                int[] Immediate_Friends_distribution_a2 = new int[a2_distinct.length];
                int[] FOF_distribution = new int[a2_distinct.length];
                double[] FOF_distribution_normalized = new double[a2_distinct.length];

                double[] Immediate_Friends_distribution_normalized_a1 = new double[a1_distinct.length];
                double[] Immediate_Friends_distribution_normalized_a2 = new double[a2_distinct.length];
//                distribution = computing_attribute_Value(Immediate_friends,a2_distinct);
//                Arrays.sort(distribution);
//                for(int i=0; i<distribution.length; i++){
//                    System.err.println("Gender distribution = " + distribution[i]);
//                }
                int[][] Contingency_Table = new int[a1_distinct.length][a2_distinct.length];
                int first_attribute_index, second_attribute_index;
                int[] predicting_Attribute_of_FOF = new int[a2_length];
                int FOF_Total_Sum = 0;
                double[] first_part_Formula = new double[a2_length];

                double[] Final_predicted_Value = new double[Hidden_Count];
                int[] Actual_Value = new int[Hidden_Count];
                int a1_of_Hidden_Node; //Status of hidden Node
                int a2_of_Hidden_Node; //Gender of hidden Node
                double[] predicted_numerator_array = new double[a2_distinct.length];
                double[] error_Result_array = new double[a2_distinct.length];
                int Count_tuples = 0;
                double[] total_Error_array = new double[Hidden_Count];
                double final_total_error=0;
                int NAN_Count=0;
                int [] Friends_Bin_Array = new int[4];
                
                for (int i = 0; i < Hidden_Count; i++) {
                    
//                    double[] Final_FOF_distribution_normalized = new double[a2_distinct.length];
//                        System.err.println("Hidden User = " + a2[Hidden_Users[i]-1]);

                    // Contingency Table Logic Start Here ////////////////////////////////////////////////
                    first_attribute_index = indexOf(a1[Hidden_Users[i] - 1], a1_distinct); // -1 is because value start from 0 in java rather than 1
                    second_attribute_index = indexOf(a2[Hidden_Users[i] - 1], a2_distinct); // -1 is because value start from 0 in java rather than 1
                    Contingency_Table[first_attribute_index][second_attribute_index]++;
                    // Contingency Table Logic Ends Here ////////////////////////////////////////////////

                    a1_of_Hidden_Node = indexOf(a1[(Hidden_Users[i] - 1)], a1_distinct);
                    a2_of_Hidden_Node = indexOf(a2[(Hidden_Users[i] - 1)], a2_distinct);
                    //Print Status of hidden user
//                        System.out.println("Status of Hidden User " + (Hidden_Users[i]) +  " = " + a1_of_Hidden_Node);
//                        //Print Gender of hidden user
//                        System.out.println("Gender of Hidden User " + (Hidden_Users[i]) +  " = " + a2_of_Hidden_Node);

                    Immediate_friends = Friend_Finder(attribute1, attribute2, (Hidden_Users[i])); // Immediate Friends of Node 3 (Hidden Node) -> y1, y2, ...
                    Collections.sort(Immediate_friends);
                    //print total immediate friends of hidden node
//                        System.err.println("Total Immediate Friends of User = " + (Hidden_Users[i]) 
//                                + " Are = " + Immediate_friends.size());

                    Immediate_Friends_distribution_a1 = computing_attribute_Value_a1(Immediate_friends, a1_distinct);
                    Immediate_Friends_distribution_a2 = computing_attribute_Value_a2(Immediate_friends, a2_distinct);

                    Friends_Bin_Array[0] = 0;
                    Friends_Bin_Array[1] = 0;
                    Friends_Bin_Array[2] = 0;
                    Friends_Bin_Array[3] = 0;
                    
                    String temp_imm_Friends_a1 = ""; 
                    String temp_imm_Friends_quartile = ""; 
                    for (int h = 0; h < Immediate_Friends_distribution_a1.length; h++) {
//                        if (h == a1_of_Hidden_Node) {
//                            Count_tuples = Immediate_Friends_distribution_a1[h];
//                        }
                        if (Immediate_friends.size() != 0) {
                            double quartile_lookup = ((double)Immediate_Friends_distribution_a1[h] /(double) Immediate_friends.size()) * 100;
//                            System.err.println("Lookup value = " + quartile_lookup);
                            if (h != 0) {
                                if (quartile_lookup >= 0 && quartile_lookup <= Bin_Low) {
                                    Friends_Bin_Array[0]++;
                                } else if (quartile_lookup > Bin_Low && quartile_lookup <= Bin_Medium) {
                                    Friends_Bin_Array[1]++;
                                } else if (quartile_lookup > Bin_Medium && quartile_lookup <= Bin_High) {
                                    Friends_Bin_Array[2]++;
                                } else if (quartile_lookup > Bin_High && quartile_lookup <= Bin_Very_High) {
                                    Friends_Bin_Array[3]++;
                                } else {
                                    System.err.println("Error!!!");
                                }
                            }
                        }
                        
                        temp_imm_Friends_a1 = temp_imm_Friends_a1 + Immediate_Friends_distribution_a1[h] + " ";
                    }
                    //Display bin distribution!!!
                    for (int h = 0; h < Friends_Bin_Array.length; h++) {
                        temp_imm_Friends_quartile = temp_imm_Friends_quartile +  Friends_Bin_Array[h] + " ";
                    }
                    Bin_Distribution_Array[i] = temp_imm_Friends_quartile; 
//                    System.err.println("Bin distribution" + (Hidden_Users[i])+ " are = " 
//                                + temp_imm_Friends_quartile); 



//                    System.err.println("Count_tuples = " + Count_tuples);

//                    //Print immediate friends for attribute 1 of the hidden Node!!!
//                        System.err.println("Immediate_Friends_distribution_a1 of Node" + (Hidden_Users[i])+ " are = " 
//                                + temp_imm_Friends_a1);   


                    //Print total tuple size
//                        System.out.println("Total Tuples a1 = " + Count_tuples);

                    String temp_imm_Friends_a2 = "";
                    String temp_numerator_array_a2 = "";
                    String temp_error_Result_array = "";
                    double total_Error = 0;
                    for (int h = 0; h < Immediate_Friends_distribution_a2.length; h++) {
//                            if(h==a2_of_Hidden_Node){
//                                Count_tuples = Immediate_Friends_distribution_a2[h];
//                            }
                        temp_imm_Friends_a2 = temp_imm_Friends_a2 + Immediate_Friends_distribution_a2[h] + " ";
//                            if(Count_tuples!=0){
                        predicted_numerator_array[h] = 
                                ((double) Immediate_Friends_distribution_a2[h] / (double) Count_tuples);
//                            }
//                            else{
//                                predicted_numerator_array[h] = -1;
//                            }
                        temp_numerator_array_a2 = temp_numerator_array_a2 + predicted_numerator_array[h] + " ";
                    }
                    //Print immediate friends for attribute 2 of the hidden Node
//                        System.err.println("Immediate_Friends_distribution_a2 of Node" + (Hidden_Users[i])+ " are = " 
//                                + temp_imm_Friends_a2);
//                        System.err.println("numerator_array = "+ temp_numerator_array_a2);
//                        System.out.println("Total Tuples a2 = " + Count_tuples);

                    for (int h = 0; h < a2_length; h++) {
                        if (h == a2_of_Hidden_Node) {
                            error_Result_array[h] = Math.abs(1 - predicted_numerator_array[h]);
                            total_Error = total_Error + error_Result_array[h];
                            temp_error_Result_array = temp_error_Result_array + error_Result_array[h] + " ";
                        } else {
                            error_Result_array[h] = Math.abs(0 - predicted_numerator_array[h]);
                            total_Error = total_Error + error_Result_array[h];
                            temp_error_Result_array = temp_error_Result_array + error_Result_array[h] + " ";
                        }
                    }
                    total_Error_array[i] = total_Error;
                    
                    String tempStr = String.valueOf(total_Error);
//                    System.out.println(total_Error_array[i]);
                    if(tempStr.equals("NaN")){
//                        System.out.println("Pasha");
                        NAN_Count++;
                    }
                    else{
//                        df.format(total_Error);
//                        double b = b.setScale(2, RoundingMode.HALF_UP);
//                        double newKB = Math.round(total_Error*100.0)/100.0;
                        
                        if(total_Error_array[i] <= 2){
                            final_total_error += (double)total_Error_array[i];
                            final_total_error += total_Error_array[i];
//                            System.out.println(final_total_error);
                        }

                    }
                    
                    
//                    System.err.println(total_Error_array[i]);
                        //File Writting Logic start here!!
//                    String file_writting = ""+ total_Error_array[i];
//                    try {
//                        bufferedWriter.write(file_writting);
//                        bufferedWriter.newLine();
//                    } catch (IOException ex) {
//                        System.out.println("Error writing to file '" + writeFileName + "'");
//                    }
//                        int[] Immediate_Friends_distribution_a1_row_sum=new int[Immediate_friends.size()];
//                        int[] Immediate_Friends_distribution_a2_row_sum=new int[Immediate_friends.size()];
                }//Hidden count loop (i) ends here
                
                //Bining Logic Start here
                String[] Unique_Bin__Distribution_Values = print_occurence_Count(Bin_Distribution_Array);
                for(int i=0; i<Unique_Bin__Distribution_Values.length; i++){
                    System.out.println("Unique Bin Values = " + Unique_Bin__Distribution_Values[i]);
                }
                int a2_of_Hidden_Node_new;
                
                
                double[] UniqueValues_probability = new double[a2_length];
                
                // Bining of unique tuples and getting there Gender!!
                for (int i = 0; i < Unique_Bin__Distribution_Values.length; i++) //Loop for unique types
                {
                    int temp_occurence = 0;
                    int[] UniqueValues_Count = new int[Unique_Bin__Distribution_Values.length];
                    int[] UniqueValues_a2 = new int[a2_length];
                    for (int j = 0; j < Hidden_Count; j++) //Loop for every Node
                    { 
                        if (Unique_Bin__Distribution_Values[i].equals(Bin_Distribution_Array[j])) {
                            a2_of_Hidden_Node_new = indexOf(a2[(Hidden_Users[j] - 1)], a2_distinct);
                            UniqueValues_a2[a2_of_Hidden_Node_new]++;
                            UniqueValues_Count[i]++;
                            temp_occurence++;
                        }
                    }
                    System.err.println("Ahmed = " + temp_occurence);
                    for (int k = 0; k < a2_length; k++) {
                        UniqueValues_probability[k] = ((double) UniqueValues_a2[k] / (double) temp_occurence);
                    }
                    String temp_a2 = "";
                    for (int h = 0; h < a2_length; h++) {
                        temp_a2 = temp_a2 + UniqueValues_probability[h] + " ";
                    }
                    System.out.println("Unique value Gender normalized = " + temp_a2);
                }
                // Bining of unique tuples and getting there Gender!!  (Ends Here)
               
                
//                for(int i = 0; i < UniqueValues_Count.length; i++){
//                    System.out.println("Unique value count = " + UniqueValues_Count[i]);
//                }
//                double arraySum = Array_sum(total_Error_array);
                System.out.println("NAN count = " + NAN_Count);
//                System.err.println(final_total_error);
                bufferedWriter.close();
            } // inner loop end here
        } // Outer loop end here
    } // Main function Ends here

    
    
    public static String[] print_occurence_Count(String[] array) {

        Map<String, Integer> hm = new HashMap();
        for (String x : array) {

            if (!hm.containsKey(x)) {
                hm.put(x, 1);
            } else {
                hm.put(x, hm.get(x) + 1);
            }
        }
//    System.out.println(hm);
        Collection c = hm.keySet();
        Iterator itr = c.iterator();
        String[] uniqueValues = new String[c.size()];
        int i = 0;
        while (itr.hasNext()) {
            uniqueValues[i] = itr.next().toString();
//            System.out.println(uniqueValues[i]);
            i++;
        }
        return uniqueValues;
    }
    
    public static int finding_Mode_value(double[] max_array) {
        int maxValue = 0, maxCount = 0;

        for (int i = 0; i < max_array.length; ++i) {
            int count = 0;
            for (int j = 0; j < max_array.length; ++j) {
                if (max_array[j] != 0) {
                    if (max_array[j] == max_array[i]) {
                        ++count;
                    }
                }
            }
            if (count > maxCount) {
                maxCount = count;
                maxValue = (int) max_array[i];
            }
        }

        return maxValue;
    }

    public static void print_array(int[] printing, String Name) {
        for (int i = 0; i < printing.length; i++) {
            System.out.println("Array = " + printing[i]);
        }
    }

    public static double findLargest(double array[]) {
        double largest = array[0];
        double largestIndex = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] > largest) {
                largest = array[i];
                largestIndex = i;
            }
        }

        return largestIndex;
    }

    public static int computing_attribute_Value_Hidden_Node_a1(int friends_attribute, int[] a1_distinct) {
        int Node;
        int index_Value = 0;
//        int distribution=new int[a1_distinct.length];
//        for(int i=0; i<friends_attribute.size(); i++){
        Node = Integer.valueOf((int) friends_attribute);
        if (Node != 0) {
            index_Value = indexOf(a1[Node - 1], a1_distinct); // -1 is because value start from 0 in java rather than 1
//         System.err.println("Array length = " + friends_attribute.size() + " Node = " + Node + " Attribute value = "+a2[Node-1]+
//                 " AND Index Value = " + index_Value + " array distinct values = " + a2_distinct[0]
//         + " array distinct values = " + a2_distinct[1]
//         + " array distinct values = " + a2_distinct[2]);
//         distribution[index_Value]++;
//         a2_distinct[a2[Node]];
        }
//        }
        return index_Value;
    }

    public static int computing_attribute_Value_Hidden_Node_a2(int friends_attribute, int[] a2_distinct) {
        int Node;
        int index_Value = 0;
//        int distribution=new int[a1_distinct.length];
//        for(int i=0; i<friends_attribute.size(); i++){
//         Node =   Integer.valueOf((int)friends_attribute);
//         if(Node!=0){
        index_Value = indexOf(a2[friends_attribute], a2_distinct); // -1 is because value start from 0 in java rather than 1
//         System.err.println("Array length = " + friends_attribute.size() + " Node = " + Node + " Attribute value = "+a2[Node-1]+
//                 " AND Index Value = " + index_Value + " array distinct values = " + a2_distinct[0]
//         + " array distinct values = " + a2_distinct[1]
//         + " array distinct values = " + a2_distinct[2]);
//         distribution[index_Value]++;
//         a2_distinct[a2[Node]];
//         }
//        }
        return index_Value;
    }

    public static int[] computing_attribute_Value_a1(ArrayList friends_attribute, int[] a1_distinct) {
        int Node;
        int index_Value;
        int[] distribution = new int[a1_distinct.length];
        for (int i = 0; i < friends_attribute.size(); i++) {
            Node = Integer.valueOf((int) friends_attribute.get(i));
            if (Node != 0) {
                index_Value = indexOf(a1[Node - 1], a1_distinct); // -1 is because value start from 0 in java rather than 1
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

    public static int[] computing_attribute_Value_a2(ArrayList friends_attribute, int[] a2_distinct) {
        int Node;
        int index_Value;
        int[] distribution = new int[a2_distinct.length];
        for (int i = 0; i < friends_attribute.size(); i++) {
            Node = Integer.valueOf((int) friends_attribute.get(i));
            if (Node != 0) {
                index_Value = indexOf(a2[Node - 1], a2_distinct); // -1 is because value start from 0 in java rather than 1
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
        double[][] new_Row_Normalized_Mixing_Matrix = new double[a1_length][a2_length];
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
        double temp = 0;
        double temp2 = 0;
        float twoDigitsF;
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        for (int i = 0; i < a1_length; i++) {
            for (int j = 0; j < a2_length; j++) {
                if (Row_Normalized_Mixing_Matrix[i][j] != 0) {
                    temp = (double) Row_Normalized_Mixing_Matrix[i][j] / row_sum[i];
                    temp2 = Math.round(temp * 100) / 100.0; // getting upto 2 decimal points

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

    public static ArrayList Friend_Finder(int attribute1, int attribute2, int Hidden_Node) {
        //        Hidden_Users;
//            int[] friends;
        ArrayList friends = new ArrayList();
        for (int i = 0; i < edgeList_Array_Count; i++) {
            if (edgeList_Array[i][0] != edgeList_Array[i][1]) {
                if (edgeList_Array[i][0] == Hidden_Node) {
                    friends.add(edgeList_Array[i][1]);
                }
                if (edgeList_Array[i][1] == Hidden_Node) {
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
                        if (a1[Friend1 - 1] != 0) { // -1 is because value start from 0 in java rather than 1
//                                    System.err.println("Value 1 = "+ edgeList_Array[i][attribute1] + " Value 2 = " + 
//                                 edgeList_Array[i][attribute2]);
                            if (a2[Friend2 - 1] != 0) { // -1 is because value start from 0 in java rather than 1

                                Friend1_index = indexOf(a1[Friend1 - 1], a1_distinct); // -1 is because value start from 0 in java rather than 1
                                Friend2_index = indexOf(a2[Friend2 - 1], a2_distinct); // -1 is because value start from 0 in java rather than 1
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
