use reception;
select
	guest_type as 'Guest Type'
    ,last_name as 'Last Name'
    ,first_name as 'First Name'
    ,concat('http://ctwedding2020.ca/ValidateGuest?id=', id) as 'Personalized Invite Link'
from
	guests
order by
	last_name