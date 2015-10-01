/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.avaliacao.data.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author LogicPC
 */
public class ConexaoBancoDados {

    final String URL = "jdbc:postgresql://localhost:5432/avaliacao";
    final String USUARIO = "postgres";
    final String SENHA = "postgres";
    final String DRIVER = "org.postgresql.Driver";

    public Connection conectar() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        return (Connection) DriverManager.getConnection(URL, USUARIO, SENHA);
    }

    public void desconectar(Connection con) throws SQLException {
        if (con != null) {
            con.close();
        }
    }
}
