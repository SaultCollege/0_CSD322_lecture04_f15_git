package org.csd322.jsfclasses;

import entities.Customers;
import sessionbeans.CustomersFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import jsfclasses.util.JsfUtil;

@Named("myCustomersController")
@SessionScoped
public class MyCustomersController implements Serializable {

    @EJB
    private sessionbeans.CustomersFacade ejbFacade;
    private Customers selectedCustomer;
    private boolean debug;

    public MyCustomersController() {
    }


    private CustomersFacade getFacade() {
        return ejbFacade;
    }

   public List<Customers> getCustomers() {
        return ejbFacade.findAll();
    }

    public Customers getCustomers(java.lang.Integer id) {
        return ejbFacade.find(id);
    }
    
    
    public String prepareEdit(Customers c){
        setSelectedCustomer(c);
        return "Edit";
    }
    public String edit() {
        try {
            if(isDebug())
                throw new Exception("Forced Error");
            getFacade().edit(selectedCustomer);
            JsfUtil.addSuccessMessage("CustomersUpdated");
            return "List";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "PersistenceErrorOccured");
            return null;
        }
    }
    

    /**
     * @return the selectedCustomer
     */
    public Customers getSelectedCustomer() {
        return selectedCustomer;
    }

    /**
     * @param selectedCustomer the selectedCustomer to set
     */
    public void setSelectedCustomer(Customers selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
    }

    /**
     * @return the debug
     */
    public boolean isDebug() {
        return debug;
    }

    /**
     * @param debug the debug to set
     */
    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    @FacesConverter(forClass = Customers.class)
    public static class CustomersControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MyCustomersController controller = (MyCustomersController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "customersController");
            return controller.getCustomers(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Customers) {
                Customers o = (Customers) object;
                return getStringKey(o.getCustomerId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Customers.class.getName());
            }
        }

    }

}
