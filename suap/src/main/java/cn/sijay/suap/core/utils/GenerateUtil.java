package cn.sijay.suap.core.utils;

import cn.sijay.suap.core.enums.GenderType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.regex.Pattern;

/**
 * <strong>GenerateUtil</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024-07-18
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GenerateUtil {

    private static final Pattern PHONE_NO_PATTERN = Pattern.compile("^1[34578]\\d{9}$");
    private static final Pattern NUMBER_PATTERN = Pattern.compile("^\\d{16,19}$");
    private static final String[] PHONE_NO_HEAD = {"130", "131", "132", "133", "134", "135", "136", "137", "138", "139", "145", "147", "150", "151", "152", "153", "156", "157", "158", "159", "170", "176", "177", "178", "180", "181", "182", "183", "184", "185", "186", "187", "188", "189"};
    private static final int PHONE_NO_MAX = 99999999;
    /**
     * 15位数身份证正则表达式
     */
    private static final Pattern ID_NO_SHOT_PATTERN = Pattern.compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([012]\\d)|3[0-1])\\d{3}$");
    /**
     * 18位数身份证正则表达式
     */
    private static final Pattern ID_NO_PATTERN = Pattern.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([012]\\d)|3[0-1])((\\d{4})|\\d{3}[xX])$");

    /**
     * 生成手机号
     *
     * @return 手机号
     */

    public static String generatorPhoneNo() {
        return PHONE_NO_HEAD[Double.valueOf((Math.random() * PHONE_NO_HEAD.length) + 1)
                                   .intValue()] + String.format("%09d", Math.round((Math.random() * PHONE_NO_MAX)));
    }

    /**
     * 生成指定数量的手机号
     *
     * @param number 数量
     * @return 手机号
     */

    public static List<String> generatorPhoneNo(int number) {
        List<String> noList = new ArrayList<>(number);
        for (int i = 0; i < number; i++) {
            noList.add(generatorPhoneNo());
        }
        return noList;
    }

    /**
     * 校验
     *
     * @param phoneNumber phone Number
     * @return 校验结果
     */

    public static boolean checkMobile(String phoneNumber) {
        return PHONE_NO_PATTERN.matcher(phoneNumber)
                               .matches();
    }

    /**
     * 生成身份证号
     **/
    public static String generateCardId(String county, String birthday, GenderType genderType) {
        double rand = Math.floor(100 + Math.random() * (999 - 100));
        if (genderType == GenderType.MALE) {
            if (0 == rand % 2) {
                rand += 1;
            }
        } else if (genderType == GenderType.FEMALE) {
            if (0 != rand % 2) {
                rand += 1;
            }
        }
        String idNo = county + birthday.replace("-", "") + rand;
        return idNo + getVerifyCode(idNo);
    }

    /**
     * 获得身份证校验位
     **/

    public static String getVerifyCode(String idNo) {
        String[] verifyCodes = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
        Integer[] wi = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
        int tmp = 0;
        for (int i = 0; i < wi.length; i++) {
            tmp += idNo.charAt(i) * wi[i];
        }
        int modValue = tmp % 11;
        return verifyCodes[modValue];
    }

    /**
     * 检查输入的身份证号格式是否正确
     * 输入:
     * str 字符串
     */

    public static boolean checkCard(String idNo) {
        boolean res = ID_NO_PATTERN.matcher(idNo)
                                   .matches() || ID_NO_SHOT_PATTERN.matcher(idNo)
                                                                   .matches();
        if (res) {
            log.error("证件号码格式错误!");
        } else {
            log.info("正确格式身份证号码");
        }
        return res;
    }

    /**
     * 统一社会信用代码
     */

    public static boolean isCreditCode(String creditCode) {
        creditCode = creditCode.toUpperCase();
        if (creditCode.length() != 18) {
            log.error("信用代码证必须为18位!");
            return false;
        }
        int sum = 0;
        String[] arr = creditCode.split("");
        for (int i = 0; i < arr.length - 1; i++) {
            String c = arr[i];
            //获得代码对应数值
            int a = getMapC(c);
            //获得当前位数权重
            int b = getMapW(i + 1);
            sum += a * b;
        }
        int mod = sum % 31;
        //计算校验码
        String res = getMapR((31 - mod));
        log.info(res);
        log.info(arr[arr.length - 1]);
        if (!arr[arr.length - 1].equals(res)) {
            log.error("请输入正确的信用代码证!");
            return false;
        }
        log.info("校验通过!");
        return true;
    }

    public static int getMapC(String pa) {
        return switch (pa) {
            case "1" -> 1;
            case "2" -> 2;
            case "3" -> 3;
            case "4" -> 4;
            case "5" -> 5;
            case "6" -> 6;
            case "7" -> 7;
            case "8" -> 8;
            case "9" -> 9;
            case "A" -> 10;
            case "B" -> 11;
            case "C" -> 12;
            case "D" -> 13;
            case "E" -> 14;
            case "F" -> 15;
            case "G" -> 16;
            case "H" -> 17;
            case "J" -> 18;
            case "K" -> 19;
            case "L" -> 20;
            case "M" -> 21;
            case "N" -> 22;
            case "P" -> 23;
            case "Q" -> 24;
            case "R" -> 25;
            case "T" -> 26;
            case "U" -> 27;
            case "W" -> 28;
            case "X" -> 29;
            case "Y" -> 30;
            default -> 0;
        };
    }

    public static int getMapW(Integer pa) {
        return switch (pa) {
            case 1 -> 1;
            case 2 -> 3;
            case 3 -> 9;
            case 4 -> 27;
            case 5 -> 19;
            case 6 -> 26;
            case 7 -> 16;
            case 8 -> 17;
            case 9 -> 20;
            case 10 -> 29;
            case 11 -> 25;
            case 12 -> 13;
            case 13 -> 8;
            case 14 -> 24;
            case 15 -> 10;
            case 16 -> 30;
            case 17 -> 28;
            default -> 0;
        };
    }

    public static String getMapR(int pa) {
        return switch (pa) {
            case 0, 31 -> "0";
            case 1 -> "1";
            case 2 -> "2";
            case 3 -> "3";
            case 4 -> "4";
            case 5 -> "5";
            case 6 -> "6";
            case 7 -> "7";
            case 8 -> "8";
            case 9 -> "9";
            case 10 -> "A";
            case 11 -> "B";
            case 12 -> "C";
            case 13 -> "D";
            case 14 -> "E";
            case 15 -> "F";
            case 16 -> "G";
            case 17 -> "H";
            case 18 -> "J";
            case 19 -> "K";
            case 20 -> "L";
            case 21 -> "M";
            case 22 -> "N";
            case 23 -> "P";
            case 24 -> "Q";
            case 25 -> "R";
            case 26 -> "T";
            case 27 -> "U";
            case 28 -> "W";
            case 29 -> "X";
            case 30 -> "Y";
            default -> "";
        };
    }

    /**
     * 统一社会信用代码生成
     */
    public static String generatorCreditCode() {
        return generatorCreditCode("9");
    }

    public static String generatorCreditCode(String regOrgCode) {
        String regOrg = getRegOrgCode(regOrgCode);
        //默认企业
        String orgType = "1";
        String[] areas = {"110000", "110101", "110102", "110103", "110104", "110105", "110106", "110107", "110108", "110109", "110111", "110112", "110113", "110114", "110115", "110116", "110117", "110228", "110229", "120000", "120101", "120102", "120103", "120104", "120105", "120106", "120107", "120108", "120109", "120110", "120111", "120112", "120113", "120114", "120115", "120221", "120223", "120225", "130000", "130100", "130102", "130103", "130104", "130105", "130107", "130108", "130121", "130123", "130124", "130125", "130126", "130127", "130128", "130129", "130130", "130131", "130132", "130133", "130181", "130182", "130183", "130184", "130185", "130200", "130202", "130203", "130204", "130205", "130207", "130208", "130223", "130224", "130225", "130227", "130229", "130230", "130281", "130283", "130300", "130302", "130303", "130304", "130321", "130322", "130323", "130324", "130400", "130402", "130403", "130404", "130406", "130421", "130423", "130424", "130425", "130426", "130427", "130428", "130429", "130430"};
        String area = areas[Math.toIntExact(Math.round(Math.random() * areas.length))];
        double num = Math.round(((Math.random() * (99999999 - 10000000)) + 10000000));
        int[] ws = {3, 7, 9, 10, 5, 8, 4, 2};
        int sum = 0;
        for (int i = 0; i < 8; i++) {
            sum += (num + "").charAt(i) * ws[i];
        }
        int c9 = 11 - (sum % 11);
        String orgCode = String.valueOf(num);
        if (c9 == 11) {
            orgCode += "0";
        } else if (c9 == 10) {
            orgCode += "X";
        } else {
            orgCode += c9;
        }

        String code = regOrg + orgType + area + orgCode;
        sum = 0;
        String[] split = code.split("");
        for (int i = 0; i < 17; i++) {
            sum += getMapC(split[i]) * getMapW(i + 1);
        }
        int c18 = 31 - (sum % 31);
        return code + getMapR(c18 == 31 ? 0 : c18);
    }

    /**
     * 获得登记机构
     */
    public static String getRegOrgCode(String num) {
        return switch (num) {
            //机构编制
            case "1" -> "1";
            //外交
            case "2" -> "2";
            //教育
            case "3" -> "3";
            //公安
            case "4" -> "4";
            //民政
            case "5" -> "5";
            //司法
            case "6" -> "6";
            //交通运输
            case "7" -> "7";
            //文化
            case "8" -> "8";
            //工商
            case "9" -> "9";
            //旅游局
            case "10" -> "A";
            //宗教事务管理
            case "11" -> "B";
            //全国总工会
            case "12" -> "C";
            //人民解放军总后勤部
            case "13" -> "D";
            //省级人民政府
            case "14" -> "E";
            //地市级人民政府
            case "15" -> "F";
            //区县级人民政府
            case "16" -> "G";
            case "17" -> "Y";
            //其他
            default -> "";
        };
    }

    /**
     * 生成银行卡号
     */
    public static String generatorBankAccount() {
        //开头6位
        String[] strBin = {"10", "18", "30", "35", "37", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "58", "60", "62", "65", "68", "69", "84", "87", "88", "94", "95", "98", "99"};
        int num = Math.toIntExact(Math.round(Math.random() * 34));
        String start = strBin[num];
        double max = 999999999999999L;
        double min = 100000000000L;
        double end = Math.round(((Math.random() * (max - min)) + min));
        String first15Or18Num = start + end;
        List<String> newArr = List.of(first15Or18Num.split(""))
                                  .reversed();

        //奇数位*2的积 <9
        List<Integer> arrJiShu = new ArrayList<>();
        //奇数位*2的积 >9
        List<Integer> arrJiShu2 = new ArrayList<>();
        //偶数位数组
        List<Integer> arrOuShu = new ArrayList<>();
        for (int j = 0; j < newArr.size(); j++) {
            //奇数位
            if ((j + 1) % 2 == 1) {
                if (Integer.parseInt(newArr.get(j)) * 2 < 9) {
                    arrJiShu.add(Integer.parseInt(newArr.get(j)) * 2);
                } else {
                    arrJiShu2.add(Integer.parseInt(newArr.get(j)) * 2);
                }
                //偶数位
            } else {
                arrOuShu.add(Integer.valueOf(newArr.get(j)));
            }
        }
        //奇数位*2 >9 的分割之后的数组个位数
        List<Integer> jishuChild1 = new ArrayList<>();
        //奇数位*2 >9 的分割之后的数组十位数
        List<Integer> jishuChild2 = new ArrayList<>();
        for (Integer integer : arrJiShu2) {
            jishuChild1.add(integer % 10);
            jishuChild2.add(integer / 10);
        }
        return first15Or18Num + getLuhm(arrJiShu, arrOuShu, jishuChild1, jishuChild2);
        // console.warn(first15Or18Num +luhm);
    }

    private static int getLuhm(List<Integer> arrJiShu, List<Integer> arrOuShu, List<Integer> jishuChild1, List<Integer> jishuChild2) {
        //奇数位*2 < 9 的数组之和
        int sumJiShu = 0;
        //偶数位数组之和
        int sumOuShu = 0;
        //奇数位*2 >9 的分割之后的数组个位数之和
        int sumJiShuChild1 = 0;
        //奇数位*2 >9 的分割之后的数组十位数之和
        int sumJiShuChild2 = 0;
        int sumTotal;
        for (Integer integer : arrJiShu) {
            sumJiShu = sumJiShu + integer;
        }
        for (Integer integer : arrOuShu) {
            sumOuShu = sumOuShu + integer;
        }
        for (int p = 0; p < jishuChild1.size(); p++) {
            sumJiShuChild1 = sumJiShuChild1 + jishuChild1.get(p);
            sumJiShuChild2 = sumJiShuChild2 + jishuChild2.get(p);
        }
        //计算总和

        sumTotal = sumJiShu + sumOuShu + sumJiShuChild1 + sumJiShuChild2;
        //计算Luhm值

        int k = sumTotal % 10 == 0 ? 10 : sumTotal % 10;
        return 10 - k;
    }

    /**
     * 银行卡号Luhm校验
     * Luhm校验规则：16位银行卡号（19位通用）:
     * 1.将未带校验位的 15（或18）位卡号从右依次编号 1 到 15（18），位于奇数位号上的数字乘以 2。
     * 2.将奇位乘积的个十位全部相加，再加上所有偶数位上的数字。
     * .将加法和加上校验位能被 10 整除。
     */
    public static boolean luhmCheck(String bankno) {
        if (StringUtil.isBlank(bankno)) {
            log.error("请输入银行卡号!");
            return false;
        }
        if (bankno.length() < 16 || bankno.length() > 19) {
            log.error("银行卡号长度必须在16到19之间!");
            return false;
        }
        if (!NUMBER_PATTERN.matcher(bankno)
                           .matches()) {
            log.error("银行卡号必须全为数字!");
            return false;
        }
        //开头6位

        String strBin = "10,18,30,35,37,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,58,60,62,65,68,69,84,87,88,94,95,98,99";
        if (!strBin.contains(bankno.substring(0, 2))) {
            log.error("银行卡号开头6位不符合规范!");
            return false;
        }
        //取出最后一位（与luhm进行比较）
        String lastNum = bankno.substring(bankno.length() - 1);
        //前15或18位
        String first15Num = bankno.substring(0, bankno.length() - 1);
        //前15或18位倒序存进数组

        List<String> newArr = new ArrayList<>(List.of(first15Num.split("")));
        Collections.reverse(newArr);
        //奇数位*2的积 <9
        List<Integer> arrJiShu = new ArrayList<>();
        //奇数位*2的积 >9
        List<Integer> arrJiShu2 = new ArrayList<>();
        //偶数位数组
        List<Integer> arrOuShu = new ArrayList<>();
        for (int j = 0; j < newArr.size(); j++) {
            //奇数位
            if ((j + 1) % 2 == 1) {
                if (Integer.parseInt(newArr.get(j)) * 2 < 9) {
                    arrJiShu.add(Integer.parseInt(newArr.get(j)) * 2);
                } else {
                    arrJiShu2.add(Integer.parseInt(newArr.get(j)) * 2);
                }
                //偶数位
            } else {
                arrOuShu.add(Integer.valueOf(newArr.get(j)));
            }
        }
        //奇数位*2 >9 的分割之后的数组个位数
        List<Integer> jishuChild1 = new ArrayList<>();
        //奇数位*2 >9 的分割之后的数组十位数
        List<Integer> jishuChild2 = new ArrayList<>();
        for (Integer integer : arrJiShu2) {
            jishuChild1.add(integer % 10);
            jishuChild2.add(integer / 10);
        }
        int luhm = getLuhm(arrJiShu, arrOuShu, jishuChild1, jishuChild2);
        if (Objects.equals(lastNum, luhm + "")) {
            log.info("Luhm验证通过!");
            return true;
        } else {
            log.error("银行卡号不符合Luhm校验!");
            return false;
        }
    }

    public static String generateUUID() {
        return UUID.randomUUID()
                   .toString();
    }

    public static String generateSimpleUUID() {
        return UUID.randomUUID()
                   .toString()
                   .replaceAll("-", "");
    }
}
