CREATE TABLE `bricklayer_column`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `original_column_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '数据库列名',
  `simple_column_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '列类型（简写）',
  `column_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '列类型（完整）',
  `column_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '列主键标识',
  `extra` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '自增标识',
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '列注解',
  `belong_table_id` int(11) NULL DEFAULT NULL COMMENT '所属模型主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1510 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '模型列描述表' ROW_FORMAT = DYNAMIC;

CREATE TABLE `bricklayer_db`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `db_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `db_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `db_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '数据库ip',
  `db_port` int(255) NULL DEFAULT NULL COMMENT '数据库端口',
  `db_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '数据库类型',
  `db_driver_class` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '数据库驱动',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '数据源表' ROW_FORMAT = DYNAMIC;

CREATE TABLE `bricklayer_dictionary`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dictionary_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '字典名称',
  `dictionary_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '字典值',
  `dictionary_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '字典编码',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '字典表' ROW_FORMAT = DYNAMIC;

CREATE TABLE `bricklayer_direct`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `direct_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '文件夹名称',
  `direct_full_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '目录全路径',
  `parent_direct_id` int(11) NULL DEFAULT NULL COMMENT '父级文件夹主键',
  `belong_project_id` int(11) NULL DEFAULT NULL COMMENT '所属项目主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 472 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '项目目录表' ROW_FORMAT = DYNAMIC;

CREATE TABLE `bricklayer_direct_template_relation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `template_id` int(11) NULL DEFAULT NULL COMMENT '模板id',
  `belong_direct_id` int(11) NULL DEFAULT NULL COMMENT '归属文件夹',
  `template_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '模板名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 319 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '目录模板关系表' ROW_FORMAT = DYNAMIC;

CREATE TABLE `bricklayer_project`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `project_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '项目名',
  `project_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '项目描述',
  `context_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '前端访问前缀',
  `fix_project` bit(1) NULL DEFAULT NULL COMMENT '基础项目无法删除',
  `create_by` varchar(255) NULL COMMENT '创建人',
  `update_by` varchar(255) NULL COMMENT '修改人',
  `create_date` datetime NULL COMMENT '创建日期',
  `update_date` datetime NULL COMMENT '修改日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '项目表' ROW_FORMAT = DYNAMIC;

CREATE TABLE `bricklayer_table`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `original_table_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '来源表名',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `source_device` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '来源设备',
  `source_data_base` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '来源数据库名',
  `create_by` varchar(255) NULL COMMENT '创建人',
  `update_by` varchar(0) NULL COMMENT '修改人',
  `create_date` datetime(0) NULL COMMENT '创建日期',
  `update_date` datetime(0) NULL COMMENT '修改日期',
  `model_service_target` varchar(255) NULL COMMENT '模型服务对象',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 169 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '数据模型表' ROW_FORMAT = DYNAMIC;

CREATE TABLE `bricklayer_template`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `template_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '模板名称',
  `template_content` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL COMMENT '模板内容',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `template_suffix` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '输出后缀',
  `name_end_string` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '文件名结束字符',
  `fixed_template` bit(1) NULL DEFAULT NULL COMMENT '是否字符串模板',
  `base_template` bit(1) NULL DEFAULT NULL COMMENT '基础模板，无法删除',
  `create_by` varchar(255) NULL COMMENT '创建人',
  `update_by` varchar(255) NULL COMMENT '修改人',
  `create_date` datetime(0) NULL COMMENT '创建日期',
  `update_date` datetime(0) NULL COMMENT '修改日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 49 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '模板表' ROW_FORMAT = DYNAMIC;

CREATE TABLE `bricklayer_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `nick_name` varchar(255) NULL COMMENT '昵称',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `user_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `user_role` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '用户角色',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

