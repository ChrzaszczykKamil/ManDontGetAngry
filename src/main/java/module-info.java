module com.example.javatcsproject {
	requires javafx.controls;
	requires javafx.fxml;


	opens com.example.javatcsproject to javafx.fxml;
	exports com.example.javatcsproject;
}