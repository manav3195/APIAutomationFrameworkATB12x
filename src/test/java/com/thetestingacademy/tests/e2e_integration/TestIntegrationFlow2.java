package com.thetestingacademy.tests.e2e_integration;

import com.thetestingacademy.asserts.AssertActions;
import com.thetestingacademy.base.BaseTest;
import com.thetestingacademy.endpoints.APIConstants;
import com.thetestingacademy.pojos.responsePOJO.BookingResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class TestIntegrationFlow2 extends BaseTest {

    // Create booking -> Delete it -> verify

    @Test
    @Owner("Manav Chaudhary")
    @Description("TC#1 - test to verify create booking")
    public void testCreateBooking(ITestContext iTestContext)
    {
        Integer bookingid;
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response= RestAssured.given(requestSpecification).contentType(ContentType.JSON)
                .body(payloadManager.createPayloadBookingAsString()).log().all().when().post();

        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(200);
        BookingResponse bookingResponse =payloadManager.bookingResponsejava(response.asString());
        assertActions.verifyStringKeyNotNull(bookingResponse.getBookingid());
        bookingid = bookingResponse.getBookingid();
        iTestContext.setAttribute("bookingid",bookingid);


    }

    @Test
    @Owner("Manav Chaudhary")
    @Description("TC#2 - test to verify user is able to delete booking")
    public void testDeleteBooking(ITestContext iTestContext)
    {
        Integer bookingid =(Integer) iTestContext.getAttribute("bookingid");
        String token = getToken();
        String basePathDELETE = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" +bookingid;
        requestSpecification.basePath(basePathDELETE);
        response = RestAssured.given(requestSpecification).contentType(ContentType.JSON)
                .cookie("token",token).when().delete();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(201);


    }





}
