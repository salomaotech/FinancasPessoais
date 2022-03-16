package sistema.model;

import br.com.taimber.arquivos.LeitorDePropriedades;
import br.com.taimber.persistencia.banco.BancoDados;
import java.util.Properties;

/**
 * Deleta o banco de dados
 *
 * @author E-mail(salomao@taimber.com)
 * @version 2.0
 */
public class DeletaBancoDados {

    private final BancoDados banco;

    /**
     * Construtor
     *
     */
    public DeletaBancoDados() {

        /* banco de dados */
        this.banco = new BancoFactory(false).getBanco();

    }

    /**
     * Cria o banco de dados
     */
    public void criarBanco() {

        /* propriedades */
        Properties propriedades = new LeitorDePropriedades(Propriedades.ENDERECO_ARQUIVO_CONFIGURACOES).getPropriedades();

        /* deleta o banco de dados */
        this.banco.excluirBancoDeDados(propriedades.getProperty("prop.server.nomeBanco"));

    }

}
