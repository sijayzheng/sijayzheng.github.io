/*
 * Ownership belongs to Sijay Zheng
 */

package sijay.zheng.z.app;

import sijay.zheng.z.common.constant.CommConstant;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author zhengshijie
 * @date 2023/7/13 9:49
 */
public class Main {
    public static void main(String[] args) throws URISyntaxException, InterruptedException {
        String[] urls = {
                "https://list.jd.com/listNew.php?cat=737%2C794%2C798&qrst=1&psort=2&ev=244_216715%5E&psort=1&page=1&s=1&click=0",
                "https://list.jd.com/listNew.php?cat=737%2C794%2C798&qrst=1&psort=2&ev=244_216715%5E&psort=1&page=2&s=31&click=0",
                "https://list.jd.com/listNew.php?cat=737%2C794%2C798&qrst=1&psort=2&ev=244_216715%5E&psort=1&page=3&s=61&click=0",
                "https://list.jd.com/listNew.php?cat=737%2C794%2C798&qrst=1&psort=2&ev=244_216715%5E&psort=1&page=4&s=91&click=0",
                "https://list.jd.com/listNew.php?cat=737%2C794%2C798&qrst=1&psort=2&ev=244_216715%5E&psort=1&page=5&s=121&click=0",
                "https://list.jd.com/listNew.php?cat=737%2C794%2C798&qrst=1&psort=2&ev=244_216715%5E&psort=1&page=6&s=151&click=0",
                "https://list.jd.com/listNew.php?cat=737%2C794%2C798&qrst=1&psort=2&ev=244_216715%5E&psort=1&page=7&s=181&click=0",
                "https://list.jd.com/listNew.php?cat=737%2C794%2C798&qrst=1&psort=2&ev=244_216715%5E&psort=1&page=8&s=211&click=0",
                "https://list.jd.com/listNew.php?cat=737%2C794%2C798&qrst=1&psort=2&ev=244_216715%5E&psort=1&page=9&s=241&click=0",
                "https://list.jd.com/listNew.php?cat=737%2C794%2C798&qrst=1&psort=2&ev=244_216715%5E&psort=1&page=10&s=271&click=0",
                "https://list.jd.com/listNew.php?cat=737%2C794%2C798&qrst=1&psort=2&ev=244_216715%5E&psort=1&page=11&s=301&click=0",
                "https://list.jd.com/listNew.php?cat=737%2C794%2C798&qrst=1&psort=2&ev=244_216715%5E&psort=1&page=12&s=331&click=0",
                "https://list.jd.com/listNew.php?cat=737%2C794%2C798&qrst=1&psort=2&ev=244_216715%5E&psort=1&page=13&s=361&click=0",
                "https://list.jd.com/listNew.php?cat=737%2C794%2C798&qrst=1&psort=2&ev=244_216715%5E&psort=1&page=14&s=391&click=0",
                "https://list.jd.com/listNew.php?cat=737%2C794%2C798&qrst=1&psort=2&ev=244_216715%5E&psort=1&page=15&s=421&click=0",
                "https://list.jd.com/listNew.php?cat=737%2C794%2C798&qrst=1&psort=2&ev=244_216715%5E&psort=1&page=16&s=451&click=0",
                "https://list.jd.com/listNew.php?cat=737%2C794%2C798&qrst=1&psort=2&ev=244_216715%5E&psort=1&page=17&s=481&click=0",
                "https://list.jd.com/listNew.php?cat=737%2C794%2C798&qrst=1&psort=2&ev=244_216715%5E&psort=1&page=18&s=511&click=0",
                "https://list.jd.com/listNew.php?cat=737%2C794%2C798&qrst=1&psort=2&ev=244_216715%5E&psort=1&page=19&s=541&click=0",
                "https://list.jd.com/listNew.php?cat=737%2C794%2C798&qrst=1&psort=2&ev=244_216715%5E&psort=1&page=20&s=571&click=0",
                "https://list.jd.com/listNew.php?cat=737%2C794%2C798&qrst=1&psort=2&ev=244_216715%5E&psort=1&page=21&s=601&click=0",
                "https://list.jd.com/listNew.php?cat=737%2C794%2C798&qrst=1&psort=2&ev=244_216715%5E&psort=1&page=22&s=631&click=0",
                "https://list.jd.com/listNew.php?cat=737%2C794%2C798&qrst=1&psort=2&ev=244_216715%5E&psort=1&page=23&s=661&click=0",
                "https://list.jd.com/listNew.php?cat=737%2C794%2C798&qrst=1&psort=2&ev=244_216715%5E&psort=1&page=24&s=691&click=0",
                "https://list.jd.com/listNew.php?cat=737%2C794%2C798&qrst=1&psort=2&ev=244_216715%5E&psort=1&page=25&s=721&click=0",
                "https://list.jd.com/listNew.php?cat=737%2C794%2C798&qrst=1&psort=2&ev=244_216715%5E&psort=1&page=26&s=751&click=0",
                "https://list.jd.com/listNew.php?cat=737%2C794%2C798&qrst=1&psort=2&ev=244_216715%5E&psort=1&page=27&s=781&click=0",
                "https://list.jd.com/listNew.php?cat=737%2C794%2C798&qrst=1&psort=2&ev=244_216715%5E&psort=1&page=28&s=811&click=0",
                "https://list.jd.com/listNew.php?cat=737%2C794%2C798&qrst=1&psort=2&ev=244_216715%5E&psort=1&page=29&s=841&click=0",
                "https://list.jd.com/listNew.php?cat=737%2C794%2C798&qrst=1&psort=2&ev=244_216715%5E&psort=1&page=30&s=871&click=0",
                "https://list.jd.com/listNew.php?cat=737%2C794%2C798&qrst=1&psort=2&ev=244_216715%5E&psort=1&page=31&s=901&click=0",
                "https://list.jd.com/listNew.php?cat=737%2C794%2C798&qrst=1&psort=2&ev=244_216715%5E&psort=1&page=32&s=931&click=0",
                "https://list.jd.com/listNew.php?cat=737%2C794%2C798&qrst=1&psort=2&ev=244_216715%5E&psort=1&page=33&s=961&click=0",
                "https://list.jd.com/listNew.php?cat=737%2C794%2C798&qrst=1&psort=2&ev=244_216715%5E&psort=1&page=34&s=991&click=0",
                "https://list.jd.com/listNew.php?cat=737%2C794%2C798&qrst=1&psort=2&ev=244_216715%5E&psort=1&page=35&s=1021&click=0",
                "https://list.jd.com/listNew.php?cat=737%2C794%2C798&qrst=1&psort=2&ev=244_216715%5E&psort=1&page=36&s=1051&click=0",
                "https://list.jd.com/listNew.php?cat=737%2C794%2C798&qrst=1&psort=2&ev=244_216715%5E&psort=1&page=37&s=1081&click=0",
                "https://list.jd.com/listNew.php?cat=737%2C794%2C798&qrst=1&psort=2&ev=244_216715%5E&psort=1&page=38&s=1111&click=0",
                "https://list.jd.com/listNew.php?cat=737%2C794%2C798&qrst=1&psort=2&ev=244_216715%5E&psort=1&page=39&s=1141&click=0",
                "https://list.jd.com/listNew.php?cat=737%2C794%2C798&qrst=1&psort=2&ev=244_216715%5E&psort=1&page=40&s=1171&click=0",
                "https://list.jd.com/listNew.php?cat=737%2C794%2C798&qrst=1&psort=2&ev=244_216715%5E&psort=1&page=41&s=1201&click=0",
                "https://list.jd.com/listNew.php?cat=737%2C794%2C798&qrst=1&psort=2&ev=244_216715%5E&psort=1&page=42&s=1231&click=0",
                "https://list.jd.com/listNew.php?cat=737%2C794%2C798&qrst=1&psort=2&ev=244_216715%5E&psort=1&page=43&s=1261&click=0",
                "https://list.jd.com/listNew.php?cat=737%2C794%2C798&qrst=1&psort=2&ev=244_216715%5E&psort=1&page=44&s=1291&click=0",
                "https://list.jd.com/listNew.php?cat=737%2C794%2C798&qrst=1&psort=2&ev=244_216715%5E&psort=1&page=45&s=1321&click=0",
                "https://list.jd.com/listNew.php?cat=737%2C794%2C798&qrst=1&psort=2&ev=244_216715%5E&psort=1&page=46&s=1351&click=0",
        };
        StringBuilder stringBuilder = new StringBuilder();
        for (String url : urls) {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest.Builder builder = HttpRequest.newBuilder()
                    .GET()
                    .version(HttpClient.Version.HTTP_1_1)
                    .uri(new URI(url));
            HashMap<String, String> headers = new HashMap<>();
            headers.put(CommConstant.CONTENT_TYPE, "text/html;charset=utf-8");
            headers.put("Cookie", "__jdu=1676216928003208189026; shshshfp=55bad9ef859a55f1d1a232e3c8ec5eb6; shshshfpa=2e7948a8-7cdf-6ac6-71cd-eb5586b598fd-1676219356; shshshfpx=2e7948a8-7cdf-6ac6-71cd-eb5586b598fd-1676219356; shshshfpb=juRwudJ3B4ldcy0wzevQdjA; __jdv=76161171|cn.bing.com|-|referral|-|1689225983398; areaId=13; ipLoc-djd=13-1000-0-0; PCSYCityID=CN_370000_370100_0; __jdc=122270672; jsavif=0; jsavif=0; __jda=122270672.1676216928003208189026.1676216928.1689225983.1689228617.8; xapieid=jdd03B3RVTEMAMIZ257TJKFFJMECK32IINJT5RMEGF6WGRPL2ITMFVO53IUEG2ZGOVNU66FGTIMOQ36IIB46SX72KLA24MIAAAAMJJW7MA5QAAAAACUDZWMFMOBLAPEX; 3AB9D23F7A4B3CSS=jdd03B3RVTEMAMIZ257TJKFFJMECK32IINJT5RMEGF6WGRPL2ITMFVO53IUEG2ZGOVNU66FGTIMOQ36IIB46SX72KLA24MIAAAAMJJXPSO6QAAAAACXTKXAVZCPOQCIX; __jdb=122270672.2.1676216928003208189026|8.1689228617; 3AB9D23F7A4B3C9B=B3RVTEMAMIZ257TJKFFJMECK32IINJT5RMEGF6WGRPL2ITMFVO53IUEG2ZGOVNU66FGTIMOQ36IIB46SX72KLA24MI");

            for (Map.Entry<String, String> entry : headers.entrySet()) {
                builder.header(entry.getKey(), entry.getValue());
            }
            client.sendAsync(builder.build(), HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body).thenAccept(stringBuilder::append);
            Thread.sleep(3000);
        }

        try (FileOutputStream fos = new FileOutputStream("C:\\Users\\sijay\\Desktop\\test.txt")) {
            fos.write(stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
