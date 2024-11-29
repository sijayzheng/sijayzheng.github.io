package cn.sijay.suap.core.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * <strong>CmdUtil</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CmdUtil {

    /**
     * 该方法封装了执行cmd的方法
     *
     * @param cmd CMD命令
     */
    public static List<String> execute(String... cmd) {
        List<String> result = new ArrayList<>();
        try {
            Process process = Runtime.getRuntime().exec(cmd);
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
