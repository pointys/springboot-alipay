package com.kicol.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.*;
import com.kicol.config.AlipayConfig;
import com.kicol.pojo.Order;
import com.kicol.service.AliPayClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static java.lang.System.out;

/**
 * @Author: 韩老魔
 * @Date: 2019/2/7 0007 22:40
 */
@Controller
@RequestMapping("/alipay")
public class AliPayController {

    @Autowired
    private AliPayClientService clientService;


    //获取AlipayClient的全局单例实例对象
    AlipayClient alipayClient=clientService.getInstance();

    //前往首页
    @RequestMapping("/index")
    public String index() {
        out.println("index控制器");
        return "index";
    }

    //前往交易页面-->支付宝官方生成二维码
    @RequestMapping("/alipayTradePagePay")
    @ResponseBody
    public String trade(Order order) throws Exception {
        out.println("alipayTradePagePay控制器");

        //从前端接收订单参数
        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = order.getWIDout_trade_no();
        //付款金额，必填
        String total_amount = order.getWIDtotal_amount();
        //订单名称，必填
        String subject = order.getWIDsubject();
        //商品描述，可空
        String body = order.getWIDbody();

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //获取请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        out.println("result:" + result);
        //向支付宝返回请求参数
        return result;
    }

    //交易查询
    @RequestMapping("/query")
    public String query(Order order, Model model) throws AlipayApiException {
        System.out.println("交易查询控制器");
        System.out.println(order.getWIDTQout_trade_no()+"\n"+order.getWIDTQtrade_no());

        //设置请求参数，商户订单号和支付宝交易号可二选一必填进行查询
        AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();
        //商户订单号，商户网站订单系统中唯一订单号，或必填
        String out_trade_no = order.getWIDTQout_trade_no();
        //支付宝交易号，或必填
        String trade_no = order.getWIDTQtrade_no();

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"trade_no\":\"" + trade_no + "\"}");

        //获取请求
        String result = alipayClient.execute(alipayRequest).getBody();

        //请求返回视图
        model.addAttribute("tip","交易查询");
        model.addAttribute("result", result);
        return "notice";
    }

    //退款
    @RequestMapping("/refund")
    public String refund(Order order, Model model) throws AlipayApiException {
        System.out.println("退款控制器");
        System.out.println(order.toString());

        //设置请求参数，商户订单号和支付宝交易号可二选一必填
        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();
        //商户订单号，商户网站订单系统中唯一订单号，或必填
        String out_trade_no =order.getWIDTRout_trade_no();
        //支付宝交易号，或必填
        String trade_no =order.getWIDTRtrade_no();

        //需要退款的金额，该金额不能大于订单金额(若不填退款请求号则必须等于订单金额)，必填
        String refund_amount =order.getWIDTRrefund_amount();
        //退款的原因说明，可空
        String refund_reason = order.getWIDTRrefund_reason();
        //退款请求号，同一笔交易多次退款需要保证唯一，若部分退款则必填，若全额退款可空
        String out_request_no =order.getWIDTRout_request_no();
                alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"trade_no\":\""+ trade_no +"\","
                + "\"refund_amount\":\""+ refund_amount +"\","
                + "\"refund_reason\":\""+ refund_reason +"\","
                + "\"out_request_no\":\""+ out_request_no +"\"}");

        //获取请求
        String result = alipayClient.execute(alipayRequest).getBody();

        //请求返回视图
        model.addAttribute("tip","退款");
        model.addAttribute("result", result);
        return "notice";
    }

    //退款查询
    @RequestMapping("/fastpayRefundQuery")
    public String RefundQuery(Order order, Model model) throws AlipayApiException {
        System.out.println("交易查询控制器");
        System.out.println(order.toString());

        //设置请求参数，商户订单号和支付宝交易号可二选一必填进行退款查询
        AlipayTradeFastpayRefundQueryRequest alipayRequest = new AlipayTradeFastpayRefundQueryRequest();
        //商户订单号，商户网站订单系统中唯一订单号，或必填
        String out_trade_no = order.getWIDRQout_trade_no();
        //支付宝交易号，或必填
        String trade_no = order.getWIDRQtrade_no();

        //退款请求号，如果在退款时未传入，则该值默认为支付宝交易号，必填
        String out_request_no = order.getWIDRQout_request_no();

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                +"\"trade_no\":\""+ trade_no +"\","
                +"\"out_request_no\":\""+ out_request_no +"\"}");

        //获取请求
        String result = alipayClient.execute(alipayRequest).getBody();

        //返回视图
        model.addAttribute("tip","退款查询");
        model.addAttribute("result", result);
        return "notice";
    }

    //交易关闭
    @RequestMapping("/close")
    public String close(Order order, Model model) throws AlipayApiException {
        System.out.println("交易查询控制器");
        System.out.println(order.toString());

        //设置请求参数
        AlipayTradeCloseRequest alipayRequest = new AlipayTradeCloseRequest();
        //商户订单号，商户网站订单系统中唯一订单号
        String out_trade_no = order.getWIDTCout_trade_no();
        //支付宝交易号
        String trade_no = order.getWIDTCtrade_no();

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," +"\"trade_no\":\""+ trade_no +"\"}");

        //获取请求
        String result = alipayClient.execute(alipayRequest).getBody();

        //请求返回视图
        model.addAttribute("tip","交易关闭");
        model.addAttribute("result", result);
        return "notice";
    }

    //同步通知 买家成功付款支付宝自动同步通知商家并以?传回相关参数
    @GetMapping("/alipayReturnNotice")
    public String rNotice(HttpServletRequest request, Model model) throws Exception {
        out.println("支付宝同步通知控制器");
        try {
            //获取支付宝GET过来反馈信息
            Map<String, String> params = new HashMap<String, String>();
            Map<String, String[]> requestParams = request.getParameterMap();
            for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
                String name = (String) iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                }
                //乱码解决，这段代码在出现乱码时使用
                valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
                params.put(name, valueStr);
            }
            // 验签
            boolean verify_result = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名
            //——请在这里编写您的程序（以下代码仅作参考）——
            if (verify_result) {
                //商户订单号
                String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

                //支付宝交易号
                String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

                //付款金额
                String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
                // 保存信息
                model.addAttribute("tip", "验签成功");
                model.addAttribute("result", "trade_no:" + trade_no + "<br/>out_trade_no:" + out_trade_no + "<br/>total_amount:" + total_amount);
            } else {
                model.addAttribute("tip", "验签失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回视图
        return "return_url";
    }

    //异步通知页面 什么时候调我也不知道
    @RequestMapping("/alipayNotifyNotice")
    public String NotifyNotice(HttpServletRequest request, Model model) throws Exception {
        out.println("支付宝同步通知控制器");
        try {
            //获取支付宝GET过来反馈信息
            Map<String, String> params = new HashMap<String, String>();
            Map<String, String[]> requestParams = request.getParameterMap();
            for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
                String name = (String) iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                }
                //乱码解决，这段代码在出现乱码时使用
                valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
                params.put(name, valueStr);
            }
            // 验签
            boolean verify_result = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名
            //——请在这里编写您的程序（以下代码仅作参考）——
            if (verify_result) {
                //商户订单号
                String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

                //支付宝交易号
                String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

                //交易状态
                String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

                //付款金额
                String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
                if (trade_status.equals("TRADE_FINISHED")) {
                    //判断该笔订单是否在商户网站中已经做过处理
                    //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                    //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                    //如果有做过处理，不执行商户的业务程序

                    //注意：
                    //如果签约的是可退款协议，退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
                    //如果没有签约可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。

                    //TODO 调用service业务处理逻辑代码
                } else if (trade_status.equals("TRADE_SUCCESS")) {
                    //判断该笔订单是否在商户网站中已经做过处理
                    //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                    //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                    //如果有做过处理，不执行商户的业务程序

                    //注意：
                    //如果签约的是可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。

                    //TODO 调用service业务处理逻辑代码
                }
                // 保存信息
                model.addAttribute("tip", "验签成功");
                model.addAttribute("result", "trade_no:" + trade_no + "<br/>out_trade_no:" + out_trade_no + "<br/>total_amount:" + total_amount + "<br/>trade_status:" + trade_status);

            } else {
                model.addAttribute("tip", "验签失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回视图
        return "notify_url";
    }

}
