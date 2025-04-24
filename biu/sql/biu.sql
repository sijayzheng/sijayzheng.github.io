-- MySQL dump 10.13  Distrib 8.2.0, for Win64 (x86_64)
--
-- Host: localhost    Database: biu
-- ------------------------------------------------------
-- Server version	8.2.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `file_storage`
--

DROP TABLE IF EXISTS `file_storage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `file_storage` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '文件名',
  `original_name` varchar(255) NOT NULL DEFAULT '' COMMENT '原名',
  `suffix` varchar(10) NOT NULL DEFAULT '' COMMENT '文件后缀名',
  `url` varchar(255) NOT NULL DEFAULT '' COMMENT '文件访问地址',
  `store_path` varchar(255) NOT NULL DEFAULT '' COMMENT '文件存储路径',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文件存储表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file_storage`
--

LOCK TABLES `file_storage` WRITE;
/*!40000 ALTER TABLE `file_storage` DISABLE KEYS */;
/*!40000 ALTER TABLE `file_storage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gen_column`
--

DROP TABLE IF EXISTS `gen_column`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gen_column` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `column_name` varchar(64) NOT NULL DEFAULT '' COMMENT '字段名',
  `table_id` bigint NOT NULL COMMENT '表编号',
  `column_comment` varchar(255) NOT NULL COMMENT '字段描述',
  `column_key` enum('MUL','NONE','PRI','UNI') DEFAULT 'NONE' COMMENT '列类型',
  `data_type` varchar(32) NOT NULL COMMENT '字段类型',
  `java_field` varchar(64) NOT NULL COMMENT 'java 属性名',
  `sort` int DEFAULT '0' COMMENT '排序',
  `nullable` bit(1) DEFAULT b'0' COMMENT '可空',
  `numeric_precision` int DEFAULT NULL COMMENT '数值长度',
  `numeric_scale` int DEFAULT NULL COMMENT '小数位数',
  `length` bigint DEFAULT NULL COMMENT '文本长度',
  `default_value` varchar(500) DEFAULT NULL COMMENT '默认值',
  `listable` bit(1) DEFAULT b'0' COMMENT '可列出',
  `editable` bit(1) DEFAULT b'0' COMMENT '可编辑',
  `queryable` bit(1) DEFAULT b'0' COMMENT '可查询',
  `exportable` bit(1) DEFAULT b'0' COMMENT '可导出',
  `java_type` enum('BIG_DECIMAL','BOOLEAN','BYTE_ARRAY','CHARACTER','DATA_SCOPE_ENUM','DOUBLE','FLOAT','GENDER_TYPE','INTEGER','LIST','LIST_LONG','LOCAL_DATE','LOCAL_DATE_TIME','LOCAL_TIME','LONG','MENU_TYPE','MESSAGE_TYPE','SET','SHOW_TYPE','STRING') DEFAULT 'STRING' COMMENT 'java 属性类型',
  `html_type` enum('CHECKBOX','DATETIME_PICKER','DATE_PICKER','EDITOR','FILE_UPLOAD','IMAGE_UPLOAD','INPUT','INPUT_NUMBER','RADIO','SELECT','SWITCH','TEXTAREA','TIME_PICKER','TREE_SELECT') DEFAULT 'INPUT' COMMENT '显示类型',
  `query_type` enum('BETWEEN','EQUAL','LIKE','NONE') DEFAULT 'NONE' COMMENT '查询方式',
  `dict_type` varchar(200) DEFAULT NULL COMMENT '字典类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=281 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='代码生成列定义';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gen_column`
--

LOCK TABLES `gen_column` WRITE;
/*!40000 ALTER TABLE `gen_column` DISABLE KEYS */;
INSERT INTO `gen_column` VALUES (100,'id',12,'id','PRI','bigint','id',1,_binary '\0',19,0,NULL,'',_binary '',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(101,'name',12,'部门名称','NONE','varchar','name',2,_binary '\0',NULL,NULL,50,'',_binary '',_binary '',_binary '',_binary '','STRING','INPUT','LIKE',''),(102,'parent_id',12,'父部门id','NONE','bigint','parentId',3,_binary '',19,0,NULL,'',_binary '',_binary '',_binary '',_binary '','LONG','TREE_SELECT','EQUAL',''),(103,'ancestors',12,'祖级列表','NONE','json','ancestors',4,_binary '',NULL,NULL,NULL,'',_binary '',_binary '\0',_binary '\0',_binary '\0','LIST_LONG','SELECT','NONE',''),(104,'sort',12,'排序','NONE','int','sort',5,_binary '',10,0,NULL,'',_binary '',_binary '',_binary '\0',_binary '','INTEGER','INPUT_NUMBER','NONE',''),(105,'leader',12,'负责人','NONE','bigint','leader',6,_binary '',19,0,NULL,'',_binary '',_binary '',_binary '\0',_binary '','LONG','SELECT','NONE',''),(106,'phone',12,'联系电话','NONE','varchar','phone',7,_binary '',NULL,NULL,11,'',_binary '',_binary '',_binary '\0',_binary '','STRING','INPUT','NONE',''),(107,'email',12,'邮箱','NONE','varchar','email',8,_binary '',NULL,NULL,100,'',_binary '',_binary '',_binary '\0',_binary '','STRING','INPUT','NONE',''),(108,'enable',12,'启用','NONE','bit','enable',9,_binary '',1,NULL,NULL,'b\'1\'',_binary '',_binary '',_binary '\0',_binary '\0','BOOLEAN','RADIO','NONE',''),(109,'create_dept',12,'创建部门','NONE','bigint','createDept',10,_binary '',19,0,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(110,'create_by',12,'创建者','NONE','bigint','createBy',11,_binary '',19,0,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(111,'create_time',12,'创建时间','NONE','datetime','createTime',12,_binary '',NULL,NULL,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LOCAL_DATE_TIME','DATETIME_PICKER','NONE',''),(112,'update_by',12,'更新者','NONE','bigint','updateBy',13,_binary '',19,0,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(113,'update_time',12,'更新时间','NONE','datetime','updateTime',14,_binary '',NULL,NULL,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LOCAL_DATE_TIME','DATETIME_PICKER','NONE',''),(114,'id',13,'id','PRI','bigint','id',1,_binary '\0',19,0,NULL,'',_binary '',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(115,'username',13,'账号','UNI','varchar','username',2,_binary '\0',NULL,NULL,20,'',_binary '',_binary '',_binary '',_binary '','STRING','INPUT','LIKE',''),(116,'dept_id',13,'部门','NONE','bigint','deptId',3,_binary '',19,0,NULL,'',_binary '',_binary '',_binary '',_binary '','LONG','TREE_SELECT','EQUAL',''),(117,'real_name',13,'姓名','NONE','varchar','realName',4,_binary '',NULL,NULL,50,'',_binary '',_binary '',_binary '',_binary '','STRING','INPUT','LIKE',''),(118,'email',13,'邮箱','NONE','varchar','email',5,_binary '',NULL,NULL,100,'',_binary '',_binary '',_binary '\0',_binary '','STRING','INPUT','NONE',''),(119,'phone',13,'手机号码','NONE','varchar','phone',6,_binary '',NULL,NULL,11,'',_binary '',_binary '',_binary '',_binary '','STRING','INPUT','LIKE',''),(120,'gender',13,'性别','NONE','enum','gender',7,_binary '',NULL,NULL,7,'UNKNOWN',_binary '',_binary '',_binary '\0',_binary '','GENDER_TYPE','RADIO','NONE',''),(121,'avatar',13,'头像','NONE','bigint','avatar',8,_binary '',19,0,NULL,'',_binary '',_binary '',_binary '\0',_binary '\0','LONG','IMAGE_UPLOAD','NONE',''),(122,'password',13,'密码','NONE','varchar','password',9,_binary '',NULL,NULL,255,'',_binary '',_binary '',_binary '\0',_binary '\0','STRING','INPUT','NONE',''),(123,'roles',13,'角色','NONE','json','roles',10,_binary '',NULL,NULL,NULL,'',_binary '',_binary '',_binary '\0',_binary '\0','LIST_LONG','SELECT','NONE',''),(124,'posts',13,'岗位','NONE','json','posts',11,_binary '',NULL,NULL,NULL,'',_binary '',_binary '',_binary '\0',_binary '\0','LIST_LONG','SELECT','NONE',''),(125,'modules',13,'模块','NONE','json','modules',12,_binary '',NULL,NULL,NULL,'',_binary '',_binary '',_binary '\0',_binary '\0','LIST_LONG','SELECT','NONE',''),(126,'enable',13,'启用','NONE','bit','enable',13,_binary '',1,NULL,NULL,'b\'1\'',_binary '',_binary '',_binary '\0',_binary '\0','BOOLEAN','RADIO','NONE',''),(127,'pwd_changed',13,'密码是否更改','NONE','bit','pwdChanged',14,_binary '',1,NULL,NULL,'b\'0\'',_binary '',_binary '',_binary '\0',_binary '\0','BOOLEAN','RADIO','NONE',''),(128,'create_dept',13,'创建部门','NONE','bigint','createDept',15,_binary '',19,0,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(129,'create_by',13,'创建者','NONE','bigint','createBy',16,_binary '',19,0,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(130,'create_time',13,'创建时间','NONE','datetime','createTime',17,_binary '',NULL,NULL,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LOCAL_DATE_TIME','DATETIME_PICKER','NONE',''),(131,'update_by',13,'更新者','NONE','bigint','updateBy',18,_binary '',19,0,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(132,'update_time',13,'更新时间','NONE','datetime','updateTime',19,_binary '',NULL,NULL,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LOCAL_DATE_TIME','DATETIME_PICKER','NONE',''),(133,'id',15,'id','PRI','bigint','id',1,_binary '\0',19,0,NULL,'',_binary '',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(134,'name',15,'文件名','UNI','varchar','name',2,_binary '\0',NULL,NULL,255,'',_binary '',_binary '\0',_binary '\0',_binary '\0','STRING','INPUT','NONE',''),(135,'original_name',15,'原名','NONE','varchar','originalName',3,_binary '\0',NULL,NULL,255,'',_binary '',_binary '\0',_binary '',_binary '\0','STRING','INPUT','LIKE',''),(136,'suffix',15,'文件后缀名','NONE','varchar','suffix',4,_binary '\0',NULL,NULL,10,'',_binary '',_binary '\0',_binary '\0',_binary '\0','STRING','INPUT','NONE',''),(137,'url',15,'文件访问地址','NONE','varchar','url',5,_binary '\0',NULL,NULL,255,'',_binary '',_binary '\0',_binary '\0',_binary '\0','STRING','INPUT','NONE',''),(138,'store_path',15,'文件存储路径','NONE','varchar','storePath',6,_binary '\0',NULL,NULL,255,'',_binary '',_binary '\0',_binary '\0',_binary '\0','STRING','INPUT','NONE',''),(139,'create_dept',15,'创建部门','NONE','bigint','createDept',7,_binary '',19,0,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(140,'create_by',15,'创建者','NONE','bigint','createBy',8,_binary '',19,0,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(141,'create_time',15,'创建时间','NONE','datetime','createTime',9,_binary '',NULL,NULL,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LOCAL_DATE_TIME','DATETIME_PICKER','NONE',''),(142,'update_by',15,'更新者','NONE','bigint','updateBy',10,_binary '',19,0,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(143,'update_time',15,'更新时间','NONE','datetime','updateTime',11,_binary '',NULL,NULL,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LOCAL_DATE_TIME','DATETIME_PICKER','NONE',''),(144,'id',16,'id','PRI','bigint','id',1,_binary '\0',19,0,NULL,'',_binary '',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(145,'user_id',16,'用户id','NONE','bigint','userId',2,_binary '\0',19,0,NULL,'',_binary '',_binary '\0',_binary '',_binary '','LONG','SELECT','EQUAL',''),(146,'ip',16,'登录ip','NONE','varchar','ip',3,_binary '',NULL,NULL,128,'',_binary '',_binary '\0',_binary '',_binary '','STRING','INPUT','LIKE',''),(147,'location',16,'登录地点','NONE','varchar','location',4,_binary '',NULL,NULL,255,'',_binary '',_binary '\0',_binary '',_binary '','STRING','INPUT','LIKE',''),(148,'browser',16,'浏览器类型','NONE','varchar','browser',5,_binary '',NULL,NULL,100,'',_binary '',_binary '\0',_binary '',_binary '','STRING','INPUT','LIKE',''),(149,'os',16,'操作系统','NONE','varchar','os',6,_binary '',NULL,NULL,50,'',_binary '',_binary '\0',_binary '',_binary '','STRING','INPUT','LIKE',''),(150,'successful',16,'登录状态','MUL','bit','successful',7,_binary '',1,NULL,NULL,'b\'0\'',_binary '',_binary '\0',_binary '',_binary '','BOOLEAN','RADIO','EQUAL',''),(151,'msg',16,'提示消息','NONE','varchar','msg',8,_binary '',NULL,NULL,255,'',_binary '',_binary '\0',_binary '\0',_binary '','STRING','INPUT','NONE',''),(152,'time',16,'时间','MUL','datetime','time',9,_binary '',NULL,NULL,NULL,'CURRENT_TIMESTAMP',_binary '',_binary '\0',_binary '',_binary '','LOCAL_DATE_TIME','DATETIME_PICKER','BETWEEN',''),(153,'id',17,'id','PRI','bigint','id',1,_binary '\0',19,0,NULL,'',_binary '',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(154,'user_id',17,'用户id','MUL','bigint','userId',2,_binary '\0',19,0,NULL,'',_binary '',_binary '\0',_binary '',_binary '','LONG','SELECT','EQUAL',''),(155,'title',17,'模块标题','NONE','varchar','title',3,_binary '',NULL,NULL,50,'',_binary '',_binary '\0',_binary '',_binary '','STRING','INPUT','LIKE',''),(156,'method',17,'方法名称','NONE','varchar','method',4,_binary '',NULL,NULL,100,'',_binary '',_binary '\0',_binary '',_binary '','STRING','INPUT','LIKE',''),(157,'request_method',17,'请求方式','NONE','varchar','requestMethod',5,_binary '',NULL,NULL,10,'',_binary '',_binary '\0',_binary '',_binary '','STRING','INPUT','LIKE',''),(158,'url',17,'请求URL','NONE','varchar','url',6,_binary '',NULL,NULL,255,'',_binary '',_binary '\0',_binary '',_binary '','STRING','INPUT','LIKE',''),(159,'ip',17,'主机地址','NONE','varchar','ip',7,_binary '',NULL,NULL,128,'',_binary '',_binary '\0',_binary '',_binary '','STRING','INPUT','LIKE',''),(160,'location',17,'操作地点','NONE','varchar','location',8,_binary '',NULL,NULL,255,'',_binary '',_binary '\0',_binary '',_binary '','STRING','INPUT','LIKE',''),(161,'param',17,'请求参数','NONE','varchar','param',9,_binary '',NULL,NULL,2000,'',_binary '',_binary '\0',_binary '\0',_binary '','STRING','TEXTAREA','NONE',''),(162,'result',17,'返回参数','NONE','varchar','result',10,_binary '',NULL,NULL,2000,'',_binary '',_binary '\0',_binary '\0',_binary '','STRING','TEXTAREA','NONE',''),(163,'status',17,'操作状态','MUL','int','status',11,_binary '',10,0,NULL,'200',_binary '',_binary '\0',_binary '\0',_binary '','INTEGER','INPUT_NUMBER','NONE',''),(164,'error_msg',17,'错误消息','NONE','varchar','errorMsg',12,_binary '',NULL,NULL,2000,'',_binary '',_binary '\0',_binary '\0',_binary '','STRING','TEXTAREA','NONE',''),(165,'time',17,'操作时间','MUL','datetime','time',13,_binary '',NULL,NULL,NULL,'CURRENT_TIMESTAMP',_binary '',_binary '\0',_binary '',_binary '','LOCAL_DATE_TIME','DATETIME_PICKER','BETWEEN',''),(166,'cost_time',17,'消耗时间','NONE','bigint','costTime',14,_binary '',19,0,NULL,'0',_binary '',_binary '\0',_binary '\0',_binary '','LONG','INPUT_NUMBER','NONE',''),(167,'id',18,'id','PRI','bigint','id',1,_binary '\0',19,0,NULL,'',_binary '',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(168,'name',18,'名称','NONE','varchar','name',2,_binary '\0',NULL,NULL,100,'',_binary '',_binary '',_binary '',_binary '','STRING','INPUT','LIKE',''),(169,'code',18,'编码','UNI','varchar','code',3,_binary '\0',NULL,NULL,100,'',_binary '',_binary '',_binary '',_binary '','STRING','INPUT','LIKE',''),(170,'value',18,'值','NONE','varchar','value',4,_binary '\0',NULL,NULL,500,'',_binary '',_binary '',_binary '\0',_binary '','STRING','INPUT','NONE',''),(171,'internal',18,'内置','NONE','bit','internal',5,_binary '',1,NULL,NULL,'b\'0\'',_binary '',_binary '',_binary '\0',_binary '','BOOLEAN','RADIO','NONE',''),(172,'create_dept',18,'创建部门','NONE','bigint','createDept',6,_binary '',19,0,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(173,'create_by',18,'创建者','NONE','bigint','createBy',7,_binary '',19,0,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(174,'create_time',18,'创建时间','NONE','datetime','createTime',8,_binary '',NULL,NULL,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LOCAL_DATE_TIME','DATETIME_PICKER','NONE',''),(175,'update_by',18,'更新者','NONE','bigint','updateBy',9,_binary '',19,0,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(176,'update_time',18,'更新时间','NONE','datetime','updateTime',10,_binary '',NULL,NULL,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LOCAL_DATE_TIME','DATETIME_PICKER','NONE',''),(186,'create_dept',19,'创建部门','NONE','bigint','createDept',10,_binary '',19,0,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(187,'create_by',19,'创建者','NONE','bigint','createBy',11,_binary '',19,0,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(188,'create_time',19,'创建时间','NONE','datetime','createTime',12,_binary '',NULL,NULL,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LOCAL_DATE_TIME','DATETIME_PICKER','NONE',''),(189,'update_by',19,'更新者','NONE','bigint','updateBy',13,_binary '',19,0,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(190,'update_time',19,'更新时间','NONE','datetime','updateTime',14,_binary '',NULL,NULL,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LOCAL_DATE_TIME','DATETIME_PICKER','NONE',''),(191,'id',20,'id','PRI','bigint','id',1,_binary '\0',19,0,NULL,'',_binary '',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(192,'code',20,'编码','NONE','varchar','code',2,_binary '\0',NULL,NULL,100,'',_binary '',_binary '',_binary '',_binary '','STRING','INPUT','LIKE',''),(193,'name',20,'名称','NONE','varchar','name',3,_binary '\0',NULL,NULL,100,'',_binary '',_binary '',_binary '',_binary '','STRING','INPUT','LIKE',''),(194,'create_dept',20,'创建部门','NONE','bigint','createDept',4,_binary '',19,0,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(195,'create_by',20,'创建者','NONE','bigint','createBy',5,_binary '',19,0,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(196,'create_time',20,'创建时间','NONE','datetime','createTime',6,_binary '',NULL,NULL,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LOCAL_DATE_TIME','DATETIME_PICKER','NONE',''),(197,'update_by',20,'更新者','NONE','bigint','updateBy',7,_binary '',19,0,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(198,'update_time',20,'更新时间','NONE','datetime','updateTime',8,_binary '',NULL,NULL,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LOCAL_DATE_TIME','DATETIME_PICKER','NONE',''),(199,'id',21,'id','PRI','bigint','id',1,_binary '\0',19,0,NULL,'',_binary '',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(200,'dict_id',21,'字典','MUL','bigint','dictId',2,_binary '\0',19,0,NULL,'',_binary '',_binary '',_binary '',_binary '','LONG','SELECT','EQUAL',''),(201,'label',21,'标签','NONE','varchar','label',3,_binary '\0',NULL,NULL,100,'',_binary '',_binary '',_binary '',_binary '','STRING','INPUT','LIKE',''),(202,'value',21,'键值','NONE','varchar','value',4,_binary '\0',NULL,NULL,100,'',_binary '',_binary '',_binary '\0',_binary '','STRING','INPUT','NONE',''),(203,'sort',21,'排序','NONE','int','sort',5,_binary '',10,0,NULL,'0',_binary '',_binary '',_binary '\0',_binary '','INTEGER','INPUT_NUMBER','NONE',''),(204,'class_name',21,'样式属性','NONE','varchar','className',6,_binary '',NULL,NULL,100,'',_binary '',_binary '',_binary '\0',_binary '','STRING','INPUT','NONE',''),(205,'show_type',21,'表格回显样式','NONE','enum','showType',7,_binary '',NULL,NULL,7,'NONE',_binary '',_binary '',_binary '\0',_binary '','SHOW_TYPE','SELECT','NONE',''),(206,'default_value',21,'默认值','NONE','bit','defaultValue',8,_binary '',1,NULL,NULL,'b\'0\'',_binary '',_binary '',_binary '\0',_binary '','BOOLEAN','RADIO','NONE',''),(207,'create_dept',21,'创建部门','NONE','bigint','createDept',9,_binary '',19,0,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(208,'create_by',21,'创建者','NONE','bigint','createBy',10,_binary '',19,0,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(209,'create_time',21,'创建时间','NONE','datetime','createTime',11,_binary '',NULL,NULL,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LOCAL_DATE_TIME','DATETIME_PICKER','NONE',''),(210,'update_by',21,'更新者','NONE','bigint','updateBy',12,_binary '',19,0,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(211,'update_time',21,'更新时间','NONE','datetime','updateTime',13,_binary '',NULL,NULL,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LOCAL_DATE_TIME','DATETIME_PICKER','NONE',''),(212,'id',22,'id','PRI','bigint','id',1,_binary '\0',19,0,NULL,'',_binary '',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(213,'parent_id',22,'父菜单id','NONE','bigint','parentId',2,_binary '',19,0,NULL,'0',_binary '',_binary '',_binary '',_binary '','LONG','TREE_SELECT','EQUAL',''),(214,'name',22,'名称','NONE','varchar','name',3,_binary '\0',NULL,NULL,50,'',_binary '',_binary '',_binary '',_binary '','STRING','INPUT','LIKE',''),(215,'perms_code',22,'权限编码','NONE','varchar','permsCode',4,_binary '\0',NULL,NULL,100,'',_binary '',_binary '',_binary '\0',_binary '','STRING','INPUT','NONE',''),(216,'sort',22,'排序','NONE','int','sort',5,_binary '',10,0,NULL,'0',_binary '',_binary '',_binary '\0',_binary '','INTEGER','INPUT_NUMBER','NONE',''),(217,'path',22,'路由地址','NONE','varchar','path',6,_binary '',NULL,NULL,200,'',_binary '',_binary '',_binary '\0',_binary '','STRING','INPUT','NONE',''),(218,'component',22,'组件路径','NONE','varchar','component',7,_binary '',NULL,NULL,255,'',_binary '',_binary '',_binary '\0',_binary '','STRING','INPUT','NONE',''),(219,'type',22,'菜单类型','NONE','enum','type',8,_binary '\0',NULL,NULL,9,'',_binary '',_binary '',_binary '\0',_binary '','MENU_TYPE','RADIO','NONE',''),(220,'external_link',22,'是否外链','NONE','bit','externalLink',9,_binary '',1,NULL,NULL,'b\'0\'',_binary '',_binary '',_binary '\0',_binary '','BOOLEAN','RADIO','NONE',''),(221,'cacheable',22,'可缓存','NONE','bit','cacheable',10,_binary '',1,NULL,NULL,'b\'1\'',_binary '',_binary '',_binary '\0',_binary '','BOOLEAN','RADIO','NONE',''),(222,'visible',22,'可显示','NONE','bit','visible',11,_binary '',1,NULL,NULL,'b\'1\'',_binary '',_binary '',_binary '\0',_binary '','BOOLEAN','RADIO','NONE',''),(223,'enable',22,'启用','NONE','bit','enable',12,_binary '',1,NULL,NULL,'b\'1\'',_binary '',_binary '',_binary '',_binary '','BOOLEAN','RADIO','EQUAL',''),(224,'icon',22,'菜单图标','NONE','varchar','icon',13,_binary '',NULL,NULL,100,'',_binary '',_binary '',_binary '\0',_binary '','STRING','INPUT','NONE',''),(225,'create_dept',22,'创建部门','NONE','bigint','createDept',14,_binary '',19,0,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(226,'create_by',22,'创建者','NONE','bigint','createBy',15,_binary '',19,0,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(227,'create_time',22,'创建时间','NONE','datetime','createTime',16,_binary '',NULL,NULL,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LOCAL_DATE_TIME','DATETIME_PICKER','NONE',''),(228,'update_by',22,'更新者','NONE','bigint','updateBy',17,_binary '',19,0,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(229,'update_time',22,'更新时间','NONE','datetime','updateTime',18,_binary '',NULL,NULL,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LOCAL_DATE_TIME','DATETIME_PICKER','NONE',''),(230,'id',23,'id','PRI','bigint','id',1,_binary '\0',19,0,NULL,'',_binary '',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(231,'title',23,'消息标题','NONE','varchar','title',2,_binary '\0',NULL,NULL,50,'',_binary '',_binary '',_binary '',_binary '','STRING','INPUT','LIKE',''),(232,'type',23,'消息类型','NONE','enum','type',3,_binary '\0',NULL,NULL,12,'NOTICE',_binary '',_binary '',_binary '',_binary '','MESSAGE_TYPE','SELECT','EQUAL',''),(233,'content',23,'内容','NONE','varchar','content',4,_binary '',NULL,NULL,5000,'',_binary '',_binary '',_binary '\0',_binary '','STRING','TEXTAREA','NONE',''),(234,'closed',23,'状态','NONE','bit','closed',5,_binary '',1,NULL,NULL,'b\'0\'',_binary '',_binary '',_binary '',_binary '','BOOLEAN','RADIO','EQUAL',''),(235,'publisher',23,'发布人','NONE','bigint','publisher',6,_binary '',19,0,NULL,'',_binary '',_binary '',_binary '',_binary '','LONG','SELECT','EQUAL',''),(236,'receiver',23,'接收人','NONE','json','receiver',7,_binary '',NULL,NULL,NULL,'',_binary '',_binary '',_binary '\0',_binary '','LIST_LONG','SELECT','NONE',''),(237,'create_dept',23,'创建部门','NONE','bigint','createDept',8,_binary '',19,0,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(238,'create_by',23,'创建者','NONE','bigint','createBy',9,_binary '',19,0,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(239,'create_time',23,'创建时间','NONE','datetime','createTime',10,_binary '',NULL,NULL,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LOCAL_DATE_TIME','DATETIME_PICKER','NONE',''),(240,'update_by',23,'更新者','NONE','bigint','updateBy',11,_binary '',19,0,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(241,'update_time',23,'更新时间','NONE','datetime','updateTime',12,_binary '',NULL,NULL,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LOCAL_DATE_TIME','DATETIME_PICKER','NONE',''),(242,'id',24,'id','PRI','bigint','id',1,_binary '\0',19,0,NULL,'',_binary '',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(243,'code',24,'编码','UNI','varchar','code',2,_binary '\0',NULL,NULL,50,'',_binary '',_binary '',_binary '',_binary '','STRING','INPUT','LIKE',''),(244,'name',24,'名称','NONE','varchar','name',3,_binary '\0',NULL,NULL,50,'',_binary '',_binary '',_binary '',_binary '','STRING','INPUT','LIKE',''),(245,'roles',24,'角色','NONE','json','roles',4,_binary '',NULL,NULL,NULL,'',_binary '',_binary '',_binary '\0',_binary '','LIST_LONG','SELECT','NONE',''),(246,'menus',24,'菜单','NONE','json','menus',5,_binary '',NULL,NULL,NULL,'',_binary '',_binary '',_binary '\0',_binary '','LIST_LONG','SELECT','NONE',''),(247,'enable',24,'启用','NONE','bit','enable',6,_binary '',1,NULL,NULL,'b\'1\'',_binary '',_binary '',_binary '\0',_binary '','BOOLEAN','RADIO','NONE',''),(248,'create_dept',24,'创建部门','NONE','bigint','createDept',7,_binary '',19,0,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(249,'create_by',24,'创建者','NONE','bigint','createBy',8,_binary '',19,0,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(250,'create_time',24,'创建时间','NONE','datetime','createTime',9,_binary '',NULL,NULL,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LOCAL_DATE_TIME','DATETIME_PICKER','NONE',''),(251,'update_by',24,'更新者','NONE','bigint','updateBy',10,_binary '',19,0,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(252,'update_time',24,'更新时间','NONE','datetime','updateTime',11,_binary '',NULL,NULL,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LOCAL_DATE_TIME','DATETIME_PICKER','NONE',''),(253,'id',25,'id','PRI','bigint','id',1,_binary '\0',19,0,NULL,'',_binary '',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(254,'name',25,'名称','NONE','varchar','name',2,_binary '\0',NULL,NULL,50,'',_binary '',_binary '',_binary '',_binary '','STRING','INPUT','LIKE',''),(255,'code',25,'编码','UNI','varchar','code',3,_binary '\0',NULL,NULL,50,'',_binary '',_binary '',_binary '',_binary '','STRING','INPUT','LIKE',''),(256,'dept_id',25,'部门id','NONE','bigint','deptId',4,_binary '\0',19,0,NULL,'',_binary '',_binary '',_binary '',_binary '','LONG','TREE_SELECT','EQUAL',''),(257,'sort',25,'排序','NONE','int','sort',5,_binary '',10,0,NULL,'0',_binary '',_binary '',_binary '\0',_binary '','INTEGER','INPUT_NUMBER','NONE',''),(258,'enable',25,'启用','NONE','bit','enable',6,_binary '',1,NULL,NULL,'b\'1\'',_binary '',_binary '',_binary '\0',_binary '','BOOLEAN','RADIO','NONE',''),(259,'create_dept',25,'创建部门','NONE','bigint','createDept',7,_binary '',19,0,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(260,'create_by',25,'创建者','NONE','bigint','createBy',8,_binary '',19,0,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(261,'create_time',25,'创建时间','NONE','datetime','createTime',9,_binary '',NULL,NULL,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LOCAL_DATE_TIME','DATETIME_PICKER','NONE',''),(262,'update_by',25,'更新者','NONE','bigint','updateBy',10,_binary '',19,0,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(263,'update_time',25,'更新时间','NONE','datetime','updateTime',11,_binary '',NULL,NULL,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LOCAL_DATE_TIME','DATETIME_PICKER','NONE',''),(264,'id',26,'id','PRI','bigint','id',1,_binary '\0',19,0,NULL,'',_binary '',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(265,'avatar',26,'头像','NONE','blob','avatar',2,_binary '',NULL,NULL,65535,'',_binary '',_binary '',_binary '\0',_binary '\0','BYTE_ARRAY','FILE_UPLOAD','NONE',''),(266,'id',27,'id','PRI','bigint','id',1,_binary '\0',19,0,NULL,'',_binary '',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(267,'code',27,'编码','UNI','varchar','code',2,_binary '\0',NULL,NULL,50,'',_binary '',_binary '',_binary '',_binary '','STRING','INPUT','LIKE',''),(268,'name',27,'名称','NONE','varchar','name',3,_binary '\0',NULL,NULL,50,'',_binary '',_binary '',_binary '',_binary '','STRING','INPUT','LIKE',''),(269,'sort',27,'排序','NONE','int','sort',4,_binary '',10,0,NULL,'0',_binary '',_binary '',_binary '\0',_binary '','INTEGER','INPUT_NUMBER','NONE',''),(270,'enable',27,'启用','NONE','bit','enable',5,_binary '',1,NULL,NULL,'b\'1\'',_binary '',_binary '',_binary '',_binary '','BOOLEAN','RADIO','EQUAL',''),(271,'menus',27,'菜单','NONE','json','menus',6,_binary '',NULL,NULL,NULL,'',_binary '',_binary '',_binary '\0',_binary '','LIST_LONG','SELECT','NONE',''),(272,'menu_check_strictly',27,'菜单树关联显示','NONE','bit','menuCheckStrictly',7,_binary '',1,NULL,NULL,'b\'1\'',_binary '',_binary '',_binary '\0',_binary '','BOOLEAN','RADIO','NONE',''),(273,'departments',27,'部门','NONE','json','departments',8,_binary '',NULL,NULL,NULL,'',_binary '',_binary '',_binary '\0',_binary '','LIST_LONG','SELECT','NONE',''),(274,'dept_check_strictly',27,'部门树关联显示','NONE','bit','deptCheckStrictly',9,_binary '',1,NULL,NULL,'b\'1\'',_binary '',_binary '',_binary '\0',_binary '','BOOLEAN','RADIO','NONE',''),(275,'data_scope',27,'数据权限范围','NONE','enum','dataScope',10,_binary '',NULL,NULL,10,'ALL',_binary '',_binary '',_binary '\0',_binary '','DATA_SCOPE_ENUM','SELECT','NONE',''),(276,'create_dept',27,'创建部门','NONE','bigint','createDept',11,_binary '',19,0,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(277,'create_by',27,'创建者','NONE','bigint','createBy',12,_binary '',19,0,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(278,'create_time',27,'创建时间','NONE','datetime','createTime',13,_binary '',NULL,NULL,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LOCAL_DATE_TIME','DATETIME_PICKER','NONE',''),(279,'update_by',27,'更新者','NONE','bigint','updateBy',14,_binary '',19,0,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LONG','INPUT_NUMBER','NONE',''),(280,'update_time',27,'更新时间','NONE','datetime','updateTime',15,_binary '',NULL,NULL,NULL,'',_binary '\0',_binary '\0',_binary '\0',_binary '\0','LOCAL_DATE_TIME','DATETIME_PICKER','NONE','');
/*!40000 ALTER TABLE `gen_column` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gen_table`
--

DROP TABLE IF EXISTS `gen_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gen_table` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `table_name` varchar(200) NOT NULL DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) NOT NULL DEFAULT '' COMMENT '表描述',
  `package_name` varchar(100) DEFAULT '' COMMENT '生成包路径',
  `module_name` varchar(30) DEFAULT '' COMMENT '生成模块名',
  `class_name` varchar(100) NOT NULL DEFAULT '' COMMENT '类名称',
  `class_comment` varchar(50) NOT NULL DEFAULT '' COMMENT '类描述',
  `gen_type` enum('DIRECTORY','ZIP') DEFAULT 'ZIP' COMMENT '生成类型',
  `template_type` enum('LIST','TREE') DEFAULT 'LIST' COMMENT '模板类型',
  `author` varchar(50) DEFAULT '' COMMENT '作者',
  `parent_menu_id` bigint DEFAULT NULL COMMENT '父菜单编号',
  `tree_key` varchar(20) DEFAULT NULL COMMENT '树节点标识',
  `tree_label` varchar(20) DEFAULT NULL COMMENT '树节点标签',
  `tree_parent_key` varchar(20) DEFAULT NULL COMMENT '树父节点标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='代码生成表定义';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gen_table`
--

LOCK TABLES `gen_table` WRITE;
/*!40000 ALTER TABLE `gen_table` DISABLE KEYS */;
INSERT INTO `gen_table` VALUES (12,'system_dept','系统部门','cn.sijay.biu','system','SystemDept','系统部门','ZIP','LIST','sijay',NULL,NULL,NULL,NULL),(13,'system_user','系统用户','cn.sijay.biu','system','SystemUser','系统用户','ZIP','LIST','sijay',NULL,NULL,NULL,NULL),(15,'file_storage','文件存储表','cn.sijay.biu','file','FileStorage','文件存储','ZIP','LIST','sijay',NULL,NULL,NULL,NULL),(16,'log_login','登录日志表','cn.sijay.biu','log','LogLogin','登录日志','ZIP','LIST','sijay',NULL,NULL,NULL,NULL),(17,'log_operate','操作日志表','cn.sijay.biu','log','LogOperate','操作日志','ZIP','LIST','sijay',NULL,NULL,NULL,NULL),(18,'system_config','系统配置','cn.sijay.biu','system','SystemConfig','系统配置','ZIP','LIST','sijay',NULL,NULL,NULL,NULL),(20,'system_dict','系统字典','cn.sijay.biu','system','SystemDict','系统字典','ZIP','LIST','sijay',NULL,NULL,NULL,NULL),(21,'system_dict_data','系统字典数据','cn.sijay.biu','system','SystemDictData','系统字典数据','ZIP','LIST','sijay',NULL,NULL,NULL,NULL),(22,'system_menu','系统菜单','cn.sijay.biu','system','SystemMenu','系统菜单','ZIP','LIST','sijay',NULL,NULL,NULL,NULL),(23,'system_message','系统消息','cn.sijay.biu','system','SystemMessage','系统消息','ZIP','LIST','sijay',NULL,NULL,NULL,NULL),(24,'system_module','系统模块','cn.sijay.biu','system','SystemModule','系统模块','ZIP','LIST','sijay',NULL,NULL,NULL,NULL),(25,'system_post','系统岗位','cn.sijay.biu','system','SystemPost','系统岗位','ZIP','LIST','sijay',NULL,NULL,NULL,NULL),(26,'system_user_avatar','用户头像表','cn.sijay.biu','system','SystemUserAvatar','用户头像','ZIP','LIST','sijay',NULL,NULL,NULL,NULL),(27,'system_role','系统权限','cn.sijay.biu','system','SystemRole','系统权限','ZIP','LIST','sijay',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `gen_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log_login`
--

DROP TABLE IF EXISTS `log_login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `log_login` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `ip` varchar(128) DEFAULT NULL COMMENT '登录ip',
  `location` varchar(255) DEFAULT NULL COMMENT '登录地点',
  `browser` varchar(100) DEFAULT NULL COMMENT '浏览器类型',
  `os` varchar(50) DEFAULT NULL COMMENT '操作系统',
  `successful` bit(1) DEFAULT b'0' COMMENT '登录状态',
  `msg` varchar(255) DEFAULT NULL COMMENT '提示消息',
  `time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '时间',
  PRIMARY KEY (`id`),
  KEY `idx_log_login_time` (`time`),
  KEY `idx_log_login_successful` (`successful`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='登录日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_login`
--

LOCK TABLES `log_login` WRITE;
/*!40000 ALTER TABLE `log_login` DISABLE KEYS */;
/*!40000 ALTER TABLE `log_login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log_operate`
--

DROP TABLE IF EXISTS `log_operate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `log_operate` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `title` varchar(50) DEFAULT NULL COMMENT '模块标题',
  `method` varchar(100) DEFAULT NULL COMMENT '方法名称',
  `request_method` varchar(10) DEFAULT NULL COMMENT '请求方式',
  `url` varchar(255) DEFAULT NULL COMMENT '请求URL',
  `ip` varchar(128) DEFAULT NULL COMMENT '主机地址',
  `location` varchar(255) DEFAULT NULL COMMENT '操作地点',
  `param` varchar(2000) DEFAULT NULL COMMENT '请求参数',
  `result` varchar(2000) DEFAULT NULL COMMENT '返回参数',
  `status` int DEFAULT '200' COMMENT '操作状态',
  `error_msg` varchar(2000) DEFAULT NULL COMMENT '错误消息',
  `time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  `cost_time` bigint DEFAULT '0' COMMENT '消耗时间',
  PRIMARY KEY (`id`),
  KEY `idx_log_operate_status` (`status`),
  KEY `idx_log_operate_user_id` (`user_id`),
  KEY `idx_log_operate_time` (`time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='操作日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_operate`
--

LOCK TABLES `log_operate` WRITE;
/*!40000 ALTER TABLE `log_operate` DISABLE KEYS */;
/*!40000 ALTER TABLE `log_operate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_config`
--

DROP TABLE IF EXISTS `system_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_config` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `code` varchar(100) NOT NULL COMMENT '编码',
  `value` varchar(500) NOT NULL COMMENT '值',
  `internal` bit(1) DEFAULT b'0' COMMENT '内置',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统配置';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_config`
--

LOCK TABLES `system_config` WRITE;
/*!40000 ALTER TABLE `system_config` DISABLE KEYS */;
/*!40000 ALTER TABLE `system_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_dept`
--

DROP TABLE IF EXISTS `system_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_dept` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) NOT NULL COMMENT '部门名称',
  `parent_id` bigint DEFAULT NULL COMMENT '父部门id',
  `ancestors` json DEFAULT NULL COMMENT '祖级列表',
  `sort` int DEFAULT NULL COMMENT '排序',
  `leader` bigint DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `enable` bit(1) DEFAULT b'1' COMMENT '启用',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统部门';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_dept`
--

LOCK TABLES `system_dept` WRITE;
/*!40000 ALTER TABLE `system_dept` DISABLE KEYS */;
/*!40000 ALTER TABLE `system_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_dict`
--

DROP TABLE IF EXISTS `system_dict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_dict` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `code` varchar(100) NOT NULL COMMENT '编码',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统字典';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_dict`
--

LOCK TABLES `system_dict` WRITE;
/*!40000 ALTER TABLE `system_dict` DISABLE KEYS */;
/*!40000 ALTER TABLE `system_dict` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_dict_data`
--

DROP TABLE IF EXISTS `system_dict_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_dict_data` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `dict_id` bigint NOT NULL COMMENT '字典',
  `label` varchar(100) NOT NULL COMMENT '标签',
  `value` varchar(100) NOT NULL COMMENT '键值',
  `sort` int DEFAULT '0' COMMENT '排序',
  `class_name` varchar(100) DEFAULT NULL COMMENT '样式属性',
  `show_type` enum('DANGER','INFO','NONE','PRIMARY','SUCCESS','WARNING') DEFAULT 'NONE' COMMENT '表格回显样式',
  `default_value` bit(1) DEFAULT b'0' COMMENT '是否默认',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_system_dict_data_dict_id` (`dict_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统字典数据';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_dict_data`
--

LOCK TABLES `system_dict_data` WRITE;
/*!40000 ALTER TABLE `system_dict_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `system_dict_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_menu`
--

DROP TABLE IF EXISTS `system_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `parent_id` bigint DEFAULT '0' COMMENT '父菜单id',
  `name` varchar(50) NOT NULL COMMENT '菜单名称',
  `perms_code` varchar(100) NOT NULL COMMENT '权限编码',
  `sort` int DEFAULT '0' COMMENT '排序',
  `path` varchar(200) DEFAULT NULL COMMENT '路由地址',
  `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
  `type` enum('BUTTON','DIRECTORY','MENU') NOT NULL COMMENT '菜单类型',
  `external_link` bit(1) DEFAULT b'0' COMMENT '是否外链',
  `cacheable` bit(1) DEFAULT b'1' COMMENT '可缓存',
  `visible` bit(1) DEFAULT b'1' COMMENT '可显示',
  `enable` bit(1) DEFAULT b'1' COMMENT '启用',
  `icon` varchar(100) DEFAULT NULL COMMENT '菜单图标',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统菜单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_menu`
--

LOCK TABLES `system_menu` WRITE;
/*!40000 ALTER TABLE `system_menu` DISABLE KEYS */;
INSERT INTO `system_menu` VALUES (1,1,'系统用户','system:systemUser:list',1,'systemUser','system/systemUser','MENU',_binary '\0',_binary '',_binary '',_binary '',NULL,NULL,NULL,NULL,NULL,NULL),(2,1,'系统用户查询','system:systemUser:query',1,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(3,1,'系统用户新增','system:systemUser:add',2,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(4,1,'系统用户修改','system:systemUser:edit',3,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(5,1,'系统用户删除','system:systemUser:remove',4,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(6,1,'系统用户导出','system:systemUser:export',5,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(7,1,'系统部门','system:systemDept:list',1,'systemDept','system/systemDept','MENU',_binary '\0',_binary '',_binary '',_binary '',NULL,NULL,NULL,NULL,NULL,NULL),(8,7,'系统部门查询','system:systemDept:query',1,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(9,7,'系统部门新增','system:systemDept:add',2,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(10,7,'系统部门修改','system:systemDept:edit',3,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(11,7,'系统部门删除','system:systemDept:remove',4,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(12,7,'系统部门导出','system:systemDept:export',5,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(13,1,'文件存储','file:fileStorage:list',1,'fileStorage','file/fileStorage','MENU',_binary '\0',_binary '',_binary '',_binary '',NULL,NULL,NULL,NULL,NULL,NULL),(14,13,'文件存储查询','file:fileStorage:query',1,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(15,13,'文件存储新增','file:fileStorage:add',2,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(16,13,'文件存储修改','file:fileStorage:edit',3,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(17,13,'文件存储删除','file:fileStorage:remove',4,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(18,13,'文件存储导出','file:fileStorage:export',5,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(19,1,'登录日志','log:logLogin:list',1,'logLogin','log/logLogin','MENU',_binary '\0',_binary '',_binary '',_binary '',NULL,NULL,NULL,NULL,NULL,NULL),(20,19,'登录日志查询','log:logLogin:query',1,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(21,19,'登录日志新增','log:logLogin:add',2,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(22,19,'登录日志修改','log:logLogin:edit',3,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(23,19,'登录日志删除','log:logLogin:remove',4,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(24,19,'登录日志导出','log:logLogin:export',5,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(25,1,'操作日志','log:logOperate:list',1,'logOperate','log/logOperate','MENU',_binary '\0',_binary '',_binary '',_binary '',NULL,NULL,NULL,NULL,NULL,NULL),(26,25,'操作日志查询','log:logOperate:query',1,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(27,25,'操作日志新增','log:logOperate:add',2,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(28,25,'操作日志修改','log:logOperate:edit',3,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(29,25,'操作日志删除','log:logOperate:remove',4,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(30,25,'操作日志导出','log:logOperate:export',5,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(31,1,'系统配置','system:systemConfig:list',1,'systemConfig','system/systemConfig','MENU',_binary '\0',_binary '',_binary '',_binary '',NULL,NULL,NULL,NULL,NULL,NULL),(32,31,'系统配置查询','system:systemConfig:query',1,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(33,31,'系统配置新增','system:systemConfig:add',2,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(34,31,'系统配置修改','system:systemConfig:edit',3,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(35,31,'系统配置删除','system:systemConfig:remove',4,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(36,31,'系统配置导出','system:systemConfig:export',5,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(37,1,'系统字典数据','system:systemDictData:list',1,'systemDictData','system/systemDictData','MENU',_binary '\0',_binary '',_binary '',_binary '',NULL,NULL,NULL,NULL,NULL,NULL),(38,37,'系统字典数据查询','system:systemDictData:query',1,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(39,37,'系统字典数据新增','system:systemDictData:add',2,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(40,37,'系统字典数据修改','system:systemDictData:edit',3,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(41,37,'系统字典数据删除','system:systemDictData:remove',4,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(42,37,'系统字典数据导出','system:systemDictData:export',5,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(43,1,'系统字典','system:systemDict:list',1,'systemDict','system/systemDict','MENU',_binary '\0',_binary '',_binary '',_binary '',NULL,NULL,NULL,NULL,NULL,NULL),(44,43,'系统字典查询','system:systemDict:query',1,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(45,43,'系统字典新增','system:systemDict:add',2,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(46,43,'系统字典修改','system:systemDict:edit',3,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(47,43,'系统字典删除','system:systemDict:remove',4,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(48,43,'系统字典导出','system:systemDict:export',5,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(49,1,'系统菜单','system:systemMenu:list',1,'systemMenu','system/systemMenu','MENU',_binary '\0',_binary '',_binary '',_binary '',NULL,NULL,NULL,NULL,NULL,NULL),(50,49,'系统菜单查询','system:systemMenu:query',1,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(51,49,'系统菜单新增','system:systemMenu:add',2,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(52,49,'系统菜单修改','system:systemMenu:edit',3,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(53,49,'系统菜单删除','system:systemMenu:remove',4,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(54,49,'系统菜单导出','system:systemMenu:export',5,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(55,1,'系统消息','system:systemMessage:list',1,'systemMessage','system/systemMessage','MENU',_binary '\0',_binary '',_binary '',_binary '',NULL,NULL,NULL,NULL,NULL,NULL),(56,55,'系统消息查询','system:systemMessage:query',1,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(57,55,'系统消息新增','system:systemMessage:add',2,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(58,55,'系统消息修改','system:systemMessage:edit',3,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(59,55,'系统消息删除','system:systemMessage:remove',4,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(60,55,'系统消息导出','system:systemMessage:export',5,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(61,1,'系统模块','system:systemModule:list',1,'systemModule','system/systemModule','MENU',_binary '\0',_binary '',_binary '',_binary '',NULL,NULL,NULL,NULL,NULL,NULL),(62,61,'系统模块查询','system:systemModule:query',1,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(63,61,'系统模块新增','system:systemModule:add',2,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(64,61,'系统模块修改','system:systemModule:edit',3,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(65,61,'系统模块删除','system:systemModule:remove',4,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(66,61,'系统模块导出','system:systemModule:export',5,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(67,1,'系统岗位','system:systemPost:list',1,'systemPost','system/systemPost','MENU',_binary '\0',_binary '',_binary '',_binary '',NULL,NULL,NULL,NULL,NULL,NULL),(68,67,'系统岗位查询','system:systemPost:query',1,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(69,67,'系统岗位新增','system:systemPost:add',2,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(70,67,'系统岗位修改','system:systemPost:edit',3,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(71,67,'系统岗位删除','system:systemPost:remove',4,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(72,67,'系统岗位导出','system:systemPost:export',5,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(73,1,'系统权限','system:systemRole:list',1,'systemRole','system/systemRole','MENU',_binary '\0',_binary '',_binary '',_binary '',NULL,NULL,NULL,NULL,NULL,NULL),(74,73,'系统权限查询','system:systemRole:query',1,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(75,73,'系统权限新增','system:systemRole:add',2,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(76,73,'系统权限修改','system:systemRole:edit',3,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(77,73,'系统权限删除','system:systemRole:remove',4,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(78,73,'系统权限导出','system:systemRole:export',5,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(79,1,'用户头像','system:systemUserAvatar:list',1,'systemUserAvatar','system/systemUserAvatar','MENU',_binary '\0',_binary '',_binary '',_binary '',NULL,NULL,NULL,NULL,NULL,NULL),(80,79,'用户头像查询','system:systemUserAvatar:query',1,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(81,79,'用户头像新增','system:systemUserAvatar:add',2,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(82,79,'用户头像修改','system:systemUserAvatar:edit',3,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(83,79,'用户头像删除','system:systemUserAvatar:remove',4,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL),(84,79,'用户头像导出','system:systemUserAvatar:export',5,NULL,NULL,'BUTTON',_binary '\0',_binary '',_binary '',_binary '','',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `system_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_message`
--

DROP TABLE IF EXISTS `system_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_message` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(50) NOT NULL COMMENT '消息标题',
  `type` enum('ANNOUNCEMENT','MESSAGE','NOTICE') NOT NULL DEFAULT 'NOTICE' COMMENT '消息类型',
  `content` varchar(5000) DEFAULT NULL COMMENT '内容',
  `closed` bit(1) DEFAULT b'0' COMMENT '状态',
  `publisher` bigint DEFAULT NULL COMMENT '发布人',
  `receiver` json DEFAULT NULL COMMENT '接收人',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统消息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_message`
--

LOCK TABLES `system_message` WRITE;
/*!40000 ALTER TABLE `system_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `system_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_module`
--

DROP TABLE IF EXISTS `system_module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_module` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `code` varchar(50) NOT NULL COMMENT '编码',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `roles` json DEFAULT NULL COMMENT '角色',
  `menus` json DEFAULT NULL COMMENT '菜单',
  `enable` bit(1) DEFAULT b'1' COMMENT '启用',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统模块';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_module`
--

LOCK TABLES `system_module` WRITE;
/*!40000 ALTER TABLE `system_module` DISABLE KEYS */;
INSERT INTO `system_module` VALUES (1,'system','系统管理',NULL,NULL,_binary '',NULL,NULL,NULL,NULL,NULL),(2,'oa','OA',NULL,NULL,_binary '',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `system_module` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_post`
--

DROP TABLE IF EXISTS `system_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_post` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `code` varchar(50) NOT NULL COMMENT '编码',
  `dept_id` bigint NOT NULL COMMENT '部门id',
  `sort` int DEFAULT '0' COMMENT '排序',
  `enable` bit(1) DEFAULT b'1' COMMENT '启用',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统岗位';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_post`
--

LOCK TABLES `system_post` WRITE;
/*!40000 ALTER TABLE `system_post` DISABLE KEYS */;
/*!40000 ALTER TABLE `system_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_role`
--

DROP TABLE IF EXISTS `system_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `code` varchar(50) NOT NULL COMMENT '编码',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `sort` int DEFAULT '0' COMMENT '排序',
  `enable` bit(1) DEFAULT b'1' COMMENT '启用',
  `menus` json DEFAULT NULL COMMENT '菜单',
  `menu_check_strictly` bit(1) DEFAULT b'1' COMMENT '菜单树关联显示',
  `departments` json DEFAULT NULL COMMENT '部门',
  `dept_check_strictly` bit(1) DEFAULT b'1' COMMENT '部门树关联显示',
  `data_scope` enum('ALL','DEPT','DEPT_UNDER','SELF') DEFAULT 'ALL' COMMENT '数据权限范围',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统权限';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_role`
--

LOCK TABLES `system_role` WRITE;
/*!40000 ALTER TABLE `system_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `system_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_user`
--

DROP TABLE IF EXISTS `system_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(20) NOT NULL COMMENT '账号',
  `dept_id` bigint DEFAULT NULL COMMENT '部门',
  `real_name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `gender` enum('MALE','FEMALE','UNKNOWN') DEFAULT 'UNKNOWN' COMMENT '性别',
  `avatar` bigint DEFAULT NULL COMMENT '头像',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `roles` json DEFAULT NULL COMMENT '角色',
  `posts` json DEFAULT NULL COMMENT '岗位',
  `modules` json DEFAULT NULL COMMENT '模块',
  `enable` bit(1) DEFAULT b'1' COMMENT '启用',
  `pwd_changed` bit(1) DEFAULT b'0' COMMENT '密码是否更改',
  `create_dept` bigint DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_user`
--

LOCK TABLES `system_user` WRITE;
/*!40000 ALTER TABLE `system_user` DISABLE KEYS */;
INSERT INTO `system_user` VALUES (1,'admin',NULL,NULL,NULL,NULL,'UNKNOWN',NULL,'OGQ5NjllZWY2ZWNhZDNjMjlhM2E2MjkyODBlNjg2Y2YwYzNmNWQ1YTg2YWZmM2NhMTIwMjBjOTIzYWRjNmM5Mg==',NULL,NULL,NULL,_binary '',_binary '\0',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `system_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_user_avatar`
--

DROP TABLE IF EXISTS `system_user_avatar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_user_avatar` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `avatar` blob COMMENT '头像',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户头像表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_user_avatar`
--

LOCK TABLES `system_user_avatar` WRITE;
/*!40000 ALTER TABLE `system_user_avatar` DISABLE KEYS */;
/*!40000 ALTER TABLE `system_user_avatar` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-24 16:33:44
