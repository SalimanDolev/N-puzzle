
public class H {

	private String endMath [][];


	// build the heuristic function comparator
	// matrix - the final matrix to compare
	public H(String[][] matrix) {
		this.endMath = new String [matrix.length][matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				this.endMath[i][j] =  matrix[i][j];
			}
		}
	}

	/**
	 * Manhattan Distance - this function calculate the block distance from his original place,
	 * only by up/down right/left moves.
	 * @param mat - the vertex to check
	 * @return - the heuristic calculation for the vertex.
	 */
	public int Heuricstic(String[][] mat) {
		int distance = 0;
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				if(!mat[i][j].equals(endMath[i][j])) { // checks if the matrix is equal in all places if not search the block to place
					for (int l = 0; l < mat.length; l++) {
						for (int k = 0; k < mat[0].length; k++) {
							if(mat[i][j].equals(endMath[l][k])) {
								if(mat[i][j].contains("R"))
									distance += Math.abs(i-l)+Math.abs(j-k) + 30;
								else if (mat[i][j].contains("G"))
									distance += Math.abs(i-l)+Math.abs(j-k) + 1;
							}
						}
					}
				}
			}
		}
		return distance;
	}

}
