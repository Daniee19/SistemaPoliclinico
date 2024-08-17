package com.practicandoweb.sistemaclinica.modelo.daoclassinterface.daoimpl;

import com.practicandoweb.sistemaclinica.controlador.Conexion;
import com.practicandoweb.sistemaclinica.modelo.Usuario;
import com.practicandoweb.sistemaclinica.modelo.clasesMetodos.Aes;
import com.practicandoweb.sistemaclinica.modelo.daoclassinterface.UsuarioDao;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JOptionPane;

public class UsuarioDaoImpl implements UsuarioDao {

    //Por medio del Dao definimos con que tipo vamos a estar trabajando para hacer el CRUD
    //Puse UsuarioDaoImpl para que de esta manera no vuelva a traer los metodos del Dao ya que si esta en UsuarioDao traerá lo que extiende pero si esta aqui traera los metodos que tiene su padre que es UsuarioDao
//    Dao<Usuario> usuario = new UsuarioDaoImpl();
    //De manera global para todos
    List<Usuario> usuarios;
    Usuario usuario;

//no se muestra pero es tipo List<Usuario> por medio del controlador
//    LoginFrame lf;
    public UsuarioDaoImpl() {
//        LoginFrame lf = new LoginFrame();
//        this.lf = lf;
    }

    @Override
    public List listarTodo() {
        //Se puede llamar y empezar con el metodo al llamar a este metodo "listaTodo()"
        usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt(1));
                usuario.setNombreUsuario(rs.getString(2));
                usuario.setRol(rs.getString(4));

                //Agregamos a la lista el primer registro de la tabla Usuario (de la bd)
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return usuarios;
    }

    //FALTA MEJORAR PARA HACER UN LOGIN 
    @Override
    public Object leerPorId(int id) {

        usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario Where id='?'";

        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt(1));
                usuario.setNombreUsuario(rs.getString(2));
                usuario.setRol(rs.getString(4));

                //Agregamos a la lista el primer registro de la tabla Usuario (de la bd)
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar Usuario");
        }
        return null;
    }

    @Override
    public void registrar(Object registrar) {
        usuario = (Usuario) registrar;

        String sql = "INSERT INTO usuario(nombreUsuario, contrasenia, rol) values (?,?,?)";
        /**
         * Dentro del parámetro del try resources, se debe de mencionar y
         * asignar el valor de las clases que implementan Autocloseable, para
         * que al finalizar el método se cierre la conexión con la base de
         * datos.
         */
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(2, usuario.getNombreUsuario());
            ps.setBlob(3, usuario.getContrasenia());
            ps.setString(4, usuario.getRol());
            int rs = ps.executeUpdate();

            if (rs > 0) {
                JOptionPane.showMessageDialog(null, "Usuario agregado con éxito");
            } else {
                JOptionPane.showMessageDialog(null, "No se agrego el usuario");
            }

        } catch (SQLException e) {
            System.out.println("Error al agregar Usuario");
        }

    }

    @Override
    public void editar(Object editar) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(int eliminar) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void register(Usuario user, String contrasenia) {
        usuario = (Usuario) user;

        //Necesito una manera de poder traer los textos de los inputs
        String sql = "INSERT INTO usuario(nombreUsuario, contrasenia, rol) values (?,?,?)";

        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1, usuario.getNombreUsuario());

            /**
             * <<Obtienes la contraseña del JPassword.>> la cual se obtiene a
             * traves del parametro pasra establecer una relacion
             */
            byte[] contra = contrasenia.getBytes();
            String scontra = new String(contra);
            /**
             * Voy a encriptarlo.
             */
            String contraseniaEncriptada = Aes.encriptar(scontra);
            //Me retorna un codigo hexadecimal eso quiere decir que funciona bien
//            String base64Salt = Base64.getEncoder().encodeToString(valor);contraseniaEncriptada
//            ps.setBytes(2, contra);
            /**
             * Convertimos la contrasena de String a byte[] para poder
             * convertirla posteriormente a tipo Blob
             */
            byte[] contraseniaByte = contraseniaEncriptada.getBytes("UTF-8");
            /**
             * Por medio del InputStream pasamos la contraseña de tipo byte[] a
             * tipo Blob
             */
            InputStream contraseniaBlob = new ByteArrayInputStream(contraseniaByte);
            ps.setBlob(2, contraseniaBlob);
            ps.setString(3, usuario.getRol());
            int rs = ps.executeUpdate();

            if (rs > 0) {
                JOptionPane.showMessageDialog(null, "Usuario registrado con éxito");
            } else {
                JOptionPane.showMessageDialog(null, "No se registro el usuario");
            }

        } catch (SQLException e) {
            System.out.println("Error al agregar usuario");
            System.out.println(e.getMessage());

        } catch (InvalidKeyException ex) {
            Logger.getLogger(UsuarioDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuarioDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(UsuarioDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(UsuarioDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(UsuarioDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(UsuarioDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
