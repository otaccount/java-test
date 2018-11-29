package ot;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class Test01 {

	public static void out(Object obj) {
		System.out.println(obj);
	}

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		out("start");

		if(StringUtils.isEmpty(null)) {
			out("nullです");
		}

//		final String path = "";
		Path path = Paths.get("test.txt");
//		Path path = Paths.get("Book1.xlsx");

		try(FileInputStream fis = new FileInputStream("Book1.xlsx")) {
//			copyTest(fis);
//			readTest(path);
			test(path);
		}catch(Exception e) {
			out(e);
		}

		out("end");
	}

	private static void test(Path path) throws IOException{
		long size = Files.size(path);
//		Files.

		out(size);
	}

	private static void copyTest(InputStream in) throws IOException{
		Path target = Paths.get("out\\output.xlsx");
		Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
	}
	/**
	 *
	 * @param path
	 */
	private static void readTest(Path path) {
		try {
			List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
			for(String s: lines) {
				out(s);
			}
		}catch(IOException ie) {
			out(ie);
		}
	}


}
