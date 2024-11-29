package cn.sijay.suap.core.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * <strong>Main</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
public class LoanUtil {
    public static void cacl(long invest, int year, double rate) {
        rate = 0.031; // 年利率
        int month = year * 12;
        BigDecimal perMonthPrincipalInterest = getPerMonthPrincipalInterest(invest, rate, month);
        System.out.println("等额本息---每月还款本息：" + perMonthPrincipalInterest);
        Map<Integer, BigDecimal> mapInterest = getPerMonthInterest(invest, rate, month);
        System.out.println("等额本息---每月还款利息：" + mapInterest);
        Map<Integer, BigDecimal> mapPrincipal = getPerMonthPrincipal(invest, rate, month);
        System.out.println("等额本息---每月还款本金：" + mapPrincipal);
        HashMap<Integer, BigDecimal> map = new HashMap<>(mapInterest.size());
        for (Map.Entry<Integer, BigDecimal> entry : mapInterest.entrySet()) {
            map.put(entry.getKey(), entry.getValue().add(mapPrincipal.get(entry.getKey())));
        }
        System.out.println("等额本息---每月还款：" + map);
        BigDecimal count = getInterestCount(invest, rate, month);
        System.out.println("等额本息---总利息：" + count);
    }

    /**
     * 每月偿还本金和利息
     * <p>
     * 公式：每月偿还本息=〔贷款本金×月利率×(1＋月利率)＾还款月数〕÷〔(1＋月利率)＾还款月数-1〕
     *
     * @param invest     总借款额（贷款本金,单位分）
     * @param yearRate   年利率
     * @param totalMonth 还款总月数
     * @return 每月偿还本金和利息(入1 单位分)
     */
    public static BigDecimal getPerMonthPrincipalInterest(long invest, double yearRate, int totalMonth) {
        double monthRate = yearRate / 12;
        double perMonthPrincipalInterest = invest * (monthRate * Math.pow(1 + monthRate, totalMonth)) / (Math.pow(1 + monthRate, totalMonth) - 1);
        return new BigDecimal(perMonthPrincipalInterest).setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * 等额本息的每月偿还利息
     * <p>
     * 公式：每月偿还利息=贷款本金×月利率×〔(1+月利率)^还款月数-(1+月利率)^(还款月序号-1)〕÷〔(1+月利率)^还款月数-1〕
     *
     * @param invest     总借款额（贷款本金,分）
     * @param yearRate   年利率
     * @param totalMonth 还款总月数
     * @return 每月偿还利息(入1 单位分)
     */
    public static Map<Integer, BigDecimal> getPerMonthInterest(long invest, double yearRate, int totalMonth) {
        Map<Integer, BigDecimal> map = new HashMap<>();
        double monthRate = yearRate / 12;
        double monthInterest;
        for (int i = 1; i < totalMonth + 1; i++) {
            double multiply = invest * monthRate;
            double sub = Math.pow(1 + monthRate, totalMonth) - Math.pow(1 + monthRate, i - 1);
            monthInterest = multiply * sub / (Math.pow(1 + monthRate, totalMonth) - 1);
            map.put(i, new BigDecimal(monthInterest).setScale(2, RoundingMode.HALF_UP));
        }
        return map;
    }

    /**
     * 等额本息的每月偿还本金（月还本息-月还利息）
     *
     * @param invest     总借款额（贷款本金,分）
     * @param yearRate   年利率
     * @param totalMonth 还款总月数
     * @return 每月偿还本金(取整舍 单位分)
     */
    public static Map<Integer, BigDecimal> getPerMonthPrincipal(long invest, double yearRate, int totalMonth) {
        double monthRate = yearRate / 12;
        double monthIncome = invest * monthRate * Math.pow(1 + monthRate, totalMonth)
            / (Math.pow(1 + monthRate, totalMonth) - 1);
        BigDecimal perMonthPrincipalInterest = new BigDecimal(monthIncome).setScale(2, RoundingMode.HALF_UP);

        Map<Integer, BigDecimal> mapPrincipal = new HashMap<>();
        double monthInterest;
        for (int i = 1; i < totalMonth + 1; i++) {
            double multiply = invest * monthRate;
            double sub = (Math.pow(1 + monthRate, totalMonth)) - (Math.pow(1 + monthRate, i - 1));
            monthInterest = multiply * sub / (Math.pow(1 + monthRate, totalMonth) - 1);
            BigDecimal monthInterestL = new BigDecimal(monthInterest).setScale(2, RoundingMode.HALF_UP);
            mapPrincipal.put(i, perMonthPrincipalInterest.subtract(monthInterestL));
        }
        return mapPrincipal;
    }

    /**
     * 等额本息的总利息
     *
     * @param invest     总借款额（贷款本金）
     * @param yearRate   年利率
     * @param totalMonth 还款总月数
     * @return 总利息 (单位分)
     */
    public static BigDecimal getInterestCount(long invest, double yearRate, int totalMonth) {
        BigDecimal count = BigDecimal.ZERO;
        for (Map.Entry<Integer, BigDecimal> entry : getPerMonthInterest(invest, yearRate, totalMonth).entrySet()) {
            count = count.add(entry.getValue());
        }
        return count;
    }

    public static void main11(String[] args) {
        String[] marr = {"700000", "750000", "800000"};
        String[] yarr = {"10", "15", "20"};
        for (String m : marr) {
            for (String y : yarr) {
                HttpRequest request = HttpRequest.newBuilder()
                                                 .header("Content-Type", "application/x-www-form-urlencoded")
                                                 .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36 Edg/117.0.2045.60")
                                                 .version(HttpClient.Version.HTTP_2)
                                                 .uri(URI.create("http://221.214.107.104/gjjservice/bianmin/11483.aspx"))
                                                 .timeout(Duration.ofMillis(5009))
                                                 .POST(HttpRequest.BodyPublishers.ofString("__VIEWSTATE=9mTjujFOtzUYgPefhzdhI%2BoSETDpnGxNOMfiMCm9rWDj1y1c%2F%2Bl0j0Pi3wnUMV6HbC3fHMCXehbZGnL4w0AXf7ao5CYiBhYZQ4gI770W9P%2FcI3B%2FwP5k39N4TELfWXHR%2BF1QCLUkkufXYby6vgCKsf6uIvE49U431rVra%2FtzhrM%2B1gJ%2FQot21nqn1%2BGu5shoFb6hGKfNZMD9T5o7iKy1Lg%3D%3D&__VIEWSTATEGENERATOR=E212515D&__VIEWSTATEENCRYPTED=&__EVENTVALIDATION=bUMrzjG4%2B5WKOhBT6WLc6Nzink47oK23LoKwTku%2BI%2BOhf8J7vCQisurUIYtMIW4yVIcZ18StSvW3pAWar4S1QS4pM%2BBpicGt%2F7mJh2jazE2JALmIzPx0q3O5FWPJpFqpsF%2ByjvvEbnxdPI4%2BghxBvY5b8TbaLkX2kxbHMkJ7JU3koT2BgQm2qcTsw9u3vXyxDrVYj6Jm1e3%2FOKDRy205FQsLjOmz5Oyc06VEg3BYaBKMTf52NpCugyZf4sUtIr1AdkiilFk%2FDyGZ35vmHhf79pdl5%2BcaY0As9AkhUXryYqam0kxfAza7F18gQHli2WVV%2BmitTS28JZZt8bvIEs%2FsBwguHmwsCdHSW3RIB0MCnlER4laNety0TcwYbskzYLNld8F%2FUuP%2Buoeku%2BX%2FKtct9ixuH3McFStkOaEy4jkg2ejy2X93S4rtBxj%2B2r7%2F5cvLA3kut4J47FiWOw1pNE7XtsZ1cZq%2FWm7EjRYY1zYt%2BXQdM2Q5RMdrTSWGgb1vTll1mkDj3Z6fuxoJjnvGBcXWpDHTlTUZEkSk043vjNaVGJylNiaB4u1CIbLOxZ7DWjosapKDbQvY%2FW7bIBo68wxw6YxS0C08XobvN8tCwyB8Fz8O5lbsQoYIsMZ3ZdhafNUi3sX4%2BOP1V2OdqdvmQzMZ2DyYtq80wc6GqTknI5N08kNGZ0W2yG1LvNDEcMyBusND3EZnD25ntZ04ZfLadzi7zP0NwU7XVMvvTf2UMftu1stpc9Hh4mGC4c0OORYI29Spfu%2BdBpuwywYBNuTOJjK1EiXPtgPwJ%2F1SWf6sZmI0AW66ZhnXXREJZrFeohp7nWM3RxSYkPYfJOyMcP8c1Y4WKg%3D%3D&tbJinE=" + m + "&ddlNian=" + y + "&DropDownList1=1&Button1=%E8%AE%A1%E7%AE%97"))
                                                 .build();
                try (HttpClient httpClient = HttpClient.newHttpClient()) {
                    HttpResponse<String> response =
                        httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                    System.out.println(response.body());
                    File file = new File("C:\\Users\\sijay\\Desktop\\" + m + "_" + y + ".html");
                    try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                        fileOutputStream.write(response.body().getBytes(StandardCharsets.UTF_8));
                    }
                    Thread.sleep(5 * 1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
