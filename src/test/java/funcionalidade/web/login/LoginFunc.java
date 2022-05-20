package funcionalidade.web.login;

import com.edsoft.framework.base.web.BaseFuncionalidadeWeb;
import com.edsoft.framework.configs.Settings;

import pagina.web.home.HomePage;
import pagina.web.home.LoginPage;

public class LoginFunc extends BaseFuncionalidadeWeb {
	/**
	 * Preencher campos login e senha @throws Exception
	 */
	public void preencherCamposLogin(String userName, String password) throws Exception {
		CurrentPage.As(LoginPage.class).Login(userName, password);
		Settings.Logs.Write("UserName: " + userName + " Password: " + password);
	}

	/**
	 * Clicar no botão logar @throws Exception
	 */
	public HomePage clicarBotaoLogar() throws Exception {
		CurrentPage = CurrentPage.As(LoginPage.class).btnLogar();
		Settings.Logs.GravaLog("^clico no botao logar$");
		return GetInstance(HomePage.class);
	}

	/**
	 * Clicar no botão logar @throws Exception
	 */
	public LoginPage clicarAccountCount() throws Exception {
		CurrentPage = CurrentPage.As(LoginPage.class).criarConta();
		return GetInstance(LoginPage.class);
	}

}
