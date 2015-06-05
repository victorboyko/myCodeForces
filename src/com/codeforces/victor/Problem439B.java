package com.codeforces.victor;

import java.io.InputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Problem439B {


	static class IntW {
		public IntW(int i) {
			this.i = i;
		}
		int i;
		@Override
		public String toString() {
			return Integer.toString(i);
		}
		
		int getAndDec() {
			return i--;
		}
	}
	
	static class BigIntegerW {
		public BigIntegerW(BigInteger i) {
			this.i = i;
		}
		BigInteger i;
		@Override
		public String toString() {
			return i.toString();
		}
		
		void setVal(BigInteger i) {
			this.i = i;
		}
	}
	
	public static void implementation(InputStream in, PrintStream out) throws Exception {
		Scanner s = new Scanner(in);
		int n = s.nextInt();
		IntW x = new IntW(s.nextInt());
		List<Integer> subjects = new LinkedList<Integer>();
		Stream.iterate(1, i -> i + 1).limit(n).forEach( i -> subjects.add(s.nextInt()));
		Collections.sort(subjects);
		BigIntegerW res = new BigIntegerW(BigInteger.ZERO);
		subjects.stream().forEach(i -> res.setVal(res.i.add(BigInteger.valueOf(Math.max(1, x.getAndDec())).multiply(BigInteger.valueOf(i)))));
		out.println(res.toString());
	}
	
	public static void main(String[] args) throws Exception {
		implementation(System.in, System.out);
	}
}
