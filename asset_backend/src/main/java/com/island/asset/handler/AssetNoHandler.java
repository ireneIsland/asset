package com.island.asset.handler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AssetNoHandler {

	public String serialNo(String assetNo) {

		DateFormat dateFormat = new SimpleDateFormat("yy");
		Date date = new Date();
		String stNo = assetNo.substring(0, 2);
		if (dateFormat.format(date).equals(stNo)) {
			int intValue = Integer.valueOf(assetNo) + 1;
			return String.valueOf(intValue);
		}
		return assetNo;
	}
}
