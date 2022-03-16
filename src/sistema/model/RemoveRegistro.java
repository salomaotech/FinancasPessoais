package sistema.model;

import javax.swing.JOptionPane;

/**
 * Remove um registro do banco de dados
 *
 * @author E-mail(salomao@taimber.com)
 * @version 2.0
 */
public class RemoveRegistro {

    /**
     * Remove um cadastro
     *
     * @param id Id do registro
     * @param tabela Nome da tabela
     * @return True para conseguiu remover o registro
     */
    public static boolean executar(Object tabela, Object id) {

        /* retorno padrão */
        boolean retorno = false;

        /* valida se irá remover o registro */
        if (JOptionPane.showConfirmDialog(null, "Remover registro?") == 0) {

            /* remove o registro */
            if (new BancoFactory(true).getBanco().deletarRegistro((String) tabela, (String) id) == true) {

                /* mensagem */
                JOptionPane.showMessageDialog(null, "Registro removido!");

                /* retorno */
                retorno = true;

            } else {

                /* mensagem */
                JOptionPane.showMessageDialog(null, "Registro não removido!");

            }

        }

        /* retorno */
        return retorno;

    }

}
