package gleen;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;

/* Comment !!!!! */
public class S3Tests {
	
	private static AmazonS3 s3;
	private static final String BUCKET_NAME = "gleen";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		s3 = new AmazonS3Client(new PropertiesCredentials(S3Tests.class.getClassLoader().getResourceAsStream("awsCredentials.properties")));
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Test
	public void testCreateBucket() {
		s3.createBucket(BUCKET_NAME);
	}
	
	@Test
	public void testDeleteBucket() {
		s3.deleteBucket(BUCKET_NAME);
	}
	
	@Test
	public void testCreateFolderObject() {		
		s3.putObject(BUCKET_NAME,"folder/",S3Tests.class.getClassLoader().getResourceAsStream("file.txt"),null);
	}
	
	@Test
	public void testCreateObjectInFolder() {				
		PutObjectRequest por = new PutObjectRequest(BUCKET_NAME,"folder/someFile.txt",S3Tests.class.getClassLoader().getResourceAsStream("file.txt"), null).withCannedAcl(CannedAccessControlList.PublicRead);
		s3.putObject(por);
	}
	
	@Test
	public void testListBuckets() {		
		List<Bucket> buckets = s3.listBuckets();
		for(Bucket bucket: buckets) {
			System.out.println(bucket.getName());
		}	
	}
	
	@Test
	public void testListObjects() {		
		ObjectListing objectListing = s3.listObjects(new ListObjectsRequest().withBucketName(BUCKET_NAME).withPrefix("folder/"));
		for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
			System.out.println(" - " + objectSummary.getKey() + "  " +
				"(size = " + objectSummary.getSize() + ")" );
        }
	}
	
}
