package htmlremote.actions;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javahttpserver.Action;
import javahttpserver.Constants;
import javahttpserver.HttpSocketAnswer;
import javahttpserver.HttpSocketRequest;
import javahttpserver.ServerProperties;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wolffp
 */
public class SoftwareMgt extends Action {
    
    private TreeMap<String,String> _softs = new TreeMap();
    

    public SoftwareMgt(){};
    
    /**
     * Manage Action
     * @param request
     * @param answer
     * @return 
     */
    @Override
    public boolean manageAction(HttpSocketRequest request, HttpSocketAnswer answer){
        
        String action = request.getParameterValue("action");
        
        if(action != null && action.equalsIgnoreCase("EXECUTE")){
            try{
                String startCommand = "";
                this.extractSoftCommands();
                
                String command = request.getParameterValue("command");

                if(command.equalsIgnoreCase("NOTEPAD")){
                    startCommand = _softs.get(command);
                }


                Runtime.getRuntime().exec(startCommand);
                this.writeOutPut("OK");
            }catch(Exception ex){
                this.writeOutPut(ex.getClass().toString() + ":" + ex.getMessage());
                ex.printStackTrace();
                return true;
            }

        }else if(action != null && action.equalsIgnoreCase("DISPLAY")){
            try{
                this.extractSoftCommands();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
             this.buildPage(request);
        }else if(action != null && action.equalsIgnoreCase("ADDKEY")){
            try{
                String key = URLDecoder.decode(request.getParameterValue("key"));
                String value = URLDecoder.decode(request.getParameterValue("value"));
                this.extractSoftCommands();
                _softs.put(key,value);
                this.storeSoftCommands();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
             this.buildPage(request);
        }else if(action != null && action.equalsIgnoreCase("REMOVEKEY")){
            try{
                String key = URLDecoder.decode(request.getParameterValue("key"));
                this.extractSoftCommands();
                _softs.remove(key);
                this.storeSoftCommands();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
             this.buildPage(request);
        }

        return true;
        
    }
    
    /**
     * Build final page
     * @return 
     */    
    private void buildPage(HttpSocketRequest request){
        /* Title */
        this.writeOutPut("<h1>" + ServerProperties.getInstance().getProperty(Constants.SERVER_TITLE) + "</h1>");
        
        /* Menu */
        this.writeOutPut(Menu.buildMenu(request));

        this.writeOutPut("<br/>");
        this.writeOutPut("<h2>Define mapping keyword <=> Application Executable</h2>");
        
        this.writeOutPut("<table border=1>");
            this.writeOutPut("<tr>");
                this.writeOutPut("<th>Keyword</th>");
                this.writeOutPut("<th>Executable</th>");
                this.writeOutPut("<th>Action</th>");
            this.writeOutPut("</tr>");
            
            Iterator<String> iterK = _softs.keySet().iterator();
            String key;
            while(iterK.hasNext()){
                this.writeOutPut("<tr>");
                    key = iterK.next();
                    this.writeOutPut("<td>" + key + "</td>");
                    this.writeOutPut("<td>" + _softs.get(key) + "</td>");
                    this.writeOutPut("<td>" + "<a href=\".\\action.pw?page=SoftwareMgt&action=RemoveKey&key=" + key + "\">Remove</a>" + "</td>");
                this.writeOutPut("</tr>");
            }
            
            // Put form here
            this.writeOutPut("<tr/>");
                this.writeOutPut("<form action=\"action.pw\" method=\"post\" >");
                this.writeOutPut("<input type=\"hidden\" name=\"page\" id=\"page\" value=\"SoftwareMgt\" />");
                this.writeOutPut("<input type=\"hidden\" name=\"action\" id=\"action\" value=\"addkey\" />");  
                
                this.writeOutPut("<td><input type=text name=\"key\" value=\"key\" ></input></td>");
                this.writeOutPut("<td><input type=text name=\"value\" value=\"value\" ></input></td>");
                this.writeOutPut("<td><input type=submit value=\"Add\" ></input></td>");
                this.writeOutPut("</form>");
            this.writeOutPut("</tr>");            
            
        this.writeOutPut("</table>");
        
        this.writeOutPut("<br/>");
        this.writeOutPut("<br/>");
        
        
        
        
        
        this.writeOutPut("<br/>");
        this.writeOutPut("<br/>");
        
        this.writeOutPut("<br/>");
        
    }
    
    private void storeSoftCommands(){
        BufferedWriter writer = null;
        try {
            String file = "." + File.separator + "soft_mapping.conf";
            String str = "";
            
            Iterator<String> iterK = _softs.keySet().iterator();
            String key;
            while(iterK.hasNext()){
                key = iterK.next();
                str = str + key + "=" + _softs.get(key) + "\n";
            }
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(str);
            writer.close();
            
        } catch (IOException ex) {
            Logger.getLogger(SoftwareMgt.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(SoftwareMgt.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void extractSoftCommands() throws FileNotFoundException, IOException{
        String file = "." + File.separator + "soft_mapping.conf";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            String key;
            String value;
            StringTokenizer strK;
            while((line = reader.readLine()) != null){
                strK = new StringTokenizer(line,"=");
                key = strK.nextToken();
                value = strK.nextToken();
                _softs.put(key, value);
            }
        }     
        finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ex) {
                    //
                }
            }
        }
        
    }
}
