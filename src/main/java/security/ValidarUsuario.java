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
        Preconditions.checkNotNull(username, "El usuario no puede ser nulo");
        Preconditions.checkNotNull(password, "La contraseña no puede ser nula");
        
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            throw new  IllegalArgumentException("Debe ingresa su usuario y contraseña");
        
        } 
    }

}
