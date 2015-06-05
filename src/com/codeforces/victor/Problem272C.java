package com.codeforces.victor;

import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Problem272C {

	public static void implementation(InputStream in, PrintStream outS) throws Exception {
		BufferedOutputStream outB = new BufferedOutputStream(outS);
		PrintWriter out = new PrintWriter(outB);
		
		Scanner s = new Scanner(in);
		int n = s.nextInt();
		int[] stairs = new int[n];
		for(int i = 0; i < n; i++)
			stairs[i] = s.nextInt();
		int m = s.nextInt();
		long curH = stairs[0];
		int curS = 0;
		for(int i = 0; i < m; i++) {
			int boxW = s.nextInt();
			int boxH = s.nextInt();
			if (boxW - 1 > curS) {
				curH = Math.max(curH, stairs[boxW - 1]);
				curS = boxW - 1;
			}
			out.println(curH);
			curH += boxH;
		}
		out.flush();
	}
	
	public static void main(String[] args) throws Exception {
		implementation(System.in, System.out);
	}
	
}