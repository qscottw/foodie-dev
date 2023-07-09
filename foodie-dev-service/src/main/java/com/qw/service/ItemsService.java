package com.qw.service;

import com.qw.pojo.Items;
import com.qw.pojo.ItemsImg;
import com.qw.pojo.ItemsParam;
import com.qw.pojo.ItemsSpec;
import com.qw.pojo.vo.CommentLevelCountsVO;
import com.qw.pojo.vo.ItemCommentVO;
import com.qw.pojo.vo.SearchItemsVO;
import com.qw.utils.PagedGridResult;

import java.util.List;

public interface ItemsService {

    /**
     * 根据id查询详情
     * @param id
     * @return
     */
    public Items queryItemById(String id);

    public List<ItemsImg> queryItemImgList(String itemId);

    public List<ItemsSpec> queryItemsSpecList(String itemId);

    public List<ItemsParam> queryItemsParamList(String itemId);

    public CommentLevelCountsVO queryCommentCounts(String itemId);

    /**
     * 根据商品id查询商品评价
     * @param itemId
     * @param level
     * @return
     */
    public PagedGridResult queryPagedComments(String itemId, Integer level, Integer page, Integer pageSize);

    /**
     * search item list
     * @param keywords
     * @param sort
     * @param page
     * @param pageSize
     * @return
     */
    public PagedGridResult searchItems(String keywords, String sort, Integer page, Integer pageSize);
    public PagedGridResult searchCatItems(Integer catId, String sort, Integer page, Integer pageSize);

    /**
     * update and render cart data
     * @param specIds
     * @return
     */
    public List<SearchItemsVO> queryItemsBySpecIds(String specIds);

    /**
     * find
     * @return
     */
    public ItemsSpec queryItemSpecById(String specId);

    public String queryItemMainImgItemId(String itemId);

    public void decreaseItemSpecStock(String specId, int buyCounts);
}
