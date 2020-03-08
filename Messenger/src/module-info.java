module Messenger {
	
	requires java.sql;
	requires javafx.graphics;
	requires javafx.base;
	requires javafx.fxml;
	requires javafx.controls;
	exports ChatClient;
	opens ChatClient to javafx.fxml;
}