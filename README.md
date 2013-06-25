BitSudoku
=========
Java program to validate the SudokuPlus matrix. 
- Uses bitmap for each row, column, square to be verified.
- Works upto 64x64 size of square
- Verifies the validity as it parses the line, if it is valid will set the appropriate bit map.

To build, use mvn command "mvn install" or just "mvn"

Usage:
java -cp target/BitSudoku-0.1-SNAPSHOT.jar com.claritics.sudoku.Sudoku ~/Downloads/FlurryProgrammingExercise-Sudoku\ \(1\)\ \(1\)/sampleInput\ 4x4.txt 
Output:
Input file:/Users/sanjath/Downloads/FlurryProgrammingExercise-Sudoku (1) (1)/sampleInput 4x4.txt has a valid sudoku values of 4x4 matrix


