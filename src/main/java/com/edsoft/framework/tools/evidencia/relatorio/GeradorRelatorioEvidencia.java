package com.edsoft.framework.tools.evidencia.relatorio;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import com.edsoft.framework.configs.Settings;
import com.edsoft.framework.tools.evidencia.NextEvidencia;
import com.edsoft.framework.tools.evidencia.RelatorioEvidencia;
import com.edsoft.framework.tools.evidencia.TipoEvidencia;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import static com.edsoft.framework.base.mobile.TestInitializeMobile.getCasoTesteMobile;
import static com.edsoft.framework.base.web.TestInitializeWeb.getCasoTesteWeb;

public class GeradorRelatorioEvidencia {
    public GeradorRelatorioEvidencia() {
    }

    public static void gerarRelatorioEvidencia(RelatorioEvidencia relatorioEvidencia, TipoEvidencia tipoEvidencia, String nomeArquivo) throws IOException {
        List<NextEvidencia> data = relatorioEvidencia.getEvidenceList();
        String caminhoEvidencia = Settings.path_evidencia.toLowerCase();
        String caminhoJornada = (Settings.path_evidencia + relatorioEvidencia.getJornada() + "/").toLowerCase();
        String caminhoEvidenciaCt = (Settings.path_evidencia + relatorioEvidencia.getJornada() + "/" + relatorioEvidencia.getCasoTeste() + "/").toLowerCase();
        criarDiretorioEvidencia(caminhoEvidencia);
        criarDiretorioEvidencia(caminhoJornada);
        criarDiretorioEvidencia(caminhoEvidenciaCt);

        try {
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();
            BufferedImage imagemNext = ImageIO.read(classLoader.getResourceAsStream(Settings.path_logo));
            BufferedImage imageClient = ImageIO.read(classLoader.getResourceAsStream(Settings.path_qa));
            String reportName = relatorioEvidencia.getCasoTeste();
            if (reportName == null) {
                reportName = "";
            }

            String tester = relatorioEvidencia.getTester();
            if (tester == null) {
                tester = "";
            }

            String nomeCt = relatorioEvidencia.getNomeCt();
            if (nomeCt == null) {
                nomeCt = "";
            }

            String objetivoCt = relatorioEvidencia.getObjetivoCt();
            if (objetivoCt == null) {
                objetivoCt = "";
            }

            String jornada = relatorioEvidencia.getJornada();
            if (jornada == null) {
                jornada = "";
            }

            String excecao = relatorioEvidencia.getExceptionString();
            if (excecao == null) {
                excecao = "";
            }

            Map<String, Object> parameters = new HashMap();
            if (excecao != null) {
                parameters.put("NEXT_EXCEPTION", excecao);
            }

            parameters.put("NEXT_COMPANY_LOGO", imagemNext);
            parameters.put("NEXT_CUSTOMER_LOGO", imageClient);
            parameters.put("NEXT_PROJECT", " " + jornada.toLowerCase());
            parameters.put("NEXT_TESTER", " " + tester);
            parameters.put("NEXT_LABEL_EVIDENCE_TITLE", "Edsoft - Evidencia de Teste");
            parameters.put("NEXT_LABEL_PROJECT", " Jornada:");
            parameters.put("NEXT_LABEL_TESTER", " Tester:");
            parameters.put("NEXT_LABEL_STATUS", " Resultado:");
            parameters.put("NEXT_LABEL_PASS", "SUCESSO");
            parameters.put("NEXT_LABEL_FAILED", "FALHA");
            parameters.put("NEXT_LABEL_EVIDENCE_REPORT", reportName);
            parameters.put("NEXT_LABEL_DATE", " Data:");
            parameters.put("NEXT_LABEL_FOOTER", "Automação de Testes - Edsoft");
            parameters.put("NEXT_LABEL_ERROR_DETAIL", "Detalhes do erro");
            parameters.put("NEXT_LABEL_PAGE", "Página");
            parameters.put("NEXT_LABEL_COMPANY_NAME", "QA-" + reportName);
            parameters.put("NEXT_LABEL_CT_NAME", nomeCt);
            parameters.put("NEXT_LABEL_CT_DESCRIPTION", objetivoCt);
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);
            String tipoModelo = null;
            if (Settings.path_modelo_report_tipo.equals("mobile")) {
            	tipoModelo = "path_modelo_report_mobile";
            } else {
            	tipoModelo = "path_modelo_report_web";
            } 
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(classLoader.getResourceAsStream(Settings.path_modelo_report_web), parameters, dataSource);
            switch(tipoEvidencia) {
            case PDF:
                JasperExportManager.exportReportToPdfFile(jasperPrint, caminhoEvidenciaCt + nomeArquivo + ".pdf");
                break;
            case HTML:
                JasperExportManager.exportReportToHtmlFile(jasperPrint, caminhoEvidenciaCt + nomeArquivo + ".html");
            }
        } catch (SecurityException var20) {
        } catch (JRException var21) {
            var21.getMessage();
            System.out.println("erro report: "+var21.getMessage());
            
        }

    }

    public static boolean criarDiretorioEvidencia(String diretorio) {
        boolean dirExiste = false;

        try {
            File dir = new File(diretorio);
            boolean existe = dir.exists();
            if (!existe) {
                dir.mkdir();
                dirExiste = false;
            } else {
                dirExiste = true;
            }
        } catch (SecurityException var4) {
            var4.printStackTrace();
        }

        return dirExiste;
    }
}
