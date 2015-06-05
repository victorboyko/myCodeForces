package com.codeforces.victor;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Problem520B {
	
	// log(base 2) x=log(base e) x/log(base e) 2
	public static double log2(double x) {
		int base = 2;
		return Math.log(x) / Math.log(base);
	}
	
	public static void implementation(InputStream in, PrintStream out) throws Exception {
		Scanner s = new Scanner(in);
		int n = s.nextInt();
		int m = s.nextInt();
		int res = 0;
		
		while(n != m) {
			if (n > m) {
				res += n - m;
				break;
			}
			res++;
			if (n * 2 == m || n - 1 == m) break;
			
			int nSq1 = (int)Math.ceil(log2(((double)m)/n));
			int d1 = n*(int)Math.pow(2, nSq1) - m;

			int nSq2 = (int)Math.ceil(log2(((double)m)/(n-1)));
			int d2 = (n-1)*(int)Math.pow(2, nSq2) - m;
			
			if (n == 1 || d1 < d2 + 1) {
				n *= 2;
			} else {
				n--;				
			}
			
		}
		out.println(res);
	}

	public static void main(String[] args) throws Exception {
		implementation(System.in, System.out);
	}
	
}
