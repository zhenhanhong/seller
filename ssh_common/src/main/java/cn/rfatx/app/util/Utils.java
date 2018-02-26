package cn.rfatx.app.util;


import cn.rfatx.api.common.DataTable;
import cn.rfatx.core.diva.security.utils.Digests;
import cn.rfatx.core.diva.utils.Encodes;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import javax.servlet.ServletRequest;
import java.io.*;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 解析xml的工具类
 */
public class Utils {
    //地球平均半径
    private static final double EARTH_RADIUS = 6378137;

    public static boolean isEmptyStr(String str) {
        if (str == null) {
            return true;
        }
        str = str.trim();
        return str.isEmpty();
    }

    public static Sort getSort(DataTable dt, ServletRequest request) {
        Sort sort = null;
        String iSortCol_0 = dt.getiSortCol_0();
        String sSortDir_0 = dt.getsSortDir_0();
        String isSort_ = "bSortable_" + iSortCol_0;
        String orderParamter = "mDataProp_" + iSortCol_0;
        boolean isSort = Boolean.parseBoolean(request.getParameter(isSort_));
        String orderName = null;
        if (isSort == true) {
            orderName = request.getParameter(orderParamter);
            if ((orderName != null) && (!orderName.equals("")) && (sSortDir_0 != null)) {
                if (sSortDir_0.equalsIgnoreCase("asc")) {
                    sort = new Sort(Direction.ASC, new String[]{orderName});
                } else if (sSortDir_0.equalsIgnoreCase("desc")) {
                    sort = new Sort(Direction.DESC, new String[]{orderName});
                }
                return sort;
            }
        }
        return null;
    }


    public static List removeDuplicate(List arlList) {
        if (isEmpityCollection(arlList)) {
            return new ArrayList();
        }
        HashSet h = new HashSet(arlList);
        arlList.clear();
        arlList.addAll(h);
        return arlList;
    }

    // first stage modify start
    public static List removeDuplicateHoldOnSort(List arlList) {
        Set set = new HashSet<>();
        List newList = new ArrayList<>();
        for (Iterator iter = arlList.iterator(); iter.hasNext(); ) {
            Object element = iter.next();
            if (set.add(element)) {
                newList.add(element);
            }
        }
        return newList;
    }
    // first stage modify end

    public static boolean isNotNull(Object obj) {

        if (obj != null) {

            return true;
        }

        return false;
    }


    public static boolean isEmpityCollection(Collection c) {

        if ((c == null) || (c.isEmpty())) {

            return true;
        }

        return false;
    }

    public static boolean isEmptyStr(Object s) {

        return (s == null) || (s.toString().trim().length() == 0);
    }


    public static boolean isEmptyString(Object s) {

        return (s == null) || (s.toString().trim().length() == 0) || (s.toString().trim().equalsIgnoreCase("null")) || (s.toString().trim().equalsIgnoreCase("<null>"));
    }


    public static String filterNullValue(String v) {

        return isEmptyString(v) ? "" : v;
    }


    public static Map<String, String> entryptPassword(String password) {

        Map<String, String> map = new HashMap();

        password = isEmptyString(password) ? "123456" : password;

        byte[] salt = Digests.generateSalt(8);

        byte[] hashPassword = Digests.sha1(password.getBytes(), salt, 1024);

        password = Encodes.encodeHex(hashPassword);

        map.put("password", password);

        map.put("enCodeSalt", Encodes.encodeHex(salt));

        return map;
    }

    public static String entryptPassword(String password, String salt) {

        password = isEmptyString(password) ? "123456" : password;


        byte[] hashPassword = Digests.sha1(password.getBytes(), Encodes.decodeHex(salt), 1024);

        password = Encodes.encodeHex(hashPassword);

        return password;
    }

    public static <T extends Object> void sortData(List<T> list, final String sortCol, final String order) {

        Collections.sort(list, new Comparator<T>() {
            @Override
            public int compare(T s1, T s2) {


                Method[] methods = s1.getClass().getDeclaredMethods();
                Object s1Value = getInvokeValue(s1, sortCol);
                Object s2Value = getInvokeValue(s2, sortCol);

                Double d1Value = null;
                Double d2Value = null;
                if (s1Value instanceof Double || s1Value instanceof Integer || s1Value instanceof Long) {
                    try {
                        d1Value = Double.valueOf(s1Value.toString());
                        d2Value = Double.valueOf(s2Value.toString());
                    } catch (Exception e) {

                    }
                }

                // 升序
                if (order.equalsIgnoreCase("asc")) {
                    if (s1Value == null && s2Value == null) {
                        return 0;
                    }

                    if (s1Value == null && s2Value != null) {
                        return -1;
                    }

                    if (s1Value != null && s2Value == null) {
                        return 1;
                    }

                    if (d1Value != null) {
                        if (d1Value.compareTo(d2Value) < 0) {
                            return -1;
                        } else if (d1Value.compareTo(d2Value) > 0) {
                            return 1;
                        }
                        return 0;
                    }

                    if (s1Value.toString().compareTo(s2Value.toString()) < 0) {
                        return -1;
                    } else if (s1Value.toString().compareTo(s2Value.toString()) > 0) {
                        return 1;
                    }
                    return 0;
                } else {
                    //降序
                    if (s1Value == null && s2Value == null) {
                        return 0;
                    }

                    if (s1Value == null && s2Value != null) {
                        return 1;
                    }

                    if (s1Value != null && s2Value == null) {
                        return -1;
                    }

                    if (d1Value != null) {
                        if (d1Value.compareTo(d2Value) < 0) {
                            return 1;
                        } else if (d1Value.compareTo(d2Value) > 0) {
                            return -1;
                        }
                        return 0;
                    }

                    if (s1Value.toString().compareTo(s2Value.toString()) < 0) {
                        return 1;
                    } else if (s1Value.toString().compareTo(s2Value.toString()) > 0) {
                        return -1;
                    }
                    return 0;
                }
            }
        });
    }

    private static <T extends Object> Object getInvokeValue(T obj, String property) {
        Method[] methods = obj.getClass().getDeclaredMethods();

        Object value = null;
        String getP = property;
        String leftP = "";
        if (property.contains(".")) {
            String[] sortColArr = property.split(Pattern.quote("."));
            getP = sortColArr[0];
            leftP = property.replaceFirst(getP + ".", "");
        }

        for (Method method : methods) {

            String methodName = method.getName();
            String propertyName = methodName.replaceFirst("get", "");
            if (propertyName.equalsIgnoreCase(getP) && methodName.startsWith("get")) {
                try {
                    value = method.invoke(obj, new Object[]{});
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        if (leftP.isEmpty() || value == null) {
            return value;
        }

        return getInvokeValue(value, leftP);
    }

    public static double getDistance(double lat1, double lng1, double lat2,
                                     double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
        s = s * 1000;
        return s;
    }

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    public static String readfile(String filePath) {
        File file = new File(filePath);
        InputStream input = null;
        try {
            input = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringBuffer buffer = new StringBuffer();
        byte[] bytes = new byte[1024];
        try {
            for (int n; (n = input.read(bytes)) != -1; ) {
                buffer.append(new String(bytes, 0, n, "UTF-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(buffer);
        return buffer.toString();
    }


}

