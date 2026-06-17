/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.*;

/**
 *
 * @author aluno
 */
public class Banco {
    public static Connection conexao = null;
    public PreparedStatement comando = null;
    public ResultSet tabela = null;

    public Banco() throws Exception {
        try {
            Class.forName("org.postgresql.Driver");
            if ((conexao == null) || (conexao.isClosed())) {
                conexao = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/ECOWATT", "postgres", "ifsp");
            }
        } catch (Exception ex) {
            throw new Exception("Erro de conexao:" + ex.getMessage());
        }

    }
}

/*create table equipamento(
codigo serial primary key,
nome varchar(50) not null,
bloco varchar(25) default('Variadas'),
sala varchar(25) default('Variadas'),
horasDiarias BIGINT default(16),
potencia float not null,
diasMes int,
qtde int,
imagem varchar(50)
)*/
