package cr.ac.ucr.paraiso.lenguajes.data;

import cr.ac.ucr.paraiso.lenguajes.domain.ClienteMedidaValor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ClienteMedidaValorData {

    @Autowired
    private JdbcTemplate jdbc;

    public List<ClienteMedidaValor> findByCliente(int idCliente) {
        String sql = "SELECT idCliente, codMedida, valor "
                   + "FROM ClienteMedidaValor WHERE idCliente = ?";
        return jdbc.query(sql, (rs, rowNum) -> map(rs), idCliente);
    }

    public void upsertValores(int idCliente, List<ClienteMedidaValor> valores) {
        jdbc.update("DELETE FROM ClienteMedidaValor WHERE idCliente = ?", idCliente);

        String insert = "INSERT INTO ClienteMedidaValor(idCliente, codMedida, valor) "
                      + "VALUES (?,?,?)";

        valores.stream()
            .filter(v -> v.getValor() != null)
            .forEach(v -> jdbc.update(insert,
                                      idCliente,
                                      v.getCodMedida(),
                                      v.getValor()));
    }

    private ClienteMedidaValor map(ResultSet rs) throws SQLException {
        int idCli = rs.getInt("idCliente");
        int cod   = rs.getInt("codMedida");

        BigDecimal bd = rs.getBigDecimal("valor");
        Double valor  = (bd != null) ? bd.doubleValue() : null;

        return new ClienteMedidaValor(idCli, cod, valor);
    }
}
