package pagina.web.cadastro;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.edsoft.framework.base.web.BasePaginaWeb;
import com.edsoft.framework.base.web.DriverContextWeb;
import com.edsoft.framework.controls.elements.Button;
import com.edsoft.framework.controls.elements.TextBox;

import io.qameta.allure.Step;

public class CreateAccountPage extends BasePaginaWeb {
	// account details
	@FindBy(how = How.NAME, using = "usernameRegisterPage")
	public TextBox txtName;
	@FindBy(how = How.NAME, using = "passwordRegisterPage")
	public TextBox txtpassword;
	@FindBy(how = How.NAME, using = "emailRegisterPage")
	public TextBox txtemail;
	@FindBy(how = How.NAME, using = "confirm_passwordRegisterPage")
	public TextBox txtconfirmPassword;
	// personal details
	@FindBy(how = How.NAME, using = "first_nameRegisterPage")
	public TextBox txtFirtName;
	@FindBy(how = How.NAME, using = "last_nameRegisterPage")
	public TextBox txtLastName;
	@FindBy(how = How.XPATH, using = "//*[@id=\"formCover\"]/div[2]/div[2]/sec-view/div/input")
	public TextBox txtPhoneNumber;
	// address
	@FindBy(how = How.NAME, using = "countryListboxRegisterPage")
	public WebElement selectCounty;
	@FindBy(how = How.NAME, using = "cityRegisterPage")
	public TextBox txtCity;
	@FindBy(how = How.NAME, using = "addressRegisterPage")
	public TextBox txtAddress;
	@FindBy(how = How.NAME, using = "state_/_province_/_regionRegisterPage")
	public TextBox txtRegion;
	@FindBy(how = How.NAME, using = "postal_codeRegisterPage")
	public TextBox txtPostalCode;

	@FindBy(how = How.NAME, using = "i_agree")
	public TextBox checkBox;

	@FindBy(how = How.ID, using = "register_btnundefined")
	public Button btnRegister;

	@Step("Preemcher os campos\"Account Details\"")
	public void preencherAccountDetails(String userName, String password, String confirmPassword, String email)
			throws Exception {
		try {
			DriverContextWeb.WaitForElementVisible(txtName);
			txtName.EnterText(userName);
			DriverContextWeb.WaitForElementVisible(txtpassword);
			txtpassword.EnterText(password);
			DriverContextWeb.WaitForElementVisible(txtconfirmPassword);
			txtconfirmPassword.EnterText(confirmPassword);
			DriverContextWeb.WaitForElementVisible(txtemail);
			txtemail.EnterText(email);
			DriverContextWeb.salvarEvidencia("Preemcher os campos details");
		} catch (Exception e) {
		}
	}

	@Step("Preemcher os campos\"Personal Details\"")
	public void preencherPersonalDetails(String firstName, String lastName, String phoneNumber) {
		DriverContextWeb.WaitForElementVisible(txtFirtName);
		txtFirtName.EnterText(firstName);
		DriverContextWeb.WaitForElementVisible(txtLastName);
		txtLastName.EnterText(lastName);
		DriverContextWeb.WaitForElementVisible(txtPhoneNumber);
		txtPhoneNumber.EnterText(phoneNumber);
		DriverContextWeb.salvarEvidencia("Preemcher os campos Personal");
		DriverContextWeb.barraDeRolagem(100);
	}

	@Step("Preemcher os campos\"Address\"")
	public CreateAccountPage preencherAddress(String county, String city, String address, String region,
			String postalCode) throws InterruptedException {
		DriverContextWeb.selectElementByVisibleText(selectCounty, county);
		DriverContextWeb.WaitForElementVisible(txtCity);
		txtCity.EnterText(city);
		DriverContextWeb.WaitForElementVisible(txtAddress);
		txtAddress.EnterText(address);
		DriverContextWeb.WaitForElementVisible(txtRegion);
		txtRegion.EnterText(region);
		DriverContextWeb.WaitForElementVisible(txtPostalCode);
		txtPostalCode.EnterText(postalCode);

		DriverContextWeb.WaitForElementVisible(checkBox);
		checkBox.click();
		DriverContextWeb.salvarEvidencia("Preencher os campos Address");
		DriverContextWeb.barraDeRolagem(300);
		return GetInstance(CreateAccountPage.class);
	}

	public CreateAccountPage clicarNoBotaoRegister() {
		DriverContextWeb.WaitForElementVisible(btnRegister);
		btnRegister.click();
		return GetInstance(CreateAccountPage.class);
	}
}
