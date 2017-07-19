package com.phelps.ps.com.basetest;

import java.sql.Timestamp;
import java.util.Date;

public class UniqueTestId {
	public String id;

	public UniqueTestId() {
		Date dt = new Date();
		Timestamp ts = new Timestamp(dt.getTime());
		id = ts.toString().replace(":", "").replace(".", "");
		id = id.substring(12, id.length() - 1);
	}

}
