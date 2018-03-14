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

    public String get_Class_Name(){
        return Class_Value_str;
    }
    
    public static void set_Class_Names(String Name_For_Second_Attribute_Value, int Class_Value) {
        
        if (Name_For_Second_Attribute_Value.equals("status")) {
            if (Class_Value == 1) {
                Class_Value_str = "Student";
            } else if (Class_Value == 2) {
                Class_Value_str = "Lecturer";
            } else if (Class_Value == 5) {
                Class_Value_str = "Asistant_Professor";
            } else if (Class_Value == 6) {
                Class_Value_str = "Professor";
            }
            else{
                Class_Value_str = "Retired Professor";
            }

        } else if (Name_For_Second_Attribute_Value.equals("gender")) {
            if (Class_Value == 0) {
                Class_Value_str = "Zero(Missing)";
            } else if (Class_Value == 1) {
                Class_Value_str = "Male";
            } else if (Class_Value == 2) {
                Class_Value_str = "Female";
            }
            else{
                Class_Value_str = "Error Value";
            }
        } else if (Name_For_Second_Attribute_Value.equals("major")) {
//                     Class_Value_str = num2str(class_list(i));
            if (Class_Value == 0) {
                Class_Value_str = "Zero(Missing)";
            } else if (Class_Value == 190) {
                Class_Value_str = "CS";
            } else if (Class_Value == 192) {
                Class_Value_str = "EE";
            } else if (Class_Value == 194) {
                Class_Value_str = "bio";
            } else if (Class_Value == 195) {
                Class_Value_str = "phy";
            } else if (Class_Value == 196) {
                Class_Value_str = "chemistry";
            } else if (Class_Value == 197) {
                Class_Value_str = "finance";
            } else if (Class_Value == 198) {
                Class_Value_str = "mba";
            } else if (Class_Value == 199) {
                Class_Value_str = "econ";
            } else if (Class_Value == 200) {
                Class_Value_str = "political";
            } else if (Class_Value == 201) {
                Class_Value_str = "history";
            } else if (Class_Value == 202) {
                Class_Value_str = "machenical";
            } else if (Class_Value == 204) {
                Class_Value_str = "civil";
            } else if (Class_Value == 205) {
                Class_Value_str = "chemical";
            } else if (Class_Value == 206) {
                Class_Value_str = "math";
            } else if (Class_Value == 207) {
                Class_Value_str = "urdu";
            } else if (Class_Value == 208) {
                Class_Value_str = "english";
            } else if (Class_Value == 209) {
                Class_Value_str = "philosophy";
            } else if (Class_Value == 211) {
                Class_Value_str = "german";
            } else if (Class_Value == 212) {
                Class_Value_str = "french";
            } else if (Class_Value == 213) {
                Class_Value_str = "argicultural";
            } else if (Class_Value == 217) {
                Class_Value_str = "bba";
            } else if (Class_Value == 220) {
                Class_Value_str = "geology";
            } else if (Class_Value == 221) {
                Class_Value_str = "socialScience";
            } else if (Class_Value == 222) {
                Class_Value_str = "medical";
            } else if (Class_Value == 223) {
                Class_Value_str = "aeronautical";
            } else if (Class_Value == 224) {
                Class_Value_str = "avionics";
            } else if (Class_Value == 226) {
                Class_Value_str = "metallurgy";
            } else if (Class_Value == 227) {
                Class_Value_str = "bakingAndFinance";
            } else if (Class_Value == 228) {
                Class_Value_str = "accounting";
            } else if (Class_Value == 229) {
                Class_Value_str = "entrepreneurship";
            }
            else{
                Class_Value_str = "Error Value";
            }
        } else if (Name_For_Second_Attribute_Value.equals("minor")) 
//                     Class_Value_str = num2str(class_list(i));
        {
            if (Class_Value == 0) {
                Class_Value_str = "Zero(Missing)";
            } else if (Class_Value == 190) {
                Class_Value_str = "CS";
            } else if (Class_Value == 192) {
                Class_Value_str = "EE";
            } else if (Class_Value == 194) {
                Class_Value_str = "bio";
            } else if (Class_Value == 196) {
                Class_Value_str = "chemistry";
            } else if (Class_Value == 197) {
                Class_Value_str = "finance";
            } else if (Class_Value == 198) {
                Class_Value_str = "mba";
            } else if (Class_Value == 199) {
                Class_Value_str = "econ";
            } else if (Class_Value == 200) {
                Class_Value_str = "political";
            } else if (Class_Value == 202) {
                Class_Value_str = "machenical";
            } else if (Class_Value == 204) {
                Class_Value_str = "civil";
            } else if (Class_Value == 205) {
                Class_Value_str = "chemical";
            } else if (Class_Value == 206) {
                Class_Value_str = "math";
            } else if (Class_Value == 207) {
                Class_Value_str = "urdu";
            } else if (Class_Value == 208) {
                Class_Value_str = "english";
            } else if (Class_Value == 209) {
                Class_Value_str = "philosophy";
            } else if (Class_Value == 210) {
                Class_Value_str = "history";
            } else if (Class_Value == 211) {
                Class_Value_str = "german";
            } else if (Class_Value == 212) {
                Class_Value_str = "french";
            } else if (Class_Value == 213) {
                Class_Value_str = "argicultural";
            } else if (Class_Value == 214) {
                Class_Value_str = "phy";
            } else if (Class_Value == 215) {
                Class_Value_str = "anthropology";
            } else if (Class_Value == 217) {
                Class_Value_str = "bba";
            } else if (Class_Value == 218) {
                Class_Value_str = "econMath";
            } else if (Class_Value == 219) {
                Class_Value_str = "doubleMath";
            } else if (Class_Value == 220) {
                Class_Value_str = "geology";
            } else if (Class_Value == 221) {
                Class_Value_str = "socialScience";
            } else if (Class_Value == 222) {
                Class_Value_str = "medical";
            } else if (Class_Value == 223) {
                Class_Value_str = "aeronautical";
            } else if (Class_Value == 224) {
                Class_Value_str = "avionics";
            } else if (Class_Value == 225) {
                Class_Value_str = "envirnmentalScience";
            } else if (Class_Value == 226) {
                Class_Value_str = "metallurgy";
            } else if (Class_Value == 227) {
                Class_Value_str = "bakingAndFinance";
            } else if (Class_Value == 228) {
                Class_Value_str = "accounting";
            } else if (Class_Value == 230) {
                Class_Value_str = "entrepreneurship";
            } else if (Class_Value == 231) {
                Class_Value_str = "IT";
            } else if (Name_For_Second_Attribute_Value.equals("dorm")) {
//                    Class_Value_str = num2str(class_list(i));
                Class_Value_str = "Hostel Number: " + Integer.toString(Class_Value);
            } else if (Name_For_Second_Attribute_Value.equals("Graduation_Year")) {
                Class_Value_str = "Graduation Year: " + Integer.toString(Class_Value);
            } else if (Name_For_Second_Attribute_Value.equals("high_school")) {
                Class_Value_str = "High School number: " + Integer.toString(Class_Value);
            }
            else{
                Class_Value_str = "Error Value";
            }
        }
    }
}
