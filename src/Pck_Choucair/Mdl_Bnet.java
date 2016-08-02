package Pck_Choucair;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//  import java.util.Date;
//  import java.util.List;
import java.util.*;

import javax.imageio.ImageIO;

import java.sql.DriverManager;



import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;

import com.google.common.base.Converter;
import com.sun.org.apache.xerces.internal.impl.xpath.XPath;
import com.thoughtworks.selenium.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.ie.*;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.seleniumhq.jetty9.server.Response.OutputType;

import Pck_Choucair.Mdl_Choucair.Funciones_Ch;
import Pck_Choucair.Web_Framework.*;

import java.sql.*;
import java.util.GregorianCalendar; //trabajar calendario y fechas
import java.util.concurrent.TimeUnit;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;;
public class Mdl_Bnet {
	
	public static String xpathTabla;
	public static String  winHandleBefore;
	public enum Funciones_bnet
	{
		menu,  login, calendario,cerraremer, refresh,htmltd2,frame, novalue ,ventana,lista_valor ,ingreso,seleccionaropcion,clave,realizartrx,
		realizartrxexterior,realizargiro,efectivomovil,seleccionarmoneda,seleccionargastos,seleccionardocumento,seleccionarlocalidad,realizardonaciones,
		seleccionarcampania,seleccionardetalle,ingresocuentas,condiciones,ultimo_mov,desconectar,ultimo_movdonaciones,verificar_historico,tipo_telefono,
		editar,editar1,editar2,editar3,editar4,tipo_operador,eliminar1,eliminar2,eliminar3,eliminar4,boton_email,boton_email1,boton_email2,boton_email3,boton_email4,
		tipo_via,editar_direccion,tipo_zona,departamento,provincia,distrito,anadir_telefono,eliminar_email1,eliminar_email2,eliminar_email3,eliminar_email4,
		seleccionar_telefono, seleccionar_email, seleccionar_telefono_card, seleccionar_email_card,
		seleccionar_dia_semana, bajar_scroll, bajar_scroll_telefono, bajar_scroll_email, bajar_pagina_flecha, launchApp_BNetActual, abrir_bnet_actual,
		login_BNetActual, ingreso_tarjetas, detalle_tarjeta, tercerostrx , claveTemporizador,
		bnetactual_propias,bnetactual_terceros,bnetactual_efectivomovil,bnetactual_donaciones,bnetactual_girosnacionales, seleccionar_periodo,
		seleccionar_numcta_regalo, otrosbancostrx, seleccionar_disponibilidad, seleccionar_docutrxotrosbancos, pagotarjeta, seleccionar_clasetarjeta, 
		seleccionar_banco, seleccionar_lugaremision, seleccionar_numtarj_regalo, seleccionar_importe, bnetactual_tarjetas, bnetactual_datos_afiliacion,
		ingreso_fondos, rescate_fondo, bnetactual_alertas_cuentas, seleccionar_numtarj_alertas, click_cuenta_pago, click_radiobutton_byclass,click_radiobutton_byposition,click_checkbox_recibo_pendiente
		,bnetactual_recarga_tarjeta_regalo,bnetactual_pago_tarjetas_terceros,bnetactual_trasladar_consumos,click_compra,bnetactual_tin_tx_cta_otros_bancos;

		public static Funciones_bnet getValue(String str)
	    {
	        try {
	            return valueOf(str);
	        }
	        catch (IllegalArgumentException ex) {
	            return novalue;
	          
	            //ex.messagge
	        }
	    }
	}
	
	public static void Fw_bnet(String strFunName, String str_parametro1,String str_parametro2, String str_parametroAdicional) throws SQLException, InterruptedException
	{
		switch (Funciones_bnet.getValue(strFunName.split(";")[0].toLowerCase()))	
		{
		
		case ingreso:
			ingreso();
			break;
		case seleccionaropcion:
			seleccionarOpcion();
			break;
		case clave:
			clave();
			break;
		case login:
			login();
			break;
		case realizartrx:
			realizarTRX();
			break;
		case realizartrxexterior:
			RealizarTRXExterior(str_parametro1);
			break;
		case realizargiro:
			realizarGiro();
			break;
		case realizardonaciones:
			realizarDonaciones();
			break;
		case efectivomovil:
			efectivoMovil();
			break;		
		case seleccionarmoneda:
			seleccionarMoneda(str_parametro1);
			break;
		case seleccionargastos:
			seleccionarGastos(str_parametro1);
			break;
		case seleccionardocumento:
			seleccionarDocumento();
			break;
		case seleccionarlocalidad:
			seleccionarLocalidad();
			break;
		case seleccionarcampania:
			seleccionarCampania();
			break;
		case seleccionardetalle:
			seleccionarDetalle(str_parametro1);
			break;
		case ingresocuentas:
			IngresoCuentas(str_parametro1);
			break;
			
		case desconectar:
			Desconectar(str_parametro1);
			break;
		case condiciones:
			Condiciones(str_parametro1);
			break;
		case calendario:
			LlenarFecha(Web_Framework.GetValue(str_parametro1),Web_Framework.GetValue(str_parametro2));
			break;
		case frame:
			swichear(Web_Framework.GetValue(str_parametro1));
			break;
		case htmltd2:
			htlmtd2 (Web_Framework.GetValue(str_parametro1), Web_Framework.GetValue(str_parametroAdicional), Web_Framework.GetValue(str_parametro2), true, "htmltd2");
			break;
		case ventana:
			ventana (Web_Framework.GetValue(str_parametro1));
			break;	
		case lista_valor:
			lista_valor (Web_Framework.GetValue(str_parametro1),str_parametro2);
			break;
			
		case ultimo_mov:
			//str_parametro1: dt_campo1:dt_campo2:dt_campo3
			ultimo_mov (str_parametro1);
			break;
			
		case ultimo_movdonaciones:
			//str_parametro1: dt_campo1:dt_campo2:dt_campo3
			ultimo_movdonaciones (str_parametro1);
			break;
			
		case verificar_historico:
			//str_parametro1: dt_campo1:dt_campo2:dt_campo3
			verificar_historico (str_parametro1);
			break;
			
		case tipo_telefono:
			//str_parametro1: dt_campo1:dt_campo2:dt_campo3
			tipo_telefono (str_parametro1);
			break;
			
		case editar:
			//str_parametro1: dt_campo1:dt_campo2:dt_campo3
			editar (str_parametro1);
			break;
		case editar1:
			//str_parametro1: dt_campo1:dt_campo2:dt_campo3
			editar1 (str_parametro1);
			break;
		case editar2:
			//str_parametro1: dt_campo1:dt_campo2:dt_campo3
			editar2 (str_parametro1);
			break;
		case editar3:
			//str_parametro1: dt_campo1:dt_campo2:dt_campo3
			editar3 (str_parametro1);
			break;
		case editar4:
			//str_parametro1: dt_campo1:dt_campo2:dt_campo3
			editar4 (str_parametro1);
			break;
		case tipo_operador:
			//str_parametro1: dt_campo1:dt_campo2:dt_campo3
			tipo_operador(str_parametro1);
			break;
		case eliminar1:
			eliminar1(str_parametro1);
			break;
		case eliminar2:
			//str_parametro1: dt_campo1:dt_campo2:dt_campo3
			eliminar2 (str_parametro1);
			break;
		case eliminar3:
			//str_parametro1: dt_campo1:dt_campo2:dt_campo3
			eliminar3 (str_parametro1);
			break;
		case eliminar4:
			//str_parametro1: dt_campo1:dt_campo2:dt_campo3
			eliminar4 (str_parametro1);
			break;
		case boton_email:
			//str_parametro1: dt_campo1:dt_campo2:dt_campo3
			boton_email(str_parametro1);
			break;
		case boton_email1:
			//str_parametro1: dt_campo1:dt_campo2:dt_campo3
			boton_email1(str_parametro1);
			break;
		case boton_email2:
			//str_parametro1: dt_campo1:dt_campo2:dt_campo3
			boton_email2(str_parametro1);
			break;	
		case boton_email3:
			//str_parametro1: dt_campo1:dt_campo2:dt_campo3
			boton_email3(str_parametro1);
			break;
		case boton_email4:
			//str_parametro1: dt_campo1:dt_campo2:dt_campo3
			boton_email4(str_parametro1);
			break;
		case tipo_via:
			//str_parametro1: dt_campo1:dt_campo2:dt_campo3
			tipo_via();
			break;
		case editar_direccion:
			//str_parametro1: dt_campo1:dt_campo2:dt_campo3
			editar_direccion();
			break;
			
		case tipo_zona:
			//str_parametro1: dt_campo1:dt_campo2:dt_campo3
			tipo_zona();
			break;
			
		case departamento:
			//str_parametro1: dt_campo1:dt_campo2:dt_campo3
			departamento();
			break;
			
		case provincia:
			//str_parametro1: dt_campo1:dt_campo2:dt_campo3
			provincia();
			break;
			
		case distrito:
			//str_parametro1: dt_campo1:dt_campo2:dt_campo3
			distrito();
			break;
		case anadir_telefono:
			//str_parametro1: dt_campo1:dt_campo2:dt_campo3
			anadir_telefono(str_parametro1);
			break;
		case eliminar_email1:
			//str_parametro1: dt_campo1:dt_campo2:dt_campo3
			eliminar_email1(str_parametro1);
			break;
		case eliminar_email2:
			//str_parametro1: dt_campo1:dt_campo2:dt_campo3
			eliminar_email2(str_parametro1);
			break;
		case eliminar_email3:
			//str_parametro1: dt_campo1:dt_campo2:dt_campo3
			eliminar_email3(str_parametro1);
			break;
		case eliminar_email4:
			//str_parametro1: dt_campo1:dt_campo2:dt_campo3
			eliminar_email4(str_parametro1);
			break;
		case seleccionar_telefono:
			//seleccionador_telefono_email(str_parametro1, 1);
			seleccionar_telefono(str_parametro1);
			//seleccionar_telefono1(str_parametro1);
			break;
		case seleccionar_email:
			//seleccionador_telefono_email(str_parametro1, 2);
			seleccionar_email(str_parametro1);
			break;
		case seleccionar_telefono_card:
			seleccionar_telefono_card(str_parametro1);
			break;
		case seleccionar_email_card:
			seleccionar_email_card(str_parametro1);
			break;
		case seleccionar_dia_semana:
			seleccionador_dia_semana();
			break;
		case bajar_scroll:
				bajar_scroll();
				break;
		case bajar_scroll_telefono:
				bajar_scroll_telefono();
				break;
		case bajar_scroll_email:
				bajar_scroll_email();
				break;
		case bajar_pagina_flecha:
			bajar_pagina_flecha();
			break;
		case ingreso_tarjetas:
				ingreso_tarjetas();
				break;
		case detalle_tarjeta:
				detalle_tarjeta(str_parametro1);
				break;
		case launchApp_BNetActual:
			launchApp_BNetActual(str_parametro1);
			break;
		case abrir_bnet_actual:
			abrir_bnet_actual();
			break;
		case login_BNetActual:
			login_BNetActual(str_parametro1);
			break;

		case tercerostrx:
			tercerosTRX(str_parametro1);
			break;
		case claveTemporizador:
			claveTemporizador();
			break;
		case bnetactual_propias:
			//Solo ingresa si el parametro adicional no tiene un ignore dentro de la keyword
			if (!Web_Framework.GetValue(str_parametroAdicional).toLowerCase().equals("<ignore>"))
			{
				bnetactual_propias(str_parametro1, str_parametro2);
			}
			break;
		case bnetactual_terceros:
			if (!Web_Framework.GetValue(str_parametroAdicional).toLowerCase().equals("<ignore>"))
			{
				bnetactual_terceros(str_parametro1, str_parametro2);
			}
			break;
		case bnetactual_efectivomovil:
			bnetactual_efectivomovil(str_parametro1);
				break;
		case bnetactual_donaciones:
			bnetactual_donaciones(str_parametro1);
				break;
		case bnetactual_girosnacionales:
			bnetactual_girosnacionales(str_parametro1);
		break;
		case seleccionar_periodo:
			seleccionar_periodo(str_parametro1);
		break;
		case seleccionar_numcta_regalo:
			seleccionar_numcta_regalo(str_parametro1);
		break;
		case otrosbancostrx:
			otrosbancosTRX(str_parametro1);
			break;
		case seleccionar_disponibilidad:
			seleccionar_disponibilidad(str_parametro1);
			break;
		case seleccionar_docutrxotrosbancos:
			seleccionar_docutrxotrosbancos(str_parametro1);
			break;
		case pagotarjeta:
			pagotarjeta();
		    break;
		case seleccionar_clasetarjeta:
			seleccionar_clasetarjeta(str_parametro1);
			break;
		case seleccionar_banco:
			seleccionar_banco(str_parametro1);
			break;
		case seleccionar_lugaremision:
			seleccionar_lugaremision(str_parametro1);
			break;
		case seleccionar_numtarj_regalo:
			seleccionar_numtarj_regalo(str_parametro1);
			break;
		case seleccionar_importe:
			seleccionar_importe(str_parametro1);
			break;
		case bnetactual_tarjetas:
			if (!Web_Framework.GetValue(str_parametroAdicional).toLowerCase().equals("<ignore>"))
			{
				bnetactual_tarjetas(str_parametro1);
			}
			break;
		case bnetactual_datos_afiliacion:
			if (!Web_Framework.GetValue(str_parametroAdicional).toLowerCase().equals("<ignore>"))
			{
				bnetactual_datos_afiliacion();
			}
			break;
		case ingreso_fondos:
			ingreso_fondos();
			break;
		case rescate_fondo:
			rescate_fondo();
			break;
		case bnetactual_alertas_cuentas:
			if (!Web_Framework.GetValue(str_parametroAdicional).toLowerCase().equals("<ignore>"))
			{
				bnetactual_alertas_cuentas();
			}
			break;
		case seleccionar_numtarj_alertas:
			seleccionar_numtarj_alertas(str_parametro1);
			break;
			
		case click_cuenta_pago:
			click_cuenta_pago(Web_Framework.GetValue(str_parametro1));
			break;
			
		case click_radiobutton_byclass:
			click_radiobutton_byclass(Web_Framework.GetValue(str_parametro1),Web_Framework.GetValue(str_parametro2));
			break;
		case click_radiobutton_byposition:
			click_radiobutton_byposition(Web_Framework.GetValue(str_parametro1),Web_Framework.GetValue(str_parametro2));
		break;
		
		case click_checkbox_recibo_pendiente:
			click_checkbox_recibo_pendiente(str_parametro1);
			break;
			
		case bnetactual_recarga_tarjeta_regalo:
			bnetactual_recarga_tarjeta_regalo(str_parametro1);
			break;
			
		case bnetactual_pago_tarjetas_terceros:
		//	bnetactual_pago_tarjetas_terceros(str_parametro1);
		break;
		
		case bnetactual_trasladar_consumos:
			bnetactual_trasladar_consumos(str_parametro1);
		break;
		
		case click_compra:
			click_compra(Web_Framework.GetValue(str_parametro1),Web_Framework.GetValue(str_parametro2));
			break;
			
		case bnetactual_tin_tx_cta_otros_bancos:
			bnetactual_tin_tx_cta_otros_bancos(str_parametro1);
			break;
	}
	}
	/*
	 * cerrar_pantalla
	
	 * 
	 * Descripcion: cuando se abre la pantalla de detalles es necesario cerrarlas para seguir interectuando mas adelante
	 * 
	 * 
	 *  
	 *  
	 *  
	 * 
	 * */
	//public static void cerrar_pantalla() 
	//{
		//try 
		//{
		//Mdl_Variables.driver.findElement(By.xpath("(//span[@class='ui-button-icon-primary ui-icon ui-icon-closethick'])[2]")).click();
		
		//}catch (Throwable e)
		//{
			//System.out.println("Error en Mdl_Bnet.Cerrar pantalla: Error en el menu no se encuentra el objeto " + e.getMessage());
			//Mdl_Evidenciamiento.Evidencia_Log(false, "");
			//Mdl_Evidenciamiento.Ingreso_Datos_Log();
			//Mdl_Variables.boolAction = 0;
		//}
	//}
	/*
	
	 * Descripcion: en muchas aplicaciones se utiliza frames; Los frames (marcos o cuadros)
	 *  permiten dividir la ventana en varias más pequeñas, de modo que en cada una de ellas se cargua una página html distinta. 
	 * 
	 * en WebDriver, no se logra identificar muchas veces los frames por tal motivo se pide switchear.
	 *  
	 *  
	 *  
	 *  switchTo().frame(NroFrame)---> (NroFrame) seria el numero de frame a trabajar
	 *  switchTo().defaultContent---> Regresa al principal sin ningun frame
	 * */
	public static void swichear(String striframe) 
	{
		try {
			if ((striframe)!="") 
			{
				
				Mdl_Variables.driver.switchTo().frame(Integer.parseInt(striframe));
			}else
			{
				Mdl_Variables.driver.switchTo().defaultContent();
			}
			//Mdl_Variables.driver.switchTo().frame(striframe);
			Thread.sleep(3000);
			
		} catch (Exception e) {
			System.out.println("Error en Mdl_GBANK: Switchear. " + e.getMessage());
		}

	}
	/*####################################################################
	 * Htmltd2 
	 * es muy parecida a la que se tiene en chc, pero dado a multiples problemas presentados 
	 * con la de cair se realizó esta donde las tablas se trabajaran con xpath 
	 * y no se utiiliza el objtable para interactuar con la tabla sino q se usa mdlvariables.driver
	 *
	 * 	 * ###################################################################
	 */
	public static String htlmtd2(String Valores_Recorrido, String Parametros_Adicionales, String iColumnaObjetivo, boolean blnInteraccion, String strFuncion) 
	{
		String strMensajeError = null;
		String[] Vector_Datapool;
		int Int_Recorrido_Fil,Int_ColTarget=0,Tamano_Col, Int_Rowheader = 0;
		boolean Bool_Contiene = false,Bool_Objeto=false,bool_EncontroDato =false;
		String[][] Matriz = null,Matriz_Recorrido = null;;
		
		try
		{
			 Vector_Datapool=Web_Framework.GetValue(Valores_Recorrido).replace("<tab>", "").replace("<enter>", "").split("\\|");
			//inicializa Int_Rowheader, Int_ColTarget, Bool_Contiene
				strMensajeError = "Inicializando las variables Int_Rowheader, Int_ColTarget, Bool_Contiene";
				for(Int_Recorrido_Fil = 0; Int_Recorrido_Fil < Parametros_Adicionales.split("\\|").length; Int_Recorrido_Fil++){
					if(Parametros_Adicionales.split("\\|")[Int_Recorrido_Fil].toUpperCase().indexOf("ROWHEADER;") != -1){ //contiene la cadena "ROWHEADER;". retorna -1 si no la contiene
						Int_Rowheader = Integer.parseInt(Parametros_Adicionales.split("\\|")[Int_Recorrido_Fil].toUpperCase().split("ROWHEADER;")[1]);
					}
					if(Parametros_Adicionales.split("\\|")[Int_Recorrido_Fil].toUpperCase().indexOf("COLTARGET;") != -1){	//contiene la cadena "COLTARGET;". retorna -1 si no la contiene
						Int_ColTarget = Integer.parseInt(Parametros_Adicionales.split("\\|")[Int_Recorrido_Fil].toUpperCase().split("COLTARGET;")[1]);
					}
					if(Parametros_Adicionales.split("\\|")[Int_Recorrido_Fil].toUpperCase().indexOf("CONTIENE") != -1){	//contiene la cadena "CONTIENE;". retorna -1 si no la contiene
						Bool_Contiene = true;
					}
				}
				//inicializa la matriz (tabla htmltd2) con los datos de la pantalla
				strMensajeError = "Obteniendo la Matriz";
				for(Int_Recorrido_Fil = 0 ; Int_Recorrido_Fil < Parametros_Adicionales.split("\\|").length ; Int_Recorrido_Fil++)
				{
					if(Parametros_Adicionales.split("\\|")[Int_Recorrido_Fil].toUpperCase().indexOf("ATTACHTABLE") != -1)
					{
						strMensajeError = "Inicializando AttachTable";
						xpathTabla =Web_Framework.GetValue(Parametros_Adicionales.split("\\|")[Int_Recorrido_Fil].split("attachtable;")[1]).replace("xpath=", "");
						//Se inicializa el objeto tabla que define la tabla htmltd2 con la que se va a interactuar
						strMensajeError = "Definiendo la tabla";
						//Parametros_Adicionales.split("\\|")[3].toUpperCase().split("ATTACHTABLE;")[1]
						strMensajeError = "Cargando la matriz";
						
						Matriz = Datos_htmltd2(xpathTabla, Int_Rowheader);
						if(Matriz == null)
						{
							System.out.println("No se cargó la matriz");
							 break;
						}
					
					}
				}
				Tamano_Col = Matriz[0].length;
				strMensajeError = "Redimencionando Matriz_Recorrido";
				Matriz_Recorrido = new String[Vector_Datapool.length][2];
				strMensajeError = "Armando la matriz recorrido";
				for(Int_Recorrido_Fil = 0 ; Int_Recorrido_Fil < Vector_Datapool.length ; Int_Recorrido_Fil++)
				{
					if(Vector_Datapool[Int_Recorrido_Fil].substring(0, 1).equals("-"))
					{
						Matriz_Recorrido[Int_Recorrido_Fil][0] = "-" + Vector_Datapool[Int_Recorrido_Fil].split("-", 3)[1];
						Matriz_Recorrido[Int_Recorrido_Fil][1] = Vector_Datapool[Int_Recorrido_Fil].split("-", 3)[2];
					}else
					{
						if(Vector_Datapool[Int_Recorrido_Fil].substring(1, 2).equals("-"))
						{
							Matriz_Recorrido[Int_Recorrido_Fil][0] = Vector_Datapool[Int_Recorrido_Fil].split("-", 2)[0];
							Matriz_Recorrido[Int_Recorrido_Fil][1] = Vector_Datapool[Int_Recorrido_Fil].split("-", 2)[1];
						}else
						{
							if(Vector_Datapool[Int_Recorrido_Fil].substring(2, 3).equals("-"))
							{
								Matriz_Recorrido[Int_Recorrido_Fil][0] = Vector_Datapool[Int_Recorrido_Fil].split("-", 2)[0];
								Matriz_Recorrido[Int_Recorrido_Fil][1] = Vector_Datapool[Int_Recorrido_Fil].split("-", 2)[1];
							}else
							{
								if(Vector_Datapool[Int_Recorrido_Fil].toLowerCase().indexOf("combobox") != -1 ||
										Vector_Datapool[Int_Recorrido_Fil].toLowerCase().indexOf("textbox") != -1 ||
												Vector_Datapool[Int_Recorrido_Fil].toLowerCase().indexOf("label") != -1 ||
														Vector_Datapool[Int_Recorrido_Fil].toLowerCase().indexOf("image") != -1 ||
																Vector_Datapool[Int_Recorrido_Fil].toLowerCase().indexOf("checkbox") != -1)
								{
									Matriz_Recorrido[Int_Recorrido_Fil][0] = "0";
									Matriz_Recorrido[Int_Recorrido_Fil][1] = "<IGNORE>";
									Bool_Objeto = true;
								}else
								{
									Matriz_Recorrido[Int_Recorrido_Fil][0] = "0";
									Matriz_Recorrido[Int_Recorrido_Fil][1] = Vector_Datapool[Int_Recorrido_Fil];
								}
							}
						}
					}
				
				}
				int[] Int_Recorrido_Fila = {0};
				String[] valorRetorno = new String[1];
				iColumnaObjetivo=Matriz_Recorrido[1][0];
				int[] Int_Recorrido_Col = {0};
				
				bool_EncontroDato =Mdl_Choucair.buscarDatoEnMatriz(Vector_Datapool, Matriz, Matriz_Recorrido, Bool_Contiene, Int_ColTarget, iColumnaObjetivo, valorRetorno, Int_Recorrido_Col, Int_Recorrido_Fila);
				if (bool_EncontroDato == false) 
				{
					Mdl_Evidenciamiento.Evidencia_Log(false, "No se encontró el dato de búsqueda.");
					Mdl_Evidenciamiento.Ingreso_Datos_Log();
					Mdl_Variables.boolAction = 0;
					return null;
				}
				if(!blnInteraccion)
					return valorRetorno[0];
				interaccionElementoHtmlTable(Bool_Objeto, xpathTabla, Vector_Datapool, Int_Recorrido_Col, Int_Recorrido_Fila, iColumnaObjetivo, Bool_Contiene);
				
		}catch (Throwable e )
		{
			System.out.println("(Mdl_Bnet - HTMLTD) Se presentó un error: " + strMensajeError + " - " + e.getMessage());
			Mdl_Evidenciamiento.Evidencia_Log(false, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			Mdl_Variables.boolAction = 0;
			
		}	
		return null;
	}
	
	private static String interaccionElementoHtmlTable(boolean Bool_Objeto, String xpath, String[] Vector_Decodificado, int[] Int_Recorrido_Col, int[] Int_Recorrido_Fil, String iColumnaObjetivo, boolean Bool_Contiene  )
	{
		String valorRetorno=null;
		if(Bool_Objeto)
		{
			switch(Web_Framework.Objetos.getValue(Vector_Decodificado[Vector_Decodificado.length - 1].split(":")[0].toLowerCase()))
			{
			case label:
				try
				{
					if(!Bool_Contiene)
					{
						if(!Vector_Decodificado[Vector_Decodificado.length - 1].split(":")[1].equals(Mdl_Variables.driver.findElement(By.xpath(xpath +"/tbody/tr[" + (Int_Recorrido_Col[0] + 1) + "]/td[" + (Int_Recorrido_Fil[0] + Integer.parseInt(iColumnaObjetivo) ) + "]")).getText().trim()))
						{
							Mdl_Evidenciamiento.Evidencia_Log(false, "Los textos no coinciden: " + Mdl_Variables.driver.findElement(By.xpath(xpath +"/tbody/tr[" + (Int_Recorrido_Col[0] + 1) + "]/td[" + (Int_Recorrido_Fil[0] + Integer.parseInt(iColumnaObjetivo) ) + "]")).getText().trim() + ", " + Vector_Decodificado[Vector_Decodificado.length - 1].split(":")[1]);
							Mdl_Evidenciamiento.Ingreso_Datos_Log();
							Mdl_Variables.boolAction = 0;
						}
					}else 
					{
						Mdl_Variables.boolAction = Mdl_Evidenciamiento.Valida_Orientacion(Mdl_Variables.driver.findElement(By.xpath(xpath +"/tbody/tr[" + (Int_Recorrido_Col[0] + 1) + "]/td[" + (Integer.parseInt(iColumnaObjetivo)+ 1 ) + "]")).getText().trim().contains(Vector_Decodificado[Vector_Decodificado.length - 1].split(":")[1].trim()));										
						if (Mdl_Variables.boolAction==0)
						{
						Mdl_Evidenciamiento.Evidencia_Log(false,Vector_Decodificado[Vector_Decodificado.length - 1].split(":")[1]+ " diferente al de pantalla "+Mdl_Variables.driver.findElement(By.xpath(xpath +"/tbody/tr[" + (Int_Recorrido_Col[0] + 1) + "]/td[" + (Integer.parseInt(iColumnaObjetivo) + 1 ) + "]")).getText());
						Mdl_Evidenciamiento.Ingreso_Datos_Log();
						System.out.println("el sistema no se encuentra el valor"+Vector_Decodificado[Vector_Decodificado.length - 1].split(":")[1]+" el valor que trae es "+ Mdl_Variables.driver.findElement(By.xpath(xpath +"/tbody/tr[" + (Int_Recorrido_Col[0] + 1) + "]/td[" + (Integer.parseInt(iColumnaObjetivo) + 1 ) + "]")).getText());
						}
						/*
							if(Mdl_Variables.driver.findElement(By.xpath(xpath +"/tbody/tr[" + (Int_Recorrido_Col[0] + 1) + "]/td[" + (Int_Recorrido_Fil[0] + Integer.parseInt(iColumnaObjetivo) ) + "]")).getText() != null && !Mdl_Variables.driver.findElement(By.xpath(xpath +"/tbody/tr[" + (Int_Recorrido_Col[0] + 1) + "]/td[" + (Int_Recorrido_Fil[0] + Integer.parseInt(iColumnaObjetivo) ) + "]")).getText().trim().contains(Vector_Decodificado[Vector_Decodificado.length - 1].split(":")[1]))
							{
								Mdl_Evidenciamiento.Evidencia_Log(false, "El texto \"" + Mdl_Variables.driver.findElement(By.xpath(xpath +"/tbody/tr[" + (Int_Recorrido_Col[0] + 1) + "]/td[" + (Int_Recorrido_Fil[0] + Integer.parseInt(iColumnaObjetivo) ) + "]")).getText().trim() + "\" no contiene el texto \"" + Vector_Decodificado[Vector_Decodificado.length - 1].split(";")[1] + "\"");
								//objTabla.findElement(By.xpath("//" + strObjetoFila + "[" + (Int_Recorrido_Col + 1) + "]/"+ strObjetoColumna + "[" + (Int_Recorrido_Fil + Integer.parseInt(iColumnaObjetivo) + 1) + "]")).getText();
								//objTabla.findElement(By.xpath("//LI[1]/span")).getText();
								//tbody/tr[1]/td[2]
								//*[@id='accounts_ops']/div[2]/div[3]/div/div/ul/li[1]/span
								Mdl_Evidenciamiento.Ingreso_Datos_Log();
								Mdl_Variables.boolAction = 0;
							}else
							{
								System.out.println("el dato se encontro " + Vector_Decodificado[Vector_Decodificado.length - 1].split(":")[1]);
							}*/
					}	
					
					
				}catch(Throwable e)
				{
					System.out.println("(Mdl_Davivienda-htmltd2) Ocurrió un error realizando la consulta. " + e.getMessage());
					Mdl_Evidenciamiento.Evidencia_Log(false, "(Mdl_Davivienda-htmltd2) Ocurrió un error realizando la consulta");
					Mdl_Evidenciamiento.Ingreso_Datos_Log();
					Mdl_Variables.boolAction = 0;
				}
				break;
				
				
			
				
			case image:
				try{
					Mdl_Variables.driver.findElement(By.xpath(xpath +"/tbody/tr[" + (Int_Recorrido_Col[0] + 1) + "]/td[" + (1 + Integer.parseInt(iColumnaObjetivo) + 1) + "]/a[1]")).click();
					return valorRetorno;
				}catch(Throwable e){
					try{
						
						return valorRetorno;
					}catch(Throwable E){}
				}
				break;
				
				default:
					try{
						Mdl_Variables.driver.findElement(By.xpath(xpath +"/tbody/tr[" + (Int_Recorrido_Col[0] + 1) + "]/td[" + (Int_Recorrido_Fil[0] + Integer.parseInt(iColumnaObjetivo) + 1) + "]/input[@type=\"image\"]")).click();
						return valorRetorno;
					}catch(Throwable e){
						try{
							Mdl_Variables.driver.findElement(By.xpath(xpath +"/tr[" + (Int_Recorrido_Col[0] + 1) + "]/td[" + (Int_Recorrido_Fil[0] + Integer.parseInt(iColumnaObjetivo) + 1) + "]/input[@type=\"image\"]")).click();
							return valorRetorno;
						}catch(Throwable E){}
					}
					break;
			}
			
		}
		return valorRetorno;
		
	}
	private static String[][] Datos_htmltd2(String xpath,int header)
	{
		String[][] matriz=null;
		try
		{
			int totalFilas=0, totalColumnas=0;
			
			totalFilas =Mdl_Variables.driver.findElement(By.xpath(xpath)).findElements(By.tagName("tr")).size();
			//Mdl_Variables.driver.findElement(By.xpath("//table[@id='gridSaving']/tbody/tr[2]"))
			totalColumnas=Mdl_Variables.driver.findElement(By.xpath(xpath + "/tbody/tr"+ "[" + (header+1)+ "]")).findElements(By.tagName("td")).size();
			//objTabla.findElement(By.xpath("//" + strObjetoFila + "[" + (header +1 )+ "]")).findElements(By.tagName(strObjetoColumna)).size();
			
			//se redimensiona la matriz con ese tamaño
			matriz = new String[totalFilas][totalColumnas];
			for(int fila = 0 ; fila < totalFilas ; fila++)
			{
				for(int columna = 0 ; columna < totalColumnas ; columna++)
				{
					if(fila < header)
					{
						matriz[fila][columna] = "";
					}else
					{
						try
						{
							matriz[fila][columna] = Mdl_Variables.driver.findElement(By.xpath(xpath.replace("xpath=", "")+"/tbody/tr[" + (fila + 1) + "]/td[" + (columna + 1) + "]")).getText();
						}catch(Throwable e)
						{
							System.out.println("Error en Mdl_Davivienda.Datos_htmltd2 recorriendo la tabla: " + e.getMessage());
							Mdl_Evidenciamiento.Evidencia_Log(false, "");
							Mdl_Evidenciamiento.Ingreso_Datos_Log();
							Mdl_Variables.boolAction = 0;
						}
					}
					
				}
			}
			
		}catch(Throwable e)
		{
			System.out.println("Error cargando la Datos htmltd2: " + e.getMessage());
			Mdl_Evidenciamiento.Evidencia_Log(false, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			Mdl_Variables.boolAction = 0;
			
		}
		
		return matriz;
	}
	
	
	/**
	 * 
	 * @author Romer Vargas Otiniano
	 * @version 1.0
	 * @date 15/04/2016
	 */
	public static void realizarGiro(){
		try{
			Mdl_Variables.driver.findElement(By.xpath("//div[@id='operaciones-list-container']/div[3]/div/ul/li[4]/a/strong")).click();

			Mdl_Variables.strPasosEvidencia = "Seleccionar la operación realizar giro nacional";
			Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
			Thread.sleep(1000);
		}catch(Throwable e){System.out.println("Error en \"realizarGiro()\" al seleccionar: " + e.getMessage());}
	}
	
	
	
	/*####################################################################
	 * Login 
	 * 	 * ###################################################################
	 */
	
	 public static void login() throws SQLException {
         try{
                 Mdl_Choucair.getDatosLogin();
                 Thread.sleep(4000);
                 Mdl_Keyboard.doType(KeyEvent.VK_ENTER);
                 Thread.sleep(5000);

                 Mdl_Variables.driver.findElement(By.id("txteai_user")).clear();
                 Mdl_Variables.driver.findElement(By.id("txteai_user")).sendKeys(Mdl_Variables.Str_DatosUsuario[3]);

                 Mdl_Variables.strPasosEvidencia = "Iniciar sesión con el usuario " + Mdl_Variables.Str_DatosUsuario[3];
                 Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);

                 Mdl_Variables.driver.findElement(By.id("txteai_password")).clear();
                 Mdl_Variables.driver.findElement(By.id("txteai_password")).sendKeys(Mdl_Variables.Str_DatosUsuario[10]);
                 Mdl_Variables.strPasosEvidencia = "Ingresar contraseña " + Mdl_Variables.Str_DatosUsuario[10];
                 Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
                 Thread.sleep(3000);

                 switch(Keyword.getValue(Mdl_Variables.Tipo_Aplicativo.toLowerCase())){
                         case firefox:
                                 Thread.sleep(20000); //necesario, caso contrario; no funciona OK en Firefox
                                 break;
                         default: break;
                 }

                 Thread.sleep(3000);
                 Mdl_Evidenciamiento.Evidencia_Log(true, "");
                 Mdl_Evidenciamiento.Ingreso_Datos_Log();
                 Mdl_Variables.driver.findElement(By.id("btnEntrar")).click();
                 Mdl_Variables.strPasosEvidencia = "Hacer click en el botón Entrar";
                 Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
                 Thread.sleep(120000);
                 Mdl_Variables.driver.switchTo().window("_bancaporinternetbbva");
         }catch(Throwable e){System.out.println("Error en el logueo: " + e.getMessage());
                 Mdl_Evidenciamiento.Evidencia_Log(false, "");
                 Mdl_Evidenciamiento.Ingreso_Datos_Log();
                 Mdl_Variables.boolAction = 0;
         }
 }


	

	public static void seleccionarOpcion(String StrCadena) throws SQLException 
	{
		String desc_paso;
		String[] Cadena =StrCadena.split("\\|");
	    int i=0;
				
		try {	
			for (i=3;i<=10;i++){
				
			
				if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath("//div[@id='mis-cuentas-scroll']/div/div/table/tbody/tr["+i+"]/td[2]/span")).getText())) {
					
				
					
					Mdl_Variables.driver.findElement(By.xpath("//div[@id='mis-cuentas-scroll']/div/div/table/tbody/tr["+i+"]/td[2]/span")).click();
					
					Mdl_Variables.strPasosEvidencia = "Seleccionar la opción: "+Mdl_Variables.arrKeyValue[0];
					Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
					
					Mdl_Evidenciamiento.Evidencia_Log(true, "");
					Mdl_Evidenciamiento.Ingreso_Datos_Log();
					
				
				
					
					
				}
				
					
				}
	
			Thread.sleep(3000);
			
			Thread.sleep(3000);
			} catch (Exception e) {
		System.out.println("Error en Mdl_Bnet: seleccionar opción " + e.getMessage());
					}
					
	}
	

	public static void RealizarTRX(String StrCadena) throws SQLException 
	{
		String desc_paso;
		String[] Cadena =StrCadena.split("\\|");
		
		
			try{
				
			
				Mdl_Variables.driver.findElement(By.xpath("//div[@id='operaciones-list-container']/div[3]/div/ul/li/a/strong")).click();
				Mdl_Variables.strPasosEvidencia = "Seleccionar la operación  Realizar transferencia";
				Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
				Thread.sleep(3000);
				
				Thread.sleep(3000);
				
			}catch(Throwable e){
				System.out.println("Error al seleccionar: " + e.getMessage());
				
			}
			
					
	}
	
	
	public static void RealizarTRXExterior(String StrCadena) throws SQLException 
	{
		String desc_paso;
		String[] Cadena =StrCadena.split("\\|");
		
		
			try{
				
				Mdl_Variables.driver.findElement(By.xpath("//div[@id='operaciones-list-container']/div[3]/div/ul/li[9]/a/strong")).click();
				Mdl_Variables.strPasosEvidencia = "Seleccionar la operación Realizar transferencia al exterior";
				Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
				
				
				Thread.sleep(3000);
				
				
				
			}catch(Throwable e){
				System.out.println("Error al seleccionar: " + e.getMessage());
				
			}
			
					
	}
	
	
	
	
	public static void RealizarGiro(String StrCadena) throws SQLException 
	{
		String desc_paso;
		String[] Cadena =StrCadena.split("\\|");
		
		int i=0;
			try{
	
				Mdl_Variables.driver.findElement(By.xpath("//div[@id='operaciones-list-container']/div[3]/div/ul/li[5]/a/strong")).click();
            
				Mdl_Variables.strPasosEvidencia = "Seleccionar la operación  Realizar giro nacional";
				Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
				Thread.sleep(3000);
				Thread.sleep(2000);
					
				
				
			}catch(Throwable e){
				System.out.println("Error al seleccionar: " + e.getMessage());
				
			}
			
					
	}
	
	public static void efectivoMovil(String StrCadena) throws SQLException 
	{
		String desc_paso;
		String[] Cadena =StrCadena.split("\\|");
		
		
			try{
				
				Mdl_Variables.driver.findElement(By.xpath("//div[@id='operaciones-list-container']/div[3]/div/ul/li[6]/a/strong")).click();

				Mdl_Variables.strPasosEvidencia = "Seleccionar la operación  Efectivo móvil";
				Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
				
				Thread.sleep(3000);
				Thread.sleep(3000);
				
				
			}catch(Throwable e){
				System.out.println("Error al seleccionar: " + e.getMessage());
				
			}
			
					
	}
	
	
	public static void seleccionarMoneda(String StrCadena) throws SQLException 
	{
		String desc_paso;
		String[] Cadena =StrCadena.split("\\|");
		
		int i=0;
			try{
				
				Mdl_Variables.driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
				
				for (i=1;i<=2;i++){
					
					
					if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath("//div[4]/div/ul/li["+i+"]/a/span")).getText())) {
						
					
						
						Mdl_Variables.driver.findElement(By.xpath("//div[4]/div/ul/li["+i+"]/a/span")).click();
						
						Mdl_Variables.strPasosEvidencia = "Seleccionar la opción "+Mdl_Variables.arrKeyValue[0];
						Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
		 

					}
					
						
					}
					
				Thread.sleep(3000);
				
				
				
			}catch(Throwable e){
				System.out.println("Error al seleccionar: " + e.getMessage());
				
			}
			
					
	}
	
	
	
	public static void seleccionarGastos(String StrCadena) throws SQLException 
	{
		String desc_paso;
		String[] Cadena =StrCadena.split("\\|");
		
		int i=0;
			try{
				
				Mdl_Variables.driver.findElement(By.xpath("(//button[@type='button'])[5]")).click();
				
				for (i=1;i<=2;i++){
					
					
					if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath("//div[5]/div/ul/li["+i+"]/a/span")).getText())) {
						
					
						
						Mdl_Variables.driver.findElement(By.xpath("//div[5]/div/ul/li["+i+"]/a/span")).click();
						
						Mdl_Variables.strPasosEvidencia = "Seleccionar la opción "+Mdl_Variables.arrKeyValue[0];
						Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
						
						

					}
					
						
					}
					
				Thread.sleep(5000);
				
				
				
			}catch(Throwable e){
				System.out.println("Error al seleccionar: " + e.getMessage());
				
			}
			
					
	}

	
	
	public static void seleccionarDocumento(){
		boolean flag = false;
		int i = 1;

		try{
			Mdl_Variables.driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();

			while(i <= 4 && !flag){
				if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath("//div[5]/div/ul/li[" + i + "]/a")).getText())){
					Mdl_Variables.driver.findElement(By.xpath("//div[5]/div/ul/li[" + i + "]/a")).click();

					Mdl_Variables.strPasosEvidencia = "Seleccionar la opción " + Mdl_Variables.arrKeyValue[0];
					Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
					flag = true;
				} i++;
			} Thread.sleep(1000);
		}catch(Throwable e){System.out.println("Error en \"seleccionarDocumento()\" al seleccionar: " + e.getMessage());}
	}
	
	public static void seleccionarLocalidad(){
		boolean flag = false;
		int i = 1;

		try{
			Mdl_Variables.driver.findElement(By.xpath("(//button[@type='button'])[5]")).click();

			while(i <= 43 && !flag){
				if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath("//div[6]/div/ul/li[" + i + "]/a/span")).getText())){
					Mdl_Variables.driver.findElement(By.xpath("//div[6]/div/ul/li[" + i + "]/a/span")).click();

					Mdl_Variables.strPasosEvidencia = "Seleccionar la localidad " + Mdl_Variables.arrKeyValue[0];
					Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
					flag = true;
				} i++;
			} Thread.sleep(1000);
		}catch(Throwable e){System.out.println("Error en \"seleccionarLocalidad()\" al seleccionar: " + e.getMessage());}
	}
	

	//public static void RecargarUrl() 
	//{
		//try {
			//Mdl_Variables.driver.navigate().refresh();
			//Thread.sleep(3000);
			
	//	} catch (Exception e) {
			//System.out.println("Error en Mdl_Davivienda: Recargar Pantalla. " + e.getMessage());
	//	}

	//}
	
	
	//Funcion 
	
	
	

	/**
	 * 
	 * @author Romer Vargas Otiniano
	 * @version 1.0
	 * @date 22042016
	 */
	public static void seleccionarCampania(){
		try{
			String findingTag = "";
			Mdl_Variables.driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();

			for(int i = 1; i <= 7; i++){findingTag = "//div[4]/div/ul/li[" + i + "]/a/span";
				if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath(findingTag)).getText())){
					Mdl_Variables.driver.findElement(By.xpath(findingTag)).click();

					Mdl_Variables.strPasosEvidencia = "Seleccionar la opción " + Mdl_Variables.arrKeyValue[0];
					Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
				}
			} Thread.sleep(1000);
		}catch(Throwable e){System.out.println("Error en \"seleccionarCampania()\" al seleccionar: " + e.getMessage());}
	}
	
	
	/**
	 * 
	 * @author Romer Vargas Otiniano
	 * @version 1.0
	 * @date 15042016
	 */
	public static void realizarDonaciones(){
		try{
			Mdl_Variables.driver.findElement(By.xpath("//div[@id='operaciones-list-container']/div[3]/div/ul/li[8]/a/strong")).click();

			Mdl_Variables.strPasosEvidencia = "Seleccionar la operación  Realizar donaciones";
			Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);

			Thread.sleep(2000);
		}catch(Throwable e){System.out.println("Error en \"realizarDonaciones()\" al seleccionar: " + e.getMessage());}
	}

	/**
	 * 
	 * @author Romer Vargas Otiniano
	 * @version 1.0
	 * @date 04/04/2016
	 */
	public static void ingreso(){
		String findingTag = "";
		boolean flag = false;
		int i = 2;

		try{
			while(i <= 30 && !flag){
				findingTag = "//article[@id='print-section']/div/table/tbody/tr[" + i + "]/td";

				if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath(findingTag + "/span")).getText())){
					Mdl_Variables.driver.findElement(By.xpath(findingTag)).click();

					Mdl_Variables.strPasosEvidencia = "Seleccionar el número de  cuenta " + Mdl_Variables.arrKeyValue[0];
					Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);

					Mdl_Evidenciamiento.Evidencia_Log(true, "");
					Mdl_Evidenciamiento.Ingreso_Datos_Log();
					flag = true;
				} i++;
			} Thread.sleep(1000);
		    //Mdl_Variables.driver.findElement(By.id("tab-operaciones-link")).click();
		   // Mdl_Variables.driver.findElement(By.xpath("//div[3]/div/ul/li/a/strong")).click();
		}catch(Throwable e){System.out.println("Error en \"ingreso()\" al seleccionar: " + e.getMessage());}
	}
	
	/**
	 * 
	 * @author Romer Vargas Otiniano
	 * @version 1.0
	 * @date 20042016
	 */
	public static void seleccionarDetalle(String strCampos){
        String aux = new String();
        boolean flag = false;

        try{
                if(!Web_Framework.GetValue(strCampos).equalsIgnoreCase("<IGNORE>")){
                        aux = Web_Framework.GetValue(strCampos).trim();
                }else{flag = true;}

                if(!flag){
                        String[] arrValores = aux.split("\\:");
                        String findingTag = "";

                        for(int i = 0; i <= arrValores.length; i++){
                                int j = 2;
                                flag = false;

                                while(j <= 20 && !flag){findingTag = "//tr[" + j + "]/td[4]/span"; //findingTag = "//div[2]/div/div/table/tbody/tr[" + j + "]/td[4]/span";
                                        if(arrValores[i].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath(findingTag)).getText())){
                                                Mdl_Variables.driver.findElement(By.xpath(findingTag)).click();

                                                Thread.sleep(3000);
                                                Mdl_Evidenciamiento.Evidencia_Log(true, "");
                                                Mdl_Evidenciamiento.Ingreso_Datos_Log();
                                                Mdl_Variables.strPasosEvidencia = "Seleccionar la opción " + Mdl_Variables.arrKeyValue[0];
                                                Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
                                                flag = true;

                                                //if(i > 1){
                                                        Mdl_Variables.driver.findElement(By.xpath("//div[@id='detalleGeneralHistoricoEfectivoMovil']/header/div/div/span/a/span[2]")).click();        
                                                //}
                                        } j++;
                                }
                        }
                }
        }catch(Throwable e){System.out.println("Error en \"seleccionarDetalle()\" al seleccionar: " + e.getMessage());}
	}
	
	public static void IngresoCuentas(String StrCadena) throws SQLException 
	{
			try{

				Mdl_Variables.driver.findElement(By.xpath("//a[contains(text(),'Cuentas y Ahorro')]")).click();
				Thread.sleep(3000);
				Mdl_Variables.driver.findElement(By.xpath("//a[contains(@href, '/bdntux_pe_web/bdntux_pe_web/transferencias/historico-efectivo-movil/popup-historico-efectivo-movil')]")).click();

				Thread.sleep(3000);

				
			}catch(Throwable e){
				System.out.println("Error al seleccionar: " + e.getMessage());
				
			}
			
					
	}
	
	public static void Desconectar(String StrCadena) throws SQLException 
	{
			try{
			
				Mdl_Variables.driver.findElement(By.linkText("Desconectar")).click();


				
				Thread.sleep(3000);
		
			}catch(Throwable e){
				System.out.println("Error en  " + e.getMessage());
				
			}
			
					
	}
	
	public static void Condiciones(String StrCadena) throws SQLException 
	{
			try{
			
				Mdl_Variables.driver.findElement(By.xpath("//div[@id='section1']/div/aside[2]/div/ul/li[3]/a")).click();

				
				Thread.sleep(3000);
		
			}catch(Throwable e){
				System.out.println("Error al seleccionar: " + e.getMessage());
				
			}
			
					
	}
	
	/**
	 * 
	 * @author Romer Vargas Otiniano
	 * @version 1.0
	 */
	public static void clave(){
		if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
			//
		}else{
			try{	
				
				
				Mdl_Variables.driver.findElement(By.xpath("//input[@name='claveOperacion']")).sendKeys("123456");
				Mdl_Variables.strPasosEvidencia = "Ingresar la clave 123456";
				Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
				Thread.sleep(1000);
				Mdl_Variables.driver.findElement(By.cssSelector("button.btn.next")).click();

				Mdl_Variables.strPasosEvidencia = "Hacer click en el botón Confirmar";
				Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);

				Mdl_Evidenciamiento.Evidencia_Log(true, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();

				Thread.sleep(2000);
				//Mdl_Variables.driver.findElement(By.name("claveOperacion")).sendKeys(Mdl_Variables.arrKeyValue[0]);
			}catch(Throwable e){System.out.println("Error en \"clave()\" no encontró el objeto" + e.getMessage());}
		}
	}
	 
	
	
	public static void LlenarFecha(String Objeto,String strfecha)
	{
		Calendar fecha = new GregorianCalendar();
		int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
//        String[] xpath=Objeto.split("\\=");
        switch(Web_Framework.GetValue(strfecha.toLowerCase()))
        {
        case "ayer":
        	if (dia!=1)
        	{dia=dia-1;}
        	else
        	{
        		if(mes==1||mes==2||mes==4||mes==6||mes==8||mes==9||mes==11)
        		{
        			dia=31;
        		}else{ 
        			if (mes==3){dia=28;}
        			else {dia=30;}
        		}
        		
        	}
        	
        	break;
        case "meses":
        	mes=mes-3;
        	dia=dia+1;
        	break;
        default:
        	String[] Arrfecha=(strfecha.split("\\/"));
        	mes=Integer.parseInt(Arrfecha[0]);
        	dia =Integer.parseInt(Arrfecha[1]);
        break;
        	
        }
        		
     //interactuo con el objeto
        String strmes="Jun";
        
        switch(mes)
        {
        case 1:
        	strmes="Ene";
        	break;
        case 2:
        	strmes="Feb";
        	break;
        case 3:
        	strmes="Mar";
        	break;
        case 4:
        	strmes="Apr";
        	break;
        case 5:
        	strmes="May";
        	break;
        case 6:
        	strmes="Jun";
        	break;
        case 7:
        	strmes="Jul";
        	break;
        case 8:
        	strmes="Ago";
        	break;
        case 9:
        	strmes="Sep";
        	break;
        case 10:
        	strmes="Oct";
        	break;
        case 11:
        	strmes="Nov";
        	break;
        case 12:
        	strmes="Dic";
        	break;
        	
        	
        	
        
        }
//        Mdl_Variables.driver.findElement(By.xpath(xpath[1])).click();
//        Mdl_Variables.driver.findElement(By.xpath("//th[@colspan='5']")).click();
//       Mdl_Variables.driver.findElement(By.xpath("//span[@class='month' and text()='"+ strmes +"']")).click();
//        ////span[@class='month' and text()='Mar']
//        Mdl_Variables.driver.findElement(By.xpath("td[@class='day' and text()='"+dia+"']")).click();
        	
        Mdl_Variables.driver.findElement(By.xpath(Objeto)).click();
        Mdl_Variables.driver.findElement(By.xpath("//th[@colspan='5']")).click();
        Mdl_Variables.driver.findElement(By.xpath("//span[@class='month' and text()='"+ strmes +"']")).click();	
        Mdl_Variables.driver.findElement(By.xpath("//td[@class='day' and text()='"+dia+"']")).click();
		
	}

	
	
	public static void ventana(String tl_ventana) 
	{
		if (tl_ventana!="cerrar")
		{
		 winHandleBefore = Mdl_Variables.driver.getWindowHandle();
		for(String winHandle : Mdl_Variables.driver.getWindowHandles())
		{
			Mdl_Variables.driver.switchTo().window(winHandle);
		}
		}else
		{
			Mdl_Variables.driver.close();
			Mdl_Variables.driver.switchTo().window(winHandleBefore);
		}
		
		/*
		String[] arrHandlers =  new String[10];
		String Titulo="";
		 
		Set<string> handlers = Mdl_Variables.driver.getWindowHandles();
		 
		for(String handler : handlers){
		    arrHandlers[i] = handler;
		    Mdl_Variables.driver = Mdl_Variables.driver.switchTo().window(handler);
		    Titulo=Mdl_Variables.driver.getTitle();
		    if (Titulo.equals("Titulo_Esperado")) {
		        break;
		    }
		}
		</String>*/
		
	}
	
	
	
	public static void lista_valor(String strObjetolista,String strValorBuscar) throws SQLException 
	{

		String[] Objetoslista=strObjetolista.split("\\|");
		String desc_paso="";
		
		try 
		{ 
			Thread.sleep(1000);
			Mdl_Variables.driver.findElement(By.xpath("//div[@"+Objetoslista[0]+"]//span[@class='select2-arrow']")).click();
			Mdl_Variables.driver.findElement(By.xpath("//input[@"+Objetoslista[1]+"]")).clear();
			Mdl_Variables.driver.findElement(By.xpath("//input[@"+Objetoslista[1]+"]")).sendKeys(Web_Framework.GetValue(strValorBuscar));
			Mdl_Variables.driver.findElement(By.xpath("//div[@id='select2-drop']//div[@class='select2-result-label']")).click();
			
			desc_paso = "Se abrió la lista de valor y se ingresó el valor: "+Web_Framework.GetValue(strValorBuscar);
						
		} catch (Exception e) 
		{
			System.out.println("Error en Mdl_Davivienda.Lista_Valor: No se pudo cargar la lista e ingresar el valor: " + e.getMessage());
			Mdl_Evidenciamiento.Evidencia_Log(false, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			Mdl_Variables.boolAction = 0;
			desc_paso = "No se pudo cargar la lista e ingresar el valor: "+Web_Framework.GetValue(strValorBuscar);
		}
		
		Mdl_Evidenciamiento.CADENA_PASOS(desc_paso);
	}
	
	/**
	 * 
	 * @param strCampos
	 * @author Romer Vargas Otiniano
	 * @version 1.0
	 * @date 05/04/2016
	 */
	public static void ultimo_mov(String strCampos) throws SQLException{
		String[] arrCampos = strCampos.split("\\:");
		String[] arrValores = new String[arrCampos.length];
		boolean flag = false;
		int inti = 0;

		while(inti <= arrCampos.length -1 && !flag){
			if(!Web_Framework.GetValue(arrCampos[inti]).equalsIgnoreCase("<IGNORE>")){
				arrValores[inti] = Web_Framework.GetValue(arrCampos[inti]).trim();
			}else{flag = true;}

			inti++;
		}

		String desc_paso = "";
		String findingTag = "";

		try{
			if(!flag){
				Mdl_Variables.driver.findElement(By.xpath("//div[2]/div[2]/ul/li/a")).click();
				Thread.sleep(1000);
				int i = 2;
				int tot = 15;

				while(i <= tot && !flag){
					findingTag = "//article[@id='print-section']/div/table/tbody/tr[" + i + "]/td/span";

					if(arrValores[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath(findingTag)).getText())){
						Mdl_Variables.driver.findElement(By.xpath(findingTag)).click();
						Thread.sleep(1000);

						Mdl_Variables.strPasosEvidencia = "Seleccionar el número de cuenta " + arrValores[0] + " para verificar el movimiento de la cuenta";
						Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);

						Mdl_Evidenciamiento.Evidencia_Log(true, "");
						Mdl_Evidenciamiento.Ingreso_Datos_Log();

						if(arrValores[2].equalsIgnoreCase("<IGNORE>")){
							if(arrValores[1].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath("//table[@id='tbl_movimientos_cuentas']/tbody/tr[2]/td[4]/em")).getText())){
								Mdl_Variables.driver.findElement(By.xpath("//table[@id='tbl_movimientos_cuentas']/tbody/tr[2]/td[4]/em")).click();
								Thread.sleep(2000);
								Mdl_Evidenciamiento.Evidencia_Log(true, "");
								Mdl_Evidenciamiento.Ingreso_Datos_Log();
								Mdl_Variables.driver.findElement(By.xpath("//div[@id='detalleMovimientosCuenta']/header/div/div/span/a/span[2]")).click();
								Mdl_Variables.strPasosEvidencia = "Se verificó correctamente el movimiento con el importe " + arrValores[1] + " en el número de cuenta: " + arrValores[0];
								Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
							}else{
								Mdl_Variables.strPasosEvidencia = "Error al verificar el valor del importe " + arrValores[1] + " en el número de cuenta: " + arrValores[0];
								Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
							}
						}else{
							if(arrValores[2].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath("//table[@id='tbl_movimientos_cuentas']/tbody/tr[2]/td[4]/em")).getText())){
								Mdl_Variables.driver.findElement(By.xpath("//table[@id='tbl_movimientos_cuentas']/tbody/tr[2]/td[4]/em")).click();
								Thread.sleep(2000);
								Mdl_Evidenciamiento.Evidencia_Log(true, "");
								Mdl_Evidenciamiento.Ingreso_Datos_Log();
								Mdl_Variables.driver.findElement(By.xpath("//div[@id='detalleMovimientosCuenta']/header/div/div/span/a/span[2]")).click();
								Mdl_Variables.strPasosEvidencia = "Se verificó correctamente el movimiento con la comisión " + arrValores[2] + " en el número de cuenta: " + arrValores[0];
								Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
							}else{
								Mdl_Variables.strPasosEvidencia = "Error al verificar el valor de la comisión " + arrValores[2] + " en el número de cuenta: " + arrValores[0];
								Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
							}

							if(arrValores[1].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath("//table[@id='tbl_movimientos_cuentas']/tbody/tr[3]/td[4]/em")).getText())){
								Mdl_Variables.driver.findElement(By.xpath("//table[@id='tbl_movimientos_cuentas']/tbody/tr[3]/td[4]/em")).click();
								Thread.sleep(2000);
								Mdl_Evidenciamiento.Evidencia_Log(true, "");
								Mdl_Evidenciamiento.Ingreso_Datos_Log();
								Mdl_Variables.driver.findElement(By.xpath("//div[@id='detalleMovimientosCuenta']/header/div/div/span/a/span[2]")).click();
								Mdl_Variables.strPasosEvidencia = "Se verificó correctamente el movimiento con el importe " + arrValores[1] + " en el número de cuenta: " + arrValores[0];
								Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
							}else{
								Mdl_Variables.strPasosEvidencia = "Error al verificar el valor del importe " + arrValores[1] + " en el número de cuenta: " + arrValores[0];
								Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
							}
						} flag = true;
					} i++;
				}

				if(i > tot && !flag){
					Mdl_Evidenciamiento.Evidencia_Log(true, "");
					Mdl_Evidenciamiento.Ingreso_Datos_Log();
					Thread.sleep(1000);
				} Thread.sleep(1000);
			}
		}catch(Throwable e){System.out.println("Error en \"ultimo_mov()\" al seleccionar: " + e.getMessage());
			//System.out.println("Error:" + e.getMessage());
			//Mdl_Evidenciamiento.Evidencia_Log(false, "");
			//Mdl_Evidenciamiento.Ingreso_Datos_Log();
			//Mdl_Variables.boolAction = 0;
			//desc_paso = "Error "+Web_Framework.GetValue("");
		} Mdl_Evidenciamiento.CADENA_PASOS(desc_paso);
	}
	
	/**
	 * 
	 * @param strCampos
	 * @author Romer Vargas Otiniano
	 * @version 1.0
	 */
	public static void verificar_historico(String strCampos){
		String[] arrCampos = strCampos.split("\\:");
		String[] arrValores = new String[arrCampos.length]; //String[] arrValores = strCampos.split("\\:");
		boolean flag = false;
		int inti = 0;

		while(inti <= arrCampos.length -1 && !flag){ //for(int inti = 0; inti <= arrCampos.length -1; inti ++){
			if(!Web_Framework.GetValue(arrCampos[inti]).equalsIgnoreCase("<IGNORE>")){
				arrValores[inti] = Web_Framework.GetValue(arrCampos[inti]).trim(); //arrValores[inti] = Web_Framework.GetValue(arrCampos[inti]);
			}else{flag = true;}

			inti++;
		}

		try{
			if(!flag){
				Mdl_Variables.driver.findElement(By.linkText("Cuentas y Ahorro")).click();
				Mdl_Variables.strPasosEvidencia = "Ir a la opción Cuentas y Ahorro ";
				Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
				Thread.sleep(1000);

				Mdl_Variables.driver.findElement(By.linkText("Histórico Efectivo móvil")).click();
				Mdl_Variables.strPasosEvidencia = "Ir a Histórico Efectivo móvil";
				Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
				Thread.sleep(1000);
				Mdl_Evidenciamiento.Evidencia_Log(true, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();

				Mdl_Variables.driver.findElement(By.xpath("//div[@id='historico-efectivo-movil']/div/div/div[2]/div/div/table/tbody/tr[2]/td[3]/em")).click();
				Thread.sleep(10000);

				if(arrValores[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath("//table[@id='tabla-detalle-historico-movil']/tbody/tr/td[3]/em")).getText())){
					Mdl_Variables.strPasosEvidencia = "Se verificó correctamente el número de celular " + arrValores[0];
					Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
				}else{
					Mdl_Variables.strPasosEvidencia = "El número de celular " + arrValores[0] + " no se verificó correctamente en el histórico";
					Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
				}

				if(arrValores[2].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath("//div[@id='detalleHistoricoEfectivoMovil']/div/div[2]/dl[2]/dt")).getText())){
					Mdl_Variables.strPasosEvidencia = "Se verificó correctamente el número de cuenta en el detalle " + arrValores[2];
					Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
				}else{
					Mdl_Variables.strPasosEvidencia = "No se verificó correctamente el número de cuenta  " + arrValores[2];
					Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
				}

				if(arrValores[1].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath("//div[@id='detalleHistoricoEfectivoMovil']/div/div[4]/dl[2]/dt")).getText())){
					Mdl_Variables.strPasosEvidencia = "Se verificó correctamente el importe a Enviar  " + arrValores[1];
					Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
				}else{
					Mdl_Variables.strPasosEvidencia = "No se verificó correctamente el importe a Enviar  " + arrValores[1];
					Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
				}

				if(arrValores[3].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath("//div[@id='detalleHistoricoEfectivoMovil']/div/div[4]/dl[2]/dt")).getText())){
					Mdl_Variables.strPasosEvidencia = "Se verificó correctamente el importe a Cargar  " + arrValores[3];
					Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
				}else{
					Mdl_Variables.strPasosEvidencia = "No se verificó correctamente el importe a Cargar  " + arrValores[3];
					Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
				}

				Thread.sleep(1000);
				Mdl_Evidenciamiento.Evidencia_Log(true, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
				Thread.sleep(1000);
				Mdl_Variables.driver.findElement(By.cssSelector("span.close-text")).click();
				Thread.sleep(1000);
				Mdl_Variables.driver.findElement(By.cssSelector("#historico-efectivo-movil > div.modal-content > div.modal-box > div.modal-header > button.close-icon")).click();
			}
		}catch(Throwable e){System.out.println("Error en \"verificar_historico()\" al seleccionar: " + e.getMessage());}
	}
	
	
	public static void tipo_telefono(String StrCadena) throws SQLException 
	{
	    if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
		}else{
		try {	
			Mdl_Variables.driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
			
			//for (i=0;i<=2;i++){
				
			
				if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath("//div[5]/div/ul/li/a")).getText())) {
					
				
					
					Mdl_Variables.driver.findElement(By.xpath("//div[5]/div/ul/li/a")).click();
					
					Mdl_Variables.strPasosEvidencia = "Seleccionar tipo de teléfono : "+Mdl_Variables.arrKeyValue[0];
					Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
					
					Mdl_Evidenciamiento.Evidencia_Log(true, "");
					Mdl_Evidenciamiento.Ingreso_Datos_Log();
					
				
				
					
					
				//}
				
					
				}
	
			Thread.sleep(4000);
			
			Thread.sleep(3000);
			} catch (Exception e) {
		System.out.println("Error en Mdl_Bnet: seleccionar opción " + e.getMessage());
					}
		}		
	}
	
	public static void tipo_operador(String StrCadena) throws SQLException 
	{
	    int i;
	    if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
		}else{
		try {
			Thread.sleep(5000);
			Mdl_Variables.driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
			for (i=1;i<=4;i++){
				
				if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath("//div[6]/div/ul/li["+i+"]/a")).getText())) {
					
					Mdl_Variables.driver.findElement(By.xpath("//div[6]/div/ul/li["+i+"]/a")).click();
					
					Mdl_Variables.strPasosEvidencia = "Seleccionar tipo de operador: "+Mdl_Variables.arrKeyValue[0];
					Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
					
					Mdl_Evidenciamiento.Evidencia_Log(true, "");
					Mdl_Evidenciamiento.Ingreso_Datos_Log();
					}	
			}
	
			Thread.sleep(4000);
			Thread.sleep(3000);
			} catch (Exception e) {
				System.out.println("Error en tipo_operador Mdl_Bnet: seleccionar opción " + e.getMessage());
			}			
		}
	}
	
	public static void editar(String StrCadena) throws SQLException 
	{
		if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
		}else{
				
		try {	
			Mdl_Variables.driver.findElement(By.xpath("//div[@id='telefonoCliente']/div/div[3]/span[3]/a")).click();
			
		
			
				
					Mdl_Variables.strPasosEvidencia = "Seleccionar modificar : ";
					Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
					
					Mdl_Evidenciamiento.Evidencia_Log(true, "");
					Mdl_Evidenciamiento.Ingreso_Datos_Log();
					
	
					Thread.sleep(4000);
					
					Thread.sleep(3000);
					
				
	
		
			} catch (Exception e) {
		System.out.println("Error en Mdl_Bnet: seleccionar opción " + e.getMessage());
					}
		}	
	}
	
	
	/**
     * Editar 1
     * @author Juliet Estefany Agreda Lavado
     * @version 1.0
     */
	public static void editar1(String StrCadena) throws SQLException 
	{
		if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
		}else{	
		try {	
					Mdl_Variables.driver.findElement(By.xpath("//div[@id='telefonoCliente']/div/div[6]/span[3]/a")).click();
					Mdl_Variables.strPasosEvidencia = "Hacer click en  el botón editar de las sección Otros teléfonos ";
					Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
					
					Mdl_Evidenciamiento.Evidencia_Log(true, "");
					Mdl_Evidenciamiento.Ingreso_Datos_Log();

					Thread.sleep(4000);
					Thread.sleep(3000);
			} catch (Exception e) {
				System.out.println("Error en Editar_1 Mdl_Bnet: seleccionar opción " + e.getMessage());
			}
		}			
	}
	
	/**
     * Editar 2
     * @author Juliet Estefany Agreda Lavado
     * @version 1.0
     */
	public static void editar2(String StrCadena) throws SQLException 
	{
		if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
		}else{
		try {	
					Mdl_Variables.driver.findElement(By.xpath("//div[@id='telefonoCliente']/div/div[7]/span[3]/a")).click();
					Mdl_Variables.strPasosEvidencia = "Hacer click en  el botón editar de las sección Otros teléfonos ";
					Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
					
					Mdl_Evidenciamiento.Evidencia_Log(true, "");
					Mdl_Evidenciamiento.Ingreso_Datos_Log();

					Thread.sleep(4000);
					Thread.sleep(3000);
			} catch (Exception e) {
				System.out.println("Error en Editar_2 Mdl_Bnet: seleccionar opción " + e.getMessage());
			}
		}		
	}
	
	/**
     * Editar 3
     * @author Juliet Estefany Agreda Lavado
     * @version 1.0
     */
	public static void editar3(String StrCadena) throws SQLException 
	{
		if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
		}else{
		try {	
					Mdl_Variables.driver.findElement(By.xpath("//div[@id='telefonoCliente']/div/div[8]/span[3]/a")).click();
					Mdl_Variables.strPasosEvidencia = "Hacer click en  el botón editar de la sección Otros teléfonos ";
					Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
					
					Mdl_Evidenciamiento.Evidencia_Log(true, "");
					Mdl_Evidenciamiento.Ingreso_Datos_Log();
					
					Thread.sleep(4000);
					Thread.sleep(3000);
			} catch (Exception e) {
				System.out.println("Error en Editar_3 Mdl_Bnet: seleccionar opción " + e.getMessage());
			}
		}			
	}
	
	/**
     * Editar 4
     * @author Juliet Estefany Agreda Lavado
     * @version 1.0
     */
	public static void editar4(String StrCadena) throws SQLException 
	{
		if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
		}else{	
		try {	
					Mdl_Variables.driver.findElement(By.xpath("//div[@id='telefonoCliente']/div/div[9]/span[3]/a")).click();
					Mdl_Variables.strPasosEvidencia = "Hacer click en  el botón editar";
					Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
					
					Mdl_Evidenciamiento.Evidencia_Log(true, "");
					Mdl_Evidenciamiento.Ingreso_Datos_Log();
	
					Thread.sleep(4000);
					Thread.sleep(3000);
			} catch (Exception e) {
				System.out.println("Error en Editar_4 Mdl_Bnet: seleccionar opción " + e.getMessage());
			}
		}	
	}
	
	/**
     * Eliminar 1
     * @author Juliet Estefany Agreda Lavado
     * @version 1.0
     */
	public static void eliminar1(String StrCadena) throws SQLException 
	{
		if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
		}else{
				
		try {	
					Mdl_Variables.driver.findElement(By.xpath("//div[@id='telefonoCliente']/div/div[6]/span[3]/a[2]")).click();
					Mdl_Variables.strPasosEvidencia = "Hacer click en  el botón eliminar";
					Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
					
					Mdl_Evidenciamiento.Evidencia_Log(true, "");
					Mdl_Evidenciamiento.Ingreso_Datos_Log();

					Thread.sleep(4000);
					Thread.sleep(3000);
			} catch (Exception e) {
				System.out.println("Error Eliminar_1 en Mdl_Bnet: seleccionar opción " + e.getMessage());
			}
		}			
	}
	
	/**
     * Eliminar 2
     * @author Juliet Estefany Agreda Lavado
     * @version 1.0
     */
	public static void eliminar2(String StrCadena) throws SQLException 
	{
		if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
		}else{
		try {	
					Mdl_Variables.driver.findElement(By.xpath("//div[@id='telefonoCliente']/div/div[7]/span[3]/a[2]")).click();
					Mdl_Variables.strPasosEvidencia = "Hacer click en  el botón eliminar";
					Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
					
					Mdl_Evidenciamiento.Evidencia_Log(true, "");
					Mdl_Evidenciamiento.Ingreso_Datos_Log();

					Thread.sleep(4000);
					Thread.sleep(3000);
			} catch (Exception e) {
				System.out.println("Error Eliminar_2 en Mdl_Bnet: seleccionar opción " + e.getMessage());
			}
		}			
	}
	
	/**
     * Eliminar 3
     * @author Juliet Estefany Agreda Lavado
     * @version 1.0
     */
	public static void eliminar3(String StrCadena) throws SQLException 
	{
		if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
		}else{
		try {	
					Mdl_Variables.driver.findElement(By.xpath("//div[@id='telefonoCliente']/div/div[8]/span[3]/a[2]")).click();
					Mdl_Variables.strPasosEvidencia = "Hacer click en  el botón eliminar ";
					Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
					
					Mdl_Evidenciamiento.Evidencia_Log(true, "");
					Mdl_Evidenciamiento.Ingreso_Datos_Log();
					
	
					Thread.sleep(4000);
					Thread.sleep(3000);
			} catch (Exception e) {
				System.out.println("Error Eliminar_3 en Mdl_Bnet: seleccionar opción " + e.getMessage());
			}
		}	
	}
	
	/**
     * Eliminar 4
     * @author Juliet Estefany Agreda Lavado
     * @version 1.0
     */
	public static void eliminar4(String StrCadena) throws SQLException 
	{
		if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
		}else{	
		try {	
					Mdl_Variables.driver.findElement(By.xpath("//div[@id='telefonoCliente']/div/div[9]/span[3]/a[2]")).click();
					Mdl_Variables.strPasosEvidencia = "Hacer click en  el botón eliminar  ";
					Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
					
					Mdl_Evidenciamiento.Evidencia_Log(true, "");
					Mdl_Evidenciamiento.Ingreso_Datos_Log();

					Thread.sleep(4000);
					Thread.sleep(3000);
			} catch (Exception e) {
				System.out.println("Error Eliminar_4 en Mdl_Bnet: seleccionar opción " + e.getMessage());
			}
		}		
	}
	

	/**
     * Modificar Email 1
     * @author Juliet Estefany Agreda Lavado
     * @version 1.0
     */
	public static void boton_email1(String StrCadena) throws SQLException 
	{
		if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
		}else{	
		try {	
				Mdl_Variables.driver.findElement(By.xpath("//div[@id='correoCliente']/div/div[5]/span[3]/a")).click();
					Mdl_Variables.strPasosEvidencia = "Hacer click en  el botón *editar* de la sección email  ";
					Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
					
					Mdl_Evidenciamiento.Evidencia_Log(true, "");
					Mdl_Evidenciamiento.Ingreso_Datos_Log();
					
	
					Thread.sleep(4000);
					Thread.sleep(3000);
			} catch (Exception e) {
				System.out.println("Error Email_1 en Mdl_Bnet: seleccionar opción " + e.getMessage());
			}			
		}
	}
	
	/**
     * Modificar Email 2
     * @author Juliet Estefany Agreda Lavado
     * @version 1.0
     */
	public static void boton_email2(String StrCadena) throws SQLException 
	{
		if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
		}else{	
		try {	
					Mdl_Variables.driver.findElement(By.xpath("//div[@id='correoCliente']/div/div[6]/span[3]/a")).click();
					Mdl_Variables.strPasosEvidencia = "Hacer click en  el botón *editar* de la sección email  ";
					Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
					
					Mdl_Evidenciamiento.Evidencia_Log(true, "");
					Mdl_Evidenciamiento.Ingreso_Datos_Log();

					Thread.sleep(4000);
					Thread.sleep(3000);
			} catch (Exception e) {
				System.out.println("Error Email_2 en Mdl_Bnet: seleccionar opción " + e.getMessage());
			}			
	}
	}
	
	/**
     * Modificar Email 3
     * @author Juliet Estefany Agreda Lavado
     * @version 1.0
     */
	public static void boton_email3(String StrCadena) throws SQLException 
	{
		if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
		}else{	
		try {	
				Mdl_Variables.driver.findElement(By.xpath("//div[@id='correoCliente']/div/div[7]/span[3]/a")).click();
				Mdl_Variables.strPasosEvidencia = "Hacer click en  el botón *editar* de la sección email  ";
				Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
				
				Mdl_Evidenciamiento.Evidencia_Log(true, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();

				Thread.sleep(4000);
				Thread.sleep(3000);
			} catch (Exception e) {
				System.out.println("Error en Mdl_Bnet: seleccionar opción " + e.getMessage());
			}		
		}
	}
	
	/**
     * Modificar Email 4
     * @author Juliet Estefany Agreda Lavado
     * @version 1.0
     */
	public static void boton_email4(String StrCadena) throws SQLException 
	{
		if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
		}else{	
		try {	
					Mdl_Variables.driver.findElement(By.xpath("//div[@id='correoCliente']/div/div[8]/span[3]/a")).click();
					Mdl_Variables.strPasosEvidencia = "Hacer click en  el botón *editar* de la sección email  ";
					Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
					
					Mdl_Evidenciamiento.Evidencia_Log(true, "");
					Mdl_Evidenciamiento.Ingreso_Datos_Log();
					
					Thread.sleep(4000);
					Thread.sleep(3000);
			} catch (Exception e) {
				System.out.println("Error en Mdl_Bnet: seleccionar opción " + e.getMessage());
			}		
		}
	}
	
	
	/**
     * Eliminar Email 1
     * @author Juliet Estefany Agreda Lavado
     * @version 1.0
     */
	public static void eliminar_email1(String StrCadena) throws SQLException 
	{
		if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
		}else{
		try {	
					Mdl_Variables.driver.findElement(By.xpath("//div[@id='correoCliente']/div/div[5]/span[3]/a[2]")).click();
					Mdl_Variables.strPasosEvidencia = "Hacer click en  el botón *eliminar* de la sección email  ";
					Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
					
					Mdl_Evidenciamiento.Evidencia_Log(true, "");
					Mdl_Evidenciamiento.Ingreso_Datos_Log();

					Thread.sleep(4000);
					Thread.sleep(3000);
			} catch (Exception e) {
				System.out.println("Error Eliminar_1 en Mdl_Bnet: seleccionar opción " + e.getMessage());
			}		
		}
	}
	
	/**
     * Eliminar Email 2
     * @author Juliet Estefany Agreda Lavado
     * @version 1.0
     */
	public static void eliminar_email2(String StrCadena) throws SQLException 
	{
		if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
		}else{
		try {	
				Mdl_Variables.driver.findElement(By.xpath("//div[@id='correoCliente']/div/div[6]/span[3]/a[2]")).click();
				Mdl_Variables.strPasosEvidencia = "Hacer click en  el botón *eliminar* de la sección email  ";
				Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
					
				Mdl_Evidenciamiento.Evidencia_Log(true, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
					
				Thread.sleep(4000);
				Thread.sleep(3000);
			} catch (Exception e) {
				System.out.println("Error Eliminar_2 en Mdl_Bnet: seleccionar opción " + e.getMessage());
			}		
		}
	}
	
	/**
     * Eliminar Email 3
     * @author Juliet Estefany Agreda Lavado
     * @version 1.0
     */
	public static void eliminar_email3(String StrCadena) throws SQLException 
	{
		if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
		}else{
		try {	
					Mdl_Variables.driver.findElement(By.xpath("//div[@id='correoCliente']/div/div[7]/span[3]/a[2]")).click();
					Mdl_Variables.strPasosEvidencia = "Hacer click en  el botón *eliminar* de la sección email  ";
					Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
					
					Mdl_Evidenciamiento.Evidencia_Log(true, "");
					Mdl_Evidenciamiento.Ingreso_Datos_Log();
					
					Thread.sleep(4000);
					Thread.sleep(3000);
			}catch (Exception e) {
				System.out.println("Error Eliminar_3 en Mdl_Bnet: seleccionar opción " + e.getMessage());
			}		
		}
	}
	
	/**
     * Eliminar Email 4
     * @author Juliet Estefany Agreda Lavado
     * @version 1.0
     */
	public static void eliminar_email4(String StrCadena) throws SQLException 
	{
		if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
		}else{
		try {	
					Mdl_Variables.driver.findElement(By.xpath("//div[@id='correoCliente']/div/div[8]/span[3]/a[2]")).click();
					Mdl_Variables.strPasosEvidencia = "Hacer click en  el botón *eliminar* de la sección email  ";
					Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
					
					Mdl_Evidenciamiento.Evidencia_Log(true, "");
					Mdl_Evidenciamiento.Ingreso_Datos_Log();
					
					Thread.sleep(4000);
					Thread.sleep(3000);
			} catch (Exception e) {
				System.out.println("Error Eliminar_4 en Mdl_Bnet: seleccionar opción " + e.getMessage());
			}		
		}
	}
	
	/**
	 * 
	 * @author Romer Vargas Otiniano
	 * @version 1.0
	 */
	public static void boton_email (String StrCadena){
		if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
			//
        }else{
            try{        
            	Mdl_Variables.driver.findElement(By.xpath("//div[@id='correoCliente']/div/div[2]/span[3]/a")).click();

            	Mdl_Variables.strPasosEvidencia = "Hacer click en  el botón editar";
            	Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);

                Mdl_Evidenciamiento.Evidencia_Log(true, "");
                Mdl_Evidenciamiento.Ingreso_Datos_Log();

                Thread.sleep(1000);
            }catch (Exception e){System.out.println("Error en Mdl_Bnet: seleccionar opción " + e.getMessage());}
        }
    }

	/**
	 * 
	 * @author Romer Vargas Otiniano
	 * @version 1.0
	 */
	public static void editar_direccion(){
		if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
			//
        }else{
            try{
                Mdl_Variables.driver.findElement(By.xpath("//div[@id='direccionCliente']/div[2]/span[3]/a")).click();

                Mdl_Variables.strPasosEvidencia = "Hacer click en  el botón *editar* de la sección Dirección ";
                Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);

                Thread.sleep(2000);

                Mdl_Evidenciamiento.Evidencia_Log(true, "");
                Mdl_Evidenciamiento.Ingreso_Datos_Log();
            }catch(Exception e){System.out.println("Error en Mdl_Bnet: seleccionar opción " + e.getMessage());}
        }
    }


	/**
	 * 
	 * @author Romer Vargas Otiniano
	 * @version 1.0
	 */
	public static void tipo_via(){
        if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
        	//
        }else{
            try{
            	Mdl_Variables.driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
            	String tagElement = new String();

                for(int i = 1; i <= 9; i++){tagElement = "//div[6]/div/ul/li[" + i + "]/a";
                    if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath(tagElement)).getText())){
                        Mdl_Variables.driver.findElement(By.xpath(tagElement)).click();

                        Mdl_Variables.strPasosEvidencia = "Seleccionar la opción " + Mdl_Variables.arrKeyValue[0];
                        Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
                    }
                } Thread.sleep(1000);
            }catch(Throwable e){System.out.println("Error en \"tipo_via()\" al seleccionar: " + e.getMessage());}
        }
    }
	
	
	/**
	 * 
	 * @author Romer Vargas Otiniano
	 * @version 1.0
	 */
	public static void tipo_zona(){
		if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
			//
        }else{
        	try{
                Mdl_Variables.driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
                String tagElement = new String();

            	for(int i = 1; i <= 9; i++){tagElement = "//div[7]/div/ul/li[" + i + "]/a/span";
            		if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath(tagElement)).getText())){
                        Mdl_Variables.driver.findElement(By.xpath(tagElement)).click();

                        Mdl_Variables.strPasosEvidencia = "Seleccionar la opción " + Mdl_Variables.arrKeyValue[0];
                        Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
                    }
                } Thread.sleep(1000);
            }catch(Throwable e){System.out.println("Error en \"tipo_zona()\" al seleccionar: " + e.getMessage());}
        }
    }
	
	
	/**
	 * 
	 * @author Romer Vargas Otiniano
	 * @version 1.0
	 */
	public static void departamento(){
		if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
			//
        }else{
        	try{
                Mdl_Variables.driver.findElement(By.xpath("(//button[@type='button'])[5]")).click();
                String tagElement = new String();

            	for(int i = 1; i <= 26; i++){tagElement = "//div[8]/div/ul/li[" + i + "]/a";
            		if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath(tagElement)).getText())){
                        Mdl_Variables.driver.findElement(By.xpath(tagElement)).click();

                        Mdl_Variables.strPasosEvidencia = "Seleccionar la opción " + Mdl_Variables.arrKeyValue[0];
                        Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
                    }
                } Thread.sleep(1000);
            }catch(Throwable e){System.out.println("Error en \"departamento()\" al seleccionar: " + e.getMessage());}
        }
    }

	/**
	 * 
	 * @author Romer Vargas Otiniano
	 * @version 1.0
	 */
	public static void provincia(){
        if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
        	//
        }else{
            try{
            	Mdl_Variables.driver.findElement(By.xpath("(//button[@type='button'])[6]")).click();
            	String tagElement = new String();

                for(int i = 1; i <= 5; i++){tagElement = "//div[9]/div/ul/li["  + i + "]/a";
                    if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath(tagElement)).getText())){
                    	Mdl_Variables.driver.findElement(By.xpath(tagElement)).click();

                        Mdl_Variables.strPasosEvidencia = "Seleccionar la provincia " + Mdl_Variables.arrKeyValue[0];
                        Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
                    }
                } Thread.sleep(1000);
            }catch(Throwable e){System.out.println("Error en \"provincia()\" al seleccionar: " + e.getMessage());}
        }
    }

	/**
	 * 
	 * @author Romer Vargas Otiniano
	 * @version 1.0
	 */
    public static void distrito(){
        if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
        	//
        }else{
            try{
                Mdl_Variables.driver.findElement(By.xpath("(//button[@type='button'])[7]")).click();
                String tagElement = new String();

            	for(int i = 1; i <= 5; i++){tagElement = "//div[10]/div/ul/li[" + i + "]/a/span";
            		if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath(tagElement)).getText())){
                        Mdl_Variables.driver.findElement(By.xpath(tagElement)).click();

                        Mdl_Variables.strPasosEvidencia = "Seleccionar la opción " + Mdl_Variables.arrKeyValue[0];
                        Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
                    }
        		} Thread.sleep(1000);
            }catch(Throwable e){System.out.println("Error en \"distrito()\" al seleccionar: " + e.getMessage());}
        }
    }

    /**
	 * 
	 * @param StrCadena
	 * @param type
	 * @author Romer Vargas Otiniano
	 * @version 1.0
	 */
	public static void seleccionador_telefono_email(String StrCadena, int type){
		try{
			String findingTag = "";
			String clickTag = "";
			boolean flag = false;
			String txtMsg = "";
			int i = 1;

			if(type == 1){clickTag = "(//button[@type='button'])[3]"; txtMsg = "teléfono";}
			else{clickTag = "(//button[@type='button'])[4]"; txtMsg = "email";}

			Mdl_Variables.driver.findElement(By.xpath(clickTag)).click();

			while(!flag && i <= 10){
				if(type == 1){findingTag = "//div[5]/div/ul/li[" + i + "]/a/span";}
				else{findingTag = "//div[6]/div/ul/li[" + i + "]/a/span";}

				if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath(findingTag)).getText())){flag = true;
					Mdl_Variables.driver.findElement(By.xpath(findingTag)).click();
					Mdl_Variables.strPasosEvidencia = "Seleccionar el " + txtMsg + " :" + Mdl_Variables.arrKeyValue[0];
					Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
				} i++;
			}
		}catch(Exception e){System.out.println("Error en Mdl_Bnet seleccionador_telefono_email : seleccionar opción " + e.getMessage());}
	}

	/**
	 * Seleccionar Email 
	 * @author Juliet Agreda Lavado
	 * @version 1.0
	 */
	public static void seleccionar_email_card(String StrCadena) throws SQLException 
	{
	    int i = 1 ;
	    if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
		}else{
		try {
			Thread.sleep(5000);
			
			Mdl_Variables.driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
			while(i<=5){
				
				if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath("//div[7]/div/ul/li["+i+"]/a/span")).getText())){
					
						Mdl_Variables.driver.findElement(By.xpath("//div[7]/div/ul/li["+i+"]/a/span")).click();
						Mdl_Variables.strPasosEvidencia = "Seleccionar Email: "+Mdl_Variables.arrKeyValue[0];
						
						Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
						Mdl_Evidenciamiento.Evidencia_Log(true, "");
						Mdl_Evidenciamiento.Ingreso_Datos_Log();
					} i++;
			}
			Thread.sleep(3000);
			} catch (Exception e) {
				System.out.println("Error en seleccionador_email_card Mdl_Bnet: seleccionar opción " + e.getMessage());
			}			
		}
	}
	
	/**
	 * Seleccionar Telefono 
	 * @author Juliet Agreda Lavado
	 * @version 1.0
	 */
	public static void seleccionar_telefono_card(String StrCadena) throws SQLException 
	{
	    int i = 1 ;
	    if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
		}else{
		try {
			Thread.sleep(5000);
			
			Mdl_Variables.driver.findElement(By.xpath("(//button[@type='button'])[5]")).click();
			while(i<=5){
				
				if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath("//div[8]/div/ul/li["+i+"]/a/span")).getText())){
					
						Mdl_Variables.driver.findElement(By.xpath("//div[8]/div/ul/li["+i+"]/a/span")).click();
						Mdl_Variables.strPasosEvidencia = "Seleccionar Telefono: "+Mdl_Variables.arrKeyValue[0];
						
						Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
						Mdl_Evidenciamiento.Evidencia_Log(true, "");
						Mdl_Evidenciamiento.Ingreso_Datos_Log();
					} i++;
			}
			Thread.sleep(3000);
			} catch (Exception e) {
				System.out.println("Error en seleccionador_telefono_card Mdl_Bnet: seleccionar opción " + e.getMessage());
			}			
		}
	}

	/**
	 * 
	 * @param type
	 * @author Romer Vargas Otiniano
	 * @version 1.0
	 */
	public static void seleccionador_dia_semana(){
		try{
			String findingTag = "";
			boolean flag = false;
			int i = 1;

			Mdl_Variables.driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();

			while(!flag && i <= 7){
				
				findingTag = "//div[6]/div/ul/li[" + i + "]/a/span";

				if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath(findingTag)).getText())){flag = true;
					Mdl_Variables.driver.findElement(By.xpath(findingTag)).click();
					Mdl_Variables.strPasosEvidencia = "Seleccionar el dia de semana :" + Mdl_Variables.arrKeyValue[0];
					Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
				} i++;
			}
		}catch(Throwable e){System.out.println("Error en \"seleccionador_dia_semana()\" al seleccionar opción " + e.getMessage());}
	}

	/**
	 * 
	 * @author Romer Vargas Otiniano
	 * @version 1.0
	 */
	public static void bajar_scroll(){
		try{
			if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("DOWN")){
				Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_PAGE_DOWN);
				robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
				Thread.sleep(1000);
				Mdl_Evidenciamiento.Evidencia_Log(true, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
				robot.keyPress(KeyEvent.VK_PAGE_DOWN);
				robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
				Thread.sleep(1000);
				Mdl_Evidenciamiento.Evidencia_Log(true, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
				robot.keyPress(KeyEvent.VK_PAGE_DOWN);
				robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
				Thread.sleep(1000);
				Mdl_Evidenciamiento.Evidencia_Log(true, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
			}
		}catch(Throwable e){System.out.println("Error en \"bajar_scroll()\" al seleccionar opción " + e.getMessage());}
	}

	/**
	 * 
	 * @author Romer Vargas Otiniano
	 * @version 1.0
	 */
	public static void bajar_scroll_telefono(){
		try{
			if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("DOWN")){
				Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_PAGE_DOWN);
				robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
				Thread.sleep(1000);
				Mdl_Evidenciamiento.Evidencia_Log(true, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
			}
		}catch(Throwable e){System.out.println("Error en \"bajar_scroll_telefono()\" al seleccionar opción " + e.getMessage());}
	}

	/**
	 * 
	 * @author Romer Vargas Otiniano
	 * @version 1.0
	 */
	public static void bajar_scroll_email(){
		try{
			if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("DOWN")){
				Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_PAGE_DOWN);
				robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
				robot.keyPress(KeyEvent.VK_PAGE_DOWN);
				robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
				Thread.sleep(1000);
				Mdl_Evidenciamiento.Evidencia_Log(true, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
			}
		}catch(Throwable e){System.out.println("Error en \"bajar_scroll_email()\" al seleccionar opción " + e.getMessage());}
	}

	public static void anadir_telefono(String StrCadena) throws SQLException 
	{
		int i=0;
		if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
		}else{
			try{
				
				Mdl_Variables.driver.findElement(By.xpath("(//button[@type='button'])[7]")).click();
				
				for (i=1;i<=5;i++){
					
					
					if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath("//div[9]/div/ul/li["+i+"]/a/span")).getText())) {
						
					
						
						Mdl_Variables.driver.findElement(By.xpath("//div[9]/div/ul/li["+i+"]/a/span")).click();
						
						Mdl_Variables.strPasosEvidencia = "Seleccionar la opción "+Mdl_Variables.arrKeyValue[0];
						Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
		 

					}else{
						
					}
					
						
					}
					
				Thread.sleep(7000);
				
				
				
			}catch(Throwable e){
				System.out.println("Error al seleccionar: " + e.getMessage());
				
			}
			
					
	}
	
	}
	
	
	/**
	 * 
	 * @param strCampos
	 * @author Romer Vargas Otiniano
	 * @version 1.0
	 * @date 04/04/2016
	 */
	public static void ultimo_movdonaciones(String strCampos) throws SQLException{
		String[] arrCampos = strCampos.split("\\:");
		String[] arrValores = new String[arrCampos.length];
		boolean flag = false;
		int inti = 0;

		while(inti <= arrCampos.length -1 && !flag){
			if(!Web_Framework.GetValue(arrCampos[inti]).equalsIgnoreCase("<IGNORE>")){
				arrValores[inti] = Web_Framework.GetValue(arrCampos[inti]).trim();
			}else{flag = true;}

			inti++;
		}

		String desc_paso="";
		String findingTag = "";

		try{
			if(!flag){
				Mdl_Variables.driver.findElement(By.xpath("//div[2]/div[2]/ul/li/a")).click();
				Thread.sleep(1000);
				int i = 2;
				int tot = 15;

				while(i <= tot && !flag){
					findingTag = "//article[@id='print-section']/div/table/tbody/tr[" + i + "]/td/span";

					if(arrValores[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath(findingTag)).getText())){
						Mdl_Variables.driver.findElement(By.xpath(findingTag)).click();
						Thread.sleep(2000);

						Mdl_Variables.strPasosEvidencia = "Seleccionar el número de cuenta " + arrValores[0] + " para verificar el movimiento de la cuenta";
						Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);

						Mdl_Evidenciamiento.Evidencia_Log(true, "");
						Mdl_Evidenciamiento.Ingreso_Datos_Log();
						flag = true;

						if(arrValores[1].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath("//table[@id='tbl_movimientos_cuentas']/tbody/tr[2]/td[4]/em")).getText())){
							Mdl_Variables.driver.findElement(By.xpath("//table[@id='tbl_movimientos_cuentas']/tbody/tr[2]/td[4]/em")).click();
							Thread.sleep(2000);
							Mdl_Evidenciamiento.Evidencia_Log(true, "");
							Mdl_Evidenciamiento.Ingreso_Datos_Log();
							Mdl_Variables.driver.findElement(By.xpath("//div[@id='detalleMovimientosCuenta']/header/div/div/span/a/span[2]")).click();
							Mdl_Variables.strPasosEvidencia = "Se verificó correctamente el movimiento con el importe " + arrValores[1] + " en el número de cuenta: " + arrValores[0];
							Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
						}else{
							Mdl_Variables.strPasosEvidencia = "Error al verificar el valor del importe  " + arrValores[1] + " en el número de cuenta: " + arrValores[0];
							Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
						}
					}else{
						Mdl_Variables.strPasosEvidencia = "Error al seleccionar el número de cuenta " + arrValores[0] + " para verificar el movimiento de la cuenta";
						Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
					} i++;
				}

				if(i > tot && !flag){
					Mdl_Evidenciamiento.Evidencia_Log(true, "");
					Mdl_Evidenciamiento.Ingreso_Datos_Log();
					Thread.sleep(1000);
				} Thread.sleep(1000);
			}
		}catch(Exception e){System.out.println("Error en \"ultimo_movdonaciones()\" al seleccionar: " + e.getMessage());
			//System.out.println("Error:" + e.getMessage());
			//Mdl_Evidenciamiento.Evidencia_Log(false, "");
			//Mdl_Evidenciamiento.Ingreso_Datos_Log();
			//Mdl_Variables.boolAction = 0;
			//desc_paso = "Error "+Web_Framework.GetValue("");
		} Mdl_Evidenciamiento.CADENA_PASOS(desc_paso);
	}

	/**
	 * 
	 * @author Romer Vargas Otiniano
	 * @version 1.0
	 * @date 05/04/2016
	 */
	public static void realizarTRX(){
		try{
			Mdl_Variables.driver.findElement(By.xpath("//div[@id='operaciones-list-container']/div[3]/div/ul/li/a/strong")).click();
			Mdl_Variables.strPasosEvidencia = "Seleccionar la operación a realizar transferencia";
			Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
			Thread.sleep(1000);
		}catch(Throwable e){System.out.println("Error en \"realizarTRX()\" al seleccionar: " + e.getMessage());}
	}
	

	/**
	 * 
	 * @author Romer Vargas Otiniano
	 * @version 1.0
	 * @date 28/04/2016
	 */
	public static void seleccionarOpcion(){
		String findingTag = "";
		boolean flag = false;
	    int i = 2;

		try{
			JavascriptExecutor js = (JavascriptExecutor) Mdl_Variables.driver;
			WebElement element = Mdl_Variables.driver.findElement(By.xpath("//div[@id='mis-cuentas-scroll']"));
			js.executeScript("arguments[0].setAttribute('style', 'height: 2000px')",element);

			while(i <= 15 && !flag){findingTag = "//div[@id='mis-cuentas-scroll']/div/div/table/tbody/tr[" + i + "]/td[2]/span"; //findingTag = "//div[@id='mis-cuentas-scroll']/div/div/table/tbody/tr[" + i + "]/td/label";
				if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath(findingTag)).getText())){
					Mdl_Variables.driver.findElement(By.xpath(findingTag)).click();
					Mdl_Variables.strPasosEvidencia = "Seleccionar la opión: " + Mdl_Variables.arrKeyValue[0];
					Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);

					Mdl_Evidenciamiento.Evidencia_Log(true, "");
					Mdl_Evidenciamiento.Ingreso_Datos_Log();
					flag = true;
				} i++;
			} Thread.sleep(1000);
		}catch(Exception e){System.out.println("Error en \"seleccionarOpcion()\" al seleccionar opción " + e.getMessage());}					
	}

	/**
	 * 
	 * @author Romer Vargas Otiniano
	 * @version 1.0
	 * @date 06/04/2016
	 */
	public static void bajar_pagina_flecha(){
		try{
			if(Mdl_Variables.arrKeyValue[0] != ""){
				int tot = Integer.parseInt(Mdl_Variables.arrKeyValue[0]);
				Robot robot = new Robot();

				for(int i = 0; i < tot; i++){
					robot.keyPress(KeyEvent.VK_DOWN);
					robot.keyRelease(KeyEvent.VK_DOWN);
				} Thread.sleep(1000);
			}
		}catch(Throwable e){System.out.println("Error en \"bajar_pagina_flecha()\" al seleccionar opción " + e.getMessage());}
	}

	/**
	 * 
	 * @author Romer Vargas Otiniano
	 * @version 1.0
	 * @date 18/04/2016
	 */
	public static void efectivoMovil(){
		try{
			Mdl_Variables.driver.findElement(By.xpath("//div[@id='operaciones-list-container']/div[3]/div/ul/li[3]/a/strong")).click();

			Mdl_Variables.strPasosEvidencia = "Seleccionar la operación efectivo móvil";
			Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);

			Thread.sleep(1000);
		}catch(Throwable e){System.out.println("Error en \"efectivoMovil()\" al seleccionar: " + e.getMessage());}
	}
	
    /**
     * Seleccionar del Combo un Telefono
     * @author Juliet
	 * @version 1.0
     * */
	
    public static void seleccionar_telefono(String StrCadena) throws SQLException 
	{
	    int i = 1 ;
	    if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
		}else{
		try {
			Thread.sleep(5000);
			Mdl_Variables.driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
			while(i<=3){
				
				if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath("//div[7]/div/ul/li["+i+"]/a/span")).getText())){
					
						Mdl_Variables.driver.findElement(By.xpath("//div[7]/div/ul/li["+i+"]/a/span")).click();
						Mdl_Variables.strPasosEvidencia = "Seleccionar Telefono: "+Mdl_Variables.arrKeyValue[0];
						
						Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
						Mdl_Evidenciamiento.Evidencia_Log(true, "");
						Mdl_Evidenciamiento.Ingreso_Datos_Log();
					} i++;
			}
			Thread.sleep(3000);
			} catch (Exception e) {
				System.out.println("Error en seleccionar_telefono Mdl_Bnet: seleccionar opción " + e.getMessage());
			}			
		}
	}
    
    
    /**
     * Seleccionar del Combo un Email
     * @author Juliet
	 * @version 1.0
     * */
	
    public static void seleccionar_email(String StrCadena) throws SQLException 
	{
	    int i = 1 ;
	    if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
		}else{
		try {
			Thread.sleep(5000);
			
			Mdl_Variables.driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
			while(i<=3){
				
				if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath("//div[6]/div/ul/li["+i+"]/a/span")).getText())){
					
						Mdl_Variables.driver.findElement(By.xpath("//div[6]/div/ul/li["+i+"]/a/span")).click();
						Mdl_Variables.strPasosEvidencia = "Seleccionar Email: "+Mdl_Variables.arrKeyValue[0];
						
						Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
						Mdl_Evidenciamiento.Evidencia_Log(true, "");
						Mdl_Evidenciamiento.Ingreso_Datos_Log();
					} i++;
			}
			Thread.sleep(3000);
			} catch (Exception e) {
				System.out.println("Error en seleccionar_email Mdl_Bnet: seleccionar opción " + e.getMessage());
			}			
		}
	}
    
    /**
     * 
     * @author Romer Vargas Otiniano
     * @version 1.0
     * @date 12/04/2016
     */
    public static void abrir_bnet_actual(){
            String urlBNetActual = "https://150.250.251.37:743/principal_bd2.html";
            String[] arrCampos = "dt_TXT_USUARIO_TARJETA:".split("\\:");
            String[] arrValores = new String[arrCampos.length];
            boolean flag = false;
            int inti = 0;

            try{
                    while(inti <= arrCampos.length -1 && !flag){
                            if(!Web_Framework.GetValue(arrCampos[inti]).equalsIgnoreCase("<IGNORE>")){
                                    arrValores[inti] = Web_Framework.GetValue(arrCampos[inti]).trim();
                            }else{flag = true;}

                            inti++;
                    }

                    if(!flag){
                            launchApp_BNetActual(urlBNetActual);
                            Thread.sleep(2000);        
                            login_BNetActual(arrValores[0]);
                    }
            }catch(Throwable e){System.out.println("Error al seleccionar: " + e.getMessage());}
    }

    /**
     * 
     * @param LaunchApp urlBNetActual
     * @author Romer Vargas Otiniano
     * @version 1.0
     * @date 12/04/2016
     */
    public static void launchApp_BNetActual(String urlBNetActual){
            try{
                    switch(Keyword.getValue(Mdl_Variables.Tipo_Aplicativo.toLowerCase())){
                            case chrome: // Optional, if not specified, WebDriver will search your path for chromedriver.
                                    System.setProperty("webdriver.chrome.driver", "C:\\Proyectos\\BNET\\lib\\chromedriver.exe");                                                          
                                    DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                                    capabilities.setCapability("chrome.switches", Arrays.asList("--start-maximized"));                                                          
                                    Thread.sleep(2000);  // Let the user actually see something!
                                    Mdl_Variables.driver = new ChromeDriver(capabilities);                                                  
                                    Thread.sleep(3000);  // Let the user actually see something!
                                    Mdl_Variables.driver.manage().window().maximize();
                                    break;
                            case firefox:
                                    Mdl_Variables.driver = new FirefoxDriver();        
                                    Mdl_Variables.driver.manage().window().maximize();
                                    Mdl_Variables.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                                    Mdl_Variables.driver.manage().window().maximize();
                                    Thread.sleep(3000);  // Let the user actually see something!                                       
                                    break;
                            default:
                                    //INTERNET EXPLORER
                                    //Para SO de 32 bits:
                                    //File iedriver = new File("lib/selenium-2.28.0/IEDriverServer 32/IEDriverServer.exe");
                                    File iedriver = new File("lib/IEDriverServer.exe");
    
                                    //Para SO de 64 bits:
                                    //File iedriver = new File("lib/selenium-2.28.0/IEDriverServer 64/IEDriverServer.exe");
                                    System.setProperty("webdriver.ie.driver", iedriver.getAbsolutePath());
                                    Mdl_Variables.driver = new InternetExplorerDriver();
                                    Mdl_Variables.driver.manage().window().maximize();
                                    Thread.sleep(3000);  // Let the user actually see something!
                                    break;
                    }

                    Mdl_Variables.P_Vrb_Browser = urlBNetActual; //Mdl_Tuya.obtenerUrlAplicativo();
                    Mdl_Variables.driver.get(urlBNetActual); 
                    Mdl_Choucair.Click_Aceptar();
    
                    Mdl_Variables.strPasosEvidencia = "Se realiza el ingreso a la URL: " + urlBNetActual;
                    Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
            }catch(Throwable e){System.out.println("Error al seleccionar: " + e.getMessage());}
    }

    /**
     * 
     * @param userTarjeta
     * @author Romer Vargas Otiniano
     * @version 1.0
     * @date 12/04/2016
     */
    public static void login_BNetActual(String userTarjeta){
            try{
                    Mdl_Variables.driver.findElement(By.id("initial_input")).clear();
                    Mdl_Variables.driver.findElement(By.name("eai_password")).clear();
                    Mdl_Variables.driver.findElement(By.id("initial_input")).sendKeys(userTarjeta);
                    Mdl_Variables.driver.findElement(By.name("eai_password")).sendKeys("gestion99");
                    Thread.sleep(1000);
                    Mdl_Evidenciamiento.Evidencia_Log(true, "");
                    Mdl_Evidenciamiento.Ingreso_Datos_Log();
                    Mdl_Variables.driver.findElement(By.xpath("//a/img")).click();
                    Mdl_Variables.strPasosEvidencia = "Hacer click en el botón Ingresar";
                    Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
                    Thread.sleep(1000);
            }catch(Throwable e){System.out.println("Error al seleccionar: " + e.getMessage());}
    }
    
    /**
     * Seleccionar numero de tarjeta 
     * @author Juliet
	 * @version 1.0
     * */
    public static void ingreso_tarjetas(){
		String findingTag = "";
		boolean flag = false;
		int i = 2;

		try{
			while(i <= 15 && !flag){ 
				findingTag = "//table[@id='tbl_tarjetas_resumen']/tbody/tr[" + i + "]/td";
				if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath(findingTag + "/span")).getText())){
					Mdl_Variables.driver.findElement(By.xpath(findingTag)).click();

					Mdl_Variables.strPasosEvidencia = "Seleccionar el número de  cuenta de la tarjeta" + Mdl_Variables.arrKeyValue[0];
					Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);

					Mdl_Evidenciamiento.Evidencia_Log(true, "");
					Mdl_Evidenciamiento.Ingreso_Datos_Log();
					flag = true;
				} i++;
			} Thread.sleep(1000);
		}catch(Exception e){System.out.println("Error en \"ingreso_tarjetas()\" al seleccionar: " + e.getMessage());}
	}
    

	/**
	 * Detalle de Tarjeta 
	 * Juliet Agreda
	 */
	public static void detalle_tarjeta(String strCampos) throws SQLException{
	try{
		String[] arrCampos = strCampos.split("\\:");
		String[] arrValores = new String[arrCampos.length];
		boolean flag = false;
		int inti = 0;

		while(inti <= arrCampos.length -1 && !flag){
			if(!Web_Framework.GetValue(arrCampos[inti]).equalsIgnoreCase("<IGNORE>")){
				arrValores[inti] = Web_Framework.GetValue(arrCampos[inti]).trim();
			}else{flag = true;}
			inti++;
		}
		//String desc_paso = "";
		String findingTag = "";
		
		try{
			if(!flag){
				Thread.sleep(1000);
				int i = 27;
				int tot = 27;
				while(i <= tot && !flag){
					
					findingTag = "//table[@id='tbl_movimientos_tarjeta']/tbody/tr[" + i + "]/td/span";
					
					if(arrValores[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath("//table[@id='tbl_movimientos_tarjeta']/tbody/tr[" + i + "]/td[1]/em")).getText()) &&
					   arrValores[1].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath("//table[@id='tbl_movimientos_tarjeta']/tbody/tr[" + i + "]/td[2]/span")).getText()) &&
					   arrValores[2].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath("//table[@id='tbl_movimientos_tarjeta']/tbody/tr[" + i + "]/td[3]/em")).getText()) ){
						
						Mdl_Variables.driver.findElement(By.xpath(findingTag)).click();
						Mdl_Variables.strPasosEvidencia = "Seleccionar la fecha " + arrValores[0] + ", descripcion "  + arrValores[1] + " y monto "  + arrValores[2] + " para verificar el movimiento de la cuenta.";
						Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
						
						Mdl_Evidenciamiento.Evidencia_Log(true, "");
						Mdl_Evidenciamiento.Ingreso_Datos_Log();
						Thread.sleep(2000);
						flag = true;
					}
					/*
					else{
						Mdl_Variables.strPasosEvidencia = "Error con los datos seleccionados fecha " + arrValores[0] + ",descripcion " + arrValores[1] + " y monto " + arrValores[2] + " para verificar el movimiento de la cuenta";
						Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
					}
					*/
					i++;
				}Thread.sleep(1000);
				/*
				if(i > tot && !flag){
					Mdl_Evidenciamiento.Evidencia_Log(true, "");
					Mdl_Evidenciamiento.Ingreso_Datos_Log();
					Thread.sleep(1000);
				} Thread.sleep(1000);
				*/
			}
		}catch(Exception e){System.out.println("Error en \"detalle_tarjeta()\" al seleccionar: " + e.getMessage());} //Mdl_Evidenciamiento.CADENA_PASOS(desc_paso);
	}catch(Exception e){System.out.println("Error en \"detalle_tarjeta_afuera()\" al seleccionar: " + e.getMessage());}
	}
	
	/**
	 * Click en boton tercero en realizar transferencias
	 * @author Juliet Agreda Lavado
	 * @version 1.0
	 * @date 05/04/2016
	 */
	public static void tercerosTRX(String cadena)throws SQLException 
	{
		//String desc_paso;
		//String[] Cadena =StrCadena.split("\\|");
	    
	    if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
		}else{
		try {
			Thread.sleep(5000);
			
			Mdl_Variables.driver.findElement(By.xpath("//div[@id='wizard-slide-viewer']/div/div/div/div/ul/li[2]/a")).click();
			Mdl_Variables.strPasosEvidencia = "Seleccionar transferencia a Terceros";
			Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
			Thread.sleep(3000);
			}catch(Throwable e){
				System.out.println("Error en \"tercerosTRX()\" al seleccionar: " + e.getMessage());
				}
		}
	}
	
	/**
     * 
     * @author Romer Vargas Otiniano
     * @version 1.0
     * @date 19/04/2016
     */
    public static void claveTemporizador(){
		if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
			//
		}else{
			try{
				Mdl_Variables.strPasosEvidencia = "Se ingresará la clave obtenida del JAR BBVA Ambiente Calidad";
				Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
				Thread.sleep(15000);
			}catch(Throwable e){System.out.println("Error en \"clave()\" no encontró el objeto" + e.getMessage());}
		}
	}

	public static void bnetactual_propias(String strAV1, String strAV2) throws SQLException, InterruptedException 
	{
		String desc_paso;
		String[] arrAV1 = strAV1.split("\\|");
		String[] arrAV2 = strAV2.split("\\|");
		
		String strCuentaOrigen = Web_Framework.GetValue(arrAV1[0]);
		String strCuentaDestino = Web_Framework.GetValue(arrAV1[1]);
		String strMonedaOp = "Soles";
		String strMonto = "0.00";
		
		String strComision = "0.00";
		String strCampoComision = arrAV2[1];
		
		String strSQLaux = "";
		
		Web_Framework.launchapp(Mdl_Variables.Tipo_Aplicativo);
		
		
		try
		{
			if (!Web_Framework.GetValue(arrAV1[2]).toLowerCase().equals("<ignore>"))
			{
				strMonedaOp = "Soles";
			}
			else if (!Web_Framework.GetValue(arrAV1[3]).toLowerCase().equals("<ignore>"))
			{
				strMonedaOp = "Dolares";
			}
			
			if (!Web_Framework.GetValue(arrAV1[4]).toLowerCase().equals("<ignore>"))
			{
				strMonto = Web_Framework.GetValue(arrAV1[4]);
			}
			else if (!Web_Framework.GetValue(arrAV1[5]).toLowerCase().equals("<ignore>"))
			{
				strMonedaOp = Web_Framework.GetValue(arrAV1[5]);
			}
				
			if (strCampoComision.toLowerCase().contains("dt_"))
			{
				strCampoComision = strCampoComision.replace("dt_", "");
			}
			
				Mdl_Choucair.getDatosLogin();
				Mdl_Variables.driver.get(Mdl_Variables.P_Str_RutaAmbienteSecundario);
				Mdl_Variables.driver.findElement(By.id("initial_input")).clear();
				Mdl_Variables.driver.findElement(By.id("initial_input")).sendKeys(Mdl_Variables.Str_DatosUsuario[3]);
				Mdl_Variables.driver.findElement(By.name("eai_password")).clear();
				Mdl_Variables.driver.findElement(By.name("eai_password")).sendKeys(Mdl_Variables.Str_DatosUsuario[10]);
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
				Mdl_Variables.driver.findElement(By.cssSelector("a.c1 > img")).click();
				Thread.sleep(2000);
				Mdl_Variables.driver.findElement(By.id("sec_mis_cuentas")).click();
				Thread.sleep(2000);
				Mdl_Variables.driver.findElement(By.linkText("Transferencias")).click();
				Mdl_Variables.driver.findElement(By.linkText("A tus Cuentas Propias")).click();

				selectByPartOfVisibleText("id","AsuntoPropio",strCuentaOrigen,true);
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
				//new Select(Mdl_Variables.driver.switchTo().frame(0).findElement(By.id("AsuntoPropio"))).selectByVisibleText("0011-0241-02-00501293...CUENTA GANADORA...SOLES...Saldo : 0.00");
			    //new Select(Mdl_Variables.driver.switchTo().frame(0).findElement(By.id("AsuntoPropio"))).selectBy("0011-0241-02-66711825...CONTIAHORRO...EUROS...Saldo : 3,702.78");
				
			    //strCadenaTemp = Mdl_Variables.driver.switchTo().frame(0).findElement(By.xpath("//table[@id='tb_1']/tbody/tr[2]/td[2]/select")).getText();
			    Mdl_Variables.driver.findElement(By.name("Submit")).click();
			    Thread.sleep(2000);
			    selectByPartOfVisibleText("id","AsuntoPropio",strCuentaDestino,false);
			    new Select(Mdl_Variables.driver.findElement(By.id("Divisa"))).selectByVisibleText(strMonedaOp);
			    Mdl_Variables.driver.findElement(By.id("Importe")).clear();
			    Mdl_Variables.driver.findElement(By.id("Importe")).sendKeys(strMonto);
			    Mdl_Variables.driver.findElement(By.id("Observaciones")).clear();
			    Mdl_Variables.driver.findElement(By.id("Observaciones")).sendKeys("referencia de prueba");
			    Mdl_Evidenciamiento.Ingreso_Datos_Log();
			    Mdl_Variables.driver.findElement(By.id("Submit")).click();
			    Mdl_Evidenciamiento.Ingreso_Datos_Log();
			    strComision = Mdl_Variables.driver.findElement(By.xpath("//table[@id='tb_0']/tbody/tr[6]/td[2]/p")).getText();
			    Mdl_Variables.driver.findElement(By.name("Submit")).click();
			    //Mdl_Variables.driver.findElement(By.name("Submit")).click();
			    Mdl_Evidenciamiento.Ingreso_Datos_Log();
			    //Almacenamos los resultado en el DDriven
			    strSQLaux = "UPDATE " + Mdl_Variables.P_Str_Tabla + "_D SET " + strCampoComision.trim() + " = '" + strComision + "' WHERE ID_CASO = " + Mdl_Variables.Rst_Tabla.getInt("ID_CASO");
                Mdl_Variables.Cnn.prepareCall(strSQLaux).execute();
			    
			    //Recargamos el DDriven
                Mdl_Variables.Rst_Tabla.refreshRow();
                //Desconectamos la aplicación 
                //Mdl_Variables.driver.findElement(By.cssSelector("#sec_mis_cuentas > span.nav_descripcion")).click();
                //quitamos el Driver
                //Thread.sleep(4000);
                Mdl_Variables.driver.quit();
				
			}catch(Throwable e){
				System.out.println("Error  " + e.getMessage());
				Mdl_Evidenciamiento.Evidencia_Log(false, "");
				desc_paso = "Error en la operación";
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
				Mdl_Variables.boolAction = 0;
				//Desconectamos la aplicación 
				//Mdl_Variables.driver.findElement(By.cssSelector("#sec_mis_cuentas > span.nav_descripcion")).click();
                //quitamos el Driver
				//Thread.sleep(4000);
                Mdl_Variables.driver.quit();
			}
	}
	
	/**
     * Transferencia a terceros BNET Actual 
     * @author Juliet Estefany Agreda Lavado
     * @version 1.0
     */
	public static void bnetactual_terceros(String strAV1, String strAV2) throws SQLException, InterruptedException 
	{
		
		String desc_paso;
		String[] arrAV1 = strAV1.split("\\|");
		String[] arrAV2 = strAV2.split("\\|");
		
		String strCuentaOrigen = Web_Framework.GetValue(arrAV1[0]);
		String strCuentaDestinoP1 = Web_Framework.GetValue(arrAV1[1]);
		String strCuentaDestinoP2 = Web_Framework.GetValue(arrAV1[2]);
		String strMonedaOp = "Soles";
		String strMonto = "0.00";
		
		String strComision = "0.00";
		String strCampoComision = arrAV2[1];
		
		String strSQLaux = "";
		
		Web_Framework.launchapp(Mdl_Variables.Tipo_Aplicativo);
	
		try
		{
			if (!Web_Framework.GetValue(arrAV1[3]).toLowerCase().equals("<ignore>"))
			{
				strMonedaOp = "Soles";
			}
			else if (!Web_Framework.GetValue(arrAV1[4]).toLowerCase().equals("<ignore>"))
			{
				strMonedaOp = "Dólares";
			}
			
			if (!Web_Framework.GetValue(arrAV1[5]).toLowerCase().equals("<ignore>"))
			{
				strMonto = Web_Framework.GetValue(arrAV1[5]);
			}
			else if (!Web_Framework.GetValue(arrAV1[6]).toLowerCase().equals("<ignore>"))
			{
				strMonto = Web_Framework.GetValue(arrAV1[6]);
				//strMonedaOp = Web_Framework.GetValue(arrAV1[6]);
			}
				
			if (strCampoComision.toLowerCase().contains("dt_"))
			{
				strCampoComision = strCampoComision.replace("dt_", "");
			}
			
				Mdl_Choucair.getDatosLogin();
				Mdl_Variables.driver.get(Mdl_Variables.P_Str_RutaAmbienteSecundario);
				Mdl_Variables.driver.findElement(By.id("initial_input")).clear();
				Mdl_Variables.driver.findElement(By.id("initial_input")).sendKeys(Mdl_Variables.Str_DatosUsuario[3]);
				Mdl_Variables.driver.findElement(By.name("eai_password")).clear();
				Mdl_Variables.driver.findElement(By.name("eai_password")).sendKeys(Mdl_Variables.Str_DatosUsuario[10]);
				//Mdl_Evidenciamiento.Ingreso_Datos_Log();
				Thread.sleep(8000);
				Mdl_Evidenciamiento.Evidencia_Log(true, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
				Thread.sleep(12000);
				

				Mdl_Variables.driver.findElement(By.cssSelector("a.c1 > img")).click();
				Thread.sleep(100000);

				Mdl_Variables.driver.findElement(By.id("sec_mis_cuentas")).click();
				Thread.sleep(50000);

				//Mdl_Variables.driver.findElement(By.id("#sec_mis_cuentas > span.nav_descripcion")).click();
				Mdl_Variables.driver.findElement(By.linkText("Transferencias")).click();
				Thread.sleep(3000);
				Thread.sleep(3000);
				Thread.sleep(3000);
				Mdl_Variables.driver.findElement(By.linkText("A Cuentas de Terceros")).click();
				Thread.sleep(3000);
				Thread.sleep(3000);
				Thread.sleep(3000);
			    // ERROR: Caught exception [ERROR: Unsupported command [selectFrame | marcoDinamico | ]]
				selectByPartOfVisibleText("id","AsuntoPropio",strCuentaOrigen,true);
				
				Mdl_Evidenciamiento.Evidencia_Log(true, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
				Thread.sleep(3000);
				//Mdl_Evidenciamiento.Ingreso_Datos_Log();
				
				//new Select(Mdl_Variables.driver.findElement(By.id("AsuntoPropio"))).selectByVisibleText("0011-0241-02-66711817...CUENTA GANADORA...SOLES...Saldo : 2,834.90");
			    Mdl_Variables.driver.findElement(By.id("Oficinab")).clear();
			    Mdl_Variables.driver.findElement(By.id("Oficinab")).sendKeys(strCuentaDestinoP1);
			    Mdl_Variables.driver.findElement(By.id("Cuentab")).clear();
			    Mdl_Variables.driver.findElement(By.id("Cuentab")).sendKeys(strCuentaDestinoP2);
			    Mdl_Variables.driver.findElement(By.id("importe")).clear();
			    Mdl_Variables.driver.findElement(By.id("importe")).sendKeys(strMonto);
			    new Select(Mdl_Variables.driver.findElement(By.name("Divisa"))).selectByVisibleText(strMonedaOp);
			    Mdl_Variables.driver.findElement(By.name("Observaciones")).clear();
			    Mdl_Variables.driver.findElement(By.name("Observaciones")).sendKeys("referencia de prueba");
			    
			    Mdl_Evidenciamiento.Evidencia_Log(true, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
				Thread.sleep(3000);
			    
			    Mdl_Variables.driver.findElement(By.name("Submit")).click();
			    
			    Thread.sleep(3000);
			    
			    Mdl_Variables.driver.findElement(By.id("ClaveSegundoFactor")).clear();
			    Mdl_Variables.driver.findElement(By.id("ClaveSegundoFactor")).sendKeys("123456");
				
				//strComision = Mdl_Variables.driver.findElement(By.xpath("//tr[7]/td[2]/p")).getText();
				//strComision = Mdl_Variables.driver.findElement(By.xpath("//table[@id='tb_0']/tbody/tr[6]/td[2]/p")).getText();
				
			    Mdl_Variables.driver.findElement(By.name("Submit")).click();
			   
			   // Mdl_Evidenciamiento.Ingreso_Datos_Log();

			   // Mdl_Variables.strPasosEvidencia = "Seleccionar la fecha ";
			 //Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
				
				Mdl_Evidenciamiento.Evidencia_Log(true, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
				Thread.sleep(2000);
			    
			/*
			    strSQLaux = "UPDATE " + Mdl_Variables.P_Str_Tabla + "_D SET " + strCampoComision.trim() + " = '" + strComision + "' WHERE ID_CASO = " + Mdl_Variables.Rst_Tabla.getInt("ID_CASO");
                Mdl_Variables.Cnn.prepareCall(strSQLaux).execute();
			*/
			/*	strSQLaux = "UPDATE " + Mdl_Variables.P_Str_Tabla + "_D SET " + strCampoComision.trim() + " = '" + valor_comision + "' WHERE ID_CASO = " + Mdl_Variables.Rst_Tabla.getInt("ID_CASO");
                Mdl_Variables.Cnn.prepareCall(strSQLaux).execute();
			   */ 
			    //Recargamos el DDriven
          /*
			    Mdl_Variables.Rst_Tabla.refreshRow();
                Mdl_Variables.driver.quit();
            */  
			}catch(Throwable e){
				System.out.println("Error en el logueo: " + e.getMessage());
				Mdl_Evidenciamiento.Evidencia_Log(false, "");
				desc_paso = "No se pudo realizar el logueo";
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
				Mdl_Variables.boolAction = 0;
				Mdl_Variables.driver.quit();
			}
			
		
		
	}
	
	public static void bnetactual_efectivomovil(String StrCadena) throws SQLException 
	{
		String desc_paso;
		String[] Cadena =StrCadena.split("\\|");
	
	
		
		System.setProperty("webdriver.chrome.driver", "C:\\Proyectos\\BNET\\lib\\chromedriver.exe");							  
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability("chrome.switches", Arrays.asList("--start-maximized"));							  
	 // Let the user actually see something!
		Mdl_Variables.driver = new ChromeDriver(capabilities);						  
		  // Let the user actually see something!
		Mdl_Variables.driver.manage().window().maximize();
		
		
			try{
			
				Mdl_Choucair.getDatosLogin();
				Mdl_Variables.driver.get("https://150.250.251.37:743/principal_bd2.html");
				Mdl_Variables.driver.findElement(By.name("eai_password")).clear();
				Mdl_Variables.driver.findElement(By.name("eai_password")).sendKeys("gestion99");
				Mdl_Variables.driver.findElement(By.id("initial_input")).clear();
				Mdl_Variables.driver.findElement(By.id("initial_input")).sendKeys(Mdl_Variables.Str_DatosUsuario[3]);
				Mdl_Variables.driver.findElement(By.cssSelector("a.c1 > img")).click();
				Thread.sleep(8000);
				Mdl_Variables.driver.findElement(By.id("sec_mis_cuentas")).click();
				//Mdl_Variables.driver.findElement(By.id("mainPrincipal")).click();
				Thread.sleep(3000);
				Mdl_Variables.driver.findElement(By.linkText("Efectivo Móvil")).click();
				Thread.sleep(2000);
				Mdl_Variables.driver.findElement(By.linkText("Enviar Efectivo")).click();
				Thread.sleep(2000);
			    // ERROR: Caught exception [ERROR: Unsupported command [selectFrame | marcoDinamico | ]]
			   // new Select(Mdl_Variables.driver.findElement(By.id("AsuntoPropio"))).selectByVisibleText("0011-0241-02-66711817...CUENTA GANADORA...SOLES...Saldo : 2,834.90");
			    Thread.sleep(1000);
			    Mdl_Variables.driver.findElement(By.id("importeEnviado")).clear();
			    Mdl_Variables.driver.findElement(By.id("importeEnviado")).sendKeys("20");
			    Thread.sleep(1000);
			    Mdl_Variables.driver.findElement(By.id("numeroBeneficiario")).clear();
			    Mdl_Variables.driver.findElement(By.id("numeroBeneficiario")).sendKeys("947882478");
			    Mdl_Variables.driver.findElement(By.name("Submit")).click();
			  
				
				
			}catch(Throwable e){
				System.out.println("Error en el logueo: " + e.getMessage());
				Mdl_Evidenciamiento.Evidencia_Log(false, "");
				desc_paso = "No se pudo realizar el logueo";
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
				Mdl_Variables.boolAction = 0;
			}
			
		
		
	}
	
	
	public static void bnetactual_donaciones(String StrCadena) throws SQLException 
	{
		String desc_paso;
		String[] Cadena =StrCadena.split("\\|");
	
	
		
		System.setProperty("webdriver.chrome.driver", "C:\\Proyectos\\BNET\\lib\\chromedriver.exe");							  
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability("chrome.switches", Arrays.asList("--start-maximized"));							  
	 // Let the user actually see something!
		Mdl_Variables.driver = new ChromeDriver(capabilities);						  
		  // Let the user actually see something!
		Mdl_Variables.driver.manage().window().maximize();
		
		
			try{
			
				Mdl_Choucair.getDatosLogin();
				Mdl_Variables.driver.get("https://150.250.251.37:743/principal_bd2.html");
				Mdl_Variables.driver.findElement(By.name("eai_password")).clear();
				Mdl_Variables.driver.findElement(By.name("eai_password")).sendKeys("gestion99");
				Mdl_Variables.driver.findElement(By.id("initial_input")).clear();
				Mdl_Variables.driver.findElement(By.id("initial_input")).sendKeys(Mdl_Variables.Str_DatosUsuario[3]);
				Mdl_Variables.driver.findElement(By.cssSelector("a.c1 > img")).click();
				Thread.sleep(3000);
		
				Mdl_Variables.driver.findElement(By.cssSelector("#sec_mis_cuentas > span.nav_descripcion")).click();
				Thread.sleep(3000);
				Mdl_Variables.driver.findElement(By.linkText("Realiza tus Donaciones")).click();
				Mdl_Variables.driver.findElement(By.linkText("Cruz Roja")).click();
			    // ERROR: Caught exception [ERROR: Unsupported command [selectFrame | marcoDinamico | ]]
			    new Select(Mdl_Variables.driver.findElement(By.id("AsuntoPropio"))).selectByVisibleText("0011-0241-02-00501293...CUENTA GANADORA...SOLES...Saldo : 0.00");
			    Mdl_Variables.driver.findElement(By.name("Importe")).clear();
			    Mdl_Variables.driver.findElement(By.name("Importe")).sendKeys("200");
			    Mdl_Variables.driver.findElement(By.name("Submit")).click();
			    Mdl_Variables.driver.findElement(By.id("ClaveSegundoFactor")).clear();
			    Mdl_Variables.driver.findElement(By.id("ClaveSegundoFactor")).sendKeys("11111111");
			    Mdl_Variables.driver.findElement(By.name("Submit")).click();
		
			}catch(Throwable e){
				System.out.println("Error en el logueo: " + e.getMessage());
				Mdl_Evidenciamiento.Evidencia_Log(false, "");
				desc_paso = "No se pudo realizar el logueo";
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
				Mdl_Variables.boolAction = 0;
			}
			
		
		
	}
	
	
	/**
     * Transferencia Giros Nacionales
     * @author Juliet Estefany Agreda Lavado
     * @version 1.0
     */
	
	public static void bnetactual_girosnacionales(String strAV1) throws SQLException 
	{
		
		String desc_paso;
		String[] arrAV1 = strAV1.split("\\|");
		
		String strCuentaOrigen = Web_Framework.GetValue(arrAV1[0]);
		String strMonto = "0.00";
		String strMonedaOp = "Soles";
		String strBeneficiario = Web_Framework.GetValue(arrAV1[5]);
		String strDocumento = Web_Framework.GetValue(arrAV1[6]);
		String strLocalidad = Web_Framework.GetValue(arrAV1[7]);
		
		Web_Framework.launchapp(Mdl_Variables.Tipo_Aplicativo);
		
		try{
		
			if (!Web_Framework.GetValue(arrAV1[1]).toLowerCase().equals("<ignore>"))
			{
				strMonto = Web_Framework.GetValue(arrAV1[1]);
			}
			else if (!Web_Framework.GetValue(arrAV1[2]).toLowerCase().equals("<ignore>"))
			{
				strMonto = Web_Framework.GetValue(arrAV1[2]);
			}
			
			if (!Web_Framework.GetValue(arrAV1[3]).toLowerCase().equals("<ignore>"))
			{
				strMonedaOp = "Soles";
			}
			else if (!Web_Framework.GetValue(arrAV1[4]).toLowerCase().equals("<ignore>"))
			{
				strMonedaOp = "Dólares";
			}
		
		
		//System.setProperty("webdriver.chrome.driver", "C:\\Proyectos\\BNET\\lib\\chromedriver.exe");							  
		//DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		//capabilities.setCapability("chrome.switches", Arrays.asList("--start-maximized"));							  
	 // Let the user actually see something!
		//Mdl_Variables.driver = new ChromeDriver(capabilities);						  
		  // Let the user actually see something!
		//Mdl_Variables.driver.manage().window().maximize();
		
	
			Mdl_Choucair.getDatosLogin();
			Thread.sleep(3000);
			//Mdl_Variables.driver.get("https://150.250.251.37:743/principal_bd2.html");
			Mdl_Variables.driver.get(Mdl_Variables.P_Str_RutaAmbienteSecundario);
			Thread.sleep(3000);
			Mdl_Variables.driver.findElement(By.id("initial_input")).clear();
			Mdl_Variables.driver.findElement(By.id("initial_input")).sendKeys(Mdl_Variables.Str_DatosUsuario[3]);
			Thread.sleep(3000);
			Thread.sleep(3000);
			Mdl_Variables.driver.findElement(By.name("eai_password")).clear();
			
			Mdl_Variables.driver.findElement(By.name("eai_password")).sendKeys(Mdl_Variables.Str_DatosUsuario[10]);
			
			//Mdl_Variables.driver.findElement(By.name("eai_password")).sendKeys("gestion99");
			Thread.sleep(3000);
			Mdl_Evidenciamiento.Evidencia_Log(true, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			Thread.sleep(3000);
			Thread.sleep(3000);
			Mdl_Variables.driver.findElement(By.cssSelector("a.c1 > img")).click();
			Thread.sleep(5000);

			//Mdl_Variables.driver.findElement(By.id("sec_mis_cuentas")).click();
			//Thread.sleep(3000);
			
			Mdl_Variables.driver.findElement(By.cssSelector("span.icn.ic_mis_cuentas")).click();
			Thread.sleep(3000);
			Mdl_Variables.driver.findElement(By.linkText("Transferencias")).click();
			Thread.sleep(3000);
			Mdl_Variables.driver.findElement(By.linkText("Realiza Giros Nacionales")).click();
			Thread.sleep(3000);

			selectByPartOfVisibleText("id","AsuntoPropio",strCuentaOrigen,true);
			
			Thread.sleep(3000);
			
			Mdl_Evidenciamiento.Evidencia_Log(true, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			Thread.sleep(3000);
			
			Mdl_Variables.driver.findElement(By.name("importe")).clear();
			Thread.sleep(3000);
		    Mdl_Variables.driver.findElement(By.name("importe")).sendKeys(strMonto);
			

		    Thread.sleep(3000);
		    
		    selectByPartOfVisibleText("name","Divisa",strMonedaOp,true);
		    
		    //new Select(Mdl_Variables.driver.findElement(By.name("Divisa"))).selectByVisibleText(strMonedaOp);
		    Thread.sleep(3000);
		    
		    Mdl_Variables.driver.findElement(By.name("beneficiario")).clear();
		    Mdl_Variables.driver.findElement(By.name("beneficiario")).sendKeys(strBeneficiario);
		    
		    Mdl_Variables.driver.findElement(By.id("num_documento")).clear();
		    Mdl_Variables.driver.findElement(By.id("num_documento")).sendKeys(strDocumento);
		    
		    new Select(Mdl_Variables.driver.findElement(By.name("Localidad"))).selectByVisibleText(strLocalidad);
			
			
		    // ERROR: Caught exception [ERROR: Unsupported command [selectFrame | marcoDinamico | ]]
		    
			//new Select(Mdl_Variables.driver.findElement(By.id("AsuntoPropio"))).selectByVisibleText("0011-0241-02-66711337...CUENTA GANADORA...SOLES...Saldo : 9,169,051,270.96");
			   
			    
			//selectByPartOfVisibleText("id","AsuntoPropio",strCuentaOrigen,true);
		    
		    //Mdl_Variables.driver.findElement(By.name("importe")).clear();
		    //Mdl_Variables.driver.findElement(By.name("importe")).sendKeys("100");
		    //Mdl_Variables.driver.findElement(By.name("beneficiario")).clear();
		    //Mdl_Variables.driver.findElement(By.name("beneficiario")).sendKeys("Ariana fernandez");
		    //Mdl_Variables.driver.findElement(By.id("num_documento")).clear();
		    //Mdl_Variables.driver.findElement(By.id("num_documento")).sendKeys("47521478");
		    //new Select(Mdl_Variables.driver.findElement(By.name("Localidad"))).selectByVisibleText("LIMA");
		    Mdl_Variables.driver.findElement(By.name("Submit")).click();
		    
		    Thread.sleep(3000);
			Mdl_Evidenciamiento.Evidencia_Log(true, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			Thread.sleep(3000);
		    
		    Mdl_Variables.driver.findElement(By.id("ClaveSegundoFactor")).clear();
		    Mdl_Variables.driver.findElement(By.id("ClaveSegundoFactor")).sendKeys("123456");
		    
		    Thread.sleep(3000);
			Mdl_Evidenciamiento.Evidencia_Log(true, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			Thread.sleep(3000);
		    
		    Mdl_Variables.driver.findElement(By.name("Submit")).click();
		
			}catch(Throwable e){
				System.out.println("Error en el logueo giros nacionales: " + e.getMessage());
				Mdl_Evidenciamiento.Evidencia_Log(false, "");
				desc_paso = "No se pudo realizar el logueo en giros nacionales";
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
				Mdl_Variables.boolAction = 0;
			}
	}
	
	public static void selectByPartOfVisibleText(String strPropiedad,String strIdSelect, String value, boolean blnFrame) throws InterruptedException {
		List<WebElement> optionElements;
		WebElement selObjeto;
		try
		{
			if (strPropiedad.toLowerCase().equals("id"))
			{
				if (blnFrame == true)
				{
					//optionElements = Mdl_Variables.driver.switchTo().frame(0).findElement(By.id(strIdSelect)).findElements(By.tagName("option"));
					
					selObjeto =  Mdl_Variables.driver.switchTo().frame(0).findElement(By.id(strIdSelect));
					
				}
				else
				{
					
					selObjeto =  Mdl_Variables.driver.findElement(By.id(strIdSelect));
				}
			}
			else if (strPropiedad.toLowerCase().equals("xpath"))
			{
				if (blnFrame == true)
				{
					
					selObjeto =  Mdl_Variables.driver.switchTo().frame(0).findElement(By.xpath(strIdSelect));
				}
				else
				{
					
					selObjeto =  Mdl_Variables.driver.findElement(By.xpath(strIdSelect));
				}
			}
			else if (strPropiedad.toLowerCase().equals("name"))
			{
				if (blnFrame == true)
				{
					
					selObjeto =  Mdl_Variables.driver.switchTo().frame(0).findElement(By.name(strIdSelect));
				}
				else
				{
					
					selObjeto =  Mdl_Variables.driver.findElement(By.name(strIdSelect));
				}
			}
			else 
			{
				if (blnFrame == true)
				{
					
					selObjeto =  Mdl_Variables.driver.switchTo().frame(0).findElement(By.cssSelector(strIdSelect));
				}
				else
				{
					
					selObjeto =  Mdl_Variables.driver.findElement(By.cssSelector(strIdSelect));
				}
			}
			optionElements = selObjeto.findElements(By.tagName("option"));
		
			for (WebElement optionElement: optionElements) {
		        if (optionElement.getText().contains(value)) {
		            String optionIndex = optionElement.getAttribute("index");
		            int intOptionIndex = Integer.parseInt(optionIndex);
		            new Select(selObjeto).selectByIndex(intOptionIndex);
		            break;
		        }
		    }

		    Thread.sleep(300);
		}
		catch(Throwable e){
			System.out.println("se presentó un error al seleccionar en lista: " + e.getMessage());
			Mdl_Evidenciamiento.Evidencia_Log(false, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			Mdl_Variables.boolAction = 0;
			
		}
	    
	}
	
	
	/**
     * Seleccionar del Combo Consultar Filtro Tarjeta
     * @author Juliet
	 * @version 1.0
     * */
	
    public static void seleccionar_periodo(String StrCadena) throws SQLException 
	{
	    int i = 1 ;
	    if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
		}else{
		try {
			Thread.sleep(5000);
			Mdl_Variables.driver.findElement(By.xpath("//button[@type='button']")).click();
			while(i<=2){
			
				if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath("//div[4]/div/ul/li["+i+"]/a")).getText())){
					
						Mdl_Variables.driver.findElement(By.xpath("//div[4]/div/ul/li["+i+"]/a")).click();
						Mdl_Variables.strPasosEvidencia = "Seleccionar Periodo: "+Mdl_Variables.arrKeyValue[0];
						
						Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
						Mdl_Evidenciamiento.Evidencia_Log(true, "");
						Mdl_Evidenciamiento.Ingreso_Datos_Log();
					} i++;
			}
			Thread.sleep(3000);
			} catch (Exception e) {
				System.out.println("Error en seleccionar_periodo Mdl_Bnet: seleccionar opción " + e.getMessage());
			}			
		}
	}
    
    
    
    /**
	 * Seleccionar Numero de Tarjeta de Recarga de Regalo
	 * @author Juliet Agreda Lavado
	 * @version 1.0
	 */
    
    public static void seleccionar_numcta_regalo(String StrCadena) throws SQLException 
	{
	    int i = 2 ;
	    if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
		}else{
		try {
			Thread.sleep(5000);
		//	Mdl_Variables.driver.findElement(By.xpath("//button[@type='button']")).click();
			while(i<=16){
			
				if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath("//div[@id='switch-1']/article/div/table/tbody/tr[" + i + "]/td[2]/span")).getText())){
					
						Mdl_Variables.driver.findElement(By.xpath("//div[@id='switch-1']/article/div/table/tbody/tr[" + i + "]/td[2]/span")).click();
						Mdl_Variables.strPasosEvidencia = "Seleccionar Tarjeta de Regalo: "+Mdl_Variables.arrKeyValue[0];
						
						Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
						Mdl_Evidenciamiento.Evidencia_Log(true, "");
						Mdl_Evidenciamiento.Ingreso_Datos_Log();
					} i++;
			}
			Thread.sleep(3000);
			} catch (Exception e) {
				System.out.println("Error en seleccionar_regalo Mdl_Bnet: seleccionar opción " + e.getMessage());
			}			
		}
	}
    
    
    /**
	 * Click en boton Otros bancos para realizar TRX
	 * @author Juliet Agreda Lavado
	 * @version 1.0
	 */
	public static void otrosbancosTRX(String cadena)throws SQLException 
	{
	    if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
		}else{
		try {
			Thread.sleep(5000);

			Mdl_Variables.driver.findElement(By.xpath("//div[@id='wizard-slide-viewer']/div/div/div/div/ul/li[3]/a")).click();
			Mdl_Variables.strPasosEvidencia = "Seleccionar transferencia a Otros Bancos ";
			Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
			Thread.sleep(3000);
			}catch(Throwable e){
				System.out.println("Error en \"otrosbancosTRX()\" al seleccionar: " + e.getMessage());
			}
		}
	}
	
	/**
	 * Click para seleccionar disponibilidad 
	 * @author Juliet Agreda Lavado
	 * @version 1.0
	 */
	public static void seleccionar_disponibilidad(String cadena)throws SQLException 
	{
		 int i = 1 ;
	    if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
		}else{
			try {
				Thread.sleep(5000);
				while(i<=2){
					
					if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath("//div[@id='paso2TransferenciaEntreCuentas']/div/div/div/div/table/tbody/tr[" + i + "]/th/fieldset/label")).getText())){
							
							Mdl_Variables.driver.findElement(By.xpath("//div[@id='paso2TransferenciaEntreCuentas']/div/div/div/div/table/tbody/tr[" + i + "]/th/fieldset/label")).click();
							Mdl_Variables.strPasosEvidencia = "Seleccionar Disponibilidad de Servicio: "+Mdl_Variables.arrKeyValue[0];
							
							Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
							Mdl_Evidenciamiento.Evidencia_Log(true, "");
							Mdl_Evidenciamiento.Ingreso_Datos_Log();
							
						} i++;
				}
				Thread.sleep(3000);
				} catch (Exception e) {
					System.out.println("Error en seleccionar_disponibilidad Mdl_Bnet: seleccionar opción " + e.getMessage());
				}			
			}
	}
	
	
	/**
     * Seleccionar Documento de Transf. Otros Bancos
     * @author SC
	 * @version 1.5
     * */
	
    public static void seleccionar_docutrxotrosbancos(String StrCadena) throws SQLException 
	{
	    int i = 1 ;
	    if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
		}else{
		try {
			Thread.sleep(5000);
			
			Mdl_Variables.driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
			while(i<=5){
			
				if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath("//div[5]/div/ul/li["+i+"]/a/span")).getText())){
					
						Mdl_Variables.driver.findElement(By.xpath("//div[5]/div/ul/li["+i+"]/a/span")).click();
						Mdl_Variables.strPasosEvidencia = "Seleccionar Documento Beneficiario : "+Mdl_Variables.arrKeyValue[0];
						
						Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
						Mdl_Evidenciamiento.Evidencia_Log(true, "");
						Mdl_Evidenciamiento.Ingreso_Datos_Log();
						
					} i++;
			}
			Thread.sleep(3000);
			} catch (Exception e) {
				System.out.println("Error en seleccionar_docutrxotrosbancos Mdl_Bnet: seleccionar opción " + e.getMessage());
			}			
		}
	}
	
    /**
	 * Pago de Tarjeta de Credito
	 * @author Juliet Agreda Lavado
	 * @version 1.0
	 */
	public static void pagotarjeta(){
		try{
			Mdl_Variables.driver.findElement(By.xpath("//div[@id='operaciones-list-container']/div[3]/div/ul/li[2]/a/strong")).click();
			Mdl_Variables.strPasosEvidencia = "Seleccionar pago de tarjeta de credito.";
			Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
			Thread.sleep(1000);
		}catch(Throwable e){System.out.println("Error en \"pagotarjeta()\" al seleccionar: " + e.getMessage());}
	}
	
	
	/**
     * Seleccionar Clase de tipo de tarjeta de credito
     * @author Juliet
	 * @version 1.0
     * */
	
    public static void seleccionar_clasetarjeta(String StrCadena) throws SQLException 
	{
	    int i = 1 ;
	    if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
		}else{
		try {
			Thread.sleep(5000);
			
			Mdl_Variables.driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
			while(i<=6){
				
				if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath("//div[5]/div/ul/li["+i+"]/a/span")).getText())){
					
						Mdl_Variables.driver.findElement(By.xpath("//div[5]/div/ul/li["+i+"]/a/span")).click();
						Mdl_Variables.strPasosEvidencia = "Seleccionar Clase de Tarjeta: "+Mdl_Variables.arrKeyValue[0];
						
						Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
						Mdl_Evidenciamiento.Evidencia_Log(true, "");
						Mdl_Evidenciamiento.Ingreso_Datos_Log();
						
					} i++;
			}
			Thread.sleep(3000);
			} catch (Exception e) {
				System.out.println("Error en seleccionar_clasetarjeta Mdl_Bnet: seleccionar opción " + e.getMessage());
			}			
		}
	}
    
    /**
     * Seleccionar Banco de la Tarjeta de Credito
     * @author Juliet
	 * @version 1.0
     * */
	
    public static void seleccionar_banco(String StrCadena) throws SQLException 
	{
	    int i = 1 ;
	    if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
		}else{
		try {
			Thread.sleep(5000);
			
			Mdl_Variables.driver.findElement(By.xpath("(//button[@type='button'])[5]")).click();
			while(i<=10){
				
				if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath("//div[6]/div/ul/li["+i+"]/a/span")).getText())){
					
						Mdl_Variables.driver.findElement(By.xpath("//div[6]/div/ul/li["+i+"]/a/span")).click();
						Mdl_Variables.strPasosEvidencia = "Seleccionar Banco: "+Mdl_Variables.arrKeyValue[0];
						
						Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
						Mdl_Evidenciamiento.Evidencia_Log(true, "");
						Mdl_Evidenciamiento.Ingreso_Datos_Log();
						
					} i++;
			}
			Thread.sleep(3000);
			} catch (Exception e) {
				System.out.println("Error en seleccionar_banco Mdl_Bnet: seleccionar opción " + e.getMessage());
			}			
		}
	}
    
    
    /**
     * Seleccionar Lugar de Emision de la Tarjeta de Credito
     * @author Juliet
	 * @version 1.0
     * */
	
    public static void seleccionar_lugaremision(String StrCadena) throws SQLException 
	{
	    int i = 1 ;
	    if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
		}else{
		try {
			Thread.sleep(5000);
			
			Mdl_Variables.driver.findElement(By.xpath("(//button[@type='button'])[6]")).click();
			while(i<=25){
				
				if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath("//div[7]/div/ul/li["+i+"]/a/span")).getText())){
					
					
						Mdl_Variables.driver.findElement(By.xpath("//div[7]/div/ul/li["+i+"]/a/span")).click();
						Mdl_Variables.strPasosEvidencia = "Seleccionar Lugar de Emision: "+Mdl_Variables.arrKeyValue[0];
						
						Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
						Mdl_Evidenciamiento.Evidencia_Log(true, "");
						Mdl_Evidenciamiento.Ingreso_Datos_Log();
						
					} i++;
			}
			Thread.sleep(3000);
			} catch (Exception e) {
				System.out.println("Error en seleccionar_lugaremision Mdl_Bnet: seleccionar opción " + e.getMessage());
			}			
		}
	}
    
    
    /**
	 * Seleccionar Numero de Tarjeta de Recarga de Regalo - Mis Tarjetas
	 * @author Juliet Agreda Lavado
	 * @version 1.0
	 */
    
    public static void seleccionar_numtarj_regalo(String StrCadena) throws SQLException 
	{
	    int i = 2 ;
	    if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
		}else{
		try {
			Thread.sleep(5000);
			while(i<Mdl_Variables.driver.findElements(By.xpath("//div[@id='switch-2']/article/div/table/tbody/tr")).size()-1){
			
				if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath("//div[@id='switch-2']/article/div/table/tbody/tr[" + i + "]/td[2]/span")).getText())){
					
						Mdl_Variables.driver.findElement(By.xpath("//div[@id='switch-2']/article/div/table/tbody/tr[" + i + "]/td[2]/span")).click();
						Mdl_Variables.strPasosEvidencia = "Seleccionar Tarjeta de Regalo - Mis Tarjetas: "+Mdl_Variables.arrKeyValue[0];
						
						Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
						Mdl_Evidenciamiento.Evidencia_Log(true, "");
						Mdl_Evidenciamiento.Ingreso_Datos_Log();
						i=1000;
					} 
				i++;
			}
			Thread.sleep(3000);
			} catch (Exception e) {
				System.out.println("Error en seleccionar_numtarj_regalo Mdl_Bnet: seleccionar opción " + e.getMessage());
			}			
		}
	}
    
    /**
	 * Seleccionar el Radiobutton del Importe 
	 * @author Juliet Agreda Lavado
	 * @version 1.0
	 */
    
    public static void seleccionar_importe(String StrCadena) throws SQLException 
	{
	    int i = 1 ;					
	    if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
		}else{
		try {
			Thread.sleep(5000);
			while(i<=3){
				if(Web_Framework.GetValue("dt_BTN_DOLARES").toLowerCase().compareTo("click") == 0){
					if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath("//div[@id='dolaresPagoTarjetaPropiaTerceros']/div[2]/div/fieldset/div[" + i + "]/label")).getText())){
						
						Mdl_Variables.driver.findElement(By.xpath("//div[@id='dolaresPagoTarjetaPropiaTerceros']/div[2]/div/fieldset/div[" + i + "]/label")).click();
						Mdl_Variables.strPasosEvidencia = "Seleccionar el importe: "+Mdl_Variables.arrKeyValue[0];
						
						Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
						Mdl_Evidenciamiento.Evidencia_Log(true, "");
						Mdl_Evidenciamiento.Ingreso_Datos_Log();
					} i++;
				}
				else{
				if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath("//div[@id='solesPagoTarjetaPropiaTerceros']/div[2]/div/fieldset/div[" + i + "]/label")).getText())){
					
						Mdl_Variables.driver.findElement(By.xpath("//div[@id='solesPagoTarjetaPropiaTerceros']/div[2]/div/fieldset/div[" + i + "]/label")).click();
						Mdl_Variables.strPasosEvidencia = "Seleccionar el importe: "+Mdl_Variables.arrKeyValue[0];
						
						Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
						Mdl_Evidenciamiento.Evidencia_Log(true, "");
						Mdl_Evidenciamiento.Ingreso_Datos_Log();
					} i++;
				}
			}
			Thread.sleep(3000);
			} catch (Exception e) {
				System.out.println("Error en seleccionar_importe Mdl_Bnet: seleccionar opción " + e.getMessage());
			}			
		}
	}  
    
    
    /**
     * BNET actual para Tarjetas
     * @author Juliet Estefany Agreda Lavado
     * @version 1.0
     */
	public static void bnetactual_tarjetas(String strAV1) throws SQLException, InterruptedException 
	{
		String desc_paso= "";
		String[] arrAV1 = strAV1.split("\\|");
		String strNroTarjeta = Web_Framework.GetValue(arrAV1[0]);
		
		Web_Framework.launchapp(Mdl_Variables.Tipo_Aplicativo);
	
		try
		{
			
				Mdl_Choucair.getDatosLogin();
				Thread.sleep(3000);
				//Mdl_Variables.driver.get("https://150.250.251.37:743/principal_bd2.html");
				Mdl_Variables.driver.get(Mdl_Variables.P_Str_RutaAmbienteSecundario);
				Thread.sleep(3000);
				Mdl_Variables.driver.findElement(By.id("initial_input")).clear();
				Mdl_Variables.driver.findElement(By.id("initial_input")).sendKeys(Mdl_Variables.Str_DatosUsuario[3]);
				Thread.sleep(3000);
				Thread.sleep(3000);
				Mdl_Variables.driver.findElement(By.name("eai_password")).clear();
				
				Mdl_Variables.driver.findElement(By.name("eai_password")).sendKeys(Mdl_Variables.Str_DatosUsuario[10]);

				Thread.sleep(3000);
				Mdl_Evidenciamiento.Evidencia_Log(true, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
				Thread.sleep(3000);
				
				Mdl_Variables.driver.findElement(By.cssSelector("a.c1 > img")).click();
				System.out.println("Logeooo:");
				
				Thread.sleep(3000*26);

				System.out.println("Logeooo1:");
				
				Mdl_Variables.driver.findElement(By.id("sec_mis_tarjetas")).click();
				Thread.sleep(5000);
				
				System.out.println("Click en tarjetas:");
				
				Mdl_Evidenciamiento.Evidencia_Log(true, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
				Thread.sleep(1000);
				
				System.out.println("Entro:" + strNroTarjeta);
				Thread.sleep(200);
				String sWind=Mdl_Variables.driver.getWindowHandle();
				if (Web_Framework.GetValue(arrAV1[0]).equalsIgnoreCase("<IGNORE>"))
				{
				}
				else
				{
					
					try{
					
					Thread.sleep(3000);
					
					Mdl_Variables.driver.switchTo().frame(0);
					if (Web_Framework.GetValue(arrAV1[0]).equalsIgnoreCase(Mdl_Variables.driver.findElement(By.linkText(Web_Framework.GetValue(arrAV1[0]))).getText()))
					{
						Thread.sleep(3000);
						
						Mdl_Variables.driver.findElement(By.linkText(Web_Framework.GetValue(arrAV1[0]))).click();
						
						Thread.sleep(3000);
						
						Mdl_Variables.driver.switchTo().window(sWind);
						Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
						Mdl_Evidenciamiento.Evidencia_Log(true, "");
						Mdl_Evidenciamiento.Ingreso_Datos_Log();
						
						
					}
					Thread.sleep(3000);
					}
					catch (Exception e) 
					{
						System.out.println("Error en seleccionar numero de tarjeta Mdl_Bnet: seleccionar opción " + e.getMessage());
					}	
				}
				
				Thread.sleep(3000);
			//	Mdl_Variables.driver.switchTo().frame(sWind);
				Mdl_Evidenciamiento.Evidencia_Log(true, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
				Thread.sleep(3000);
		}
		catch(Throwable e){
				System.out.println("Error en el bnetactual_tarjetas: " + e.getMessage());
				Mdl_Evidenciamiento.Evidencia_Log(false, "");
				desc_paso = "No se pudo realizar el logueo";
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
				Mdl_Variables.boolAction = 0;
				Mdl_Variables.driver.quit();
		}
	}
	
	/**
     * BNet Actual Datos de Afiliacion 
     * @author Juliet Agreda Lavado
     * @version 1.0
     * @date 16/05/2016
     */
	public static void bnetactual_datos_afiliacion() throws SQLException, InterruptedException{

		try{
			if(Web_Framework.GetValue("dt_KWD_BNET_ACTUAL").equals("TRUE")){
				Web_Framework.launchapp(Mdl_Variables.Tipo_Aplicativo);
				Mdl_Choucair.getDatosLogin();
				Mdl_Variables.driver.get(Mdl_Variables.P_Str_RutaAmbienteSecundario);
				Mdl_Variables.driver.findElement(By.id("initial_input")).clear();
				Mdl_Variables.driver.findElement(By.id("initial_input")).sendKeys(Mdl_Variables.Str_DatosUsuario[3]);
				Mdl_Variables.driver.findElement(By.name("eai_password")).clear();
				Mdl_Variables.driver.findElement(By.name("eai_password")).sendKeys(Mdl_Variables.Str_DatosUsuario[10]);

				Thread.sleep(3000);
				Mdl_Evidenciamiento.Evidencia_Log(true, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
				Thread.sleep(3000);
				Thread.sleep(3500);
				Thread.sleep(3500);
				Thread.sleep(3500);
				Thread.sleep(3500);
				Thread.sleep(3500);
				Thread.sleep(3500);
				
				Mdl_Variables.driver.findElement(By.cssSelector("a.c1 > img")).click();
				Thread.sleep(2000);
				Thread.sleep(3000);
				Thread.sleep(3500);
				Thread.sleep(3500);
				Thread.sleep(3500);
				Thread.sleep(3500);
				Thread.sleep(3500);
				Thread.sleep(3500);
				Thread.sleep(3500);
				Thread.sleep(3500);
				Thread.sleep(3500);
				Thread.sleep(3500);
				Thread.sleep(3500);
				Thread.sleep(3500);
				Thread.sleep(3500);
				Thread.sleep(3500);
				Thread.sleep(3500);
				Thread.sleep(3500);
				Thread.sleep(3500);
				Thread.sleep(3500);
				Thread.sleep(3500);
				Mdl_Variables.driver.findElement(By.id("sec_mi_perfil")).click();
				Thread.sleep(3500);
				Thread.sleep(3000);
				Thread.sleep(3000);
				Thread.sleep(3000);
				Thread.sleep(3500);
				Thread.sleep(3500);
				Thread.sleep(3500);
				Thread.sleep(3500);
				Thread.sleep(3500);
				Thread.sleep(3500);
				Thread.sleep(3500);
				Thread.sleep(3500);
				Thread.sleep(3500);
				Thread.sleep(3500);
				Thread.sleep(3500);
				Thread.sleep(3500);
				Thread.sleep(3500);
				Thread.sleep(3500);
				Thread.sleep(3500);
				Thread.sleep(3500);
				Thread.sleep(3500);
				Thread.sleep(3500);
				Thread.sleep(3500);
				Thread.sleep(3500);
				Thread.sleep(3500);
				
				//bajarPaginaFlecha("11");
				
				Mdl_Variables.driver.findElement(By.linkText("Datos Personales")).click();
				Thread.sleep(3000);
				Thread.sleep(3000);
				Mdl_Variables.driver.findElement(By.linkText("Datos de Afiliación")).click();
				Thread.sleep(3000);
				
				Mdl_Variables.driver.switchTo().frame(0);

				Mdl_Evidenciamiento.Evidencia_Log(true, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
				Thread.sleep(2000);

				Mdl_Variables.driver.findElement(By.id("ClaveSegundoFactor")).clear();
			    Mdl_Variables.driver.findElement(By.id("ClaveSegundoFactor")).sendKeys("123456");
		    	Thread.sleep(10000);
		    	Thread.sleep(10000);
		    	
				//Mdl_Evidenciamiento.Evidencia_Log(true, "");
				//Mdl_Evidenciamiento.Ingreso_Datos_Log();
				
				Mdl_Variables.driver.findElement(By.name("Submit")).click();
			    Thread.sleep(2000);
			    
				Mdl_Evidenciamiento.Evidencia_Log(true, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();

			}
		}catch(Throwable e){System.out.println("Error en \"bnetactual_datos_afiliacion()\" no encontró el objeto: " + e.getMessage());
			Mdl_Evidenciamiento.Evidencia_Log(false, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			Mdl_Variables.boolAction = 0;
            Mdl_Variables.driver.quit();
		}
	}
	
	
	/**
	 * Ingreso Fondos Mutuos
	 * @author Juliet Agreda Lavado 
	 * @version 1.0
	 */
	public static void ingreso_fondos(){
		String findingTag = "";
		boolean flag = false;
		int i = 2, in = 1, n= 30;

		try{
			while(i <= 30 && !flag){
				
				findingTag = "//div[@id='section1']/div/div/table/tbody/tr[" + i + "]/td";

				if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath(findingTag + "/span")).getText())){
					Thread.sleep(10000);
					Mdl_Variables.driver.findElement(By.xpath(findingTag)).click();
					Thread.sleep(10000);

					Mdl_Variables.strPasosEvidencia = "Seleccionar el número de  cuenta " + Mdl_Variables.arrKeyValue[0] + " de Fondos Mutuos.";
					Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);

					Mdl_Evidenciamiento.Evidencia_Log(true, "");
					Mdl_Evidenciamiento.Ingreso_Datos_Log();
					flag = true;
				} i++; in++; n++;
			} Thread.sleep(1000);
		}catch(Throwable e){System.out.println("Error en \"ingreso_fondos()\" al seleccionar: " + e.getMessage());}
	}
	
	/**
	 * Rescate de Fondo
	 * @author Juliet Agreda Lavado
	 * @version 1.0
	 */
	public static void rescate_fondo(){
		try{
			Mdl_Variables.driver.findElement(By.xpath("//div[@id='operaciones-list-container']/div[3]/div/ul/li/a/strong")).click();
			Mdl_Variables.strPasosEvidencia = "Seleccionar Realizar Rescate.";
			Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
			Thread.sleep(1000);
		}catch(Throwable e){System.out.println("Error en \"rescate_fondo()\" al seleccionar: " + e.getMessage());}
	}
	
	/**
     * BNet Actual Alertas de Pago de Sueldo y Salario 
     * @author Juliet Agreda Lavado
     * @version 1.0
     * @date 16/05/2016
     */
	public static void bnetactual_alertas_cuentas() throws SQLException, InterruptedException{
		try{
			if(Web_Framework.GetValue("dt_KWD_BNET_ACTUAL").equals("TRUE")){
				
			
				Web_Framework.launchapp(Mdl_Variables.Tipo_Aplicativo);
				Mdl_Choucair.getDatosLogin();
				Mdl_Variables.driver.get(Mdl_Variables.P_Str_RutaAmbienteSecundario);
				Mdl_Variables.driver.findElement(By.id("initial_input")).clear();
				Mdl_Variables.driver.findElement(By.id("initial_input")).sendKeys(Mdl_Variables.Str_DatosUsuario[3]);
				Mdl_Variables.driver.findElement(By.name("eai_password")).clear();
				Mdl_Variables.driver.findElement(By.name("eai_password")).sendKeys(Mdl_Variables.Str_DatosUsuario[10]);

				Thread.sleep(3000);
				Mdl_Evidenciamiento.Evidencia_Log(true, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
				Thread.sleep(3000);
				
				Mdl_Variables.driver.findElement(By.cssSelector("a.c1 > img")).click();
				Thread.sleep(2000);
				Mdl_Variables.driver.findElement(By.id("sec_mi_perfil")).click();
				
				Mdl_Evidenciamiento.Evidencia_Log(true, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
				Thread.sleep(3500);
				
				Mdl_Variables.driver.findElement(By.linkText("Avisos y Alertas")).click();
				Thread.sleep(3000);
				Mdl_Variables.driver.findElement(By.linkText("Pago de Sueldo y Salario")).click();
				Thread.sleep(3000);
				
				//Mdl_Variables.driver.switchTo().frame(0);
				
				Mdl_Evidenciamiento.Evidencia_Log(true, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
				Thread.sleep(2000);

			}
		}catch(Throwable e){System.out.println("Error en \"bnetactual_alertas_cuentas()\" no encontró el objeto: " + e.getMessage());
			Mdl_Evidenciamiento.Evidencia_Log(false, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			Mdl_Variables.boolAction = 0;
            Mdl_Variables.driver.quit();
		}
	}

	/**
	 * Seleccionar Numero de Tarjeta de Alertas
	 * @author Juliet Agreda Lavado
	 * @version 1.0
	 */
    
    public static void seleccionar_numtarj_alertas(String StrCadena) throws SQLException 
	{
	    int i = 2 ;
	    if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
		}else{
		try {
			Thread.sleep(5000);
			while(i<=2){
			
				if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath("//div[@id='wizard-slide-viewer']/div/div/div/article/div[2]/table/tbody/tr[" + i + "]/td[2]/span")).getText())){
					
						Mdl_Variables.driver.findElement(By.xpath("//div[@id='wizard-slide-viewer']/div/div/div/article/div[2]/table/tbody/tr[" + i + "]/td[2]/span")).click();
						Mdl_Variables.strPasosEvidencia = "Seleccionar Tarjeta en Alertas: "+Mdl_Variables.arrKeyValue[0];
						
						Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
						Mdl_Evidenciamiento.Evidencia_Log(true, "");
						Mdl_Evidenciamiento.Ingreso_Datos_Log();
					} i++;
			}
			Thread.sleep(3000);
			} catch (Exception e) {
				System.out.println("Error en seleccionar_numtarj_alertas Mdl_Bnet: seleccionar opción " + e.getMessage());
			}			
		}
	}
	
    
    public static void click_cuenta_pago(String StrCuenta) throws SQLException 
	{
    	try {
			Thread.sleep(2000);
			for (int f=0;f<Mdl_Variables.driver.findElements(By.className("link")).size();f++)
			{
				if(Mdl_Variables.driver.findElements(By.className("link")).get(f).getText().compareTo(StrCuenta)==0)
				{
					Mdl_Variables.driver.findElements(By.className("link")).get(f).click();
					Thread.sleep(2000);
					Mdl_Variables.strPasosEvidencia = "Click en cuenta: "+StrCuenta;
					Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
					Mdl_Evidenciamiento.Evidencia_Log(true, "");
					Mdl_Evidenciamiento.Ingreso_Datos_Log();
					f=1000;
				}
			}
			Thread.sleep(1000);
			} 
    	catch (Exception e) {
				System.out.println("Error en click_cuenta_pago Mdl_Bnet: click en opción " + e.getMessage());
			}	
	}

    public static void click_radiobutton_byclass(String StrClass,String StrSeleccion)
    {
    	try 
    	{
			Thread.sleep(2000);
			if(StrSeleccion.compareTo("Nombre") == 0)
			{
				Mdl_Variables.driver.findElements(By.className(StrClass)).get(3).click();
			}
			else
			{
				Mdl_Variables.driver.findElements(By.className(StrClass)).get(2).click();
				Thread.sleep(1000);
				Mdl_Variables.driver.findElement(By.xpath("//div[2]/div/button")).click();
				Thread.sleep(1000);
				Mdl_Variables.driver.findElement(By.linkText(Web_Framework.GetValue("dt_TXT_ESCRIBIR_NOMBRE"))).click();
				
			}
			Mdl_Variables.strPasosEvidencia = "Click en radiobutton: "+StrSeleccion;
			Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
			Mdl_Evidenciamiento.Evidencia_Log(true, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
    	} 
    	catch (Exception e) {
				System.out.println("Error en click_radiobutton_byclass Mdl_Bnet: click en opción " + e.getMessage());
			}	
    }
    
    public static void click_radiobutton_byposition(String StrName,String StrSeleccion)
    {
    	try {
			Thread.sleep(2000);
			for (int f=2;f<Mdl_Variables.driver.findElements(By.xpath("//td[2]/strong")).size()+2;f++)
			{
				if(Mdl_Variables.driver.findElement(By.xpath("//tr["+f+"]/td[2]/strong")).getText().toLowerCase().compareTo(StrName.toLowerCase())==0)
				{
					Mdl_Variables.driver.findElement(By.xpath("//tr["+f+"]/td[2]/strong")).click();
					Thread.sleep(2000);
					Mdl_Variables.strPasosEvidencia = "Click en institución: "+StrName;
					Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
					Mdl_Evidenciamiento.Evidencia_Log(true, "");
					Mdl_Evidenciamiento.Ingreso_Datos_Log();
					f=1000;
				}
			}
			Thread.sleep(1000);
			} 
    	catch (Exception e) {
				System.out.println("Error en click_cuenta_pago Mdl_Bnet: click en opción " + e.getMessage());
			}		
    }
    
    public static void click_checkbox_recibo_pendiente(String StrParametro)
    {
    	try {
			Thread.sleep(2000);
			 String[] strMode;
			 int iCantidadRecibos = Mdl_Variables.driver.findElements(By.className("cbRecibo")).size();
			 strMode = StrParametro.split(";");
			 if (Web_Framework.GetValue(strMode[0]) != "")
			 {
				 for (int i = 2; i< iCantidadRecibos+2; i++ )
				 {
					 if(Mdl_Variables.driver.findElement(By.xpath("//tr["+i+"]/td[4]/strong")).getText().compareTo(Web_Framework.GetValue(strMode[0]))==0)
					 {
						Mdl_Variables.driver.findElement(By.xpath("//div/div/article/div/table/tbody/tr["+i+"]/td/label")).click();
						Thread.sleep(2000);
						Mdl_Variables.strPasosEvidencia = "Click en institución: "+Web_Framework.GetValue(strMode[0]);
						Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
						Mdl_Evidenciamiento.Evidencia_Log(true, "");
						Mdl_Evidenciamiento.Ingreso_Datos_Log();
						i=1000;
					 }
				 }
			 }
			 else if (Web_Framework.GetValue(strMode[1]) != "")
			 {
				 for (int i = 2; i< iCantidadRecibos+2; i++ )
				 {
					 
				 }
			 }
			 else
			 {
				 
			 }
		}
		    
    	catch (Exception e) {
				System.out.println("Error en click_cuenta_pago Mdl_Bnet: click en opción " + e.getMessage());
			}		
    }
    
    
    public static void bnetactual_recarga_tarjeta_regalo(String strAV1) throws SQLException{
		try{
			if(Web_Framework.GetValue("dt_KWD_BNET_ACTUAL").equals("TRUE")){
				
				 Web_Framework.launchapp(Mdl_Variables.Tipo_Aplicativo);
				 Mdl_Variables.driver.get(Mdl_Variables.P_Str_RutaAmbienteSecundario);
                 Mdl_Choucair.getDatosLogin();

                 Mdl_Variables.driver.findElement(By.id("initial_input")).clear();
                 Mdl_Variables.driver.findElement(By.id("initial_input")).sendKeys(Mdl_Variables.Str_DatosUsuario[3]);
                 Mdl_Variables.driver.findElement(By.name("eai_password")).clear();
                 Mdl_Variables.driver.findElement(By.name("eai_password")).sendKeys("1111"); //Mdl_Variables.Str_DatosUsuario[10]
                 Thread.sleep(4000);
                 Mdl_Evidenciamiento.Evidencia_Log(true, "");
                 Mdl_Evidenciamiento.Ingreso_Datos_Log();
                 Mdl_Variables.driver.findElement(By.cssSelector("a.c1 > img")).click();
                 Thread.sleep(17000);
                 Mdl_Evidenciamiento.Evidencia_Log(true, "");
                 Mdl_Evidenciamiento.Ingreso_Datos_Log();
                 Mdl_Variables.driver.findElement(By.id("sec_mis_tarjetas")).click();
                 Thread.sleep(2000);
                 Mdl_Evidenciamiento.Evidencia_Log(true, "");
                 Mdl_Evidenciamiento.Ingreso_Datos_Log();
                 Mdl_Variables.driver.findElement(By.xpath("//div[3]/ul/li[6]/a")).click();
                 Thread.sleep(7000);
                 Mdl_Evidenciamiento.Evidencia_Log(true, "");
                 Mdl_Evidenciamiento.Ingreso_Datos_Log();
                 if(Web_Framework.GetValue("dt_BTN_MISCUENTAS").compareTo("Click") == 0)
                 {
                	 Mdl_Variables.driver.switchTo().frame("marcoDinamico").findElement(By.xpath("//label/input")).click();
                 }
                 else{
                	 Mdl_Variables.driver.switchTo().frame("marcoDinamico").findElement(By.tagName("tipoTarjeta")).click();
                 }
                 Mdl_Variables.driver.findElement(By.name("num1")).sendKeys(Web_Framework.GetValue("dt_TXT_NRTARJW"));
                 Thread.sleep(10);
                 Mdl_Variables.driver.findElement(By.name("num2")).sendKeys(Web_Framework.GetValue("dt_TXT_NRTARJX"));
                 Thread.sleep(10);
                 Mdl_Variables.driver.findElement(By.name("num3")).sendKeys(Web_Framework.GetValue("dt_TXT_NRTARJY"));
                 Thread.sleep(10);
                 Mdl_Variables.driver.findElement(By.name("num4")).sendKeys(Web_Framework.GetValue("dt_TXT_NRTARJZ"));
                 Thread.sleep(2000);
                 
                 Mdl_Evidenciamiento.Evidencia_Log(true, "");
                 Mdl_Evidenciamiento.Ingreso_Datos_Log();
                 if(Web_Framework.GetValue("dt_BTN_SOLES").toLowerCase().compareTo("click") == 0){
                	 Mdl_Variables.driver.findElement(By.name("Importe")).sendKeys(Web_Framework.GetValue("dt_TXT_IMPSOLES"));
                	 Thread.sleep(200);
                	 new Select(Mdl_Variables.driver.findElement(By.name("Divisa"))).selectByVisibleText("Soles");
                 }
                 if(Web_Framework.GetValue("dt_BTN_DOLARES").toLowerCase().compareTo("click") == 0){
                	 Mdl_Variables.driver.findElement(By.name("Importe")).sendKeys(Web_Framework.GetValue("dt_TXT_IMPDOLARES"));
                	 Thread.sleep(200);
                	 new Select(Mdl_Variables.driver.findElement(By.name("Divisa"))).selectByVisibleText("Dólares");
                	 
                 }     
                 Mdl_Evidenciamiento.Evidencia_Log(true, "");
                 Mdl_Evidenciamiento.Ingreso_Datos_Log();
                 Thread.sleep(1000);
                 Mdl_Variables.driver.findElement(By.name("Submit")).click();
                 Mdl_Evidenciamiento.Evidencia_Log(true, "");
                 Mdl_Evidenciamiento.Ingreso_Datos_Log();
                 Thread.sleep(1000);
                // Mdl_Variables.driver.findElement(By.id("ClaveSegundoFactor")).sendKeys("11111111");
                 Mdl_Evidenciamiento.Evidencia_Log(true, "");
                 Mdl_Evidenciamiento.Ingreso_Datos_Log();
                 Thread.sleep(1000);
                 Mdl_Variables.driver.findElement(By.name("Submit")).click();
                 Mdl_Evidenciamiento.Evidencia_Log(true, "");
                 Mdl_Evidenciamiento.Ingreso_Datos_Log();
                 Thread.sleep(1000);
			}
		}
		catch(Throwable e)
		{
			System.out.println("Error en \"bnetactual()\" no encontró el objeto: " + e.getMessage());
			Mdl_Evidenciamiento.Evidencia_Log(false, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			Mdl_Variables.boolAction = 0;
            Mdl_Variables.driver.quit();
		}
	}
    


	public static void bnetactual_trasladar_consumos(String strAV1) throws SQLException{
		try{
			if(Web_Framework.GetValue("dt_KWD_BNET_ACTUAL").equals("TRUE")){
				
				 Web_Framework.launchapp(Mdl_Variables.Tipo_Aplicativo);
				 Mdl_Variables.driver.get(Mdl_Variables.P_Str_RutaAmbienteSecundario);
                 Mdl_Choucair.getDatosLogin();
                 Mdl_Variables.driver.findElement(By.id("initial_input")).clear();
                 Mdl_Variables.driver.findElement(By.id("initial_input")).sendKeys(Mdl_Variables.Str_DatosUsuario[3]);
                 Mdl_Variables.driver.findElement(By.name("eai_password")).clear();
                 Mdl_Variables.driver.findElement(By.name("eai_password")).sendKeys("gestion99"); //Mdl_Variables.Str_DatosUsuario[10]
                 Thread.sleep(2000);
                
                 Mdl_Variables.driver.findElement(By.cssSelector("a.c1 > img")).click();
                 Thread.sleep(17000);
                
                
                 Mdl_Variables.driver.findElement(By.xpath("//li[3]/a/span")).click();
                 Thread.sleep(5000);
                 String sWind=Mdl_Variables.driver.getWindowHandle();
                 Robot robot = new Robot();
                 Thread.sleep(3000);
                 Mdl_Variables.driver.switchTo().frame("marcoDinamico").findElement(By.linkText(Web_Framework.GetValue("dt_LNK_NUM_TARJETA"))).click();
                 Thread.sleep(3000);
                 Mdl_Variables.driver.switchTo().window(sWind).findElement(By.linkText("Operaciones en Cuotas")).click();
                 Thread.sleep(5000);
             //    Mdl_Variables.driver.findElement(By.linkText("Operaciones en Cuotas")).click();
                 Thread.sleep(1000);
                 Mdl_Variables.driver.findElement(By.linkText("Traslada tus Consumos")).click();
               
                 Thread.sleep(4000);
                 Mdl_Variables.driver.switchTo().frame("marcoDinamico");
    
                 for(int i =0;i<new Select(Mdl_Variables.driver.findElement(By.id("AsuntoPropio"))).getOptions().size();i++)
                 {
                	 if(Web_Framework.GetValue("dt_LNK_NUM_TARJETA").contains(new Select(Mdl_Variables.driver.findElement(By.id("AsuntoPropio"))).getOptions().get(i).getText())){
                		 new Select(Mdl_Variables.driver.findElement(By.id("AsuntoPropio"))).selectByIndex(i);
                	 }
                	 i=10000;
                 }
                 
                 Thread.sleep(1000);
                // Mdl_Variables.driver.switchTo().frame("marcoDinamico").findElement(By.xpath("//input[@name='Submit']")).click();
                 Mdl_Variables.driver.findElement(By.xpath("//input[@name='Submit']")).click();
                 Thread.sleep(2000);

                 for(int i =3;i<Mdl_Variables.driver.findElements(By.xpath("//td/input")).size()+3;i++)
                 {
                	 if(Web_Framework.GetValue("dt_TD_VALOR_COMPRA").contains(Mdl_Variables.driver.findElement(By.xpath("//tr["+i+"]/td[4]")).getText())){
                		 Mdl_Variables.driver.findElement(By.xpath("//tr["+i+"]/td/input")).click();
                	 }
                	 i=10000;
                 }
                 Thread.sleep(500);
                 Mdl_Variables.driver.findElement(By.name("okCta")).click();
                 Thread.sleep(2000);

                 Mdl_Variables.driver.findElement(By.id("button")).click();
                 Thread.sleep(2000);

                 Mdl_Variables.driver.findElement(By.id("nroCuotas")).clear();
                 Thread.sleep(200);
                 Mdl_Variables.driver.findElement(By.id("nroCuotas")).sendKeys("3");
                 Thread.sleep(2000);

                 Mdl_Variables.driver.findElement(By.name("consultar")).click();
                 Thread.sleep(2000);
                 Mdl_Variables.driver.switchTo().window(sWind).findElement(By.id("mainPrincipal")).click();
                 int tot = 1;
 				 Robot robot2 = new Robot();

 				for(int i = 0; i < tot; i++){
 					robot2.keyPress(KeyEvent.VK_UP);
 					robot2.keyRelease(KeyEvent.VK_UP);
 				} Thread.sleep(1000);
                 //evidencia
                 Mdl_Evidenciamiento.Evidencia_Log(true, "");
                 Mdl_Evidenciamiento.Ingreso_Datos_Log();
                 Thread.sleep(500);
                 Mdl_Variables.driver.switchTo().frame("marcoDinamico");
                 Mdl_Variables.driver.findElement(By.cssSelector("input.bt.bt_g")).click();
                 Thread.sleep(2000);
                 Mdl_Variables.driver.switchTo().window(sWind).findElement(By.id("mainPrincipal")).click();
                 robot.keyPress(KeyEvent.VK_UP);
				 robot.keyRelease(KeyEvent.VK_UP);
				 Thread.sleep(100);
                 Mdl_Evidenciamiento.Evidencia_Log(true, "");
                 Mdl_Evidenciamiento.Ingreso_Datos_Log();
                 Thread.sleep(500);
                 //evidencia
                 //strSQLaux = "UPDATE " + Mdl_Variables.P_Str_Tabla + "_D SET WHERE ID_CASO = " + Mdl_Variables.Rst_Tabla.getInt("ID_CASO");
                 //Mdl_Variables.Cnn.prepareCall(strSQLaux).execute();
 			    
 			    //Recargamos el DDriven
                 Mdl_Variables.Rst_Tabla.refreshRow();
                 Mdl_Variables.boolAction = 0;
                 Mdl_Variables.driver.quit();
			}
		}
		catch(Throwable e)
		{
			System.out.println("Error en \"bnetactual()\" no encontró el objeto: " + e.getMessage());
			Mdl_Evidenciamiento.Evidencia_Log(false, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			Mdl_Variables.boolAction = 0;
            Mdl_Variables.driver.quit();
		}
	}
    
    public static void click_compra(String StrName,String StrSeleccion)
    {
    	try {
			Thread.sleep(2000);
			for (int f=2;f<Mdl_Variables.driver.findElements(By.xpath("//td[3]/span")).size()+2;f++)
			{
				if(Mdl_Variables.driver.findElement(By.xpath("//tr["+f+"]/td[4]/strong")).getText().compareTo(StrName)==0)
				{
					Mdl_Variables.driver.findElement(By.xpath("//tr["+f+"]/td[3]/span")).click();
					Thread.sleep(2000);
					Mdl_Variables.strPasosEvidencia = "Click en compra: "+StrName;
					Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
					Mdl_Evidenciamiento.Evidencia_Log(true, "");
					Mdl_Evidenciamiento.Ingreso_Datos_Log();
					f=1000;
				}
			}
			Thread.sleep(1000);
			} 
    	catch (Exception e) {
				System.out.println("Error en click_compra Mdl_Bnet: click en opción " + e.getMessage());
			}		
    }
    
    

 /*   public static void bnetactual_pago_tarjetas_terceros(String strAV1) throws SQLException{
        String[] arrAV1 = strAV1.split("\\|");

        String strTxtNumTarjeta = Web_Framework.GetValue(arrAV1[0]); //dt_TXT_NUMTARJETA
        String strTarjTercW = Web_Framework.GetValue(arrAV1[1]); //dt_TXT_NRTARJW
        String strTarjTercX = Web_Framework.GetValue(arrAV1[2]); //dt_TXT_NRTARJX
        String strTarjTercY = Web_Framework.GetValue(arrAV1[3]); //dt_TXT_NRTARJY
        String strTarjTercZ = Web_Framework.GetValue(arrAV1[4]); //dt_TXT_NRTARJZ
        String strTxtImporteSoles = Web_Framework.GetValue(arrAV1[5]); //dt_TXT_IMPSOLES
        String strTxtImporteDolares = Web_Framework.GetValue(arrAV1[6]); //dt_TXT_IMPDOLARES
        //String strBtnSgte1 = Web_Framework.GetValue(arrAV1[7]); //dt_BTN_SGTE1
        String strBtnSgte2 = Web_Framework.GetValue(arrAV1[8]); //dt_BTN_SGTE2

        try{
                if(Web_Framework.GetValue("dt_KWD_BNET_ACTUAL").equals("TRUE")){
                        Web_Framework.launchapp(Mdl_Variables.Tipo_Aplicativo);
                        Mdl_Choucair.getDatosLogin();

                        Mdl_Variables.driver.get(Mdl_Variables.P_Str_RutaAmbienteSecundario);
                        Mdl_Variables.driver.findElement(By.id("initial_input")).clear();
                        Mdl_Variables.driver.findElement(By.id("initial_input")).sendKeys(Mdl_Variables.Str_DatosUsuario[3]);
                        Mdl_Variables.driver.findElement(By.name("eai_password")).clear();
                        Mdl_Variables.driver.findElement(By.name("eai_password")).sendKeys("gestion99"); //Mdl_Variables.Str_DatosUsuario[10]
                        Thread.sleep(2000);
                        Mdl_Evidenciamiento.Evidencia_Log(true, "");
                        Mdl_Evidenciamiento.Ingreso_Datos_Log();
                        Mdl_Variables.driver.findElement(By.cssSelector("a.c1 > img")).click();
                        Thread.sleep(5000);
                        Mdl_Evidenciamiento.Evidencia_Log(true, "");
                        Mdl_Evidenciamiento.Ingreso_Datos_Log();
                        Mdl_Variables.driver.findElement(By.id("sec_mis_tarjetas")).click();
                        Thread.sleep(2000);
                        Mdl_Variables.driver.findElement(By.linkText("Paga Tarj. Bco Continental")).click();
                        Thread.sleep(3000);
                        Mdl_Variables.driver.switchTo().frame(0).findElement(By.id("tipoTarjeta_2")).click();
                        Thread.sleep(1000);
                        Mdl_Variables.driver.findElement(By.name("num1")).clear();
                        Mdl_Variables.driver.findElement(By.name("num2")).clear();
                        Mdl_Variables.driver.findElement(By.name("num3")).clear();
                        Mdl_Variables.driver.findElement(By.name("num4")).clear();
                        Mdl_Variables.driver.findElement(By.name("num1")).sendKeys(strTarjTercW);
                        Mdl_Variables.driver.findElement(By.name("num2")).sendKeys(strTarjTercX);
                        Mdl_Variables.driver.findElement(By.name("num3")).sendKeys(strTarjTercY);
                        Mdl_Variables.driver.findElement(By.name("num4")).sendKeys(strTarjTercZ);
                        Thread.sleep(2000);
                        Mdl_Evidenciamiento.Evidencia_Log(true, "");
                        Mdl_Evidenciamiento.Ingreso_Datos_Log();
                        Mdl_Variables.driver.findElement(By.name("Submit")).click();
                        Thread.sleep(2000);
                     //   selectByPartOfVisibleText("id", "AsuntoPropio", strTxtNumTarjeta, false, true);

                        if(!strTxtImporteDolares.equalsIgnoreCase("<IGNORE>")){
                                Mdl_Variables.driver.findElement(By.id("tipoMoneda_2")).click();
                                Thread.sleep(1000);
                                Mdl_Variables.driver.findElement(By.name("ImporteDolares")).sendKeys(strTxtImporteDolares);
                        }else{Mdl_Variables.driver.findElement(By.name("ImporteSoles")).sendKeys(strTxtImporteSoles);}

                        Thread.sleep(1000);
                        Mdl_Evidenciamiento.Evidencia_Log(true, "");
                        Mdl_Evidenciamiento.Ingreso_Datos_Log();

                        if(!strBtnSgte2.equalsIgnoreCase("<IGNORE>")){
                                Mdl_Variables.driver.findElement(By.name("Submit")).click();
                                Thread.sleep(2000);
                                Mdl_Variables.driver.findElement(By.id("ClaveSegundoFactor")).clear();
                                Mdl_Variables.driver.findElement(By.id("ClaveSegundoFactor")).sendKeys("123456");
                                Thread.sleep(1000);
                                Mdl_Evidenciamiento.Evidencia_Log(true, "");
                                Mdl_Evidenciamiento.Ingreso_Datos_Log();
                                Mdl_Variables.driver.findElement(By.name("Submit")).click();
                                Thread.sleep(3000);
                                Mdl_Evidenciamiento.Evidencia_Log(true, "");
                                Mdl_Evidenciamiento.Ingreso_Datos_Log();
                        }

                        //Recargamos el DDriven
            Mdl_Variables.Rst_Tabla.refreshRow();
            Mdl_Variables.driver.quit();
                }
        }catch(Throwable e){System.out.println("Error en \"bnetactual_pago_tarjetas_terceros()\" no encontró el objeto: " + e.getMessage());
                Mdl_Evidenciamiento.Evidencia_Log(false, "");
                Mdl_Evidenciamiento.Ingreso_Datos_Log();
                Mdl_Variables.boolAction = 0;
    Mdl_Variables.driver.quit();
        }
}

    */
  
    
    public static void bnetactual_tin_tx_cta_otros_bancos(String strAV1) throws SQLException{
		try{
			if(Web_Framework.GetValue("dt_KWD_BNET_ACTUAL").equals("TRUE")){
				 Web_Framework.launchapp(Mdl_Variables.Tipo_Aplicativo);
				 Mdl_Variables.driver.get(Mdl_Variables.P_Str_RutaAmbienteSecundario);
                 Mdl_Choucair.getDatosLogin();

                 Mdl_Variables.driver.findElement(By.id("initial_input")).clear();
                 Mdl_Variables.driver.findElement(By.id("initial_input")).sendKeys(Mdl_Variables.Str_DatosUsuario[3]);
                 Mdl_Variables.driver.findElement(By.name("eai_password")).clear();
                 Mdl_Variables.driver.findElement(By.name("eai_password")).sendKeys("1111"); //Mdl_Variables.Str_DatosUsuario[10]
                 Thread.sleep(4000);
                 Mdl_Evidenciamiento.Evidencia_Log(true, "");
                 Mdl_Evidenciamiento.Ingreso_Datos_Log();
                 Mdl_Variables.driver.findElement(By.cssSelector("a.c1 > img")).click();
                 Thread.sleep(17000);
                 Mdl_Evidenciamiento.Evidencia_Log(true, "");
                 Mdl_Evidenciamiento.Ingreso_Datos_Log();
                 Mdl_Variables.driver.findElement(By.id("sec_mis_cuentas")).click();
                 Thread.sleep(2000);
                 Mdl_Evidenciamiento.Evidencia_Log(true, "");
                 Mdl_Evidenciamiento.Ingreso_Datos_Log();
                 Mdl_Variables.driver.switchTo().frame("marcoDinamico").findElement(By.linkText(Web_Framework.GetValue("dt_TXT_NUMTARJETA"))).click();
                 Thread.sleep(2000);
                 
                 Mdl_Variables.driver.findElement(By.linkText("Operaciones")).click();
                 Mdl_Variables.driver.findElement(By.linkText("Realizar transferencias a otros bancos")).click();
                 Mdl_Variables.driver.findElement(By.name("w3importe")).clear();
                 Mdl_Variables.driver.findElement(By.name("w3importe")).sendKeys("500");
                 Mdl_Variables.driver.findElement(By.id("w1banco")).clear();
                 Mdl_Variables.driver.findElement(By.id("w1banco")).sendKeys("dt_TXT_CUENTAX");
                 Mdl_Variables.driver.findElement(By.id("w1oficina")).clear();
                 Mdl_Variables.driver.findElement(By.id("w1oficina")).sendKeys("dt_TXT_CUENTAY");
                 Mdl_Variables.driver.findElement(By.id("w1cuenta")).clear();
                 Mdl_Variables. driver.findElement(By.id("w1cuenta")).sendKeys("dt_TXT_CUENTAZ");
                 Mdl_Variables. driver.findElement(By.id("w1control")).clear();
                 Mdl_Variables.driver.findElement(By.id("w1control")).sendKeys("dt_TXT_CUENTAZA");
                 Mdl_Evidenciamiento.Evidencia_Log(true, "");
                 Mdl_Evidenciamiento.Ingreso_Datos_Log();
                 Thread.sleep(1000);
                 
                 Mdl_Variables.driver.findElement(By.id("btnContinuar")).click();
                 
                 
              
			}
		}
		catch(Throwable e)
		{
			System.out.println("Error en \"bnetactual()\" no encontró el objeto: " + e.getMessage());
			Mdl_Evidenciamiento.Evidencia_Log(false, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			Mdl_Variables.boolAction = 0;
            Mdl_Variables.driver.quit();
		}
	}
    
    
}
