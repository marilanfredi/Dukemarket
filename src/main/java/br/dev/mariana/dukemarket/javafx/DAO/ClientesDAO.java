package br.dev.mariana.dukemarket.javafx.DAO;


import br.dev.mariana.dukemarket.javafx.model.Clientes;
import connection.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientesDAO {
    
   private static final String SQL_INSERT = "INSERT INTO clientes (Nome, CPF, Endereco, Cidade, UF) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_ALL = "SELECT * FROM clientes";
    private static final String SQL_SELECT_ID = "SELECT * FROM clientes WHERE id = ?";
    private static final String SQL_UPDATE = "UPDATE clientes SET Nome = ?, CPF = ?, Endereco = ?, Cidade = ?, UF = ? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM clientes WHERE id = ?";

    public void create(Clientes p) {
        Connection conn = (Connection) MySQLConnection.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getCPF());
            stmt.setString(3, p.getEndereco());
            stmt.setString(4, p.getCidade());
            stmt.setString(5, p.getUF());

            int auxRetorno = stmt.executeUpdate();

            Logger.getLogger(ClientesDAO.class.getName()).log(Level.INFO, null,
                    "Inclusao: " + auxRetorno);
        } catch (SQLException sQLException) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null,
                    sQLException);
        } finally {
            MySQLConnection.closeConnection(conn, stmt);
        }
    }

    /**
     * Retorna todos os registros da tabela produto
     * @return List<Produto>
     */
    public List<Clientes> getResults() {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Clientes> listaClientes = new ArrayList<>();

        try {
            stmt = conn.prepareStatement(SQL_SELECT_ALL);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Clientes p = new Clientes();           
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("codNome"));
                p.setCPF(rs.getString("CPF"));
                p.setEndereco(rs.getString("Endereco"));
                p.setCidade(rs.getString("Cidade"));
                p.setUF(rs.getString("UF"));
                p.setDataCadastro(rs.getString("dataCadastro"));
                listaClientes.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            MySQLConnection.closeConnection(conn, stmt, rs);
        }
        return listaClientes;
    }

    public Clientes getResultById(int id) {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Clientes p = null;
        
        try {
            stmt = conn.prepareStatement(SQL_SELECT_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                p = new Clientes();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("Nome"));
                p.setCPF(rs.getString("CPF"));
                p.setEndereco(rs.getString("Endereco"));
                p.setCidade(rs.getString("Cidade"));
                p.setUF(rs.getString("UF"));
                p.setDataCadastro(rs.getString("dataCadastro"));
            }
        } catch (SQLException sqlException) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, sqlException);
        } finally {
            MySQLConnection.closeConnection(conn, stmt, rs);
        }
        return p;
    }
    
    public void update(Clientes p) {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getCPF());
            stmt.setString(3, p.getEndereco());
            stmt.setString(4, p.getCidade());
            stmt.setString(5, p.getUF());
            stmt.setInt(6, p.getId());

            int auxRetorno = stmt.executeUpdate();

            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.INFO, null,
                    "Update: " + auxRetorno);

        } catch (SQLException sqlException) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, sqlException);
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

            Logger.getLogger(ClientesDAO.class.getName()).log(Level.INFO, null,
                    "Delete: " + auxRetorno);

        } catch (SQLException sqlException) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, sqlException);
        } finally {
            MySQLConnection.closeConnection(conn, stmt);
        }
    }
}



