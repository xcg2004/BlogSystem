drop database if exists blog_system;
create database blog_system;
use blog_system;

create table user(
                     id bigint primary key auto_increment comment '用户id',
                     username varchar(255) unique not null comment '用户名',
                     email  varchar(255) unique not null comment '邮箱',
                     password varchar(255) not null comment '密码',
                     create_time datetime default current_timestamp comment '创建时间',
                     update_time datetime default current_timestamp comment '更新时间'
);
create table role(
                     id bigint primary key auto_increment comment '角色id',
                     name varchar(255) not null comment '角色名',
                     create_time datetime default current_timestamp comment '创建时间',
                     update_time datetime default current_timestamp on update current_timestamp

);
create table user_role(
                          user_id bigint not null comment '用户id',
                          role_id bigint not null comment '角色id',
                          primary key (user_id, role_id)
);
create table permission(
                           id bigint primary key auto_increment comment '权限id',
                           name varchar(255) not null comment '权限名',
                           create_time datetime default current_timestamp comment '创建时间',
                           update_time datetime default current_timestamp on update current_timestamp
);
create table role_permission(
                                role_id bigint not null comment '角色id',
                                permission_id bigint not null comment '权限id',
                                primary key (role_id, permission_id)
);
create table article(
                        id bigint primary key auto_increment comment '文章id',
                        title varchar(255) not null comment '标题',
                        content text not null comment '内容',
                        summary varchar(255) not null comment '文章摘要',
                        category_id bigint not null comment '分类id',
                        user_id bigint not null comment '用户id',
                        status tinyint default 0 comment '已发布、草稿、已删除',
                        view_count bigint default 0 comment '阅读量',
                        comment_count bigint default 0 comment '评论量',
                        create_time datetime default current_timestamp,
                        update_time datetime default current_timestamp on update current_timestamp
);

create table category(
                         id bigint primary key auto_increment comment '分类id',
                         name varchar(255) not null comment '分类名',
                         create_time datetime default current_timestamp,
                         update_time datetime default current_timestamp on update current_timestamp
);

create table tag(
                    id bigint primary key auto_increment comment '标签id',
                    name varchar(255) not null comment '标签名',
                    create_time datetime default current_timestamp,
                    update_time datetime default current_timestamp on update current_timestamp
);

create table article_tag(
                            article_id bigint not null comment '文章id',
                            tag_id bigint not null comment '标签id',
                            primary key (article_id, tag_id)
);

create table comment(
                        id bigint primary key auto_increment comment '评论id',
                        article_id bigint not null comment '文章id',
                        user_id bigint not null comment '用户id',
                        content varchar(255) not null comment '评论内容',
                        status tinyint default 0 comment '评论状态(已审核、未审核、已删除)',
                        create_time datetime default current_timestamp comment '创建时间',
                        update_time datetime default current_timestamp comment '更新时间'
);

-- admin 123456  => 管理员角色、拥有所有权限
-- xcg 123456  => 普通用户角色、拥有部分权限(发布文章)

-- user
insert into user(username,email,password)
values('admin','admin@example.com','e10adc3949ba59abbe56e057f20f883e'),
      ('xcg','xcg@example.com','e10adc3949ba59abbe56e057f20f883e');

-- role
insert into role(name)
values('管理员'),('用户');

-- user_role  admin->管理员角色  xcg->用户角色
insert into user_role(user_id, role_id)
values (1,1),(2,2);

-- permission
insert into permission(name)
values('管理员权限'),('用户权限');

-- role_permission  管理员角色->管理员权限  用户角色->用户权限
insert into role_permission(role_id, permission_id)
values (1,1),(2,2);


