package pagina.web.quantidadeProduto;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.edsoft.framework.base.web.BasePaginaWeb;
import com.edsoft.framework.base.web.DriverContextWeb;

import io.qameta.allure.Step;

public class ManageProductPage extends BasePaginaWeb {

	@FindBy(how = How.NAME, using = "buy_now")
	public WebElement buyNow;

	@FindBy(how = How.XPATH, using = "//div[@increment-value-attr='+']")
	public WebElement btnMais;

	@Step("tocar no botao \"buy Now\"")
	public ManageProductPage primeiraOferta() throws Exception {
		DriverContextWeb.WaitForElementVisible(buyNow);
		buyNow.click();
		DriverContextWeb.salvarEvidencia("clicar na primeira oferta");
		return GetInstance(ManageProductPage.class);
	}

	@Step("Selecionar \"Quantidade de produto\"")
	public ManageProductPage quantidade(String valor) throws Exception{
		int quant = Integer.parseInt(valor);
		quant--;
		for (int i = 0; i < quant; i++) {
			DriverContextWeb.WaitForElementVisible(btnMais);
			btnMais.click();
		}
		DriverContextWeb.salvarEvidencia("Selecionar quantidade de produto");

		return GetInstance(ManageProductPage.class);
	}

}
