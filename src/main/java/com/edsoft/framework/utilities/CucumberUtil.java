package com.edsoft.framework.utilities;

import java.util.ArrayList;
import java.util.List;

import cucumber.api.DataTable;

public class CucumberUtil {
	private static List<DataCollection> _dataCollection = new ArrayList<>();
	/**
	 * conventDataTableToDic gera uma coleção de data da tabela no cucumber com 
	 * _dataCollection, usando um valor chumbado como index
	 */
	public static List<DataCollection> convertDataTableToDisc(DataTable table) {
		_dataCollection.clear();
		List<List<String>> data = table.raw();
		int rowNumber = 0;
		for (List<String> col : data) {
			for (int colIndex = 0; colIndex < col.size(); colIndex++) {
				_dataCollection.add(rowNumber,
						new DataCollection(data.get(0).get(colIndex), col.get(colIndex), rowNumber));
			}
			rowNumber++;
		}
		return _dataCollection;
	}

	/**
	 * conventDataTableToDic gera uma coleção de data da tabela no cucumber com 
	 * _dataCollection, usando um valor chumbado como index
	 */
	public static String GetCellValueWithRowIndex(String columnName, int rowNumber) {
		final String[] columnValue = { null };
		_dataCollection.forEach(x -> {
			if (x.ColumnName.equals(columnName) && x.RowNumber == rowNumber) {
				columnValue[0] = x.ColumnValue;
			}
		});
		return columnValue[0];
	}

	private static class DataCollection {
		private String ColumnName;
		private String ColumnValue;
		private int RowNumber;
		public DataCollection(String columnName, String columnValue, int rowNumber) {
			super();
			ColumnName = columnName;
			ColumnValue = columnValue;
			RowNumber = rowNumber;
		}
	}
}
