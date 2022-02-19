package com.example.crudcomspring.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao implements ConexaoJDBC{

    public static Connection conexao(){
        ConexaoJDBC conexao = new Conexao();
        return conexao.criarConexao();
    }

    @Override
    public Connection criarConexao() {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/alunosBD";
            String usuario = "postgres";
            String senha = "admin";

            return DriverManager.getConnection(url, usuario, senha);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
