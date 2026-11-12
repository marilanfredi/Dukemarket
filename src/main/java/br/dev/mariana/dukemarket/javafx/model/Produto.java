package br.dev.mariana.dukemarket.javafx.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sesi2dib
 */
public class Produto {

    int id;
    String codBarras;
    String descricao;
    double Qtd;
    double valorCompra;
    double valorVenda;
    Calendar dataCadastro;

    public Produto() {
    }

    public Produto(int id, String codBarras, String descricao, double Qtd, double valorCompra, double valorVendas, Calendar dataCadastro) {
        this.id = id;
        this.codBarras = codBarras;
        this.descricao = descricao;
        this.Qtd = Qtd;
        this.valorCompra = valorCompra;
        this.valorVenda = valorVendas;
        this.dataCadastro = dataCadastro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(String codBarras) {
        this.codBarras = codBarras;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getQtd() {
        return Qtd;
    }

    public void setQtd(double Qtd) {
        this.Qtd = Qtd;
    }

    public double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(double valorVendas) {
        this.valorVenda = valorVendas;
    }

    public String getDataCadastro() {
        if (this.dataCadastro != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            return sdf.format(this.dataCadastro.getTime());
        } else {
            return "";
        }
    }

    public void setDataCadastro(String strDataCadastro) {
        try {
            this.dataCadastro = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            this.dataCadastro.setTime(sdf.parse(strDataCadastro));
        } catch (ParseException ex) {
            Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        return String.format("%3d | %15s | %-30s | %10.2f | %10.2f | %10s",
                this.id,
                this.codBarras,
                this.descricao,
                this.Qtd,
                this.valorCompra,
                this.valorVenda,
                this.getDataCadastro());
    }

}
