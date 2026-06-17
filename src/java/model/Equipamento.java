/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.Duration;

/**
 *
 * @author aluno
 */
public class Equipamento {
    private int codigo, qtde;
    private String nome, bloco, sala, imagem;
    private int horasDiarias;
    private double potencia, diasMes;
    
    
    
    public Double getGasto(){
        return ((getPotencia() * getDiasMes() / 1000)*0.90);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public void setCodigo(String codigo) {
        setCodigo(Integer.parseInt(codigo));
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getBloco() {
        return bloco;
    }

    public void setBloco(String bloco) {
        this.bloco = bloco;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public int getHorasDiarias() {
        return horasDiarias;
    }

    public void setHorasDiarias(int horasDiarias) {
        this.horasDiarias = horasDiarias;
    }
    public void setHorasDiarias(String horasDiarias) {
        this.horasDiarias = Integer.parseInt(horasDiarias);
    }

    public Double getDiasMes() {
        return diasMes;
    }

    public void setDiasMes(Double diasMes) {
        this.diasMes = diasMes;
    }
    public void setDiasMes(String diasMes) {
        this.diasMes = Double.parseDouble(diasMes);
    }

    public double getPotencia() {
        return potencia;
    }

    public void setPotencia(double potencia) {
        this.potencia = potencia;
    }
    public void setPotencia(String potencia) {
        this.potencia = Double.parseDouble(potencia);
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }
    public void setQtde(String qtde) {
        this.qtde = Integer.parseInt(qtde);
    }
}
