package pagina.web.home;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.edsoft.framework.base.web.BasePaginaWeb;
import com.edsoft.framework.base.web.DriverContextWeb;
import com.edsoft.framework.controls.elements.Button;
import com.edsoft.framework.controls.elements.TextBox;

import io.qameta.allure.Step;
import pagina.web.cadastro.CreateAccountPage;

public class LoginPage extends BasePaginaWeb {

	@FindBy(how = How.NAME, using = "username")
	public TextBox txtUserName;

	@FindBy(how = How.NAME, using = "password")
	public TextBox txtPassword;

	@FindBy(how = How.ID, using = "sign_in_btnundefined")
	public Button btnLogin;

	@FindBy(how = How.LINK_TEXT, using = "CREATE NEW ACCOUNT")
	public WebElement createCount;

	@Step("Preemcher Formulario \"Username e password\"")
	public void Login(String userName, String password) throws Exception {
		try {
			DriverContextWeb.WaitForElementVisible(txtUserName);
			txtUserName.EnterText(userName);

			DriverContextWeb.WaitForElementVisible(txtPassword);
			txtPassword.EnterText(password);
		} catch (Exception e) {
		}
		DriverContextWeb.salvarEvidencia("Preemchido Formulario de login");
	}

	@Step("Tocar no botao \"Logar\"")
	public HomePage btnLogar() throws Exception {
		Thread.sleep(3000);
		DriverContextWeb.clicarElemento(btnLogin, "não clicou em logar");
		DriverContextWeb.salvarEvidencia("clicar em logar");
		return GetInstance(HomePage.class);
	}

	@Step("Tocar no botao \"Criar conta\"")
	public CreateAccountPage criarConta() throws Exception{
		try {
			Thread.sleep(3000);
			createCount.click();
		} catch (Exception e) {
			System.out.println("botao criar com erro: " + e.getMessage());
		}
		return GetInstance(CreateAccountPage.class);
	}
}
