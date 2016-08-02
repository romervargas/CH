package Pck_Choucair;
import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.firefox.*;

import Pck_Choucair.Mdl_Choucair.Funciones_Ch;

/**
 * Clase que se encarga de la gestión de las ejecuciones
 *
 */
public class Web_Framework 
{
	private static WebDriver driver;
	public enum Keyword 
	{
		check,	context,	fw_cliente,	fw_ch, fw_tuya, fw_bnet,
		launchapp,	loop,	msgbox,	navigate,	perform, safari, ie, chrome, firefox,
		presskeys,	presskeys_as400,	textexist,	wait, novalue; 
		public static Keyword getValue(String str)
	    {
	        try {
	            return valueOf(str);
	        }
	        catch (IllegalArgumentException ex) {
	            return novalue;
	        }
	    }
	}
	public enum Perform 
	{
		activate,	activateparent,	attach,	button,
		checkbox,	clear,	click,	clickbutton,
		clickoncoordinate,	close,	combobox,	commanbutton,
		conectar,	desconectar,	doubleclick,	exist,
		htmltd2,	image,	itemspresent,	mousedown,
		mousemove,	mouseup, press,	radobutton,
		random,	rightclick,	rightclickoncoordinate,	select,
		selectindex,	selectrange,	selectrangeindex,	set,
		setdate,	setfocus,	textbox,	textselect,
		type,	uncheck_set, novalue;
		public static Perform getValue(String str)
	    {
	        try {
	            return valueOf(str);
	        }
	        catch (IllegalArgumentException ex) {
	            return novalue;
	        }
	    }
	}
	public enum Check
	{
		anchor,	check,	checked,	columncount,
		count,	enabled,	exist,	innertext,
		itemexist,	itemscount,	itemspresent,	label,
		len,	readonly,	rowcount,	text,
		textexist,	visible,	name,	XML, novalue;
		public static Check getValue(String str)
	    {
	        try {
	            return valueOf(str);
	        }
	        catch (IllegalArgumentException ex) {
	            return novalue;
	        }
	    }
	}
	public enum BasedeDatos 
	{
		access,	sql,	mysql,	sql2008,
		access2007,	oracle,	oracle10g,	db2,
		as400;
	}
	public enum Objetos
	{
		novalue, as400,	button,	cablered,	checkbox,
		combobox,	commandbutton,	db_object,	frame,
		htmltd2,	image,	label,	link,
		listbox,	radiobutton,	tabla,	table,
		td,	textbox,	xml,	htmltable,
		scrollbar	,browser	,anchor	,dialog,
		div,tabbar;
		
		public static Objetos getValue(String str)
	    {
	        try {
	            return valueOf(str);
	        }
	        catch (IllegalArgumentException ex) {
	            return novalue;
	        }
	    }
	}
	
	public enum tecla
    {
    	enter, tab, f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, escape, delete, end, alt_f4, ctrl_s, ctrl_p;
    };
	
    public enum Identificadores
    {
    	xpath,	name,		classname,			cssselector,
    	id,		linktext,	partiallinktext,	tagname,		value,	novalue;
    	public static Identificadores getValue(String str)
	    {
	        try {
	            return valueOf(str);
	        }
	        catch (IllegalArgumentException ex) {
	            return novalue;
	        }
	    }
    }
    
    /**
     * Procedimiento encargado de recorrer cada caso prueba y ejecutar los pasos correspondientes.
     * 
     * @throws Exception
     */
    public static void Keyword_Drive() throws Exception 
    {
    	int Int_TipEjecucion = 1;
    	
    	while ( Mdl_Variables.Rst_Tabla.next()) 
        {
    			
        	Mdl_Variables.P_Entrada_Sistema = new Date();
        	Mdl_Variables.Rst_Coordenadas.absolute(0);
        	
			
			//SE ADICIONA PARA LIMPIAR EL DESIGN_STEP DEL CASO A EJECUTAR CON RESPECTO A LA EJECUCIÓN ANTERIOR
		    	String desc_paso = "";
		    	Mdl_Evidenciamiento.CADENA_PASOS(desc_paso);  

	    	
        	
            while (Mdl_Variables.Rst_Coordenadas.next())
            {
            	Mdl_Variables.P_Tipo_Log = 0;
            	
            	
            	if (Mdl_Variables.Rst_Coordenadas.getInt("ID") == 24) 
            	{
            		Int_TipEjecucion = Int_TipEjecucion;
            	}

            	
            	if (Mdl_Variables.Rst_Coordenadas.getInt("TIPO_EJECUCION") >= Int_TipEjecucion && Mdl_Variables.Rst_Coordenadas.getString("AUTOMATE").trim().equalsIgnoreCase("r")) 
				{
					Keyword_Web();
				}

            	if (Mdl_Variables.P_Tipo_Log == 2)
				{
					Mdl_Choucair.Cerrar_Pantallas();
					break;
				}
				if (Mdl_Variables.P_Tipo_Log == 5)
				{
					Int_TipEjecucion = 1;
					Mdl_Variables.Str_Mensaje = "";
					Mdl_Variables.boolAction = 0;
					Mdl_Variables.P_Tipo_Log = 0;
					Mdl_Variables.Rst_Coordenadas.absolute(0);
					Mdl_Variables.intRowCount = 0;
					Mdl_Choucair.Cerrar_Pantallas();
				}
				Mdl_Variables.intRowCount +=1;
				//WMN2150420: Reinicializo la variable de paso para la evidencia.
				Mdl_Variables.strPasosEvidencia = "";
            }
            // valida que no se presenten errores inesperados
            Valida_LogIf();
        	Mdl_Evidenciamiento.Actualizar_RegistroUtilizado();
            if (Mdl_Variables.P_Tipo_Log == 2)
            	Int_TipEjecucion = 1;
            else
            	Int_TipEjecucion = 2;    
            Mdl_Variables.rowCountDataSheet = Mdl_Variables.rowCountDataSheet + 1;
            Mdl_Variables.boolAction = 0;
            Mdl_Variables.intRowCount = 0;
            Mdl_Variables.P_Tipo_Log = 0;
            Mdl_Variables.Str_Mensaje = "";
            //WMN2150420: Reinicializo la variable de pasos evidencia.
            Mdl_Variables.strPEvidencia = "";
            Mdl_Variables.Str_Mensaje = "";
	    	

        }
	}
 
    /**
     * Procedimiento encargado de la ejecución de un paso en específico.
     * 
     * @throws Exception
     */
	public static void Keyword_Web() throws Exception
	{
		String desc_paso;
		try 
		{
			Mdl_Variables.initial = Mdl_Variables.Rst_Coordenadas.getString("ACTION").toLowerCase().trim();
			if (Mdl_Variables.Rst_Coordenadas.getString("OBJECT") + "" != "")
			{
				Mdl_Variables.objName = (Mdl_Variables.Rst_Coordenadas.getString("OBJECT") + "").trim();
				Mdl_Variables.arrAction = Mdl_Variables.objName.split(";", 2);
				Mdl_Variables.arrAction[0] = Mdl_Variables.arrAction[0].trim().toLowerCase();
				if (Mdl_Variables.arrAction.length > 1)
				{
					Mdl_Variables.strNombreObjeto = Mdl_Variables.arrAction[1].replace("dto_","");
				}
				
				for  (int inti = 0; inti <= Mdl_Variables.arrAction.length -1;inti ++)
				{
					Mdl_Variables.arrAction[inti] = GetValue(Mdl_Variables.arrAction[inti]);
					Mdl_Variables.arrAction[inti] = GetValue(Mdl_Variables.arrAction[inti]).trim();
				}
			}
						
		    if (Mdl_Variables.Rst_Coordenadas.getString("Action_value1")+ "" != "")
		    {
				Mdl_Variables.arrKeyValue = GetValue((Mdl_Variables.Rst_Coordenadas.getString("Action_value1") + "").trim()).trim().split(":", 2);
				for  (int inti = 0 ;inti <= Mdl_Variables.arrKeyValue.length -1;inti ++)
				{
					Mdl_Variables.arrKeyValue[inti] = GetValue(GetValue(Mdl_Variables.arrKeyValue[inti]));
				}
		    }

            if (Mdl_Variables.arrKeyValue.length == 1)
            {
            	if (Mdl_Variables.arrKeyValue[0].trim().equalsIgnoreCase("<IGNORE>"))
            	{
					Mdl_Variables.boolAction = 1;
					return;
            	}
			}
            if (Mdl_Variables.arrKeyValue.length == 2)
            {
            	if (Mdl_Variables.arrKeyValue[1].trim().equalsIgnoreCase("<IGNORE>"))
            	{
					Mdl_Variables.boolAction = 1;
					return;
            	}
			}	
            if (Mdl_Variables.arrAction.length == 1)
            {
            	if (Mdl_Variables.arrAction[0].trim().equalsIgnoreCase("<IGNORE>"))
            	{
					Mdl_Variables.boolAction = 1;
					return;
            	}
			}
            if (Mdl_Variables.arrAction.length == 2)
            {
            	if (Mdl_Variables.arrAction[1].trim().toUpperCase().contentEquals("<IGNORE>"))
            	{
					Mdl_Variables.boolAction = 1;
					return;
            	}
			}	
            
			switch (Keyword.getValue(Mdl_Variables.initial.toLowerCase()))	
			{
				
			
				case perform:
					Func_ObjectSet(Mdl_Variables.arrAction);
					if (Mdl_Variables.P_Tipo_Log == 2) return;
					Func_Perform(Mdl_Variables.arrKeyValue,Mdl_Variables.arrAction);
					
					
					break;
				case check:
					Func_ObjectSet(Mdl_Variables.arrAction);
					if (Mdl_Variables.P_Tipo_Log == 2) return;
					Func_Check(Mdl_Variables.arrKeyValue,Mdl_Variables.arrAction);
					break;
				case fw_ch:
					Mdl_Choucair.Fw_Ch(Mdl_Variables.objName, Mdl_Variables.Rst_Coordenadas.getString("ACTION_VALUE1") + "", Mdl_Variables.Rst_Coordenadas.getString("ACTION_VALUE2") + "", Mdl_Variables.Rst_Coordenadas.getString("PARAMETRO_ADICIONAL") + "");
					break;
				case fw_bnet:
						Mdl_Bnet.Fw_bnet(Mdl_Variables.objName, Mdl_Variables.Rst_Coordenadas.getString("ACTION_VALUE1") + "", Mdl_Variables.Rst_Coordenadas.getString("ACTION_VALUE2") + "", Mdl_Variables.Rst_Coordenadas.getString("PARAMETRO_ADICIONAL") + "");
					break;
				case wait:
//					if (Mdl_Variables.Rst_Coordenadas.getString("ACTION_VALUE1").split("|||")[0].equals("dt_ID_CASO")){
//						 String AV1[] = Mdl_Variables.Rst_Coordenadas.getString("ACTION_VALUE1").split("|");
//						 String strParamName; 
//						
//						for  (int inti = 1; inti <= AV1.length -1;inti ++)
//						{
//						//	Mdl_Variables.Rst_Coordenadas.getString("ACTION_VALUE1").split(";")[1];
//							try{Thread.sleep(Integer.parseInt(Mdl_Variables.arrAction[0])*1000);}catch(Throwable e){}
//						}
//						Funciones_Ch.getValue(Mdl_Variables.Rst_Coordenadas.getString("ID_CASO"));
//						break;
//					}
//					else
//					{
						try{Thread.sleep(Integer.parseInt(Mdl_Variables.arrAction[0])*1000);}catch(Throwable e){}
						
						
						//Mdl_Variables.strPasosEvidencia = "Se realiza la espera de "+Mdl_Variables.arrAction[0]+" segundos.";
						//Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
						
						break;
//					}
					
				case launchapp:
						// call a la funcion		
					launchapp(Mdl_Variables.Tipo_Aplicativo);
					
					break;
				case presskeys:
					Func_presskey(Mdl_Variables.arrAction);
					break;
				case loop:
					break;
				case context:
					break;
				case msgbox:
					JOptionPane.showMessageDialog(null, GetValue(Mdl_Variables.arrAction[0]), "Mensaje", JOptionPane.INFORMATION_MESSAGE);
					break;
				case navigate:
					Mdl_Variables.driver.get(GetValue(Mdl_Variables.arrAction[0])); 
					Mdl_Variables.boolAction = 1;
					return;
				case presskeys_as400:
					break;
			
			}
		}
		catch(Exception e) 
		{
			System.out.println("(Web_Framework - Keyword_Web) Se presentó un error: " + e.getMessage());
			e.printStackTrace();
			Mdl_Evidenciamiento.Evidencia_Log(false, " Keyword_Web - " + Mdl_Variables.initial + " - Paso Número: " + Mdl_Variables.Rst_Coordenadas.getString("ID"));
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			Mdl_Variables.boolAction = 0;
		}
	}
	
	public static void Func_ObjectSet(String arrAction[])
	{
		try
		{
			Mdl_Choucair.esperaObjeto(arrAction[0], arrAction[1]);//Mdl_Variables.driver.getTitle()
			if (Mdl_Variables.P_Tipo_Log == 2)	return;
			//Mdl_Choucair.highLightOff(Mdl_Variables.objPerform);
			switch (Identificadores.valueOf(arrAction[1].split("=")[0].toLowerCase())){
				case xpath:
					Mdl_Variables.objPerform = Mdl_Variables.driver.findElement(By.xpath(arrAction[1].split("=",2)[1]));
					if (arrAction[0].toLowerCase().contentEquals("combobox") || arrAction[0].toLowerCase().contentEquals("listbox") || arrAction[0].toLowerCase().contentEquals("button"))
					{
						Mdl_Variables.objPerform_Select = new Select(Mdl_Variables.driver.findElement(By.xpath(arrAction[1].split("=",2)[1])));
					}
					break;
				case name:
					Mdl_Variables.objPerform = Mdl_Variables.driver.findElement(By.name(arrAction[1].split("=")[1]));
					if (arrAction[0].toLowerCase().contentEquals("combobox") || arrAction[0].toLowerCase().contentEquals("listbox"))
					{
						Mdl_Variables.objPerform_Select = new Select(Mdl_Variables.driver.findElement(By.name(arrAction[1].split("=")[1])));
					}
					break;
				case classname:
					Mdl_Variables.objPerform=  Mdl_Variables.driver.findElement(By.className(arrAction[1].split("=")[1]));
					if (arrAction[0].toLowerCase().contentEquals("combobox") || arrAction[0].toLowerCase().contentEquals("listbox"))
					{
						Mdl_Variables.objPerform_Select = new Select(Mdl_Variables.driver.findElement(By.className(arrAction[1].split("=")[1])));
					}
					break;
				case cssselector:
					Mdl_Variables.objPerform=  Mdl_Variables.driver.findElement(By.cssSelector(arrAction[1].split("=")[1]));
					if (arrAction[0].toLowerCase().contentEquals("combobox") || arrAction[0].toLowerCase().contentEquals("listbox"))
					{
						Mdl_Variables.objPerform_Select = new Select(Mdl_Variables.driver.findElement(By.cssSelector(arrAction[1].split("=")[1])));
					}
					break;
				case id:
					Mdl_Variables.objPerform=  Mdl_Variables.driver.findElement(By.id(arrAction[1].split("=")[1]));
					if (arrAction[0].toLowerCase().contentEquals("combobox") || arrAction[0].toLowerCase().contentEquals("listbox"))
					{
						Mdl_Variables.objPerform_Select = new Select(Mdl_Variables.driver.findElement(By.id(arrAction[1].split("=")[1])));
					}
					break;
				case linktext:
					Mdl_Variables.objPerform=  Mdl_Variables.driver.findElement(By.linkText(arrAction[1].split("=")[1]));
					if (arrAction[0].toLowerCase().contentEquals("combobox") || arrAction[0].toLowerCase().contentEquals("listbox"))
					{
						Mdl_Variables.objPerform_Select = new Select(Mdl_Variables.driver.findElement(By.linkText(arrAction[1].split("=")[1])));
					}
					break;
				case partiallinktext:
					Mdl_Variables.objPerform=  Mdl_Variables.driver.findElement(By.partialLinkText(arrAction[1].split("=")[1]));
					if (arrAction[0].toLowerCase().contentEquals("combobox") || arrAction[0].toLowerCase().contentEquals("listbox"))
					{
						Mdl_Variables.objPerform_Select = new Select(Mdl_Variables.driver.findElement(By.partialLinkText(arrAction[1].split("=")[1])));
					}
					break;
				case tagname:
					Mdl_Variables.objPerform=  Mdl_Variables.driver.findElement(By.tagName(arrAction[1].split("=")[1]));
					if (arrAction[0].toLowerCase().contentEquals("combobox") || arrAction[0].toLowerCase().contentEquals("listbox"))
					{
						Mdl_Variables.objPerform_Select = new Select(Mdl_Variables.driver.findElement(By.tagName(arrAction[1].split("=")[1])));
					}
					break;
				case novalue:
					System.out.println("(Web_Framework - Func_ObjectSet) No se encontró el id");
					Mdl_Evidenciamiento.Evidencia_Log(false, "");
					Mdl_Evidenciamiento.Ingreso_Datos_Log();
					Mdl_Variables.boolAction = 0;
			}
			//Mdl_Choucair.highLightOn(Mdl_Variables.objPerform);
		}
		catch(Throwable ex)
		{
			String Datos_Script="";
			for  (int inti = 0; inti <= arrAction.length -1;inti ++)
			{
				Datos_Script += " - " + GetValue(Mdl_Variables.arrAction[inti]).trim();
			}
			Mdl_Evidenciamiento.Evidencia_Log(false, " Object Set " + Datos_Script);
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			Mdl_Variables.boolAction = 0;
			System.out.println("(Web_Framework - Func_ObjectSet) Error inicializando el objeto. arrAction[0]: " + arrAction[1] + " - arrAction[1]: " + arrAction[1] + " - " + ex.getMessage());
		}
	}
	
	public static void Func_Perform(String arrKeyValue[],String arrAction[]) throws Exception 
	{				
		String desc_paso;
		
		Mdl_Variables.boolAction = 0;
		try
		{	
			/*if (arrKeyValue[1].toLowerCase().contentEquals("on") && Mdl_Variables.objPerform.isSelected()==false)
				Mdl_Variables.objPerform.click();
			else 
			{
				if (Mdl_Variables.objPerform.isSelected())
					Mdl_Variables.objPerform.click();
			}*/	
			
			/*-------------------------------------------------------------------------------------------------
			FAVC20120103 Identificar si el valor que trae del datapool corresponde a la función checkproperty
			if(Mdl_Variables.arrkeyindex.length > 0){ //se espera que el segundo parámetro contenga una de las palabras claves
			En la tabla de pasos se debe poner 
			Action: perform
			 object: tipo_objeto;id_objeto (ej: label;dto_LBL_TEXTO)
			ACTION_VALUE1 : instrucción a ejecutar (ej:dt_LBL_TEXTO las instrucciones se manejan desde la tabla de datos)
			En la tabla de datos se debe enviar checkproperty|propiedad:valor
			Donde checkproperty es palabra clave y siempre debe ir
			propiedad es la propiedad que se quiere validar del objeto (ver Func_Check)
			y valor es el dato para hacer la validación
			ej: checkproperty|text:Cuentas de ahorro valida que el label LBL_TEXTO diga Cuentas de ahorro
			*/
			for(int i = 0 ; i < arrKeyValue.length ; i++){
				//se setea el valor para que entre al case correspondiente
				if(arrKeyValue[i].toLowerCase().contains("checkproperty")){
					arrKeyValue = Mdl_Choucair.getProperty(arrKeyValue);
					if(arrKeyValue == null){
						System.out.println("(Web_Framework - Func_Perform) Ocurrió un error validando el checkproperty");
						Mdl_Evidenciamiento.Evidencia_Log(false,  "");
						Mdl_Evidenciamiento.Ingreso_Datos_Log();
						Mdl_Variables.boolAction = 0;
						return;
					}
					Func_Check(arrKeyValue, arrAction);
					return;
				}
			}
			
			switch (Perform.valueOf(arrKeyValue[0].toLowerCase())){
				case attach:

				break;
				case clear:
					Mdl_Variables.objPerform.clear();
					Mdl_Variables.boolAction = 1;
					return;
				case click:					
					if (arrAction[0].toLowerCase().contentEquals("combobox") )
					{
						Mdl_Variables.driver.findElement(By.id(arrAction[1].split("=")[1])).click();
					}else
					{
						Mdl_Variables.objPerform.click();
						Mdl_Variables.boolAction = 1;
					}
					try{
						//se valida que no hay alert activos
						Mdl_Variables.driver.switchTo().alert();
					}catch(NoAlertPresentException e){
						//Se elimina lo que está después del # en el link
						if(Mdl_Variables.driver.getCurrentUrl().contains("#guider=")){
							Mdl_Variables.driver.get(Mdl_Variables.driver.getCurrentUrl().split("#")[0]);
						}
					}
					Mdl_Variables.strPasosEvidencia = "Hacer click en el  "+arrAction[0]+" "+Mdl_Variables.strNombreObjeto;
					Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
					
					return;
				case clickoncoordinate:
					Mdl_Choucair.Mouse_Click ( Mdl_Variables.objPerform.getLocation().x + Integer.parseInt(GetValue(Mdl_Variables.Rst_Coordenadas.getString("ACTION_VALUE2")).split(",")[0]),Mdl_Variables.objPerform.getLocation().y + Integer.parseInt(GetValue(Mdl_Variables.Rst_Coordenadas.getString("ACTION_VALUE2")).split(",")[1]));
					Mdl_Variables.boolAction = 1;
					return;
				case doubleclick:
			        Actions action = new Actions(Mdl_Variables.driver); 
			        action.doubleClick(Mdl_Variables.objPerform); 
			        action.perform(); 
					Mdl_Variables.boolAction = 1;
					return;
				case mousemove:
					Mdl_Choucair.Mouse_Move(Mdl_Variables.objPerform.getLocation().x,Mdl_Variables.objPerform.getLocation().y);
					Mdl_Variables.boolAction = 1;
					return;
				case press:
					Mdl_Keyboard Keyboard= new Mdl_Keyboard();
					Keyboard.type(arrKeyValue[1]);
					Mdl_Variables.boolAction = 1;
					return;
				case random:
					//Se compone en Actio_Value1 por |random:numero,1,1000| 1=inicial 2=mayor
					if (arrKeyValue[1].split(",")[0].toString().toLowerCase().contentEquals("numero"))
					{
						int dato =(int)(Math.random()* Integer.parseInt(arrKeyValue[1].split(",")[2]) + Integer.parseInt(arrKeyValue[1].split(",")[1]));
						Mdl_Variables.objPerform.sendKeys(String.valueOf(dato)); 
					}else if (arrKeyValue[1].split(",")[0].toString().toLowerCase().contentEquals("texto"))
					{
						//Se compone en Actio_Value1 por |random:texto,10| donde 10 es el número de caracteres
						Mdl_Keyboard Keyboard2= new Mdl_Keyboard();
		        		Random r = new Random();
		        	    String alphabet = "abcdefghijkl,mopqrstuvwxyz";
		        	    for (int i = 0; i < Integer.parseInt(arrKeyValue[1].split(",")[1]); i++) {
							Keyboard2.type(alphabet.charAt(r.nextInt(alphabet.length())));
		        	    }
					}
					Mdl_Variables.boolAction = 1;
					return;	
				case rightclick:
					//Actions action2 = new Actions(Mdl_Variables.driver);
					//action2. ContextClick(Mdl_Variables.objPerform).Perform();
					Mdl_Variables.boolAction = 1;
					return;
				case rightclickoncoordinate:
					Mdl_Variables.boolAction = 1;
					return;
				case select:
					if(arrKeyValue[1].contains("<enter>")){
						Mdl_Variables.objPerform.click();
						String[] datos = arrKeyValue[1].split("<enter>");
						if(datos.length == 0){
							Mdl_Variables.objPerform.sendKeys(Keys.ENTER);
						}else{
							for(int i = 0 ; i < datos.length ; i++){
								if(!datos[i].equals(""))
									Mdl_Variables.objPerform_Select.selectByVisibleText(datos[i]);
								Mdl_Variables.objPerform.sendKeys(Keys.ENTER);
							}
						}
					}else{
						Mdl_Variables.objPerform_Select.selectByVisibleText(arrKeyValue[1]);
					}
					Mdl_Variables.boolAction = 1;
					return;
				case selectindex:
					Mdl_Variables.objPerform_Select.selectByIndex(Integer.parseInt(arrKeyValue[1]));
					Mdl_Variables.boolAction = 1;
					return;
				case set:
					if (arrAction[0].toLowerCase().contentEquals("checkbox") || arrAction[0].toLowerCase().contentEquals("radiobutton"))
					{
						if (arrKeyValue[1].toLowerCase().contentEquals("on") && Mdl_Variables.objPerform.isSelected()==false)
								Mdl_Variables.objPerform.click();
						else 
						{
							if (Mdl_Variables.objPerform.isSelected())
								Mdl_Variables.objPerform.click();
						}
					}else{
						try{Mdl_Variables.objPerform.clear();}catch(Throwable e){}
						if(arrKeyValue[1].toLowerCase().contains("telaleatorio")){
							String tipo = "";
							if(arrKeyValue[1].split("\\|").length > 1)
								tipo = arrKeyValue[1].split("\\|")[1];
							Mdl_Variables.objPerform.sendKeys(Mdl_Choucair.generarTelAleatorio(tipo));
						}else{
							if(arrKeyValue[1].contains("<enter>")){
								Mdl_Variables.objPerform.click();
								String[] datos = arrKeyValue[1].split("<enter>");
								if(datos.length == 0){
									Mdl_Variables.objPerform.sendKeys(Keys.ENTER);
								}else{
									for(int i = 0 ; i < datos.length ; i++){
										Mdl_Variables.objPerform.sendKeys(datos[i]);
										Mdl_Variables.objPerform.sendKeys(Keys.ENTER);
									}
								}
							}else{
								if(arrKeyValue[1].contains("<tab>")){
									Mdl_Variables.objPerform.click();
									String[] datos = arrKeyValue[1].split("<tab>");
									if(datos.length == 0){
										Mdl_Variables.objPerform.sendKeys(Keys.TAB);
									}else{
										for(int i = 0 ; i < datos.length ; i++){
											Mdl_Variables.objPerform.sendKeys(datos[i]);
											Mdl_Variables.objPerform.sendKeys(Keys.TAB);
										}
									}
								}else{
									
									Mdl_Variables.objPerform.sendKeys(arrKeyValue[1]);
								}
							}
						}
					}
					
					desc_paso = "";
					desc_paso = "Ingresar el valor: "+arrKeyValue[1]+" en el campo "+Mdl_Variables.strNombreObjeto;;
					Mdl_Evidenciamiento.CADENA_PASOS(desc_paso);
					
					Mdl_Variables.boolAction = 1;
					return;
				case setdate:
					Mdl_Variables.boolAction = 1;
					return;
				case setfocus:
					Mdl_Variables.boolAction = 1;
					return;
				case type:
					Mdl_Keyboard Keyboard2= new Mdl_Keyboard();
					Keyboard2.type(arrKeyValue[1]);
					Mdl_Variables.boolAction = 1;
					return;
				case uncheck_set:
					Mdl_Variables.objPerform.sendKeys(arrKeyValue[1]);
					Mdl_Variables.boolAction = 1;
					return;
				default:
					return;
				
			}
		}
		catch(Throwable ex)
		{
			String Datos_Script="";
			
			for  (int inti = 0; inti <= Mdl_Variables.arrAction.length -1;inti ++)
			{
				Datos_Script += " - " + GetValue(Mdl_Variables.arrAction[inti]).trim();
			}

			for  (int inti = 0 ;inti <= Mdl_Variables.arrKeyValue.length -1;inti ++)
			{
				Datos_Script += " - " + GetValue(Mdl_Variables.arrKeyValue[inti]);
			}
			System.out.println("(Web_Framework - Func_Perform) datos: arrKeyValue[0]= " + arrKeyValue[0] + "  Y  arrKeyValue[1]= " + arrKeyValue[1] + ".  Ocurrió un error en la ejecución: " + ex.getMessage());
			Mdl_Evidenciamiento.Evidencia_Log(false,  " Func_Perform " + Datos_Script);
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			Mdl_Variables.boolAction = 0;
		}
	}
	public static void Func_Check(String arrKeyValue[],String arrAction[]) throws Exception 
	{		
		Mdl_Variables.boolAction = 0;
		String desc_paso = "";
		
		try
		{
			switch (Check.valueOf(arrKeyValue[0].toLowerCase()))
			{
				case anchor:
					Mdl_Variables.boolAction = 1;
					return;
				case checked:
					Mdl_Variables.boolAction = Mdl_Evidenciamiento.Valida_Orientacion(Mdl_Variables.objPerform.isSelected() == Boolean.parseBoolean(arrKeyValue[1]));
					return;
				case columncount:
					Mdl_Variables.boolAction = 1;
					return;
				case count:
					Mdl_Variables.boolAction = 1;
					return;
				case enabled:
					Mdl_Variables.boolAction = Mdl_Evidenciamiento.Valida_Orientacion(Mdl_Variables.objPerform.isEnabled());
					return;
				case exist:
					Mdl_Variables.boolAction = 1;
					return;
				case innertext:
					Mdl_Variables.boolAction = Mdl_Evidenciamiento.Valida_Orientacion(Mdl_Variables.objPerform.getText().trim().contentEquals(arrKeyValue[1].trim()));
					return;
				case itemexist:
					Mdl_Variables.boolAction = 1;
					return;
				case itemscount:
					Mdl_Variables.boolAction = 1;
					return;
				case itemspresent:
					Mdl_Variables.boolAction = 1;
					return;
				case len:
					Mdl_Variables.boolAction = Mdl_Evidenciamiento.Valida_Orientacion(Mdl_Variables.objPerform.getText().trim().length() == Integer.parseInt(arrKeyValue[1]));
					return;
				case readonly:
					if(Mdl_Variables.objPerform.getAttribute("readonly") != null && Mdl_Variables.objPerform.getAttribute("readonly").equalsIgnoreCase("true")){
						Mdl_Variables.boolAction = 1;
					}else{
						System.out.println("(Web_Framework - Funck_Check) El campo es editable");
						Mdl_Evidenciamiento.Evidencia_Log(false,  "");
						Mdl_Evidenciamiento.Ingreso_Datos_Log();
					}
					break;
				case rowcount:
					Mdl_Variables.boolAction = 1;
					return;
				case text:
					//if (!Mdl_Variables.objPerform_Select.equals(null)){
                      //  Mdl_Variables.boolAction = Mdl_Evidenciamiento.Valida_Orientacion(Mdl_Variables.objPerform_Select.getFirstSelectedOption().getText().contentEquals(arrKeyValue[1].trim()));
                    //}else{
                        //Mdl_Variables.boolAction = Mdl_Evidenciamiento.Valida_Orientacion(Mdl_Variables.objPerform.getText().trim().contentEquals(arrKeyValue[1].trim())); 
                        Mdl_Variables.boolAction = Mdl_Evidenciamiento.Valida_Orientacion(Mdl_Variables.objPerform.getText().trim().contains(arrKeyValue[1].trim()));  
                    //}                   
                    //Mdl_Variables.boolAction = Mdl_Evidenciamiento.Valida_Orientacion(Mdl_Variables.objPerform.getText().trim().contentEquals(arrKeyValue[1].trim()));
					desc_paso = "";
					if (Mdl_Variables.boolAction == 1)
					{
						desc_paso = "Verificar el valor: "+arrKeyValue[1]+" en el campo "+Mdl_Variables.strNombreObjeto;
					}
					else
					{
						desc_paso = "<b><FONT COLOR='RED'>No se comparó correctamente el valor: "+arrKeyValue[1]+" en el campo "+Mdl_Variables.strNombreObjeto+"</font></b>";
					}
					
					
					Mdl_Evidenciamiento.CADENA_PASOS(desc_paso);
					
					return;
				case textexist:
					Mdl_Variables.boolAction = Mdl_Evidenciamiento.Valida_Orientacion(Mdl_Variables.objPerform.getText().trim().contains(arrKeyValue[1].trim()));
					return;
				case visible:
					Mdl_Variables.boolAction = Mdl_Evidenciamiento.Valida_Orientacion(Mdl_Variables.objPerform.isDisplayed());
					return;
				case check:
					String AA = Mdl_Variables.objPerform.getText().trim();
					Mdl_Variables.boolAction = Mdl_Evidenciamiento.Valida_Orientacion(Mdl_Variables.objPerform.getText().trim().equalsIgnoreCase(arrKeyValue[1].trim()));
					
					//Mdl_Variables.boolAction = Mdl_Evidenciamiento.Valida_Orientacion(Mdl_Variables.objPerform.getText().trim().contains(arrKeyValue[1].trim()));
					if (Mdl_Variables.boolAction==0)
					{
						Mdl_Evidenciamiento.Evidencia_Log(false,arrKeyValue[1]+ " diferente al de pantalla "+Mdl_Variables.objPerform.getText());
						//Mdl_Evidenciamiento.Ingreso_Datos_Log();
						
						desc_paso = "NO se comparó correctamente, el valor en DataDriven es: "+arrKeyValue[1]+" y el valor en pantalla es: "+ Mdl_Variables.objPerform.getText();
						System.out.println(desc_paso);
											}
					else
						desc_paso = "Se comparó correctamente el valor del DataDriven "+"'"+arrKeyValue[1]+"'"+ " con el valor en el Objeto :"+ Mdl_Variables.objPerform.getText();
					
					
					
					Mdl_Evidenciamiento.CADENA_PASOS(desc_paso);	
					return;
			}
		}
		catch(Throwable ex)
		{			
			String Datos_Script="";
			
			for  (int inti = 0; inti <= Mdl_Variables.arrAction.length -1;inti ++)
			{
				Datos_Script += " - " + GetValue(Mdl_Variables.arrAction[inti]).trim();
			}
	
			for  (int inti = 0 ;inti <= Mdl_Variables.arrKeyValue.length -1;inti ++)
			{
				Datos_Script += " - " + GetValue(Mdl_Variables.arrKeyValue[inti]);
			}
			
			Mdl_Evidenciamiento.Evidencia_Log(false,  " Func_Perform " + Datos_Script);
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			Mdl_Variables.boolAction = 0;
		}
	}
	
	 public static String GetValue( String strCellData )
	    {
	    	try
	    	{
		    String arrSplitCheckData[];
		    String strParamName;     

	        arrSplitCheckData = strCellData.split("_", 2);
	            if (arrSplitCheckData[0].trim().toLowerCase().contentEquals("dt"))
	            {
	                    strParamName = arrSplitCheckData[1];
	                    strCellData = Mdl_Variables.Rst_Tabla.getString(strParamName.toUpperCase());
	            }
	            else if (arrSplitCheckData[0].trim().toLowerCase().contentEquals("dto"))
	            {
	            	Statement stmt_Objeto = Mdl_Variables.Cnn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        	    ResultSet Rst_Objeto = stmt_Objeto.executeQuery("SELECT * FROM TBL_OBJETOS  WHERE PANTALLA = '" + Mdl_Variables.P_Str_Tabla + "' AND NOMBRE ='" + arrSplitCheckData[1].trim() + "'");
	        	    Rst_Objeto.next();
	        	    strCellData = Rst_Objeto.getString("OBJETO") + "";
	                Mdl_Variables.izquierda = Rst_Objeto.getInt("IZQUIERDA");
	                Mdl_Variables.Arriba= Rst_Objeto.getInt("ARRIBA");
	                Mdl_Variables.Alto= Rst_Objeto.getInt("ALTO");
	                Mdl_Variables.Ancho= Rst_Objeto.getInt("ANCHO");
	            }
	        //}
	    	}catch(Exception ex){
	    		System.out.println("Error en Web_Framework.GetValue: " + ex.getMessage());
	    	}
	    return strCellData;
	    }
    
    public static void Func_presskey(String[] arrObj)
    {
    	try
    	{
           Mdl_Keyboard keyboard = new Mdl_Keyboard();
           keyboard.type(arrObj[0]);
        }
        catch (Exception e)
 		{ 
        	System.out.println("Error en presskey");
        	System.out.println(e.getMessage());
 		}
        
    }
	public static void Valida_LogIf()
	{
		try
		{
			/*TestObject[] Vrb_objBrowser = find(atChild(".class", "Html.Dialog"));
			GuiTestObject objPerform_Existe = ((GuiTestObject)Vrb_objBrowser[0].find(atDescendant(".class","Html.DialogStatic"))[1]);
			Mdl_Evidenciamiento.Evidencia_Log(false, " Pantalla no controlada por el Script ");
			Mdl_Evidenciamiento.Ingreso_Datos_Log();
			Mdl_Variables.boolAction = 0;
			GuiTestObject objPerform_Click = ((GuiTestObject)Vrb_objBrowser[0].find(atDescendant(".class","Html.DialogButton"))[0]);
			objPerform_Click.click();*/
		}catch(Exception ex){}
	}

	public static void launchapp(String strTipoAplicativo)
	{
		try
		{
			switch (strTipoAplicativo.toLowerCase())	
			{
				case "safari":
					Mdl_Variables.driver = new SafariDriver();			              
					Thread.sleep(3000);  // Let the user actually see something!
					break;
				case "chrome":
					
					// Optional, if not specified, WebDriver will search your path for chromedriver.
					System.setProperty("webdriver.chrome.driver", "C:\\Proyectos\\BNET\\lib\\chromedriver.exe");							  
					DesiredCapabilities capabilities = DesiredCapabilities.chrome();
					capabilities.setCapability("chrome.switches", Arrays.asList("--start-maximized"));							  
					Thread.sleep(2000);  // Let the user actually see something!
					Mdl_Variables.driver = new ChromeDriver(capabilities);						  
					Thread.sleep(3000);  // Let the user actually see something!
					Mdl_Variables.driver.manage().window().maximize();
	
					break;
					
				case "firefox":
				
					Mdl_Variables.driver = new FirefoxDriver();	
					Mdl_Variables.driver.manage().window().maximize();
					
					Mdl_Variables.driver.manage().timeouts().implicitlyWait(10,	TimeUnit.SECONDS);
					Mdl_Variables.driver.manage().window().maximize();
					Thread.sleep(3000);  // Let the user actually see something!			               
					break;
					
					
				default:
					//INTERNET EXPLORER
					//Para SO de 32 bits:
					//File iedriver = new File("lib/selenium-2.28.0/IEDriverServer 32/IEDriverServer.exe");
					Thread.sleep(3000);
					File iedriver = new File("lib/IEDriverServer.exe");
					
					//Para SO de 64 bits:
					//File iedriver = new File("lib/selenium-2.28.0/IEDriverServer 64/IEDriverServer.exe");
					System.setProperty("webdriver.ie.driver", iedriver.getAbsolutePath());
					Mdl_Variables.driver = new InternetExplorerDriver();
					Mdl_Variables.driver.manage().window().maximize();
					Thread.sleep(3000);  // Let the user actually see something!
					break;					
			
				}					

			Mdl_Variables.P_Vrb_Browser = Mdl_Variables.arrAction[0];
			if (!Mdl_Variables.P_Str_RutaAmbientePrincipal.equals(""))
			{
				Mdl_Variables.driver.get(Mdl_Variables.P_Str_RutaAmbientePrincipal); 
			}
			else if (!Mdl_Variables.P_Str_RutaAmbientePrincipal.equals(""))
			{
				Mdl_Variables.driver.get(Mdl_Variables.arrAction[0]); 	
			}	
			
			Mdl_Choucair.Click_Aceptar();
			
			Mdl_Variables.strPasosEvidencia = "Se realiza el ingreso a la URL: "+Mdl_Variables.arrAction[0];
			Mdl_Evidenciamiento.CADENA_PASOS(Mdl_Variables.strPasosEvidencia);
			
		}
		catch(Exception ex)
		{
			
		}
	}

//---------------------------------------------------------------------------------------------
}