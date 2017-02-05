package com.livraria.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Alertas {

	public static boolean campoVasio(String campo){
		if(campo.equals("")){
			return true;
		}
			return false;
	}
	
	public static boolean comecaComEspaco(String campo){
		if(campo.startsWith(" ")){
			return true;
		}
		    return false;
	}
	
	public static void alertaSucesso(String sucesso){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText(sucesso);
		alert.show();
	}
	
	public static void alertaErro(String erro){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setContentText(erro);
		alert.show();
	}
	
} //0800-6429291
