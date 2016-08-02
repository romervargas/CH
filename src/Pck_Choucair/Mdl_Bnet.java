package Pck_Choucair;

import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import java.sql.SQLException;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import bsh.This;
import Pck_Choucair.Web_Framework.*;

import java.util.GregorianCalendar; //trabajar calendario y fechas
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

public class Mdl_Bnet {
	
	public static String xpathTabla;
	public static String  winHandleBefore;
	public enum Funciones_bnet
	{
		menu,  login, calendario,cerraremer, refresh,htmltd2,frame, novalue ,ventana,lista_valor ,ingreso,seleccionaropcion,clave,realizartrx,
		realizartrxexterior,realizargiro,efectivomovil,seleccionarmoneda,seleccionargastos,seleccionardocumento,seleccionarlocalidad,realizardonaciones,
		seleccionarcampania,seleccionardetalle,ingresocuentas,condiciones,ultimo_mov,desconectar,ultimo_movdonaciones,verificar_historico,tipo_telefono,
		editar,editar1,editar2,editar3,editar4,tipo_operador,eliminar2,eliminar3,eliminar4,boton_email,boton_email1,boton_email2,boton_email3,boton_email4,
		tipo_via,editar_direccion,tipo_zona,departamento,provincia,distrito,anadir_telefono,eliminar_email1,eliminar_email2,eliminar_email3,eliminar_email4,
		seleccionar_telefono, seleccionar_email, seleccionar_telefono_card, seleccionar_email_card,
		seleccionar_dia_semana, bajar_scroll, bajar_scroll_telefono, bajar_scroll_email, bajar_pagina_flecha, launchApp_BNetActual, abrir_bnet_actual,
		login_BNetActual, ingreso_tarjetas, detalle_tarjeta, tercerostrx , claveTemporizador,
		bnetactual_propias,bnetactual_terceros,bnetactual_efectivomovil,bnetactual_donaciones,bnetactual_girosnacionales,
		ultimo_mov_exterior, bnetactual_exterior, bnetactual_datos_personales, busqueda_tarjeta_credito, seleccion_tarjeta_credito_fancy, bnetactual_tarjetas_consultas_compras,
		seleccionar_cuotas_pagadas, bnetactual_tarjetas_simular_cuotas, simular_cuotas_fancy, seleccionar_tipo_simulacion, bnetactual_envio_efectivo_movil, subir_pagina_flecha,
		seleccionar_periodo, evidencia_pantalla_completa, seleccionar_tarjeta_telefonica, seleccionar_importe_tarjeta_telefonica, pagotarjeta, seleccionar_importe,
		bnetactual_pago_tarjetas_propias, bnetactual_pago_tarjetas_terceros, bnetactual_compra_tarjeta_prepago, seleccionar_tarjeta, seleccionar_cuenta_abono,
		bnetactual_tarjetas_efectivo_revolvente, bnetactual_tarjetas_efectivo_cuotas, ultimo_mov_disp_efect, salir_esc_form;

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
			realizarTRXExterior();
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
			seleccionarMoneda();
			break;
		case seleccionargastos:
			seleccionarGastos();
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
			seleccionador_telefono_email_card(1);
			break;
		case seleccionar_email_card:
			seleccionador_telefono_email_card(2);
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
		case subir_pagina_flecha:
			subir_pagina_flecha();
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
			//Solo ingresa si el parametro adicional no tiene un ignore dentro de la keyword
			if(!Web_Framework.GetValue(str_parametroAdicional).toLowerCase().equals("<ignore>")){bnetactual_donaciones(str_parametro1, str_parametro2);}
			break;
		case bnetactual_girosnacionales:
			bnetactual_girosnacionales(str_parametro1);
			break;
		case ultimo_mov_exterior:
			ultimo_mov_exterior(str_parametro1);
			break;
		case bnetactual_exterior:
			bnetactual_exterior(str_parametro1, str_parametro2);
			break;
		case bnetactual_datos_personales:
			bnetactual_datos_personales(str_parametro1, str_parametro2);
			break;
		case busqueda_tarjeta_credito:
			busqueda_tarjeta_credito();
			break;
		case seleccion_tarjeta_credito_fancy:
			seleccion_tarjeta_credito_fancy();
			break;
		case bnetactual_tarjetas_consultas_compras:
			bnetactual_tarjetas_consultas_compras(str_parametro1);
			break;
		case seleccionar_cuotas_pagadas:
			seleccionar_cuotas_pagadas(str_parametro1);
			break;
		case bnetactual_tarjetas_simular_cuotas:
			bnetactual_tarjetas_simular_cuotas(str_parametro1);
			break;
		case simular_cuotas_fancy:
			simular_cuotas_fancy();
			break;
		case seleccionar_tipo_simulacion:
			seleccionar_tipo_simulacion();
			break;
		case bnetactual_envio_efectivo_movil:
			bnetactual_envio_efectivo_movil();
			break;
		case seleccionar_periodo:
			seleccionar_periodo(str_parametro1);
			break;
		case evidencia_pantalla_completa:
			helperEvidencia_PantallaCompleta(Integer.valueOf(Web_Framework.GetValue(str_parametro1)));
			break;
		case seleccionar_tarjeta_telefonica:
			seleccionar_tarjeta_telefonica(str_parametro1);
			break;
		case seleccionar_importe_tarjeta_telefonica:
			seleccionar_importe_tarjeta_telefonica();
			break;
		case pagotarjeta:
			pagotarjeta();
			break;
		case seleccionar_importe:
			seleccionar_importe(str_parametro1);
			break;
		case bnetactual_pago_tarjetas_propias:
			bnetactual_pago_tarjetas_propias(str_parametro1);
			break;
		case bnetactual_pago_tarjetas_terceros:
			bnetactual_pago_tarjetas_terceros(str_parametro1);
			break;
		case bnetactual_compra_tarjeta_prepago:
			bnetactual_compra_tarjeta_prepago(str_parametro1);
			break;
		case seleccionar_tarjeta:
			seleccionar_tarjeta();
			break;
		case seleccionar_cuenta_abono:
			seleccionar_cuenta_abono();
			break;
		case bnetactual_tarjetas_efectivo_revolvente:
			bnetactual_tarjetas_efectivo_revolvente(str_parametro1);
			break;
		case bnetactual_tarjetas_efectivo_cuotas:
			bnetactual_tarjetas_efectivo_cuotas(str_parametro1);
			break;
		case ultimo_mov_disp_efect:
			ultimo_mov_disp_efect(str_parametro1);
			break;
		case salir_esc_form:
			salir_esc_form(str_parametro1);
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
	
	
	/**
	 * 
	 * @author Romer Vargas Otiniano
	 * @version 1.0
	 * @date 10/05/2016
	 */
	/*
	public static void login() throws SQLException {
		//switch(aplicativos.getValue(Mdl_Variables.P_Str_Aplicativo.toLowerCase())){
		//case imagine:
		try{
			Mdl_Choucair.getDatosLogin();
			Thread.sleep(4000);
			Mdl_Keyboard.doType(KeyEvent.VK_ENTER);
			Thread.sleep(5000);
			
			Mdl_Variables.driver.findElement(By.xpath("//button[@type='button']")).click();

			if (Mdl_Variables.Str_DatosUsuario[6].equalsIgnoreCase("DNI")){
				Mdl_Variables.driver.findElement(By.xpath("//li[1]/a/span")).click();					
			}else{
				if(Mdl_Variables.Str_DatosUsuario[6].equalsIgnoreCase("PAS")){Mdl_Variables.driver.findElement(By.xpath("//li[2]/a/span")).click();}
				if(Mdl_Variables.Str_DatosUsuario[6].equalsIgnoreCase("EXT")){Mdl_Variables.driver.findElement(By.xpath("//li[3]/a/span")).click();}
				if(Mdl_Variables.Str_DatosUsuario[6].equalsIgnoreCase("CIM")){Mdl_Variables.driver.findElement(By.xpath("//li[4]/a/span")).click();}
			}

			Mdl_Variables.driver.findElement(By.id("documento")).clear();
			//Mdl_Variables.Str_DatosUsuario[0]
			//Mdl_Variables.Str_DatosUsuario[9]
			//Mdl_Variables.driver.findElement(By.id("documento")).sendKeys(Web_Framework.GetValue(Cadena[0]));
			Mdl_Variables.driver.findElement(By.id("documento")).sendKeys(Mdl_Variables.Str_DatosUsuario[0]);

			Mdl_Variables.strPasosEvidencia = "Iniciar sesión con el usuario " + Mdl_Variables.Str_DatosUsuario[0];
			Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);

			Mdl_Variables.driver.findElement(By.id("claveAcceso")).clear();
			//Mdl_Variables.driver.findElement(By.id("claveAcceso")).sendKeys(Web_Framework.GetValue(Cadena[1]));
			Mdl_Variables.driver.findElement(By.id("claveAcceso")).sendKeys(Mdl_Variables.Str_DatosUsuario[9]);
			Mdl_Variables.strPasosEvidencia = "Ingresar contraseña " + Mdl_Variables.Str_DatosUsuario[9];
			Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
			Thread.sleep(1000);

			switch(Keyword.getValue(Mdl_Variables.Tipo_Aplicativo.toLowerCase())){
				case firefox:
					Thread.sleep(20000); //necesario, caso contrario; no funciona OK en Firefox
					break;
				default: break;
			}

			Thread.sleep(1000);
			Mdl_Evidenciamiento.Evidencia_Log(true, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			Mdl_Variables.driver.findElement(By.xpath("//button[@type='submit']")).click();
			Mdl_Variables.strPasosEvidencia = "Hacer click en el botón Entrar";
			Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);				

			//Mdl_Variables.driver.findElement(By.xpath("//article[@id='print-section']/div/table/tbody/tr[2]/td")).click();
			//Thread.sleep(3000);
			//desc_paso = "Se realizó el Login de manera exitosa para CLIENTE:USUARIO:CLAVE / "+Web_Framework.GetValue(Cadena[0])+":"+Web_Framework.GetValue(Cadena[1])+":"+Web_Framework.GetValue(Cadena[2])+":";
		}catch(Throwable e){System.out.println("Error en el logueo: " + e.getMessage());
			Mdl_Evidenciamiento.Evidencia_Log(false, "");
			//String desc_paso = "No se pudo realizar el logueo";
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			Mdl_Variables.boolAction = 0;
		}
	}*/

	/**
	 * 
	 * @author Romer Vargas Otiniano
	 * @version 1.0
	 * @date 22/07/2016
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
			Thread.sleep(30000);
			Mdl_Variables.driver.switchTo().window("_bancaporinternetbbva");
		}catch(Throwable e){System.out.println("Error en el logueo: " + e.getMessage());
			Mdl_Evidenciamiento.Evidencia_Log(false, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			Mdl_Variables.boolAction = 0;
		}
	}

	/*public static void RealizarTRX(String StrCadena) throws SQLException 
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
			
					
	}*/

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

	/**
	 * 
	 * @author Romer Vargas Otiniano
	 * @version 1.0
	 * @date 11/05/2016
	 */
	public static void seleccionarDocumento(){
		try{
			String findingTag = "";
			boolean flag = false;
			int i = 1;

			if(!Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
				Mdl_Variables.driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
				Thread.sleep(1000);

				while(i <= 4 && !flag){findingTag = "//div[5]/div/ul/li[" + i + "]/a";
					if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath(findingTag)).getText())){
						Mdl_Variables.driver.findElement(By.xpath(findingTag)).click();

						Mdl_Variables.strPasosEvidencia = "Seleccionar la opción " + Mdl_Variables.arrKeyValue[0];
						Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
						flag = true;
					} i++;
				}
			} Thread.sleep(1000);
		}catch(Throwable e){System.out.println("Error en \"seleccionarDocumento()\" al seleccionar: " + e.getMessage());}
	}

	/**
	 * 
	 * @author Romer Vargas Otiniano
	 * @version 1.0
	 * @date 11/05/2016
	 */
	public static void seleccionarLocalidad(){
		try{
			String findingTag = "";
			boolean flag = false;
			int i = 1;
			Mdl_Variables.driver.findElement(By.xpath("(//button[@type='button'])[5]")).click();
			Thread.sleep(1000);

			while(i <= 43 && !flag){findingTag = "//div[6]/div/ul/li[" + i + "]/a/span";
				if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath(findingTag)).getText())){
					Mdl_Variables.driver.findElement(By.xpath(findingTag)).click();

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
			Thread.sleep(2000);

			for(int i = 1; i <= 7; i++){findingTag = "//div[5]/div/ul/li[" + i + "]/a/span";
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
	 * @date 11/05/2016
	 */
	public static void ingreso() throws SQLException{
		String findingTag = "";
		boolean flag = false;
		int i = 2;

		try{
			while(!flag){findingTag = "//article[@id='print-section']/div/table/tbody/tr[" + i + "]/td";
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
		}catch(Throwable e){System.out.println("Error en \"ingreso()\" al seleccionar: " + e.getMessage());
		}finally{
			Mdl_Variables.strPasosEvidencia = "No se pudo seleccionar el número de cuenta " + Mdl_Variables.arrKeyValue[0];
			Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
			Mdl_Evidenciamiento.Evidencia_Log(true, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
		}
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
			String[] auxStrcampos = strCampos.split("\\|");

			if(!Web_Framework.GetValue(auxStrcampos[0]).equalsIgnoreCase("<IGNORE>")){
				aux = Web_Framework.GetValue(auxStrcampos[0]).trim();
			}else{flag = true;}

			if(!flag){
				String[] arrValores = aux.split("\\:");
				String findingTag = "";

				for(int i = 0; i < arrValores.length; i++){
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
								if(!Web_Framework.GetValue(auxStrcampos[1]).equalsIgnoreCase("<IGNORE>")){
									Mdl_Variables.driver.findElement(By.id("btnReenviarEfectivoMovil")).click();
									Thread.sleep(3000);
									Mdl_Evidenciamiento.Evidencia_Log(true, "");
									Mdl_Evidenciamiento.Ingreso_Datos_Log();
								} Mdl_Variables.driver.findElement(By.xpath("//div[@id='detalleGeneralHistoricoEfectivoMovil']/header/div/div/span/a/span[2]")).click();	
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
	    int i=0;
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
		System.out.println("Error en Mdl_Bnet: seleccionar opción " + e.getMessage());
					}
		}			
	}
	
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
		System.out.println("Error en Mdl_Bnet: seleccionar opción " + e.getMessage());
					}
		}		
	}
	
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
		System.out.println("Error en Mdl_Bnet: seleccionar opción " + e.getMessage());
					}
		}			
	}
	
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
		System.out.println("Error en Mdl_Bnet: seleccionar opción " + e.getMessage());
					}
		}	
	}
	
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
		System.out.println("Error en Mdl_Bnet: seleccionar opción " + e.getMessage());
					}
		}			
	}
	
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
		System.out.println("Error en Mdl_Bnet: seleccionar opción " + e.getMessage());
					}
		}	
	}
	
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
		System.out.println("Error en Mdl_Bnet: seleccionar opción " + e.getMessage());
					}
		}		
	}
	

	
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
		System.out.println("Error en Mdl_Bnet: seleccionar opción " + e.getMessage());
					}
					
	}
	}
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
		System.out.println("Error en Mdl_Bnet: seleccionar opción " + e.getMessage());
					}
					
	}
	}
	
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
		System.out.println("Error en Mdl_Bnet: seleccionar opción " + e.getMessage());
					}
					
	}
	}
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
		System.out.println("Error en Mdl_Bnet: seleccionar opción " + e.getMessage());
					}
					
	}
	}
	
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
					
				
	
		
			} catch (Exception e) {
		System.out.println("Error en Mdl_Bnet: seleccionar opción " + e.getMessage());
					}
					
	}
	}
	
	
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
		System.out.println("Error en Mdl_Bnet: seleccionar opción " + e.getMessage());
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
	 * @date 13/05/2016
	 */
	public static void tipo_via(){
        if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
        	//
        }else{
            try{
            	boolean flag = false;
        		int i = 1;
            	Mdl_Variables.driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
            	String tagElement = new String();
            	Thread.sleep(2000);

            	Mdl_Evidenciamiento.Evidencia_Log(true, "");
                Mdl_Evidenciamiento.Ingreso_Datos_Log();
                Thread.sleep(1000);

                while(i <= 17 && !flag){tagElement = "//div[5]/div/ul/li[" + i + "]/a";
                    if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath(tagElement)).getText())){
                        Mdl_Variables.driver.findElement(By.xpath(tagElement)).click();

                        Mdl_Variables.strPasosEvidencia = "Seleccionar la opción " + Mdl_Variables.arrKeyValue[0];
                        Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
                        flag = true;
                    } i++;
                } Thread.sleep(2000);
            }catch(Throwable e){System.out.println("Error en \"tipo_via()\" al seleccionar: " + e.getMessage());}
        }
    }

	/**
	 * 
	 * @author Romer Vargas Otiniano
	 * @version 1.0
	 * @date 13/05/2016
	 */
	public static void tipo_zona(){
		if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
			//
        }else{
        	try{
        		boolean flag = false;
        		int i = 1;
                Mdl_Variables.driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
                String tagElement = new String();
                Thread.sleep(2000);
                //Mdl_Variables.driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();

                Mdl_Evidenciamiento.Evidencia_Log(true, "");
                Mdl_Evidenciamiento.Ingreso_Datos_Log();
                Thread.sleep(1000);

                while(i <= 12 && !flag){tagElement = "//div[6]/div/ul/li[" + i + "]/a/span";
            		if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath(tagElement)).getText())){
                        Mdl_Variables.driver.findElement(By.xpath(tagElement)).click();

                        Mdl_Variables.strPasosEvidencia = "Seleccionar la opción " + Mdl_Variables.arrKeyValue[0];
                        Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
                        flag = true;
                    } i++;
                } Thread.sleep(2000);
            }catch(Throwable e){System.out.println("Error en \"tipo_zona()\" al seleccionar: " + e.getMessage());}
        }
    }

	/**
	 * 
	 * @author Romer Vargas Otiniano
	 * @version 1.0
	 * @date 13/05/2016
	 */
	public static void departamento(){
		if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
			//
        }else{
        	try{
        		boolean flag = false;
        		int i = 1;

                Mdl_Variables.driver.findElement(By.xpath("(//button[@type='button'])[5]")).click();
                String tagElement = new String();
                Thread.sleep(2000);

                Mdl_Evidenciamiento.Evidencia_Log(true, "");
                Mdl_Evidenciamiento.Ingreso_Datos_Log();
                Thread.sleep(1000);

                while(i <= 26 && !flag){tagElement = "//div[7]/div/ul/li[" + i + "]/a";
            		if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath(tagElement)).getText())){
                        Mdl_Variables.driver.findElement(By.xpath(tagElement)).click();

                        Mdl_Variables.strPasosEvidencia = "Seleccionar la opción " + Mdl_Variables.arrKeyValue[0];
                        Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
                        flag = true;
                    } i++;
                } Thread.sleep(2000);
            }catch(Throwable e){System.out.println("Error en \"departamento()\" al seleccionar: " + e.getMessage());}
        }
    }

	/**
	 * 
	 * @author Romer Vargas Otiniano
	 * @version 1.0
	 * @date 13/05/2016
	 */
	public static void provincia(){
        if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
        	//
        }else{
            try{
            	boolean flag = false;
        		int i = 1;

            	Mdl_Variables.driver.findElement(By.xpath("(//button[@type='button'])[6]")).click();
            	String tagElement = new String();
            	Thread.sleep(2000);

            	Mdl_Evidenciamiento.Evidencia_Log(true, "");
                Mdl_Evidenciamiento.Ingreso_Datos_Log();
                Thread.sleep(1000);

                while(i <= 5 && !flag){tagElement = "//div[8]/div/ul/li["  + i + "]/a";
                    if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath(tagElement)).getText())){
                    	Mdl_Variables.driver.findElement(By.xpath(tagElement)).click();

                        Mdl_Variables.strPasosEvidencia = "Seleccionar la provincia " + Mdl_Variables.arrKeyValue[0];
                        Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
                        flag = true;
                    } i++;
                } Thread.sleep(2000);
            }catch(Throwable e){System.out.println("Error en \"provincia()\" al seleccionar: " + e.getMessage());}
        }
    }

	/**
	 * 
	 * @author Romer Vargas Otiniano
	 * @version 1.0
	 * @date 13/05/2016
	 */
    public static void distrito(){
        if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
        	//
        }else{
            try{
            	boolean flag = false;
        		int i = 1;

                Mdl_Variables.driver.findElement(By.xpath("(//button[@type='button'])[7]")).click();
                String tagElement = new String();
                Thread.sleep(2000);

                Mdl_Evidenciamiento.Evidencia_Log(true, "");
                Mdl_Evidenciamiento.Ingreso_Datos_Log();
                Thread.sleep(1000);

                while(i <= 5 && !flag){tagElement = "//div[9]/div/ul/li[" + i + "]/a/span";
            		if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath(tagElement)).getText())){
                        Mdl_Variables.driver.findElement(By.xpath(tagElement)).click();

                        Mdl_Variables.strPasosEvidencia = "Seleccionar la opción " + Mdl_Variables.arrKeyValue[0];
                        Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
                        flag = true;
                    } i++;
        		} Thread.sleep(2000);
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
	 * 
	 * @param type
	 * @author Romer Vargas Otiniano
	 * @version 1.0
	 */
	public static void seleccionador_telefono_email_card(int type){
		try{
			String findingTag = "";
			String clickTag = "";
			boolean flag = false;
			String txtMsg = "";
			int i = 1;

			if(type == 1){clickTag = "(//button[@type='button'])[4]"; txtMsg = "teléfono";}
			else{clickTag = "(//button[@type='button'])[5]"; txtMsg = "email";}

			Mdl_Variables.driver.findElement(By.xpath(clickTag)).click();

			while(!flag && i <= 10){
				if(type == 1){findingTag = "//div[5]/div/ul/li[" + i + "]/a/span";}
				else{findingTag = "//div[6]/div/ul/li[" + i + "]/a/span";}

				if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath(findingTag)).getText())){flag = true;
					Mdl_Variables.driver.findElement(By.xpath(findingTag)).click();
					Mdl_Variables.strPasosEvidencia = "Seleccionar el " + txtMsg + " de tarjetas :" + Mdl_Variables.arrKeyValue[0];
					Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
				} i++;
			}
		}catch(Throwable e){System.out.println("Error en \"seleccionador_telefono_email_card()\" al seleccionar opción " + e.getMessage());}
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
				int tot = 30;

				while(i <= tot && !flag){
					findingTag = "//article[@id='print-section']/div/table/tbody/tr[" + i + "]/td/span";
System.out.println("arrValores[0] " + arrValores[0] + "\nListado: " + Mdl_Variables.driver.findElement(By.xpath(findingTag)).getText());

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
			Thread.sleep(2000);
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
			//WebElement element = Mdl_Variables.driver.findElement(By.xpath("//div[@id='mis-cuentas-scroll']"));
			//js.executeScript("arguments[0].setAttribute('style', 'height: 3000px')",element);
			WebElement element = Mdl_Variables.driver.findElement(By.xpath("//div[@id='switch-1']/article"));
			js.executeScript("arguments[0].removeAttribute('class')", element);
			Thread.sleep(3000);

			if(!Mdl_Variables.arrKeyValue[0].equals("010101010101010101")){
				while(i <= 15 && !flag){findingTag = "//div[@id='mis-cuentas-scroll']/div/div/table/tbody/tr[" + i + "]/td[2]/span"; //findingTag = "//div[@id='mis-cuentas-scroll']/div/div/table/tbody/tr[" + i + "]/td/label";
					if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath(findingTag)).getText())){
						Mdl_Variables.driver.findElement(By.xpath(findingTag)).click();
						Mdl_Variables.strPasosEvidencia = "Seleccionar la opión: " + Mdl_Variables.arrKeyValue[0];
						Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
	
						Mdl_Evidenciamiento.Evidencia_Log(true, "");
						Mdl_Evidenciamiento.Ingreso_Datos_Log();
						flag = true;
					} i++;
				}
			}else{
				Mdl_Evidenciamiento.Evidencia_Log(true, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
			} Thread.sleep(1000);
		}catch(Exception e){System.out.println("Error en \"seleccionarOpcion()\" al seleccionar opción " + e.getMessage());}					
	}

	/**
	 * 
	 * @author Romer Vargas Otiniano
	 * @version 1.0
	 * @date 06/04/2016
	 */
	public static void bajar_pagina_flecha(){Mdl_Bnet.bajarPaginaFlecha(Mdl_Variables.arrKeyValue[0]);}

	/**
	 * 
	 * @author Romer Vargas Otiniano
	 * @version 1.0
	 * @date 05/05/2016
	 */
	public static void bajar_pagina_flecha(String total){Mdl_Bnet.bajarPaginaFlecha(total);}

	/**
	 * 
	 * @author Romer Vargas Otiniano
	 * @version 1.0
	 * @date 05/05/2016
	 */
	private static void bajarPaginaFlecha(String total){
		try{
			if(total != ""){
				int tot = Integer.parseInt(total);
				Robot robot = new Robot();

				for(int i = 0; i < tot; i++){
					robot.keyPress(KeyEvent.VK_DOWN);
					robot.keyRelease(KeyEvent.VK_DOWN);
				} Thread.sleep(1000);
			}
		}catch(Throwable e){System.out.println("Error en \"bajarPaginaFlecha()\" al seleccionar opción " + e.getMessage());}
	}

	/**
	 * 
	 * @author Romer Vargas Otiniano
	 * @version 1.0
	 * @date 31/05/2016
	 */
	public static void subir_pagina_flecha(){Mdl_Bnet.subirPaginaFlecha(Mdl_Variables.arrKeyValue[0]);}

	/**
	 * 
	 * @author Romer Vargas Otiniano
	 * @version 1.0
	 * @date 31/05/2016
	 */
	public static void subir_pagina_flecha(String total){Mdl_Bnet.subirPaginaFlecha(total);}

	/**
	 * 
	 * @author Romer Vargas Otiniano
	 * @version 1.0
	 * @date 31/05/2016
	 */
	private static void subirPaginaFlecha(String total){
		try{
			if(total != ""){
				int tot = Integer.parseInt(total);
				Robot robot = new Robot();

				for(int i = 0; i < tot; i++){
					robot.keyPress(KeyEvent.VK_UP);
					robot.keyRelease(KeyEvent.VK_UP);
				} Thread.sleep(1000);
			}
		}catch(Throwable e){System.out.println("Error en \"subirPaginaFlecha()\" al seleccionar opción " + e.getMessage());}
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
		//String desc_paso;
		//String[] Cadena =StrCadena.split("\\|");
	    int i = 1 ;
	    if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
		}else{
		try {
			Thread.sleep(5000);
			Mdl_Variables.driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
			while(i<=3){
				
				if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath("//div[6]/div/ul/li["+i+"]/a/span")).getText())){
					
						Mdl_Variables.driver.findElement(By.xpath("//div[6]/div/ul/li["+i+"]/a/span")).click();
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
		//String desc_paso;
		//String[] Cadena =StrCadena.split("\\|");
	    int i = 1 ;
	    if (Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
		}else{
		try {
			Thread.sleep(5000);
			
			Mdl_Variables.driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
			while(i<=3){
				
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
			while(i <= 15 && !flag){findingTag = "//table[@id='tbl_tarjetas_resumen']/tbody/tr[" + i + "]/td";
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
     * Seleccionar del Combo Consultar Filtro Tarjeta
     * @author Juliet
     * @author Romer Vargas
     * @version 1.0
     * @date 14062016
     **/

    public static void seleccionar_periodo(String StrCadena) throws SQLException{
    	boolean flag = false;
    	String findingTag = "";
    	int i = 1 ;

    	if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
    		//
    	}else{
    		try{
				Mdl_Variables.driver.findElement(By.xpath("//button[@type='button']")).click();
				Thread.sleep(2000);

				while(i <= 2 && !flag){findingTag = "//div[contains(@class, 'btn-group') and contains(@class, 'bootstrap-select') and contains(@class, 'open')]/div/ul/li[" + i + "]/a/span";
					if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath(findingTag)).getText())){
						Mdl_Variables.strPasosEvidencia = "Seleccionar Periodo: "+ Mdl_Variables.arrKeyValue[0];
						Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
						Mdl_Evidenciamiento.Evidencia_Log(true, "");
						Mdl_Evidenciamiento.Ingreso_Datos_Log();
						flag = true;

						Mdl_Variables.driver.findElement(By.xpath(findingTag)).click();
					} i++;
				} Thread.sleep(1000);
    		}catch(Exception e){System.out.println("Error en seleccionar_periodo Mdl_Bnet: seleccionar opción " + e.getMessage());}
    	}
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
				int i = 2;
				int tot = 15;
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
				Thread.sleep(2000);
			}catch(Throwable e){System.out.println("Error en \"claveTemporizador()\" no encontró el objeto" + e.getMessage());}
		}
	}

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
				strMonedaOp = Web_Framework.GetValue(arrAV1[6]);
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
				Thread.sleep(3000);

				Mdl_Variables.driver.findElement(By.id("sec_mis_cuentas")).click();
				Mdl_Variables.driver.findElement(By.linkText("Transferencias")).click();
				Mdl_Variables.driver.findElement(By.linkText("A Cuentas de Terceros")).click();
			    // ERROR: Caught exception [ERROR: Unsupported command [selectFrame | marcoDinamico | ]]
				selectByPartOfVisibleText("id","AsuntoPropio",strCuentaOrigen,true,true);
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
				
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
			    strComision = Mdl_Variables.driver.findElement(By.xpath("//table[@id='tb_0']/tbody/tr[6]/td[2]/p")).getText();
			    Mdl_Variables.driver.findElement(By.name("Submit")).click();
			   
			    strSQLaux = "UPDATE " + Mdl_Variables.P_Str_Tabla + "_D SET " + strCampoComision.trim() + " = '" + strComision + "' WHERE ID_CASO = " + Mdl_Variables.Rst_Tabla.getInt("ID_CASO");
                Mdl_Variables.Cnn.prepareCall(strSQLaux).execute();
			    
			    //Recargamos el DDriven
                Mdl_Variables.Rst_Tabla.refreshRow();
                Mdl_Variables.driver.quit();
                
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
	
	/**
     * 
     * @author Romer Vargas Otiniano
     * @version 1.0
     * @date 28/04/2016
     */
	public static void bnetactual_donaciones(String strAV1, String strAV2) throws SQLException, InterruptedException{
		String[] arrAV1 = strAV1.split("\\|");
		String strCuentaOrigen = Web_Framework.GetValue(arrAV1[0]);
		String strCampania = Web_Framework.GetValue(arrAV1[1]);
		//String strCuentaDestino = Web_Framework.GetValue(arrAV1[2]);
		String strMonedaOp = "Soles";
		String strMonto = "0.00";
		//String strSQLaux = "";
		Web_Framework.launchapp(Mdl_Variables.Tipo_Aplicativo);

		try{
			if(!Web_Framework.GetValue(arrAV1[3]).toLowerCase().equals("<ignore>")){strMonedaOp = "Soles";}
			else if(!Web_Framework.GetValue(arrAV1[4]).toLowerCase().equals("<ignore>")){strMonedaOp = "Dólares";}

			if(!Web_Framework.GetValue(arrAV1[5]).toLowerCase().equals("<ignore>")){strMonto = Web_Framework.GetValue(arrAV1[5]);}
			else if(!Web_Framework.GetValue(arrAV1[6]).toLowerCase().equals("<ignore>")){strMonto = Web_Framework.GetValue(arrAV1[6]);}

			Mdl_Choucair.getDatosLogin();
			Mdl_Variables.driver.get(Mdl_Variables.P_Str_RutaAmbienteSecundario);
			Mdl_Variables.driver.findElement(By.id("initial_input")).clear();
			Mdl_Variables.driver.findElement(By.id("initial_input")).sendKeys(Mdl_Variables.Str_DatosUsuario[3]);
			Mdl_Variables.driver.findElement(By.name("eai_password")).clear();
			Mdl_Variables.driver.findElement(By.name("eai_password")).sendKeys(Mdl_Variables.Str_DatosUsuario[10]);
			Mdl_Evidenciamiento.Evidencia_Log(true, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			Mdl_Variables.driver.findElement(By.cssSelector("a.c1 > img")).click();
			Thread.sleep(2000);

			Mdl_Variables.driver.findElement(By.id("sec_mis_cuentas")).click();
			Thread.sleep(2000);
			Mdl_Variables.driver.findElement(By.linkText("Realiza tus Donaciones")).click();
			Thread.sleep(2000);
			Mdl_Variables.driver.findElement(By.linkText(strCampania)).click();
			Thread.sleep(2000);

			bajarPaginaFlecha("5");
			selectByPartOfVisibleText("id", "AsuntoPropio", strCuentaOrigen, true, true);
			Mdl_Variables.driver.findElement(By.id("Importe")).clear();
			Mdl_Variables.driver.findElement(By.id("Importe")).sendKeys(strMonto);
			new Select(Mdl_Variables.driver.findElement(By.id("Divisa"))).selectByVisibleText(strMonedaOp);
			Thread.sleep(1000);
			Mdl_Evidenciamiento.Evidencia_Log(true, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
		    Mdl_Variables.driver.findElement(By.name("Submit")).click();
		    Thread.sleep(1000);
		    bajarPaginaFlecha("5");
		    
			Mdl_Variables.driver.findElement(By.id("ClaveSegundoFactor")).clear();
			Mdl_Variables.driver.findElement(By.id("ClaveSegundoFactor")).sendKeys("123456");
			Thread.sleep(1000);
			Mdl_Evidenciamiento.Evidencia_Log(true, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			Mdl_Variables.driver.findElement(By.name("Submit")).click();
		    Thread.sleep(2000);
		    Mdl_Evidenciamiento.Evidencia_Log(true, "");
		    Mdl_Evidenciamiento.Ingreso_Datos_Log();

//Almacenamos los resultado en el DDriven
//strSQLaux = "UPDATE " + Mdl_Variables.P_Str_Tabla + "_D SET " + strCampoComision.trim() + " = '" + strComision + "' WHERE ID_CASO = " + Mdl_Variables.Rst_Tabla.getInt("ID_CASO");
//Mdl_Variables.Cnn.prepareCall(strSQLaux).execute();
            Mdl_Variables.Rst_Tabla.refreshRow(); //Recargamos el DDriven
            Mdl_Variables.driver.quit(); //Desconectamos la aplicación
		}catch(Throwable e){System.out.println("Error en \"bnetactual_donaciones()\" no encontró el objeto" + e.getMessage());
			Mdl_Evidenciamiento.Evidencia_Log(false, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			Mdl_Variables.boolAction = 0;
            Mdl_Variables.driver.quit();
		}
	}
	
	/**
     * Transferencia Giros Nacionales
     * @author Juliet Estefany Agreda Lavado
     * @version 1.0
     * @date 11/05/2016
     */
	public static void bnetactual_girosnacionales(String strAV1) throws SQLException{
		String[] arrAV1 = strAV1.split("\\|");

		String strCuentaOrigen = Web_Framework.GetValue(arrAV1[0]);
		String strMonto = "0.00";
		String strMonedaOp = "Soles";
		String strBeneficiario = Web_Framework.GetValue(arrAV1[5]);
		String strDocumento = Web_Framework.GetValue(arrAV1[6]);
		String strLocalidad = Web_Framework.GetValue(arrAV1[7]);
		String strTipoDocumento = Web_Framework.GetValue(arrAV1[8]);

		try{
			if(Web_Framework.GetValue("dt_KWD_BNET_ACTUAL").equals("TRUE")){
				Web_Framework.launchapp(Mdl_Variables.Tipo_Aplicativo);
				Thread.sleep(4000);
				Mdl_Keyboard.doType(KeyEvent.VK_ENTER);
				Thread.sleep(5000);

				if(!Web_Framework.GetValue(arrAV1[1]).toLowerCase().equals("<ignore>")){strMonto = Web_Framework.GetValue(arrAV1[1]);}
				else if(!Web_Framework.GetValue(arrAV1[2]).toLowerCase().equals("<ignore>")){strMonto = Web_Framework.GetValue(arrAV1[2]);}
				
				if (!Web_Framework.GetValue(arrAV1[3]).toLowerCase().equals("<ignore>")){strMonedaOp = "Soles";}
				else if (!Web_Framework.GetValue(arrAV1[4]).toLowerCase().equals("<ignore>")){strMonedaOp = "Dólares";}

				Mdl_Choucair.getDatosLogin();
				Mdl_Variables.driver.get(Mdl_Variables.P_Str_RutaAmbienteSecundario);
				Mdl_Variables.driver.findElement(By.id("initial_input")).clear();
				Mdl_Variables.driver.findElement(By.id("initial_input")).sendKeys(Mdl_Variables.Str_DatosUsuario[3]);
				Mdl_Variables.driver.findElement(By.name("eai_password")).clear();
				Mdl_Variables.driver.findElement(By.name("eai_password")).sendKeys(Mdl_Variables.Str_DatosUsuario[10]);
				Mdl_Evidenciamiento.Evidencia_Log(true, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
				Mdl_Variables.driver.findElement(By.cssSelector("a.c1 > img")).click();
				Thread.sleep(2000);
				
				Mdl_Variables.driver.findElement(By.id("sec_mis_cuentas")).click();
				Thread.sleep(2000);
				Mdl_Variables.driver.findElement(By.linkText("Transferencias")).click();
				Thread.sleep(2000);
				Mdl_Variables.driver.findElement(By.linkText("Realiza Giros Nacionales")).click();
				Thread.sleep(2000);

				bajarPaginaFlecha("5");
				selectByPartOfVisibleText("id", "AsuntoPropio", strCuentaOrigen, true, true);			
				Mdl_Variables.driver.findElement(By.name("importe")).clear();
			    Mdl_Variables.driver.findElement(By.name("importe")).sendKeys(strMonto);
			    selectByPartOfVisibleText("name", "Divisa", strMonedaOp, false, true);
			    Thread.sleep(1000);
			    Mdl_Variables.driver.findElement(By.name("beneficiario")).clear();
			    Mdl_Variables.driver.findElement(By.name("beneficiario")).sendKeys(strBeneficiario);

List<WebElement> optionElements = Mdl_Variables.driver.findElement(By.name("documento")).findElements(By.tagName("option"));
for(WebElement optionElement : optionElements){
    if(optionElement.getText().toLowerCase().contains(strTipoDocumento.toLowerCase())){
        String optionIndex = optionElement.getAttribute("index");
        int intOptionIndex = Integer.parseInt(optionIndex);
        new Select(Mdl_Variables.driver.findElement(By.name("documento"))).selectByIndex(intOptionIndex);
    }
}
			    Thread.sleep(1000);
			    Mdl_Variables.driver.findElement(By.id("num_documento")).clear();
			    Mdl_Variables.driver.findElement(By.id("num_documento")).sendKeys(strDocumento);
			    new Select(Mdl_Variables.driver.findElement(By.name("Localidad"))).selectByVisibleText(strLocalidad);
			    Thread.sleep(1000);
			    Mdl_Evidenciamiento.Evidencia_Log(true, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
			    Mdl_Variables.driver.findElement(By.name("Submit")).click();
			    Thread.sleep(1000);
			    bajarPaginaFlecha("5");

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
			    Thread.sleep(3000);
				Mdl_Evidenciamiento.Evidencia_Log(true, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();

				//Recargamos el DDriven
	            Mdl_Variables.Rst_Tabla.refreshRow();
	            Mdl_Variables.driver.quit();
			}
		}catch(Throwable e){System.out.println("Error en el logueo giros nacionales: " + e.getMessage());
			Mdl_Evidenciamiento.Evidencia_Log(false, "");
			//String desc_paso = "No se pudo realizar el logueo en giros nacionales";
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			Mdl_Variables.boolAction = 0;
		}
	}

	/**
     * 
     * @author Romer Vargas Otiniano
     * @version 1.0
     * @date 28/04/2016
     */
	public static void selectByPartOfVisibleText(String strPropiedad, String strIdSelect, String value, boolean blnFrame, boolean evidencia) throws InterruptedException{
		List<WebElement> optionElements;
		WebElement selObjeto;

		try{
			if(strPropiedad.toLowerCase().equals("id")){
				if(blnFrame == true){//optionElements = Mdl_Variables.driver.switchTo().frame(0).findElement(By.id(strIdSelect)).findElements(By.tagName("option"));
					selObjeto =  Mdl_Variables.driver.switchTo().frame(0).findElement(By.id(strIdSelect));
				}else{selObjeto =  Mdl_Variables.driver.findElement(By.id(strIdSelect));}
			}else if(strPropiedad.toLowerCase().equals("xpath")){
				if(blnFrame == true){selObjeto =  Mdl_Variables.driver.switchTo().frame(0).findElement(By.xpath(strIdSelect));}
				else{selObjeto =  Mdl_Variables.driver.findElement(By.xpath(strIdSelect));}
			}else if(strPropiedad.toLowerCase().equals("name")){
				if(blnFrame == true){selObjeto =  Mdl_Variables.driver.switchTo().frame(0).findElement(By.name(strIdSelect));}
				else{selObjeto =  Mdl_Variables.driver.findElement(By.name(strIdSelect));}
			}else{
				if(blnFrame == true){selObjeto =  Mdl_Variables.driver.switchTo().frame(0).findElement(By.cssSelector(strIdSelect));}
				else{selObjeto =  Mdl_Variables.driver.findElement(By.cssSelector(strIdSelect));}
			}

			if(evidencia){
				selObjeto.click();
				Thread.sleep(1000);
				Mdl_Evidenciamiento.Evidencia_Log(true, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
			}

			if(!value.equalsIgnoreCase("010101010101010101")){
				optionElements = selObjeto.findElements(By.tagName("option"));

				for(WebElement optionElement : optionElements){
			        if(optionElement.getText().contains(value)){
			            String optionIndex = optionElement.getAttribute("index");
			            int intOptionIndex = Integer.parseInt(optionIndex);
			            new Select(selObjeto).selectByIndex(intOptionIndex);
			            break;
			        }
			    }
			}
		}catch(Throwable e){System.out.println("se presentó un error al seleccionar en lista: " + e.getMessage());
			Mdl_Evidenciamiento.Evidencia_Log(false, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			Mdl_Variables.boolAction = 0;
		}
	}

	/**
     * 
     * @author Romer Vargas Otiniano
     * @version 1.0
     * @date 03/05/2016
     */
	public static void realizarTRXExterior(){
		try{
			Mdl_Variables.driver.findElement(By.xpath("//div[@id='operaciones-list-container']/div[3]/div/ul/li[7]/a/strong")).click();
			Mdl_Variables.strPasosEvidencia = "Seleccionar la operación Realizar transferencia al exterior";
			Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
			Thread.sleep(3000);
		}catch(Throwable e){System.out.println("Error en \"realizarTRXExterior()\" no encontró el objeto" + e.getMessage());}
	}

	/**
     * 
     * @author Romer Vargas Otiniano
     * @version 1.0
     * @date 03/05/2016
     */
	public static void seleccionarMoneda(){
		try{
			String findingTag = "";
			boolean flag = false;
			int i = 1;

			Mdl_Variables.driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
			Thread.sleep(1000);

			while(i <= 30 && !flag){findingTag = "//div[5]/div/ul/li[" + i + "]/a/span";
				if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath(findingTag)).getText())){
					Mdl_Variables.driver.findElement(By.xpath(findingTag)).click();
					Mdl_Variables.strPasosEvidencia = "Seleccionar la opción " + Mdl_Variables.arrKeyValue[0];
					Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
					flag = true;
				} i++;
			} Thread.sleep(1000);
		}catch(Throwable e){System.out.println("Error en \"seleccionarMoneda()\" no encontró el objeto" + e.getMessage());}
	}

	/**
     * 
     * @author Romer Vargas Otiniano
     * @version 1.0
     * @date 03/05/2016
     */
	public static void seleccionarGastos(){
		try{
			String findingTag = "";
			boolean flag = false;
			int i = 1;

			Mdl_Variables.driver.findElement(By.xpath("(//button[@type='button'])[5]")).click();
			Thread.sleep(1000);

			while(i <= 2 && !flag){findingTag = "//div[6]/div/ul/li[" + i + "]/a/span";
				if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath(findingTag)).getText())) {
					Mdl_Variables.driver.findElement(By.xpath(findingTag)).click();

					Mdl_Variables.strPasosEvidencia = "Seleccionar la opción " + Mdl_Variables.arrKeyValue[0];
					Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
					flag = true;
				} i++;
			} Thread.sleep(1000);
		}catch(Throwable e){System.out.println("Error en \"seleccionarGastos()\" no encontró el objeto" + e.getMessage());}
	}

	/**
     * 
     * @author Romer Vargas Otiniano
     * @version 1.0
     * @date 05/05/2016
     */
	public static void bnetactual_propias(String strAV1, String strAV2) throws SQLException, InterruptedException{
		String[] arrAV1 = strAV1.split("\\|");
		String[] arrAV2 = strAV2.split("\\|");

		String strCuentaOrigen = Web_Framework.GetValue(arrAV1[0]);
		String strCuentaDestino = Web_Framework.GetValue(arrAV1[1]);
		String strMonedaOp = "Soles";
		String strMonto = "0.000";

		String strComision = "0.00";
		String strCampoComision = arrAV2[1];
		String strSQLaux = "";

		Web_Framework.launchapp(Mdl_Variables.Tipo_Aplicativo);
		Thread.sleep(4000);
		Mdl_Keyboard.doType(KeyEvent.VK_ENTER);
		Thread.sleep(5000);

		try{
			if (!Web_Framework.GetValue(arrAV1[2]).toLowerCase().equals("<ignore>")){strMonedaOp = "Soles";}
			else if (!Web_Framework.GetValue(arrAV1[3]).toLowerCase().equals("<ignore>")){strMonedaOp = "Dolares";}

			if (!Web_Framework.GetValue(arrAV1[4]).toLowerCase().equals("<ignore>")){strMonto = Web_Framework.GetValue(arrAV1[4]);}
			else if (!Web_Framework.GetValue(arrAV1[5]).toLowerCase().equals("<ignore>")){strMonto = Web_Framework.GetValue(arrAV1[5]);}

			if (strCampoComision.toLowerCase().contains("dt_")){strCampoComision = strCampoComision.replace("dt_", "");}

			Mdl_Choucair.getDatosLogin();
			Mdl_Variables.driver.get(Mdl_Variables.P_Str_RutaAmbienteSecundario);
			Mdl_Variables.driver.findElement(By.id("initial_input")).clear();
			Mdl_Variables.driver.findElement(By.id("initial_input")).sendKeys(Mdl_Variables.Str_DatosUsuario[3]);
			Mdl_Variables.driver.findElement(By.name("eai_password")).clear();
			Mdl_Variables.driver.findElement(By.name("eai_password")).sendKeys(Mdl_Variables.Str_DatosUsuario[10]);
			Thread.sleep(3000);
			Mdl_Evidenciamiento.Evidencia_Log(true, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			Mdl_Variables.driver.findElement(By.cssSelector("a.c1 > img")).click();
			Thread.sleep(3000);
			Mdl_Evidenciamiento.Evidencia_Log(true, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			Mdl_Variables.driver.findElement(By.id("sec_mis_cuentas")).click();
			Thread.sleep(3000);
			Mdl_Evidenciamiento.Evidencia_Log(true, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			Mdl_Variables.driver.findElement(By.linkText("Transferencias")).click();
			Thread.sleep(3000);
			Mdl_Evidenciamiento.Evidencia_Log(true, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			Mdl_Variables.driver.findElement(By.linkText("A tus Cuentas Propias")).click();
			Thread.sleep(3000);
			Mdl_Evidenciamiento.Evidencia_Log(true, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();

			selectByPartOfVisibleText("id", "AsuntoPropio", strCuentaOrigen, true, true);
			Thread.sleep(1000);
			Mdl_Evidenciamiento.Evidencia_Log(true, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();

		    Mdl_Variables.driver.findElement(By.name("Submit")).click();
		    Thread.sleep(2000);
		    bajarPaginaFlecha("7");
		    selectByPartOfVisibleText("id", "AsuntoPropio", strCuentaDestino, false, true);
		    new Select(Mdl_Variables.driver.findElement(By.id("Divisa"))).selectByVisibleText(strMonedaOp);
		    Mdl_Variables.driver.findElement(By.id("Importe")).clear();
		    Mdl_Variables.driver.findElement(By.id("Importe")).sendKeys(strMonto);
		    Mdl_Variables.driver.findElement(By.id("Observaciones")).clear();
		    Mdl_Variables.driver.findElement(By.id("Observaciones")).sendKeys("referencia de prueba");
		    Thread.sleep(2000);
			Mdl_Evidenciamiento.Evidencia_Log(true, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
		    Mdl_Variables.driver.findElement(By.id("Submit")).click();
		    bajarPaginaFlecha("8");
		    Thread.sleep(1000);
			Mdl_Evidenciamiento.Evidencia_Log(true, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
		    strComision = Mdl_Variables.driver.findElement(By.xpath("//table[@id='tb_0']/tbody/tr[6]/td[2]/p")).getText();
		    Mdl_Variables.driver.findElement(By.name("Submit")).click();
		    Thread.sleep(2000);
		    bajarPaginaFlecha("9");
		    Mdl_Evidenciamiento.Evidencia_Log(true, "");
		    Mdl_Evidenciamiento.Ingreso_Datos_Log();
		    //Almacenamos los resultado en el DDriven
		    strSQLaux = "UPDATE " + Mdl_Variables.P_Str_Tabla + "_D SET " + strCampoComision.trim() + " = '" + strComision + "' WHERE ID_CASO = " + Mdl_Variables.Rst_Tabla.getInt("ID_CASO");
            Mdl_Variables.Cnn.prepareCall(strSQLaux).execute();

		    //Recargamos el DDriven
            Mdl_Variables.Rst_Tabla.refreshRow();
            Mdl_Variables.driver.quit();
		}catch(Throwable e){System.out.println("Error en \"bnetactual_propias()\" no encontró el objeto" + e.getMessage());
			Mdl_Evidenciamiento.Evidencia_Log(false, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			Mdl_Variables.boolAction = 0;
            Mdl_Variables.driver.quit();
		}
	}

	/**
	 * 
	 * @param strCampos
	 * @author Romer Vargas Otiniano
	 * @version 1.0
	 * @date 09/05/2016
	 */
	public static void ultimo_mov_exterior(String strCampos) throws SQLException{
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
				Thread.sleep(2000);
				int i = 2;
				int tot = 15;

				while(i <= tot && !flag){
					findingTag = "//article[@id='print-section']/div/table/tbody/tr[" + i + "]/td/span";

					if(arrValores[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath(findingTag)).getText())){
						Mdl_Variables.driver.findElement(By.xpath(findingTag)).click();
						Thread.sleep(2000);

						Mdl_Variables.strPasosEvidencia = "Seleccionar el número de cuenta " + arrValores[0] + " para verificar el movimiento de la cuenta";
						Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);

						Thread.sleep(3000);
						Mdl_Bnet.bajar_pagina_flecha("9");
						Mdl_Evidenciamiento.Evidencia_Log(true, "");
						Mdl_Evidenciamiento.Ingreso_Datos_Log();
						flag = true;
					} i++;
				}

				if(!flag){
					Mdl_Evidenciamiento.Evidencia_Log(true, "");
					Mdl_Evidenciamiento.Ingreso_Datos_Log();
					Thread.sleep(1000);
				} Thread.sleep(1000);
			}
		}catch(Throwable e){System.out.println("Error en \"ultimo_mov()\" al seleccionar: " + e.getMessage());}

		Mdl_Evidenciamiento.CADENA_PASOS(desc_paso);
	}

	/**
     * 
     * @author Romer Vargas Otiniano
     * @version 1.0
     * @date 09/05/2016
     */
	public static void bnetactual_exterior(String strAV1, String strAV2) throws SQLException, InterruptedException{
		String[] arrAV1 = strAV1.split("\\|");

		String strCuentaOrigen = Web_Framework.GetValue(arrAV1[0]); //dt_TXT_CUENTAINICIAL
		String strCuentaDestino = Web_Framework.GetValue(arrAV1[1]); //dt_TXT_CUENTABONO
		String strMonedaOp = Web_Framework.GetValue(arrAV1[2]); //dt_TXT_MONEDA
		String strMonto = Web_Framework.GetValue(arrAV1[3]); //dt_TXT_IMPORTE

		try{
			if(Web_Framework.GetValue("dt_KWD_BNET_ACTUAL").equals("TRUE")){
				Web_Framework.launchapp(Mdl_Variables.Tipo_Aplicativo);
				Thread.sleep(4000);
				Mdl_Keyboard.doType(KeyEvent.VK_ENTER);
				Thread.sleep(5000);
				Mdl_Choucair.getDatosLogin();

				Mdl_Variables.driver.get(Mdl_Variables.P_Str_RutaAmbienteSecundario);
				Mdl_Variables.driver.findElement(By.id("initial_input")).clear();
				Mdl_Variables.driver.findElement(By.id("initial_input")).sendKeys(Mdl_Variables.Str_DatosUsuario[3]);
				Mdl_Variables.driver.findElement(By.name("eai_password")).clear();
				Mdl_Variables.driver.findElement(By.name("eai_password")).sendKeys(Mdl_Variables.Str_DatosUsuario[10]); //2015
				Thread.sleep(2000);
				Mdl_Evidenciamiento.Evidencia_Log(true, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
				Mdl_Variables.driver.findElement(By.cssSelector("a.c1 > img")).click();
				Thread.sleep(2000);
				Mdl_Variables.driver.findElement(By.id("sec_mis_cuentas")).click();
				Thread.sleep(2000);
				Mdl_Variables.driver.findElement(By.linkText("Transferencias")).click();
				Thread.sleep(2000);
				Mdl_Variables.driver.findElement(By.linkText("Al Exterior")).click();
				Thread.sleep(2000);

				selectByPartOfVisibleText("name", "AsuntoPropio", strCuentaOrigen, true, true);
			    Thread.sleep(2000);
			    bajarPaginaFlecha("5");
			    Mdl_Variables.driver.findElement(By.name("I4mporte")).clear();
			    Mdl_Variables.driver.findElement(By.name("I4mporte")).sendKeys(strMonto);

				List<WebElement> optionElements = Mdl_Variables.driver.findElement(By.name("Divisa")).findElements(By.tagName("option"));
				for(WebElement optionElement : optionElements){
				    if(optionElement.getText().toLowerCase().contains(strMonedaOp.toLowerCase())){
				        String optionIndex = optionElement.getAttribute("index");
				        int intOptionIndex = Integer.parseInt(optionIndex);
				        new Select(Mdl_Variables.driver.findElement(By.name("Divisa"))).selectByIndex(intOptionIndex);
				    }
				}

			    Mdl_Variables.driver.findElement(By.id("C3taAbono")).clear();
			    Mdl_Variables.driver.findElement(By.id("C3taAbono")).sendKeys(strCuentaDestino);

			    if(!Web_Framework.GetValue(arrAV1[4]).equalsIgnoreCase("<IGNORE>")){Mdl_Variables.driver.findElement(By.name("N2ombBeneficiario")).clear();
				    Mdl_Variables.driver.findElement(By.name("N2ombBeneficiario")).sendKeys(Web_Framework.GetValue(arrAV1[4])); //dt_TXT_BENEFICIARIO
			    }

			    if(!Web_Framework.GetValue(arrAV1[5]).equalsIgnoreCase("<IGNORE>")){Mdl_Variables.driver.findElement(By.name("D3ireTeleMotivo1")).clear();
				    Mdl_Variables.driver.findElement(By.name("D3ireTeleMotivo1")).sendKeys(Web_Framework.GetValue(arrAV1[5])); //dt_TXT_DIRBENEFICIARIO
			    }

			    if(!Web_Framework.GetValue(arrAV1[6]).equalsIgnoreCase("<IGNORE>")){Mdl_Variables.driver.findElement(By.name("DireTeleMotivo3")).clear();
				    Mdl_Variables.driver.findElement(By.name("DireTeleMotivo3")).sendKeys(Web_Framework.GetValue(arrAV1[6])); //dt_TXT_MOTIVO
			    }

			    if(!Web_Framework.GetValue(arrAV1[7]).equalsIgnoreCase("<IGNORE>")){Mdl_Variables.driver.findElement(By.name("DireTeleMotivo4")).clear();
				    Mdl_Variables.driver.findElement(By.name("DireTeleMotivo4")).sendKeys(Web_Framework.GetValue(arrAV1[7])); //dt_TXT_MOTIVO2
			    }

			    if(!Web_Framework.GetValue(arrAV1[8]).equalsIgnoreCase("<IGNORE>")){Mdl_Variables.driver.findElement(By.name("A1vance")).clear();
				    Mdl_Variables.driver.findElement(By.name("A1vance")).sendKeys(Web_Framework.GetValue(arrAV1[8])); //dt_TXT_NAVANCE
			    }

			    optionElements = Mdl_Variables.driver.findElement(By.name("Gastos")).findElements(By.tagName("option"));
				for(WebElement optionElement : optionElements){
				    if(optionElement.getText().toLowerCase().contains(Web_Framework.GetValue(arrAV1[9]).toLowerCase())){ //dt_TXT_GASTOS
				        String optionIndex = optionElement.getAttribute("index");
				        int intOptionIndex = Integer.parseInt(optionIndex);
				        new Select(Mdl_Variables.driver.findElement(By.name("Gastos"))).selectByIndex(intOptionIndex);
				    }
				}

				if(!Web_Framework.GetValue(arrAV1[10]).equalsIgnoreCase("<IGNORE>")){Mdl_Variables.driver.findElement(By.name("B2anNombre")).clear();
				    Mdl_Variables.driver.findElement(By.name("B2anNombre")).sendKeys(Web_Framework.GetValue(arrAV1[10])); //dt_TXT_BANCO	
				}

				if(!Web_Framework.GetValue(arrAV1[11]).equalsIgnoreCase("<IGNORE>")){Mdl_Variables.driver.findElement(By.name("B3anDire1")).clear();
				    Mdl_Variables.driver.findElement(By.name("B3anDire1")).sendKeys(Web_Framework.GetValue(arrAV1[11])); //dt_TXT_DIRECCIONBANCO
				}

				if(!Web_Framework.GetValue(arrAV1[12]).equalsIgnoreCase("<IGNORE>")){Mdl_Variables.driver.findElement(By.name("B3anDire2")).clear();
				    Mdl_Variables.driver.findElement(By.name("B3anDire2")).sendKeys(Web_Framework.GetValue(arrAV1[12])); //dt_TXT_DIRECCIONBANCO2
				}

				if(!Web_Framework.GetValue(arrAV1[13]).equalsIgnoreCase("<IGNORE>")){Mdl_Variables.driver.findElement(By.name("C3odSwift")).clear();
				    Mdl_Variables.driver.findElement(By.name("C3odSwift")).sendKeys(Web_Framework.GetValue(arrAV1[13])); //dt_TXT_CODIGO
				}
			    
			    Thread.sleep(2000);
				Mdl_Evidenciamiento.Evidencia_Log(true, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();

				if(Web_Framework.GetValue(arrAV1[14]).equalsIgnoreCase("on")){ //dt_CHK_SELECC
					Mdl_Variables.driver.findElement(By.id("opcional")).click();
				}

				Thread.sleep(2000);

				if(!Web_Framework.GetValue(arrAV1[15]).equalsIgnoreCase("<IGNORE>")){Mdl_Variables.driver.findElement(By.name("c2onBanNombre")).clear();
				    Mdl_Variables.driver.findElement(By.name("c2onBanNombre")).sendKeys(Web_Framework.GetValue(arrAV1[15])); //dt_TXT_BANCOINTERMEDIARIO
				}

				if(!Web_Framework.GetValue(arrAV1[16]).equalsIgnoreCase("<IGNORE>")){Mdl_Variables.driver.findElement(By.name("c3onBanDire1")).clear();
				    Mdl_Variables.driver.findElement(By.name("c3onBanDire1")).sendKeys(Web_Framework.GetValue(arrAV1[16])); //dt_TXT_DIRINTERM
				}

				if(!Web_Framework.GetValue(arrAV1[17]).equalsIgnoreCase("<IGNORE>")){Mdl_Variables.driver.findElement(By.name("c3onBanDire2")).clear();
				    Mdl_Variables.driver.findElement(By.name("c3onBanDire2")).sendKeys(Web_Framework.GetValue(arrAV1[17])); //dt_TXT_DIRINTERM
				}

				if(!Web_Framework.GetValue(arrAV1[18]).equalsIgnoreCase("<IGNORE>")){Mdl_Variables.driver.findElement(By.name("c3onCodSwift")).clear();
				    Mdl_Variables.driver.findElement(By.name("c3onCodSwift")).sendKeys(Web_Framework.GetValue(arrAV1[18])); //dt_TXT_CODIGOINTERM
				}

			    Thread.sleep(2000);
				Mdl_Evidenciamiento.Evidencia_Log(true, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
				Mdl_Variables.driver.findElement(By.name("Submit")).click();
				Thread.sleep(2000);
				Mdl_Variables.driver.findElement(By.id("ClaveSegundoFactor")).clear();
				Mdl_Variables.driver.findElement(By.id("ClaveSegundoFactor")).sendKeys("123456");
		    	Thread.sleep(2000);
				Mdl_Evidenciamiento.Evidencia_Log(true, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
			    Mdl_Variables.driver.findElement(By.name("Submit")).click();
			    Thread.sleep(2000);
			    bajar_pagina_flecha("5");
			    Thread.sleep(2000);
			    Mdl_Evidenciamiento.Evidencia_Log(true, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();

			    //Recargamos el DDriven
	            Mdl_Variables.Rst_Tabla.refreshRow();
	            Mdl_Variables.driver.quit();
			}
		}catch(Throwable e){System.out.println("Error en \"bnetactual_exterior()\" no encontró el objeto: " + e.getMessage());
			Mdl_Evidenciamiento.Evidencia_Log(false, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			Mdl_Variables.boolAction = 0;
            Mdl_Variables.driver.quit();
		}
	}

	/**
     * 
     * @author Romer Vargas Otiniano
     * @version 1.0
     * @date 16/05/2016
     */
	public static void bnetactual_datos_personales(String strAV1, String strAV2) throws SQLException, InterruptedException{
		String[] arrAV1 = strAV1.split("\\|");
		String[] arrAV2 = strAV2.split("\\|");
		boolean procede = false;

		String strPrefijo = Web_Framework.GetValue(arrAV1[0]); //dt_TXT_PREFIJO
		String strTelefono = Web_Framework.GetValue(arrAV1[1]); //dt_TXT_TELEFONO
		String strCelular = Web_Framework.GetValue(arrAV1[2]); //dt_TXT_CELULAR
		String strMail = Web_Framework.GetValue(arrAV1[3]); //dt_TXT_EMAIL
		String strTipoVia = Web_Framework.GetValue(arrAV1[4]); //dt_TXT_TIPOVIA_COMBOBOX
		String strViaNombre = Web_Framework.GetValue(arrAV1[5]); //dt_TXT_VIA_NOMBRE
		String strViaNumero = Web_Framework.GetValue(arrAV1[6]); //dt_TXT_VIA_NUMERO
		String strViaDepartamento = Web_Framework.GetValue(arrAV1[7]); //dt_TXT_VIA_DEPARTAMENTO
		String strTipoZona = Web_Framework.GetValue(arrAV1[8]); //dt_TXT_TIPOZONA_COMBOBOX
		String strUrbanizacion = Web_Framework.GetValue(arrAV1[9]); //dt_TXT_URBANIZACION
		String strReferencia = Web_Framework.GetValue(arrAV1[10]); //dt_TXT_REFERENCIA
		String strDepartamento = Web_Framework.GetValue(arrAV1[11]); //dt_TXT_DEPARTAMENTO_COMBOBOX
		String strProvincia = Web_Framework.GetValue(arrAV1[12]); //dt_TXT_PROVINCIA_COMBOBOX
		String strDistrito = Web_Framework.GetValue(arrAV1[13]); //dt_TXT_DISTRITO_COMBOBOX

		String strModifCelu = Web_Framework.GetValue(arrAV2[0]); //dt_LNK_MODIFICAR_CELULAR
		String strModifFijo = Web_Framework.GetValue(arrAV2[1]); //dt_LNK_MODIFICAR_FIJO
		String strModifMail = Web_Framework.GetValue(arrAV2[2]); //dt_LNK_MODIFICAR_EMAIL
		String strModifDire = Web_Framework.GetValue(arrAV2[3]); //dt_LNK_MODIFICAR_DIRECCION

		try{
			if(Web_Framework.GetValue("dt_KWD_BNET_ACTUAL").equals("TRUE")){
				Web_Framework.launchapp(Mdl_Variables.Tipo_Aplicativo);
				Thread.sleep(4000);
				Mdl_Keyboard.doType(KeyEvent.VK_ENTER);
				Thread.sleep(5000);

				Mdl_Choucair.getDatosLogin();
				Mdl_Variables.driver.get(Mdl_Variables.P_Str_RutaAmbienteSecundario);
				Mdl_Variables.driver.findElement(By.id("initial_input")).clear();
				Mdl_Variables.driver.findElement(By.id("initial_input")).sendKeys(Mdl_Variables.Str_DatosUsuario[3]);
				Mdl_Variables.driver.findElement(By.name("eai_password")).clear();
				Mdl_Variables.driver.findElement(By.name("eai_password")).sendKeys(Mdl_Variables.Str_DatosUsuario[10]); //"gestion99"
				Thread.sleep(2000);
				Mdl_Evidenciamiento.Evidencia_Log(true, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
				Mdl_Variables.driver.findElement(By.cssSelector("a.c1 > img")).click();
				Thread.sleep(2000);
				Mdl_Variables.driver.findElement(By.id("sec_mi_perfil")).click();
				Thread.sleep(3500);
				bajarPaginaFlecha("11");
				Mdl_Variables.driver.switchTo().frame(0);

				if(strModifDire.equalsIgnoreCase("click")){
					if(!strTipoVia.equalsIgnoreCase("<IGNORE>") || !strViaNombre.equalsIgnoreCase("<IGNORE>") || !strViaNumero.equalsIgnoreCase("<IGNORE>") || !strViaDepartamento.equalsIgnoreCase("<IGNORE>") || !strTipoZona.equalsIgnoreCase("<IGNORE>") || !strUrbanizacion.equalsIgnoreCase("<IGNORE>") || !strReferencia.equalsIgnoreCase("<IGNORE>") || !strDepartamento.equalsIgnoreCase("<IGNORE>") || !strProvincia.equalsIgnoreCase("<IGNORE>") || !strDistrito.equalsIgnoreCase("<IGNORE>")){
						new Select(Mdl_Variables.driver.findElement(By.id("tipoVia"))).selectByVisibleText(strTipoVia);
						Mdl_Variables.driver.findElement(By.id("direccion")).clear();
					    Mdl_Variables.driver.findElement(By.id("direccion")).sendKeys(strViaNombre);
					    Mdl_Variables.driver.findElement(By.id("numero")).clear();
					    Mdl_Variables.driver.findElement(By.id("numero")).sendKeys(strViaNumero);
					    Mdl_Variables.driver.findElement(By.id("numDepa")).clear();
					    Mdl_Variables.driver.findElement(By.id("numDepa")).sendKeys(strViaDepartamento);
					    new Select(Mdl_Variables.driver.findElement(By.id("tipoZona"))).selectByVisibleText(strTipoZona);
					    Mdl_Variables.driver.findElement(By.id("nombreZona")).clear();
					    Mdl_Variables.driver.findElement(By.id("nombreZona")).sendKeys(strUrbanizacion);
					    Mdl_Variables.driver.findElement(By.id("sReferenciaDomic1")).clear();
					    Mdl_Variables.driver.findElement(By.id("sReferenciaDomic1")).sendKeys(strReferencia);
					    new Select(Mdl_Variables.driver.findElement(By.id("departamento"))).selectByVisibleText(strDepartamento);
					    Thread.sleep(1500);
					    new Select(Mdl_Variables.driver.findElement(By.id("provincia"))).selectByVisibleText(strProvincia);
					    Thread.sleep(1500);
					    new Select(Mdl_Variables.driver.findElement(By.id("distrito"))).selectByVisibleText(strDistrito);
					    procede = true;
					}
				}

				if(strModifFijo.equalsIgnoreCase("click")){
					if(!strPrefijo.equalsIgnoreCase("<IGNORE>") || !strTelefono.equalsIgnoreCase("<IGNORE>")){
						Mdl_Variables.driver.findElement(By.id("prefTelefono1")).clear();
					    Mdl_Variables.driver.findElement(By.id("prefTelefono1")).sendKeys(strPrefijo);
					    Mdl_Variables.driver.findElement(By.id("numTelefono1D")).clear();
					    Mdl_Variables.driver.findElement(By.id("numTelefono1D")).sendKeys(strTelefono);
					    procede = true;
					}
				}

				if(strModifCelu.equalsIgnoreCase("click")){
					if(!strCelular.equalsIgnoreCase("<IGNORE>")){
						Mdl_Variables.driver.findElement(By.id("numTelefono2C")).clear();
						Mdl_Variables.driver.findElement(By.id("numTelefono2C")).sendKeys(strCelular);
						procede = true;
				    }
				}

				if(strModifMail.equalsIgnoreCase("click")){
					if(!strMail.equalsIgnoreCase("<IGNORE>")){
						Mdl_Variables.driver.findElement(By.id("email")).clear();
					    Mdl_Variables.driver.findElement(By.id("email")).sendKeys(strMail);
					    procede = true;
					}
				}

				Thread.sleep(1500);

				if(procede){
					Mdl_Variables.driver.findElement(By.id("indAutorizachk")).click();
					Thread.sleep(1000);
				}

				Mdl_Evidenciamiento.Evidencia_Log(true, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();

				if(procede){
					Thread.sleep(2000);
					Mdl_Variables.driver.findElement(By.id("btAceptar")).click();
					Thread.sleep(2000);

					Mdl_Variables.driver.findElement(By.id("ClaveSegundoFactor")).clear();
				    Mdl_Variables.driver.findElement(By.id("ClaveSegundoFactor")).sendKeys("123456");
			    	Thread.sleep(2000);
					Mdl_Evidenciamiento.Evidencia_Log(true, "");
					Mdl_Evidenciamiento.Ingreso_Datos_Log();
					Mdl_Variables.driver.findElement(By.id("btAceptar")).click();
				    Thread.sleep(2000);
					Mdl_Evidenciamiento.Evidencia_Log(true, "");
					Mdl_Evidenciamiento.Ingreso_Datos_Log();
				}

			    //Recargamos el DDriven
	            Mdl_Variables.Rst_Tabla.refreshRow();
	            Mdl_Variables.driver.quit();
			}
		}catch(Throwable e){System.out.println("Error en \"bnetactual_datos_personales()\" no encontró el objeto: " + e.getMessage());
			Mdl_Evidenciamiento.Evidencia_Log(false, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			Mdl_Variables.boolAction = 0;
            Mdl_Variables.driver.quit();
		}
	}

	/** 
     * @author Romer Vargas
	 * @version 1.0
	 * @date 27052016
     * */
    public static void busqueda_tarjeta_credito(){
		String findingTag = "";
		boolean flag = false;
		int i = 2;

		try{
			if(!Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
				while(i <= 15 && !flag){findingTag = "//div[@id='wizard-slide-viewer']/div/div/div/article/div/table/tbody/tr[" + i + "]/td[2]/span";
					if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath(findingTag)).getText())){
						Mdl_Variables.driver.findElement(By.xpath(findingTag)).click();

						Mdl_Variables.strPasosEvidencia = "Seleccionar el número de tarjeta de crédito " + Mdl_Variables.arrKeyValue[0];
						Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);

						Mdl_Evidenciamiento.Evidencia_Log(true, "");
						Mdl_Evidenciamiento.Ingreso_Datos_Log();
						flag = true;
					} i++;
				} Thread.sleep(1000);
			}
		}catch(Exception e){System.out.println("Error en \"busqueda_tarjeta_credito()\" al seleccionar: " + e.getMessage());}
	}

    /**
     * @author Romer Vargas
	 * @version 1.0
	 * @date 17052016
     * */
    public static void seleccion_tarjeta_credito_fancy() throws SQLException{
		String findingTag = "";
		boolean flag = false;
		String card = "";
		int i = 2;

		try{
			Mdl_Variables.driver.findElement(By.linkText("Detalle de compras en cuotas")).click(); //div[@id='section1']/div[2]/aside[3]/ul/li/a
			Thread.sleep(3000);

			if(!Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("0101010101010101") && !Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
				while(i <= 15 && !flag){findingTag = "//div[@id='wizard-slide-viewer']/div/div/div/article/div/table/tbody/tr[" + i + "]/td[2]/span";
					if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath(findingTag)).getText())){
						Mdl_Variables.driver.findElement(By.xpath(findingTag)).click();
						card = Mdl_Variables.arrKeyValue[0];
	
						/*Mdl_Variables.strPasosEvidencia = "Seleccionar el número de tarjeta de crédito " + Mdl_Variables.arrKeyValue[0];
						Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
	
						Thread.sleep(3000);
						Mdl_Evidenciamiento.Evidencia_Log(true, "");
						Mdl_Evidenciamiento.Ingreso_Datos_Log();*/
						flag = true;
					} i++;
				}
			}

			Mdl_Variables.strPasosEvidencia = "Seleccionar el número de tarjeta de crédito " + card;
			Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
			Thread.sleep(1000);

			Mdl_Evidenciamiento.Evidencia_Log(true, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
		}catch(Exception e){System.out.println("Error en \"seleccion_tarjeta_credito_fancy()\" al seleccionar: " + e.getMessage());
		}finally{
			Mdl_Variables.strPasosEvidencia = "No se pudo seleccionar el número de cuenta " + Mdl_Variables.arrKeyValue[0];
			Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
			Mdl_Evidenciamiento.Evidencia_Log(true, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
		}
	}

    /**
     * 
     * @author Romer Vargas Otiniano
     * @version 1.0
     * @date 17/05/2016
     */
	public static void bnetactual_tarjetas_consultas_compras(String strAV1) throws SQLException, InterruptedException{
		String[] arrAV1 = strAV1.split("\\|");

		String strNumeroTarjeta = Web_Framework.GetValue(arrAV1[0]); //dt_LBL_NUMERO_TARJETA
		//String strNumeroTarjetaFancy = Web_Framework.GetValue(arrAV1[1]); //dt_LBL_NUMERO_TARJETA_FANCY

		try{
			/*Toolkit toolkit = Toolkit.getDefaultToolkit();
		    Dimension screenResolution = new Dimension((int) 
		                        toolkit.getScreenSize().getWidth(), (int) 
		                        toolkit.getScreenSize().getHeight());
	    	System.out.println("Height: " + screenResolution.getHeight() + "\nWidth: " + screenResolution.getWidth());*/
		    
			if(Web_Framework.GetValue("dt_KWD_BNET_ACTUAL").equals("TRUE")){
				Web_Framework.launchapp(Mdl_Variables.Tipo_Aplicativo);
				Thread.sleep(4000);
				Mdl_Keyboard.doType(KeyEvent.VK_ENTER);
				Thread.sleep(5000);

				Mdl_Choucair.getDatosLogin();
				Mdl_Variables.driver.get(Mdl_Variables.P_Str_RutaAmbienteSecundario);
				Mdl_Variables.driver.findElement(By.id("initial_input")).clear();
				Mdl_Variables.driver.findElement(By.id("initial_input")).sendKeys(Mdl_Variables.Str_DatosUsuario[3]);
				Mdl_Variables.driver.findElement(By.name("eai_password")).clear();
				Mdl_Variables.driver.findElement(By.name("eai_password")).sendKeys(Mdl_Variables.Str_DatosUsuario[10]); //"gestion99"
				Thread.sleep(2000);
				Mdl_Evidenciamiento.Evidencia_Log(true, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
				Mdl_Variables.driver.findElement(By.cssSelector("a.c1 > img")).click();
				Thread.sleep(3000);
				Mdl_Variables.driver.findElement(By.id("sec_mis_tarjetas")).click();
				Thread.sleep(3500);
				bajarPaginaFlecha("9");
				Thread.sleep(2000);

				Mdl_Variables.driver.findElement(By.linkText("Operaciones en Cuotas")).click();
				Thread.sleep(1000);
				Mdl_Variables.driver.findElement(By.linkText("Consulta tus Compras")).click();
				Thread.sleep(2000);
				selectByPartOfVisibleText("id", "AsuntoPropio", strNumeroTarjeta, true, true);

				if(!strNumeroTarjeta.equalsIgnoreCase("<IGNORE>") && !strNumeroTarjeta.equalsIgnoreCase("0101010101010101")){
					Mdl_Variables.driver.findElement(By.name("Submit")).click();
					Thread.sleep(2000);
					Mdl_Bnet.helperEvidencia_PantallaCompleta(6);

					/*JavascriptExecutor executor = (JavascriptExecutor) Mdl_Variables.driver;
					Long value = (Long) executor.executeScript("return window.scrollY;");
					System.out.println("El valor es: " + value);
					value = (Long) executor.executeScript("return Math.max( document.body.scrollHeight, document.body.offsetHeight, document.documentElement.clientHeight, document.documentElement.scrollHeight, document.documentElement.offsetHeight );");
					System.out.println("El valor es: " + value);*/
				}

			    //Recargamos el DDriven
	            Mdl_Variables.Rst_Tabla.refreshRow();
	            Mdl_Variables.driver.quit();
			}
		}catch(Throwable e){System.out.println("Error en \"bnetactual_tarjetas_consultas_compras()\" no encontró el objeto: " + e.getMessage());
			Mdl_Evidenciamiento.Evidencia_Log(false, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			Mdl_Variables.boolAction = 0;
            Mdl_Variables.driver.quit();
		}
	}

	/**
	 * 
	 * @author Romer Vargas Otiniano
	 * @version 1.0
	 * @date 19/05/2016
	 */
	public static void helperEvidencia_PantallaCompleta(int loops){
		try{
			Robot robot = new Robot();

			for(int i = 1; i <= loops; i++){
				Mdl_Evidenciamiento.Evidencia_Log(true, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
				robot.keyPress(KeyEvent.VK_PAGE_DOWN);
				robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
				Thread.sleep(1000);
			}
		}catch(Exception ex){System.out.println("Error en \"helperEvidencia_PantallaCompleta()\": " + ex.getMessage());}
	}

	/**
	 * 
	 * @author Romer Vargas Otiniano
	 * @version 1.0
	 * @date 18/05/2016
	 */
	public static void seleccionar_cuotas_pagadas(String strCampos){
		String aux = new String();
		boolean flag = false;

		try{
			if(!Web_Framework.GetValue(strCampos).equalsIgnoreCase("<IGNORE>")){
				aux = Web_Framework.GetValue(strCampos).trim();
			}else{flag = true;}

			if(!flag){
				String[] arrValores = aux.split("\\:");
				String findingTag = "";

				for(int i = 0; i < arrValores.length; i++){
					int j = 1;
					flag = false;

					while(j <= 20 && !flag){findingTag = "(//tr[@id='trComprasCuotas']/td[3]/strong)[" + j + "]";
						if(arrValores[i].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath(findingTag)).getText())){
							Mdl_Variables.driver.findElement(By.xpath(findingTag)).click();
							Thread.sleep(3000);
							Mdl_Evidenciamiento.Evidencia_Log(true, "");
							Mdl_Evidenciamiento.Ingreso_Datos_Log();
							Mdl_Variables.strPasosEvidencia = "Seleccionar la cuota: " + Mdl_Variables.arrKeyValue[0];
							Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
							flag = true;
							Thread.sleep(1000);
							Mdl_Variables.driver.findElement(By.xpath("//div[@id='movimientoDetalleCompras']/header/div/div/span/a/span[2]")).click();	
						} j++;
					}
				}
			}
		}catch(Throwable e){System.out.println("Error en \"seleccionar_cuotas_pagadas()\" al seleccionar: " + e.getMessage());}
	}

	/**
	 * 
	 * @author Romer Vargas Otiniano
	 * @version 1.0
	 * @date 23/05/2016
	 */
	public static void bnetactual_tarjetas_simular_cuotas(String strAV1) throws SQLException, InterruptedException{
		String[] arrAV1 = strAV1.split("\\|");

		String strNumeroTarjeta = Web_Framework.GetValue(arrAV1[0]); //dt_LBL_NUMERO_TARJETA
		String strTipoSimulacion = Web_Framework.GetValue(arrAV1[1]); //dt_LBL_TIPO_SIMULACION
		String strMoneda = Web_Framework.GetValue(arrAV1[4]); //dt_LBL_MONEDA
		String strNumeroCuotas = Web_Framework.GetValue(arrAV1[5]); //dt_TXT_NUMERO_CUOTAS
		String strImporte;

		if(strMoneda.equalsIgnoreCase("Soles")){strImporte = Web_Framework.GetValue(arrAV1[2]);}//dt_TXT_IMPORTE 
		else{strImporte = Web_Framework.GetValue(arrAV1[3]);}//dt_TXT_IMPORTE_DOLARES

		try{
			if(Web_Framework.GetValue("dt_KWD_BNET_ACTUAL").equals("TRUE")){
				Web_Framework.launchapp(Mdl_Variables.Tipo_Aplicativo);
				Thread.sleep(4000);
				Mdl_Keyboard.doType(KeyEvent.VK_ENTER);
				Thread.sleep(5000);

				Mdl_Choucair.getDatosLogin();
				Mdl_Variables.driver.get(Mdl_Variables.P_Str_RutaAmbienteSecundario);
				Mdl_Variables.driver.findElement(By.id("initial_input")).clear();
				Mdl_Variables.driver.findElement(By.id("initial_input")).sendKeys(Mdl_Variables.Str_DatosUsuario[3]);
				Mdl_Variables.driver.findElement(By.name("eai_password")).clear();
				Mdl_Variables.driver.findElement(By.name("eai_password")).sendKeys(Mdl_Variables.Str_DatosUsuario[10]); //"gestion99"
				Thread.sleep(2000);
				Mdl_Evidenciamiento.Evidencia_Log(true, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
				Mdl_Variables.driver.findElement(By.cssSelector("a.c1 > img")).click();
				Thread.sleep(3000);
				Mdl_Variables.driver.findElement(By.id("sec_mis_tarjetas")).click();
				Thread.sleep(3500);
				bajarPaginaFlecha("9");
				Thread.sleep(2000);

				Mdl_Variables.driver.findElement(By.linkText("Operaciones en Cuotas")).click();
				Thread.sleep(1000);
				Mdl_Variables.driver.findElement(By.linkText("Simula tus Cuotas")).click();
				Thread.sleep(2000);

				selectByPartOfVisibleText("id", "AsuntoPropio", strNumeroTarjeta, true, true);
				Thread.sleep(1000);

				if(!strTipoSimulacion.equalsIgnoreCase("<IGNORE>") && !strImporte.equalsIgnoreCase("<IGNORE>") && !strMoneda.equalsIgnoreCase("<IGNORE>")){
					if(strTipoSimulacion.equalsIgnoreCase("Compras en Cuotas")){strTipoSimulacion = "Compra Cuotas";}
					else if(strTipoSimulacion.equalsIgnoreCase("Compras en Cuotas")){strTipoSimulacion = "Avance Cuotas";}
					else{strTipoSimulacion = "Efectivo Cuotas";}

					List<WebElement> optionElements = Mdl_Variables.driver.findElement(By.name("TipoSimulacion")).findElements(By.tagName("option"));
					for(WebElement optionElement : optionElements){
					    if(optionElement.getText().toLowerCase().contains(strTipoSimulacion.toLowerCase())){
					        String optionIndex = optionElement.getAttribute("index");
					        int intOptionIndex = Integer.parseInt(optionIndex);
					        new Select(Mdl_Variables.driver.findElement(By.name("TipoSimulacion"))).selectByIndex(intOptionIndex);
					    }
					}

					Thread.sleep(1000);
					Mdl_Variables.driver.findElement(By.name("importe")).clear();
					Mdl_Variables.driver.findElement(By.name("importe")).sendKeys(strImporte);
					Thread.sleep(1000);
					optionElements = Mdl_Variables.driver.findElement(By.name("moneda")).findElements(By.tagName("option"));
					for(WebElement optionElement : optionElements){
					    if(optionElement.getText().toLowerCase().contains(strMoneda.toLowerCase())){
					        String optionIndex = optionElement.getAttribute("index");
					        int intOptionIndex = Integer.parseInt(optionIndex);
					        new Select(Mdl_Variables.driver.findElement(By.name("moneda"))).selectByIndex(intOptionIndex);
					    }
					}

					Thread.sleep(1000);
					Mdl_Evidenciamiento.Evidencia_Log(true, "");
					Mdl_Evidenciamiento.Ingreso_Datos_Log();
					Mdl_Variables.driver.findElement(By.name("button")).click();
					Thread.sleep(4000);
					Mdl_Variables.driver.findElement(By.id("nroCuotas")).clear();
					Mdl_Variables.driver.findElement(By.id("nroCuotas")).sendKeys(strNumeroCuotas);
					Thread.sleep(1000);
					Mdl_Evidenciamiento.Evidencia_Log(true, "");
					Mdl_Evidenciamiento.Ingreso_Datos_Log();
					Mdl_Variables.driver.findElement(By.name("consultar")).click();
					Thread.sleep(2000);
					Mdl_Evidenciamiento.Evidencia_Log(true, "");
					Mdl_Evidenciamiento.Ingreso_Datos_Log();
					Robot robot = new Robot();
					robot.keyPress(KeyEvent.VK_PAGE_DOWN);
					robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
					Thread.sleep(1000);
					Mdl_Evidenciamiento.Evidencia_Log(true, "");
					Mdl_Evidenciamiento.Ingreso_Datos_Log();
				}

	            Mdl_Variables.driver.quit();
			}
		}catch(Throwable e){System.out.println("Error en \"bnetactual_tarjetas_simular_cuotas()\" no encontró el objeto: " + e.getMessage());
			Mdl_Evidenciamiento.Evidencia_Log(false, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			Mdl_Variables.boolAction = 0;
	        Mdl_Variables.driver.quit();
        }
	}

	/** 
     * @author Romer Vargas
	 * @version 1.0
	 * @throws SQLException 
	 * @date 23052016
     */
    public static void simular_cuotas_fancy() throws SQLException{
		String findingTag = "";
		boolean flag = false;
		String card = "";
		int i = 2;

		try{
			Mdl_Variables.driver.findElement(By.xpath("//div[@id='section1']/div[2]/aside[2]/ul/li[2]/a")).click(); //div[@id='section1']/div[2]/aside[3]/ul/li[2]/a //div[@id='section1']/div[2]/aside[2]/ul/li[3]/a
			Thread.sleep(3000);

			if(!Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("0101010101010101") && !Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
				while(i <= 15 && !flag){findingTag = "//div[@id='wizard-slide-viewer']/div/div/div/article/div[2]/table/tbody/tr[" + i + "]/td[2]/span";
					if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath(findingTag)).getText())){
						Mdl_Variables.driver.findElement(By.xpath(findingTag)).click();
						card = Mdl_Variables.arrKeyValue[0];
						
						/*Mdl_Variables.strPasosEvidencia = "Seleccionar el número de tarjeta de crédito " + Mdl_Variables.arrKeyValue[0];
						Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
	
						Mdl_Evidenciamiento.Evidencia_Log(true, "");
						Mdl_Evidenciamiento.Ingreso_Datos_Log();*/
						flag = true;
					} i++;
				}
			}

			Mdl_Variables.strPasosEvidencia = "Seleccionar el número de tarjeta de crédito " + card;
			Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
			Thread.sleep(1000);

			Mdl_Evidenciamiento.Evidencia_Log(true, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
		}catch(Exception e){System.out.println("Error en \"simular_cuotas_fancy()\" al seleccionar: " + e.getMessage());
		}finally{
			Mdl_Variables.strPasosEvidencia = "No se pudo seleccionar el número de cuenta " + Mdl_Variables.arrKeyValue[0];
			Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
			Mdl_Evidenciamiento.Evidencia_Log(true, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
		}
	}

    /**
     * @author Romer Vargas
	 * @version 1.0
	 * @date 23052016
     */
    public static void seleccionar_tipo_simulacion(){
    	String findingTag = "";
		boolean flag = false;
		int i = 1;

		try{
			Mdl_Variables.driver.findElement(By.xpath("(//button[@type='button'])[29]")).click();//(//button[@type='button'])[12] //(//button[@type='button'])[7]
			Thread.sleep(3000);

			while(i <= 3 && !flag){findingTag = "//div[6]/div/ul/li[" + i + "]/a";
				if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath(findingTag)).getText())){
					Mdl_Variables.driver.findElement(By.xpath(findingTag)).click();

					Mdl_Variables.strPasosEvidencia = "Seleccionar el tipo de simulación " + Mdl_Variables.arrKeyValue[0];
					Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
					flag = true;
				} i++;
			} Thread.sleep(1000);
		}catch(Exception e){System.out.println("Error en \"seleccionar_tipo_simulacion()\" al seleccionar: " + e.getMessage());}
    }

    /**
     * @author Romer Vargas
	 * @version 1.0
	 * @date 30052016
     */
    public static void bnetactual_envio_efectivo_movil(){
    	try{
    		if(Web_Framework.GetValue("dt_KWD_BNET_ACTUAL").equals("TRUE")){}
    		Thread.sleep(10000);
    	}catch(Exception e){System.out.println("Error en \"bnetactual_envio_efectivo_movil()\" no encontró el objeto: " + e.getMessage());
			Mdl_Evidenciamiento.Evidencia_Log(false, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			Mdl_Variables.boolAction = 0;
	        Mdl_Variables.driver.quit();
    	}
    }

    /**
     * @author Romer Vargas
	 * @version 1.0
	 * @date 17062016
     */
    public static void seleccionar_tarjeta_telefonica(String strAV1){
    	String findingTag = "";
		boolean flag = false;
		int i = 2;

		try{
			Mdl_Variables.driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
			Thread.sleep(2000);

			while(i <= 2 && !flag){findingTag = "//div[4]/div/ul/li[" + i + "]/a";
				if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath(findingTag)).getText())){
					Mdl_Variables.driver.findElement(By.xpath(findingTag)).click();

					Mdl_Variables.strPasosEvidencia = "Seleccionar el tipo de simulación " + Mdl_Variables.arrKeyValue[0];
					Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
					flag = true;
				} i++;
			} Thread.sleep(1000);
		}catch(Exception e){System.out.println("Error en \"seleccionar_tarjeta_telefonica()\" al seleccionar: " + e.getMessage());}
    }

    /**
     * @author Romer Vargas
	 * @version 1.0
	 * @date 17062016
    */
    public static void seleccionar_importe_tarjeta_telefonica(){
    	String findingTag = "";
		boolean flag = false;
		int i = 2;

		try{
			Mdl_Variables.driver.findElement(By.xpath("(//button[@type='button'])[5]")).click();
			Thread.sleep(2000);

			while(i <= 6 && !flag){findingTag = "//div[5]/div/ul/li[" + i + "]/a";
				if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath(findingTag)).getText())){
					Mdl_Variables.driver.findElement(By.xpath(findingTag)).click();

					Mdl_Variables.strPasosEvidencia = "Seleccionar el tipo de simulación " + Mdl_Variables.arrKeyValue[0];
					Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
					flag = true;
				} i++;
			} Thread.sleep(1000);
		}catch(Exception e){System.out.println("Error en \"seleccionar_importe_tarjeta_telefonica()\" al seleccionar: " + e.getMessage());}
    }

    /**
     * @author Romer Vargas
	 * @version 1.0
	 * @date 20062016
    */
    public static void pagotarjeta(){
    	try{
            Mdl_Variables.driver.findElement(By.xpath("//div[@id='operaciones-list-container']/div[3]/div/ul/li[2]/a/strong")).click();
            Mdl_Variables.strPasosEvidencia = "Seleccionar pago de tarjeta de credito.";
            Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
            Thread.sleep(2000);
        }catch(Throwable e){System.out.println("Error en \"pagotarjeta()\" al seleccionar: " + e.getMessage());}
    }

    /**
     * @author Romer Vargas
	 * @version 1.0
	 * @date 20062016
    */
    public static void seleccionar_importe(String strAV1){
    	try{
    		String[] arrAV1 = strAV1.split("\\|");
    		String helper = Web_Framework.GetValue(arrAV1[0]);
    		String btnDolares = Web_Framework.GetValue(arrAV1[1]);

    		if(helper.equalsIgnoreCase("<IGNORE>")){
    			//
            }else{
            	String prefix = "solesPagoTarjetaPropiaTerceros";
            	String findingTag = "";
            	int i = 1 ;

            	if(btnDolares.equals("click")){prefix = "dolaresPagoTarjetaPropiaTerceros";}

                while(i <= 3){findingTag = "//div[@id='" + prefix + "']/div[2]/div/fieldset/div[" + i + "]/label";
                	if(helper.equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath(findingTag)).getText())){
                        Mdl_Variables.driver.findElement(By.xpath(findingTag)).click();
                        Mdl_Variables.strPasosEvidencia = "Seleccionar el importe: " + helper;

                        Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
                    } i++;
                } Thread.sleep(2000);
            }
        }catch(Exception e){System.out.println("Error en \"seleccionar_importe()\" al seleccionar: " + e.getMessage());}                        
    }

    /**
     * 
     * @author Romer Vargas Otiniano
     * @version 1.0
     * @date 22062016
     */
	public static void bnetactual_pago_tarjetas_propias(String strAV1) throws SQLException{
		String[] arrAV1 = strAV1.split("\\|");

		String strTxtNumTarjeta = Web_Framework.GetValue(arrAV1[0]); //dt_TXT_NUMTARJETA
		String strTxtOpcion = Web_Framework.GetValue(arrAV1[1]); //dt_TXT_OPCION
		String strTxtImporteSoles = Web_Framework.GetValue(arrAV1[2]); //dt_TXT_IMPORTESOLES
		String strTxtImporteDolares = Web_Framework.GetValue(arrAV1[3]); //dt_TXT_IMPORTEDOLARES
		String strBtnSgte1 = Web_Framework.GetValue(arrAV1[4]); //dt_BTN_SGTE1
		String strBtnSgte2 = Web_Framework.GetValue(arrAV1[5]); //dt_BTN_SGTE2

		try{
			if(Web_Framework.GetValue("dt_KWD_BNET_ACTUAL").equals("TRUE")){
				Web_Framework.launchapp(Mdl_Variables.Tipo_Aplicativo);
				Mdl_Choucair.getDatosLogin();

				Mdl_Variables.driver.get(Mdl_Variables.P_Str_RutaAmbienteSecundario);
				Mdl_Variables.driver.findElement(By.id("initial_input")).clear();
				Mdl_Variables.driver.findElement(By.id("initial_input")).sendKeys(Mdl_Variables.Str_DatosUsuario[3]);
				Mdl_Variables.driver.findElement(By.name("eai_password")).clear();
				Mdl_Variables.driver.findElement(By.name("eai_password")).sendKeys(Mdl_Variables.Str_DatosUsuario[10]);
				Thread.sleep(2000);
				Mdl_Evidenciamiento.Evidencia_Log(true, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
				Mdl_Variables.driver.findElement(By.cssSelector("a.c1 > img")).click();
				Thread.sleep(5000);
				Mdl_Evidenciamiento.Evidencia_Log(true, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
				Mdl_Variables.driver.findElement(By.id("sec_mis_tarjetas")).click();
				Thread.sleep(2000);
				bajarPaginaFlecha("5");
				Mdl_Variables.driver.findElement(By.linkText("Paga Tarj. Bco Continental")).click();
				Thread.sleep(3000);
				selectByPartOfVisibleText("id", "AsuntoPropio", strTxtOpcion, true, true);
				Thread.sleep(2000);
				Mdl_Variables.driver.findElement(By.name("Submit")).click();
				Thread.sleep(2000);
				//subirPaginaFlecha("3");
				selectByPartOfVisibleText("id", "AsuntoPropio", strTxtNumTarjeta, false, true);

				if(!strBtnSgte1.equalsIgnoreCase("<IGNORE>")){
					if(!strTxtImporteDolares.equalsIgnoreCase("<IGNORE>")){
						Mdl_Variables.driver.findElement(By.id("tipoMoneda_2")).click();
						Thread.sleep(1000);
						Mdl_Variables.driver.findElement(By.name("ImporteDolares")).sendKeys(strTxtImporteDolares);
					}else{Mdl_Variables.driver.findElement(By.name("ImporteSoles")).sendKeys(strTxtImporteSoles);}

					Thread.sleep(1000);
					Mdl_Evidenciamiento.Evidencia_Log(true, "");
					Mdl_Evidenciamiento.Ingreso_Datos_Log();
					Mdl_Variables.driver.findElement(By.name("Submit")).click();
					Thread.sleep(2000);
					bajarPaginaFlecha("1");
					Mdl_Evidenciamiento.Evidencia_Log(true, "");
					Mdl_Evidenciamiento.Ingreso_Datos_Log();

					if(!strBtnSgte2.equalsIgnoreCase("<IGNORE>")){
						Mdl_Variables.driver.findElement(By.name("Submit")).click();
						Thread.sleep(2000);
						bajarPaginaFlecha("6");
						Mdl_Evidenciamiento.Evidencia_Log(true, "");
						Mdl_Evidenciamiento.Ingreso_Datos_Log();
					}
				}

				//Recargamos el DDriven
	            Mdl_Variables.Rst_Tabla.refreshRow();
	            Mdl_Variables.driver.quit();
			}
		}catch(Throwable e){System.out.println("Error en \"bnetactual_pago_tarjetas_propias()\" no encontró el objeto: " + e.getMessage());
			Mdl_Evidenciamiento.Evidencia_Log(false, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			Mdl_Variables.boolAction = 0;
            Mdl_Variables.driver.quit();
		}
	}

	/**
     * 
     * @author Romer Vargas Otiniano
     * @version 1.0
     * @date 23062016
    */
	public static void bnetactual_pago_tarjetas_terceros(String strAV1) throws SQLException{
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
				Web_Framework.launchapp(Mdl_Variables.Tipo_Aplicativo);
				Thread.sleep(4000);
				Mdl_Keyboard.doType(KeyEvent.VK_ENTER);
				Thread.sleep(5000);

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
				selectByPartOfVisibleText("id", "AsuntoPropio", strTxtNumTarjeta, false, true);

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

	/**
     * 
     * @author Romer Vargas Otiniano
     * @version 1.0
     * @date 30062016
    */
	public static void bnetactual_compra_tarjeta_prepago(String strAV1) throws SQLException{
		String[] arrAV1 = strAV1.split("\\|");

		String strTxtCuentaInicial = Web_Framework.GetValue(arrAV1[0]); //dt_TXT_CUENTAINICIAL
		String strImporteTarjetaTelefonica = Web_Framework.GetValue(arrAV1[1]); //dt_TXT_IMPORTE_TARJETA_TELEFONICA
		String strBtnSgte1 = Web_Framework.GetValue(arrAV1[2]); //dt_BTN_SIGUIENTE_DATOS_TARJETA
		String strTxtSMS = Web_Framework.GetValue(arrAV1[3]); //dt_TXT_SMS

		try{
			if(Web_Framework.GetValue("dt_KWD_BNET_ACTUAL").equals("TRUE")){
				Web_Framework.launchapp(Mdl_Variables.Tipo_Aplicativo);
				Mdl_Choucair.getDatosLogin();

				Mdl_Variables.driver.get(Mdl_Variables.P_Str_RutaAmbienteSecundario);
				Mdl_Variables.driver.findElement(By.id("initial_input")).clear();
				Mdl_Variables.driver.findElement(By.id("initial_input")).sendKeys(Mdl_Variables.Str_DatosUsuario[3]);
				Mdl_Variables.driver.findElement(By.name("eai_password")).clear();
				Mdl_Variables.driver.findElement(By.name("eai_password")).sendKeys(Mdl_Variables.Str_DatosUsuario[10]); //1111
				Thread.sleep(2000);
				Mdl_Evidenciamiento.Evidencia_Log(true, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
				Mdl_Variables.driver.findElement(By.cssSelector("a.c1 > img")).click();
				Thread.sleep(5000);
				Mdl_Evidenciamiento.Evidencia_Log(true, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
				Mdl_Variables.driver.switchTo().frame(0);
				Mdl_Variables.driver.findElement(By.linkText(strTxtCuentaInicial)).click();
				Thread.sleep(2000);
				bajarPaginaFlecha("7");
				Mdl_Evidenciamiento.Evidencia_Log(true, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
				Mdl_Variables.driver.findElement(By.linkText("Operaciones")).click();
				Thread.sleep(3000);
				Mdl_Evidenciamiento.Evidencia_Log(true, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
				Mdl_Variables.driver.findElement(By.linkText("Realizar una recarga telefónica")).click();
				Thread.sleep(2000);
				bajarPaginaFlecha("7");
				Mdl_Evidenciamiento.Evidencia_Log(true, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();

				if(!strImporteTarjetaTelefonica.equalsIgnoreCase("<IGNORE>")){
					Mdl_Variables.driver.findElement(By.linkText("Tarjetas 147")).click();
					Thread.sleep(2000);

					selectByPartOfVisibleText("id", "Importe", strImporteTarjetaTelefonica, true, true);
					Thread.sleep(1000);
					selectByPartOfVisibleText("id", "AsuntoPropio", strTxtCuentaInicial, true, true);
					Thread.sleep(1000);
					Mdl_Evidenciamiento.Evidencia_Log(true, "");
					Mdl_Evidenciamiento.Ingreso_Datos_Log();

					if(!strBtnSgte1.equalsIgnoreCase("<IGNORE>")){
						Mdl_Variables.driver.findElement(By.name("Submit")).click();
						Thread.sleep(2000);
						Mdl_Variables.driver.findElement(By.id("ClaveSegundoFactor")).clear();

						if(!strTxtSMS.equalsIgnoreCase("<IGNORE>")){
							Mdl_Variables.driver.findElement(By.id("ClaveSegundoFactor")).sendKeys(strTxtSMS);
							Thread.sleep(1000);
							Mdl_Evidenciamiento.Evidencia_Log(true, "");
							Mdl_Evidenciamiento.Ingreso_Datos_Log();
							Mdl_Variables.driver.findElement(By.name("Submit")).click();
							Thread.sleep(3000);
							Mdl_Evidenciamiento.Evidencia_Log(true, "");
							Mdl_Evidenciamiento.Ingreso_Datos_Log();
						}else{
							Thread.sleep(1000);
							Mdl_Evidenciamiento.Evidencia_Log(true, "");
							Mdl_Evidenciamiento.Ingreso_Datos_Log();
						}
					}
				} Thread.sleep(1000);

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

	/**
     * @author Romer Vargas
	 * @version 1.0
	 * @date 04072016
     */
    public static void seleccionar_tarjeta(){
    	String findingTag = "";
		boolean flag = false;
		int i = 2;

		try{
			while(i <= 50 && !flag){findingTag = "//table[@id='tbl_tarjetas_resumen']/tbody/tr[" + i + "]/td/span";
				if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath(findingTag)).getText())){
					Mdl_Variables.driver.findElement(By.xpath(findingTag)).click();

					Mdl_Variables.strPasosEvidencia = "Seleccionar la tarjeta " + Mdl_Variables.arrKeyValue[0];
					Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
					flag = true;
				} i++;
			} Thread.sleep(1000);
		}catch(Exception e){System.out.println("Error en \"seleccionar_tarjeta()\" al seleccionar: " + e.getMessage());}
    }

    /**
     * @author Romer Vargas
	 * @version 1.0
	 * @date 04072016
     */
    public static void seleccionar_cuenta_abono(){
    	String findingTag = "";
		boolean flag = false;
		int i = 2;

		try{
			if(!Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("010101010101010101")){
				JavascriptExecutor js = (JavascriptExecutor) Mdl_Variables.driver;
				WebElement element = Mdl_Variables.driver.findElement(By.id("frmCuentaAbonoDisposicionTC"));
				js.executeScript("arguments[0].removeAttribute('class')", element);
				Thread.sleep(3000);

				while(i <= 15 && !flag){findingTag = "//div[@id='mis-cuentas-scroll']/div/div/table/tbody/tr[" + i + "]/td[2]/span";
					if(Mdl_Variables.arrKeyValue[0].equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath(findingTag)).getText())){
						Mdl_Variables.driver.findElement(By.xpath(findingTag)).click();
	
						Mdl_Variables.strPasosEvidencia = "Seleccionar la tarjeta " + Mdl_Variables.arrKeyValue[0];
						Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
						flag = true;
					} i++;
				}
			}else{
				Mdl_Variables.strPasosEvidencia = "No hay tarjeta para seleccionar";
				Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
			} Thread.sleep(1000);
		}catch(Exception e){System.out.println("Error en \"seleccionar_cuenta_abono()\" al seleccionar: " + e.getMessage());}
    }

    /**
     * @author Romer Vargas
	 * @version 1.0
	 * @date 05072016
     */
    public static void bnetactual_tarjetas_efectivo_revolvente(String strAV1) throws SQLException{
		String[] arrAV1 = strAV1.split("\\|");

		String strTxtNumeroTarjeta = Web_Framework.GetValue(arrAV1[0]); //dt_LBL_NUMERO_TARJETA
		String strNumeroCuenta = Web_Framework.GetValue(arrAV1[1]); //dt_LBL_NUMERO_CUENTA
		String strTxtMonto = Web_Framework.GetValue(arrAV1[2]); //dt_TXT_MONTO
		//String strBtnSgte1 = Web_Framework.GetValue(arrAV1[2]); //dt_TXT_MONTO
		strTxtNumeroTarjeta = strTxtNumeroTarjeta.replace("-", "");

		try{
			if(Web_Framework.GetValue("dt_KWD_BNET_ACTUAL").equals("TRUE")){
				Web_Framework.launchapp(Mdl_Variables.Tipo_Aplicativo);
				Thread.sleep(4000);
				Mdl_Keyboard.doType(KeyEvent.VK_ENTER);
				Thread.sleep(5000);
				Mdl_Choucair.getDatosLogin();

				Mdl_Variables.driver.get(Mdl_Variables.P_Str_RutaAmbienteSecundario);
				Mdl_Variables.driver.findElement(By.id("initial_input")).clear();
				Mdl_Variables.driver.findElement(By.id("initial_input")).sendKeys(Mdl_Variables.Str_DatosUsuario[3]);
				Mdl_Variables.driver.findElement(By.name("eai_password")).clear();
				Mdl_Variables.driver.findElement(By.name("eai_password")).sendKeys(Mdl_Variables.Str_DatosUsuario[10]); //gestion99
				Thread.sleep(2000);
				Mdl_Evidenciamiento.Evidencia_Log(true, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
				Mdl_Variables.driver.findElement(By.cssSelector("a.c1 > img")).click();
				Thread.sleep(5000);
				Mdl_Evidenciamiento.Evidencia_Log(true, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
				Mdl_Variables.driver.findElement(By.linkText("Mis Tarjetas")).click();
				Thread.sleep(6000);
				Mdl_Variables.driver.findElement(By.linkText("Disposición de Efectivo")).click();
				Thread.sleep(6000);
				selectByPartOfVisibleText("id", "cbxTarjeta", strTxtNumeroTarjeta, true, false);
				Thread.sleep(5000);

				if(!strNumeroCuenta.equalsIgnoreCase("<IGNORE>") || !strNumeroCuenta.equalsIgnoreCase("010101010101010101")){
					selectByPartOfVisibleText("name", "cuentaSeleccionada", strNumeroCuenta, false, true);
					Thread.sleep(5000);
					bajarPaginaFlecha("10");

					if(!strTxtMonto.equalsIgnoreCase("<IGNORE>")){
						Mdl_Variables.driver.findElement(By.id("importe")).clear();
						Mdl_Variables.driver.findElement(By.id("importe")).sendKeys(strTxtMonto);
						Thread.sleep(1000);
						Mdl_Variables.driver.findElement(By.id("lblAceptacion")).click();
						Thread.sleep(2000);
						//bajarPaginaFlecha("8");
						Mdl_Evidenciamiento.Evidencia_Log(true, "");
						Mdl_Evidenciamiento.Ingreso_Datos_Log();
						Thread.sleep(3000);
						Mdl_Variables.driver.findElement(By.id("btnenviar")).click();
						Thread.sleep(6000);
						bajarPaginaFlecha("5");
						Mdl_Evidenciamiento.Evidencia_Log(true, "");
						Mdl_Evidenciamiento.Ingreso_Datos_Log();
						Thread.sleep(1000);
					}
				}

				//Recargamos el DDriven
	            Mdl_Variables.Rst_Tabla.refreshRow();
	            Mdl_Variables.driver.quit();
			}
		}catch(Throwable e){
			System.out.println("Error en \"bnetactual_tarjetas_efectivo_revolvente()\" no encontró el objeto: " + e.getMessage());
			Mdl_Evidenciamiento.Evidencia_Log(false, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			Mdl_Variables.boolAction = 0;
            Mdl_Variables.driver.quit();
		}
    }

    /**
     * @author Romer Vargas
	 * @version 1.0
	 * @date 05072016
     */
    public static void bnetactual_tarjetas_efectivo_cuotas(String strAV1) throws SQLException{
		String[] arrAV1 = strAV1.split("\\|");

		String strTxtNumeroTarjeta = Web_Framework.GetValue(arrAV1[0]); //dt_LBL_NUMERO_TARJETA
		String strNumeroCuenta = Web_Framework.GetValue(arrAV1[1]); //dt_LBL_NUMERO_CUENTA
		String strTxtMonto = Web_Framework.GetValue(arrAV1[2]); //dt_TXT_MONTO
		String strTxtNroCuotas = Web_Framework.GetValue(arrAV1[3]); //dt_TXT_NUMERO_CUOTAS
		String strBtnSgteDispo = Web_Framework.GetValue(arrAV1[4]); //dt_BTN_SIGUIENTE_DISPOSICION
		strTxtNumeroTarjeta = strTxtNumeroTarjeta.replace("-", "");

		try{
			if(Web_Framework.GetValue("dt_KWD_BNET_ACTUAL").equals("TRUE")){
				Web_Framework.launchapp(Mdl_Variables.Tipo_Aplicativo);
				Thread.sleep(4000);
				Mdl_Keyboard.doType(KeyEvent.VK_ENTER);
				Thread.sleep(5000);
				Mdl_Choucair.getDatosLogin();

				Mdl_Choucair.getDatosLogin();
				Thread.sleep(5000);
				Mdl_Variables.driver.get(Mdl_Variables.P_Str_RutaAmbienteSecundario);
				Mdl_Variables.driver.findElement(By.id("initial_input")).clear();
				Mdl_Variables.driver.findElement(By.id("initial_input")).sendKeys(Mdl_Variables.Str_DatosUsuario[3]);
				Mdl_Variables.driver.findElement(By.name("eai_password")).clear();
				Mdl_Variables.driver.findElement(By.name("eai_password")).sendKeys(Mdl_Variables.Str_DatosUsuario[10]); //"gestion99"
				Thread.sleep(2000);
				Mdl_Evidenciamiento.Evidencia_Log(true, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
				Mdl_Variables.driver.findElement(By.cssSelector("a.c1 > img")).click();
				Thread.sleep(5000);
				Mdl_Evidenciamiento.Evidencia_Log(true, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
				Mdl_Variables.driver.findElement(By.linkText("Mis Tarjetas")).click();
				Thread.sleep(2000);
				Mdl_Variables.driver.findElement(By.linkText("Disposición de Efectivo")).click();
				Thread.sleep(4000);
				selectByPartOfVisibleText("id", "cbxTarjeta", strTxtNumeroTarjeta, true, true);
				Thread.sleep(5000);

				if(!strNumeroCuenta.equalsIgnoreCase("<IGNORE>") || !strNumeroCuenta.equalsIgnoreCase("010101010101010101")){
					selectByPartOfVisibleText("name", "cuentaSeleccionada", strNumeroCuenta, false, true);
					Thread.sleep(3000);

					if(!strTxtMonto.equalsIgnoreCase("<IGNORE>")){
						Mdl_Variables.driver.findElement(By.id("importe")).clear();
						Mdl_Variables.driver.findElement(By.id("importe")).sendKeys(strTxtMonto);
						Thread.sleep(1000);
						Mdl_Variables.driver.findElement(By.id("opcion2")).click();
						Thread.sleep(1000);
						Mdl_Variables.driver.findElement(By.id("numcuotas")).clear();
						Mdl_Variables.driver.findElement(By.id("numcuotas")).sendKeys(strTxtNroCuotas);
						Thread.sleep(2000);
						Mdl_Evidenciamiento.Evidencia_Log(true, "");
						Mdl_Evidenciamiento.Ingreso_Datos_Log();
						Mdl_Variables.driver.findElement(By.id("btnCalcular")).click();
						Thread.sleep(6000);

						if(getIfElementIsDisplay("lblAceptacion")){
							bajarPaginaFlecha("10");
							Mdl_Variables.driver.findElement(By.id("lblAceptacion")).click();
							Thread.sleep(6000);
							////bajarPaginaFlecha("8");
							Mdl_Evidenciamiento.Evidencia_Log(true, "");
							Mdl_Evidenciamiento.Ingreso_Datos_Log();
							Thread.sleep(3000);

							if(!strBtnSgteDispo.equalsIgnoreCase("<IGNORE>")){
								Mdl_Variables.driver.findElement(By.id("btnenviar")).click();
								Thread.sleep(6000);
								bajarPaginaFlecha("7");
								Mdl_Evidenciamiento.Evidencia_Log(true, "");
								Mdl_Evidenciamiento.Ingreso_Datos_Log();
								Thread.sleep(3000);
							}
						}else{Thread.sleep(2000);
							Mdl_Evidenciamiento.Evidencia_Log(true, "");
							Mdl_Evidenciamiento.Ingreso_Datos_Log();
						}
					}
				}

				//Recargamos el DDriven
	            Mdl_Variables.Rst_Tabla.refreshRow();
	            Mdl_Variables.driver.quit();
			}
		}catch(Throwable e){System.out.println("Error en \"bnetactual_tarjetas_efectivo_cuotas()\" no encontró el objeto: " + e.getMessage());
			Mdl_Evidenciamiento.Evidencia_Log(false, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			Mdl_Variables.boolAction = 0;
            Mdl_Variables.driver.quit();
		}finally{}
    }

    public static void ultimo_mov_disp_efect(String strAV1) throws SQLException{
		String[] arrAV1 = strAV1.split("\\|");
		String findingTag = "";
		boolean flag = false;
		int i = 2;

		String strNumeroCuenta = Web_Framework.GetValue(arrAV1[0]); //dt_LBL_NUMERO_CUENTA
		String reviewMovs = Web_Framework.GetValue(arrAV1[1]); //dt_LBL_NUMERO_CUENTA

		try{
			if(reviewMovs.equalsIgnoreCase("TRUE") && !strNumeroCuenta.equalsIgnoreCase("<IGNORE>")){
				Mdl_Variables.driver.findElement(By.linkText("Resumen de Productos")).click();
				Thread.sleep(3000);

				while(i <= 15 && !flag){findingTag = "//article[@id='print-section']/div/table/tbody/tr[" + i + "]/td/span";
					if(strNumeroCuenta.equalsIgnoreCase(Mdl_Variables.driver.findElement(By.xpath(findingTag)).getText())){
						Mdl_Variables.driver.findElement(By.xpath(findingTag)).click();
						flag = true;
					} i++;
				}

				if(flag){
					Thread.sleep(3000);
					Mdl_Variables.driver.findElement(By.xpath("//table[@id='tbl_movimientos_cuentas']/tbody/tr[2]/td[2]/span")).click();
					bajarPaginaFlecha("10");
					Thread.sleep(2000);
					Mdl_Evidenciamiento.Evidencia_Log(true, "");
					Mdl_Evidenciamiento.Ingreso_Datos_Log();
					Mdl_Variables.strPasosEvidencia = "Selecciona el último movimiento de la cuenta " + strNumeroCuenta;
					Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
				} Thread.sleep(1000);
			}
		}catch(Exception ex){System.out.println("Error en \"ultimo_mov_disp_efect()\" al seleccionar: " + ex.getMessage());}
	}

    private static boolean getIfElementIsDisplay(String elementId){
    	return Mdl_Variables.driver.findElement(By.id(elementId)).isDisplayed();
    }

    /**
     * @author Romer Vargas
	 * @version 1.0
	 * @date 20072016
     */
    public static void salir_esc_form(String strAV1){
    	try{
    		if(!Web_Framework.GetValue(strAV1).equalsIgnoreCase("<IGNORE>")){
    			Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_ESCAPE);
        		Thread.sleep(1000);    			
    		}
    	}catch(Exception ex){System.out.println("Error en \"salir_esc_form()\" al seleccionar: " + ex.getMessage());}
    }
}