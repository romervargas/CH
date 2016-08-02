package Pck_Choucair;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.io.File;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.*;


public class Mdl_Datapool {
	public enum BasedeDatos 
	{
		access,	sql,	mysql,	sql2008,
		access2007,	oracle,	oracle10g,	db2,
		as400, novalue;
		public static BasedeDatos getValue(String str)
	    {
	        try {
	            return valueOf(str);
	        }
	        catch (IllegalArgumentException ex) {
	            return novalue;
	        }
	    }
	}
	
	public static void Conectar_Sql(String Ciclo_Funcional , String Version_Aplicativo , Boolean Limpiar_Datos , String Tipo_Ejecucion)
    {
			
		
		//String strCasosEliminarLog = "";
		//String strCasosActualizarLog  = "";
		String strCasosEjecutar;
		
		if (Mdl_Variables.P_Str_CasosExec.equals(""))
		{
			strCasosEjecutar = Mdl_Choucair.obtenerRangos(Mdl_Variables.P_Str_CasosExec).replace(",", "','");//Mdl_Variables.P_Str_CasosExec.replace(",", "','");			
		}
		else
		{
			strCasosEjecutar = "'"+Mdl_Choucair.obtenerRangos(Mdl_Variables.P_Str_CasosExec).replace(",", "','")+"'";//Mdl_Variables.P_Str_CasosExec.replace(",", "','");
		}

 
		
		if (!strCasosEjecutar.equals("")) 
		{
			Mdl_Variables.strCasosExec = " AND ID_CASO IN (" + strCasosEjecutar + ") ";
			//strCasosEliminarLog = " AND INDICE IN (" + strCasosEjecutar + ") ";
			//strCasosActualizarLog = " WHERE ID_CASO IN (" + strCasosEjecutar + ") ";
		}
    	try
    	{
    		

	    	Mdl_Variables.P_Str_Ciclo = Ciclo_Funcional;                                         
	    	Mdl_Variables.Vrb_VersionAplicativo = Version_Aplicativo;                            
	        if (Tipo_Ejecucion.toUpperCase().contains("TECNICA"))        
	        {
	        	Mdl_Variables.P_Str_Datos = Mdl_Variables.P_Str_Tabla + "_TEC_D";                              
	            //Call CREAR_DATAPOOL_TEC(P_STR_DATOS, TIPO_EJECUCION)              
	        	Mdl_Variables.P_Str_Script = Mdl_Variables.P_Str_Tabla + "_S";                                  
	        	Mdl_Variables.P_Str_Tabla = Mdl_Variables.P_Str_Tabla + "_TEC";                                
	        }
	        else
	        {
	        	Mdl_Variables.P_Str_Datos = Mdl_Variables.P_Str_Tabla + "_D";                                  
	        	Mdl_Variables.P_Str_Script = Mdl_Variables.P_Str_Tabla + "_S";                                  
	        }                                                                
		
            //Asignación de variables para Trabajar con internet explorer
        	Mdl_Variables.CurBrowser = "ie";

            
            Statement stmt = Mdl_Variables.Cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            Statement stmt2 = Mdl_Variables.Cnn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        	Statement stmt3 = Mdl_Variables.Cnn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        	Statement stmt4 = Mdl_Variables.Cnn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        	Statement stmt5 = Mdl_Variables.Cnn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        	Statement stmt6 = Mdl_Variables.Cnn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        	Statement stmt7 = Mdl_Variables.Cnn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        	
            // abrir el Resulset indicando la tabla keyword a la que queremos acceder
            if (Mdl_Choucair.TRATA_NULL(Mdl_Variables.P_Str_CasosExec,"")!= "")                
            {                
                Mdl_Variables.Rst_Tabla = stmt.executeQuery("SELECT * FROM " + Mdl_Variables.P_Str_Datos + " where UPPER( ESTADO_CASO) ='ACTIVO' " + Mdl_Variables.strCasosExec + " ORDER BY ID_CASO");                
            }else{
                Mdl_Variables.Rst_Tabla = stmt.executeQuery("SELECT * FROM " + Mdl_Variables.P_Str_Datos + " where UPPER( ESTADO_CASO) ='ACTIVO' ORDER BY ID_CASO");
            }

        	
        	// abrir el recordset indicando la tabla keyword a la que queremos acceder
        	Mdl_Variables.Rst_Tabla = stmt.executeQuery("SELECT * FROM " + Mdl_Variables.P_Str_Datos + " where UPPER( ESTADO_CASO) ='ACTIVO' " + Mdl_Variables.strCasosExec + "ORDER BY ID_CASO");
            // abrir el recordset indicando el identificador en la tabla de Datos
        	Mdl_Variables.Rst_Coordenadas = stmt2.executeQuery("SELECT * FROM " + Mdl_Variables.P_Str_Script + "  where UPPER( AUTOMATE) ='R' ORDER BY ID");
            // abrir el recordset indicando la tabla de mensajes acierto y error
        	Mdl_Variables.Rst_Mensaje = stmt3.executeQuery("SELECT * FROM TBL_MENSAJES ORDER BY ID");
            // abrir el recordset indicando la tabla del logo que vamos a utilizar
        	Mdl_Variables.Rst_Log = stmt4.executeQuery("SELECT NOMBRE_TABLA, ID_MAESTRO,NUMERO_CICLO from TBL_LOG_MAESTRO where NOMBRE_TABLA = '" + Mdl_Variables.P_Str_Tabla + "'");
            // abrir el recordset indicando la tabla del log detalle que vamos a utilizar para borrar las imagenes
        	Mdl_Variables.Rst_Log_Detalle = stmt5.executeQuery("SELECT IMAGEN FROM TBL_LOG_DETALLE");
            // abrir el recordset indicando la tabla de objetos que vamos a utilizar para borrar las imagenes
        	 Mdl_Variables.Rst_Objetos = stmt6.executeQuery("SELECT * FROM TBL_OBJETOS WHERE PANTALLA = '" + Mdl_Variables.P_Str_Tabla + "'");
			//se define la ruta del repositorio donde se almacenaran las evidencias de las ejecuciones
        	Mdl_Variables.Rst_Aplicativo = stmt7.executeQuery("SELECT * FROM Tbl_Aplicativo WHERE Nombre = '" + Mdl_Variables.P_Str_Aplicativo + "'");
        	
			if  (Mdl_Variables.Rst_Aplicativo.next()==true)
			{
				Mdl_Variables.Ruta_Aplicativo = Mdl_Variables.Rst_Aplicativo.getString("Ruta_repositorio");
				//Mdl_Variables.Ruta_Object_Mobile=  Mdl_Variables.Rst_Aplicativo.getString("Ruta_objetos");
				//Mdl_Variables.Ruta_Aplicativo = "C:\\evidencia";
			}else{
				//Mdl_Variables.Ruta_Aplicativo = "C:\\evidencia";
				//System.out.println("No se encontró la ruta del aplicativo " + Mdl_Variables.P_Str_Aplicativo);
				//return;
			}
			
//            // Ruta del aplicativo
//            //Limpieza del datapool y la evidencia no usada 
//            //si ponemos conecta sql en true borra las imagenes del disco duro y limplia los registros
//
//			//Variable creada con el fín de eliminar archivos del disco
			File ArchivosDisco;
//			//------------------------------------------------------------------------------------------------------------------------------------------------------------
//			//Si se requiere generar html de nuevo, poner un break point en la línea           Set fso = CreateObject("Scripting.FileSystemObject")                  ejecutar el script y en la ventana de 
//			//Comando escribir                 generar_html "aaa", "bbb"                     cambiando aaa por responsable y bbb por arquitecto
//			//------------------------------------------------------------------------------------------------------------------------------------------------------------
//			************************************************
			if(Limpiar_Datos){
                if(Mdl_Variables.Rst_Log.next()){
                    if (Mdl_Choucair.TRATA_NULL(Mdl_Variables.P_Str_CasosExec,"")!= "")                
                    {
                        //strCasosEjecutar = Mdl_Variables.P_Str_CasosExec.replace(",", "','");
                        Mdl_Variables.Rst_Limpia_Datos = Mdl_Variables.Cnn.prepareCall("SELECT * FROM TBL_LOG_DETALLE WHERE ID_MAESTRO = " + Mdl_Variables.Rst_Log.getString("ID_MAESTRO") + " AND CICLO = '" + Mdl_Variables.P_Str_Ciclo + "' AND VERSION = '" + Version_Aplicativo + "' AND INDICE in (" + strCasosEjecutar + ") ").executeQuery();
                    }else
                    {
                        Mdl_Variables.Rst_Limpia_Datos = Mdl_Variables.Cnn.prepareCall("SELECT * FROM TBL_LOG_DETALLE WHERE ID_MAESTRO = " + Mdl_Variables.Rst_Log.getString("ID_MAESTRO") + " AND CICLO = '" + Mdl_Variables.P_Str_Ciclo + "' AND VERSION = '" + Version_Aplicativo + "'").executeQuery();
                    }
                    while(Mdl_Variables.Rst_Limpia_Datos.next()){
                        ArchivosDisco = new File(Mdl_Variables.Ruta_Aplicativo + "\\" + Mdl_Variables.Rst_Limpia_Datos.getString("IMAGEN"));
                        if(!ArchivosDisco.delete()){
                            //System.out.println("No se eliminó el archivo: " + Mdl_Variables.Ruta_Aplicativo + "\\" + Mdl_Variables.Rst_Limpia_Datos.getString("IMAGEN"));
                        }
                    }
                    if (Mdl_Choucair.TRATA_NULL(Mdl_Variables.P_Str_CasosExec,"")!= "") //(Mdl_Variables.P_Str_CasosExec!= null)                
                    {
                        //strCasosEjecutar = Mdl_Variables.P_Str_CasosExec.replace(",", "','");
                        if(Mdl_Variables.Cnn.prepareStatement("DELETE FROM TBL_LOG_DETALLE WHERE ID_MAESTRO = " + Mdl_Variables.Rst_Log.getString("ID_MAESTRO") + " AND CICLO ='" + Mdl_Variables.P_Str_Ciclo + "' AND VERSION = '" + Version_Aplicativo + "' AND INDICE in (" + strCasosEjecutar+ ")").executeUpdate() == 0){
                            //System.out.println("No se eliminaron los datos de la tabla TBL_LOG_DETALLE");
                            }
                    }else{
                        if(Mdl_Variables.Cnn.prepareStatement("DELETE FROM TBL_LOG_DETALLE WHERE ID_MAESTRO = " + Mdl_Variables.Rst_Log.getString("ID_MAESTRO") + " AND CICLO ='" + Mdl_Variables.P_Str_Ciclo + "' AND VERSION = '" + Version_Aplicativo + "'").executeUpdate() == 0){
                        //System.out.println("No se eliminaron los datos de la tabla TBL_LOG_DETALLE");
                        }
                    }
                }
                Mdl_Variables.Rst_Log.absolute(0);

                if(Mdl_Variables.Cnn.prepareStatement("UPDATE " + Mdl_Variables.P_Str_Datos + " SET UTILIZADO = 0, HORA = 0, TIEMPO_EJECUCION_CASO = 0").executeUpdate() == 0){
                    //System.out.println("No se encotraron datos a acutaliar en " + Mdl_Variables.P_Str_Datos);
                }
            }else{
                try{
                    Mdl_Variables.Rst_Tabla.close();
                }catch(Exception e){}
                Mdl_Variables.Rst_Log.next();
                if (Mdl_Choucair.TRATA_NULL(Mdl_Variables.P_Str_CasosExec,"")!= "") //(Mdl_Variables.P_Str_CasosExec!= null)                
                {
                    Mdl_Variables.Rst_Tabla = stmt.executeQuery("SELECT * FROM " + Mdl_Variables.P_Str_Datos + " WHERE UTILIZADO <> -1 AND UPPER( ESTADO_CASO) ='ACTIVO' and ID_CASO in (" + strCasosEjecutar + " ORDER BY ID_CASO");
                    //Mdl_Variables.Rst_Tabla = Mdl_Variables.Cnn.prepareCall("SELECT * FROM " + Mdl_Variables.P_Str_Datos + " WHERE UTILIZADO <> -1 AND UPPER( ESTADO_CASO) ='ACTIVO' and ID_CASO in ('" + strCasosEjecutar + "' ORDER BY ID_CASO").executeQuery();
                }else{    
                    Mdl_Variables.Rst_Tabla = stmt.executeQuery("SELECT * FROM " + Mdl_Variables.P_Str_Datos + " WHERE UTILIZADO <> -1 AND UPPER( ESTADO_CASO) ='ACTIVO' ORDER BY ID_CASO");
                    //Mdl_Variables.Rst_Tabla = Mdl_Variables.Cnn.prepareCall("SELECT * FROM " + Mdl_Variables.P_Str_Datos + " WHERE UTILIZADO <> -1 AND UPPER( ESTADO_CASO) ='ACTIVO' ORDER BY ID_CASO").executeQuery();
                }
                while(Mdl_Variables.Rst_Tabla.next()){
                    Mdl_Variables.Rst_Limpia_Datos = Mdl_Variables.Cnn.prepareCall("SELECT * FROM TBL_LOG_DETALLE WHERE ID_MAESTRO = " + Mdl_Variables.Rst_Log.getString("ID_MAESTRO") + " AND INDICE = " + Mdl_Variables.Rst_Tabla.getString("ID_CASO") + " AND CICLO ='" + Mdl_Variables.P_Str_Ciclo + "' AND VERSION = '" + Version_Aplicativo + "'").executeQuery();
                    while(Mdl_Variables.Rst_Limpia_Datos.next()){
                        ArchivosDisco = new File(Mdl_Variables.Ruta_Aplicativo + "\\" + Mdl_Variables.Rst_Limpia_Datos.getString("IMAGEN"));
                        if(!ArchivosDisco.delete()){
                            //System.out.println("No se eliminó el archivo: " + Mdl_Variables.Ruta_Aplicativo + "\\" + Mdl_Variables.Rst_Limpia_Datos.getString("IMAGEN"));
                        }
                    }
                    if(Mdl_Variables.Cnn.prepareStatement("DELETE FROM TBL_LOG_DETALLE WHERE INDICE = " + Mdl_Variables.Rst_Tabla.getString("ID_CASO") + " AND ID_MAESTRO = " + Mdl_Variables.Rst_Log.getString("ID_MAESTRO") + " AND CICLO ='" + Mdl_Variables.P_Str_Ciclo + "' AND VERSION = '" + Version_Aplicativo + "'").executeUpdate() == 0){
                        //System.out.println("No se eliminaron los datos de la tabla TBL_LOG_DETALLE");
                    }
                }
                //luego de ejecutar la sentencia se cargan los recorsets de nuevo
                Mdl_Variables.Rst_Tabla.close();
                Mdl_Variables.Rst_Tabla = stmt.executeQuery("SELECT * FROM " + Mdl_Variables.P_Str_Datos + " WHERE UTILIZADO <> -1 AND UPPER( ESTADO_CASO) ='ACTIVO'");
                //Mdl_Variables.Rst_Tabla = Mdl_Variables.Cnn.prepareCall("SELECT * FROM " + Mdl_Variables.P_Str_Datos + " WHERE UTILIZADO <> -1 AND UPPER( ESTADO_CASO) ='ACTIVO'").executeQuery();
                //If Rst_Tabla.RecordCount = 0 Then
                //    Debug.WriteLine("------tabla sin datos------")
                //End If
                Mdl_Variables.Rst_Log.absolute(0);
            }
            
            Mdl_Variables.p_cnn = Mdl_Variables.Cnn;
        }catch (Exception ex){
            System.out.println("Error en Mdl_Datapool.Conectar_Sql: " + ex.getMessage());
        }
        
     }
    
	//---------------------------------------------------------------------------------------
    // Procedure : SCHEDULE
    // Author    : JAB
    // Date      : 27/06/2012
    // Purpose   : Procedimiento que busca ejecuciones programadas para la máquina
    //---------------------------------------------------------------------------------------
	   public static void Schedule(String Str_DataPool ,String Str_BasedeDatos ,String Str_RutaRepositorio ,String Str_Usuario ,String Str_Password ,String Str_Servidor )
	    {
	    	Mdl_Variables.blnpendiente = false;
		try
		{
		    java.net.InetAddress InfoSistema = java.net.InetAddress.getLocalHost();
		    Mdl_Variables.P_Str_NombreMaquina =InfoSistema.getHostName();
		    Mdl_Variables.P_Entrada_Sistema =new Date();
		    Mdl_Variables.P_TiempoTotal_Sistema =new Date();

		    Conectar_Dp(Str_DataPool, Str_RutaRepositorio, Str_BasedeDatos, Str_Usuario, Str_Password, Str_Servidor);
		    
		    //Consulta ejecuciones pendientes en la tabla schedule, teniendo en cuenta la máquina desde la cuál se ejecuta
		    Mdl_Variables.Rst_QaPendienes = Mdl_Variables.Cnn.prepareCall("SELECT * FROM TBL_SCHEDULE_EXEC  WHERE EXECUTAR = 'TRUE' AND EXECUTADO = 'FALSE' AND upper(MAQUINA)='" + Mdl_Variables.P_Str_NombreMaquina.trim().toUpperCase() + "' ORDER BY DATA_EXEC, HORA_EXEC, INDICE").executeQuery();

	        //Valida que existan scripts pendientes por ejecución
	        if (Mdl_Variables.Rst_QaPendienes.next() == true) {
	        	
	        	
	            if (Mdl_Choucair.DateCompare(new Date(),Mdl_Variables.Rst_QaPendienes.getString("DATA_EXEC") + " " + Mdl_Variables.Rst_QaPendienes.getString("HORA_EXEC")) == false)
	            {
	            	Mdl_Variables.blnpendiente = true;
	            	Mdl_Variables.dtFechaInicio = new Date();

	            	//linea comentariarar cuando se haga debug.
            		Mdl_Variables.Cnn.prepareCall( "UPDATE TBL_SCHEDULE_EXEC SET EXECUTADO = 'TRUE', FECHA_INICIO_EXEC = '" + Mdl_Variables.dtFechaInicio + "' WHERE INDICE = " + Mdl_Variables.Rst_QaPendienes.getString("INDICE")).execute();

			        //Asignación, de las variables para keyword driver
	            	Mdl_Variables.Rst_Script = Mdl_Variables.Cnn.prepareCall( "SELECT * FROM TBL_SCRIPT WHERE SCRIPT= '" + Mdl_Variables.Rst_QaPendienes.getString("SCRIPT") + "'").executeQuery();
	            	if(Mdl_Variables.Rst_Script.next()){
		            	Mdl_Variables.P_Str_Tabla = Mdl_Variables.Rst_Script.getString("SCRIPT");
		            	Mdl_Variables.P_Str_Aplicativo = Mdl_Variables.Rst_Script.getString("APLICATIVO");
		            	Mdl_Variables.P_Str_Tela = Mdl_Variables.Rst_Script.getString("PANTALLA");
		            	Mdl_Variables.P_Str_Modulo = Mdl_Variables.Rst_Script.getString("MODULO");
		            	Mdl_Variables.P_Str_Ciclo = Mdl_Variables.Rst_QaPendienes.getString("CORRIDA");
		            	Mdl_Variables.P_Str_Tester = Mdl_Variables.Rst_QaPendienes.getString("TESTER_RESPONSABLE");
		            	Mdl_Variables.P_Str_Tipo_Ejecucion = Mdl_Variables.Rst_Script.getString("TIPO_EJECUCION");
		            	Mdl_Variables.Tipo_Aplicativo = Mdl_Variables.Rst_QaPendienes.getString("APLICATIVO");
		            	//Mdl_Variables.Tipo_Aplicativo = Mdl_Variables.Rst_Script.getString("TIPO_APLICATIVO");
		            	Mdl_Variables.P_Str_Nombre_Version = Mdl_Variables.Rst_QaPendienes.getString("NOMBRE_VERSION");
		            	Mdl_Variables.P_Bln_Limpia_Casos = Mdl_Variables.Rst_QaPendienes.getBoolean("LIMPIA_CASOS");
		            	Mdl_Variables.P_Str_Arquitecto = Mdl_Variables.Rst_QaPendienes.getString("ARQUITECTO_RESPONSABLE");
		            	Mdl_Variables.P_Str_Sistema = Mdl_Variables.Rst_QaPendienes.getString("SISTEMA");
		            	Mdl_Variables.P_Str_Ambiente = Mdl_Variables.Rst_QaPendienes.getString("AMBIENTE");
		            	// WMN20150324
		            	Mdl_Variables.P_Str_CasosExec = Mdl_Variables.Rst_QaPendienes.getString("CASOS_EJECUTAR");
		            
		            	Mdl_Variables.Rst_Script.close();
		            	
		            	//Reutilizamos el recordet script para obtener la ruta de prueba desde la tbl_ambientes de la base de datos del cliente
		            	
		            	
		            }else{
		            	Mdl_Variables.blnpendiente = false;
		            	System.out.println("El script " + Mdl_Variables.Rst_QaPendienes.getString("SCRIPT") + " existe en TBL_SCHEDULE_EXEC pero no existe en TBL_SCRIPT.");
		            }
	            	
	            	Mdl_Variables.Rst_Script = Mdl_Variables.Cnn.prepareCall( "SELECT * FROM TBL_AMBIENTES WHERE NOMBRE= '" + Mdl_Variables.P_Str_Ambiente + "'").executeQuery();
	            	if(Mdl_Variables.Rst_Script.next()){
		            	Mdl_Variables.P_Str_RutaAmbientePrincipal = Mdl_Variables.Rst_Script.getString("DIRECCION_PRINCIPAL");
		            	Mdl_Variables.P_Str_RutaAmbienteSecundario = Mdl_Variables.Rst_Script.getString("DIRECCION_SECUNDARIA");
		            	
		            			            	
		            }else{
		            	Mdl_Variables.P_Str_RutaAmbientePrincipal = "";
		            	Mdl_Variables.P_Str_RutaAmbienteSecundario = "";
		            }
	            	
	            	
		        }else{
		        	Mdl_Variables.blnpendiente = false;
		        }
	        }
		}catch(Exception ex)
		{
			Mdl_Variables.blnpendiente = false;
			System.out.println("Error en Mdl_Datapool.Schedule: " + ex.getMessage());
		}
		
    	
	}   
	    public static void Conectar_Dp(String Str_Datapool ,String Str_RutaRepositorio ,String Str_BasedeDatos ,String Str_Usuario ,String Str_Password ,String Str_Servidor) throws SQLException
	    {
		    switch (BasedeDatos.getValue(Str_BasedeDatos.toLowerCase()))
		    {
			    case access:
				    if (Mdl_Variables.P_Str_Usuario == "")
				    {
				    }
				    else
				    {
				    }
			    break;
			    case sql:
			        SQLServerDataSource ds = new SQLServerDataSource();
			        ds.setServerName(Str_Servidor); // Instancia
			        ds.setDatabaseName(Str_Datapool);  //BD       
			        ds.setUser(Str_Usuario);//usuario
			        ds.setPassword(Str_Password);//clave usuario	
			        Mdl_Variables.Cnn = ds.getConnection();
				break;
			    case sql2008:
			    break;
			    case access2007:
			    	if (Mdl_Variables.P_Str_Usuario == "")
				    {
				    }
				    else
				    {
				    }
			    break;
			    case mysql:
			    break;
			    case oracle:
			    break;
			    case oracle10g:
			    break;
			    case db2:
			    break;
			    case as400:
//			    	Connection con = null;
//			    	//Class.forName("com.ibm.as400.access.AS400JDBCDriver");
//			    	//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			    				    	
//			    	con = DriverManager.getConnection("jdbc:as400://192.168.0.226", "TD1JDBC", "TD1JDBC");
			    	 
//			        Statement stmt = con.createStatement();
			 
//			        ResultSet rs = stmt.executeQuery("SELECT * FROM DAPCYFILES.CUMST");
			    	
			    	
//			    	//Implementado en MDL_Tuya - Conectar
//		    		DriverManager.registerDriver(new com.ibm.as400.access.AS400JDBCDriver());
//			    	//("jdbc:as400://IP/nombreBD","usuario","clave");
//					Mdl_Variables.CnnAS = DriverManager.getConnection("jdbc:as400://172.25.8.24:443/C10E774F", " + Str_Usuario + ", " + Str_Password + "); //TUYA
//					
			    break;
			    }
		}
	    public static void Desconectar_Sql()
	    {
	    	try
	    	{	
	    		try{Mdl_Variables.driver.quit();}catch(Exception e){}
		    	Mdl_Variables.Rst_Tabla.close();
	          	Mdl_Variables.Rst_Coordenadas.close();
	        	Mdl_Variables.Rst_Mensaje.close();
	        	Mdl_Variables.Rst_Log.close();
	        	Mdl_Variables.Rst_Log_Detalle.close();
	        	Mdl_Variables.Rst_Objetos.close();
	        	Mdl_Variables.Rst_Aplicativo.close();
	        	Mdl_Variables.Cnn.close();
	    	}catch(Exception e){}
	    }
}
