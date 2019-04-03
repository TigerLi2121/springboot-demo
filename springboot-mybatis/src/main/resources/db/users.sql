DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) PRIMARY KEY AUTO_INCREMENT COMMENT '主键id',
  `user_name` varchar(32) NOT NULL COMMENT '用户名',
  `pass_word` varchar(32) NOT NULL COMMENT '密码',
  `gender` varchar(32) NOT NULL COMMENT '性别',
  `nick_name` varchar(32) NOT NULL COMMENT '昵称',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间'
) COMMENT '用户信息';