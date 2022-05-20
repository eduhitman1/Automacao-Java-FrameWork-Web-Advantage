package funcionalidade.web.criarConta;

import java.io.IOException;

import com.edsoft.framework.base.web.BaseFuncionalidadeWeb;
import com.edsoft.framework.configs.Settings;
import com.itextpdf.text.DocumentException;

import pagina.web.cadastro.CreateAccountPage;
import pagina.web.home.HomePage;

public class CreatAccountFunc extends BaseFuncionalidadeWeb {
	/**
	 * Preemcher os campos Details @throws ClassNotFoundException,
	 * IOException,DocumentException, Exception
	 */
	public void preencherAccountDetails(String username, String password, String confirmPassword, String email)
			throws ClassNotFoundException, IOException, DocumentException, Exception {
		CurrentPage.As(CreateAccountPage.class).preencherAccountDetails(username, password, confirmPassword, email);
		Settings.Logs.GravaLog("^eu clico no link do login$");
	}

	/**
	 * Preemcher os campos Personal @throws ClassNotFoundException,
	 * IOException,DocumentException, Exception
	 */
	public void preencherAccountPersonal(String firstName, String lastName, String phoneNumber) {
		CurrentPage.As(CreateAccountPage.class).preencherPersonalDetails(firstName, lastName, phoneNumber);
	}

	/**
	 * Preemcher os campos Address e clicar no botao register@throws InterruptedException
	 */
	public void preencherAccountAddress(String county, String city, String address, String state, String postalCode)
			throws InterruptedException {
		CurrentPage.As(CreateAccountPage.class).preencherAddress(county, city, address, state, postalCode)
				.clicarNoBotaoRegister();
	}

}
