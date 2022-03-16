package sistema.model;

import br.com.taimber.arquivos.LeitorDePropriedades;
import br.com.taimber.persistencia.banco.BancoDados;
import br.com.taimber.persistencia.banco.ConexaoSingleTon;
import java.util.Properties;

/**
 * Fábrica do banco de dados
 *
 * @author E-mail(salomao@taimber.com)
 * @version 2.0
 */
public class BancoFactory {

    private final BancoDados bancoDados;

    /**
     * Construtor para abrir instância de banco de dados
     *
     * @param isConectaComBancoDeDados True conecta-se com o banco de dados,
     * False conecta-se sem o uso do banco de dados
     */
    public BancoFactory(boolean isConectaComBancoDeDados) {

        /* propriedades */
        Properties propriedades = new LeitorDePropriedades(Propriedades.ENDERECO_ARQUIVO_CONFIGURACOES).getPropriedades();

        /* dados do servidor */
        String servidor = propriedades.getProperty("prop.server.servidor");
        String nomeBanco = propriedades.getProperty("prop.server.nomeBanco");
        String usuario = propriedades.getProperty("prop.server.usuario");
        String senha = propriedades.getProperty("prop.server.senha");
        String porta = propriedades.getProperty("prop.server.porta");

        /* conexão */
        ConexaoSingleTon conexao;

        /* valida se irá usar o banco de dados na conexão */
        if (isConectaComBancoDeDados) {

            /* abre conexão */
            conexao = new ConexaoSingleTon(servidor, nomeBanco, usuario, senha, porta);

        } else {

            /* abre conexão */
            conexao = new ConexaoSingleTon(servidor, null, usuario, senha, porta);

        }

        /* abre conexão com o servidor sem usar o banco de dados */
        this.bancoDados = new BancoDados(conexao);

    }

    /**
     * Retorna o banco de dados
     *
     * @return BancoDados
     */
    public BancoDados getBanco() {

        return bancoDados;

    }

}
