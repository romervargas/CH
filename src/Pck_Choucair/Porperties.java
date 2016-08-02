package Pck_Choucair;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException ;
import java.util.Properties ;

public class Porperties 
{ 
    public static void main ( String [ ] args ) 
    { 
    	Properties prop = new Properties ( ) ; 

    	try { 
    		//set the properties value / / Establecer el valor de las propiedades
    		prop.setProperty (  "server", "NBK-416\\SQLEXPRESS");
    		prop.setProperty ( "database" , "BBVA_BNET" ) ;
    		prop.setProperty ( "dbuser" , "sa" ) ; 
    		prop.setProperty ( "dbpassword" , "sql2005" ) ;

    		//save properties to project root folder / / Guardar propiedades para proyectar carpeta raíz
    		prop. store ( new FileOutputStream ( "config.properties" ) , null ) ; 

    	} catch ( IOException ex ) { 
    		ex. printStackTrace ( ) ; 
        } 
    }
} 