
package pathfinder.models;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import pathfinder.App;
import pathfinder.database.DBControler;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;

public class CVBuilder extends DBControler {
        DBControler dbControler;
        TextField usernameLogin;
        VBox vBox;

        public void PdfMaker(DBControler dbControler, TextField usernameLogin, VBox vBox) {

                vBox.getStylesheets().add(App.class.getResource("/scene3style.css").toExternalForm());

                ObservableList<data_pdf> listPdf = dbControler.getDataPdfByUsername(usernameLogin.getText());
                System.out.println("value of list : " + String.valueOf(listPdf.size()));
                vBox.getChildren().clear();
                for (data_pdf data_pdf : listPdf) {
                        Button btn = new Button(data_pdf.getFullname());
                        btn.setId("btn");
                        btn.setMaxWidth(Double.MAX_VALUE);
                        btn.setOnAction(o -> {
                                String pdfPath = data_pdf.getFullname() + ".pdf";
                                PdfWriter writer;

                                try {
                                        writer = new PdfWriter(pdfPath);
                                        com.itextpdf.kernel.pdf.PdfDocument pdf = new com.itextpdf.kernel.pdf.PdfDocument(
                                                        writer);
                                        Document document = new Document(pdf);
                                        DeviceRgb headerBgColor = new DeviceRgb(196, 95, 77);
                                        DeviceRgb sectionBgColor = new DeviceRgb(236, 239, 241);
                                        DeviceRgb textColor = new DeviceRgb(66, 66, 66);
                                        // / Tambahkan judul "CURRICULUM VITAE" dengan padding
                                        Paragraph title = new Paragraph("Curriculum Vitae")
                                                        .setFontSize(24)
                                                        .setBold()
                                                        .setFontColor(ColorConstants.WHITE)
                                                        .setTextAlignment(TextAlignment.CENTER)
                                                        .setBackgroundColor(headerBgColor)
                                                        .setMarginBottom(20)
                                                        .setPadding(10);
                                        document.add(title);

                                        // Tambahkan nama lengkap
                                        document.add(new Paragraph(data_pdf.getFullname())
                                                        .setFontSize(20)
                                                        .setBold()
                                                        .setFontColor(textColor)
                                                        .setTextAlignment(TextAlignment.CENTER)
                                                        .setMarginBottom(10));

                                        // Tambahkan judul pekerjaan
                                        document.add(new Paragraph(data_pdf.getJobTitle())
                                                        .setFontSize(18)
                                                        .setFontColor(textColor)
                                                        .setTextAlignment(TextAlignment.CENTER)
                                                        .setMarginBottom(10));
                                        document.add(new Paragraph(
                                                        "______________________________________________________________________________"));

                                        // Tambahkan deskripsi profil
                                        document.add(new Paragraph("Profile")
                                                        .setFontSize(14)
                                                        .setBold()
                                                        .setFontColor(ColorConstants.WHITE)
                                                        .setBackgroundColor(headerBgColor)
                                                        .setPadding(5)
                                                        .setMarginBottom(10));
                                        document.add(new Paragraph(data_pdf.getProfileDesc())
                                                        .setFontSize(12)
                                                        .setFontColor(textColor)
                                                        .setMarginBottom(20));
                                        document.add(new Paragraph(
                                                        "______________________________________________________________________________"));

                                        // Tambahkan detail kontak
                                        document.add(new Paragraph("Contact Details")
                                                        .setFontSize(14)
                                                        .setBold()
                                                        .setFontColor(ColorConstants.WHITE)
                                                        .setBackgroundColor(headerBgColor)
                                                        .setPadding(5)
                                                        .setMarginBottom(10));
                                        Table contactTable = new Table(
                                                        UnitValue.createPercentArray(new float[] { 1, 3 }));
                                        contactTable.setWidth(UnitValue.createPercentValue(100));
                                        contactTable.addCell(new Cell()
                                                        .add(new Paragraph("Phone:").setFontSize(12).setBold()
                                                                        .setFontColor(textColor))
                                                        .setBorder(Border.NO_BORDER)
                                                        .setBackgroundColor(sectionBgColor));
                                        contactTable.addCell(new Cell()
                                                        .add(new Paragraph(String.valueOf(data_pdf.getPhoneNum()))
                                                                        .setFontSize(12).setFontColor(textColor))
                                                        .setBorder(Border.NO_BORDER)
                                                        .setBackgroundColor(sectionBgColor));
                                        contactTable.addCell(new Cell()
                                                        .add(new Paragraph("Address:").setFontSize(12).setBold()
                                                                        .setFontColor(textColor))
                                                        .setBorder(Border.NO_BORDER)
                                                        .setBackgroundColor(sectionBgColor));
                                        contactTable.addCell(new Cell()
                                                        .add(new Paragraph(data_pdf.getAddress()).setFontSize(12)
                                                                        .setFontColor(textColor))
                                                        .setBorder(Border.NO_BORDER)
                                                        .setBackgroundColor(sectionBgColor));
                                        document.add(contactTable);
                                        document.add(new Paragraph(
                                                        "______________________________________________________________________________"));

                                        // Tambahkan keterampilan
                                        document.add(new Paragraph("Skills")
                                                        .setFontSize(14)
                                                        .setBold()
                                                        .setFontColor(ColorConstants.WHITE)
                                                        .setBackgroundColor(headerBgColor)
                                                        .setPadding(5)
                                                        .setMarginBottom(10));
                                        Table skillsTable = new Table(
                                                        UnitValue.createPercentArray(new float[] { 1, 1 }));
                                        skillsTable.setWidth(UnitValue.createPercentValue(100));
                                        skillsTable.addCell(new Cell()
                                                        .add(new Paragraph(data_pdf.getSkill1()).setFontSize(12)
                                                                        .setFontColor(textColor))
                                                        .setBorder(Border.NO_BORDER)
                                                        .setBackgroundColor(sectionBgColor));
                                        skillsTable.addCell(new Cell()
                                                        .add(new Paragraph(data_pdf.getSkill2()).setFontSize(12)
                                                                        .setFontColor(textColor))
                                                        .setBorder(Border.NO_BORDER)
                                                        .setBackgroundColor(sectionBgColor));
                                        skillsTable.addCell(new Cell()
                                                        .add(new Paragraph(data_pdf.getSkill3()).setFontSize(12)
                                                                        .setFontColor(textColor))
                                                        .setBorder(Border.NO_BORDER)
                                                        .setBackgroundColor(sectionBgColor));
                                        skillsTable.addCell(new Cell()
                                                        .add(new Paragraph(data_pdf.getSkill4()).setFontSize(12)
                                                                        .setFontColor(textColor))
                                                        .setBorder(Border.NO_BORDER)
                                                        .setBackgroundColor(sectionBgColor));
                                        document.add(skillsTable);
                                        document.add(new Paragraph(
                                                        "______________________________________________________________________________"));

                                        // Tambahkan pendidikan
                                        document.add(new Paragraph("Education")
                                                        .setFontSize(14)
                                                        .setBold()
                                                        .setFontColor(ColorConstants.WHITE)
                                                        .setBackgroundColor(headerBgColor)
                                                        .setPadding(5)
                                                        .setMarginBottom(10));
                                        Table educationTable = new Table(
                                                        UnitValue.createPercentArray(new float[] { 1 }));
                                        educationTable.setWidth(UnitValue.createPercentValue(100));
                                        educationTable.addCell(new Cell().add(new Paragraph(data_pdf.getSchoolName())
                                                        .setFontSize(14)
                                                        .setBold()
                                                        .setFontColor(textColor)).setBorder(Border.NO_BORDER)
                                                        .setBackgroundColor(sectionBgColor));
                                        educationTable.addCell(new Cell()
                                                        .add(new Paragraph("School:").setFontSize(12).setBold()
                                                                        .setFontColor(textColor))
                                                        .setBorder(Border.NO_BORDER)
                                                        .setBackgroundColor(sectionBgColor));
                                        educationTable.addCell(new Cell()
                                                        .add(new Paragraph(data_pdf.getSchoolName()).setFontSize(12)
                                                                        .setFontColor(textColor))
                                                        .setBorder(Border.NO_BORDER)
                                                        .setBackgroundColor(sectionBgColor));
                                        educationTable.addCell(new Cell()
                                                        .add(new Paragraph("Location:").setFontSize(12).setBold()
                                                                        .setFontColor(textColor))
                                                        .setBorder(Border.NO_BORDER)
                                                        .setBackgroundColor(sectionBgColor));
                                        educationTable.addCell(new Cell()
                                                        .add(new Paragraph(data_pdf.getSchoolLoc()).setFontSize(12)
                                                                        .setFontColor(textColor))
                                                        .setBorder(Border.NO_BORDER)
                                                        .setBackgroundColor(sectionBgColor));
                                        educationTable.addCell(new Cell()
                                                        .add(new Paragraph("Degree:").setFontSize(12).setBold()
                                                                        .setFontColor(textColor))
                                                        .setBorder(Border.NO_BORDER)
                                                        .setBackgroundColor(sectionBgColor));
                                        educationTable.addCell(new Cell()
                                                        .add(new Paragraph(data_pdf.getDegrees()).setFontSize(12)
                                                                        .setFontColor(textColor))
                                                        .setBorder(Border.NO_BORDER)
                                                        .setBackgroundColor(sectionBgColor));
                                        educationTable.addCell(new Cell()
                                                        .add(new Paragraph("Field of Study:").setFontSize(12).setBold()
                                                                        .setFontColor(textColor))
                                                        .setBorder(Border.NO_BORDER)
                                                        .setBackgroundColor(sectionBgColor));
                                        educationTable.addCell(new Cell()
                                                        .add(new Paragraph(data_pdf.getField()).setFontSize(12)
                                                                        .setFontColor(textColor))
                                                        .setBorder(Border.NO_BORDER)
                                                        .setBackgroundColor(sectionBgColor));
                                        educationTable.addCell(new Cell()
                                                        .add(new Paragraph("Graduation:").setFontSize(12).setBold()
                                                                        .setFontColor(textColor))
                                                        .setBorder(Border.NO_BORDER)
                                                        .setBackgroundColor(sectionBgColor));
                                        educationTable.addCell(new Cell()
                                                        .add(new Paragraph(data_pdf.getGradStart() + " - "
                                                                        + data_pdf.getGradEnd()).setFontSize(12)
                                                                        .setFontColor(textColor))
                                                        .setBorder(Border.NO_BORDER)
                                                        .setBackgroundColor(sectionBgColor));
                                        document.add(educationTable);

                                        document.add(new Paragraph(
                                                        "______________________________________________________________________________"));

                                        // Tambahkan pengalaman kerja
                                        document.add(new Paragraph("Experience")
                                                        .setFontSize(14)
                                                        .setBold()
                                                        .setFontColor(ColorConstants.WHITE)
                                                        .setBackgroundColor(headerBgColor)
                                                        .setPadding(5)
                                                        .setMarginBottom(10));
                                        Table workTable = new Table(UnitValue.createPercentArray(new float[] { 1 }));
                                        workTable.setWidth(UnitValue.createPercentValue(100));
                                        workTable.addCell(new Cell().add(new Paragraph(data_pdf.getWorkplace1())
                                                        .setFontSize(12)
                                                        .setBold()
                                                        .setFontColor(textColor)).setBorder(Border.NO_BORDER)
                                                        .setBackgroundColor(sectionBgColor));
                                        workTable.addCell(new Cell().add(new Paragraph(data_pdf.getPos1())
                                                        .setFontSize(12).setFontColor(textColor))
                                                        .setBorder(Border.NO_BORDER)
                                                        .setBackgroundColor(sectionBgColor));
                                        workTable.addCell(new Cell()
                                                        .add(new Paragraph(String.valueOf(data_pdf.getYear1()))
                                                                        .setFontSize(12).setFontColor(textColor))
                                                        .setBorder(Border.NO_BORDER)
                                                        .setBackgroundColor(sectionBgColor));
                                        workTable.addCell(new Cell().add(new Paragraph(data_pdf.getWorkplace2())
                                                        .setFontSize(12)
                                                        .setBold()
                                                        .setFontColor(textColor)).setBorder(Border.NO_BORDER)
                                                        .setBackgroundColor(sectionBgColor));
                                        workTable.addCell(new Cell().add(new Paragraph(data_pdf.getPos2())
                                                        .setFontSize(12).setFontColor(textColor))
                                                        .setBorder(Border.NO_BORDER)
                                                        .setBackgroundColor(sectionBgColor));
                                        workTable.addCell(new Cell()
                                                        .add(new Paragraph(String.valueOf(data_pdf.getYear2()))
                                                                        .setFontSize(12).setFontColor(textColor))
                                                        .setBorder(Border.NO_BORDER)
                                                        .setBackgroundColor(sectionBgColor));
                                        document.add(workTable);
                                        document.add(new Paragraph(
                                                        "______________________________________________________________________________"));

                                        document.close();
                                        pdf.close();
                                        writer.close();

                                        System.out.println("PDF created successfully!");
                                } catch (Exception e) {
                                        e.printStackTrace();
                                }
                        });
                        vBox.getChildren().add(btn);
                }

        }
}
