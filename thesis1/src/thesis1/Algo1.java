/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thesis1;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;
import static oracle.jrockit.jfr.events.Bits.intValue;

/**
 *
 * @author Pasha
 */
public class Algo1 {

    //Caltech Dataest
    static int Node_Count = 770;
    static int edgeList_Array_Count = 33313;
    static int Total_random_Edges = 10;
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
    
    
//    static int[] Hidden_Users = {3};
    static int[] Hidden_Users = IntStream.rangeClosed(1,Node_Count).toArray();
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
        System.out.println("Number of vertices in G " + numNodes + "  " + (numEdges/2));

        // create instance of Random class
        Random rand = new Random();
        //Generate random edges
        int[] rand_Edge=new int[Total_random_Edges];
        
        for(int i=0; i<Total_random_Edges;i++){
            rand_Edge[i] = rand.nextInt(edgeList_Array_Count);
            System.out.println("Randomly choosen edge is = " + rand_Edge[i]);
        }
        int[][] Actual_Edge_Values = new int[Total_random_Edges][2];
        int[][] Predicted_Edge_Values = new int[Total_random_Edges][2];
        
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
                System.err.println("a1 distinct = " + temp_a1_distinct);
                System.err.println("a2 distinct = " + temp_a2_distinct);
                
                int a1_length = a1_distinct.length;
                int a2_length = a2_distinct.length;


                int[][] Mixing_Matrix = new int[a1_length][a2_length];
                double[][] Row_Normalized_Mixing_Matrix = new double[a1_length][a2_length];
                double[][] Edge_Prediction_Matrix = new double[a1_length][a2_length];
                edgeList_Array = G.get_edgeList_Array();
                
                String temp_String_Edges="";
                for(int i=0; i<Total_random_Edges; i++){
                    Actual_Edge_Values[i][0] = edgeList_Array[rand_Edge[i]][0];
                    Actual_Edge_Values[i][1] = edgeList_Array[rand_Edge[i]][1];
                    temp_String_Edges = temp_String_Edges + Actual_Edge_Values[i][0] + " " + Actual_Edge_Values[i][1] + "\n";
                }
                System.out.println("Actual edges = " + temp_String_Edges);
                //calling function to generate mixing matrix
                Mixing_Matrix = Generated_Mixing_Matrix(attribute1, attribute2, a1_length, a2_length, a1_distinct, a2_distinct);

                Row_Normalized_Mixing_Matrix = Normalized_Mixing_Matrix(Mixing_Matrix,a1_length,a2_length,attribute1,attribute2);
                String temp_Edges_Normalized = "";
                String temp_MM = "";
                for (int i = 0; i < a1_length; i++) {
                    for (int j = 0; j < a2_length; j++) {
                        
                        Edge_Prediction_Matrix[i][j] = ((double)Mixing_Matrix[i][j]/edgeList_Array_Count);
                        temp_Edges_Normalized = temp_Edges_Normalized + Edge_Prediction_Matrix[i][j] + " ";
                        temp_MM = temp_MM + Mixing_Matrix[i][j] + " ";
//                    System.err.println(Row_Normalized_Mixing_Matrix[i][j]);
                    }
                    temp_Edges_Normalized = temp_Edges_Normalized + "\n";
                    temp_MM = temp_MM + "\n";
                }
                System.err.println(temp_MM);
                System.err.println(temp_Edges_Normalized);
                //Calling function to find friends of hidden node
                ArrayList Immediate_friends = new ArrayList();
                ArrayList Friends_of_friend = new ArrayList();
                
                ArrayList hidden_User = new ArrayList();
//                Immediate_friends = Friend_Finder(attribute1, attribute2, Hidden_Users);
//                Collections.sort(Immediate_friends);
//              System.out.println("New Contents of friends: " + Immediate_friends);

                //Class wise Computing predicted attribute
                int[] distribution=new int[a2_distinct.length];
                int[] Immediate_Friends_distribution_a1=new int[a1_distinct.length];
                int[] Immediate_Friends_distribution_a2=new int[a2_distinct.length];
                int[] FOF_distribution=new int[a2_distinct.length];
                double[] FOF_distribution_normalized=new double[a2_distinct.length];
                
                double[] Immediate_Friends_distribution_normalized_a1=new double[a1_distinct.length];
                double[] Immediate_Friends_distribution_normalized_a2=new double[a2_distinct.length];
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
                int a1_of_Hidden_Node; //Status of hidden Node
                int a2_of_Hidden_Node; //Gender of hidden Node
                double[] predicted_numerator_array = new double[a2_distinct.length];
                double[] error_Result_array = new double[a2_distinct.length];
                int Count_tuples = 0;
                double[] total_Error_array = new double[Hidden_Count];
                
                for (int i = 0; i < Total_random_Edges; i++) {
                    double[] Final_FOF_distribution_normalized=new double[a2_distinct.length];
//                        System.err.println("Hidden User = " + a2[Hidden_Users[i]-1]);

                        // Contingency Table Logic Start Here ////////////////////////////////////////////////
                        first_attribute_index = indexOf(a1[Hidden_Users[i]-1], a1_distinct); // -1 is because value start from 0 in java rather than 1
                        second_attribute_index = indexOf(a2[Hidden_Users[i]-1], a2_distinct); // -1 is because value start from 0 in java rather than 1
                        Contingency_Table[first_attribute_index][second_attribute_index]++;
                        // Contingency Table Logic Ends Here ////////////////////////////////////////////////
                        
//                        for(int j=0; j<a1_length; j++){
//                            for(int k=0; k<a2_length; k++){
//                                Edge_Prediction_Matrix[j][k] = (Mixing_Matrix[j][k]/edgeList_Array_Count);
//                            }
//                        }
                        
                        a1_of_Hidden_Node = indexOf(a1[(Hidden_Users[i]-1)], a1_distinct); 
                        a2_of_Hidden_Node = indexOf(a2[(Hidden_Users[i]-1)], a2_distinct); 
                        //Print Status of hidden user
//                        System.out.println("Status of Hidden User " + (Hidden_Users[i]) +  " = " + a1_of_Hidden_Node);
//                        //Print Gender of hidden user
//                        System.out.println("Gender of Hidden User " + (Hidden_Users[i]) +  " = " + a2_of_Hidden_Node);
                        
                        Immediate_friends = Friend_Finder(attribute1, attribute2, (Hidden_Users[i])); // Immediate Friends of Node 3 (Hidden Node) -> y1, y2, ...
                        Collections.sort(Immediate_friends);
                        //print total immediate friends of hidden node
//                        System.err.println("Total Immediate Friends of User = " + (Hidden_Users[i]) 
//                                + " Are = " + Immediate_friends);

                        Immediate_Friends_distribution_a1 = computing_attribute_Value_a1(Immediate_friends, a1_distinct);
                        Immediate_Friends_distribution_a2 = computing_attribute_Value_a2(Immediate_friends, a2_distinct);
                        
                        String temp_imm_Friends_a1 = "";
                        for(int h=0; h<Immediate_Friends_distribution_a1.length; h++){
                            if(h==a1_of_Hidden_Node){
                                Count_tuples = Immediate_Friends_distribution_a1[h];
                            }
                            temp_imm_Friends_a1 = temp_imm_Friends_a1 + Immediate_Friends_distribution_a1[h] + " ";
                        }
                        //Print immediate friends for attribute 1 of the hidden Node
//                        System.err.println("Immediate_Friends_distribution_a1 of Node" + (Hidden_Users[i])+ " are = " 
//                                + temp_imm_Friends_a1);   
                        //Print total tuple size
//                        System.out.println("Total Tuples a1 = " + Count_tuples);
                        
                        String temp_imm_Friends_a2 = "";
                        String temp_numerator_array_a2 = "";
                        String temp_error_Result_array = "";
                        double total_Error = 0;
                        for(int h=0; h<Immediate_Friends_distribution_a2.length; h++){
//                            if(h==a2_of_Hidden_Node){
//                                Count_tuples = Immediate_Friends_distribution_a2[h];
//                            }
                            temp_imm_Friends_a2 = temp_imm_Friends_a2 + Immediate_Friends_distribution_a2[h] + " ";
//                            if(Count_tuples!=0){
                            predicted_numerator_array[h] = ((double)Immediate_Friends_distribution_a2[h] / (double)Count_tuples);
//                            }
//                            else{
//                                predicted_numerator_array[h] = 1;
//                            }
                            temp_numerator_array_a2 = temp_numerator_array_a2 + predicted_numerator_array[h] + " ";
                        }
                        //Print immediate friends for attribute 2 of the hidden Node
//                        System.err.println("Immediate_Friends_distribution_a2 of Node" + (Hidden_Users[i])+ " are = " 
//                                + temp_imm_Friends_a2);
//                        System.err.println("numerator_array = "+ temp_numerator_array_a2);
//                        System.out.println("Total Tuples a2 = " + Count_tuples);
                        
                        for(int h=0; h<a2_length; h++){
                            if(h==a2_of_Hidden_Node){
                               error_Result_array[h] = Math.abs(1 - predicted_numerator_array[h]);
                               total_Error = total_Error + error_Result_array[h]; 
                               temp_error_Result_array = temp_error_Result_array + error_Result_array[h] + " ";
                            }
                            else{
                                error_Result_array[h] = Math.abs(0 - predicted_numerator_array[h]);
                                total_Error = total_Error + error_Result_array[h]; 
                                temp_error_Result_array = temp_error_Result_array + error_Result_array[h]+ " ";
                            }
                        }
                        total_Error_array[i] = total_Error;
//                        System.err.println(total_Error_array[i]);
                        
                        
                        

                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
//                        int[] Immediate_Friends_distribution_a1_row_sum=new int[Immediate_friends.size()];
//                        int[] Immediate_Friends_distribution_a2_row_sum=new int[Immediate_friends.size()];
//                        //Computing row-sum for attribute a1
//                    for (int y = 0; y < Immediate_friends.size(); y++) {
//                        for (int h = 0; h < a1_distinct.length; h++) {
//                            if (h != 0) {
//                                Immediate_Friends_distribution_a1_row_sum[y] = Immediate_Friends_distribution_a1_row_sum[y]
//                                        + Immediate_Friends_distribution_a1[h];
//                            }
//                        }
//                    }
//                    
//                    //Computing row-sum for attribute a2
//                    for (int y = 0; y < Immediate_friends.size(); y++) {
//                        for (int h = 0; h < a2_distinct.length; h++) {
//                            if (h != 0) {
//                                Immediate_Friends_distribution_a2_row_sum[y] = Immediate_Friends_distribution_a2_row_sum[y]
//                                        + Immediate_Friends_distribution_a2[h];
//                            }
//                        }
//                    }
//                    
//                    //Computing Normalized friends distribution for attribute a1
//                    for (int y = 0; y < Immediate_friends.size(); y++) {
//                        for (int h = 0; h < a1_distinct.length; h++) {
//                            if (h != 0) {
//                                if (Immediate_Friends_distribution_a1_row_sum[y] != 0) {
////                                    System.err.println("FOF_distribution_normalized = " + FOF_distribution[h]);
//                                    Immediate_Friends_distribution_normalized_a1[h]
//                                            = ((double) Immediate_Friends_distribution_a1[h] / Immediate_Friends_distribution_a1_row_sum[y]) * 100;
//                                } else {
//                                    Immediate_Friends_distribution_normalized_a1[h] = 0;
//                                }
//                            }
////                                System.err.println("finding_Max_Vote_class = " + Immediate_Friends_distribution_normalized[h]);
//                        }
//                    }
//                    
//                    //Computing Normalized friends distribution for attribute a2
//                    for (int y = 0; y < Immediate_friends.size(); y++) {
//                        for (int h = 0; h < a2_distinct.length; h++) {
//                            if (h != 0) {
//                                if (Immediate_Friends_distribution_a2_row_sum[y] != 0) {
////                                    System.err.println("FOF_distribution_normalized = " + FOF_distribution[h]);
//                                    Immediate_Friends_distribution_normalized_a2[h]
//                        = ((double) Immediate_Friends_distribution_a2[h] / Immediate_Friends_distribution_a2_row_sum[y]) * 100;
//                                } else {
//                                    Immediate_Friends_distribution_normalized_a2[h] = 0;
//                                }
//                            }
////                                System.err.println("finding_Max_Vote_class = " + Immediate_Friends_distribution_normalized[h]);
//                        }
//                    }
//                    
//                    
//                    //Printing immidiate friends distribution for attribute a1
//                        String temp_imm_Friends_Distribution_a1 = "";
//                        for(int h=0; h<Immediate_Friends_distribution_a1.length; h++){
//                            temp_imm_Friends_Distribution_a1 = temp_imm_Friends_Distribution_a1 + 
//                                    Immediate_Friends_distribution_normalized_a1[h] + " ";
//                        }
//                         System.err.println("Immediate_Friends_distribution of Node" + (Hidden_Users[i])+ " And attribute" +
//                                 temp_attr_1 +" are = " +  
//                                 temp_imm_Friends_Distribution_a1);   
////                            System.err.println(temp_imm_Friends_Distribution_a1); 
//
//
//                    //Printing immidiate friends distribution for attribute a2
//                        String temp_imm_Friends_Distribution_a2 = "";
//                        for(int h=0; h<Immediate_Friends_distribution_a2.length; h++){
//                            temp_imm_Friends_Distribution_a2 = temp_imm_Friends_Distribution_a2 + 
//                                    Immediate_Friends_distribution_normalized_a2[h] + " ";
//                        }
//                         System.err.println("Immediate_Friends_distribution of Node" + (Hidden_Users[i])+ " And attribute" +
//                                 temp_attr_2 +" are = " + 
//                                 temp_imm_Friends_Distribution_a2);   
////                            System.err.println(temp_imm_Friends_Distribution_a2); 
//
//
//                        double[] temp_predicted_Value = new double[Immediate_friends.size()];
//                        int[] Predicting_Attribute_Counter = new int[a2_distinct.length];
//                        int index_count;
//                        int temp3;
//                        double[] vote = new double[a2_length];
//                        double[] final_vote = new double[Immediate_friends.size()];
//                        double[][] prediction_Final= new double[Immediate_friends.size()][a2_length];
//                        
////                        int[] finding_Max_Vote_class = new int[a2_distinct.length];
//                        //Loop to find friends of friend (y1, y2, .....)
//                        int[] FOF_distribution_row_sum=new int[Immediate_friends.size()];
//                        
//                        
//                        for (int j = 0; j < Immediate_friends.size(); j++) {
//                             
//                            //Find FOF of each friend Friend
//                            Friends_of_friend = Friend_Finder(attribute1, attribute2, (Integer) Immediate_friends.get(j)); //y1, y2, ....
//                            Collections.sort(Friends_of_friend); //Sorting Arraylist
////                            System.err.println("Length of FOF = " + Friends_of_friend.size() +" AND Total FOF of friend = " + 
////                                    Immediate_friends.get(j) + " Are = " + Friends_of_friend);
//                            // Find classe for predicting attribute
//                            FOF_distribution = computing_attribute_Value_a2(Friends_of_friend, a2_distinct); //finding_Max_Vote_class
////                            Arrays.sort(FOF_distribution); // Sorting array
//                            for (int h = 0; h < a2_distinct.length; h++) {
////                                System.err.println("finding_Max_Vote_class for friend " + (Integer) Immediate_friends.get(j)
////                                        + " = Total friends = " + 
////                                        Friends_of_friend.size() + " AND Total Gender of friend = "
////                                        + FOF_distribution[h]);
//                                if (h != 0) {
//                                    FOF_distribution_row_sum[j] = FOF_distribution_row_sum[j] + FOF_distribution[h];
//                                }
//
//                            }
//                            String tmp_FOF_distribution_normalized = "";
//                            for (int h = 0; h < a2_distinct.length; h++) {
//                                if (h != 0 && (double)FOF_distribution[h] !=0 && (double)FOF_distribution_row_sum[j]!=0) {
////                                    System.err.println("FOF_distribution_normalized for Node = " + i + " "  + FOF_distribution[h]);
//                                    FOF_distribution_normalized[h] = (double)FOF_distribution[h]/ (double)FOF_distribution_row_sum[j];
//                                    tmp_FOF_distribution_normalized = tmp_FOF_distribution_normalized + 
//                                            FOF_distribution_normalized[h] + " ";
//                                    Final_FOF_distribution_normalized[h] = Final_FOF_distribution_normalized[h] + 
//                                            FOF_distribution_normalized[h];
////                                    System.err.println("finding_Max_Vote_class for Node" + i +  " = " + 
////                                            FOF_distribution_normalized[h]);
//                                }
////                                System.err.println("finding_Max_Vote_class = " + FOF_distribution_normalized[h]);
//                            }
//                            
//                            
////                            System.err.println("FOF Distribution for Node " + i + " with friend "+ Immediate_friends.get(j) + " are = " + 
////                              tmp_FOF_distribution_normalized);
//                            for(int s=0;s<a1_length;s++){
//                                String vote2="";
//                            for (int b = 0; b < a2_length; b++) {
//                                
////                                int index=(find(a1(j)==a1_distinct));
////                                int index = indexOf(a1[j], a1_distinct);
////                                int index = indexOf(a1[(Integer) Immediate_friends.get(j)], a1_distinct);
////                                for (int v = 0; v < a1_length; v++) {
////                                    System.err.println("FOF_distribution_normalized is =  " + FOF_distribution_normalized[b]);
//                                    prediction_Final[j][b] =  (FOF_distribution_normalized[b] * 
//                                            Row_Normalized_Mixing_Matrix[s][b]);
////                                }
//                                vote[b] = prediction_Final[j][b];
//                                vote2 = vote2 + vote[b] + " ";
//                            }
////                            System.err.println("Vote = " + vote2);
//                            }
////                            System.err.println("Predicted final = " + prediction_Final[j][0] + " "+ prediction_Final[j][1] + 
////                                       " "+ prediction_Final[j][2] + " "+ prediction_Final[j][3]);
//                            
//                            
//                            final_vote[j] = findLargest(vote);
////                           System.err.println("Largest = " + final_vote[j]);
////                            System.err.println("Votes for = " + j + " Are = " + final_vote[j]);
////                            for (int b = 0; b < Immediate_friends.size(); b++) {
////                                System.err.println("Votes for = " + b + " Are = " + final_vote[b]);
////                            }
//                            double q = 2;
//                            temp_predicted_Value[j] = q;
//                        }//Loop for FOF finder ends here (y1)
//                        double Final_FOF_Row_Sum = 0;
//                        for (int h = 0; h < a2_distinct.length; h++) {
//                                if (h != 0) {
//                                    Final_FOF_Row_Sum = Final_FOF_Row_Sum + Final_FOF_distribution_normalized[h];
////                                  System.err.println("Final FOF Value = " + Final_FOF_distribution_normalized[h]);
//                                }
//                            }
//                        String Final_FOF_distibution = "";
//                        for (int h = 0; h < a2_distinct.length; h++) {
//                                if (h != 0) {
//                                    Final_FOF_distibution = Final_FOF_distibution + 
//                                            ((Final_FOF_distribution_normalized[h] / Final_FOF_Row_Sum)*100) + "    ";
////                                     System.err.println("First =  " + Final_FOF_distribution_normalized[h] + " / " 
////                                             + Final_FOF_Row_Sum);
//                                }
//                            }
////                        Print FOF distribution of predicted attribute
////                        System.err.println(Final_FOF_distibution);
//
////                       System.err.println("Final FOF Distribution for Node " + i + " = " + Final_FOF_distibution);
////                        System.err.println("FOF Distribution for Node " + i + " are = " + 
////                              tmp_FOF_distribution_normalized);
//
////                        for (int b = 0; b < final_vote.length; b++) { 
////                                   System.err.println("Final Vote = " + final_vote[b]);
////                               }
////                        Final_predicted_Value[i] = ss findLargest(final_vote);
//                        Final_predicted_Value[i] = finding_Mode_value(final_vote);
////                        System.err.println("Mode " + i + " is = " + Final_predicted_Value[i]);
//
//                        
////                        print_array(Predicting_Attribute_Counter);
//                        double[] temp_Prediction = new double[a2_length];
//                        int sum = IntStream.of(Predicting_Attribute_Counter).sum();
//                        for (int qwe = 1; qwe < Predicting_Attribute_Counter.length; qwe++) {
//                            temp_Prediction[qwe] = (double)(Predicting_Attribute_Counter[qwe]* 100) / sum;
//                        }
////                        for(int temp4=0; temp4<Predicting_Attribute_Counter.length; temp4++){
////                            System.err.println("temp_Prediction Array = " + temp_Prediction[temp4]);
////                        }
//                        
//
//                        for (int n = 0; n < a2_length; n++) {
//                            if (n == 0) {
//
//                            } else {
//                                FOF_Total_Sum = FOF_Total_Sum + predicting_Attribute_of_FOF[n];
//                            }
//                        }
////                    System.err.println("Total Friends of friend for user = " + i + " = " + Friends_of_friend);
////                        Final_predicted_Value[i] = findLargest(temp_predicted_Value);
//                    
//                    Actual_Value[i] = a2[Hidden_Users[i]-1]; // True classes
//                } // Hidden count loop ends here
//                int[] unique_Actual_Values = toUniqueArray(Actual_Value);
//                
//               int[][] Confusion_Matrix= new int[a2_length][a2_length]; // Initializing Confusion matrix with zero's
////                for (int i=0; i<predicting_Attribute_of_FOF.length; i++)
////                {
////                    System.err.println("Total FOF Gender Distribution = " + predicting_Attribute_of_FOF[i]);
////                }
//                double success = 0;
//                double failure = 0;
//                double accuracy = 0;
//                int row_index;
//                int column_index;
//                for(int i=0; i<Hidden_Count; i++){
//                    
//                    row_index = indexOf((int)Final_predicted_Value[i], a2_distinct);
////                    System.out.println("row_index for node " + i + " = " + row_index);
//                    column_index = indexOf(Actual_Value[i], a2_distinct);
//                    if(row_index>=0 &&column_index>=0){
//                    Confusion_Matrix[row_index][column_index]++; 
//                    }
////                    System.err.println("Actual Value = " + a2[Hidden_Users[i]-1]);
////                    System.err.println("Predicted Value = " + (int)Final_predicted_Value[i]);
//                    if((int)Final_predicted_Value[i] == a2[Hidden_Users[i]-1]){
//                        success++;
//                    }
//                    else{
//                        failure++;
//                    }
//                    
//                }
//                //Printing Confusion Matrix
//                
//                for(int i=0; i<a2_length; i++){
//                    String Confusion = "";
//                    for(int f=0; f<a2_length; f++){
//                        if(i!=0 && f!=0){
//                        Confusion = Confusion + Confusion_Matrix[i][f] + " ";
//                        }
//                    
//                    }
//                    System.out.println("Confusion Matrix = " +  Confusion);
//                }
//                System.err.println("Success = "  + success + " Failure = " + failure);
//                accuracy = (success / (success + failure)) * 100;
//                System.err.println("accuracy = "  + accuracy );
//                //Printing Contingency Table
//                for (int i=0; i<a1_distinct.length; i++)
//                {
//                    String Contingency = "";
//                    for (int f=0; f<a2_distinct.length; f++)
//                {
//                    if(i!=0 && f!=0){
//                    Contingency = Contingency + Contingency_Table[i][f] + " ";
//                    }
//                    
//                }
//                    System.err.println("Contingency Table = " + Contingency);
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
                if (max_array[j] == max_array[i])
                    ++count;
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

    public static int computing_attribute_Value_Hidden_Node_a1(int friends_attribute, int[] a1_distinct){
        int Node;
        int index_Value=0;
//        int distribution=new int[a1_distinct.length];
//        for(int i=0; i<friends_attribute.size(); i++){
         Node =   Integer.valueOf((int)friends_attribute);
         if(Node!=0){
         index_Value = indexOf(a1[Node-1], a1_distinct); // -1 is because value start from 0 in java rather than 1
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
    
        public static int computing_attribute_Value_Hidden_Node_a2(int friends_attribute, int[] a2_distinct){
        int Node;
        int index_Value=0;
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
    
    
    public static int[] computing_attribute_Value_a1(ArrayList friends_attribute, int[] a1_distinct){
        int Node;
        int index_Value;
        int[] distribution=new int[a1_distinct.length];
        for(int i=0; i<friends_attribute.size(); i++){
         Node =   Integer.valueOf((int)friends_attribute.get(i));
         if(Node!=0){
         index_Value = indexOf(a1[Node-1], a1_distinct); // -1 is because value start from 0 in java rather than 1
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
    
    
    public static int[] computing_attribute_Value_a2(ArrayList friends_attribute, int[] a2_distinct){
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
