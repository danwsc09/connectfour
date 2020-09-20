package test;
import java.util.Scanner;

public class Connect4 {
	public static void main(String[] args) {
		Connect4Board game = new Connect4Board();

		game.printBoard();
		Scanner sc = new Scanner(System.in);
		
		int colNum = 0, lastMove;
		String check;
		// Play the game
		System.out.println("Press any key to begin.");
		while (sc.hasNextLine()) {
			
			sc.nextLine();
			if (game.OToMove) {
				System.out.print("Player O: ");
			}
			else {
				System.out.print("Player X: ");
			}
			
			if (sc.hasNextInt()) {
				colNum = sc.nextInt();
				while (colNum < 1 || colNum > 7) {
					
					if (game.OToMove)
						System.out.print("1부터 7까지 입력하세요. Player O: ");
					else				
						System.out.print("1부터 7까지 입력하세요. Player X: ");
					try {
						colNum = sc.nextInt();
					} catch (Exception e) {
						sc.next();
					}
					
				}
				game.playMove(colNum);
			} else {
				check = sc.next().toLowerCase();
				if (check.equals("u")) {
					System.out.println("수를 물립니다.");
					game.undoMove();

				} else if (check.equals("q")) {
					System.out.println("종료합니다.");
					break;
				} else {
					System.out.println("Invalid input. Try again.");
				}
			}
			
			game.printBoard();
			
			if (game.checkWinner() != 'N') {
				break;
			}
		}
		
		if (game.checkWinner() == 'N') {
			System.out.println("승자는 없습니다.");
			return;
		}
		
		// Print winner
		System.out.println("=-=-=-=-=-=-=-=-=-=");
		System.out.println("    Winner: " + game.checkWinner());
		System.out.println("=-=-=-=-=-=-=-=-=-=");
		
	}
	
}
