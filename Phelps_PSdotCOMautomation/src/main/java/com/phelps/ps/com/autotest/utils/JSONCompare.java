package com.phelps.ps.com.autotest.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Reporter;

/**
 * JSON comparison utilities.
 * 
 * @author amodak
 * 
 */
public class JSONCompare {

	/**
	 * Compares two JSON string either with values or ignoring those. If
	 * onlyStructure flag is true it compares only for keys name, key hierarchy
	 * and data type of the values in both JSON.
	 * 
	 * @param expectedString
	 *          - Expected JSON string
	 * @param actualString
	 *          - Actual JOSN string
	 * @param onlyStructure
	 *          - ignore values if set to true.
	 * @return - True if both are same, false otherwise.
	 * @throws JSONException
	 */
	public static boolean equalityJSONString(String expectedString, String actualString, boolean onlyStructure) throws JSONException {
		JSONObject expectedJSON;
		JSONObject actualJSON;

		if (!expectedString.isEmpty() && !actualString.isEmpty()) {
			try {
				expectedJSON = new JSONObject(expectedString);
			} catch (JSONException e) {
				Reporter.log("Unable to process JSON by expected string:" + expectedString);
				e.printStackTrace();
				throw new AssertionError();
			}
			try {
				actualJSON = new JSONObject(actualString);
			} catch (JSONException e) {
				Reporter.log("Unable to process JSON by actual string:" + actualString);
				e.printStackTrace();
				throw new AssertionError();
			}
			return equalityJSON(expectedJSON, actualJSON, onlyStructure);
		} else {
			if (expectedString.isEmpty()) {
				Reporter.log("Expected JSON string is empty");
			} else {
				Reporter.log("Actual JSON String id empty");
			}
			return false;
		}
	}

	/**
	 * Compares two JSON either with values or ignoring those. If onlyStructure
	 * flag is true it compares only for keys name, key hierarchy and data type of
	 * the values in both JSON.
	 * 
	 * @param expectedJSONObj
	 *          - Expected JSON
	 * @param actualJSONObj
	 *          - Actual JOSN
	 * @param onlyStructure
	 *          - ignore values if set to true.
	 * @return - True if both are same, false otherwise.
	 * @throws JSONException
	 *           - JSON exception
	 */
	public static boolean equalityJSON(Object expectedJSONObj, Object actualJSONObj, boolean onlyStructure) {
		/*
		 * If both are instance of JSONObject
		 */
		if (expectedJSONObj instanceof JSONObject && actualJSONObj instanceof JSONObject) {
			/*
			 * Convert to JSONOnject
			 */
			JSONObject expectedJSON = (JSONObject) expectedJSONObj;
			JSONObject actualJSON = (JSONObject) actualJSONObj;
			/*
			 * First check if expected has key then both have same number of keys
			 */
			if (expectedJSON.length() > 0) {
				if (expectedJSON.length() != actualJSON.length()) {
					return false;
				} else {
					/*
					 * Match the key names
					 */
					JSONArray expectedKeys = expectedJSON.names();
					JSONArray actualKeys = actualJSON.names();
					if (!expectedKeys.toString().equals(actualKeys.toString())) {
						Reporter.log("Expected keys" + expectedKeys.toString() + "Actual Keys" + actualKeys.toString());
						return false;
					} else {
						/*
						 * Check type of value of each keys and value of value if not only
						 * structure
						 */
						String expectedKey = "";
						String actualKey = "";
						for (int i = 0; i < expectedKeys.length(); i++) {
							try {
								/*
								 * Get the key.
								 */
								expectedKey = expectedKeys.getString(i);
								actualKey = actualKeys.getString(i);
								/*
								 * Get the value as Object
								 */
								Object expctedValue = expectedJSON.get(expectedKey);
								Object actualValue = actualJSON.get(actualKey);

								/*
								 * Case 1: String. Check if both values are String
								 */
								if (expctedValue instanceof String && actualValue instanceof String) {
									/*
									 * If values to be matched
									 */
									if (!onlyStructure) {
										if (!actualValue.toString().equals(expctedValue.toString())) {
											Reporter.log("For the key:" + expectedKey + ", Expected:" + expctedValue.toString() + ", Actual:"
													+ actualValue.toString());
											return false;
										}
									}
								}
								/*
								 * Case 2: Number. Check if both values are number
								 */
								else if (expctedValue instanceof Number && actualValue instanceof Number) {
									/*
									 * If values to be matched
									 */
									if (!onlyStructure) {
										if (!actualValue.toString().equals(expctedValue.toString())) {
											Reporter.log("For the key:" + expectedKey + ", Expected:" + expctedValue.toString() + ", Actual:"
													+ actualValue.toString());
											return false;
										}
									}
								}
								/*
								 * Case 3: Boolean: If both values are Boolean
								 */
								else if (expctedValue instanceof Boolean && actualValue instanceof Boolean) {
									/*
									 * If values to be matched
									 */
									if (!onlyStructure) {
										if ((Boolean) expctedValue != (Boolean) actualValue) {
											Reporter.log("For the key:" + expectedKey + ", Expected:" + expctedValue.toString() + ", Actual:"
													+ actualValue.toString());
											return false;
										}
									}
								}
								/*
								 * Case 4: Array: If both values are JSONArray
								 */
								else if (expctedValue instanceof JSONArray && actualValue instanceof JSONArray) {

									if (!equalityJSONArray((JSONArray) expctedValue, (JSONArray) actualValue, onlyStructure)) {
										return false;
									}
								}
								/*
								 * Case 5: JSONObject: If both are JSONObject
								 */
								else if (expctedValue instanceof JSONObject && actualValue instanceof JSONObject) {
									/*
									 * Call jsonEquality with the objects
									 */
									if (!equalityJSON((JSONObject) expctedValue, (JSONObject) actualValue, onlyStructure)) {
										return false;
									}
								} else {
									Reporter.log("One/both of the value is/are not a JSON data type or of same data type for the Key:" + expectedKey
											+ ". Expected: " + expctedValue + ", Actual: " + actualValue);
									return false;
								}
							} catch (Exception e) {
								/*
								 * If the key is not found. If there is no string value for the
								 * index.
								 */
								Reporter
										.log("Either the key is not found or there is no string value for the key in request or responce JSON. Refer to Key - "
												+ expectedKey);
								return false;
							}
						}
					}
				}
			}
		} else if (expectedJSONObj instanceof JSONArray && actualJSONObj instanceof JSONArray) {
			try {
				if (!equalityJSONArray((JSONArray) expectedJSONObj, (JSONArray) actualJSONObj, onlyStructure)) {
					return false;
				}
			} catch (JSONException e) {
				Reporter.log("Exception while procesing JSON.<br>Expected JSON:" + expectedJSONObj.toString() + "<br>Actual JSON"
						+ actualJSONObj.toString());
				e.printStackTrace();
				throw new AssertionError();
			}
		} else {
			Reporter.log("JSON arrays are not same. Expected: " + ((JSONArray) expectedJSONObj).toString() + " Actual: "
					+ ((JSONArray) actualJSONObj).toString());
			return false;
		}
		/*
		 * If all passes
		 */
		return true;
	}

	/**
	 * Compares two JSON arrays either with values or ignoring those. If
	 * onlyStructure flag is true it compares only for keys name, key hierarchy
	 * and data type of the values in both JSON.
	 * 
	 * @param expctedArray
	 *          - Expected JSON array
	 * @param actualArray
	 *          - Actual JSON array
	 * @param onlyStructure
	 *          - ignore values if set to true.
	 * @return - True if both are same, false otherwise.
	 * @throws JSONException
	 *           - JSON exception
	 */
	private static boolean equalityJSONArray(JSONArray expctedArray, JSONArray actualArray, boolean onlyStructure)
			throws JSONException {
		/*
		 * Converting to JSONArray
		 */
		JSONArray expectedJSONArray = ((JSONArray) expctedArray);
		JSONArray actualJSONArray = ((JSONArray) actualArray);
		/*
		 * If both are of same length
		 */
		if (expectedJSONArray.length() == actualJSONArray.length()) {
			/*
			 * Check if arrays are not empty
			 */
			if (expectedJSONArray.length() > 0) {
				/*
				 * Check if both array elements have same data type
				 */
				Object expectedElementType = expectedJSONArray.get(0);
				Object actualElementType = actualJSONArray.get(0);
				/*
				 * Check if both are String type array
				 */
				if (expectedElementType instanceof String && actualElementType instanceof String) {
					/*
					 * If values to be matched
					 */
					if (!onlyStructure) {
						if (!expectedJSONArray.toString().equals(actualJSONArray.toString())) {
							Reporter.log("Array elements are not same. Expected: " + expectedJSONArray.toString() + ", Actal: "
									+ actualJSONArray.toString());
							return false;
						}
					}
				}
				/*
				 * Check if both are Number type array
				 */
				else if (expectedElementType instanceof Number && actualElementType instanceof Number) {
					/*
					 * If values to be matched
					 */
					if (!onlyStructure) {
						if (!expectedJSONArray.toString().equals(actualJSONArray.toString())) {
							Reporter.log("Array elements are not same. Expected: " + expectedJSONArray.toString() + ", Actal: "
									+ actualJSONArray.toString());
							return false;
						}
					}
				}
				/*
				 * Check if both are boolean type array
				 */
				else if (expectedElementType instanceof Boolean && actualElementType instanceof Boolean) {
					/*
					 * If values to be matched
					 */
					if (!onlyStructure) {
						if (!expectedJSONArray.toString().equals(actualJSONArray.toString())) {
							Reporter.log("Array elements are not same. Expected: " + expectedJSONArray.toString() + ", Actal: "
									+ actualJSONArray.toString());
							return false;
						}
					}
				}
				/*
				 * Check if both are JOSNObject type array
				 */
				else if (expectedElementType instanceof JSONObject && actualElementType instanceof JSONObject) {
					/*
					 * Do all for each object
					 */
					for (int j = 0; j < expectedJSONArray.length(); j++) {
						if (!equalityJSON(expectedJSONArray.get(j), actualJSONArray.get(j), onlyStructure)) {
							return false;
						}
					}
				}
				/*
				 * Check if both are JSONArray type array
				 */
				else if (expectedElementType instanceof JSONArray && actualElementType instanceof JSONArray) {
					/*
					 * Do all for each array
					 */
					for (int j = 0; j < expectedJSONArray.length(); j++) {
						if (!equalityJSON(expectedJSONArray.get(j), actualJSONArray.get(j), onlyStructure)) {
							return false;
						}
					}
				} else {
					return false;
				}
			}
		} else {
			Reporter.log("JSON arrays are not of same length. Expected (" + expectedJSONArray.length() + "): "
					+ expectedJSONArray.toString() + ", Actual (" + actualJSONArray.length() + "):" + actualJSONArray.toString());
			return false;
		}
		/*
		 * If all passes
		 */
		return true;
	}

}
