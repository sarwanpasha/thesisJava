package thesis1;

import com.sun.glass.ui.Size;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
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
public class Algo4_With_5_Bins {
    static int result=0;
//    //Caltech Dataest
//    static int Node_Count = 769;
//    static int edgeList_Array_Count = 33313;
//    //Caltech Dataest

     //Rice Dataest
    static int Node_Count = 4088;
    static int edgeList_Array_Count = 369657;
    //Rice Dataest
    
//         //American75 Dataest
//    static int Node_Count = 6387;
//    static int edgeList_Array_Count = 435325;
//    //American75 Dataest
    
//    static int Node_Count = 6;
//    static int edgeList_Array_Count = 6;
////    //
////    //caltech Dataset Attributed Matrix Path
//    static String attribute_Path = "E:\\MS Computer Science\\MS Thesis\\Java Code\\thesis1\\src\\thesis1\\caltech_attributes.txt";
////    //caltech Dataset Edge list path
//    static String Path = "E:\\MS Computer Science\\MS Thesis\\Java Code\\thesis1\\src\\thesis1\\Caltech36_edgelist2.txt";
////     static String Path = "E:\\MS Computer Science\\MS Thesis\\Java Code\\thesis1\\src\\thesis1\\Caltech36_edgelist.txt";
////
//        //Rice Dataset Attributed Matrix Path
    static String attribute_Path = "E:\\MS Computer Science\\MS Thesis\\Java Code\\thesis1\\src\\thesis1\\Rice_attributes.txt";
//    //Rice Dataset Edge list path
    static String Path = "E:\\MS Computer Science\\MS Thesis\\Java Code\\thesis1\\src\\thesis1\\Rice31_edgelist.txt";
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
//    static int[] Hidden_Users = { 7, 9, 11, 13, 684,704, 712, 725,  743,  751};
    static int[] Hidden_Users = IntStream.rangeClosed(1, Node_Count).toArray();
    
    static int Bin_1 = 20;
    static int Bin_2 = 40;
    static int Bin_3 = 60;
    static int Bin_4 = 80;
    static int Bin_5 = 100;

//static int[] Hidden_Users = IntStream.rangeClosed(1,100).toArray();

//    static int[] Hidden_Users_range = IntStream.rangeClosed(1, 769).toArray();
    static int Hidden_Count = Hidden_Users.length;

    static String[] Bin_Distribution_Array=new String[Hidden_Count];
    static String[] Global_unique_Occurences;
    static int[] Global_a1=new int[Hidden_Count];
    static int[] Global_a2=new int[Hidden_Count];
//    
//    static int success = 0;
//    static int failure = 0;
//    static int[] a1_new = new int[Node_Count];
//    static int[] a2_new = new int[Node_Count];
    
    public static void main(String[] args) throws IOException {
        Five_Bin_Function();
        
    } // Main function Ends here
        
    public static void Five_Bin_Function() throws IOException{
        System.err.println("Welcome!! 5 Bin Logic");
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
        int attributeCount = 7; // Cannot go more then 7
        String[] Sucess_Matrix_Full = new String[attributeCount];
        for (outerLoop = 0; outerLoop < attributeCount; outerLoop++) {
             String Sucess_Matrix = "";
            for (innerLoop = 0; innerLoop < attributeCount; innerLoop++) {
                attribute1 = outerLoop;
                attribute2 = innerLoop;
                // 0 Status (5 min values)
                // 1 Gender (3 min values)
                // 2 Major (31 min values)
                // 3 Minor (36 min values)
                // 4 Dorm (9 min values)
                // 5 Graduation year (18  min values)
                // 6 High School (501  min values)
//                attribute1 = 4; // Status
//                attribute2 = 3; // Gender
                attributes_Names att = new attributes_Names();
                att.set_attribute_Name(attribute1, attribute2);
                String temp_attr_1 = att.get_attribute_1();
                String temp_attr_2 = att.get_attribute_2();
                System.err.println("Predicting Attribute " + temp_attr_2 + " using " + temp_attr_1);
                
//                String   writeFileName1 = 
//                		"E:/MS Computer Science/MS Thesis/Java Code/errorResults/Rice/5Bins/";
                String   writeFileName1 = 
                		"E:/MS Computer Science/MS Thesis/Java Code/errorResults/temp/";
                String   writeFileName = writeFileName1 + temp_attr_2 + "_using_" + temp_attr_1 + ".txt";
                
                FileWriter fileWriter = new FileWriter(writeFileName);
   	        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                
//                System.err.println("Attribute 1 = " + attribute1 + " Which is = " + temp_attr_1);
//                System.err.println("Attribute 2 = " + attribute2 + " Which is = " + temp_attr_2);

                String temp_name_str = "Predicting " + temp_attr_2 + " using " + temp_attr_1;
//                String writeFileName
//                        = "C:/Users/Pasha/Desktop/error results/"+ temp_name_str+ ".txt";
//                FileWriter fileWriter = new FileWriter(writeFileName);
//                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//                for (int i = 0; i < Node_Count - 1; i++) {
//                    a1_new[i] = Integer.parseInt(attributeMatrix[i][attribute1]);
//                    a2_new[i] = Integer.parseInt(attributeMatrix[i][attribute2]);
//                }
//                int[] a1 = removeZeros(a1_new);
//                int[] a2 = removeZeros(a2_new);
//                System.out.println("AttributeMatrix length = " + attributeMatrix.length);
                String[] a2_str = new String[a2.length];
                for (int i = 0; i < Node_Count; i++) {

                    a1[i] = attributeMatrix[i][attribute1];
                    a2[i] = attributeMatrix[i][attribute2];
                    
                    a2_str[i] = String.valueOf(a2[i]);
//                    System.out.println("A1 = " + a1[i] + " A2 = " + a2[i]);
                }
                
//                String[] unique_a2_str = print_occurence_Count(a2_str);
//                String a2_str_temp = "";
//                for (int i = 0; i < unique_a2_str.length; i++) {
//                    a2_str_temp = a2_str_temp + unique_a2_str[i] + " ";
//                }
                //Print dataset stats for unique attribute values!!!
//                System.err.println("Unique A2 Values = " + a2_str_temp);
                
                
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
//                System.err.println("a1 distinct = " + temp_a1_distinct);
//                System.err.println("a2 distinct length = " + temp_a2_distinct);

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
                    //Display mixing matrix!!!
//                    System.err.println(temp_MM);
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
                double[] total_Error_array_L1 = new double[Hidden_Count];
                double[] total_Error_array_L2 = new double[Hidden_Count];
                double[] total_Error_array_L3 = new double[Hidden_Count];
                double total_predicted_Error_Count_L1=0;
                double total_predicted_Error_Count_L2=0;
                double total_predicted_Error_Count_L3=0;
                double total_Actual_Error_Count=(a2_distinct.length-1)*Hidden_Count;
                double final_total_error=0;
                int NAN_Count=0;
                int [] Friends_Bin_Array = new int[a1_length];
               
                String[] Immediate_Friends_distribution_a1_str_array = new String[Hidden_Count];
                for (int i = 0; i < Hidden_Count; i++) {
                   String Look_up = "";  
//                    double[] Final_FOF_distribution_normalized = new double[a2_distinct.length];
//                        System.err.println("Hidden User = " + Hidden_Users[i]);

                    // Contingency Table Logic Start Here ////////////////////////////////////////////////
                    first_attribute_index = indexOf(a1[Hidden_Users[i] - 1], a1_distinct); // -1 is because value start from 0 in java rather than 1
                    second_attribute_index = indexOf(a2[Hidden_Users[i] - 1], a2_distinct); // -1 is because value start from 0 in java rather than 1
                    Contingency_Table[first_attribute_index][second_attribute_index]++;
                    // Contingency Table Logic Ends Here ////////////////////////////////////////////////

                    a1_of_Hidden_Node = indexOf(a1[(Hidden_Users[i] - 1)], a1_distinct);
                    a2_of_Hidden_Node = indexOf(a2[(Hidden_Users[i] - 1)], a2_distinct);
                    //Print Status of hidden user
//                        System.out.println("Status of Hidden User " + (Hidden_Users[i]-1) +  " = " + 
//                                a1_distinct[a1_of_Hidden_Node]);
                        //Print Gender of hidden user
//                        System.out.println("Gender of Hidden User " + (Hidden_Users[i]-1) +  " = " + 
//                                a2_distinct[a2_of_Hidden_Node]);
                        Global_a1[i] = a1_distinct[a1_of_Hidden_Node];
                        Global_a2[i] = a2_distinct[a2_of_Hidden_Node];

                    Immediate_friends = Friend_Finder(attribute1, attribute2, (Hidden_Users[i])); // Immediate Friends of Node 3 (Hidden Node) -> y1, y2, ...
                    Collections.sort(Immediate_friends);
                    //print total immediate friends of hidden node
//                        System.err.println("Total Immediate Friends of User = " + (Hidden_Users[i]) 
//                                + " Are = " + Immediate_friends);

                    Immediate_Friends_distribution_a1 = computing_attribute_Value_a1(Immediate_friends, a1_distinct);
                    Immediate_Friends_distribution_a2 = computing_attribute_Value_a2(Immediate_friends, a2_distinct);

//                    Friends_Bin_Array[0] = 0;
//                    Friends_Bin_Array[1] = 0;
//                    Friends_Bin_Array[2] = 0;
//                    Friends_Bin_Array[3] = 0;
                    
                    String temp_imm_Friends_a1 = ""; 
                    String temp_imm_Friends_quartile = ""; 
                    int Friends_Bin_Array_Counter=0;
                    String Immediate_Friends_distribution_a1_str = "";
                    for (int h = 0; h < Immediate_Friends_distribution_a1.length; h++) {
//                        if(h!=0){
                        Immediate_Friends_distribution_a1_str = Immediate_Friends_distribution_a1_str +
                                Immediate_Friends_distribution_a1[h] + " ";
//                        }
//                        if (h == a1_of_Hidden_Node) {
//                            Count_tuples = Immediate_Friends_distribution_a1[h];
//                        }
                        
                        if (Immediate_friends.size() != 0) {
                            double quartile_lookup = ((double)Immediate_Friends_distribution_a1[h] /(double) Immediate_friends.size()) * 100;
//                            System.err.println("Lookup value for node " + Hidden_Users[i] + " = " + 
//                                    Immediate_Friends_distribution_a1[h]);
                            if(h==0){
                            Look_up = Look_up + ("Status distribution for node " + Hidden_Users[i] + " = " + 
                                    Immediate_Friends_distribution_a1[h]) + " ";
                            }
                            else{
                               Look_up = Look_up + (" " + Immediate_Friends_distribution_a1[h]) + " "; 
                            }
//                            if (h != 0) {
                                if (quartile_lookup > 0 && quartile_lookup <= Bin_1) {
                                    Friends_Bin_Array_Counter=1;
//                                    Friends_Bin_Array[0]++;
                                } else if (quartile_lookup > Bin_1 && quartile_lookup <= Bin_2) {
                                    Friends_Bin_Array_Counter=2;
//                                    Friends_Bin_Array[1]++;
                                } else if (quartile_lookup > Bin_2 && quartile_lookup <= Bin_3) {
                                    Friends_Bin_Array_Counter=3;
//                                    Friends_Bin_Array[2]++;
                                } else if (quartile_lookup > Bin_3 && quartile_lookup <= Bin_4) {
                                    Friends_Bin_Array_Counter=4;
//                                    Friends_Bin_Array[3]++;
                                }else if (quartile_lookup > Bin_4 && quartile_lookup <= Bin_5) {
                                    Friends_Bin_Array_Counter=5;
//                                    Friends_Bin_Array[3]++;
                                }else if (quartile_lookup <= 0) {
                                    Friends_Bin_Array_Counter=0;
//                                    Friends_Bin_Array[3]++;
                                } else {
                                    System.err.println("Error!!!");
                                }
                                Friends_Bin_Array[h] = Friends_Bin_Array_Counter;
//                            }
                        }
                        
                        temp_imm_Friends_a1 = temp_imm_Friends_a1 + Immediate_Friends_distribution_a1[h] + " ";
                    }
                    Immediate_Friends_distribution_a1_str_array[i] = Immediate_Friends_distribution_a1_str;
//                    System.err.println(Look_up);
                    //Print immediate friends status distribution!!!
//                    System.err.println("Immediate_Friends_distribution_a1 of Node " + i + 
//                            " = " + Immediate_Friends_distribution_a1_str);
                    //Display bin distribution!!!
                    for (int h = 0; h < Friends_Bin_Array.length; h++) {
//                        if(h!=0){
                        temp_imm_Friends_quartile = temp_imm_Friends_quartile +  Friends_Bin_Array[h] + " ";
//                        }
                    }
                    Bin_Distribution_Array[i] = temp_imm_Friends_quartile; 
//                    System.err.println("Bin distribution of node " + (Hidden_Users[i])+ " is = " 
//                                + temp_imm_Friends_quartile); 

                }//Hidden count loop (i) ends here
                
                //Bining Logic Start here
                String[] Unique_Bin__Distribution_Values = print_occurence_Count(Bin_Distribution_Array);
//                for(int i=0; i<Unique_Bin__Distribution_Values.length; i++){
//                    //Print unique bin values!!!
//                    System.out.println("Type_" + i + " " +  
//                             Unique_Bin__Distribution_Values[i] + "\\\\_\\hline" + " Occurences = " + 
//                            Global_unique_Occurences[i]);
//                }
                

                int[] Final_Unique_Bin__Distribution_Values_a2 = new int[Unique_Bin__Distribution_Values.length];
                
                
//                for(int i=0; i<Unique_Bin__Distribution_Values.length; i++){
//                    //Print unique bin values!!!
//                    System.out.println("Type_" + i + " " +  Unique_Bin__Distribution_Values[i]);
//                }
                String[] Unique_Bin__Distribution_Values_a2_str_array=new String[Unique_Bin__Distribution_Values.length];
                for(int i=0; i<Unique_Bin__Distribution_Values.length; i++){
                    int counter=0;
                    String Unique_Bin__Distribution_Values_a2_str="";
                    int[] Unique_Bin__Distribution_Values_a2 = new int[a2_length];
                    int[] Unique_Bin__Distribution_Values_a1 = new int[a1_length];
                    for(int j=0; j<Bin_Distribution_Array.length; j++){
//                        System.out.println("Unique_Bin__Distribution_Values " + Unique_Bin__Distribution_Values[i]);
//                        System.out.println("Bin_Distribution_Array " + Bin_Distribution_Array[j]);
                        if(Unique_Bin__Distribution_Values[i].equals(Bin_Distribution_Array[j])){
                            counter++;
                            int temp_a2 = indexOf(a2[Hidden_Users[j]-1], a2_distinct);
//                            System.out.println("temp_a2 = " + Hidden_Users[j]);
                            Unique_Bin__Distribution_Values_a2[temp_a2]++;
                        }
                    }
                    for(int j=1; j<a2_length; j++){
                        Unique_Bin__Distribution_Values_a2_str = Unique_Bin__Distribution_Values_a2_str + 
                                Unique_Bin__Distribution_Values_a2[j] + " ";
                    }
                    Unique_Bin__Distribution_Values_a2_str_array[i] = Unique_Bin__Distribution_Values_a2_str;
//                    System.out.println("Counter = " + counter);
                    //Print unique gender distribution of each unique type tuple!!
//                    System.out.println("Type_" + i + " " +  Unique_Bin__Distribution_Values[i] + " Counter =  " +   counter 
//                    + " Distribution (# of male female) =  " 
//                    + Unique_Bin__Distribution_Values_a2_str + "_\\\\_\\hline");
                }
                
                int a2_of_Hidden_Node_new;
                
                
                double[] UniqueValues_probability = new double[a2_length];
                
                // Bining of unique tuples and getting there Gender!!
                    
                    
                    int[] UniqueValues_Count = new int[Unique_Bin__Distribution_Values.length];
                    int[] UniqueValues_a2 = new int[a2_length];
                    
                    String[] Absolute_Error_Array = new String[Hidden_Count];
                    int[] Final_predicted_Value_2 = new int[Hidden_Count];
                for (int j = 0; j < Hidden_Count; j++){ //Loop for every Node
                    int[] Type_Wise_a2_Attribute = new int[a2_distinct.length];
                    double[] Type_Wise_a2_Attribute_Normalized_temp = new double[a2_distinct.length];
                    double[] Type_Wise_a2_Attribute_Normalized = new double[a2_distinct.length];
                    double[] Type_Wise_a2_Attribute_Error = new double[a2_distinct.length];
                    double[] Type_Wise_a2_Attribute_Error_square = new double[a2_distinct.length];
                    double[] Type_Wise_a2_Attribute_Error_Cube= new double[a2_distinct.length];
//                    ArrayList similar_Type_Node_Array = new ArrayList();
                    
                    double total_Error_L1 = 0;
                    double total_Error_L2 = 0;
                    double total_Error_L3 = 0;
//                    System.err.println("a2 = " + a2[Hidden_Users[j] - 1]);
                    Actual_Value[j] = a2[Hidden_Users[j] - 1]; // True classes
//                    System.err.println(Actual_Value[j]);
                    int type_occurence = 0;
                    String Type_Wise_a2_Attribute_Error_str_L1="";
                    String Type_Wise_a2_Attribute_Error_str_L2="";
                    String Type_Wise_a2_Attribute_Error_str_L3="";
                    
                    for (int k = 0; k < Unique_Bin__Distribution_Values.length; k++){ //Loop for unique types
                        if (Bin_Distribution_Array[j].equals(Unique_Bin__Distribution_Values[k])) {
                            ArrayList similar_Type_Node_Array = new ArrayList();
//                                 System.err.println("Neighbour of Node " + j + " is of type = " + 
//                                         Unique_Bin__Distribution_Values[k]);
                            //Loop to compute nodes of similar type
                            for (int i = 0; i < Hidden_Count; i++) {
                                if (Unique_Bin__Distribution_Values[k].equals(Bin_Distribution_Array[i])) {
//                                    System.err.println("Unique_Bin__Distribution_Values = " + i);
                                    similar_Type_Node_Array.add(Hidden_Users[i]);
                                    type_occurence++;
                                }
                            }
//                            System.err.println("similar_Type_Node_Array for Node " + j + " = " + similar_Type_Node_Array);
                            Type_Wise_a2_Attribute = computing_attribute_Value_a2(similar_Type_Node_Array, a2_distinct);
                            int[] sum_Of_type_occurence=new int[Hidden_Count];
                            //Check if attribute a2 contains Zero
                            boolean contains = IntStream.of(a2_distinct).anyMatch(x -> x == 0);
                            if (contains == true) {
                                for (int y = 0; y < Hidden_Count; y++) {
                                    for (int z = 1; z < Type_Wise_a2_Attribute.length; z++) {
                                        sum_Of_type_occurence[y] = sum_Of_type_occurence[y] + Type_Wise_a2_Attribute[z];
                                    }
                                }
                            }
                            else{
                                for (int y = 0; y < Hidden_Count; y++) {
                                    for (int z = 0; z < Type_Wise_a2_Attribute.length; z++) {
                                        sum_Of_type_occurence[y] = sum_Of_type_occurence[y] + Type_Wise_a2_Attribute[z];
                                    }
                                }
                            }
//                            System.err.println("Type_Wise_a2_Attribute = " + Type_Wise_a2_Attribute[2]);
                            String Absolute_Error = "";
                            String Type_Wise_a2_Attribute_str = "";
                            List Error_myList = new ArrayList();
                            for (int y = 0; y < Type_Wise_a2_Attribute.length; y++) {
//                                System.err.println("Type_Wise_a2_Attribute = " + Type_Wise_a2_Attribute[y]);
                                if (y != 0) {
                                    Type_Wise_a2_Attribute_str = Type_Wise_a2_Attribute_str + Type_Wise_a2_Attribute[y] + " ";
//                                    int sum_Of_type_occurence = IntStream.of(Type_Wise_a2_Attribute).sum();
//                                    Type_Wise_a2_Attribute_Normalized[y] = 
//                                            (double) Type_Wise_a2_Attribute[y] / (double) type_occurence;
                                    if(sum_Of_type_occurence[j]!=0){
                                    Type_Wise_a2_Attribute_Normalized_temp[y] = 
                                            (double) Type_Wise_a2_Attribute[y] / (double) sum_Of_type_occurence[j];
                                    }
                                    else{
                                        Type_Wise_a2_Attribute_Normalized_temp[y] = 0;
                                    }
//                                    Absolute_Error = Absolute_Error + " Numerator = " +
//                                            (double) Type_Wise_a2_Attribute[y] + " Denomirator = " + 
//                                            (double) sum_Of_type_occurence[j] + " = " + 
//                                            Type_Wise_a2_Attribute_Normalized[y] + " ";
                                //Rounding value to two decimal Places
                                    Type_Wise_a2_Attribute_Normalized[y] = 
                                            Math.round(Type_Wise_a2_Attribute_Normalized_temp[y] * Math.pow(10, 2)) / Math.pow(10, 2);// 2 is for frounding to 2 decimal Places
                                      Absolute_Error = Absolute_Error + Type_Wise_a2_Attribute_Normalized[y] + " ";
                                      
                                      
//                                    System.err.println("Type_Wise_a2_Attribute_Normalized for index " + y + " = " + 
//                                            Type_Wise_a2_Attribute_Normalized[y] );
                                    if(y==indexOf(Actual_Value[j], a2_distinct)){
//                                        System.err.println("Actual_Value = " + Actual_Value[j]);
                                        Type_Wise_a2_Attribute_Error[y] = Math.abs(1 - Type_Wise_a2_Attribute_Normalized[y]);
//                                        System.out.println("1 - "+ Type_Wise_a2_Attribute_Normalized[y]+" = "+Type_Wise_a2_Attribute_Error[y]);
                                        Type_Wise_a2_Attribute_Error_square[y] = Math.pow(Type_Wise_a2_Attribute_Error[y], 2);
                                        Type_Wise_a2_Attribute_Error_Cube[y] = Math.pow(Type_Wise_a2_Attribute_Error[y], 3);
                                        
//                                        System.err.println("Absolute difference from 1 is of index " + y + 
//                                                " and value = " + Type_Wise_a2_Attribute_Normalized[y]);

                                        Type_Wise_a2_Attribute_Error_str_L1 = Type_Wise_a2_Attribute_Error_str_L1 + 
                                           Type_Wise_a2_Attribute_Error[y] + " "; 
                                        
                                        Type_Wise_a2_Attribute_Error_str_L2 = Type_Wise_a2_Attribute_Error_str_L2 + 
                                           Type_Wise_a2_Attribute_Error_square[y] + " ";   
                                        
                                        Type_Wise_a2_Attribute_Error_str_L3 = Type_Wise_a2_Attribute_Error_str_L3 + 
                                           Type_Wise_a2_Attribute_Error_Cube[y] + " "; 
                                        
                                        total_Error_L1 = total_Error_L1 + Type_Wise_a2_Attribute_Error[y];
                                        total_Error_L2 = total_Error_L2 + Type_Wise_a2_Attribute_Error_square[y];
                                        total_Error_L3 = total_Error_L3 + Type_Wise_a2_Attribute_Error_Cube[y];
                                    }
                                    else{
                                        Type_Wise_a2_Attribute_Error[y] = Math.abs(0 - Type_Wise_a2_Attribute_Normalized[y]) ;
//                                        System.out.println("0 - "+ Type_Wise_a2_Attribute_Normalized[y]+" = "+Type_Wise_a2_Attribute_Error[y]);
//                                        System.out.println("");
                                        Type_Wise_a2_Attribute_Error_square[y] = Math.pow(Type_Wise_a2_Attribute_Error[y], 2);
                                        Type_Wise_a2_Attribute_Error_Cube[y] = Math.pow(Type_Wise_a2_Attribute_Error[y], 3);
                                        
//                                        System.err.println("Absolute difference from 0 is of index " + y + 
//                                                " and value = " + Type_Wise_a2_Attribute_Normalized[y]);
                                        
                                        Type_Wise_a2_Attribute_Error_str_L1 = Type_Wise_a2_Attribute_Error_str_L1 + 
                                           Type_Wise_a2_Attribute_Error[y] + " "; 
                                        
                                        Type_Wise_a2_Attribute_Error_str_L2 = Type_Wise_a2_Attribute_Error_str_L2 + 
                                           Type_Wise_a2_Attribute_Error_square[y] + " "; 
                                        
                                        Type_Wise_a2_Attribute_Error_str_L3 = Type_Wise_a2_Attribute_Error_str_L3 + 
                                           Type_Wise_a2_Attribute_Error_Cube[y] + " "; 
                                        
                                        total_Error_L1 = total_Error_L1 + Type_Wise_a2_Attribute_Error[y];
                                        total_Error_L2 = total_Error_L2 + Type_Wise_a2_Attribute_Error_square[y];
                                        total_Error_L3 = total_Error_L3 + Type_Wise_a2_Attribute_Error_Cube[y];
                                    }
//                                    System.err.println("Type_Wise_a2_Attribute_Error2 for nore " + j + " = "
//                                                + Type_Wise_a2_Attribute_Error_str_L1 + " Squared = " + 
//                                            Type_Wise_a2_Attribute_Error_str_L2 + " Cubed = " + 
//                                            Type_Wise_a2_Attribute_Error_str_L3);
//                                    Type_Wise_a2_Attribute_Error[y] = indexOf(Actual_Value[j], a2_distinct);
                                }
//                                
                            }
                            
                            /////////////////////////////////////////////////////////
                            // Weighted sample logic start here!!
                            /////////////////////////////////////////////////////////
                            int[] Hundred_Size_Weighted_Array = new int[100];
                           Hundred_Size_Weighted_Array = Normalized_Probabilities_testing(Type_Wise_a2_Attribute_Normalized,a2_distinct.length);
                           Final_predicted_Value_2[j] =  Weighted_Sample_testing(Hundred_Size_Weighted_Array);
                           /////////////////////////////////////////////////////////
                            // Weighted sample logic Ends here!!
                            /////////////////////////////////////////////////////////
                            
                            Absolute_Error_Array[j] = Absolute_Error;
//                            System.out.println("Absolute Error for Node " + Hidden_Users[j] + " is =  " + Absolute_Error);
                            //Print unique bins with seperate gender!!!
//                            System.err.println("Bin type = " + j + " Gender value = " + Type_Wise_a2_Attribute_str);
//                            Final_predicted_Value[j] = getIndexOfLargest(Type_Wise_a2_Attribute_Normalized);
//                                 System.out.println("Final_predicted_Value = " + Final_predicted_Value[j]);
                        }
                    }
//                    System.out.println("Error_L1 for Node " + Hidden_Users[j] + " = " + total_Error_L1);
                    total_Error_array_L1[j] = total_Error_L1;
                    total_predicted_Error_Count_L1 = total_predicted_Error_Count_L1 + total_Error_array_L1[j];
                    
//                    System.err.println("total_Error_L2 = " + total_Error_L2);
                    total_Error_array_L2[j] = Math.sqrt(total_Error_L2);
//                    System.err.println("total_Error_L2 = " + total_Error_array_L2[j]);
                    total_predicted_Error_Count_L2 = total_predicted_Error_Count_L2 + total_Error_array_L2[j];
                    
//                    System.err.println("total_Error_L3 = " + total_Error_L3);
                    total_Error_array_L3[j] = Math.cbrt(total_Error_L3);
                    total_predicted_Error_Count_L3 = total_predicted_Error_Count_L3 + total_Error_array_L3[j];
                    //Print Combine Gender Error (L1 Norm)!!!
//                    System.out.println(total_Error);
                    //Print separate gender wise error!!!
//                    System.out.println(Type_Wise_a2_Attribute_Error_str);
                    
                }
                                //Print with Occurences!!!
                for (int i = 0; i < Bin_Distribution_Array.length; i++) {
                    for (int j = 0; j < Unique_Bin__Distribution_Values.length; j++) {
                        if(Bin_Distribution_Array[i].equals(Unique_Bin__Distribution_Values[j])){
//                           System.out.println("Gender = " + Global_a2[i] +  " Status = " + Global_a1[i] + 
//                                   " Status Distribution = " + 
//                                   Immediate_Friends_distribution_a1_str_array[i]
//                                   + " Bin " + i + " = "
//                                + Bin_Distribution_Array[i] + " Occurences = "
//                                + Global_unique_Occurences[j] + " Gender Distribution = " + 
//                                   Unique_Bin__Distribution_Values_a2_str_array[j] + " Predicted Gender Distribution = " +
//                                           Absolute_Error_Array[i] + 
//                                           "//New_hline!!!"); 
                           
                           try {
                                bufferedWriter.write(Absolute_Error_Array[i]);
                                bufferedWriter.newLine();
                            } catch (IOException ex) {
                                System.out.println("Error writing to file '" + writeFileName + "'");
                            }
                        }
                    }
                }
                int[][] Confusion_Matrix= new int[a2_length][a2_length]; // Initializing Confusion matrix with zero's
                bufferedWriter.close();
                double Average_L1_Percentage = (total_predicted_Error_Count_L1/(Hidden_Count));
                double Average_L2_Percentage = (total_predicted_Error_Count_L2/(Hidden_Count));
                double Average_L3_Percentage = (total_predicted_Error_Count_L3/(Hidden_Count));
                //Print Average L1 Norm!!!
//                System.out.println("Average L1 norm = " + Average_L1_Percentage);
//                System.out.println("Average L2 norm = " + Average_L2_Percentage);
//                System.out.println("Average L3 norm = " + Average_L3_Percentage);
                
//                System.out.println("total_predicted_Error_Count = " + total_predicted_Error_Count);
//                System.out.println("total_Actual_Error_Count = " + total_Actual_Error_Count);
                int row_index;
                int column_index;
                int temp_Total_Nodes=0;
                int success = 0;
                int failure = 0;
                
//                ////////////////////////
//                // Temp Logic Starts Here!!
//                ////////////////////////
//                int[] Final_predicted_Value_2 = new int[Hidden_Count];
//                String Path = "C:\\Users\\Pasha\\Desktop\\new 1.txt";
//                double[][] File_Values = new double[Hidden_Count][a2_distinct.length];
//                File_Values = read_Column_Of_File_testing(Path,a2_distinct.length);
//                Final_predicted_Value_2 = Normalized_Probabilities_testing(File_Values,a2_distinct.length);
//                ////////////////////////
//                // Temp Logic Ends Here!!
//                ////////////////////////
                
                for(int i=0; i<Hidden_Count; i++){
                    
                    row_index = indexOf((int)a2_distinct[Final_predicted_Value_2[i]], a2_distinct);
//                    System.out.println("row_index for node " + i + " = " + row_index);
                    column_index = indexOf(Actual_Value[i], a2_distinct);
                    if(row_index>=0 &&column_index>=0){
                    Confusion_Matrix[row_index][column_index]++; 
                    }
//                    System.err.println("Actual Value = " + a2[Hidden_Users[i]-1]);
//                    System.err.println("Predicted Value = " + (int)Final_predicted_Value[i]);

                if(a2[Hidden_Users[i]-1]!=0){
                    temp_Total_Nodes++;
                        if ((int) a2_distinct[Final_predicted_Value_2[i]] == a2[Hidden_Users[i] - 1]) {
                            success++;
                        } else {
                            failure++;
                        }
                    }
                }

          
                //Printing Confusion Matrix
                
                for(int i=0; i<a2_length; i++){
                    String Confusion = "";
                    for(int f=0; f<a2_length; f++){
//                        if(i!=0 && f!=0){
                        Confusion = Confusion + Confusion_Matrix[i][f] + " ";
//                        }
                    
                    }
                    //Print confusion Matrix
//                    System.out.println("Confusion Matrix = " +  Confusion);
                }
                
                double success_percentage = ((double)success/(double)temp_Total_Nodes)*100;
                double failure_percentage = ((double)failure/(double)temp_Total_Nodes)*100;
                
                Sucess_Matrix = Sucess_Matrix + success_percentage + " ";
                
//                double arraySum = Array_sum(total_Error_array);
                    //Print success and failure !!!
//                System.out.println("success count = " + success_percentage + " And failure count = " + failure_percentage);
//                System.err.println(final_total_error);
//                bufferedWriter.close();

                //Printing Contingency Table!!
                for (int i = 0; i < a1_distinct.length; i++) {
                    String Contingency = "";
                    for (int f = 0; f < a2_distinct.length; f++) {
//                        if (i != 0 && f != 0) {
                            Contingency = Contingency + Contingency_Table[i][f] + " ";
//                        }
                    }
//                    System.err.println("Contingency Table = " + Contingency);
                }//Printing Contingency Table ends here!!
                
            } // inner loop end here
            Sucess_Matrix_Full[outerLoop] = Sucess_Matrix;
            
        } // Outer loop end here
        
        for(int i=0;i<attributeCount; i++){
            System.out.println("Success Matrix = ");
            System.out.println(Sucess_Matrix_Full[i]);
        }
    }


    
    public static double[] removeElements(double[] input, double deleteMe) {
    double [] result = new double [input.length-1];

    for (int i=0;i<input.length-1;i++)
    {
        result[i]=input[i+1];
    }

    return result;
}
    public static int[] Normalized_Probabilities(double[] temp_Prediction_2_temp){
        double[] temp_Prediction_2_Normalized = new double[temp_Prediction_2_temp.length];
        double[] temp_Prediction_2 = new double[temp_Prediction_2_temp.length];
        
        temp_Prediction_2 = removeElements(temp_Prediction_2_temp,0);
//        for(int m=0; m<temp_Prediction_2.length; m++){
//            System.err.println("temp_Prediction_3 = " + temp_Prediction_2[m]);
//        }
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
//                        System.err.println("result = " + result);
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
//                System.err.println("temp_Prediction_2 = " + temp_Prediction_2[j]);
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
        
        
        return temp_Prediction_3;
    }
      public static int Weighted_Sampe(int[] temp_Prediction) throws IOException{
         int  attribute1 = 0; // Status
          int      attribute2 = 1; // Gender
                attributes_Names att = new attributes_Names();
          att.set_attribute_Name(attribute1, attribute2);
                String temp_attr_1 = att.get_attribute_1();
                String temp_attr_2 = att.get_attribute_2();
          String   writeFileName1 = 
                		"E:/MS Computer Science/MS Thesis/Java Code/RandomizedRoundingPrediction/";
                String   writeFileName = writeFileName1 + temp_attr_2 + "_using_" + temp_attr_1 + ".txt";
                
         FileWriter fileWriter = new FileWriter(writeFileName);
   	        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
         
//         int [] bin = {1,3,1,3,2}; // 1,3 80%, 2 20%
    int count=0;
    int numChosen = -1;
    int index=-1;
//    for (int i=0;i<result;i++) {

    double randomNumber=Math.random();

    int numChosen_temp=(int)(Math.random()*(temp_Prediction.length));
//    System.err.println("numChosen_temp = " + numChosen_temp);
//    System.err.println("temp_Prediction.length = " + temp_Prediction);
    numChosen = temp_Prediction[numChosen_temp];
    numChosen++;
//          System.err.println("numChosen = " + numChosen);
      try {
        bufferedWriter.write(numChosen);
        bufferedWriter.newLine();
    } catch (IOException ex) {
        System.out.println("Error writing to file '" + writeFileName + "'");
    }
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
    
      
    public static int getIndexOfLargest( double[] array )
{
  if ( array == null || array.length == 0 ) return -1; // null or empty

  int largest = 0;
  for ( int i = 1; i < array.length; i++ )
  {
      if ( array[i] > array[largest] ) largest = i;
  }
  return largest; // position of the first largest found
}
    
    
    public static String[] print_occurence_Count(String[] array) {

        Map<String, Integer> hm = new HashMap();
        for (String x : array) {

            if (!hm.containsKey(x)) {
                hm.put(x, 1);
            } else {
                hm.put(x, hm.get(x) + 1);
            }
        }

//    System.out.println("Unique Bin count = " + hm.size());
//    System.out.println(hm);
    Collection cc = hm.values();
    Iterator itr_cc = cc.iterator();
    String[] unique_Occurences = new String[cc.size()];
            int ii = 0;
        while (itr_cc.hasNext()) {
            unique_Occurences[ii] = itr_cc.next().toString();
//            System.out.println(unique_Occurences[ii]);
            ii++;
        }
        //Save unique Occurnces in an array!!!
        set_occurence_Count(unique_Occurences);
        
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
    
    public static void set_occurence_Count(String[] unique_Occurences){
        Global_unique_Occurences = unique_Occurences;
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
     static int[] printDistinct(int arr[])
    {
        int n=arr.length;
        // Pick all elements one by one
        for (int i = 0; i < n; i++)
        {
            // Check if the picked element 
            // is already printed
            int j;
            for (j = 0; j < i; j++)
            if (arr[i] == arr[j])
                break;
     
            // If not printed earlier, 
            // then print it
            if (i == j)
            System.out.print( arr[i] + " ");
        }
        return arr;
    }
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

    
    //////////////////////////////////
    // Testing Code Start Here
    //////////////////////////////////
    
     public static double[][] read_Column_Of_File_testing(String filename,int a2_distinct_Length){
        Set<Integer> vertexSet = new HashSet<Integer>();
        ArrayList<String> edgeList= new ArrayList<String>();
        double[][] File_Values = new double[Hidden_Count][a2_distinct_Length];
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
        
   public static int[] Normalized_Probabilities_testing(double[] temp_Prediction_2_temp,int a2_distinct_Length) throws IOException{
//       System.err.println("a2_distinct_Length = " + a2_distinct_Length); 
       double[] temp_Prediction_2_Normalized = new double[temp_Prediction_2_temp.length  -1];
        double[] temp_Prediction_2 = new double[a2_distinct_Length];
        

        int temp_Normalized_100 = 100;
        int[] temp_Prediction_3 = new int[temp_Normalized_100];
//        int[] Predicted_Array = new int[Hidden_Count];
        
        for(int i=0; i<temp_Normalized_100; i++){
            temp_Prediction_3[i] = 0;
        }
//        for(int i=0; i<Hidden_Count; i++){
            String ttr="";
          for(int j=0; j<a2_distinct_Length; j++){
              
              temp_Prediction_2_temp[j] = temp_Prediction_2_temp[j] * temp_Normalized_100;
//              ttr = ttr + temp_Prediction_2_temp[i][j] + " ";
//              System.err.println("temp_Prediction_2_temp : " + temp_Prediction_2_temp[i][j]);
          }  
//          System.err.println("temp_Prediction_2_temp : " + ttr);
//        }
        temp_Prediction_2 = temp_Prediction_2_temp;
//       for (int r = 0; r < Hidden_Count; r++) { //For each node
           int k = 0;
           String tty2 = "";
//           System.err.println("r = " + r);
           for (int j = 0; j < a2_distinct_Length; j++) {
               String tty = "";
               if(temp_Prediction_2[j]!=0){
                   for (int l = 0; l < temp_Prediction_2[j]; l++) {
                if(k<100){
//                       System.err.println("k = " + k + " L = " + l);
                       temp_Prediction_3[k] = j;
                       tty = tty + temp_Prediction_3[k] + " " ;
//                       System.err.println("temp_Prediction_3 = " + k + " = " + temp_Prediction_3[k]);
                }

                       k = k + 1;
                   }
               }
               else{
                   k = k + 1;
//                   System.err.println("Error!!!");
               }
                   tty2 = tty2 + tty + " ";
           }
//           System.err.println(r + " = tty = " + tty2);
          
//          Predicted_Array[r] =  Weighted_Sample_testing(temp_Prediction_3);
//           System.err.println("Predicted_Array = " + Predicted_Array[r]);
//       }

        
        return temp_Prediction_3;
    }
      public static int Weighted_Sample_testing(int[] temp_Prediction) throws IOException {


        int numChosen = -1;
        int numChosen_temp = (int) (Math.random() * (temp_Prediction.length));
        numChosen = temp_Prediction[numChosen_temp];
//        numChosen++;

        return numChosen;
    }
          /////////////////////////////////////
          // Testing Code Ends Here
          /////////////////////////////////////

}
