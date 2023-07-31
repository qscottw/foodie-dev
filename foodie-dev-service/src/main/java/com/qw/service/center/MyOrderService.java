package com.qw.service.center;

import com.qw.pojo.Users;
import com.qw.pojo.vo.MyOrdersVO;
import com.qw.utils.PagedGridResult;

import java.util.List;
import java.util.Map;


public interface MyOrderService {
    public PagedGridResult queryMyOrders(Map<String, Object> map, Integer page, Integer pageSize);
}
