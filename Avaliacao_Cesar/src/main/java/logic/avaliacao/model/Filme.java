/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.avaliacao.model;

import java.util.List;
import java.sql.Timestamp;

public class Filme {

    private int id;
    private String nome;
    private String anoLancamento;
    private Timestamp dataHoraInclusao;
    private Timestamp dataHoraAtualizacao;

    public Filme(int id, String nome, String anoLancamento, Timestamp dataHoraInclusao, Timestamp dataHoraAtualizacao) {
        this.id = id;
        this.nome = nome;
        this.anoLancamento = anoLancamento;
        this.dataHoraInclusao = dataHoraInclusao;
        this.dataHoraAtualizacao = dataHoraAtualizacao;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(String anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public Timestamp getDataHoraInclusao() {
        return dataHoraInclusao;
    }

    public void setDataHoraInclusao(Timestamp dataHoraInclusao) {
        this.dataHoraInclusao = dataHoraInclusao;
    }

    public Timestamp getDataHoraAtualizacao() {
        return dataHoraAtualizacao;
    }

    public void setDataHoraAtualizacao(Timestamp dataHoraAtualizacao) {
        this.dataHoraAtualizacao = dataHoraAtualizacao;
    }

}
