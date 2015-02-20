package net.odtel.usercheck.test;

import com.sun.org.apache.bcel.internal.generic.LUSHR;
import net.odtel.usercheck.domain.RadiusAttribute;
import net.odtel.usercheck.domain.RadiusOperation;
import net.odtel.usercheck.domain.RadiusUser;
import net.odtel.usercheck.domain.RadiusUserValue;
import net.odtel.usercheck.web.utils.StringUtils;
import org.hibernate.sql.ordering.antlr.OrderingSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.testng.Assert.assertEquals;

@Test
public class RadiusUserModelTest {

    private RadiusUser radiusUser;
    private RadiusUserValue radiusUserValue;

    @BeforeMethod
    private void init() {
        radiusUser = new RadiusUser();
        radiusUser.setPassword("password");
        radiusUser.setPasswordType(RadiusAttribute.CLEARTEXT_PASSWORD);
        radiusUser.setUsername("user");

        radiusUserValue = new RadiusUserValue("user", RadiusAttribute.MIKROTIK_RATE_LIMIT, RadiusOperation.ADD_NEW_VALUE, "\"1M 3M 768k 128 8 1M\"");
        radiusUser.setRadiusUserValues(Collections.singletonList(radiusUserValue));

    }

    @Test
    public void simpleRadiusUserModelTest() {
        assertEquals(radiusUser.getPasswordType().getValue(), "Cleartext-Password");
        assertEquals(radiusUser.getPasswordOperation().getValue(), ":=");
    }

    @Test
    public void simpleRadiusUserValueTest() {
        RadiusUserValue value = radiusUser.getRadiusUserValues().iterator().next();

        assertEquals(value.getAttribute().getValue(), "Mikrotik-Rate-Limit");
        assertEquals(value.getOperator().getValue(), "+=");
        assertEquals(value.getValue(), "\"1M 3M 768k 128 8 1M\"");

    }

/*
    @Test
    public void simpleRadiusUserPageTest() {
        String s = "7";
        int limit = 23;

        int startIndex = (s == null) ? 1 : Integer.parseInt(s) + 1;

        int endIndex = startIndex + limit - 1 ;
        int step = (limit > 10) ? (int) Math.ceil(limit / 10) + 1: limit;

        System.out.println("+ [" + startIndex + "]" + endIndex + " step=" + step);

        for (long i = startIndex; i <= endIndex; i += step) {
            final int end = (int) Math.min(endIndex, i + step - 1);
            final int start = (int) i;

            System.out.println("+ [" + start + " " + end + "] " + (end - start + 1) );
        }
    }
*/

    @Test
    public void stringTest() {
        String s = StringUtils.setQuates("dkdlskk slssddlks dlskkdl ksk    klklklkl klk\"");
        System.out.println("[" + s + "]");
        System.out.print("[" + Math.floor(200.0082) + "]");
    }

    @Test
    public void unZipFile() {
        byte[] b = new byte[1024];
        Map<String, Integer> list = new HashMap<>();
        try {
            FileInputStream fim = new FileInputStream("/home/acid/2.zip");
            ZipInputStream zipInputStream = new ZipInputStream(fim);
            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                System.out.println("" + zipEntry.getName() + " size:" + zipEntry.getSize());

                byte[] am = null;
                while (zipInputStream.available() > 0) {

                    int size = zipInputStream.read(b, 0, 1024);
                    if (size > 0) {

                        am = testByte(list, am, b);

                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, Integer> map = sortByValue(list);

        for (String s : map.keySet()) {

            System.out.println("[" + s + "]" + map.get(s));
        }
    }

    public byte[] testByte(Map<String, Integer> list, byte[] am, byte[] a) {
        byte[] c;
        if (am != null) {
            int size = am.length + a.length;
            int j = 0;
            c = new byte[size];
            for (int i = 0; i < am.length; i++) {
                c[i] = am[i];
            }
            for (int i = 0; i < a.length; i++) {
                c[j + i] = a[i];
            }

        } else {
            int size = a.length;
            c = new byte[size];
            for (int i = 0; i < a.length; i++) {
                c[i] = a[i];
            }
        }

        byte[] b = null;
        boolean begin = false;
        boolean end = true;
        int j = 0;

        for (int i = 0; i < c.length - 1; i++) {
            if (isSpacedChar(c[i]) && !isSpacedChar(c[i + 1])) {
                if (end) {
                    begin = true;
                    end = false;
                    j = i + 1;
                }
            }
            if (!isSpacedChar(c[i]) && isSpacedChar(c[i + 1])) {
                if (begin) {
                    begin = false;
                    end = true;
                    String key = new String(c, j, i - j + 1).toLowerCase();
                    if (list.containsKey(key)) {
                        list.put(key, list.get(key) + 1);
                    } else {
                        list.put(key, 1);
                    }
                }

            }
        }

        if (begin) {
            b = new byte[c.length - j];
            for (int i = 0; i < b.length; i++) {
                b[i] = c[j + i];
            }
        }

        Math.random();
        return b;

    }

    private boolean isSpacedChar(byte c) {
        return c == ' ' || c == '\n' || c == '\t' || c == '\r' || c == '\0' || c == '.' || c == ':' || c == '}' || c == '{'
                || c == ')' || c == '(' || c == '"' || c == ';' || c == '/' || c == '@' || c == '?' || c == '<' || c == '>'
                || c == '-' || c == ',' || c == '$' || c == '=' || c == '!' || c == '*' || c == '+' || c == '&';
    }

    private List<String> getStringArray() {
        List<String> list = new ArrayList<>();

        return list;
    }

    private static Map sortByValue(Map map) {
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getValue())
                        .compareTo(((Map.Entry) (o2)).getValue());
            }
        });

        Map result = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            Map.Entry entry = (Map.Entry) it.next();
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

}
