import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Game {
	private static final char[] PARTICIPANTS = {'G', 'B'};
	  private final int breadth, tall;

	  private final char[][] box;

	  private int endCol = -1, startTop = -1;



	  public Game(int w1, int h1) {

	    breadth= w1;

	    tall = h1;

	    box = new char[h1][];

	    for (int j = 0; j < h1; j++) {

	      Arrays.fill(box[j] = new char[w1], '.');

	    }

	  }

public String toString() {

	  return IntStream.range(0,  breadth).

	         mapToObj(Integer::toString).

	         collect(Collectors.joining()) + 

	         "\n" +

	         Arrays.stream(box).

	         map(String::new).

	         collect(Collectors.joining("\n"));

	}
public String sideways() {

	  return new String(box[endCol]);

	}

	public String verti() {

	  StringBuilder b = new StringBuilder(tall);



	  for (int a = 0; a < tall; a++) {

	    b.append(box[a][endCol]);

	  }



	  return b.toString();

	}
	public String sideDiagonal() {

		  StringBuilder b = new StringBuilder(tall);



		  for (int a = 0; a < tall; a++) {

		    int m = endCol + startTop - a;



		    if (0 <= m && m < breadth) {

		      b.append(box[a][m]);

		    }

		  }



		  return b.toString();

		}
	public String backsideways() {

		  StringBuilder b = new StringBuilder(tall);



		  for (int c = 0; c < tall; c++) {

		    int m = endCol - startTop + c;



		    if (0 <= m && m < breadth) {

		      b.append(box[c][m]);

		    }

		  }



		  return b.toString();

		}
	public static boolean has(String let, String str) {

		  return let.indexOf(str) >= 0;

		}

		public boolean isFirst() {

		  if (endCol == -1) {

		    System.err.println("No move has been made yet");

		    return false;

		  }



		  char comp = box[startTop][endCol];

		  String stk = String.format("%c%c%c%c", comp, comp, comp, comp);


		  return has(sideways(), stk) || 

		         has(verti(), stk) || 

		         has(sideDiagonal(), stk) || 

		         has(backsideways(), stk);
		}
		public void select(char icon, Scanner sa) {

			  do {

			    System.out.println("\nPlayer " + icon + " turn: ");

			    int kol = sa.nextInt();

			    if (!(0 <= kol && kol < breadth)) {

			      System.out.println("Column must be between 0 and " + (breadth - 1));

			      continue;

			    }


			    for (int i = tall - 1; i >= 0; i--) {

			      if (box[i][kol] == '.') {

			        box[startTop = i][endCol = kol] = icon;

			        return;

			      }
			    
			    }	    
			    	    System.out.println("Column " + kol + " is full.");

			    	  } while (true);

			    	}
}