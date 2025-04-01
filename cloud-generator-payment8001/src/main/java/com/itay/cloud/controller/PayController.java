package com.itay.cloud.controller;

import com.itay.cloud.DTO.PayDTO;
import com.itay.cloud.entities.Pay;
import com.itay.cloud.resp.ResultData;
import com.itay.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/***
* @Author: ay
* @Date: 2024/12/6
*/

@RestController
@Slf4j
@Tag(name = "支付微服务模块",description = "支付CRUD")
public class PayController {
    @Resource
    private PayService payService;

    @PostMapping(value = "/pay/add")
    @Operation(summary = "新增",description = "新增支付流水方法，json参数")
    public ResultData<String> addPay(@RequestBody Pay pay){
        System.out.println(pay.toString());
        int i = payService.add(pay);
        return ResultData.success("成功插入记录，返回值：" + i);
    }

    @DeleteMapping(value = {"pay/del/{id}"})
    @Operation(summary = "删除",description = "删除支付流水方法")
    public ResultData<Integer>  deletePay(@PathVariable("id")Integer id){
        return ResultData.success(payService.delete(id));
    }

    @PutMapping(value = {"/pay/update"})
    @Operation(summary = "修改",description = "修改支付流水方法")
    public ResultData<String> updatePay(@RequestBody PayDTO payDTO){
        System.out.println(payDTO.toString());
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO,pay);
        int i = payService.update(pay);
        return ResultData.success("成功修改记录，返回值：" + i);
    }

    @GetMapping(value = {"/pay/get/{id}"})
    @Operation(summary = "按照ID查流水",description = "查询支付流水方法")
    public ResultData<Pay> getById(@PathVariable("id") Integer id){
        try{
            TimeUnit.SECONDS.sleep(62);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        return ResultData.success(payService.getById(id));
    }

    @GetMapping(value = {"/pay/getAll"})
    @Operation(summary = "查所有流水",description = "查询所有支付流水方法")
    public ResultData<List<Pay>> getAllPay(){
        return  ResultData.success(payService.getAll());
    }


    @Value("${server.port}")
    private  String port;

    @GetMapping(value = "/pay/get/info")
    public String getInfoByConcul(@Value("${itay.info}")String itayInfo){
            return itayInfo + ",调用成功，端口号：" + port; }

}
