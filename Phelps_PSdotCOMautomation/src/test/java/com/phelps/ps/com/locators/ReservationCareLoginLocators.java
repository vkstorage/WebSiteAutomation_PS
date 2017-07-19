package com.phelps.ps.com.locators;

public interface ReservationCareLoginLocators {

	// WC2
	String tbEmailAddressId = "email_address";
	String tbReservationNumberId = "reservation_number";
	String btnRservationCareLoginXpath = ".//*[@id='login_main']//label[@class='lblLoginButton']/input";
	String hlinkForgetReservationNumberLink = "Forgot your Reservation Number?";
	String lbChangeUnitSizeLiText = "Change Unit Size";
	String lbChangeLocationLiText = "Change Location";
	String lbChangeMoveInDateLiText = "Change Move-In Date";
	String lbCancelReservationLiText = "Cancel Reservation";
	String lbExpressCheckInLiText = "Express Check-In";
	String lbManageYourReservationSpanText="Manage Your Reservation";
	
	
	String hlinkHomeLink="Home";
	String lbValidEmailErrorDivText="A valid email address is required.";
	String lbValidReservationNumberErrorDivText="A valid reservation number is required.";
	String lbEmailReservationNotMatchErrorDivText="The email address and reservation number you have provided do not match.";
	
}
