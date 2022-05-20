package com.edsoft.framework.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelUtil {
	static Sheet wrksheet;
	static Workbook wrkbook = null;
	static Hashtable dict = new Hashtable();

	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cel;

	/****************** Excel JXL Ssheet ***********************/

	/**
	 * ExcelUtil faz somente a leitura de arquivo excel.xls
	 * @param excelSheetPath
	 * @param nome da aba Sheet1
	 */
	public ExcelUtil(String ExcelSheetPath) throws BiffException, IOException {
		wrkbook = Workbook.getWorkbook(new File(ExcelSheetPath));
		wrksheet = wrkbook.getSheet("Sheet1");
		ColumnDictionary();
	}

	
	public static int RowCount() {
		return wrksheet.getRows();
	}

	private static String ReadCell(int column, int row) {
		return wrksheet.getCell(column, row).getContents();
	}

	public static String ReadCell(String columnName, int rowNumber) {
		return ReadCell(GetCell(columnName), rowNumber);
	}

	private static void ColumnDictionary() {
		for (int col = 0; col < wrksheet.getColumns(); col++) {
			dict.put(ReadCell(col, 0), col);
		}
	}

	private static int GetCell(String colName) {
		try {
			int value;
			value = ((Integer) dict.get(colName)).intValue();
			return value;
		} catch (NullPointerException e) {
			return (0);
		}
	}
	/************************* Excel POI XSSF *************************/
	/**
	 * ExecuteStoredProc metodo de criação para acesso ao banco
	 * @param procedureName
	 * @param parameters
	 * @param connection
	 */
	public static String getCell(int numLine, int numCol) throws Exception {
		try {
			FileInputStream ExcelFile = new FileInputStream("date.xlsx");
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet("Sheet1");
			Cel = ExcelWSheet.getRow(numLine).getCell(numCol);
			String CellData = Cel.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	/**
	 *  metodo de criação para acesso ao banco
	 * @param columnVerify
	 * @param campareName
	 * @param columnInsert
	 * @param setValue
	 */
	public static String setCellByName(int columnVerify, String compareName, int columnInsert, String setValue)
			throws IOException {
		try {
			FileInputStream file = new FileInputStream(new File("date.xlsx")); /* local do arquivo */
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheet("Sheet1"); /* name plan */
			int totalRow = sheet.getLastRowNum() + 1;
			for (int i = 1; i < totalRow; i++) {
				XSSFRow r = sheet.getRow(i);
				String ce = r.getCell(columnVerify).getStringCellValue();
				if (ce.contains(compareName)) { /* Compara��o valor e inserir valor na coluna */
					r.createCell(columnInsert).setCellValue(setValue);
					file.close();
					System.out.println("UpdateCell, Coluna de verifica��o:(" + columnVerify + ")- CompareName: ("
							+ compareName + ") - SettingColumn(" + columnInsert + "): " + setValue);
					FileOutputStream outFile = new FileOutputStream(new File("date.xlsx"));
					workbook.write(outFile);
					outFile.close();
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return setValue;
	}

	/************** VERIFY EXCEL TOOLS ************/
	/**
	 *  getExcelData retorna o valor da celular do excel
	 */
	public static String[][] getExcelData() {
		try {
			String dataSets[][] = null;
			FileInputStream file = new FileInputStream(new File("date.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheet("Planilha1");
			int totalRow = sheet.getLastRowNum();
			int totalColumn = sheet.getRow(0).getLastCellNum();
			dataSets = new String[totalRow][totalColumn];
			Iterator<Row> rowIterator = sheet.iterator();
			int i = 0;
			while (rowIterator.hasNext()) {
				System.out.println(i);
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				int j = 0;
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					if (cell.getStringCellValue().contains("User Name")) {
						break;
					}
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						dataSets[i - 1][j++] = cell.getStringCellValue();
						System.out.println(cell.getNumericCellValue());
						break;
					case Cell.CELL_TYPE_STRING:
						dataSets[i - 1][j++] = cell.getStringCellValue();
						System.out.println(cell.getStringCellValue());
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						dataSets[i - 1][j++] = cell.getStringCellValue();
						System.out.println(cell.getStringCellValue());
						break;
					case Cell.CELL_TYPE_FORMULA:
						dataSets[i - 1][j++] = cell.getStringCellValue();
						System.out.println(cell.getStringCellValue());
						break;
					}
				}
				System.out.println("");
				i++;
			}
			file.close();
			return dataSets;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 *  getExcelDataBasedOnStartingPoint retorna o valor da celular do excel
	 */
	public static Object[][] getExcelDataBasedOnStartingPoint(String testName) {
		try {
			String dataSets[][] = null;
			FileInputStream file = new FileInputStream(new File("date.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheet("Planilha1");
			int totalRow = sheet.getLastRowNum();
			int totalColumn = 0;
			Iterator<Row> rowIterator = sheet.iterator();
			int i = 0;
			int count = 1;
			while (rowIterator.hasNext() && count == 1 || count == 2) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				int j = 0;
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					if (cell.getStringCellValue().contains(testName + "end")) {
						count = 0;
						break;
					}
					if (cell.getStringCellValue().contains(testName + "start")) {
						totalColumn = row.getPhysicalNumberOfCells() - 1;
						dataSets = new String[totalRow][totalColumn];
					}
					if (cell.getStringCellValue().contains(testName + "start") || count == 2) {
						System.out.println("Planilha1" + "start");
						count = 2;
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							dataSets[i - 1][j++] = cell.getStringCellValue();
							System.out.println(cell.getNumericCellValue());
							break;
						case Cell.CELL_TYPE_STRING:
							if (!cell.getStringCellValue().contains(testName + "start")) {
								dataSets[i - 1][j++] = cell.getStringCellValue();
								System.out.println(cell.getStringCellValue());
							}
							break;
						case Cell.CELL_TYPE_BOOLEAN:
							dataSets[i - 1][j++] = cell.getStringCellValue();
							System.out.println(cell.getStringCellValue());
							break;
						case Cell.CELL_TYPE_FORMULA:
							dataSets[i - 1][j++] = cell.getStringCellValue();
							System.out.println(cell.getStringCellValue());
							break;
						}
					}
				}
				System.out.println("");
				i++;
			}
			file.close();
			return parseData(dataSets, totalColumn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Object[][] parseData(Object[][] data, int colSize) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		ArrayList<String> list1;
		System.out.println(data.length);
		for (int i = 0; i < data.length; i++) {
			System.out.println(data[i].length);
			list1 = new ArrayList<String>();
			for (int j = 0; j < data[i].length; j++) {
				if (data[i][j] != null) {
					list1.add((String) data[i][j]);
				}
			}
			if (list1.size() > 0) {
				list.add(list1);
			}
		}
		Object[][] arr2d = new Object[list.size()][colSize];
		for (int i = 0; i < list.size(); i++) {
			ArrayList<String> t = list.get(i);
			for (int j = 0; j < t.size(); j++) {
				arr2d[i][j] = t.get(j);
			}
		}
		System.out.println(list);
		System.out.println(arr2d);
		return arr2d;
	}

	public static String setCell(int rowInsert, int columnInsert, String setValue) throws IOException {
		try {
			FileInputStream file = new FileInputStream(new File("date.xlsx")); /* local do arquivo */
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheet("Sheet1"); /* name plan */
			int totalRow = sheet.getLastRowNum() + 1;
			for (int i = 1; i < totalRow; i++) {
				XSSFRow r = sheet.getRow(i);
				if (i == rowInsert) {
					r.createCell(columnInsert).setCellValue(setValue);
					file.close();
					System.out.println(
							"UpdateCell, in line:(" + rowInsert + ")-  Column(" + columnInsert + "): " + setValue);
					FileOutputStream outFile = new FileOutputStream(new File("date.xlsx"));
					workbook.write(outFile);
					outFile.close();
					break;
				}
			}

		} catch (Exception e) {
			e.getStackTrace();
		}
		return setValue;
	}

	public static void main(String[] args) throws Exception {
//       setCell("date.xlsx", "Planilha1", "Eduhitman1**", "OK");
//		 System.out.println("Value cell: " + getCell(0, 0));
//		 setCellByName(2, "valido", 3, "OK");
//		 setCell(1, 0, "eduardo");
		 setCell(2, 1, "EduardoH");
//		 setCell(1, 2, "eduardo");
	}
}
