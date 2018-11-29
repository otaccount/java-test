package file;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;

import ifc.FileIf;

public class AwsFileTest {

	FileIf file = null;
	@Before
	public void setUp() throws Exception {
		file = new AwsFile();
	}

//	@Test
//	public void test01{
//		assertEquals(System.getProperty("test"), "test");
//	}

	@Test
	public void testWrite() {
		try(InputStream is = new ByteArrayInputStream("テスト２です".getBytes())){
			file.write(is, "folder/test.txt");
		}catch(IOException ie) {
			output(ie);
		}
//		fail("まだ実装されていません");
	}

	@Test
	public void testTest01() {
		assertEquals(System.getProperty("test"), "test");
//		assertEquals(expected, actual);
	}

	private void output(Object obj) {
		System.out.println(obj);
	}

}
