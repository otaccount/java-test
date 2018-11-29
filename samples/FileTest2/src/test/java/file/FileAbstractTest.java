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

//import com.google.common.io.Files;

public class FileAbstractTest {

	FileAbstract fileAbstract = null;

	@Before
	public void setup() {
		// 全体のセットアップ
		fileAbstract = new FileAbstract();
	}

	@Test
	public void writeTest() {
		try(InputStream is = new ByteArrayInputStream("テスト２です".getBytes())){
			fileAbstract.write(is, "test.txt");


		}catch(IOException ie) {
			output(ie);
			fail("err");
		}
	}

	@Test
	public void readTest() {
		try(InputStream is = fileAbstract.read("test.txt")){

			Path path = Paths.get("copy.txt");
			Files.copy(is, path, StandardCopyOption.REPLACE_EXISTING);
//		}catch(FileNotFoundException fe) {
//			output(fe);
		}catch(IOException ie) {
			output(ie);
			fail("err");
		}
	}

	private void output(Object obj) {
		System.out.println(obj);
	}

}
