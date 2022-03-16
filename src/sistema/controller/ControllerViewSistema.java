package sistema.controller;

import mvc.controller.cadastro.ControllerCadastroView;
import sistema.model.Swap;

/**
 * Responsável por controlar todos os elementos da view
 *
 * @author E-mail(salomao@taimber.com)
 * @version 1.0
 */
public class ControllerViewSistema {

    /**
     * Reseta os controles da view
     *
     * @param view View
     */
    public static void resete(mvc.view.telas.sistema.ViewSistema view) {

        /* limpa swap */
        Swap.setIdCadastro(null);

        /* tratamentos */
        ControllerCadastroView.resetaControles(view);
        ControllerCadastroView.limpaListaResultadosAntigos(view);

        /* reseta abas */
        view.jTabaPrincipal.setSelectedIndex(0);

        /* repaint */
        view.repaint();

    }

}
