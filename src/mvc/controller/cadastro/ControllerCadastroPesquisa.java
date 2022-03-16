package mvc.controller.cadastro;

import br.com.taimber.algoritmos.Datas;
import br.com.taimber.algoritmos.FormataParaBigDecimal;
import br.com.taimber.algoritmos.FormataParaMoedaBrasileira;
import br.com.taimber.persistencia.sql.SqlCompletaQuery;
import br.com.taimber.persistencia.sql.SqlEntreDatas;
import br.com.taimber.swings.MudaCorLinhaJtable;
import br.com.taimber.swings.Paginador;
import java.awt.Color;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import static java.util.Objects.isNull;
import javax.swing.table.DefaultTableModel;
import mvc.model.cadastro.DaoCadastro;
import mvc.view.telas.sistema.ViewSistema;
import sistema.model.BancoFactory;
import sistema.model.PesquisaRegistro;

/**
 * Pesquisa os dados e apresenta os resultados na view
 *
 * @author E-mail(salomao@taimber.com)
 * @version 1.0
 */
public class ControllerCadastroPesquisa {

    /**
     * Exibe os dados
     *
     * @param view View
     */
    public static void pesquisar(ViewSistema view) {

        /* entidades */
        Map entidadesPesquisa = new LinkedHashMap();
        entidadesPesquisa.put("numeroConta", view.jTcadastroPesquisaConta.getText());

        /* valida categoria */
        if (!isNull(view.jCpesquisaCategoria.getSelectedItem())) {

            /* popula entidade de pesquisa */
            entidadesPesquisa.put(("categoria"), view.jCpesquisaCategoria.getSelectedItem());

        }

        /* valida carteira */
        if (!isNull(view.jCpesquisaCarteira.getSelectedItem())) {

            /* popula entidade de pesquisa */
            entidadesPesquisa.put(("carteira"), view.jCpesquisaCarteira.getSelectedItem());

        }

        /* valida se foi pago */
        switch (view.jCpesquisaPago.getSelectedItem().toString()) {

            case "Sim":
                entidadesPesquisa.put("isPago", "True");
                break;

            case "Nao":
                entidadesPesquisa.put("isPago", "False");
                break;

        }

        /* completa query */
        SqlCompletaQuery completaQuery = new SqlCompletaQuery(entidadesPesquisa, view.jCcadastroPaginador.getSelectedItem(), new DaoCadastro().getTabela(), true);

        /* colunas a serem pesquisadas */
        List colunasTabela = new ArrayList();
        colunasTabela.add("id");
        colunasTabela.add("data");
        colunasTabela.add("numeroConta");
        colunasTabela.add("historico");
        colunasTabela.add("categoria");
        colunasTabela.add("entrada");
        colunasTabela.add("saida");
        colunasTabela.add("isPago");
        colunasTabela.add("carteira");

        /* condição anterior */
        String condicaoAnterior = null;

        /* valida data inicial de pesquisa */
        if (!isNull(view.jDdataInicialPesquisa.getDate())) {

            /* condição anterior */
            condicaoAnterior = SqlEntreDatas.montar(Datas.converterDateParaString(view.jDdataInicialPesquisa.getDate()), null, "data");

        }

        /* valida data inicial e final de pesquisa */
        if (!isNull(view.jDdataInicialPesquisa.getDate()) && !isNull(view.jDdataFinalPesquisa.getDate())) {

            /* condição anterior */
            condicaoAnterior = SqlEntreDatas.montar(Datas.converterDateParaString(view.jDdataInicialPesquisa.getDate()), Datas.converterDateParaString(view.jDdataFinalPesquisa.getDate()), "data");

        }

        /* dados */
        List dados = new PesquisaRegistro().executar(new DaoCadastro().getTabela(), completaQuery, colunasTabela, condicaoAnterior, "order by str_to_date(data, '%d/%m/%Y') asc");

        /* default model */
        DefaultTableModel model = (DefaultTableModel) view.jTcadastroLista.getModel();
        model.setNumRows(0);

        /* contador */
        int contador = 0;

        /* list com cores */
        List cores = new ArrayList();

        /* saldo */
        BigDecimal saldoTotal = FormataParaBigDecimal.formatar("0");

        /* listando os dados */
        for (Object linha : dados) {

            /* map de dados */
            Map dadosMap = (Map) linha;

            /* entrada, saida e saldo total */
            BigDecimal entrada = FormataParaBigDecimal.formatar(dadosMap.get("entrada"));
            BigDecimal saida = FormataParaBigDecimal.formatar(dadosMap.get("saida"));
            saldoTotal = saldoTotal.add(entrada.subtract(saida));

            /* valida se foi pago */
            if (dadosMap.get("isPago").equals("True")) {

                dadosMap.put("isPago", "Sim");

            } else {

                dadosMap.put("isPago", "Nao");
            }

            /* popula*/
            dadosMap.put("saldoTotal", FormataParaMoedaBrasileira.cifrar(saldoTotal));
            dadosMap.put("entrada", FormataParaMoedaBrasileira.cifrar(dadosMap.get("entrada")));
            dadosMap.put("saida", FormataParaMoedaBrasileira.cifrar(dadosMap.get("saida")));

            /* objeto linha */
            Object[] linhaJtable = new Object[]{
                dadosMap.get("id"),
                dadosMap.get("data"),
                dadosMap.get("entrada"),
                dadosMap.get("saida"),
                dadosMap.get("numeroConta"),
                dadosMap.get("categoria"),
                dadosMap.get("carteira"),
                dadosMap.get("saldoTotal"),
                dadosMap.get("isPago")

            };

            /* valida */
            if (saldoTotal.compareTo(FormataParaBigDecimal.formatar("0")) > 0) {

                /* verde */
                cores.add(Color.decode("#ace0c6"));

            } else {

                /* vermelho */
                cores.add(Color.decode("#f49093"));

            }

            /* insere a linha */
            model.insertRow(contador, linhaJtable);

            /* atualiza o contador */
            contador++;

        }

        /* muda a cor da jtable */
        MudaCorLinhaJtable.mudar(view.jTcadastroLista, cores);

        /* paginador */
        Paginador paginador = new Paginador(view.jCcadastroPaginador, new DaoCadastro().getTabela(), completaQuery.completar(false, condicaoAnterior, null));
        paginador.popular(new BancoFactory(true).getBanco());

    }

}
