/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secure;

import entity.Customer;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import session.RoleFacade;
import session.UserRolesFacade;

/**
 *
 * @author Melnikov
 */
public class SecureLogic {
    private UserRolesFacade userRolesFacade;
    private RoleFacade roleFacade;

    public SecureLogic() {
        Context context;
        try {
            context = new InitialContext();
            this.userRolesFacade = (UserRolesFacade) context.lookup("java:module/UserRolesFacade");
            this.roleFacade = (RoleFacade) context.lookup("java:module/RoleFacade");
        } catch (NamingException ex) {
            Logger.getLogger(SecureLogic.class.getName()).log(Level.SEVERE, "Не удалось найти Бин", ex);
        }
    }
    
    public void addRoleToUser(UserRoles ur){
        if(ur.getRole().getName().equals("ADMIN")){
            userRolesFacade.create(ur);
            Role addNewRole = roleFacade.findRoleByName("USER");
            UserRoles addedNewRoles = new UserRoles(ur.getCustomer(),addNewRole);
            userRolesFacade.create(addedNewRoles);
        }else if(ur.getRole().getName().equals("USER")){
            userRolesFacade.create(ur);
        }
        
    }
    public void deleteRoleToUser(Customer user){
        List<UserRoles> listUserRoles = userRolesFacade.findByUser(user);
        int n = listUserRoles.size();
        for(int i=0; i<n; i++){
            userRolesFacade.remove(listUserRoles.get(i));
        }
    }

    public String getRole(Customer regUser) {
        List<UserRoles> listUserRoles = userRolesFacade.findByUser(regUser);
        int n = listUserRoles.size();
        for(int i = 0; i<n; i++){
            if("ADMIN".equals(listUserRoles.get(i).getRole().getName())){
                return listUserRoles.get(i).getRole().getName();
            }
        }
        for(int i = 0; i<n; i++){
            if("USER".equals(listUserRoles.get(i).getRole().getName())){
                return listUserRoles.get(i).getRole().getName();
            }
        }
        return null;
    }
}