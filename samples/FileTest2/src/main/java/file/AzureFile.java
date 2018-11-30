package file;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

import ifc.FileIf;

public class AzureFile implements FileIf{

	final String BUCKETNAME = "ootasample01";
	CloudBlobContainer blobContainer = null;

	public AzureFile() {
// proxy環境で実行する場合
//		System.setProperty("http.proxyHost","mtc-px14");
//        System.setProperty("http.proxyPort","8081");
//        System.setProperty("https.proxyHost","mtc-px14");
//        System.setProperty("https.proxyPort","8081");

		String connectionString = System.getProperty("connectionString");

		try {
			CloudStorageAccount account = CloudStorageAccount.parse(connectionString);
			CloudBlobClient serviceClient = account.createCloudBlobClient();
			blobContainer = serviceClient.getContainerReference(BUCKETNAME);
			// コンテナが存在しない場合は作成する
			blobContainer.createIfNotExists();
		}catch(Exception e) {
			out(e);
		}
	}

	@Override
	public void write(InputStream is, final String path) {
		try {
			CloudBlockBlob blob = blobContainer.getBlockBlobReference(path);

			blob.upload(is, 100);
		}catch(Exception e) {
			out(e);
		}
	}

	@Override
	public InputStream read(final String path) {

		return null;
	}

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		out("start");
		FileIf file = new AzureFile();

		out("write");
		try(InputStream is = new ByteArrayInputStream("さんぷる".getBytes(StandardCharsets.UTF_8))){
			file.write(is, "test/test.txt");
			out("write end");
		}catch(IOException ie) {
			out(ie);
		}
		out("end");
	}

	private static void out(Object obj) {
		System.out.println(obj);
	}

}
