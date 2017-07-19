package com.phelps.ps.com.autotest.utils;

import java.util.ArrayList;
import java.util.List;

public class VerificationStatus {

	public static boolean isStatusPass;

	public static boolean isTextFound;

	List<String> statusMessage;

	public VerificationStatus() {
		
		isStatusPass = true;

		isTextFound = true;
		statusMessage = new ArrayList<String>();
	}

	public void setStatusMessage(String message) {

		this.statusMessage.add(message);
	}

	public List<String> getStatusMessage() {

		return statusMessage;
	}
	//to make a method which returns all the failed strings getAllFailed texts

}
