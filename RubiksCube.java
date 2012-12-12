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

	void twist(int[] face, int amount,
	             int[] face0, int[] face1, int[] face2, int[] face3,
	               int s00, int s01, int s02,   int s10, int s11, int s12,
	                 int s20, int s21, int s22,   int s30, int s31, int s32) {

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

		int[] edges = { face0[s00], face0[s01], face0[s02],
		                face1[s10], face1[s11], face1[s12],
		                face2[s20], face2[s21], face2[s22],
		                face3[s30], face3[s31], face3[s32] };
		face0[s00] = edges[ (3*amount    ) % 12 ];
		face0[s01] = edges[ (3*amount  +1) % 12 ];
		face0[s02] = edges[ (3*amount  +2) % 12 ];
		face1[s10] = edges[ (3*amount  +3) % 12 ];
		face1[s11] = edges[ (3*amount  +4) % 12 ];
		face1[s12] = edges[ (3*amount  +5) % 12 ];
		face2[s20] = edges[ (3*amount  +6) % 12 ];
		face2[s21] = edges[ (3*amount  +7) % 12 ];
		face2[s22] = edges[ (3*amount  +8) % 12 ];
		face3[s30] = edges[ (3*amount  +9) % 12 ];
		face3[s31] = edges[ (3*amount +10) % 12 ];
		face3[s32] = edges[ (3*amount +11) % 12 ];
	}

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
				case 'R': twist(  right, amount,
				           front, bottom,   back,    top,
				           2,5,8,  2,5,8,  6,3,0,  2,5,8 );
				          break;
				case 'L': twist(   left, amount,
				           front,    top,   back, bottom,
				           6,3,0,  6,3,0,  2,5,8,  6,3,0 );
				          break;
				case 'T': twist(    top, amount,
				           front,  right,   back,   left,
				           0,1,2,  0,1,2,  0,1,2,  0,1,2 );
				          break;
				case 'D': twist( bottom, amount,
				           front,   left,   back,  right,
				           8,7,6,  8,7,6,  8,7,6,  8,7,6 );
				          break;
				case 'F': twist(  front, amount,
				             top,   left, bottom,  right,
				           8,7,6,  2,5,8,  0,1,2,  6,3,0 );
				          break;
				case 'B': twist(   back, amount,
				             top,  right, bottom,   left,
				           0,1,2,  2,5,8,  8,7,6,  6,3,0 );
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

