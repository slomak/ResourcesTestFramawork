package com.softserve.edu.rs.data.input;

import java.util.List;

import com.softserve.edu.atqc.data.CSVUtils;


public class SearchDataRepository {
	private static volatile SearchDataRepository instance = null;
	
	private SearchDataRepository(){
	}
	
	public static SearchDataRepository get() {
        if (instance == null) {
            synchronized (SearchDataRepository.class) {
                if (instance == null) {
                    instance = new SearchDataRepository();
                }
            }
        }
        return instance;
    }
	
	public List<String> getInputFromCVS(String filePath) {
		return new CSVUtils().getAllSingleCells(filePath);
    }
	
	public List<List<String>> getMInputFromCVS(String filePath) {
		return new CSVUtils().getAllCells(filePath);
    }

//    public List<String> getInputFromExcel(String filePath) {
//        
//    }
}
