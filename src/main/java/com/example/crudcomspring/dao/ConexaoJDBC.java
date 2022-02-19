package com.example.crudcomspring.dao;

import java.sql.Connection;

public interface ConexaoJDBC {
    public Connection criarConexao();
}
