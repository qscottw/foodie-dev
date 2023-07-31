package com.qw.service;


import com.qw.pojo.Carousel;
import com.qw.pojo.bo.SubmitOrderBO;
import com.qw.pojo.vo.OrderVO;

import java.util.List;

public interface OrderService {
    /**
     * create order realted info
     */
    public OrderVO createOrder(SubmitOrderBO submitOrderBO);

    /**
     * update order status
     * @param orderId
     * @param orderStatus
     */
    public void updateOrderStatus(String orderId, Integer orderStatus);

    /**
     * close unpaid overtimed orders
     */
    public void closeOrder();
}
