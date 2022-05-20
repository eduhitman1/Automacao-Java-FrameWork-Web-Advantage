package steps;

import com.edsoft.framework.base.web.BaseFuncionalidadeWeb;
import com.edsoft.framework.configs.Settings;
import com.edsoft.framework.utilities.CucumberUtil;

import cucumber.api.DataTable;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import pagina.web.home.HomePage;

public class ContactStep extends BaseFuncionalidadeWeb {

	private int produto = 3;
	private String categoriaProduto;

	@E("^desco a barra de rolagem para baixo$")
	public void desco_a_barra_de_rolagem_para_baixo() throws Throwable {
		CurrentPage.As(HomePage.class).descerBarraDeRolagem();
		Settings.Logs.GravaLog("descer barra de rolagem");
	}

	@E("^seleciono a categoria de produto$")
	public void seleciono_o_tipo_de_produto(DataTable table) throws Throwable {
		CucumberUtil.convertDataTableToDisc(table);
		CurrentPage.As(HomePage.class)
				.selecionarCategoriaDeProdutoCombo(CucumberUtil.GetCellValueWithRowIndex("Produto", produto));
		Settings.Logs.GravaLog(
				"Seleciona o tipo de produto para contato: " + CucumberUtil.GetCellValueWithRowIndex("produto", 3));

		categoriaProduto = CucumberUtil.GetCellValueWithRowIndex("Produto", produto);
	}

	@E("^seleciono o modelo do produto$")
	public void seleciono_o_modelo_do_produto(DataTable table) throws Throwable {
		CucumberUtil.convertDataTableToDisc(table);
		CurrentPage.As(HomePage.class)
				.selecionarModeloDeProdutoCombo(CucumberUtil.GetCellValueWithRowIndex(categoriaProduto, 2));
	}

	@E("^preemcho o endereco de email$")
	public void preemcho_o_endereco_de_email(DataTable table) throws Throwable {
		CucumberUtil.convertDataTableToDisc(table);
		CurrentPage.As(HomePage.class).selecionarEmail(CucumberUtil.GetCellValueWithRowIndex("email", 2));
	}

	@Quando("^preemcho a descricao da mensagem$")
	public void preemcho_o_descricao_a_mensagem(DataTable table) throws Throwable {
		CucumberUtil.convertDataTableToDisc(table);
		CurrentPage.As(HomePage.class).digitarAreaDeTexto(CucumberUtil.GetCellValueWithRowIndex("texto", 1));
	}

	@Entao("^clicar no go up$")
	public void clicar_no_go_up() throws Throwable {
		CurrentPage.As(HomePage.class).clicarNoBotaoGoUp();
	}
}
