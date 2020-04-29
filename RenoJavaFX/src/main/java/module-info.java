module RenoJavaFX {

	exports app;
	exports pkgBetEngine;
	requires RenoBLL;
	requires RenoJabber;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.media;

	requires jackson.databind;
	requires jackson.core;
	requires jackson.dataformat.xml;
	requires jackson.annotations;
	
	requires java.sql;
	
	opens app.controller to javafx.fxml;
	
}