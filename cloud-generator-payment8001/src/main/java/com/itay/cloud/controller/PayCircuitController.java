package com.itay.cloud.controller;


import cn.hutool.core.util.IdUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class PayCircuitController {
    //=========Resilience4j CircuitBreaker 的例子
    @GetMapping(value = "/pay/circuit/{id}")
    public String myCircuit(@PathVariable("id") Integer id)
    {
        // 直接报错
        if(id == -4) throw new RuntimeException("----circuit id 不能负数");

        // 超时
        if(id == 9999){
            try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
        }

        //正确返回
        return "Hello, circuit! inputId:  "+id+" \t " + IdUtil.simpleUUID();
    }


    //=========Resilience4j CircuitBreaker 的例子
    @GetMapping(value = "/pay/circuit2/{id}")
    public String myCircuit2(@PathVariable("id") Integer id)
    {
        // 直接报错
        if(id == -4) throw new RuntimeException("----circuit id 不能负数");

        // 超时
        if(id == 9999){
            try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
        }

        //正确返回
        return "Hello, circuit! inputId:  "+id+" \t " + IdUtil.simpleUUID();
    }

    //=========Resilience4j bulkhead 的例子
    @GetMapping(value = "/pay/bulkhead/{id}")
    public String myBulkhead(@PathVariable("id") Integer id)
    {
        if(id == -4) throw new RuntimeException("----bulkhead id 不能-4");

        if(id == 9999)
        {
            try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
        }

        return "Hello, bulkhead! inputId:  "+id+" \t " + IdUtil.simpleUUID();
    }


    //=========Resilience4j ratelimit 的例子
    @GetMapping(value = "/pay/ratelimit/{id}")
    public String myRatelimit(@PathVariable("id") Integer id)
    {
        return "Hello, myRatelimit欢迎到来 inputId:  "+id+" \t " + IdUtil.simpleUUID();
    }

}