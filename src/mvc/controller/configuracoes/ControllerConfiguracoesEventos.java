package mvc.controller.configuracoes;

import br.com.taimber.arquivos.ExecutaProgramaJarExterno;
import br.com.taimber.swings.ImagemIcone;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import mvc.model.configuracoes.CarregaConfiguracoes;
import mvc.view.telas.sistema.ViewSistema;

public class ControllerConfiguracoesEventos {

    /**
     * Eventos
     *
     * @param view View
     */
    public static void addEventos(ViewSistema view) {

        /* botão */
        view.jBconfiguracaoSalvar.addActionListener((ActionEvent e) -> {

            /* grava */
            ControllerConfiguracoesGrava.gravar(view);

        });

        /* atalho para abrir o módulo de backup */
        view.jBatalhoBackup.addActionListener((ActionEvent e) -> {

            ExecutaProgramaJarExterno.executar("modulos/dist/BackupPlus.jar");

        });

        /* botão */
        view.jTconfiguracaoTestar.addActionListener((ActionEvent e) -> {

            /* configurações */
            CarregaConfiguracoes configuracoes = new CarregaConfiguracoes();

            /* valida conexão */
            if (configuracoes.testaConexao(view.jTconfiguracaoIpServidor.getText(), view.jTconfiguracaoUsuario.getText(), view.jPconfiguracaoSenha.getText(), view.jTconfiguracaoBanco.getText(), view.jTconfiguracaoPorta.getText())) {

                /* mensagem */
                JOptionPane.showMessageDialog(null, "Conexão bem sucedida.");

                /* seta resposta */
                view.jLconfiguracaoTestarResposta.setIcon(new ImagemIcone().getIcone("/mvc/view/icones/sucesso.png"));
                view.jLconfiguracaoTestarResposta.setText("");

            } else {

                /* mensagem */
                JOptionPane.showMessageDialog(null, "Falha na conexão!");

                /* seta resposta */
                view.jLconfiguracaoTestarResposta.setIcon(new ImagemIcone().getIcone("/mvc/view/icones/erro.png"));
                view.jLconfiguracaoTestarResposta.setText("");

            }

        });

    }

}
