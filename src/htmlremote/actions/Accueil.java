package htmlremote.actions;



import javahttpserver.Action;
import javahttpserver.HttpSocketAnswer;
import javahttpserver.HttpSocketRequest;
import javahttpserver.HttpSocketSessionManager;
import javahttpserver.JavaHttpServerLogger;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wolffp
 */
public class Accueil extends Action {
    
    public Accueil(){};
    
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
            HttpSocketSessionManager.getInstance().disconnect(request.getSessionId());
            answer.redirect("Accueil");
        }
        
                 
        if(action.equalsIgnoreCase("stop")){
            JavaHttpServerLogger.getInstance().log(this.getClass(), 100, "Manual Stop requested");
            //wait is history is beeing written
            int timeOut = 0;
            this.buildPageStop();
            
            //return false to stop Server
            return false;
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
        this.writeOutPut("<h1>HTMLRemote - Action launcher for HTMLRemote</h1>");
        
        /* Menu */
        this.writeOutPut(Menu.buildMenu(request));

        this.writeOutPut("<br/>");
        this.writeOutPut("<p><a href=\".\\action.pw?page=Accueil&action=disconnect\" class=\"button\">Disconnect</a></p>");
        
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
