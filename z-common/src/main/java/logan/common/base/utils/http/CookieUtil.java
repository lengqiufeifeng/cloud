package logan.common.base.utils.http;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie 工具类
 *
 * @author zhangpanfeng
 */
public class CookieUtil {
    public static final String DEFAULT_DOMAIN = ".activelife.com";
    public static final String DEFAULT_PATH = "/";

    /**
     * 根据Cookie名称获取Cookie
     *
     * @param request
     * @param name    Cookie名称
     * @return Cookie对象
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            int length = cookies.length;
            for (int i = 0; i < length; i++) {
                if (cookies[i].getName().equals(name)) {
                    return cookies[i];
                }
            }
        }

        return null;
    }

    /**
     * 根据Cookie名称获取Cookie的值
     *
     * @param request
     * @param name    Cookie名称
     * @return Cookie值
     */
    public static String getCookieValue(HttpServletRequest request, String name) {
        Cookie cookie = getCookie(request, name);

        return cookie == null ? null : cookie.getValue();
    }

    /**
     * 添加Cookie
     *
     * @param response
     * @param key       Cookie键
     * @param value     Cookie值
     * @param validDays Cookie有效天数，null表示永不失效
     * @param domain    Cookie domain， null表示使用默认domain ".activelife.com"
     * @param path      Cookie 路径， null表示使用默认路径 "/"
     */
    public static void addCookie(HttpServletResponse response, String key, String value, Integer validDays,
                                 String domain, String path) {
        Cookie cookie = new Cookie(key, value);
        if (validDays != null && validDays >= 0) {
            cookie.setMaxAge(validDays * 3600 * 24);
        }
        if (domain != null) {
            cookie.setDomain(domain);
        } else {
            cookie.setDomain(DEFAULT_DOMAIN);
        }
        if (path != null) {
            cookie.setPath(path);
        } else {
            cookie.setPath(DEFAULT_PATH);
        }

        response.addCookie(cookie);
    }

    /**
     * 删除Cookie
     *
     * @param response
     * @param key      Cookie键
     * @param path     Cookie路径， null表示使用默认路径 "/"
     */
    public static void deleteCookie(HttpServletResponse response, String key, String path) {
        Cookie cookie = new Cookie(key, "" );
        cookie.setMaxAge(0);
        if (path != null) {
            cookie.setPath(path);
        } else {
            cookie.setPath(DEFAULT_PATH);
        }
        response.addCookie(cookie);
    }
}
