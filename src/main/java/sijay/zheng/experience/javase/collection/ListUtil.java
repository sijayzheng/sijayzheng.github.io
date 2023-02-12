package sijay.zheng.experience.javase.collection;

import java.util.*;
import java.util.stream.*;

/**
 * @author Sijay
 * @date 2022/1/19 22:21
 */
public class ListUtil {
    public static void main(String[] args) {
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("1", "a");
        map1.put("2", "b");
        HashMap<String, String> map2 = new HashMap<>();
        map2.put("1", "c");
        map2.put("2", "d");
        HashMap<String, String> map3 = new HashMap<>();
        map3.put("1", "e");
        map3.put("2", "f");
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        list.add(map1);
        list.add(map2);
        list.add(map3);

        list.stream()
                .collect(Collector.of(HashMap<String, String>::new, (map, element) -> map.put(element.get("1")
                                , element.get("2"))
                        , (m1, m2) -> {
                            m1.putAll(m2);
                            return m1;
                        }))
                .forEach((k, v) -> System.out.println(k + ":" + v));
    }
}