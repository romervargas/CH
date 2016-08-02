package Pck_Choucair;

import java.util.Properties;
import java.awt.*;
import java.awt.event.*;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.sql.*;
 
/*import com.ibm.as400.access.*;
import com.ibm.as400.*;*/
 
import javax.imageio.ImageIO;
 
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
 
 
import org.openqa.selenium.*;
//import com.gargoylesoftware.htmlunit.ElementNotFoundException;
 
import Pck_Choucair.Web_Framework.Identificadores;

 
//PARA EL SET PROPERTY
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.JavascriptExecutor;
 

 
/*
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
*/
 

//ADICIONADAS PARA EL CARGAR DATOS
import java.sql.ResultSetMetaData;
import java.io.*;
import java.util.*;
//PARA EL SET PROPERTY
//ADICIONADAS PARA EL CARGAR DATOS

public class Mdl_Choucair {
	public enum Funciones_Ch 
	{
		evidencia,	espera_cargar,	espera_objeto, click_ok, clica_ok_msg, clickxy, movemouseonobject,
		valida_log,	entrada_pantalla,	siguiente_pantalla,
		htmltd2, htmldiv,	cerrar_pantallas,	pantallas_publicitarias,
		llenar_siguiente_pantalla, click_objeto, ordenacion, ejecutar_javascript, novalue, presskey,
		guardar_contenido,cnx_destino,cargar_datos,cargar_ruta,cargar_ruta2;
		public static Funciones_Ch getValue(String str)
	    {
	        try {
	            return valueOf(str);
	        }
	        catch (IllegalArgumentException ex) {
	            return novalue;
	        }
	    }
	}
	public enum imgProperties{
		onmouseover, click, novalue;
		public static imgProperties getValue(String str)
	    {
	        try {
	            return valueOf(str);
	        }
	        catch (IllegalArgumentException ex) {
	            return novalue;
	        }
	    }
	}
	
	public enum tipo_ordenamiento{
		A, DAD, DDA, D, DAA, DDD, novalue;
		public static tipo_ordenamiento getValue(String str)
	    {
	        try {
	            return valueOf(str);
	        }
	        catch (IllegalArgumentException ex) {
	            return novalue;
	        }
	    }
	}
	
	public enum tipos_recorrido{
		T, N, F, M, novalue;
		public static tipos_recorrido getValue(String str)
	    {
	        try {
	            return valueOf(str);
	        }
	        catch (IllegalArgumentException ex) {
	            return novalue;
	        }
	    }
	}

	private static final String HP_SolicitudCreHipVar = null;

	public static void Fw_Ch(String strFunName,String  str_parametro1,String  str_parametro2,String str_parametroAdicional) throws SQLException, ClassNotFoundException, InterruptedException, IOException, AWTException
	{
		String desc_paso;
		
		switch (Funciones_Ch.getValue(strFunName.split(";")[0].toLowerCase()))	
		{
		case evidencia:
			if(!Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>")){
			Mdl_Evidenciamiento.Evidencia_Log(true, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			
			//desc_paso = "";
			//desc_paso = "Se capturó evidencia";
			//Mdl_Evidenciamiento.CADENA_PASOS(desc_paso);
			}
			break;
		case htmltd2:
			if(!Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>"))
				Recorrer_HTMLTable2(Web_Framework.GetValue(str_parametro1), Web_Framework.GetValue(str_parametroAdicional), Web_Framework.GetValue(str_parametro2), true, "htmltd2");
			break;
		case htmldiv: //FAVC20130516
			if(!Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>"))
				Recorrer_HTMLTable2(Web_Framework.GetValue(str_parametro1), Web_Framework.GetValue(str_parametroAdicional), Web_Framework.GetValue(str_parametro2), true, "htmldiv");
			break;			
		case click_objeto:
			 //click_objeto(str_parametro1);
			 break;
		case click_ok: case clica_ok_msg:
			Click_Aceptar();
			 break;	
		case ordenacion:
			//if(!Mdl_Variables.arrKeyValue[0].equalsIgnoreCase("<IGNORE>"))
			//	Verifica_Ordenacion(Web_Framework.GetValue(strFunName.split(";")[1]), Web_Framework.GetValue(str_parametro1), Web_Framework.GetValue(str_parametroAdicional));
			break;
		case siguiente_pantalla:
			siguiente_pantalla(str_parametro1, str_parametro2, str_parametroAdicional);
			break;
		case ejecutar_javascript:
			ejecutarJavaScript(Web_Framework.GetValue(str_parametro1));
			break;
		case clickxy:
			clickXY(str_parametro1);
			break;	
		case movemouseonobject:
			moveMouseOnObject(Web_Framework.GetValue(str_parametro1), Web_Framework.GetValue(str_parametroAdicional), Web_Framework.GetValue(str_parametro2));
			break;
		case presskey:
			Mdl_Keyboard.TeclaAbajo();
			break;
		case guardar_contenido:
			GUARDAR_CONTENIDO(str_parametro1, str_parametro2, str_parametroAdicional,"");  
			break;
			
		case cnx_destino:
			cnx_destino(str_parametro1);  
			break;
			
		case cargar_datos:
			cargar_datos( str_parametro1,  str_parametro2, str_parametroAdicional);  
			break;
			
		case cerrar_pantallas:
			Cerrar_Pantallas();
			break;
			
		case cargar_ruta:
			Cargar_Ruta();
			break;
		case cargar_ruta2:
			Cargar_Ruta2();
			break;
			
			
		}
	}

	public static boolean DateCompare (Date date, String Date2) throws ParseException
	{
        Date Date_Now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        Date Date_Ejecucion = date;
        Date_Now = (Date)formatter.parse(Date2);
        return Date_Now.after(Date_Ejecucion);
	}
	// La variable que recibe es en milisegundos ejm: para 2 segundo es 2000
	public static void EsperaCargar (int parar) 
	{
		try {
			Thread.sleep(parar);
		//	Mdl_Variables.driver.manage().timeouts().implicitlyWait(parar, TimeUnit.SECONDS(5));  // No funciono
		//	WebDriverWait ( parar ,  TimeSpan.FromSeconds ( 4 ));  // No funciono
			} catch(InterruptedException e) {
			} 
	}
		
	//public void EsperaCargar () {Mdl_Variables.driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);}
	
	/**
	 * Espera objeto, 
	 * 
	 */
	public static void Existe(String IdExiste, String ExisteObj) 
	{
		
		
		try {
			
			//  Mdl_Variables.objPerform= Mdl_Variables.driver.findElement(By.id("txtNombre"));                          SIIIIII
			//  Mdl_Variables.objPerform= Mdl_Variables.driver.findElement(By.xpath("//*[@id=\"dWGrid\"]/tbody"));       NOOOOOO saca error el xpath
			// Preguntar si un objeto existe solo si el Xpath es asignado a un label o tabla o por el estilo   o mirar el   tagName != xpath
			
			if(Mdl_Variables.Rst_Tabla.getString("ACIERTO_ERROR").equals("0")){	//El caso es orientado al error
				if((Mdl_Variables.driver.findElements(By.xpath(IdExiste)).size()) >= 1){	//si encontró el objeto a dar clic se marca el caso como fallido ya que está orientado al error
					Mdl_Evidenciamiento.Evidencia_Log(false, "Se encontró el objeto");
					Mdl_Evidenciamiento.Ingreso_Datos_Log();
					Mdl_Variables.boolAction = 0;
				}
			}else{	//el caso es orientado al acierto
				if((Mdl_Variables.driver.findElements(By.xpath(IdExiste)).size()) == 0){	//si no encontró el objeto a dar clic se marca el caso como fallido ya que está orientado al acierto
					Mdl_Evidenciamiento.Evidencia_Log(false, "No se encontró el objeto");
					Mdl_Evidenciamiento.Ingreso_Datos_Log();
					Mdl_Variables.boolAction = 0;
				}
			}
		} catch (Exception e) 
		{
			System.out.println("Error validando el objeto " + e.getMessage());
			Mdl_Evidenciamiento.Evidencia_Log(false, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			Mdl_Variables.boolAction = 0;
		}
	}

	
	public static void CapturaPantalla(String Ruta, String NombreImagen) 
	{
		try {
		    /*
			Robot robot = new Robot();  
		    BufferedImage image = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));  
		    ImageIO.write(image, "png", new File(Ruta + "//" + NombreImagen + ".png"));  
		    */
			
			Date fechaActual = new Date();
           // SimpleDateFormat formato = new SimpleDateFormat("ddMMyyyyHHmmss");
            //formato.format(fechaActual);
         WebDriver driver1 = new Augmenter().augment(Mdl_Variables.driver);
         File file = ((TakesScreenshot) driver1).getScreenshotAs(OutputType.FILE);
       
         
         FileUtils.copyFile(file, new File(Ruta + "//" + NombreImagen + ".png"));

		    
		} catch (Exception e) 
		{
			System.out.println("Error tomando la captura de la pantalla Mdl_Choucair.CapturaPantalla en Ruta: " + Ruta + e.getMessage());
			Mdl_Evidenciamiento.Evidencia_Log(false, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			Mdl_Variables.boolAction = 0;
		}
	}

	public static int Diferencia_Fechas(java.util.Date fecha1, java.util.Date fecha2){
		   java.util.Date fechaMayor = null;
		   java.util.Date fechaMenor = null;
		 
		   if (fecha1.compareTo(fecha2) > 0){
		    fechaMayor = fecha1;
		    fechaMenor = fecha2;
		   }else{
		    fechaMayor = fecha2;
		    fechaMenor = fecha1;
		   }
		 
		   long diferenciaMils = fechaMayor.getTime() - fechaMenor.getTime();
		   long segundos = diferenciaMils / 1000;
		   	 
		   return (int) (segundos);
	}
    public static void Actualizar_Utilizado()
    {
    	try
    	{
    		Mdl_Variables.Cnn.prepareCall("UPDATE " + Mdl_Variables.P_Str_Datos + " set UTILIZADO =  -1,HORA = '" + new Date() + "', TIEMPO_EJECUCION_CASO = '" + new Date().compareTo(Mdl_Variables.P_Entrada_Sistema) + "'  where ID_CASO =" + Mdl_Variables.Rst_Tabla.getString("ID_CASO"));
    	}catch(Exception ex){
    		System.out.println("Ocurrió un error actualizando el registro. Mdl_Choucair.Actualizar_Utilizado: " + ex.getMessage());
    	}
    }
    /*Romer*/
    public static void Cerrar_Pantallas() throws IOException, InterruptedException{
        if(Mdl_Variables.boolAction == 1){
                Mdl_Variables.driver.findElement(By.linkText("Desconectar")).click();
                Thread.sleep(3000);
                //Mdl_Variables.driver.quit();
        }else{
                //System.out.println("ocurri󮠵n error al cerrar sesi󮺠" );
        } 
        
        
        switch(Web_Framework.GetValue(Mdl_Variables.Tipo_Aplicativo.toLowerCase())){
            case "chrome":
            	
                    Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
                Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
                    break;
            case "firefox":
                    Runtime.getRuntime().exec("taskkill /F /IM firefox.exe");                        //descomentar - RVO: 14032016
                    Runtime.getRuntime().exec("taskkill /F /IM firefoxdriver.exe");                //descomentar - RVO: 14032016
                    break;
            default:
                    Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe");
                Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
                Runtime.getRuntime().exec("taskkill /F /IM safari.exe");
                Runtime.getRuntime().exec("taskkill /F /IM safaridriver.exe");
                Runtime.getRuntime().exec("taskkill /F /IM opera.exe");
                Runtime.getRuntime().exec("taskkill /F /IM operadriver.exe");
              break;
        }

}
    
    static int keyInput[] = 
        {
          KeyEvent.VK_C, KeyEvent.VK_I, KeyEvent.VK_C, KeyEvent.VK_S, KeyEvent.VK_T,
          KeyEvent.VK_T, KeyEvent.VK_O, KeyEvent.VK_R,KeyEvent.VK_ENTER
          
         
          //KeyEvent.VK_P,KeyEvent.VK_0,KeyEvent.VK_1,KeyEvent.VK_8,KeyEvent.VK_6,KeyEvent.VK_1,KeyEvent.VK_8,
          //KeyEvent.VK_TAB,
          //KeyEvent.VK_M,KeyEvent.VK_U,KeyEvent.VK_L,
          //KeyEvent.VK_E,KeyEvent.VK_K,KeyEvent.VK_A,KeyEvent.VK_8,KeyEvent.VK_5,KeyEvent.VK_ENTER,KeyEvent.VK_PAUSE
        };
    static int keyInput2[] = 
        {
          KeyEvent.VK_P,KeyEvent.VK_0,KeyEvent.VK_1,KeyEvent.VK_8,KeyEvent.VK_6,KeyEvent.VK_1,KeyEvent.VK_8,
          KeyEvent.VK_TAB
          //,           KeyEvent.VK_M,KeyEvent.VK_U,KeyEvent.VK_L,KeyEvent.VK_E,KeyEvent.VK_K,KeyEvent.VK_A,KeyEvent.VK_8,KeyEvent.VK_5,KeyEvent.VK_ENTER,KeyEvent.VK_PAUSE
        };
    static int keyInput3[] = 
        {
          KeyEvent.VK_M,KeyEvent.VK_U,KeyEvent.VK_L,KeyEvent.VK_E,KeyEvent.VK_K,KeyEvent.VK_A,KeyEvent.VK_8,KeyEvent.VK_5,KeyEvent.VK_ENTER,KeyEvent.VK_PAUSE
        };
    public static void Cargar_Ruta() throws  AWTException,IOException, InterruptedException {
    
    	Runtime.getRuntime().exec("C:\\Users\\Administrador\\Desktop\\TE\\HostExplorer.nt\\Tn3270.exe -p C:\\Users\\Administrador\\Desktop\\TE\\HostExplorer.nt\\HostEx\\Profile\\PU\\P2.HEP");
    	
    	// String command = "C:\\Users\\Administrador\\Desktop\\TE\\HostExplorer.nt\\Tn3270.exe -p C:\\Users\\Administrador\\Desktop\\TE\\HostExplorer.nt\\HostEx\\Profile\\PU\\P2.HEP";
    	   // Process child = Runtime.getRuntime().exec(command);
    	  //  Runtime.getRuntime().exec("cicsttor");
    	    
    	    Thread.sleep(5000);

    	    Robot robot = new Robot();
    	    
    	    for (int i = 0; i < keyInput.length; i++)
    	    	   {
  	    	      robot.keyPress(keyInput[i]);	
    	    	      robot.delay(150);
    	    	   	}
    	    
    	    robot.delay(3000);
    	   // robot.mouseMove(400,400);
     	   
    	  
    	    for(int k=0;k<keyInput2.length;k++){
     		   robot.keyPress(keyInput2[k]);
     		   
    	    	
      	      robot.delay(150);  
     	   }
    	   
    	   /*for(int j=0;j<keyInput3.length;j++){
    		   robot.keyPress(keyInput3[j]);
   	    	
     	      robot.delay(150);  
    	   }*/
    	    
    	    
    	
    
    }
    public static void Cargar_Ruta2() throws  AWTException,IOException, InterruptedException {
        
    	
    	    

    	    Robot robot = new Robot();
    	 
    	    robot.delay(10000);
    	   
    	    robot.keyPress(KeyEvent.VK_P);
    	    robot.delay(150); 
    	    robot.keyPress(KeyEvent.VK_0);
    	    robot.delay(150);
    	    robot.keyPress(KeyEvent.VK_1);
    	    robot.delay(150);
    	    robot.keyPress(KeyEvent.VK_8);
    	    robot.delay(150);
    	    robot.keyPress(KeyEvent.VK_6);
    	    robot.delay(150);
    	    robot.keyPress(KeyEvent.VK_1);
    	    robot.delay(150);
    	    robot.keyPress(KeyEvent.VK_8);
    	    robot.delay(150);
    	    robot.keyPress(KeyEvent.VK_TAB);
    	    robot.delay(150);
    
    }
    public static void Mouse_Click(int x, int y) throws AWTException
    {
    	Robot MouseRobot = new java.awt.Robot();
    	MouseRobot.mouseMove(x,y);
		MouseRobot.mousePress(InputEvent.BUTTON1_MASK);
		MouseRobot.mouseRelease(InputEvent.BUTTON1_MASK);
    }
    public static void Mouse_Move(int x, int y) throws AWTException
    {
    	Robot MouseRobot = new java.awt.Robot();
    	MouseRobot.mouseMove(x,y);
    }
    /**
     * procedimiento que valida si existe un alert y lo cierra, y también valida si existe el link "Vaya a este sitio web (no recomendado)." y le da clic
     */
    public static void Click_Aceptar()
    {
    	try
    	{
    		//Si existe un alert lo cierra
    		Mdl_Variables.driver.switchTo().alert().accept();
    	}catch(Exception Ex){}
    	try{
    		//click en el link
			Mdl_Variables.driver.findElement(By.linkText("Vaya a este sitio web (no recomendado).")).click();
		}catch(Throwable e){}
    }
    
    // Espera Objeto tipo y el identificador
    private void waitForById(String tipo, String xpath) throws InterruptedException 
    {
    for (int i = 0; i < 10; i++) {
	    try {
		    if (tipo.equals("name")) {
		    if (Mdl_Variables.driver.findElements(By.name(xpath)).size() != 0)
		    break;
		    else
		    Thread.sleep(1000);
		    } else {
		    if (Mdl_Variables.driver.findElements(By.xpath(xpath)).size() != 0)
		    break;
		    else
		    Thread.sleep(1000);
		    }
		    } catch (Exception e) {
	    }
    }
    }


    
	public static void siguiente_pantalla(String Str_Tabla, String Numero_Caso, String SQL_OPCIONAL)
	{
		try{
			Mdl_Variables.strTablaAnterior = Mdl_Variables.P_Str_Tabla;
			String[] spl_sql; 
			Mdl_Variables.P_Str_IdEvidencia = Mdl_Variables.Rst_Tabla.getString("id_caso");
			Mdl_Variables.P_Str_Desc_Acc_Ant = Mdl_Variables.Rst_Tabla.getString("DESCRIPCION_ACIERTO");
			Mdl_Variables.P_Str_Desc_Err_Ant = Mdl_Variables.Rst_Tabla.getString("DESCRIPCION_ERROR");
  
			if (Str_Tabla.toLowerCase().contains("dt_")){
				spl_sql = (Mdl_Variables.arrKeyValue[0]).split("\\;");
				if (spl_sql.length == 3){
					Str_Tabla = spl_sql[0];
					Numero_Caso = spl_sql[1];
					SQL_OPCIONAL = spl_sql[2];
				}else{
					Str_Tabla = spl_sql[0];
					Numero_Caso = spl_sql[1];
				}
			}else{
				spl_sql = (Numero_Caso).split(",");
				if (spl_sql.length > 1)
					SQL_OPCIONAL = spl_sql[1];
				else
					if (SQL_OPCIONAL=="")
					SQL_OPCIONAL = "";
				Numero_Caso = spl_sql[0];
			}
			//se agrega para realizar la ejecución de varios casos consecutivos, por ejemplo para Imagine
			//se ingresa el rango de casos, ej: 1-5 para ejecutar los casos del 1 al 5
			if(Numero_Caso.split("-").length > 1){
				String numeroCasoAux = Numero_Caso;
				Numero_Caso = "";
				for(int i = Integer.parseInt(numeroCasoAux.split("-")[0]) ; i <= Integer.parseInt(numeroCasoAux.split("-")[1]) ; i++){
					Numero_Caso += i + "";
					if(i < Integer.parseInt(numeroCasoAux.split("-")[1])){
						Numero_Caso += " OR \"ID_CASO\" = ";
					}
				}
			}
			Mdl_Variables.P_Str_Tabla =Str_Tabla;
			int row_Tabla_ant = Mdl_Variables.Rst_Tabla.getRow();
			int row_Coordenadas_ant = Mdl_Variables.Rst_Coordenadas.getRow();
			//' se cargan los recorsets del script secundario
			Mdl_Variables.Rst_Tabla.close();
			Mdl_Variables.Rst_Coordenadas.close();
			Mdl_Variables.Rst_Objetos.close();
			//Se cargan los objetos para la pantalla a ejecutar
			StringBuilder consulta = new StringBuilder();
			consulta.append("SELECT * FROM \"TBL_OBJETOS\" WHERE \"PANTALLA\" = '" + Str_Tabla + "'");
			Mdl_Variables.Rst_Objetos = Mdl_Variables.Cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery(consulta.toString());
			//Se cargan los casos a ejecutar
			consulta = new StringBuilder(); 
			consulta.append("SELECT * FROM \"" + Str_Tabla + "_D\" WHERE \"ID_CASO\" = " + Numero_Caso + " ORDER BY \"ID_CASO\"");
			Mdl_Variables.Rst_Tabla = Mdl_Variables.Cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery(consulta.toString());
			//Se cargan los pasos a ejecutar
			consulta = new StringBuilder(); 
			consulta.append("SELECT * FROM \"" + Str_Tabla + "_S\" WHERE (\"ID\" >= '21' AND \"ID\" < '150' AND UPPER(AUTOMATE) ='R') " +  SQL_OPCIONAL + " ORDER BY \"ID\" ");
			Mdl_Variables.Rst_Coordenadas = Mdl_Variables.Cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery(consulta.toString());
			while (Mdl_Variables.Rst_Tabla.next()){
				Mdl_Variables.Rst_Coordenadas.first();
				Mdl_Variables.Rst_Coordenadas.previous();
				while (Mdl_Variables.Rst_Coordenadas.next()){
//					Esto se puso para la ejecución de fiado, luego de terminar el proceso ELA
//					if(Mdl_Variables.Rst_Coordenadas.getInt("ID") == 56 && Mdl_Variables.Rst_Coordenadas.getString("OBJECT").equalsIgnoreCase("BUTTON;dto_BTN_CONTINUAR_PROCESO"))
//						EsperaCargar(16000);
//						//Mdl_Variables.driver.findElement(By.name("B4")).click();
					Web_Framework.Keyword_Web();
					if (Mdl_Variables.P_Tipo_Log == 2){
						Mdl_Variables.Rst_Coordenadas.close();
						Mdl_Variables.Rst_Objetos.close();
						Mdl_Variables.Rst_Tabla.close();
						Mdl_Variables.P_Str_Tabla = Mdl_Variables.strTablaAnterior;
						Mdl_Variables.strTablaAnterior = "";
						consulta = new StringBuilder(); 
						consulta.append("SELECT * FROM \"TBL_OBJETOS\" WHERE \"PANTALLA\" = '" + Mdl_Variables.P_Str_Tabla + "'");
						Mdl_Variables.Rst_Objetos = Mdl_Variables.Cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
								ResultSet.CONCUR_READ_ONLY).executeQuery(consulta.toString());
						consulta = new StringBuilder(); 
						if(Mdl_Variables.P_Bln_Limpia_Casos){
							consulta.append("SELECT * FROM \"" + Mdl_Variables.P_Str_Datos +"\" WHERE UPPER( ESTADO_CASO) ='ACTIVO' ORDER BY \"ID_CASO\"");
							Mdl_Variables.Rst_Tabla = Mdl_Variables.Cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
									ResultSet.CONCUR_UPDATABLE).executeQuery(consulta.toString());
						}else{
							consulta.append("SELECT * FROM \"" + Mdl_Variables.P_Str_Datos +"\" WHERE UTILIZADO <> -1 AND UPPER( ESTADO_CASO) ='ACTIVO' ORDER BY \"ID_CASO\"");
							Mdl_Variables.Rst_Tabla = Mdl_Variables.Cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
									ResultSet.CONCUR_UPDATABLE).executeQuery(consulta.toString());
						}
						consulta = new StringBuilder();
						consulta.append("SELECT * FROM \"" + Mdl_Variables.P_Str_Script +"\" where UPPER( AUTOMATE) ='R' ORDER BY \"ID\"");
						Mdl_Variables.Rst_Coordenadas = Mdl_Variables.Cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
								ResultSet.CONCUR_READ_ONLY).executeQuery(consulta.toString());
						Mdl_Variables.Rst_Tabla.first();
						Mdl_Variables.Rst_Tabla.previous();
						Mdl_Variables.Rst_Coordenadas.first();
						Mdl_Variables.Rst_Coordenadas.previous();
						//' se lleva el recorset principal al caso en el que va
						Mdl_Variables.Rst_Coordenadas.absolute(row_Coordenadas_ant);
						if (Mdl_Variables.P_Bln_Limpia_Casos){
							Integer i=1;
							Mdl_Variables.Rst_Tabla.next();	
							while (Mdl_Variables.Rst_Tabla.getInt("id_caso") != Integer.parseInt(Mdl_Variables.P_Str_IdEvidencia)){
								Mdl_Variables.Rst_Tabla.next();
							}
						}else{
							Mdl_Variables.Rst_Tabla.absolute(row_Tabla_ant);
						}
						Mdl_Variables.P_Str_IdEvidencia = "";
						Mdl_Variables.P_Str_Desc_Acc_Ant = "";
						Mdl_Variables.P_Str_Desc_Err_Ant = "";
						return;
					}
				}
			}
			//' se cargan nuevamente los re_VARIABLES.RST_TABLA.close();
			Mdl_Variables.Rst_Coordenadas.close();
			Mdl_Variables.Rst_Objetos.close();
			Mdl_Variables.Rst_Tabla.close();
			Mdl_Variables.P_Str_Tabla = Mdl_Variables.strTablaAnterior;
			consulta = new StringBuilder(); 
			consulta.append("SELECT * FROM \"TBL_OBJETOS\" WHERE \"PANTALLA\" = '" + Mdl_Variables.P_Str_Tabla + "'");
			Mdl_Variables.Rst_Objetos = Mdl_Variables.Cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery(consulta.toString());
			consulta = new StringBuilder(); 
			if(Mdl_Variables.P_Bln_Limpia_Casos){
				consulta.append("SELECT * FROM \"" + Mdl_Variables.P_Str_Datos +"\" WHERE UPPER( ESTADO_CASO) ='ACTIVO' ORDER BY \"ID_CASO\"");
				Mdl_Variables.Rst_Tabla = Mdl_Variables.Cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE).executeQuery(consulta.toString());
			}else{
				consulta.append("SELECT * FROM \"" + Mdl_Variables.P_Str_Datos +"\" WHERE UTILIZADO <> -1 AND UPPER( ESTADO_CASO) ='ACTIVO' ORDER BY \"ID_CASO\"");
				Mdl_Variables.Rst_Tabla = Mdl_Variables.Cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE).executeQuery(consulta.toString());
			}
			consulta = new StringBuilder(); 
			consulta.append("SELECT * FROM \"" + Mdl_Variables.P_Str_Script +"\" where UPPER( AUTOMATE) ='R' ORDER BY \"ID\"");
			Mdl_Variables.Rst_Coordenadas = Mdl_Variables.Cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery(consulta.toString());
			Mdl_Variables.Rst_Tabla.first();
			Mdl_Variables.Rst_Tabla.previous();
			Mdl_Variables.Rst_Coordenadas.first();
			Mdl_Variables.Rst_Coordenadas.previous();
			//' se lleva el recorset principal al caso en el que va
			Mdl_Variables.Rst_Coordenadas.absolute(row_Coordenadas_ant);
			//Mdl_Variables.Rst_Tabla.absolute(row_Tabla_ant);
			if(!Mdl_Variables.P_Bln_Limpia_Casos){
				//Integer i=1;
				Mdl_Variables.Rst_Tabla.next();	        	
//				while (Mdl_Variables.Rst_Tabla.getInt("id_caso") != Integer.parseInt(Mdl_Variables.P_Str_IdEvidencia))
//	        	{	        		
//	        		Mdl_Variables.Rst_Tabla.next();	        		
//	        	}
			}else{
				Mdl_Variables.Rst_Tabla.absolute(row_Tabla_ant);
			}
			Mdl_Variables.P_Str_Tabla = Mdl_Variables.strTablaAnterior;
			Mdl_Variables.P_Str_IdEvidencia = "";
			Mdl_Variables.P_Str_Desc_Acc_Ant = "";
			Mdl_Variables.P_Str_Desc_Err_Ant = "";
			Mdl_Variables.strTablaAnterior = "";
		}catch (Exception e){ 
			Mdl_Evidenciamiento.Evidencia_Log(false,  " Siguiente_Pantalla ");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			Mdl_Variables.boolAction = 0;
		}
	}
	
	
	
    
	/**
	 * Jhonny Montoya  Fecha 25 de Octubre del 2012
	 * Se agrega esta función para formatear un numero que sea un String y le asigne ceros a la izquierda según se necesite.
	 * 
	 */
    public static String formatearNumero(String numero, int longitud){
    	for(int i = numero.length(); i < longitud ; i++){
    		numero = "0" + numero;
    	}
    	return numero;
    }
    
    public static void getDatosLogin(){
    	try {
			if(!Mdl_Variables.Rst_Coordenadas.getString("ACTION_VALUE1").contains("|") && Mdl_Variables.Rst_Coordenadas.getString("ACTION_VALUE1") != null && !Mdl_Variables.Rst_Coordenadas.getString("ACTION_VALUE1").trim().equalsIgnoreCase("null") && !Mdl_Variables.Rst_Coordenadas.getString("ACTION_VALUE1").trim().equalsIgnoreCase(""))
				Mdl_Variables.Rst_Inicio_Sesion = Mdl_Variables.Cnn.prepareCall("select * from TBL_INICIO_SESION where USUARIO='" + Mdl_Variables.Rst_Tabla.getString("TXT_USUARIO").trim() + "' AND lower(AMBIENTE) like '" + Mdl_Variables.Rst_Coordenadas.getString("ACTION_VALUE1").trim().toLowerCase() + "'").executeQuery();
			else
				Mdl_Variables.Rst_Inicio_Sesion = Mdl_Variables.Cnn.prepareCall("select * from TBL_INICIO_SESION where USUARIO='" + Mdl_Variables.Rst_Tabla.getString("TXT_USUARIO").trim() + "'").executeQuery();
			if(Mdl_Variables.Rst_Inicio_Sesion.next()){
				Mdl_Variables.Str_DatosUsuario = (Mdl_Variables.Rst_Inicio_Sesion.getString("USUARIO") + "|" + Mdl_Variables.Rst_Inicio_Sesion.getString("CONSECUTIVO") + "|" + Mdl_Variables.Rst_Inicio_Sesion.getString("NOM_APLICATIVO") +  "|" + Mdl_Variables.Rst_Inicio_Sesion.getString("TARJETAS") + "|" + Mdl_Variables.Rst_Inicio_Sesion.getString("NOMBRE") + "|" + Mdl_Variables.Rst_Inicio_Sesion.getString("APELLIDO") + "|" + Mdl_Variables.Rst_Inicio_Sesion.getString("TIPO") + "|" + Mdl_Variables.Rst_Inicio_Sesion.getString("EMPRESA") + "|" + Mdl_Variables.Rst_Inicio_Sesion.getString("TIPO_USUARIO") + "|" + Mdl_Variables.Rst_Inicio_Sesion.getString("CLAVE" + Mdl_Variables.Rst_Inicio_Sesion.getString("CLAVE")) + "|" + Mdl_Variables.Rst_Inicio_Sesion.getString("SEGUNDA_CLAVE") + "|" + Mdl_Variables.Rst_Inicio_Sesion.getString("AMBIENTE") + "|" + Mdl_Variables.Rst_Inicio_Sesion.getString("NOM_USUARIO")).split("\\|");
				Mdl_Variables.Str_DatosUsuario[11] = Mdl_Variables.Rst_Inicio_Sesion.getString("CLAVE" + Mdl_Variables.Rst_Inicio_Sesion.getString("CLAVE").trim()).trim();
				//Mdl_Variables.Str_DatosUsuario[12] = Mdl_Variables.Rst_Inicio_Sesion.getString("CLAVE1").trim();
				Mdl_Variables.Rst_Inicio_Sesion.close();
			}else{
				System.out.println("No se encontraron datos para el usuario " + Mdl_Variables.Rst_Tabla.getString("USUARIO").trim() + " en TBL_INICIO_SESION");
				Mdl_Evidenciamiento.Evidencia_Log(false, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
				Mdl_Variables.boolAction = 0;
			}
		} catch (Exception e) {
			System.out.println("Error en Mdl_Choucair.getDatosLogin: " + e.getMessage());
			Mdl_Evidenciamiento.Evidencia_Log(false, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			Mdl_Variables.boolAction = 0;
		}
    }
    
    /**
     * Genera un número de teléfono aleatorio
     * @param tipo si es cel entonces genera un numero de 10 digitos, de lo contrario lo genera de 7
     * @return String que contiene un numero de telefono generado de forma aleatoria
     */
    public static String generarTelAleatorio(String tipo){
    	String valorRetorno = null;
    	valorRetorno = ((int)(Math.random()*9999999)) + "";
    	if(tipo.equalsIgnoreCase("cel")){
    		valorRetorno = "300" + valorRetorno;
    	}
    	//se valida la longitud
    	for(int i = valorRetorno.length() ; i < (tipo.equalsIgnoreCase("cel") ? 10 : 7) ; i++)
    		valorRetorno += "0";
    	return valorRetorno;
    }
    
    /**
     * Se encarga de ejecutar una instrucción de javascript en la página
     */
    public static void ejecutarJavaScript(String instruccion){
    	try{
    		((JavascriptExecutor)Mdl_Variables.driver).executeScript(instruccion);
    	}catch(Throwable e){
    		System.out.println("(Mdl_Choucair.ejecutarJavaScript) Error: " + e.getMessage());
    		Mdl_Evidenciamiento.Evidencia_Log(false, "(Mdl_Choucair.ejecutarJavaScript) Error: " + e.getMessage());
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			Mdl_Variables.boolAction = 0;
    	}
    }
    
    /**
     * Procedimiento que espera a que un objeto aparezca en la pantalla, si no aparece marca el caso de prueba como fallido
     * @param tipoObjeto es el tipo de objeto a validar (ejemplo: button, textbox, link, label...)
     * @param idObjeto es el id del objeto a validar, para el caso de los label, es el texto que desea que aparezca (ejemplo: id=A0001, name=Salir..... para un label sería el texto a validar: Continuar operación)
     * @author jmoreno
     */
    public static void esperaObjeto(String tipoObjeto, String idObjeto){
    	int intentos = 0;
    	while(true){
    		if(tipoObjeto != null && tipoObjeto.equalsIgnoreCase("label")){
    			if(Mdl_Variables.driver.getPageSource().toLowerCase().contains(idObjeto.toLowerCase()))
    			{
    				return;	//encontró el texto
    			}else
    			{
    				try{
    	    			switch(Identificadores.getValue(idObjeto.toLowerCase().split("=")[0])){
    	    				case xpath:
    	    					Mdl_Variables.driver.findElement(By.xpath(idObjeto.split("=",2)[1]));
    	    					break;
    	    				case name:
    	    					Mdl_Variables.driver.findElement(By.name(idObjeto.split("=")[1]));
    	    					break;
    	    				case classname:
    	    					Mdl_Variables.driver.findElement(By.className(idObjeto.split("=")[1]));
    	    					break;
    	    				case cssselector:
    	    					Mdl_Variables.driver.findElement(By.cssSelector(idObjeto.split("=")[1]));
    	    					break;
    	    				case id:
    	    					Mdl_Variables.driver.findElement(By.id(idObjeto.split("=")[1]));
    	    					break;
    	    				case linktext:
    	    					Mdl_Variables.driver.findElement(By.linkText(idObjeto.split("=")[1]));
    	    					break;
    	    				case partiallinktext:
    	    					Mdl_Variables.driver.findElement(By.partialLinkText(idObjeto.split("=")[1]));
    	    					break;
    	    				case tagname:
    	    					Mdl_Variables.driver.findElement(By.tagName(idObjeto.split("=")[1]));
    	    					break;
    	    				case novalue:
    	    					System.out.println("(Mdl_Choucair.esperaObjeto) No definió correctamente el id del objeto: " + idObjeto);
    	    		    		Mdl_Evidenciamiento.Evidencia_Log(false, "(Mdl_Choucair.esperaObjeto) No defini󠣯rrectamente el id del objeto: " + idObjeto);
    	    					Mdl_Evidenciamiento.Ingreso_Datos_Log();
    	    					Mdl_Variables.boolAction = 0;
    	    					return;
    	    			}
    	    			return;	//el objeto ya está en la pantalla
        			}catch(Throwable e){
    	    			//no ha aparecido, sigue validando
    	    		}
    			}
    		}else{
    			try{
	    			switch(Identificadores.getValue(idObjeto.toLowerCase().split("=")[0])){
	    				case xpath:
	    					Mdl_Variables.driver.findElement(By.xpath(idObjeto.split("=",2)[1]));
	    					break;
	    				case name:
	    					Mdl_Variables.driver.findElement(By.name(idObjeto.split("=")[1]));
	    					break;
	    				case classname:
	    					Mdl_Variables.driver.findElement(By.className(idObjeto.split("=")[1]));
	    					break;
	    				case cssselector:
	    					Mdl_Variables.driver.findElement(By.cssSelector(idObjeto.split("=")[1]));
	    					break;
	    				case id:
	    					Mdl_Variables.driver.findElement(By.id(idObjeto.split("=")[1]));
	    					break;
	    				case linktext:
	    					Mdl_Variables.driver.findElement(By.linkText(idObjeto.split("=")[1]));
	    					break;
	    				case partiallinktext:
	    					Mdl_Variables.driver.findElement(By.partialLinkText(idObjeto.split("=")[1]));
	    					break;
	    				case tagname:
	    					Mdl_Variables.driver.findElement(By.tagName(idObjeto.split("=")[1]));
	    					break;
	    				case novalue:
	    					System.out.println("(Mdl_Choucair.esperaObjeto) No definió correctamente el id del objeto: " + idObjeto);
	    		    		Mdl_Evidenciamiento.Evidencia_Log(false, "(Mdl_Choucair.esperaObjeto) No definió correctamente el id del objeto: " + idObjeto);
	    					Mdl_Evidenciamiento.Ingreso_Datos_Log();
	    					Mdl_Variables.boolAction = 0;
	    					return;
	    			}
	    			return;	//el objeto ya está en la pantalla
    			}catch(Throwable e){
	    			//no ha aparecido, sigue validando
	    		}
    		}
    		intentos++;
    		if(intentos > 10)
    			break;
    		try{Thread.sleep(1000);}catch(Throwable e){}	//espera un segundo
    	}
    	//pasaron los 40 segundos y el objeto no apareció en la pantalla
    	System.out.println("(Mdl_Choucair.esperaObjeto) No se carga la pantalla con el objeto: " + idObjeto);
		Mdl_Evidenciamiento.Evidencia_Log(false, "(Mdl_Choucair.esperaObjeto) No se carga la pantalla con el objeto: " + idObjeto);
		Mdl_Evidenciamiento.Ingreso_Datos_Log();
		Mdl_Variables.boolAction = 0;
    }
    
    
    /**
	 * retorna la posición separada por ";". Ej: 5;2 si encontró el dato en la fila 5, columna 2
	 * @param Valores_Recorrido
	 * @param Parametros_Adicionales
	 * @param iColumnaObjetivo
	 * @param blnInteraccion
	 * @return
	 */
	public static String Recorrer_HTMLTable2(String Valores_Recorrido, String Parametros_Adicionales, String iColumnaObjetivo, boolean blnInteraccion, String strFuncion){
		//declaración de variables
		String strMensajeError = null; //String utilizado para tener mejor claridad en el manejo de errores
		int Int_Recorrido_Fil, Int_Rowheader = 0, Int_ColTarget = 0, Tamano_Col, int_Recorrido = 0;
		//Integer Int_Recorrido_Col = 0;
		int[] Int_Recorrido_Col = {0};
		String AttachTabla = null, imgProperty = null, imgIndex = "0", chkAction = "off";
		String[] valorRetorno = new String[1];
		boolean Bool_Contiene = false, Bool_Objeto = false, bool_EncontroDato = false;
		String[] Vector_Decodificado;
		String[][] Matriz = null, Matriz_Recorrido = null;
		String strClaveBusqueda = "";
		//---------begin FAVC20130515
		String strObjetoFila = "";
		String strObjetoColumna = "";
		String strObjetoTarget = "";
		//---------end FAVC20130515
		WebElement objTabla = null;
		try{
			Vector_Decodificado = Web_Framework.GetValue(Valores_Recorrido).replace("<tab>", "").replace("<enter>", "").split("\\|");
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
				if(Parametros_Adicionales.split("\\|")[Int_Recorrido_Fil].toUpperCase().indexOf("CLAVE;") != -1){	//contiene la cadena "CLAVE;". retorna -1 si no la contiene
					strClaveBusqueda = Parametros_Adicionales.split("\\|")[Int_Recorrido_Fil].toUpperCase().split("CLAVE;")[1];
				}
				//---------begin FAVC20130515
				if(Parametros_Adicionales.split("\\|")[Int_Recorrido_Fil].toUpperCase().indexOf("OBJETO_FILA;") != -1){	//contiene la cadena "OBJETO_FILA;". retorna -1 si no la contiene
					strObjetoFila = Parametros_Adicionales.split("\\|")[Int_Recorrido_Fil].toUpperCase().split("OBJETO_FILA;")[1];
				}
				if(Parametros_Adicionales.split("\\|")[Int_Recorrido_Fil].toUpperCase().indexOf("OBJETO_COLUMNA;") != -1){	//contiene la cadena "OBJETO_COLUMNA;". retorna -1 si no la contiene
					strObjetoColumna = Parametros_Adicionales.split("\\|")[Int_Recorrido_Fil].toUpperCase().split("OBJETO_COLUMNA;")[1];
				}
				if(Parametros_Adicionales.split("\\|")[Int_Recorrido_Fil].toUpperCase().indexOf("OBJECT_TARGET;") != -1){	//contiene la cadena "OBJECT_TARGET;". retorna -1 si no la contiene
					strObjetoTarget = Parametros_Adicionales.split("\\|")[Int_Recorrido_Fil].toUpperCase().split("OBJECT_TARGET;")[1];
				}				
				//---------end FAVC20130515
			}
			//inicializa la matriz (tabla htmltd2) con los datos de la pantalla
			strMensajeError = "Obteniendo la Matriz";
			for(Int_Recorrido_Fil = 0 ; Int_Recorrido_Fil < Parametros_Adicionales.split("\\|").length ; Int_Recorrido_Fil++){
				if(Parametros_Adicionales.split("\\|")[Int_Recorrido_Fil].toUpperCase().indexOf("ATTACHTABLE") != -1){
					strMensajeError = "Inicializando AttachTable";
					AttachTabla = Web_Framework.GetValue(Parametros_Adicionales.split("\\|")[Int_Recorrido_Fil].toUpperCase().split("ATTACHTABLE;")[1]);
					//Se inicializa el objeto tabla que define la tabla htmltd2 con la que se va a interactuar
					strMensajeError = "Definiendo la tabla";
					objTabla = definirTabla(AttachTabla, strClaveBusqueda);
					if(objTabla == null){
						return null;
					}
					strMensajeError = "Cargando la matriz";
					Matriz = captura_Tabla(objTabla, Int_Rowheader, strObjetoFila, strObjetoColumna, strObjetoTarget,AttachTabla);
					if(Matriz == null){
						System.out.println("No se cargó la matriz");
						return null;
					}
				}
			}
//**************************************************************			
			Tamano_Col = Matriz[0].length;
			strMensajeError = "Redimencionando Matriz_Recorrido";
			Matriz_Recorrido = new String[Vector_Decodificado.length][2];
			strMensajeError = "Armando la matriz recorrido";
			for(Int_Recorrido_Fil = 0 ; Int_Recorrido_Fil < Vector_Decodificado.length ; Int_Recorrido_Fil++){
				if(Vector_Decodificado[Int_Recorrido_Fil].substring(0, 1).equals("-")){
					Matriz_Recorrido[Int_Recorrido_Fil][0] = "-" + Vector_Decodificado[Int_Recorrido_Fil].split("-", 3)[1];
					Matriz_Recorrido[Int_Recorrido_Fil][1] = Vector_Decodificado[Int_Recorrido_Fil].split("-", 3)[2];
				}else{
					if(Vector_Decodificado[Int_Recorrido_Fil].substring(1, 2).equals("-")){
						Matriz_Recorrido[Int_Recorrido_Fil][0] = Vector_Decodificado[Int_Recorrido_Fil].split("-", 2)[0];
						Matriz_Recorrido[Int_Recorrido_Fil][1] = Vector_Decodificado[Int_Recorrido_Fil].split("-", 2)[1];
					}else{
						if(Vector_Decodificado[Int_Recorrido_Fil].substring(2, 3).equals("-")){
							Matriz_Recorrido[Int_Recorrido_Fil][0] = Vector_Decodificado[Int_Recorrido_Fil].split("-", 2)[0];
							Matriz_Recorrido[Int_Recorrido_Fil][1] = Vector_Decodificado[Int_Recorrido_Fil].split("-", 2)[1];
						}else{
							if(Vector_Decodificado[Int_Recorrido_Fil].toLowerCase().indexOf("combobox") != -1 ||
									Vector_Decodificado[Int_Recorrido_Fil].toLowerCase().indexOf("textbox") != -1 ||
									Vector_Decodificado[Int_Recorrido_Fil].toLowerCase().indexOf("label") != -1 ||
									Vector_Decodificado[Int_Recorrido_Fil].toLowerCase().indexOf("image") != -1 ||
									Vector_Decodificado[Int_Recorrido_Fil].toLowerCase().indexOf("checkbox") != -1){
								Matriz_Recorrido[Int_Recorrido_Fil][0] = "0";
								Matriz_Recorrido[Int_Recorrido_Fil][1] = "<IGNORE>";
								Bool_Objeto = true;
								if(Vector_Decodificado[Int_Recorrido_Fil].toLowerCase().indexOf("image") != -1){ //Cuando se envía una imágen, se le debe envíar la imágen seguida con la acción. EJ: image;click  o  image;onmouseover
									imgProperty = Vector_Decodificado[Int_Recorrido_Fil].split(";")[1];
									if(Vector_Decodificado[Int_Recorrido_Fil].split(";").length == 3) //Si la celda a evaluar contiene mas de una imágen, se le indica el índice de la que se va a evaluar, contando desde 0. EJ: image;onmouseover;1
										imgIndex = Vector_Decodificado[Int_Recorrido_Fil].split(";")[2];
								}
								if(Vector_Decodificado[Int_Recorrido_Fil].toLowerCase().indexOf("checkbox") != -1){
									chkAction = Vector_Decodificado[Int_Recorrido_Fil].split(";")[1];
								}
							}else{
								Matriz_Recorrido[Int_Recorrido_Fil][0] = "0";
								Matriz_Recorrido[Int_Recorrido_Fil][1] = Vector_Decodificado[Int_Recorrido_Fil];
							}
						}
					}
				}
			}
//*************************************************			
			int[] Int_Recorrido_Fila = {0};
			bool_EncontroDato = buscarDatoEnMatriz(Vector_Decodificado, Matriz, Matriz_Recorrido, Bool_Contiene, Int_ColTarget, iColumnaObjetivo, valorRetorno, Int_Recorrido_Col, Int_Recorrido_Fila);
			if (bool_EncontroDato == false) {
				Mdl_Evidenciamiento.Evidencia_Log(false, "No se encontró el dato de búsqueda.");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
				Mdl_Variables.boolAction = 0;
				return null;
			}
			//Si encontró el objeto en la matriz
			if(!blnInteraccion)
				return valorRetorno[0];		

			if (strFuncion == "htmltd2"){
				interaccionElementoHtmlTable(Bool_Objeto, objTabla, Vector_Decodificado, Int_Recorrido_Col, Int_Recorrido_Fila, iColumnaObjetivo, Bool_Contiene, strObjetoFila, strObjetoColumna, strObjetoTarget);
			}else{
				interaccionElementoDivTable(Bool_Objeto, objTabla, Vector_Decodificado, Int_Recorrido_Col, Int_Recorrido_Fila, iColumnaObjetivo, Bool_Contiene, strObjetoFila, strObjetoColumna, strObjetoTarget);
			}	
		}catch(Throwable e){
			System.out.println("(Mdl_Choucair - Recorrer_HTMLTable2) Se presentó un error: " + strMensajeError + " - " + e.getMessage());
			Mdl_Evidenciamiento.Evidencia_Log(false, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			Mdl_Variables.boolAction = 0;
			return null;
		}
		
		return valorRetorno[0];
	}
	//----------------------------------------------------------------------------------
	public static boolean buscarDatoEnMatriz(String[] Vector_Decodificado,  String[][] Matriz, String[][] Matriz_Recorrido, boolean Bool_Contiene, int Int_ColTarget, String iColumnaObjetivo, String[] valorRetorno, int[] Int_Recorrido_Col, int[] Int_Recorrido_Fil){
	
		boolean bool_EncontroDato = false;
		try
		{
		
			String strMensajeError = "";	
			int int_Recorrido = 0;
			valorRetorno[0] = "";
			Int_Recorrido_Fil[0] = Int_ColTarget - 1;
			strMensajeError = "Buscando el dato en la matriz";
			//while(!Bool_Dato){
			while(true){	//la salida del ciclo se maneja con break
				if(!Bool_Contiene){
					if(Matriz[Int_Recorrido_Col[0]][Int_Recorrido_Fil[0]].equals(Matriz_Recorrido[int_Recorrido][1]) ||
							Matriz_Recorrido[int_Recorrido][1].equalsIgnoreCase("<IGNORE>")){
						int_Recorrido++;
						if(int_Recorrido < Matriz_Recorrido.length){
							Int_Recorrido_Fil[0] += Integer.parseInt(Matriz_Recorrido[int_Recorrido][0]);
							bool_EncontroDato = true;
						}else{
							bool_EncontroDato = true;
							break;
						}
					}else{
						bool_EncontroDato = false;
						Int_Recorrido_Col[0]++;
						int_Recorrido = 0;
						Int_Recorrido_Fil[0] = Int_ColTarget - 1;
					}
				}else{ //Contiene
					//
					if(Matriz[Int_Recorrido_Col[0]][Int_Recorrido_Fil[0]].toUpperCase().indexOf(Matriz_Recorrido[int_Recorrido][1].toUpperCase()) != -1 ||
							Matriz_Recorrido[int_Recorrido][1].equalsIgnoreCase("<IGNORE>")){
						int_Recorrido++;
						if(int_Recorrido < Matriz_Recorrido.length){
							Int_Recorrido_Fil[0] += Integer.parseInt(Matriz_Recorrido[int_Recorrido][0]);
							bool_EncontroDato = true;
						}else{
							bool_EncontroDato = true;
							break;
						}
					}else{
						bool_EncontroDato = false;
						Int_Recorrido_Col[0]++;
						int_Recorrido = 0;
						Int_Recorrido_Fil[0] = Int_ColTarget - 1;
					}
				}
				if(Int_Recorrido_Col[0] >= Matriz.length){
					bool_EncontroDato = false;
					break;
				}
			}
			
			strMensajeError = "Comenzando la interacción con el objeto";
			if(bool_EncontroDato){
				
					valorRetorno[0] = (Int_Recorrido_Col[0] + 1) + ";" + (Int_Recorrido_Fil[0]+ Integer.parseInt(iColumnaObjetivo) + 1);	//Se retorna la posición en la que encontró el dato ya que se necesita en llenar_tabla
					//valorRetorno[0] = (Int_Recorrido_Col[0] + 1) + ";" + (Int_Recorrido_Fil[0]);	//Se retorna la posición en la que encontró el dato ya que se necesita en llenar_tabla

				
				
			}
			return bool_EncontroDato;
			
		}catch(Throwable e)
		{ 
			System.out.println("(Mdl_Choucair - buscarDatoEnMatriz) Se presentó un error:  - " + e.getMessage());
			Mdl_Evidenciamiento.Evidencia_Log(false, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			Mdl_Variables.boolAction = 0;
			return bool_EncontroDato;
			
		}
		
	}
	//----------------------------------------------------------------------------------
	private static String interaccionElementoHtmlTable(boolean Bool_Objeto, WebElement objTabla, String[] Vector_Decodificado, int[] Int_Recorrido_Col, int[] Int_Recorrido_Fil, String iColumnaObjetivo, boolean Bool_Contiene, String strObjetoFila, String strObjetoColumna, String strObjetoTarget){
		String valorRetorno = null, AttachTabla = null, imgProperty = null, imgIndex = "0", chkAction = "off";
		//int Int_Rowheader = 0, Int_ColTarget = 0, Tamano_Col, int_Recorrido = 0, Int_Recorrido_Col = 0;
					if(Bool_Objeto){
					switch(Web_Framework.Objetos.getValue(Vector_Decodificado[Vector_Decodificado.length - 1].split(";")[0].toLowerCase())){
						case textbox:
							try{
								objTabla.findElement(By.xpath("//tbody/tr[" + (Int_Recorrido_Col[0] + 1) + "]/td[" + (Int_Recorrido_Fil[0] + Integer.parseInt(iColumnaObjetivo) + 1) + "]/input[@type=\"text\"]")).sendKeys(Vector_Decodificado[Vector_Decodificado.length - 1].split(";")[1]);
							}catch(Throwable e){
								try{
									objTabla.findElement(By.xpath("//tr[" + (Int_Recorrido_Col[0] + 1) + "]/td[" + (Int_Recorrido_Fil[0] + Integer.parseInt(iColumnaObjetivo) + 1) + "]/input[@type=\"text\"]")).sendKeys(Vector_Decodificado[Vector_Decodificado.length - 1].split(";")[1]);
								}catch(Throwable E){
									System.out.println("Error asignando el texto en la posición " + Int_Recorrido_Col + ", " + (Int_Recorrido_Fil[0] + Integer.parseInt(iColumnaObjetivo)) + " : " + Vector_Decodificado[Vector_Decodificado.length - 1].split(";")[1] + " ---- " + e.getMessage());
									Mdl_Evidenciamiento.Evidencia_Log(false, "Error asignando el texto en la posición " + Int_Recorrido_Col + ", " + (Int_Recorrido_Fil[0] + Integer.parseInt(iColumnaObjetivo)) + " : " + Vector_Decodificado[Vector_Decodificado.length - 1].split(";")[1]);
									Mdl_Evidenciamiento.Ingreso_Datos_Log();
									Mdl_Variables.boolAction = 0;
								}
							}
							break;
						case combobox:
							//(new Select(objCelda.findElement(By.xpath(""))).selectByVisibleText(Vector_Decodificado[Vector_Decodificado.length - 1].split(";")[1]);
							try{
								(new Select(objTabla.findElement(By.xpath("//tbody/tr[" + (Int_Recorrido_Col[0] + 1) + "]/td[" + (Int_Recorrido_Fil[0] + Integer.parseInt(iColumnaObjetivo) + 1) + "]/select")))).selectByVisibleText(Vector_Decodificado[Vector_Decodificado.length - 1].split(";")[1]);
							}catch(Throwable e){
								try{
									(new Select(objTabla.findElement(By.xpath("//tr[" + (Int_Recorrido_Col[0] + 1) + "]/td[" + (Int_Recorrido_Fil[0] + Integer.parseInt(iColumnaObjetivo) + 1) + "]/select")))).selectByVisibleText(Vector_Decodificado[Vector_Decodificado.length - 1].split(";")[1]);
								}catch(Throwable E){
									System.out.println("Error seleccionando en el combobox que está en la posición " + Int_Recorrido_Col + ", " + (Int_Recorrido_Fil[0] + Integer.parseInt(iColumnaObjetivo)) + " : " + Vector_Decodificado[Vector_Decodificado.length - 1].split(";")[1] + " ---- " + e.getMessage());
									Mdl_Evidenciamiento.Evidencia_Log(false, "Error seleccionando en el combobox que está en la posición " + Int_Recorrido_Col + ", " + (Int_Recorrido_Fil[0] + Integer.parseInt(iColumnaObjetivo)) + " : " + Vector_Decodificado[Vector_Decodificado.length - 1].split(";")[1]);
									Mdl_Evidenciamiento.Ingreso_Datos_Log();
									Mdl_Variables.boolAction = 0;
								}
							}
							break;
						case label:
							try{
								if(!Bool_Contiene){
									if(!Vector_Decodificado[Vector_Decodificado.length - 1].split(";")[1].equals(objTabla.findElement(By.xpath("//tbody/tr[" + (Int_Recorrido_Col[0] + 1) + "]/td[" + (Int_Recorrido_Fil[0] + Integer.parseInt(iColumnaObjetivo) + 1) + "]")).getText().trim())){
										Mdl_Evidenciamiento.Evidencia_Log(false, "Los textos no coinciden: " + objTabla.findElement(By.xpath("//tbody/tr[" + (Int_Recorrido_Col[0] + 1) + "]/td[" + (Int_Recorrido_Fil[0] + Integer.parseInt(iColumnaObjetivo) + 1) + "]")).getText().trim() + ", " + Vector_Decodificado[Vector_Decodificado.length - 1].split(";")[1]);
										Mdl_Evidenciamiento.Ingreso_Datos_Log();
										Mdl_Variables.boolAction = 0;
									}
								}else{
									//!Mdl_Variables.driver.findElement(By.xpath("//table[@id='gridSaving']/tbody/tr[" + (Int_Recorrido_Col[0] + 1) + "]/td[" + (Int_Recorrido_Fil[0]+ Integer.parseInt(iColumnaObjetivo) )+"]")).getText()
									//objTabla.findElement(By.xpath("//tbody/tr[" + (Int_Recorrido_Col[0] + 1) + "]/td[" + (Int_Recorrido_Fil[0]+ Integer.parseInt(iColumnaObjetivo) )+"]")).getText()
									
									//!Mdl_Variables.driver.findElement(By.xpath("//table[@id='gridSaving']/tbody/tr[" + (Int_Recorrido_Col[0] + 1) + "]/td[" + (Int_Recorrido_Fil[0] + Integer.parseInt("0") + 1) + "]")).getText().trim().contains(Vector_Decodificado[Vector_Decodificado.length - 1].split(";")[1])
									if(objTabla.findElement(By.xpath("//tbody/tr[" + (Int_Recorrido_Col[0] + 1) + "]/td[" + (Int_Recorrido_Fil[0] + Integer.parseInt(iColumnaObjetivo) + 1) + "]")).getText() != null && !objTabla.findElement(By.xpath("//tbody/tr[" + (Int_Recorrido_Col[0] + 1) + "]/td[" + (Int_Recorrido_Fil[0] + Integer.parseInt(iColumnaObjetivo) + 1) + "]")).getText().trim().contains(Vector_Decodificado[Vector_Decodificado.length - 1].split(";")[1])){
										Mdl_Evidenciamiento.Evidencia_Log(false, "El texto \"" + objTabla.findElement(By.xpath("//tbody/tr[" + (Int_Recorrido_Col[0] + 1) + "]/td[" + (Int_Recorrido_Fil[0] + Integer.parseInt(iColumnaObjetivo) + 1) + "]")).getText().trim() + "\" no contiene el texto \"" + Vector_Decodificado[Vector_Decodificado.length - 1].split(";")[1] + "\"");
										//objTabla.findElement(By.xpath("//" + strObjetoFila + "[" + (Int_Recorrido_Col + 1) + "]/"+ strObjetoColumna + "[" + (Int_Recorrido_Fil + Integer.parseInt(iColumnaObjetivo) + 1) + "]")).getText();
										//objTabla.findElement(By.xpath("//LI[1]/span")).getText();
										//tbody/tr[1]/td[2]
										//*[@id='accounts_ops']/div[2]/div[3]/div/div/ul/li[1]/span
										Mdl_Evidenciamiento.Ingreso_Datos_Log();
										Mdl_Variables.boolAction = 0;
									}
								}
							}catch(Throwable e){
								try{
									if(!Bool_Contiene){
										if(!Vector_Decodificado[Vector_Decodificado.length - 1].split(";")[1].equals(objTabla.findElement(By.xpath("//tr[" + (Int_Recorrido_Col[0] + 1) + "]/td[" + (Int_Recorrido_Fil[0] + Integer.parseInt(iColumnaObjetivo) + 1) + "]")).getText().trim())){
											Mdl_Evidenciamiento.Evidencia_Log(false, "Los textos no coinciden: " + objTabla.findElement(By.xpath("//tr[" + (Int_Recorrido_Col[0] + 1) + "]/td[" + (Int_Recorrido_Fil[0] + Integer.parseInt(iColumnaObjetivo) + 1) + "]")).getText().trim() + ", " + Vector_Decodificado[Vector_Decodificado.length - 1].split(";")[1]);
											Mdl_Evidenciamiento.Ingreso_Datos_Log();
											Mdl_Variables.boolAction = 0;
										}
									}else{
										if(objTabla.findElement(By.xpath("//tr[" + (Int_Recorrido_Col[0] + 1) + "]/td[" + (Int_Recorrido_Fil[0] + Integer.parseInt(iColumnaObjetivo) + 1) + "]")).getText() != null && !objTabla.findElement(By.xpath("//tr[" + (Int_Recorrido_Col[0] + 1) + "]/td[" + (Int_Recorrido_Fil[0] + Integer.parseInt(iColumnaObjetivo) + 1) + "]")).getText().trim().contains(Vector_Decodificado[Vector_Decodificado.length - 1].split(";")[1])){
											Mdl_Evidenciamiento.Evidencia_Log(false, "El texto \"" + objTabla.findElement(By.xpath("//tr[" + (Int_Recorrido_Col[0] + 1) + "]/td[" + (Int_Recorrido_Fil[0] + Integer.parseInt(iColumnaObjetivo) + 1) + "]")).getText().trim() + "\" no contiene el texto \"" + Vector_Decodificado[Vector_Decodificado.length - 1].split(";")[1] + "\"");
											Mdl_Evidenciamiento.Ingreso_Datos_Log();
											Mdl_Variables.boolAction = 0;
										}
									}
								}catch(Throwable E){
									System.out.println("(Mdl_Choucair-htmltd2) Ocurrió un error realizando la consulta. " + E.getMessage());
									Mdl_Evidenciamiento.Evidencia_Log(false, "(Mdl_Choucair-htmltd2) Ocurrió un error realizando la consulta");
									Mdl_Evidenciamiento.Ingreso_Datos_Log();
									Mdl_Variables.boolAction = 0;
								}
							}
							break;
						case image:
							switch(imgProperties.getValue(imgProperty.toLowerCase())){
								case click:
									try{
										objTabla.findElement(By.xpath("//tbody/tr[" + (Int_Recorrido_Col[0] + 1) + "]/td[" + (Int_Recorrido_Fil[0] + Integer.parseInt(iColumnaObjetivo) + 1) + "]/input[@type=\"image\"]"));
									}catch(Throwable e){
										objTabla.findElement(By.xpath("//tr[" + (Int_Recorrido_Col[0] + 1) + "]/td[" + (Int_Recorrido_Fil[0] + Integer.parseInt(iColumnaObjetivo) + 1) + "]/input[@type=\"image\"]"));
									}
									break;
								case onmouseover:
									//objCelda.findElement(By.xpath("//")).getLocation();
									break;
							}
							break;
						case checkbox:
							try{
								String estado = objTabla.findElement(By.xpath("//tbody/tr[" + (Int_Recorrido_Col[0] + 1) + "]/td[" + (Int_Recorrido_Fil[0] + Integer.parseInt(iColumnaObjetivo) + 1) + "]/input[@type=\"checkbox\"]")).getAttribute("checked");
								if(chkAction.equalsIgnoreCase("on")){
									if(estado == null || estado.equalsIgnoreCase("false")){
										objTabla.findElement(By.xpath("//tbody/tr[" + (Int_Recorrido_Col[0] + 1) + "]/td[" + (Int_Recorrido_Fil[0] + Integer.parseInt(iColumnaObjetivo) + 1) + "]/input[@type=\"checkbox\"]")).click();
									}
								}else{
									if(estado != null && estado.equalsIgnoreCase("true")){
										objTabla.findElement(By.xpath("//tbody/tr[" + (Int_Recorrido_Col[0] + 1) + "]/td[" + (Int_Recorrido_Fil[0] + Integer.parseInt(iColumnaObjetivo) + 1) + "]/input[@type=\"checkbox\"]")).click();
									}
								}
							}catch(Throwable e){
								System.out.println("(Mdl_Choucair-htmltd2) Ocurrió un error interactuando con el checkbox. " + e.getMessage());
								Mdl_Evidenciamiento.Evidencia_Log(false, "(Mdl_Choucair-htmltd2) Ocurrió un error interactuando con el checkbox.");
								Mdl_Evidenciamiento.Ingreso_Datos_Log();
								Mdl_Variables.boolAction = 0;
							}
							break;
					}
				}else{//No es un objeto especificado por el usuario
					//checkbox
					try{
						objTabla.findElement(By.xpath("//tbody/tr[" + (Int_Recorrido_Col[0] + 1) + "]/td[" + (Int_Recorrido_Fil[0] + Integer.parseInt(iColumnaObjetivo) + 1) + "]/input[@type=\"checkbox\"]")).click();
						return valorRetorno;
					}catch(Throwable e){
						try{
							objTabla.findElement(By.xpath("//tr[" + (Int_Recorrido_Col[0] + 1) + "]/td[" + (Int_Recorrido_Fil[0] + Integer.parseInt(iColumnaObjetivo) + 1) + "]/input[@type=\"checkbox\"]")).click();
							return valorRetorno;
						}catch(Throwable E){}
					}
					//radio
					try{
						objTabla.findElement(By.xpath("//tbody/tr[" + (Int_Recorrido_Col[0] + 1) + "]/td[" + (Int_Recorrido_Fil[0] + Integer.parseInt(iColumnaObjetivo) + 1) + "]/input[@type=\"radio\"]")).click();
						return valorRetorno;
					}catch(Throwable e){
						try{
							objTabla.findElement(By.xpath("//tr[" + (Int_Recorrido_Col[0] + 1) + "]/td[" + (Int_Recorrido_Fil[0] + Integer.parseInt(iColumnaObjetivo) + 1) + "]/input[@type=\"radio\"]")).click();
							return valorRetorno;
						}catch(Throwable E){}
					}
					//button
					try{
						objTabla.findElement(By.xpath("//tbody/tr[" + (Int_Recorrido_Col[0] + 1) + "]/td[" + (Int_Recorrido_Fil[0] + Integer.parseInt(iColumnaObjetivo) + 1) + "]/input[@type=\"button\"]")).click();
						return valorRetorno;
					}catch(Throwable e){
						try{
							objTabla.findElement(By.xpath("//tr[" + (Int_Recorrido_Col[0] + 1) + "]/td[" + (Int_Recorrido_Fil[0] + Integer.parseInt(iColumnaObjetivo) + 1) + "]/input[@type=\"button\"]")).click();
							return valorRetorno;
						}catch(Throwable E){}
					}
					//image
					try{
						objTabla.findElement(By.xpath("//tbody/tr[" + (Int_Recorrido_Col[0] + 1) + "]/td[" + (Int_Recorrido_Fil[0] + Integer.parseInt(iColumnaObjetivo) + 1) + "]/input[@type=\"image\"]")).click();
						return valorRetorno;
					}catch(Throwable e){
						try{
							objTabla.findElement(By.xpath("//tr[" + (Int_Recorrido_Col[0] + 1) + "]/td[" + (Int_Recorrido_Fil[0] + Integer.parseInt(iColumnaObjetivo) + 1) + "]/input[@type=\"image\"]")).click();
							return valorRetorno;
						}catch(Throwable E){}
					}
					//link
					try{
						objTabla.findElement(By.xpath("//tbody/tr[" + (Int_Recorrido_Col[0] + 1) + "]/td[" + (Int_Recorrido_Fil[0] + Integer.parseInt(iColumnaObjetivo) + 1) + "]/a")).click();
						return valorRetorno;
						
					}catch(Throwable e){
						try{
							objTabla.findElement(By.xpath("//tr[" + (Int_Recorrido_Col[0] + 1) + "]/td[" + (Int_Recorrido_Fil[0] + Integer.parseInt(iColumnaObjetivo) + 1) + "]/a")).click();
							return valorRetorno;
						}catch(Throwable E){}
					}
				}//end if(Bool_Objeto)
		return valorRetorno;
	}
//----------------------------------------------------------------------------------	
	private static String interaccionElementoDivTable(boolean Bool_Objeto, WebElement objTabla, String[] Vector_Decodificado, int[] Int_Recorrido_Col, int[] Int_Recorrido_Fil, String iColumnaObjetivo, boolean Bool_Contiene, String strObjetoFila, String strObjetoColumna, String strObjetoTarget){
		String valorRetorno = null, AttachTabla = null, imgProperty = null, imgIndex = "0", chkAction = "off";
		//int Int_Rowheader = 0, Int_ColTarget = 0, Tamano_Col, int_Recorrido = 0, Int_Recorrido_Col = 0;
					if(Bool_Objeto){
					switch(Web_Framework.Objetos.getValue(Vector_Decodificado[Vector_Decodificado.length - 1].split(";")[0].toLowerCase())){
/*
 						//TOMAR COMO REFERENCIA LOS OBJETOS DE LA FUNCIÓN interaccionElementoHtmlTable PARA IMPLEMENTAR LOS RESPECTIVOS EN ESTA FUNCIÓN 
 						case textbox:
						case combobox:

							*/
						case label:
							try{
								if(!Bool_Contiene){
									if(!Vector_Decodificado[Vector_Decodificado.length - 1].split(";")[1].equals(objTabla.findElement(By.xpath("//" + strObjetoFila + "[" + (Int_Recorrido_Col[0] + 1) + "]/" + strObjetoTarget)).getText().trim())){
										Mdl_Evidenciamiento.Evidencia_Log(false, "Los textos no coinciden: " + objTabla.findElement(By.xpath("//" + strObjetoFila + "[" + (Int_Recorrido_Col[0] + 1) + "]/" + strObjetoTarget)).getText().trim() + ", " + Vector_Decodificado[Vector_Decodificado.length - 1].split(";")[1]);
										Mdl_Evidenciamiento.Ingreso_Datos_Log();
										Mdl_Variables.boolAction = 0;
									}
								}else{
									if(objTabla.findElement(By.xpath("//" + strObjetoFila + "[" + (Int_Recorrido_Col[0] + 1) + "]/" + strObjetoTarget)).getText() != null && !objTabla.findElement(By.xpath("//" + strObjetoFila + "[" + (Int_Recorrido_Col[0] + 1) + "]/" + strObjetoTarget)).getText().trim().contains(Vector_Decodificado[Vector_Decodificado.length - 1].split(";")[1])){
										Mdl_Evidenciamiento.Evidencia_Log(false, "El texto \"" + objTabla.findElement(By.xpath("//" + strObjetoFila + "[" + (Int_Recorrido_Col[0] + 1) + "]/" + strObjetoTarget)).getText().trim() + "\" no contiene el texto \"" + Vector_Decodificado[Vector_Decodificado.length - 1].split(";")[1] + "\"");
										//objTabla.findElement(By.xpath("//" + strObjetoFila + "[" + (Int_Recorrido_Col[0] + 1) + "]/"+ strObjetoColumna + "[" + (Int_Recorrido_Fil[0] + Integer.parseInt(iColumnaObjetivo) + 1) + "]")).getText();
										objTabla.findElement(By.xpath("//" + strObjetoFila + "[" + (Int_Recorrido_Col[0] + 1) + "]/" + strObjetoTarget)).getText();
										//tbody/tr[1]/td[2]
										//*[@id='accounts_ops']/div[2]/div[3]/div/div/ul/li[1]/span
										Mdl_Evidenciamiento.Ingreso_Datos_Log();
										Mdl_Variables.boolAction = 0;
									}
								}
							}catch(Throwable e){
								try{
									if(!Bool_Contiene){
										if(!Vector_Decodificado[Vector_Decodificado.length - 1].split(";")[1].equals(objTabla.findElement(By.xpath("//tr[" + (Int_Recorrido_Col[0] + 1) + "]/td[" + (Int_Recorrido_Fil[0] + Integer.parseInt(iColumnaObjetivo) + 1) + "]")).getText().trim())){
											Mdl_Evidenciamiento.Evidencia_Log(false, "Los textos no coinciden: " + objTabla.findElement(By.xpath("//tr[" + (Int_Recorrido_Col[0] + 1) + "]/td[" + (Int_Recorrido_Fil[0] + Integer.parseInt(iColumnaObjetivo) + 1) + "]")).getText().trim() + ", " + Vector_Decodificado[Vector_Decodificado.length - 1].split(";")[1]);
											Mdl_Evidenciamiento.Ingreso_Datos_Log();
											Mdl_Variables.boolAction = 0;
										}
									}else{
										if(objTabla.findElement(By.xpath("//tr[" + (Int_Recorrido_Col[0] + 1) + "]/td[" + (Int_Recorrido_Fil[0] + Integer.parseInt(iColumnaObjetivo) + 1) + "]")).getText() != null && !objTabla.findElement(By.xpath("//tr[" + (Int_Recorrido_Col[0] + 1) + "]/td[" + (Int_Recorrido_Fil[0] + Integer.parseInt(iColumnaObjetivo) + 1) + "]")).getText().trim().contains(Vector_Decodificado[Vector_Decodificado.length - 1].split(";")[1])){
											Mdl_Evidenciamiento.Evidencia_Log(false, "El texto \"" + objTabla.findElement(By.xpath("//tr[" + (Int_Recorrido_Col[0] + 1) + "]/td[" + (Int_Recorrido_Fil[0] + Integer.parseInt(iColumnaObjetivo) + 1) + "]")).getText().trim() + "\" no contiene el texto \"" + Vector_Decodificado[Vector_Decodificado.length - 1].split(";")[1] + "\"");
											Mdl_Evidenciamiento.Ingreso_Datos_Log();
											Mdl_Variables.boolAction = 0;
										}
									}
								}catch(Throwable E){
									System.out.println("(Mdl_Choucair-htmltd2) Ocurrió un error realizando la consulta. " + E.getMessage());
									Mdl_Evidenciamiento.Evidencia_Log(false, "(Mdl_Choucair-htmltd2) Ocurrió un error realizando la consulta");
									Mdl_Evidenciamiento.Ingreso_Datos_Log();
									Mdl_Variables.boolAction = 0;
								}
							}
							break;
/*						case image:

						case checkbox:

*/							
					}
				}else{//No es un objeto especificado por el usuario

				}//end if(Bool_Objeto)
		return valorRetorno;
	}
//----------------------------------------------------------------------------------	
	/**
	 * Procedimiento encargado de obtener la tabla de la pantalla, realiza la búsqueda por id, xpath, name... y adicional permite buscar por el título de la tabla
	 * @param attachTabla identificador con el que se buscará la tabla
	 * @param strClaveBusqueda texto de la celda 0,0 porque pueden existir dos tablas con el mismo id
	 * @return Un objeto con la tabla
	 */
	private static WebElement definirTabla(String attachTabla, String strClaveBusqueda){
		WebElement objTabla = null;
		WebElement objCelda;
		List<WebElement> objTablas = null;
		try{
			switch(Identificadores.getValue(attachTabla.split("=")[0])){
			case xpath:	
				objTablas = Mdl_Variables.driver.findElements(By.xpath(attachTabla.split("=",2)[1]));
				break;
			case name:
				objTablas = Mdl_Variables.driver.findElements(By.name(attachTabla.split("=")[1]));
				break;
			case classname:
				objTablas = Mdl_Variables.driver.findElements(By.className(attachTabla.split("=")[1]));
				break;
			case cssselector:
				objTablas = Mdl_Variables.driver.findElements(By.cssSelector(attachTabla.split("=")[1]));
				break;
			case id:
				objTablas = Mdl_Variables.driver.findElements(By.id(attachTabla.split("=")[1]));
				break;
			case linktext:
				objTablas = Mdl_Variables.driver.findElements(By.linkText(attachTabla.split("=")[1]));
				break;
			case partiallinktext:
				objTablas = Mdl_Variables.driver.findElements(By.partialLinkText(attachTabla.split("=")[1]));
				break;
			case tagname:
				objTablas = Mdl_Variables.driver.findElements(By.tagName(attachTabla.split("=")[1]));
				break;
			case novalue:
				System.out.println("(Mdl_Choucair.definirTabla) No definió correctamente el id del objeto: " + attachTabla);
	    		Mdl_Evidenciamiento.Evidencia_Log(false, "(Mdl_Choucair.definirTabla) No definió correctamente el id del objeto: " + attachTabla);
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
				Mdl_Variables.boolAction = 0;
				return null;
			}
		}catch(Throwable e){
			System.out.println("(Mdl_Choucair.definirTabla) Error definiendo la tabla: " + attachTabla + " - " + e.getMessage());
    		Mdl_Evidenciamiento.Evidencia_Log(false, "(Mdl_Choucair.definirTabla) Error definiendo la tabla: " + attachTabla);
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			Mdl_Variables.boolAction = 0;
			return null;
		}
		//Se consulta el texto de la primera celda para validar que se seleccione la tabla correcta, esto se hace cuando existen varias tablas con los mismos id
		if(strClaveBusqueda != null && !strClaveBusqueda.equals("")){
			String texto = "";
			for(WebElement tabla : objTablas){
				try{
					objCelda = tabla.findElement(By.xpath("//tbody/tr[1]/td[1]"));
				}catch(Throwable e){
					try{
						objCelda = tabla.findElement(By.xpath("//tr[1]/td[1]"));
					}catch(Throwable E){
						System.out.println("Error en Mdl_Choucair.definirTabla Recorriendo la tabla: " + e.getMessage());
						Mdl_Evidenciamiento.Evidencia_Log(false, "");
						Mdl_Evidenciamiento.Ingreso_Datos_Log();
						Mdl_Variables.boolAction = 0;
						return null;
					}
				}
				texto = objCelda.getText();
				if(texto != null && texto.toUpperCase().contains(strClaveBusqueda)){
					objTabla = tabla;
					break;
				}
			}
			if(objTabla == null){
				System.out.println("Error en Mdl_Choucair.definirTabla, no se Encontró la tabla: " + attachTabla);
				Mdl_Evidenciamiento.Evidencia_Log(false, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
				Mdl_Variables.boolAction = 0;
				return null;
			}
		}else{
			//Si no hay texto para buscar, le lleva la primer tabla que encuentre
			for(WebElement tabla : objTablas){
				objTabla = tabla;
				break;
			}
		}
		return objTabla;
	}
	/**
	 * Obtiene los datos del objeto tabla y los pasa a un array bidimensional de String
	 * @param objTabla objeto con la tabla de la pantalla
	 * @param header indica si la tabla tiene encabezado, de cuantas columnas es
	 * @return
	 */
	private static String[][] captura_Tabla(WebElement objTabla, int header, String strObjetoFila, String strObjetoColumna, String strObjectTarjet,String attachTabla) {
		String[][] matriz=null;
		try{
			int totalFilas=0, totalColumnas=0;
			if (strObjetoFila == "") {
				strObjetoFila = "tr";//el "tr" es lo más común que se encuentra en las tablas
			}
			if (strObjetoColumna == "") {
				strObjetoColumna = "td";//el "td" es lo más común que se encuentra en las tablas
			}
			
			try{
				totalFilas = (objTabla.findElements(By.tagName(strObjetoFila))).size();
				
				//2013-05-13 FAVC-YFAC: Se modifica temporalmente para verificar que funcione con la etiqueta TD si usar xpath 
				//totalColumnas = (objTabla.findElements(By.tagName("td"))).size();
				totalColumnas = objTabla.findElement(By.xpath("//" + strObjetoFila + "[" + (header +1 )+ "]")).findElements(By.tagName(strObjetoColumna)).size();
				

				List<WebElement> rows = objTabla.findElements(By.tagName(strObjetoFila));
				//List<WebElement> rows = objTabla.findElements(By.tagName(strObjetoFila));
				Iterator<WebElement> i = rows.iterator(); 
				while(i.hasNext()) {
			        WebElement row = i.next();
			        totalColumnas = row.findElements(By.tagName(strObjetoColumna)).size();
			        break;
				}
				//-----------------begin FAVC20130515
				
				if (totalColumnas == 1 && strObjectTarjet !=  ""){
					totalColumnas = 2; //Indica que el objeto será leído de un objeto especificado en el parametro adiciona en la etiqueta OBJECT_TARGET
				}
				//-----------------end FAVC20130515
			}catch(Throwable e){
				System.out.println("Error en Mdl_Choucair.captura_Tabla calculando las filas y las columnas de la matriz: " + e.getMessage());
				Mdl_Evidenciamiento.Evidencia_Log(false, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
				Mdl_Variables.boolAction = 0;
				return null;
			}
			//se redimensiona la matriz con ese tamaño
			matriz = new String[totalFilas][totalColumnas];
			for(int fila = 0 ; fila < totalFilas ; fila++){
				for(int columna = 0 ; columna < totalColumnas ; columna++){
					if(fila < header){
						matriz[fila][columna] = "";
					}else{
						try{
							//-----------------begin FAVC20130515
							if(strObjectTarjet != "" && columna == 1){
								//en este caso la columna se lee con el indice de la fila (especificidad BBVA) 
								matriz[fila][columna] = objTabla.findElement(By.xpath("//" + strObjetoFila + "[" + (fila+1) + "]/" + strObjectTarjet)).getText() != null ? objTabla.findElement(By.xpath("//" + strObjetoFila + "[" + (fila  + 1) + "]/" + strObjectTarjet)).getText() : "";
							}	
							else
							{
								//matriz[fila][columna] = objTabla.findElement(By.xpath("//" + strObjetoFila + "[" + (fila + 1) + "]/" + strObjetoColumna + "[" + (columna + 1) + "]")).isDisplayed();
								//Mdl_Variables.driver.findElement(By.xpath("//table[@id='gridSaving']/tbody/tr[2]/td[1]")).getText()
								//Mdl_Variables.driver.findElement(By.xpath("//table[@id='gridSaving']/tbody/tr[2]/td[1]")).isDisplayed()
								//objTabla.findElement(By.xpath("//tr[3]/td[1]")).isDisplayed()
								// objTabla.findElements(By.xpath("//td")).size()/tr[" + (fila + 1) + "]/td[" + (columna + 1) + "]")).isDisplayed()
								//objTabla.findElement(By.xpath("//tbody/" + strObjetoFila + "[" + (fila + 1) + "]/" + strObjetoColumna + "[" + (columna + 1) + "]")).isDisplayed()
								matriz[fila][columna] = Mdl_Variables.driver.findElement(By.xpath(attachTabla.replace("xpath=", "")+"/tbody/" + strObjetoFila + "[" + (fila + 1) + "]/" + strObjetoColumna + "[" + (columna + 1) + "]")).getText(); // EZS 06-11-2014 toco trabajar con la tabla con xpath y no hacer el findelement porq no encontraba los datos de todas mis tablas
								//matriz[fila][columna] = objTabla.findElement(By.xpath("//" + strObjetoFiMdl_Variables.driver.findElementla + "[" + (fila + 1) + "]/" + strObjetoColumna + "[" + (columna + 1) + "]")).getText() != null ? objTabla.findElement(By.xpath("//" + strObjetoFila + "[" + (fila + 1) + "]/" + strObjetoColumna + "[" + (columna + 1) + "]")).getText() : "";
							}
							//-----------------end FAVC20130515
							//objTabla.findElement(By.xpath("//LI[1]/span[1]")).getText();
						}catch(Throwable e){
							System.out.println("Error en Mdl_Choucair.captura_Tabla recorriendo la tabla: " + e.getMessage());
							Mdl_Evidenciamiento.Evidencia_Log(false, "");
							Mdl_Evidenciamiento.Ingreso_Datos_Log();
							Mdl_Variables.boolAction = 0;
							return null;
						}
					}
				}
			}
			return matriz;
		}catch(Throwable e){
			System.out.println("Error cargando la matriz: " + e.getMessage());
			Mdl_Evidenciamiento.Evidencia_Log(false, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			Mdl_Variables.boolAction = 0;
			return null;
		}
	}

	/**
	 * Obtiene los datos del objeto tabla y los pasa a un array bidimensional de String
	 * @param objTabla objeto con la tabla de la pantalla
	 * @param header indica si la tabla tiene encabezado, de cuantas columnas es
	 * @return
	 */
	private static String[][] captura_DIVTabla(WebElement objTabla, int header) {
		String[][] matriz=null;
		try{
			int totalFilas=0, totalColumnas=0;
			try{
				totalFilas = (objTabla.findElements(By.tagName("tr"))).size();
				
				//2013-05-13 FAVC-YFAC: Se modifica temporalmente para verificar que funcione con la etiqueta TD si usar xpath 
				//totalColumnas = (objTabla.findElements(By.tagName("td"))).size();
				totalColumnas = objTabla.findElement(By.xpath("//tr[" + (header +1 )+ "]")).findElements(By.tagName("td")).size();
				

				List<WebElement> rows = objTabla.findElements(By.tagName("tr"));
				Iterator<WebElement> i = rows.iterator(); 
				while(i.hasNext()) {
			        WebElement row = i.next();
			        totalColumnas = row.findElements(By.tagName("td")).size();
			        break;
				}
				
			
				
				
			}catch(Throwable e){
				System.out.println("Error en Mdl_Choucair.captura_Tabla calculando las filas y las columnas de la matriz: " + e.getMessage());
				Mdl_Evidenciamiento.Evidencia_Log(false, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
				Mdl_Variables.boolAction = 0;
				return null;
			}
			//se redimensiona la matriz con ese tamaño
			matriz = new String[totalFilas][totalColumnas];
			for(int fila = 0 ; fila < totalFilas ; fila++){
				for(int columna = 0 ; columna < totalColumnas ; columna++){
					if(fila < header){
						matriz[fila][columna] = "";
					}else{
						try{
							matriz[fila][columna] = objTabla.findElement(By.xpath("//tr[" + (fila + 1) + "]/td[" + (columna + 1) + "]")).getText() != null ? objTabla.findElement(By.xpath("//tr[" + (fila + 1) + "]/td[" + (columna + 1) + "]")).getText() : "";
						}catch(Throwable e){
							System.out.println("Error en Mdl_Choucair.captura_Tabla recorriendo la tabla: " + e.getMessage());
							Mdl_Evidenciamiento.Evidencia_Log(false, "");
							Mdl_Evidenciamiento.Ingreso_Datos_Log();
							Mdl_Variables.boolAction = 0;
							return null;
						}
					}
				}
			}
			return matriz;
		}catch(Throwable e){
			System.out.println("Error cargando la matriz: " + e.getMessage());
			Mdl_Evidenciamiento.Evidencia_Log(false, "");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			Mdl_Variables.boolAction = 0;
			return null;
		}
	}
	
/*

	
	 // procedimiento para realizar el highlight en un objeto (se resalta)
	 // @param element Objeto al cual se le quiere hacer el highLight
	
	public static void highLightOn(WebElement element) {
		JavascriptExecutor js = null;
		try{
			Mdl_Variables.background = element.getAttribute("style");
			if(Mdl_Variables.background.split("=").length > 1)
				Mdl_Variables.background = Mdl_Variables.background.split("=")[1];
		}catch(Throwable e){
			//no se pudo recuperar el valor anterior, igual continúa con el proceso
		}
		try{
	        js = (JavascriptExecutor) Mdl_Variables.driver;
	        js.executeScript("arguments[0].style.background='#CECEF6';", element);
		}catch(Throwable e){
			//System.out.println("(Mdl_Choucair - highlightOn) Error en el highlight: " + e.getMessage());
		}
	}
	
	
	 // procedimiento para quitar el highlight en un objeto
	 // @param element Objeto al cual se le quiere quitar el highLight
	 
	public static void highLightOff(WebElement element) {
		JavascriptExecutor js = null;
		try{
	        js = (JavascriptExecutor) Mdl_Variables.driver;
	        js.executeScript("arguments[0].style.background='" + (Mdl_Variables.background != null && !Mdl_Variables.background.equals("") ? Mdl_Variables.background : "#FFFFFF") + "';", element);
		}catch(Throwable e){
			//System.out.println("(Mdl_Choucair - highlightOff) Error en el highlight: " + e.getMessage());
		}
	}
	
*/
	
	/**
	* Nombre Función: getProperty
	* Parámetros: ByRef arrKeyValue: Contiene el valor del campo ACTION_VALUE1 despues de haber
	*                               sido tratado con el valor de prueba que contiene el Datapool,
	*                               cuando el valor en el datapool contiene la palabra clave
	*                               getProperty, es decodificado el protocolo "checkProperty|propiedad:valor_esperado"
	*                               y el arreglo arrKeyValue es actualizado con estos valores,
	*                               esta varaible es devuelta por referencia.
	*            ByRef arrKeyIndex: Contiene el valor del campo ACTION_VALUE1 despues de haber
	*                               sido tratado con el valor de prueba que contiene el Datapool,
	*                               cuando el valor en el datapool contiene la palabra clave
	*                               getProperty, es decodificado el protocolo "getProperty|propiedad:valor_esperado"
	*                               y el arreglo arrKeyIndex es actualizado con estos valores,
	*                               esta varaible es devuelta por referencia.
	* Creada por:    Fabio Andrés Valencia Corrales
	* Fecha Creación: 20100410
	* Fecha Última Modificacion: yyyymmaa    Modificado por: (nombre de responsable)
	* Descripción Modificacion:
	*/
	public static String[] getProperty(String[] arrKeyValue){
		String[] arrayProtocol;
		for(int i = 0 ; i < arrKeyValue.length ; i++){
			if(arrKeyValue[i].toLowerCase().contains("checkproperty|")){
				arrayProtocol = arrKeyValue[i].split("\\|");
				arrKeyValue = arrayProtocol[1].split(":").length == 1 ? new String[]{arrayProtocol[1], arrKeyValue[1]} : arrayProtocol[1].split(":"); 
				return arrKeyValue;
			}
		}
		return null;
	}
	
	/**
	* Nombre Función: ClickXY
	* Parámetros: en el Action_Value1 van las cordenadas en pixeles separadas por coma 
	* 			 Ejemplo: 200,150
	* Creada por:    JAM
	* Fecha Creación: 20130228
	* Fecha Última Modificacion: yyyymmaa    Modificado por: (nombre de responsable)
	* Descripción Modificacion:
	*/
	
	public static void clickXY(String Str_Cordenadas)
	{
		
		try{
			Robot robot = null;
		    
			robot = new Robot();
			
			 robot.mouseMove(Integer.parseInt(Str_Cordenadas.split(",")[0].trim()), Integer.parseInt(Str_Cordenadas.split(",")[1].trim()));
			 //robot.mouseMove(1015, 725);
	         robot.mousePress(InputEvent.BUTTON1_MASK);
	         robot.mouseRelease(InputEvent.BUTTON1_MASK);
				
		}
		 catch (Throwable e){ 
			 	System.out.println("Error MDL_Choucair - ClickXY  " + e.getMessage());
				Mdl_Evidenciamiento.Evidencia_Log(false, "");
				Mdl_Evidenciamiento.Ingreso_Datos_Log();
				Mdl_Variables.boolAction = 0;
		 	}
	
	}
	private static void moveMouseOnObject(String strParametro1, String Parametros_Adicionales, String StrParametro2 )  
	{
		int Int_Recorrido_Fil, Int_CoreccionY=0, Int_CoreccionX=0;
		Boolean Bool_MouseClick = false;
		
		try {
			for(Int_Recorrido_Fil = 0; Int_Recorrido_Fil < Parametros_Adicionales.split("\\|").length; Int_Recorrido_Fil++){
				if(Parametros_Adicionales.split("\\|")[Int_Recorrido_Fil].toUpperCase().indexOf("MOUSEMOVEY;") != -1){ //contiene la cadena "ROWHEADER;". retorna -1 si no la contiene
					Int_CoreccionY = Integer.parseInt(Parametros_Adicionales.split("\\|")[Int_Recorrido_Fil].toUpperCase().split("MOUSEMOVEY;")[1]);
				}
				if(Parametros_Adicionales.split("\\|")[Int_Recorrido_Fil].toUpperCase().indexOf("MOUSEMOVEX;") != -1){	//contiene la cadena "COLTARGET;". retorna -1 si no la contiene
					Int_CoreccionX = Integer.parseInt(Parametros_Adicionales.split("\\|")[Int_Recorrido_Fil].toUpperCase().split("MOUSEMOVEX;")[1]);
				}
				if(Parametros_Adicionales.split("\\|")[Int_Recorrido_Fil].toUpperCase().indexOf("MOUSECLICK") != -1){	//contiene la cadena "CONTIENE;". retorna -1 si no la contiene
					Bool_MouseClick = true;
				}				
			}
			
			Web_Framework.Func_ObjectSet(Mdl_Variables.arrAction);
			Robot MouseRobot = new java.awt.Robot();
	    	MouseRobot.mouseMove(Mdl_Variables.objPerform.getLocation().getX() - Int_CoreccionX,Mdl_Variables.objPerform.getLocation().getY() - Int_CoreccionY);

			//Se da click sobre la posición actual del Mouse
			if (Bool_MouseClick)
			{
		    	MouseRobot.mousePress(InputEvent.BUTTON1_MASK);
				MouseRobot.mouseRelease(InputEvent.BUTTON1_MASK);
			}			
		} catch (Exception e) {
			System.out.println("Error moveMouseOnObject: " + e.getMessage());
		}
		
	}
	/**
	* Nombre Función: lblEstado
	* Parámetros: para llamar de otra función adaptar al FW si es necesario, recibe el string que quiere buscar en toda la pagina.
	* Fecha Última Modificacion: 2013-04-05    
	* Descripción Modificacion:
	*/
	 public static Boolean lblEstado(String strValue)
	    {
	                   try 
	                   {
	                                   Assert.assertEquals(Mdl_Variables.driver.findElement(By.cssSelector("BODY")).getText().matches(("^[\\s\\S]*" + strValue + "[\\s\\S]*$")),true);
	                                   return true;
	                   } catch (Throwable e) {
	                	   System.out.println("NO se encontro String: " + strValue );
	                       return false;
	                   }
	    }
	 
	 
	 
	 
	 
	    //Función migrada por FAVC 20130521
	    
	    //Función migrada por FAVC 20130521
	    public static  void GUARDAR_CONTENIDO(String strObjeto, String strColumna, String strParametroAdicional, String strValor)
	    {
	    	/*
	        'strObjeto: contiene el origen del dato:
	        '   Si tiene la palabra Externo significa que tiene un protocolo diferente:
	        '   EXTERNO|NOMBRE_ESCENARIO:CASO_PRUEBA:CAMPO_ORIGEN
	        '   dt_nom_campo: cuando es un valor que se quiere transferir dentro del mismo datapool (de un campo a otro)
	        '   tipoobjeto:idnetificador: cuando se requiere el valor de un objeto, normalmene textbox, label. Ejemplo: textbox:@id='name'
	        'strColumna: Contiene el destino, puede ser dt_nombre_campo, o el nombre normal Nombre_campo
	        '           Si tiene la palabra Externo significa que tiene un protocolo diferente:
	        '           EXTERNO|NOMBRE_ESCENARIO:CASO_PRUEBA:CAMPO_DESTINO
	        '           El caso de prueba puede ser un entero o un valor referenciado de un campo del datapool y caso actual (e.g dt_siguiente_pantalla)
	        'strParametroAdicional: Si tiene la palabra concatenar significa que tiene un protocolo diferente:
	        '           Es utilizada en los casos en el que el valor del datapool tiene una parte dinámica y una estática
	        '           concatenar|separador=<separador>:posicion=<posicion_en_el_vector> 
	        '			CONCATENAR|separador=<|>:posicion=0
	        '           Ejemplo: Valor en el datapool: 811020579|COMBOBOX;Respaldo financiero
	        'strValor: Contiene el valor a guardar en el destino, se usará siempre y cuando la variable strObjeto no trae un objeto
	        'strParametro3: concatenar:<VALOR>|COMBOBOX;Bloquear
	        */
	        //---------------------------------------
	        //OLS - 2011-03-25
	        //---------------------------------------
	        String[] arrObjeto;
	        String strAux  = "";
	        String strSQLaux  = "";
	        //'Dim strNombreObjeto() As String
	        //'MAS - 2011Abr15 - Se modifica para controlar datapool con FILL y errores con TRY-CATH
	        String strColumna1 = "";
	        String strColOrigen = "";
	        String strColumnaDestino;
	        String[] arrParametroAdicional;

	        //'Dim arrAuxiliar() As String

	        String[] arrAux;
	        String[] arrValorDP;
	        String[] arrValores;
	        String strCasoPruebaDestino  = "";//Mdl_Choucair.P_CASO_ACTUAL;
	        String strTablaDestino = Mdl_Variables.P_Str_Tabla;//.P_Str_Datos;
	        String strValorConcatenar = "";
	        String strSeparador = "NOTIENESEPARADOR";
	        String strPosicion = "0";
	        int i;

	        String strCasoPruebaOrigen = "";
	        String strTablaOrigen = "";
	        String strColumnaOrigen = "";
	        String dt_ColumnaDestino = "";
	        String strColumnaInicial= "";
	        //Dim rs As New ADODB.Recordset()
	        ResultSet rs = null;
	        //rs = CreateObject("ADODB.Recordset")
	        String dataColumnaDestino = "";
	        try{
	        	strCasoPruebaDestino  = Mdl_Variables.Rst_Tabla.getString("id_caso");//Mdl_Choucair.P_CASO_ACTUAL;
		        Statement stmt = Mdl_Variables.Cnn.createStatement(rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
		        //rs = stmt.executeQuery("SELECT * FROM TBL_OBJETOS WHERE PANTALLA = '" + Mdl_Variables.P_Str_Tabla + "'");
	            //--------------------------------------------------------------
	            //Implementación para guardar datos en escenarios externos
	            strColumnaInicial = strColumna;
	            //Parametros_Adicionales.split("\\|")[Int_Recorrido_Fil].toUpperCase().indexOf("CLAVE;")
	            if(strColumna.toLowerCase().indexOf("externo") != -1)
	            {
	                //'EXTERNO|dt_Campo
	                //'EXTERNO|NOMBRE_ESCENARIO:CASO_PRUEBA:CAMPO_DESTINO
	                arrValores = strColumna.split("\\|");
	                if(arrValores[1].toLowerCase().startsWith("dt_")){//REVISAR
	                //if(arrValores[1].StartsWith("dt_", StringComparison.OrdinalIgnoreCase)){
	                	Web_Framework.GetValue(arrValores[1]);
	                    if(arrValores[1] == "<IGNORE>")return;
	                }

	                strTablaDestino = arrValores[1].split(":")[0];
	                Web_Framework.GetValue(strTablaDestino); //dt_nombrecampo
	                strCasoPruebaDestino = arrValores[1].split(":")[1];
	                if(strCasoPruebaDestino.toLowerCase().startsWith("dt_")){
	                	strCasoPruebaDestino = Web_Framework.GetValue(strCasoPruebaDestino);
	                }
	                //'strCasoPruebaDestino = IIf(Mid(strCasoPruebaDestino, 1, 3) = "dt_", Choucair.GetValue(strCasoPruebaDestino), strCasoPruebaDestino)
	                strColumnaDestino = arrValores[1].split(":")[2];
	                strColumna = strColumnaDestino;
	                if(strCasoPruebaDestino == "<IGNORE>") return; //FAVC20120222 se agrega condicion para cuando no es necesario realizar el cambio
	                if(strCasoPruebaDestino == "" || !isNumeric(strCasoPruebaDestino)){
	                	//Integer.parseInt(strCasoPruebaDestino);
	                    throw new RuntimeException("Error en los parámetros de la función.");
	                }

	                if(strParametroAdicional.toLowerCase().indexOf("concatenar") != -1){
	                    strSQLaux = "SELECT " + strColumnaDestino + " FROM " + strTablaDestino + "_D WHERE ID_CASO = " + strCasoPruebaDestino;
	                    rs = stmt.executeQuery(strSQLaux);
	                    rs.next();
	                    dataColumnaDestino = rs.getString(strColumnaDestino);
	                }
	            }else{
	            	if(strColumna.substring(0, 3).equals("dt_"))
	            		strColumnaDestino = strColumna.split("_", 2)[1];//REVISAR : Tuvo cambios respecto al original
	            	else
	            		strColumnaDestino = strColumna;
	                //strColumnaDestino = IIf(Mid(strColumna, 1, 3) = "dt_", Split(strColumna, "_", 2, 1)[1], strColumna);
	            }	        	

	            if(strParametroAdicional.toLowerCase().indexOf("concatenar") != -1)
	            
	            {
	                arrParametroAdicional = strParametroAdicional.split("\\|", 2);
	                arrAux = arrParametroAdicional[1].split(":");
	                for(i = 0; i <= arrAux.length; i++){ //REVISAR Ciclo y límite si es <=
	                    if (arrAux[i].toLowerCase().indexOf("separador") > 0) strSeparador = arrAux[i].split("=")[1];
	                    if (arrAux[i].toLowerCase().indexOf("posicion") > 0) strPosicion = arrAux[i].split("=")[1];
	                }
	
	                strValorConcatenar = arrParametroAdicional[1];
	                if(strColumna.substring(0, 3) == "dt_") strColumnaDestino = strColumna.split("_", 2)[1];//REVISAR Cambió respecto a la original
	                else strColumnaDestino = strColumna;
	                //strColumnaDestino = IIf(Mid(strColumna, 1, 3) = "dt_", Split(strColumna, "_", 2, 1)(1), strColumna);

	            }else{
	            	if(strParametroAdicional.toLowerCase().indexOf("constante") >= 0) {
		                arrAux = strParametroAdicional.split(":", 2);
		                strValor = arrAux[1];
	            	}
	            }
         //--------------------------------------------------------------

	            if (strObjeto.toLowerCase().indexOf("externo") != -1)
	                arrObjeto = strObjeto.split("\\|");
	            else
	                arrObjeto = strObjeto.split(";"); //'**
	            
	            strColumna1 = strColumna;
	            strColumna = Web_Framework.GetValue(strColumna);	       
	            
	            if(!strColumna.equals("<IGNORE>")){    //'MAS - 2011Abr18
             //'arrObjeto = strObjeto.Split(";")
             //'--------------------------------------------------------------------------
	                if(arrObjeto[arrObjeto.length-1].substring(0, 3).equals("dt_")) { //'FAVC20110907 Condición nueva, para poder trasladar valores entre campos, no solamente de un objeto a un campo
	
	                    strColOrigen = arrObjeto[arrObjeto.length-1];
	                    strColOrigen = Web_Framework.GetValue(strColOrigen);
	                    if(strColOrigen.equals("<IGNORE>")) System.exit(0);//Exit Try
	                    strAux = strColOrigen;
	                }else{
	                	if(arrObjeto.length <= 0) 
	                		strAux = strValor; //'el objeto está vacio y el dato a guardar llega por parametro en la variable strValor
	                //}
	                	else{
		                    if(arrObjeto[0].toLowerCase().indexOf("externo") >= 0){ //'DFGA 20120417 Se agrega manejo para campo origen de copiar valores de tablas externas
		                        //'EXTERNO|dt_Campo
		                        //'EXTERNO|NOMBRE_ESCENARIO:CASO_PRUEBA:CAMPO_DESTINO
		                        arrValores = strObjeto.split("\\|");
		                        //if(arrValores[1].startsWith("dt_", StringComparison.OrdinalIgnoreCase){
		                        if(arrValores[1].toLowerCase().startsWith("dt_")){
		                            Web_Framework.GetValue(arrValores[1]);
		                            if(arrValores[1] == "<IGNORE>") return;
		                        }
		
		                        strTablaOrigen = arrValores[1].split(":")[0];
		                        Web_Framework.GetValue(strTablaOrigen); //'dt_nombrecampo
		                        strCasoPruebaOrigen = arrValores[1].split(":")[1];
		                        if(strCasoPruebaOrigen.startsWith("dt_"))
		                        	strCasoPruebaOrigen = Web_Framework.GetValue(strCasoPruebaOrigen);
		                        else
		                        	strCasoPruebaOrigen = strCasoPruebaOrigen;
		                        //strCasoPruebaOrigen = IIf(Mid(strCasoPruebaOrigen, 1, 3) = "dt_", Choucair.GetValue(strCasoPruebaOrigen), strCasoPruebaOrigen)
		                        strColumnaOrigen = arrValores[1].split(":")[2];
		
		                        if(strCasoPruebaOrigen == "<IGNORE>")  return;//Exit Sub 'FAVC20120222 se agrega condicion para cuando no es necesario realizar el cambio
		
		                        if(strCasoPruebaOrigen == "" || !isNumeric(strCasoPruebaOrigen))
		                            throw new RuntimeException("Error en los parámetros de la función.");
		
		                        strSQLaux = "SELECT " + strColumnaOrigen + " FROM " + strTablaOrigen + "_D WHERE ID_CASO = " + strCasoPruebaOrigen;
		                        rs = stmt.executeQuery(strSQLaux);
		                        rs.next();
		                        strAux = rs.getString(strColumnaOrigen);
				            }//END if(arrObjeto[0].toLowerCase().indexOf("externo") > 0)
			                else{
			                	//----------------------------
			                	arrAux = arrObjeto[0].split(":");
			                	if(arrAux.length <= 0) return;//protocolo mal definido, se sale de la función
			                	
			                	arrAux[1] = Web_Framework.GetValue(arrAux[1]);
			                	//----------------------------
		                        Web_Framework.Func_ObjectSet(arrAux);
		                        
             	

		                    	//if(arrAux[0].toLowerCase() == "textbox" || arrAux[0].toLowerCase() == "textarea" || arrAux[0].toLowerCase() == "label")
		                        if(arrAux[0].toLowerCase() == "textbox" || arrAux[0].toLowerCase() == "textarea" || arrAux[0].toLowerCase() == "label")
		                        {
		                        	//GMJT: CON GET TEXT NO CAPTURA EL VALOR SE REEMPLAZO POR VALUE
		                            //strAux = Mdl_Variables.objPerform.getText();
		                        	
			                        
			                        if(Mdl_Variables.objPerform.getAttribute("readonly") != null &&Mdl_Variables.objPerform.getAttribute("readonly").equalsIgnoreCase("readonly")){
			                        	//setAttribute(Mdl_Variables.objPerform, "ReadOnly", "");
			                        	setAttribute("arguments[0].removeAttribute(arguments[1])",Mdl_Variables.objPerform, "ReadOnly", "");
			                        	strAux = Mdl_Variables.objPerform.getAttribute("value");
			                        	setAttribute("arguments[0].setAttribute(arguments[1], arguments[2])",Mdl_Variables.objPerform ,"ReadOnly", "readonly");
			    					} else
			    					{
			    						strAux = Mdl_Variables.objPerform.getText();
			    						
			    					}
		                        	
		                        }
		                        else
		                        {
		                        	
		                            //strAux = Replace(Replace(Mdl_Variables.objPerform.getText().trim(), Chr(13), ""), Chr(10), ""); // FAVC20110822 Se agrega Trim para limpiar el dato
		                        	
		                        	//GMJT: CON GET TEXT NO CAPTURA EL VALOR SE REEMPLAZO POR VALUE
		                        	//strAux = Mdl_Variables.objPerform.getText().trim().replace("[\n\r]", "");// FAVC20110822 Se agrega Trim para limpiar el dato
		                        	
		                        	
			                        if(Mdl_Variables.objPerform.getAttribute("readonly") != null && Mdl_Variables.objPerform.getAttribute("readonly").equalsIgnoreCase("true")){
			                        	//setAttribute(Mdl_Variables.objPerform, "ReadOnly", "");
			                        	setAttribute("arguments[0].removeAttribute(arguments[1])",Mdl_Variables.objPerform, "ReadOnly", "");
			                        	strAux = Mdl_Variables.objPerform.getAttribute("value").trim().replace("[\n\r]", "");// FAVC20110822 Se agrega Trim para limpiar el dato
			                        	setAttribute("arguments[0].setAttribute(arguments[1], arguments[2])",Mdl_Variables.objPerform ,"ReadOnly", "readonly");
			    					}  else{
			    						
			    						if(Mdl_Variables.objPerform.getAttribute("value") != null)
			    							strAux = Mdl_Variables.objPerform.getAttribute("value").trim().replace("[\n\r]", "");
			    						else
			    							strAux = Mdl_Variables.objPerform.getText().trim().replace("[\n\r]", "");
			    						//strAux = Mdl_Variables.objPerform.getText().trim().replace("[\n\r]", "");// FAVC20110822 Se agrega Trim para limpiar el dato
			    						//strAux = Mdl_Variables.objPerform.getAttribute("value").trim().replace("[\n\r]", "");
			    					}
			                        
			    					}
		                        	
		                        	
		                        }
		                    }
	                	}
	                }

             //**arrValorDP = Split(strColumna, strSeparador) 'separar el protocolo
             //-- DFGA 20120419 - Se agrega manejo de concatenar a escenarios externos .
             if((strColumnaInicial.toLowerCase().indexOf("externo") > 0) && (strParametroAdicional.toLowerCase().indexOf("concatenar") != -1))
                 arrValorDP = dataColumnaDestino.split(strSeparador);
             else
                 arrValorDP = strColumna.split(strSeparador);

             //'verificar si la posicón es intermedia y tiene valores de posiciones tipo n-
             //if(arrValorDP[Integer.parseInt(strPosicion)].split("-").length > 0){
             if(arrValorDP[Integer.parseInt(strPosicion)].split("-").length > 1){
                 /*
             	arrAux = arrValorDP[Integer.parseInt(strPosicion)].split("-");
                 arrAux[1] = strAux;
                 arrValorDP[Integer.parseInt(strPosicion)] = join(arrAux, "-");
                 strAux = join(arrValorDP, strSeparador);
                */
             }
             else{
                 arrValorDP[Integer.parseInt(strPosicion)] = strAux; //'reemplazar el valor de la posición especifica por el valor nuevo
                 strAux = join(arrValorDP, strSeparador); //'unir nuevamente el protocolo para que quede listo y se pueda actualizar
             }
             //'--------------------------------------------------------------------------
             //' DFGA 13022012-  Se agrega este IF, ya que si se llama la esta función sin pasar por Siguente_Pantalla que es donde se defina la variable Choucair.P_CASO_ACTUAL, esta viene en 0.
             if(strCasoPruebaDestino == "0")
                 strCasoPruebaDestino = Mdl_Variables.Rst_Tabla.getString("id_caso");//Choucair.P_CASO_ACTUAL_PRINCIPAL;

	    	    try{	
	                strSQLaux = "UPDATE " + strTablaDestino + "_D SET " + strColumnaDestino + " = '" + strAux + "' WHERE ID_CASO = " + strCasoPruebaDestino;
	                Mdl_Variables.Cnn.prepareCall(strSQLaux).execute();
		    	}catch(Exception ex){
		    		System.out.println("(Mdl_Choucair - GUARDAR_CONTENIDO) Ocurrió un error actualizando el campo destino: " + ex.getMessage());
		    	}
	        
	    
//END if(strColumna != "<IGNORE>")
     }catch(Throwable ex)
     {
     	System.out.println("(Mdl_Choucair - GUARDAR_CONTENIDO): " + ex.getMessage());
     }
}
//---------------------------------------------------------------------------------------------
	    
	    
	    
	    
	    
	  //---------------------------------------------------------------------------------------------
	    public static boolean isNumeric( String s ){
	        try{
	            double y = Double.parseDouble( s );
	            return true;
	        }
	        catch( NumberFormatException err ){
	            return false;
	        }
	        }



	    /**
	     ** FAVC tomado de http://www.java2s.com/Code/Java/Data-Type/Splitandjoinstrings.htm
	     * Joins the specified parts separating each from one another with the 
	     * specified delimiter.  If delim is null, then this merely returns the 
	     * concatenation of all the parts.
	     * 
	     * @param parts the strings to be joined
	     * @param delim the char(s) that should separate the parts in the result
	     * @return a string representing the joined parts.
	     */
	    public static String join(String[] parts, String delim) {
	        StringBuilder result = new StringBuilder();
	        
	        for (int i = 0; i < parts.length; i++) {
	            String part = parts[i];
	            result.append(part);
	            if (delim != null && i < parts.length-1) {
	                result.append(delim);
	            }        
	        }
	        return result.toString();
	    }
	    
	    
	    public static  void cnx_destino(String strConexion) throws SQLException, ClassNotFoundException
	    {
	    	String GINO="";
	    	ResultSet Rst_Gino;
	    	//Connection Conn;
	    	
	    	try
	    	{

	    	DriverManager.registerDriver(new com.ibm.as400.access.AS400JDBCDriver());
	    	//Class.forName("com.ibm.as400.access.AS400JDBCDriver");
	    	//Conn = DriverManager.getConnection("jdbc:as400://192.168.0.252;naming=system;libraries=PRUCYFILES,IBSR04M08,PRUBUFILES","PRUJDBC","PRUJDBC");
	    	Mdl_Variables.CnnAs400 = DriverManager.getConnection("jdbc:as400://192.168.0.226;naming=system;libraries=PRUCYFILES","PRUJDBC","PRUJDBC");
	    	//Conn = DriverManager.getConnection("jdbc:as400://192.168.0.226;naming=system;libraries=PRUCYFILES","PRUJDBC","PRUJDBC");
	    	
	    	
	    	if (Mdl_Variables.CnnAs400 != null) {
	    		 System.out.println("Conexion exitosa!");
	    		} else {
	    		    System.out.println("Conexion fallida!");
	    		}
	    	/*
	    	
	    	//System.out.println(Conn.state);
	    	
	    	Rst_Gino = Mdl_Variables.CnnAs400.prepareCall("SELECT * FROM CUMST").executeQuery();
	    	
	    	while(Rst_Gino.next())
	    	{
	    		
	    		GINO=Rst_Gino.getString("CUSIDN").trim();
	    		System.out.println(GINO);
	    	}
	    	
	    	
	    	//Statement stmt = Conn.createStatement();
	    	//ResultSet rs = stmt.executeQuery("SELECT * FROM ACMST WHERE ACMACC = '900007011'");
	    	//ResultSet rs = stmt.executeQuery("SELECT * FROM CUMST");
	    	System.out.println("Acierto");
	    	
	    	*/
	    	
	    	}catch(Exception e){
	    		
	    		System.out.println("Error " + e.getMessage());
	    	}
	    
	    }
	    
	

	    private static void setAttribute(String Accion,WebElement element, String attributeName, String value) {
	    	JavascriptExecutor js = (JavascriptExecutor) Mdl_Variables.driver;
	    	
	    	//Para hacer un Set a un atributo
	        //js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, attributeName, value);
	    	
	    	//Para eliminar un atributo x completo
	    	if (value =="")
	    		js.executeScript(Accion, element, attributeName);
	    	else
	    		js.executeScript(Accion, element, attributeName, value);
	      }
	    
	    
	    
	    private static void cargar_datos(String str_parametro1, String str_parametro2, String str_parametro3) throws SQLException, IOException {
	    	
	    	 //GMJT02032015

            //#####################################################################################################

           //# Funcionalidad Cargar_Datos: Se usa para cargar datos en el caso actual que se encuentre en ejecuci�n (Funcina para siguiente pantalla)
	    	
           //# Precondici�n: Realizar conexion a la BD del cual se obtendran los datos (Solo toma en cuenta el primer registro por ende se esperas que el query no genere mas de 1 registro para el caso).

           //# str_parametro1: Contiene el Query o ruta del archivo .sql.

           //# str_parametro2: Contiene la condicion del Query

           //# str_parametro3: Parametro adicional.

            //####################################################################################################

           String desc_paso="";
		try

           {

                       String strCampoDP = ""; //nombre de la columna de la cual se carga el dato y a la cual se graba
                       String strValorDP = ""; //valor en el datapool : valor de la columna de la cual se carga el dato
                       String strQueryCliente = ""; //query

                       int x = 0;

                       String[] arrValores = null;
                       String str_parametro1Aux = null;
                       
                       desc_paso = ""; //query


                      //-----------------------------------------------------------------------------

                       if (str_parametro2.indexOf("dt_") + 1 != 0)

                       {

                                   arrValores = Web_Framework.GetValue(str_parametro2).split("[|]", -1);

                       }

                       else

                       {

                                   arrValores = str_parametro2.split("[|]", -1);

                       }



                       str_parametro1Aux=Web_Framework.GetValue(str_parametro1);

                       strQueryCliente = Func_ExtraeArchivo(str_parametro1Aux);

                       if (strQueryCliente.equals("Error"))

                       {

                                //Stop 
                    	   		desc_paso = "La ruta del archivo no es correcta";
               					Mdl_Evidenciamiento.Evidencia_Log(false,desc_paso);
            					Mdl_Evidenciamiento.Ingreso_Datos_Log();
            					System.out.println(desc_paso);
            					              					
               					Mdl_Evidenciamiento.CADENA_PASOS(desc_paso);

                                return;

                       }



                       //Ya se tiene el query remplazamos los valores de la condicion
                       //GMJT SE CAMBIO OBJECT POR INT
                       for (int i = 0; i <= (arrValores.length) - 1; i++)
                       {

                                   if (arrValores[i] == null || arrValores[i].length() == 0)

                                   {

                                               break;

                                   }

                                   if (Mdl_Variables.Rst_Tabla.getString(arrValores[i]).toString().equals("<IGNORE>"))

                                   {

                                               return;

                                   }

                                   strQueryCliente = strQueryCliente.replace("<" + arrValores[i] + ">", Mdl_Variables.Rst_Tabla.getString(arrValores[i]).toString());

                       }



                   /*    if (Mdl_Variables.Rst_MDMigracionDestino.State == 1)

                       {

                    	   Mdl_Variables.Rst_MigracionDestino.close();

                       }*/

                       //Mdl_Variables.Rst_MigracionDestino.close();

                   //    java.time.LocalDateTime dtTiempoIniciodst = java.time.LocalDateTime.MIN;

                   //    dtTiempoIniciodst = java.time.LocalDateTime.now();

                       Mdl_Variables.Rst_MigracionDestino = Mdl_Variables.CnnAs400.prepareCall(strQueryCliente).executeQuery();
                       
                       
                       
                       Mdl_Variables.Rst_MDMigracionDestino = Mdl_Variables.Rst_MigracionDestino.getMetaData();
                       
                       
                       

/*                       
                       //Recorremos el Recordset de destino y solo lo hacemos para la primera fila.

                       if ( Mdl_Variables.Rst_MDMigracionDestino. != 0)
                       {
                    	   
*/

                                   //Do While Choucair.rsMigracionDestino.EOF = False

                                   //Cargamos los datos al datapool corespondiente como si lo hicieramos con LAD

                                   // Rescatamos el valor a una variable temporal y lo ingresamos al DP

                                   // contamos el numero de columans del query
                       
                       			   int temporal =  Mdl_Variables.Rst_MDMigracionDestino.getColumnCount();
                       			   
                         try

                         {
                       			   
                       			 while(Mdl_Variables.Rst_MigracionDestino.next())
                                 {


                                  
                                    	 for (x = 1; x <= Mdl_Variables.Rst_MDMigracionDestino.getColumnCount(); x++)

                                   {



                                                          strCampoDP = Mdl_Variables.Rst_MDMigracionDestino.getColumnName(x).toString();
                                                          
                                                          
                                                         
                                                          if ( ! Mdl_Variables.Rst_Tabla.getString(strCampoDP).toString().equals("<IGNORE>"))

                                                          {

                                                                      //20121212 WMN. VALIDACION DE SI EL CAMPO ES NULO LO COLOCAMOS COMO NULL EN EL DATAPOOL

                                                                      if (Mdl_Variables.Rst_Tabla.getString(strCampoDP) == null)

                                                                      {

                                                                                  strValorDP = "";

                                                                                  //Aqui hay que modificar o definir por estrategia que valor se escribe en el datapool cuando el valor de la BD es nulo o simplemente no se cargar ningun valor

                                                                      }

                                                                      else

                                                                      {

                                                                                  strValorDP = Mdl_Variables.Rst_MigracionDestino.getString(strCampoDP).toString();

                                                                      }

                                                                      
                                                                      Mdl_Variables.Cnn.prepareCall("UPDATE " + Mdl_Variables.P_Str_Tabla + "_D SET " + strCampoDP + " = '" + strValorDP + "' WHERE ID_CASO = " + Mdl_Variables.Rst_Tabla.getString("ID_CASO")).execute();
                                                                      //Mdl_Variables.Cnn.prepareCall("UPDATE " + Mdl_Variables.P_Str_Tabla + "_D SET " + strCampoDP + " = '" + strValorDP + "' WHERE ID_CASO = '" + Mdl_Variables.Rst_Tabla.getString("ID_CASO")+"'");
                                                                      
                                                                      


                                                          }

                                                          }

                                               }
                       			 
                       			 				desc_paso = "Se ejecutó el query para la carga de datos de manera exitosa ";





                                   }
                         
                         catch (RuntimeException ex)

                         {

                                    //Sale por que no existe la columna

                         }

                                   //Choucair.rsMigracionDestino.MoveLast()

                                   // Loop



/*                       }

                       else

                       {

                                   //Stop

                                   //ENVIAMOS MENSAJE DE QUE EL QUERY NO GENERO RESULTADOS DE BUSQUEDA.

                                   desc_paso = "Query de carga solicitado no tiene Resultados";

                       }
*/                       

                       Mdl_Variables.Rst_MigracionDestino.close();

                       //desc_paso = "Cargar datos del query: " & strQueryCliente



           }

           catch (RuntimeException ex)

           {
/*
                       //Stop

                       Mdl_Variables.Descripcion_Acierto_Error = "Error en el Paso #" + Mdl_Variables.P_PASO_ACTUAL + ". " + ex.getMessage();

                       Mdl_Variables.P_Tipo_Log = Mdl_Evidenciamiento.errores.MSG_Error;

                       desc_paso = "No es posible cargar el query solicitado.";
                       
                       */
                       
                       
                       
                       
                       
                       
                     //Stop 
           	   		desc_paso = "La ruta del archivo no es correcta";
      				Mdl_Evidenciamiento.Evidencia_Log(false,desc_paso);
   					Mdl_Evidenciamiento.Ingreso_Datos_Log();
   					System.out.println(desc_paso);
   					              					
      					Mdl_Evidenciamiento.CADENA_PASOS(desc_paso);

           }

           //ANM : Pasos evidencia

           Mdl_Evidenciamiento.CADENA_PASOS(desc_paso);
	    	
	    	
	    	
	    	
	      }
	    
	    
	    public static String Func_ExtraeArchivo(String strRuta) throws IOException

        {

                               //strRuta = "\\src\\PRUEBA.sql";

                               //strRuta = "F:\\TV WC7\\CHOUCAIR\\Auto\\Eclipse VM\\PCH\\src\\PRUEBA.sql";

                               //strRuta = "Select * from awkehwihd";

	    						//strRuta = "\\\\192.168.1.201\\";

                              

                               String varRutaAplicativo = Mdl_Variables.Ruta_Aplicativo;

                              

                               String strQueryCliente = "";

                               String sLine = "";

                               boolean bolLeer = true;





                               //WMN20140203: Se carga el  query destino en la variable *strQueryDestino*

                               if (strRuta.indexOf("\\") + 1 == 1)

                               {

                                           if (strRuta.indexOf("\\\\") + 1 > 0)

                                           {

                                                       //La ruta se mantiene

                                           }

                                           else

                                           {

                                        	   strRuta = varRutaAplicativo + strRuta;

                                           }

                               }

                               else

                               {

                                           int inttemp;

                                           inttemp = strRuta.indexOf(":\\");

                                          

                                           if (strRuta.indexOf(":\\") == 1)

                                           {

                                                       //La ruta se mantiene

                                           }

                                           else

                                           {

                                                       //Se ha enviado un query mas no una ruta

                                                       strQueryCliente = strRuta;

                                                       // ya no es necesari leer el archivo

                                                       bolLeer = false;

                                           }



                               }



                               if (bolLeer == true)

                               {

                                           // leemos el archivo plano

                                       try

                                       {

                                                      

                                                       FileReader objReader = new FileReader(strRuta);

                                                       BufferedReader bufReader = new BufferedReader(objReader);

                                                       while((sLine = bufReader.readLine())!=null)

                                       {

                                           //sLine = bufReader.readLine();

                                                                   strQueryCliente = strQueryCliente + sLine + "\r\n";

                                           System.out.println(sLine);

                                       }

                                       bufReader.close();

                                       objReader.close();

                                                      

                                                      

                                           }

                                           catch (RuntimeException ex)

                                           {

                                                       //LA ruta enviada no es correcta

                                                       //return "Error";

                                                       strQueryCliente = "Error";

                                           }



                               }
							return strQueryCliente;



                               //return strQueryCliente;

                               //strQueryCliente = strQueryCliente;

                    //-------------------------------------FIN--------------------------



        }
	    
	    public static String obtenerRangos(String valor){
	        if(valor == null || valor.equalsIgnoreCase("null") || valor.equals(""))
	            return "";
	        StringBuffer retorno = new StringBuffer();
	        String[] valores = valor.split(",");
	        for(int i = 0 ; i < valores.length ; i++){
	            if(valores[i].contains("-")){
	                if(Integer.parseInt(valores[i].split("-")[0]) < Integer.parseInt(valores[i].split("-")[1])){
	                    for(int j = Integer.parseInt(valores[i].split("-")[0]) ; j <= Integer.parseInt(valores[i].split("-")[1]) ; j++){
	                        if(!retorno.toString().contains(j+""))
	                            retorno.append(j + ",");
	                    }
	                }else{
	                    for(int j = Integer.parseInt(valores[i].split("-")[1]) ; j <= Integer.parseInt(valores[i].split("-")[0]) ; j++){
	                        if(!retorno.toString().contains(j+""))
	                            retorno.append(j + ",");
	                    }
	                }
	            }else{
	                if(!retorno.toString().contains(valores[i]))
	                    retorno.append(valores[i] + ",");
	            }
	        }
	        return retorno.substring(0, retorno.length()-1);
	    }

	    public static String TRATA_NULL(String valor, String VALOR_DEF)
	    {
	        if (valor == null || valor.trim().equals(""))
	        {    
	            return VALOR_DEF;
	        }
	        else
	        {
	            return valor;
	        }
	    }


}