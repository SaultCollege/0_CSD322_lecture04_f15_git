/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author fcarella
 */
@Named("loginController")
@SessionScoped
public class loginController implements Serializable{
    @Inject
    UserBean user;
    public loginController() {
    }
    public String verifyUser(){
        if(user.getName().equals("fcarella") && user.getPassword().equals("itstudies12345"))
            return "success";
        else
            return "failure";
    }
    
}
