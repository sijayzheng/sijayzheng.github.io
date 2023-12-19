package sijay.zheng.common;

import sijay.zheng.common.util.FileUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @author sijay
 * @desc Main
 * @date 2023/12/5 16:44
 */
public class Main {
    public static void main(String[] args) {
        try (FileOutputStream fos = new FileOutputStream("C:\\Users\\sijay\\home\\code\\vscode\\js.js")) {
            String path = "C:\\Users\\sijay\\home\\code\\corpCode\\grd1300\\code\\snowy-master\\snowy-plugin\\snowy-plugin-biz\\src\\main\\java\\vip\\xiaonuo\\biz\\modular";
            for (File file : FileUtil.listFile(path)) {
                String absolutePath = file.getAbsolutePath();
                if (absolutePath.contains("\\entity\\")) {
                    System.out.println(absolutePath);
                    try (FileInputStream fis = new FileInputStream(absolutePath)) {
                        fos.write(fis.readAllBytes());
                        fos.flush();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
