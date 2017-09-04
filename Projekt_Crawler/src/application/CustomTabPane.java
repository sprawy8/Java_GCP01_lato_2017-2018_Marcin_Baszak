package application;

import java.io.FileWriter;
import java.io.BufferedWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class CustomTabPane extends AnchorPane{

	
	Student tempStudent;
	Tab studentsTab;
	LogTab logTab;
	HistogramTab histogramTab;
	TabPane tabPane;
	CustomMenuBar customMenuBar;
	
	
	ObservableList<Student> studentsObservable = FXCollections.observableArrayList();
	
	Logger guiLogger;
	
	
	Button addButton;
	Button removeButton;
	
	VBox insertStudentBox;
	TextField insertFirstNameField;
	TextField insertLastNameField;
	TextField insertAgeField;
	TextField insertMarkField;
	
	VBox removeStudentBox;
	TextField removeIndex;

	
	private List<Student> studentsList;
	private Set<Student> studentsSet;
	
	
	
	
	
	
	
	public CustomTabPane() throws IOException, InterruptedException
	{

		studentsList = StudentsParser.parse(new File("C:\\Users\\MarcinB\\Desktop\\Crawler1.txt"));
		
		
		initButtons();
	    initTabPane();
	    initMenuBar();
	    initTable();
	    initBoxes();
	    
	    histogramTab.updateHistogram(this);
			
	}
	
	public void addStudent() throws IOException, InterruptedException
	{
		
	     
		 if(!insertFirstNameField.getText().trim().isEmpty()  && !insertLastNameField.getText().trim().isEmpty() &&
					!insertAgeField.getText().trim().isEmpty() && !insertMarkField.getText().trim().isEmpty())
			{
	          	tempStudent = new Student(insertFirstNameField.getText(),insertLastNameField.getText(),Integer.parseInt(insertAgeField.getText()),
					Double.parseDouble(insertMarkField.getText()));
	        	studentsObservable.add(tempStudent);
	        	logTab.log("ADDED", tempStudent);
	        	histogramTab.updateHistogram(this);

			}
	
		 else
		 {
			 Alert alert = new Alert(AlertType.INFORMATION);
			 alert.setTitle("Error!");
			 alert.setHeaderText("Blad przy dodawaniu studenta.");
			 alert.setContentText("Wypelnij wszystkie pola!");
			 alert.showAndWait();
		 }
		
	}
	
	public void removeStudent()
	{

		logTab.log("REMOVED", studentsObservable.get(Integer.parseInt(removeIndex.getText())));
		studentsObservable.remove(Integer.parseInt(removeIndex.getText()));
		histogramTab.updateHistogram(this);

	}
	
	public void initButtons()
	{

		 addButton = new Button("Add");
		addButton.setStyle("-fx-font: 12 arial; -fx-base: #b6e7c9;");
	//	addButton.setMinSize(50, 50);
	     AnchorPane.setBottomAnchor(addButton, 165.0);
	     AnchorPane.setLeftAnchor(addButton, 190.0);
	     addButton.setOnAction(new EventHandler<ActionEvent>() {
	    	    @Override public void handle(ActionEvent e) {
	    	       try {
					addStudent();
				} catch (IOException | InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	    }
	    	});
	   
		
	    removeButton = new Button("Remove");
		removeButton.setStyle("-fx-font: 12 arial; -fx-base: #b6e7c9;");
	    AnchorPane.setBottomAnchor(removeButton, 165.0);
	    AnchorPane.setRightAnchor(removeButton, 180.0);
	    removeButton.setOnAction(new EventHandler<ActionEvent>() {
	    	    @Override public void handle(ActionEvent e) {
	    	       removeStudent();
	    	        
	    	    }
	    	});
	     
	     this.getChildren().addAll(addButton, removeButton);
	}
	
	public void initTabPane()
	{
		    tabPane = new TabPane();
			studentsTab = new StudentsTab();
			logTab = new LogTab();
			histogramTab = new HistogramTab();
			tabPane.getTabs().add(studentsTab);
			tabPane.getTabs().add(logTab);
			tabPane.getTabs().add(histogramTab);
			
			AnchorPane.setTopAnchor(tabPane, 25.0);
			AnchorPane.setLeftAnchor(tabPane, 0.0);
			AnchorPane.setRightAnchor(tabPane, 0.0);
			
			this.getChildren().add(tabPane);
	}
	
	public void initMenuBar()
	{
		customMenuBar = new CustomMenuBar();
		
		AnchorPane.setTopAnchor(customMenuBar, 1.0);
		AnchorPane.setLeftAnchor(customMenuBar, 0.0);
		AnchorPane.setRightAnchor(customMenuBar, 0.0);
		
		
		this.getChildren().add(customMenuBar);
	}
	
	public void initTable()
	{
		TableView<Student> table = new TableView<Student>();
		
		for(Student s : studentsList)
			studentsObservable.add(s);
		
		table.setItems(studentsObservable);

		
		TableColumn<Student,String> markCol = new TableColumn<Student,String>("Marks");
		markCol.setCellValueFactory(new PropertyValueFactory("mark"));
		
		TableColumn<Student,String> firstNameCol = new TableColumn<Student,String>("First Name");
		firstNameCol.setCellValueFactory(new PropertyValueFactory("firstName"));
		TableColumn<Student,String> lastNameCol = new TableColumn<Student,String>("Last Name");
		lastNameCol.setCellValueFactory(new PropertyValueFactory("lastName"));
		
		TableColumn<Student,String> ageCol = new TableColumn<Student,String>("Age");
		ageCol.setCellValueFactory(new PropertyValueFactory("age"));


		table.getColumns().setAll(markCol,firstNameCol,lastNameCol, ageCol);
		studentsTab.setContent(table);



	}
	
	public void initInsertStudentBox()
	{
		insertStudentBox = new VBox(5);
		insertFirstNameField = new TextField();
		insertLastNameField = new TextField();
		insertAgeField = new TextField();
		insertMarkField = new TextField();
		insertStudentBox.getChildren().addAll(new Label(),new Label("First Name, Last name, Age, Mark: "), insertFirstNameField,  insertLastNameField,
				insertAgeField,  insertMarkField);
		AnchorPane.setBottomAnchor(insertStudentBox,75.0);
		AnchorPane.setLeftAnchor(insertStudentBox,5.0); 
		this.getChildren().add(insertStudentBox);
	}
	
	public void initRemoveStudentBox()
	{
		removeStudentBox = new VBox(5);
		removeIndex = new TextField();
	
		removeStudentBox.getChildren().addAll(new Label(), new Label("Indeks:"), removeIndex);
		AnchorPane.setBottomAnchor(removeStudentBox, 165.0);
		AnchorPane.setLeftAnchor(removeStudentBox, 300.0);
		this.getChildren().add(removeStudentBox);
	}
	
	public void initBoxes()
	{
		initInsertStudentBox();
		initRemoveStudentBox();
	}
	
	public int counter2()
	{
		int result=0;
		for(Student s : studentsObservable)
			if(s.getMark()==2.0)
				result++;
		return result;
	}
	
	public int counter3andhalf()
	{
		int result=0;
		for(Student s : studentsObservable)
			if(s.getMark()==3.5)
				result++;
		return result;
	}
	public int counter3()
	{
		int result=0;
		for(Student s : studentsObservable)
			if(s.getMark()==3.0)
				result++;
		return result;
	}
	public int counter4()
	{
		int result=0;
		for(Student s : studentsObservable)
			if(s.getMark()==4.0)
				result++;
		return result;
	}
	public int counter4andhalf()
	{
		int result=0;
		for(Student s : studentsObservable)
			if(s.getMark()==4.5)
				result++;
		return result;
	}
	public int counter5()
	{
		int result=0;
		for(Student s : studentsObservable)
			if(s.getMark()==5.0)
				result++;
		return result;
	}

	
}
