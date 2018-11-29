package file;

import java.io.InputStream;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class AwsFile extends FileAbstract {
	AmazonS3 client = null;

	public AwsFile() {
		ClientConfiguration conf = new ClientConfiguration();
		conf.setProtocol(Protocol.HTTPS);
		conf.setProxyHost(System.getProperty("proxyHost"));
		conf.setProxyPort(Integer.parseInt(System.getProperty("proxyPort")));

		String accessKey = System.getProperty("accessKey");
		String secretKey = System.getProperty("secretKey");
		AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

		String serviceEndpoint = System.getProperty("serviceEndpoint");
		String signingRegion = System.getProperty("signingRegion");
		EndpointConfiguration endpointConfiguration = new EndpointConfiguration(serviceEndpoint, signingRegion);

		client = AmazonS3ClientBuilder.standard()
				.withClientConfiguration(conf)
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withEndpointConfiguration(endpointConfiguration)
				.build();
	}

	@Override
	public void write(InputStream is, final String path) {
		String bucketName = "s3testot";
		ObjectMetadata om = new ObjectMetadata();

		final PutObjectRequest putRequest = new PutObjectRequest(bucketName, path, is, om);

		client.putObject(putRequest);
	}

	@Override
	public InputStream read(final String path) {

		return null;
	}
}
