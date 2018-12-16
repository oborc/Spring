create table user(
`id` bigint(20) unsigned not null auto_increment,
`age` int not null default 0 ,
`name` varchar(20) not null default '' ,
`idcard` varchar(18) not null default '' ,
primary key (id)
)engine=innodb auto_increment=1 default charset=utf8;

create table court(
`id` bigint(20) unsigned auto_increment,
`user_id` bigint(20)unsigned not null default 0 ,
`name` varchar(40) not null default '',
primary key(`id`),
foreign key (`user_id`) references `user` (`id`)
)engine=innodb auto_increment=1 default charset=utf8 ;
