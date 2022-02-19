package com.example.crudcomspring.dao;

import com.example.crudcomspring.model.Aluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AlunoDAO {
    Connection con;


    public AlunoDAO(){
        con = Conexao.conexao();
    }


    public List<Aluno> buscarAlunos() {
        try {
            //comando sql
            String sql = "select * from tb_alunos";
            PreparedStatement ps = con.prepareStatement(sql);
            //ResultSet, representa o resultado do comando SQL
            ResultSet rs = ps.executeQuery();
            //cria uma lista de pessoas para retornar
            List<Aluno> alunos = new ArrayList<>();
            //laço para buscar todas as pessoas do banco
            while (rs.next()) {
                Aluno a = new Aluno();
                a.setId(rs.getLong("id"));
                a.setNome(rs.getString("nome"));
                a.setMatricula(rs.getString("matricula"));
                //add pessoa na lista
                alunos.add(a);
                Logger.getLogger("Alunos: " + a.getId() + " - " + a.getNome() +" - " + a.getMatricula()).log(Level.WARNING, null, a);
            }
            //retorna a lista de pessoas
            return alunos;
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //REMOVEEEE AQUI -------
    public boolean remove(Long id) {
        try {
            //criar um objeto Connection para receber a conexão
            Connection con = Conexao.conexao();
            //comando sql
            String sql = "delete from tb_alunos where id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            //referênciar o parâmetro do método para a ?
            ps.setLong(1, id);
            if(ps.executeUpdate()==1)
                return true;

        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean save(Aluno aluno) {
        try {
            //comando sql
            String sql = "insert into tb_alunos (nome, matricula) values (?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            //referênciar o parâmetro do método para a ?
            ps.setString(1, aluno.getNome());
            ps.setString(2, aluno.getMatricula());



            if(ps.executeUpdate()==1)
                return true;

        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean update(Aluno aluno) {
        try {
            //comando sql
            String sql = "update tb_alunos set nome=?, matricula=?  where id=? ";
            PreparedStatement ps = con.prepareStatement(sql);
            //referênciar o parâmetro do método para a ?
            ps.setString(1, aluno.getNome());
            ps.setString(2, aluno.getMatricula());
            ps.setLong(3, aluno.getId());


            if (ps.executeUpdate()==1)
                return true;

        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Aluno buscarAluno(Long id) {
        try {
            //comando sql
            String sql = "select * from tb_alunos where id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            //referênciar o parâmetro do método para a ?
            ps.setLong(1, id);
            //ResultSet, representa o resultado do comando SQL
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Aluno a = new Aluno();
                a.setId(rs.getLong("id"));
                a.setNome(rs.getString("nome"));
                a.setMatricula(rs.getString("matricula"));
                return a;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
