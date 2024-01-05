package cn.zheng.sijay.j.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sijay
 * @desc CmdUtil
 * @date 2023/12/19 14:30
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
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
            if (StringUtil.isNotBlank(filePath)) {
                process = Runtime.getRuntime().exec(cmd, null, new File(filePath));
            } else {
                process = Runtime.getRuntime().exec(cmd);
            }
            BufferedReader bReader = new BufferedReader(new InputStreamReader(process.getInputStream(), FileUtil.CODE_GBK));
            String line;
            while ((line = bReader.readLine()) != null) {
                result.add(line);
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return result;
    }

    public static List<String> execute(String cmd) {
        Process process;
        List<String> result = new ArrayList<>();
        try {
            process = Runtime.getRuntime().exec(cmd);
            BufferedReader bReader = new BufferedReader(new InputStreamReader(process.getInputStream(), FileUtil.CODE_GBK));
            String line;
            while ((line = bReader.readLine()) != null) {
                result.add(line);
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return result;
    }

}
