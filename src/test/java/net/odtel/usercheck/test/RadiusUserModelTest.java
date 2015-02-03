package net.odtel.usercheck.test;

import net.odtel.usercheck.domain.RadiusAttribute;
import net.odtel.usercheck.domain.RadiusOperation;
import net.odtel.usercheck.domain.RadiusUser;
import net.odtel.usercheck.domain.RadiusUserValue;
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
        System.out.println("+ [" + RadiusOperation.getKey("==") + "]");
    }
}
