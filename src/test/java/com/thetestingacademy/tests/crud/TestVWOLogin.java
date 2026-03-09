package com.thetestingacademy.tests.crud;

import com.thetestingacademy.base.BaseTest;
import com.thetestingacademy.endpoints.APIConstants;
import com.thetestingacademy.pojos.responsePOJO.LoginResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.annotations.Test;


public class TestVWOLogin extends BaseTest {

    @Test
    @Description("TC#1 - Test to verify user is able to do Login in VWO")
    @Owner("Manav Chaudhary")
    public void test_VWO_Login_Positive()
    {
        requestSpecification.baseUri(APIConstants.APP_VWO_URL);
        response= RestAssured.given(requestSpecification).body(payloadManager.setLoginData()).log().all().when().post();

        LoginResponse loginResponse =payloadManager.getLoginData(response.asString());
        assertActions.verifyStatusCode(response,200);

        System.out.println(loginResponse.getAccountId());

        assertActions.verifyStringKeyNotNull(loginResponse.getAccountId());
        assertActions.verifyStringKey(loginResponse.getName(),"Manav Chaudhary");



    }


}
