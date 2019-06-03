package com.kicol.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: 韩老魔
 * 封装订单参数
 * @Date: 2019/2/9 0009 17:39
 */
@Setter
@Getter
@ToString
public class Order {
    //1.付款
    //商户订单号，商户网站订单系统中唯一订单号，必填(out_trade_no)
    String WIDout_trade_no;
    //付款金额，必填(otal_amount)
    String WIDtotal_amount;
    //订单名称，必填(subject)
    String WIDsubject;
    //商品描述，可空(body)
    String WIDbody;

    //交易查询
    //商户订单号，商户网站订单系统中唯一订单号(out_trade_no)
    String WIDTQout_trade_no;
    //交易查询的支付宝交易号(trade_no)
    String WIDTQtrade_no;

    //3.退款
    //退款查询的商户订单号
    String WIDTRout_trade_no;
    //退款查询的支付宝交易号
    String WIDTRtrade_no;
    //需要退款的金额，该金额不能大于订单金额，必填
    String WIDTRrefund_amount;
    //退款原因
    String WIDTRrefund_reason;
    //标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
    String WIDTRout_request_no;

    //4.退款查询
    //商户订单号，商户网站订单系统中唯一订单号
    String WIDRQout_trade_no;
    //支付宝交易号
    String WIDRQtrade_no;
    //请求退款接口时，传入的退款请求号，如果在退款请求时未传入，则该值为创建交易时的外部交易号，必填
    String WIDRQout_request_no;

    //5.交易关闭
    //商户订单号，商户网站订单系统中唯一订单号
    String WIDTCout_trade_no;
    //支付宝交易号
    String WIDTCtrade_no;
}
