package com.loganisawesome.drivebetter.utilities.enums;

public enum DriveBetterMenuItem
{
	TripInformation("Trip Info"),
	Graphs("Graphs");
	
	private final String displayName;
	
	private DriveBetterMenuItem(String displayName)
	{
		this.displayName = displayName;
	}

	public String getDisplayName()
	{
		return displayName;
	}
}
