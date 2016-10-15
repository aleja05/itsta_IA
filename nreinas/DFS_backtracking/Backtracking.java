import java.util.Arrays;

public class Backtracking {
	static Tablero result;

		public boolean canPlace(int x2, int y2) {

		for (int i = 0; i < x2; i++) {
			if ((result.reinas[i] == y2)
					|| (Math.abs(i - x2) == Math.abs(result.reinas[i] - y2))) {
				return false;
			}
		}
		return true;
	}
	public void placeQueens(int x, int size) {
		for (int i = 0; i < size; i++) {
			//check if queen at xth row can be placed at i-th column.
			if (canPlace(x, i)) {
				result.reinas[x] = i; // place the queen at this position.
				if (x == size - 1) {
					System.out.println("Order of " + size + " queens"
							+ Arrays.toString(result.reinas));
				}
				placeQueens(x + 1, size); //backtracking
				result.reinas[x] = 0;
			}
		}
	}
	public static void main(String[] args) {
		int n = 6;
		result = new Tablero(n);
		Backtracking i = new Backtracking();
		i.placeQueens(0, n);
	}
}
