package teste.web.regressivo.adicionarProduto;

import org.testng.annotations.Test;

import com.edsoft.framework.annotations.Jornada;
import com.edsoft.framework.base.web.TestInitializeWeb;
import com.edsoft.framework.configs.Settings;
import com.edsoft.framework.constantes.Jornadas;
import com.edsoft.framework.constantes.massa.Massa;

import funcionalidade.web.login.LoginFunc;
import funcionalidade.web.menu.MenuFunc;
import funcionalidade.web.quantidadeProduto.QuantidadeProdutoFunc;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Jornada(Jornadas.ADICIONARPRODUTO)
public class CT0002 extends TestInitializeWeb {
	MenuFunc menu = new MenuFunc();
	LoginFunc login = new LoginFunc();
	QuantidadeProdutoFunc telaProduto = new QuantidadeProdutoFunc();

	@Test(description = "REG.ARPRO.02 - Adicionar produto no carrinho \\\"advantage\\\".\"")
	@Description("Validar quando finalizar a quantidade de produto adicionado")
	@Severity(SeverityLevel.NORMAL)
	public void CT0002() throws Exception{
			menu.carregaAplicacao();
			menu.abrirPopUpLogin();
			Settings.Logs.GravaLog("^eu clico no link do login$");

			login.preencherCamposLogin(recuperarMassa(Massa.USERNAME), recuperarMassa(Massa.PASSWORD));
			login.clicarBotaoLogar();
			Settings.Logs.GravaLog("^eu insiro UserName and Password$");

			menu.selecionarCategoriaDoProduto(recuperarMassa(Massa.CATEGORIA));
			Settings.Logs.GravaLog("^estou selecionando a categoria do produto$");

			telaProduto.clicarNoBuyNow();
			telaProduto.selecionarQuantidadeDeProduto(recuperarMassa(Massa.QUANTIDADE));
			Settings.Logs.GravaLogReport("^seleciona a quantidade desejada$");
	}
}
