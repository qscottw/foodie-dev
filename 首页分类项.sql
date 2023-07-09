select
	f.id as id,
	f.name as name,
	f.father_id as fatherId,
    c.id as subId,
    c.name as subName,
    c.type as subType,
    c.father_id as subFatherId
from category f
left join
	category c
on f.id = c.father_id
where f.father_id = 1;