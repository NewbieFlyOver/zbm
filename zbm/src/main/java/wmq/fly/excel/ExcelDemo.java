package wmq.fly.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * excel表格的导入导出，包含浏览器下载
 *
 */

public class ExcelDemo {

	/**
	 *  导入Excel表格
	 */
	public static void readExcel(){
		 //excel文件路径
        String excelPath = "C:\\10月回访-许娜-.xls";
        try {
            //String encoding = "GBK";
            File excel = new File(excelPath);
            if (excel.isFile() && excel.exists()) {   //判断文件是否存在

                String[] split = excel.getName().split("\\.");  //.是特殊字符，需要转义！！！！！
                Workbook wb;
                //根据文件后缀（xls/xlsx）进行判断
                if ( "xls".equals(split[1])){
                    FileInputStream fis = new FileInputStream(excel);   //文件流对象
                    wb = new HSSFWorkbook(fis);
                }else if ("xlsx".equals(split[1])){
                    wb = new XSSFWorkbook(excel);
                }else {
                    System.out.println("文件类型错误!");
                    return;
                }

                //开始解析
                Sheet sheet = wb.getSheetAt(0);     //读取sheet 0

                int firstRowIndex = sheet.getFirstRowNum()+1;   //第一行是列名，所以不读
                int lastRowIndex = sheet.getLastRowNum();
                System.out.println("firstRowIndex: "+firstRowIndex);
                System.out.println("lastRowIndex: "+lastRowIndex);

                for(int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {   //遍历行
                    System.out.println("rIndex: " + rIndex);
                    Row row = sheet.getRow(rIndex);
                    if (row != null) {
                        int firstCellIndex = row.getFirstCellNum();
                        int lastCellIndex = row.getLastCellNum();
                        for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++) {   //遍历列
                            Cell cell = row.getCell(cIndex);
                            if (cell != null) {
                                System.out.println(cell.toString());
                            }
                        }
                    }
                }
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

	}
	
	/**
	 *  导出excel表格
	 * @throws ParseException
	 */
	static void writeExcel() throws ParseException {
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
  
        Member user1 = new Member(1, "熊大", 24, df.parse("1993-08-28"));  
        Member user2 = new Member(2, "熊二", 23, df.parse("1994-08-19"));  
        Member user3 = new Member(3, "熊熊", 24, df.parse("1983-11-22"));  
        list.add(user1);  
        list.add(user2);  
        list.add(user3);  
  
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
        try  
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
        } 
        
        /*
        // 第六步，浏览器下载excel
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
        */
        
	}
	
	
	
	
	
	
	public static void main(String[] args) throws ParseException {
		//readExcel();
		writeExcel();
	}
	
	
}



