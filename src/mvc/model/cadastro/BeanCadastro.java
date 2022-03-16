package mvc.model.cadastro;

/**
 * Bean que representa um registro no banco de dados
 *
 * @author E-mail(salomao@taimber.com)
 * @version 1.0
 */
public class BeanCadastro {

    private Object id;
    private Object data;
    private Object numeroConta;
    private Object historico;
    private Object categoria;
    private Object entrada;
    private Object saida;
    private boolean isPago;
    private Object carteira;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(Object numeroConta) {
        this.numeroConta = numeroConta;
    }

    public Object getHistorico() {
        return historico;
    }

    public void setHistorico(Object historico) {
        this.historico = historico;
    }

    public Object getCategoria() {
        return categoria;
    }

    public void setCategoria(Object categoria) {
        this.categoria = categoria;
    }

    public Object getEntrada() {
        return entrada;
    }

    public void setEntrada(Object entrada) {
        this.entrada = entrada;
    }

    public Object getSaida() {
        return saida;
    }

    public void setSaida(Object saida) {
        this.saida = saida;
    }

    public boolean isIsPago() {
        return isPago;
    }

    public void setIsPago(boolean isPago) {
        this.isPago = isPago;
    }

    public Object getCarteira() {
        return carteira;
    }

    public void setCarteira(Object carteira) {
        this.carteira = carteira;
    }

}
