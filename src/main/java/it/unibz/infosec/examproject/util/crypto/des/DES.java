package it.unibz.infosec.examproject.util.crypto.des;

public class DES {


	public static String encodeMessage (String message, String key) {
		return performRoundsEncryption(BlockEncoder.encodeBlock(message), SubKeysGenerator.generateSubKeys(key));
	}

	public static String decodeMessage (String message, String key) {
		return performRoundsDecryption(BlockEncoder.encodeBlock(message), SubKeysGenerator.generateSubKeys(key));
	}

	private static int[] E = 
		{
				32,  1,  2,  3,  4,  5,
				4,  5,  6,  7,  8,  9, 
				8,  9, 10, 11, 12, 13, 
				12, 13, 14, 15, 16, 17,
				16, 17, 18, 19, 20, 21, 
				20, 21, 22, 23, 24, 25, 
				24, 25, 26, 27, 28, 29, 
				28, 29, 30, 31, 32,  1
		};

	private static int[][] s1 = {
			{14, 4, 13,  1,  2, 15, 11,  8,  3, 10,  6, 12,  5,  9,  0,  7},
			{0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11,  9,  5,  3,  8},
			{4, 1, 14,  8, 13,  6, 2, 11, 15, 12,  9,  7,  3, 10,  5,  0},
			{15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}
	};

	private static int[][] s2 = {
			{15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
			{3, 13,  4, 7, 15,  2,  8, 14, 12,  0, 1, 10,  6,  9, 11,  5},
			{0, 14, 7, 11, 10,  4, 13,  1,  5,  8, 12,  6,  9,  3,  2, 15},
			{13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14,  9}
	};

	private static int[][] s3 = {
			{10, 0, 9, 14, 6, 3, 15, 5,  1, 13, 12, 7, 11, 4, 2,  8},
			{13, 7, 0, 9, 3,  4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
			{13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14,  7},
			{1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}
	};

	private static int[][] s4 = {
			{7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
			{13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14,  9},
			{10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
			{3, 15, 0, 6, 10, 1, 13, 8, 9,  4, 5, 11, 12, 7, 2, 14}
	};

	private static int[][] s5 = {
			{2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
			{14, 11, 2, 12,  4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
			{4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
			{11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}
	};

	private static int[][] s6 = {
			{12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
			{10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
			{9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
			{4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}
	};

	private static int[][] s7 = {
			{4, 11, 2, 14, 15,  0, 8, 13 , 3, 12, 9 , 7,  5, 10, 6, 1},
			{13 , 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
			{1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
			{6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}
	};

	private static int[][] s8 = {
			{13, 2, 8,  4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
			{1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6 ,11, 0, 14, 9, 2},
			{7, 11, 4, 1, 9, 12, 14, 2,  0, 6, 10 ,13, 15, 3, 5, 8},
			{2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6 ,11}
	};

	static int[] p = 
		{
				16,  7, 20, 21, 
				29, 12, 28, 17, 
				1, 15, 23, 26, 
				5, 18, 31, 10, 
				2,  8, 24, 14, 
				32, 27,  3,  9, 
				19, 13, 30,  6, 
				22, 11,  4, 25
		};

	static int[] FP = 
		{
				40, 8, 48, 16, 56, 24, 64, 32,
				39, 7, 47, 15, 55, 23, 63, 31,
				38, 6, 46, 14, 54, 22, 62, 30,
				37, 5, 45, 13, 53, 21, 61, 29,
				36, 4, 44, 12, 52, 20, 60, 28,
				35, 3, 43 ,11, 51, 19, 59, 27,
				34, 2, 42, 10, 50, 18, 58, 26,
				33, 1, 41, 9, 49, 17, 57, 25
		};

	//The array is considered to have numbers of the nth number ot be positioned not the position itself
	// so a -1 is required to not exceed array bounds
	private static String performPermutation(int[] arrayPerms, String input) {
		String output = "";
		for (int i = 0; i < arrayPerms.length; i++) {
			output += input.charAt(arrayPerms[i]-1);
		}

		return output;
	}

	private static String performRoundsEncryption(String ip, String[] keys) {

		String cyphertext = "";

		// Split ip in half | 64/2 = 32
		String Ln_minus_1 = ip.substring(0, 32);
		String Rn_minus_1 = ip.substring(32);

//------------------------------

		for(int j = 0; j < 16; j++) {

			String[] RnLn = performRounds(keys, Ln_minus_1, Rn_minus_1, j);
			String Ln = RnLn[0];
			String Rn = RnLn[1];

			Rn_minus_1 = Rn;
			Ln_minus_1 = Ln;

		}

//------------------------------
		
		//merge R16 and L16 (R16 first)
		String RL16 = Rn_minus_1 + Ln_minus_1;
		// Apply Final Permutation FP
		cyphertext = performPermutation(FP, RL16);

		return cyphertext;
	}

	private static String performRoundsDecryption(String ip, String[] keys) {

		String cyphertext = "";

		// Split ip in half | 64/2 = 32
		String Ln_minus_1 = ip.substring(0, 32);
		String Rn_minus_1 = ip.substring(32);

//------------------------------

		for(int j = 15; j >= 0; j--) {

			String[] RnLn = performRounds(keys, Ln_minus_1, Rn_minus_1, j);
			String Ln = RnLn[0];
			String Rn = RnLn[1];

			Rn_minus_1 = Rn;
			Ln_minus_1 = Ln;

		}

//------------------------------

		//merge R16 and L16 (R16 first)
		String RL16 = Rn_minus_1 + Ln_minus_1;
		// Apply Final Permutation FP
		cyphertext = performPermutation(FP, RL16);

		return cyphertext;
	}

	private static String[] performRounds(String[] keys, String Ln_minus_1, String Rn_minus_1, int j) {
		// for n from 1 to 16
		// Ln = Rn-1
		// Rn =  Utils.computeXOR(Ln-1,f(Rn-1,Kn))
		String Ln = Rn_minus_1;
		String Rn;

		// computation of f(Rn-1,Kn)
		String fn = "";
		String e_Rn_minus_1 = "";

		// Apply Expansion table E to expand Rn-1
		e_Rn_minus_1 = performPermutation(E, Rn_minus_1);
		//Compute XOR of E(Rn-1) and Kn

		//Divide the resulting string into 8 substrings of length 6
		String XOR_result = DESUtils.computeXOR(e_Rn_minus_1, keys[j]);
		String[] b = DESUtils.splitEqually(XOR_result, 6);

		int[][][] s = new int[][][]{s1, s2, s3, s4, s5, s6, s7, s8};

		// for i from 0 to 7
		//convert b[i][0]b[i][5] into a decimal row
		//convert b[i][1]b[i][2]b[i]30]b[i][4] into a decimal column

		String[] s_b = new String[8];

		for(int i = 0; i < 8; i++) {
			s_b[i] = Integer.toBinaryString(
					s[i]
							[Integer.parseInt(b[i].charAt(0) + "" + b[i].charAt(5),2)]
							[Integer.parseInt(b[i].charAt(1) + "" + b[i].charAt(2) + "" + b[i].charAt(3) + "" + b[i].charAt(4) ,2)]
			);
		}

		//transform si[row][column] into a binary number of 4 bits
		//compute S by merging s1(b[0])...s8(b[7])
		String S = "";

		for(int i = 0; i < s_b.length; i++) {

			while(s_b[i].length() < 4)
				s_b[i] = "0" + s_b[i];

			S += s_b[i];
		}

		//compute f(Rn-1,Kn) by permuting S using p
		fn = performPermutation(p, S);
		Rn = DESUtils.computeXOR(Ln_minus_1,fn);

		return new String[]{Ln, Rn};
	}

}
