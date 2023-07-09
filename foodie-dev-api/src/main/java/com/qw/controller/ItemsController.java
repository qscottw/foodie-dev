package com.qw.controller;

import com.qw.enums.YesOrNo;
import com.qw.pojo.*;
import com.qw.pojo.vo.CommentLevelCountsVO;
import com.qw.pojo.vo.ItemInfoVO;
import com.qw.pojo.vo.SearchItemsVO;
import com.qw.service.ItemsService;
import com.qw.utils.PagedGridResult;
import com.qw.utils.QWJSONResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("items")
public class ItemsController extends BasicController {

    @Autowired
    private ItemsService itemsService;




    @GetMapping("/info/{itemId}")
    public QWJSONResult info(@PathVariable String itemId){
        if (StringUtils.isBlank(itemId)){
            return QWJSONResult.errorMsg("item id cannot be empty");
        }
        Items item = itemsService.queryItemById(itemId);
        List<ItemsImg> itemsImgs = itemsService.queryItemImgList(itemId);
        List<ItemsSpec> itemsSpec = itemsService.queryItemsSpecList(itemId);
        List<ItemsParam> itemsParam = itemsService.queryItemsParamList(itemId);

        ItemInfoVO itemInfoVO = new ItemInfoVO();
        itemInfoVO.setItem(item);
        itemInfoVO.setItemsImgsList(itemsImgs);
        itemInfoVO.setItemSpecList(itemsSpec);
        itemInfoVO.setItemParams(itemsParam);
        return QWJSONResult.ok(itemInfoVO);

    }

    @GetMapping("/commentLevel")
    public QWJSONResult commentLevel(@RequestParam String itemId){
        if (StringUtils.isBlank(itemId)){
            return QWJSONResult.errorMsg("item id cannot be empty");
        }
        CommentLevelCountsVO countsVO = itemsService.queryCommentCounts(itemId);
        return QWJSONResult.ok(countsVO);
    }

    @GetMapping("comments")
    public QWJSONResult comments(@RequestParam String itemId,
                        @RequestParam Integer level,
                        @RequestParam Integer page,
                        @RequestParam Integer pageSize ){
        if (StringUtils.isBlank(itemId)){
            return QWJSONResult.errorMsg("item id cannot be empty");
        }
        if (page==null){
            page=1;
        }
        if (pageSize==null){
            pageSize = COMMENT_PAGE_SIZE;
        }
        PagedGridResult grid = itemsService.queryPagedComments(itemId,
                                                                level,
                                                                page,
                                                                pageSize);
        return QWJSONResult.ok(grid);
    }

    @ApiOperation(value = "search item list", notes = "search item list", httpMethod = "GET")
    @GetMapping("search")
    public QWJSONResult search(
            @ApiParam(name= "keywords", value = "Key words", required = true)
            @RequestParam String keywords,
            @ApiParam(name= "sort", value = "sort", required = false)
             @RequestParam String sort,
            @ApiParam(name= "page", value = "page", required = false)
             @RequestParam Integer page,
            @ApiParam(name= "pageSize", value = "pageSize", required = false)
             @RequestParam Integer pageSize){
        if (keywords==null){
            return QWJSONResult.errorMsg("keywords cannot be empty");
        }
        if (sort==null){
            sort="k";
        }
        if (page==null){
            page=1;
        }
        if (pageSize==null){
            pageSize = PAGE_SIZE;
        }
        PagedGridResult grid = itemsService.searchItems(keywords,
                                                        sort,
                                                        page,
                                                        pageSize);
        return QWJSONResult.ok(grid);
    }
    @ApiOperation(value = "search category item list", notes = "search category item list", httpMethod = "GET")
    @GetMapping("catItems")
    public QWJSONResult catItems(
            @ApiParam(name= "catId", value = "category id", required = true)
            @RequestParam Integer catId,
            @ApiParam(name= "sort", value = "sort", required = false)
            @RequestParam String sort,
            @ApiParam(name= "page", value = "page", required = false)
            @RequestParam Integer page,
            @ApiParam(name= "pageSize", value = "pageSize", required = false)
            @RequestParam Integer pageSize){
        if (catId==null){
            return QWJSONResult.errorMsg("category cannot be empty");
        }
        if (sort==null){
            sort="k";
        }
        if (page==null){
            page=1;
        }
        if (pageSize==null){
            pageSize = PAGE_SIZE;
        }
        PagedGridResult grid = itemsService.searchCatItems(catId,
                sort,
                page,
                pageSize);
        return QWJSONResult.ok(grid);
    }

    @ApiOperation(value = "update cart list with newest spec data", notes = "update cart list with newest spec data, user hasn't logged in for a long time", httpMethod = "GET")
    @GetMapping("refresh")
    public QWJSONResult refresh(
            @ApiParam(name= "itemSpecIds", value = "concated itemSpecIds", required = true, example = "1001,1003,1005")
            @RequestParam String itemSpecIds
           ){
        if (itemSpecIds==null){
            return QWJSONResult.ok();
        }
        List<SearchItemsVO> list = itemsService.queryItemsBySpecIds(itemSpecIds);
        return QWJSONResult.ok(list);
    }
}
