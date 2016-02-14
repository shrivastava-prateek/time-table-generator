package com.service;


import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
 




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Batch;
import com.bean.Session;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
 
/**
 * This view class generates a PDF document 'on the fly' based on the data
 * contained in the model.
 * @author www.codejava.net
 *
 */
public class PDFBuilder extends AbstractITextPdfView {
 
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document doc,
            PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // get data model which is passed by the Spring container
        List<Session> sessions = (List<Session>) model.get("sessions");
  
        //System.out.println("--------------PDF Geneartor----------------");
         Batch b= sessions.get(0).getBatch();
         
         String heading="Time Table for "+b.getBatch_name()+" "+b.getBatch_city();
        doc.add(new Paragraph(heading));
         
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[] {2.0f, 2.0f, 2.0f, 2.0f, 2.5f,2.5f});
        table.setSpacingBefore(10);
         
        // define font for table header row
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(BaseColor.WHITE);
         
        // define table header cell
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.BLUE);
        cell.setPadding(6);
         
        // write table header
        cell.setPhrase(new Phrase("Batch Venue", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Subject Name", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Trainer Name", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Session Type", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Start Date", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("End Date", font));
        table.addCell(cell);
         SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
        // write table row data
        for (Session session : sessions) {
            table.addCell(b.getBatch_venue());
            table.addCell(session.getSubjectName());
            table.addCell(session.getTrainererName());
            table.addCell(session.getSession_type().toString());
            table.addCell(sdf.format(session.getStartDate()));
            table.addCell(sdf.format(session.getEndDate()));
        }
         
        doc.add(table);
         
    }
 
}