SELECT
    ITEM_ID,
    ITEM_NAME
from item_info a
inner join item_tree b using (item_id)
where parent_item_id is null
order by item_id asc