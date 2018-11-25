package com.home.common.utils;

import java.net.URL;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.DownloadFileRequest;
import com.aliyun.oss.model.DownloadFileResult;
import com.aliyun.oss.model.ObjectMetadata;

/**
 * 阿里云OSS 工具类
  * <p>Title: OssUtil</p>  
  * <p>Description: </p>  
  * @author zhangyf 
  * @date 2018年11月5日
 */
public class OssUtil {

	public static String endpoint;

	public static String accessKeyId;

	public static String accessKeySecret;

	public static String bucketName;
	
	private OSSClient client;

	public OssUtil() {
		client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
	}
	
	/**
	 * 文件上传
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public String uploadFile(MultipartFile file) throws Exception {
		String filename = file.getOriginalFilename();
		StringBuffer url = new StringBuffer();
		try {
			client.putObject(bucketName, filename, file.getInputStream());
			//授权访问url
//			url = authAccess(filename);
			url = url.append("https://").append(bucketName).append(".").append(endpoint).append("/").append(filename);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("文件上传失败", e);
		} finally {
			client.shutdown();
		}
		return url.toString();
	}
	
	/**
	 * 授权访问
	 * @param filename
	 * @return
	 */
	public String authAccess(String filename) {
		if (filename.contains("http")) {
			//从数据库中查询出的url
			filename = filename.substring(filename.lastIndexOf("/") + 1, filename.indexOf("?"));
		}
		// 设置URL过期时间为1小时。
		Date expiration = new Date(new Date().getTime() + 3600 * 1000);
		// 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
		URL url = client.generatePresignedUrl(bucketName, filename, expiration);
		shutdown();
		return url.toString();
	}
	
	public void deleteImage(String filename) {
		if (filename.contains("http")) {
			//从数据库中查询出的url
			filename = filename.substring(filename.lastIndexOf("/") + 1, filename.indexOf("?"));
		}
		client.deleteObject(bucketName, filename);
	}
	
	public void shutdown() {
		if (client != null) {
			client.shutdown();
		}
	}
	
	public void downloadFile() throws Throwable {
		// 下载请求，10个任务并发下载，启动断点续传。
		DownloadFileRequest downloadFileRequest = new DownloadFileRequest(bucketName, "1057570712933412865.zip");
		downloadFileRequest.setDownloadFile("E:\\test.zip");
		downloadFileRequest.setPartSize(1 * 1024 * 1024);
		downloadFileRequest.setTaskNum(10);
		downloadFileRequest.setEnableCheckpoint(true);
		downloadFileRequest.setCheckpointFile("uploadFile.ucp");

		// 下载文件。
		DownloadFileResult downloadRes = client.downloadFile(downloadFileRequest);
		// 下载成功时，会返回文件元信息。
		ObjectMetadata objectMetadata = downloadRes.getObjectMetadata();
		
		System.out.println(objectMetadata);
		// 关闭OSSClient。
		client.shutdown();
	}
	
}
