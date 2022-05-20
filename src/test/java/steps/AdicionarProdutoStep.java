package steps;

import org.junit.Assert;

import com.aventstack.extentreports.GherkinKeyword;
import com.edsoft.framework.base.web.BaseFuncionalidadeWeb;
import com.edsoft.framework.configs.Settings;
import com.edsoft.framework.utilities.CucumberUtil;
import com.edsoft.framework.utilities.ExcelUtil;
import com.edsoft.framework.utilities.ExtentReport;

import cucumber.api.DataTable;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import pagina.web.home.HomePage;
import pagina.web.home.LoginPage;
import pagina.web.quantidadeProduto.ManageProductPage;

public class AdicionarProdutoStep extends BaseFuncionalidadeWeb {

	@Dado("^que estou com a aplicacao aberta$")
	public void queEstouComAAplicacao() throws Throwable {
		CurrentPage = GetInstance(HomePage.class);
		ExtentReport.getScenario().createNode(new GherkinKeyword("Given"), "que estou com a aplicacao");
	}

	@Quando("^eu clico no link do login$")
	public void euClicoNoLinkDoLogin() throws Throwable {
		CurrentPage = CurrentPage.As(HomePage.class).clickLogin();
		ExtentReport.getScenario().createNode(new GherkinKeyword("When"), "eu clico no link do login");
		Settings.Logs.GravaLog("^eu clico no link do login$");
	}

	@Entao("^eu insiro o userName e Password$")
	public void euInsiroUserNameAndPassword() throws Throwable {
//		CurrentPage.As(LoginPage.class).Login(ExcelUtil.ReadCell(("UserName"), 1), ExcelUtil.ReadCell(("Password"), 1));
		CurrentPage.As(LoginPage.class).Login(ExcelUtil.getCell(1, 0), ExcelUtil.getCell(1, 1));
		Settings.Logs.Write("UserName: " + ExcelUtil.getCell(1, 0) + " Password: " + ExcelUtil.getCell(1, 1));

		ExtentReport.getScenario().createNode(new GherkinKeyword("Then"), "eu insiro UserName and Password");
		Settings.Logs.GravaLog("^eu insiro UserName and Password$");
//		List<List<String>> table = data.raw(); CurrentPage.As(LoginPage.class).Login(table.get(1).get(0).toString(),table.get(1).get(1).toString());
	}

	@E("^clico no botao logar$")
	public void clicoNoBotaoLogar() throws Throwable {
		CurrentPage = CurrentPage.As(LoginPage.class).btnLogar();
		ExtentReport.getScenario().createNode(new GherkinKeyword("And"), "clico no botao logar");
		Settings.Logs.GravaLog("^clico no botao logar$");
	}

	@E("^valido na barra superior username$")
	public void validoNaBarraSuperiorUsername() throws Throwable {
//		Assert.assertEquals("eduardotest", CurrentPage.As(HomePage.class).getLoggerInUser());
		ExtentReport.getScenario().createNode(new GherkinKeyword("And"), "valido na barra superior username");
		Settings.Logs.GravaLog("^valido na barra superior username$");
	}

	@Dado("^estou selecionando a categoria do produto$")
	public void estou_selecionando_a_categoria_do_produto(DataTable table) throws Throwable {
		CucumberUtil.convertDataTableToDisc(table);
		CurrentPage = CurrentPage.As(HomePage.class)
				.selecionarCategorira(CucumberUtil.GetCellValueWithRowIndex("categoria", 2));
//      ExtentReport.getScenario().createNode(new GherkinKeyword("Given"), "estou selecionando a categoria do produto");	  
		Settings.Logs.GravaLog("^estou selecionando a categoria do produto$");
	}

	@E("^clicar no botao da primeira oferta de produto$")
	public void clicar_no_botao_da_primeira_oferta_de_produto() throws Throwable {
		CurrentPage = CurrentPage.As(ManageProductPage.class).primeiraOferta();
		Settings.Logs.GravaLog("^clicar no botao da primeira oferta de produto$");
	}

	@Entao("^seleciono a quantidade desejada$")
	public void seleciona_a_quantidade_desejada(DataTable table) throws Throwable {
		CucumberUtil.convertDataTableToDisc(table);
		CurrentPage = CurrentPage.As(ManageProductPage.class)
				.quantidade(CucumberUtil.GetCellValueWithRowIndex("quantidade", 1));
		Settings.Logs.GravaLog("^seleciona a quantidade desejada$");
	}
}
