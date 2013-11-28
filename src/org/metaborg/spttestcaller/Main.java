package org.metaborg.spttestcaller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
	
	// NOTE: we require the Jackson libraries on the classpath
	
	private static final String REPORT_EXT = ".testreport";
	
	private File AutoLangDir;
	private File TestCaseProjectDir;
	private final String builder = "test-runner-file";
	// NOTE: be aware of caching, it might influence stuff
	private final String[] extraFlags = new String[]{"--no-analysis"};
	
	/**
	 * Initialize the command line options.
	 * @param autoLangDir the directory under which the languages are stored.
	 * @param testCaseProjectDir the home directory of the (test)project.
	 */
	public void init(File autoLangDir, File testCaseProjectDir) {
		this.AutoLangDir = autoLangDir;
		this.TestCaseProjectDir = testCaseProjectDir;
	}
	
	/**
	 * Call the current builder ({@value #builder}) on the given testsuite.
	 * 
	 * Note: requires {@link #init} to have been called.
	 * 
	 * @param testsuite the spt file to run the builder on.
	 */
	public void call(File testsuite) {
		// setup command line arguments
		List<String> argList = new ArrayList<String>();
		argList.add("--auto-lang");
		argList.add(AutoLangDir.getAbsolutePath());
		
		argList.add("--project");
		argList.add(TestCaseProjectDir.getAbsolutePath());
		
		argList.add("--builder");
		argList.add(builder);
		
		argList.add("--build-on");
		argList.add(getRelativePath(TestCaseProjectDir, testsuite));
		
		for (String flag : extraFlags) {
			argList.add(flag);
		}
		
		// call Sunshine
		org.spoofax.sunshine.drivers.Main.main(argList.toArray(new String[argList.size()]));
		
		String sptFilePath = testsuite.getAbsolutePath();
		String base = sptFilePath.substring(0, sptFilePath.lastIndexOf('.'));
		try {
			TestSuite resultSuite = new ObjectMapper().readValue(new File(base+REPORT_EXT), TestSuite.class);
			// TODO do useful stuff with the testsuite. Return it, for example.
			System.out.println(resultSuite);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private String getRelativePath(File parent, File file) {
		String parentPath = parent.getAbsolutePath();
		String childPath = file.getAbsolutePath();
		String separator = System.getProperty("file.separator");
		if (!childPath.startsWith(parentPath)) throw new IllegalArgumentException("File "+file+" is not a child of "+parent); 
		return childPath.substring(parentPath.endsWith(separator) ? parentPath.length() : (parentPath.length() + 1));
	}
	
	
	
	/**
	 * Example of how to use this class.
	 * @param args
	 */
	public static void main(String[] args) {
		Main m = new Main();
		m.init(
				new File("/Users/volker/Documents/git/compiler-ta/"),
				new File("/Users/volker/Documents/git/compiler-ta/spoofax-minijava/test-cases/"));
		m.call(new File("/Users/volker/Documents/git/compiler-ta/spoofax-minijava/test-cases/syntax/context-free/ClassDecl/pos2.spt"));
	}
}
