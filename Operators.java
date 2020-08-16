

public class Operators{

/**
 * 
 * @param mat - the state that need to find his empty place
 * @return the index of the empty place
 */
	public int [] searchEmpty(String [][] mat) {
		int []place = new int [2];
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				if(mat[i][j].equals("_")) {
					place[0] = i;
					place[1] = j;
					return place;
				}
			}
		}
		return place;
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////// Operator left ///////////////////////////////////////////////////////////////
	public Vertex operatorLeft(String [][] Mat){
		String [][] temp = new String[Mat.length][Mat[0].length];
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[0].length; j++) {
				temp[i][j] = Mat[i][j];
			}
		}
		int [] emptyPlace = searchEmpty(Mat);
		if(emptyPlace[1] + 1 < Mat[0].length) {
			if(Mat[ emptyPlace[0] ][ emptyPlace[1] + 1 ].contains("G") || Mat[ emptyPlace[0] ][ emptyPlace[1] + 1 ].contains("R") ) {
				temp[ emptyPlace[0] ][ emptyPlace[1] ]=temp[ emptyPlace[0] ][ emptyPlace[1] + 1 ];
				temp[ emptyPlace[0] ][ emptyPlace[1] + 1 ] = "_";
			}
		}
		Vertex V = new Vertex(temp);
		if(!temp[ emptyPlace[0] ][ emptyPlace[1]].equals("_")) {
			String st1=temp[ emptyPlace[0] ][ emptyPlace[1]].replaceAll("[^0-9]", "");
			V.setPath(st1 +"L-");
			String st2=temp[ emptyPlace[0] ][ emptyPlace[1]].replaceAll("[^A-Z]", "");
			if(st2.equals("R")) V.setCost(30);
			if(st2.equals("G")) V.setCost(1);
		}
		V.setMove("L");
		return V;
	}

	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////operator right //////////////////////////////////////////////////////////////////
	public Vertex operatorRight(String [][] Mat){
		String [][] temp = new String[Mat.length][Mat[0].length];
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[0].length; j++) {
				temp[i][j] = Mat[i][j];
			}
		}
		int [] emptyPlace = searchEmpty(Mat);
		if(emptyPlace[1] - 1 >= 0) {
			if(Mat[ emptyPlace[0] ][ emptyPlace[1] - 1 ].contains("G") || Mat[ emptyPlace[0] ][ emptyPlace[1] - 1 ].contains("R") ) {
				temp[ emptyPlace[0] ][ emptyPlace[1] ]=temp[ emptyPlace[0] ][ emptyPlace[1] - 1 ];
				temp[ emptyPlace[0] ][ emptyPlace[1] - 1 ] = "_";
			}
		}
		Vertex V = new Vertex(temp);
		if(!temp[ emptyPlace[0] ][ emptyPlace[1]].equals("_")) {
			String st1=temp[ emptyPlace[0] ][ emptyPlace[1]].replaceAll("[^0-9]", "");
			V.setPath(st1 +"R-");
			String st2=temp[ emptyPlace[0] ][ emptyPlace[1]].replaceAll("[^A-Z]", "");
			if(st2.equals("R")) V.setCost(30);
			if(st2.equals("G")) V.setCost(1);
			
		}
		V.setMove("R");
		return V;
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////operator up ////////////////////////////////////////////////////////////////////
	public Vertex operatorUp(String [][] Mat){
		String [][] temp = new String[Mat.length][Mat[0].length];
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[0].length; j++) {
				temp[i][j] = Mat[i][j];
			}
		}
		int [] emptyPlace = searchEmpty(Mat);
		if(emptyPlace[0] + 1 < Mat.length ) {
			if(Mat[ emptyPlace[0] + 1 ][ emptyPlace[1] ].contains("G") || Mat[ emptyPlace[0] + 1 ][ emptyPlace[1] ].contains("R") ) {
				temp[ emptyPlace[0] ][ emptyPlace[1] ]=temp[ emptyPlace[0] + 1 ][ emptyPlace[1] ];
				temp[ emptyPlace[0] + 1 ][ emptyPlace[1] ] = "_";
			}
		}
		Vertex V = new Vertex(temp);
		if(!temp[ emptyPlace[0] ][ emptyPlace[1]].equals("_")) {
			String st1=temp[ emptyPlace[0] ][ emptyPlace[1]].replaceAll("[^0-9]", "");
			V.setPath(st1 +"U-");
			String st2=temp[ emptyPlace[0] ][ emptyPlace[1]].replaceAll("[^A-Z]", "");
			if(st2.equals("R")) V.setCost(30);
			if(st2.equals("G")) V.setCost(1);
		}
		V.setMove("U");
		return V;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////operator down ////////////////////////////////////////////////////////////////
	public Vertex operatorDown(String [][] Mat){
		String [][] temp = new String[Mat.length][Mat[0].length];
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[0].length; j++) {
				temp[i][j] = Mat[i][j];
			}
		}
		int [] emptyPlace = searchEmpty(Mat);
		if(emptyPlace[0] - 1 >= 0)
			if(Mat[ emptyPlace[0] - 1 ][ emptyPlace[1] ].contains("G") || Mat[ emptyPlace[0] - 1 ][ emptyPlace[1] ].contains("R") ) {
				temp[ emptyPlace[0] ][ emptyPlace[1] ]=temp[ emptyPlace[0] - 1 ][ emptyPlace[1] ];
				temp[ emptyPlace[0] - 1 ][ emptyPlace[1] ] = "_";
			}
		Vertex V = new Vertex(temp);
		if(!temp[ emptyPlace[0] ][ emptyPlace[1]].equals("_")) {
			String st1=temp[ emptyPlace[0] ][ emptyPlace[1]].replaceAll("[^0-9]", "");
			V.setPath(st1 +"D-");
			String st2=temp[ emptyPlace[0] ][ emptyPlace[1]].replaceAll("[^A-Z]", "");
			if(st2.equals("R")) V.setCost(30);
			if(st2.equals("G")) V.setCost(1);
		}
		V.setMove("D");
		return V;
	}
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////checking if eligible///////////////////////////////////
	
	public boolean checkifLeft(String[][]Mat) {
		int [] emptyPlace = searchEmpty(Mat);
		if(emptyPlace[1] + 1 < Mat[0].length && !Mat[emptyPlace[0]][emptyPlace[1]+1].contains("B"))
			return true;
		return false;
	}
	
	public boolean checkifRight(String[][]Mat) {
		int [] emptyPlace = searchEmpty(Mat);
		//System.out.println(Mat[emptyPlace[0]][emptyPlace[1]-1]);
		if (emptyPlace[1] - 1 >= 0 && !Mat[emptyPlace[0]][emptyPlace[1]-1].contains("B")) 
			return true;
		return false;
	}
	
	public boolean checkifUp(String[][]Mat) {
		int [] emptyPlace = searchEmpty(Mat);
		if(emptyPlace[0] + 1 < Mat.length && !Mat[emptyPlace[0]+1][emptyPlace[1]].contains("B")) 
			return true;
		return false;
	}
	
	public boolean checkifDown(String[][]Mat) {
		int [] emptyPlace = searchEmpty(Mat);
		if(emptyPlace[0] - 1 >= 0 && !Mat[emptyPlace[0]-1][emptyPlace[1]].contains("B")) 
			return true;
		return false;
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////OPERATOR WITH VERTEX ///////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////
	public Vertex operatorLeft(Vertex x){
		
		String [][] temp = new String[x.getMat().length][x.getMat()[0].length];
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[0].length; j++) {
				temp[i][j] = x.getMat()[i][j];
			}
		}
		
		int [] emptyPlace = searchEmpty(x.getMat());
		if(emptyPlace[1] + 1 < temp[0].length) {
			if(temp[ emptyPlace[0] ][ emptyPlace[1] + 1 ].contains("G") || temp[ emptyPlace[0] ][ emptyPlace[1] + 1 ].contains("R") ) {
				temp[ emptyPlace[0] ][ emptyPlace[1] ]=temp[ emptyPlace[0] ][ emptyPlace[1] + 1 ];
				temp[ emptyPlace[0] ][ emptyPlace[1] + 1 ] = "_";
			}
		}
		Vertex V = new Vertex(temp);
		if(!temp[ emptyPlace[0] ][ emptyPlace[1]].equals("_")) {
			String st1=temp[ emptyPlace[0] ][ emptyPlace[1]].replaceAll("[^0-9]", "");
			V.setPath(x.getPath() + st1 +"L-");
			String st2=temp[ emptyPlace[0] ][ emptyPlace[1]].replaceAll("[^A-Z]", "");
			if(st2.equals("R")) V.setCost(30 + x.getCost());
			if(st2.equals("G")) V.setCost(1 + x.getCost());
			V.setFather(x);
			V.setDepth(x.getDepth() + 1);
		}
		V.setMove("L");
		return V;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public Vertex operatorRight(Vertex x){
		String [][] temp = new String[x.getMat().length][x.getMat()[0].length];
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[0].length; j++) {
				temp[i][j] = x.getMat()[i][j];
			}
		}
		
		int [] emptyPlace = searchEmpty(x.getMat());
		if(emptyPlace[1] - 1 >= 0) {
			if(temp[ emptyPlace[0] ][ emptyPlace[1] - 1 ].contains("G") || temp[ emptyPlace[0] ][ emptyPlace[1] - 1 ].contains("R") ) {
				temp[ emptyPlace[0] ][ emptyPlace[1] ]=temp[ emptyPlace[0] ][ emptyPlace[1] - 1 ];
				temp[ emptyPlace[0] ][ emptyPlace[1] - 1 ] = "_";
			}
		}
		Vertex V = new Vertex(temp);
		if(!temp[ emptyPlace[0] ][ emptyPlace[1]].equals("_")) {
			String st1=temp[ emptyPlace[0] ][ emptyPlace[1]].replaceAll("[^0-9]", "");
			V.setPath(x.getPath() + st1 +"R-");
			String st2=temp[ emptyPlace[0] ][ emptyPlace[1]].replaceAll("[^A-Z]", "");
			if(st2.equals("R")) V.setCost(x.getCost() + 30);
			if(st2.equals("G")) V.setCost(x.getCost() + 1);
			V.setFather(x);
			V.setDepth(x.getDepth() + 1);
		}
		V.setMove("R");
		return V;
	}
	
	
	public Vertex operatorUp(Vertex x){
		
		String [][] temp = new String[x.getMat().length][x.getMat()[0].length];
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[0].length; j++) {
				temp[i][j] = x.getMat()[i][j];
			}
		}
		
		int [] emptyPlace = searchEmpty(temp);
		if(emptyPlace[0] + 1 < temp.length ) {
			if(temp[ emptyPlace[0] + 1 ][ emptyPlace[1] ].contains("G") || temp[ emptyPlace[0] + 1 ][ emptyPlace[1] ].contains("R") ) {
				temp[ emptyPlace[0] ][ emptyPlace[1] ]=temp[ emptyPlace[0] + 1 ][ emptyPlace[1] ];
				temp[ emptyPlace[0] + 1 ][ emptyPlace[1] ] = "_";
			}
		}
		Vertex V = new Vertex(temp);
		if(!temp[ emptyPlace[0] ][ emptyPlace[1]].equals("_")) {
			String st1=temp[ emptyPlace[0] ][ emptyPlace[1]].replaceAll("[^0-9]", "");
			V.setPath(x.getPath() + st1 +"U-");
			String st2=temp[ emptyPlace[0] ][ emptyPlace[1]].replaceAll("[^A-Z]", "");
			if(st2.equals("R")) V.setCost(x.getCost() + 30);
			if(st2.equals("G")) V.setCost(x.getCost() + 1);
			V.setFather(x);
			V.setDepth(x.getDepth() + 1);
		}
		V.setMove("U");
		return V;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////operator down ////////////////////////////////////////////////////////////////
	public Vertex operatorDown(Vertex x){
		String [][] temp = new String[x.getMat().length][x.getMat()[0].length];
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[0].length; j++) {
				temp[i][j] = x.getMat()[i][j];
			}
		}
		
		int [] emptyPlace = searchEmpty(temp);
		if(emptyPlace[0] - 1 >= 0)
			if(temp[ emptyPlace[0] - 1 ][ emptyPlace[1] ].contains("G") || temp[ emptyPlace[0] - 1 ][ emptyPlace[1] ].contains("R") ) {
				temp[ emptyPlace[0] ][ emptyPlace[1] ]=temp[ emptyPlace[0] - 1 ][ emptyPlace[1] ];
				temp[ emptyPlace[0] - 1 ][ emptyPlace[1] ] = "_";
			}
		Vertex V = new Vertex(temp);
		if(!temp[ emptyPlace[0] ][ emptyPlace[1]].equals("_")) {
			String st1=temp[ emptyPlace[0] ][ emptyPlace[1]].replaceAll("[^0-9]", "");
			V.setPath(x.getPath() + st1 +"D-");
			String st2=temp[ emptyPlace[0] ][ emptyPlace[1]].replaceAll("[^A-Z]", "");
			if(st2.equals("R")) V.setCost(x.getCost() + 30);
			if(st2.equals("G")) V.setCost(x.getCost() + 1);
			V.setFather(x);
			V.setDepth(x.getDepth() + 1);
		}
		V.setMove("D");
		return V;
	}
	
	
}
