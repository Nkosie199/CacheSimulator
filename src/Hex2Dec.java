import java.util.Scanner;

public class Hex2Dec {
	private static String binary;
	private static int integer;
	private static String hexadecimal;
	
    public static String hex2Binary(String s) {
    	String val = "";
    	try{ // for the case of an integer vs an actual hexadecimal stringntg
    		integer = hex2int(s);  		
    		//byte b2 = (byte) integer;
    		String s2 = String.format("%16s", Integer.toString(integer, 2)).replace(' ', '0');
			//System.out.println(s2);			
			val = s2;
    	}
    	catch (Exception e){
    		System.out.println(e.getMessage());	
    	}	
        return val;
    }

    // precondition:  d is a nonnegative integer
    public static String binary2Hex(int d) {
        String digits = "0123456789ABCDEF";
        if (d == 0) return "0";
        hexadecimal = "";
        while (d > 0) {
            int digit = d % 16;                // rightmost digit
            hexadecimal = digits.charAt(digit) + hexadecimal;  // string concatenation
            d = d / 16;
        }
        return hexadecimal;
    }
    
    public static int binary2Int(String binary){
    	integer = Integer.parseInt(binary, 2);
    	return integer;
    }
    
    public static int hex2int(String hex){
    	integer = Integer.parseInt(hex, 16);
    	return integer;
    }
    
    private int getInteger(){
    	return integer;
    }
    
    private String getBinary(){
    	return binary;
    }
    
    private String getHexadecimal(){
    	return hexadecimal;
    }
}
