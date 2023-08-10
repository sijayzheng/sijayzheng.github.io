/*
 * Ownership belongs to Sijay Zheng
 */

package sijay.zheng.z.common.util;

import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sijay
 * @date 2022/11/24 17:12
 */
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class CmdUtil {

    /**
     * 该方法封装了执行cmd的方法
     *
     * @param cmd      CMD命令
     * @param filePath 需要在哪个目录下执行
     */
    public static List<String> execute(String cmd, String filePath) {
        Process process;
        List<String> result = new ArrayList<>();
        try {
            if (filePath != null) {
                process = Runtime.getRuntime().exec(cmd, null, new File(filePath));
            } else {
                process = Runtime.getRuntime().exec(cmd);
            }
            BufferedReader bReader = new BufferedReader(new InputStreamReader(process.getInputStream(), "gbk"));
            String line;
            while ((line = bReader.readLine()) != null) {
                result.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
