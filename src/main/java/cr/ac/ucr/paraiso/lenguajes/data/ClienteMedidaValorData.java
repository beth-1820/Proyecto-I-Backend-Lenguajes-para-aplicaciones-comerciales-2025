package cr.ac.ucr.paraiso.lenguajes.data;

import cr.ac.ucr.paraiso.lenguajes.domain.ClienteMedidaValor;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ClienteMedidaValorData {

    @Autowired
    private JdbcTemplate jdbc;

    public List<ClienteMedidaValor> findByCliente(int idCliente) {
        String sql = "SELECT idCliente, codMedida, valor FROM ClienteMedidaValor WHERE idCliente = ?";
        return jdbc.query(sql, (rs, r) -> map(rs), idCliente);
    }

    public void upsertValores(int idCliente, List<ClienteMedidaValor> valores) {
        jdbc.update("DELETE FROM ClienteMedidaValor WHERE idCliente = ?", idCliente);
        String insert = "INSERT INTO ClienteMedidaValor(idCliente, codMedida, valor) VALUES (?,?,?)";
        valores.forEach(v -> jdbc.update(insert, idCliente, v.getCodMedida(), v.getValor()));
    }

    private ClienteMedidaValor map(java.sql.ResultSet rs) throws java.sql.SQLException {
        return new ClienteMedidaValor(
            rs.getInt("idCliente"),
            rs.getInt("codMedida"),
            (Double) rs.getObject("valor")
        );
    }
}