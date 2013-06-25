package com.claritics.sudoku;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author sanjath
 * This class works for sudoku validation of sudoku blocks of size 64*64
 * It works by creating a bit map for each value set that needs to be checked
 * While parsing the lines, it checks each value against the three sets of valuebitsets which are created
 * for each row, column and the inner sqaures. 
 *
 */
public class Sudoku {
	long[] rowBitMap;
	long[] colBitMap;
	long[] sqBitMap;
	int sqSize;
	List<String> lines = new ArrayList<String>();
	public Sudoku(String filepath) throws Exception  {
		BufferedReader brdr;
		try {
			brdr = new BufferedReader(new FileReader(filepath));
			String currentLine;
			

			while ((currentLine = brdr.readLine()) != null) {
				lines.add(currentLine);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception(e);
		}
		
		
	}
	public Sudoku(List<String> lines) {
		this.lines = lines;
	}


	public boolean validate() throws Exception {
		int sudokuSize = 0;
		if (lines.size() > 0) {
			String[] cells = lines.get(0).split(",");
			sudokuSize = cells.length;
			if (sudokuSize != lines.size()) {
				// not a square, error
			}
			sqSize = (int) Math.sqrt(sudokuSize);
			if (sqSize*sqSize != sudokuSize ) {
				throw new Exception("Size of the square must be a perfect sqaure");
			}
			if (sudokuSize > 64) {
				throw new Exception("Program cannot handle size > 64");
			}
			rowBitMap = new long[sudokuSize];
			colBitMap = new long[sudokuSize];
			sqBitMap = new long[sudokuSize];
			
			validateLine(cells, 0);
			for (int i=1; i<sudokuSize; i++) {
				cells = lines.get(i).split(",");
				validateLine(cells, i);
			}

		} else {
			throw new Exception("Empty input");
		}
		return true;
	}


	private void validateLine(String[] cells, int linenum) throws Exception {
		
		for (int i = 0; i< cells.length ; i++) {
			try {
				int num = Integer.parseInt(cells[i]);
				if (num <1 || num > cells.length) {
					throw new Exception("Invalid cell value:"+num+" at("+linenum+","+i+")");
				}
				long bitSet = (1L << (num-1));
				validate(linenum, i, bitSet, num);
			} catch (NumberFormatException nfe) {
				throw new Exception("Non number found in input", nfe);
			}
		}

	}
	
	static int getSquareIndex(int row, int col, int sqSize) {
		int i = (row)/sqSize;
		int j = (col)/sqSize;
		return (sqSize*i)+j;
	}
	
	void validate(int row, int col, long cellBitMap, int num) throws Exception {
		if ((rowBitMap[row] & cellBitMap) != 0) {
			throw new Exception("Duplicate value:"+num + " for row:"+ row);
		}
		rowBitMap[row] = rowBitMap[row] | cellBitMap;
		
		if ((colBitMap[col] & cellBitMap) != 0) {
			throw new Exception("Duplicate value:"+num + " for col:"+ col);
		}
		colBitMap[col] = colBitMap[col] | cellBitMap;
		
		int sqIdx = getSquareIndex(row, col, sqSize);
		if ((sqBitMap[sqIdx] & cellBitMap) != 0) {
			throw new Exception("Duplicate value:"+num + " for sqaure:"+ sqIdx);
		}
		sqBitMap[sqIdx] = sqBitMap[sqIdx] | cellBitMap;
		
	}


	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Sudoku s;
		try {
			 s = new Sudoku(args[0]);
			 s.validate();
		} catch (Exception e) {
			System.out.println("Error is validating the input file:"+e);
			System.out.println("Usage: Sudoku <filepath>");
			return;
		}
		int size = s.sqSize*s.sqSize;
		System.out.println("Input file:"+args[0]+" has a valid sudoku values of "+size+"x" + size +" matrix");

	}

}
