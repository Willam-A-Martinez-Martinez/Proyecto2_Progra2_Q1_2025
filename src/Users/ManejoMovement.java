package Users;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;



public class ManejoMovement {
    private static final String BASE_DIRECTORY = "Usuarios/";

    public void saveMovements(String username, Movement manager) {
        if (username == null || username.isEmpty()) {
            System.out.println("ERROR: Username is null or empty.");
            return;
        }

        File userDir = new File(BASE_DIRECTORY + username);
        if (!userDir.exists()) {
            userDir.mkdirs();
        }

        File file = new File(userDir, "movimientos.txt");

        // Leer movimientos anteriores
        int previousTotal = loadMovements(username);
        int newTotal = previousTotal + manager.getTotalMovements(); // Sumar movimientos

        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file, false)))) {
            pw.println(newTotal); // Guardar el nuevo total de movimientos
            System.out.println("Movimientos guardados: " + newTotal);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Verificar si el archivo realmente se ha actualizado
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String contenido = br.readLine();
            System.out.println("Contenido actual del archivo después de escribir: " + contenido);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int loadMovements(String username) {
        System.out.println("[DEBUG] Entrando en loadMovements con username: " + username);

        if (username == null || username.isEmpty()) {
            System.out.println("[ERROR] Username es null o vacío.");
            return 0;
        }

        File file = new File(BASE_DIRECTORY + username + "/movimientos.txt");
        System.out.println("[DEBUG] Verificando existencia del archivo: " + file.getAbsolutePath());

        if (!file.exists()) {
            System.out.println("[ERROR] Archivo no existe en loadMovements.");
            return 0; // Si el archivo no existe, retornamos 0
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            System.out.println("[DEBUG] Archivo encontrado. Intentando leer...");

            String line = br.readLine();
            System.out.println("[DEBUG] Línea leída: " + (line == null ? "null" : "'" + line + "'"));

            if (line == null || line.trim().isEmpty()) { // Manejar archivos vacíos
                System.out.println("[ERROR] El archivo existe, pero está vacío.");
                return 0;
            }

            try {
                int movimientos = Integer.parseInt(line.trim()); // Trim elimina espacios en blanco
                System.out.println("[DEBUG] Logró leer el loadMovements: " + movimientos);
                return movimientos;
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] El archivo contiene un valor inválido: '" + line + "'");
                e.printStackTrace();
                return 0;
            }

        } catch (IOException e) {
            System.out.println("[ERROR] Error al intentar leer el archivo.");
            e.printStackTrace();
            return 0;
        }
    }
}
