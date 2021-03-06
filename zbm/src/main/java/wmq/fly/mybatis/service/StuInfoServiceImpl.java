package wmq.fly.mybatis.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wmq.fly.excel.Member;
import wmq.fly.mybatis.dao.StuInfoMapper;
import wmq.fly.mybatis.entity.StuInfo;

@Service
public class StuInfoServiceImpl implements StuInfoService {
	@Autowired
    private StuInfoMapper stuInfoMapper;
	
	public Object insertStuInfo(StuInfo stuInfo) {
		stuInfoMapper.insetStuInfo(stuInfo);
		return stuInfo.getId();
	}
	
	public Object getAllStuInfo() {
		return stuInfoMapper.selectAllStuInfo();
	}
	
	public Object getAllStuInfo01() {
		return stuInfoMapper.selectAllStuInfo01();
	}
	public Object getAllStuInfo02() {
		return stuInfoMapper.selectAllStuInfo02();
	}
	
	public Object getStuInfoById(StuInfo stuInfo) {
		return stuInfoMapper.selectStuInfoById(stuInfo);
	}

	@Override
	public Object writeExcel(HttpServletResponse response) {
		 // 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("学生表一");  
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
  
        HSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("学号");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 1);  
        cell.setCellValue("姓名");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 2);  
        cell.setCellValue("年龄");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 3);  
        cell.setCellValue("生日");  
        cell.setCellStyle(style);  
  
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，  
        List list = new ArrayList();  
        SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");  
  
        Member user1;
		try {
			user1 = new Member(1, "熊大", 24, df.parse("1993-08-28"));
			  Member user2 = new Member(2, "熊二", 23, df.parse("1994-08-19"));  
		        Member user3 = new Member(3, "熊熊", 24, df.parse("1983-11-22"));  
		        list.add(user1);  
		        list.add(user2);  
		        list.add(user3);  
		} catch (ParseException e1) {
			e1.printStackTrace();
		}  
      
  
        for (int i = 0; i < list.size(); i++)  
        {  
            row = sheet.createRow((int) i + 1);  
            Member stu = (Member) list.get(i);  
            // 第四步，创建单元格，并设置值  
            row.createCell((short) 0).setCellValue((double) stu.getCode());  
            row.createCell((short) 1).setCellValue(stu.getName());  
            row.createCell((short) 2).setCellValue((double) stu.getAge());  
            cell = row.createCell((short) 3);  
            cell.setCellValue(new SimpleDateFormat("yyyy-mm-dd").format(stu  
                    .getBirth()));  
        }  
        // 第六步，将文件存到指定位置  
       /* try  
        {  
        	 File file1 = new File("C:/upload/Members.xls");
             if(!file1.exists()){
                 file1.createNewFile();
             }
            FileOutputStream fout = new FileOutputStream(file1);  
            wb.write(fout);  
            fout.close();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        } */
        
        
        // 第六步，下载excel
        OutputStream out = null;  
        try {      
            out = response.getOutputStream();  
            String fileName = "enroll.xls";// 文件名  
            response.setContentType("application/x-msdownload");  
            response.setHeader("Content-Disposition", "attachment; filename="  
                                                    + URLEncoder.encode(fileName, "UTF-8"));  
            wb.write(out);  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {    
            try {     
                out.close();    
            } catch (IOException e) {    
                e.printStackTrace();  
            }    
        }   
        return "执行完毕！";
	}

	@Override
	public Object echart(HttpServletResponse response) {
		String[] xText = {"数学","语文","英语","体育","自然"};
		List<String> list = Arrays.asList(xText);
		String[] xValue1 = {"80","85","76","90","92"};
		List<String> list2 = Arrays.asList(xValue1);
		
		String[] xValue2 = {"90","82","79","99","93"};
		List<String> list3 = Arrays.asList(xValue2);
		
		Map<String,List<String>> map = new HashMap<String,List<String>>();
		map.put("xText", list);
		map.put("xValue1", list2);
		map.put("xValue2", list3);
		return map;
	}

	@Override
	public String freemarker(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("111111");
		
		return "freemarker";
	}
	
	
}
