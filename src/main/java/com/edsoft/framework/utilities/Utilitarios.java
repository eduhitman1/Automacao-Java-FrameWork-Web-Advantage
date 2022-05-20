package com.edsoft.framework.utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

public class Utilitarios {
	public static String pegarDataAtualEpoch() {
		return String.valueOf(Instant.now().toEpochMilli());
	}
	/**
	 * gerador de cpf
	 */
	public static String gerarCpf() {
		String iniciais = "";
		for (int i = 0; i < 9; i++) {
			Integer numero = new Integer((int) (Math.random() * 10.0D));
			iniciais = iniciais + numero.toString();
		}
		return iniciais + calcDigVerif(iniciais);
	}

	/**
	 *  calcular o difito de verificação
	 */
	private static String calcDigVerif(String numero) {
		Integer primDig, segDig;
		int soma = 0, peso = 10;
		int i;
		for (i = 0; i < numero.length(); i++)
			soma += Integer.parseInt(numero.substring(i, i + 1)) * peso--;
		if ((((soma % 11 == 0) ? 1 : 0) | ((soma % 11 == 1) ? 1 : 0)) != 0) {
			primDig = new Integer(0);
		} else {
			primDig = new Integer(11 - soma % 11);
		}
		soma = 0;
		peso = 11;
		for (i = 0; i < numero.length(); i++)
			soma += Integer.parseInt(numero.substring(i, i + 1)) * peso--;
		soma += primDig.intValue() * 2;
		if ((((soma % 11 == 0) ? 1 : 0) | ((soma % 11 == 1) ? 1 : 0)) != 0) {
			segDig = new Integer(0);
		} else {
			segDig = new Integer(11 - soma % 11);
		}
		return primDig.toString() + segDig.toString();
	}

	public static int numeroAleatorio(int minimo, int maximo) {
		return (int) Math.floor(Math.random() * (maximo - minimo + 1)) + minimo;
	}

	/**
	 *  Data atual
	 */
	public static String dataAtual() {
		Date data = Calendar.getInstance().getTime();
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String strData = sdf.format(data);
		return strData;
	}
}
