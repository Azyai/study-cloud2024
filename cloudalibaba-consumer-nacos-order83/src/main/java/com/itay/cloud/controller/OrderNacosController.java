package com.itay.cloud.controller;

import com.itay.cloud.apis.PayFeignGateWaySentinelApi;
import com.itay.cloud.apis.PayFeignSentinelApi;
import com.itay.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderNacosController
{
    @Resource
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    private String serverURL;

    @GetMapping("/consumer/pay/nacos/{id}")
    public String paymentInfo(@PathVariable("id") Integer id)
    {
        String result = restTemplate.getForObject(serverURL + "/pay/nacos/" + id, String.class);
        return result+"\t"+"    我是OrderNacosController83调用者。。。。。。";
    }

    @Resource
    private PayFeignSentinelApi payFeignSentinelApi;


    @GetMapping(value = "/consumer/pay/nacos/get/{orderNo}")
    public ResultData getPayByOrderNo(@PathVariable("orderNo") String orderNo){
        return payFeignSentinelApi.getPayByOrderNo(orderNo);
    }

    @Resource
    private PayFeignGateWaySentinelApi payFeignGateWaySentinelApi;


    @GetMapping(value = "/consumer/pay/nacos/gateway/get/{orderNo}")
    public ResultData getPayGayeWayByOrderNo(@PathVariable("orderNo") String orderNo){
        return payFeignGateWaySentinelApi.getPayByOrderNo(orderNo);
    }
}