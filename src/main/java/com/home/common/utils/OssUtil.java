package com.home.common.utils;

import java.net.URL;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;

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
		String url = "";
		try {
			client.putObject(bucketName, filename, file.getInputStream());
			url = authAccess(filename);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("文件上传失败", e);
		} finally {
			client.shutdown();
		}
		return url;
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
	
	public static void main(String[] args) {
		String filename = "http://articleshare.oss-cn-beijing.aliyuncs.com/Sasa2.jpg?Expires=1541393740&OSSAccessKeyId=LTAIsWtUWWrnS4ly&Signature=va8LQclrrDGr3o2DKH5BIQNbWCU%3D";
		filename = filename.substring(filename.lastIndexOf("/") + 1, filename.indexOf("?"));
		System.out.println(filename);
	}
	
}
