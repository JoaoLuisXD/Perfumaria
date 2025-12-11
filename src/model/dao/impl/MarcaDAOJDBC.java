package src.model.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import src.db.DB;
import src.exceptions.CampoObrigatorioException;
import src.exceptions.DBException;
import src.exceptions.DBIntegrityException;
import src.exceptions.EntidadeJaExisteException;
import src.exceptions.EntidadeNaoEncontradaException;
import src.model.dao.MarcaDAO;
import src.model.entities.Marca;

public class MarcaDAOJDBC implements MarcaDAO {

    private Connection conn;

    public MarcaDAOJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Marca obj) throws EntidadeJaExisteException, DBException {
        try {
            PreparedStatement st = conn.prepareStatement(
                "INSERT INTO marca (cnpj, nome, origem, ano_criacao) VALUES (?, ?, ?, ?)"
            );

            st.setString(1, obj.getCnpj());
            st.setString(2, obj.getNome());
            st.setString(3, obj.getOrigem());
            st.setInt(4, obj.getAnoCriacao());

            st.executeUpdate();
            DB.closeStatement(st);
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                throw new EntidadeJaExisteException("CNPJ já cadastrado.");
            }
            throw new DBException(e.getMessage()); 
        }
    }

    @Override
    public void update(Marca obj) {
        try {
            PreparedStatement st = conn.prepareStatement(
                "UPDATE marca SET nome=?, origem=?, ano_criacao=? WHERE cnpj=?"
            );

            st.setString(1, obj.getNome());
            st.setString(2, obj.getOrigem());
            st.setInt(3, obj.getAnoCriacao());
            st.setString(4, obj.getCnpj());

            st.executeUpdate();
            DB.closeStatement(st);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteByCnpj(String cnpj) throws DBIntegrityException{
        try {
            PreparedStatement st = conn.prepareStatement(
                "DELETE FROM marca WHERE cnpj=?"
            );

            st.setString(1, cnpj);
            st.executeUpdate();
            DB.closeStatement(st);

        } catch (SQLException e) {
            throw new DBIntegrityException("Erro ao deletar");
        }
    }

    @Override
    public Marca findByCnpj(String cnpj) throws EntidadeNaoEncontradaException, DBException, CampoObrigatorioException {
    try {
        PreparedStatement st = conn.prepareStatement(
            "SELECT * FROM marca WHERE cnpj = ?"
        );
        st.setString(1, cnpj);

        ResultSet rs = st.executeQuery();

        // Se não encontrou nenhum registro no banco
        if (!rs.next()) {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
            throw new EntidadeNaoEncontradaException("Marca com CNPJ " + cnpj + " não encontrada.");
        }

        // Se encontrou, instancia normalmente
        Marca obj = instantiateMarca(rs);

        DB.closeResultSet(rs);
        DB.closeStatement(st);
        return obj;

    } catch (SQLException e) {
        throw new DBException("Erro ao buscar marca");
    }
}


    @Override
    public List<Marca> findAll() throws CampoObrigatorioException{
        try {
            PreparedStatement st = conn.prepareStatement(
                "SELECT * FROM marca"
            );

            ResultSet rs = st.executeQuery();
            List<Marca> list = new ArrayList<>();

            while (rs.next()) {
                list.add(instantiateMarca(rs));
            }

            DB.closeResultSet(rs);
            DB.closeStatement(st);
            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Marca instantiateMarca(ResultSet rs) throws SQLException, CampoObrigatorioException{
        Marca obj = new Marca();
        obj.setCnpj(rs.getString("cnpj"));
        obj.setNome(rs.getString("nome"));
        obj.setOrigem(rs.getString("origem"));
        obj.setAnoCriacao(rs.getInt("ano_criacao"));
        return obj;
    }
}
