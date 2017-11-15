package com.capinfo.sior.pay.CMBC;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.X509EncodedKeySpec;
import java.text.SimpleDateFormat;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class Util {
	// 商户密钥
	public static final String SECRET_KEY = "1234567890abcABC";
	public static final String CHARSET = "UTF-8";

	public static final String HOST = "http://121.15.180.66:801/";

	// 支付+签约
	public static final String URL_PREPAYEUSERP_D = "http://121.15.180.66:801/NetPayment/BaseHttp.dll?MB_EUserPay";

	// 按商户日期查询订单
	public static final String URL_QUERYORDERBYMERCHANTDATE = HOST + "NetPayment_dl/BaseHttp.dll?QuerySettledOrderByMerchantDate";
	// 查询入账明细
	public static final String URL_QUERYACCOUNTEDORDER = HOST + "NetPayment_dl/BaseHttp.dll?QueryAccountList";
	// 查询单笔订单
	public static final String URL_QUERYSINGLEORDER = HOST + "NetPayment_dl/BaseHttp.dll?QuerySingleOrder";
	// 退款
	public static final String URL_DOREFUND = HOST + "NetPayment_dl/BaseHttp.dll?DoRefund";

	public static void main(String[] args) {
		System.out.println(uploadParam("", "http://172.20.65.57:9000/shutdown", "GBK"));
	}
	
	/**
	 * 发送POST请求
	 */
	public static String uploadParam(String jsonParam, String url, String charset) {
		System.out.println("URL:　" + url);
		System.out.println(jsonParam);
		OutputStreamWriter out = null;
		BufferedReader in = null;
		StringBuffer result = new StringBuffer();
		try {
			URL httpUrl = new URL(url);
			HttpURLConnection urlCon = (HttpURLConnection) httpUrl.openConnection();
			urlCon.setRequestMethod("POST");
			urlCon.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
			urlCon.setDoOutput(true);
			urlCon.setDoInput(true);
			urlCon.setReadTimeout(5 * 1000);
			out = new OutputStreamWriter(urlCon.getOutputStream(), charset);// 指定编码格式
			out.write("jsonRequestData=" + jsonParam);
			out.flush();

			in = new BufferedReader(new InputStreamReader(urlCon.getInputStream(), charset));
			String str = null;
			while ((str = in.readLine()) != null) {
				result.append(str);
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result.toString();
	}

	/**
	 * 获取当前时间戳
	 */
	public static String getNowTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		return format.format(System.currentTimeMillis());
	}

	/**
	 * DES加密
	 */
	public static String DesEncrypt(byte[] plain, byte[] key) {
		try {
			SecureRandom random = new SecureRandom();
			DESKeySpec desKeySpec = new DESKeySpec(key);
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(desKeySpec);
			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance("DES");// DES/ECB/PKCS5Padding
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
			// 现在，获取数据并加密
			// 正式执行加密操作
			byte[] byteBuffer = cipher.doFinal(plain);
			// 將 byte转换为16进制string
			StringBuffer strHexString = new StringBuffer();
			for (int i = 0; i < byteBuffer.length; i++) {
				String hex = Integer.toHexString(0xff & byteBuffer[i]);
				if (hex.length() == 1) {
					strHexString.append('0');
				}
				strHexString.append(hex);
			}
			return strHexString.toString();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * RC4加密
	 */
	public static String RC4Encrypt(byte[] plain, byte[] key) {
		try {
			SecretKey secretKey = new SecretKeySpec(key, "RC4");
			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance("RC4");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			// 现在，获取数据并加密
			// 正式执行加密操作
			byte[] byteBuffer = cipher.doFinal(plain);
			// 將 byte转换为16进制string
			StringBuffer strHexString = new StringBuffer();
			for (int i = 0; i < byteBuffer.length; i++) {
				String hex = Integer.toHexString(0xff & byteBuffer[i]);
				if (hex.length() == 1) {
					strHexString.append('0');
				}
				strHexString.append(hex);
			}
			return strHexString.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 验签
	 * @param strToSign 待验证签名字符串
	 * @param strSign   签名结果
	 * @param publicKey 商户公钥
	 * @throws Exception
	 */
	public boolean isValidSignature(String strToSign, String strSign,
								 String publicKey) throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		byte[] encodedKey = Base64.decode(publicKey);
		PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));

		java.security.Signature signature = java.security.Signature
				.getInstance("SHA1WithRSA");

		signature.initVerify(pubKey);
		signature.update(strToSign.getBytes("UTF-8") );

		boolean bverify = signature.verify( Base64.decode(strSign) );
		return bverify;
	}
}
