import java.util.Arrays;

import java.util.Scanner;

import java.util.stream.Collectors;

import java.util.stream.IntStream;

public class Main {

				  private static final char[] PARTICIPANTS = {'G', 'B'};
				
				  private final int breadth, tall;
				
				  private final char[][] box;
				
				  private int endCol = -1, startTop = -1;
				


  public Main(int w1, int h1) {

				    breadth = w1;
				
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

							    return new String(box[startTop]);

  }

  public String vert() {

							    StringBuilder b = new StringBuilder(tall);
							
							
							
							    for (int j = 0; j < tall; j++) {
							
							      b.append(box[j][endCol]);
							
							    }
							
							
							
							    return b.toString();

  }

  public String crosssideways() {

							    StringBuilder b = new StringBuilder(tall);
							
							
							
							    for (int i = 0; i < tall; i++) {
							
							      int m = endCol + startTop - i;
							
							
							
							      if (0 <= m && m < breadth) {
							
							        b.append(box[i][m]);
							
							      }
							
							    }
							
							
							
							    return b.toString();

  }

  public String vertsideways() {

								    StringBuilder b = new StringBuilder(tall);
								
								
								
								    for (int i = 0; i < tall; i++) {
								
								      int m = endCol - startTop + i;
								
								
								
								      if (0 <= m && m < breadth) {
								
								        b.append(box[i][m]);
								
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
									
									           has(vert(), stk) || 
									
									           has(crosssideways(), stk) || 
									
									           has(vertsideways(), stk);

  }

  public void select(char icon, Scanner sa) {

							    do {
							
							      System.out.println("\nPlayer " + icon + " turn: ");
							
							      int kol = sa.nextInt();
							
							      if (!(0 <= kol && kol < breadth)) {
							
							        System.out.println("Column must be between 0 and " + (breadth - 1));
							
							        continue;
							
							      }
							
							      for (int a = tall - 1; a >= 0; a--) {
							
							        if (box[a][kol] == '.') {
							
							          box[startTop = a][endCol = kol] = icon;
							
							          return;
							
							        }
							
							      }
							
							      System.out.println("Column " + kol + " is full.");
							
							    } while (true);

  }



  public static void main(String[] args) {
								
								    try (Scanner sa = new Scanner(System.in)) {
								
								      int tall = 6; int breadth = 7; 
								      int total = tall * breadth;
								
								      Main dash = new Main(breadth, tall);
								
								      System.out.println("Use 0-" + (breadth - 1) + " to choose a column");
								
								      System.out.println(dash);
								
								      for (int gamer = 0; total-- > 0; gamer = 1 - gamer) {
								
								        char icon = PARTICIPANTS[gamer];
								
								        dash.select(icon, sa);
								
								
								        System.out.println(dash);
								
								        if (dash.isFirst()) {
								
								          System.out.println("\nPlayer " + icon + " wins!");
								
								          return;
								
								        }
								
								      }
								
								    
								
								      System.out.println("Game over. No winner. Try again!");
      
    }}}