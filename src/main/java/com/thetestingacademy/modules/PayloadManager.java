package com.thetestingacademy.modules;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.thetestingacademy.pojos.requestPOJO.Booking;
import com.thetestingacademy.pojos.requestPOJO.Bookingdates;

public class PayloadManager {

    Gson gson;
    Faker faker;

    public String createPayloadBookingAsString()
    {
        Booking booking = new Booking();
        booking.setFirstname("Pramod");
        booking.setLastname("Dutta");
        booking.setTotalprice(112);
        booking.setDepositpaid(true);

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2018-01-01");
        bookingdates.setCheckout("2019-01-01");

        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");

        System.out.println(booking);

        //Java object to JSON
        gson = new Gson();
        String jsonBookingString = gson.toJson(booking);
        return jsonBookingString;

    }

    public String createPayloadBookingAsStringWrongBody()
    {
        Booking booking = new Booking();
        booking.setFirstname("会意; 會意");
        booking.setLastname("会意; 會意");
        booking.setTotalprice(112);
        booking.setDepositpaid(false);

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("5025-02-01");
        bookingdates.setCheckout("5025-02-01");

        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("会意; 會意");

        //Java object to JSON
        gson = new Gson();
        String jsonStringBooking = gson.toJson(booking);
        return jsonStringBooking;
    }

}
