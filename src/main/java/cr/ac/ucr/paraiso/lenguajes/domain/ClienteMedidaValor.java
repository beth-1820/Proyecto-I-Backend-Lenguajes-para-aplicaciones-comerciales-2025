package cr.ac.ucr.paraiso.lenguajes.domain;

public class ClienteMedidaValor {
    private int idCliente;
    private int codMedida;
    private Double valor; // opcional

    public ClienteMedidaValor() {}

    public ClienteMedidaValor(int idCliente, int codMedida, Double valor) {
        this.idCliente = idCliente;
        this.codMedida = codMedida;
        this.valor = valor;
    }
    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
    public int getCodMedida() { return codMedida; }
    public void setCodMedida(int codMedida) { this.codMedida = codMedida; }
    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }
}