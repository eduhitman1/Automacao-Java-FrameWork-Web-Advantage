package com.edsoft.framework.listeners.mobile;

import java.io.IOException;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.edsoft.framework.base.mobile.TestInitializeMobile;
import com.edsoft.framework.base.web.TestInitializeWeb;
import com.edsoft.framework.tools.evidencia.RelatorioEvidencia;
import com.edsoft.framework.tools.evidencia.TipoEvidencia;
import com.edsoft.framework.tools.evidencia.relatorio.GeradorRelatorioEvidencia;

public class ListenerTestMobile implements ITestListener {

	/**
	 * onTestStart inicialização de report do test
	 */
	public void onTestStart(ITestResult result) {
		Reporter.log("Iniciado: " + result.getName(), true);
		Reporter.log("===============================================", true);
	}

	/**
	 * onTestSucess report do test sucess, gerado relatório final
	 */
	public void onTestSuccess(ITestResult result) {
		Reporter.log(result.getName() + " executado com sucesso!", true);
		Reporter.log("===============================================\n", true);
		gerarEvidencia("");
	}

	/**
	 * onTestFailure report do test failure, gerado relatório final
	 */
	public void onTestFailure(ITestResult result) {
		Reporter.log("===============================================", true);
		Reporter.log("O caso de teste " + result.getName() + " falhou!", true);
		List<String> log = Reporter.getOutput(result);
		gerarEvidencia(result.getThrowable().getMessage());
	}

	/**
	 * onTestSkipped report do test ignorado.
	 */
	public void onTestSkipped(ITestResult result) {
		Reporter.log("===============================================", true);
		Reporter.log("O caso de teste " + result.getName() + " foi ignorado!", true);
	}

	/**
	 * onTestFailedButWithinSuccessPercentage porcentagem result.
	 */
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	/**
	 * onStart start no contexto de logs
	 */	
	public void onStart(ITestContext context) {
	}
	/**
	 * onFinish start no contexto de logs final
	 */
	public void onFinish(ITestContext context) {
	}
	
	/**
	 * gerarEvidencia metodo de finalização de cenario de test
	 * recupera todos os dados para report de teste
	 * @param erros
	 */
	private synchronized void gerarEvidencia(String erros) {
	    if (TestInitializeMobile.getCasoTesteMobile().getTipoTeste().equals("frontend")) {
	      RelatorioEvidencia relatorio = null;
	      relatorio = new RelatorioEvidencia(TestInitializeMobile.getEvidenciaMobile(), TestInitializeMobile.getCasoTesteMobile().getCasoId(), TestInitializeMobile.getCasoTesteMobile().getTester(), TestInitializeMobile.getCasoTesteMobile().getJornadaCt(), erros, TestInitializeMobile.getCasoTesteMobile().getNomeCasoTeste(), TestInitializeMobile.getCasoTesteMobile().getObjetivoTeste(), TestInitializeMobile.getCasoTesteMobile().getPlataformaCt());
	      try {
	        GeradorRelatorioEvidencia.gerarRelatorioEvidencia(relatorio, TipoEvidencia.PDF, TestInitializeMobile.getCasoTesteMobile().getNomeArquivoEvidencia());
	      } catch (IOException e) {
	        e.printStackTrace();
	      } 
	    } else if (TestInitializeWeb.getCasoTesteWeb().getTipoTeste().equals("backend")) {
	    // inserir report por api
	    } 
	  }
}
