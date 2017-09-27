package com.flower.bd;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONArray;
import com.flower.bd.po.Student;
import com.flower.bd.util.ExcelUtil;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ExcelUtilTests {

	@Autowired
	private ExcelUtil excelUtil;
	
	@Test
	public void contextLoads() throws IOException {
	     /*int count = 10000;
	        JSONArray ja = new JSONArray();
	        for(int i=0;i<100000;i++){
	            Student s = new Student();
	            s.setName("POI"+i);
	            s.setAge(i);
	            s.setBirthday(new Date());
	            s.setHeight(i);
	            s.setWeight(i);
	            s.setSex(i/2==0?false:true);
	            ja.add(s);
	        }
	        Map<String,String> headMap = new LinkedHashMap<String,String>();
	        headMap.put("name","姓名");
	        headMap.put("age","年龄");
	        headMap.put("birthday","生日");
	        headMap.put("height","身高");
	        headMap.put("weight","体重");
	        headMap.put("sex","性别");

	        String title = "测试";
	       
	        OutputStream outXlsx = new FileOutputStream("E://b.xlsx");
	        System.out.println("正在导出xlsx....");
	        Date d2 = new Date();
	        ExcelUtil.exportExcelX(title,headMap,ja,null,0,outXlsx);
	        System.out.println("共"+count+"条数据,执行"+(new Date().getTime()-d2.getTime())+"ms");
	        outXlsx.close();*/
	}

}
