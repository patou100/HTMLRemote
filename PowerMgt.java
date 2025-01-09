package htmlremote.actions;



import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.net.URLDecoder;
import javahttpserver.Action;
import javahttpserver.HttpSocketAnswer;
import javahttpserver.HttpSocketRequest;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wolffp
 */
public class PowerMgt extends Action {
    

    public PowerMgt(){};
    
    /**
     * Manage Action
     * @param request
     * @param answer
     * @return 
     */
    @Override
    public boolean manageAction(HttpSocketRequest request, HttpSocketAnswer answer){
        
        String action = request.getParameterValue("action");
        
        try{
            String osName = System.getProperty("os.name");
            String shutdownCommand = "";
            
            if(action.equalsIgnoreCase("STOP")){
                if (osName.startsWith("Win")) {
                    shutdownCommand = "shutdown.exe -s -t 0";
                } else if (osName.startsWith("Linux") || osName.startsWith("Mac")) {
                    shutdownCommand = "shutdown -h now";
                } else {
                    this.writeOutPut("Shutdown unsupported operating system ...");
                    return true;
                }           
            }else if(action.equalsIgnoreCase("RESTART")){
                if (osName.startsWith("Win")) {
                    shutdownCommand = "shutdown.exe -r -t 0";
                } else if (osName.startsWith("Linux") || osName.startsWith("Mac")) {
                    shutdownCommand = "shutdown -r now";
                } else {
                    this.writeOutPut("Shutdown unsupported operating system ...");
                    return true;
                }           
            }
            
            Runtime.getRuntime().exec(shutdownCommand);
            
            this.writeOutPut("OK");

        }catch(Exception ex){
            this.writeOutPut(ex.getClass().toString() + ":" + ex.getMessage());
            ex.printStackTrace();
            return true;
        }

        return true;
        
    }    
}
