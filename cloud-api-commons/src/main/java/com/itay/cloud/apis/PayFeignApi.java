package com.itay.cloud.apis;

import com.itay.cloud.DTO.PayDTO;
import com.itay.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("cloud-payment-service")
public interface PayFeignApi {

    @PostMapping(value = "/pay/add")
    // 这里是PayDTO，是因为消费者没有完整的Pay数据，只有DTO数据
    // 因此传入PayDTO后，调用相关的实现接口，会自动传到服务端的Pay中
    public ResultData addPay(@RequestBody PayDTO payDTO);

    @GetMapping(value = {"/pay/get/{id}"})
    public ResultData getPayInfo(@PathVariable("id") Integer id);


    @GetMapping(value = "/pay/get/info")
    // 理论上抽象方法名可以随便起，但是最好与业务实现代码保持一致
    // 起决定作用的主要是URL路径
    public String myInfo();

    /**
     * Resilience4j CircuitBreaker 的例子
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/circuit/{id}")
    public String myCircuit(@PathVariable("id") Integer id);


    /**
     * Resilience4j CircuitBreaker 的例子
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/circuit2/{id}")
    public String myCircuit2(@PathVariable("id") Integer id);

    /**
     * Resilience4j Bulkhead 的例子
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/bulkhead/{id}")
    public String myBulkhead(@PathVariable("id") Integer id);


    /**
     * Resilience4j Ratelimit 的例子
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/ratelimit/{id}")
    public String myRatelimit(@PathVariable("id") Integer id);


    /**
     * Micrometer(Sleuth)进行链路监控的例子
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/micrometer/{id}")
    public String myMicrometer(@PathVariable("id") Integer id);


    /**
     * GateWay进行网关测试案例01
     * @param id
     * @return
     */
//    @GetMapping(value = "/pay/gateway/get/{id}")
//    public ResultData getById(@PathVariable("id") Integer id);

    /**
     * GateWay进行网关测试案例02
     * @return
     */
//    @GetMapping(value = "/pay/gateway/info")
//    public ResultData<String> getGatewayInfo();

}