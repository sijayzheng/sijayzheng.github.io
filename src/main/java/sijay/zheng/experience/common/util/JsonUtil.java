package sijay.zheng.experience.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import sijay.zheng.experience.common.annotation.LogDog;

/**
 * @author sijay
 */
public class JsonUtil {
    static final JsonMapper MAPPER = new JsonMapper();

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