package sistema.model;

/**
 * Swap para troca de dados temporários
 *
 * @author E-mail(salomao@taimber.com)
 * @version 1.0
 */
public class Swap {

    private static Object idCadastro;

    public static Object getIdCadastro() {
        return idCadastro;
    }

    public static void setIdCadastro(Object aIdCadastro) {
        idCadastro = aIdCadastro;
    }

}
