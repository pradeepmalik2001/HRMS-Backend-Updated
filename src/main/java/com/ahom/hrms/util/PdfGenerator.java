package com.ahom.hrms.util;

import java.awt.Font;
import java.io.IOException;
import java.util.List;

import com.ahom.hrms.dto.BasicEmployeeDto;
import com.ahom.hrms.entities.BasicEmployee;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import javax.servlet.http.HttpServletResponse;

public class PdfGenerator {
	public void generate(List<BasicEmployee> allEmployee, HttpServletResponse response) throws DocumentException, IOException {
	    // Creating the Object of Document
	    Document document = new Document(PageSize.A4);
	    // Getting instance of PdfWriter
	    PdfWriter.getInstance(document, response.getOutputStream());
	    // Opening the created document to change it
	    document.open();
	    // Creating font
	    // Setting font style and size
	    com.lowagie.text.Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
	    fontTiltle.setSize(20);
	    // Creating paragraph
	    Paragraph paragraph1 = new Paragraph("List of the all employee", fontTiltle);
	    // Aligning the paragraph in the document
	    paragraph1.setAlignment(Paragraph.ALIGN_CENTER);
	    // Adding the created paragraph in the document
	    document.add(paragraph1);
	    // Creating a table of the 4 columns
	    PdfPTable table = new PdfPTable(10);
	    // Setting width of the table, its columns and spacing
	    table.setWidthPercentage(100);
	    table.setWidths(new int[] {3,3,3,3,3,3,3,3,3,3});
	    table.setSpacingBefore(5);
	    // Create Table Cells for the table header
	    PdfPCell cell = new PdfPCell();
	    // Setting the background color and padding of the table cell
	    cell.setBackgroundColor(CMYKColor.BLUE);
	    cell.setPadding(5);
	    // Creating font
	    // Setting font style and size
	    com.lowagie.text.Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
	    font.setColor(CMYKColor.WHITE);
	    
//	    
//	    PdfPTable table1 = new PdfPTable(2);
//	    // Setting width of the table, its columns and spacing
//	    table1.setWidthPercentage(100);
//	    table1.setWidths(new int[] {3,3});
//	    table1.setSpacingBefore(5);
//	    
//	    PdfPCell cell1 = new PdfPCell();
//	    // Setting the background color and padding of the table cell
//	    cell1.setBackgroundColor(CMYKColor.BLUE);
//	    cell1.setPadding(2);
//	    // Creating font
//	    // Setting font style and size
//	    com.lowagie.text.Font font1 = FontFactory.getFont(FontFactory.TIMES_ROMAN);
//	    font1.setColor(CMYKColor.WHITE);
	    
//	    PdfPTable table2 = new PdfPTable(2);
//	    // Setting width of the table, its columns and spacing
//	    table2.setWidthPercentage(100);
//	    table2.setWidths(new int[] {3,3});
//	    table2.setSpacingBefore(5);
//	    
//	    PdfPCell cell2 = new PdfPCell();
//	    // Setting the background color and padding of the table cell
//	    cell2.setBackgroundColor(CMYKColor.BLUE);
//	    cell2.setPadding(2);
//	    // Creating font
//	    // Setting font style and size
//	    com.lowagie.text.Font font2 = FontFactory.getFont(FontFactory.TIMES_ROMAN);
//	    font2.setColor(CMYKColor.WHITE);
	    
	    
	    // Adding headings in the created table cell or  header
	    // Adding Cell to table
//	    cell.setPhrase(new Phrase("employeeId", font));
//	    table.addCell(cell);
	    cell.setPhrase(new Phrase("employeeName", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("whichCompany", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("selectDepartment", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("designation", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("email", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("mobile", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("joiningDate", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("reportingTo", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("dob", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("workType", font));
	    table.addCell(cell);
	    // Iterating the list of students
	    for (BasicEmployee basicEmployee: allEmployee) {
	      // Adding student id
	      table.addCell(basicEmployee.getEmployeeName());
	      // Adding student name
	      table.addCell(basicEmployee.getMobile());
	      table.addCell(basicEmployee.getDesignation());
	      table.addCell(basicEmployee.getDob());
	      table.addCell(basicEmployee.getEmail());
	      table.addCell(basicEmployee.getEmployeeName());
	      table.addCell(basicEmployee.getSelectDepartment());
	      table.addCell(basicEmployee.getReportingTo());
	      table.addCell(basicEmployee.getWhichCompany());
	      table.addCell(basicEmployee.getWorkType());
	      table.addCell(basicEmployee.getMobile());
	      table.addCell(basicEmployee.getJoiningDate());
	    }
	    // Adding the created table to the document
	    document.add(table);
	    // Closing the document
	    document.close();
	  }
}
