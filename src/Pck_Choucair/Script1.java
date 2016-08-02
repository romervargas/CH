package Pck_Choucair;
//WebDriver
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class Script1 {

	
    public static void main(String[] arg) throws Exception 
    {
		//Asignaciones
    	//Mdl_Variables.StrIexplorePath = "C:\\Archivos de programa\\Internet Explorer\\iexplore.exe ";
		//Mdl_Variables.StrIexplorePath = "C:\\Archivos de programa\\Mozilla Firefox\\firefox.exe";
		//Mdl_Variables.P_Str_Base_Datos_SOcr = "Z: \\DB_SERVIDOR_OCR\\DB_SERVIDOR_OCR.mdb";
		//Mdl_Variables.Ruta_Aplicativo= "C:\\Evidencia";
		//Mdl_Variables.Ruta_Aplicativo= "\\CHOUCAIR\\BNET";
    	Mdl_Variables.Ruta_Aplicativo= "\\\\NBK-416\\Documentos\\Evidencias"; 
    	//Mdl_Variables.Ruta_Aplicativo= "D:\\CHOUCAIR\\BNET";
		
		//*****Configuración*****
		while(true)
		{
			//Carga la información del schedule
		//	Mdl_Sufi.Conectar();
			Mdl_Datapool.Schedule("BBVA_BNET" ,"SQL", "", "sa", "sql2005", "NBK-416\\SQLEXPRESS");
																		
			//Validación de escenarios pendientes por ejecución
			if (Mdl_Variables.blnpendiente)
			{
				//Cierra pantallas para iniciar con un sistema limpio
				Mdl_Choucair.Cerrar_Pantallas();
				// Conecta al datapool "Ciclo", "Versión", "LimpiarDP","Tipo Ejecución"
				Mdl_Datapool.Conectar_Sql(Mdl_Variables.P_Str_Ciclo,Mdl_Variables.P_Str_Nombre_Version, Mdl_Variables.P_Bln_Limpia_Casos, Mdl_Variables.P_Str_Tipo_Ejecucion);


				//Ejecución de los scripts
				Web_Framework.Keyword_Drive();
				//Cierra pantallas para iniciar con un sistema limpio
				Mdl_Choucair.Cerrar_Pantallas();
				//Genera Html "Automatizador, "Responsable"
				Mdl_Evidenciamiento.Generar_HTML2(Mdl_Variables.P_Str_Tester, Mdl_Variables.P_Str_Arquitecto);		
				//Ingresar Registros de tiempo al Historial
				Mdl_Evidenciamiento.VerificarEnvioCorreo("parametrosCorreo");
				
				Mdl_Evidenciamiento.Ingreso_Datos_Historia();
			}
			// Cierra Sql y deja el sistema limpio
			Mdl_Datapool.Desconectar_Sql();
			//***Fin Configuración***
			break; 
		}
	}

}
