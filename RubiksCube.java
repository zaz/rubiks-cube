import java.io.Console;

class Cube {
	// Define colors. XXX: Should use enums
	final int W = 0;  // White
	final int B = 1;  // Blue
	final int R = 2;  // Red
	final int G = 3;  // Green
	final int Y = 4;  // Yellow
	final int O = 5;  // Orange

	// Define directions. XXX: Should use enums
	final int C = 0;  // Clockwise
	final int I = 1;  // Counter-clockwise (Inverted)

	int[] front  = { W, W, W, W, W, W, W, W, W };
	int[] top    = { R, R, R, R, R, R, R, R, R };
	int[] bottom = { O, O, O, O, O, O, O, O, O };
	int[] left   = { B, B, B, B, B, B, B, B, B };
	int[] right  = { G, G, G, G, G, G, G, G, G };
	int[] back   = { Y, Y, Y, Y, Y, Y, Y, Y, Y };

	final String COMMANDS = "\n" +
"Rotate faces using: " +
"F(ront), R(ight), L(eft), T(op), D(ownwards), B(ack).\n" +
"Add i to rotate inverse (counter-clockwise).\n" +
"Commands can be strung together. Capitalization and spacing are ignored.\n" +
"e.g. Ri U Fi Ui  or  rIufIuI";

	void show() {
		System.out.printf(
		"\n\n           "+    top[0] +" "+    top[1] +" "+    top[2] +"\n"+
		    "           "+    top[3] +" "+    top[4] +" "+    top[5] +"\n"+
		    "           "+    top[6] +" "+    top[7] +" "+    top[8] +"\n\n"+

		"   "+  left[0] +" "+  left[1] +" "+  left[2] +"   "+
		       front[0] +" "+ front[1] +" "+ front[2] +"   "+
		       right[0] +" "+ right[1] +" "+ right[2] +"   "+
		        back[0] +" "+  back[1] +" "+  back[2] +"\n"+

		"   "+  left[3] +" "+  left[4] +" "+  left[5] +"   "+
		       front[3] +" "+ front[4] +" "+ front[5] +"   "+
		       right[3] +" "+ right[4] +" "+ right[5] +"   "+
		        back[3] +" "+  back[4] +" "+  back[5] +"\n"+

		"   "+  left[6] +" "+  left[7] +" "+  left[8] +"   "+
		       front[6] +" "+ front[7] +" "+ front[8] +"   "+
		       right[6] +" "+ right[7] +" "+ right[8] +"   "+
		        back[6] +" "+  back[7] +" "+  back[8] +"\n\n"+

		"           "+ bottom[0] +" "+ bottom[1] +" "+ bottom[2] +"\n"+
		"           "+ bottom[3] +" "+ bottom[4] +" "+ bottom[5] +"\n"+
		"           "+ bottom[6] +" "+ bottom[7] +" "+ bottom[8] +"\n\n");
	}

	void face(int[] face, int direction) {
		int[] twist = { face[2], face[1], face[0], face[3],
		                face[6], face[7], face[8], face[5] };
		if (direction == C) {
			face[2] = twist[2];
			face[1] = twist[3];
			face[0] = twist[4];
			face[3] = twist[5];
			face[6] = twist[6];
			face[7] = twist[7];
			face[8] = twist[0];
			face[5] = twist[1];
		} else if (direction == I) {
			face[2] = twist[6];
			face[1] = twist[7];
			face[0] = twist[0];
			face[3] = twist[1];
			face[6] = twist[2];
			face[7] = twist[3];
			face[8] = twist[4];
			face[5] = twist[5];
		}
	}

	void right(int direction) {
		face(right, direction);
		int[] twist = {  front[2],  front[5],  front[8],
		                bottom[2], bottom[5], bottom[8],
		                  back[6],   back[3],   back[0],
		                   top[2],    top[5],    top[8] };

		if (direction == C) {
			 front[2] =  twist[3];
			 front[5] =  twist[4];
			 front[8] =  twist[5];
			bottom[2] =  twist[6];
			bottom[5] =  twist[7];
			bottom[8] =  twist[8];
			  back[6] =  twist[9];
			  back[3] = twist[10];
			  back[0] = twist[11];
			   top[2] =  twist[0];
			   top[5] =  twist[1];
			   top[8] =  twist[2];
		} else if (direction == I) {
			 front[2] =  twist[9];
			 front[5] = twist[10];
			 front[8] = twist[11];
			bottom[2] =  twist[0];
			bottom[5] =  twist[1];
			bottom[8] =  twist[2];
			  back[6] =  twist[3];
			  back[3] =  twist[4];
			  back[0] =  twist[5];
			   top[2] =  twist[6];
			   top[5] =  twist[7];
			   top[8] =  twist[8];
		}
	}

	void left(int direction) {
		face(left, direction);
		int[] twist = {  front[6],  front[3],  front[0],
		                   top[6],    top[3],    top[0],
		                  back[2],   back[5],   back[8],
		                bottom[6], bottom[3], bottom[0] };

		if (direction == C) {
			 front[6] =  twist[3];
			 front[3] =  twist[4];
			 front[0] =  twist[5];
			   top[6] =  twist[6];
			   top[3] =  twist[7];
			   top[0] =  twist[8];
			  back[2] =  twist[9];
			  back[5] = twist[10];
			  back[8] = twist[11];
			bottom[6] =  twist[0];
			bottom[3] =  twist[1];
			bottom[0] =  twist[2];
		} else if (direction == I) {
			 front[6] =  twist[9];
			 front[3] = twist[10];
			 front[0] = twist[11];
			   top[6] =  twist[0];
			   top[3] =  twist[1];
			   top[0] =  twist[2];
			  back[2] =  twist[3];
			  back[5] =  twist[4];
			  back[8] =  twist[5];
			bottom[6] =  twist[6];
			bottom[3] =  twist[7];
			bottom[0] =  twist[8];
		}
	}

	void top(int direction) {
		face(top, direction);
		int[] twist = {  front[0],  front[1],  front[2],
		                 right[0],  right[1],  right[2],
		                  back[0],   back[1],   back[2],
		                  left[0],   left[1],   left[2] };

		if (direction == C) {
			 front[0] =  twist[3];
			 front[1] =  twist[4];
			 front[2] =  twist[5];
			 right[0] =  twist[6];
			 right[1] =  twist[7];
			 right[2] =  twist[8];
			  back[0] =  twist[9];
			  back[1] = twist[10];
			  back[2] = twist[11];
			  left[0] =  twist[0];
			  left[1] =  twist[1];
			  left[2] =  twist[2];
		} else if (direction == I) {
			 front[0] =  twist[9];
			 front[1] = twist[10];
			 front[2] = twist[11];
			 right[0] =  twist[0];
			 right[1] =  twist[1];
			 right[2] =  twist[2];
			  back[0] =  twist[3];
			  back[1] =  twist[4];
			  back[2] =  twist[5];
			  left[0] =  twist[6];
			  left[1] =  twist[7];
			  left[2] =  twist[8];
		}
	}

	void bottom(int direction) {
		face(bottom, direction);
		int[] twist = {  front[8],  front[7],  front[6],
		                  left[8],   left[7],   left[6],
		                  back[8],   back[7],   back[6],
		                 right[8],  right[7],  right[6] };

		if (direction == C) {
			 front[8] =  twist[3];
			 front[7] =  twist[4];
			 front[6] =  twist[5];
			  left[8] =  twist[6];
			  left[7] =  twist[7];
			  left[6] =  twist[8];
			  back[8] =  twist[9];
			  back[7] = twist[10];
			  back[6] = twist[11];
			 right[8] =  twist[0];
			 right[7] =  twist[1];
			 right[6] =  twist[2];
		} else if (direction == I) {
			 front[8] =  twist[9];
			 front[7] = twist[10];
			 front[6] = twist[11];
			  left[8] =  twist[0];
			  left[7] =  twist[1];
			  left[6] =  twist[2];
			  back[8] =  twist[3];
			  back[7] =  twist[4];
			  back[6] =  twist[5];
			 right[8] =  twist[6];
			 right[7] =  twist[7];
			 right[6] =  twist[8];
		}
	}

	void front(int direction) {
		face(front, direction);
		int[] twist = {    top[8],    top[7],    top[6],
		                  left[2],   left[5],   left[8],
		                bottom[0], bottom[1], bottom[2],
		                 right[6],  right[3],  right[0] };

		if (direction == C) {
			   top[8] =  twist[3];
			   top[7] =  twist[4];
			   top[6] =  twist[5];
			  left[2] =  twist[6];
			  left[5] =  twist[7];
			  left[8] =  twist[8];
			bottom[0] =  twist[9];
			bottom[1] = twist[10];
			bottom[2] = twist[11];
			 right[6] =  twist[0];
			 right[3] =  twist[1];
			 right[0] =  twist[2];
		} else if (direction == I) {
			   top[8] =  twist[9];
			   top[7] = twist[10];
			   top[6] = twist[11];
			  left[2] =  twist[0];
			  left[5] =  twist[1];
			  left[8] =  twist[2];
			bottom[0] =  twist[3];
			bottom[1] =  twist[4];
			bottom[2] =  twist[5];
			 right[6] =  twist[6];
			 right[3] =  twist[7];
			 right[0] =  twist[8];
		}
	}

	void back(int direction) {
		face(back, direction);
		int[] twist = {    top[0],    top[1],    top[2],
		                 right[2],  right[5],  right[8],
		                bottom[8], bottom[7], bottom[6],
		                  left[6],   left[3],   left[0] };

		if (direction == C) {
			   top[0] =  twist[3];
			   top[1] =  twist[4];
			   top[2] =  twist[5];
			 right[2] =  twist[6];
			 right[5] =  twist[7];
			 right[8] =  twist[8];
			bottom[8] =  twist[9];
			bottom[7] = twist[10];
			bottom[6] = twist[11];
			  left[6] =  twist[0];
			  left[3] =  twist[1];
			  left[0] =  twist[2];
		} else if (direction == I) {
			   top[0] =  twist[9];
			   top[1] = twist[10];
			   top[2] = twist[11];
			 right[2] =  twist[0];
			 right[5] =  twist[1];
			 right[8] =  twist[2];
			bottom[8] =  twist[3];
			bottom[7] =  twist[4];
			bottom[6] =  twist[5];
			  left[6] =  twist[6];
			  left[3] =  twist[7];
			  left[0] =  twist[8];
		}
	}

	// Overload functions to make clockwise rotation default:
	void right()  { right(C);  }
	void left()   { left(C);   }
	void top()    { top(C);    }
	void bottom() { bottom(C); }
	void front()  { front(C);  }
	void back()   { back(C);   }

	String rotate(String commands) {
		String invalid = "";
		commands = commands.toUpperCase();
		for (int x = 0; x < commands.length(); x++) {
			char command = commands.charAt(x);
			int direction = C;
			if ( x+1 < commands.length() && commands.charAt(x+1) == 'I') {
				direction = I;
				x++;
			}
			switch (command) {
				case 'R': right(direction);
				          break;
				case 'L': left(direction);
				          break;
				case 'T': top(direction);
				          break;
				case 'D': bottom(direction);
				          break;
				case 'F': front(direction);
				          break;
				case 'B': back(direction);
				          break;
				case ' ':
				default:  invalid += String.valueOf(command);
			}
		}
		return invalid;
	}
}

class Run {
	public static void main(String[] args) {
		String invalid = "";
		Console console = System.console();
		Cube cube = new Cube();
		while (true) {
			for (int x = 0; x < 50; x++) { System.out.println(); }
			if (invalid.length() > 0) {
				System.out.println("Invalid command: " + invalid);
				System.out.printf(cube.COMMANDS);
			}
			cube.show();
			String commands = console.readLine("Rotate: ");
			invalid = cube.rotate(commands);
		}
	}
}

class Test {
	public static void main(String[] args) {
		Cube cube = new Cube();

		cube.rotate("FTBLDRFTBLDR" + "RiDiLiBiTiFiRiDiLiBiTiFi");

		for (int y = 0; y < 8; y++) {
			if (! (  cube.front[y] == cube.W &&    cube.top[y] == cube.R &&
			          cube.back[y] == cube.Y &&   cube.left[y] == cube.B &&
			        cube.bottom[y] == cube.O &&  cube.right[y] == cube.G) ) {
				System.err.println("ERROR: Rotation not working properly.");
				cube.show();
				System.exit(1);
			}
			if (y == 7) { System.exit(0); }
		}
	}
}



// Map:
//          0 1 2
//          3 4 5    // Top
//          6 7 8
//
// 0 1 2    0 1 2    0 1 2    0 1 2
// 3 4 5    3 4 5    3 4 5    3 4 5    // Left // Front // Right // Back
// 6 7 8    6 7 8    6 7 8    6 7 8
//
//          0 1 2
//          3 4 5    // Bottom ('Downwards')
//          6 7 8

