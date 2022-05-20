package funcionalidade.web.menu;

import java.io.IOException;

import org.testng.Assert;

import com.edsoft.framework.base.web.BaseFuncionalidadeWeb;
import com.edsoft.framework.configs.Settings;
import com.itextpdf.text.DocumentException;

import pagina.web.home.HomePage;

public class MenuFunc extends BaseFuncionalidadeWeb {
	/**
	 * Carregar página da aplicação @throws Exception, ClassNotFoundException,
	 * IOException, DocumentException
	 */
	public void carregaAplicacao() throws ClassNotFoundException, IOException, DocumentException, Exception {
		CurrentPage = GetInstance(HomePage.class);
	}

	/**
	 * Abrir PopUp Login @throws Exception
	 */
	public void abrirPopUpLogin() throws ClassNotFoundException, IOException, DocumentException, Exception {
		CurrentPage = CurrentPage.As(HomePage.class).clickLogin();
		Settings.Logs.GravaLog("^eu clico no link do login$");
	}

	/**
	 * Selecionar Categoria de produto @throws Exception DocumentException,
	 * InterruptedException
	 */
	public void selecionarCategoriaDoProduto(String categoria)
			throws IOException, DocumentException, InterruptedException, Exception {
		CurrentPage = CurrentPage.As(HomePage.class).selecionarCategorira(categoria);
		Settings.Logs.GravaLog("^estou selecionando a categoria do produto$");
	}

	/**
	 * Validar usuario cadastrado @throws Exception DocumentException,
	 * InterruptedException
	 */
	public void validarUsuarioCadastrado(String valor) 
			throws IOException, DocumentException, InterruptedException, Exception {
		CurrentPage = GetInstance(HomePage.class);
		Assert.assertEquals(valor, CurrentPage.As(HomePage.class).txtUsuarioLogado());
		Settings.Logs.GravaLog("^estou selecionando a categoria do produto$");
	}

}
