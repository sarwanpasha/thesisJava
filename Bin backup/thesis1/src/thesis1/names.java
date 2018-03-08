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
public class names {
public static String Class_Value_str;

    public static String get_Class_Name(){
        return Class_Value_str;
    }
    
    public static void set_Class_Names(String Name_For_Second_Attribute_Value, int Class_Value) {
        
        if (Name_For_Second_Attribute_Value.equals("status")) {
            if (Class_Value == 0) {
                Class_Value_str = "Zero(Missing)" + Class_Value;
            }
            else if (Class_Value == 1) {
                Class_Value_str = "Student"+ Class_Value;
            } else if (Class_Value == 2) {
                Class_Value_str = "Lecturer"+ Class_Value;
            } else if (Class_Value == 5) {
                Class_Value_str = "Asistant_Professor"+ Class_Value;
            } else if (Class_Value == 6) {
                Class_Value_str = "Professor"+ Class_Value;
            }
            else{
                Class_Value_str = "Retired Professor"+ Class_Value;
            }

        } else if (Name_For_Second_Attribute_Value.equals("gender")) {
            if (Class_Value == 0) {
                Class_Value_str = "Zero(Missing)"+ Class_Value;
            } else if (Class_Value == 1) {
                Class_Value_str = "Male"+ Class_Value;
            } else if (Class_Value == 2) {
                Class_Value_str = "Female"+ Class_Value;
            }
            else{
                Class_Value_str = "Error Value"+ Class_Value;
            }
        } else if (Name_For_Second_Attribute_Value.equals("major")) {
//                     Class_Value_str = num2str(class_list(i))+ Class_Value;
            if (Class_Value == 0) {
                Class_Value_str = "Zero(Missing)"+ Class_Value;
            } else if (Class_Value == 190) {
                Class_Value_str = "CS"+ Class_Value;
            } else if (Class_Value == 192) {
                Class_Value_str = "EE"+ Class_Value;
            } else if (Class_Value == 194) {
                Class_Value_str = "bio"+ Class_Value;
            } else if (Class_Value == 195) {
                Class_Value_str = "phy"+ Class_Value;
            } else if (Class_Value == 196) {
                Class_Value_str = "chemistry"+ Class_Value;
            } else if (Class_Value == 197) {
                Class_Value_str = "finance"+ Class_Value;
            } else if (Class_Value == 198) {
                Class_Value_str = "mba"+ Class_Value;
            } else if (Class_Value == 199) {
                Class_Value_str = "econ"+ Class_Value;
            } else if (Class_Value == 200) {
                Class_Value_str = "political"+ Class_Value;
            } else if (Class_Value == 201) {
                Class_Value_str = "history"+ Class_Value;
            } else if (Class_Value == 202) {
                Class_Value_str = "machenical"+ Class_Value;
            } else if (Class_Value == 204) {
                Class_Value_str = "civil"+ Class_Value;
            } else if (Class_Value == 205) {
                Class_Value_str = "chemical"+ Class_Value;
            } else if (Class_Value == 206) {
                Class_Value_str = "math"+ Class_Value;
            } else if (Class_Value == 207) {
                Class_Value_str = "urdu"+ Class_Value;
            } else if (Class_Value == 208) {
                Class_Value_str = "english"+ Class_Value;
            } else if (Class_Value == 209) {
                Class_Value_str = "philosophy"+ Class_Value;
            } else if (Class_Value == 211) {
                Class_Value_str = "german"+ Class_Value;
            } else if (Class_Value == 212) {
                Class_Value_str = "french"+ Class_Value;
            } else if (Class_Value == 213) {
                Class_Value_str = "argicultural"+ Class_Value;
            } else if (Class_Value == 217) {
                Class_Value_str = "bba"+ Class_Value;
            } else if (Class_Value == 220) {
                Class_Value_str = "geology"+ Class_Value;
            } else if (Class_Value == 221) {
                Class_Value_str = "socialScience"+ Class_Value;
            } else if (Class_Value == 222) {
                Class_Value_str = "medical"+ Class_Value;
            } else if (Class_Value == 223) {
                Class_Value_str = "aeronautical"+ Class_Value;
            } else if (Class_Value == 224) {
                Class_Value_str = "avionics"+ Class_Value;
            } else if (Class_Value == 226) {
                Class_Value_str = "metallurgy"+ Class_Value;
            } else if (Class_Value == 227) {
                Class_Value_str = "bakingAndFinance"+ Class_Value;
            } else if (Class_Value == 228) {
                Class_Value_str = "accounting"+ Class_Value;
            } else if (Class_Value == 229) {
                Class_Value_str = "entrepreneurship"+ Class_Value;
            }
            else{
                Class_Value_str = "Error Value"+ Class_Value;
            }
        } 
        
        else if (Name_For_Second_Attribute_Value.equals("minor")) 
//                     Class_Value_str = num2str(class_list(i))+ Class_Value;
        {
            if (Class_Value == 0) {
                Class_Value_str = "Zero(Missing)"+ Class_Value;
            } else if (Class_Value == 190) {
                Class_Value_str = "CS"+ Class_Value;
            } else if (Class_Value == 192) {
                Class_Value_str = "EE"+ Class_Value;
            } else if (Class_Value == 194) {
                Class_Value_str = "bio"+ Class_Value;
            } else if (Class_Value == 196) {
                Class_Value_str = "chemistry"+ Class_Value;
            } else if (Class_Value == 197) {
                Class_Value_str = "finance"+ Class_Value;
            } else if (Class_Value == 198) {
                Class_Value_str = "mba"+ Class_Value;
            } else if (Class_Value == 199) {
                Class_Value_str = "econ"+ Class_Value;
            } else if (Class_Value == 200) {
                Class_Value_str = "political"+ Class_Value;
            } else if (Class_Value == 202) {
                Class_Value_str = "machenical"+ Class_Value;
            } else if (Class_Value == 204) {
                Class_Value_str = "civil"+ Class_Value;
            } else if (Class_Value == 205) {
                Class_Value_str = "chemical"+ Class_Value;
            } else if (Class_Value == 206) {
                Class_Value_str = "math"+ Class_Value;
            } else if (Class_Value == 207) {
                Class_Value_str = "urdu"+ Class_Value;
            } else if (Class_Value == 208) {
                Class_Value_str = "english"+ Class_Value;
            } else if (Class_Value == 209) {
                Class_Value_str = "philosophy"+ Class_Value;
            } else if (Class_Value == 210) {
                Class_Value_str = "history"+ Class_Value;
            } else if (Class_Value == 211) {
                Class_Value_str = "german"+ Class_Value;
            } else if (Class_Value == 212) {
                Class_Value_str = "french"+ Class_Value;
            } else if (Class_Value == 213) {
                Class_Value_str = "argicultural"+ Class_Value;
            } else if (Class_Value == 214) {
                Class_Value_str = "phy"+ Class_Value;
            } else if (Class_Value == 215) {
                Class_Value_str = "anthropology"+ Class_Value;
            } else if (Class_Value == 217) {
                Class_Value_str = "bba"+ Class_Value;
            } else if (Class_Value == 218) {
                Class_Value_str = "econMath"+ Class_Value;
            } else if (Class_Value == 219) {
                Class_Value_str = "doubleMath"+ Class_Value;
            } else if (Class_Value == 220) {
                Class_Value_str = "geology"+ Class_Value;
            } else if (Class_Value == 221) {
                Class_Value_str = "socialScience"+ Class_Value;
            } else if (Class_Value == 222) {
                Class_Value_str = "medical"+ Class_Value;
            } else if (Class_Value == 223) {
                Class_Value_str = "aeronautical"+ Class_Value;
            } else if (Class_Value == 224) {
                Class_Value_str = "avionics"+ Class_Value;
            } else if (Class_Value == 225) {
                Class_Value_str = "envirnmentalScience"+ Class_Value;
            } else if (Class_Value == 226) {
                Class_Value_str = "metallurgy"+ Class_Value;
            } else if (Class_Value == 227) {
                Class_Value_str = "bakingAndFinance"+ Class_Value;
            } else if (Class_Value == 228) {
                Class_Value_str = "accounting"+ Class_Value;
            } else if (Class_Value == 230) {
                Class_Value_str = "entrepreneurship"+ Class_Value;
            } else if (Class_Value == 231) {
                Class_Value_str = "IT"+ Class_Value;
            }
        }
            
            else if (Name_For_Second_Attribute_Value.equals("dorm")) {
//                    Class_Value_str = num2str(class_list(i))+ Class_Value;
                Class_Value_str = "Hostel Number: " + Integer.toString(Class_Value);
            } 
            
            else if (Name_For_Second_Attribute_Value.equals("Graduation_Year")) {
                Class_Value_str = "Graduation Year: " + Integer.toString(Class_Value);
            } 
            
            else if (Name_For_Second_Attribute_Value.equals("high_school")) {
                Class_Value_str = "High School number: " + Integer.toString(Class_Value);
            }
            else{
                Class_Value_str = "Error Value"+ Class_Value;
            }
        
    }
}
