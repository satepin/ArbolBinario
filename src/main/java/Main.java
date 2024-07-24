import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int menu = 0;
    ArbolBinario arbol = new ArbolBinario();

    
    while (menu != 5) {
      System.out.println("\n1.Ver Arbol\n2.Insertar Nodo\n3.Buscar Nodo\n4.Eliminar Nodo\n5.Salir");

      try{
        menu = sc.nextInt();
      }catch(Exception invalido){//llama recursivamente a la toma de dato hasta obtener un valor valido y lo retorna al pedido original. desconozco si esta es la forma mas apropiada de implementar la excepcion
        sc.next();
        System.out.println("Ingrese un valor numero entero");
        menu = 0;
      }
      
      switch (menu) {
        case 1: // ver arbol
          System.out.println("\n1.Preorden\n2.Inorden\n3.Postorden");
          menu = sc.nextInt();
          
          switch (menu){ // llama un segundo menu para elegir el tipo de recorrido
            case 1:
              arbol.imprimirPreOrden(arbol.raiz, 1);
              break;
            case 2:
              arbol.imprimirInOrden(arbol.raiz, 1);
              break;
            case 3:
              arbol.imprimirPostOrden(arbol.raiz, 1);
              break;
          }
          break;
        case 2: // crear nodo
          arbol.crearNodo();
          break;
        case 3: // buscar nodo
          arbol.busqueda(arbol.pedirDato(), arbol.raiz);
          break;
        case 4:
          arbol.eliminar(arbol.raiz, arbol.pedirDato());
          break;
        case 5:
          System.out.println("Finalizando Programa...");
          break;
      }
    }
    sc.close();
  }

}