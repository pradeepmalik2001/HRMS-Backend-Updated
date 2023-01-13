package com.ahom.hrms.Helper;

import com.ahom.hrms.entities.Attendance;
import com.ahom.hrms.serviceimpl.AttendanceServiceImpl;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
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
                            attendance.setDate((String) cells.getStringCellValue());
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
