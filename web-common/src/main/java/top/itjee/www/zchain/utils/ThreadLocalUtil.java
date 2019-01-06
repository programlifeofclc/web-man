package top.itjee.www.zchain.utils;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalUtil {

    public static ThreadLocal<Map<Object, Object>> threadLocal = new ThreadLocal<>();

    public static <T> void set(Object key, T t) {
        Map<Object, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<>();
            map.put(key, t);
            threadLocal.set(map);
        } else {
            map.put(key, t);
        }

    }

    public static <T> T get(Object key) {
        Map<Object, Object> map = threadLocal.get();
        if (map == null) {
            return (T) null;
        } else {
            return (T) map.get(key);
        }
    }

    public static void remove(Object key) {
        Map<Object, Object> map = threadLocal.get();
        if (map != null) {
            map.remove(key);
            if (map.isEmpty()) {
                threadLocal.remove();
            }
        }
    }

    public static void empty() {
        threadLocal.remove();
    }

}
