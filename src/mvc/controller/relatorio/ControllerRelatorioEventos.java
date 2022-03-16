package mvc.controller.relatorio;

import br.com.taimber.algoritmos.Datas;
import java.awt.event.ActionEvent;
import java.util.LinkedHashMap;
import static java.util.Objects.isNull;
import javax.swing.JOptionPane;
import mvc.model.relatorio.SalvarRelatorioPDF;
import mvc.view.telas.sistema.ViewSistema;

public class ControllerRelatorioEventos {

    /**
     * Eventos
     *
     * @param view View
     */
    public static void addEventos(ViewSistema view) {

        /* botão para exportar relatório */
        view.jBexportarRelatorio.addActionListener((ActionEvent e) -> {

            /* novo relatório */
            SalvarRelatorioPDF relatorio = new SalvarRelatorioPDF();

            /* entidades de pesquisa */
            LinkedHashMap entidades = new LinkedHashMap();
            entidades.put("numeroConta", view.jTcadastroPesquisaConta.getText());
            entidades.put("dataInicial", Datas.converterDateParaString(view.jDdataInicialPesquisa.getDate()));
            entidades.put("dataFinal", Datas.converterDateParaString(view.jDdataFinalPesquisa.getDate()));

            /* valida categoria */
            if (!isNull(view.jCpesquisaCategoria.getSelectedItem())) {

                /* popula entidade de pesquisa */
                entidades.put(("categoria"), view.jCpesquisaCategoria.getSelectedItem());

            }

            /* valida carteira */
            if (!isNull(view.jCpesquisaCarteira.getSelectedItem())) {

                /* popula entidade de pesquisa */
                entidades.put(("carteira"), view.jCpesquisaCarteira.getSelectedItem());

            }

            /* valida se foi pago */
            switch (view.jCpesquisaPago.getSelectedItem().toString()) {

                case "Sim":
                    entidades.put("isPago", "True");
                    break;

                case "Nao":
                    entidades.put("isPago", "False");
                    break;

            }

            /* seta as entidades */
            relatorio.setEntidades(entidades);

            /* valida se salvou o relatório */
            if (relatorio.salvar()) {

                JOptionPane.showMessageDialog(null, "Relatório salvo com sucesso!");

            } else {

                JOptionPane.showMessageDialog(null, "Relatório não salvo!");

            }

        });

    }

}
