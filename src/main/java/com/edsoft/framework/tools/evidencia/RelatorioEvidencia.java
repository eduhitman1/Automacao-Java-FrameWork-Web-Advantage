package com.edsoft.framework.tools.evidencia;

import java.util.List;

public class RelatorioEvidencia {
	/**
	 * RelatorioEvidencia atributos de relatório final
	 */
	private List<NextEvidencia> evidenceList = null;

	private String casoTeste = null;

	private String tester = null;

	private String jornada = null;

	private String exceptionString = null;

	private String nomeCt = null;

	private String objetivoCt = null;

	private String plataformaCt = null;

	public RelatorioEvidencia(List<NextEvidencia> evidenceList, String casoTeste, String tester, String jornada,
			String exceptionString, String nomeCt, String objetivoCt, String plataformaCt) {
		this.evidenceList = evidenceList;
		this.casoTeste = casoTeste;
		this.tester = tester;
		this.jornada = jornada;
		this.exceptionString = exceptionString;
		this.nomeCt = nomeCt;
		this.objetivoCt = objetivoCt;
		this.plataformaCt = plataformaCt;
	}

	public List<NextEvidencia> getEvidenceList() {
		return this.evidenceList;
	}

	public void setEvidenceList(List<NextEvidencia> evidenceList) {
		this.evidenceList = evidenceList;
	}

	public String getCasoTeste() {
		return this.casoTeste;
	}

	public void setCasoTeste(String casoTeste) {
		this.casoTeste = casoTeste;
	}

	public String getTester() {
		return this.tester;
	}

	public void setTester(String tester) {
		this.tester = tester;
	}

	public String getJornada() {
		return this.jornada;
	}

	public void setJornada(String jornada) {
		this.jornada = jornada;
	}

	public String getExceptionString() {
		return this.exceptionString;
	}

	public void setExceptionString(String exceptionString) {
		this.exceptionString = exceptionString;
	}

	public String getNomeCt() {
		return this.nomeCt;
	}

	public void setNomeCt(String nomeCt) {
		this.nomeCt = nomeCt;
	}

	public String getObjetivoCt() {
		return this.objetivoCt;
	}

	public void setObjetivoCt(String objetivoCt) {
		this.objetivoCt = objetivoCt;
	}

	public String getPlataformaCt() {
		return this.plataformaCt;
	}

	public void setPlataformaCt(String plataformaCt) {
		this.plataformaCt = plataformaCt;
	}
}
