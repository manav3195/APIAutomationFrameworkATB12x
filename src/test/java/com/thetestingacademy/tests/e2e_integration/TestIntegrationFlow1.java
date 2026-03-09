package com.thetestingacademy.tests.e2e_integration;

import com.thetestingacademy.base.BaseTest;
import com.thetestingacademy.endpoints.APIConstants;
import com.thetestingacademy.pojos.responsePOJO.BookingResponse;
import com.thetestingacademy.pojos.responsePOJO.GetBookingResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class TestIntegrationFlow1 extends BaseTest {


    //Test E2E scenario-1
    //1- Create Booking ->bookingID

    //2- Create Token- token
    //3-Verify create booking is working - GET Request to bookingID
    //4-Update the booking(bookingID,token) - Need to get token, bookingID from above request
    //5- Delete the booking- Need to get token,bookingID from above request

    @Test(groups="qa",priority = 1)
    @Owner("Manav Chaudhary")
    @Description("TC#1 - Verify user is able to create booking and get bookingID")
    public void testCreateBooking(ITestContext iTestContext)
    {
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response= RestAssured.given(requestSpecification).body(payloadManager.createPayloadBookingAsString())
                .log().all().when().post();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        BookingResponse bookingResponse = payloadManager.bookingResponsejava(response.asString());
        assertActions.verifyStringKey(bookingResponse.getBooking().getFirstname(),"Pramod");
        assertActions.verifyStringKeyNotNull(bookingResponse.getBookingid());

        Integer bookingid = bookingResponse.getBookingid();
        iTestContext.setAttribute("bookingid",bookingid);

    }

    @Test(groups = "qa", priority = 2)
    @Owner("Manav Chaudhary")
    @Description("TC#2 - Verify the booking by bookingID")
    public void testVerifyBookingID(ITestContext iTestContext)
    {
        System.out.println(iTestContext.getAttribute("bookingid"));
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");

        String basePathGet= APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+bookingid;
        System.out.println(basePathGet);

        requestSpecification.basePath(basePathGet);
        response = RestAssured.given(requestSpecification).when().get();
        validatableResponse= response.then().log().all();
        validatableResponse.statusCode(200);
        GetBookingResponse getBookingResponse = payloadManager.getResponseFromJSON(response.asString());
        assertActions.verifyStringKeyNotNull(getBookingResponse.getFirstname());


    }

    @Test(groups="qa", priority=3)
    @Owner("Manav Chaudhary")
    @Description("TC#3 - Verify user is able to update booking ")
    public void testUpdateBooking(ITestContext iTestContext)
    {
        Integer bookingID = (Integer) iTestContext.getAttribute("bookingid");
        String token = getToken();
        iTestContext.setAttribute("token",token);
        System.out.println(token);
        String basePathPUTPATCH = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingID;
        requestSpecification.basePath(basePathPUTPATCH);

        response = RestAssured.given(requestSpecification).contentType(ContentType.JSON).body(payloadManager.fullUpdatePayloadAsString())
                .cookie("token",token).log().all().when().put();
        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(200);

        GetBookingResponse getBookingResponse=payloadManager.getResponseFromJSON(response.asString());
        assertActions.verifyStringKey(getBookingResponse.getFirstname(),"Lucky");


    }


    @Test(groups = "qa", priority=4)
    @Owner("Manav Chaudhary")
    @Description("TC#4 - Delete Booking by id")
    public void testDeleteBookingByID(ITestContext iTestContext)
    {
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        String token = (String) iTestContext.getAttribute("token");

        String basePathDELETE = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;
        requestSpecification.basePath(basePathDELETE);
        response = RestAssured.given(requestSpecification).cookie("token",token).when().delete();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(201);



    }








}
