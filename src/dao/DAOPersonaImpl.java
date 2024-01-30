package dao;

import cursose.Persona;
import interfaces.DAOPersona;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAOPersonaImpl extends Conexion implements DAOPersona {
    @Override
    public void registrar(Persona per) throws Exception {
        try {
            this.conectar();

            PreparedStatement st = this.conexion.prepareStatement("INSERT INTO PERSONAPRUEBA(nombre) VALUES(?)");
            st.setString(1, per.getNombre()); //si tubieramos que ingresa rmas seria agregar eso con otra linea de codigo
            st.executeUpdate();

        } catch (Exception e) {
            throw e;
        }finally {
            this.cerrar();
        }
    }

    @Override
    public void modificar(Persona per) throws Exception {
        try {
            this.conectar();

            PreparedStatement st = this.conexion.prepareStatement("UPDATE PERSONAPRUEBA set nombre = ? where id = ?");
            st.setString(1,per.getNombre());
            st.setInt(2, per.getId()); //si tubieramos que ingresa rmas seria agregar eso con otra linea de codigo
            st.executeUpdate();

        } catch (Exception e) {
            throw e;
        }finally {
            this.cerrar();
        }
    }

    @Override
    public void eliminar(Persona per) throws Exception {
        try {
            this.conectar();

            PreparedStatement st = this.conexion.prepareStatement("delete from PERSONAPRUEBA where id = ?");
            st.setInt(1, per.getId()); //si tubieramos que ingresa rmas seria agregar eso con otra linea de codigo
            st.executeUpdate();

        } catch (Exception e) {
            throw e;
        }finally {
            this.cerrar();
        }
    }

    @Override
    public List<Persona> listar() throws Exception {
        List<Persona> lista = null;
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("select * from PERSONAPRUEBA");

            lista = new ArrayList<>();
            ResultSet rs = st.executeQuery();//va poder contener el contenido de la bd que vamos a traer
            //lo que vamos a hacer es guardar los datos en un objeto
            while(rs.next()){
                Persona per =new Persona();
                per.setId(rs.getInt("id"));
                per.setNombre(rs.getString("nombre"));
                lista.add(per);
            }
            rs.close(); //aun que no es necesario porque por teoria con solo st close ya cierro ese.
            st.close();

        } catch (Exception e) {
            throw e;
        }finally {
            this.cerrar();
        }
        return lista;
    }
}
