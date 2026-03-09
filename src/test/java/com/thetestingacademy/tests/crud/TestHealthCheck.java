package com.thetestingacademy.tests.crud;

import com.thetestingacademy.base.BaseTest;
import com.thetestingacademy.endpoints.APIConstants;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.apache.commons.io.input.ReadAheadInputStream;
import org.testng.annotations.Test;

public class TestHealthCheck extends BaseTest {

    @Test
    @Owner("Manav Chaudhary")
    @Description("TC#3 Verify Health")
    public void testGETHealthCheck()
    {
        requestSpecification.basePath(APIConstants.PING_URL);
        response = RestAssured.given(requestSpecification).when().get();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(201);

        assertActions.verifyTrue(response.asString().contains("Created"));
    }

}
