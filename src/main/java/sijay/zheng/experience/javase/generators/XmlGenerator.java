package sijay.zheng.experience.javase.generators;

import com.alibaba.fastjson2.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.sax.*;
import javax.xml.transform.stream.*;
import java.io.*;
import java.util.*;

/**
 * @author Sijay
 */
public class XmlGenerator {
    static List<LinkedHashMap> indexList = JSON.parseArray("[{\"table_name\":\"auth_corp\",\"index_name\":\"PRIMARY\"}," +
            "{\"table_name\":\"auth_corp\",\"index_name\":\"id\"}]", LinkedHashMap.class);
    static List<LinkedHashMap> colmunList = JSON.parseArray("[{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"id\",\"ORDINAL_POSITION\":\"1\",\"NUMERIC_PRECISION\":\"19\",\"COLUMN_TYPE\":\"bigint(20)\",\"COLUMN_COMMENT\":\"id\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"corp_code\",\"ORDINAL_POSITION\":\"2\",\"COLUMN_TYPE\":\"varchar(50)\",\"COLUMN_COMMENT\":\"企业统一社会信用代码 : 企业统一社会信用代码\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"corp_name\",\"ORDINAL_POSITION\":\"3\",\"COLUMN_TYPE\":\"varchar(120)\",\"COLUMN_COMMENT\":\"企业名称\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"subject_type\",\"ORDINAL_POSITION\":\"4\",\"COLUMN_TYPE\":\"varchar(50)\",\"COLUMN_COMMENT\":\"主体类型 : 主体类型\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"corp_address\",\"ORDINAL_POSITION\":\"5\",\"COLUMN_TYPE\":\"varchar(200)\",\"COLUMN_COMMENT\":\"企业注册地址 : 企业注册地址\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"regist_capi\",\"ORDINAL_POSITION\":\"6\",\"COLUMN_TYPE\":\"varchar(50)\",\"COLUMN_COMMENT\":\"注册资本\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"start_date\",\"ORDINAL_POSITION\":\"7\",\"COLUMN_TYPE\":\"varchar(20)\",\"COLUMN_COMMENT\":\"成立日期\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"buss_term\",\"ORDINAL_POSITION\":\"8\",\"COLUMN_TYPE\":\"varchar(50)\",\"COLUMN_COMMENT\":\"营业期限\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"scope\",\"ORDINAL_POSITION\":\"9\",\"COLUMN_TYPE\":\"text\",\"COLUMN_COMMENT\":\"经营范围 : 经营范围\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"province_id\",\"ORDINAL_POSITION\":\"10\",\"NUMERIC_PRECISION\":\"19\",\"COLUMN_TYPE\":\"bigint(20)\",\"COLUMN_COMMENT\":\"省份ID : 省份ID\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"city_id\",\"ORDINAL_POSITION\":\"11\",\"NUMERIC_PRECISION\":\"19\",\"COLUMN_TYPE\":\"bigint(20)\",\"COLUMN_COMMENT\":\"城市ID : 城市ID\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"industry_id\",\"ORDINAL_POSITION\":\"12\",\"NUMERIC_PRECISION\":\"19\",\"COLUMN_TYPE\":\"bigint(20)\",\"COLUMN_COMMENT\":\"行业ID : 行业ID\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"biz_license_file_Id\",\"ORDINAL_POSITION\":\"13\",\"NUMERIC_PRECISION\":\"19\",\"COLUMN_TYPE\":\"bigint(20)\",\"COLUMN_COMMENT\":\"营业执照文件ID\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"approval_status\",\"ORDINAL_POSITION\":\"14\",\"NUMERIC_PRECISION\":\"10\",\"COLUMN_TYPE\":\"int(11)\",\"COLUMN_COMMENT\":\"审批状态 : 审批状态[0-已注册/1-待审批/2-审批通过/3-审批退回]\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"approval_desc\",\"ORDINAL_POSITION\":\"15\",\"COLUMN_TYPE\":\"varchar(200)\",\"COLUMN_COMMENT\":\"审批说明\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"workflow_biz_key\",\"ORDINAL_POSITION\":\"16\",\"COLUMN_TYPE\":\"varchar(50)\",\"COLUMN_COMMENT\":\"工作流关联的bizkey\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"auth_cert_file_id\",\"ORDINAL_POSITION\":\"17\",\"NUMERIC_PRECISION\":\"19\",\"COLUMN_TYPE\":\"bigint(20)\",\"COLUMN_COMMENT\":\"平台授权书Id : 平台授权书Id\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"business_tel\",\"ORDINAL_POSITION\":\"18\",\"COLUMN_TYPE\":\"varchar(30)\",\"COLUMN_COMMENT\":\"工商电话 : 工商电话\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"office_tel\",\"ORDINAL_POSITION\":\"19\",\"COLUMN_TYPE\":\"varchar(20)\",\"COLUMN_COMMENT\":\"办公电话 : 办公电话\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"office_addr\",\"ORDINAL_POSITION\":\"20\",\"COLUMN_TYPE\":\"varchar(100)\",\"COLUMN_COMMENT\":\"办公地址 : 办公地址\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"cust_manager_no\",\"ORDINAL_POSITION\":\"21\",\"COLUMN_TYPE\":\"varchar(20)\",\"COLUMN_COMMENT\":\"客户经理工号 : 客户经理工号\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"use_flag\",\"ORDINAL_POSITION\":\"22\",\"COLUMN_TYPE\":\"char(1)\",\"COLUMN_COMMENT\":\"启用标识 : 启用标识[Y-启用/N-禁用]\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"bank_org_code\",\"ORDINAL_POSITION\":\"23\",\"COLUMN_TYPE\":\"varchar(50)\",\"COLUMN_COMMENT\":\"机构网点编号 : 机构网点编号\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"corp_identitys\",\"ORDINAL_POSITION\":\"24\",\"COLUMN_TYPE\":\"varchar(30)\",\"COLUMN_COMMENT\":\"企业身份集合 : 企业身份[H=核心企业/J=监管企业/R=融资企业]\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"credit_cust_no\",\"ORDINAL_POSITION\":\"25\",\"COLUMN_TYPE\":\"varchar(30)\",\"COLUMN_COMMENT\":\"信贷客户编号 : 信贷客户编号\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"bank_crt_user\",\"ORDINAL_POSITION\":\"26\",\"NUMERIC_PRECISION\":\"19\",\"COLUMN_TYPE\":\"bigint(20)\",\"COLUMN_COMMENT\":\"创建者 : 创建者\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"bank_crt_date\",\"ORDINAL_POSITION\":\"27\",\"DATETIME_PRECISION\":\"0\",\"COLUMN_TYPE\":\"datetime\",\"COLUMN_COMMENT\":\"更新时间 : 更新时间\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"bank_upd_user\",\"ORDINAL_POSITION\":\"28\",\"NUMERIC_PRECISION\":\"19\",\"COLUMN_TYPE\":\"bigint(20)\",\"COLUMN_COMMENT\":\"更新者 : 更新者\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"bank_upd_date\",\"ORDINAL_POSITION\":\"29\",\"DATETIME_PRECISION\":\"0\",\"COLUMN_TYPE\":\"datetime\",\"COLUMN_COMMENT\":\"更新时间 : 更新时间\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"corp_crt_user\",\"ORDINAL_POSITION\":\"30\",\"NUMERIC_PRECISION\":\"19\",\"COLUMN_TYPE\":\"bigint(20)\",\"COLUMN_COMMENT\":\"创建者 : 创建者\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"corp_crt_date\",\"ORDINAL_POSITION\":\"31\",\"DATETIME_PRECISION\":\"0\",\"COLUMN_TYPE\":\"datetime\",\"COLUMN_COMMENT\":\"创建时间 : 创建时间\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"corp_upd_user\",\"ORDINAL_POSITION\":\"32\",\"NUMERIC_PRECISION\":\"19\",\"COLUMN_TYPE\":\"bigint(20)\",\"COLUMN_COMMENT\":\"更新者 : 更新者\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"corp_upd_date\",\"ORDINAL_POSITION\":\"33\",\"DATETIME_PRECISION\":\"0\",\"COLUMN_TYPE\":\"datetime\",\"COLUMN_COMMENT\":\"更新时间 : 更新时间\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"version\",\"ORDINAL_POSITION\":\"34\",\"NUMERIC_PRECISION\":\"10\",\"COLUMN_TYPE\":\"int(11)\",\"COLUMN_COMMENT\":\"版本号 : 版本号\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"register_type\",\"ORDINAL_POSITION\":\"35\",\"NUMERIC_PRECISION\":\"10\",\"COLUMN_TYPE\":\"int(11)\",\"COLUMN_COMMENT\":\"注册方式 : 注册方式[1=ukey注册/2=普通注册]\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"e_account_id\",\"ORDINAL_POSITION\":\"36\",\"COLUMN_TYPE\":\"varchar(50)\",\"COLUMN_COMMENT\":\"e签宝账户id : e签宝账户id\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"dk_flow_id\",\"ORDINAL_POSITION\":\"37\",\"COLUMN_TYPE\":\"varchar(100)\",\"COLUMN_COMMENT\":\"打款流程id : 打款流程id\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"identity_step\",\"ORDINAL_POSITION\":\"38\",\"NUMERIC_PRECISION\":\"10\",\"COLUMN_TYPE\":\"int(11)\",\"COLUMN_COMMENT\":\"实名认证步骤 : 实名认证步骤[1-基础信息/2-法人信息/3-对公打款]\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"other_file_ids\",\"ORDINAL_POSITION\":\"39\",\"COLUMN_TYPE\":\"text\",\"COLUMN_COMMENT\":\"其他附件文件id : 其他附件文件id\"}]", LinkedHashMap.class);

    static char[] lineBreak = "\n".toCharArray();

    public static void g1() {
        List<LinkedHashMap> indexList = JSON.parseArray("[{\"table_name\":\"auth_corp\",\"index_name\":\"PRIMARY\"},{\"table_name\":\"auth_corp\",\"index_name\":\"id\"}]", LinkedHashMap.class);
        List<LinkedHashMap> colmunList = JSON.parseArray("[{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"id\",\"ORDINAL_POSITION\":\"1\",\"NUMERIC_PRECISION\":\"19\",\"COLUMN_TYPE\":\"bigint(20)\",\"COLUMN_COMMENT\":\"id\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"corp_code\",\"ORDINAL_POSITION\":\"2\",\"COLUMN_TYPE\":\"varchar(50)\",\"COLUMN_COMMENT\":\"企业统一社会信用代码 : 企业统一社会信用代码\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"corp_name\",\"ORDINAL_POSITION\":\"3\",\"COLUMN_TYPE\":\"varchar(120)\",\"COLUMN_COMMENT\":\"企业名称\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"subject_type\",\"ORDINAL_POSITION\":\"4\",\"COLUMN_TYPE\":\"varchar(50)\",\"COLUMN_COMMENT\":\"主体类型 : 主体类型\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"corp_address\",\"ORDINAL_POSITION\":\"5\",\"COLUMN_TYPE\":\"varchar(200)\",\"COLUMN_COMMENT\":\"企业注册地址 : 企业注册地址\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"regist_capi\",\"ORDINAL_POSITION\":\"6\",\"COLUMN_TYPE\":\"varchar(50)\",\"COLUMN_COMMENT\":\"注册资本\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"start_date\",\"ORDINAL_POSITION\":\"7\",\"COLUMN_TYPE\":\"varchar(20)\",\"COLUMN_COMMENT\":\"成立日期\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"buss_term\",\"ORDINAL_POSITION\":\"8\",\"COLUMN_TYPE\":\"varchar(50)\",\"COLUMN_COMMENT\":\"营业期限\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"scope\",\"ORDINAL_POSITION\":\"9\",\"COLUMN_TYPE\":\"text\",\"COLUMN_COMMENT\":\"经营范围 : 经营范围\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"province_id\",\"ORDINAL_POSITION\":\"10\",\"NUMERIC_PRECISION\":\"19\",\"COLUMN_TYPE\":\"bigint(20)\",\"COLUMN_COMMENT\":\"省份ID : 省份ID\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"city_id\",\"ORDINAL_POSITION\":\"11\",\"NUMERIC_PRECISION\":\"19\",\"COLUMN_TYPE\":\"bigint(20)\",\"COLUMN_COMMENT\":\"城市ID : 城市ID\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"industry_id\",\"ORDINAL_POSITION\":\"12\",\"NUMERIC_PRECISION\":\"19\",\"COLUMN_TYPE\":\"bigint(20)\",\"COLUMN_COMMENT\":\"行业ID : 行业ID\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"biz_license_file_Id\",\"ORDINAL_POSITION\":\"13\",\"NUMERIC_PRECISION\":\"19\",\"COLUMN_TYPE\":\"bigint(20)\",\"COLUMN_COMMENT\":\"营业执照文件ID\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"approval_status\",\"ORDINAL_POSITION\":\"14\",\"NUMERIC_PRECISION\":\"10\",\"COLUMN_TYPE\":\"int(11)\",\"COLUMN_COMMENT\":\"审批状态 : 审批状态[0-已注册/1-待审批/2-审批通过/3-审批退回]\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"approval_desc\",\"ORDINAL_POSITION\":\"15\",\"COLUMN_TYPE\":\"varchar(200)\",\"COLUMN_COMMENT\":\"审批说明\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"workflow_biz_key\",\"ORDINAL_POSITION\":\"16\",\"COLUMN_TYPE\":\"varchar(50)\",\"COLUMN_COMMENT\":\"工作流关联的bizkey\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"auth_cert_file_id\",\"ORDINAL_POSITION\":\"17\",\"NUMERIC_PRECISION\":\"19\",\"COLUMN_TYPE\":\"bigint(20)\",\"COLUMN_COMMENT\":\"平台授权书Id : 平台授权书Id\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"business_tel\",\"ORDINAL_POSITION\":\"18\",\"COLUMN_TYPE\":\"varchar(30)\",\"COLUMN_COMMENT\":\"工商电话 : 工商电话\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"office_tel\",\"ORDINAL_POSITION\":\"19\",\"COLUMN_TYPE\":\"varchar(20)\",\"COLUMN_COMMENT\":\"办公电话 : 办公电话\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"office_addr\",\"ORDINAL_POSITION\":\"20\",\"COLUMN_TYPE\":\"varchar(100)\",\"COLUMN_COMMENT\":\"办公地址 : 办公地址\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"cust_manager_no\",\"ORDINAL_POSITION\":\"21\",\"COLUMN_TYPE\":\"varchar(20)\",\"COLUMN_COMMENT\":\"客户经理工号 : 客户经理工号\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"use_flag\",\"ORDINAL_POSITION\":\"22\",\"COLUMN_TYPE\":\"char(1)\",\"COLUMN_COMMENT\":\"启用标识 : 启用标识[Y-启用/N-禁用]\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"bank_org_code\",\"ORDINAL_POSITION\":\"23\",\"COLUMN_TYPE\":\"varchar(50)\",\"COLUMN_COMMENT\":\"机构网点编号 : 机构网点编号\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"corp_identitys\",\"ORDINAL_POSITION\":\"24\",\"COLUMN_TYPE\":\"varchar(30)\",\"COLUMN_COMMENT\":\"企业身份集合 : 企业身份[H=核心企业/J=监管企业/R=融资企业]\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"credit_cust_no\",\"ORDINAL_POSITION\":\"25\",\"COLUMN_TYPE\":\"varchar(30)\",\"COLUMN_COMMENT\":\"信贷客户编号 : 信贷客户编号\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"bank_crt_user\",\"ORDINAL_POSITION\":\"26\",\"NUMERIC_PRECISION\":\"19\",\"COLUMN_TYPE\":\"bigint(20)\",\"COLUMN_COMMENT\":\"创建者 : 创建者\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"bank_crt_date\",\"ORDINAL_POSITION\":\"27\",\"DATETIME_PRECISION\":\"0\",\"COLUMN_TYPE\":\"datetime\",\"COLUMN_COMMENT\":\"更新时间 : 更新时间\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"bank_upd_user\",\"ORDINAL_POSITION\":\"28\",\"NUMERIC_PRECISION\":\"19\",\"COLUMN_TYPE\":\"bigint(20)\",\"COLUMN_COMMENT\":\"更新者 : 更新者\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"bank_upd_date\",\"ORDINAL_POSITION\":\"29\",\"DATETIME_PRECISION\":\"0\",\"COLUMN_TYPE\":\"datetime\",\"COLUMN_COMMENT\":\"更新时间 : 更新时间\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"corp_crt_user\",\"ORDINAL_POSITION\":\"30\",\"NUMERIC_PRECISION\":\"19\",\"COLUMN_TYPE\":\"bigint(20)\",\"COLUMN_COMMENT\":\"创建者 : 创建者\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"corp_crt_date\",\"ORDINAL_POSITION\":\"31\",\"DATETIME_PRECISION\":\"0\",\"COLUMN_TYPE\":\"datetime\",\"COLUMN_COMMENT\":\"创建时间 : 创建时间\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"corp_upd_user\",\"ORDINAL_POSITION\":\"32\",\"NUMERIC_PRECISION\":\"19\",\"COLUMN_TYPE\":\"bigint(20)\",\"COLUMN_COMMENT\":\"更新者 : 更新者\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"corp_upd_date\",\"ORDINAL_POSITION\":\"33\",\"DATETIME_PRECISION\":\"0\",\"COLUMN_TYPE\":\"datetime\",\"COLUMN_COMMENT\":\"更新时间 : 更新时间\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"version\",\"ORDINAL_POSITION\":\"34\",\"NUMERIC_PRECISION\":\"10\",\"COLUMN_TYPE\":\"int(11)\",\"COLUMN_COMMENT\":\"版本号 : 版本号\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"register_type\",\"ORDINAL_POSITION\":\"35\",\"NUMERIC_PRECISION\":\"10\",\"COLUMN_TYPE\":\"int(11)\",\"COLUMN_COMMENT\":\"注册方式 : 注册方式[1=ukey注册/2=普通注册]\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"e_account_id\",\"ORDINAL_POSITION\":\"36\",\"COLUMN_TYPE\":\"varchar(50)\",\"COLUMN_COMMENT\":\"e签宝账户id : e签宝账户id\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"dk_flow_id\",\"ORDINAL_POSITION\":\"37\",\"COLUMN_TYPE\":\"varchar(100)\",\"COLUMN_COMMENT\":\"打款流程id : 打款流程id\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"identity_step\",\"ORDINAL_POSITION\":\"38\",\"NUMERIC_PRECISION\":\"10\",\"COLUMN_TYPE\":\"int(11)\",\"COLUMN_COMMENT\":\"实名认证步骤 : 实名认证步骤[1-基础信息/2-法人信息/3-对公打款]\"},{\"TABLE_NAME\":\"auth_corp\",\"COLUMN_NAME\":\"other_file_ids\",\"ORDINAL_POSITION\":\"39\",\"COLUMN_TYPE\":\"text\",\"COLUMN_COMMENT\":\"其他附件文件id : 其他附件文件id\"}]", LinkedHashMap.class);
        try {
            // 创建解析器工厂
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = factory.newDocumentBuilder();
            Document document = db.newDocument();
            // 不显示standalone="no"
            document.setXmlStandalone(true);
            Element file = document.createElement("file");

            Element fileName = document.createElement("fileName");
            fileName.setTextContent("fileNameStr--------");

            Element tableName = document.createElement("tableName");
            tableName.setTextContent(colmunList.get(0)
                    .get("TABLE_NAME")
                    .toString()
                    .toUpperCase());

            Element tableZhName = document.createElement("tableZhName");
            tableZhName.setTextContent("tableNameStr----------");

            Element fileVersion = document.createElement("fileVersion");
            fileVersion.setTextContent("1.0");

            Element fieldCount = document.createElement("fieldCount");
            fieldCount.setTextContent(String.valueOf(colmunList.size()));

            Element dbType = document.createElement("dbType");
            dbType.setTextContent("mysql");

            Element fileDescription = document.createElement("fileDescription");
            for (int i = 0; i < colmunList.size(); i++) {
                Element field = document.createElement("field");
                LinkedHashMap<String, Object> column = colmunList.get(i);
                field.setAttribute("fieldName", column.get("COLUMN_NAME")
                        .toString());
                field.setAttribute("fieldType", column.get("COLUMN_TYPE")
                        .toString());
                field.setAttribute("fieldSeq", String.valueOf(i + 1));
                field.setAttribute("Comments", column.get("COLUMN_COMMENT")
                        .toString());
                fileDescription.appendChild(field);
            }

            Element keyDescription = document.createElement("keyDescription");
            for (int i = 1; i < indexList.size(); i++) {
                Element key = document.createElement("key");
                key.setAttribute("keyType", indexList.get(0)
                        .get("index_name")
                        .toString());
                key.setAttribute("keyName", indexList.get(i)
                        .get("table_name")
                        .toString());
                key.setAttribute("keyValue", indexList.get(i)
                        .get("table_name")
                        .toString());
                keyDescription.appendChild(key);
            }
            file.appendChild(fileName);
            file.appendChild(tableName);
            file.appendChild(tableZhName);
            file.appendChild(fileVersion);
            file.appendChild(fieldCount);
            file.appendChild(dbType);
            file.appendChild(fileDescription);
            file.appendChild(keyDescription);
            document.appendChild(file);

            System.out.println(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void g2() {
        try {
            StringWriter stringWriter = new StringWriter();
            //创建xml的结果流对象
            Result resultXml = new StreamResult(stringWriter);
            //获取sax生成处理这对象实例
            TransformerHandler transformerHandler = ((SAXTransformerFactory) SAXTransformerFactory.newInstance()).newTransformerHandler();
            transformerHandler.setResult(resultXml);
            //获取sax生成器
            Transformer transformer = transformerHandler.getTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
            //用来标识是否允许空格
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformerHandler.startDocument();
            transformerHandler.characters(lineBreak, 0, lineBreak.length);
            transformerHandler.startElement("", "", "file", null);
            setContent(transformerHandler, "fileName", "fileNameStr--------");
            setContent(transformerHandler, "tableName", "tableName--------");
            setContent(transformerHandler, "tableZhName", "tableZhName----------");
            setContent(transformerHandler, "fileVersion", "1.0");
            setContent(transformerHandler, "fieldCount", String.valueOf(colmunList.size()));
            setContent(transformerHandler, "dbType", "mysql");
            transformerHandler.characters(lineBreak, 0, lineBreak.length);
            transformerHandler.startElement("", "", "fileDescription", null);
            for (LinkedHashMap<String, Object> colmun : colmunList) {
                AttributesImpl attimp = new AttributesImpl();
                attimp.addAttribute("", "", "fieldName", "", colmun.get("COLUMN_NAME")
                        .toString());
                attimp.addAttribute("", "", "fieldType", "", colmun.get("COLUMN_TYPE")
                        .toString());
                attimp.addAttribute("", "", "fieldSeq", "", colmun.get("ORDINAL_POSITION")
                        .toString());
                attimp.addAttribute("", "", "Comments", "", colmun.get("COLUMN_COMMENT")
                        .toString());
                transformerHandler.characters(lineBreak, 0, lineBreak.length);
                transformerHandler.startElement("", "", "field", attimp);
                transformerHandler.endElement("", "", "field");
            }
            transformerHandler.characters(lineBreak, 0, lineBreak.length);
            transformerHandler.endElement("", "", "fileDescription");

            transformerHandler.characters(lineBreak, 0, lineBreak.length);
            transformerHandler.startElement("", "", "keyDescription", null);
            for (int i = 1; i < indexList.size(); i++) {
                AttributesImpl attimp = new AttributesImpl();
                attimp.addAttribute("", "", "keyType", "", indexList.get(0)
                        .get("index_name")
                        .toString());
                attimp.addAttribute("", "", "keyName", "", indexList.get(i)
                        .get("table_name")
                        .toString());
                attimp.addAttribute("", "", "keyValue", "", indexList.get(i)
                        .get("table_name")
                        .toString());
                transformerHandler.characters(lineBreak, 0, lineBreak.length);
                transformerHandler.startElement("", "", "key", attimp);
                transformerHandler.endElement("", "", "key");
            }
            transformerHandler.characters(lineBreak, 0, lineBreak.length);
            transformerHandler.endElement("", "", "keyDescription");

            transformerHandler.characters(lineBreak, 0, lineBreak.length);
            transformerHandler.endElement("", "", "file");
            transformerHandler.endDocument();
            System.out.println(stringWriter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setContent(TransformerHandler transformerHandler, String nodeName, String nodeContent) throws SAXException {
        transformerHandler.characters(lineBreak, 0, lineBreak.length);
        transformerHandler.startElement("", "", nodeName, null);
        char[] contents = nodeContent.toCharArray();
        transformerHandler.characters(contents, 0, contents.length);
        transformerHandler.endElement("", "", nodeName);
    }
}