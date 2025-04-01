package com.itay.cloud.service;

import com.itay.cloud.entities.Pay;

import java.util.List;

/**
 * @author ay
 */
public interface PayService {
    public int add(Pay pay);
    public int delete(Integer id);
    public int update(Pay pay);
    public Pay getById(Integer id);

    public List<Pay> getAll();
}
