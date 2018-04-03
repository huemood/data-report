package com.flower.bd.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.flower.bd.po.mapper.LogMapper;

@Service
public class LogService {
	@Autowired
	private LogMapper logMapper;
	
	private static Date cacheLatestReportGenerateTime;
	
	@Value("${setting.reportgenerateinterval}")
	private Integer reportGenerateInterval;
	
	public String getlatestReportGenerateTime(){
		Date latestReportGenerateTime = getCacheLatestReportGenerateTime();
		//logMapper.getlatestReportGenerateTime();
		
		if (latestReportGenerateTime == null)
			return "无数据";
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		String str=sdf.format(latestReportGenerateTime); 
		
		return str;
		
	}
	
	public synchronized Date getCacheLatestReportGenerateTime(){
		/* 大数据生成时间不固定，去掉缓存读取
		if (cacheLatestReportGenerateTime == null)
			cacheLatestReportGenerateTime = logMapper.getlatestReportGenerateTime();
		else {
			Calendar calendarCacheLatestReportGenerateTime = Calendar.getInstance();
			calendarCacheLatestReportGenerateTime.setTime(cacheLatestReportGenerateTime);
			calendarCacheLatestReportGenerateTime.add(Calendar.SECOND, reportGenerateInterval);
			
			Calendar now = Calendar.getInstance();
			if (now.getTime().getTime() > calendarCacheLatestReportGenerateTime.getTime().getTime())
				cacheLatestReportGenerateTime = logMapper.getlatestReportGenerateTime();
		}
		*/
		//每次读取最新
		cacheLatestReportGenerateTime = logMapper.getlatestReportGenerateTime();
		return cacheLatestReportGenerateTime;
	}
}
