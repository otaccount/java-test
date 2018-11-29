package file;
//package file;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//class FileAbstractTest {
//
//	FileAbstract fileAbstract = null;
//
//	@BeforeEach
//	void setup() {
//		// 全体のセットアップ
//		fileAbstract = new FileAbstract();
//	}
//
//	@Test
//	void test() {
//		try(InputStream is = new ByteArrayInputStream("テスト２".getBytes())){
//			fileAbstract.write(is, "test.txt");
//		}catch(IOException ie) {
//			output(ie);
//		}
//	}
//
//	@Test
//	void test2() {
//		assertEquals(1, 1);
//	}
//
//	@Test
//	void test3() {
//		assertEquals(1, 2);
//	}
//
//	private void output(Object obj) {
//		System.out.println(obj);
//	}
//}
