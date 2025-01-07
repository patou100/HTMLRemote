/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package htmlremote;

import java.io.File;
import java.net.URISyntaxException;
import javahttpserver.JavaHTTPServer;

/**
 *
 * @author wolffp
 */
public class startServer {
    
    public static void main(String args[]){

        try {
            
            
            /* Get file for application configuration */
            File file = new File(startServer.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
            if(file.isDirectory()){
                JavaHTTPServer.initServer(null);
            }else{
                JavaHTTPServer.initServer(file.getPath());
            }
            
            /* Start HTTP Server */ 
            JavaHTTPServer.startServer();
            
        } catch (URISyntaxException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
        
        
        
    }
    
}
