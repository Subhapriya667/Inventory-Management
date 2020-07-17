package com.cde.ims.domain.model;

import java.util.Date;

/**
 * 
 * InventoryManagement
 * com.cde.ims.domain.model | ErrorInformation.java
 * -------------------------------------------------------------------------
 * 
 *
 * @author Subhapriya M
 * @version 1.0
 * 
 */

/**
 * <Code modification record>
 * 
 * <pre>
 * No.	Modified by (ID) 	Date (MM DD, YYYY) 	[BUG-ID] 	Description
 * -------------------------------------------------------------------------
 * 1    --					 Jul 15, 2020			-		Initial version
 * 
 * </pre>
 */

public class ErrorInformation {

	private Date timestamp;
	private String message;
	private String details;

	public ErrorInformation(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

}
