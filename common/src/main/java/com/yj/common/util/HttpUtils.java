package com.yj.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;


public class HttpUtils {

	/**
	 * 发送HTTPS POST请求
	 * 
	 * @param 要访问的HTTPS地址,POST访问的参数Map对象
	 * @return 返回响应值
	 */
	public static final String sendHttpsRequestByPost(String url, Map<String, String> params) {
		String responseContent = null;
		HttpClient httpClient = new DefaultHttpClient();
		// 创建TrustManager
		X509TrustManager xtm = new X509TrustManager() {
			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		};
		// 这个好像是HOST验证
		X509HostnameVerifier hostnameVerifier = new X509HostnameVerifier() {
			public boolean verify(String arg0, SSLSession arg1) {
				return true;
			}

			public void verify(String arg0, SSLSocket arg1) throws IOException {
			}

			public void verify(String arg0, String[] arg1, String[] arg2) throws SSLException {
			}

			public void verify(String arg0, X509Certificate arg1) throws SSLException {
			}
		};
		try {
			// TLS1.0与SSL3.0基本上没有太大的差别，可粗略理解为TLS是SSL的继承者，但它们使用的是相同的SSLContext
			SSLContext ctx = SSLContext.getInstance("TLS");
			// 使用TrustManager来初始化该上下文，TrustManager只是被SSL的Socket所使用
			ctx.init(null, new TrustManager[] { xtm }, null);
			// 创建SSLSocketFactory
			SSLSocketFactory socketFactory = new SSLSocketFactory(ctx);
			socketFactory.setHostnameVerifier(hostnameVerifier);
			// 通过SchemeRegistry将SSLSocketFactory注册到我们的HttpClient上
			httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", socketFactory, 443));
			HttpPost httpPost = new HttpPost(url);

			List<NameValuePair> formParams = new ArrayList<NameValuePair>(); // 构建POST请求的表单参数
			for (Map.Entry<String, String> entry : params.entrySet()) {
				formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			httpPost.setEntity(new UrlEncodedFormEntity(formParams, "UTF-8"));
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity(); // 获取响应实体
			if (entity != null) {
				responseContent = EntityUtils.toString(entity, "UTF-8");
			}
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			httpClient.getConnectionManager().shutdown();
		}
		return responseContent;
	}

	/**
	 * 发送HTTPS POST请求
	 * 
	 * @param 要访问的HTTPS地址,POST访问的参数Map对象
	 * @return 返回响应值
	 */
	public static final String sendHttpsRequestByPost(String url, String authorization) {
		String responseContent = null;
		HttpClient httpClient = new DefaultHttpClient();
		// 创建TrustManager
		X509TrustManager xtm = new X509TrustManager() {
			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		};
		// 这个好像是HOST验证
		X509HostnameVerifier hostnameVerifier = new X509HostnameVerifier() {
			public boolean verify(String arg0, SSLSession arg1) {
				return true;
			}

			public void verify(String arg0, SSLSocket arg1) throws IOException {
			}

			public void verify(String arg0, String[] arg1, String[] arg2) throws SSLException {
			}

			public void verify(String arg0, X509Certificate arg1) throws SSLException {
			}
		};
		try {
			// TLS1.0与SSL3.0基本上没有太大的差别，可粗略理解为TLS是SSL的继承者，但它们使用的是相同的SSLContext
			SSLContext ctx = SSLContext.getInstance("TLS");
			// 使用TrustManager来初始化该上下文，TrustManager只是被SSL的Socket所使用
			ctx.init(null, new TrustManager[] { xtm }, null);
			// 创建SSLSocketFactory
			SSLSocketFactory socketFactory = new SSLSocketFactory(ctx);
			socketFactory.setHostnameVerifier(hostnameVerifier);
			// 通过SchemeRegistry将SSLSocketFactory注册到我们的HttpClient上
			httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", socketFactory, 443));
			HttpGet httpGet = new HttpGet(url);
			httpGet.addHeader("Authorization", "bearer " + authorization);

			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity(); // 获取响应实体
			if (entity != null) {
				responseContent = EntityUtils.toString(entity, "UTF-8");
			}
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			httpClient.getConnectionManager().shutdown();
		}
		return responseContent;
	}

	public static String httpGet(String url,String paramstr) throws Exception {
		
		CloseableHttpClient httpClient = HttpClients.createDefault(); 
		CloseableHttpResponse response = null; 
		InputStream is = null; 
		String body = null; 
		StringBuffer buffer = new StringBuffer();
		try {
			//转换为键值对 
			//str = EntityUtils.toString(new UrlEncodedFormEntity(mapToNameValuePair(mapParams), Consts.UTF_8)); 
			//创建Get请求 
			String urlencode = url+"?"+paramstr;
			System.out.println(urlencode);
			HttpGet httpGet = new HttpGet(urlencode); 
			httpGet.setHeader("Accept","application/json");
			//执行Get请求， 
			response = httpClient.execute(httpGet); 
			//得到响应体 
			HttpEntity entity = response.getEntity(); 
			if(entity != null){ 
				is = entity.getContent(); 
				//转换为字节输入流 
				BufferedReader br = new BufferedReader(new InputStreamReader(is, Consts.UTF_8)); 
				while((body=br.readLine()) != null){ 
					buffer.append(body);
				} 
			}	
		} catch (Exception e) { 
			throw e;
		} finally{ 
			//关闭输入流，释放资源 
			if(is != null){
				try { 
					is.close(); 
				} catch (IOException e) { 
					throw e;
				} 
			} 
			//消耗实体内容 
			if(response != null){ 
				try { 
					response.close(); 
				} catch (IOException e) { 
					throw e;
				} 
			} 
			//关闭相应 丢弃http连接 
			if(httpClient != null){ 
				try { 
					httpClient.close(); 
				} catch (IOException e) { 
					throw e;
				} 
			} 
		} 
		System.out.println(buffer.toString());
		return buffer.toString();
	}
	
	/**
	 * get请求支持header参数
	 * @param url
	 * @param headerMaps
	 * @param mapParams
	 * @return
	 */
	public static String doGet(String url, Map<String, String> mapParams) {
		return doGet(url,null,mapParams);
	}
	
	/**
	 * get请求支持header参数
	 * @param url
	 * @param headerMaps
	 * @param mapParams
	 * @return
	 */
	public static String doGet(String url,Map<String, String> headerMaps, Map<String, String> mapParams)   {
		CloseableHttpClient httpClient = HttpClients.createDefault(); // getHttpClient()
																		// ;
		CloseableHttpResponse response = null;
		InputStream is = null;

		String str = "";
		String body = null;
		StringBuffer buffer = new StringBuffer();

		try {
			// 转换为键值对
			str = EntityUtils.toString(new UrlEncodedFormEntity(mapToNameValuePair(mapParams), Consts.UTF_8));
			// 创建Get请求
			HttpGet httpGet = new HttpGet(url + "?" + str);
			// 执行Get请求，
			 
			httpGet.setHeader("Accept", "application/json");
			if(headerMaps !=null)
			httpGet = addHeaders(  httpGet, headerMaps);

			
			response = httpClient.execute(httpGet);
			// 得到响应体
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				is = entity.getContent();
				// 转换为字节输入流
				BufferedReader br = new BufferedReader(new InputStreamReader(is, Consts.UTF_8));

				while ((body = br.readLine()) != null) {
					//System.out.println(body);
					buffer.append(body);
				}

				return buffer.toString();

			}
		}catch (IOException e) {
		} finally {
			// 关闭输入流，释放资源
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			// 消耗实体内容
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			// 关闭相应 丢弃http连接
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();

				}
			}
		}
		return null;
	}

	public static HttpGet addHeaders(HttpGet httpGet,Map<String, String> headerMaps) {
		 
		for (Map.Entry<String, String> entry : headerMaps.entrySet()) {
			
			httpGet.addHeader(entry.getKey(), entry.getValue());
		}
		return httpGet;
	}

	
	/**
	 * post请求(用于key-value格式的参数)
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static String doPost(String url, Map params) {

		BufferedReader in = null;
		try {
			// 定义HttpClient
			HttpClient client = new DefaultHttpClient();
			// 实例化HTTP方法
			HttpPost request = new HttpPost();
			request.setURI(new URI(url));

			// 设置参数
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			for (Iterator iter = params.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String value = String.valueOf(params.get(name));
				nvps.add(new BasicNameValuePair(name, value));

				// System.out.println(name +"-"+value);
			}
			request.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

			HttpResponse response = client.execute(request);
			int code = response.getStatusLine().getStatusCode();
			if (code == 200) { // 请求成功
				in = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "utf-8"));
				StringBuffer sb = new StringBuffer("");
				String line = "";
				String NL = System.getProperty("line.separator");
				while ((line = in.readLine()) != null) {
					sb.append(line + NL);
				}

				in.close();

				return sb.toString();
			} else { //
//				System.out.println("状态码：" + code);
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}	
	
	
	/**
	 * post请求(用于key-value格式的参数)
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static String doPost(String url,Map  headerMaps, Map params) {

		BufferedReader in = null;
		try {
			HttpClient httpClient = new DefaultHttpClient();
			// 创建TrustManager
			X509TrustManager xtm = new X509TrustManager() {
				public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				}

				public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				}

				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			};
			// 这个好像是HOST验证
			X509HostnameVerifier hostnameVerifier = new X509HostnameVerifier() {
				public boolean verify(String arg0, SSLSession arg1) {
					return true;
				}

				public void verify(String arg0, SSLSocket arg1) throws IOException {
				}

				public void verify(String arg0, String[] arg1, String[] arg2) throws SSLException {
				}

				public void verify(String arg0, X509Certificate arg1) throws SSLException {
				}
			};
			
			// TLS1.0与SSL3.0基本上没有太大的差别，可粗略理解为TLS是SSL的继承者，但它们使用的是相同的SSLContext
						SSLContext ctx = SSLContext.getInstance("TLS");
						// 使用TrustManager来初始化该上下文，TrustManager只是被SSL的Socket所使用
						ctx.init(null, new TrustManager[] { xtm }, null);
						// 创建SSLSocketFactory
						SSLSocketFactory socketFactory = new SSLSocketFactory(ctx);
						socketFactory.setHostnameVerifier(hostnameVerifier);
						// 通过SchemeRegistry将SSLSocketFactory注册到我们的HttpClient上
						httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", socketFactory, 443));
						HttpPost request = new HttpPost(url);
			// 定义HttpClient
//			HttpClient client = new DefaultHttpClient();
			// 实例化HTTP方法
		//	HttpPost request = new HttpPost();
			request.setURI(new URI(url));
			 if(headerMaps!=null)
			request = addHeadersForPost(  request, headerMaps);
			
			// 设置参数
			 JSONObject json = new JSONObject();
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			for (Iterator iter = params.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String value = String.valueOf(params.get(name));
				nvps.add(new BasicNameValuePair(name, value));
				json.put(name, value);
 			}
 			request.setEntity(new StringEntity(json.toString()));

			HttpResponse response = httpClient.execute(request);
			int code = response.getStatusLine().getStatusCode();
			if (code == 200) { // 请求成功
				in = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "utf-8"));
				StringBuffer sb = new StringBuffer("");
				String line = "";
				String NL = System.getProperty("line.separator");
				while ((line = in.readLine()) != null) {
					sb.append(line + NL);
				}

				in.close();

				return sb.toString();
			} else { //
				System.out.println("状态码：" + code);
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}
	
	public static HttpPost addHeadersForPost(HttpPost httpPost,Map<String, String> headerMaps) {
		for (Map.Entry<String, String> entry : headerMaps.entrySet()) {
			
			httpPost.addHeader(entry.getKey(), entry.getValue());
		}
		return httpPost;
	}
	
	/**
	* 将传进来的map参数值转换为List<NameValuePair> ， 发送请求时附带参数
	* @param mapParams
	* @return
	*/
	public static List<NameValuePair> mapToNameValuePair(Map<String, String> mapParams){
	
	List<NameValuePair> params =new ArrayList<NameValuePair>();
	for (Map.Entry<String, String> entry : mapParams.entrySet()) {
		params.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
	}
		return params;
	}
}
