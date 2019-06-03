package com.kicol.config;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @Author: 韩老魔
 * @Date: 2019/2/7 0007 22:40
 * 类名：AlipayConfig
 * 功能：基础配置类
 * 详细：设置帐户有关信息及返回路径
 * 说明：
 * 宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016092400589002";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCZ6i9RQ2ZFgGI5srQ9nR4McVtJUZN0mZEKZoqVC2K1oG5NV4os2GZJ/wZAeIjPhLwhq6QP3nT2F99Kn9lEnVrJJMm/obgwznlB7Do4R57x0Z9jwg3h5faKS8xGbagJkNMQW4h/wP6QN9bS8fzrsJ8uFY+ifmpkgdn6lUBQIj+tNjxyhrHgfcH7yGmhFVEfha5DmEeTTKHUjBCMB6MaWSkKFtxJO+7F9SQt0hXXTKU5m48mYTtM8tplvGbEusqyl97UX7mAUWs8IERVABv8JZZYOeCDJ1q+HadGFqxRQL3TKx+ZlNOUnjqcww5HW2bl0K28rvhaXCgVs+xjmsrH+8jfAgMBAAECggEAbEwUKSi0cFjKRHDSU7wWpYipQ6iLiXJ4VIocHo0ZUlTk57Sdu7o0FwLDQl/NW764pTRhRu0u/O2FJrvco2PYukkRT64ZrPXK6jCuMmVqmBn3/bhQDk/BrkNxI2kK4FgvuoW9QK7qL02q8VhlruzixAlVvG8Yq0mWt7OGwhunFFdKg9HjsHcRL09jtOMnDyYqUW6NlAj+wpHt0X9WXny1/qlF2LltIgmIXT80RndsRZ1+f0nFKO7ySKEtaUiDiunklymqwNXCdCMVxIX0lQBJTAKbzRpUKo56c8CI3GBli7r+0yfYws8Bu998ZRw+yralyfpwe+1SaoLs6HERhHt4mQKBgQDP3tkPGZ/lq/8PF22YHYJx6gmZG3yKDNq1HS+gM8ChuC0kIZjsQZvZBk6dF+EREhxVv4E4/e+UNivVnqJDkG8VaP80sfkS8pmvI0AYP7ScYHRLdfAKcLJ7p9VQG1r5NZ1QCMrmHoUmm/5sn7ESErJwvU59Y9h5b2F0x2mQEN9FnQKBgQC9jTVbqLvouRBCIxF8cxYCMwNxOau8mnL7pjKekWrgEi3pgiXwZ7ieFcdWOtbNqx7kgLR+RYIFZUpUAyNPXCHOP77oPj7VcU4rDODEsU46Ocqt/EFZdSXQf7MR3qzb9Ym17XO3sjkNXpmEM1QVIbLDL9dFPP+RwpxwkCmDTJOdqwKBgFzkgm2MDveNCDtPReuPbGbJpqZkMSBkJCyBsXHPDst2Sb32wab6ntG6TtFHBY4iYMU1AmNcn37FQouBC5RT7EBOHpYG3n7IKUOp5g+wEF89f1rlhnKz8B7wXpQg1WO+wOEuPLXfd7NMTMNwDao87r8Q6g2A3BFQY+XS42q9D3nFAoGAHlZQWOgrkvazlHI6ZTmiJx31ChUZ2Gi7yGz0Iw7RFSWztXDAE7b2BE8mBTYLlQUzP40d1ivXwHdSqxmJeDUdr/3h1xfHRLAKu7cm7jQWehkvyCLcbq3iLHmTtmN2NQoaTUTAxYC/wtUGKvNwHKW1D6pDgPBfUvvbv+RC0KHb6lsCgYA6WH6xxLXxfrqYP66JXzU5jerFFgEZu7LJfkvWkEGO7rtnUTJ0/LIdQeZTxmY5JKt87etlFBdOfY4k4y2Uoqn78MjNVaj9ZMbA0aIoUJFLa3zCrYYHNZlz3Iw7PTxCI5whSU8dF2KfgkWjjjkU0y+ONyXDXulvWWat8v0VwnBU+Q==";
    // 支付宝公钥
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA7GNrOdwVtuog4V4kJcYFQdVWyajTvBwRcZ7ccq0/ty8BYPR9EH4AVAba5s+UFEY3jyHGDG74MgEXpjH5rWWGRuHAJArCY/R0XS66rr8t9YkAhxKkmQXBCiHqUa70Gm5MBFqLBrm0+qfLdAMYG9NtBEtGJcmx0sk1Iq2uf4XCiEnraedCppIRks5yUPRN1hb0Q0W+aen72/VoxgYg4P6wgTwgRc+LmJwpVv/GgElhGIBEHrn/qmczfHwuKk00rmxQ3R2wsrTfkCatcr700e7riwWr0ATcfwDpXq64sjbPM5iLSyzV4W7gXMMY4HvQMjLXO91mPNRPfeCmCoRVRU3DOQIDAQAB";

    // 服务器异步通知页面路径(大项目一般用这个)  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://2351j8s098.iask.in:26415/springboot-alipay/alipay/alipayNotifyNotice";
    // (请求适中可用)页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问(用户付款成功会自动跳转该链接)
    public static String return_url = "http://2351j8s098.iask.in:26415/springboot-alipay/alipay/alipayReturnNotice";

    // 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关 //
	//public static String gatewayUrl = "https://openapi.alipay.com/gateway.do";// 正式
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";// 测试

    // 返回格式
    public static String FORMAT = "json";

    // 日志记录目录
    public static String log_path = "C:\\";
}

