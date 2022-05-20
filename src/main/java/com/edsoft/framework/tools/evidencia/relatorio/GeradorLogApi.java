package com.edsoft.framework.tools.evidencia.relatorio;

import com.edsoft.framework.base.mobile.TestInitializeMobile;
import com.edsoft.framework.configs.Settings;

import com.edsoft.framework.utilities.Utilitarios;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GeradorLogApi {
  public static void geradorLogApi(List<String> log) {
    String caminhoEvidencia = Settings.path_evidencia.toLowerCase();
    String caminhoJornada = (Settings.path_evidencia + TestInitializeMobile.getCasoTesteMobile().getJornadaCt() + "/").toLowerCase();
    String caminhoEvidenciaCt = (Settings.path_evidencia + TestInitializeMobile.getCasoTesteMobile().getJornadaCt() + "/" + TestInitializeMobile.getCasoTesteMobile().getCasoId() + "/").toLowerCase();
    GeradorRelatorioEvidencia.criarDiretorioEvidencia(caminhoEvidencia);
    GeradorRelatorioEvidencia.criarDiretorioEvidencia(caminhoJornada);
    GeradorRelatorioEvidencia.criarDiretorioEvidencia(caminhoEvidenciaCt);
    FileWriter writer = null;
    try {
      writer = new FileWriter(new File(caminhoEvidenciaCt + TestInitializeMobile.getCasoTesteMobile().getCasoId() + "_" + Utilitarios.pegarDataAtualEpoch() + ".txt"));
    } catch (IOException e) {
      e.printStackTrace();
    } 
    for (String str : log) {
      try {
        writer.write(str + System.lineSeparator());
      } catch (IOException e) {
        e.printStackTrace();
      } 
    } 
    try {
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
}


