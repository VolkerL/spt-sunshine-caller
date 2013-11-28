package org.metaborg.spttestcaller;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TestSuite {
	@JsonProperty
	private List<TestCase> testCases;
	private String name;
	private String filename;
	
	public List<TestCase> getTestCases() {
		return testCases;
	}
	public void setTestCases(List<TestCase> testcases) {
		testCases = testcases;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String s) {
		filename = s;
	}
	
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		for (TestCase c : testCases) {
			b.append(c).append("\n\n");
		}
		return b.toString();
	}
}
