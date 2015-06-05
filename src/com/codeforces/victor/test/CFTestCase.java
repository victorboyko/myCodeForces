package com.codeforces.victor.test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.junit.Test;

public class CFTestCase {
	
	@Test
	public final void testAllInputs() throws Exception {
		File assetDir = new File("src/com/codeforces/victor/test/asset/");
		Pattern fileDelim = Pattern.compile("input\n|output\n");
		for(File f: assetDir.listFiles()) {
			System.out.print(String.format("\nPassing tests for %s: ", f.getName()));
			Scanner s = new Scanner(f);
			s.useDelimiter(fileDelim);
			while(s.hasNext()) {
				String input = s.next();
				String correctOutput = s.next();
				assertProcessing(input, correctOutput, Class.forName(String.format("com.codeforces.victor.%s",  f.getName())));
			}
		}
		System.out.println("\nSuccess!");
	}
	
	public static final boolean EXPANDED = false;
	
	@SuppressWarnings({ "rawtypes", "unchecked" }) 
	protected static void assertProcessing(String testIn, String testOut, Class clazz) throws Exception {
		ByteArrayOutputStream baosIn = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baosIn);
		ps.print(testIn);
		InputStream is = new ByteArrayInputStream(baosIn.toByteArray()); 
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Method method = clazz.getMethod("implementation", InputStream.class, PrintStream.class);
		long timeBefore = System.currentTimeMillis();
		method.invoke(null, is, new PrintStream(out));
		System.out.print(EXPANDED ? String.format("\n%s ms", System.currentTimeMillis() - timeBefore) : ".");
		String actualOut = out.toString();
		boolean res = testOut.trim().equals(actualOut.trim());
		if (!res) System.out.println(String.format("\nfailed!\ngiven:\n%sexpected:\n%s\nreceived\n%s", 
				testIn, testOut, actualOut));
		assertTrue(res);
	}
	
}
