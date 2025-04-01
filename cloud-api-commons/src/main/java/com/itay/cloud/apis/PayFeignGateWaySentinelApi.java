package com.itay.cloud.apis;

import com.itay.cloud.common.PayFeignSentinelApiFallBack;
import com.itay.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(value = "nacos-payment-provider",fallback = PayFeignSentinelApiFallBack.class)

@FeignClient(value = "cloudalibaba-sentinel-gateway")
public interface PayFeignGateWaySentinelApi
{

    @GetMapping("/pay2/gateway/nacos/get/{orderNo}")
    public ResultData getPayByOrderNo(@PathVariable("orderNo") String orderNo);
}
 