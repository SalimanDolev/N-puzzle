

public class Vertex implements Comparable<Vertex> {

	private String [][] mat ;
	private String path ="";
	private String key = "";  
	private String MatToString = "";
	private int depth;
	private int cost;
	private int f = 0;
	private boolean out = false;
	private Vertex father = null;
	private String move;
	
/**
 * Constructor for the matrix in the vertex
 * @param Matrix - String matrix
 */
	public Vertex(String [][] Matrix) {
		this.mat = new String [Matrix.length][Matrix[0].length];
		for (int i = 0; i < Matrix.length; i++) {
			for (int j = 0; j < Matrix[0].length; j++) {
				mat[i][j] = Matrix[i][j];
				MatToString += Matrix[i][j]+"";
				if(Matrix[i][j].equals("_")) {
					this.key = i+""+ j;
				}
			}
		}
		path = "";
		this.cost=0;
		this.depth = 0;
		this.depth = 0;
		this.out = false;
		this.move = new String ("");
	}
	
/**
 * Copy constructor for other vertex
 * @param mat2 - other vertex
 */
	public Vertex(Vertex mat2) {
		this.mat = new String [mat2.mat.length][mat2.mat[0].length];
		for (int i = 0; i < mat2.mat.length; i++) {
			for (int j = 0; j < mat2.mat[0].length; j++) {
				this.mat[i][j] = mat2.mat[i][j];
			}
		}
		this.path = mat2.getPath();
		this.cost = mat2.getCost();
		this.depth = mat2.getDepth();
		this.f = mat2.getF();
		this.out = mat2.out;
		this.move = mat2.move;
	}
	

	public void setCost(int Mcost) {
		this.cost = this.cost + Mcost;
	}
	
	public int getCost() {
		return this.cost;
	}


	public void setPath(String Npath) {
		this.path = Npath + this.path;// + "-" + this.path;
	}


	public String[][] getMat(){
		return this.mat;
	}

	public String getPath() {
		return this.path;
	}

	public void printVertex() {
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				System.out.print(this.mat[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public String getKey() {
		return this.key;
	}
	
	public String getMatToString() {
		return this.MatToString;
	}
	
	public void setDepth(int x) {
		this.depth = x;
	}
	
	public int getDepth() {
		return this.depth;
	}

/////////////////////////////////////////////////////////////////////////////////////
///////compareable for the heuristic functions///////////////////////////////////////
	
	public void setF(int x) {
		this.f = x;
	}
	public int getF() {
		return this.f;
	}
	
	@Override
	public int compareTo(Vertex v) {
		if(this.f > v.getF() ) {
			return 1;
		}
		if(this.f < v.getF()) {
			return -1;
		}else return 0;
	}
	
	
	/// for IDA*- mark the vertex as out/not
	public void setOut(boolean a) {
		this.out = a;
	}
	
	public boolean getOut() {
		return this.out;
	}
	
	//////////////////////////////////////////////////////
	// father get and set
	public void setFather(Vertex v) {
		this.father = v;
	}
	
	public Vertex getFather() {
		return this.father;
	}
	////////////////////////////////////////////////////////
	/////////////last move /////////////////////////////////
	public void setMove(String a) {
		this.move = a;
	}
	
	public String getMove() {
		return this.move;
	}
	
}
