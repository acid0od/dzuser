package net.odtel.usercheck.test;

import net.odtel.usercheck.domain.RadiusAttribute;
import net.odtel.usercheck.domain.RadiusOperation;
import net.odtel.usercheck.domain.RadiusUser;
import net.odtel.usercheck.domain.RadiusUserValue;
import net.odtel.usercheck.web.utils.StringUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;

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

    @Test
    public  void stringTest () {
        String s = StringUtils.setQuates("dkdlskk slssddlks dlskkdl ksk    klklklkl klk\"");

        System.out.println("[" + s + "]");
    }
}
