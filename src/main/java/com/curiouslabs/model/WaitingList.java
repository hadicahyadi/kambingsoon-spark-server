package com.curiouslabs.model;

import com.google.gson.Gson;

public class WaitingList extends BaseModel implements BaseModelInterface{
	
	private String guestName;
	private int guestCount;
	
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	public int getGuestCount() {
		return guestCount;
	}
	public void setGuestCount(int guestCount) {
		this.guestCount = guestCount;
	}
	
	public Object[] toArray(){
		return new Object[]{guestName,guestCount};
		
	}
	

}
