package cn.sijay.suap.core.convert;

import cn.sijay.suap.core.annotation.ExcelDictFormat;
import cn.sijay.suap.core.utils.BaseExcelUtil;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import lombok.extern.slf4j.Slf4j;
import org.mockito.internal.configuration.GlobalConfiguration;

/**
 * <strong>ExcelDictConvert</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-06-01
 */
@Slf4j
public class ExcelDictConvert implements Converter<Object> {

    @Override
    public Class<Object> supportJavaTypeKey() {
        return Object.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return null;
    }

    @Override
    public Object convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) {
        ExcelDictFormat anno = getAnnotation(contentProperty.getField());
        String type = anno.dictType();
        String label = cellData.getStringValue();
        String value;
//        if (StringUtil.isBlank(type)) {
        value = BaseExcelUtil.reverseByExp(label, anno.readConverterExp(), anno.separator());
//        } else {
//            value = SpringUtil.getBean(DictService.class).getDictValue(type, label, anno.separator());
//        }
        return Convert.convert(contentProperty.getField().getType(), value);
    }

    @Override
    public WriteCellData<String> convertToExcelData(Object object, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) {
        if (ObjectUtil.isNull(object)) {
            return new WriteCellData<>("");
        }
        ExcelDictFormat anno = getAnnotation(contentProperty.getField());
        String type = anno.dictType();
        String value = Convert.toStr(object);
        String label;
//        if (StringUtil.isBlank(type)) {
        label = BaseExcelUtil.convertByExp(value, anno.readConverterExp(), anno.separator());
//        } else {
//            label = SpringUtil.getBean(DictService.class).getDictLabel(type, value, anno.separator());
//        }
        return new WriteCellData<>(label);
    }

    private ExcelDictFormat getAnnotation(Field field) {
        return AnnotationUtil.getAnnotation(field, ExcelDictFormat.class);
    }
}
