package com.codeforces.victor;

// this solution is too slow :(
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Problem4D {

	
	static class Point {
		int w, h, i;
		public Point(int w, int h, int i) {
			this.w = w;
			this.h = h;
			this.i = i;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof Point))
				return false;
			Point p = (Point)obj;
			return w == p.w && h == p.h;
		}
		
		@Override
		public int hashCode() {
			return 43 + w + 64 * h;
		}
		
		
		@Override
		public String toString() {
			return String.format("(%s, %s):%s", w, h, i);
		}
		
		public boolean covers(Point p) {
			return w > p.w && h > p.h;
		}
		
	}
	
	public static void implementation(InputStream in, PrintStream outS) throws Exception {
		BufferedOutputStream outB = new BufferedOutputStream(outS);
		PrintWriter out = new PrintWriter(outB);
		Scanner s = new Scanner(in);
		int n = s.nextInt();
		int counter = 0;
		Point card = new Point(s.nextInt(), s.nextInt(), 0);
		Set<Point> envs = new HashSet<Point>();
		for(int i = 0; i < n; i++) {
			Point p = new Point(s.nextInt(), s.nextInt(), ++counter);
			if (p.h > card.h && p.w > card.w) {
				envs.add(p);
			}
		}

		Point start = new Point(1000001, 1000001, 0);
		
		final Comparator<LinkedList<Point>> sizeCmp = new Comparator<LinkedList<Point>>() {
			@Override
			public int compare(LinkedList<Point> o1, LinkedList<Point> o2) {
				return (o2.size() == o1.size()) ? o2.hashCode() - o1.hashCode() : o2.size() - o1.size();
			}
		};
		
		Set<LinkedList<Point>> paths = new TreeSet<LinkedList<Point>>(sizeCmp);
		LinkedList<Point> first = new LinkedList<Point>();
		first.add(start);
		paths.add(first);
		
		
		while(!envs.isEmpty()) {
			Set<Point> aggregators = separateAggregators(envs);
			
			Set<LinkedList<Point>> pathsToUpdate = new TreeSet<LinkedList<Point>>(sizeCmp);
			pathsToUpdate.addAll(paths);
			
			for(Point p : aggregators) {
				Iterator<LinkedList<Point>> it = paths.iterator();
				while(it.hasNext()) {
					LinkedList<Point> path = it.next();
					if (path.peekLast().covers(p)) {
						LinkedList<Point> newPath = new LinkedList<Point>(path);
						newPath.add(p);
						pathsToUpdate.add(newPath);
						pathsToUpdate.remove(path);
						break;
					}
				}
			}
			
			paths = pathsToUpdate;			
			
		}
		
		LinkedList<Point> maxPath = Collections.max(paths, new Comparator<LinkedList<Point>>() {
			@Override
			public int compare(LinkedList<Point> o1, LinkedList<Point> o2) {
				return o1.size() - o2.size();
			}
		});
		
		maxPath.pollFirst();
		out.println(maxPath.size());
		Collections.reverse(maxPath);
		
		for(Point p : maxPath) {
			out.print(p.i + " ");
		}
		out.flush();
	}
	
	public static Set<Point> separateAggregators(Set<Point> input) {
		Set<Point> res = new HashSet<Point>();
		
		ArrayList<Point> inputL = new ArrayList<Point>(input);
//		Collections.sort(inputL, new Comparator<Point>() {
//			@Override
//			public int compare(Point o1, Point o2) {
//				return (o2.w == o1.w) ? (o2.h - o1.h) : (o2.w - o1.w);
//			}
//		});
		
		for(int i = 0; i < inputL.size(); i++) {
			Point p = inputL.get(i);
			boolean aggregator = true;

			for(int j = 0; aggregator && j < inputL.size(); j++) {
				Point p2 = inputL.get(j);
				if (p2.covers(p)) {
					aggregator = false;
					break;
				}
			}
			if (aggregator) {
				res.add(p);
				input.remove(p);
			};
		}

		return res;
	}
	
	public static void main(String[] args) throws Exception {
		implementation(System.in, System.out);
	}
	
}