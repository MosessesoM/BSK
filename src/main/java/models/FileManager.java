package models;

import java.io.*;
import java.util.List;
import java.util.Scanner;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class FileManager {
    public String path;

//    Zwraca ścieżkę do pliku
    public String getPath() {
        return path;
    }

//    Pobiera ścieżkę do pliku
    public void setPath(String path) {
        this.path = path;
    }

//    Odczytuje dane z pliku .txt
    public String readTextFile() throws FileNotFoundException {
        File file = new File(path);
        Scanner reader = new Scanner(file);
        StringBuilder data= new StringBuilder();
        while (reader.hasNextLine()){
            data.append(reader.nextLine());
        }
        reader.close();
        return data.toString();
    }

    public String readPDFFile() throws IOException {
        PDDocument document = PDDocument.load(new File(path));
        String data = new PDFTextStripper().getText(document);
        return data;
    }

    public String readDOCFile() throws IOException {
        StringBuilder data = new StringBuilder();

        FileInputStream fileInputStream = new FileInputStream(path);

        XWPFDocument document = new XWPFDocument(fileInputStream);

        List<XWPFParagraph> paragraphList = document.getParagraphs();

        for (XWPFParagraph para : paragraphList){
            data.append(para.getText());
            data.append(" ");
        }
        fileInputStream.close();
        return data.toString();
    }

//    Zapisuje dane do pliku
    public void writeTextFile(String data, String name) throws IOException {
        File file = new File(System.getProperty("user.home"),"Desktop\\"+name+"txt");
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(data);
        fileWriter.close();
    }

    public void writePDFFile(String data, String name) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        PDFont font = PDType1Font.TIMES_ROMAN;
        document.addPage(page);
        PDPageContentStream contentStream = new PDPageContentStream(document,page);
        contentStream.beginText();
        contentStream.setFont(font,14);
        contentStream.newLineAtOffset(25, 750);
        contentStream.showText(data);
        contentStream.endText();
        contentStream.close();
        document.save(System.getProperty("user.home")+"\\Desktop\\"+name+".pdf");
        document.close();
    }

    public void writeDOCFile(String data, String name) throws IOException {
        XWPFDocument document = new XWPFDocument();
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun run = paragraph.createRun();
        run.setBold(false);
        run.setItalic(false);
        run.setFontSize(14);
        run.setFontFamily("New Roman");
        run.setText(data);
        FileOutputStream out = new FileOutputStream(System.getProperty("user.home")+"\\Desktop\\"+name+".docx");
        document.write(out);
    }
}
