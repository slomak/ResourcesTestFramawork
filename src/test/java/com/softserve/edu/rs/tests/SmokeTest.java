package com.softserve.edu.rs.tests;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.atqc.data.ListUtils;
import com.softserve.edu.atqc.data.apps.ApplicationSources;
import com.softserve.edu.atqc.specs.FlexAssert;
import com.softserve.edu.atqc.test.ParameterUtils;
import com.softserve.edu.rs.data.apps.Application;
import com.softserve.edu.rs.data.apps.ApplicationSourcesRepository;
import com.softserve.edu.rs.data.users.IUser;
import com.softserve.edu.rs.data.users.UserRepository;
import com.softserve.edu.rs.pages.AdminHomePage;
import com.softserve.edu.rs.pages.LoginPage;
import com.softserve.edu.rs.pages.LoginPage.LoginPageText;

public class SmokeTest {
	
	@AfterClass
	public void oneTimeTearDown() {
		Application.quitAll();
	}

    @DataProvider//(parallel = true)
    public Object[][] getApplicationSources(ITestContext context) {
    	return ListUtils.get().toMultiArrayNumberParams(
    			ParameterUtils.get().updateAllApplicationSources(
    				ApplicationSourcesRepository.get()
    					.getLocalHostByFirefoxTemporary(), context),
    			UserRepository.get().getExistUsersExcel());
    }

	//@Test(dataProvider = "getApplicationSources")
	public void adminLogin(ApplicationSources applicationSources, IUser admin) throws Exception {
		// Preconditions.
		Application application = Application.get(applicationSources);
		// Test Steps.
		LoginPage loginPage = application.load(); 
		// Checking.
		Thread.sleep(2000);
		FlexAssert.get()
			.forElement(loginPage.getLogin())
				.attributeMatch("name", "login")
				.attributeMatch("id", "login")
				.isVisible()
				.next()
			.forElement(loginPage.getPassword())
				.attributeMatch("name", "password")
				.attributeMatch("id", "password")
				.isVisible();
		//
		//application.quit();
		// Check
		FlexAssert.get()
			.check();
	}

	//@Test(dataProvider = "getApplicationSources")
	public void adminLoginAdv(ApplicationSources applicationSources, IUser admin) {
		// Preconditions.
		Application application = Application.get(applicationSources);
		// Test Steps.
		AdminHomePage adminHomePage = application.load().successAdminLogin(admin); 
		// Checking.
		FlexAssert.get()
			.forElement(adminHomePage.getLoginAccountText())
				.valueMatch(admin.getAccount().getLogin())
				.next()
			.forElement(adminHomePage.getLoginAccount())
				.valueMatch(admin.getAccount().getLogin());
//		Assert.assertEquals(admin.getAccount().getLogin(),
//				adminHomePage.getLoginAccountText());
		// Test Steps.
		//adminHomePage.setFocusUsers();
		adminHomePage.clickUsers(); 
		// Return to previous state.
		LoginPage loginPage = adminHomePage.logout();
		FlexAssert.get()
			.forElement(loginPage.getLogin())
				.attributeMatch("name", "login")
				.attributeMatch("id", "login")
				.isVisible()
				.next()
			.forElement(loginPage.getPassword())
				.attributeMatch("name", "password")
				.attributeMatch("id", "password")
				.isVisible();
		//
		//application.quit();
		// Check
		FlexAssert.get()
			.check();
	}

//	@Test(dataProvider = "getApplicationSources")
	public void adminLoginLocalization(ApplicationSources applicationSources, IUser admin) {
		// Preconditions.
		applicationSources.setLanguage("eng");
		Application application = Application.get(applicationSources);
		// Test Steps.
		LoginPage loginPage = application.load();
//		loginPage = loginPage.changeLanguage(ChangeLanguageFields.ENGLISH);

		// Checking.
		FlexAssert.get()
			.forElement(loginPage.getLoginLabelText())
				.valueMatch(LoginPageText.LOGIN_LABEL.getLocalization(application.getChangeLanguageFields()))
				.next()
			.forElement(loginPage.getPasswordLabelText())
				.valueMatch(LoginPageText.PASSWORD_LABEL.getLocalization(application.getChangeLanguageFields()))
				.next()
			.forElement(loginPage.getSigninText())
				.valueMatch(LoginPageText.SUBMIT_BUTTON.getLocalization(application.getChangeLanguageFields()));
		//
		//application.quit();
		// Check
		FlexAssert.get()
			.check();
	}

	@Test(dataProvider = "getApplicationSources")
	public void checkDB(ApplicationSources applicationSources, IUser admin) {
		
		
		
	}
}