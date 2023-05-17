package it.unibz.infosec.examproject.util.crypto.des;

public class DESUtils {

	public static String cyclicLeftShift(String s, int k){
	    k = k%s.length();
	    return s.substring(k) + s.substring(0, k);
	}
	
	public static String computeXOR(String str1, String str2) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str1.length(); i++) {
			sb.append(str1.charAt(i)^str2.charAt(i));
		}
		return sb.toString();
	}
	
	public static String[] splitEqually(String str, int size) {
	    String[] ret = new String[(str.length()) / size];
	    int i = 0;
	    for (int start = 0; start < str.length(); start += size) {
	        ret[i] =  str.substring(start, Math.min(str.length(), start + size));
	        i++;
	    }
	    return ret;
	}

	public static int binaryToDecimal(String str){  
		return Integer.parseInt(str,2);  
	}  

	public static String decimalToBinary(int dec){  
		return Integer.toBinaryString(dec);  
	}
}