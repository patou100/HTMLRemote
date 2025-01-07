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
public class Menu extends Action {
    
    public Menu(){};
    
    /**
     * Manage Action
     * @param request
     * @param answer
     * @return 
     */
    @Override
    public boolean manageAction(HttpSocketRequest request, HttpSocketAnswer answer){
        return true;
    }
    
    /**
     * Build Menu Page
     * @return 
     */    
    private String buildPage(HttpSocketRequest request){
        
        String _body = this.buildMenu(request);
        return _body;
        
    }
    
    
    /**
     * Build Menu Page
     * @return 
     */    
    public static String buildMenu(HttpSocketRequest request){
        
        String _body;
        
        _body = "<ul>";
        _body = _body + "<li><a href=\".\\action.pw?page=Accueil&action=accueil\">Accueil</a></li>\n";
        _body = _body + "<li><a href=\".\\action.pw?page=ConfigKeys&action=display\">Config Keys</a></li>\n";
        if(HttpSocketSessionManager.getInstance().getSession(request.getSessionId()) != null && 
                HttpSocketSessionManager.getInstance().getSession(request.getSessionId()).getProfileLevel() >= 3){
            _body = _body + javahttpserver.actions.Menu.buildAminMenu();
        }
        _body = _body + "</ul>";  
        
        return _body;
        
    }    
    
}
