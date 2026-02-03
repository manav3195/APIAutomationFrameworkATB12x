package com.thetestingacademy.tests.sample;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestIntegrationSample {

    @Test(groups = "qa",priority = 1)
    @Owner("Manav Chaudhary")
    @Description("Test case 1 - verify create booking")
    public void test_createbooking()
    {
        Assert.assertTrue(true);
    }

    @Test(groups="qa",priority=2)
    @Owner("Manav Chaudhary")
    @Description("Test case 2 - verify booking")
    public void test_verifyBooking()
    {
        Assert.assertTrue(true);
    }

    @Test(groups="qa",priority=3)
    @Owner("Manav Chaudhary")
    @Description("Test case 3 - verify update booking")
    public void test_updateBooking()
    {
        Assert.assertTrue(true);
    }

    @Test(groups="qa",priority=4)
    @Owner("Manav Chaudhary")
    @Description("Test case 4 - verify delete booking")
    public void test_deleteBooking()
    {
        Assert.assertTrue(true);
    }

}
