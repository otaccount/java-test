package file;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.junit.Before;
import org.junit.Test;

import ifc.FileIf;

public class AwsFileTest {

	FileIf file = null;
	@Before
	public void setUp() throws Exception {
		file = new AwsFile();
	}

	@Test
	public void writeTest() {
		try(InputStream is = new ByteArrayInputStream("テスト２です".getBytes())){
			file.write(is, "folder/test.txt");
		}catch(IOException ie) {
			fail(ie.getMessage());
		}
	}

	@Test
	public void readTest() {
		try(InputStream is = file.read("folder/test.txt")){
			Path target = Paths.get("test.txt");
			Files.copy(is, target, StandardCopyOption.REPLACE_EXISTING);
		}catch(IOException ie){
			fail(ie.getMessage());
		}
	}

	private void output(Object obj) {
		System.out.println(obj);
	}

}
