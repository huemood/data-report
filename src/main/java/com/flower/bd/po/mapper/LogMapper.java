package com.flower.bd.po.mapper;

import java.sql.Date;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogMapper {
	Date getlatestReportGenerateTime();
}
