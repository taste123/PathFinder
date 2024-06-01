/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package pathfinder;

import java.io.FileNotFoundException;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pathfinder.database.DBControler;
import pathfinder.models.User;
import pathfinder.models.data_pdf;

public class App extends Application {

    DBControler dbControler = new DBControler();

    @Override
    public void start(Stage primaryStage) {
        AnchorPane root = new AnchorPane();
        root.getStylesheets().add(App.class.getResource("/root1style.css").toExternalForm());

        Image image1 = new Image("scene1.png");
        ImageView imageView1 = new ImageView(image1);
        imageView1.setFitWidth(370);
        imageView1.setFitHeight(200);
        imageView1.setX(313);
        imageView1.setY(124);

        Button register = new Button("Register");
        register.setId("register1");
        register.setLayoutX(310);
        register.setLayoutY(525);

        Button login = new Button("Login");
        login.setId("login1");
        login.setLayoutX(620);
        login.setLayoutY(526.0);

        TextField textField1 = new TextField();
        textField1.setId("password-field");
        textField1.setPromptText("password");
        textField1.setLayoutX(345);
        textField1.setLayoutY(474);
        textField1.setPrefWidth(305);

        TextField textField2 = new TextField();
        textField2.setId("username-field");
        textField2.setPromptText("username");
        textField2.setLayoutX(347);
        textField2.setLayoutY(417);
        textField2.getStyleClass().add("password-field");
        textField2.setPrefWidth(305);

        Text text = new Text("SmartCV");
        text.setLayoutX(460);
        text.setLayoutY(70);
        text.getStyleClass().add("text-label");

        root.getChildren().addAll(imageView1, login, register, textField1, textField2, text);

        Scene scene = new Scene(root, 1000, 600);
        scene.getStylesheets().add(App.class.getResource("/scene1style.css").toExternalForm());

        AnchorPane root2 = new AnchorPane();
        root2.getStylesheets().add(App.class.getResource("/root2style.css").toExternalForm());

        Button btn1 = new Button("Back");
        btn1.setId("back-1");
        btn1.setLayoutX(320);
        btn1.setLayoutY(526);
        btn1.setOnAction(e -> primaryStage.setScene(scene));

        Text header = new Text("Create Account");
        header.setLayoutX(450);
        header.setLayoutY(70);

        TextField name = new TextField();
        name.setPromptText("name");
        name.setLayoutX(260);
        name.setLayoutY(245);
        name.getStyleClass().add("text-field");
        name.setPrefWidth(480);

        TextField username = new TextField();
        username.setPromptText("username");
        username.setLayoutX(260);
        username.setLayoutY(292);
        username.setPrefWidth(480);
        username.getStyleClass().add("text-field");

        TextField email = new TextField();
        email.setPromptText("email");
        email.setLayoutX(260);
        email.setLayoutY(329);
        email.setPrefWidth(480);
        email.getStyleClass().add("text-field");

        TextField password = new TextField();
        password.setPromptText("password");
        password.setLayoutX(260);
        password.setLayoutY(373);
        password.setPrefWidth(480);
        password.getStyleClass().add("text-field");

        TextField retype = new TextField();
        retype.setPromptText("retype password");
        retype.getStyleClass().add("text-field");
        retype.setLayoutX(260);
        retype.setLayoutY(420);
        retype.setPrefWidth(480);

        Image image2 = new Image("scene2.png");
        ImageView imageView2 = new ImageView(image2);
        imageView2.setFitWidth(95);
        imageView2.setFitHeight(95);
        imageView2.setX(450);
        imageView2.setY(98);

        Button btn2 = new Button("Register");
        btn2.setId("btn2");
        btn2.setLayoutX(610);
        btn2.setLayoutY(526);
        btn2.setOnAction(e -> {
            dbControler.insertUser(username.getText(), password.getText(), name.getText(), email.getText());
            primaryStage.setScene(scene);
        });

        root2.getChildren().addAll(imageView2, header, btn1, btn2, name, username, email, password, retype);
        Scene scene2 = new Scene(root2, 1000, 600);
        scene2.getStylesheets().add(App.class.getResource("/scene2style.css").toExternalForm());

        register.setOnAction(e -> {
            primaryStage.setScene(scene2);
            primaryStage.show();
        });

        AnchorPane root3 = new AnchorPane();
        root3.getStylesheets().add(App.class.getResource("/scene3style.css").toExternalForm());

        Text welcomText = new Text("Welcome, 'username'");
        welcomText.setX(180);
        welcomText.setY(55);

        Image image3 = new Image("scene3.png");
        ImageView imageView3 = new ImageView(image3);
        imageView3.setFitHeight(360);
        imageView3.setFitWidth(370);
        imageView3.setX(287);
        imageView3.setY(105);

        Button addCV = new Button("+");
        addCV.setLayoutX(865);
        addCV.setLayoutY(480);
        addCV.setPrefSize(95, 95);

        Button back = new Button("back");
        back.setLayoutX(40);
        back.setLayoutY(480);
        back.setPrefSize(95, 95);
        back.setOnAction(e -> primaryStage.setScene(scene));

        System.out.println(textField2.getText() + "1111");

        VBox vBox = new VBox();

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(vBox);
        scrollPane.setLayoutX(400);
        scrollPane.setLayoutY(200);

        root3.getChildren().addAll(welcomText, imageView3, addCV, back, scrollPane);
        Scene scene3 = new Scene(root3, 1000, 600);

        login.setOnAction(e -> {
            User user = dbControler.getUserByUsername(textField2.getText());

            ObservableList<data_pdf> listPdf = dbControler.getDataPdfByUsername(textField2.getText());
            System.out.println("value of list : " + String.valueOf(listPdf.size()));
            vBox.getChildren().clear();
            for (data_pdf data_pdf : listPdf) {
                Button btn = new Button(data_pdf.getFullname());
                btn.setOnAction(i -> {
                    System.out.println(textField2.getText() + " <= username");
                    String pdfPath = "output.pdf";
                    PdfWriter writer;
                    try {
                        writer = new PdfWriter(pdfPath);
                        com.itextpdf.kernel.pdf.PdfDocument pdf = new com.itextpdf.kernel.pdf.PdfDocument(writer);
                        Document document = new Document(pdf);
                        document.add(new Paragraph("Name: " + data_pdf.getFullname()));
                        document.close();
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }
                });
                vBox.getChildren().add(btn);
            }
            System.out.println(textField1.getText());
            System.out.println(user.getPassword());
            if (user.getPassword().equals(textField1.getText())) {
                primaryStage.setScene(scene3);
            } else {
                textField1.setText("wrong password");
            }
        });

        AnchorPane root4 = new AnchorPane();
        root4.getStylesheets().add(App.class.getResource("/scene3style.css").toExternalForm());

        Text profile = new Text("Profile");
        profile.setLayoutX(105);
        profile.setLayoutY(60);
        profile.setFill(Color.web("#813b82"));

        TextField fullname = new TextField();
        fullname.setPromptText("fullname");
        fullname.setLayoutX(90);
        fullname.setLayoutY(170);
        fullname.setPrefWidth(640);

        TextField jobTitle = new TextField();
        jobTitle.setPromptText("job title");
        jobTitle.setLayoutX(90);
        jobTitle.setLayoutY(230);
        jobTitle.setPrefWidth(640);

        TextField profileDesc = new TextField();
        profileDesc.setPromptText("profile description");
        profileDesc.setLayoutX(90);
        profileDesc.setLayoutY(290);
        profileDesc.setPrefWidth(640);

        TextField phoneNum = new TextField();
        phoneNum.setPromptText("phone number");
        phoneNum.setLayoutX(90);
        phoneNum.setLayoutY(350);
        phoneNum.setPrefWidth(640);

        TextField address = new TextField();
        address.setPromptText("address");
        address.setLayoutX(90);
        address.setLayoutY(410);
        address.setPrefWidth(640);

        Text textcreate = new Text("let's create your VC!");
        textcreate.setLayoutX(105);
        textcreate.setLayoutY(108);
        textcreate.setFill(Color.web("#813b82"));

        Text inputPhoto = new Text("add your profile picture");
        inputPhoto.setLayoutX(778);
        inputPhoto.setLayoutY(250);

        Button addPhoto = new Button("+");
        addPhoto.setLayoutX(778);
        addPhoto.setLayoutY(260);
        addPhoto.setPrefSize(130, 105);

        Button next4 = new Button(">");
        next4.setId("next4");
        next4.setPrefSize(60, 60);
        next4.setLayoutX(540);
        next4.setLayoutY(501);

        Button prev4 = new Button("<");
        prev4.setId("prev4");
        prev4.setPrefSize(60, 60);
        prev4.setLayoutX(396);
        prev4.setLayoutY(501);

        root4.getChildren().addAll(profile, fullname, jobTitle, profileDesc, phoneNum, address, textcreate, inputPhoto,
                addPhoto, prev4, next4);

        Scene scene4 = new Scene(root4, 1000, 600);
        scene4.getStylesheets().add(App.class.getResource("/scene4style.css").toExternalForm());

        addCV.setOnAction(e -> primaryStage.setScene(scene4));
        prev4.setOnAction(e -> primaryStage.setScene(scene3));

        AnchorPane root5 = new AnchorPane();
        root5.getStylesheets().add(App.class.getResource("/scene5style.css").toExternalForm());

        Text profileText = new Text(107, 90, "Education");
        profileText.setFill(Color.web("#813b82"));
        Text descriptionText = new Text(107, 138, "tell us about your education");
        descriptionText.setFill(Color.web("#813b82"));

        TextField schoolName = new TextField();
        schoolName.setPromptText("school name");
        schoolName.setLayoutX(93);
        schoolName.setLayoutY(150);
        schoolName.setPrefWidth(640);

        TextField schoolLoc = new TextField();
        schoolLoc.setPromptText("school location");
        schoolLoc.setLayoutX(93);
        schoolLoc.setLayoutY(200);
        schoolLoc.setPrefWidth(640);

        TextField degrees = new TextField();
        degrees.setPromptText("degrees");
        degrees.setLayoutX(93);
        degrees.setLayoutY(250);
        degrees.setPrefWidth(640);

        TextField field = new TextField();
        field.setPromptText("field");
        field.setLayoutX(93);
        field.setLayoutY(300);
        field.setPrefWidth(640);

        TextField gradStart = new TextField();
        gradStart.setPromptText("graduation start year");
        gradStart.setLayoutX(93);
        gradStart.setLayoutY(350);
        gradStart.setPrefWidth(640);

        TextField gradEnd = new TextField();
        gradEnd.setPromptText("graduation end year");
        gradEnd.setLayoutX(93);
        gradEnd.setLayoutY(400);
        gradEnd.setPrefWidth(640);

        Button next5 = new Button(">");
        next5.setId("next5");
        next5.setPrefSize(60, 60);
        next5.setLayoutX(540);
        next5.setLayoutY(501);

        Button prev5 = new Button("<");
        prev5.setId("prev5");
        prev5.setPrefSize(60, 60);
        prev5.setLayoutX(396);
        prev5.setLayoutY(501);

        Image image5 = new Image("scene5.png");
        ImageView pict5 = new ImageView(image5);
        pict5.setFitHeight(130);
        pict5.setFitWidth(125);
        pict5.setX(800);
        pict5.setY(230);

        root5.getChildren().addAll(pict5, profileText, descriptionText, schoolName, schoolLoc, degrees, field,
                gradStart,
                gradEnd, next5, prev5);

        Scene scene5 = new Scene(root5, 1000, 600);
        scene5.getStylesheets().add(App.class.getResource("/scene6style.css").toExternalForm());

        next4.setOnAction(e -> primaryStage.setScene(scene5));
        prev5.setOnAction(e -> primaryStage.setScene(scene4));

        AnchorPane root6 = new AnchorPane();
        root6.getStylesheets().add(App.class.getResource("/scene7style.css").toExternalForm());

        Text workExp = new Text(40, 60, "Work Experience");
        workExp.setFill(Color.web("#813b82"));

        TextField workplace1 = new TextField();
        workplace1.setPromptText("first workplace");
        workplace1.setLayoutX(93);
        workplace1.setLayoutY(150);
        workplace1.setPrefWidth(640);

        TextField position1 = new TextField();
        position1.setPromptText("position 1");
        position1.setLayoutX(93);
        position1.setLayoutY(200);
        position1.setPrefWidth(640);

        TextField year1 = new TextField();
        year1.setPromptText("year 1");
        year1.setLayoutX(93);
        year1.setLayoutY(250);
        year1.setPrefWidth(640);

        TextField workplace2 = new TextField();
        workplace2.setPromptText("second workplace");
        workplace2.setLayoutX(93);
        workplace2.setLayoutY(300);
        workplace2.setPrefWidth(640);

        TextField position2 = new TextField();
        position2.setPromptText("position 2");
        position2.setLayoutX(93);
        position2.setLayoutY(350);
        position2.setPrefWidth(640);

        TextField year2 = new TextField();
        year2.setPromptText("year 2");
        year2.setLayoutX(93);
        year2.setLayoutY(400);
        year2.setPrefWidth(640);

        Text addText = new Text(40, 75, "add your work experience");
        addText.setFill(Color.web("#813b82"));
        Button next6 = new Button(">");
        next6.setId("next6");
        next6.setLayoutX(540);
        next6.setLayoutY(501);
        next6.setPrefSize(60, 60);

        Button prev6 = new Button("<");
        prev6.setId("prev6");
        prev6.setLayoutX(396);
        prev6.setLayoutY(501);
        prev6.setPrefSize(60, 60);

        Image image6 = new Image("scene6.png");
        ImageView pict6 = new ImageView(image6);
        pict6.setFitHeight(130);
        pict6.setFitWidth(125);
        pict6.setX(800);
        pict6.setY(230);

        root6.getChildren().addAll(pict6, workExp, position1, workplace1, year1, position2, workplace2, year2, addText,
                next6, prev6);

        Scene scene6 = new Scene(root6, 1000, 600);
        scene6.getStylesheets().add(App.class.getResource("/scene8style.css").toExternalForm());
        next5.setOnAction(e -> primaryStage.setScene(scene6));
        prev6.setOnAction(e -> primaryStage.setScene(scene5));

        AnchorPane root7 = new AnchorPane();
        root.setPrefSize(487, 352);
        root7.getStylesheets().add(App.class.getResource("/scene9style.css").toExternalForm());

        Text skillsTitle = new Text("Skills");
        skillsTitle.setFill(Color.web("#813b82"));
        skillsTitle.setLayoutX(107);
        skillsTitle.setLayoutY(90);

        TextField skill1 = new TextField();
        skill1.setPromptText("skill 1");
        skill1.setLayoutX(93);
        skill1.setLayoutY(150);
        skill1.setPrefWidth(640);

        TextField skill2 = new TextField();
        skill2.setPromptText("skill 2");
        skill2.setLayoutX(93);
        skill2.setLayoutY(230);
        skill2.setPrefWidth(640);

        TextField skill3 = new TextField();
        skill3.setPromptText("skill 3");
        skill3.setLayoutX(93);
        skill3.setLayoutY(310);
        skill3.setPrefWidth(640);

        TextField skill4 = new TextField();
        skill4.setPromptText("skill 4");
        skill4.setLayoutX(93);
        skill4.setLayoutY(390);
        skill4.setPrefWidth(640);

        Text addSkills = new Text("add your skills");
        addSkills.setFill(Color.web("#813b82"));
        addSkills.setLayoutX(107);
        addSkills.setLayoutY(138);

        Button saveAndCreate = new Button("save & create");
        saveAndCreate.setId("saveandcreate");
        saveAndCreate.setLayoutX(860);
        saveAndCreate.setLayoutY(515);
        saveAndCreate.setPrefHeight(60);

        Button prev7 = new Button("<");
        prev7.setId("prev7");
        prev7.setLayoutX(50);
        prev7.setLayoutY(515);
        prev7.setPrefSize(60, 60);

        Image image7 = new Image("scene7.png");
        ImageView pict7 = new ImageView(image7);
        pict7.setFitHeight(130);
        pict7.setFitWidth(125);
        pict7.setX(800);
        pict7.setY(230);

        root7.getChildren().addAll(pict7, skillsTitle, skill1, skill2, skill3, skill4, addSkills, saveAndCreate, prev7);

        Scene scene7 = new Scene(root7, 1000, 600);
        scene7.getStylesheets().add(App.class.getResource("/scene10style.css").toExternalForm());
        next6.setOnAction(e -> primaryStage.setScene(scene7));
        prev7.setOnAction(e -> primaryStage.setScene(scene6));
        saveAndCreate.setOnAction(e -> {
            dbControler.insertDataPDF(fullname.getText(), jobTitle.getText(), profileDesc.getText(),
                    Integer.parseInt(phoneNum.getText()),
                    address.getText(), schoolName.getText(), schoolLoc.getText(), degrees.getText(), field.getText(),
                    Integer.parseInt(gradStart.getText()), Integer.parseInt(gradStart.getText()), workplace1.getText(),
                    position1.getText(),
                    Integer.parseInt(year1.getText()), workplace2.getText(), position2.getText(),
                    Integer.parseInt(year2.getText()), skill1.getText(),
                    skill2.getText(), skill3.getText(), skill4.getText(), textField2.getText());
            primaryStage.setScene(scene3);
        });

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
