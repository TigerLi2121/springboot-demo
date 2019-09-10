-- 定时任务
DROP TABLE IF EXISTS `quartz_job`;
CREATE TABLE `quartz_job` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `job_name` varchar(255) DEFAULT NULL COMMENT '任务名称',
  `cron_expression` varchar(255) DEFAULT NULL COMMENT 'cron 表达式',
  `is_pause` bit(1) DEFAULT NULL COMMENT '状态：1暂停、0启用',
  `bean_name` varchar(255) DEFAULT NULL COMMENT 'spring bean名称',
  `method_name` varchar(255) DEFAULT NULL COMMENT '方法名称',
  `params` varchar(255) DEFAULT NULL COMMENT '参数',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务';

-- 定时任务日志
DROP TABLE IF EXISTS `quartz_log`;
CREATE TABLE `quartz_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `job_id` bigint(20) DEFAULT NULL COMMENT '任务ID',
  `job_name` varchar(255) DEFAULT NULL COMMENT '任务名称',
  `cron_expression` varchar(255) DEFAULT NULL COMMENT 'cron 表达式',
  `is_success` bit(1) DEFAULT NULL COMMENT '状态',
  `bean_name` varchar(255) DEFAULT NULL COMMENT 'spring bean名称',
  `method_name` varchar(255) DEFAULT NULL COMMENT '方法名称',
  `params` varchar(255) DEFAULT NULL COMMENT '参数',
  `exception_detail` text DEFAULT NULL COMMENT '异常详细',
  `time` bigint(20) DEFAULT NULL COMMENT '耗时(单位：毫秒)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务日志';