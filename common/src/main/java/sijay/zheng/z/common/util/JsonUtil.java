/*
 * Ownership belongs to Sijay Zheng
 */

package sijay.zheng.z.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.NoArgsConstructor;
import sijay.zheng.z.common.annotation.LogDog;

/**
 * @author sijay
 */
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class JsonUtil {
    static final JsonMapper MAPPER = new JsonMapper();

    public static JsonMapper getMAPPER() {
        return MAPPER;
    }

    /**
     * @param o
     * @return
     */
    @LogDog
    public static String toJsonString(Object o) {
        try {
            return MAPPER.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
