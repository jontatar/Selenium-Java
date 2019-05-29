package io.platform.project.data;

import org.testng.annotations.DataProvider;

public class ContactUsData {
	
	@DataProvider(name="credentials")
	public static Object[][] getData() {
		return new Object[][] {
			{"", "", "Howdy!"},
			{"", "lars@test.com", "Howdy!"},
			{"Lars Jones", "", "Howdy!"},
			{"Lars Jones", "lars@test", "Howdy!"},
			{"Lars Jones", "@gmail.com", "Howdy!"},
			{"Lars Jonse", "lars@test.com", "Howdy!"}
		};
	}

}
