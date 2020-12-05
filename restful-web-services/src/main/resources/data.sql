insert into todo(id, username,description,target_date,is_done)
values(10001, 'sept', 'Learn JPA', sysdate(), false);

insert into todo(id, username,description,target_date,is_done)
values(10002, 'sept', 'Learn Data JPA', sysdate(), false);

insert into todo(id, username,description,target_date,is_done)
values(10003, 'sept', 'Learn Microservices', sysdate(), false);

insert into courses(id, username,course_id,course_name,is_completed,grade)
values(10001, 'sept', 'COSC1234', 'Intro to Programming', false, 0);

insert into courses(id, username,course_id,course_name,is_completed,grade)
values(10002, 'sept', 'COSC2269', 'Web Programming', true, 63);

insert into courses(id, username,course_id,course_name,is_completed,grade)
values(10003, 'sept', 'COSC5879', 'Algorithms', true, 89);

insert into mentor(id, username,course_id,course_name,mentor_start_date, total_rate, total_mentee)
values(10001, 'sept', 'COSC5879', 'Algorithms',sysdate(), 20, 8);

insert into mentor(id, username,course_id,course_name,mentor_start_date, total_rate, total_mentee)
values(10002, 's3665858', 'COSC5879', 'Algorithms', sysdate(), 14, 3);

insert into mentor(id, username,course_id,course_name,mentor_start_date, total_rate, total_mentee)
values(10003, 'abc', 'COSC5879', 'Algorithms', sysdate(), 12, 5);

insert into mentor(id, username,course_id,course_name,mentor_start_date, total_rate, total_mentee)
values(10004, 'abc', 'COSC1234', 'Intro to Programming', sysdate(), 15, 5);

insert into Mentee(id, username,course_id,course_name,mentor_date, mentor_id, mentor_name, mentor_email, is_completed)
values(10001, 'sept', 'COSC1234', 'Intro to Programming',sysdate(),10004,'abc','abc@gmail.com',false);

insert into Study_Group(id, username, course_id, course_name,group_name , members, number_of_member, start_date)
values(10001, ' ', 'COSC5879', 'Algorithms', 'SEPTOne','sept,abc,s3665858,',3, sysdate()); 

insert into Study_Group(id, username, course_id, course_name,group_name , members, number_of_member, start_date)
values(10002, ' ', 'COSC1234', 'Intro to Programming', 'The Dummy', 'sept,',1, sysdate()); 


