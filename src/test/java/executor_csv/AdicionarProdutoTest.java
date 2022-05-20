package executor_csv;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.loader.LoaderType;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.edsoft.framework.configs.Settings;

import pagina.web.home.HomePage;
import pagina.web.home.LoginPage;
import pagina.web.quantidadeProduto.ManageProductPage;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = { "AdicionarProdutoDataDriver.csv" }, loaderType = LoaderType.CSV)
public class AdicionarProdutoTest extends TestInitialize {

	@Test
	public void adicionarUsuario(@Param(name = "username") String username, @Param(name = "password") String password,
			@Param(name = "categoria") String categoria, @Param(name = "quantidade") String quantidade)
			throws Exception {
		try {
			CurrentPage = GetInstance(HomePage.class);
			CurrentPage = CurrentPage.As(HomePage.class).clickLogin();
			Settings.Logs.GravaLog("^eu clico no link do login$");

			CurrentPage.As(LoginPage.class).Login(username, password);
			Settings.Logs.GravaLog("^eu insiro UserName and Password$");
			CurrentPage = CurrentPage.As(LoginPage.class).btnLogar();
			Settings.Logs.GravaLog("^clico no botao logar$");

			CurrentPage = CurrentPage.As(HomePage.class).selecionarCategorira(categoria);
			Settings.Logs.GravaLog("^estou selecionando a categoria do produto$");

			CurrentPage = CurrentPage.As(ManageProductPage.class).primeiraOferta();
			Settings.Logs.GravaLog("^clicar no botao da primeira oferta de produto$");

			CurrentPage = CurrentPage.As(ManageProductPage.class).quantidade(quantidade);
			Settings.Logs.GravaLog("^seleciona a quantidade desejada$");

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
