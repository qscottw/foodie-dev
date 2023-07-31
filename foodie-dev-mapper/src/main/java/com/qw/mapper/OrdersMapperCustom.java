package com.qw.mapper;

import com.qw.pojo.vo.MyOrdersVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrdersMapperCustom {
    public List<MyOrdersVO> queryMyOrders(@Param("paramsMap") Map<String, Object> map);

}