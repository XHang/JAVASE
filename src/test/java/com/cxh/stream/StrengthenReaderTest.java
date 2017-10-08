package com.cxh.stream;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import org.junit.Test;

public class StrengthenReaderTest {
	
	@Test
	public void testReadLine() throws IOException{
		InputStream input=this.getClass().getResourceAsStream("/src.txt");
		Reader reader = new InputStreamReader(input);
		StrengthenReader strengthenReader = new StrengthenReader(reader);
		String line = "";
		System.out.println("readLine File start");
		while((line=strengthenReader.readline())!=null){
			System.out.println(line);
		}
		System.out.println("readLine File end");
		strengthenReader.close();
	}
}
