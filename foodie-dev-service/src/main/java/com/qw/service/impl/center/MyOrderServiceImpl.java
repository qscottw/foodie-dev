package com.qw.service.impl.center;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qw.mapper.OrdersMapperCustom;
import com.qw.pojo.vo.ItemCommentVO;
import com.qw.pojo.vo.MyOrdersVO;
import com.qw.service.center.MyOrderService;
import com.qw.utils.DesensitizationUtil;
import com.qw.utils.PagedGridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service
public class MyOrderServiceImpl implements MyOrderService {

    @Autowired
    public OrdersMapperCustom ordersMapperCustom;
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public PagedGridResult queryMyOrders(Map<String, Object> map, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<MyOrdersVO> list = ordersMapperCustom.queryMyOrders(map);
        return setterPagedGrid(list, page);
    }

    private PagedGridResult setterPagedGrid(List<?> list, Integer page) {
        PageInfo<?> pageList = new PageInfo<>(list);
        PagedGridResult grid = new PagedGridResult();
        grid.setPage(page);
        grid.setRows(list);
        grid.setTotal(pageList.getPages());
        grid.setRecords(pageList.getTotal());
        return grid;
    }
}
