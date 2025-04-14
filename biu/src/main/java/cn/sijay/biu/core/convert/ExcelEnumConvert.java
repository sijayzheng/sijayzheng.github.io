package cn.sijay.biu.core.convert;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import lombok.extern.slf4j.Slf4j;

/**
 * 枚举格式化转换处理
 *
 * @author Sijay
 */
@Slf4j
public class ExcelEnumConvert implements Converter<Object> {

    @Override
    public Class<Object> supportJavaTypeKey() {
        return Object.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return null;
    }

//    @Override
//    public Object convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) {
//        cellData.checkEmpty();
//        // Excel中填入的是枚举中指定的描述
//        Object textValue = switch (cellData.getType()) {
//            case STRING, DIRECT_STRING, RICH_TEXT_STRING -> cellData.getStringValue();
//            case NUMBER -> cellData.getNumberValue();
//            case BOOLEAN -> cellData.getBooleanValue();
//            default -> throw new IllegalArgumentException("单元格类型异常!");
//        };
//        // 如果是空值
//        if (Objects.isNull(textValue)) {
//            return null;
//        }
//        Map<Object, String> enumCodeToTextMap = beforeConvert(contentProperty);
//        // 从Java输出至Excel是code转text
//        // 因此从Excel转Java应该将text与code对调
//        Map<Object, Object> enumTextToCodeMap = new HashMap<>();
//        enumCodeToTextMap.forEach((key, value) -> enumTextToCodeMap.put(value, key));
//        // 应该从text -> code中查找
//        Object codeValue = enumTextToCodeMap.get(textValue);
//        return Convert.convert(contentProperty.getField().getType(), codeValue);
//    }

//    @Override
//    public WriteCellData<String> convertToExcelData(Object object, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) {
//        if (Objects.isNull(object)) {
//            return new WriteCellData<>("");
//        }
//        Map<Object, String> enumValueMap = beforeConvert(contentProperty);
//        String value = Convert.toStr(enumValueMap.get(object), "");
//        return new WriteCellData<>(value);
//    }

//    private Map<Object, String> beforeConvert(ExcelContentProperty contentProperty) {
//        ExcelEnumFormat anno = getAnnotation(contentProperty.getField());
//        Map<Object, String> enumValueMap = new HashMap<>();
//        Enum<?>[] enumConstants = anno.enumClass().getEnumConstants();
//        for (Enum<?> enumConstant : enumConstants) {
//            Object codeValue = ReflectUtil.invokeGetter(enumConstant, anno.codeField());
//            String textValue = ReflectUtil.invokeGetter(enumConstant, anno.textField());
//            enumValueMap.put(codeValue, textValue);
//        }
//        return enumValueMap;
//    }

//    private ExcelEnumFormat getAnnotation(Field field) {
//        return AnnotationUtil.getAnnotation(field, ExcelEnumFormat.class);
//    }
}
