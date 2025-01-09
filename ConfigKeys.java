package htmlremote.actions;



import javahttpserver.Action;
import javahttpserver.HttpSocketAnswer;
import javahttpserver.HttpSocketRequest;
import javahttpserver.HttpSocketSessionManager;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wolffp
 */
public class ConfigKeys extends Action {
    
    public ConfigKeys(){};
    
    /**
     * Manage Action
     * @param request
     * @param answer
     * @return 
     */
    @Override
    public boolean manageAction(HttpSocketRequest request, HttpSocketAnswer answer){
        
        String action = request.getParameterValue("action");
        
        if(action.equalsIgnoreCase("disconnect")){
            // TODO
        }
        
        this.buildPage(request);
        
        return true;
        
    }

    /**
     * Build final page
     * @return 
     */    
    private void buildPage(HttpSocketRequest request){
        /* Title */
        this.writeOutPut("<h1>HTMLRemote - Config / Map keys</h1>");
        
        /* Menu */
        this.writeOutPut(Menu.buildMenu(request));

        this.writeOutPut("<br/>");
        this.writeOutPut("<p>Form to be included here</p>");
        
        this.writeOutPut("<br/>");
        this.writeOutPut("<br/>");
        
        
        if(HttpSocketSessionManager.getInstance().getSession(request.getSessionId()) != null && 
                HttpSocketSessionManager.getInstance().getSession(request.getSessionId()).getProfileLevel() >= 3){
            this.writeOutPut("<p><a href=\".\\action.pw?page=Accueil&action=stop\" title=\"WARNING\"class=\"button\">!! FULL SERVER STOP !!</a></p>");
        }
        
        
        this.writeOutPut("<br/>");
        this.writeOutPut("<br/>");
        
        this.writeOutPut("<br/>");
        
    }
    
    /**
     * Build final page
     * @return 
     */    
    private void buildPageStop(){
        /* Title */
        this.writeOutPut("<h1>Stopping server</h1>");    
    }
    
}
