package teste.web.regressivo.cadastrarUsuario;

import org.testng.annotations.Test;

import com.edsoft.framework.annotations.Jornada;
import com.edsoft.framework.base.web.TestInitializeWeb;
import com.edsoft.framework.configs.Settings;
import com.edsoft.framework.constantes.Jornadas;
import com.edsoft.framework.constantes.massa.Massa;

import funcionalidade.web.criarConta.CreatAccountFunc;
import funcionalidade.web.login.LoginFunc;
import funcionalidade.web.menu.MenuFunc;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Jornada(Jornadas.CADASTRARUSUARIO)
public class CT0014 extends TestInitializeWeb {
	MenuFunc menu = new MenuFunc();
	LoginFunc login = new LoginFunc();
	CreatAccountFunc criaContaFunc = new CreatAccountFunc();

	@Test(description = "REG.CADAS.14 - Cadastrar Usuário no Sistema \\\"advantage\\\".\"")
	@Description("Validar Usuário Cadastrado no Sistema")
	@Severity(SeverityLevel.NORMAL)
	public void CT00014() throws Exception {
		menu.carregaAplicacao();
		menu.abrirPopUpLogin();
		Settings.Logs.GravaLog("^eu clico no link do login$");

		login.clicarAccountCount();
		Settings.Logs.GravaLog("^clico no account count$");

		criaContaFunc.preencherAccountDetails(recuperarMassa(Massa.USERNAME), recuperarMassa(Massa.PASSWORD),
				recuperarMassa(Massa.CONFIRMPASSWORD), recuperarMassa(Massa.EMAIL));
		Settings.Logs.GravaLog("^informo os dados account details$");

		criaContaFunc.preencherAccountPersonal(recuperarMassa(Massa.FIRSTNAME), recuperarMassa(Massa.LASTNAME),
				recuperarMassa(Massa.PHONENUMBER));
		Settings.Logs.GravaLog("^informo os dados personal details$");

		criaContaFunc.preencherAccountAddress(recuperarMassa(Massa.COUNTRY), recuperarMassa(Massa.CITY),
				recuperarMassa(Massa.ADDRESS), recuperarMassa(Massa.STATE), recuperarMassa(Massa.POSTALCODE));
		Settings.Logs.GravaLogReport("^informo o address$");
		
		menu.validarUsuarioCadastrado(recuperarMassa(Massa.USERNAME));
	}

}
