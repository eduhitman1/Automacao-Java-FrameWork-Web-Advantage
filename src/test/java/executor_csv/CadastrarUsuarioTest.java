package executor_csv;

import java.io.IOException;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.loader.LoaderType;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.edsoft.framework.configs.Settings;
import com.itextpdf.text.DocumentException;

import pagina.web.cadastro.CreateAccountPage;
import pagina.web.home.HomePage;
import pagina.web.home.LoginPage;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = { "CadastrarUsuarioDataDriver.csv" }, loaderType = LoaderType.CSV)
public class CadastrarUsuarioTest extends TestInitialize {

	@Test
	public void cadastrarUsuario(@Param(name = "username") String username, @Param(name = "password") String password,
			@Param(name = "confirmPassword") String confirmPassword, @Param(name = "email") String email,
			@Param(name = "firstName") String firstName, @Param(name = "lastName") String lastName,
			@Param(name = "phoneNumber") String phoneNumber, @Param(name = "county") String county,
			@Param(name = "city") String city, @Param(name = "address") String address,
			@Param(name = "region") String region, @Param(name = "postalCode") String postalCode)
			throws IOException, ClassNotFoundException, InterruptedException,Exception {
		try {
			CurrentPage = GetInstance(HomePage.class);
			CurrentPage = CurrentPage.As(HomePage.class).clickLogin();
			CurrentPage = CurrentPage.As(LoginPage.class).criarConta();

			CurrentPage.As(CreateAccountPage.class).preencherAccountDetails(username, password, confirmPassword, email);
			Settings.Logs.GravaLog("^informo os dados account details$\nUsername: " + username + "\nPassword: "
					+ password + "\nE-mail: " + email);

			CurrentPage.As(CreateAccountPage.class).preencherPersonalDetails(firstName, lastName, phoneNumber);
			Settings.Logs.GravaLog("^informo os dados personal details$\nFirstName: " + firstName + "\nLastName: "
					+ lastName + "\nPhoneNumber: " + phoneNumber);

			CurrentPage.As(CreateAccountPage.class).preencherAddress(county, city, address, region, postalCode);
			Settings.Logs.GravaLogReport("^informo o address$\nCounty: " + county + "\nCity: " + city + "\nAddress: "
					+ address + "\nRegion: " + region + "\nPostalCode: " + postalCode);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

}
