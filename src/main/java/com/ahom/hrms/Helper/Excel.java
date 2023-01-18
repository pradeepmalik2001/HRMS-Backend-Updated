package com.ahom.hrms.Helper;

import com.ahom.hrms.entities.Attendance;
import com.ahom.hrms.entities.BasicEmployee;
import com.ahom.hrms.serviceimpl.AttendanceServiceImpl;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Excel {
    public static boolean checkFormat(MultipartFile file){
        String contentType = file.getContentType();
        if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
        {
            return true;
        }else
        {
            return false;
        }
    }

//    public void  setResponseHeader(HttpServletResponse response,String contentType,String extension,
//                                  String prefix)
//    {
//        String fileName =prefix+extension;
//        response.setContentType(contentType);
//
//        String headerKey ="Content-Disposition";
//        String headerValue="attachment;fileName="+ fileName;
//        response.setHeader(headerKey,headerValue);
//
//    }
//    public void exportToCsv(List<BasicEmployee>basicEmployeeList,HttpServletResponse response)
//    {
//        setResponseHeader(response,"text/csv",".csv","Employee_");
//    }

    @Autowired
    AttendanceServiceImpl attendanceService;
    public static List<Attendance> convertExcelToList(InputStream inputStream, String fileName)
    {


        List<Attendance>list=new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

            XSSFSheet sheet = workbook.getSheetAt(0);
                int rowNumber=1;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext())
            {
                Row row = iterator.next();

                if (rowNumber==1){
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cell = row.iterator();
                int cid=0;
                Attendance attendance=new Attendance();
                while (cell.hasNext()){
                    Cell cells = cell.next();
                    switch (cid)
                    {
                        case 0:
                            attendance.setId((int) cells.getNumericCellValue());
                            break;
                        case 1:
                            attendance.setSelectEmployee(cells.getStringCellValue());
                            break;
                        case 2:
                            attendance.setDate((Date) cells.getDateCellValue());
                            break;
                        case 3:
                            attendance.setInTime((String) cells.getStringCellValue());
                            break;
                        case 4 :
                            attendance.setOutTime((String) cells.getStringCellValue());
                            break;
                        case 5:
                            attendance.setStatus((String) cells.getStringCellValue());
                        default:
                            break;

                    }
                    cid++;
                }

                list.add(attendance);
            }



        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }
}
