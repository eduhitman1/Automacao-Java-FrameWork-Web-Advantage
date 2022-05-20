package com.edsoft.framework.utilities;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class LeitorPdf {

	/**
	 *  Leitor de pdf
	 *  @param path
	 */
	public String lerDocumento(String path) throws IOException {
    File file = new File(path);
    PDDocument document = PDDocument.load(file);
    PDFTextStripper pdfTextStripper = new PDFTextStripper();
    String texto = pdfTextStripper.getText(document);
    document.close();
    return texto;
  }
}
