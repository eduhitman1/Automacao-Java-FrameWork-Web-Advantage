package com.edsoft.framework.base.web;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.edsoft.framework.annotations.Jornada;
import com.edsoft.framework.caso.CasoTeste;
import com.edsoft.framework.configs.ConfigReader;
import com.edsoft.framework.configs.Settings;
import com.edsoft.framework.constantes.Jornadas;
import com.edsoft.framework.constantes.massa.Massa;
import com.edsoft.framework.massa.LeitorMassaDados;
import com.edsoft.framework.tools.evidencia.NextEvidencia;
import com.edsoft.framework.tools.evidencia.RelatorioEvidencia;
import com.edsoft.framework.tools.evidencia.TipoEvidencia;
import com.edsoft.framework.tools.evidencia.relatorio.GeradorRelatorioEvidencia;
import com.edsoft.framework.utilities.DatabaseUtil;
import com.edsoft.framework.utilities.ExcelUtil;
import com.edsoft.framework.utilities.LogUtil;
import com.edsoft.framework.utilities.Utilitarios;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;

public class TestInitializeWeb extends FrameworkInitialize {
	private ThreadLocal<LeitorMassaDados> leitorMassaDadosWeb = null;
	private static ThreadLocal<CasoTeste> casoTesteWeb = null;
	public static ThreadLocal<ArrayList<NextEvidencia>> evidenciaWeb = null;
	public static String nomeSuiteWeb;

	/**
	 * @Param de passagem de de jornada
	 */
	Jornadas jornadaWeb = ((Jornada) getClass().<Jornada>getAnnotation(Jornada.class)).value();

	/**
	 * Metodo que retorna caso de teste
	 */
	public static CasoTeste getCasoTesteWeb() {
		return casoTesteWeb.get();
	}
	
	/**
	 * Metodo que de set no report
	 */
	private void setCasoTesteWeb() {
		if (casoTesteWeb == null)
			casoTesteWeb = new ThreadLocal<>();
		casoTesteWeb.set(new CasoTeste());
	}

	/**
	 * Gerador de evidencia
	 */
	public static ArrayList<NextEvidencia> getEvidenciaWeb() {
		return evidenciaWeb.get();
	}

	/**
	 * Set evidencia Mobile
	 */
	private void setEvidenciaWeb() {
		if (evidenciaWeb == null)
			evidenciaWeb = new ThreadLocal<>();
		evidenciaWeb.set(new ArrayList<>());
	}

	/**
	 * Metodo que retorna dados da massa Json
	 */
	private LeitorMassaDados getLeitorMassaDadosWeb() {
		return this.leitorMassaDadosWeb.get();
	}

	/**
	 * Metodo que set o valor da massa json
	 */
	private void setLeitorMassaDadosWeb(String casoTeste) {
		if (this.leitorMassaDadosWeb == null)
			this.leitorMassaDadosWeb = new ThreadLocal<>();
		this.leitorMassaDadosWeb.set(new LeitorMassaDados(casoTeste));
	}

	/**
	 * Metodo que pega o valor chave da massa como constantes
	 */
	protected String recuperarMassa(Massa chave) {
		return (String) getLeitorMassaDadosWeb().getMassaDados().get(chave.getValue());
	}
	/**
	 * Metodo que pega valor da massa de dados
	 */
	protected String recuperarMassa(String chave) {
		return (String) getLeitorMassaDadosWeb().getMassaDados().get(chave);
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

		Settings.Logs.Write("Browser Initiliazer");
		initializeBrowser(Settings.BrowserType);
		Settings.Logs.Write("Navegated to URL " + Settings.AUT);
		DriverContextWeb.Browser.Maximize().GoToUrl(Settings.AUT);
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
	public void getNomeSuiteWeb(ITestContext contexto) {
		nomeSuiteWeb = contexto.getCurrentXmlTest().getSuite().getName();
	}

	/**
	 * Metodo de Criação da JORNADA
	 */
	@BeforeSuite(dependsOnMethods = { "getNomeSuite" })
	@BeforeClass
	public synchronized void criarCasoTesteWeb() {
		setCasoTesteWeb();
		setEvidenciaWeb();
		getCasoTesteWeb().setCasoId(StringUtils.substringAfter(getClass().getName(), "CT"));
		getCasoTesteWeb().setJornadaCt(((Jornada) getClass().<Jornada>getAnnotation(Jornada.class)).value().toString());
		getCasoTesteWeb().setTester("Eduardo");
		getCasoTesteWeb().setTipoTeste("frontend");
		getCasoTesteWeb().setNomeArquivoEvidencia(
				getCasoTesteWeb().getCasoId() + "_" + "TEST" + "_" + Utilitarios.dataAtual());
		getCasoTesteWeb().setPlataformaCt("web");
		Method[] methods = getClass().getMethods();
		for (Method m : methods) {
			if (m.isAnnotationPresent((Class) Test.class)) {
				Test nomeCt = m.<Test>getAnnotation(Test.class);
				getCasoTesteWeb().setNomeCasoTeste(nomeCt.description());
			}

			if (m.isAnnotationPresent(Description.class)) {
				Description description = (Description) m.getAnnotation(Description.class);
				getCasoTesteWeb().setObjetivoTeste(description.value());
			}

			if (m.isAnnotationPresent(Test.class)) {
				Severity severidade = (Severity) m.getAnnotation(Severity.class);
				getCasoTesteWeb().setSeveridadeCt(severidade.value().toString());
			}
		}

	}

	/**
	 * Metodo que carrega massa Json por ID de CT
	 */
	@BeforeMethod
	public void carregaMassaDadosMobile() {
		setLeitorMassaDadosWeb(getCasoTesteWeb().getCasoId());
	}

	/**
	 * Metodo de set de parametro do driverFactory
	 */
	@AfterTest(alwaysRun = true)
	public void tearDownWeb() {
		Reporter.log("===============================================", true);
		Reporter.log("Finalizando o WEB DRIVER...", true);
		Reporter.log("===============================================", true);
		try {
			DriverContextWeb.Driver.close();
		} catch (NullPointerException exception) {
			Reporter.log("Não foi possivel encerrar o WebDriver!", true);
			exception.getMessage();
		}
		/**
		 * Metodos e variavel para caso de utilização JUNIT
		 */
//		RelatorioEvidencia relatorio = null;
//		relatorio = new RelatorioEvidencia(TestInitializeWeb.getEvidenciaWeb(),
//				TestInitializeWeb.getCasoTesteWeb().getCasoId(), TestInitializeWeb.getCasoTesteWeb().getTester(),
//				TestInitializeWeb.getCasoTesteWeb().getJornadaCt(), null,
//				TestInitializeWeb.getCasoTesteWeb().getNomeCasoTeste(),
//				TestInitializeWeb.getCasoTesteWeb().getObjetivoTeste(),
//				TestInitializeWeb.getCasoTesteWeb().getPlataformaCt());
//		try {
//			GeradorRelatorioEvidencia.gerarRelatorioEvidencia(relatorio, TipoEvidencia.PDF,
//					TestInitializeWeb.getCasoTesteWeb().getNomeArquivoEvidencia());
//		} catch (IOException var5) {
//			var5.printStackTrace();
//		}
	}
}
