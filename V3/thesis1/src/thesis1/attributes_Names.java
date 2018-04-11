/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thesis1;

/**
 *
 * @author Pasha
 */
public class attributes_Names {
    public static String attribute1;
    public static String attribute2;
    public attributes_Names(){
        
    }
    public void set_attribute_Name(int attribute_1,int attribute_2){
        if(attribute_1 == 0)
        attribute1 = "status";
        else if (attribute_1 == 1)
            attribute1 = "gender";
            else if (attribute_1 == 2)
            attribute1 = "major";
            else if (attribute_1 == 3)
            attribute1 = "minor";
            else if (attribute_1 == 4)
            attribute1 = "dorm";
            else if (attribute_1 == 5)
            attribute1 = "Graduation_Year";
            else if (attribute_1 == 6)
            attribute1 = "high_school";

        if(attribute_2 == 0)
        attribute2 = "status";
        else if (attribute_2 == 1)
            attribute2 = "gender";
            else if (attribute_2 == 2)
            attribute2 = "major";
            else if (attribute_2 == 3)
            attribute2 = "minor";
            else if (attribute_2 == 4)
            attribute2 = "dorm";
            else if (attribute_2 == 5)
            attribute2 = "Graduation_Year";
            else if (attribute_2 == 6)
            attribute2 = "high_school";
    }
    
        public String get_attribute_1(){
        return attribute1;
    }
        
    public String get_attribute_2(){
        return attribute2;
    }

}
