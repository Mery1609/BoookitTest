package com.bookit.pages;

import org.openqa.selenium.support.PageFactory;

import com.bookit.utilities.Driver;

public class MapPage extends TopNavigationBar {
	
	public MapPage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}
}


//want to make some minor changes. will push ti git first