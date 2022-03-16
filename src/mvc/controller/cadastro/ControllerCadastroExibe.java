package mvc.controller.cadastro;

import br.com.taimber.algoritmos.Datas;
import br.com.taimber.arquivos.LeitorDePropriedades;
import br.com.taimber.swings.PopulaCombobox;
import java.util.Map;
import java.util.Properties;
import mvc.model.cadastro.DaoCadastro;
import mvc.view.telas.sistema.ViewSistema;
import sistema.model.BancoFactory;
import sistema.model.Propriedades;

/**
 * Exibe os dados cadastrados de um registro na view
 *
 * @author E-mail(salomao@taimber.com)
 * @version 1.0
 */
public class ControllerCadastroExibe {

    /**
     * Exibe os dados
     *
     * @param view View
     * @param id Id do cadastro
     */
    public static void exibir(ViewSistema view, Object id) {

        /* carrega os comboboxes */
        carregaComboboxes(view);

        /* excessão */
        try {

            /* cadastro */
            DaoCadastro cadastro = new DaoCadastro();

            /* dados */
            Map dados = (Map) cadastro.getDadosCadastro((String) id).get(0);

            /* popula */
            view.jDdata.setDate(Datas.converterStringParaDate((String) dados.get("data")));
            view.jCnumeroConta.setSelectedItem((String) dados.get("numeroConta"));
            view.jThistorico.setText((String) dados.get("historico"));
            view.jCcategoria.setSelectedItem((String) dados.get("categoria"));
            view.jTentrada.setText((String) dados.get("entrada"));
            view.jTsaida.setText((String) dados.get("saida"));
            view.jCisPago.setSelected(dados.get("isPago").equals("True"));
            view.jCcarteira.setSelectedItem((String) dados.get("carteira"));

        } catch (java.lang.IndexOutOfBoundsException ex) {

        }

    }

    /**
     * Exibe os dados
     *
     * @param view View
     */
    public static void exibir(ViewSistema view) {

        /* carrega os comboboxes */
        carregaComboboxes(view);

    }

    /**
     * Carrega os comboboxes necessários
     */
    private static void carregaComboboxes(ViewSistema view) {

        /* propriedades */
        Properties propriedades = new LeitorDePropriedades(Propriedades.ENDERECO_ARQUIVO_CONFIGURACOES).getPropriedades();

        /* popula */
        PopulaCombobox.executa(new BancoFactory(true).getBanco(), view.jCcategoria, propriedades.getProperty("prop.server.tabela"), "categoria");
        PopulaCombobox.executa(new BancoFactory(true).getBanco(), view.jCnumeroConta, propriedades.getProperty("prop.server.tabela"), "numeroConta");
        PopulaCombobox.executa(new BancoFactory(true).getBanco(), view.jCpesquisaCategoria, propriedades.getProperty("prop.server.tabela"), "categoria");
        PopulaCombobox.executa(new BancoFactory(true).getBanco(), view.jCcarteira, propriedades.getProperty("prop.server.tabela"), "carteira");
        PopulaCombobox.executa(new BancoFactory(true).getBanco(), view.jCpesquisaCarteira, propriedades.getProperty("prop.server.tabela"), "carteira");

    }

}
