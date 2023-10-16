select
	confirm.id as 'Confirmation ID'
    ,confirm.confirmation_dttm as 'Date Confirmed'
    ,guest.guest_type as 'Guest Type'
    ,concat(guest.first_name, ' ', guest.last_name) as 'Guest Name'
    ,case
		when guest.attending = 0 then 'Absent'
        when guest.attending = 1 then 'Attending'
	end as 'Attendance'
    ,case
		when guest.vegetarian = 0 then 'False'
        when guest.vegetarian = 1 then 'True'
	end as 'Vegetarian'
    ,case
		when guest.vegan = 0 then 'False'
        when guest.vegan = 1 then 'True'
	end as 'Vegan'
    ,case
		when guest.gluten = 0 then 'False'
        when guest.gluten = 1 then 'True'
	end as 'Gluten'
    ,guest.other_allergy as 'Other Allergies'
    ,guest.email as 'Email'
    ,guest.id as 'Guest ID'
from
	guest_confirmation confirm
inner join
	guests guest
    on guest.id = confirm.guest_id
order by
	confirm.confirmation_dttm
    
    