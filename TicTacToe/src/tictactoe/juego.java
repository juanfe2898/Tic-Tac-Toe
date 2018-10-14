/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.util.ArrayList;

/**
 *
 * @author juanf
 */
public class juego {
    char cpu;
    char jugador;
    char m [][]=new char[][]{{'-','-','-'}, {'-','-','-'}, {'-','-','-'}};; 
   
    //constructor del juego que inicia indicando cual letra le toca a la cpu y al jugador//
    public juego(char cpu) {
        this.cpu = cpu;
        if(cpu=='X'){
            jugador='O';
        }else{
            jugador='X';
        }
    }

    public void setM(char[][] m) {
        this.m = m;
    }
    
    /*funcion que aclara quien gano*/
    private int ganoArbol(char [][]matArbol){
        int gano=0;
        /*filas*/
        for(int i=0; i<3;++i){
            if(matArbol[i][0]==cpu&&matArbol[i][1]==cpu&&matArbol[i][2]==cpu){
                gano= 1;
            }
            else if(matArbol[i][0]==jugador&&matArbol[i][1]==jugador&&matArbol[i][2]==jugador){
                gano= -1;
            }
        }
        /*Columnas*/
        for(int i=0; i<3;++i){
            if(matArbol[0][i]==cpu&&matArbol[1][i]==cpu&&matArbol[2][i]==cpu){
                gano= 1;
            }
            else if(matArbol[0][i]==jugador&&matArbol[1][i]==jugador&&matArbol[2][i]==jugador){
                gano= -1;
            }
        }
        /*Diagonales*/
        if(matArbol[0][0]==cpu&&matArbol[1][1]==cpu&&matArbol[2][2]==cpu){
                gano= 1;
            }
        else if(matArbol[0][0]==jugador&&matArbol[1][1]==jugador&&matArbol[2][2]==jugador){
                gano= -1;
            }
        if(matArbol[0][2]==cpu&&matArbol[1][1]==cpu&&matArbol[2][0]==cpu){
                gano= 1;
            }
        else if(matArbol[0][2]==jugador&&matArbol[1][1]==jugador&&matArbol[2][0]==jugador){
                gano= -1;
            }
        return gano;
    }
    /* grafica el siguiente movimiento*/
    public char[][] posibilidad(){//va a retornar un entero 
        char[][] mov = null;
        int slots=9-nivel(m);
        int[ ]jugada = new int[slots];
        int contador=0;
        /*envia a buscar cual es el mejor camino y guarda los datos para compararlo*/
        for(int i=0;i<3;++i){
            for(int j=0;j<3;++j){
                if(m[i][j]!='X'&&m[i][j]!='O'){
                    m[i][j]=cpu;
                    if(ganoArbol(m)==1){
                        return m;
                    }else{
                        jugada[contador]=recorrido(m);
                        contador++;
                    }
                    m[i][j]='-';
                }
            }
        }  
        contador=0;
        /*busca donde esta el mayor y lo guarda*/
        for(int i=0;i<3;++i){
            for(int j=0;j<3;++j){
                if(m[i][j]!='X'&&m[i][j]!='O'){
                    if(contador==mejorCamino(jugada, slots)){
                        m[i][j]=cpu;
                        mov=m;
                        m[i][j]='-';
                    }
                    contador++;
                }
            }
        }
        return mov;
    }
    /*indica los hijos */
    private int recorrido(char [][]posi){
        if(nivel(posi)==8){
            return 0;
        }
        int resul=0;
        for(int i=0;i<3;++i){
            for(int j=0;j<3;++j){
                if(posi[i][j]!='X'&&posi[i][j]!='O'){
                    if(nivel(posi)%2==0){
                        posi[i][j]='O';
                        imprimir(posi);
                        System.out.println(ganoArbol(m));
                        if(ganoArbol(posi)==-1){
                           resul= resul-1;
                        }else if(ganoArbol(posi)==-1){
                            resul= resul+1;
                        }else{
                            resul=recorrido(posi)+resul;
                        }
                        posi[i][j]='-';
                    }else{
                        posi[i][j]='X';
                        imprimir(posi);
                        System.out.println(ganoArbol(m));
                        if(ganoArbol(posi)==-1){
                           resul= resul-1;
                        }else if(ganoArbol(posi)==-1){
                            resul= resul+1;
                        }else{
                            resul=recorrido(posi)+resul;
                        }
                        posi[i][j]='-';
                    }
                }
            }
        } 
        return resul;
    }
    /*calcula el nivel actual del juego*/
    public int nivel(char[][]niv){
       int n=0;
       for(int i=0;i<3;++i){
            for(int j=0;j<3;++j){
                if(niv[i][j]=='X'||niv[i][j]=='O'){
                    n++;
                }
            }
        }
       return n;
   }
    /*selecciona la posicion del mejor camino*/
    private int mejorCamino(int[] k, int size){
       int pos=0;
       int mayor=0;
       for(int i=0;i<size;++i){
           if(k[i]>mayor){
               mayor=k[i];
               pos=i;
           }
       }
       return pos;
   }
    private void imprimir(char[][]mostrar){
        String a = "";
        int  j=0,k=0;
             for(j=0;j<3;++j){
                for(k=0;k<3;++k){
                    char y=mostrar[j][k];
                    a+=y;
                }
                a+="\n";
            }
       a+="\n";
        System.out.println(a);
   }
}
