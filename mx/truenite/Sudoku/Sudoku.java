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
	// Flags. True if if its solved for x number.
	public TreeSet<Integer>[][] canSet;
	// default iterations limit
	private int limit = 1500;
	
	/**
	 * Class constructor. Iterations limit = 1500
	 */
	public Sudoku(){
		sudoku =  new int[9][9];
		canSet = new TreeSet[9][9];
		for(int i = 0; i <9;i++){
			for(int j = 0; j <9; j++){
				canSet[i][j] = canSet[i][j] = initTreeSet();
			}
		}
		
	}
	
	/**
	 * Class constructor with a set iterations limit.
	 */
	public Sudoku(int lim){
		limit = lim;
		sudoku =  new int[9][9];
		canSet = new TreeSet[9][9];
		for(int i = 0; i <9;i++){
			for(int j = 0; j <9; j++){
				canSet[i][j] = initTreeSet(); 
			}
		}
		
	}
	
	private TreeSet<Integer> initTreeSet(){
		TreeSet<Integer> newOne = new TreeSet<Integer>();
		for(int i = 1; i < 10; i++){
			newOne.add(i);
		}
		return newOne;
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
					System.out.print("\t\t| "+i+", "+(j+1));
				
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
			for(int k = 0; k < 9; k++){
				if(canSet[i][k].contains(number))
					canSet[i][k].remove(number);
				if(canSet[k][j].contains(number))
					canSet[k][j].remove(number);
			}
			for(int h = (i/3)*3; h < ((i/3)*3)+3; h++){
				for(int m = (j/3)*3; m < ((j/3)*3)+3; m++){
					if(canSet[h][m].contains(number))
						canSet[h][m].remove(number);
				}
			}
			canSet[i][j].clear();
		}else{
			System.out.println("Fatal error while inserting number: "+number+" in: "+i+", "+j);
			System.exit(0);
		}
		//calculateCanSet();
	}
	
		
	/**
	 * Solves or tries to solve, the Sudoku game. Has a limit of iterations through it since by this time
	 * if it doesn't solve it in < 10 iterations it won't happen.
	 * 
	 * @return true if solved.
	 */
	public boolean solve(){
		//calculateCanSet();
		int counter = 0;
		while(!solved() && counter < limit){
			solveSudokuPrivate();
			counter++;
		}
		//System.out.println(counter);
		return solved();
	}

	private void solveSudokuPrivate(){
		searchNakedDoubles();
		onlyOneForRegion();
		onlyOneForRowColumn();
		onlyOneForCell();
	}
	
	private void onlyOneForRegion(){
		// Where a number can only be in one cell in a region.
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
	}
	
	private void onlyOneForRowColumn(){
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
	}
	
	
	private void onlyOneForCell(){
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
	
	private void searchNakedDoubles(){
		// We look after 2 squares in a region that can only have the same 2 numbers.
		for(int i = 0; i <3;i++){
			for(int j = 0; j <3; j++){
				// here we are in each region
				int[][] coords = new int[9][2];
				int count = 0;
				int x = 0;
				int y = 0;
				for(int k = 0;k<3;k++){
					for(int l = 0;l<3;l++){
						x = k+(i*3);
						y = l+(j*3);
						if(2 == canSet[x][y].size()){
							coords[count][0]=x;
							coords[count][1]=y;
							count++;
						}
					}
				}
				if(count>=2){
					for(int k = 0; k < count-1; k++)
						for(int l = k+1; l<count;l++){
							if(canSet[coords[k][0]][coords[k][1]].equals(canSet[coords[l][0]][coords[l][1]])){
								deleteNakedDoubles(coords[k][0], coords[k][1], coords[l][0], coords[l][1]);
							}
						}
				}
			}
		}
	}
	private void deleteNakedDoubles(int x1, int y1, int x2, int y2){
		/*System.out.println("coords: "+x1+", "+y1);
		System.out.println("coords: "+x2+", "+y2);
		System.out.println("canset: "+canSet[x1][y1]);*/
		
		@SuppressWarnings("unchecked")
		TreeSet<Integer> clone = (TreeSet<Integer>)canSet[x1][y1].clone();
		int[] nums = new int[2];
		nums[0]=clone.first();
		clone.remove(nums[0]);
		nums[1]=clone.first();
		/*System.out.println("canset: "+clone);
		System.out.println("nums: "+nums[0]+", "+nums[1]);*/
		for(int i = (x1/3)*3; i < ((x1/3)*3)+3; i++){
			for(int j = (y1/3)*3; j < ((y1/3)*3)+3; j++){
				if(!(i== x1 && j==y1) && !(i==x2 && j==y2)){
					if(canSet[i][j].contains(nums[0])){
						//System.out.println("removing "+nums[0]+" from: "+i+", "+j);
						canSet[i][j].remove(nums[0]);
					}
					if(canSet[i][j].contains(nums[1])){
						//System.out.println("removing "+nums[1]+" from: "+i+", "+j);
						canSet[i][j].remove(nums[1]);
					}
				}
			}
		}
		if(x1 == x2){
			for(int j = 0; j < 9; j++){
				if(j!=y1 && j!=y2){
					if(canSet[x1][j].contains(nums[0])){
						//System.out.println("removing "+nums[0]+" from: "+x1+", "+j);
						canSet[x1][j].remove(nums[0]);
					}
					if(canSet[x1][j].contains(nums[1])){
						//System.out.println("removing "+nums[1]+" from: "+x1+", "+j);
						canSet[x1][j].remove(nums[1]);
					}
				}
			}
		}
		if(y1==y2){
			for(int i = 0; i<9;i++){
				if(i!=x1 && i!=x2){
					if(canSet[i][y1].contains(nums[0])){
						//System.out.println("removing "+nums[0]+" from: "+i+", "+y1);
						canSet[i][y1].remove(nums[0]);
					}
					if(canSet[i][y1].contains(nums[1])){
						//System.out.println("removing "+nums[1]+" from: "+i+", "+y1);
						canSet[i][y1].remove(nums[1]);
					}
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
	
	public void clear(){
		sudoku =  new int[9][9];
		canSet = new TreeSet[9][9];
		for(int i = 0; i <9;i++){
			for(int j = 0; j <9; j++){
				canSet[i][j] = canSet[i][j] = initTreeSet();
			}
		}
		
	}
}
