package com.candela.forte.integration.db.util;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ForteUtility {

	private Logger	log	=	LoggerFactory.getLogger(ForteUtility.class);
	
	public String genarateMonthlyReferenceNumber()throws Exception{
		try {
				Date today = new Date(); 
				Calendar cal = Calendar.getInstance();
				cal.setTime(today);
				String month = new SimpleDateFormat("MM").format(cal.getTime());
				String year  = new SimpleDateFormat("yy").format(cal.getTime()); 
				return "MC"+month+year;
		}catch(Exception xe) {
			log.error("Exception while executing genarateMonthlyReferenceNumber",xe);
			throw xe;
		}
	}
	
	public String getNextMonthDateFormate()throws Exception,Error{
		try {
			Date today = new Date(); 
			Calendar cal = Calendar.getInstance();
			cal.setTime(today);
			cal.add(Calendar.MONTH, 1);
			String month = new SimpleDateFormat("MMM").format(cal.getTime());
			String year  = new SimpleDateFormat("yy").format(cal.getTime()); 
			return month+year;
		}catch(Exception xe) {
			log.error("Exception while executing getNextMonthDateFormate",xe );
			throw xe;
		}catch(Error error) {
			log.error("Error while eecuting getNextMonthDateFormate",error);
			throw error;
		}
	}
	
	public String getPreviousMonthDateFormate()throws Exception,Error{
		try {
			Date today = new Date(); 
			Calendar cal = Calendar.getInstance();
			cal.setTime(today);
			cal.add(Calendar.MONTH, -1);
			String month = new SimpleDateFormat("MM").format(cal.getTime());
			String year  = new SimpleDateFormat("yy").format(cal.getTime()); 
			return "MC"+month+year;
		}catch(Exception xe) {
			log.error("Exception while executing getNextMonthDateFormate",xe );
			throw xe;
		}catch(Error error) {
			log.error("Error while eecuting getNextMonthDateFormate",error);
			throw error;
		}
	}
	
	public String getLastTolastMonthDateFormate()throws Exception,Error{
		try {
			Date today = new Date(); 
			Calendar cal = Calendar.getInstance();
			cal.setTime(today);
			cal.add(Calendar.MONTH, -2);
			String month = new SimpleDateFormat("MM").format(cal.getTime());
			String year  = new SimpleDateFormat("yy").format(cal.getTime()); 
			return "MC"+month+year;
		}catch(Exception xe) {
			log.error("Exception while executing getNextMonthDateFormate",xe );
			throw xe;
		}catch(Error error) {
			log.error("Error while eecuting getNextMonthDateFormate",error);
			throw error;
		}
	}
	
	public long getDaysBetweenDates(Date startDate,Date endDate)throws Exception,Error{
		try {
			
				LocalDate dateBefore  = Instant.ofEpochMilli(startDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
				LocalDate dateAfter   = Instant.ofEpochMilli(endDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
				long noOfDaysBetween  = ChronoUnit.DAYS.between(dateBefore, dateAfter);
				if(log.isDebugEnabled())
					log.debug("noOfDaysBetween"+noOfDaysBetween);
				return noOfDaysBetween;
				
		}catch(Exception xe) {
			log.error("Exception while executing getDaysBetweenDates",xe);
			throw xe;
		}catch(Error error) {
			log.error("Error while executing getDaysBetweenDates",error);
			throw error;
		}
	}
	
}
