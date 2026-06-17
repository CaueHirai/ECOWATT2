/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import model.Banco;
import model.Equipamento;

/**
 *
 * @author aluno
 */
public class EquipamentoDAO {

    public int gravar(Equipamento obj) throws Exception {
        Banco banco;
        int qtde = 0;
        try {
            banco = new Banco();
            banco.comando = Banco.conexao.prepareStatement("Insert into equipamento(qtde,nome,horasDiarias,potencia,diasMes,imagem) values(?,?,?,?,?,?)");
            banco.comando.setString(2, obj.getNome());
            banco.comando.setInt(1, obj.getQtde());
            banco.comando.setLong(3, obj.getHorasDiarias());
            banco.comando.setDouble(4, obj.getPotencia());
            banco.comando.setDouble(5, obj.getDiasMes());
            banco.comando.setString(6, obj.getImagem());
            qtde = banco.comando.executeUpdate();
            Banco.conexao.close();
            return (qtde);
        } catch (Exception ex) {
            throw new Exception("Erro ao gravar : " + ex.getMessage());
        }
    }

    public int alterar(Equipamento obj) throws Exception {
        Banco banco;
        int qtde = 0;
        try {
            banco = new Banco();    
            banco.comando = Banco.conexao.prepareStatement("Update equipamento set nome=?,bloco=?,sala=?,horasDiarias=?,potencia=?,diasMes=?,imagem=? where codigo=?");
            banco.comando.setString(1, obj.getNome());
            banco.comando.setString(2, obj.getBloco());
            banco.comando.setString(3, obj.getSala());
            banco.comando.setObject(4, obj.getHorasDiarias());
            banco.comando.setDouble(5, obj.getPotencia());
            banco.comando.setDouble(6, obj.getDiasMes());
            banco.comando.setString(7, obj.getImagem());
            banco.comando.setDouble(8, obj.getCodigo());
            qtde = banco.comando.executeUpdate();
            Banco.conexao.close();
            return (qtde);
        } catch (Exception ex) {
            throw new Exception("Erro ao alterar : " + ex.getMessage());
        }
    }
    
    public Equipamento get(int codigo) throws Exception {
        Banco banco;
        Equipamento prod = null;
        try {
            banco = new Banco();
            banco.comando = Banco.conexao.prepareStatement(
            "Select qtde,nome,horasDiarias,potencia,diasMes,imagem,bloco,sala from equipamento where codigo = ?");
            banco.comando.setInt(1, codigo);
            banco.tabela = banco.comando.executeQuery();
            if(banco.tabela.next()){
                prod = new Equipamento();
                prod.setQtde(banco.tabela.getString(1));
                prod.setCodigo(codigo);
                prod.setNome(banco.tabela.getString(2));
                prod.setHorasDiarias(banco.tabela.getString(3));
                prod.setPotencia(banco.tabela.getInt(4));
                prod.setDiasMes(banco.tabela.getDouble(5));
                prod.setImagem(banco.tabela.getString(6));
                prod.setBloco(banco.tabela.getString(7));
                prod.setSala(banco.tabela.getString(8));
            }
            Banco.conexao.close();
            return (prod);
        } catch (Exception ex) {
            throw new Exception("Erro ao pegar informações do equipamento: " + ex.getMessage());
        }
    }

    public int remover(Equipamento obj) throws Exception {
        Banco banco;
        int qtde = 0;
        try {
            banco = new Banco();
            banco.comando = Banco.conexao.prepareStatement("Delete from equipamento where codigo=?");
            banco.comando.setInt(1, obj.getCodigo());
            qtde = banco.comando.executeUpdate();
            Banco.conexao.close();
            return (qtde);
        } catch (Exception ex) {
            throw new Exception("Erro ao alterar : " + ex.getMessage());
        }
    }

    public ResultSet listar() throws Exception {
        Banco banco;

        try {
            banco = new Banco();
            banco.comando = Banco.conexao.prepareStatement("Select qtde,nome,horasDiarias,potencia,diasMes,imagem from equipamento order by 2");
            banco.tabela = banco.comando.executeQuery();
            Banco.conexao.close();
            return (banco.tabela);
        } catch (Exception ex) {
            throw new Exception("Erro ao listar: " + ex.getMessage());
        }
    }
}
