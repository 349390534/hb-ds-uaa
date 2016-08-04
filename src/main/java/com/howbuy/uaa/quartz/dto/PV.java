package com.howbuy.uaa.quartz.dto;

public class PV extends AnaLysisObj {

	private String channelID;
	
	private String subChannelID;
	
	private String land;
	
	public String getLand() {
		return land;
	}

	public void setLand(String land) {
		this.land = land;
	}

	public String getChannelID() {
		return channelID;
	}

	public void setChannelID(String channelID) {
		this.channelID = channelID;
	}

	public String getSubChannelID() {
		return subChannelID;
	}

	public void setSubChannelID(String subChannelID) {
		this.subChannelID = subChannelID;
	}


	@Override
	public boolean equals(Object obj) {
		if(!PV.class.isInstance(obj))
			return false;
		PV target = (PV)obj;
		return getCookie().equals(target.getCookie());
	}
}
