package Pck_Choucair;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.*;
import java.io.*;
// para enviar correo
import java.util.Properties;
//import javax.mail.*;
//import javax.mail.internet.*;
//import javax.activation.*;

import com.google.common.base.Converter;
//import com.steadystate.css.util.Output;


public class Mdl_Evidenciamiento 
{
    private static final boolean True = false;
	// variables usadas para autenticarse ante el servidor de correo
	public static String SMTP_AUTH_USER = "";
    public static String SMTP_AUTH_PWD  = "";
    // usadas par enviar correoEvidencia
    public static String NombreEvd;  

	/** CAA_2013-02-04 Begin
	 * Crea la estructura de las carpetas para la generación de evidencia en la ruta indicada por el parametro
	 * @param ruta : La ruta se esta armando antes de llamar esta rutina con Ruta_Aplicativo + Vrb_VersionAplicativo + P_Str_Ciclo 
	 */
	public static void crearRutaEvidencia(String ruta){
		File file = new File(ruta + "\\1. Evidencias");
		file.mkdirs();
		//file = new File(ruta + "\\6. Encabezado");
		//file.mkdirs();
		//file = new File(ruta + "\\7. Descripcion");
		//file.mkdirs();
		//file = new File(ruta + "\\8. Menús");
		//file.mkdirs();
		//file = new File(ruta + "\\9. Diagramas");
		//file.mkdirs();	 	
	}
	// CAA_2013-02-04 End

	public static void Ingreso_Datos_Log()
	{
		// CAA_2013-02-04 Begin
		   Mdl_Variables.Ruta_Evidencia = Mdl_Variables.Ruta_Aplicativo + "\\" + Mdl_Variables.Vrb_VersionAplicativo + "\\" + Mdl_Variables.P_Str_Ciclo + "\\";
		   crearRutaEvidencia(Mdl_Variables.Ruta_Evidencia);
		// CAA_2013-02-04 End
		try
		{
			if ((Mdl_Variables.P_Tipo_Log == 0 || Mdl_Variables.boolAction == 0) && Mdl_Variables.P_Tipo_Log < 2)
	        {
	        		Mdl_Variables.P_Tipo_Log = 1;
	        		Mdl_Variables.boolAction = 1;
	        }
	        
		    Mdl_Variables.P_Int_TiempoEntradaSistema = new Date().compareTo(Mdl_Variables.P_Entrada_Sistema);
		    String VRB_Tiempo = (Mdl_Variables.strTablaAnterior != null && !Mdl_Variables.strTablaAnterior.equals("") ? Mdl_Variables.strTablaAnterior : Mdl_Variables.P_Str_Tabla) + "-" + Mdl_Variables.Vrb_VersionAplicativo + "_" + Mdl_Variables.P_Str_Ciclo + "-"  +  new Date().toString().replace("/","_").replace(" ","_").replace(":","_").replace("#","");
		
			if (Mdl_Variables.P_Str_Modulo.toUpperCase().contentEquals("MIGRACION") == false)
			{
				//CAA_2013-02-04 Begin
				// Mdl_Choucair.CapturaPantalla(Mdl_Variables.Ruta_Aplicativo + "\\1. Evidencias" , VRB_Tiempo);
				Mdl_Choucair.CapturaPantalla( Mdl_Variables.Ruta_Evidencia + "\\1. Evidencias" , VRB_Tiempo);
				Thread.sleep(1);
				// CAA_2013-02-04 End
			}
			try
			{
				Mdl_Variables.Rst_Log.absolute(0);
			}catch(Exception Ex){}
			
			if (Mdl_Variables.Rst_Log.next() == false) 
			{
				Mdl_Variables.Sql_Log_Maestro = "INSERT INTO TBL_LOG_MAESTRO"  + "(NOMBRE_TABLA"  + ",MODULO"  + ",NUMERO_CICLO" + ",ROBOT_EJECUTOR" + ",VALIDADO" + ",FECHA) Values (" + "'" + (Mdl_Variables.strTablaAnterior != null && !Mdl_Variables.strTablaAnterior.equals("") ? Mdl_Variables.strTablaAnterior : Mdl_Variables.P_Str_Tabla) + "','" + Mdl_Variables.P_Str_Modulo + "','" + Mdl_Variables.P_Str_Ciclo + "','" + Mdl_Variables.P_Str_NombreMaquina + "','" + "True" + "','" + new Date() + "')";    
				Mdl_Variables.Cnn.prepareCall(Mdl_Variables.Sql_Log_Maestro).execute();
				Mdl_Variables.Rst_Log.close();
				Statement stmtLog = Mdl_Variables.Cnn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				Mdl_Variables.Rst_Log = stmtLog.executeQuery("SELECT NOMBRE_TABLA, ID_MAESTRO from TBL_LOG_MAESTRO where NOMBRE_TABLA = '" + (Mdl_Variables.strTablaAnterior != null && !Mdl_Variables.strTablaAnterior.equals("") ? Mdl_Variables.strTablaAnterior : Mdl_Variables.P_Str_Tabla) + "'");
			}
		
			Mdl_Variables.Sql_Log_Detalle = "INSERT INTO TBL_LOG_DETALLE" + "(CICLO" + ",INDICE" + ",ID_MAESTRO" + ",TIPO_LOG" + ",MENSAJE_LOG" + ",IMAGEN" + ",TIEMPO_EJECUCION_CASO, VERSION) Values (" + "'" + Mdl_Variables.P_Str_Ciclo + "'," + (Mdl_Variables.P_Str_IdEvidencia != null && !Mdl_Variables.P_Str_IdEvidencia.equals("") ? Mdl_Variables.P_Str_IdEvidencia : Mdl_Variables.Rst_Tabla.getString("ID_CASO")) + "," + Mdl_Variables.Rst_Log.getString("ID_MAESTRO") + "," + Mdl_Variables.P_Tipo_Log + ",'" + (String)(Mdl_Variables.Descripcion_Acierto_Error == "" ? "PRINT DE LOS DATOS INGRESADOS": Mdl_Variables.Descripcion_Acierto_Error).replace("'", "''") + "','" + (String)(Mdl_Variables.P_Str_Modulo.toUpperCase() != "MIGRACION"? "1. Evidencias\\" + VRB_Tiempo + ".png" : "") + "'," + Mdl_Choucair.Diferencia_Fechas(new Date(), Mdl_Variables.P_Entrada_Sistema) + " ,'" + Mdl_Variables.Vrb_VersionAplicativo + "')";
			Mdl_Variables.Cnn.prepareCall(Mdl_Variables.Sql_Log_Detalle).execute();
			//Mdl_Variables.Descripcion_Acierto_Error = "";
		}catch(Exception ex)
		{
			System.out.println("Ocurrió un error en Mdl_Evidenciamiento.Ingreso_Datos_Log: " + ex.getMessage());
		}
	}
	public static void Evidencia_Log(boolean blnResultado, String Descripcion_Adicional )
	{
		try
		{
			boolean vrbleAux=false;
			if ((Mdl_Variables.Rst_Coordenadas.getString("ACTION_VALUE1") + "").contains("dto_") || (Mdl_Variables.Rst_Coordenadas.getString("ACTION_VALUE1") + "").contains("dt_"))
			{
				vrbleAux=true;
			}
			
			if (blnResultado == true || (  vrbleAux == true && Mdl_Variables.Rst_Tabla.getString("ACIERTO_ERROR").contentEquals("0")))
			{
				Mdl_Variables.Descripcion_Acierto_Error = (Mdl_Variables.P_Str_Desc_Acc_Ant != null && !Mdl_Variables.P_Str_Desc_Acc_Ant.equals("")) ? Mdl_Variables.P_Str_Desc_Acc_Ant : Mdl_Variables.Rst_Tabla.getString("DESCRIPCION_ACIERTO");
				Mdl_Variables.Cnn.prepareCall("Update " + (Mdl_Variables.strTablaAnterior != null && !Mdl_Variables.strTablaAnterior.equals("") ? Mdl_Variables.strTablaAnterior : Mdl_Variables.P_Str_Tabla) + "_D set EJECUCION_AC_ERR = 0 where [INDEX] = " + ((Mdl_Variables.P_Str_IdEvidencia != null && !Mdl_Variables.P_Str_IdEvidencia.equals("")) ? Mdl_Variables.P_Str_IdEvidencia : Mdl_Variables.Rst_Tabla.getString("INDEX"))).execute();
				Mdl_Variables.P_Tipo_Log = Mdl_Variables.MSG_valido;
				Mdl_Variables.boolAction = 1;
				Mdl_Variables.P_Tipo_Log = 1;
			}
			else
			{
				Mdl_Variables.Descripcion_Acierto_Error = ((Mdl_Variables.P_Str_Desc_Err_Ant != null && !Mdl_Variables.P_Str_Desc_Err_Ant.equals("")) ? Mdl_Variables.P_Str_Desc_Err_Ant : Mdl_Variables.Rst_Tabla.getString("DESCRIPCION_ERROR")) + (Descripcion_Adicional.contentEquals("") ? "" : "-" + Descripcion_Adicional)+" -> "+Mdl_Variables.StrComplementoDescripcion;
				Mdl_Variables.Cnn.prepareCall("Update " + (Mdl_Variables.strTablaAnterior != null && !Mdl_Variables.strTablaAnterior.equals("") ? Mdl_Variables.strTablaAnterior : Mdl_Variables.P_Str_Tabla) + "_D set EJECUCION_AC_ERR = -1 where [INDEX] = " + ((Mdl_Variables.P_Str_IdEvidencia != null && !Mdl_Variables.P_Str_IdEvidencia.equals("")) ? Mdl_Variables.P_Str_IdEvidencia : Mdl_Variables.Rst_Tabla.getString("INDEX"))).execute();
				Mdl_Variables.P_Tipo_Log = Mdl_Variables.MSG_Error;
				Mdl_Variables.boolAction = 0;
				Mdl_Variables.P_Tipo_Log = 2;
			}
		}catch(Exception ex)
		{
			System.out.println("Ocurrió un error en Mdl_Evidenciamiento: " + ex.getMessage());
		}
	}
	public static int Valida_Orientacion(boolean BlnResultado)
	{	
		try
		{
			if (BlnResultado == true)
			{
				if (Mdl_Variables.Rst_Tabla.getString("ACIERTO_ERROR").contentEquals("0"))
				{
					Mdl_Evidenciamiento.Evidencia_Log(false, "Error en la Validación.");
					//Mdl_Evidenciamiento.Ingreso_Datos_Log();
					Mdl_Variables.boolAction = 2;
					return 0;
				}else{
					Mdl_Evidenciamiento.Evidencia_Log(true, "Validación Satisfactoria.");
					//Mdl_Evidenciamiento.Ingreso_Datos_Log();
					Mdl_Variables.boolAction = 1;
					return 1;
				}	
			}
			else
			{
				if (Mdl_Variables.Rst_Tabla.getString("ACIERTO_ERROR").contentEquals("0"))
				{	
					Mdl_Evidenciamiento.Evidencia_Log(true, "Validación Satisfactoria.");
					//Mdl_Evidenciamiento.Ingreso_Datos_Log();
					Mdl_Variables.boolAction = 1;
					return 1;
				}else{
					Mdl_Evidenciamiento.Evidencia_Log(false, "Error en la Validación.");
					//Mdl_Evidenciamiento.Ingreso_Datos_Log();
					Mdl_Variables.boolAction = 2;
					return 0;
				}
			}
		}catch(Exception ex)
		{
			return 0;
		}
	}
	
	/** Crea en el repositorio el html de evidencia que contendrá la prueba
	 * 
	 * @param strAutomatizador Persona responsable de la ejecución y estabilización del script
	 * @param strResponsable Persona responsable de la prueba
	 * @throws SQLException 
	 */
	
	
	public static void Generar_HTML(String strAutomatizador, String strResponsable) throws SQLException{
	// CAA_2013-02-04 Begin
		Mdl_Variables.Ruta_Evidencia = Mdl_Variables.Ruta_Aplicativo + "\\" + Mdl_Variables.Vrb_VersionAplicativo + "\\" + Mdl_Variables.P_Str_Ciclo ;
	// CAA_2013-02-04 End

		ResultSet RS_ScreenEvd = null, RS_Edatos = null;
		String 
		Vrb_Dt_ConteoErrores = null, 
		Vrb_Dt_ConteoAciertos = null, 
		Vrb_ConteoErrores = null, 
		Vrb_ConteoAciertos = null;
		
		  try { Mdl_Variables.Rst_Evidencia.close(); } 
		catch (Exception e) { /* continua con el proceso */ }
		
		//Cuenta cuantos casos de prueba fueron exitosos
		Vrb_Dt_ConteoErrores = "SELECT count(distinct(DE.INDICE)) " +
				"      FROM TBL_LOG_DETALLE DE " +
				"INNER JOIN TBL_LOG_MAESTRO MA ON MA.ID_MAESTRO = DE.ID_MAESTRO " +
				"     WHERE MA.NOMBRE_TABLA like '" + Mdl_Variables.P_Str_Tabla + "' AND DE.VERSION ='" + Mdl_Variables.Vrb_VersionAplicativo + "' and DE.CICLO = '" + Mdl_Variables.P_Str_Ciclo + "' AND DE.INDICE not in " +
						"(SELECT INDICE FROM TBL_LOG_DETALLE DET " +
						"INNER JOIN TBL_LOG_MAESTRO MAS ON MAS.ID_MAESTRO = DET.ID_MAESTRO " +
						"WHERE DET.TIPO_LOG  in(2,4,6) AND MAS.NOMBRE_TABLA like '" + Mdl_Variables.P_Str_Tabla + "' AND DET.VERSION = '" + Mdl_Variables.Vrb_VersionAplicativo + "'  and DET.CICLO = '" + Mdl_Variables.P_Str_Ciclo + "') ";
		try {
			Mdl_Variables.Rst_Evidencia = Mdl_Variables.Cnn.prepareCall(Vrb_Dt_ConteoErrores).executeQuery();
		} catch (SQLException e) {
			System.out.println("Ocurrió un error consultando los aciertos en TBL_LOG_DETALLE: " + e.getMessage());
			return;
		}
		try {
			if(Mdl_Variables.Rst_Evidencia.next()){
				Vrb_ConteoAciertos = Mdl_Variables.Rst_Evidencia.getString(1);
				try{
					Mdl_Variables.Rst_Evidencia.close();
				}catch(Exception e){
					//continua con el proceso
				}
			}else{
				System.out.println("Error consultando los aciertos: la consulta no trajo ningún resultado.");
				return;
			}
		} catch (SQLException e) {
			System.out.println("Error inesperado consultando los aciertos: " + e.getMessage());
			return;
		}
		// Cuenta cuantos casos de prueba fueron fallidos
		Vrb_Dt_ConteoAciertos = "SELECT count(distinct(DE.INDICE)) " +
				"      FROM TBL_LOG_DETALLE DE " +
				"INNER JOIN TBL_LOG_MAESTRO MA ON MA.ID_MAESTRO = DE.ID_MAESTRO " +
				"     WHERE MA.NOMBRE_TABLA like '" + Mdl_Variables.P_Str_Tabla + "' AND DE.VERSION ='" + Mdl_Variables.Vrb_VersionAplicativo + "' and DE.CICLO = '" + Mdl_Variables.P_Str_Ciclo + "' AND DE.INDICE  in " +
						"(SELECT INDICE FROM TBL_LOG_DETALLE DET " +
						"INNER JOIN  TBL_LOG_MAESTRO MAS ON MAS.ID_MAESTRO = DET.ID_MAESTRO " +
						"WHERE DET.TIPO_LOG  in(2,4,6) AND MAS.NOMBRE_TABLA like '" + Mdl_Variables.P_Str_Tabla + "' AND DET.VERSION = '" + Mdl_Variables.Vrb_VersionAplicativo + "'  and DET.CICLO = '" + Mdl_Variables.P_Str_Ciclo + "') ";
		try {
			Mdl_Variables.Rst_Evidencia = Mdl_Variables.Cnn.prepareCall(Vrb_Dt_ConteoAciertos).executeQuery();
		} catch (SQLException e) {
			System.out.println("Ocurrió un error consultando los errores en TBL_LOG_DETALLE: " + e.getMessage());
			return;
		}
		try {
			if(Mdl_Variables.Rst_Evidencia.next()){
				Vrb_ConteoErrores = Mdl_Variables.Rst_Evidencia.getString(1);
				try{
					Mdl_Variables.Rst_Evidencia.close();
				}catch(Exception e){
					//continua con el proceso
				}
			}else{
				System.out.println("Error consultando los errores: la consulta no trajo ningún resultado.");
				return;
			}
		} catch (SQLException e) {
			System.out.println("Error inesperado consultando los errores: " + e.getMessage());
			return;
		}
		
		NombreEvd = Mdl_Variables.P_Str_Datos.replace("_D", "") + "_" + Mdl_Variables.P_Str_Ciclo + "_" + Mdl_Variables.Vrb_VersionAplicativo + ".html";
		// Crear Archivo Frame principal
		// CAA_2013-01-04 Begin 
		FileWriter fw;
		try {
		 // fw = new FileWriter(Mdl_Variables.Ruta_Aplicativo + "\\" + NombreEvd);
			fw = new FileWriter( Mdl_Variables.Ruta_Evidencia + "\\" + NombreEvd );
			} catch (IOException e) {
		 // System.out.println("Error generando el archivo " + Mdl_Variables.Ruta_Aplicativo + "\\" + NombreEvd + ": " + e.getMessage());
			System.out.println("Error generando el archivo " + Mdl_Variables.Ruta_Evidencia + "\\" + NombreEvd + ": " + e.getMessage());
			return;
		}
		// CAA_2013-01-04 End

	    BufferedWriter bw = new BufferedWriter(fw);
	    PrintWriter MyFile = new PrintWriter(bw);

		MyFile.println("<html>");
		MyFile.println("<head>");
		MyFile.println("<title>" + Mdl_Variables.P_Str_Datos + "</title>");
		MyFile.println("</head>");
		MyFile.println("<FRAMESET cols=\"15%,*\" FRAMEBORDER=\"\">");
		MyFile.println("<FRAMESET rows=\"77%,*\" border = 0>");
		MyFile.println("<FRAME SRC=\"8. Menús/" + NombreEvd + "\" name=\"Menu\">");
		MyFile.println("<FRAME SRC=\"../../Recursos/planeta.png\" name = \"imagen\">");
		MyFile.println("</FRAMESET>");
		MyFile.println("<FRAMESET rows=\"80%,*\">");
		MyFile.println("<FRAME SRC=\"6. Encabezado/" + NombreEvd + "\" name=\"Arriba\">");
		MyFile.println("<FRAME name=\"Abajo\">");
		MyFile.println("</FRAMESET>");
		MyFile.println("</FRAMESET>");
		MyFile.println("</html>");
	    MyFile.close();

	    
	    
	    //Crear Archivo Encabezado
		// CAA_2013-01-04 Begin 
	    try {
		 // fw = new FileWriter(Mdl_Variables.Ruta_Aplicativo + "\\6. Encabezado\\" + NombreEvd);
			fw = new FileWriter( Mdl_Variables.Ruta_Evidencia + "\\6. Encabezado\\" + NombreEvd );
		} catch (IOException e) {
		 // System.out.println("Error generando el archivo " + Mdl_Variables.Ruta_Aplicativo + "\\6. Encabezado\\" + NombreEvd + ": " + e.getMessage());
			System.out.println("Error generando el archivo " + Mdl_Variables.Ruta_Evidencia + "\\6. Encabezado\\" + NombreEvd + ": " + e.getMessage());
			return;
		}
		// CAA_2013-01-04 End
		
	    bw = new BufferedWriter(fw);
	    MyFile = new PrintWriter(bw);
	    MyFile.println("<style type=\"text/css\">body {font-family:Arial, Helvetica, sans-serif;font-size:14px}</style>");
	    MyFile.println("<TABLE BORDER=\"1\" CELLSPACING=\"1\" align = center>" + "\n" );
	    MyFile.println("<TH COLSPAN=4 border = 0 ><img src ='../../../Recursos/Logo.JPG'> </TH>" + "\n" );
	    MyFile.println("<TR align=\"center\">" + "\n" );
	    MyFile.println("<TD colspan = 2><B>Nombre del Script</B></TD>" + "\n" );
	    MyFile.println("<TD colspan = 2><B>" + Mdl_Variables.P_Str_Tabla + "<B></TD>" + "\n" );
	    MyFile.println("</TR>" + "\n" );
	    MyFile.println("<TR align=\"center\">" + "\n" );
	    MyFile.println("<TD colspan = 2><B>Requerimento</B></TD>" + "\n" );
	    MyFile.println("<TD colspan = 2><B> " + (Mdl_Variables.P_Str_Descripcion != null ? Mdl_Variables.P_Str_Descripcion : "") + ".<B></TD>" + "\n" );
	    MyFile.println("</TR>" + "\n" );
	    MyFile.println("<TR align=\"center\">" + "\n" );
	    MyFile.println("<TD colspan = 2><B>Ruta del aplicativo<B></TD>" + "\n" );
	    MyFile.println("<TD colspan = 2><B>" + (Mdl_Variables.P_Vrb_Browser != null ? Mdl_Variables.P_Vrb_Browser : "") + ".<B></TD>" + "\n" );
	    MyFile.println("</TR>" + "\n" );
	    MyFile.println("<TR align=\"center\">" + "\n" );
	    MyFile.println("<TD colspan = 2><B>Fecha de ejecucion<B></TD>" + "\n" );
		//Se obtiene la fecha
		Date fechaActual = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		MyFile.println("<TD colspan = 2><B>" + formato.format(fechaActual) + "<B></TD>" + "\n" );
		MyFile.println("</TR>" + "\n" );
		MyFile.println("<TR align=\"center\">" + "\n" );
		MyFile.println("<TD colspan = 2><B>Arquitecto Responsable<B></TD>" + "\n" );
		MyFile.println("<TD colspan = 2><B>" + strResponsable + ".<B></TD>" + "\n" );
		MyFile.println("</TR>" + "\n" );
		MyFile.println("<TR align=\"center\">" + "\n" );
		MyFile.println("<TD colspan = 2><B>Automatizador Responsable<B></TD>" + "\n" );
		MyFile.println("<TD colspan = 2><B>" + strAutomatizador + ".<B></TD>" + "\n" );
		MyFile.println("</TR>" + "\n" );
		MyFile.println("<TR align=\"center\">"); // CAA_2013-02-04 Se agrega al encabezado
		MyFile.println("<TD colspan = 2><B>Tiempo de Ejecucion (segundos) <B></TD>"); // CAA_2013-02-04 Se agrega al encabezado
		MyFile.println("<TD colspan = 2><B>" + Mdl_Variables.contSegFuncionalidad + ".<B></TD>"); // CAA_2013-02-04 Se agrega al encabezado
		MyFile.println("</TR>");
		MyFile.println("<TR align=\"center\">"); // CAA_2013-02-04 Se agrega al encabezado
		MyFile.println("<TD colspan = 2><B>Nombre del Robot <B></TD>"); // CAA_2013-02-04 Se agrega al encabezado
		MyFile.println("<TD colspan = 2><B>" + Mdl_Variables.P_Str_NombreMaquina + ".<B></TD>"); // CAA_2013-02-04 Se agrega al encabezado
		MyFile.println("</TR>");
		MyFile.println("<TR align=\"center\">"); // CAA_2013-02-04 Se agrega al encabezado
		MyFile.println("<TD colspan = 2><B>Ruta de las Evidencias<B></TD>"); // CAA_2013-02-04 Se agrega al encabezado
		MyFile.println("<TD colspan = 2><a href=\"" + Mdl_Variables.Ruta_Evidencia + "\" >" + Mdl_Variables.Ruta_Evidencia + ".</a></TD>"); // CAA_2013-02-04 Se agrega al encabezado
		MyFile.println("</TR>");
		MyFile.println("<TR align=\"center\">" + "\n" );
		MyFile.println("<TD><B>Ciclo<B></TD>" + "\n" );
		MyFile.println("<TD><B>" + Mdl_Variables.P_Str_Ciclo + "<B></TD>" + "\n" );
		MyFile.println("<TD><B>Version<B></TD>" + "\n" );
		MyFile.println("<TD><B>" + Mdl_Variables.P_Str_Nombre_Version + "<B></TD>" + "\n" );
		MyFile.println("</TR>" + "\n" );
		MyFile.println("<TR align=\"center\">" + "\n" );
		MyFile.println("<TD><B>Casos ok<B></TD>" + "\n" );
		MyFile.println("<TD><B>" + Vrb_ConteoAciertos + " / " + (Integer.parseInt(Vrb_ConteoAciertos) + Integer.parseInt(Vrb_ConteoErrores)) + "<B></TD>" + "\n" );
		MyFile.println("<TD><B>Casos error<B></TD>" + "\n" );
		MyFile.println("<TD><B>" + Vrb_ConteoErrores + " / " + (Integer.parseInt(Vrb_ConteoAciertos) + Integer.parseInt(Vrb_ConteoErrores)) + "<B></TD>" + "\n" );
		MyFile.println("</TABLE>" + "\n" );
		MyFile.close();
		
		// Crear Archivos de evidencias
		// CAA_2013-01-04 Begin 
		try {
		 // fw = new FileWriter(Mdl_Variables.Ruta_Aplicativo + "\\8. Menús\\" + NombreEvd);
			fw = new FileWriter( Mdl_Variables.Ruta_Evidencia + "\\8. Menús\\" + NombreEvd );
		} catch (IOException e) {
		 // System.out.println("Error generando el archivo " + Mdl_Variables.Ruta_Aplicativo + "\\8. Menús\\" + NombreEvd + ": " + e.getMessage());
			System.out.println("Error generando el archivo " + Mdl_Variables.Ruta_Evidencia + "\\8. Menús\\" + NombreEvd + ": " + e.getMessage());
			return;
		}
		// CAA_2013-01-04 End

	    bw = new BufferedWriter(fw);
	    MyFile = new PrintWriter(bw);
		MyFile.println("<html>");
		MyFile.println("<head>");
		MyFile.println("<title>Menú</title>");
		MyFile.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../../../Recursos/sdmenu/sdmenu.css\" />"); // CAA_2013-01-04
		MyFile.println("<script type=\"text/javascript\" src=\"../../../Recursos/sdmenu/sdmenu.js\">"); // CAA_2013-01-04
		MyFile.println("</script>");
		MyFile.println("<script type=\"text/javascript\">");
		MyFile.println("// <![CDATA[");
		MyFile.println("var myMenu;");
		MyFile.println("window.onload = function(); {");
		MyFile.println("myMenu = new SDMenu(\"my_menu\");");
		MyFile.println("myMenu.init();};");
		MyFile.println("</script>");
		MyFile.println("</head>");
		MyFile.println("<body> ");
		MyFile.println("<body> <table align = center>");
		MyFile.println("<body> <img src='../../../Recursos/Choucair_Tested.JPG' height=100 width = 100>"); // CAA_2013-01-04
		MyFile.println("<body> </table>");
		MyFile.println("<div style=\"float: left\" id=\"my_menu\" class=\"sdmenu\">");
		MyFile.println("<div>");
		MyFile.println("<span>Información</span>");
		MyFile.println("<a onmouseover=\"parent.parent.frames[2].location='../6. Encabezado/" + NombreEvd + "';parent.parent.frames[3].location='../../../Recursos/Sin Datos.html'\">Información</a>");
		MyFile.println("</div>");
		MyFile.println("<div>");
		MyFile.println("<span>Casos Ok</span>");
		
		//Cuenta cuantos casos de prueba fueron exitosos
		Vrb_Dt_ConteoErrores = "SELECT  distinct(DE.INDICE) FROM TBL_LOG_DETALLE DE " +
				"INNER JOIN TBL_LOG_MAESTRO MA " +
				"ON MA.ID_MAESTRO = DE.ID_MAESTRO " +
				"WHERE MA.NOMBRE_TABLA like '" + Mdl_Variables.P_Str_Tabla + "' " +
				"  AND DE.CICLO like '" + Mdl_Variables.P_Str_Ciclo + "' AND DE.VERSION ='" + Mdl_Variables.Vrb_VersionAplicativo + "' " +
				"  AND DE.INDICE NOT IN " +
				"(SELECT INDICE FROM TBL_LOG_DETALLE DET INNER JOIN TBL_LOG_MAESTRO MAS " +
				"ON MAS.ID_MAESTRO = DET.ID_MAESTRO " +
				"WHERE DET.TIPO_LOG IN(2,4,6) AND MAS.NOMBRE_TABLA like '" + Mdl_Variables.P_Str_Tabla + "' " +
				"AND DET.CICLO like '" + Mdl_Variables.P_Str_Ciclo + "'  AND DET.VERSION = '" + Mdl_Variables.Vrb_VersionAplicativo + "') order by  INDICE";
		try {
			Mdl_Variables.Rst_Evidencia = Mdl_Variables.Cnn.prepareCall(Vrb_Dt_ConteoErrores).executeQuery();
		} catch (SQLException e) {
			System.out.println("Ocurrió un error consultando los aciertos, en TBL_LOG_DETALLE: " + e.getMessage());
			return;
		}
		try {
			ResultSet rsStepName;
			String strStepName;
			while(Mdl_Variables.Rst_Evidencia.next()){
				rsStepName = Mdl_Variables.Cnn.prepareCall("select STEP_NAME from " + Mdl_Variables.P_Str_Datos + " where ID_CASO = " +  Mdl_Variables.Rst_Evidencia.getString("INDICE")).executeQuery();
				if(rsStepName.next()){
					strStepName = rsStepName.getString(1);
				}else{
					//esto nunca debería darse pero igual se pone la validación
					System.out.println("No se encontró el nombre del paso en la tabla " + Mdl_Variables.P_Str_Datos + ", para el caso con id " + Mdl_Variables.Rst_Evidencia.getString("INDICE"));
					return;
				}
				MyFile.println("<a onmouseover=\"parent.parent.frames[2].location='../1. Evidencias/" + Mdl_Variables.Rst_Evidencia.getString("INDICE") + "_" + NombreEvd + "';parent.parent.frames[3].location='../7. Descripcion/" + Mdl_Variables.Rst_Evidencia.getString("INDICE") + "_" + NombreEvd +"'\">" + Mdl_Variables.Rst_Evidencia.getString("INDICE") + " : " + strStepName + "</a>");
			}
			try{
				Mdl_Variables.Rst_Evidencia.close();
			}catch(Exception e){
				//continua con el proceso
			}
		} catch (SQLException e) {
			System.out.println("Ocurrió un error inesperado consultando los aciertos: " + e.getMessage());
			return;
		}
		MyFile.println("</div>");
		MyFile.println("<div>");
		MyFile.println("<span>Casos Fallidos</span>");
		
		//Cuenta cuantos casos de prueba fueron fallidos
		Vrb_Dt_ConteoErrores = "SELECT distinct(DE.INDICE)  FROM   TBL_LOG_DETALLE DE " +
				"INNER JOIN  TBL_LOG_MAESTRO MA " +
				"ON MA.ID_MAESTRO = DE.ID_MAESTRO " +
				"WHERE MA.NOMBRE_TABLA like '" + Mdl_Variables.P_Str_Tabla + "' " +
				"  AND DE.CICLO like '" + Mdl_Variables.P_Str_Ciclo + "' AND DE.VERSION ='" + Mdl_Variables.Vrb_VersionAplicativo + "' " +
				"  AND DE.INDICE   IN " +
				"(SELECT INDICE FROM TBL_LOG_DETALLE DET INNER JOIN  TBL_LOG_MAESTRO MAS " +
				"ON MAS.ID_MAESTRO = DET.ID_MAESTRO " +
				"WHERE DET.TIPO_LOG IN(2,4,6) AND MAS.NOMBRE_TABLA like '" + Mdl_Variables.P_Str_Tabla + "' " +
				"AND DET.CICLO like '" + Mdl_Variables.P_Str_Ciclo + "'  AND DET.VERSION = '" + Mdl_Variables.Vrb_VersionAplicativo + "')";
		try {
			Mdl_Variables.Rst_Evidencia = Mdl_Variables.Cnn.prepareCall(Vrb_Dt_ConteoErrores).executeQuery();
		} catch (SQLException e) {
			System.out.println("Ocurrió un error consultando los errores, en TBL_LOG_DETALLE: " + e.getMessage());
			return;
		}
		try {
			ResultSet rsStepName;
			String strStepName;
			while(Mdl_Variables.Rst_Evidencia.next()){
				rsStepName = Mdl_Variables.Cnn.prepareCall("select STEP_NAME from " + Mdl_Variables.P_Str_Datos + " where ID_CASO = " +  Mdl_Variables.Rst_Evidencia.getString("INDICE")).executeQuery();
				if(rsStepName.next()){
					strStepName = rsStepName.getString(1);
				}else{
					//esto nunca debería darse pero igual se pone la validación
					System.out.println("No se encontró el Nombre del paso en la tabla " + Mdl_Variables.P_Str_Datos + ", para el caso con id " + Mdl_Variables.Rst_Evidencia.getString("INDICE"));
					return;
				}
				MyFile.println("<a onmouseover=\"parent.parent.frames[2].location='../1. Evidencias/" + Mdl_Variables.Rst_Evidencia.getString("INDICE") + "_" + NombreEvd + "';parent.parent.frames[3].location='../7. Descripcion/" + Mdl_Variables.Rst_Evidencia.getString("INDICE") + "_" + NombreEvd +"'\">" + Mdl_Variables.Rst_Evidencia.getString("INDICE") + " : " + strStepName + "</a>");
			}
			try{
				Mdl_Variables.Rst_Evidencia.close();
			}catch(Exception e){
				//continua con el proceso
			}
		} catch (SQLException e) {
			System.out.println("Ocurrió un error inesperado consultando los errores: " + e.getMessage());
			return;
		}
		MyFile.println("</div>");
		MyFile.println("<div>");
		MyFile.println("<span>Graficos</span>");
		MyFile.println("<a onmouseover=\"parent.parent.frames[2].location='../9. Diagramas/" + NombreEvd + "';parent.parent.frames[3].location='../../../Recursos/Sin Datos.html'\">Gráficos</a>");
		MyFile.println("</div>");
		MyFile.println("</div>");
		MyFile.println("</body>");
		MyFile.println("</html>");
		MyFile.close();
		
		//Crear Archivos de Imagenes
		//Cuenta cuantos casos de prueba fueron exitosos
		String Str_Evidencias = "SELECT  distinct(DE.INDICE)  FROM   TBL_LOG_DETALLE DE " +
				"INNER JOIN  TBL_LOG_MAESTRO MA " +
				"ON MA.ID_MAESTRO = DE.ID_MAESTRO " +
				"WHERE MA.NOMBRE_TABLA like '" + Mdl_Variables.P_Str_Tabla + "' " +
				"  AND DE.CICLO like '" + Mdl_Variables.P_Str_Ciclo + "' AND DE.VERSION ='" + Mdl_Variables.Vrb_VersionAplicativo + "' " +
				"  AND DE.INDICE  NOT IN " +
				"(SELECT INDICE FROM TBL_LOG_DETALLE DET INNER JOIN  TBL_LOG_MAESTRO MAS " +
				"ON MAS.ID_MAESTRO = DET.ID_MAESTRO " +
				"WHERE DET.TIPO_LOG IN(2,4,6) AND MAS.NOMBRE_TABLA like '" + Mdl_Variables.P_Str_Tabla + "' " +
				"AND DET.CICLO like '" + Mdl_Variables.P_Str_Ciclo + "'  AND DET.VERSION = '" + Mdl_Variables.Vrb_VersionAplicativo + "') order by  INDICE";
		try {
			Mdl_Variables.Rst_Evidencia = Mdl_Variables.Cnn.prepareCall(Str_Evidencias).executeQuery();
		} catch (SQLException e) {
			System.out.println("Ocurrió un error consultando las evidencias en TBL_LOG_DETALLE: " + e.getMessage());
			return;
		}
		try {
			while(Mdl_Variables.Rst_Evidencia.next()){
				Str_Evidencias = "SELECT  IMAGEN  FROM   TBL_LOG_DETALLE DE " +
						"INNER JOIN  TBL_LOG_MAESTRO MA " +
						"ON MA.ID_MAESTRO = DE.ID_MAESTRO " +
						"WHERE MA.NOMBRE_TABLA like '" + Mdl_Variables.P_Str_Tabla + "' " +
						"  AND DE.CICLO like '" + Mdl_Variables.P_Str_Ciclo + "' AND DE.VERSION ='" + Mdl_Variables.Vrb_VersionAplicativo + "' " +
						"  AND DE.INDICE   = " + Mdl_Variables.Rst_Evidencia.getString("INDICE");
				RS_ScreenEvd = Mdl_Variables.Cnn.prepareCall(Str_Evidencias).executeQuery();
				RS_Edatos = Mdl_Variables.Cnn.prepareCall("Select * from " + Mdl_Variables.P_Str_Tabla + "_D WHERE ID_CASO = " + Mdl_Variables.Rst_Evidencia.getString("INDICE")).executeQuery();

				// CAA_2013-01-04 Begin 
				try {
				 // fw = new FileWriter(Mdl_Variables.Ruta_Aplicativo + "\\1. Evidencias\\" + Mdl_Variables.Rst_Evidencia.getString("INDICE") + "_" + NombreEvd);
					fw = new FileWriter( Mdl_Variables.Ruta_Evidencia + "\\1. Evidencias\\" + Mdl_Variables.Rst_Evidencia.getString("INDICE") + "_" + NombreEvd );
				} catch (IOException e) {
				 // System.out.println("Error generando el archivo " + Mdl_Variables.Ruta_Aplicativo + "\\1. Evidencias\\" + Mdl_Variables.Rst_Evidencia.getString("INDICE") + "_" + NombreEvd + ": " + e.getMessage());
					System.out.println("Error generando el archivo " + Mdl_Variables.Ruta_Evidencia + "\\1. Evidencias\\" + Mdl_Variables.Rst_Evidencia.getString("INDICE") + "_" + NombreEvd + ": " + e.getMessage());
					return;
				}
				// CAA_2013-01-04 End

			    bw = new BufferedWriter(fw);
			    PrintWriter MyFile_Screen = new PrintWriter(bw);
				// CAA_2013-01-04 Begin 
			    try {
				 // fw = new FileWriter(Mdl_Variables.Ruta_Aplicativo + "\\7. Descripcion\\" + Mdl_Variables.Rst_Evidencia.getString("INDICE") + "_" + NombreEvd);
					fw = new FileWriter( Mdl_Variables.Ruta_Evidencia + "\\7. Descripcion\\" + Mdl_Variables.Rst_Evidencia.getString("INDICE") + "_" + NombreEvd);
				} catch (IOException e) {
				 // System.out.println("Error generando el archivo " + Mdl_Variables.Ruta_Aplicativo + "\\7. Descripcion\\" + Mdl_Variables.Rst_Evidencia.getString("INDICE") + "_" + NombreEvd + ": " + e.getMessage());
					System.out.println("Error generando el archivo " + Mdl_Variables.Ruta_Evidencia + "\\7. Descripcion\\" + Mdl_Variables.Rst_Evidencia.getString("INDICE") + "_" + NombreEvd + ": " + e.getMessage());
					return;
				}
				// CAA_2013-01-04 Begin 

			    bw = new BufferedWriter(fw);
			    PrintWriter MyFile_Datos = new PrintWriter(bw);
				MyFile_Datos.println("<table>");
				MyFile_Screen.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"><HTML><HEAD>");
				MyFile_Screen.println("<SCRIPT type=\"text/javascript\" src=\"../../../Recursos/slideshow.js\"></SCRIPT><SCRIPT type=\"text/javascript\"><!--"); // CAA_2013-01-04
				MyFile_Screen.println("SLIDES = new slideshow(\"SLIDES\");");
				MyFile_Screen.println("s = new slide();");
				String datos_Evd = null;
				while(RS_ScreenEvd.next()){
					datos_Evd = RS_ScreenEvd.getString(1);
	                MyFile_Screen.println("s = new slide();");
					MyFile_Screen.println("s.src = \"" + datos_Evd.split("\\\\")[datos_Evd.split("\\\\").length - 1] + "\";");
					MyFile_Screen.println("s.text=\"\"");
					MyFile_Screen.println("SLIDES.add_slide(s);");
				}
				MyFile_Screen.println("//-->");
				MyFile_Screen.println("</SCRIPT>");
				MyFile_Screen.println("</HEAD><BODY onLoad=\"SLIDES.pause()\"></body>");
				MyFile_Screen.println("<table align = center>");
				MyFile_Screen.println("<FORM>");
				MyFile_Screen.println("<tr>"); // Mario Alejandro Prieto Valdes
				MyFile_Screen.println("<td align = center>");
				MyFile_Screen.println("<INPUT TYPE=\"image\"  src='../../../Recursos/anterior.JPG' width=\"60\" height=\"40\" onmouseover=\"SLIDES.pause();SLIDES.previous()\">");
				MyFile_Screen.println("<INPUT TYPE=\"image\"  src='../../../Recursos/pausa.JPG' width=\"60\" height=\"40\" onmouseover=\"SLIDES.pause()\">");
				MyFile_Screen.println("<INPUT TYPE=\"image\"  src='../../../Recursos/slide.JPG' width=\"60\" height=\"40\" onmouseover=\"SLIDES.play()\">");
				MyFile_Screen.println("<INPUT TYPE=\"image\"  src='../../../Recursos/siguiente.JPG' width=\"60\" height=\"40\" onmouseover=\"SLIDES.next();SLIDES.pause()\">");
				MyFile_Screen.println("</td>");
				MyFile_Screen.println("</tr>");// Mario Alejandro Prieto Valdes
				
				MyFile_Screen.println("<tr>");// Mario Alejandro Prieto Valdes
				MyFile_Screen.println("<td align = center>");// Mario Alejandro Prieto Valdes
				MyFile_Screen.println("<script>");// Mario Alejandro Prieto Valdes
				MyFile_Screen.println("document.write('<div id=\"showlinks\">  </div>')");// Mario Alejandro Prieto Valdes
				MyFile_Screen.println("SLIDES.showlinks();");// Mario Alejandro Prieto Valdes
				MyFile_Screen.println("</script>");// Mario Alejandro Prieto Valdes
				MyFile_Screen.println("</td>");// Mario Alejandro Prieto Valdes
				MyFile_Screen.println("</tr>");// Mario Alejandro Prieto Valdes
				
				MyFile_Screen.println("</FORM>");
				
				MyFile_Screen.println("</table>");
				MyFile_Screen.println("<table align = center>");
				MyFile_Screen.println("<IMG name=\"SLIDESIMG\" src=\"..\\" + datos_Evd + "\" WIDTH=896 HEIGHT=560 alt=\"Slideshow image\">");
				MyFile_Screen.println("</table>");
				MyFile_Screen.println("<SCRIPT type=\"text/javascript\"><!--");
				MyFile_Screen.println("if (document.images) {  SLIDES.image = document.images.SLIDESIMG;  SLIDES.textid = \"SLIDESTEXT\";  SLIDES.update();  SLIDES.play();}");
				MyFile_Screen.println("//-->");
				MyFile_Screen.println("</SCRIPT>");
				//MyFile_Screen.println("<div id=showlinks>This is a test</div>   ");//Mario A. Prieto V. Links
				MyFile_Screen.println("</BODY>");
				MyFile_Screen.println("</HTML>");
				
				ResultSetMetaData RSMD_Edatos = RS_Edatos.getMetaData();
				while(RS_Edatos.next()){
					for(int a=1 ; a <= RSMD_Edatos.getColumnCount() ; a++)
//						if(!RSMD_Edatos.getColumnName(a).equalsIgnoreCase("INDEX") && !RSMD_Edatos.getColumnName(a).equalsIgnoreCase("EJECUCION_AC_ERR") && !RSMD_Edatos.getColumnName(a).equalsIgnoreCase("ESTADO_CASO") && !RSMD_Edatos.getColumnName(a).equalsIgnoreCase("CASO_FELIZ") && !RSMD_Edatos.getColumnName(a).equalsIgnoreCase("ESTADO_CASO") && !RSMD_Edatos.getColumnName(a).equalsIgnoreCase(" ESTADO_CASO") && !RSMD_Edatos.getColumnName(a).equalsIgnoreCase("HORA") && !RSMD_Edatos.getColumnName(a).equalsIgnoreCase("UTILIZADO") && !RSMD_Edatos.getColumnName(a).equalsIgnoreCase("ACIERTO_ERROR") && !RSMD_Edatos.getColumnName(a).equalsIgnoreCase("DESCRIPCION_ERROR") && !RSMD_Edatos.getColumnName(a).equalsIgnoreCase("ID_CASO") && RS_Edatos.getString(a) != null && !RS_Edatos.getString(a).equalsIgnoreCase("NULL") && !RS_Edatos.getString(a).equalsIgnoreCase("<IGNORE>"))
						if(RSMD_Edatos.getColumnName(a).equals("DESIGN_STEPS"))
							MyFile_Datos.println("<TR><TD><b><font style=\"font-family: Arial;font-size: 14px;\">" + RSMD_Edatos.getColumnName(a) + " : </b>" +RS_Edatos.getString(a) + "</font></TD></TR>");
				}
				MyFile_Datos.println("</table>");
				MyFile_Datos.close();
	            MyFile_Screen.close();
				try{
					RS_ScreenEvd.close();
				}catch(Exception e){
					//continua con el proceso
				}
				try{
					RS_Edatos.close();
				}catch(Exception e){
					//continua con el proceso
				}
			}
			try{
				Mdl_Variables.Rst_Evidencia.close();
			}catch(Exception e){
				//continua con el proceso
			}
		} catch (SQLException e) {
			System.out.println("Error recorriendo el rs de las evidencias: " + e.getMessage());
			return;
		}
		
		Str_Evidencias = "SELECT  distinct(DE.INDICE)  FROM   TBL_LOG_DETALLE DE " +
				"INNER JOIN  TBL_LOG_MAESTRO MA " +
				"ON MA.ID_MAESTRO = DE.ID_MAESTRO " +
				"WHERE MA.NOMBRE_TABLA like '" + Mdl_Variables.P_Str_Tabla + "' " +
				"  AND DE.CICLO like '" + Mdl_Variables.P_Str_Ciclo + "' AND DE.VERSION ='" + Mdl_Variables.Vrb_VersionAplicativo + "' " +
				"  AND DE.INDICE   IN " +
				"(SELECT INDICE FROM TBL_LOG_DETALLE DET INNER JOIN  TBL_LOG_MAESTRO MAS " +
				"ON MAS.ID_MAESTRO = DET.ID_MAESTRO " +
				"WHERE DET.TIPO_LOG IN(2,4,6) AND MAS.NOMBRE_TABLA like '" + Mdl_Variables.P_Str_Tabla + "' " +
				"AND DET.CICLO like '" + Mdl_Variables.P_Str_Ciclo + "'  AND DET.VERSION = '" + Mdl_Variables.Vrb_VersionAplicativo + "') order by  INDICE";
		try {
			Mdl_Variables.Rst_Evidencia = Mdl_Variables.Cnn.prepareCall(Str_Evidencias).executeQuery();
		} catch (SQLException e) {
			System.out.println("Ocurrió un error Consultando las evidencias en TBL_LOG_DETALLE: " + e.getMessage());
			return;
		}
		//Evd Error
		try {
			while(Mdl_Variables.Rst_Evidencia.next()){
				try{
					RS_Edatos.close();
				}catch(Exception e){
					//continua con el proceso
				}
				Str_Evidencias = "SELECT IMAGEN, MENSAJE_LOG FROM TBL_LOG_DETALLE DE " +
						"INNER JOIN TBL_LOG_MAESTRO MA " +
						"ON MA.ID_MAESTRO = DE.ID_MAESTRO " +
						"WHERE MA.NOMBRE_TABLA like '" + Mdl_Variables.P_Str_Tabla + "' " +
						"  AND DE.CICLO like '" + Mdl_Variables.P_Str_Ciclo + "' AND DE.VERSION ='" + Mdl_Variables.Vrb_VersionAplicativo + "' " +
						"  AND DE.INDICE   = " + Mdl_Variables.Rst_Evidencia.getString("INDICE");
				RS_ScreenEvd = Mdl_Variables.Cnn.prepareCall(Str_Evidencias).executeQuery();
				RS_Edatos = Mdl_Variables.Cnn.prepareCall("Select * from " + Mdl_Variables.P_Str_Tabla + "_D WHERE ID_CASO = " + Mdl_Variables.Rst_Evidencia.getString("INDICE")).executeQuery();
				// CAA_2013-01-04 Begin 
				try {
				 // fw = new FileWriter(Mdl_Variables.Ruta_Aplicativo + "\\1. Evidencias\\" + Mdl_Variables.Rst_Evidencia.getString("INDICE") + "_" + NombreEvd);
					fw = new FileWriter( Mdl_Variables.Ruta_Evidencia + "\\1. Evidencias\\" + Mdl_Variables.Rst_Evidencia.getString("INDICE") + "_" + NombreEvd);
				} catch (IOException e) {
				 // System.out.println("Error generando el archivo " + Mdl_Variables.Ruta_Aplicativo + "\\1. Evidencias\\" + Mdl_Variables.Rst_Evidencia.getString("INDICE") + "_" + NombreEvd + ": " + e.getMessage());
					System.out.println("Error generando el archivo " + Mdl_Variables.Ruta_Evidencia + "\\1. Evidencias\\" + Mdl_Variables.Rst_Evidencia.getString("INDICE") + "_" + NombreEvd + ": " + e.getMessage());
					return;
				}
				// CAA_2013-01-04 End 

			    bw = new BufferedWriter(fw);
			    PrintWriter MyFile_Screen = new PrintWriter(bw);

				// CAA_2013-01-04 Begin 
			    try {
				 // fw = new FileWriter(Mdl_Variables.Ruta_Aplicativo + "\\7. Descripcion\\" + Mdl_Variables.Rst_Evidencia.getString("INDICE") + "_" + NombreEvd);
					fw = new FileWriter( Mdl_Variables.Ruta_Evidencia + "\\7. Descripcion\\" + Mdl_Variables.Rst_Evidencia.getString("INDICE") + "_" + NombreEvd);
				} catch (IOException e) {
				 // System.out.println("Error generando el archivo " + Mdl_Variables.Ruta_Aplicativo + "\\7. Descripcion\\" + Mdl_Variables.Rst_Evidencia.getString("INDICE") + "_" + NombreEvd + ": " + e.getMessage());
					System.out.println("Error generando el archivo " + Mdl_Variables.Ruta_Evidencia + "\\7. Descripcion\\" + Mdl_Variables.Rst_Evidencia.getString("INDICE") + "_" + NombreEvd + ": " + e.getMessage());
					return;
				}
				// CAA_2013-01-04 End

			    bw = new BufferedWriter(fw);
			    PrintWriter MyFile_Datos = new PrintWriter(bw);
			    MyFile_Datos.println("<table>");
			    
				MyFile_Screen.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"><HTML><HEAD>");
				MyFile_Screen.println("<SCRIPT type=\"text/javascript\" src=\"../../../Recursos/slideshow.js\"></SCRIPT><SCRIPT type=\"text/javascript\"><!--"); // CAA_2013-01-04
				MyFile_Screen.println("SLIDES = new slideshow(\"SLIDES\");");
				MyFile_Screen.println("s = new slide();");
				
				String datos_Evd = null;
				String mensaje_log=null;
				while(RS_ScreenEvd.next()){
					datos_Evd =  RS_ScreenEvd.getString("IMAGEN");
					mensaje_log =  RS_ScreenEvd.getString("MENSAJE_LOG");

					
	                MyFile_Screen.println("s = new slide();");
					MyFile_Screen.println("s.src =  \"" + datos_Evd.split("\\\\")[datos_Evd.split("\\\\").length - 1]   + "\";");
					MyFile_Screen.println("s.text=\"\"");
					MyFile_Screen.println("SLIDES.add_slide(s);");
				}
				MyFile_Screen.println("//-->");
				MyFile_Screen.println("</SCRIPT>");
				MyFile_Screen.println("</HEAD><BODY onLoad=\"SLIDES.pause()\"></body>");
				MyFile_Screen.println("<table align = center>");
				MyFile_Screen.println("<FORM>");
				MyFile_Screen.println("<tr>"); // Mario Alejandro Prieto Valdes
				MyFile_Screen.println("<td align = center>");
				MyFile_Screen.println("<INPUT TYPE=\"image\"  src='../../../Recursos/anterior.JPG' width=\"60\" height=\"40\" onmouseover=\"SLIDES.pause();SLIDES.previous()\">"); // caa_2013-02-04
				MyFile_Screen.println("<INPUT TYPE=\"image\"  src='../../../Recursos/pausa.JPG' width=\"60\" height=\"40\" onmouseover=\"SLIDES.pause()\">"); // caa_2013-02-04
				MyFile_Screen.println("<INPUT TYPE=\"image\"  src='../../../Recursos/slide.JPG' width=\"60\" height=\"40\" onmouseover=\"SLIDES.play()\">"); // caa_2013-02-04
				MyFile_Screen.println("<INPUT TYPE=\"image\"  src='../../../Recursos/siguiente.JPG' width=\"60\" height=\"40\" onmouseover=\"SLIDES.next();SLIDES.pause()\">"); // caa_2013-02-04
				MyFile_Screen.println("</td>");
				
				MyFile_Screen.println("</tr>");// Mario Alejandro Prieto Valdes
				
				MyFile_Screen.println("<tr>");// Mario Alejandro Prieto Valdes
				MyFile_Screen.println("<td align = center>");// Mario Alejandro Prieto Valdes
				MyFile_Screen.println("<script>");// Mario Alejandro Prieto Valdes
				MyFile_Screen.println("document.write('<div id=\"showlinks\">  </div>')");// Mario Alejandro Prieto Valdes
				MyFile_Screen.println("SLIDES.showlinks();");// Mario Alejandro Prieto Valdes
				MyFile_Screen.println("</script>");// Mario Alejandro Prieto Valdes
				MyFile_Screen.println("</td>");// Mario Alejandro Prieto Valdes
				MyFile_Screen.println("</tr>");// Mario Alejandro Prieto Valdes
				
				MyFile_Screen.println("</FORM>");
				MyFile_Screen.println("</table>");
				MyFile_Screen.println("<table align = center>");
				MyFile_Screen.println("<IMG name=\"SLIDESIMG\" src=\"..\\" + datos_Evd + "\" WIDTH=896 HEIGHT=560 alt=\"Slideshow image\">");
				MyFile_Screen.println("</table>");
				MyFile_Screen.println("<SCRIPT type=\"text/javascript\"><!--");
				MyFile_Screen.println("if (document.images) {  SLIDES.image = document.images.SLIDESIMG;  SLIDES.textid = \"SLIDESTEXT\";  SLIDES.update();  SLIDES.play();}");
				MyFile_Screen.println("//-->");
				MyFile_Screen.println("</SCRIPT>");
				MyFile_Screen.println("</BODY>");
				MyFile_Screen.println("</HTML>");
				
				ResultSetMetaData RSMD_Edatos = RS_Edatos.getMetaData();
				while(RS_Edatos.next()){
					MyFile_Datos.println("<TR><TD><font color=red style=\"font-family: Arial;font-size: 14px;\"><b>Mensaje ejecución : </b>"+mensaje_log +"</i></font>");
//                  
					for(int a = 1 ; a <= RSMD_Edatos.getColumnCount() ; a++)
//						if(!RSMD_Edatos.getColumnName(a).equalsIgnoreCase("INDEX") && !RSMD_Edatos.getColumnName(a).equalsIgnoreCase("EJECUCION_AC_ERR") && !RSMD_Edatos.getColumnName(a).equalsIgnoreCase("DESCRIPCION_ACIERTO") && !RSMD_Edatos.getColumnName(a).equalsIgnoreCase("CASO_FELIZ") && !RSMD_Edatos.getColumnName(a).equalsIgnoreCase("ESTADO_CASO") && !RSMD_Edatos.getColumnName(a).equalsIgnoreCase(" ESTADO_CASO") && !RSMD_Edatos.getColumnName(a).equalsIgnoreCase("HORA") && !RSMD_Edatos.getColumnName(a).equalsIgnoreCase("UTILIZADO") && !RSMD_Edatos.getColumnName(a).equalsIgnoreCase("ID_CASO") && RS_Edatos.getString(a) != null && !RS_Edatos.getString(a).equalsIgnoreCase("NULL") && !RS_Edatos.getString(a).equalsIgnoreCase("<IGNORE>"))
						if(RSMD_Edatos.getColumnName(a).equals("DESIGN_STEPS"))					
							MyFile_Datos.println("<TR><TD><font style=\"font-family: Arial;font-size: 14px;\"><b>" + RSMD_Edatos.getColumnName(a) + " : </b>" + RS_Edatos.getString(a) + "</font>");
//                                              if(!RSMD_Edatos.getColumnName(a).equalsIgnoreCase("INDEX") && !RSMD_Edatos.getColumnName(a).equalsIgnoreCase("EJECUCION_AC_ERR") && !RSMD_Edatos.getColumnName(a).equalsIgnoreCase("DESCRIPCION_ACIERTO") && !RSMD_Edatos.getColumnName(a).equalsIgnoreCase("CASO_FELIZ") && !RSMD_Edatos.getColumnName(a).equalsIgnoreCase("ESTADO_CASO") && !RSMD_Edatos.getColumnName(a).equalsIgnoreCase(" ESTADO_CASO") && !RSMD_Edatos.getColumnName(a).equalsIgnoreCase("HORA") && !RSMD_Edatos.getColumnName(a).equalsIgnoreCase("UTILIZADO") && !RSMD_Edatos.getColumnName(a).equalsIgnoreCase("DESCRIPCION_ERROR") && !RSMD_Edatos.getColumnName(a).equalsIgnoreCase("TIEMPO_EJECUCION_CASO") && !RSMD_Edatos.getColumnName(a).equalsIgnoreCase("ID_CASO") && RS_Edatos.getString(a) != null && !RS_Edatos.getString(a).equalsIgnoreCase("NULL") && !RS_Edatos.getString(a).equalsIgnoreCase("<IGNORE>"))
//				                          MyFile_Datos.println("<TR><TD><b>" + RSMD_Edatos.getColumnName(a) + " : </b>" + RS_Edatos.getString(a) + "");
				}
					
	            MyFile_Datos.println("</table>");
				MyFile_Datos.close();
	            MyFile_Screen.close();
	            try{
	            	RS_ScreenEvd.close();
				}catch(Exception e){
					//continua con el proceso
				}
			}
			try{
				Mdl_Variables.Rst_Evidencia.close();
			}catch(Exception e){
				//continua con el proceso
			}
		} catch (SQLException e) {
			System.out.println("Error recorriendo las evidencias: " + e.getMessage());
			return;
		}
		//Creación Diagrama
		// CAA_2013-01-04 Begin 
		try {
		 // fw = new FileWriter(Mdl_Variables.Ruta_Aplicativo + "\\9. Diagramas\\"  + NombreEvd);
			fw = new FileWriter( Mdl_Variables.Ruta_Evidencia + "\\9. Diagramas\\"  + NombreEvd);
		} catch (IOException e) {
		 // System.out.println("Error generando el archivo " + Mdl_Variables.Ruta_Aplicativo + "\\9. Diagramas\\"  + NombreEvd + ": " + e.getMessage());
			System.out.println("Error generando el archivo " + Mdl_Variables.Ruta_Evidencia + "\\9. Diagramas\\"  + NombreEvd + ": " + e.getMessage());
			return;
		}
		// CAA_2013-01-04 End

	    bw = new BufferedWriter(fw);
	    PrintWriter MyFile_Diagrama = new PrintWriter(bw);
		MyFile_Diagrama.println("<HTML>");
		MyFile_Diagrama.println("<HEAD>");
		MyFile_Diagrama.println("<TITLE>" + Mdl_Variables.P_Str_Tabla + "</TITLE>	<table border = 1 align = center><td><b>Consolidado para la versión " + Mdl_Variables.Vrb_VersionAplicativo + "</b></td><td>");
		MyFile_Diagrama.println("<style type=\"text/css\">");
		
		MyFile_Diagrama.println("	body {");
		MyFile_Diagrama.println("font-family: Arial, Helvetica, sans-serif;");
		MyFile_Diagrama.println("font-size: 12px;");
		MyFile_Diagrama.println("}");
		
		MyFile_Diagrama.println("	</style>");
		MyFile_Diagrama.println("<SCRIPT LANGUAGE=\"Javascript\" SRC=\"../../../Recursos/FusionCharts.js\"></SCRIPT>"); // caa_2013-02-04
		MyFile_Diagrama.println("<SCRIPT LANGUAGE=\"JavaScript\">");
		
		String strarracierto = "", strarrerror = "", strencabezado = "";
		
		Str_Evidencias = "SELECT  distinct(DE.CICLO) " +
				"      FROM TBL_LOG_DETALLE DE " +
				"INNER JOIN TBL_LOG_MAESTRO MA ON MA.ID_MAESTRO = DE.ID_MAESTRO " +
				"     WHERE MA.NOMBRE_TABLA like '" + Mdl_Variables.P_Str_Tabla + "' AND DE.VERSION ='" + Mdl_Variables.Vrb_VersionAplicativo + "' ";
		try {
			Mdl_Variables.Rst_Evidencia = Mdl_Variables.Cnn.prepareCall(Str_Evidencias).executeQuery();
		} catch (SQLException e) {
			System.out.println("Error realizando la consulta: " + e.getMessage());
			return;
		}
		ResultSet rsAux = null;
		String strAux = "";
		try {
			while(Mdl_Variables.Rst_Evidencia.next()){
				strencabezado = strencabezado  + "<category name='" + Mdl_Variables.Rst_Evidencia.getString(1) + "' />";
				rsAux = Mdl_Variables.Cnn.prepareCall("SELECT  count(distinct(DE.INDICE)) " +
						"      FROM TBL_LOG_DETALLE DE " +
						"INNER JOIN TBL_LOG_MAESTRO MA ON MA.ID_MAESTRO = DE.ID_MAESTRO " +
						"WHERE MA.NOMBRE_TABLA like '" + Mdl_Variables.P_Str_Tabla + "' AND DE.VERSION ='" + Mdl_Variables.Vrb_VersionAplicativo + "' and DE.CICLO = '" + Mdl_Variables.Rst_Evidencia.getString(1) +"' AND DE.INDICE not in " +
						"(SELECT INDICE FROM TBL_LOG_DETALLE DET " +
						"INNER JOIN TBL_LOG_MAESTRO MAS ON MAS.ID_MAESTRO = DET.ID_MAESTRO " +
						"WHERE DET.TIPO_LOG  in(2,4,6) AND MAS.NOMBRE_TABLA like '" + Mdl_Variables.P_Str_Tabla + "' AND DET.VERSION = '" + Mdl_Variables.Vrb_VersionAplicativo + "'  and DET.CICLO = '" + Mdl_Variables.Rst_Evidencia.getString(1) +"') ").executeQuery();
				if(rsAux.next())
					strAux = rsAux.getString(1);
				else{ //esto no debería presentarse pero se valida
					System.out.println("No se encontraron datos en la consulta. Valores de la consulta: " + Mdl_Variables.P_Str_Tabla + ", " + Mdl_Variables.Vrb_VersionAplicativo + ", " + Mdl_Variables.Rst_Evidencia.getString(1));
					return;
				}
				strarracierto = strarracierto + "," + strAux;

				rsAux = Mdl_Variables.Cnn.prepareCall("SELECT count(distinct(DE.INDICE)) " +
						"      FROM TBL_LOG_DETALLE DE " +
						"INNER JOIN TBL_LOG_MAESTRO MA ON MA.ID_MAESTRO = DE.ID_MAESTRO " +
						"     WHERE MA.NOMBRE_TABLA like '" + Mdl_Variables.P_Str_Tabla + "' AND DE.VERSION ='" + Mdl_Variables.Vrb_VersionAplicativo + "' and DE.CICLO = '" + Mdl_Variables.Rst_Evidencia.getString(1) +"' AND DE.INDICE  in " +
						"(SELECT INDICE FROM TBL_LOG_DETALLE DET " +
						"INNER JOIN  TBL_LOG_MAESTRO MAS ON MAS.ID_MAESTRO = DET.ID_MAESTRO " +
						"WHERE DET.TIPO_LOG  in(2,4,6) AND MAS.NOMBRE_TABLA like '" + Mdl_Variables.P_Str_Tabla + "' AND DET.VERSION = '" + Mdl_Variables.Vrb_VersionAplicativo + "'  and DET.CICLO = '" + Mdl_Variables.Rst_Evidencia.getString(1) + "') " ).executeQuery();
				if(rsAux.next())
					strAux = rsAux.getString(1);
				else{
					System.out.println("No se encontraron Datos en la consulta. Valores de la consulta: " + Mdl_Variables.P_Str_Tabla + ", " + Mdl_Variables.Vrb_VersionAplicativo + ", " + Mdl_Variables.Rst_Evidencia.getString(1));
					return;
				}
				strarrerror =strarrerror  + "," + strAux;
			}
		} catch (SQLException e) {
			System.out.println("Error en el recorrido: " + e.getMessage());
			return;
		}
		
		MyFile_Diagrama.println("var data = new Array();");
		MyFile_Diagrama.println("data[0] = new Array(\"Casos Ok\"" + strarracierto + ");");
		MyFile_Diagrama.println("data[1] = new Array(\"Casos Fallidos\"" + strarrerror + ");");
		
		MyFile_Diagrama.println("var colors=new Array(\"00FF00\", \"FF0000\", \"000000\", \"000000\");");
		MyFile_Diagrama.println("function updateChart(domId){			");
		MyFile_Diagrama.println("updateChartXML(domId,generateXML(this.document.productSelector.AnimateChart.checked));");
		MyFile_Diagrama.println("}");
		MyFile_Diagrama.println("function generateXML(animate){			");
		MyFile_Diagrama.println("var strXML=\"\";");
		MyFile_Diagrama.println("strXML = \"<graph numberPrefix='' decimalPrecision='0' animation='\" + ((animate==true)?\"1\":\"0\") + \"' \" + ");
		MyFile_Diagrama.println("((1==1)?(\"showValues='1' rotateValues='1'\"):(\" showValues='0' \")) + ");
		MyFile_Diagrama.println("\"yaxismaxvalue='" + Vrb_ConteoAciertos + Vrb_ConteoErrores + "'>\"; ");
		MyFile_Diagrama.println("strXML = strXML + \"<categories>" + strencabezado +"</categories>\"; ");
		
		MyFile_Diagrama.println("			strXML = (1==1)?(strXML + getProductXML(0)):(strXML); ");
		MyFile_Diagrama.println("strXML = (1==1)?(strXML + getProductXML(1)):(strXML); ");
		MyFile_Diagrama.println("strXML = strXML + \"</graph>\"; ");
		MyFile_Diagrama.println("return strXML; ");
		MyFile_Diagrama.println("} ");
		MyFile_Diagrama.println("function getProductXML(productIndex){		 ");
		MyFile_Diagrama.println("var productXML; ");
		MyFile_Diagrama.println("productXML = \"<dataset seriesName='\" + data[productIndex][0] + \"' color='\"+ colors[productIndex]  +\"' >\";			 ");
		MyFile_Diagrama.println("for (var i=1; i<=4; i++){ ");
		MyFile_Diagrama.println("productXML = productXML + \"<set value='\" + data[productIndex][i] + \"' />\"; ");
		MyFile_Diagrama.println("} ");
		MyFile_Diagrama.println("productXML = productXML + \"</dataset>\"; ");
		MyFile_Diagrama.println("return productXML;			 ");
		MyFile_Diagrama.println("} ");
		
		MyFile_Diagrama.println("</SCRIPT> ");
		MyFile_Diagrama.println("</HEAD> ");
		MyFile_Diagrama.println("<BODY> ");
		MyFile_Diagrama.println("<CENTER> ");
		MyFile_Diagrama.println("<div id=\"chart1div\"></div> ");
		MyFile_Diagrama.println("<script language=\"JavaScript\">					 ");
		MyFile_Diagrama.println("var chart1 = new FusionCharts(\"../../../Recursos/FCF_MSColumn3D.swf\", \"chart1Id\", \"300\", \"300\");		    "); // caa_2013-02-04
		MyFile_Diagrama.println("var strXML=generateXML(1); ");
		MyFile_Diagrama.println("chart1.setDataXML(strXML); ");
		MyFile_Diagrama.println("chart1.render(\"chart1div\"); ");
		MyFile_Diagrama.println("</script> ");
		MyFile_Diagrama.println("<BR /> ");
		MyFile_Diagrama.println("</CENTER> ");
		MyFile_Diagrama.println("</BODY> </td></table>");
		MyFile_Diagrama.println("</HTML> ");
		MyFile_Diagrama.close();
		try{
			Mdl_Variables.Rst_Evidencia.close();
		}catch(Exception e){
			//continua con el proceso
		}
		
	} // Generar_HTML End

	// CAA_2013-02-04 Begin 
	public static void VerificarEnvioCorreo( String claveParametrosCorreo ) throws SQLException{
		// Se envia correo cuando TBL_SCHEDULE_EXE.MAIL = true
		boolean bln_enviarMail = Mdl_Variables.Rst_QaPendienes.getBoolean("MAIL"); 
		if ( bln_enviarMail ) {
			
			Properties DBProperties;
			DBProperties = getDBProperties( claveParametrosCorreo );
			
			String sMailTO = DBProperties.getProperty( "TO" );
			if ( sMailTO == null ) {  
				sMailTO = Mdl_Variables.Rst_QaPendienes.getString("MAIL_TO");
				DBProperties.setProperty( "TO", sMailTO );
			}
			
			String msgBody = DBProperties.getProperty( "msgBody" );
			if ( msgBody == null ) {
				DBProperties.setProperty( "msgBody", "@\"" + Mdl_Variables.Ruta_Evidencia + "\\6. Encabezado\\" + NombreEvd + "\"" );
			}
			// Convierte las propiedades a String 
			String sCmdArgs = "";
			for(String key : DBProperties.stringPropertyNames()) {
				  String value = DBProperties.getProperty(key);
				  sCmdArgs = sCmdArgs + key + "=" + value + " ";
				}
			 System.out.println( RunShell( DBProperties.getProperty("mail.application"), sCmdArgs ) ); // Ejecuta Shell para enviar correo
            DBProperties = null;

			// enviarCorreo( sbHeader.toString(),""  );
		}
		
		
	} //VerificarEnvioCorreo

	public static Properties getDBProperties(String claveParametros ){
		Properties propList = new Properties();

		// recupera los parametros de la base de de datos
	    ResultSet rs_propList = null;
	    try {
	    	rs_propList = Mdl_Variables.Cnn.prepareCall("select parametro, valor from TBL_PARAMETROS where funcionalidad = '"+ claveParametros + "'").executeQuery() ;
	        while(rs_propList.next()){
	          // almacena las propiedades de la db en un property List
	    	  propList.setProperty( rs_propList.getString("parametro"), rs_propList.getString("valor") );
	        } // while end
		} // try end
	    catch(SQLException e){ System.out.println("Error Mdl_Evidenciamiento.getDBProperties: Recuperar parametros bd: " + e.getMessage()); return propList; } // catch end

	    // retorna un objeto tipo properties (que es una lista de parametros y valores ( key.values ) )
		return propList;
	}

	// funcion RunShell: Funcion de uso general que puede ser invocado desde cualquier parte del codigo para ejecutar una aplicacion externa y retorna al resultado de la misms
	// Parametro de Entrada sAppPath: Ruta y nombre de la aplicacion a ejecutar
	// Parametro de Entrada sCmdArgs: Parametros que se pasan a la aplicacion.
	
	public static String RunShell( String sAppPath, String sCmdArgs ){
			//String sOutput = "";
			StringBuilder sOutput = new StringBuilder();
			try {
				String command = sAppPath + " " + sCmdArgs;
			    Process child = Runtime.getRuntime().exec(command);
			    
			    // captura los resultados de la ejecucion
			    java.io.BufferedReader input = new java.io.BufferedReader(new java.io.InputStreamReader(child.getInputStream()));
			    	String line = null;
			    	//Here we first read the next line into the variable
			    	//line and then check for the EOF condition, which
			    	//is the return value of null
			    	while((line = input.readLine()) != null){
			    		sOutput.append( line+'\n');
			    	}
			}
			catch(Exception ex ){
				sOutput.append(ex.getMessage());
				//System.out.println("Choucair.Error.." + ex.getMessage() );
			}
        	return sOutput.toString();
	}  
		
//	public static void enviarCorreo( String m_text, Properties props ){
//      	 
//	    // Authenticator auth = new SMTPAuthenticator(); 
//	    SMTP_AUTH_USER = props.getProperty("mail.smtp.user") ;
//	    SMTP_AUTH_PWD  = props.getProperty("mail.smtp.password") ;
//		Session session = Session.getInstance(props, 
//			new javax.mail.Authenticator() {
//              protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(SMTP_AUTH_USER, SMTP_AUTH_PWD); }
//              }
//		); //props, auth
//			session.setDebug(true);
//
//		try {			
//		MimeMessage msg = new MimeMessage(session);
//			msg.setFrom(new InternetAddress( props.getProperty( "From" ) ));
//			
//			//Data String  
//			String[] to = props.getProperty( "TO" ).split(",");  
//	        InternetAddress[] addressTo = new InternetAddress[to.length];
//	        for (int i = 0; i < to.length; i++) {
//	             addressTo[i] = new InternetAddress(to[i]);
//	        }
//	        msg.setRecipients(Message.RecipientType.TO, addressTo); 			
//			//msg.addRecipient(Message.RecipientType.TO, new InternetAddress( props.getProperty( "TO" ) ));
//			
//			msg.setSubject( props.getProperty( "Subject" ) );
//			msg.setContent( m_text, props.getProperty("ContentType") ); //setText: TextoPlano or SetContent: text/html
//			msg.setSentDate(new Date());
//
//		int i_puerto_servidor= Integer.valueOf( props.getProperty( "mail.smtp.port" ) );
//		
//		Transport transport = session.getTransport( props.getProperty( "Transport" ) ); //Session.getTransport(Address) 
//			transport.connect( props.getProperty( "mail.smtp.host" ), i_puerto_servidor, SMTP_AUTH_USER, SMTP_AUTH_PWD );
//			transport.sendMessage(msg, msg.getAllRecipients());
//			transport.close();
//		} // try end
//	    catch(Exception e){ System.out.println("Error Mdl_Evidenciamiento.enviarCorreo: sendMessage " + e.getMessage()); return; 
//	    } // catch end
//
//	} // enviar_Correo End
//
	// CAA_2013-02-04 End

	public static void Ingreso_Datos_Historia()
	{
	    try
		{
	        Integer intExitosos=0;
	        Integer intFallidos=0;
	        Double avgQrydst;
	        Double avgQryorg;
	        ResultSet Rst_Tabla_aux;
	        String strQueryHistoria;
	        
	        Statement stmt = Mdl_Variables.Cnn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	    	
	        // abrir el recordset indicando la tabla keyword a la que queremos acceder
	    	Rst_Tabla_aux = stmt.executeQuery("SELECT * FROM " + Mdl_Variables.P_Str_Datos );
	
	    	while(Rst_Tabla_aux.next())
	    	{
	            if (Rst_Tabla_aux.getString("EJECUCION_AC_ERR").trim().contentEquals("0")) 
	                intExitosos += 1;
	            else
	                intFallidos += 1;
	    	}
	
	        avgQryorg = (double)Mdl_Variables.P_Int_TiempoTotalEjecucionOrg / (intExitosos + intFallidos);
	        avgQrydst = (double)Mdl_Variables.P_Int_TiempoTotalEjecucionDst / (intExitosos + intFallidos);
	
	        strQueryHistoria = "INSERT INTO HISTORIA (ESCENARIO, CICLO, NOMBRE_VERSION, TOTAL_CASOS, CASOS_EXEC," +
	                         " CASOS_FALLIDOS, CASOS_EXITOSOS, TIEMPO_EJECUCION, AVG_TMP_QRY_DST, AVG_TMP_QRY_ORG, RESPONSABLE_EXEC," +
	                         " FECHA_HORA, MAQUINA) values ('" + Mdl_Variables.P_Str_Datos + "', '" + Mdl_Variables.P_Str_Ciclo + "', '" + Mdl_Variables.P_Str_Nombre_Version + "'," +
	                         (intExitosos + intFallidos) + ", " + (intExitosos + intFallidos) + ", " + intFallidos + ", " + intExitosos + ", " +
	                         Mdl_Choucair.Diferencia_Fechas(Mdl_Variables.P_TiempoTotal_Sistema, new Date()) + ", " + avgQrydst + ", " + avgQryorg + ", '" + Mdl_Variables.P_Str_Tester + "', getdate() ,'" +
	                        Mdl_Variables.P_Str_NombreMaquina + "')";
	
	        Mdl_Variables.Cnn.prepareCall(strQueryHistoria).execute();
	
		}catch (Exception ex){
			System.out.println("Error en Mdl_Evidenciamiento.Ingreso_Datos_Historia: " + ex.getMessage());
		}
	}
	
	
	
	/*
public static void CADENA_PASOS(String desc_paso) throws SQLException
	{
		String StrCompara;
		
		if (!desc_paso.contentEquals(""))
		{
			StrCompara = Mdl_Variables.Rst_Tabla.getString("DESIGN_STEPS");
			
			
			if (!StrCompara.equals(""))
				desc_paso = "<TR><TD>"+Mdl_Variables.Rst_Coordenadas.getString("ID")+".-"+desc_paso + "</TR></TD>";		
			else
				desc_paso = StrCompara+"<TR><TD>"+Mdl_Variables.Rst_Coordenadas.getString("ID")+".-"+desc_paso+"</TR></TD>";	
		}
		
		try
		{
			Mdl_Variables.Cnn.prepareCall( "UPDATE " + Mdl_Variables.P_Str_Datos + "  SET DESIGN_STEPS = '" + desc_paso + "' WHERE [INDEX] = " + Mdl_Variables.Rst_Tabla.getString("id_caso")).execute();
			
			//Mdl_Variables.Rst_Evidencia.getString("INDICE")
			//Mdl_Variables.Rst_Tabla.getString("INDEX")

		}catch (Exception ex){
			System.out.println("Error en Mdl_Evidenciamiento: " + ex.getMessage());
		}
	}
	 */
	
	
	
	public void VALIDAR_TIPO_LOG(String ATTACH_OBJETO , String strValidarMensaje , String strParametroAdicional )
    {
    //'On Error GoTo TrataError    'MAS 22062010 - Se cambia en try por On Error
	    try
	    {
	    /*
	     * '''''''''''''''logs reporter''''''''''''''''''''''''''
	        'LogInfo("Información") 'Entrega un mensaje de Información
	        'LogWarning("Warning2") 'Entrega un mensaje de Advertencia
	        ''LogError("Error") 'Entrega un mensaje de Error
	        'LogTestResult("Test Satisfactorio", True, "Esto quedo bien") 'Entrega un mensaje OK
	        'LogTestResult("Test Satisfactorio", True, "Esto quedo bien") 'Entrega un mensaje OK
	        ''''''''''''''''''''''''''''''''''''''''''''''''''''''
	
	        'mensajes INFORMATIVOS: 1, VALIDACION: 2, ERROR: 3
	        'SI MENSAJE EXISTE
	        '   LEER_MENSAJE
	        '----------------------------------------------
	        'CASO_ACIERTO & MSG_INFORMATIVO = CASO EXITOSO '
	        'CASO_ACIERTO & MSG_VALIDACION = CASO EXITOSO  '
	        'CASO_ACIERTO & MSG_ERROR = CASO NO EXITOSO    '
	        '----------------------------------------------
	        'CASO_ERROR & MSG_INFORMATIVO = CASO NO EXITOSO'
	        'CASO_ERROR & MSG_VALIDACION = CASO EXITOSO    '
	        'CASO_ERROR & MSG_ERROR = CASO NO EXITOSO      '
	        '----------------------------------------------
	        '0 error    -1 acierto
	
	        'GENERAR_EVIDENCIA
	     */
	        
	
	        Boolean Bool_Acierto;
	        String[] arrObj = new String[1];
	        String[] arrParametroAdicional; 		//'FAVC20110809
	        String[] arrParamAux; 					//'FAVC20110809
	        int intFilaMsg = 2; 						//'FAVC20110809
	        String strUbicacionMsg = ""; 			//'FAVC20110809
	        Boolean blnValidacionIntermedia = false;
	        Bool_Acierto = false;
	
	        if (ATTACH_OBJETO.contentEquals("")) 		//'MAS - 10062010
	        {
	            ATTACH_OBJETO = ";;";
	        }
	        //'FAVC 20110819 DESAGREGACIÓN DE PARAMETROS ADICIONALES
            //'******************************************************************
            arrParametroAdicional = strParametroAdicional.split(";");
            if (arrParametroAdicional.length > 1)
            {
                for (int i = 0; i<= arrParametroAdicional.length; i++)
                {
                    if (arrParametroAdicional[i].toLowerCase().contains("fila"))
                    {
                        arrParamAux = arrParametroAdicional[i].split(":");
                        intFilaMsg = Integer.parseInt(arrParamAux[1]);
                    }
                    if (arrParametroAdicional[i].toLowerCase().contains("validacion_intermedia"))
                    {
                        arrParamAux =arrParametroAdicional[i].split(":");
                        if (arrParamAux.length > 1)
                            if(arrParamAux[1].toLowerCase().contentEquals("true"))
                            	blnValidacionIntermedia = true;
                            else
                            	blnValidacionIntermedia = false;
                     }

                }
            }
            //'******************************************************************
            if (ATTACH_OBJETO != ";;")
            {
                String[] Vrb_Spl_Attach;
                Vrb_Spl_Attach = ATTACH_OBJETO.split(";");
                if (Vrb_Spl_Attach[0].toLowerCase().contentEquals("htmlspan"))
                {
                	Web_Framework.Func_ObjectSet(arrObj);
                	if (Mdl_Variables.objPerform != null)
                	{
                		 //if (Mdl_Variables.objPerform.exists())
                			 Mdl_Variables.Str_Mensaje = Mdl_Variables.objPerform.getText();
                	}
                	else
                	{
                		Mdl_Variables.Str_Mensaje = "";
                	}
                }
                else if (Vrb_Spl_Attach[0].toLowerCase().contentEquals("htmldiv"))
                {
                	Web_Framework.Func_ObjectSet(arrObj);
                	if (Mdl_Variables.objPerform != null)
                		Mdl_Variables.Str_Mensaje = Mdl_Variables.objPerform.getText();	//	 Mdl_Variables.Str_Mensaje = Mdl_Variables.objPerform.getProperty(".text").toString();
           	    }
                else if (Vrb_Spl_Attach[0].toLowerCase().contentEquals("htmltable"))
                {
  //StatelessGuiSubitemTestObject objAux = new StatelessGuiSubitemTestObject(Mdl_Variables.objPerform);  //'Objeto auxiliar para asignarlo al objeto Tabla
                    //Dim Fila As HtmlRow = New HtmlRow(Vrb_objBrowser) 'Objeto Fila que recorre las tablas
                    //Dim celda As HtmlCell = New HtmlCell(Vrb_objBrowser) 'Objeto Celda de la tabla de cada registro

                	Web_Framework.Func_ObjectSet(arrObj);
                	if (strUbicacionMsg != "" && strUbicacionMsg.toLowerCase().contains("fila"))
                	{
                		arrParametroAdicional = strUbicacionMsg.split("|");
	                    for (int i = 0; i<=arrParametroAdicional.length; i++)
	                    {
	                    	if (arrParametroAdicional[i].toLowerCase().contains("fila"))
	                    	{
	                            arrParamAux = arrParametroAdicional[i].split(":");
	                            intFilaMsg = Integer.parseInt(arrParamAux[i]);
	                    	}
	                    }
                	}
                	if (Mdl_Variables.objPerform != null)
                	{
                		// 26-12-2011 MAP la herramienta no es capaz de realizar este tipo de busqueda
                		
                		/*
                		 * objAux = objPerform
                            If objAux.RowCount <= intFilaMsg + 1 Then ' CONTROL PARA TABLA DE ERRORES EN BCSC
                                For Each Fila In objAux.Rows
                                    If Fila.RowIndex = intFilaMsg Then
                                        Str_Mensaje = Fila.Cells(0).GetProperty("InnerText")
                                    End If
                                Next
                            End If
                		 */
	                  
                	}
                	else
                		Mdl_Variables.P_Tipo_Log = 1; //'FAVC20110809 Instrucción necesaria para evitar que salga con error cuando no encuentre un mensaje de error
                
                }
                arrObj = null;
            }
            if (Mdl_Variables.P_Tipo_Log == 4)
            	Mdl_Variables.P_Tipo_Log = 2;
            else
            {
            	Mdl_Variables.Rst_Mensaje.first();
            	Mdl_Variables.Rst_Mensaje.previous();
                while (Mdl_Variables.Rst_Mensaje.next() && Bool_Acierto == false)
                {
                    if (Mdl_Variables.Rst_Mensaje.getString("MENSAJE").toUpperCase().trim().contentEquals(Mdl_Variables.Str_Mensaje.toUpperCase().trim())) //'encontró el mensaje
                    {
                        if (Mdl_Variables.Rst_Mensaje.getString("INFORMACION").contentEquals("False") && Mdl_Variables.Rst_Mensaje.getString("VALIDACION").contentEquals("False") && Mdl_Variables.Rst_Mensaje.getString("ERROR").contentEquals("False"))
                        {
                            Mdl_Variables.Descripcion_Acierto_Error = "SE Encontro el mensaje pero no esta validado en la tabla de mensajes";
                            //'Publicar
                            //'LogWarning("SE Encontro el mensaje pero no esta validado en la tabla de mensajes")
                            Mdl_Variables.P_Tipo_Log = 3;
                            Mdl_Evidenciamiento.Ingreso_Datos_Log();
                            Bool_Acierto = true;
                            return;
                        }
                        if (Mdl_Variables.Rst_Tabla.getString("ACIERTO_ERROR").contentEquals("0") && Mdl_Variables.Rst_Mensaje.getString("INFORMACION").contentEquals("True")) 	//' caso orientado al error y mensaje INFORMATIVO
                        {
                        	//'MAS - 2011Abr15 - Se agrega el # de Paso, para facilitar la trazabilidad.
                            Mdl_Variables.Descripcion_Acierto_Error = "Error en el Paso #" + Mdl_Variables.intRowCount + ". " + Mdl_Variables.Rst_Tabla.getString("DESCRIPCION_ERROR");
                            Mdl_Variables.P_Tipo_Log = 2;
                            Bool_Acierto = true;
                        }
                        else if (Mdl_Variables.Rst_Tabla.getString("ACIERTO_ERROR").contentEquals("0") && Mdl_Variables.Rst_Mensaje.getString("VALIDACION").contentEquals("True")) 	//' caso orientado al error y mensaje VALIDACION
                        {
                        	Mdl_Variables.Descripcion_Acierto_Error = Mdl_Variables.Rst_Tabla.getString("DESCRIPCION_ACIERTO");
                        	Mdl_Variables.P_Tipo_Log = 1;
                            Bool_Acierto = true;
                        }
                        else if (Mdl_Variables.Rst_Tabla.getString("ACIERTO_ERROR").contentEquals("0") && Mdl_Variables.Rst_Mensaje.getString("ERROR").contentEquals("True")) 	//' caso orientado al error y mensaje ERROR
                        {
                        	//'FAVC - 20110808 - Se agrega el # de Paso, para facilitar la trazabilidad.
                            Mdl_Variables.Descripcion_Acierto_Error = "Error en el Paso #" + Mdl_Variables.intRowCount + ". " + Mdl_Variables.Rst_Tabla.getString("DESCRIPCION_ERROR");
                            Mdl_Variables.P_Tipo_Log = 1;
                            Bool_Acierto = true;
                        }
                        if (Mdl_Variables.Rst_Tabla.getString("ACIERTO_ERROR").contentEquals("-1") && Mdl_Variables.Rst_Mensaje.getString("INFORMACION").contentEquals("True")) 	
                        {
                        	Mdl_Variables.Descripcion_Acierto_Error = Mdl_Variables.Rst_Tabla.getString("DESCRIPCION_ACIERTO");
                            Mdl_Variables.P_Tipo_Log = 1;
                            Bool_Acierto = true;
                        }
                        else if (Mdl_Variables.Rst_Tabla.getString("ACIERTO_ERROR").contentEquals("-1") && Mdl_Variables.Rst_Mensaje.getString("VALIDACION").contentEquals("True")) 	
                        {
                        	Mdl_Variables.Descripcion_Acierto_Error = Mdl_Variables.Rst_Tabla.getString("DESCRIPCION_ACIERTO");
                        	Mdl_Variables.P_Tipo_Log = 1;
                            Bool_Acierto = true;
                        }
                        else if (Mdl_Variables.Rst_Tabla.getString("ACIERTO_ERROR").contentEquals("-1") && Mdl_Variables.Rst_Mensaje.getString("ERROR").contentEquals("True")) 	//' caso orientado al error y mensaje ERROR
                        {
                        	//'MAS - 2011Abr15 - Se agrega el # de Paso, para facilitar la trazabilidad.
                            Mdl_Variables.Descripcion_Acierto_Error = "Error en el Paso #" + Mdl_Variables.intRowCount + ". " + Mdl_Variables.Rst_Tabla.getString("DESCRIPCION_ERROR");
                            Mdl_Variables.P_Tipo_Log = 2;
                            Bool_Acierto = true;
                        }
                    }
                }
                if (Bool_Acierto == false)
                {
                	if (Mdl_Variables.Str_Mensaje != "")
                	{
	                    
                		StringBuilder consulta = new StringBuilder();
                		consulta.append("INSERT INTO \"TBL_MENSAJES\"(MENSAJE ,VALIDACION,ERROR,INFORMACION)Values('" + Mdl_Variables.Str_Mensaje + "','False','False','False')");
            			Mdl_Variables.Cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                ResultSet.CONCUR_UPDATABLE).executeQuery(consulta.toString());
                		//'Publicar
	                    //'LogWarning("Se ingreso el siguiente mensaje en la tabla de mensajes : | " & Str_Mensaje & " | por favor clasifiquelo")
                	}
                }
                
                
                //'FAVC20110824 Este fragmento de código se encarga que los mensajes de acierto o error se presenten en las evidencias
                //'Documento las asignaciones a la variable P_Tipo_Log, porque ya viene seteada en líneas anteriores

                //'EVALUAR EL RESULTADO RESPECTO A LA ORIENTACIÓN DE CASO DE PRUEBA
                if (Bool_Acierto == false && Mdl_Variables.Str_Mensaje != "" && Mdl_Variables.Descripcion_Acierto_Error == "" && Mdl_Variables.Rst_Tabla.getString("ACIERTO_ERROR") == "-1") 
                {
                	Mdl_Variables.Descripcion_Acierto_Error = Mdl_Variables.Rst_Tabla.getString("DESCRIPCION_ACIERTO");
                    //'P_Tipo_Log = errores.MSG_valido
                    //'ElseIf Str_Mensaje = "" And blnValidacionIntermedia = True Then
                    //'FAVC20110819 se crea condicion nueva
                    //'P_Tipo_Log = errores.MSG_valido
                }
                else if (Bool_Acierto == false && Mdl_Variables.Str_Mensaje != "" && Mdl_Variables.Descripcion_Acierto_Error == "" && Mdl_Variables.Rst_Tabla.getString("ACIERTO_ERROR") == "0") 
                {
                	//'MAS - 2011Abr15 - Se agrega el # de Paso, para facilitar la trazabilidad.
                    Mdl_Variables.Descripcion_Acierto_Error = "Error en el Paso #" + Mdl_Variables.intRowCount + ". " + Mdl_Variables.Rst_Tabla.getString("DESCRIPCION_ERROR");
                    //'P_Tipo_Log = errores.MSG_Error
                }
                if (Mdl_Variables.P_Tipo_Log == 0)
                    Mdl_Variables.P_Tipo_Log = 3;
                //'se llama la pantalla para ingresar los datos al log
                if (strValidarMensaje.toLowerCase() != "validar_mensaje")
                    Mdl_Evidenciamiento.Ingreso_Datos_Log();
                
            }
            
	    }
	    catch(Exception ex)
	    {
	    }
    }
	
	//WMN20150323: Se agrega la funcion generar_html2, por estandarización de las evidencias en el cliente.
		public static void Generar_HTML2(String strAutomatizador, String strResponsable) throws SQLException{
			// CAA_2013-02-04 Begin
			Mdl_Variables.Ruta_Evidencia = Mdl_Variables.Ruta_Aplicativo + "\\" + Mdl_Variables.Vrb_VersionAplicativo + "\\" + Mdl_Variables.P_Str_Ciclo ;

			int intCasoActual = 0; //intCasoActual
			
			int intVrbConteoErrores 	= 0; //intVrbConteoErrores
			int intVrbConteoAciertos 	= 0; //intVrbConteoAciertos
			int intVrbTotalCasos 		= 0; // intVrbTotalCasos
			int intContador 			= 0; //intContador
			int intIndice 				= 0; // intIndice
			int intPosicion 			= 0; // intPosicion
			int intTiempoTotalEscenario = 0; //intTiempoTotalEscenario
			
			String strVrbDtConteoErrores 	= ""; //strVrbDtConteoErrores
			String strVrbDtConteoAciertos 	= ""; //strVrbDtConteoAciertos
			String strVrbDtTotalCasos 		= ""; //strVrbDtTotalCasos
			String strVrbTotalTiempos 		= ""; //strVrbTotalTiempos
			String strPasos  				= ""; //strPasos
			String strFecha 				= ""; //strFecha
			String strFechaEvd 				= ""; //strFecha
			
			Date datFecha = new Date();
			SimpleDateFormat sdfFecha = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
			//SimpleDateFormat adfFecha = new SimpleDateFormat("dd-mm-YYYY");
			strFecha = sdfFecha.format(datFecha);
			strFechaEvd = strFecha.replace("/", "");
			strFechaEvd = strFechaEvd.replace(":", "");
			strFechaEvd = strFechaEvd.replace(" ", "");
			//FileWriter fw;
			//FileOutputStream fw;
			OutputStreamWriter osw;
			NombreEvd = Mdl_Variables.P_Str_Datos.replace("_D", "") + "_" + Mdl_Variables.Vrb_VersionAplicativo + "_" + Mdl_Variables.P_Str_Ciclo  + "_" + strFechaEvd + ".html";
			try 
			{
				// fw = new FileWriter(Mdl_Variables.Ruta_Aplicativo + "\\" + NombreEvd);
				
				osw = new OutputStreamWriter(new FileOutputStream(Mdl_Variables.Ruta_Evidencia + "\\" + NombreEvd), "UTF-8");
				
			}
			catch (IOException e) 
			{
				// System.out.println("Error generando el archivo " + Mdl_Variables.Ruta_Aplicativo + "\\" + NombreEvd + ": " + e.getMessage());
				System.out.println("Error generando el archivo " + Mdl_Variables.Ruta_Evidencia + "\\" + NombreEvd + ": " + e.getMessage());
				return;
			}
			
		    BufferedWriter bw = new BufferedWriter(osw);
		    //PrintWriter MyFile = new PrintWriter(bw);
			
			//bw = new BufferedWriter(fw);
			PrintWriter MyFile_Screen = new PrintWriter(bw);
			//PrintWriter MyFile_Screen2 = new PrintWriter(bw);
			
			try
			{
				
			    MyFile_Screen.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		        MyFile_Screen.println("<html>");
		        MyFile_Screen.println("<title>EVIDENCIA DE PRUEBA AUTOMATIZADA</title>");
		        MyFile_Screen.println("<body><CENTER>");
		        //*****************************************************************************************
		        //* Script para agrandar y normalizar las imagenes con las lupas de + , -                 *
		        //*****************************************************************************************
			    
		        MyFile_Screen.println("<!-- CSS goes in the document HEAD or added to your external stylesheet -->");
	            MyFile_Screen.println("<style type=\"text/css\">");
	            MyFile_Screen.println("table.formatoTexto {");
	            MyFile_Screen.println(" font-family: verdana,arial,sans-serif;");
	            MyFile_Screen.println(" font-size:11px;");
	            MyFile_Screen.println(" color:#333333;");
	            MyFile_Screen.println(" border-width: 1px;");
	            MyFile_Screen.println(" border-color: #a9c6c9;");
	            MyFile_Screen.println(" border-collapse: collapse;");
	            MyFile_Screen.println("}");
	            MyFile_Screen.println("table.altrowstable {");
	            MyFile_Screen.println(" font-family: verdana,arial,sans-serif;");
	            MyFile_Screen.println(" font-size:11px;");
	            MyFile_Screen.println(" color:#333333;");
	            MyFile_Screen.println(" border-width: 1px;");
	            MyFile_Screen.println(" border-color: #a9c6c9;");
	            MyFile_Screen.println(" border-collapse: collapse;");
	            MyFile_Screen.println("}");
	            MyFile_Screen.println("table.altrowstable th {");
	            MyFile_Screen.println(" border-width: 1px;");
	            MyFile_Screen.println(" padding: 8px;");
	            MyFile_Screen.println(" border-style: solid;");
	            MyFile_Screen.println(" border-color: #a9c6c9;");
	            MyFile_Screen.println("}");
	            MyFile_Screen.println("table.altrowstable td {");
	            MyFile_Screen.println(" border-width: 1px;");
	            MyFile_Screen.println(" padding: 8px;");
	            MyFile_Screen.println(" border-style: solid;");
	            MyFile_Screen.println(" border-color: #a9c6c9;");
	            MyFile_Screen.println("}");
	            MyFile_Screen.println(".oddrowcolor{");
	            MyFile_Screen.println(" background-color:#d4e3e5;");
	            MyFile_Screen.println("}");
	            MyFile_Screen.println(".evenrowcolor{");
	            MyFile_Screen.println(" background-color:#c3dde0;");
	            MyFile_Screen.println("}");
	            MyFile_Screen.println("</style>");
	            MyFile_Screen.println(" <script language=\"JavaScript1.2\">");
	            MyFile_Screen.println("    var zoomfactor=0.5");
	            MyFile_Screen.println("    function zoomhelper(){");
	            MyFile_Screen.println("    whatcache.style.width=1300");
	            MyFile_Screen.println("whatcache.style.height=1100");
	            MyFile_Screen.println("    }");
	            MyFile_Screen.println("    function zoom(originalW, originalH, what, state)");
	            MyFile_Screen.println("   {");
	            MyFile_Screen.println("    if (!document.all&&document.getElementById)");
	            MyFile_Screen.println("    return");
	            MyFile_Screen.println("    whatcache=eval(\"document.images.\"+what)");
	            MyFile_Screen.println("    prefix=(state==\"in\")? 1 : -1");
	            MyFile_Screen.println("    if (whatcache.style.width==\"\"||state==\"restore\")");
	            MyFile_Screen.println("    {");
	            MyFile_Screen.println("    whatcache.style.width=originalW + 111");
	            MyFile_Screen.println("    whatcache.style.height=originalH + 100");
	            MyFile_Screen.println("    if (state==\"restore\")");
	            MyFile_Screen.println("    return");
	            MyFile_Screen.println("    }");
	            MyFile_Screen.println("    else");
	            MyFile_Screen.println("    {");
	            MyFile_Screen.println("    zoomhelper()");
	            MyFile_Screen.println("    }");
	            MyFile_Screen.println("    beginzoom=setInterval(\"zoomhelper()\",10)");
	            MyFile_Screen.println("    }");
	            MyFile_Screen.println("    function clearzoom(){");
	            MyFile_Screen.println("    if (window.beginzoom)");
	            MyFile_Screen.println("    clearInterval(beginzoom)");
	            MyFile_Screen.println("    }");

	            MyFile_Screen.println("</script>");
	            MyFile_Screen.println("<script type=\"text/javascript\">");
	            MyFile_Screen.println("function PrintThisDiv(id) {");
	            MyFile_Screen.println("var HTMLContent = document.getElementById(id);");
	            MyFile_Screen.println("var Popup = window.open('about:blank', id, 'width=500,height=500');");
	            MyFile_Screen.println("Popup.document.writeln('<html><head>');");
	            MyFile_Screen.println("Popup.document.writeln('<style type=\"text/css\">');");
	            MyFile_Screen.println("Popup.document.writeln('body{font-family: verdana,arial,sans-serif;font-size:11;color:#333333;border-width: 1px;border-color: #a9c6c9;border-collapse: collapse;}');");
	            MyFile_Screen.println("Popup.document.writeln('</style>');");
	            MyFile_Screen.println("Popup.document.writeln('</head><body>');");
	            MyFile_Screen.println("Popup.document.writeln('<a href=\"javascript:;\" onclick=\"window.print();\">Imprimir</a>');");
	            MyFile_Screen.println("Popup.document.writeln(HTMLContent.innerHTML);");
	            MyFile_Screen.println("Popup.document.writeln('</body></html>');");
	            MyFile_Screen.println("Popup.document.close();");
	            MyFile_Screen.println("Popup.focus();");
	            MyFile_Screen.println("}");
	            MyFile_Screen.println("</script>");

	            //'*****************************************************************************************
	            //'* Script Para Agrandar,ocultar,mostrar y dejarlas en estado normal todas las imagenes   *
	            //'*****************************************************************************************

	            //'MyFile_Screen.println("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\"> ");
	            MyFile_Screen.println("      <style> ");
	            MyFile_Screen.println("        .imagenNO {display:none;} ");
	            MyFile_Screen.println("        .imagen {display:table-cell;} ");
	            MyFile_Screen.println("      </style> ");
	            MyFile_Screen.println("      <script> ");
	            MyFile_Screen.println("      function mostrarImagenes(recolocar)  ");
	            MyFile_Screen.println("      { ");
	            MyFile_Screen.println("        for (x=1;x<document.images.length;x++) ");
	            MyFile_Screen.println("            { ");
	            MyFile_Screen.println("                document.images[x].className = \"imagen\"; ");
	            MyFile_Screen.println("            } ");
	            MyFile_Screen.println("        } ");
	            MyFile_Screen.println("      function ocultarImagenes(recolocar) ");
	            MyFile_Screen.println("       { ");
	            MyFile_Screen.println("        for (x=1;x<document.images.length;x++) ");
	            MyFile_Screen.println("            { ");
	            MyFile_Screen.println("                  document.images[x].className = \"imagenNO\"; ");
	            MyFile_Screen.println("            } ");
	            MyFile_Screen.println("        } ");
	            MyFile_Screen.println("        function MostrarTImagenes(recolocar) ");
	            MyFile_Screen.println("       { ");
	            MyFile_Screen.println("        for (x=3;x<document.images.length;x=x+3) ");
	            MyFile_Screen.println("            { ");
	            MyFile_Screen.println("                  document.images[x].height=\"700\" ");
	            MyFile_Screen.println("                  document.images[x].width=\"1000\" ");
	            MyFile_Screen.println("            } ");
	            MyFile_Screen.println("        } ");
	            MyFile_Screen.println("        function ImagenesP(recolocar) ");
	            MyFile_Screen.println("       { ");
	            MyFile_Screen.println("        for (x=3;x<document.images.length;x=x+3) ");
	            MyFile_Screen.println("            { ");
	            MyFile_Screen.println("                  document.images[x].height=\"211\" ");
	            MyFile_Screen.println("                  document.images[x].width=\"250\" ");
	            MyFile_Screen.println("            } ");
	            MyFile_Screen.println("        } ");
	            MyFile_Screen.println("      </script> ");
		        
	          
		        //'*****************************************
		        //'*         Tabla de encabezado           *
		        //'*****************************************
		        MyFile_Screen.println("<TABLE border='1' class='altrowstable' cellpadding='5' cellspacing='0'>");
		        MyFile_Screen.println("<TR ALIGN=CENTER><TD COLSPAN=2><a href='http://www.choucairtesting.com/' title='Visita Choucair Software Testing'><IMG SRC='logo.jpg'></a></TD></TR>");
		        MyFile_Screen.println("<TR><TD><b>Escenario:</b> </TD><TD>" + Mdl_Variables.P_Str_Tabla + "</TD></TR>");
		        MyFile_Screen.println("<TR><TD><b>Nombre de script:</b> </TD><TD>" + Mdl_Variables.P_Str_Tabla + "</TD></TR>");
		        MyFile_Screen.println("<TR><TD><b>Versión:</b></TD><TD>" + Mdl_Variables.Vrb_VersionAplicativo + "</TD></TR>");
		        MyFile_Screen.println("<TR><TD><b>Corrida:</b></TD><TD>" + Mdl_Variables.P_Str_Ciclo + "</TD></TR>");
		        MyFile_Screen.println("<TR><TD><b>Automatizador:</b> </TD><TD>" + strAutomatizador + "</TD></TR>");
		        MyFile_Screen.println("<TR><TD><b>Fecha y hora de la ejecucion: </b> </TD><TD> " + strFecha +  " </TD></TR>");
		        MyFile_Screen.println("<TR><TD><b>Executable o Link de la aplicación: </b> </TD><TD> " + Mdl_Variables.Tipo_Aplicativo + " </TD></TR>");
		        MyFile_Screen.println("<TR><TD><b>Arquitecto de Automatización:</b> </TD><TD>" + strResponsable + "</TD></TR>");
		        
		        strVrbDtTotalCasos = "SELECT COUNT(DISTINCT INDICE) AS TOTAL_CASOS FROM TBL_LOG_DETALLE DE INNER JOIN  TBL_LOG_MAESTRO MA ON MA.ID_MAESTRO = DE.ID_MAESTRO WHERE  MA.NOMBRE_TABLA like '" + Mdl_Variables.P_Str_Tabla + "' AND DE.CICLO like '" + Mdl_Variables.P_Str_Ciclo + "' AND DE.VERSION ='" + Mdl_Variables.Vrb_VersionAplicativo + "' ";
		        Statement stmtstrVrbDtTotalCasos = Mdl_Variables.Cnn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		        Mdl_Variables.RST_EVIDENCIA = stmtstrVrbDtTotalCasos.executeQuery(strVrbDtTotalCasos);
		        intVrbTotalCasos = 0;
		        if (Mdl_Variables.RST_EVIDENCIA.next())
		        {
		        	intVrbTotalCasos = Integer.parseInt(Mdl_Variables.RST_EVIDENCIA.getString("TOTAL_CASOS"));
		        }
		        
		        Mdl_Variables.RST_EVIDENCIA.close();
		        
		        MyFile_Screen.println("<TR ALIGN=center><TD COLSPAN=2><b><a name='MENU'>RESUMEN EJECUCIÓN</a></b></TD></TR>");
		        MyFile_Screen.println("<TR><TD><b>TOTAL CASOS EJECUTADOS:</b> </TD><TD>" + intVrbTotalCasos + "</TD></TR>");
		        
		        strVrbDtConteoAciertos = "SELECT  DISTINCT(INDICE) FROM TBL_LOG_DETALLE WHERE ID_MAESTRO IN (SELECT ID_MAESTRO FROM TBL_LOG_MAESTRO WHERE NOMBRE_TABLA like '" + Mdl_Variables.P_Str_Tabla + "' AND CICLO like '" + Mdl_Variables.P_Str_Ciclo + "' AND VERSION = '" + Mdl_Variables.Vrb_VersionAplicativo + "'AND INDICE NOT IN (SELECT INDICE FROM TBL_LOG_DETALLE DET WHERE ID_MAESTRO IN (SELECT ID_MAESTRO FROM TBL_LOG_MAESTRO WHERE NOMBRE_TABLA like '" + Mdl_Variables.P_Str_Tabla + "' )AND DET.TIPO_LOG IN(2,4,6)AND DET.CICLO like '" + Mdl_Variables.P_Str_Ciclo + "'AND DET.VERSION = '" + Mdl_Variables.Vrb_VersionAplicativo + "')) ORDER BY INDICE";
		        Statement stmtstrVrbDtConteoAciertos= Mdl_Variables.Cnn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		        Mdl_Variables.RST_EVIDENCIA = stmtstrVrbDtConteoAciertos.executeQuery(strVrbDtConteoAciertos);
		        Mdl_Variables.RST_EVIDENCIA.last(); 
		        intVrbConteoAciertos = Mdl_Variables.RST_EVIDENCIA.getRow();
		        Mdl_Variables.RST_EVIDENCIA.beforeFirst();
		        MyFile_Screen.println("<TR><TD><b>TOTAL <a href='#CASOS ACIERTOS'> CASOS ACIERTOS</a></TD><TD>" + intVrbConteoAciertos + "</b></TD></TR>");
		        MyFile_Screen.println("<TR><TD><b>DETALLE</b></TD><TD>");
		        
		        int m = 1;
		        intPosicion = 0;
		        
		        while (Mdl_Variables.RST_EVIDENCIA.next())
		        {
		        	if (intPosicion == intVrbConteoAciertos - 1)
		        	{
		        		MyFile_Screen.println("<a href='#CASOS '" + Mdl_Variables.RST_EVIDENCIA.getString("INDICE") + "'>" + Mdl_Variables.RST_EVIDENCIA.getString("INDICE") + "</a>");
		        	}    
		            else
		            {
		            	MyFile_Screen.println("<a href='#CASOS '" + Mdl_Variables.RST_EVIDENCIA.getString("INDICE") + "'>" + Mdl_Variables.RST_EVIDENCIA.getString("INDICE") + "</a> ,");
		                intPosicion = intPosicion + 1;
		            }
		                   
		            if (m % 20 == 0) 
		            {
		            	MyFile_Screen.println("<br>");
		            	m = m + 1;  
		            }
		   
		                            
		        }
		                
		        MyFile_Screen.println("</TD></TR>");
	            Mdl_Variables.RST_EVIDENCIA.close();
	            
	            strVrbDtConteoErrores = "SELECT DISTINCT INDICE FROM TBL_LOG_DETALLE  WHERE ID_MAESTRO IN (SELECT ID_MAESTRO FROM TBL_LOG_MAESTRO WHERE NOMBRE_TABLA like '" + Mdl_Variables.P_Str_Tabla + "')   AND TIPO_LOG IN(2,4,6) AND VERSION ='" + Mdl_Variables.Vrb_VersionAplicativo + "' AND CICLO like '" + Mdl_Variables.P_Str_Ciclo + "'ORDER BY INDICE";
	            
	            Statement stmtstrVrbDtConteoErrores = Mdl_Variables.Cnn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        	Mdl_Variables.RST_EVIDENCIA = stmtstrVrbDtConteoErrores.executeQuery(strVrbDtConteoErrores);
	        	Mdl_Variables.RST_EVIDENCIA.last();       
	        	intVrbConteoErrores = Mdl_Variables.RST_EVIDENCIA.getRow();
	        	Mdl_Variables.RST_EVIDENCIA.beforeFirst();
	        	MyFile_Screen.println("<TR><TD><b>TOTAL <a href='#CASOS ERRADOS'> CASOS ERRADOS</a></TD><TD>" + intVrbConteoErrores + "</b></TD></TR>");
	        	MyFile_Screen.println("<TR><TD><b>DETALLE</b></TD><TD>");
	        	        
	        	intPosicion = 0;
	        	m = 1;
	        	        
		        while (Mdl_Variables.RST_EVIDENCIA.next())
		        {
		        	if (intPosicion == intVrbConteoAciertos - 1)
		        	{
		        		MyFile_Screen.println("<a href='#CASOS " + Mdl_Variables.RST_EVIDENCIA.getString("INDICE") + "'>" + Mdl_Variables.RST_EVIDENCIA.getString("INDICE") + "</a>");
		        	}   
		            else
		            {
		            	MyFile_Screen.println("<a href='#CASOS " + Mdl_Variables.RST_EVIDENCIA.getString("INDICE") + "'>" + Mdl_Variables.RST_EVIDENCIA.getString("INDICE") + "</a> ,");
		                intPosicion = intPosicion + 1;
		            }
		                   
		        	if (m % 20 == 0) 
		            {
		            	MyFile_Screen.println("<br>");
		            	m = m + 1;  
		            }              
		        }
		        
		        Mdl_Variables.RST_EVIDENCIA.close();
		        
		        strVrbTotalTiempos = "SELECT DISTINCT INDICE FROM TBL_LOG_DETALLE  WHERE ID_MAESTRO IN (SELECT ID_MAESTRO FROM TBL_LOG_MAESTRO WHERE NOMBRE_TABLA like '" + Mdl_Variables.P_Str_Tabla + "') AND VERSION ='" + Mdl_Variables.Vrb_VersionAplicativo + "'  AND CICLO like '" + Mdl_Variables.P_Str_Ciclo + "' ORDER BY INDICE";
		        Statement stmtstrVrbTotalTiempos = Mdl_Variables.Cnn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		        Mdl_Variables.RST_LOG_DETALLE_INDICES = stmtstrVrbTotalTiempos.executeQuery(strVrbTotalTiempos);
		        
		        while (Mdl_Variables.RST_LOG_DETALLE_INDICES.next())
		        {
		        	intIndice = Integer.parseInt(Mdl_Variables.RST_LOG_DETALLE_INDICES.getString("INDICE"));
		        	
		        	strVrbTotalTiempos = "SELECT MAX(TIEMPO_EJECUCION_CASO) AS TIEMPO_CASO FROM TBL_LOG_DETALLE  WHERE ID_MAESTRO IN (SELECT ID_MAESTRO FROM TBL_LOG_MAESTRO WHERE NOMBRE_TABLA like '" + Mdl_Variables.P_Str_Tabla + "') AND VERSION ='" + Mdl_Variables.Vrb_VersionAplicativo + "' AND CICLO like '" + Mdl_Variables.P_Str_Ciclo + "' AND INDICE = " + intIndice + "";
		            
		        	//Statement stmtstrVrbTotalTiempos1 = Mdl_Variables.Cnn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		        	//Mdl_Variables.RST_EVIDENCIA = stmtstrVrbTotalTiempos1.executeQuery(strVrbTotalTiempos);
		        	Mdl_Variables.RST_EVIDENCIA = Mdl_Variables.Cnn.prepareCall(strVrbTotalTiempos).executeQuery();
		        	if (Mdl_Variables.RST_EVIDENCIA.next())
			        {
		        		intTiempoTotalEscenario = intTiempoTotalEscenario + Integer.parseInt(Mdl_Variables.RST_EVIDENCIA.getString("TIEMPO_CASO"));
			        }
		            Mdl_Variables.RST_EVIDENCIA.close();
		                   
		        }
	        	        
		        Mdl_Variables.RST_LOG_DETALLE_INDICES.close();
		        
		        if (intTiempoTotalEscenario == 0)
		        {
		        	MyFile_Screen.println("<TR><TD><b>TOTAL TIEMPO EJECUCIÓN:</b></TD><TD> 0h 0m 0s </TD></TR>");
		        }    
		        else
		        {
		        	MyFile_Screen.println("<TR><TD><b>TOTAL TIEMPO EJECUCIÓN:</b></TD><TD>" + String.valueOf((intTiempoTotalEscenario / 3600)) + "h" + String.valueOf((intTiempoTotalEscenario % 3600) / 60) + "m" + String.valueOf((intTiempoTotalEscenario % 3600) % 60) + "s" + "</TD></TR>");
		        }
		                
		        if (Mdl_Variables.P_Str_Modulo.toUpperCase() != "MIGRACION") 
		        {
		        	MyFile_Screen.println("<TR ALIGN=CENTER><TD COLSPAN=2><b>");
		            MyFile_Screen.println("<TABLE border='1'>");
		            MyFile_Screen.println("<TD><a href='#' onClick=\"mostrarImagenes(true);\">Mostrar Imagenes</a><TD> ");
		            MyFile_Screen.println("<TD><a href='#' onClick=\"ocultarImagenes(true);\">Ocultar Imagenes</a><TD> ");
		            MyFile_Screen.println("<TD><a href='#' onClick=\"MostrarTImagenes(true);\">Agrandar Imagenes</a><TD> ");
		            MyFile_Screen.println("<TD><a href='#' onClick=\"ImagenesP(true);\">Imagenes Normales</a><TD> ");
		            MyFile_Screen.println("</TABLE></b></TD></TR>");
		            MyFile_Screen.println("<br><br>");
		        }
		        
		        
		        //'****************************************************************
		        //'*      Script para ingresar los mensajes de Acierto            *
		        //'****************************************************************
		        strVrbDtConteoAciertos = "SELECT  DISTINCT(INDICE) FROM TBL_LOG_DETALLE WHERE ID_MAESTRO IN (SELECT ID_MAESTRO FROM TBL_LOG_MAESTRO WHERE NOMBRE_TABLA like '" + Mdl_Variables.P_Str_Tabla + "' AND CICLO like '" + Mdl_Variables.P_Str_Ciclo + "' AND VERSION = '" + Mdl_Variables.Vrb_VersionAplicativo + "'AND INDICE NOT IN (SELECT INDICE FROM TBL_LOG_DETALLE DET WHERE ID_MAESTRO IN (SELECT ID_MAESTRO FROM TBL_LOG_MAESTRO WHERE NOMBRE_TABLA like '" + Mdl_Variables.P_Str_Tabla + "' )AND DET.TIPO_LOG IN(2,4,6)AND DET.CICLO like '" + Mdl_Variables.P_Str_Ciclo + "'AND DET.VERSION = '" + Mdl_Variables.Vrb_VersionAplicativo + "')) ORDER BY INDICE ";
		        Statement stmtstrVrbDtConteoAciertos2 = Mdl_Variables.Cnn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        	Mdl_Variables.RST_LOG_DETALLE_INDICES = stmtstrVrbDtConteoAciertos2.executeQuery(strVrbDtConteoAciertos);
		        	        
		        MyFile_Screen.println("<TABLE  WIDTH='100%'><TR WIDTH='100%' > <TD WIDTH='100%' VALIGN='CENTER' ALIGN='CENTER' BGCOLOR='#00FF00'><H3><FONT FACE='verdana' ><a name='CASOS ACIERTOS'>CASOS ACIERTOS</a></FONT></H3></TD></TR></TABLE><BR>");
		        
		        while (Mdl_Variables.RST_LOG_DETALLE_INDICES.next())
		        {
		        	intIndice = Integer.parseInt(Mdl_Variables.RST_LOG_DETALLE_INDICES.getString("INDICE"));
		        	strVrbDtConteoAciertos = "SELECT * FROM TBL_LOG_DETALLE WHERE INDICE = " + intIndice + " AND ID_MAESTRO IN (SELECT ID_MAESTRO FROM TBL_LOG_MAESTRO WHERE NOMBRE_TABLA like '" + Mdl_Variables.P_Str_Tabla + "' AND CICLO like '" + Mdl_Variables.P_Str_Ciclo + "' AND VERSION = '" + Mdl_Variables.Vrb_VersionAplicativo + "'AND INDICE NOT IN (SELECT INDICE FROM TBL_LOG_DETALLE DET WHERE ID_MAESTRO IN (SELECT ID_MAESTRO FROM TBL_LOG_MAESTRO WHERE NOMBRE_TABLA like '" + Mdl_Variables.P_Str_Tabla + "' )AND DET.TIPO_LOG IN(2,4,6)AND DET.CICLO like '" + Mdl_Variables.P_Str_Ciclo + "'AND DET.VERSION = '" + Mdl_Variables.Vrb_VersionAplicativo + "')) ORDER BY fecha_exec ";
		        	Statement stmtstrVrbDtConteoAciertos3 = Mdl_Variables.Cnn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		        	Mdl_Variables.RST_EVIDENCIA = stmtstrVrbDtConteoAciertos3.executeQuery(strVrbDtConteoAciertos);
		        	if (Mdl_Variables.RST_EVIDENCIA.next())
		        	{
		        		MyFile_Screen.println("<div id=" + Mdl_Variables.RST_EVIDENCIA.getString("INDICE") + " align='left'>");
			            MyFile_Screen.println("<TABLE WIDTH='100%'><TR WIDTH='100%' > <TD WIDTH='100%' VALIGN='CENTER' ALIGN='CENTER' BGCOLOR='#1EC10C'><H3><FONT FACE='verdana' ><a name='CASOS " + intIndice + "'>CASO " + intIndice + " - OK</a></FONT></H3></TD></TR></TABLE><BR>");

			            
			            if (Integer.parseInt(Mdl_Variables.RST_EVIDENCIA.getString("TIPO_LOG")) == 1)
			            {
			            	MyFile_Screen.println("<TABLE class='formatoTexto' border='1' cellpadding='5' cellspacing='0'>");
			            }
			            else
			            {
			            	MyFile_Screen.println("<TABLE class='formatoTexto' border='2' style='border-color: red' cellpadding='5' cellspacing='0'>");
			            }
			                
			            MyFile_Screen.println("<td>");
			            MyFile_Screen.println(Mdl_Variables.RST_EVIDENCIA.getString("INDICE"));
			            MyFile_Screen.println("</td>");
			            MyFile_Screen.println("<td width='30' VALIGN='TOP' >");
		        	}
		        	//******************************wmn********Devolvemos
		        	Mdl_Variables.RST_EVIDENCIA.previous();

		            while (Mdl_Variables.RST_EVIDENCIA.next())
		            {
		            	intCasoActual = Integer.parseInt((Mdl_Variables.RST_EVIDENCIA.getString("INDICE")));
		              
		                MyFile_Screen.println("<TABLE border='0'  class='formatoTexto' cellpadding='5' cellspacing='0' valign='top'>");
		                if (Mdl_Variables.RST_EVIDENCIA.getString("MENSAJE_LOG") != "EVIDENCIA DE PRUEBA: PRINT DE LOS DATOS INGRESADOS")
		                {
		                	MyFile_Screen.println("<TR><TD><b>" + Mdl_Variables.RST_EVIDENCIA.getString("MENSAJE_LOG").replaceAll("<", "<>") + "</b>");
		                }
		                else
		                {
		                	MyFile_Screen.println("<TR><TD>" + Mdl_Variables.RST_EVIDENCIA.getString("MENSAJE_LOG").replaceAll("<", "<>"));
		                }
		                    
		                MyFile_Screen.println("<p align='center'>");
		                MyFile_Screen.println("<tr>");
		                MyFile_Screen.println("<a href=\"#\" onmouseover=\"zoom(201,300,'a" + intContador + "','in')\" onmouseout=\"clearzoom()\" style=\"color: #000000; text-decoration: none\">");
		                MyFile_Screen.println("<img src=\"Grande.bmp\" border=\"0\" width=\"40\" height=\"40\"></a>");

		                MyFile_Screen.println("<a href=\"#\" onmouseover=\"zoom(139,100,'a" + intContador + "','restore')\" style=\"color: #000000; text-decoration: none\">");
		                MyFile_Screen.println("<img src=\"Pequeño.bmp\" border=\"0\" width=\"30\" height=\"30\"></a>");
		                MyFile_Screen.println("<TR>");
		                MyFile_Screen.println("<TD> <img name='a" + intContador + "' src='" + Mdl_Variables.RST_EVIDENCIA.getString("IMAGEN") + "' align='left' border='1' hspace='0' vspace='0' width='250' height='200'></p>");
		                MyFile_Screen.println("</table>");
		 
		                intContador = intContador + 1;
		            }
		                
		            MyFile_Screen.println("<td valign='top'>");
		            MyFile_Screen.println("<TABLE WIDTH='1000' border='0' cellspacing='0' cellpadding='4' class='formatoTexto'>");
		            MyFile_Screen.println("<TR>");
		            MyFile_Screen.println("<TD><b><div align=center>INFORMACIÓN CASO DE PRUEBA</b></div>");
		            
		            Statement stmtstrVrbDtConteoAciertos4 = Mdl_Variables.Cnn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		        	Mdl_Variables.RST_DESCRIPCION_EVIDENCIA = stmtstrVrbDtConteoAciertos4.executeQuery("SELECT *, CASE WHEN ACIERTO_ERROR = '-1' THEN 'ACIERTO' ELSE 'ERROR' END AS ORIENTACION_CASO FROM " + Mdl_Variables.P_Str_Tabla + "_D WHERE ID_CASO = '" + intCasoActual + "'");
		        	if (Mdl_Variables.RST_DESCRIPCION_EVIDENCIA.next())
		 	        {
		    
			            MyFile_Screen.println("<TR><TD><b> DESCRIPTION: </b>" + Mdl_Variables.RST_DESCRIPCION_EVIDENCIA.getString("DESCRIPTION"));
			            MyFile_Screen.println("<TR><TD><b> ORIENTACION_CASO: </b>" + Mdl_Variables.RST_DESCRIPCION_EVIDENCIA.getString("ORIENTACION_CASO"));
			            if (Mdl_Variables.RST_DESCRIPCION_EVIDENCIA.getString("EJECUCION_AC_ERR") == "0")
			            {
			            	/*juliet*/
			            	//MyFile_Screen.println("<TR><TD><b>DESCRIPCION_ACIERTO: </b>" + Mdl_Variables.RST_DESCRIPCION_EVIDENCIA.getString("DESCRIPCION_ACIERTO") + "");
			            	MyFile_Screen.println("<TR><TD><b>DESCRIPCION_ERROR: </b>" + Mdl_Variables.RST_DESCRIPCION_EVIDENCIA.getString("DESCRIPCION_ERROR") + "");
			            }  
			            else
			            {
			            	/*juliet*/
			            	//MyFile_Screen.println("<TR><TD><b>DESCRIPCION_ERROR: </b>" + Mdl_Variables.RST_DESCRIPCION_EVIDENCIA.getString("DESCRIPCION_ERROR") + "");
			            	MyFile_Screen.println("<TR><TD><b>DESCRIPCION_ACIERTO: </b>" + Mdl_Variables.RST_DESCRIPCION_EVIDENCIA.getString("DESCRIPCION_ACIERTO") + "");
			            }
			                
			                
			          
			            MyFile_Screen.println("<TR><TD><b>PASOS: </b>");
			            MyFile_Screen.println("<TR>");
			            
			            strPasos = Mdl_Variables.RST_DESCRIPCION_EVIDENCIA.getString("DESIGN_STEPS");
		                strPasos = EVIDENCIA_PASOS_EVD2(strPasos);
		                MyFile_Screen.println(strPasos);
			            MyFile_Screen.println("<TR><TD><b>HORA: </b>" + Mdl_Variables.RST_DESCRIPCION_EVIDENCIA.getString("HORA") + "");
			            int intTiempoExecCaso;
			            if ((Mdl_Variables.RST_DESCRIPCION_EVIDENCIA.getString("TIEMPO_EJECUCION_CASO")) != null)
			            {
			            	 intTiempoExecCaso = Integer.parseInt(Mdl_Variables.RST_DESCRIPCION_EVIDENCIA.getString("TIEMPO_EJECUCION_CASO"));
			            }
			            else
			            {
			            	 intTiempoExecCaso = 0;
			            }
			           
			            MyFile_Screen.println("<TR><TD><b>TIEMPO_EJECUCION_CASO: </b>" + String.valueOf(intTiempoExecCaso / 3600) + "h" + String.valueOf((intTiempoExecCaso % 3600) / 60)  + "m" + String.valueOf((intTiempoExecCaso % 3600) % 60) + "s");
			            MyFile_Screen.println("<TR><TD><b><a href='#MENU'>REGRESAR RESUMEN EJECUCIÓN</a>");
		
			            MyFile_Screen.println("<TR><TD><b><p><a href='javascript:;' onclick='PrintThisDiv('" + intCasoActual + "')' id='imprime'>IMPRIMIR</a></p>");
		 	        }
		            Mdl_Variables.RST_DESCRIPCION_EVIDENCIA.close();
		            MyFile_Screen.println("</TABLE>");
		            MyFile_Screen.println("</td>");
		            MyFile_Screen.println("</tr>");
		            MyFile_Screen.println("</table>");
		            MyFile_Screen.println("</div>");
		            Mdl_Variables.RST_EVIDENCIA.close();
		           
		        }

		        //'****************************************************************
		        //'*      Script para ingresar los mensajes de Error              *
		        //'****************************************************************

		        strVrbDtConteoErrores = "SELECT  DISTINCT(INDICE) FROM TBL_LOG_DETALLE WHERE ID_MAESTRO IN (SELECT ID_MAESTRO FROM TBL_LOG_MAESTRO WHERE NOMBRE_TABLA like '" + Mdl_Variables.P_Str_Tabla + "' AND CICLO like '" + Mdl_Variables.P_Str_Ciclo + "' AND VERSION = '" + Mdl_Variables.Vrb_VersionAplicativo + "'AND INDICE IN (SELECT INDICE FROM TBL_LOG_DETALLE DET WHERE ID_MAESTRO IN (SELECT ID_MAESTRO FROM TBL_LOG_MAESTRO WHERE NOMBRE_TABLA like '" + Mdl_Variables.P_Str_Tabla + "' )AND DET.TIPO_LOG IN(2,4,6)AND DET.CICLO like '" + Mdl_Variables.P_Str_Ciclo + "'AND DET.VERSION = '" + Mdl_Variables.Vrb_VersionAplicativo + "')) ORDER BY INDICE ";
		        Statement stmtstrVrbDtConteoErrores2 = Mdl_Variables.Cnn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		        Mdl_Variables.RST_LOG_DETALLE_INDICES = stmtstrVrbDtConteoErrores2.executeQuery(strVrbDtConteoErrores);
	        	
		     
		        MyFile_Screen.println("<TABLE  WIDTH='100%'><TR WIDTH='100%' > <TD WIDTH='100%' VALIGN='CENTER' ALIGN='CENTER' BGCOLOR=red><H3><FONT FACE='verdana' ><a name='CASOS ERRADOS'>CASOS ERRADOS</a></FONT></H3></TD></TR></TABLE><BR>");
		        while (Mdl_Variables.RST_LOG_DETALLE_INDICES.next())
		        {
		        	 intIndice = Integer.parseInt(Mdl_Variables.RST_LOG_DETALLE_INDICES.getString("INDICE"));
		        	 strVrbDtConteoErrores = "SELECT * FROM TBL_LOG_DETALLE WHERE INDICE = " + intIndice + " AND ID_MAESTRO IN (SELECT ID_MAESTRO FROM TBL_LOG_MAESTRO WHERE NOMBRE_TABLA like '" + Mdl_Variables.P_Str_Tabla + "' AND CICLO like '" + Mdl_Variables.P_Str_Ciclo + "' AND VERSION = '" + Mdl_Variables.Vrb_VersionAplicativo + "'AND INDICE IN (SELECT INDICE FROM TBL_LOG_DETALLE DET WHERE ID_MAESTRO IN (SELECT ID_MAESTRO FROM TBL_LOG_MAESTRO WHERE NOMBRE_TABLA like '" + Mdl_Variables.P_Str_Tabla + "' )AND DET.TIPO_LOG IN(2,4,6)AND DET.CICLO like '" + Mdl_Variables.P_Str_Ciclo + "'AND DET.VERSION = '" + Mdl_Variables.Vrb_VersionAplicativo + "')) ORDER BY fecha_exec ";
			         
		        	 Statement stmtstrVrbDtConteoErrores3 = Mdl_Variables.Cnn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		 	         Mdl_Variables.RST_EVIDENCIA = stmtstrVrbDtConteoErrores3.executeQuery(strVrbDtConteoErrores);
		 	        
					 if (Mdl_Variables.RST_EVIDENCIA.next())
					 {
						 MyFile_Screen.println("<div id=" + Mdl_Variables.RST_EVIDENCIA.getString("INDICE") + " align='left'>");
						 MyFile_Screen.println("<TABLE WIDTH='100%'><TR WIDTH='100%' > <TD WIDTH='100%' VALIGN='CENTER' ALIGN='CENTER' BGCOLOR='#C20C09'><H3><FONT FACE='verdana' ><a name='CASOS " + intIndice + "'>CASO " + intIndice + " - ERROR</a></FONT></H3></TD></TR></TABLE><BR>");
		
		                
						 MyFile_Screen.println("<TABLE border='2' style='border-color: red' class='formatoTexto' cellpadding='5' cellspacing='0'>");
		               
						 MyFile_Screen.println("<td>");
						 MyFile_Screen.println(Mdl_Variables.RST_EVIDENCIA.getString("INDICE"));
						 MyFile_Screen.println("</td>");
						 MyFile_Screen.println("<td width='30' >");
					 }
					 
					 Mdl_Variables.RST_EVIDENCIA.previous();
					 while (Mdl_Variables.RST_EVIDENCIA.next())
					 {
						intCasoActual = Integer.parseInt(Mdl_Variables.RST_EVIDENCIA.getString("INDICE"));
						MyFile_Screen.println("<TABLE border='0' class='formatoTexto'  cellpadding='5' cellspacing='0'>");
	                    if ((Mdl_Variables.RST_EVIDENCIA.getString("MENSAJE_LOG") != "EVIDENCIA DE PRUEBA: PRINT DE LOS DATOS INGRESADOS") && (Mdl_Variables.RST_EVIDENCIA.getString("MENSAJE_LOG") != "EVIDENCIA DE PRUEBA:") && (Mdl_Variables.RST_EVIDENCIA.getString("MENSAJE_LOG") != "") && (Mdl_Variables.RST_EVIDENCIA.getString("MENSAJE_LOG")) != "EVIDENCIA DE PRUEBA: (0) -" && Mdl_Variables.RST_EVIDENCIA.getString("MENSAJE_LOG").toUpperCase().contains("Failed to find the attach name:") == true && Mdl_Variables.RST_EVIDENCIA.getString("MENSAJE_LOG").toUpperCase().contains("MENSAGEM") == true && Mdl_Variables.RST_EVIDENCIA.getString("MENSAJE_LOG").toUpperCase().contains("Línea evaluada:") == true && Mdl_Variables.RST_EVIDENCIA.getString("MENSAJE_LOG").toUpperCase().contains("EVIDENCIA DE PRUEBA: Evidencia de Prueba") == true)
	                    {
	                    	MyFile_Screen.println("<TR><TD><b>" + Mdl_Variables.RST_EVIDENCIA.getString("MENSAJE_LOG").replace("<", "<>") + "</b>");
	                    } 
	                    else
	                    {
	                        MyFile_Screen.println("<TR><TD><FONT COLOR='RED'>" + Mdl_Variables.RST_EVIDENCIA.getString("MENSAJE_LOG").replace("<", "<>") + "</font>");
	                    }
	                    
	                    MyFile_Screen.println("<p align='center'>");
	                    MyFile_Screen.println("<tr>");
	                    MyFile_Screen.println("<a href='#' onmouseover=\"zoom(201,300,'a" + intContador + "','in')\" onmouseout='clearzoom()' style='color: #000000; text-decoration: none'>");
	                    MyFile_Screen.println("<img src='Grande.bmp' border='0' width='40' height='40'></a>");

	                    MyFile_Screen.println("<a href='#' onmouseover=\"zoom(139,100,'a" + intContador + "','restore')\" style='color: #000000; text-decoration: none'>");
	                    MyFile_Screen.println("<img src='Pequeño.bmp' border='0' width='30' height='30'></a>");
	                    MyFile_Screen.println("<TR>");
	                    MyFile_Screen.println("<TD> <img name='a" + intContador + "' src='" + Mdl_Variables.RST_EVIDENCIA.getString("IMAGEN") + "' align='left' border='1' hspace='0' vspace='0' width='250' height='200'></p>");
	                    MyFile_Screen.println("</table>");
	                    intContador = intContador + 1;

					 }
	                    
	                MyFile_Screen.println("<td valign='top'>");
	                MyFile_Screen.println("<TABLE WIDTH='1000' border='0' cellspacing='0' cellpadding='4' class='formatoTexto'>");
	                MyFile_Screen.println("<TR>");
	                MyFile_Screen.println("<TD><b><div align=center>INFORMACIÓN CASO DE PRUEBA</b></div>");
	                
	                Statement stmtstrVrbDtConteoErrores4 = Mdl_Variables.Cnn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	                //*************WMN*-******* Se modifica querie
		 	        Mdl_Variables.RST_DESCRIPCION_EVIDENCIA = stmtstrVrbDtConteoErrores4.executeQuery("SELECT *, CASE WHEN ACIERTO_ERROR = '-1' THEN 'ACIERTO' ELSE 'ERROR' END AS ORIENTACION_CASO FROM " + Mdl_Variables.P_Str_Tabla + "_D WHERE ID_CASO = '" + intCasoActual + "'");
		 	        if (Mdl_Variables.RST_DESCRIPCION_EVIDENCIA.next())
		 	        {
		 	        	MyFile_Screen.println("<TR><TD><b>DESCRIPTION: </b>" + Mdl_Variables.RST_DESCRIPCION_EVIDENCIA.getString("DESCRIPTION"));
		                MyFile_Screen.println("<TR><TD><b>ORIENTACION_CASO: </b>" + Mdl_Variables.RST_DESCRIPCION_EVIDENCIA.getString("ORIENTACION_CASO"));
		                if (Mdl_Variables.RST_DESCRIPCION_EVIDENCIA.getString("EJECUCION_AC_ERR").toString() == "0") 
		                {
		                    MyFile_Screen.println("<TR><TD><b>DESCRIPCION_ACIERTO: </b>" + Mdl_Variables.RST_DESCRIPCION_EVIDENCIA.getString("DESCRIPCION_ACIERTO"));
		                }
		                else
		                {
		                    MyFile_Screen.println("<TR><TD><b>DESCRIPCION_ERROR: </b>" + Mdl_Variables.RST_DESCRIPCION_EVIDENCIA.getString("DESCRIPCION_ERROR"));
		                }
		                MyFile_Screen.println("<TR><TD><b>PASOS: </b>");
		                MyFile_Screen.println("<TR>");
		                
		                strPasos = Mdl_Variables.RST_DESCRIPCION_EVIDENCIA.getString("DESIGN_STEPS");
		                strPasos = EVIDENCIA_PASOS_EVD2(strPasos);
		                MyFile_Screen.println(strPasos);
		                MyFile_Screen.println("<TR><TD><b>HORA: </b>" + Mdl_Variables.RST_DESCRIPCION_EVIDENCIA.getString("HORA"));
		                int intTiempoExecCaso1;
			            intTiempoExecCaso1 = Integer.parseInt(Mdl_Variables.RST_DESCRIPCION_EVIDENCIA.getString("TIEMPO_EJECUCION_CASO"));
			            MyFile_Screen.println("<TR><TD><b>TIEMPO_EJECUCION_CASO: </b>" + String.valueOf(intTiempoExecCaso1 / 3600) + "h" + String.valueOf((intTiempoExecCaso1 % 3600) / 60)  + "m" + String.valueOf((intTiempoExecCaso1 % 3600) % 60) + "s");
		                MyFile_Screen.println("<TR><TD><b><a href='#MENU'>REGRESAR RESUMEN EJECUCIÓN</a>");
		                MyFile_Screen.println("<TR><TD><b><p><a href='javascript:;' onclick='PrintThisDiv('" + intCasoActual + "')' id='imprime'>IMPRIMIR</a></p>");
		              
		 	        }
		 	        
	                Mdl_Variables.RST_DESCRIPCION_EVIDENCIA.close();

	                MyFile_Screen.println("</TABLE>");
	                MyFile_Screen.println("</td>");
	                MyFile_Screen.println("</tr>");
	                MyFile_Screen.println("</table>");
	                MyFile_Screen.println("</div>");
	                Mdl_Variables.RST_EVIDENCIA.close();
	            
		        }      
			}	
	        catch (Exception e)
	        {
	        	MyFile_Screen.println(e.getMessage());
	        			
	        }
			MyFile_Screen.println("</body></html>");
			MyFile_Screen.close();
		}
	    
		





	//***********************WMN********************************
		public static void Actualizar_RegistroUtilizado()
		{
			try
			{
				String strFecha 				= ""; //strFecha
				
				
				Date datFecha = new Date();
				SimpleDateFormat sdfFecha = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
				//SimpleDateFormat adfFecha = new SimpleDateFormat("dd-mm-YYYY");
				strFecha = sdfFecha.format(datFecha);

				Mdl_Variables.Cnn.prepareCall( "UPDATE " + Mdl_Variables.P_Str_Datos + " SET UTILIZADO = -1, TIEMPO_EJECUCION_CASO = " + Mdl_Choucair.Diferencia_Fechas(new Date(), Mdl_Variables.P_Entrada_Sistema) + ", HORA = '" + strFecha + "' WHERE [ID_CASO] = " + Mdl_Variables.Rst_Tabla.getString("ID_CASO")).execute();
				//Mdl_Variables.contSegCaso=Mdl_Choucair.Diferencia_Fechas(new Date(), Mdl_Variables.P_Entrada_Sistema);
			}catch (Exception ex){
				System.out.println("Error en Mdl_Evidenciamiento: " + ex.getMessage());
			}
			

		}
		//********************************************************************
		//WMN20150401: Se modifica la función para que almacene los datos en la evidencia.
		public static void CADENA_PASOS(String strPasos) throws SQLException
		{
			
			try
			{
				if (!strPasos.contentEquals(""))
				{
					strPasos = strPasos.replace("'", "''");
				}

				if (!Mdl_Variables.strPEvidencia.contentEquals(""))
				{
					Mdl_Variables.strPEvidencia = Mdl_Variables.strPEvidencia + "|>.<|" + strPasos;
				}
				else
				{
					Mdl_Variables.strPEvidencia = strPasos;
				}
				Mdl_Variables.Cnn.prepareCall( "UPDATE " + Mdl_Variables.P_Str_Datos + "  SET DESIGN_STEPS = '"+ Mdl_Variables.strPEvidencia + "' WHERE [ID_CASO] = " + Mdl_Variables.Rst_Tabla.getString("id_caso")).execute();	

			}
			catch (Exception ex)
			{
				System.out.println("Error en Mdl_Evidenciamiento: " + ex.getMessage());
			}
		}
		
		public static String EVIDENCIA_PASOS_EVD2(String strCadenaPasos2) throws Exception
		{
			
			String strPasos  = "";
			String arrValores[];
			int i;
	   
			try
			{
			    if (strCadenaPasos2 == "")
			    {
			    	return "";
			    }
			    else
			    {
			    	
				    //arrValores = strCadenaPasos2.split("|>.<|");
				    String temp = "\\|\\>\\.\\<\\|";
				    
				    arrValores = strCadenaPasos2.split(temp);
				    for (i = 0; i <= arrValores.length - 1; i++)
				    {
				    	if (i == 0) 
				    	{
				    		strPasos = "<TR><TD>" + String.valueOf(i + 1) + ". " + arrValores[0] + "</TD></TR>";
				    	} 
				    	else
				    	{
				    		strPasos = strPasos + "<TR><TD>" + String.valueOf(i + 1) + ". " + arrValores[i] + "</TD></TR>";
				    	}
				    }
			    }
			    
			}
			catch (Exception e) 
			{
				System.out.println(e.getMessage());
				
			}
			return strPasos;
		      
		    
		    
		}
	    
	}
