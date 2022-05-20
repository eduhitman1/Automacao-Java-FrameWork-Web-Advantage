package com.edsoft.framework.utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.edsoft.framework.base.web.DriverContextWeb;
import com.edsoft.framework.configs.Settings;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

public class ScreenshotUtil {
	private static Integer i = 2;
	private static Integer j = 1;
	static List<String> files = new ArrayList<String>();
	File pdfRoot = new File(Settings.path_Screenshort);

	public static String getTime() {
		return new SimpleDateFormat("dd_MM_yyyy HH.mm.ss").format(new Date());
	}

	/**
	 *  Screenshot no path informado na configuração
	 */
	public ScreenshotUtil screenShot() throws IOException, DocumentException {
		String fonte = ".png";
		while (i > j) {
			File file = ((TakesScreenshot) DriverContextWeb.Driver).getScreenshotAs(OutputType.FILE);
			String path = Settings.path_Screenshort + j + ".png";
			files.add(+j + "" + fonte);
			Settings.Logs.Write("Screenshot time:" + getTime());
			try {
				FileUtils.copyFile(file, new File(path));
			} catch (IOException e) {
				System.out.println("Erro: " + e.getMessage());
			}
			j++;
		}
		i++;
		return new ScreenshotUtil();
	}

	
	/**
	 *  Report final, nome do report pdf
	 */
	public ScreenshotUtil report() throws DocumentException, MalformedURLException, IOException {
		String outputFile = "Screenshort report.pdf";
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(new File(pdfRoot, outputFile)));
		document.open();
		for (String f : files) {
			document.newPage();
			Image image = Image.getInstance(new File(pdfRoot, f).getAbsolutePath());
			image.setAbsolutePosition(0, 0);
			image.setBorderWidth(0);
			image.scaleAbsolute(PageSize.DEMY_QUARTO);
			document.add(image);
		}
		document.close();
		return new ScreenshotUtil();
	}
	
	
	
}
