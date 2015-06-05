package com.codeforces.victor;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Stream;

public class Problem103B {

	static class Graph {

		private final Map<Integer, Set<Integer>> map;

		public Graph() {
			map = new HashMap<Integer, Set<Integer>>();
		}

		public Graph(int capacity) {
			map = new HashMap<Integer, Set<Integer>>(capacity);
		}

		public Set<Integer> get(Integer key) {
			return Collections.unmodifiableSet(getModifiable(key));
		}
		
		private Set<Integer> getModifiable(Integer key) {
			Set<Integer> res;
			if (map.containsKey(key)) {
				res = map.get(key);
			} else {
				map.put(key, res = new HashSet<Integer>());
			}
			return res;
		}

		public void put(Integer key, Integer value) {
			getModifiable(key).add(value);
			getModifiable(value).add(key);
		}

		public void remove(Integer key, Integer value) {
			getModifiable(key).remove(value);
			getModifiable(value).remove(key);
		}
	}

	public static void implementation(InputStream in, PrintStream out)
			throws Exception {
		Scanner s = new Scanner(in);
		int n = s.nextInt();
		int m = s.nextInt();
		Graph g = new Graph(n);
		Stream.iterate(1, i -> i + 1).limit(m)
				.forEach(i -> g.put(s.nextInt(), s.nextInt()));
		System.out.println("OUT!");
	}

	public static void main(String[] args) throws Exception {
		implementation(System.in, System.out);
	}

}
