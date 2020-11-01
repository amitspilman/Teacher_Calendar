package TeacherCalendar;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;
import java.text.SimpleDateFormat;  

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application {


	//main
	public static void main(String[] args) {		
		launch(args);
	}
	//start	*******
	public void start(Stage stage) throws IOException{
		stage.setMinHeight(450);
		stage.setMinWidth (800);
		stage.setMaxHeight(650);
		stage.setMaxWidth (1100);

		stage.setTitle("Dor & Amit");
		ArrayList<Plan> list = new ArrayList<Plan>();
		list = loadData("resources/Plans.json");


		stage.setScene(MainScene(list));		
		stage.show();
	}
	public Scene MainScene(ArrayList<Plan> list)  throws IOException{
		BorderPane mainGui = new BorderPane();
		BorderPane topGui = new BorderPane();
		HBox hbox = new HBox();

		boolean resetButtonDisable = true;
		
		topGui.setPadding(new Insets(10));
		
		
		Button homeBtn = new Button("Home");
		Button plusBtn = new Button("+");
		Button resetDataBtn = new Button("reset");

//		ImageView addImage = new ImageView(new Image("resources/Add.png"));
//		ImageView homeImage = new ImageView(new Image("resources/Home.png"));
//		ImageView resetImage = new ImageView(new Image("resources/Reset.png"));
//
//		homeBtn.setGraphic(addImage);
//		plusBtn.setGraphic(homeImage);
//		resetDataBtn.setGraphic(resetImage);
//
//		addImage.setFitHeight(10);
//		addImage.setFitWidth(10);
//		homeImage.setFitHeight(10);
//		homeImage.setFitWidth(10);
//		resetImage.setFitHeight(10);
//		resetImage.setFitWidth(10);
		
		
		resetDataBtn.setDisable(resetButtonDisable);

		mainGui.setTop(topGui);
		mainGui.setCenter(hbox);
		mainGui.setBottom(resetDataBtn);
		topGui.setLeft(plusBtn);
		topGui.setRight(homeBtn);

		//when button pressed, the appropriate function will executed.
		plusBtn.setOnAction(e -> addPlanGui(hbox,list));
		resetDataBtn.setOnAction(e -> reset(hbox,list));
		homeBtn.setOnAction(e -> Home(hbox, list));

		updateList(hbox, list);
		return new Scene(mainGui);
	}

	//Home page button
	public void Home(HBox hbox, ArrayList<Plan> list) {
		hbox.getChildren().clear();
		hbox.setAlignment(Pos.CENTER);
		updateList(hbox,list);
	}
	//add Plan button
	public void addPlanGui(HBox hbox, ArrayList<Plan> list) {
		//	Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12);
		hbox.getChildren().clear();

		Button submit = new Button("submit");
		Button cancel = new Button("cancel");

		//Text area
		TextArea area_title = new TextArea();
		TextArea area_duration = new TextArea();
		TextArea area_goal = new TextArea();
		TextArea area_accessories = new TextArea();
		TextArea area_opening = new TextArea();
		TextArea area_budy = new TextArea();
		TextArea area_summary = new TextArea();

		area_title.setText("Enter your title here");
		area_duration.setText("duration");
		area_goal.setText("Enter your goal here");
		area_accessories.setText("Accessories section..");
		area_opening.setText("opening section..");
		area_budy.setText("budy section..");
		area_summary.setText("Enter your Summary here");

		area_title.setPrefColumnCount(115);
		area_title.setPrefHeight(120);
		area_title.setPrefWidth(300);
		area_duration.setPrefColumnCount(15);
		area_duration.setPrefHeight(120);
		area_duration.setPrefWidth(300);
		area_goal.setPrefColumnCount(15);
		area_goal.setPrefHeight(120);
		area_goal.setPrefWidth(300);
		area_accessories.setPrefColumnCount(15);
		area_accessories.setPrefHeight(120);
		area_accessories.setPrefWidth(300);
		area_opening.setPrefColumnCount(15);
		area_opening.setPrefHeight(120);
		area_opening.setPrefWidth(300);
		area_budy.setPrefColumnCount(15);
		area_budy.setPrefHeight(120);
		area_budy.setPrefWidth(300);
		area_summary.setPrefColumnCount(15);
		area_summary.setPrefHeight(120);
		area_summary.setPrefWidth(300);

		GridPane gridPane = new GridPane();

		gridPane.add(new Label("כותרת"), 3, 0, 1, 1);
		gridPane.add(area_title, 2, 0, 1, 1);

		gridPane.add(new Label("משך השיעור"), 1, 0, 1, 1);
		gridPane.add(area_duration, 0, 0, 1, 1);

		gridPane.add(new Label("מטרה"), 3, 1, 1, 1);
		gridPane.add(area_goal, 2, 1, 1, 1);

		gridPane.add(new Label("עזרים"), 1, 1, 1, 1);
		gridPane.add(area_accessories, 0, 1, 1, 1);

		gridPane.add(new Label("פתיחה"), 3, 2, 1, 1);
		gridPane.add(area_opening, 2, 2, 1, 1);

		gridPane.add(new Label("גוף"), 1, 2, 1, 1);
		gridPane.add(area_budy, 0, 2, 1, 1);

		gridPane.add(new Label("תקציר"), 3, 3, 1, 1);
		gridPane.add(area_summary, 2, 3, 1, 1);

		gridPane.setHgap(10); //horizontal gap in pixels => that's what you are asking for
		gridPane.setVgap(10); //vertical gap in pixels
		gridPane.setPadding(new Insets(10, 10, 10, 10)); //(top/right/bottom/left)


		VBox vbox = new VBox();
		HBox hbox2 = new HBox();
		hbox.getChildren().add(vbox);

		vbox.getChildren().add(gridPane);
		vbox.getChildren().add(hbox2);	 
		hbox2.getChildren().add(submit);	 
		hbox2.getChildren().add(cancel);	
		hbox2.setSpacing(15);


		// action event 
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent e) 
			{ 
				String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
				Plan plan = new Plan(time,area_title.getText(),area_duration.getText(),area_goal.getText(),area_accessories.getText(),area_opening.getText(),area_budy.getText(),area_summary.getText());
				list.add(plan);
				saveData(list);
				updateList(hbox,list);
			} 
		}; 
		// when button is pressed 
		submit.setOnAction(event); 
		cancel.setOnAction(e ->  updateList(hbox, list));        
	}
	//reset button
	public void reset(HBox hbox, ArrayList<Plan> list) {
		list.clear();
		saveData(list);
		updateList(hbox, list);
	}


	//save data
	@SuppressWarnings("unchecked")
	public void saveData(ArrayList<Plan> array) {
		JSONArray jsonArray = new JSONArray();


		for (int i = 0;i < array.size() ; i++) {
			JSONObject obj = new JSONObject();
			Plan plan = array.get(i);

			obj.put("Time", plan.getPlanTime());
			obj.put("Title", plan.getTitle());
			obj.put("Duration",  plan.getDuration());
			obj.put("Goal",  plan.getGoal());
			obj.put("Accessories", plan.getAccessories());
			obj.put("Opening",  plan.getOpening());
			obj.put("Budy",  plan.getBudy());
			obj.put("Summary",  plan.getSummary());
			jsonArray.add(new JSONObject(obj));
		}


		try (FileWriter file = new FileWriter("resources/Amit.json")) {
			file.write(jsonArray.toString());
		} catch(Exception e){System.out.println(e.getStackTrace());}
	}
	//load data
	public ArrayList<Plan> loadData(String path) {
		ArrayList<Plan> array = new ArrayList<Plan>();
		JSONParser parser = new JSONParser();
		JSONArray jsonArray = new JSONArray();

		try (FileReader file = new FileReader(path)){
			jsonArray = (JSONArray) parser.parse(file);
		}catch (Exception e) {e.printStackTrace();}

		for (Object o : jsonArray) {
			JSONObject obj = (JSONObject) o;
			Plan plan = new Plan();
			plan.setPlanTime((String)obj.get("Time"));
			plan.setTitle((String)obj.get("Title"));
			plan.setDuration((String)obj.get("Duration"));
			plan.setGoal((String)obj.get("Goal"));
			plan.setAccessories((String)obj.get("Accessories"));
			plan.setOpening((String)obj.get("Opening"));
			plan.setBudy((String)obj.get("Budy"));
			plan.setSummary((String)obj.get("Summary"));
			array.add(new Plan(plan));
		}



		return array;
	}
	//show Plan list
	public void updateList(HBox hbox, ArrayList<Plan> list) {
		ListView<HBox> listView = new ListView<HBox>();

		int listWidth = 650;
		int listHeight = 300;

		listView.setPrefWidth(listWidth);
		listView.setPrefHeight(listHeight);	
		hbox.getChildren().clear();

		for (int i =0; i < list.size(); i++) {
			HBox newHbox = new HBox();
			VBox left = new VBox();
			VBox right = new VBox();
			Plan plan = list.get(i);

			right.setAlignment(Pos.TOP_RIGHT);
			left.setAlignment(Pos.TOP_LEFT);
			newHbox.getChildren().addAll(left,right);

			Button btn = new Button("PDF");
			btn.setOnAction(e -> createNewPDF(plan));
			left.getChildren().add(new Text(plan.toString()));
			right.getChildren().add(btn);
			listView.getItems().add(newHbox);
		}
		hbox.getChildren().add(listView);


	}
	//create new pdf
	public void createNewPDF(Plan plan) {
		PDDocument document = new PDDocument();
		PDPage page = new PDPage();
		document.addPage(page);

		String download_path = "C:\\Users\\spilm\\Desktop";
		String fileName = plan.getPlanTime();

		writeToPDF(document, page, plan);
		saveAsPDF(document, fileName, download_path);
	}
	//write to pdf
	public void writeToPDF(PDDocument document, PDPage page, Plan plan) {
		PDFont font = PDType1Font.HELVETICA_BOLD;
		PDPageContentStream contentStream;

		String title = plan.getTitle();
		String duration = plan.getDuration();
		String goal = plan.getGoal();
		String accessories = plan.getAccessories();

		String opening = plan.getOpening();
		String budy = plan.getBudy();
		String summary = plan.getSummary();

		try {
			contentStream = new PDPageContentStream(document, page);
			contentStream.beginText();
			contentStream.setFont(font, 12 );
			contentStream.moveTextPositionByAmount(150, 700);
			contentStream.drawString(title);
			contentStream.moveTextPositionByAmount(0, -20);
			contentStream.drawString(duration);
			contentStream.moveTextPositionByAmount(0, -20);
			contentStream.drawString(goal);
			contentStream.moveTextPositionByAmount(0, -20);
			contentStream.drawString(accessories);
			contentStream.moveTextPositionByAmount(0, -20);
			contentStream.drawString(opening);
			contentStream.moveTextPositionByAmount(0, -20);
			contentStream.drawString(budy);
			contentStream.moveTextPositionByAmount(0, -20);
			contentStream.drawString(summary);
			contentStream.endText();
			contentStream.close();
		} 
		catch (IOException e){e.printStackTrace();}

	}
	//save to pdf
	public void saveAsPDF(PDDocument document, String fileName, String download_path) {
		try {
			document.save(download_path+ "\\"+  fileName +".pdf");
			document.close();
		}
		catch (COSVisitorException e){e.printStackTrace();}
		catch(IOException e){e.printStackTrace();}

	}




	//im trying to send an Email..
	public void sendEmail() {
		final String username = "spilmanamit@gmail.com";
		final String password = "amitSH@12";
		final String destination = "dor.shte@gmail.com";

		Properties props = new Properties();

		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(destination));
			message.setSubject("Hey Dor");
			message.setText("Love you");

			Transport.send(message);

			System.out.println("Mail Sent Successfully");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}


}