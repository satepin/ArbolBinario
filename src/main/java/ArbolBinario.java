import java.util.Scanner;

public class ArbolBinario {
  Scanner sc = new Scanner(System.in);
  Nodo raiz;

  public ArbolBinario() {
    raiz = null;
  }

  public void crearNodo() {
    int dato = pedirDato();
    if (raiz == null) {
      this.raiz = nuevoNodo(dato, null);
      this.raiz.padre = nuevoNodo(0, null);
      this.raiz.padre.izquierda = raiz;
      this.raiz.padre.derecha = raiz;
    } else {
      Nodo padre = raiz;
      insertar(padre, dato);
    }
  }

  void insertar(Nodo padre, int dato) {
    int cerrar = 0;
    while (cerrar != 1) {
      if (dato == padre.dato) {
        System.out.println("\nEl arbol ya posee este dato\n");
        cerrar = 1;
      } else {
        if (dato < padre.dato) {
          if (padre.izquierda == null) {
            padre.izquierda = nuevoNodo(dato, padre);
            cerrar = 1;
          } else {
            padre = padre.izquierda;
          }
        } else if (dato > padre.dato) {
          if (padre.derecha == null) {
            padre.derecha = nuevoNodo(dato, padre);
            cerrar = 1;
          } else {
            padre = padre.derecha;
          }
        }
      }
    }
  }

  public int pedirDato() {
    try{
      System.out.println("\nIngrese el valor del nodo: ");
      int dato = sc.nextInt();
      return dato;
    }catch(Exception invalido){ //llama recursivamente a la toma de dato hasta obtener un valor valido y lo retorna al pedido original. desconozco si esta es la forma mas apropiada de implementar la excepcion
      sc.next();
      System.out.println("Ingrese un valor numero entero");
      return pedirDato();
    }

  }

  public Nodo nuevoNodo(int dato, Nodo padre) {
    Nodo nuevo = new Nodo();
    nuevo.dato = dato;
    nuevo.izquierda = null;
    nuevo.derecha = null;
    nuevo.padre = padre;
    return nuevo;
  }

  public void imprimirPreOrden(Nodo raiz, int profundidad)// impresos al reves para facilitar representacion
  {
    if (raiz != null) {
      System.out.println(raiz.dato);
      identar(profundidad, 0, '├', '└');
      imprimirPreOrden(raiz.derecha, profundidad + 1);
      identar(profundidad, 1, '├', '└');
      imprimirPreOrden(raiz.izquierda, profundidad + 1);
    } else {
      System.out.println("null");
    }
  }

  public void imprimirInOrden(Nodo raiz, int profundidad) {
      if (raiz != null) {
          imprimirInOrden(raiz.derecha, profundidad + 1);
          identar(profundidad, 0, '⸗', '⸗');
          System.out.println(raiz.dato);
          imprimirInOrden(raiz.izquierda, profundidad + 1);
      } else {
          identar(profundidad, 1, '⸗', '⸗');
          System.out.println("null");
      }
  }

  public void imprimirPostOrden(Nodo raiz, int profundidad) {
    if (raiz != null) {
        imprimirPostOrden(raiz.derecha, profundidad + 1);
        imprimirPostOrden(raiz.izquierda, profundidad + 1);
        identar(profundidad, 0, '┌', '┌');
        System.out.println(raiz.dato);
    } else {
        identar(profundidad, 1, '┌', '┌');
        System.out.println("null");
    }
  }

  public void identar(int profundidad, int lado, char alta, char baja) {
    for (int i = 0; i < profundidad; i++) {
        System.out.print("    ");
    }
    if (profundidad > 0) {
        if (lado == 0) {
            System.out.print(alta + "── ");
        } else {
            System.out.print(baja + "── ");
        }
    }
  }

  public void busqueda(int dato, Nodo raiz) {
    if (raiz == null) {
      System.out.println("El arbol esta vacio");
    } else {
      Nodo aux = raiz;
      aux = buscar(dato, aux);
      if (aux == null) {
        System.out.println("El arbol no posee el dato");
      } else {
        imprimirPreOrden(aux, 1);
      }
    }
  }

  Nodo buscar(int dato, Nodo aux) {
    int cerrar = 0;
    while (cerrar != 1) {
      if (dato < aux.dato) {
        aux = aux.izquierda;
      } else if (dato > aux.dato) {
        aux = aux.derecha;
      } else if (aux.dato == dato) {
        cerrar = 1;
        return aux;
      } else {
        cerrar = 1;
        return null;
      }
    }
    return null;
  }

  public void eliminar(Nodo raiz, int dato) {
    Nodo aux = buscar(dato, raiz);
    Nodo izquierda = null;
    Nodo derecha = null;
    if (aux == null) {
      System.out.println("El arbol no posee el dato");
    } else {
      izquierda = aux.izquierda;
      derecha = aux.derecha;
        if (aux.dato > aux.padre.dato) {
          aux.padre.derecha = null;
        } else {
          aux.padre.izquierda = null;
        }
      }
      if (izquierda != null) {
        copia(izquierda, derecha);
        copia(raiz, izquierda);
      } else {
        if (derecha != null) {
          copia(raiz, derecha);
        }
      }
    }

  /* encontre problemas para implementar estos metodos al borrado de ciertos nodos
  public Nodo menorMax(Nodo aux) {
    Nodo max;
    if (aux.izquierda != null) {
      max = menorMax(aux.izquierda);
    } else {
      return aux;
    }
    return max;
  }

  public Nodo mayorMin(Nodo aux) {
    Nodo min;
    if (aux.derecha != null) {
      min = mayorMin(aux.derecha);
    } else {
      return aux;
    }
    return min;
  } */

  void copia(Nodo padre, Nodo copia) {
    if (copia == null) {
      return;
    }
    if (padre == null) {
      padre = nuevoNodo(copia.dato, null);
    }
    if (copia.dato < padre.dato) {
      if (padre.izquierda == null) {
        padre.izquierda = nuevoNodo(copia.dato, padre);
        padre = padre.izquierda;
        if (copia.izquierda != null) {
          copia(padre, copia.izquierda);
        }
        if (copia.derecha != null) {
          copia(padre, copia.derecha);
        }
      } else {
        padre = padre.izquierda;
        copia(padre, copia.izquierda);
      }
    } else if (copia.dato > padre.dato) {
      if (padre.derecha == null) {
        padre.derecha = nuevoNodo(copia.dato, padre);
        padre = padre.derecha;
        if (copia.izquierda != null) {
          copia(padre, copia.izquierda);
        }
        if (copia.derecha != null) {
          copia(padre, copia.derecha);
        }
      } else {
        padre = padre.derecha;
      }
    }
  }

}
