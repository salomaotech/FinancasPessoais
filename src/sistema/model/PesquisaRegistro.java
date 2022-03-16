package sistema.model;

import br.com.taimber.persistencia.banco.BancoDados;
import br.com.taimber.persistencia.sql.SqlCompletaQuery;
import java.util.List;

/**
 * Pesquisa registros no banco de dados
 *
 * @author E-mail(salomao@taimber.com)
 * @version 2.0
 */
public class PesquisaRegistro {

    /**
     * Realiza a pesquisa
     *
     * @param nomeTabela Nome da tabela
     * @param completaQuery Completa query
     * @param colunasTabela Colunas da tabela a serem pesquisadas
     * @param condicaoAnterior Condição de pesquisa anterior
     * @param orderBy Order by
     * @return list com os dados da pesquisa
     */
    public List executar(String nomeTabela, SqlCompletaQuery completaQuery, List colunasTabela, String condicaoAnterior, String orderBy) {

        /* excessão */
        try {

            /* banco */
            BancoDados banco = new BancoFactory(true).getBanco();

            /* condição de pesquisa */
            String condicaoPesquisa = completaQuery.completar(true, condicaoAnterior, orderBy);

            /* retorno */
            return banco.consultarRegistro(nomeTabela, colunasTabela, condicaoPesquisa);

        } catch (java.lang.NullPointerException ex) {

            /* retorno */
            return null;

        }

    }

}
