package test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class Connect4Board {
	char[][] board = new char[6][7];
	char[][] initialBoard = new char[6][7];
	boolean OToMove = true;
	char initChar = ' ';
	Stack<char[][]> moveHistory;
		
	public Connect4Board() {
		moveHistory = new Stack<>();
		
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				initialBoard[i][j] = initChar;
			}
		}
		moveHistory.push(initialBoard);
		this.board = cloneBoard(initialBoard);
	}
	
	public void playMove(int col ) {
		
		col -= 1;
		boolean validMove = false;
		char player;
		if (this.OToMove)
			player = 'O';
		else
			player = 'X';
		
		for (int i = 0; i < 6; i++) {
			if (board[i][col] == initChar) {
				board[i][col] = player;
				OToMove = !OToMove;
				validMove = true;
				
				moveHistory.push(cloneBoard(this.board));
				break;
			}
		}
		
		if (!validMove) {
			System.out.println("Invalid Move from " + player + ". Try again.");			
		}		
	}
	
	public char[][] cloneBoard(char[][] board) {
		if (board == null) {
			return null;
		}
		
		char[][] result = new char[board.length][];
		for (int i = 0; i < board.length; i++) {
			result[i] = Arrays.copyOf(board[i], board[i].length);
		}
		
		return result;
	}
	
	public void printBoard() {
		System.out.println();
		for (char[][] item : this.moveHistory) {
			printOneBoard(item);
		}
//		char[][] topBoard = this.moveHistory.peek();
//		for (int i = 5; i >= 0; i--) {
//			System.out.print("| ");
//			for (int j = 0; j < 7; j++) {
//				
//				System.out.print(topBoard[i][j] + " ");
//			}
//			System.out.println("|");
//		}
		
		System.out.println("| - - - - - - - |");
		System.out.println("| 1 2 3 4 5 6 7 |");
		System.out.println(this.moveHistory);
		System.out.println();
		
		System.out.println();
	}
	
	public void printOneBoard(char[][] board) {
		for (int i = 5; i >= 0; i--) {
			System.out.print("| ");
			for (int j = 0; j < 7; j++) {
				
				System.out.print(board[i][j] + " ");
			}
			System.out.println("|");
		}
		System.out.println();
	}
	
	public void undoMove() {
		if (this.moveHistory.peek() == initialBoard) {
			
			System.out.println("더이상 수를 물릴수 없습니다.");
			return;
		}
		this.moveHistory.pop();
		this.board = this.moveHistory.peek();
		this.OToMove = !this.OToMove;
	}
	
	// 승자 체크
	public char checkWinner() {
		// 마지막 수 가 승리하는 수인지 체크
		
		
		
		// 세로 체크
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 7; j++) {
				if (this.board[i][j] != this.initChar && this.board[i][j] == this.board[i+1][j] 
						&& this.board[i][j] == this.board[i+2][j] && this.board[i][j] == this.board[i+3][j])
				return this.board[i][j];
			}
		}
		
		
		// 가로 체크
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 4; j++) {
				if (this.board[i][j] != this.initChar && this.board[i][j] == this.board[i][j+1] && this.board[i][j+1] == this.board[i][j+2]
						&& this.board[i][j+2] == this.board[i][j+3] ) {
					return this.board[i][j];
				}
			}
		}
		
		
		// 대각선 체크
		// 오른쪽 위
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				if (this.board[i][j] != this.initChar && this.board[i][j] == this.board[i+1][j+1] 
						&& this.board[i][j] == this.board[i+2][j+2] && this.board[i][j] == this.board[i+3][j+3]) {
					return this.board[i][j];
				}
			}
		}
		
		// 왼쪽 위
		for (int i = 0; i < 3; i++) {
			for (int j = 3; j < 7; j++) {
				if (this.board [i][j] != this.initChar && this.board[i][j] == this.board[i+1][j-1]
						&& this.board[i][j] == this.board[i+2][j-2] && this.board[i][j] == this.board[i+3][j-3]) {
					return this.board[i][j];
				}
			}
		}
		
		return 'N';
	}	
}
