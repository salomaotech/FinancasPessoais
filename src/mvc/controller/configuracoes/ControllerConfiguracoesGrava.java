package mvc.controller.configuracoes;

import br.com.taimber.algoritmos.Datas;
import br.com.taimber.arquivos.LeitorDePropriedades;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import mvc.view.telas.sistema.ViewSistema;
import sistema.model.Propriedades;

public class ControllerConfiguracoesGrava {

    /**
     * Grava os dados
     *
     * @param view View
     */
    @SuppressWarnings("ConvertToTryWithResources")
    public static void gravar(ViewSistema view) {

        /* propriedades */
        Properties propriedades = new LeitorDePropriedades(Propriedades.ENDERECO_ARQUIVO_CONFIGURACOES).getPropriedades();

        /* excessão */
        try {

            /* arquivo */
            PrintWriter arquivo = new PrintWriter(Propriedades.ENDERECO_ARQUIVO_CONFIGURACOES, "UTF-8");

            /* popula */
            propriedades.setProperty("prop.server.servidor", view.jTconfiguracaoIpServidor.getText());
            propriedades.setProperty("prop.server.usuario", view.jTconfiguracaoUsuario.getText());
            propriedades.setProperty("prop.server.senha", view.jPconfiguracaoSenha.getText());
            propriedades.setProperty("prop.server.porta", view.jTconfiguracaoPorta.getText());
            propriedades.setProperty("prop.server.nomeBanco", view.jTconfiguracaoBanco.getText());

            /* itera propriedades */
            for (Map.Entry<Object, Object> entry : propriedades.entrySet()) {

                /* grava */
                arquivo.println("# Configuracao salva em (" + Datas.getDataAtualDiaMesAnoComHoraMinuto() + ")");
                arquivo.println(entry.getKey() + " = " + entry.getValue());
                arquivo.println("");

            }

            /* fecha */
            arquivo.close();

        } catch (FileNotFoundException | UnsupportedEncodingException ex) {

            /* mensagem */
            JOptionPane.showMessageDialog(null, "Falha crítica!");

            /* fecha o sistema */
            System.exit(0);

        } finally {

            /* mensagem */
            JOptionPane.showMessageDialog(null, "Configurações salvas! O sistema será fechado\nPor favor abra novamente o sistema depois disto.");

            /* fecha o sistema */
            System.exit(0);

        }

    }

}
