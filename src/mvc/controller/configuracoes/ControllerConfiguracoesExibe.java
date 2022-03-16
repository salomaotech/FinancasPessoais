package mvc.controller.configuracoes;

import br.com.taimber.arquivos.LeitorDePropriedades;
import java.util.Properties;
import mvc.view.telas.sistema.ViewSistema;
import sistema.model.Propriedades;

public class ControllerConfiguracoesExibe {

    /**
     * Exibe as propriedades de conexão
     *
     * @param view View
     */
    private static void exibePropriedadesDeConexao(ViewSistema view) {

        /* propriedades */
        Properties propriedades = new LeitorDePropriedades(Propriedades.ENDERECO_ARQUIVO_CONFIGURACOES).getPropriedades();

        /* popula */
        view.jTconfiguracaoIpServidor.setText(propriedades.getProperty("prop.server.servidor"));
        view.jTconfiguracaoUsuario.setText(propriedades.getProperty("prop.server.usuario"));
        view.jPconfiguracaoSenha.setText(propriedades.getProperty("prop.server.senha"));
        view.jTconfiguracaoPorta.setText(propriedades.getProperty("prop.server.porta"));
        view.jTconfiguracaoBanco.setText(propriedades.getProperty("prop.server.nomeBanco"));

    }

    /**
     * Exibe os dados
     *
     * @param view View
     */
    public static void exibir(ViewSistema view) {

        /* exibe as propriedades de conexão */
        exibePropriedadesDeConexao(view);

    }

}
