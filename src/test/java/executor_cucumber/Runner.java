package executor_cucumber;

import java.io.IOException;

import org.junit.runner.RunWith;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.edsoft.framework.base.web.DriverContextWeb;
import com.edsoft.framework.configs.Settings;
import com.edsoft.framework.utilities.ExtentReport;
import com.itextpdf.text.DocumentException;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

//Web
@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/java/features/AdicionarProduto.feature" }, glue = { "steps" }, format = {
		"json:target/cucumber-json-report.json", "html:target/cucumber-report-html" }, plugin = {
				"json:target/cucumber-json-report.json", "html:target/cucumber-report-html",
				"com.cucumber.listener.ExtentCucumberFormatter:extent-reporting/CucumberExtentReport.html" })
public class Runner {
	public TestNGCucumberRunner testNGCucumberRunner;

	@BeforeClass(alwaysRun = true)
	public void setUpClass() {
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	}

	@Test(dataProvider = "features")
	public void runTest(CucumberFeatureWrapper cucumberFeatureWrapper)
			throws ClassNotFoundException, IOException, DocumentException {
		String Feature = cucumberFeatureWrapper.getCucumberFeature().getGherkinFeature().getName();
		ExtentReport.startFeature(Feature);
		testNGCucumberRunner.runCucumber(cucumberFeatureWrapper.getCucumberFeature());
//		killBrownser();
	}

	@DataProvider
	public Object[][] features() {
		return testNGCucumberRunner.provideFeatures();
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		testNGCucumberRunner.finish();
	}

	public void killBrownser() throws IOException, DocumentException {
		try {
			Thread.sleep(3000);
			DriverContextWeb.Driver.close();
			Settings.Logs.GravaLog("Closer brownser\n");
		} catch (InterruptedException e) {
			Settings.Logs.GravaLog("Erro ao fecha brownser");
		}
	}
}
