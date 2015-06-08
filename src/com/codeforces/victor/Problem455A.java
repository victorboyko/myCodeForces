package com.codeforces.victor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Stream;

public class Problem455A {

	static class Sets {

		private final Map<Integer, Integer> map;

		public Sets() {
			map = new HashMap<Integer, Integer>();
		}
		
		public Set<Integer> keySet() {
			return map.keySet();
		}
		
		public Integer get(Integer key) {
			Integer res;
			if (map.containsKey(key)) {
				res = map.get(key);
			} else {
				map.put(key, res = Integer.valueOf(0));
			}
			return res;
		}

		/**
		 * Carefully here, this method overwrites the count, use add(Integer) when possible 
		 */
		public void set(Integer key, Integer count) {
			map.put(key, count);
		}
		
		public void add(Integer key) {
			map.put(key, get(key) + 1);
		}
		
		public Integer removeAll(Integer key) {
			return map.remove(key);
		}
		
		@Override
		public String toString() {
			return map.toString();
		}

	}
	
	public static void implementation(InputStream in, PrintStream out) throws Exception {
		Scanner sc = new Scanner(new BufferedInputStream(in));
		Sets sets = new Sets();
		Stream.iterate(1, i -> i + 1).limit(sc.nextInt()).forEach(i -> sets.add(sc.nextInt()));
		sets.set(Collections.min(sets.keySet()) - 1, 0);
		sets.set(Collections.max(sets.keySet()) + 1, 0);
		long result = 0;
		long cnt1, cnt2;
		do {
			List<Integer> keys = new ArrayList<Integer>(sets.keySet());
			Collections.sort(keys);
			
			cnt1 = cnt2 = 0;
			
			for(int i = 1; i < keys.size() - 1; i++) {
				int key = keys.get(i);
				int val = key * sets.get(key);
				if (key % 2 == 0) {
					if (cnt2-cnt1 >= val) {
						break;
					}
					cnt1 += val ;
				} else {
					if (cnt1-cnt2 >= val) {
						break;
					}
					cnt2 += val;
				}
			}
			
			for(int i = 1; i < keys.size() - 1; i++) {
				int key;
				if (((key = keys.get(i)) % 2 == 0) == (cnt1 > cnt2)) {
					result += key * sets.get(key);
					if (i + 1< keys.size() - 1) { // removal sequence is essential, bigger indexes first
						sets.removeAll(key + 1);
					}
					sets.removeAll(key);
					if (i - 1 > 0) {
						sets.removeAll(key - 1);
					}
					break;
				}
			}
			
			
		} while (cnt1 + cnt2 > 0);
		out.println(result);
	}
	
	public static void main(String[] args) throws Exception {
		implementation(System.in, System.out);
	}
	
}
