package com.qw.mapper;

import com.qw.my.mapper.MyMapper;
import com.qw.pojo.Items;
import com.qw.pojo.vo.ItemCommentVO;
import com.qw.pojo.vo.SearchItemsVO;
import com.qw.pojo.vo.SimpleItemsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemsMapperCustom {
    public List<ItemCommentVO> queryItemComments(@Param("paramsMap") Map<String, Object> paramsMap);
    public List<SearchItemsVO> searchItems(@Param("paramsMap") Map<String, Object> paramsMap);

    public List<SearchItemsVO> searchCatItems(@Param("paramsMap") Map<String, Object> paramsMap);

    public List<SearchItemsVO> queryItemsBySpecIds(@Param("paramsList") List paramsList);

    public int decreaseItemSpecStock(@Param("specId") String specId, @Param("pendingCounts") int pendingCounts);
}