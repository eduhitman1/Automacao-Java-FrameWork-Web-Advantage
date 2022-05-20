package com.edsoft.framework.base.web;

import java.io.ByteArrayInputStream;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.edsoft.framework.configs.Settings;
//import br.com.next.core.tools.evidencia.NextEvidencia;
import com.edsoft.framework.tools.evidencia.NextEvidencia;

import io.qameta.allure.Allure;

public class DriverContextWeb {

	public static WebDriver Driver;
	public static Browser Browser;

	private static final Integer DEFAULTPOLLINGSECONDS = 1;
	private static final Integer DEFAULTTIMEOUTSECONDS = 10;

	public static void setDriver(WebDriver driver) {
		Driver = driver;
	}

	/**
	 * Carrega pagina inicial @param @throws
	 */
	public static void WaitForPageToLoad() {
		WebDriverWait wait = new WebDriverWait(DriverContextWeb.Driver, 30);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) DriverContextWeb.Driver;
		ExpectedCondition<Boolean> jsLoad = webDriver -> ((JavascriptExecutor) DriverContextWeb.Driver)
				.executeScript("return document.readyStates").toString().equals("complete");
		boolean jsReady = jsExecutor.executeScript("return document.readyState").toString().equals("complete");
		if (!jsReady)
			wait.until(jsLoad);
		else
			Settings.Logs.Write("Page is ready");
	}

	/**
	 * tempo para o elemento ser visivel @param @throws
	 */
	public static void WaitForElementVisible(final WebElement elementFindBy) {
		WebDriverWait wait = new WebDriverWait(DriverContextWeb.Driver, 30);
		wait.until(ExpectedConditions.visibilityOf(elementFindBy));
		Settings.Logs.Write("Wait visiblity element");
	}

	/**
	 * Display de texpo @param @throws
	 */
	public static void WaitUntilTextDisplayed(final By element, String text) {
		WebDriverWait wait = new WebDriverWait(DriverContextWeb.Driver, 30);
		wait.until(textDisplayed(element, text));
	}

	/**
	 * Comparação de texto @param @throws
	 */
	public static ExpectedCondition<Boolean> textDisplayed(final By elementFindBy, final String text) {
		return webDriver -> webDriver.findElement(elementFindBy).getText().contains(text);
	}

	/**
	 * Elemento Abilitado @param @throws
	 */
	public static void WaitElementEnabled(final By elementFindBy) {
		WebDriverWait wait = new WebDriverWait(DriverContextWeb.Driver, 30);
		wait.until(webDriver -> webDriver.findElement(elementFindBy).isEnabled());
	}

	/**
	 * Texto visivel @param @throws
	 */
	public static void WaitForElementTextVisible(final WebElement elementFindBy, String text) {
		WebDriverWait wait = new WebDriverWait(DriverContextWeb.Driver, 30);
		wait.until(ExpectedConditions.textToBePresentInElement(elementFindBy, text));
		Settings.Logs.Write("Wait visiblity element text");
	}

	/**
	 * Clicar em um elemento escondido @param @throws
	 */
	public static void WaitUntilElementClicked(final By elementFindBy) {
		WebDriverWait wait = new WebDriverWait(DriverContextWeb.Driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(elementFindBy)).click();
		Settings.Logs.Write("Clicrable Dialog");
	}

	/**
	 * Tempo de espera @param @throws
	 */
	public static DriverContextWeb sleep(int seconds) throws InterruptedException {
		Thread.sleep(seconds * 1000);
		return new DriverContextWeb();
	}

	/**
	 * Metado de data de agora @param @throws
	 */
	public static String getDataFormatada() {
		LocalDate data = LocalDate.now();
		DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/uuuu");
		String dataFormatada = formatterData.format(data);
		return dataFormatada;
	}

	/**
	 * Metodo de hora @param @throws
	 */
	public static String getHoraFormatada() {
		LocalTime hora = LocalTime.now();
		DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");
		String horaFormatada = formatterHora.format(hora);
		return horaFormatada;
	}

	/**
	 * Manipulação de driver @param @throws
	 */
	public static DriverContextWeb driverManage() {
		for (String winHandle : DriverContextWeb.Driver.getWindowHandles()) {
			DriverContextWeb.Driver.switchTo().window(winHandle);
		}
		return new DriverContextWeb();
	}

	/**
	 * Selecionar Combo box @param @throws
	 */
	public static DriverContextWeb selectElementByVisibleText(WebElement element, String text) {
		try {
			element.click();
			Select drop = new Select(element);
			drop.selectByVisibleText(text);
		} catch (Exception e) {
			System.out.println("Error select element from text!!!");
		}
		return new DriverContextWeb();
	}

	/**
	 * Controle da barra de rolagem @param @throws
	 */
	private static Object executeJs(String script, Object... args) {
		JavascriptExecutor js = (JavascriptExecutor) DriverContextWeb.Driver;
		return js.executeScript(script, args);
	}

	public static DriverContextWeb scrollToElement(WebElement element) {
		executeJs("Arguments[0].scrollIntoView(true)", element);
		return new DriverContextWeb();
	}

	public static DriverContextWeb barraDeRolagem(int scroll) {
		executeJs("window.scrollBy(0," + scroll + ")", "");
		return new DriverContextWeb();
	}

	/**
	 * Clicar elemento @param @throws
	 */
	public static <T> void clicarElemento(T elemento, String mensagemErro) throws Exception {
		try {
			if (elemento instanceof By) {
				DriverContextWeb.Driver.findElement((By) elemento).click();
				return;
			}
			((WebElement) elemento).click();
		} catch (Exception ex) {
			Settings.Logs
					.Write("Messagem no local do elemento erro : " + mensagemErro + "\nException: " + ex.getMessage());
		}
	}
	
	/**
	 * Dublo Click @param @throws
	 */
	public static <T> void dubloClick(T elemento, String mensagemErro) throws Exception {
		try {
			if (elemento instanceof By) {
				DriverContextWeb.Driver.findElement((By) elemento).click();
				DriverContextWeb.Driver.findElement((By) elemento).click();
				return;
			}
			((WebElement) elemento).click();
			((WebElement) elemento).click();
		} catch (Exception ex) {
			Settings.Logs
					.Write("Messagem no local do elemento erro : " + mensagemErro + "\nException: " + ex.getMessage());
		}
	}

	/**
	 * Retorna texto @param @throws
	 */
	public static <T> String retornarTexto(T elemento, String mensagemErro) throws Exception {
		try {
			if (elemento instanceof By)
				return DriverContextWeb.Driver.findElement((By) elemento).getText();
			return ((WebElement) elemento).getText();
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * Flutuar elemento @param @throws
	 */
	public static <T> void flutuarElemento(T elemento, String mensagemErro) throws Exception {
		try {
			Actions actions = new Actions(DriverContextWeb.Driver);
			if (elemento instanceof By) {
				actions.moveToElement(DriverContextWeb.Driver.findElement((By) elemento));
				actions.perform();
				return;
			}
			actions.moveToElement((WebElement) elemento);
			actions.perform();
		} catch (Exception ex) {
			Settings.Logs
					.Write("Messagem no local do elemento erro : " + mensagemErro + "\nException: " + ex.getMessage());
		}
	}

	/**
	 * Digitar texto @param @throws
	 */
	public static <T> void digitarTexto(T elemento, String texto, String mensagemErro) throws Exception {
		try {
			if (elemento instanceof By) {
				DriverContextWeb.Driver.findElement((By) elemento).sendKeys(new CharSequence[] { texto });
				return;
			}
			clicarElemento(elemento, mensagemErro);
			((WebElement) elemento).sendKeys(new CharSequence[] { texto });
		} catch (Exception ex) {
			Settings.Logs
					.Write("Messagem no local do elemento erro : " + mensagemErro + "\nException: " + ex.getMessage());
		}
	}

	/**
	 * Clicar por texto @param @throws
	 */
	public static void clicarTexto(String texto, String mensagemErro) throws Exception {
		try {
			DriverContextWeb.Driver.findElement(By.xpath("//*[@text='" + texto + "']")).click();
		} catch (Exception ex) {
			Settings.Logs
					.Write("Messagem no local do elemento erro : " + mensagemErro + "\nException: " + ex.getMessage());
		}
	}

	/**
	 * Clicar combo xbox @param @throws
	 */
	public static <T> void clicarTextoComboBox(T elemento, String valor, String mensagemErro) throws Exception {
		try {
			if (elemento instanceof By) {
				DriverContextWeb.Driver.findElement((By) elemento).click();
				clicarTexto(valor, mensagemErro);
				return;
			}
			((WebElement) elemento).click();
			clicarTexto(valor, mensagemErro);
		} catch (Exception ex) {
			Settings.Logs
					.Write("Messagem no local do elemento erro : " + mensagemErro + "\nException: " + ex.getMessage());
		}
	}

	/**
	 * rola para baixo até encontrar o elemento @param @throws
	 */
	public static <T> void rolarAteElemento(T elemento, String mensagemErro) throws Exception {
		try {
			Actions actions = new Actions(DriverContextWeb.Driver);
			if (elemento instanceof By) {
				actions.moveToElement(DriverContextWeb.Driver.findElement((By) elemento));
				actions.perform();
				return;
			}
			actions.moveToElement((WebElement) elemento);
			actions.perform();
		} catch (Exception ex) {
			Settings.Logs
					.Write("Messagem no local do elemento erro : " + mensagemErro + "\nException: " + ex.getMessage());
		}
	}

	/**
	 * Assert de elemento @param @throws
	 */
	public static WebElement retornarElemento(By by, String mensagemErro) throws Exception {
		try {
			return DriverContextWeb.Driver.findElement(by);
		} catch (Exception ex) {
			Settings.Logs
					.Write("Messagem no local do elemento erro : " + mensagemErro + "\nException: " + ex.getMessage());
			return null;
		}
	}

	protected void validarAtributoBoleano(By by, String atributo, String condicao, String mensagemErro)
			throws Exception {
		try {
			Assert.assertTrue(retornarElemento(by, mensagemErro).getAttribute(atributo).equals(condicao));
		} catch (Exception ex) {
			Settings.Logs
					.Write("Messagem no local do elemento erro : " + mensagemErro + "\nException: " + ex.getMessage());
		}
	}

	/**
	 * Assert de elemento @param @throws
	 */
	public static void validarAtributoTexto(By by, String texto, String mensagemErro) throws Exception {
		try {
			Assert.assertEquals(texto, retornarTexto(by, mensagemErro));
		} catch (Exception ex) {
			Settings.Logs
					.Write("Messagem no local do elemento erro : " + mensagemErro + "\nException: " + ex.getMessage());
		}
	}

	/**
	 * Volta a pagina @param @throws
	 */
	public static void voltarPaginaAnterior() {
		DriverContextWeb.Driver.navigate().back();
	}

	/**
	 * Avança a pagina @param @throws
	 */
	public static void avancarPagina() {
		DriverContextWeb.Driver.navigate().forward();
	}

	/**
	 * Atualizar pagina @param @throws
	 */
	public static void atualizarPagina() {
		DriverContextWeb.Driver.navigate().refresh();
	}

	/**
	 * Fechar aba @param @throws
	 */
	public static void fecharAba() {
		DriverContextWeb.Driver.findElement(By.cssSelector("body")).sendKeys(new CharSequence[] { Keys.CONTROL + "w" });
	}

	/**
	 * Retorna um texto por index @param @throws
	 */
	public static String retornarTextoPorIndice(By by, String mensagemErro, int index) throws Exception {
		try {
			return ((WebElement) DriverContextWeb.Driver.findElements(by).get(index)).getText();
		} catch (Exception ex) {
			Settings.Logs
					.Write("Messagem no local do elemento erro : " + mensagemErro + "\nException: " + ex.getMessage());
			return null;
		}
	}

	/**
	 * Retorna uma lista de elementos @param @throws
	 */
	public static List<WebElement> retornarListaElementos(By by, String mensagemErro) {
		try {
			return DriverContextWeb.Driver.findElements(by);
		} catch (Exception ex) {
			Settings.Logs
					.Write("Messagem no local do elemento erro : " + mensagemErro + "\nException: " + ex.getMessage());
		}
		return null;
	}

	/**
	 * Limpar campos preemchido @param @throws
	 */
	public static <T> void limparTexto(T elemento, String mensagemErro) {
		try {
			if (elemento instanceof By) {
				DriverContextWeb.Driver.findElement((By) elemento).clear();
				return;
			}
			((WebElement) elemento).clear();
		} catch (Exception ex) {
			Settings.Logs
					.Write("Messagem no local do elemento erro : " + mensagemErro + "\nException: " + ex.getMessage());
		}
	}

	/**
	 * Salva evidencia em report @param @throws
	 */
	public static void salvarEvidencia(String description) {
		try {
			String screenshot = tirarScreenShot();
			TestInitializeWeb.getEvidenciaWeb().add(new NextEvidencia(description, screenshot));
			Allure.addAttachment(description,
					new ByteArrayInputStream((byte[]) OutputType.BYTES.convertFromBase64Png(screenshot)));
		} catch (Exception ex) {
			Reporter.log("Não salva evidencia");
			ex.getMessage();
		}
		logarPasso(description);
	}

	private static String tirarScreenShot() {
		return (String) ((TakesScreenshot) DriverContextWeb.Driver).getScreenshotAs(OutputType.BASE64);
	}

	public static void logarPasso(String texto) {
		Reporter.log("[PASSO] " + texto, true);
	}

	/**
	 * Metodos Abaixo Adicionais @param @throws
	 */
	public void clickComumSelenium(WebElement element) {
		element.click();
	}

	public void click(WebElement element) throws InterruptedException {
		try {
			executeJs("arguments[0].click", element);
		} catch (StaleElementReferenceException e) {
			this.clickComumSelenium(element);
		} catch (WebDriverException we) {
			this.sleep(2);
			this.clickComumSelenium(element);
		}
	}

	public void insertText(WebElement element, String value) {
		this.fluentlyWaitUtilVisibility(element).sendKeys(value);
	}

	public void clearAndInsertText(WebElement element, String value) {
		this.fluentlyWaitUtilVisibility(element);
		element.clear();
		element.sendKeys(value);
	}

	public String getText(WebElement element) {
		return this.fluentlyWaitUtilVisibility(element).getText();
	}

	public void elementSubmit(WebElement element) {
		element.submit();
	}

	public void selectElementByIndex(WebElement element, int index) {
		try {
			Select drop = new Select(this.fluentlyWaitUtilVisibility(element));
			drop.selectByIndex(index);
		} catch (Exception e) {
			System.out.println("Error select element from index!!!");
		}
	}

	public void selectElementByValue(WebElement element, String value) {
		try {
			Select drop = new Select(this.fluentlyWaitUtilVisibility(element));
			drop.selectByValue(value);
		} catch (Exception e) {
			System.out.println("Error select element from value: " + e.getMessage());
		}
	}

	public void selectRadioFromList(List<WebElement> listElement, int option) throws InterruptedException {
		try {
			if (option >= 0 && option <= listElement.size()) {
				this.click(listElement.get(option));
			}
		} catch (Exception e) {
			System.out.println("option not exist or element not located: " + e.getMessage());
		}
	}

	public void moveToElement(WebElement element) {
		new Actions(DriverContextWeb.Driver).moveToElement(this.fluentlyWaitUtilVisibility(element));
	}

	public void moveToElementAndClick(WebElement element) {
		new Actions(DriverContextWeb.Driver).moveToElement(element).click();
	}

	public Boolean elementIsClickable(WebElement element, Integer seconds) {
		try {
			return this.waitForElementToBeClickable(element, seconds) != null;
		} catch (NoSuchElementException e) {
			System.out.println("Elemento nao visivel (NoSuchElementException).");
			return false;
		} catch (StaleElementReferenceException e) {
			System.out.println("Elemento nao visivel (StaleElementReferenceException).");
			return false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	private WebElement fluentlyWaitUtilVisibility(WebElement element) {
		return (new FluentWait<WebDriver>(DriverContextWeb.Driver))
				.withTimeout(Duration.ofSeconds(DEFAULTTIMEOUTSECONDS))
				.pollingEvery(Duration.ofSeconds(DEFAULTPOLLINGSECONDS)).ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.visibilityOf(element));
	}

	private WebElement waitForElementToBeClickable(WebElement element, int seconds) {
		return new WebDriverWait(DriverContextWeb.Driver, seconds).ignoring(StaleElementReferenceException.class)
				.ignoring(NoSuchElementException.class).until(ExpectedConditions.elementToBeClickable(element));
	}
}
