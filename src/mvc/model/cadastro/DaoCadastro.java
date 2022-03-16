package mvc.model.cadastro;

import br.com.taimber.arquivos.LeitorDePropriedades;
import br.com.taimber.persistencia.sql.SqlTrataEntidades;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import static java.util.Objects.isNull;
import sistema.model.BancoFactory;
import sistema.model.Propriedades;

/**
 * Responsável por gravar a bean no banco de dados
 *
 * @author E-mail(salomao@taimber.com)
 * @version 1.0
 */
public class DaoCadastro {

    private final String tabela;

    /**
     * Construtor
     */
    public DaoCadastro() {

        /* tabela */
        this.tabela = new LeitorDePropriedades(Propriedades.ENDERECO_ARQUIVO_CONFIGURACOES).getPropriedades().getProperty("prop.server.tabela");

    }

    /**
     * Retorna a tabela
     *
     * @return Nome da tabela
     */
    public String getTabela() {

        return tabela;

    }

    /**
     * Cria a entidade, ou seja a tabela
     */
    public void criaEntidade() {

        /* banco */
        BancoFactory banco = new BancoFactory(true);

        /* entidades */
        LinkedHashMap entidades = new LinkedHashMap();
        entidades.put("id", "int not null auto_increment primary key");
        entidades.put("data", "text");
        entidades.put("numeroConta", "text");
        entidades.put("historico", "text");
        entidades.put("categoria", "text");
        entidades.put("entrada", "text");
        entidades.put("saida", "text");
        entidades.put("isPago", "text");
        entidades.put("carteira", "text");

        /* cria a tabela */
        banco.getBanco().criarTabela(this.tabela, entidades);

    }

    /**
     * Grava os dados
     *
     * @param cadastro Cadastro
     * @return Retorna true em caso de sucesso ao gravar dados
     */
    public boolean gravar(BeanCadastro cadastro) {

        /* banco */
        BancoFactory banco = new BancoFactory(true);

        /* entidades */
        LinkedHashMap entidades = new LinkedHashMap();
        entidades.put("data", cadastro.getData());
        entidades.put("numeroConta", cadastro.getNumeroConta());
        entidades.put("historico", cadastro.getHistorico());
        entidades.put("categoria", cadastro.getCategoria());
        entidades.put("entrada", cadastro.getEntrada());
        entidades.put("saida", cadastro.getSaida());
        entidades.put("isPago", cadastro.isIsPago());
        entidades.put("carteira", cadastro.getCarteira());

        /* trata as entidades */
        entidades = SqlTrataEntidades.tratar(entidades);

        /* valida id */
        if (isNull(cadastro.getId())) {

            /* grava e retorna */
            return banco.getBanco().inserirRegistro(this.tabela, entidades);

        } else {

            /* condições */
            LinkedHashMap condicoes = new LinkedHashMap();
            condicoes.put("id", cadastro.getId());

            /* atualiza e retorna */
            return banco.getBanco().atualizarRegistro(this.tabela, entidades, condicoes);

        }

    }

    /**
     * Retorna os dados cadastrados
     *
     * @param idRegistro Id do cadastro
     * @return List com dados
     */
    public List getDadosCadastro(String idRegistro) {

        /* banco */
        BancoFactory banco = new BancoFactory(true);

        /* entidades */
        List entidades = new ArrayList();
        entidades.add("data");
        entidades.add("numeroConta");
        entidades.add("historico");
        entidades.add("categoria");
        entidades.add("entrada");
        entidades.add("saida");
        entidades.add("isPago");
        entidades.add("carteira");

        /* retorno */
        return banco.getBanco().consultarRegistro(this.tabela, entidades, "where id='" + idRegistro + "'");

    }

}
