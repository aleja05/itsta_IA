import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;

public class BFS {

	public static int cont =0;

	public static void main(String[] args) {
		
		// Dimension del tablero
		int N = 4;
		cont = 0;
		Tablero respuesta = DFS(N);
	
		if (respuesta != null) {
			imprimirTablero(respuesta);
		}
		System.out.println(cont);
	}

	/**
	 * Imprime el tablero
	 * 
	 * @param respuesta
	 */
	private static void imprimirTablero(Tablero tablero) {
		int n = tablero.reinas.length;
		char[][] reinas = new char[n][n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(reinas[i], 'X');
		}
		for (int i = 0; i < n; i++) {
			reinas[tablero.reinas[i]][i] = 'R';
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(reinas[i][j] + " ");
			}
			System.out.println();
		}

	}

	/**
	 * Validar si las reinas ubicadas hasta la columna n son validas y no
	 * existen choques
	 * 
	 * @param q
	 *            tablero de reinas
	 * @param n
	 *            columna hasta donde se quiere verificar
	 * @return true o false dependiendo la ubicaci'on de las reinas
	 */
	public static boolean exito(int[] q){
		
		for (int i = 0; i < q.length; i++) {
			for(int j = i+1; j < q.length; j++){
			if (q[i] == q[j])
				return false; // same column
			if ((q[i] - q[j]) == (j - i))
				return false; // same major diagonal
			if ((q[j] - q[i]) == (j - i))
				return false; // same minor diagonal
			
			}
		}
		return true;
	}

	/**
	 * 
	 * @param tablero
	 *            tablero con las reinas ubicadas
	 * @return true o false si un tablero ha posido ser completado exitosamente
	 */
	public static boolean completo(Tablero tablero) {

		for (int i = 0; i < tablero.reinas.length; i++) {
			if (tablero.reinas[i] == -1)
				return false;
		}
		
		
		
		return exito(tablero.reinas);
	}

	/**
	 * Busqueda en profundidad para determinar si existe una configuraci'on en
	 * un tablero de NxX donde se puedan ubicar N reinas
	 * 
	 * @param N
	 *            dimensiones del tablero y cantidad de reinas a ubicar
	 * @return Tablero con las configuraci'on exitosa, en caso de no existir
	 *         configuraci'on posible retorna null
	 */
	public static Tablero DFS(int N) {
		// Abierto <- X

		Stack<Tablero> stack = new Stack<Tablero>();
		int[] reinas_inicial = new int[N];
		Arrays.fill(reinas_inicial, -1);

		Tablero x = new Tablero(reinas_inicial);

		// T con nodo inicial X
		stack.add(x);

		// Crear el conjunto cerrado
		HashSet<String> visitados = new HashSet<String>();

		// mientras abierto != []
		while (!stack.isEmpty()) {
			// remover el primer elemento X del conjunto abierto
			Tablero tablero = stack.pop();
			cont++;
			// si X es un estado objetivo
			if (completo(tablero)) {
					
				System.out.println("Camino en T hasta X "
						+ Arrays.toString(tablero.reinas));
				return tablero;

			} else {

				// Agregar X al conjunto CERRADO
				visitados.add(tablero.toString());
				// Generar el conjunto de sucesores admisibles del estado X
				ArrayList<Tablero> sucesores = getSucesores(tablero);
				for (int i = 0; i < sucesores.size(); i++) {
					if(!visitados.contains(sucesores.get(i).toString()))
						stack.add(sucesores.get(i));

				}

			}

		}
		System.out.println("fracaso, no existe respuesta");
		return null;
	}
	/**
	 * Obtener los posibles tableros sucesores de un tablero
	 * @param tablero estado del tablero de donde se parte
	 * @return lista de tableros con los posibles estados siguientes
	 */
	private static ArrayList<Tablero> getSucesores(Tablero tablero) {
		int[] reinas = tablero.reinas;
		int inicio = 0;
		//determinar la columna hasta donde se han ubicado reinas
		for (int i = 0; i < reinas.length; i++) {
			if (reinas[i] == -1) {
				inicio = i;
				break;
			}
		}
		
		ArrayList<Tablero> sucesores = new ArrayList<Tablero>();
		if (inicio != -1) {
			for (int i = 0; i < reinas.length; i++) {
				reinas[inicio] = i;
				//si es valido unicar la reina en esa posici'on
				//if (exito(reinas, inicio)) {
					//agregar el tablero a la lista de posibles sucesores
					sucesores.add(new Tablero(Arrays.copyOf(reinas,
							reinas.length)));
				//}
			}
		}
		return sucesores;
	}
}

/**
 * Clase que representa un tablero
 * 
 *
 */

