package com.kicol.service;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.kicol.config.AlipayConfig;
import org.springframework.stereotype.Service;

/**
 * @Author: 韩老魔
 * @Date: 2019/2/11 0011 16:59
 */
@Service
public class AliPayClientService {
    /**
     * 使用懒汉式单例
     * 只需要初始化一次，后续调用不同的API都可以使用同一个alipayClient对象，
     * @return
     */
    //私有静态对象,加载时候不做初始化
    private static AlipayClient client_intance = null;

    // 私有构造方法,避免外部创建实例
    private AliPayClientService() {
    };

    /**
     * 静态工厂方法,返回此类的唯一实例.
     * 当发现实例没有初始化的时候,才初始化.
     *
     * @return AlipayClient
     */
    synchronized public static AlipayClient getInstance() {
        //判断是否创建实例
        if (client_intance == null) {
            client_intance = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        }
        return client_intance;
    }
}
