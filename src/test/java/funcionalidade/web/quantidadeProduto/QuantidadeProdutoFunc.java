package funcionalidade.web.quantidadeProduto;

import java.io.IOException;

import com.edsoft.framework.base.web.BaseFuncionalidadeWeb;
import com.edsoft.framework.configs.Settings;
import com.itextpdf.text.DocumentException;

import pagina.web.quantidadeProduto.ManageProductPage;

public class QuantidadeProdutoFunc extends BaseFuncionalidadeWeb {
	/**
	 * Clicar no primeiro botão de oferta @throws Exception
	 */
	public ManageProductPage clicarNoBuyNow() throws IOException, DocumentException, Exception {
		CurrentPage = CurrentPage.As(ManageProductPage.class).primeiraOferta();
		Settings.Logs.GravaLog("^clicar no botao da primeira oferta de produto$");
		return GetInstance(ManageProductPage.class);
	}

	/**
	 * Selecionar Quantidade de produto @throws Exception
	 */
	public ManageProductPage selecionarQuantidadeDeProduto(String quantidade) throws IOException, DocumentException, Exception {
		CurrentPage = CurrentPage.As(ManageProductPage.class).quantidade(quantidade);
		Settings.Logs.GravaLog("^seleciona a quantidade desejada$");
		return GetInstance(ManageProductPage.class);
	}

}
