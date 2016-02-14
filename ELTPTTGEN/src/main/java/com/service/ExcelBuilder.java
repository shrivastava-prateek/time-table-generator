package com.service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.springframework.web.servlet.view.document.AbstractJExcelView;
import com.bean.Batch;
import com.bean.Session;

 
/**
 * This class builds an Excel spreadsheet document using JExcelApi library.
 * @author www.codejava.net
 *
 */
public class ExcelBuilder extends AbstractJExcelView {
 
    @Override
    protected void buildExcelDocument(Map<String, Object> model,
            WritableWorkbook workbook, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
    	
    	 List<Session> sessions = (List<Session>) model.get("sessions");
    	 Batch b= sessions.get(0).getBatch();
    	 
    	 String heading="Time Table for "+b.getBatch_name()+" "+b.getBatch_city();
    	 
    	 //create a new excel sheet
    	 WritableSheet sheet = workbook.createSheet("Time Table", 0);
    	 
    	 //create header row
         sheet.addCell(new Label(0, 0, "Subject Name"));
         sheet.addCell(new Label(1, 0, "Trainer Name"));
         sheet.addCell(new Label(2, 0, "Session Type"));
         sheet.addCell(new Label(3, 0, "Start Date"));
         sheet.addCell(new Label(4, 0, "End Date"));
         
         //create data rows
         SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
         int rowCount=1;
         for (Session session : sessions) {
            sheet.addCell(new Label(0, rowCount, session.getSubjectName()));
            sheet.addCell(new Label(1, rowCount, session.getTrainererName()));
            sheet.addCell(new Label(2, rowCount, session.getSession_type().toString()));
            sheet.addCell(new Label(3, rowCount, sdf.format(session.getStartDate())));
            sheet.addCell(new Label(4, rowCount, sdf.format(session.getEndDate())));
             
            rowCount++;
        }
    }
}
