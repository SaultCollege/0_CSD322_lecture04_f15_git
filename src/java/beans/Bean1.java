/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

/**
 *
 * @author fcarella
 */
@Named("bean1")
@SessionScoped
public class Bean1 implements Serializable{
    private String firstname;
//    private UIInput middlename;
    private String lastname;
    private int age;
    private int retirementAge;
    private boolean retired;
    private String buttonValue="Click Me!";
    
    public Bean1() {
    }
    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the retirementAge
     */
    public int getRetirementAge() {
        return retirementAge;
    }


    /**
     * @return the retired
     */
    public boolean isRetired() {
        if(age>=retirementAge)
            return true;
        return false;
    }

    /**
     * @return the middlename
     */
/*    public UIInput getMiddlename() {
        return middlename;
    }

    public void setMiddlename(UIInput middlename) {
        this.middlename = middlename;
    }
*/
    /**
     * @param retirementAge the retirementAge to set
     */
    public void setRetirementAge(int retirementAge) {
        this.retirementAge = retirementAge;
    }

    /**
     * @param retired the retired to set
     */
    public void setRetired(boolean retired) {
        this.retired = retired;
    }

    public void buttonListener(ActionEvent event){
        String value2=(String)event.getComponent().getAttributes().get("value2");
    }
    
    public String getButtonValue() {
        return buttonValue;
    }

    public void setButtonValue(String buttonValue) {
        this.buttonValue = buttonValue;
    }

}
