import java.io.Console;

class Cube {
	// Define colors. XXX: Should use enums
	final int W = 0;  // White
	final int B = 1;  // Blue
	final int R = 2;  // Red
	final int G = 3;  // Green
	final int Y = 4;  // Yellow
	final int O = 5;  // Orange

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

	void face(int[] face, int amount) {
		int[] twist = { face[2], face[1], face[0], face[3],
		                face[6], face[7], face[8], face[5] };
		face[2] = twist[ (2*amount   ) % 8 ];
		face[1] = twist[ (2*amount +1) % 8 ];
		face[0] = twist[ (2*amount +2) % 8 ];
		face[3] = twist[ (2*amount +3) % 8 ];
		face[6] = twist[ (2*amount +4) % 8 ];
		face[7] = twist[ (2*amount +5) % 8 ];
		face[8] = twist[ (2*amount +6) % 8 ];
		face[5] = twist[ (2*amount +7) % 8 ];
	}

	void right(int amount) {
		face(right, amount);
		int[] twist = {  front[2],  front[5],  front[8],
		                bottom[2], bottom[5], bottom[8],
		                  back[6],   back[3],   back[0],
		                   top[2],    top[5],    top[8] };
		 front[2] = twist[ (3*amount    ) % 12 ];
		 front[5] = twist[ (3*amount  +1) % 12 ];
		 front[8] = twist[ (3*amount  +2) % 12 ];
		bottom[2] = twist[ (3*amount  +3) % 12 ];
		bottom[5] = twist[ (3*amount  +4) % 12 ];
		bottom[8] = twist[ (3*amount  +5) % 12 ];
		  back[6] = twist[ (3*amount  +6) % 12 ];
		  back[3] = twist[ (3*amount  +7) % 12 ];
		  back[0] = twist[ (3*amount  +8) % 12 ];
		   top[2] = twist[ (3*amount  +9) % 12 ];
		   top[5] = twist[ (3*amount +10) % 12 ];
		   top[8] = twist[ (3*amount +11) % 12 ];
	}

	void left(int amount) {
		face(left, amount);
		int[] twist = {  front[6],  front[3],  front[0],
		                   top[6],    top[3],    top[0],
		                  back[2],   back[5],   back[8],
		                bottom[6], bottom[3], bottom[0] };
		 front[6] = twist[ (3*amount    ) % 12 ];
		 front[3] = twist[ (3*amount  +1) % 12 ];
		 front[0] = twist[ (3*amount  +2) % 12 ];
		   top[6] = twist[ (3*amount  +3) % 12 ];
		   top[3] = twist[ (3*amount  +4) % 12 ];
		   top[0] = twist[ (3*amount  +5) % 12 ];
		  back[2] = twist[ (3*amount  +6) % 12 ];
		  back[5] = twist[ (3*amount  +7) % 12 ];
		  back[8] = twist[ (3*amount  +8) % 12 ];
		bottom[6] = twist[ (3*amount  +9) % 12 ];
		bottom[3] = twist[ (3*amount +10) % 12 ];
		bottom[0] = twist[ (3*amount +11) % 12 ];
	}

	void top(int amount) {
		face(top, amount);
		int[] twist = {  front[0],  front[1],  front[2],
		                 right[0],  right[1],  right[2],
		                  back[0],   back[1],   back[2],
		                  left[0],   left[1],   left[2] };
		 front[0] = twist[ (3*amount    ) % 12 ];
		 front[1] = twist[ (3*amount  +1) % 12 ];
		 front[2] = twist[ (3*amount  +2) % 12 ];
		 right[0] = twist[ (3*amount  +3) % 12 ];
		 right[1] = twist[ (3*amount  +4) % 12 ];
		 right[2] = twist[ (3*amount  +5) % 12 ];
		  back[0] = twist[ (3*amount  +6) % 12 ];
		  back[1] = twist[ (3*amount  +7) % 12 ];
		  back[2] = twist[ (3*amount  +8) % 12 ];
		  left[0] = twist[ (3*amount  +9) % 12 ];
		  left[1] = twist[ (3*amount +10) % 12 ];
		  left[2] = twist[ (3*amount +11) % 12 ];
	}

	void bottom(int amount) {
		face(bottom, amount);
		int[] twist = {  front[8],  front[7],  front[6],
		                  left[8],   left[7],   left[6],
		                  back[8],   back[7],   back[6],
		                 right[8],  right[7],  right[6] };
		 front[8] = twist[ (3*amount    ) % 12 ];
		 front[7] = twist[ (3*amount  +1) % 12 ];
		 front[6] = twist[ (3*amount  +2) % 12 ];
		  left[8] = twist[ (3*amount  +3) % 12 ];
		  left[7] = twist[ (3*amount  +4) % 12 ];
		  left[6] = twist[ (3*amount  +5) % 12 ];
		  back[8] = twist[ (3*amount  +6) % 12 ];
		  back[7] = twist[ (3*amount  +7) % 12 ];
		  back[6] = twist[ (3*amount  +8) % 12 ];
		 right[8] = twist[ (3*amount  +9) % 12 ];
		 right[7] = twist[ (3*amount +10) % 12 ];
		 right[6] = twist[ (3*amount +11) % 12 ];
	}

	void front(int amount) {
		face(front, amount);
		int[] twist = {    top[8],    top[7],    top[6],
		                  left[2],   left[5],   left[8],
		                bottom[0], bottom[1], bottom[2],
		                 right[6],  right[3],  right[0] };
		   top[8] = twist[ (3*amount    ) % 12 ];
		   top[7] = twist[ (3*amount  +1) % 12 ];
		   top[6] = twist[ (3*amount  +2) % 12 ];
		  left[2] = twist[ (3*amount  +3) % 12 ];
		  left[5] = twist[ (3*amount  +4) % 12 ];
		  left[8] = twist[ (3*amount  +5) % 12 ];
		bottom[0] = twist[ (3*amount  +6) % 12 ];
		bottom[1] = twist[ (3*amount  +7) % 12 ];
		bottom[2] = twist[ (3*amount  +8) % 12 ];
		 right[6] = twist[ (3*amount  +9) % 12 ];
		 right[3] = twist[ (3*amount +10) % 12 ];
		 right[0] = twist[ (3*amount +11) % 12 ];
	}

	void back(int amount) {
		face(back, amount);
		int[] twist = {    top[0],    top[1],    top[2],
		                 right[2],  right[5],  right[8],
		                bottom[8], bottom[7], bottom[6],
		                  left[6],   left[3],   left[0] };
		   top[0] = twist[ (3*amount    ) % 12 ];
		   top[1] = twist[ (3*amount  +1) % 12 ];
		   top[2] = twist[ (3*amount  +2) % 12 ];
		 right[2] = twist[ (3*amount  +3) % 12 ];
		 right[5] = twist[ (3*amount  +4) % 12 ];
		 right[8] = twist[ (3*amount  +5) % 12 ];
		bottom[8] = twist[ (3*amount  +6) % 12 ];
		bottom[7] = twist[ (3*amount  +7) % 12 ];
		bottom[6] = twist[ (3*amount  +8) % 12 ];
		  left[6] = twist[ (3*amount  +9) % 12 ];
		  left[3] = twist[ (3*amount +10) % 12 ];
		  left[0] = twist[ (3*amount +11) % 12 ];
	}

	// Overload functions to make clockwise rotation default:
	void right()  { right(1);  }
	void left()   { left(1);   }
	void top()    { top(1);    }
	void bottom() { bottom(1); }
	void front()  { front(1);  }
	void back()   { back(1);   }

	String rotate(String commands) {
		String invalid = "";
		commands = commands.toUpperCase();
		for (int x = 0; x < commands.length(); x++) {
			char command = commands.charAt(x);
			int amount = 1;
			if ( x+1 < commands.length() && commands.charAt(x+1) == 'I') {
				amount = 3;
				x++;
			}
			switch (command) {
				case 'R': right(amount);
				          break;
				case 'L': left(amount);
				          break;
				case 'T': top(amount);
				          break;
				case 'D': bottom(amount);
				          break;
				case 'F': front(amount);
				          break;
				case 'B': back(amount);
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

