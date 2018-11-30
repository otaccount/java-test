package file;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import com.ibm.cloud.objectstorage.ClientConfiguration;
//import com.ibm.cloud.objectstorage.Protocol;
import com.ibm.cloud.objectstorage.SDKGlobalConfiguration;
import com.ibm.cloud.objectstorage.auth.AWSCredentials;
import com.ibm.cloud.objectstorage.auth.AWSStaticCredentialsProvider;
import com.ibm.cloud.objectstorage.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.ibm.cloud.objectstorage.oauth.BasicIBMOAuthCredentials;
import com.ibm.cloud.objectstorage.services.s3.AmazonS3;
import com.ibm.cloud.objectstorage.services.s3.AmazonS3ClientBuilder;
import com.ibm.cloud.objectstorage.services.s3.model.ObjectMetadata;
import com.ibm.cloud.objectstorage.services.s3.model.PutObjectRequest;
import com.ibm.cloud.objectstorage.services.s3.model.S3Object;

public class BluemixFile extends FileAbstract {
	AmazonS3 client = null;
	String BUCKETNAME = "ottestbucket01";
	private static String COS_AUTH_ENDPOINT = "https://iam.ng.bluemix.net/oidc/token";

	public BluemixFile() {
		SDKGlobalConfiguration.IAM_ENDPOINT = COS_AUTH_ENDPOINT;

		// proxy環境の場合は、ClientConfigurationを設定する
		ClientConfiguration conf = new ClientConfiguration();
		conf.setUseTcpKeepAlive(true);
		conf.withRequestTimeout(5000);
		// 以下はローカル用
//		conf.setProtocol(Protocol.HTTPS);
//		conf.setProxyHost(System.getProperty("proxyHost"));
//		conf.setProxyPort(Integer.parseInt(System.getProperty("proxyPort")));

//		String accessKey = System.getProperty("accessKey");
//		String secretKey = System.getProperty("secretKey");
//		AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

		// 下記の４つは、ibmダッシュボードから取得可能です。
		String apiKey = System.getProperty("apiKey");
		String serviceInstanceId = System.getProperty("serviceInstanceId");
		AWSCredentials credentials = new BasicIBMOAuthCredentials(apiKey, serviceInstanceId);

		String serviceEndpoint = System.getProperty("serviceEndpoint");
		String signingRegion = System.getProperty("signingRegion");
		EndpointConfiguration endpointConfiguration = new EndpointConfiguration(serviceEndpoint, signingRegion);

		client = AmazonS3ClientBuilder.standard()
				.withClientConfiguration(conf)
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withPathStyleAccessEnabled(true)
				.withEndpointConfiguration(endpointConfiguration)
				.build();
	}

	public void test() {
		//InputStreamのサイズ計算？
		//http://tomoyamkung.net/2014/03/27/java-inputstream-getsize/
		client.createBucket("ottestbucket01");
	}

	/**
	 * アップロード
	 */
	@Override
	public void write(InputStream is, final String path) {
		ObjectMetadata om = new ObjectMetadata();

		final PutObjectRequest putRequest = new PutObjectRequest(BUCKETNAME, path, is, om);

		client.putObject(putRequest);
	}

	/**
	 * ダウンロード
	 */
	@Override
	public InputStream read(final String path) {
		S3Object object = client.getObject(BUCKETNAME, path);
		return object.getObjectContent();
	}

	// Null token returned by the Token Provider
	//
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

//		System.setProperty("http.proxyHost","mtc-px14");
//        System.setProperty("http.proxyPort","8081");
//        System.setProperty("https.proxyHost","mtc-px14");
//        System.setProperty("https.proxyPort","8081");

		out("start");
		BluemixFile fa = new BluemixFile();

//		out("test");
//		fa.test();

		out("write");
		try(InputStream is = new ByteArrayInputStream("さんぷる".getBytes(StandardCharsets.UTF_8))){
			fa.write(is, "test/test.txt");
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
