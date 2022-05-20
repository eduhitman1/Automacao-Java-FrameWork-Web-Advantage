package com.edsoft.framework.base.mobile;

import static com.edsoft.framework.base.mobile.DriverFactory.getDriver;

import java.io.ByteArrayInputStream;
import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.edsoft.framework.configs.Settings;
import com.edsoft.framework.tools.evidencia.NextEvidencia;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Allure;

public class DriverContextMobile {

	protected static AppiumDriver<MobileElement> driver;

	/**
	 * metodo para escrever
	 * 
	 * @param By
	 * @Param String
	 */
	public void escrever(By by, String texto) {
		getDriver().findElement(by).sendKeys(texto);
	}

	/**
	 * metodo para escrever por xpath @param @throws
	 */
	public static void escreverXPath(String campo_xpath, String texto) {
		getDriver().findElementByXPath(campo_xpath).sendKeys(texto);
	}

	/**
	 * metodo para clicar no elemento
	 * 
	 * @param By
	 */
	public static void clicar(By by) {
		getDriver().findElement(by).click();
	}

	/**
	 * metodo para clicar no elemento
	 * 
	 * @param By
	 */
	public void clicarId(String texto) {
		getDriver().findElement(By.id(texto)).click();
	}

	/**
	 * metodo para clicar no elemento por xpath
	 * 
	 * @param By
	 */
	public void clicarPorXPath(String campo_xpath) {
		getDriver().findElementByXPath(campo_xpath).click();
	}

	/**
	 * metodo para retorna texto na tela
	 * 
	 * @param By
	 */
	public static String obterTexto(By by) {
		return getDriver().findElement(by).getText();
	}

	/**
	 * metodo para clicar no texto
	 * 
	 * @param By
	 */
	public static void clicarPorTexto(String texto) {
		getDriver().findElement(By.xpath("//android.widget.TextView[starts-with(@text, '" + texto + "')]")).click();
	}

	/**
	 * metodo verificação de texto 
	 * usando com a condição while para barra de rolagem
	 * @param By
	 */
	public static boolean verificaExistenciaElementeText(String texto) {
		try {
			getDriver().findElement(By.xpath("//android.widget.TextView[starts-with(@text, '" + texto + "')]"));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * metodos para clicar no elemento e arrasta para direita e esquerda
	 * 
	 */
	public void swipeDireita() {
		swipe(0.9, 0.1);
	}

	public void swipeEsquerda() {
		swipe(0.1, 0.9);
	}

	public void swipe(double inicio, double fim) {
		Dimension size = getDriver().manage().window().getSize();
		int y = 0;
		y = size.height / 2;
		int start_x = (int) (size.width * inicio);
		int end_x = 0;
		end_x = (int) (size.width * fim);
//		new TouchAction(getDriver()).press(start_x, y).waitAction(Duration.ofMillis(500)).moveTo(end_x, y).release()
//				.perform();
	}

	public static void swipeElementy(MobileElement element, double inicio, double fim) {
		int y = element.getLocation().y + (element.getSize().height / 2);
		int start_x = (int) (element.getSize().width * inicio);
		int end_x = (int) (element.getSize().width * fim);
//		new TouchAction(getDriver()).press(start_x, y).waitAction(Duration.ofMillis(500)).moveTo(end_x, y).release()
//				.perform();
	}

	/**
	 * Salva evidencia em report @param @throws
	 */
	public static void salvarEvidencia(String description) {
		try {
			String screenshot = tirarScreenShot();
			TestInitializeMobile.getEvidenciaMobile().add(new NextEvidencia(description, screenshot));
			Allure.addAttachment(description,
					new ByteArrayInputStream((byte[]) OutputType.BYTES.convertFromBase64Png(screenshot)));
		} catch (Exception ex) {
			Reporter.log("Não salva evidencia");
			ex.getMessage();
		}
		logarPasso(description);
	}

	private static String tirarScreenShot() {
		return (String) ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BASE64);
	}

	public static void logarPasso(String texto) {
		Reporter.log("[PASSO] " + texto, true);
	}

	/**
	 * Escrever texto @param @throws
	 */
	public static <T> void escreverTexto(T elemento, String texto, String mensagemErro) throws Exception {
		try {
			if (elemento instanceof By) {
				tocarElemento(elemento, mensagemErro);
				esconderTeclado();
				((MobileElement) DriverFactory.getDriver().findElement((By) elemento))
						.sendKeys(new CharSequence[] { texto });
				return;
			}
			tocarElemento(elemento, mensagemErro);
			esconderTeclado();
			((MobileElement) elemento).sendKeys(new CharSequence[] { texto });
		} catch (Exception ex) {
			Settings.Logs
					.Write("Messagem no local do elemento erro : " + mensagemErro + "\nException: " + ex.getMessage());
		}
	}

	/**
	 * Retorna Texto @param @throws
	 */
	public static <T> String retornarTexto(T elemento, String mensagemErro) throws Exception {
		try {
			if (elemento instanceof By)
				return ((MobileElement) DriverFactory.getDriver().findElement((By) elemento)).getText();
			return ((MobileElement) elemento).getText();
		} catch (Exception ex) {
			Settings.Logs
					.Write("Messagem no local do elemento erro : " + mensagemErro + "\nException: " + ex.getMessage());
			return null;
		}
	}

	/**
	 * Tocar no elemento @param @throws
	 */
	public static <T> void tocarElemento(T elemento, String mensagemErro) throws Exception {
		try {
			if (elemento instanceof By) {
				((MobileElement) DriverFactory.getDriver().findElement((By) elemento)).click();
				return;
			}
			((MobileElement) elemento).click();
		} catch (Exception ex) {
			Settings.Logs
					.Write("Messagem no local do elemento erro : " + mensagemErro + "\nException: " + ex.getMessage());
		}
	}

	/**
	 * Pressionar elemento no combo @param @throws
	 */
	public static <T> void pressionarValorCombo(T elemento, String valor, String mensagemErro) throws Exception {
		try {
			if (elemento instanceof By) {
				((MobileElement) DriverFactory.getDriver().findElement((By) elemento)).click();
				tocarTexto(valor, mensagemErro);
				return;
			}
			((MobileElement) elemento).click();
			tocarTexto(valor, mensagemErro);
		} catch (Exception ex) {
			Settings.Logs
					.Write("Messagem no local do elemento erro : " + mensagemErro + "\nException: " + ex.getMessage());
		}
	}

	/**
	 * Tocar no texto @param @throws
	 */
	public static void tocarTexto(String texto, String mensagemErro) throws Exception {
		try {
			((MobileElement) DriverFactory.getDriver().findElement(By.xpath("//*[@text='" + texto + "']"))).click();
		} catch (Exception ex) {
			Settings.Logs
					.Write("Messagem no local do elemento erro : " + mensagemErro + "\nException: " + ex.getMessage());
		}
	}

	/**
	 * retorna elemento @param @throws
	 */
	public static MobileElement retornarElemento(By by, String mensagemErro) throws Exception {
		try {
			return (MobileElement) DriverFactory.getDriver().findElement(by);
		} catch (Exception ex) {
			Settings.Logs
					.Write("Messagem no local do elemento erro : " + mensagemErro + "\nException: " + ex.getMessage());
			return null;
		}
	}

	/**
	 * validar Atributo@param @throws
	 */
	public static void validarAtributoBoleano(By by, String atributo, String condicao, String mensagemErro)
			throws Exception {
		try {
			Assert.assertTrue(retornarElemento(by, mensagemErro).getAttribute(atributo).equals(condicao));
		} catch (Exception ex) {
			Settings.Logs
					.Write("Messagem no local do elemento erro : " + mensagemErro + "\nException: " + ex.getMessage());
		}
	}

	/**
	 * validar Atributo@param @throws
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
	 * Tocar por coordenada @param @throws
	 */
	public static void tocarCoordenada(int x, int y) {
		(new TouchAction((PerformsTouchActions) DriverFactory.getDriver())).tap(PointOption.point(x, y)).perform();
	}

	/**
	 * Tocar por coordenada mais mensagem de texto@param @throws
	 */
	public static void tocarCoordenada(WebElement elemento, int x, int y, String mensagemErro) throws Exception {
		try {
			(new TouchAction((PerformsTouchActions) DriverFactory.getDriver()))
					.tap((TapOptions) TapOptions.tapOptions().withElement(ElementOption.element(elemento, x, y)))

					.perform();
		} catch (Exception ex) {
			Settings.Logs
					.Write("Messagem no local do elemento erro : " + mensagemErro + "\nException: " + ex.getMessage());
		}
	}

	/**
	 * mantenha pressionado @param @throws
	 */
	public static <T> void manterPressionado(T elemento, int duracao, String mensagemErro) throws Exception {
		try {
			if (elemento instanceof By) {
				(new TouchAction((PerformsTouchActions) DriverFactory.getDriver()))
						.longPress(((LongPressOptions) LongPressOptions.longPressOptions().withElement(
								ElementOption.element(DriverFactory.getDriver().findElement((By) elemento))))
										.withDuration(Duration.ofMillis(duracao)))
						.release().perform();
			} else {
				(new TouchAction((PerformsTouchActions) DriverFactory.getDriver()))
						.longPress(((LongPressOptions) LongPressOptions.longPressOptions()
								.withElement(ElementOption.element((WebElement) elemento)))
										.withDuration(Duration.ofMillis(duracao)))
						.release().perform();
			}
		} catch (Exception ex) {
			Settings.Logs
					.Write("Messagem no local do elemento erro : " + mensagemErro + "\nException: " + ex.getMessage());
		}
	}

	/**
	 * Obter o tamanho da tela @param @throws
	 */
	public static Dimension retornaDimensaoTela() {
		Dimension size;
		return size = DriverFactory.getDriver().manage().window().getSize();
	}

	/**
	 * Rolar tela pra baixo @param @throws
	 * 
	 * @return
	 */
	public static void rolaTelaParaBaixo() {
		rolagemDeTela(0.9, 0.1);
	}

	/**
	 * Rolar tela pra cima @param @throws
	 */
	public static void rolaTelaParaCima() {
		rolagemDeTela(0.1, 0.9);
	}

	/**
	 * modificar Rolar tela @param @throws
	 */

	public static void rolagemDeTela(double inicio, double fim) {
		Dimension size = getDriver().manage().window().getSize();
		int x = size.width / 2;
		int start_y = (int) (size.height * inicio);
		int end_y = (int) (size.height * fim);

		new TouchAction((PerformsTouchActions) DriverFactory.getDriver()).press(PointOption.point(x, start_y))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(500L))).moveTo(PointOption.point(x, end_y))
				.release().perform();
	}

	/**
	 * Deslizer tela @param @throws
	 */
	public static void deslizar(double inicio, double fim) {
		Dimension size = retornaDimensaoTela();
		int y = size.height / 2;
		int xInicial = (int) (size.width * inicio);
		int xFinal = (int) (size.width * fim);
		(new TouchAction((PerformsTouchActions) DriverFactory.getDriver())).press(PointOption.point(xInicial, y))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(500L))).moveTo(PointOption.point(xFinal, y))
				.release().perform();
	}

	/**
	 * Tocar tela proporcional @param @throws
	 */
	public static void tocarCoordenadaProporcional(int percentualX, int percentualY) {
		Dimension dimensao = retornaDimensaoTela();
		double x = percentualX / 100.0D * dimensao.width;
		double y = percentualY / 100.0D * dimensao.height;
		tocarCoordenada((int) x, (int) y);
	}

	/**
	 * Deslizar para esquerda @param @throws
	 */
	public static void deslizarParaEsquerda() {
		deslizar(0.9D, 0.1D);
	}

	/**
	 * Deslizar para esquerda com elemento @param @throws
	 */
	public static void deslizarParaEsquerda(By by) {
		deslizar(by, 0.9D, 0.1D);
	}

	/**
	 * deslizar para direita @param @throws
	 */
	public static void deslizarParaDireita() {
		deslizar(0.1D, 0.9D);
	}

	/**
	 * Deslizar para direita com elemento @param @throws
	 */
	public static void deslizarParaDireita(By by) {
		deslizar(by, 0.9D, 0.1D);
	}

	/**
	 * Deslizar elememento para cima ou baixo @param @throws
	 */
	public static void deslizar(By by, double inicio, double fim) {
		MobileElement elemento = (MobileElement) DriverFactory.getDriver().findElement(by);
		int y = (elemento.getLocation()).y + (elemento.getSize()).height / 2;
		int xInicial = (int) ((elemento.getSize()).width * inicio);
		int xFinal = (int) ((elemento.getSize()).width * fim);
		(new TouchAction((PerformsTouchActions) DriverFactory.getDriver())).press(PointOption.point(xInicial, y))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(500L))).moveTo(PointOption.point(xFinal, y))
				.release().perform();
	}

	/**
	 * Arrastar elemento @param @throws
	 */
	public static void arrastarElemento(By origem, By destino) {
		MobileElement de = (MobileElement) DriverFactory.getDriver().findElement(origem);
		MobileElement para = (MobileElement) DriverFactory.getDriver().findElement(destino);
		int y = (para.getLocation()).y;
		int x = (para.getSize()).width;
		(new TouchAction((PerformsTouchActions) DriverFactory.getDriver()))
				.longPress((LongPressOptions) LongPressOptions.longPressOptions()
						.withElement(ElementOption.element((WebElement) de)))

				.moveTo(PointOption.point(x, y))

				.release().perform();
	}

	/**
	 * Esconder teclado @param @throws
	 */
	public static void esconderTeclado() throws Exception {
//		if (DriverFactory.getDriver() instanceof io.appium.java_client.ios.IOSDriver) {
		if (verificaPresencaElemento(By.id("OK"))) {
			tocarElemento(By.id("OK"), "Nfoi possesconder o teclado tocando em OK");
		} else {
			tocarCoordenadaProporcional(99, 45);
		}
//		} else {
//			DriverFactory.getDriver().hideKeyboard();
//		}
	}

	/**
	 * verificar elemento presente
	 */
	public static boolean verificaPresencaElemento(By by) {
		try {
			DriverFactory.getDriver().findElement(by);
			return true;
		} catch (NoSuchElementException ignored) {
			return false;
		}
	}

	/**
	 * Volta tela anterior @param @throws
	 */
	public static void voltarTelaAnterior() {
		DriverFactory.getDriver().navigate().back();
	}

	/**
	 * Aguarda carregar elemento @param @throws
	 */
	public static boolean aguardarCarregamentoElemento(MobileElement elemento) {
		try {
			WebDriverWait wait = new WebDriverWait((WebDriver) DriverFactory.getDriver(), 15L);
			wait.until((Function) ExpectedConditions.visibilityOf((WebElement) elemento));
			boolean elementoPresente = elemento.isDisplayed();
			return elementoPresente;
		} catch (Exception e) {
			boolean elementoPresente = false;
			return elementoPresente;
		}
	}

	/**
	 * Retorna texto por indice @param @throws
	 */
	public static String retornarTextoPorIndice(By by, String mensagemErro, int index) throws Exception {
		try {
			return ((MobileElement) DriverFactory.getDriver().findElements(by).get(index)).getText();
		} catch (Exception ex) {
			Settings.Logs
					.Write("Messagem no local do elemento erro : " + mensagemErro + "\nException: " + ex.getMessage());
		}
		return mensagemErro;
	}

	/**
	 * Retorna lista @param @throws
	 */
	public static List<MobileElement> retornarLista(By by, String mensagemErro) {
		try {
			return DriverFactory.getDriver().findElements(by);
		} catch (Exception ex) {
			Settings.Logs
					.Write("Messagem no local do elemento erro : " + mensagemErro + "\nException: " + ex.getMessage());
		}
		return null;
	}

	/**
	 * Apagar campo texto @param @throws
	 */
	public static <T> void apagarCampoTexto(T elemento, String mensagemErro) {
		try {
			if (elemento instanceof By) {
				((MobileElement) DriverFactory.getDriver().findElement((By) elemento)).clear();
				return;
			}
			((MobileElement) elemento).clear();
		} catch (Exception ex) {
			Settings.Logs
					.Write("Messagem no local do elemento erro : " + mensagemErro + "\nException: " + ex.getMessage());
		}
	}

	/**
	 * Volta App IOS @param @throws
	 */
	public static void voltarAppIos() {
		if (DriverFactory.getDriver() instanceof io.appium.java_client.ios.IOSDriver)
			((MobileElement) DriverFactory.getDriver().findElement((By) new MobileBy.ByAccessibilityId("breadcrumb")))
					.click();
	}
}
