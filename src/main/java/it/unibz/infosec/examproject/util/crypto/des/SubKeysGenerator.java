package it.unibz.infosec.examproject.util.crypto.des;

public class SubKeysGenerator {

	private static int[] PC1 = 
		{  
				57, 49, 41, 33, 25, 17,  9,
				1, 58, 50, 42, 34, 26, 18,
				10,  2, 59, 51, 43, 35, 27,
				19, 11,  3, 60, 52, 44, 36,
				63, 55, 47, 39, 31, 23, 15,
				7, 62, 54, 46, 38, 30, 22,
				14,  6, 61, 53, 45, 37, 29,
				21, 13,  5, 28, 20, 12,  4
		};

	private static int[] PC2 = 
		{
				14, 17, 11, 24,  1,  5,
				3, 28, 15,  6, 21, 10,
				23, 19, 12,  4, 26,  8,
				16,  7, 27, 20, 13,  2,
				41, 52, 31, 37, 47, 55,
				30, 40, 51, 45, 33, 48,
				44, 49, 39, 56, 34, 53,
				46, 42, 50, 36, 29, 32
		};

	private static int[] KEY_SHIFTS = 
		{
				1,  1,  2,  2,  2,  2,  2,  2,  1,  2,  2,  2,  2,  2,  2,  1
		};


	public static String[] generateSubKeys(String binkey) {
		String[] keys = new String[16];
		// Reduce the input key to a 56-bit permuted key

		String binKey_PC1 = "";

		for(int i = 0; i < PC1.length; i++)
			binKey_PC1 += binkey.charAt(PC1[i]-1);


		String[] Cn = new String[KEY_SHIFTS.length + 1];
		String[] Dn = new String[KEY_SHIFTS.length + 1];

		// Split permuted string in half | 56/2 = 28
		Cn[0] = binKey_PC1.substring(0, 28);
		Dn[0] = binKey_PC1.substring(28);

		//for i from 0 to 15 compute key[i]

		for(int i = 1; i <= KEY_SHIFTS.length; i++) {
			Cn[i] = DESUtils.cyclicLeftShift(Cn[i-1], KEY_SHIFTS[i-1]);
			Dn[i] = DESUtils.cyclicLeftShift(Dn[i-1], KEY_SHIFTS[i-1]);
		}

		// Merge the two halves into 56-bit merged

		String[] CDn = new String[16];

		for(int i = 1; i <= 16; i++) {
			CDn[i - 1] = Cn[i] + Dn[i];

			keys[i-1] = "";

			for(int j = 0; j < PC2.length; j++)
				keys[i-1] += CDn[i-1].charAt(PC2[j]-1);

		}

		
		return keys;
	}

}
