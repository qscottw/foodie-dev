package com.qw.service.impl;

import com.qw.enums.OrderStatusEnum;
import com.qw.enums.YesOrNo;
import com.qw.mapper.CarouselMapper;
import com.qw.mapper.OrderItemsMapper;
import com.qw.mapper.OrderStatusMapper;
import com.qw.mapper.OrdersMapper;
import com.qw.pojo.*;
import com.qw.pojo.bo.SubmitOrderBO;
import com.qw.service.AddressService;
import com.qw.service.CarouselService;
import com.qw.service.ItemsService;
import com.qw.service.OrderService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private AddressService addressService;

    @Autowired
    private OrderItemsMapper orderItemsMapper;

    @Autowired
    private OrderStatusMapper orderStatusMapper;
    @Autowired
    ItemsService itemsService;
    @Autowired
    Sid sid;
    @Override
    public void createOrder(SubmitOrderBO submitOrderBO) {
        String userId = submitOrderBO.getUserId();
        String addressId = submitOrderBO.getAddressId();
        String itemSpecIds = submitOrderBO.getItemSpecIds();
        Integer payMethod = submitOrderBO.getPayMethod();
        String leftMsg = submitOrderBO.getLeftMsg();
        Integer postAmount = 0;

        String orderId = sid.nextShort();

        UserAddress userAddress = addressService.queryUserAddress(userId, addressId);

        // 1. save new order data
        Orders newOrder = new Orders();
        newOrder.setId(orderId);
        newOrder.setUserId(userId);
        newOrder.setReceiverName(userAddress.getReceiver());
        newOrder.setReceiverMobile(userAddress.getMobile());
        newOrder.setReceiverAddress(userAddress.getProvince() +
                " " + userAddress.getCity() +
                " " + userAddress.getDistrict() +
                " " + userAddress.getDetail());
//        newOrder.setTotalAmount();
//        newOrder.setRealPayAmount();

        newOrder.setPayMethod(payMethod);
        newOrder.setPostAmount(postAmount);
        newOrder.setLeftMsg(leftMsg);
        newOrder.setIsComment(YesOrNo.NO.type);
        newOrder.setIsDelete(YesOrNo.NO.type);
        newOrder.setCreatedTime(new Date());
        newOrder.setUpdatedTime(new Date());

        // 2. iterate save order items
        String itemSpecIdArr[] = itemSpecIds.split(",");
        Integer totalAmount = 0;
        Integer realPayAmount = 0;

        for (String itemSpecId: itemSpecIdArr){
            // search prive from item_specs
            // TODO get from redis buyCounts
            int buyCounts = 1; //should get from redis
            ItemsSpec itemsSpec = itemsService.queryItemSpecById(itemSpecId);
            Integer itemDiscountPrice = itemsSpec.getPriceDiscount();
            Integer itemNormalPrice = itemsSpec.getPriceNormal();
            totalAmount += itemNormalPrice * buyCounts;
            realPayAmount += itemDiscountPrice * buyCounts;
            // get item info and item url with itemId
            String itemId = itemsSpec.getItemId();
            Items item = itemsService.queryItemById(itemId);
            String itemName = item.getItemName();
            String itemMainImgUrl = itemsService.queryItemMainImgItemId(itemId);

            //iterate save suborder to db
            String subOrderId = sid.nextShort();
            OrderItems subOrderItem = new OrderItems();
            subOrderItem.setOrderId(orderId);
            subOrderItem.setId(subOrderId);
            subOrderItem.setItemId(itemId);
            subOrderItem.setItemImg(itemMainImgUrl);
            subOrderItem.setItemName(itemName);
            subOrderItem.setBuyCounts(buyCounts);
            subOrderItem.setItemSpecId(itemSpecId);
            subOrderItem.setItemSpecName(itemsSpec.getName());
            subOrderItem.setPrice(itemDiscountPrice);
            orderItemsMapper.insert(subOrderItem);
            itemsService.decreaseItemSpecStock(itemSpecId, buyCounts);
        }

        newOrder.setTotalAmount(totalAmount);
        newOrder.setRealPayAmount(realPayAmount);
        ordersMapper.insert(newOrder);

        // 3. save order status
        OrderStatus newOrderStatus = new OrderStatus();
        newOrderStatus.setCreatedTime(new Date());
        newOrderStatus.setOrderId(orderId);
        newOrderStatus.setOrderStatus(OrderStatusEnum.WAIT_PAY.type);
        orderStatusMapper.insert(newOrderStatus);
    }
}
