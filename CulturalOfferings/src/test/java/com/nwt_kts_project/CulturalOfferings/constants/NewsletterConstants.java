package com.nwt_kts_project.CulturalOfferings.constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewsletterConstants {
	
	public static final int DB_NEWS_COUNT = 2;
	public static Long DB_NEWS_ID = 1l;
	public static final Long DB_NEWS_ID2 = 2L;
	
	public static final String NEW_NEWS_TITLE = "Test-Title1";
	public static final String NEW_NEWS_CONTENT = "Test-Content1";
	public static final Date NEW_NEWS_DATE = StringToDate("2020-05-06") ;
	
	public static final String DB_NEWS_TITLE = "Test-UpdateTitle1";
	public static final String DB_NEWS_CONTENT = "Test-UpdateContent1";
	public static final Date DB_NEWS_DATE = StringToDate("2020-05-06") ;
	
	public static Long DB_DEL_NEWS_ID = 1l;
	public static final String DB_DEL_NEWS_TITLE = "Test-DeleteTitle";
	public static final String DB_DEL_NEWS_CONTENT  = "Test-DeleteContent";
	public static final Date DB_DEL_NEWS_DATE = StringToDate("2020-05-06") ;

	
	public static Date StringToDate(String s){

	    Date result = null;
	    try{
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        result  = dateFormat.parse(s);
	    }

	    catch(ParseException e){
	        e.printStackTrace();

	    }
	    return result ;
	}

}
