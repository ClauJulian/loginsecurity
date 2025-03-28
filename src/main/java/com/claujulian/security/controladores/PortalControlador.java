package com.claujulian.security.controladores;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.claujulian.security.entidades.Usuario;
import com.claujulian.security.excepciones.MiException;
import com.claujulian.security.servicios.UsuarioServicio;

import jakarta.servlet.http.HttpSession;

@Controller // Esta clase es un Controlador
@RequestMapping("/") 
public class PortalControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/") // GET /
    public String index() {
        return "index.html";
    }

    @GetMapping("/registrar")
    public String registrar() {
        return "registro.html";
    }

     @PostMapping("/registro")
    public String registro(@RequestParam String nombre, @RequestParam String email, @RequestParam String password, @RequestParam String password2, ModelMap modelo) {

        try {
            usuarioServicio.registrar(nombre, email, password, password2);
            modelo.put("exito", "El usuario se registro correctamente");
            modelo.put("nombre", nombre);
            modelo.put("email", email);
           
            return "index.html";
        } catch (MiException e) {
            modelo.put("error", e.getMessage());
            return "registro.html";
        }
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo) {
        if (error != null) {
            modelo.put("error", "Usuario o Contraseña inválidos!");
        }
        return "login.html";
    }

   @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/inicio")
    public String inicio(HttpSession session) {
        Usuario logueado = (Usuario) session.getAttribute("usuariosession");
        if (logueado.getRol().toString().equals("ADMIN")) {
            return "redirect:/admin/dashboard";
        }
        return "inicio.html";
    }

    @GetMapping("/logout")
    public String logout() {
        return "login.html";
    }



    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/perfil")
    public String perfil(ModelMap modelo, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        modelo.put("usuario", usuario);
        
        return "usuario_modificar.html";
    }

     @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping("/perfil/{id}")
    public String actualizar( @PathVariable UUID id, @RequestParam String nombre, @RequestParam String email, 
        @RequestParam String password, @RequestParam String password2, ModelMap modelo) {
        
            try{
                usuarioServicio.actualizar(id, nombre, email, password, password2);
                modelo.put("exito", "El usuario fue actualizado correctamente.");
                return "inicio.html";
            } catch (MiException ex) {
                modelo.put("error", ex.getMessage());
                modelo.put("nombre", nombre);
                modelo.put("email", email);
                return "usuario_modificar.html";
            }
    }
}
