package mvc.controller.cadastro;

import static java.util.Objects.isNull;
import javax.swing.table.DefaultTableModel;
import mvc.view.telas.sistema.ViewSistema;
import sistema.model.Swap;

/**
 * Realiza operações na view como (Resetar os controles, Limpar, habilitar
 * campos etc).
 *
 * @author E-mail(salomao@taimber.com)
 * @version 1.0
 */
public class ControllerCadastroView {

    /**
     * Reseta os controles
     *
     * @param view
     */
    public static void resetaControles(ViewSistema view) {

        /* limpa campos */
        view.jDdata.setDate(null);
        view.jCnumeroConta.setSelectedIndex(-1);
        view.jThistorico.setText("");
        view.jCcategoria.setSelectedIndex(-1);
        view.jTentrada.setText("");
        view.jTsaida.setText("");
        view.jCisPago.setSelected(false);
        view.jCcarteira.setSelectedIndex(-1);

    }

    /**
     * Habilita controles
     *
     * @param view View
     */
    public static void habilitaControles(ViewSistema view) {

        /* habilita */
        view.jBcadastroExcluir.setEnabled(!isNull(Swap.getIdCadastro()));

    }

    /**
     * Limpa resultados antigos
     *
     * @param view View
     */
    public static void limpaListaResultadosAntigos(ViewSistema view) {

        /* default model */
        DefaultTableModel model = (DefaultTableModel) view.jTcadastroLista.getModel();
        model.setNumRows(0);

    }

}
