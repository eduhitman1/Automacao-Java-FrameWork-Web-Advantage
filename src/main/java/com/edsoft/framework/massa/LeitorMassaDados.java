package com.edsoft.framework.massa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Reporter;

import com.edsoft.framework.configs.Settings;

public class LeitorMassaDados {
	/**
	 * LeitorMassaDados class de leitura de massa Json
	 */
	public ThreadLocal<JSONObject> massaDados = null;

	/**
	 * Metodo que recupera inicia o metodo lerArquivo e retorna o caso de teste
	 */
	public LeitorMassaDados(String casoTeste) {
		lerArquivo(casoTeste);
	}

	private void lerArquivo(String casoTeste) {
		JSONParser jsonParser = new JSONParser();
		Collection<File> files = FileUtils.listFiles(new File(Settings.path_MassaJson), new String[] { "json" }, true);
		for (File file : files) {
			if (file.getAbsolutePath().contains("CT" + casoTeste + ".json"))
				try {
					FileReader fileReader = new FileReader(file);
					JSONArray jsonObject = (JSONArray) jsonParser.parse(fileReader);
					jsonObject.forEach(json -> gerarMassaDados((JSONObject) json, "CT" + casoTeste));
				} catch (ParseException e) {
					Reporter.log("Falha ao parsear arquivo de massa! Verifique a tipagem das varido objeto de massa.",
							true);
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					Reporter.log(
							"Arquivo de massa nao encontrado! Verfique se o arquivo \"CT" + casoTeste + "\" existe.",
							true);
					e.printStackTrace();
				} catch (IOException e) {
					Reporter.log("Falha ao ler dados da massa! Verifique a estrutura do arquivo", true);
					e.printStackTrace();
				} catch (NullPointerException e) {
					Reporter.log("Falha ao ler dados da massa! Verifique se o ID do CT esta correto no JSON", true);
					e.printStackTrace();
				}
		}
	}

	/**
	 * getMassaDados recupera massa json
	 */
	public JSONObject getMassaDados() {
		return this.massaDados.get();
	}

	/**
	 * setMassaDados retorno da massa json
	 */
	private void setMassaDados(JSONObject jsonObject) {
		if (this.massaDados == null)
			this.massaDados = new ThreadLocal<>();
		this.massaDados.set(new JSONObject((Map) jsonObject));
	}

	/**
	 * gerarMassaDados gerado de massa Json
	 */
	private void gerarMassaDados(JSONObject jsonObject, String nomeCasoTeste) {
		setMassaDados((JSONObject) jsonObject.get(nomeCasoTeste));
	}
}
