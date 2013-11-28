package org.metaborg.spttestcaller;

import java.util.HashMap;
import java.util.Map;


public class TestCase {

	private boolean result;
	private boolean finished;
	private long start;
	private long end;
	private String name;
	
	public boolean getResult() {
		return result;
	}
	public void setResult(boolean b) {
		result = b;
	}
	
	public boolean isFinished() {
		return finished;
	}
	public void setFinished(boolean b) {
		finished = b;
	}
	
	public long getRuntime() {
		return end - start;
	}
	public void setStart(long l) {
		start = l;
	}
	public void setEnd(long l) {
		end = l;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String s) {
		name = s;
	}
	
	@Override
	public String toString() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", getName());
		map.put("finished", Boolean.toString(isFinished()));
		if (isFinished()) map.put("result", Boolean.toString(getResult()));
		if (isFinished()) map.put("runtime", Long.toString(getRuntime()));
		return map.toString();
	}
}
