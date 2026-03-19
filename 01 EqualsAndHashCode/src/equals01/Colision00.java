package equals01;

public class Colision00 {
public static void main(String[] args) {
	String s1 = "Aa";
	String s2 = "BB";

	System.out.println(s1.hashCode()); // 2112
	System.out.println(s2.hashCode()); // 2112 (¡Colisión!)
	System.out.println(s1.hashCode()==s2.hashCode());
	System.out.println(s1.equals(s2)); // false
}
/**
 * 1. La fórmula de String.hashCode()

Java utiliza un algoritmo polinomial. Para un String, el valor se calcula como:
s[0]⋅31n−1+s[1]⋅31n−2+⋯+s[n−1]

Donde:

    s[i] es el valor ASCII/Unicode del carácter.

    n es la longitud de la cadena.

    31 es un número primo elegido porque ofrece una buena distribución y permite optimizaciones 
    a nivel de procesador (como desplazamientos de bits).

2. El cálculo real de "Aa" y "BB"

Miremos los valores Unicode:

    'A' = 65, 'a' = 97

    'B' = 66, 'B' = 66

Para "Aa":
65⋅311+97=2015+97=2112

Para "BB":
66⋅311+66=2046+66=2112

Como ves, aunque los caracteres son distintos, la combinación matemática da exactamente 
el mismo resultado.
3. ¿Por qué ocurre esto? (Principio del palomar)

Matemáticamente, es imposible evitar las colisiones por una razón de espacio:

    Un String puede tener una longitud infinita (combinaciones infinitas).

    El hashCode es un int de 32 bits, lo que significa que solo puede representar 232 
    (unos 4.200 millones) de valores diferentes.

Como hay más strings posibles que números enteros disponibles, obligatoriamente habrá 
strings diferentes que compartan el mismo código.
4. ¿Cómo lo gestiona la colección?

Cuando insertas ambos en un HashMap:

    Calcula el hash de "Aa" (2112) y lo guarda en la cubeta 2112.

    Calcula el hash de "BB" (2112). Como es la misma cubeta, Java dice: "Un momento, ya hay algo aquí".

    Llama a equals(): "Aa".equals("BB") devuelve false.

    Java decide que son distintos y los guarda ambos en la misma cubeta, usualmente 
    formando una pequeña lista enlazada o un árbol.

Conclusión: El hashCode no es un identificador único (como un DNI), es más bien un filtro de proximidad.
 Su trabajo no es ser perfecto, sino ser rápido para descartar la gran mayoría de los casos donde los
  objetos son claramente distintos.

¿Te genera curiosidad saber por qué se eligió precisamente el número 31 para esta fórmula y no un 10 
o un 100?
 */
}
