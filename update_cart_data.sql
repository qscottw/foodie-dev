select
	t_items.id as itemId,
    t_items.item_name as itemName,
    t_items_img.url as itemImgUrl,
    t_items_spec.price_normal as priceNormal,
    t_items_spec.price_discount as priceDiscount,
    t_items_spec.id as specId,
    t_items_spec.name as specName
from
	items_spec t_items_spec
left join 
	items t_items
on 
	t_items.id = t_items_spec.item_id
left join 
	items_img t_items_img
on 
	t_items_img.item_id = t_items.id
where
	t_items_img.is_main = 1 and
	t_items_spec.id in ('1', '3', '5')
select
	t_items.id as itemId,
    t_items.item_name as itemName,
    t_items_img.url as itemImgUrl,
    t_items_spec.price_normal as priceNormal,
    t_items_spec.price_discount as priceDiscount,
    t_items_spec.id as specId,
    t_items_spec.name as specName
from
	items_spec t_items_spec
left join 
	items t_items
on 
	t_items.id = t_items_spec.item_id
left join 
	items_img t_items_img
on 
	t_items_img.item_id = t_items.id
where
	t_items_img.is_main = 1 and
	t_items_spec.id in ('1', '3', '5')
-- 	private String itemId;
--     private String itemImgUrl;
--     private String itemName;
--     private String specId;
--     private String specName;
--     private Integer buyCounts;
--     private String priceDiscount;
--     private String priceNormal;