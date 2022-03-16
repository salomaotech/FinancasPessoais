package mvc.controller.cadastro;

import br.com.taimber.algoritmos.Datas;
import br.com.taimber.swings.ValidaCamposNumericos;
import static java.util.Objects.isNull;
import javax.swing.JOptionPane;
import mvc.model.cadastro.BeanCadastro;
import mvc.model.cadastro.DaoCadastro;
import mvc.view.telas.sistema.ViewSistema;

/**
 * Pega os dados da view e grava no banco de dados
 *
 * @author E-mail(salomao@taimber.com)
 * @version 1.0
 */
public class ControllerCadastroGrava {

    /**
     * Grava os dados
     *
     * @param view View
     * @param id ID do cadastro
     * @return
     */
    public static boolean gravar(ViewSistema view, Object id) {

        /* cadastro */
        BeanCadastro cadastro = new BeanCadastro();
        cadastro.setId(id);
        cadastro.setData(Datas.converterDateParaString(view.jDdata.getDate()));
        cadastro.setNumeroConta(view.jCnumeroConta.getEditor().getItem());
        cadastro.setHistorico(view.jThistorico.getText());
        cadastro.setCategoria(view.jCcategoria.getEditor().getItem());
        cadastro.setEntrada(view.jTentrada.getText());
        cadastro.setSaida(view.jTsaida.getText());
        cadastro.setIsPago(view.jCisPago.isSelected());
        cadastro.setCarteira(view.jCcarteira.getEditor().getItem());

        /* cadastro */
        DaoCadastro cadastroDao = new DaoCadastro();

        /* validad dados */
        if (isDadosValidados(view)) {

            /* grava */
            if (cadastroDao.gravar(cadastro)) {

                /* Informa que o cadastro foi realizado */
                JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!");

                /* retorno */
                return true;
                
            }
            
        }

        /* retorno */
        return false;
        
    }

    /* valida os dados */
    private static boolean isDadosValidados(ViewSistema view) {

        /* valida data */
        if (isNull(view.jDdata.getDate())) {

            /* diálogo */
            JOptionPane.showMessageDialog(null, "Data inválida");

            /* foco */
            view.jDdata.requestFocus();

            /* retorno */
            return false;
            
        }

        /* valida campo de entrada */
        if (view.jTentrada.getText().length() != 0) {

            /* retorno */
            if (!ValidaCamposNumericos.isCamposNumericosValidados(view.jTentrada)) {

                /* retorno */
                return false;
                
            }
            
        }

        /* valida campo de saída */
        if (view.jTsaida.getText().length() != 0) {

            /* retorno */
            if (!ValidaCamposNumericos.isCamposNumericosValidados(view.jTsaida)) {

                /* retorno */
                return false;
                
            }
            
        }

        /* valida entrada e saída */
        if (view.jTentrada.getText().length() == 0 && view.jTsaida.getText().length() == 0) {

            /* diálogo */
            JOptionPane.showMessageDialog(null, "Informe uma entrada ou uma saída R$.");

            /* foco */
            view.jTentrada.requestFocus();

            /* retorno */
            return false;
            
        }

        /* retorno */
        return true;
        
    }
    
}
