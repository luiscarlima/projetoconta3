package com.company;

import java.util.Date;

public class Movimentacao {
    private String data;
    private char tipoMovimento;
    private double valor;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public char getTipoMovimento() {
        return tipoMovimento;
    }

    public void setTipoMovimento(char tipoMovimento) {
        this.tipoMovimento = tipoMovimento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Movimentacao{" +
                "data=" + data +
                ", tipoMovimento=" + tipoMovimento +
                ", valor=" + valor +
                '}';
    }
}
