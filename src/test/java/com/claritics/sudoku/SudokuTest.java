package com.claritics.sudoku;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class SudokuTest {

    String[] lines =
    	   {"1,4,2,3", 
    		"2,3,1,4",
    		"4,2,3,1", 
    		"3,1,4,2" };

    String[] lines2 =
 	   {"1,4,4,3", 
 		"2,3,1,4",
 		"4,2,3,1", 
 		"3,1,4,2" };

    String[] lines3 =
  	   {"1,4,10,3", 
  		"2,3,1,4",
  		"4,2,3,1", 
  		"3,1,4,2" };

    @Test
    public void test1() throws Exception {
    	Sudoku s = new Sudoku(Arrays.asList(lines));
    	Assert.assertTrue(s.validate());
    	s = new Sudoku(Arrays.asList(lines2));
    	try {
    		Assert.assertTrue(s.validate());
    	} catch (Exception e) {
    		Assert.assertEquals("Duplicate value:4 for row:0", e.getMessage());
    	}

    	s = new Sudoku(Arrays.asList(lines3));
    	try {
    		Assert.assertTrue(s.validate());
    	} catch (Exception e) {
    		Assert.assertEquals("Invalid cell value:10 at(0,2)", e.getMessage());
    	}

    }
    
    String[] lines4 = 
    	{   "5,3,4,6,7,8,9,1,2",
    		"6,7,2,1,9,5,3,4,8",
    		"1,9,8,3,4,2,5,6,7",
    		"8,5,9,7,6,1,4,2,3",
    		"4,2,6,8,5,3,7,9,1",
    		"7,1,3,9,2,4,8,5,6",
    		"9,6,1,5,3,7,2,8,4",
    		"2,8,7,4,1,9,6,3,5",
    		"3,4,5,2,8,6,1,7,9" };
 
    
    String[] lines5 = 
    	{   "5,3,4,6,7,8,9,1,2",
    		"6,7,1,2,9,5,3,4,8",
    		"1,9,8,3,4,2,5,6,7",
    		"8,5,9,7,6,1,4,2,3",
    		"4,2,6,8,5,3,7,9,1",
    		"7,1,3,9,2,4,8,5,6",
    		"9,6,1,5,3,7,2,8,4",
    		"2,8,7,4,1,9,6,3,5",
    		"3,4,5,2,8,6,1,7,9" };
 
    String[] lines6 = 
    	{   "5,3,4,6,7,8,9,1,2",
    		"6,7,2,1,9,5,3,4,8",
    		"1,9,8,3,4,2,5,6,7",
    		"8,5,9,7,6,1,4,2,3",
    		"4,2,6,8,5,3,7,9,1",
    		"7,1,3,9,2,4,8,5,6",
    		"9,6,1,5,3,7,2,8",
    		"2,8,7,4,1,9,6,3,5",
    		"3,4,5,2,8,6,1,7,9" };
    
    @Test
    public void test2() throws Exception {
    	Sudoku s = new Sudoku(Arrays.asList(lines4));
    	Assert.assertTrue(s.validate());
    	
       	s = new Sudoku(Arrays.asList(lines5));
    	try {
    		Assert.assertTrue(s.validate());
    	} catch (Exception e) {
    		Assert.assertEquals("Duplicate value:1 for sqaure:0", e.getMessage());
    	}

       	s = new Sudoku(Arrays.asList(lines6));
    	try {
    		Assert.assertTrue(s.validate());
    	} catch (Exception e) {
    		Assert.assertEquals("Number of lines should match the number of columns in each line", e.getMessage());
    	}

    }
	
	@Test
	public void testSquareIndexCalc() {
		Assert.assertEquals(0,Sudoku.getSquareIndex(0, 0, 3));
		Assert.assertEquals(3,Sudoku.getSquareIndex(3, 0, 3));
		Assert.assertEquals(1,Sudoku.getSquareIndex(1, 3, 3));
		Assert.assertEquals(8,Sudoku.getSquareIndex(6, 6, 3));
		Assert.assertEquals(5,Sudoku.getSquareIndex(3, 8, 3));
		Assert.assertEquals(8,Sudoku.getSquareIndex(8, 8, 3));
		Assert.assertEquals(2,Sudoku.getSquareIndex(2, 8, 3));
		Assert.assertEquals(4,Sudoku.getSquareIndex(5, 5, 3));
		Assert.assertEquals(5,Sudoku.getSquareIndex(5, 6, 3));
		Assert.assertEquals(6,Sudoku.getSquareIndex(7, 1, 3));
		Assert.assertEquals(7,Sudoku.getSquareIndex(8, 4, 3));
	}

}
