package br.dev.mariana.dukemarket.javafx.DAO;

import br.dev.mariana.dukemarket.javafx.model.Produto;
import connection.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProdutoDAO {

    private static final String SQL_INSERT = "INSERT INTO produto (codBarras, descricao, qtd, valorCompra, valorVenda) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_ALL = "SELECT * FROM produto";
    private static final String SQL_SELECT_ID = "SELECT * FROM produto WHERE id = ?";
    private static final String SQL_UPDATE = "UPDATE produto SET codBarras = ?, descricao = ?, qtd = ?, valorCompra = ?, valorVenda = ? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM produto WHERE id = ?";

    public void create(Produto p) {
        Connection conn = (Connection) MySQLConnection.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, p.getCodBarras());
            stmt.setString(2, p.getDescricao());
            stmt.setDouble(3, p.getQtd());
            stmt.setDouble(4, p.getValorCompra());
            stmt.setDouble(5, p.getValorVenda());

            int auxRetorno = stmt.executeUpdate();

            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.INFO, null,
                    "Inclusao: " + auxRetorno);
        } catch (SQLException sQLException) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null,
                    sQLException);
        } finally {
            MySQLConnection.closeConnection(conn, stmt);
        }
    }

    /**
     * Retorna todos os registros da tabela produto
     * @return List<Produto>
     */
    public List<Produto> getResults() {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> listaProdutos = new ArrayList<>();

        try {
            stmt = conn.prepareStatement(SQL_SELECT_ALL);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Produto p = new Produto();           
                p.setId(rs.getInt("id"));
                p.setCodBarras(rs.getString("codBarras"));
                p.setDescricao(rs.getString("descricao"));
                p.setQtd(rs.getDouble("qtd"));
                p.setValorCompra(rs.getDouble("valorCompra"));
                p.setValorVenda(rs.getDouble("valorVenda"));
                p.setDataCadastro(rs.getString("dataCadastro"));
                listaProdutos.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQLConnection.closeConnection(conn, stmt, rs);
        }
        return listaProdutos;
    }

    public Produto getResultById(int id) {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Produto p = null;
        
        try {
            stmt = conn.prepareStatement(SQL_SELECT_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                p = new Produto();
                p.setId(rs.getInt("id"));
                p.setCodBarras(rs.getString("codBarras"));
                p.setDescricao(rs.getString("descricao"));
                p.setQtd(rs.getDouble("qtd"));
                p.setValorCompra(rs.getDouble("valorCompra"));
                p.setValorVenda(rs.getDouble("valorVenda"));
                p.setDataCadastro(rs.getString("dataCadastro"));
            }
        } catch (SQLException sqlException) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, sqlException);
        } finally {
            MySQLConnection.closeConnection(conn, stmt, rs);
        }
        return p;
    }
    
    public void update(Produto p) {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, p.getCodBarras());
            stmt.setString(2, p.getDescricao());
            stmt.setDouble(3, p.getQtd());
            stmt.setDouble(4, p.getValorCompra());
            stmt.setDouble(5, p.getValorVenda());
            stmt.setInt(6, p.getId());

            int auxRetorno = stmt.executeUpdate();

            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.INFO, null,
                    "Update: " + auxRetorno);

        } catch (SQLException sqlException) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, sqlException);
        } finally {
            MySQLConnection.closeConnection(conn, stmt);
        }
    }

    public void delete(int id) {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, id);

            int auxRetorno = stmt.executeUpdate();

            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.INFO, null,
                    "Delete: " + auxRetorno);

        } catch (SQLException sqlException) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, sqlException);
        } finally {
            MySQLConnection.closeConnection(conn, stmt);
        }
    }

}
