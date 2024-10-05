import java.util.Random;
import java.util.Scanner;

public class JuegoDeLaVida {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Solicitar tamaño del tablero
        System.out.print("Ingrese el tamaño del tablero: ");
        int n = sc.nextInt();
        sc.nextLine();  // Limpiar el buffer

        boolean[][] tablero = new boolean[n][n];
        boolean[][] nuevoTablero = new boolean[n][n];
        Random r = new Random();

        int gen = 0;

        // Inicializar tablero aleatoriamente
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tablero[i][j] = r.nextBoolean();
            }
        }

        while (true) {
            System.out.println("Generación " + gen);
            
            // Mostrar tablero
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(tablero[i][j] ? "■ " : "□ ");
                }
                System.out.println();
            }

            // Calcular siguiente generación
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int vecinosVivos = 0;
                    for (int di = -1; di <= 1; di++) {
                        for (int dj = -1; dj <= 1; dj++) {
                            if (di == 0 && dj == 0) continue;
                            int fi = (i + di + n) % n;
                            int fj = (j + dj + n) % n;
                            if (tablero[fi][fj]) vecinosVivos++;
                        }
                    }
                    nuevoTablero[i][j] = (tablero[i][j] && (vecinosVivos == 2 || vecinosVivos == 3)) || (!tablero[i][j] && vecinosVivos == 3);
                }
            }

            // Intercambiar referencias de los tableros en lugar de copiar
            boolean[][] temp = tablero;
            tablero = nuevoTablero;
            nuevoTablero = temp;

            gen++;

            System.out.print("Presione Enter para la siguiente generación o 'q' para salir: ");
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("q")) {
                break;
            }
        }
        sc.close();
    }
}

