package mvc.model.configuracoes;

import br.com.taimber.arquivos.LeitorDePropriedades;
import br.com.taimber.persistencia.banco.ConexaoSingleTon;
import java.util.Properties;
import sistema.model.Propriedades;

public class CarregaConfiguracoes {

    private final Properties propriedades;

    /**
     * Construtor
     */
    public CarregaConfiguracoes() {

        /* fecha a conexão pendurada */
        new ConexaoSingleTon(null, null, null, null, null).fecha();

        /* propriedades */
        this.propriedades = new LeitorDePropriedades(Propriedades.ENDERECO_ARQUIVO_CONFIGURACOES).getPropriedades();

    }

    /**
     * Testa a conexão com o uso do banco de dados
     *
     * @return True para sucesso
     */
    public boolean testaConexaoComBancoDeDados() {

        /* conexão */
        ConexaoSingleTon conexao = new ConexaoSingleTon(this.propriedades.getProperty("prop.server.servidor"), this.propriedades.getProperty("prop.server.nomeBanco"), this.propriedades.getProperty("prop.server.usuario"), this.propriedades.getProperty("prop.server.senha"), this.propriedades.getProperty("prop.server.porta"));

        /* retorno */
        return conexao.getErros().isEmpty();

    }

    /**
     * Testa a conexão sem o uso do banco de dados
     *
     * @return True para sucesso
     */
    public boolean testaConexaoSemBancoDeDados() {

        /* conexão */
        ConexaoSingleTon conexao = new ConexaoSingleTon(this.propriedades.getProperty("prop.server.servidor"), null, this.propriedades.getProperty("prop.server.usuario"), this.propriedades.getProperty("prop.server.senha"), this.propriedades.getProperty("prop.server.porta"));

        /* retorno */
        return conexao.getErros().isEmpty();

    }

    /**
     * Testa a conexão
     *
     * @param servidor Servidor
     * @param usuario Usuário
     * @param senha Senha
     * @param nomeBanco Nome do banco
     * @param porta Porta
     * @return True para sucesso
     */
    public boolean testaConexao(String servidor, String usuario, String senha, String nomeBanco, String porta) {

        /* conexão */
        ConexaoSingleTon conexao = new ConexaoSingleTon(servidor, nomeBanco, usuario, senha, porta);

        /* retorno */
        return conexao.getErros().isEmpty();

    }

    /**
     * Retorna se as configurações funcionaram
     *
     * @return True funcionaram
     */
    public boolean isConfiguracoesFuncionaram() {

        /* testa a conexão com o banco de dados */
        if (testaConexaoComBancoDeDados()) {

            /* retorno */
            return true;

        } else {

            /* retorno */
            return testaConexaoSemBancoDeDados();

        }

    }

    /**
     * Propriedades
     *
     * @return properties
     */
    public Properties getPropriedades() {

        return propriedades;

    }

}
