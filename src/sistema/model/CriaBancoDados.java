package sistema.model;

import br.com.taimber.arquivos.LeitorDePropriedades;
import br.com.taimber.persistencia.banco.BancoDados;
import java.util.Properties;

/**
 * Cria o banco de dados
 *
 * @author E-mail(salomao@taimber.com)
 * @version 2.0
 */
public class CriaBancoDados {

    private final BancoDados banco;

    /**
     * Construtor
     *
     */
    public CriaBancoDados() {

        /* banco de dados */
        this.banco = new BancoFactory(false).getBanco();

    }

    /**
     * Cria o banco de dados
     */
    public void criarBanco() {

        /* propriedades */
        Properties propriedades = new LeitorDePropriedades(Propriedades.ENDERECO_ARQUIVO_CONFIGURACOES).getPropriedades();

        /* cria o banco de dados */
        this.banco.criarBancoDeDados(propriedades.getProperty("prop.server.nomeBanco"));

    }

}
