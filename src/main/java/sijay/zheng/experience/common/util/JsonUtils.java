package sijay.zheng.experience.common.util;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.json.*;
import sijay.zheng.experience.common.annotation.*;

/**
 * @author sijay
 */
public class JsonUtils {
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
