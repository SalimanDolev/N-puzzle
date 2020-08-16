import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class Ex1 {
	static String AlgoTu;
	static boolean withTime;
	static boolean WithOpen;
	static int n;
	static int m;
	static Vector<Integer> Black = new Vector<Integer>();
	static Vector<Integer> Red = new Vector<Integer>();
	static Vector<Integer> Green = new Vector<Integer>();
	static String [][] startingMat;

	static String Final [][];

	public static void ReadFile(File obj) {
		Scanner myReader;
		try {
			myReader = new Scanner(obj);
			AlgoTu = myReader.nextLine();//Read the Name of the algo to use

			withTime = myReader.nextLine().equals("with time")  ? true : false; // print with runtime

			WithOpen = myReader.nextLine().equals("with open") ? true : false; // print with open list


			// Initialize the size of the matrix
			String sizeOfMat = myReader.nextLine();
			String[] size = new String [2];
			size = sizeOfMat.split("x");
			n = Integer.parseInt(size[0]);
			m = Integer.parseInt(size[1]);

			// scanner for black blocks
			String blackBlocks = myReader.nextLine();
			blackBlocks = blackBlocks.replaceAll(" ","");
			String [] black = blackBlocks.length() > 6 ? blackBlocks.substring(6).split(",") : null;
			if (black != null) {
				for (int i = 0; i < black.length; i++) {
					Black.add(Integer.parseInt(black[i]));
				}
			}

			// scanner for red blocks
			String redBlocks = myReader.nextLine();
			redBlocks = redBlocks.replaceAll(" ", "");
			String [] red = redBlocks.length() > 4 ? redBlocks.substring(4).split(",") : null;
			if(red != null) {
				for (int i = 0; i < red.length; i++) {
					Red.add(Integer.parseInt(red[i]));
				}
			}


			// The green blocks
			int place = 1;
			while(place < n*m-1) {
				if(Red.contains(place) || Black.contains(place)) {
					place++;
				}
				else {
					Green.add(place);
					place++;}
			}

			// the start matrix for the problem
			startingMat = new String [n][m];
			for (int i = 0; i < n; i++) {
				String line = myReader.nextLine();
				String temp [] = new String [m];
				temp = line.split(",");
				for (int j = 0; j < m; j++) {
					if(!temp[j].equals("_")) {
						startingMat[i][j] = temp[j];
						if(Black.contains(Integer.parseInt(startingMat[i][j]))) startingMat[i][j]+="B";
						else if(Red.contains(Integer.parseInt(startingMat[i][j]))) startingMat[i][j]+="R";
						else startingMat[i][j]+="G";
					}else {
						startingMat[i][j] = "_";
					}

				}
				System.out.println();
			}

			// close the starting scanner
			myReader.close();

			// Build the answer Matrix
			Final = new String [n][m];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					Final[i][j]= ""+(j+1 + (i*m));
					if(Red.contains((j+1 + (i*m)))) {
						Final[i][j]+="R";
					}else if (Black.contains((j+1 + (i*m)))){
						Final[i][j]+="B";
					}else Final[i][j]+="G";
				}
			}
			Final [n-1][m-1] = "_";

		} catch (FileNotFoundException e) {
			System.out.println("File not exist");
			e.printStackTrace();
		}


	}


	public static boolean ChecksMat(String[][] mat , String [][] mat2) {
		for (int i = 0; i < mat2.length; i++) {
			for (int j = 0; j < mat2[0].length; j++) {
				if(!mat[i][j].contains(mat2[i][j]))
					return false;
			}
		}
		return true;
	}


	public static void main(String[] args) throws FileNotFoundException {
		File object = new File ("input.txt"); // enter the file name
		ReadFile(object);
		SearchAlgo s = new SearchAlgo();

		switch(AlgoTu) {
		case "BFS":
			s.BFS(startingMat, Final, WithOpen, withTime);
			break;
		case "DFID":
			s.FIDF(startingMat, Final, WithOpen, withTime);
			break;
		case "A*":
			s.aStar(startingMat, Final, WithOpen, withTime);
			break;
		case "IDA*":
			s.IDAstar(startingMat, Final, WithOpen, withTime);
			break;
		case "DFBnB":
			s.DFBnB(startingMat, Final, WithOpen, withTime);
			break;
		}


		
	}
}


