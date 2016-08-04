/**
 * 
 */
package com.howbuy.uaa.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * @author qiankun.li
 * 文件工具类
 */
public class FileUtil {

	private static final Logger LOGGER = Logger.getLogger(FileUtil.class);
	private static int BUF_SIZE = 1024;

	public static void readRemoteFile(String fromUrl, String localFile) {
		if (StringUtils.isBlank(fromUrl) || StringUtils.isBlank(localFile)) {
			LOGGER.error("fromUrl is null or localFile is null");
			return;
		}
		BufferedOutputStream outStream = null;
		InputStream inStream = null;
		int byteLength = 0;
		byte[] input_buffer = new byte[BUF_SIZE];

		try {
			URL url = new URL(fromUrl);
			URLConnection con = url.openConnection();
			inStream = con.getInputStream();
			outStream = new BufferedOutputStream(new FileOutputStream(localFile),
					BUF_SIZE);
			while ((byteLength = inStream.read(input_buffer, 0, BUF_SIZE)) > 0) {
				outStream.write(input_buffer, 0, byteLength);
			}
			outStream.flush();
		} catch (MalformedURLException e) {
			LOGGER.error(e);
		} catch (IOException e) {
			LOGGER.error(e);
		} finally {

			try {
				if (outStream != null) {
					outStream.close();
				}
				if (inStream != null) {
					inStream.close();
				}
			} catch (IOException e) {
				LOGGER.error(e);
			}
		}


	}
	
	public static String readRemoteFileToString(String fromUrl,String encoding) {
		if (StringUtils.isBlank(fromUrl)) {
			LOGGER.error("fromUrl is null");
			return null;
		}
		InputStream inStream = null;
		try {
			URL url = new URL(fromUrl);
			URLConnection con = url.openConnection();
			inStream = con.getInputStream();
			return IOUtils.toString(inStream, encoding);

		} catch (MalformedURLException e) {
			LOGGER.error(e);
		} catch (IOException e) {
			LOGGER.error(e);
		} finally {

			try {
				if (inStream != null) {
					inStream.close();
				}
			} catch (IOException e) {
				LOGGER.error(e);
			}
		}
		return null;
	}
	
	public static List<String> readRemoteFileToLines(String fromUrl,String encoding) {
		if (StringUtils.isBlank(fromUrl)) {
			LOGGER.error("fromUrl is null");
			return null;
		}
		InputStream inStream = null;
		try {
			URL url = new URL(fromUrl);
			URLConnection con = url.openConnection();
			inStream = con.getInputStream();
			return IOUtils.readLines(inStream, encoding);
			
		} catch (MalformedURLException e) {
			LOGGER.error(e);
		} catch (IOException e) {
			LOGGER.error(e);
		} finally {
			
			try {
				if (inStream != null) {
					inStream.close();
				}
			} catch (IOException e) {
				LOGGER.error(e);
			}
		}
		return null;
	}
	
	public static void down(File file,String fileNameVar,HttpServletResponse response){
		try {
			String fileName = file.getName();
			if(StringUtils.isNotBlank(fileNameVar)){
				fileName = fileNameVar;
			}
			fileName = URLEncoder.encode(fileName, "UTF-8");  
			response.reset();  
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");  
			response.setContentType("application/octet-stream;charset=UTF-8");
			BufferedInputStream bis = null;  
			bis = new BufferedInputStream(new FileInputStream(file));  
			OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());  
			byte[] buff = new byte[2048];  
	        int bytesRead;  
	        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {  
	        	outputStream.write(buff, 0, bytesRead);  
	        }   
	        bis.close();
			outputStream.flush();  
			outputStream.close();
		} catch (UnsupportedEncodingException e) {
			LOGGER.error(e);
		} catch (IOException e) {
			LOGGER.error(e);
		}  
	}

}
