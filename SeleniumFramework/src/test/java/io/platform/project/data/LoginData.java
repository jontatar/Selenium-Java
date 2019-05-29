package io.platform.project.data;

import org.testng.annotations.DataProvider;

public class LoginData {
	
	@DataProvider(name="credentials")
	public static Object[][] getData() {
		return new Object[][] {
			{"lars@test.com", "password"},
			{"", ""},
			{"lars@test.com", "incorrect"}
		};
	}

}
