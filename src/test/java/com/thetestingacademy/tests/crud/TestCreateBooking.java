package com.thetestingacademy.tests.crud;

import com.thetestingacademy.base.BaseTest;
import com.thetestingacademy.endpoints.APIConstants;
import com.thetestingacademy.pojos.responsePOJO.BookingResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class TestCreateBooking extends BaseTest {

    @Test(groups = "reg",priority = 1)
    @Owner("Manav Chaudhary")
    @Description("TC#1 - Verify the booking can be created")
    public void testCreateBookingPOST_Positivie()
    {
        //setup will first and making the request

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given(requestSpecification).body(payloadManager.createPayloadBookingAsString()).log().all()
                .when().post();

        //Extraction
        BookingResponse bookingResponse = payloadManager.bookingResponsejava(response.asString());

        //verification and validation via the AssertJ,TestNG part
        assertActions.verifyStatusCode(response,200);
        assertActions.verifyStringKeyNotNull(bookingResponse.getBookingid());
        assertActions.verifyStringKey(bookingResponse.getBooking().getFirstname(),"Pramod");


    }

    @Test(groups = "reg",priority = 1)
    @Owner("Manav Chaudhary")
    @Description("TC#2 - Verify that the Booking can't be Created, When Payload is null")
    public void testCreateBookingPOST_Negative()
    {

    }

    @Test(groups="reg",priority = 1)
    @Owner("Manav Chaudhary")
    @Description("TC#3 - Verify that the Booking can be Created, When Payload is CHINESE")
    public void testCreateBookingPOST_POSITIVE_CHINESE()
    {

    }




}
