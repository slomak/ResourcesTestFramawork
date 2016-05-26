package com.softserve.edu.rs.tests;


import java.util.List;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.atqc.data.ListUtils;
import com.softserve.edu.atqc.data.apps.ApplicationSources;
import com.softserve.edu.atqc.test.ParameterUtils;
import com.softserve.edu.rs.data.apps.Application;
import com.softserve.edu.rs.data.apps.ApplicationSourcesRepository;
import com.softserve.edu.rs.data.input.SearchDataRepository;
import com.softserve.edu.rs.data.users.IUser;
import com.softserve.edu.rs.data.users.UserRepository;
import com.softserve.edu.rs.pages.ActiveAdminSearchPage;
import com.softserve.edu.rs.pages.ActiveAdminSearchPage.RolesForSearch;


public class NewTest {
	@DataProvider
    public Object[][] getUsers(ITestContext context) {
		return ListUtils.get().toMultiArrayNumberParams(
    			ParameterUtils.get().updateAllApplicationSources(
        				ApplicationSourcesRepository.get()
        					.getLocalHostByFirefoxTemporary(), context),
        					SearchDataRepository.get().getInputFromCVS("C:/lv176-multilayer/target/test-classes/logins.csv"));
    }
 
//	@Test //(dataProvider = "getUsers")
	public void adminLogin(){
		List<String> arr = SearchDataRepository.get().getInputFromCVS("C:/lv176-multilayer/target/test-classes/logins.csv");
		
		for (String str : arr){
			System.out.println(str);
		}
	}
	
	@Test (dataProvider = "getUsers")
	public void adminLogin2(ApplicationSources applicationSources, String str){
		System.out.println(str);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}
