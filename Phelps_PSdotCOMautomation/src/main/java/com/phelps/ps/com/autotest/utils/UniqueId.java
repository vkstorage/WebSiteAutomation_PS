package com.phelps.ps.com.autotest.utils;

import java.sql.Timestamp;
import java.util.Date;

public class UniqueId {
	public String id;
	public String charId="";
	public UniqueId() {
		Date dt = new Date();
		Timestamp ts = new Timestamp(dt.getTime());
		id = ts.toString().replace(":", "").replace(".", "");
		id = id.substring(12, id.length() - 1);
	}
	public UniqueId(String id){
		int intId = Integer.parseInt(id);
		while(intId>0){
			int temp=intId%10;
			temp+=65;
			 charId+=new Character((char) temp).toString();
			 intId/=10;
		}
	
	}
	
	

}
