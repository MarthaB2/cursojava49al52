package cursose;


import dao.DAOPersonaImpl;
import interfaces.DAOPersona;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CursoSE {
    //llamado de los valores
    //50

    public String solicitarValores(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese un nombre en la tabla de persona para la base de datos:");
        String nombre = sc. next();
        return nombre;
    }


    //50

    public void regitrarBD(String valor) {
        // JDBC driver nombre y base de datos URL;
        final String JDBC_DRIVER = "org.postgresql.Driver";//com.mysql.jdbc.Driver ( si no conectamos a otra tipo  es este por ejemplo)  // con este vamos a poder distinguir a que tipo de base de dato nos vamos a conectar
        final String DB_URL = "jdbc:postgresql://localhost:5432/ejemplo";//"jdbc:mysql://localhost/ejemplo";

        //base de datos credenciales
        final String USER = "postgres";
        final String PASS = "password";

        try (Connection conexion = (Connection) DriverManager.getConnection(DB_URL, USER, PASS)) {
            Class.forName(JDBC_DRIVER);

            PreparedStatement st = conexion.prepareStatement("INSERT INTO PERSONA(id,nombre) values(?,?)");
            st.setInt(1,7);
            st.setString(2, valor); //si tubieramos que ingresa rmas seria agregar eso con otra linea de codigo

            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            e.getMessage();
        }
    }



    //51

    public List<Persona> listar() {

        List<Persona> lista = null;
        // JDBC driver nombre y base de datos URL;
        final String JDBC_DRIVER = "org.postgresql.Driver";//com.mysql.jdbc.Driver ( si no conectamos a otra tipo  es este por ejemplo)  // con este vamos a poder distinguir a que tipo de base de dato nos vamos a conectar
        final String DB_URL = "jdbc:postgresql://localhost:5432/ejemplo";//"jdbc:mysql://localhost/ejemplo";

        //base de datos credenciales
        final String USER = "postgres";
        final String PASS = "password";

        try (Connection conexion = (Connection) DriverManager.getConnection(DB_URL, USER, PASS)) {

            Class.forName(JDBC_DRIVER);

            PreparedStatement st = conexion.prepareStatement("SELECT * FROM PERSONA");

            //List<Persona> lista = new ArrayList<>();
            //pero lo haremos algo mas global en ese metodo
            lista = new ArrayList<Persona>();
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
            e.getMessage();
        }
        return lista;
    }

//


    public static void main(String... mitocode ) throws SQLException {
        // 49 Conexion a Base de Datos
        System.out.println("---------------------49 Conexion a Base de Datos---------------------");

        //final signfiica que no va cambiar
        // JDBC driver nombre y base de datos URL;

        final String JDBC_DRIVER = "org.postgresql.Driver";//com.mysql.jdbc.Driver ( si no conectamos a otra tipo  es este por ejemplo)  // con este vamos a poder distinguir a que tipo de base de dato nos vamos a conectar
        final String DB_URL ="jdbc:postgresql://localhost:5432/ejemplo";//"jdbc:mysql://localhost/ejemplo";

        //base de datos credenciales
        final String USER = "postgres";
        final String PASS= "password";//"123" esto era para el ejemplo del video



        //forma 1 (aun no lo ejecute)
        /*
        //variable adicional
        System.out.println("------------------FORMA 1------------------");
        Connection conexion = null;
        try{
            Class.forName(JDBC_DRIVER);
            conexion = (Connection) DriverManager.getConnection(DB_URL,USER,PASS); //es para la conexion a la bd
            //ahora haremos una simple insercion
            PreparedStatement st =  conexion.prepareStatement("INSERT INTO PERSONA(id,nombre) values ('4','Mitocode Tutos')");
            //ahora vamos a ejecutarlo para que  la bd pueda registrar
            st.executeUpdate();
            st.close();

        }catch(Exception e){
            e.getMessage();
        }finally { //deberiamos liberar los recursos
            if(conexion != null){
                if(!conexion.isClosed()){
                    conexion.close();
                }
            }
        }


        */
        //forma 2
        /*
        try(Connection conexion = (Connection) DriverManager.getConnection(DB_URL,USER,PASS)) {
            Class.forName(JDBC_DRIVER);

            PreparedStatement st =  conexion.prepareStatement("INSERT INTO PERSONA(id,nombre) values ('5','Isabela')");

            st.executeUpdate();
            st.close();
        }catch(Exception e){
            e.getMessage();
        }
         */


        //50 Insercion a base de datos con parametros

        System.out.println("---------------------50 Insercion a base de datos con parametros---------------------");
        /*
        CursoSE curso = new CursoSE();
        String valor = curso.solicitarValores();
        if(valor != null){
            curso.regitrarBD(valor);
        }
        */

        //51 Listar de Base de Datos
        System.out.println("---------------------51 Listar de Base de Datos---------------------");

        CursoSE curso = new CursoSE();
/*
        String valor = curso.solicitarValores();
        if(valor != null){
            curso.regitrarBD(valor);
        }*/

        //FORMA 1
        for(Persona per : curso.listar()){
            System.out.println(per.getId()+"-"+per.getNombre());
        }

        //FORMA 2
        List<Persona> lisBD = curso.listar();
        for(Persona per : lisBD){
            System.out.println(per.getId()+"-"+per.getNombre());
        }



        //52 Patron DAO
        System.out.println("------------------52 Patron DAO------------------");
        //esta seria un mejor manera de poder usar la bd
        //para ello eliminaremos todo lo anterior y nos quedamos con los paquetes dao e interfaces, y la clase persona

        //para reegistrar
        /*
        Persona per = new Persona();
        per.setNombre("Mitocode");

        try{
            DAOPersona dao = new DAOPersonaImpl();
            dao.registrar(per);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
*/

        //para modificar
        /*
        Persona per = new Persona();
        per.setId(4);//ejemplo 11 no se que seria
        per.setNombre("Jaime");

        try{
            DAOPersona dao = new DAOPersonaImpl();
            dao.modificar(per);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
*/
        /*
        per.setId(4);//ejemplo 11 no se que seria
        per.setNombre("Jaime");

        try{
            DAOPersona dao = new DAOPersonaImpl();
            dao.eliminar(per);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        per.setId(4);//ejemplo 11 no se que seria
        per.setNombre("Jaime");

         */


        try{
            DAOPersona dao = new DAOPersonaImpl();
            for(Persona p : dao.listar()){
                System.out.println(p.getNombre());
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }


    }
}
