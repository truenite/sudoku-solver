/**
 * Solution.java
 * 27/11/2012
 * @author Diego Garcia
 * @version 1.0
 * 
 * Copyright (C) 2012  Diego Garcia
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package mx.truenite.Sudoku;


/**
 * 
 */
public class Solution {

	static Sudoku sudoku;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		sudoku = new Sudoku();
		initExpert();
		//initEasy();
		//sudoku.clear();
		//initHard();
		sudoku.printSudokuBoard(false);
		sudoku.solve();
		
		
		/*System.out.println(sudoku.canSet[6][1]);
		System.out.println(sudoku.canSet[6][1]);
		if(sudoku.canSet[6][1].equals(sudoku.canSet[6][2]))
			System.out.println("yes!");*/
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		sudoku.printSudokuBoard(false);

	}
	
	// You can erease this method since this was only to test.
	private static void initExpert(){
		
		// genina.com random expert sudoku   iterations
		sudoku.insertNumber(0, 2, 1);
		sudoku.insertNumber(0, 7, 3);
		sudoku.insertNumber(0, 8, 5);
		sudoku.insertNumber(1, 5, 3);
		sudoku.insertNumber(1, 7, 8);
		sudoku.insertNumber(2, 1, 9);
		sudoku.insertNumber(2, 4, 1);
		sudoku.insertNumber(3, 3, 2);
		sudoku.insertNumber(3, 7, 5);
		sudoku.insertNumber(4, 1, 7);
		sudoku.insertNumber(4, 2, 2);
		sudoku.insertNumber(4, 3, 5);
		sudoku.insertNumber(4, 5, 4);
		sudoku.insertNumber(4, 6, 8);
		sudoku.insertNumber(4, 7, 1);
		sudoku.insertNumber(5, 1, 4);
		sudoku.insertNumber(5, 5, 1);
		sudoku.insertNumber(6, 4, 8);
		sudoku.insertNumber(6, 7, 6);
		sudoku.insertNumber(7, 1, 6);
		sudoku.insertNumber(7, 3, 9);
		sudoku.insertNumber(8, 0, 9);
		sudoku.insertNumber(8, 1, 5);
		sudoku.insertNumber(8, 6, 7);
		/*
0 8 1 |0 0 0 |0 3 5 
0 2 0 |0 0 3 |0 8 0 
0 9 0 |0 1 0 |0 0 0 
––––––––––––––––––––
0 0 0 |2 0 0 |0 5 0 
0 7 2 |5 0 4 |8 1 0 
0 4 0 |0 0 1 |0 0 0 
––––––––––––––––––––
0 0 0 |0 8 0 |0 6 0 
0 6 0 |9 0 0 |0 0 0   < enforce 4 y 2
9 5 0 |0 0 0 |7 0 0   < enforce 4 y 2
		
		
		 */
	}
	static void initHard(){
		// genina.com random hard sudoku  3 iterations
		
		sudoku.insertNumber(0, 6, 2);
		sudoku.insertNumber(0, 7, 7);
		sudoku.insertNumber(1, 0, 1);
		sudoku.insertNumber(1, 1, 9);
		sudoku.insertNumber(1, 3, 4);
		sudoku.insertNumber(1, 6, 6);
		sudoku.insertNumber(1, 7, 5);
		sudoku.insertNumber(2, 0, 5);
		sudoku.insertNumber(2, 1, 7);
		sudoku.insertNumber(2, 3, 3);
		sudoku.insertNumber(3, 3, 6);
		sudoku.insertNumber(3, 5, 7);
		sudoku.insertNumber(3, 6, 4);
		sudoku.insertNumber(3, 7, 9);
		sudoku.insertNumber(4, 4, 8);
		sudoku.insertNumber(5, 1, 6);
		sudoku.insertNumber(5, 2, 7);
		sudoku.insertNumber(5, 3, 2);
		sudoku.insertNumber(5, 5, 4);
		sudoku.insertNumber(6, 5, 8);
		sudoku.insertNumber(6, 7, 4);
		sudoku.insertNumber(6, 8, 6);
		sudoku.insertNumber(7, 1, 2);
		sudoku.insertNumber(7, 2, 9);
		sudoku.insertNumber(7, 5, 5);
		sudoku.insertNumber(7, 7, 3);
		sudoku.insertNumber(7, 8, 1);
		sudoku.insertNumber(8, 1, 1);
		sudoku.insertNumber(8, 2, 4);
	}
	
	static void initEasy(){
		
		
		// genina.com random easy sudoku 2 iterations
		
		sudoku.insertNumber(0, 0, 7);
		sudoku.insertNumber(0, 8, 9);
		sudoku.insertNumber(1, 1, 5);
		sudoku.insertNumber(1, 2, 1);
		sudoku.insertNumber(1, 6, 6);
		sudoku.insertNumber(1, 7, 7);
		sudoku.insertNumber(2, 1, 6);
		sudoku.insertNumber(2, 4, 2);
		sudoku.insertNumber(2, 7, 4);
		sudoku.insertNumber(3, 0, 3);
		sudoku.insertNumber(3, 1, 9);
		sudoku.insertNumber(3, 3, 2);
		sudoku.insertNumber(3, 5, 5);
		sudoku.insertNumber(3, 7, 8);
		sudoku.insertNumber(3, 8, 7);
		sudoku.insertNumber(4, 2, 4);
		sudoku.insertNumber(4, 6, 2);
		sudoku.insertNumber(5, 0, 2);
		sudoku.insertNumber(5, 1, 8);
		sudoku.insertNumber(5, 3, 3);
		sudoku.insertNumber(5, 5, 1);
		sudoku.insertNumber(5, 7, 9);
		sudoku.insertNumber(5, 8, 4);
		sudoku.insertNumber(6, 1, 2);
		sudoku.insertNumber(6, 4, 1);
		sudoku.insertNumber(6, 7, 5);
		sudoku.insertNumber(7, 1, 7);
		sudoku.insertNumber(7, 2, 8);
		sudoku.insertNumber(7, 6, 9);
		sudoku.insertNumber(7, 7, 6);
		sudoku.insertNumber(8, 0, 4);
		sudoku.insertNumber(8, 8, 1);
	}
}
