/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.avaliacao.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logic.avaliacao.model.Filme;
import logic.avaliacao.data.database.ConexaoBancoDados;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class FilmeDAO extends ConexaoBancoDados {

    private Connection con = null;

    public void criarFilme(Filme filme) throws SQLException, ClassNotFoundException {
        if (filme != null) {
            StringBuilder sql = new StringBuilder();
            sql.append("\n INSERT INTO filme(");
            sql.append("\n id, nome, anolancamento, datahorainclusao, datahoraatualizacao)");
            sql.append("\n VALUES (?, ?, ?, ?, ?);");
            try {
                con = conectar();
                PreparedStatement stm = con.prepareStatement(sql.toString());
                stm.setInt(1, filme.getId());
                stm.setString(2, filme.getNome());
                stm.setString(3, filme.getAnoLancamento());
                stm.setTimestamp(4, filme.getDataHoraInclusao());
                stm.setTimestamp(5, filme.getDataHoraAtualizacao());
                stm.execute();
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
                throw ex;
            } finally {
                desconectar(con);
            }
        }
    }

    public void atualizarFilme(Filme filme) throws Exception {
        throw new Exception("falta implementar");
    }

    public Filme obterFilme(int id) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM filme WHERE id=?");
        Filme f = null;
        try {
            con = conectar();
            PreparedStatement stm = con.prepareStatement(sql.toString());
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                f = new Filme(rs.getInt("id"), rs.getString("nome"), rs.getString("anolancamento"), rs.getTimestamp("datahorainclusao"), rs.getTimestamp("datahoraatualizacao"));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            desconectar(con);
        }
        return f;
    }

    public List<Filme> listarFilme() {
       throw new UnsupportedOperationException();
    }

public void removerFilme(Filme filme) throws SQLException, ClassNotFoundException {
        if (filme != null) {
            StringBuilder sql = new StringBuilder();
            sql.append("\n DELETE FROM filme");
            sql.append("\n WHERE id = ?");
            try {
                con = conectar();
                PreparedStatement stm = con.prepareStatement(sql.toString());
                stm.setInt(1, filme.getId());
                stm.execute();
            } catch (SQLException | ClassNotFoundException ex) {
                throw ex;
            } finally {
                desconectar(con);
            }
        }
    }

}
