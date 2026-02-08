package com.thetestingacademy.tests.crud;

import com.thetestingacademy.base.BaseTest;
import com.thetestingacademy.endpoints.APIConstants;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class TestCreateToken extends BaseTest {

    @Test(groups = "reg",priority = 1)
    @Owner("Manav Chaudhary")
    @Description("TC - Create Token and verify")
    public void testTokenPOST()
    {
        requestSpecification.basePath(APIConstants.AUTH_URL);
        response = RestAssured.given(requestSpecification).body(payloadManager.setAuthPayload()).when().post();

        String token=payloadManager.getTokenFromJSON(response.asString());
        System.out.println(token);
        assertActions.verifyStringKeyNotNull(token);
    }

    @Test(groups = "reg", priority=1)
    @Owner("Manav Chaudhary")
    @Description("Verify invalid token")
    public void testTokenPOST_NEGATIVE()
    {
        requestSpecification.basePath(APIConstants.AUTH_URL);
        response = RestAssured.given(requestSpecification).body("{}").when().post();

        String invalid_reason=payloadManager.invalidTokenResponse(response.asString());
        System.out.println(invalid_reason);

        assertActions.verifyStringKey(invalid_reason,"Bad credentials");

    }

}
