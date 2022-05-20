package com.edsoft.framework.base.mobile;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.edsoft.framework.annotations.Jornada;
import com.edsoft.framework.caso.CasoTeste;
import com.edsoft.framework.configs.ConfigReader;
import com.edsoft.framework.configs.Settings;
import com.edsoft.framework.constantes.Jornadas;
import com.edsoft.framework.constantes.massa.Massa;
import com.edsoft.framework.massa.LeitorMassaDados;
import com.edsoft.framework.tools.evidencia.NextEvidencia;
import com.edsoft.framework.utilities.DatabaseUtil;
import com.edsoft.framework.utilities.ExcelUtil;
import com.edsoft.framework.utilities.LogUtil;
import com.edsoft.framework.utilities.Utilitarios;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;

public class TestInitializeMobile extends FrameworkInitialize {
	private ThreadLocal<LeitorMassaDados> leitorMassaDadosMobile = null;
	private static ThreadLocal<CasoTeste> casoTesteMobile = null;
	public static ThreadLocal<ArrayList<NextEvidencia>> evidenciaMobile = null;
	public static String nomeSuiteMobile;

	/**
	 * @Param de passagem de de jornada
	 */
	Jornadas jornadaMobile = ((Jornada) getClass().<Jornada>getAnnotation(Jornada.class)).value();

	/**
	 * Metodo que retorna caso de teste
	 */
	public static CasoTeste getCasoTesteMobile() {
		return casoTesteMobile.get();
	}

	/**
	 * Metodo que de set no report
	 */
	private void setCasoTesteMobile() {
		if (casoTesteMobile == null)
			casoTesteMobile = new ThreadLocal<>();
		casoTesteMobile.set(new CasoTeste());
	}

	/**
	 * Gerador de evidencia
	 */
	public static ArrayList<NextEvidencia> getEvidenciaMobile() {
		return evidenciaMobile.get();
	}

	/**
	 * Set evidencia Mobile
	 */
	private void setEvidenciaMobile() {
		if (evidenciaMobile == null)
			evidenciaMobile = new ThreadLocal<>();
		evidenciaMobile.set(new ArrayList<>());
	}

	/**
	 * Metodo que retorna dados da massa Json
	 */
	private LeitorMassaDados getLeitorMassaDadosMobile() {
		return this.leitorMassaDadosMobile.get();
	}

	/**
	 * Metodo que set o valor da massa json
	 */
	private void setLeitorMassaDadosMobile(String casoTeste) {
		if (this.leitorMassaDadosMobile == null)
			this.leitorMassaDadosMobile = new ThreadLocal<>();
		this.leitorMassaDadosMobile.set(new LeitorMassaDados(casoTeste));
	}

	/**
	 * Metodo que pega o valor chave da massa como constantes
	 */
	protected String recuperarMassa(Massa chave) {
		return (String) getLeitorMassaDadosMobile().getMassaDados().get(chave.getValue());
	}

	/**
	 * Metodo que pega valor da massa de dados
	 */
	protected String recuperarMassa(String chave) {
		return (String) getLeitorMassaDadosMobile().getMassaDados().get(chave);
	}

	/**
	 * Metodo de inicialização do framework com logs de utilidades
	 */
	@BeforeClass
	public void Initialize() throws IOException {
		// Initilialize Config
		ConfigReader.PopulateSettings();
		// Logging
		Settings.Logs = new LogUtil();
		Settings.Logs.CreateLogFile();
		Settings.Logs.Write("Framework Initialize");

		// Create teste database
		Settings.Logs.Write("Conect ao database");
		Settings.AUTConnection = DatabaseUtil.Open(Settings.AUTConnectionString);
		DatabaseUtil.ExecuteQuery("select * from usuario where cod_usu='1'", Settings.AUTConnection);

		try {
			ExcelUtil util = new ExcelUtil(Settings.ExcelSheetPath);
		} catch (Exception e) {
			System.out.println("Erro " + e.getMessage());

		}

	}

	/**
	 * Report de suite TestNG para dar o retorno final
	 */
	@BeforeSuite
	public void getNomeSuiteMobile(ITestContext contexto) {
		nomeSuiteMobile = contexto.getCurrentXmlTest().getSuite().getName();
	}

	@BeforeSuite(dependsOnMethods = { "getNomeSuite" })

	/**
	 * Metodo de Criação da JORNADA
	 */
	@BeforeClass
	public synchronized void criarCasoTesteMobile() {
		setCasoTesteMobile();
		setEvidenciaMobile();
		getCasoTesteMobile().setCasoId(StringUtils.substringAfter(getClass().getName(), "CT"));
		getCasoTesteMobile()
				.setJornadaCt(((Jornada) getClass().<Jornada>getAnnotation(Jornada.class)).value().toString());
		getCasoTesteMobile().setTester("Eduardo");
		getCasoTesteMobile().setTipoTeste("frontend");
		getCasoTesteMobile().setNomeArquivoEvidencia(
				getCasoTesteMobile().getCasoId() + "_" + "TEST" + "_" + Utilitarios.dataAtual());
		getCasoTesteMobile().setPlataformaCt("mobile");
		Method[] methods = getClass().getMethods();
		for (Method m : methods) {
			if (m.isAnnotationPresent((Class) Test.class)) {
				Test nomeCt = m.<Test>getAnnotation(Test.class);
				getCasoTesteMobile().setNomeCasoTeste(nomeCt.description());
			}

			if (m.isAnnotationPresent(Description.class)) {
				Description description = (Description) m.getAnnotation(Description.class);
				getCasoTesteMobile().setObjetivoTeste(description.value());
			}

			if (m.isAnnotationPresent(Test.class)) {
				Severity severidade = (Severity) m.getAnnotation(Severity.class);
				getCasoTesteMobile().setSeveridadeCt(severidade.value().toString());
			}
		}

	}

	/**
	 * Metodo que carrega massa Json por ID de CT
	 */
	@BeforeMethod
	public void carregaMassaDadosWeb() {
		setLeitorMassaDadosMobile(getCasoTesteMobile().getCasoId());
	}

	/**
	 * Metodo de set de parametro do driverFactory
	 */
	@BeforeMethod
	@Parameters({ "platformName", "deviceName", "appPackage", "appActivity" })
	public void setUp(@Optional("") String platformName, @Optional("") String deviceName, String appPackage,
			@Optional("") String appActivity) throws MalformedURLException {
		if (DriverFactory.getDriver() != null) {
			DriverFactory.getDriver().resetApp();
		}
		DriverFactory.constant(platformName, deviceName, appPackage, appActivity);
		DriverFactory.getDriver().resetApp();
	}

	/**
	 * Metodo de finalização e report final.
	 */
	@AfterTest(alwaysRun = true)
	public void tearDown() {
		Reporter.log("===============================================", true);
		Reporter.log("Finalizando o DRIVER...", true);
		Reporter.log("===============================================", true);

		try {
			DriverFactory.getDriver().quit();
		} catch (NullPointerException var2) {
			Reporter.log(
					"Não foi possivel encerrar o driver! Dica: Se a conexao com o dispositivo nao foi encerrada automaticamente, lembre-se de que a reserva do mesmo não foi excluida.",
					true);
			var2.getMessage();
		}

	}
}
