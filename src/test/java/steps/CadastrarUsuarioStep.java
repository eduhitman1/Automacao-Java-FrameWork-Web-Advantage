package steps;

import java.util.List;

import com.aventstack.extentreports.GherkinKeyword;
import com.edsoft.framework.base.web.BaseFuncionalidadeWeb;
import com.edsoft.framework.base.web.DriverContextWeb;
import com.edsoft.framework.configs.Settings;
import com.edsoft.framework.utilities.CucumberUtil;
import com.edsoft.framework.utilities.ExcelUtil;
import com.edsoft.framework.utilities.ExtentReport;

import cucumber.api.DataTable;
import cucumber.api.java.pt.E;
import pagina.web.cadastro.CreateAccountPage;
import pagina.web.home.LoginPage;

public class CadastrarUsuarioStep extends BaseFuncionalidadeWeb {

	@E("^clico em 'CREATE NEW ACCOUNT'$")
	public void clicoEmCREATENEWACCOUNT() throws Throwable {
		DriverContextWeb.WaitForPageToLoad();
		CurrentPage = CurrentPage.As(LoginPage.class).criarConta();
		ExtentReport.getScenario().createNode(new GherkinKeyword("And"), "clico em 'CREATE NEW ACCOUNT'");
		Settings.Logs.GravaLog("^clico em 'CREATE NEW ACCOUNT'$");
	}

	@E("^informo os dados account details$")
	public void informoOsDadosAccountDetails(DataTable table) throws Throwable {
		CucumberUtil.convertDataTableToDisc(table);
		CurrentPage.As(CreateAccountPage.class).preencherAccountDetails(
				CucumberUtil.GetCellValueWithRowIndex("username", 1),
				CucumberUtil.GetCellValueWithRowIndex("password", 1),
				CucumberUtil.GetCellValueWithRowIndex("confirmPassword", 1),
				CucumberUtil.GetCellValueWithRowIndex("email", 1));
		ExtentReport.getScenario().createNode(new GherkinKeyword("And"), "informo os dados account details");

		List<String> dataList = table.asList(String.class);
		for (String key : dataList) {
			System.out.println(String.format("Value: %s ", key));
		}
//		        CurrentPage.As(CriarContaPage.class).preencherAccountDetails(ExcelUtil.ReadCell(("UserName"), 1),
//				ExcelUtil.ReadCell(("Password"), 1), ExcelUtil.ReadCell(("ConfirmPassword"), 1),
//				ExcelUtil.ReadCell(("Email"), 1));

		ExcelUtil.setCell(1, 0, CucumberUtil.GetCellValueWithRowIndex("username", 1));
		ExcelUtil.setCell(1, 1, CucumberUtil.GetCellValueWithRowIndex("password", 1));
		Settings.Logs.GravaLog("^informo os dados account details$");
	}

	@E("^informo os dados personal details$")
	public void informo_os_dados_personal_details(DataTable table) throws Throwable {
		CucumberUtil.convertDataTableToDisc(table);
		CurrentPage.As(CreateAccountPage.class).preencherPersonalDetails(
				CucumberUtil.GetCellValueWithRowIndex("firstName", 1),
				CucumberUtil.GetCellValueWithRowIndex("lastName", 1),
				CucumberUtil.GetCellValueWithRowIndex("phoneNumber", 1));
		Settings.Logs.GravaLog("^informo os dados personal details$");
	}

	@E("^informo o address$")
	public void informo_o_address(DataTable table) throws Throwable {
		CucumberUtil.convertDataTableToDisc(table);
		CurrentPage.As(CreateAccountPage.class).preencherAddress(CucumberUtil.GetCellValueWithRowIndex("county", 1),
				CucumberUtil.GetCellValueWithRowIndex("city", 1), CucumberUtil.GetCellValueWithRowIndex("address", 1),
				CucumberUtil.GetCellValueWithRowIndex("region", 1),
				CucumberUtil.GetCellValueWithRowIndex("postalCode", 1));
		Settings.Logs.GravaLogReport("^informo o address$");
	}

}
