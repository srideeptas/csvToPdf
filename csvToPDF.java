package com.demo.CovertToPDF;

import java.io.FileOutputStream;
import java.io.*;
import au.com.bytecode.opencsv.CSVReader;
import java.io.FileReader;
import java.util.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
public class csv2pdf {  
        public static void main(String[] args) throws Exception{
                
                /* Step -1 : Read input CSV file in Java */
                String inputCSVFile = "C:\\Users\\hp\\Documents\\csv_to_pdf.csv";
                CSVReader reader = new CSVReader(new FileReader(inputCSVFile));
                /* Variables to loop through the CSV File */
                
                String [] nextLine; /* for every line in the file */   
                
                int lnNum = 0; /* line number */
                /* Step-2: Initialize PDF documents - logical objects */
                Document my_pdf_data = new Document();
                PdfWriter.getInstance(my_pdf_data, new FileOutputStream("C:\\\\Users\\\\hp\\\\Documents\\\\converted_PDF_File.pdf"));
                my_pdf_data.open();            
                PdfPTable my_first_table = new PdfPTable(4); 
                // set the parameter as per the number of columns in the input file
                
                my_first_table.setWidths(new int[]{50, 50, 50, 100}); //no. of width parameters = no. of columns
                
                Paragraph para = new Paragraph("This is the Header",
                		FontFactory.getFont(FontFactory.TIMES_ROMAN,18, Font.BOLD, BaseColor.RED));
                my_pdf_data.add(para);
                my_pdf_data.add( Chunk.NEWLINE );
                my_pdf_data.add( Chunk.NEWLINE );
                
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
               
                /* Converting table to pdf file and closing the file */
                my_pdf_data.add(my_first_table);  
                
                //optional part 
                my_pdf_data.add( Chunk.NEWLINE );
                Paragraph para2= new Paragraph("Code by Srideepta Sarkar",
                		FontFactory.getFont(FontFactory.TIMES_ROMAN,9, Font.BOLD, BaseColor.GREEN));
                my_pdf_data.add(para2);
                my_pdf_data.close();            
        }
}
