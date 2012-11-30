/**
 * Sudoku.java
 * 30/11/2012
 * @author Diego Garcia
 * diego.mendiburu@gmail.com
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

import java.util.TreeSet;


public class Sudoku {
	// Sudoku grid
	private int[][] sudoku;
	// This each represent each quadrant for each number, returning true if they exist in that quadrant.
	// This provides quicker access, not need to search every time you want to know if that three square quadrant has x number.
	private boolean[][] one;
	private boolean[][] two;
	private boolean[][] three;
	private boolean[][] four;
	private boolean[][] five;
	private boolean[][] six;
	private boolean[][] seven;
	private boolean[][] eight;
	private boolean[][] nine;
	// Flags. True if if its solved for x number.
	private boolean oneSolved, twoSolved, threeSolved, fourSolved, fiveSolved, sixSolved, sevenSolved, eightSolved, nineSolved = false;
	private TreeSet<Integer>[][] canSet;
	// default iterations limit
	private int limit = 1500;
	
	/**
	 * Class constructor. Iterations limit = 1500
	 */
	public Sudoku(){
		sudoku =  new int[9][9];
		one = new boolean[3][3];
		two = new boolean[3][3];
		three = new boolean[3][3];
		four = new boolean[3][3];
		five = new boolean[3][3];
		six = new boolean[3][3];
		seven = new boolean[3][3];
		eight = new boolean[3][3];
		nine = new boolean[3][3];
		canSet = new TreeSet[9][9];
	}
	
	/**
	 * Class constructor with a set iterations limit.
	 */
	public Sudoku(int lim){
		limit = lim;
		sudoku =  new int[9][9];
		one = new boolean[3][3];
		two = new boolean[3][3];
		three = new boolean[3][3];
		four = new boolean[3][3];
		five = new boolean[3][3];
		six = new boolean[3][3];
		seven = new boolean[3][3];
		eight = new boolean[3][3];
		nine = new boolean[3][3];
		canSet = new TreeSet[9][9];
	}
	

	/**
	 * Returns true if the Sudoku game is solved. False otherwise.
	 * 
	 * @return boolean
	 */
	public boolean checkSolved(){
		oneSolved = checkSolved(one);
		twoSolved = checkSolved(two);
		threeSolved = checkSolved(three);
		fourSolved = checkSolved(four);
		fiveSolved = checkSolved(five);
		sixSolved = checkSolved(six);
		sevenSolved = checkSolved(seven);
		eightSolved = checkSolved(eight);
		nineSolved = checkSolved(nine);
		return (oneSolved && twoSolved && threeSolved && fourSolved && fiveSolved && sixSolved && sevenSolved && eightSolved && nineSolved);
	}
	
	private boolean checkSolved(boolean[][] number){
		for(int i = 0; i < 3; i++)
			for(int j = 0; j<3; j++)
				if(number[i][j]==false)
					return false;
		return true;
	}
	
	/**
	 *  Prints the Sudoku board in console. 
	 * 
	 * @param printCanSetFlag pass true if you want to see the canSet board. This is not well formated.
	 */
	public void printSudokuBoard(boolean printCanSetFlag){
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9 ; j++){
				System.out.print(sudoku[i][j]+" ");
				if(2 == j || 5 == j)
					System.out.print("|");
				
			}
			if(2 == i || 5 == i){
				System.out.println();
				for(int j = 0; j < 10 ; j++)
					System.out.print("––");
			}
			System.out.println();
		}
		if(printCanSetFlag){
			System.out.println();
			System.out.println("Can Set:");
			System.out.println();
			this.printCanSet();
		}
	}
	
	private void printCanSet(){
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9 ; j++){
				System.out.print(canSet[i][j]+" ");
				if(2 == j || 5 == j)
					System.out.print("|");
				
			}
			if(2 == i || 5 == i){
				System.out.println();
				for(int j = 0; j < 10 ; j++)
					System.out.print("––");
			}
			System.out.println();
		}
	}
	
	
	
	
	/**
	 * Insert number into (i,j) position in the board.
	 * 
	 * @param i row coordinate
	 * @param j column coordinate
	 * @param number number to insert
	 */
	public void insertNumber(int i, int j, int number){
		if(9>i && 9>j && 0<=i && 0<=j && 0<number && 9>=number){
			sudoku[i][j] = number;
			switch(number){
			case 1:
				one[i/3][j/3] = true;
				break;
			case 2:
				two[i/3][j/3] = true;
				break;
			case 3:
				three[i/3][j/3] = true;
				break;
			case 4:
				four[i/3][j/3] = true;
				break;
			case 5:
				five[i/3][j/3] = true;
				break;
			case 6:
				six[i/3][j/3] = true;
				break;
			case 7:
				seven[i/3][j/3] = true;
				break;
			case 8:
				eight[i/3][j/3] = true;
				break;
			case 9:
				nine[i/3][j/3] = true;
				break;
			}
		}else{
			System.out.println("Fatal error while inserting number: "+number+" in: "+i+", "+j);
			System.exit(0);
		}
		calculateCanSet();
	}
	
	// Prints each numbers boolean grid. Used for testing aswell.
	public void printNumber(int number){ 
		switch(number){
		case 1:
			for(int i = 0; i < 3;i++){
				for(int j = 0;j<3;j++){
					System.out.print(one[i][j]+"\t");
				}
				System.out.println();
				System.out.println();
			}
			break;
		case 2:
			for(int i = 0; i < 3;i++){
				for(int j = 0;j<3;j++){
					System.out.print(two[i][j]+"\t");
				}
				System.out.println();
				System.out.println();
			}
			break;
		case 3:
			for(int i = 0; i < 3;i++){
				for(int j = 0;j<3;j++){
					System.out.print(three[i][j]+"\t");
				}
				System.out.println();
				System.out.println();
			}
			break;
		case 4:
			for(int i = 0; i < 3;i++){
				for(int j = 0;j<3;j++){
					System.out.print(four[i][j]+"\t");
				}
				System.out.println();
				System.out.println();
			}
			break;
		case 5:
			for(int i = 0; i < 3;i++){
				for(int j = 0;j<3;j++){
					System.out.print(five[i][j]+"\t");
				}
				System.out.println();
				System.out.println();
			}
			break;
		case 6:
			for(int i = 0; i < 3;i++){
				for(int j = 0;j<3;j++){
					System.out.print(six[i][j]+"\t");
				}
				System.out.println();
				System.out.println();
			}
			break;
		case 7:
			for(int i = 0; i < 3;i++){
				for(int j = 0;j<3;j++){
					System.out.print(seven[i][j]+"\t");
				}
				System.out.println();
				System.out.println();
			}
			break;
		case 8:
			for(int i = 0; i < 3;i++){
				for(int j = 0;j<3;j++){
					System.out.print(eight[i][j]+"\t");
				}
				System.out.println();
				System.out.println();
			}
			break;
		case 9:
			for(int i = 0; i < 3;i++){
				for(int j = 0;j<3;j++){
					System.out.print(nine[i][j]+"\t");
				}
				System.out.println();
				System.out.println();
			}
			break;
		}
	}
	
	// Method to see if a number can fit in a cell
	private boolean canFit(int number, int i, int j){
		switch(number){
		case 1:
			if(one[i/3][j/3])
				return false;
			break;
		case 2:
			if(two[i/3][j/3])
				return false;
			break;
		case 3:
			if(three[i/3][j/3])
				return false;
			break;
		case 4:
			if(four[i/3][j/3])
				return false;
			break;
		case 5:
			if(five[i/3][j/3])
				return false;
			break;
		case 6:
			if(six[i/3][j/3])
				return false;
			break;
		case 7:
			if(seven[i/3][j/3])
				return false;
			break;
		case 8:
			if(eight[i/3][j/3])
				return false;
			break;
		case 9:
			if(nine[i/3][j/3])
				return false;
			break;
		}
		 for(int x = 0; x < 9; x++)
			if(sudoku[i][x] == number || sudoku[x][j] == number || sudoku[i][j] != 0)
				return false;
		return true;
	}
	
	private void calculateCanSet(){
		for(int i = 0; i <9;i++){
			for(int j = 0; j <9; j++){
				canSet[i][j] = new TreeSet<Integer>();
			}
		}
		for(int i = 0; i <9;i++){
			for(int j = 0; j <9; j++){
				for(int k = 1; k < 10; k++){
					if(canFit(k,i,j)){
						canSet[i][j].add(k);
						//System.out.println(k+" en: "+i+", "+j);
					}
				}
			}
		}
	}
	
	/**
	 * Solves or tries to solve, the Sudoku game. Has a limit of iterations through it since by this time
	 * if it doesn't solve it in < 10 iterations it won't happen.
	 * 
	 * @return true if solved.
	 */
	public boolean solve(){
		calculateCanSet();
		int counter = 0;
		while(!solved() && counter < limit){
			solveSudokuPrivate();
			counter++;
			//System.out.println(counter);
		}
		System.out.println(counter);
		return checkSolved();
	}
	
	
	private void solveSudokuPrivate(){
		// Where a number can only be in one cell in a quadrant.
		// Means there's no other option so we insert.
		for(int n = 1; n < 10; n++){
			for(int i = 0; i <3;i++){
				for(int j = 0; j <3; j++){
					// here we are in each quadrant
					int count = 0;
					int x = 0;
					int y = 0;
					for(int k = 0;k<3;k++){
						for(int l = 0;l<3;l++){
							if(canSet[k+(i*3)][l+(j*3)].contains(n)){
								count++;
								x = k+(i*3);
								y = l+(j*3);
							}
						}
					}
					if(1 == count){
						//System.out.println("inserting: "+n+" in "+x+", "+y);
						insertNumber(x, y, n);
					}
				}
			}
		}
		
		// Where a number can only be in one cell in a row or column.
		// Means there's no other option so we insert.
		for(int i = 0; i <9;i++){
			for(int j = 0; j <9; j++){
				for(Integer n:canSet[i][j]) {
					int counti = 0;
					int countj = 0;
			        for(int k = 0; k <9;k++){
			        	if(canSet[i][k].contains(n))
			        		counti++;
			        	if(canSet[k][j].contains(n))
			        		countj++;
			        		
			        }
			        if(1 == counti || 1 == countj){
			        	insertNumber(i,j,n);
			        	//System.out.println("inserting3: "+n+" en "+i+", "+j+" canset en: "+canSet[i][j]);
			        	break;
			        }
			    }
			}
		}
		// Where there's only 1 number that can be inserted in a cell. Means, the number could go in other squares
		// But for that cell there's only one option.
		for(int i = 0; i <9;i++){
			for(int j = 0; j <9; j++){
				if(canSet[i][j].size()==1){
					insertNumber(i, j, canSet[i][j].first());
				}
			}
		}
	}
	
	/**
	 * Check if Sudoku is solved.
	 * 
	 * @return true if solved.
	 */
	public boolean solved(){
		for(int i = 0; i < 9; i++)
			for(int j = 0; j < 9; j++)
				if(0 == sudoku[i][j])
					return false;
		return true;
	}
	
	/**
	 * Set a variable iterations limit.
	 * 
	 * @param limit int iterations
	 */
	public void setLimit(int limit){
		this.limit = limit;
	}
	
	/**
	 * Gets the limit number of iterations
	 * 
	 * @return int limit
	 */
	public int getLimit(){
		return limit;
	}
}
