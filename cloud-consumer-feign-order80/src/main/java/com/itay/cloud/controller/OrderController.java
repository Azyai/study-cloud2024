package com.itay.cloud.controller;

import cn.hutool.core.date.DateUtil;
import com.itay.cloud.DTO.PayDTO;
import com.itay.cloud.apis.PayFeignApi;
import com.itay.cloud.resp.ResultData;
import com.itay.cloud.resp.ReturnCodeEnum;
import jakarta.annotation.Resource;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLOutput;
import java.util.List;


@RestController
public class OrderController {

    @Resource
    private PayFeignApi payFeignApi;

    @PostMapping(value = "consumerFeign/pay/add")
    public ResultData addPay(@RequestBody PayDTO payDTO) {
        System.out.println("第一步，本地新增订单成功，第二步，再开启addPay支付微服务远程调用");
        return payFeignApi.addPay(payDTO);
    }

    @GetMapping(value = "/consumerFeign/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id) {

        try{
            System.out.println("支付微服务远程调用-----开始" + DateUtil.now());
            return payFeignApi.getPayInfo(id);
        }catch (Exception e){
            System.out.println("支付微服务远程调用-----结束"+ DateUtil.now());
            e.printStackTrace();
            return ResultData.fail(ReturnCodeEnum.RC500.getCode(),e.getMessage());
        }
    }

    /**
     * 返回的数据信息是服务端的信息，也就是当前具体调用的那一个服务提供者
     * openFeign自带负载均衡，所以这里的服务提供者是多个
     * @return String
     */
    @GetMapping(value = "/consumerFeign/pay/get/info")
    public String myInfo() {
        return payFeignApi.myInfo();
    }
}
