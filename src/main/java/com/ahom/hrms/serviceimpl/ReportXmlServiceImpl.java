


package com.ahom.hrms.serviceimpl;

import com.ahom.hrms.Repository.BankingInfoRepository;
import com.ahom.hrms.Repository.BasicEmployeeRepository;
import com.ahom.hrms.entities.BankingInfo;
import com.ahom.hrms.entities.BasicEmployee;
import com.sun.istack.NotNull;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class ReportXmlServiceImpl {

        @Autowired
        private BasicEmployeeRepository basicEmployeeRepository;

        @Autowired
        BankingInfoRepository bankingInfoRepository;
@NotNull
        public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
    //Resource sourceFile = new ClassPathResource("report.jrxml");
    String path="E:\\report";
           // List<BasicEmployee> hrms=basicEmployeeRepository.findByManyTables(employeeId);
   List<BankingInfo> hrms1=bankingInfoRepository.findAll();
            File file=
                    ResourceUtils.getFile("classpath:kkk.jrxml");
            JasperReport jasperReport= JasperCompileManager.compileReport(file.getAbsolutePath());
            JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(hrms1);
    JRBeanCollectionDataSource dataSource1=new JRBeanCollectionDataSource(hrms1);
            Map<String, Object> parameters=new HashMap<>();
            parameters.put("created by", "aadi");
            JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport, parameters,
                    dataSource);
//    JasperPrint jasperPrint1= JasperFillManager.fillReport(jasperReport, parameters,
//            dataSource1);
            if(reportFormat.equalsIgnoreCase("html"))
            {
                JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\kkk.html");
//                JasperExportManager.exportReportToHtmlFile(jasperPrint1, path + "\\kkk.html");
            }
            if(reportFormat.equalsIgnoreCase("pdf"))
            {
                JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\kkk.pdf");
//                JasperExportManager.exportReportToPdfFile(jasperPrint1, path + "\\kkk.pdf");
            }
            return "Report generated in path:" + path;
        }
    }


