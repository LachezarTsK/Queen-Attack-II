import java.util.*;

public class Solution {

	private static ArrayList<Integer> horizontal_Left = new ArrayList<Integer>();
	private static ArrayList<Integer> horizontal_Right = new ArrayList<Integer>();
	private static ArrayList<Integer> vertical_Down = new ArrayList<Integer>();
	private static ArrayList<Integer> vertical_Up = new ArrayList<Integer>();
	private static ArrayList<Integer> diagonal_UpLeft = new ArrayList<Integer>();
	private static ArrayList<Integer> diagonal_DownLeft = new ArrayList<Integer>();
	private static ArrayList<Integer> diagonal_DownRight = new ArrayList<Integer>();
	private static ArrayList<Integer> diagonal_UpRight = new ArrayList<Integer>();
	static int elementUpLeft;
	static int elementDownLeft;
	static int elementDownRight;
	static int elementUpRight;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int sideLength = in.nextInt();
		int k = in.nextInt();
		int rQueen = in.nextInt();
		int cQueen = in.nextInt();
		int rowObstacle;
		int columnObstacle;
		for (int a0 = 0; a0 < k; a0++) {

			rowObstacle = in.nextInt();
			columnObstacle = in.nextInt();
			
			addHorizontalAndVertical(cQueen, rQueen, rowObstacle, columnObstacle);
			addAllDiagonals(sideLength, cQueen, rQueen, rowObstacle, columnObstacle);
		}
		if (k == 0) {
			addAllDiagonals(sideLength, cQueen, rQueen, 0, 0);
		}
		int total = getHorizontalAndVertical(sideLength, cQueen, rQueen, horizontal_Left, horizontal_Right,
				vertical_Down, vertical_Up)
				+ getAllDiagonals(sideLength, cQueen, rQueen, diagonal_UpLeft, diagonal_DownLeft, diagonal_DownRight,
						diagonal_UpRight);

		System.out.println(total);
	}

	public static void addHorizontalAndVertical(int cQueen, int rQueen, int row, int column) {

		if (rQueen == row) {
			if (column < cQueen) {
				horizontal_Left.add(column);
			} else if (column > cQueen) {
				horizontal_Right.add(column);
			}
		} else if (cQueen == column) {
			if (row < rQueen) {
				vertical_Down.add(row);
			} else if (row > rQueen) {
				vertical_Up.add(row);
			}
		}

	}

	public static int getHorizontalAndVertical(int sideLength, int cQueen, int rQueen, ArrayList<Integer> hL,
			ArrayList<Integer> hR, ArrayList<Integer> vD, ArrayList<Integer> vU) {
		Collections.sort(hL);
		Collections.sort(hR);
		Collections.sort(vD);
		Collections.sort(vU);

		int movesHorizontal_Left;
		if (hL.size() > 0) {
			movesHorizontal_Left = cQueen - (hL.get(hL.size() - 1) + 1);
		} else {
			movesHorizontal_Left = cQueen - 1;
		}

		int movesHorizontal_Right;
		if (hR.size() > 0) {
			movesHorizontal_Right = hR.get(0) - (cQueen + 1);
		} else {
			movesHorizontal_Right = sideLength - cQueen;
		}

		int movesVertical_Down;
		if (vD.size() > 0) {
			movesVertical_Down = rQueen - (vD.get(vD.size() - 1) + 1);
		} else {
			movesVertical_Down = rQueen - 1;
		}

		int movesVertical_UP;
		if (vU.size() > 0) {
			movesVertical_UP = vU.get(0) - (rQueen + 1);
		} else {
			movesVertical_UP = sideLength - rQueen;
		}
		int total = movesHorizontal_Left + movesHorizontal_Right + movesVertical_Down + movesVertical_UP;
		return total;
	}

	public static void addDiagonal_UpLeft(int sideLength, int cQueen, int rQueen, int row, int column) {
		HashMap<Integer, Integer> upLeft = new HashMap<Integer, Integer>();

		if (cQueen < sideLength - rQueen) {
			elementUpLeft = cQueen - 1;
			elementDownRight = rQueen - 1;
		} else {
			elementUpLeft = sideLength - rQueen;
			elementDownRight = sideLength - cQueen;
		}
		
		int columnClose = cQueen - 1;
		int columnFar = cQueen - elementUpLeft;
		int rowClose = rQueen + 1;
		int rowFar = rQueen + elementUpLeft;

		if (column < cQueen && row > rQueen) {
			if (column <= columnClose
					&& column >= columnFar) {
				if (row >= rowClose && row <= rowFar) {
					if (Math.abs(column - cQueen) == Math
							.abs(row - rQueen)) {
						diagonal_UpLeft.add(column);
					}
				}
			}
		}
	}

	public static void addDiagonal_DownLeft(int sideLength, int cQueen, int rQueen, int row, int column) {
		HashMap<Integer, Integer> downLeft = new HashMap<Integer, Integer>();

		if (cQueen < rQueen) {
			elementDownLeft = cQueen - 1;
			elementUpRight = sideLength - rQueen;
		} else {
			elementDownLeft = rQueen - 1;
			elementUpRight = sideLength - cQueen;
		}

		int columnClose = cQueen - 1;
		int columnFar = cQueen - elementDownLeft;
		int rowClose = rQueen - 1;
		int rowFar = rQueen - elementDownLeft;

		if (column < cQueen && row < rQueen) {
			if (column <= columnClose && column >= columnFar) {
				if (row <= rowClose && row >= rowFar) {
					if (Math.abs(column - cQueen) == Math.abs(row - rQueen)) {
						diagonal_DownLeft.add(column);
					}
				}
			}
		}
	}

	public static void addDiagonal_DownRight(int sideLength, int cQueen, int rQueen, int row, int column) {
		HashMap<Integer, Integer> downRight = new HashMap<Integer, Integer>();

		int columnClose = cQueen + 1;
		int columnFar = cQueen + elementDownRight;
		int rowClose = rQueen - 1;
		int rowFar = rQueen - elementDownRight;

		if (column > cQueen && row < rQueen) {
			if (column >= columnClose
					&& column <= columnFar) {
				if (row <= rowClose && row >= rowFar) {
					if (Math.abs(column - cQueen) == Math.abs(row - rQueen)) {
						diagonal_DownRight.add(column);
					}
				}
			}
		}
	}

	public static void addDiagonal_UpRight(int sideLength, int cQueen, int rQueen, int row, int column) {
		HashMap<Integer, Integer> upRight = new HashMap<Integer, Integer>();

		int columnClose = cQueen + 1;
		int columnFar = cQueen + elementUpRight;
		int rowClose = rQueen + 1;
		int rowFar = rQueen + elementUpRight;

		if (column > cQueen && row > rQueen) {

			if (column >= columnClose
					&& column <= columnFar) {
				if (row >= rowClose && row <= rowFar) {
					if (Math.abs(column - cQueen) == Math.abs(row - rQueen)) {
						diagonal_UpRight.add(column);
					}
				}
			}
		}
	}

	public static void addAllDiagonals(int sideLength, int cQueen, int rQueen, int row, int column) {
		addDiagonal_UpLeft(sideLength, cQueen, rQueen, row, column);
		addDiagonal_DownLeft(sideLength, cQueen, rQueen, row, column);
		addDiagonal_DownRight(sideLength, cQueen, rQueen, row, column);
		addDiagonal_UpRight(sideLength, cQueen, rQueen, row, column);
	}

	public static int getAllDiagonals(int sideLength, int cQueen, int rQueen, ArrayList<Integer> uL,
			ArrayList<Integer> dL, ArrayList<Integer> dR, ArrayList<Integer> uR) {

		Collections.sort(uL);
		Collections.sort(dL);
		Collections.sort(dR);
		Collections.sort(uR);
	
		int movesDiagonal_UpLeft;
		if (uL.size() > 0) {
			movesDiagonal_UpLeft = cQueen - (uL.get(uL.size() - 1) + 1);
		} else {
			movesDiagonal_UpLeft = elementUpLeft;
		}

		int movesDiagonal_DwonLeft;
		if (dL.size() > 0) {
			movesDiagonal_DwonLeft = cQueen - (dL.get(dL.size() - 1) + 1);
		} else {
			movesDiagonal_DwonLeft = elementDownLeft;
		}

		int movesDiagonal_DwonRight;
		if (dR.size() > 0) {
			movesDiagonal_DwonRight = dR.get(0) - (cQueen + 1);
		} else {
			movesDiagonal_DwonRight = elementDownRight;
		}

		int movesDiagonal_UpRight;
		if (uR.size() > 0) {
			movesDiagonal_UpRight = uR.get(0) - (cQueen + 1);
		} else {
			movesDiagonal_UpRight = elementUpRight;
		}

		int total = movesDiagonal_UpLeft + movesDiagonal_DwonLeft + movesDiagonal_DwonRight + movesDiagonal_UpRight;
		return total;
	}
}
