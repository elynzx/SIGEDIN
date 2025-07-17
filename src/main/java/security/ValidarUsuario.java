/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package security;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;


/**
 *
 * @author rpasc
 */
public class ValidarUsuario {

    public static void validarLogin(String username, String password) {
        Preconditions.checkNotNull(username, "Debe ingresar el usuario");
        Preconditions.checkNotNull(password, "Debe ingresar la contraseña");
        
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            throw new  IllegalArgumentException("Debe ingresar su usuario y contraseña");
        
        } 
    }

}
