package Pck_Choucair;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.internal.seleniumemulation.Click;
import org.openqa.selenium.support.ui.Select;
import java.sql.ResultSetMetaData;
import org.openqa.selenium.firefox.FirefoxDriver;


public abstract class Mdl_Variables {
	
	public static int contSegCaso=0;
	public static int contSegFuncionalidad=0;
	
	
	public static  String TCPath= null;
	public static  String ObjPath= null;
	public static  String Dbalias= null;
	
	public static int StpCnt;
	public static Map<String, String> map = new HashMap<String, String>();
	public static java.util.List<Integer> loopcnt = new java.util.ArrayList<Integer>();
	public static java.util.List<Integer> loopind = new java.util.ArrayList<Integer>();
	public static java.util.List<Integer> lpstrtrow = new java.util.ArrayList<Integer>();
			
	public static String strKword= null;
	public static String [] arrObj;
	public static String strObjtype= null;
	public static String strObjpath= null;
	public static String [] arrAct;
	public static String strActtype= null;
	public static String strActval1= null;
	public static String strActval2= null;

	//Conexión
    public static String Tipo_Aplicativo= null;
    public static String Str_Conexion= null;
    public static Connection Cnn = null;
 
    
    
    public static Connection p_cnn = null;
    
    public static ResultSet Rst_Log = null;
    public static ResultSet RS_L = null;
    public static ResultSet Rst_Loop = null;
    public static ResultSet Rst_Tabla = null;
    public static ResultSet rs_datos = null; 
    public static ResultSet Rst_Objetos = null; 
    public static ResultSet Rst_Mensaje = null; 
    public static ResultSet Rst_Coordenadas = null; 
    public static ResultSet Rst_Evidencia = null; 
    public static ResultSet Rst_Log_Detalle = null; 
    public static ResultSet Rst_Siguiente_Pantalla_S = null; 
    public static ResultSet Rst_Siguiente_Pantalla_D = null; 
    public static ResultSet Rst_Inicio_Sesion = null; 
    public static ResultSet Rst_Limpia_Datos = null; 
    public static ResultSet Rst_Descripcion_Evidencia = null;
    public static ResultSet Str_RutaRepositorio = null;
    public static ResultSet Rst_QaPendienes = null; 
    public static ResultSet Rst_MNTSistema = null; 
    public static ResultSet Rst_Script = null; 
	public static ResultSet Rst_Aplicativo = null;
	public static int intCaso=0; //Variable para mantener el caso de la generación de un consecutivo en el cual se actulizara el escenario

	//Datos del computador
    public static String P_Str_NombreMaquina= null;
    public static String P_Str_Script1= null;
    public static String P_Str_Usuario= null;
    public static String P_Str_Pwd= null;
    public static String P_Str_Servidor= null;
    public static String P_Str_Parametros_Adicionales= null;
    public static Date dtFechaInicio= null;

    //Constantes de conexion
    public static int adLockReadOnly  = 1;
    public static int adLockPessimistic  = 2;
    public static int adLockOptimistic  = 3;
    public static int adLockBatchOptimistic  = 4;
    public static int adOpenForwardOnly  = 0;
    public static int adOpenKeyset  = 1;
    public static int adOpenDynamic  = 2;
    public static int adOpenStatic  = 3;

	//Constantes de mensaje
    public static int MSG_Informacion =3;
    public static int MSG_valido =1;
    public static int MSG_Error =2;

    //Variables Sistema
    public static String P_Str_Tabla= null;

    public static int P_Int_TiempoEntradaSistema= 0;
    public static int boolAction=0; 
    public static int P_Tipo_Log= 0; 
    public static Date P_Entrada_Sistema= null;
    public static Date P_TiempoTotal_Sistema= null;
    public static String Vrb_VersionAplicativo= null;
    public static String P_Str_Ciclo= null; 
    public static String Ruta_Aplicativo= null;
    public static String Ruta_Evidencia= null;
    public static String P_Str_Modulo= null;
    public static String Sql_Log_Maestro= null;
    public static String Sql_Log_Detalle= null;
    public static String Descripcion_Acierto_Error= null;
    public static String P_Str_Datos= null;
    public static String Str_Mensaje= null;
    public static int intNumErrores= 0;
    public static int rowCountDataSheet= 0; 
    public static int intRowCount= 0; 
    public static String P_Str_Script= null;
    public static String Limpiar_Datos= null;
    public static int intLoopCount= 0; 
    public static String CurBrowser= null;
    public static String initial= null;
    public static int InvalidNumber= 0; 
    public static String P_Str_Aplicativo= null;
    public static String P_Str_Datapool= null;
    public static String P_Str_Tela= null;
    public static String P_Vrb_Browser= null;
    public static String P_Str_Descripcion= null;
    public static String PStrObjectValue= null;
    public static String P_Str_Tester= null;
    public static String objCheck= null; 
    public static int inti= 0; 
    public static String objName= null;
    public static String[] arrAction= null;
    public static String[] arrkeyindex= null;
    public static String[] arrKeyValue= null; 
    public static String StrParam= null;
    public static String P_Str_Nombre_Version= null;
    public static Boolean P_Bln_Limpia_Casos= null; 
    public static Boolean blnpendiente=false; 
    public static String P_Str_Arquitecto= null;
    public static String P_Str_Tipo_Ejecucion= null;
    public static String bValicacionEnPrencheCampo= null; 
    public static String CNXMigracionOrigen= null; 
    public static String CNXMigracionDestino= null; 
    public static String rsMigracionOrigen= null; 
    public static String rsMigracionDestino= null; 
    public static int P_Int_TiempoTotalEjecucion= 0; 
    public static int P_Int_TiempoTotalEjecucionOrg= 0; 
    public static int P_Int_TiempoTotalEjecucionDst= 0; 
    public static int p_int_tiempo_ejecucion_destino= 0; 
    public static int p_int_tiempo_ejecucion_origen= 0; 
	public static String FrameName= null;  	    //This variable is used to store the page name 
	public static String parent= null;          //This variable is used to store the current context
	public static String curParent= null; 	    //This variable is used to store the current parent
	public static String parChild= null;	    //This variable is used to store last item in 4th Column of Datasheet
	public static String propertyVal= null;     //This variable is used to store the current property to be used
	public static int childCount= 0;	 	//This variable is used to store the child item count
	public static int index= 0;		   	//This variable is used to store the object index.
	public static String StrIexplorePath= null;
	public static Object object= null;
	public static String P_Str_Sistema= null;
	public static String P_Str_Ambiente= null;
	public static String P_Str_RutaAmbientePrincipal = null;
	public static String P_Str_RutaAmbienteSecundario = null;
	public static String StrCellData= null;
	public static String P_Str_Base_Datos_SOcr= null;
	public static String StrComplementoDescripcion= "";
	public static String StrObject= "";
	
    //Coordenadas Tbl_Objetos
	public static int izquierda=0;
	public static int Arriba=0;
	public static int Alto=0;
	public static int Ancho=0;

	//Datos del usuario
	public static String[] Str_DatosUsuario= null;
	public static String Str_UsuarioActual= null;
    public static String P_Certificado_Dig= null;

	//Variables As400
	public static String Sessions= null;
	public static String As400= null;
	public static String Sess0 = null;
	public static int g_HostSettleTime = 150;	
	
	//Variables WebDriver
	public static WebDriver driver;
	public static WebElement objPerform;
	public static Select objPerform_Select;
	public static Set setOfOldHandles = null;
	public static int ventanacambiar = 0;
	
	 public static String P_Str_IdEvidencia= "";
     public static String P_Str_Desc_Acc_Ant= null;
     public static String P_Str_Desc_Err_Ant= null;
     public static String strTablaAnterior = null;
     
     
     //GINO
     public static Connection CnnAs400 = null;
     public static ResultSet Rst_MigracionDestino = null;
     public static ResultSetMetaData Rst_MDMigracionDestino = null;
     
   //WMN20150323: Se agrega para generar HTML2
   //WMN20150323: Se agrega para generar HTML2
 	public static ResultSet RST_LOG_DETALLE_INDICES = null;
 	public static ResultSet RST_EVIDENCIA = null;
 	public static ResultSet RST_DESCRIPCION_EVIDENCIA = null;
 	public static String strCasosEjecutar = "";
 	public static String strPEvidencia = "";
 	public static String strPasosEvidencia = "";
 	public static String strCasosExec = "";
 	public static String P_Str_CasosExec= null;
    

 	public static String strNombreObjeto="";
	
}
