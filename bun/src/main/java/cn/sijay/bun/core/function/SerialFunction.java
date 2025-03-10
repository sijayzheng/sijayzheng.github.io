package cn.sijay.bun.core.function;

import java.io.*;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.function.Function;

/**
 * <strong>SerialFunction</strong>
 * <p>
 * 可序列化的Function
 * </p>
 *
 * @author sijay
 * @since 2024-12-11
 */
@FunctionalInterface
public interface SerialFunction<T, R> extends Function<T, R>, Serializable {
    default String getFieldName() {
        String fieldName;
        try {
            Method method = this.getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(true);
            fieldName = ((SerializedLambda) method.invoke(this)).getImplMethodName();
        } catch (Throwable et) {
            try (ByteArrayOutputStream bs = new ByteArrayOutputStream();
                 ObjectOutputStream os = new ObjectOutputStream(bs)) {
                os.writeObject(this);
                os.flush();
                try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bs.toByteArray())) {
                    @Override
                    protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
                        return super.resolveClass(desc);
                    }

                }) {
                    fieldName = ((SerializedLambda) ois.readObject()).getImplMethodName();
                }
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        if (fieldName.startsWith("is")) {
            fieldName = fieldName.substring(2);
        } else if (fieldName.startsWith("get") || fieldName.startsWith("set")) {
            fieldName = fieldName.substring(3);
        } else {
            throw new RuntimeException("反射错误，" + fieldName + "非以is get set开头");
        }
        if (fieldName.length() == 1 || fieldName.length() > 1 && !Character.isUpperCase(fieldName.charAt(1))) {
            fieldName = fieldName.substring(0, 1).toLowerCase(Locale.ENGLISH) + fieldName.substring(1);
        }
        return fieldName;
    }
}
