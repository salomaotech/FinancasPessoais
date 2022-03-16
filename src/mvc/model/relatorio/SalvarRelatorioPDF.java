package mvc.model.relatorio;

import br.com.taimber.algoritmos.FormataParaBigDecimal;
import br.com.taimber.algoritmos.FormataParaMoedaBrasileira;
import br.com.taimber.arquivos.GerarPdf;
import br.com.taimber.arquivos.DialogoSalvarArquivo;
import br.com.taimber.persistencia.sql.SqlCompletaQuery;
import br.com.taimber.persistencia.sql.SqlEntreDatas;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import mvc.model.cadastro.DaoCadastro;
import static java.util.Objects.isNull;
import sistema.model.BancoFactory;

public class SalvarRelatorioPDF {

    private LinkedHashMap entidades;

    /**
     * Atualiza lista de entidades a serem pesquisadas
     *
     * @param entidades Entidades a serem pesquisadas
     */
    public void setEntidades(LinkedHashMap entidades) {

        /* popula entidades */
        this.entidades = entidades;

    }

    /**
     * Monta condição de pesquisa
     *
     * @return String com condição de pesquisa
     */
    private String montaCondicaoPesquisa() {

        /* data inicial e final */
        String dataInicial = (String) this.entidades.get("dataInicial");
        String dataFinal = (String) this.entidades.get("dataFinal");

        /* condição anterior */
        String condicaoAnterior = null;

        /* valida data inicial de pesquisa */
        if (!isNull(dataInicial)) {

            /* condição anterior */
            condicaoAnterior = SqlEntreDatas.montar(dataInicial, null, "data");

        }

        /* valida data inicial e final de pesquisa */
        if (!isNull(dataInicial) && !isNull(dataFinal)) {

            /* condição anterior */
            condicaoAnterior = SqlEntreDatas.montar(dataInicial, dataFinal, "data");

        }

        /* remove entidades */
        this.entidades.remove("dataInicial");
        this.entidades.remove("dataFinal");

        /* completa query */
        SqlCompletaQuery completaQuery = new SqlCompletaQuery(this.entidades, 0, new DaoCadastro().getTabela(), true);

        /* retorno */
        return completaQuery.completar(false, condicaoAnterior, "order by str_to_date(data, '%d/%m/%Y') asc");

    }

    /**
     * Salva o relatório
     *
     * @return True conseguiu salvar o relatório
     */
    public boolean salvar() {

        /* endereço onde será salvo o arquivo */
        String enderecoSalvar = DialogoSalvarArquivo.executar();

        /* valida endereço para salvar arquivo */
        if (isNull(enderecoSalvar)) {

            /* retorno */
            return false;

        }

        /* banco */
        BancoFactory banco = new BancoFactory(true);

        /* dados */
        List dados = banco.getBanco().consultarRegistro("select *from " + new DaoCadastro().getTabela() + " " + montaCondicaoPesquisa());

        /* novo PDF */
        GerarPdf pdf = new GerarPdf(enderecoSalvar);

        /* saldo */
        BigDecimal saldoEntrada = FormataParaBigDecimal.formatar("0");
        BigDecimal saldoSaida = FormataParaBigDecimal.formatar("0");
        BigDecimal saldoTotal = FormataParaBigDecimal.formatar("0");

        /* listando os dados */
        for (Object linha : dados) {

            /* map de dados */
            Map dadosMap = (Map) linha;

            /* entrada, saida */
            BigDecimal entrada = FormataParaBigDecimal.formatar(dadosMap.get("entrada"));
            BigDecimal saida = FormataParaBigDecimal.formatar(dadosMap.get("saida"));

            /* calcula o saldo */
            saldoEntrada = saldoEntrada.add(entrada);
            saldoSaida = saldoSaida.add(saida);
            saldoTotal = saldoTotal.add(entrada.subtract(saida));

            /* pago ou não */
            String pago;

            /* valida se foi pago */
            if (dadosMap.get("isPago").equals("True")) {

                pago = "Sim";

            } else {

                pago = "Nao";

            }

            /* conteudo */
            String conteudo = ""
                    + "Data: " + dadosMap.get("data")
                    + "   "
                    + "Pago: " + pago
                    + "   "
                    + "Conta: " + dadosMap.get("numeroConta")
                    + "   "
                    + "Categoria: " + dadosMap.get("categoria")
                    + "   "
                    + "Carteira: " + dadosMap.get("carteira")
                    + "\n"
                    + "Entrada: " + FormataParaMoedaBrasileira.cifrar(entrada)
                    + " *** "
                    + "Saida: " + FormataParaMoedaBrasileira.cifrar(saida)
                    + " *** "
                    + "\n"
                    + "Histórico: " + dadosMap.get("historico")
                    + "\n"
                    + "-----------------------------------------------------------------------------------------"
                    + "";

            /* add linha */
            pdf.addLinha(conteudo);

        }

        /* titulo */
        String título = ""
                + "Financeiro pessoal " + this.entidades.get("numeroConta")
                + "\n"
                + "Entrada: " + FormataParaMoedaBrasileira.cifrar(saldoEntrada)
                + " *** "
                + "Saida: " + FormataParaMoedaBrasileira.cifrar(saldoSaida)
                + "\n"
                + "Saldo: " + FormataParaMoedaBrasileira.cifrar(saldoTotal)
                + "";

        /* excessão */
        try {

            /* valida se foi pago */
            switch (this.entidades.get("isPago").toString()) {

                case "True":
                    título += "\nPago: Sim";
                    break;

                case "False":
                    título += "\nPago: Nao";
                    break;

            }

        } catch (java.lang.NullPointerException ex) {

            título += "\nPago: Pagos e não pagos";

        }

        /* gera o PDF */
        pdf.gerar(título);

        /* retorno */
        return true;

    }

}
