package com.codeforces.victor;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Problem79B {

	static final String[] PLANTS = {
		"Carrots", "Kiwis", "Grapes", "Waste"
	};
	
	static class Point implements Comparable<Point> {
		int i, j;
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof Point))
				return false;
			Point p = (Point)obj;
			return i == p.i && j == p.j;
		}
		
		@Override
		public int hashCode() {
			return 43 + i + 64 * j;
		}
		
		@Override
		public int compareTo(Point o) {
			return (i - o.i == 0) ? (j - o.j) : (i - o.i);
		}
		
		@Override
		public String toString() {
			return String.format("(%s, %s)", i, j);
		}
		
	}
	

	public static void implementation(InputStream in, PrintStream out) throws Exception {
		int n, m, k, t;
		Scanner s = new Scanner(in);
		n = s.nextInt();
		m = s.nextInt();
		k = s.nextInt();
		t = s.nextInt();
		List<Point> wastePoints = new ArrayList<Point>();
		for(int i = 0; i < k; i++)
			wastePoints.add(new Point(s.nextInt(), s.nextInt()));
		Collections.sort(wastePoints);
		for(int i = 0; i < t; i++) {
			Point test = new Point(s.nextInt(), s.nextInt());
			int clenanNum = (test.i - 1) * m + test.j;
			
			int pos = Collections.binarySearch(wastePoints, test);
			
			if (pos >= 0 && test.equals(wastePoints.get(pos))) {
				out.println(PLANTS[3]);
				continue;
			}
			pos = -pos - 1;
			String res = PLANTS[(clenanNum - pos - 1) % 3];
			out.println(res);
		}
	}
	
	public static void main(String[] args) throws Exception {
		implementation(System.in, System.out);
	}
	
}
