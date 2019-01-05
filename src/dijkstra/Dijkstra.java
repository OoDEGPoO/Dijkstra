/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijkstra;

/**
 *
 * @author diego
 */
public class Dijkstra {
    
    private static final int N = 5;
    private static final int MAX = Integer.MAX_VALUE/2;
    
    private static final int[][] M_EJEMPLO =    {{0,    7,  MAX,    2,  MAX}
                                                ,{MAX,  0,  1,      2,  MAX}
                                                ,{MAX,  MAX,0,      MAX,5}
                                                ,{MAX,  3,  8,      0,  5}
                                                ,{MAX,  MAX,4,      MAX,0}
    };
    
    /**
     * Realiza la busqueda del Camino Mínimo del Grafo dado por la matriz de costes M desde el nodo origen
     * @param M
     * @param origen
     * @return 
     */
    
    public static int[] Dijkstra(int[][] M, int origen){
        int[] dist = new int[N];
        boolean[] selec = new boolean[N];
        int i, j, min, s = origen;//i y j son para las iteraciones, min es un auxiliar para el coste mínimo y s guardará el nodo desde el que se parte en las iteraciones
        int[] out = M[origen];
        
        
        for (i = 0; i<N; i++){// Inicialización de las variables y arrays
            dist[i] = M[origen][i];//distancia o coste al nodo, se inicializa tomando el origen
            selec[i] = false;//ningún nodo ha sido seleccionado
            if (out[i] > 0 && out[i] < MAX) out[i]=origen;
            else out[i]=0;
        }
        selec[origen]=true;//el único que se selecciona al principio es el propio origen
        
        for (i = 0; i<N-1; i++){
            min = MAX;
            for (j=0; j<N; j++){//Buscamos el nodo al que cueste menos llegar (Camino Mínimo)
                if (!selec[j] && min > dist[j]){//si ya ha sido seleccionado, no se toma
                    min = dist[j];
                    s = j;
                }
            }
            
            selec[s] = true;//se marca el nodo seleccionado para el camino mínimo
            
            for (j=0; j<N; j++){//desde el nodo, buscamos nuevos nodos y actualizamos la distancia a estos
                if (!selec[j] && dist[j] > (dist[s]+M[s][j])){//si ya ha sido seleccionado, no se mira, si la distancia con la que se alcanza es mayor, se actualiza
                    dist[j] = dist[s] + M[s][j];
                    out[j] = s;//se añade al camino
                }
            }
        }
        
        return out;
    }
    
    public static void main(String[] args) {
        int[] out = Dijkstra(M_EJEMPLO, 0);
        
        System.out.print(out[0]);
        for (int i = 1; i < out.length; i++){
            System.out.print(", " + out[i]);
        }
        
        System.out.println();
    }
    
}
