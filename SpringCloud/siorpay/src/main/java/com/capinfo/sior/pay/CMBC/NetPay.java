package com.capinfo.sior.pay.CMBC;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.capinfo.sior.pay.CMBC.Util;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings(value = "all")
public class NetPay {

    public static void main(String[] args) {
        String result = "";
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("1,支付签约     2,查询入账明细    3,查询单笔订单明细    4,退款    5,查询已处理订单（按商户日期查询）   0,退出");
            switch (in.nextInt()) {
                case 1:// 支付+签约
                    result = Util.uploadParam(prePay(), Util.URL_PREPAYEUSERP_D, Util.CHARSET);
                    break;
                case 2:// 查询入账明细
                    result = Util.uploadParam(queryAccountedOrder(), Util.URL_QUERYACCOUNTEDORDER, Util.CHARSET);
                    break;
                case 3:// 查询单笔订单明细
                    result = Util.uploadParam(querySingleOrder(), Util.URL_QUERYSINGLEORDER, Util.CHARSET);
                    break;
                case 4:// 退款
                    result = Util.uploadParam(doRefund(), Util.URL_DOREFUND, Util.CHARSET);
                    break;
                case 5:// 查询已处理订单（按处理日期查询）
                    result = Util.uploadParam(queryByMerchantDate(), Util.URL_QUERYORDERBYMERCHANTDATE, Util.CHARSET);
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    break;
            }
            System.out.println("result:  " + result);
        }

    }

    /**
     * 签约+支付
     */
    public static String prePay() {
        Map<String, String> reqData = new HashMap<String, String>();
        reqData.put("dateTime", Util.getNowTime());
        reqData.put("branchNo", "0755");
        reqData.put("merchantNo", "000054");
        reqData.put("date", "20160624");
        reqData.put("orderNo", "9999000001");
        reqData.put("amount", "0.01");
        reqData.put("expireTimeSpan", "30");
        reqData.put("payNoticeUrl", "http://www.merchant.com/path/WAPProcResult.dll");
        reqData.put("payNoticePara", "支付");
        reqData.put("returnUrl", "");
        reqData.put("cardType", "");
        reqData.put("agrNo", "9934567890987654332");// 已签约客户协议号
        // reqData.put("agrNo", "12345678901234567890");//新签约协议号
        // reqData.put("merchantSerialNo", "20160804100538");
        reqData.put("userID", "2016062388888");
        reqData.put("mobile", "18202532653");
        reqData.put("lon", "30.949505");
        reqData.put("lat", "50.949506");
        reqData.put("riskLevel", "3");
        reqData.put("signNoticeUrl", "");
        reqData.put("signNoticePara", "");
        reqData.put("merchantCssUrl", "");
        reqData.put("merchantBridgeName", "");

        return buildParam(reqData);
    }

    /**
     * 查询已处理订单（按商户日期查询）
     */
    public static String queryByMerchantDate() {
        Map<String, String> reqData = new HashMap<String, String>();
        reqData.put("dateTime", Util.getNowTime());
        reqData.put("branchNo", "0755");
        reqData.put("merchantNo", "000054");
        reqData.put("beginDate", "20160502");
        reqData.put("endDate", "20160503");
        reqData.put("operatorNo", "9999");
        reqData.put("nextKeyValue", "");

        return buildParam(reqData);
    }

    /**
     * 给请求数据增加签名字段
     */
    public static String addSignParam(String reqJSON) {
        try {
            JSONObject param = new JSONObject(reqJSON);
            String reqData = param.getString("reqData");
            String sign = sign(reqData, Util.SECRET_KEY, param.getString("charset"));
            param.put("sign", sign);
            return param.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 查询入账明细
     */
    public static String queryAccountedOrder() {
        Map<String, String> reqData = new HashMap<String, String>();
        reqData.put("dateTime", Util.getNowTime());
        reqData.put("branchNo", "0755");
        reqData.put("merchantNo", "000054");
        reqData.put("date", "20160625");
        reqData.put("operatorNo", "9999");
        reqData.put("nextKeyValue", "");

        return buildParam(reqData);
    }

    /**
     * 查询单笔订单明细
     */
    public static String querySingleOrder() {
        Map<String, String> reqData = new HashMap<String, String>();
        reqData.put("dateTime", Util.getNowTime());
        reqData.put("branchNo", "0755");
        reqData.put("merchantNo", "000054");
        reqData.put("type", "A");
        reqData.put("bankSerialNo", "16250323300000000010");
        reqData.put("date", "20160625");
        reqData.put("orderNo", "9999000001");
        reqData.put("orderRefNo", "");
        reqData.put("operatorNo", "9999");

        return buildParam(reqData);
    }

    /**
     * 退款
     */
    public static String doRefund() {
        Map<String, String> reqData = new HashMap<String, String>();
        reqData.put("dateTime", Util.getNowTime());
        reqData.put("branchNo", "0755");
        reqData.put("merchantNo", "000054");
        reqData.put("date", "20160503");
        reqData.put("orderNo", "9999000015");
        reqData.put("amount", "0.01");
        reqData.put("desc", "取消订单");
        reqData.put("refundSerialNo", "16250323300000000010");
        reqData.put("operatorNo", "9998");
        reqData.put("encrypType", "");
        reqData.put("pwd", "123456");

        return buildParam(reqData);
    }

    public static String buildParam(Map<String, String> reqDataMap) {
        JSONObject jsonParam = new JSONObject();
        try {
            jsonParam.put("version", "1.0");
            jsonParam.put("charset", Util.CHARSET);// 支持GBK和UTF-8两种编码
            jsonParam.put("sign", sign(reqDataMap, Util.SECRET_KEY));
            jsonParam.put("signType", "SHA-256");
            jsonParam.put("reqData", reqDataMap);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonParam.toString();
    }

    /**
     * 对参数签名：
     * 对reqData所有请求参数按从a到z的字典顺序排列，如果首字母相同，按第二个字母排列，以此类推。排序完成后按将所有键值对以“&”符号拼接。
     * 拼接完成后再加上商户密钥。示例：param1=value1&param2=value2&...&paramN=valueN&secretKey
     *
     * @param reqDataMap 请求参数
     * @param secretKey  商户密钥
     */
    public static String sign(Map<String, String> reqDataMap, String secretKey) {
        StringBuffer buffer = new StringBuffer();
        List<String> keyList = sortParams(reqDataMap);
        for (String key : keyList) {
            buffer.append(key).append("=").append(reqDataMap.get(key)).append("&");
        }
        buffer.append(secretKey);// 商户密钥
        System.out.println(buffer.toString());

        try {
            // 创建加密对象
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            // 传入要加密的字符串,按指定的字符集将字符串转换为字节流
            messageDigest.update(buffer.toString().getBytes(Util.CHARSET));
            byte byteBuffer[] = messageDigest.digest();

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
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 对参数签名
     */
    public static String sign(String reqDataJSON, String secretKey, String charset) {
        StringBuffer buffer = new StringBuffer();

        try {
            JSONObject json = new JSONObject(reqDataJSON);
            List<String> keyList = sortParams(json);
            for (String key : keyList) {
                buffer.append(key).append("=").append(json.get(key)).append("&");
            }
            buffer.append(secretKey);// 商户密钥
            System.out.println(buffer.toString());
            // 创建加密对象
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            // 传入要加密的字符串,按指定的字符集将字符串转换为字节流
            messageDigest.update(buffer.toString().getBytes(charset));
            byte byteBuffer[] = messageDigest.digest();

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
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 对参数按字典顺序排序，不区分大小写
     */
    public static List<String> sortParams(Map<String, String> reqDataMap) {
        List<String> list = new ArrayList<String>(reqDataMap.keySet());
        Collections.sort(list, new Comparator<String>() {
            public int compare(String o1, String o2) {
                String[] temp = {o1.toLowerCase(), o2.toLowerCase()};
                Arrays.sort(temp);
                if (o1.equalsIgnoreCase(temp[0])) {
                    return -1;
                } else if (temp[0].equalsIgnoreCase(temp[1])) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
        return list;
    }

    /**
     * 对参数排序
     */
    public static List<String> sortParams(JSONObject json) {
        List<String> list = new ArrayList<String>();
        Iterator it = json.keys();
        while (it.hasNext()) {
            list.add((String) it.next());
        }
        Collections.sort(list, new Comparator<String>() {
            public int compare(String o1, String o2) {
                String[] temp = {o1.toLowerCase(), o2.toLowerCase()};
                Arrays.sort(temp);
                if (o1.equalsIgnoreCase(temp[0])) {
                    return -1;
                } else if (temp[0].equalsIgnoreCase(temp[1])) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
        return list;
    }

    public static String queryPubkey() {
        Map<String, String> reqData = new HashMap<String, String>();
        reqData.put("dateTime", Util.getNowTime());
        reqData.put("branchNo", "0755");
        reqData.put("txCode", "FBPK");
        reqData.put("merchantNo", "002346");

        return buildParam(reqData);
    }
}
