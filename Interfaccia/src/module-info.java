module Interfaccia {
	requires javafx.controls;
	requires java.sql;
	requires com.google.common;
	requires javafx.graphics;
	opens application to javafx.graphics, javafx.fxml;
}
