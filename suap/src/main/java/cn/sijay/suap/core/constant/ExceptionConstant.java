package cn.sijay.suap.core.constant;

/**
 * <strong>ExceptionEnum</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
public interface ExceptionConstant {
    String UNKNOWN_ERROR = "未知错误";

    String BUSINESS_ERROR = "业务错误";
    String UPDATE_ERROR = "更新错误";
    String DELETE_ERROR = "删除错误";
    String INSERT_ERROR = "插入错误";
    String SELECT_ERROR = "查询错误";

    String NO_LOGIN_ERROR = "用户未登录";
    String NO_MODULE_PERMISSION_ERROR = "用户没有该模块的访问权限";
    String LOGIN_ERROR = "登录失败，用户名或密码错误";
    String LOGOUT_ERROR = "登出错误";
    String REGISTER_ERROR = "注册错误";

    String VALIDATE_UNIQUE_ERROR = "唯一性校验错误,字段[{}]的值[{}]已存在";

    String ENTITY_NOT_FOUND = "数据{}不存在";

    String REQUEST_PARAM_ERROR = "请求参数错误";

    String EXCEL_READ_ERROR = "Excel文件读取失败";
    String EXCEL_EXPORT_ERROR = "Excel文件导出失败";
    String EXCEL_TEMPLATE_EXPORT_ERROR = "Excel模板导出失败";

    String REFLECT_ERROR = "反射错误";
    String REFLECT_CLASS_NOT_FOUND_ERROR = "反射错误，类{}找不到";
    String REFLECT_METHOD_NOT_FOUND_ERROR = "反射错误，类{}方法{}找不到";
    String REFLECT_METHOD_INVOKE_ERROR = "反射错误，类{}方法{}调用错误";

    String FILE_NOT_FOUND = "文件{}不存在";
    String FILE_NOT_FOLDER = "{}不是文件夹";

    String ZIP_FILE_FAILED = "文件压缩失败";

    String IO_ERROR = "IO异常";

    String JSON_SERIAL_ERROR = "JSON序列化错误,{}";
    String JSON_PARSE_ERROR = "JSON反序列化错误,{}";
    String HAS_CHILDREN = "数据{}含有子节点";
    String PRIMARY_KEY_NOT_FOUND = "找不到类{}的主键";
    String TRANSLATE_ERROR = "翻译错误";
    String FILE_MERGE_ERROR = "文件合并失败";
    String XOR_CALC_ERROR = "xor计算错误";
}
