package br5.apoio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GeradorDeCT2 {
	
	public static String CriarTextoCT(String funcao) throws IOException{
	
		String texto = SwitchAutomatizador.CriadorDeTexto(funcao);
		return texto;
	}


//public static void CriarCT(String ct, String canal, String funcaoTeste, String objetivo, String detalhe,String descricao, String[] comandos) throws IOException{

	public static void CriarCT(String ct, String canal, String funcaoTeste, String objetivo, String detalhe,String descricao) throws IOException{
	
	System.out.println("Iniciado a Criação do CT: " + ct);
	//criação de CT
	
	String endereco = "C:\\Users\\Public\\Documents\\Projetos\\BR5_wipro_homolog\\BR5\\br5\\src\\br5\\ct";
	String enderecoMassa = "C:\\Users\\Public\\Documents\\Projetos\\BR5_wipro_homolog\\BR5\\br5\\src\\br5\\massadados";
	
	//Criação da pasta
	String caminhoMassa = enderecoMassa + "\\" + canal + "\\" + funcaoTeste;
	String caminhoCT = endereco + "\\" + canal + "\\" + funcaoTeste;

		//cria a pasta do projeto
		if (!new File(caminhoCT).exists()) {
            new File(caminhoCT).mkdir();
            new File(caminhoMassa).mkdir();
            System.out.println("Pasta dos CTs e Massa criada com sucesso");

        } else {
            System.out.println("Pasta caminho já existe");
        }
		
	
		//cria a pasta do objetivo
		caminhoMassa = (caminhoMassa + "\\" + objetivo);
		caminhoCT = (caminhoCT + "\\" + objetivo);
		if (!new File(caminhoCT).exists()) {
            new File(caminhoCT).mkdir();
            new File(caminhoMassa).mkdir();
            System.out.println("Pasta dos CTs e Massa  com Objetivo criada com sucesso");

        } else {
            System.out.println("Pasta caminho já existe");
        }
		
		//cria a pasta do Detalhe
				caminhoMassa = (caminhoMassa + "\\" + detalhe);
				caminhoCT = (caminhoCT + "\\" + detalhe);
				if (!new File(caminhoCT).exists()) {
		            new File(caminhoCT).mkdir();
		            new File(caminhoMassa).mkdir();
		            System.out.println("Pasta dos CTs e Massa  com Objetivo criada com sucesso");

		        } else {
		            System.out.println("Pasta caminho já existe");
		        }
				
				
	//Criando o CT e Massa
	String caminhoArquivoCT = caminhoCT + "\\" + ct + ".java";
	String caminhoArquivoMassa = caminhoMassa + "\\" + ct + ".json";
		if (!new File(caminhoArquivoCT).exists()) {
					
		File ctArquivo = new File(caminhoArquivoCT);
		ctArquivo.createNewFile();
			
		File ctMassa = new File(caminhoArquivoMassa);
		ctMassa.createNewFile();
		System.out.println("Arquivos criados com sucesso ( Massa e CT )");
		} else {
            System.out.println("Arquivos já existem");
		}

	//Criando conteudo do CT
	FileWriter conteudoCT = new FileWriter(caminhoArquivoCT, true);
	BufferedWriter bw = new BufferedWriter(conteudoCT);
	
		bw.write("package br5.ct." + canal + "." + funcaoTeste + "." + objetivo + "." + detalhe + ";");
		bw.newLine();
		bw.newLine();
		bw.newLine();
		bw.write("import br.com.itau.sqt.apoio.componente.ModeloCasoTeste;");
		bw.newLine();
		bw.write("import br.com.itau.sqt.apoio.exception.ExecucaoTesteException;");
		bw.newLine();
		bw.write("import br.com.itau.sqt.apoio.ControleTeste;");
		bw.newLine();
		bw.write("import br.com.itau.sqt.apoio.massa.ILeitorMassaDados.TipoMassa;");
		bw.newLine();
		bw.write("import br5.aplicacao.CEI;");
		bw.newLine();
		bw.write("import br5.func.Acesso;");
		bw.newLine();
		bw.write("import br5.telas.TelaGeral;");
		bw.newLine();
		bw.newLine();
		bw.write("public class "+ ct +" extends ModeloCasoTeste{");
		bw.newLine();
		bw.newLine();
		bw.write("public "+ ct +" () {super(\""+ ct +"\", TipoMassa.PADRAO_JSON);");
		bw.newLine();
		bw.write("}");
		bw.newLine();
		bw.newLine();
		bw.write("@Override");
		bw.newLine();
		bw.write("public void executarCasoTeste(Object[] arg0) throws ExecucaoTesteException, Exception {");
		bw.newLine();
		bw.newLine();
		bw.write("CEI aplicacao = new CEI();");
		bw.newLine();
		bw.write("Acesso acesso = new Acesso();");
		bw.newLine();
		bw.write("TelaGeral tela = new TelaGeral();");
		bw.newLine();
		bw.newLine();
		bw.newLine();
		bw.newLine();
		bw.write("registrarDescricaoCaso(\"" + ct + " - " + descricao + "\");");
		bw.newLine();
		bw.newLine();
		bw.write("try {");
		bw.newLine();
		bw.newLine();
		bw.write("ControleTeste.logInfo(\"Abrindo Aplicação para inicio do teste\");");
		bw.newLine();
		bw.newLine();
		bw.write("aplicacao.iniciar();");
		bw.newLine();
		bw.newLine();
		bw.write("//inicio do teste");
		bw.newLine();
		bw.newLine();
		bw.newLine();
		
//		bw.newLine();
//			int i = 0;
//			while (i < comandos.length) {
//				String texto = SwitchAutomatizador.CriadorDeTexto(comandos[i]);
//				bw.write("ControleTeste.logInfo(\"Step - " + comandos[i] + "\");");
//				bw.newLine();
//				bw.write(texto);
//				bw.newLine();
//				bw.newLine();
//				i++;
//			}
		bw.newLine();
		bw.newLine();
		bw.newLine();
		bw.newLine();
		bw.write("}catch(Exception e) {");
		bw.newLine();
		bw.write("ControleTeste.logInfo(\"Falha no teste " + ct + "\");");
		bw.newLine();
		bw.write("throw e;");
		bw.newLine();
		bw.write("}");
		bw.newLine();
		bw.write("}");
		bw.newLine();
		bw.write("}");
		bw.close();
		conteudoCT.close();
		System.out.println("Arquivo CT criado com sucesso");
		
		//criando conteudo da Massa
		FileWriter conteudoMassa = new FileWriter(caminhoArquivoMassa, true);
		BufferedWriter bw2 = new BufferedWriter(conteudoMassa);
		bw2.write("{");
		bw2.newLine();
		bw2.write("\"nomeCartao\":\"\",");
		bw2.newLine();
		bw2.write("\"senhaCartao\":\"147258\"");
		bw2.newLine();
		bw2.write("}");
		bw2.close();
		conteudoMassa.close();	
		
		System.out.println(ct + " criado com sucesso");
		
	}
}
