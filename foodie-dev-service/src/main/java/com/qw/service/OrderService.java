package com.qw.service;


import com.qw.pojo.Carousel;
import com.qw.pojo.bo.SubmitOrderBO;

import java.util.List;

public interface OrderService {
    /**
     * create order realted info
     */
    public void createOrder(SubmitOrderBO submitOrderBO);


}
