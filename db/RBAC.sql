/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/7/23 星期一 14:14:14                       */
/*==============================================================*/


drop table if exists sys_permission;

drop table if exists sys_permission_role;

drop table if exists sys_role;

drop table if exists sys_role_user;

drop table if exists sys_user;

/*==============================================================*/
/* Table: sys_permission                                        */
/*==============================================================*/
create table sys_permission
(
   permis_id            varchar(50) not null,
   name                 varchar(50),
   description          varchar(100),
   url                  varchar(200),
   pid                  varchar(50),
   primary key (permis_id)
);

alter table sys_permission comment '权限表';

/*==============================================================*/
/* Table: sys_permission_role                                   */
/*==============================================================*/
create table sys_permission_role
(
   role_id              varchar(50) not null,
   permis_id            varchar(50) not null,
   primary key (role_id, permis_id)
);

/*==============================================================*/
/* Table: sys_role                                              */
/*==============================================================*/
create table sys_role
(
   role_id              varchar(50) not null,
   name                 varchar(50),
   primary key (role_id)
);

alter table sys_role comment '角色表';

/*==============================================================*/
/* Table: sys_role_user                                         */
/*==============================================================*/
create table sys_role_user
(
   role_id              varchar(50) not null,
   user_id              varchar(50) not null,
   primary key (role_id, user_id)
);

/*==============================================================*/
/* Table: sys_user                                              */
/*==============================================================*/
create table sys_user
(
   user_id              varchar(50) not null,
   username             varchar(30),
   password             varchar(100),
   phone                varchar(11),
   email                varchar(100),
   expired              bool,
   locked               bool,
   deleted              bool,
   primary key (user_id)
);

alter table sys_user comment '系统用户表';

alter table sys_permission_role add constraint FK_Relationship_5 foreign key (permis_id)
      references sys_permission (permis_id) on delete restrict on update restrict;

alter table sys_permission_role add constraint FK_Relationship_6 foreign key (role_id)
      references sys_role (role_id) on delete restrict on update restrict;

alter table sys_role_user add constraint FK_Relationship_2 foreign key (user_id)
      references sys_user (user_id) on delete restrict on update restrict;

alter table sys_role_user add constraint FK_Relationship_3 foreign key (role_id)
      references sys_role (role_id) on delete restrict on update restrict;

