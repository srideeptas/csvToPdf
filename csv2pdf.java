package com.demo.CovertToPDF;

import java.io.FileOutputStream;
import au.com.bytecode.opencsv.CSVReader;
import java.io.FileReader;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

class csv2pdf {  
	
	/*Method for adding Main Header*/
	void addHeader(Document my_pdf_data)throws Exception
	{
		
		PdfPTable table = new PdfPTable(1);
		table.setTotalWidth(800);
		Font f = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD, BaseColor.WHITE);
		
		PdfPCell cell = new PdfPCell(new Phrase("MAIN HEADER",f));
		cell.setBackgroundColor(new BaseColor(19, 117, 173));
		cell.setRowspan(2);
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		my_pdf_data.add(table);
	}
	
	/*Method for adding subheader*/
	void addSubHeader(Document my_pdf_data)throws Exception
	{		
		my_pdf_data.add( Chunk.NEWLINE );

		Paragraph para1 = new Paragraph("This is the subheader",
        		FontFactory.getFont(FontFactory.TIMES_ROMAN,12, Font.ITALIC, BaseColor.BLACK));
		//para1.setAlignment(Element.ALIGN_CENTER);
		para1.setAlignment(Paragraph.ALIGN_CENTER);
        my_pdf_data.add(para1);
        my_pdf_data.add( Chunk.NEWLINE );
	}
	
	/*Method for parsing and adding table rows to pdf*/
	void addTable(Document my_pdf_data,CSVReader reader)throws Exception {
		
		PdfPTable my_first_table = new PdfPTable(4); 		
		my_first_table.setWidths(new int[]{50, 50, 50, 100}); //no. of width parameters = no. of columns
		String [] nextLine;
		int lnNum = 0;
		//creating table rows
        PdfPCell table_cell;
        
        /* Step -3: Loop through CSV file and convert data to PDF table */
        while ((nextLine = reader.readNext()) != null) {
                lnNum++;        
                table_cell=new PdfPCell(new Phrase(nextLine[0]));
                my_first_table.addCell(table_cell);
                table_cell=new PdfPCell(new Phrase(nextLine[1]));
                my_first_table.addCell(table_cell); 
                table_cell=new PdfPCell(new Phrase(nextLine[2]));
                my_first_table.addCell(table_cell);
                table_cell=new PdfPCell(new Phrase(nextLine[3]));
                my_first_table.addCell(table_cell);
                /* number of cells to be added according to the number of columns in input file*/
        }
        my_pdf_data.add(my_first_table);  
       
		
	}
	void addFooter(Document my_pdf_data)throws Exception{
		my_pdf_data.add( Chunk.NEWLINE );
        Paragraph para2= new Paragraph("Code by Srideepta Sarkar",
        		FontFactory.getFont(FontFactory.TIMES_ROMAN,9, Font.BOLD, BaseColor.GREEN));
        my_pdf_data.add(para2);
	}
	
        public static void main(String[] args) throws Exception{
                
                /*Reading input CSV file in Java */
                String inputCSVFile = "C:\\Users\\hp\\Documents\\csv_to_pdf.csv";
                CSVReader reader = new CSVReader(new FileReader(inputCSVFile));
                
                csv2pdf C = new csv2pdf();

                /*Initializing PDF documents - logical objects */
                Document my_pdf_data = new Document();
                PdfWriter.getInstance(my_pdf_data, new FileOutputStream("C:\\\\Users\\\\hp\\\\Documents\\\\converted_PDF_File.pdf"));
                
                my_pdf_data.open();  
                
                C.addHeader(my_pdf_data);
                C.addSubHeader(my_pdf_data);
                C.addTable(my_pdf_data,reader);
                C.addFooter(my_pdf_data);
                
                my_pdf_data.close();            
        }
}
