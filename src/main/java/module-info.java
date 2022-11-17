module com.example.project {
	requires javafx.controls;
	requires javafx.fxml;


	opens man_dont_get_angry to javafx.fxml;
	exports man_dont_get_angry;
}