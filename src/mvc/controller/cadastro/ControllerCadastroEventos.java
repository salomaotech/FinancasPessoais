package mvc.controller.cadastro;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import mvc.model.cadastro.DaoCadastro;
import mvc.view.telas.sistema.ViewSistema;
import sistema.model.RemoveRegistro;
import sistema.model.Swap;

/**
 * Eventos da view
 *
 * @author E-mail(salomao@taimber.com)
 * @version 1.0
 */
public class ControllerCadastroEventos {

    /**
     * Eventos
     *
     * @param view View
     */
    public static void addEventos(ViewSistema view) {

        /* botão salvar */
        view.jBcadastroSalvar.addActionListener((ActionEvent e) -> {

            /* grava */
            if (ControllerCadastroGrava.gravar(view, Swap.getIdCadastro())) {

                /* executar após ação */
                executarPosAcao1(view);

            }

        });

        /* botão excluir */
        view.jBcadastroExcluir.addActionListener((ActionEvent e) -> {

            /* valida se conseguiu remover o registro */
            if (RemoveRegistro.executar(new DaoCadastro().getTabela(), Swap.getIdCadastro())) {

                /* executar após ação */
                executarPosAcao1(view);

            }

        });

        /* botão novo */
        view.jBcadastroNovo.addActionListener((ActionEvent e) -> {

            /* executar após ação */
            executarPosAcao2(view);

        });

        /* botão pesquisa */
        view.jBcadastroPesquisa.addActionListener((ActionEvent e) -> {

            /* pesquisa */
            ControllerCadastroPesquisa.pesquisar(view);

        });

        /* botao paginador pesquisa */
        view.jBcadastroPaginador.addActionListener((ActionEvent e) -> {

            /* pesquisa */
            ControllerCadastroPesquisa.pesquisar(view);

        });

        /* resultados */
        view.jTcadastroLista.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                /* valida duplo clique */
                if (e.getClickCount() == 2) {

                    /* atualiza o swap */
                    Swap.setIdCadastro(view.jTcadastroLista.getModel().getValueAt(view.jTcadastroLista.getSelectedRow(), 0));

                    /* exibe os dados */
                    ControllerCadastroExibe.exibir(view, Swap.getIdCadastro());

                    /* seleciona aba */
                    view.jTabaPrincipal.setSelectedIndex(1);

                    /* exibe os controles */
                    ControllerCadastroView.habilitaControles(view);

                }
            }

        });

        /* atalho para novo cadastro */
        view.jBcadastroNovoAtalho.addActionListener((ActionEvent e) -> {

            /* executar após ação */
            executarPosAcao2(view);

        });

    }

    /**
     * Executar após uma ação
     *
     * @param view View
     */
    private static void executarPosAcao1(ViewSistema view) {

        /* limpa swap */
        Swap.setIdCadastro(null);

        /* reseta e habilita controles */
        ControllerCadastroView.resetaControles(view);
        ControllerCadastroView.habilitaControles(view);

        /* pesquisa */
        ControllerCadastroPesquisa.pesquisar(view);

        /* seleciona a aba */
        view.jTabaPrincipal.setSelectedIndex(0);

    }

    /**
     * Executar após uma ação
     *
     * @param view View
     */
    private static void executarPosAcao2(ViewSistema view) {

        /* limpa swap */
        Swap.setIdCadastro(null);

        /* reseta e habilita controles */
        ControllerCadastroView.resetaControles(view);
        ControllerCadastroView.habilitaControles(view);

        /* seleciona a aba */
        view.jTabaPrincipal.setSelectedIndex(1);

        /* move o foco */
        view.jDdata.requestFocus();

    }

}
