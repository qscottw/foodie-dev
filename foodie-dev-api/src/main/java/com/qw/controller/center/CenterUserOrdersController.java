package com.qw.controller.center;


import com.qw.controller.BasicController;
import com.qw.service.center.MyOrderService;
import com.qw.utils.QWJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Api(value = "user center order controller", tags = {"endpoints of center order service"})
@RequestMapping("myorders")
public class CenterUserOrdersController extends BasicController {
    @Autowired
    private MyOrderService myOrderService;
    @PostMapping("query")
    @ApiOperation(value = "query user orders", notes = "query user orders", httpMethod = "POST")
    public QWJSONResult query(
            @RequestParam String userId,
            @RequestParam Integer orderStatus,
            @RequestParam Integer page,
            @RequestParam Integer pageSize
            ){
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("orderStatus", orderStatus);
        if (page==null){
            page=1;
        }
        if (pageSize==null){
            pageSize = ORDER_PAGE_SIZE;
        }
        return QWJSONResult.ok(myOrderService.queryMyOrders(map, page, pageSize));
    }

}
