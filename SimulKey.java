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
public class SimulKey extends Action {
    
    Robot _robot = null;
    final int[] _fKeys = {0,KeyEvent.VK_F1,KeyEvent.VK_F2,KeyEvent.VK_F3,KeyEvent.VK_F4,KeyEvent.VK_F5,KeyEvent.VK_F6,KeyEvent.VK_F7
                                ,KeyEvent.VK_F8,KeyEvent.VK_F9,KeyEvent.VK_F10,KeyEvent.VK_F11,KeyEvent.VK_F12};    
    
    public SimulKey(){};
    
    /**
     * Manage Action
     * @param request
     * @param answer
     * @return 
     */
    @Override
    public boolean manageAction(HttpSocketRequest request, HttpSocketAnswer answer){
        
        String keyboard = request.getParameterValue("keyboard");
        
        boolean shift = false;
        boolean ctrl = false;
        boolean alt = false;
        
        try{
            if(_robot == null){
                _robot = new Robot();
            }
            keyboard = keyboard.replace("+", "PLUS");
            keyboard = URLDecoder.decode(keyboard,"UTF-8");
            keyboard = keyboard.replace("PLUS", "+");
            
            // SHIFT
            if(keyboard.toUpperCase().startsWith("SHIFT+")){
                keyboard = keyboard.substring(6);
                shift = true;
            }
            
            // CTRL
            if(keyboard.toUpperCase().startsWith("CTRL+")){
                keyboard = keyboard.substring(5);
                ctrl = true;
            }
            
            // ALT
            if(keyboard.toUpperCase().startsWith("ALT+")){
                keyboard = keyboard.substring(4);
                alt = true;
            }            
            
            System.out.println(keyboard + " received");
            
            // Key management
            if(keyboard.equalsIgnoreCase("right")){
                _robot.keyPress(KeyEvent.VK_RIGHT);
                Thread.sleep(100);
                _robot.keyRelease(KeyEvent.VK_RIGHT);
                
            }else if(keyboard.equalsIgnoreCase("down")){
                _robot.keyPress(KeyEvent.VK_DOWN);
                Thread.sleep(100);
                _robot.keyRelease(KeyEvent.VK_DOWN);
                
            }else if(keyboard.equalsIgnoreCase("left")){
                _robot.keyPress(KeyEvent.VK_LEFT);
                Thread.sleep(100);
                _robot.keyRelease(KeyEvent.VK_LEFT);
                
            }else if(keyboard.equalsIgnoreCase("up")){
                _robot.keyPress(KeyEvent.VK_UP);
                Thread.sleep(100);
                _robot.keyRelease(KeyEvent.VK_UP);
                
            }else if(keyboard.equalsIgnoreCase("enter")){
                _robot.keyPress(KeyEvent.VK_ENTER);
                Thread.sleep(100);
                _robot.keyRelease(KeyEvent.VK_ENTER);
                
            }else if(keyboard.equalsIgnoreCase("esc")){
                _robot.keyPress(KeyEvent.VK_ESCAPE);
                Thread.sleep(100);
                _robot.keyRelease(KeyEvent.VK_ESCAPE);
                
            }else if(keyboard.equalsIgnoreCase("del")){
                _robot.keyPress(KeyEvent.VK_DELETE);
                Thread.sleep(100);
                _robot.keyRelease(KeyEvent.VK_DELETE);
                
            }else if(keyboard.equalsIgnoreCase("back")){
                _robot.keyPress(KeyEvent.VK_BACK_SPACE);
                Thread.sleep(100);
                _robot.keyRelease(KeyEvent.VK_BACK_SPACE);
                
            }else if(keyboard.equalsIgnoreCase("tab")){
                if(alt){
                    _robot.keyPress(KeyEvent.VK_ALT);
                }                
                _robot.keyPress(KeyEvent.VK_TAB);
                Thread.sleep(100);
                _robot.keyRelease(KeyEvent.VK_TAB);
                if(alt){
                    _robot.keyRelease(KeyEvent.VK_ALT);
                }
                
            }else if(keyboard.length() >= 2 && keyboard.toUpperCase().startsWith("F")){
                int idx = Integer.parseInt(keyboard.substring(1));
                _robot.keyPress(_fKeys[idx]);
                Thread.sleep(100);
                _robot.keyRelease(_fKeys[idx]);
                
            }else{
                for (int i = 0; i < keyboard.length(); i++) {
                    // Check if the current character is a capital letter
                    if(shift){
                        _robot.keyPress(KeyEvent.VK_SHIFT);
                    }
                    if(ctrl){
                        _robot.keyPress(KeyEvent.VK_CONTROL);
                    }
                    if(alt){
                        _robot.keyPress(KeyEvent.VK_ALT);
                    }

                    _robot.keyPress(Character.toUpperCase(keyboard.charAt(i)));
                    Thread.sleep(100);
                    _robot.keyRelease(Character.toUpperCase(keyboard.charAt(i)));

                    if(alt){
                        _robot.keyRelease(KeyEvent.VK_ALT);
                    }
                    if(ctrl){
                        _robot.keyRelease(KeyEvent.VK_CONTROL);
                    }  
                    if(shift){
                        _robot.keyRelease(KeyEvent.VK_SHIFT);
                    }

                    Thread.sleep(10);
                }
            }            
            
            this.writeOutPut("OK");

        }catch(Exception ex){
            this.writeOutPut(ex.getClass().toString() + ":" + ex.getMessage());
            ex.printStackTrace();
            return true;
        }

        return true;
        
    }    
}
