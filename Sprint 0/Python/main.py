import datetime #Se importa la librería de fechas
import math #Se importa la libreria aritmética "math"

print(datetime.date.today())

foo = "Hello World"
print(foo)

print(type(6.5))

print(6+5)

print(6-5)

print(6*5)

print(6/5)

print(6**5) #Exponente (6 elevado a 5)

print(6%5) #Resto de la operacion 

print(6//5) #División entera(sin decimales)

print(math.cos(1)) #Se imprime el coseno de 1 usando la librería "math"

print(type("Prueba clase string"))

texto = """Con triples comillas 
podemos crear
string multilínea."""

print(texto)

print("Prueba " + "concatenación de strings")

print("123456789"[3:7]) #Se muestran los caracteres que estan en las posiciones entre los [], es decir se muestra a partir del 3er caracter no incluido hasta el 7mo incluido

print("aabbcc".replace("a","z")) #Se reemplazan los caracteres iguales al primero del parentesis por los caracteres del segundo

print("aa-bb-cc".split("-")) #Se separará con espacio donde se encuentre el caracter entre los parentesis

print("hola".upper()) #Se transforma todo el texto a mayúsculas

print("HOLA".lower()) #Se transforma todo el texto a minúsculas

a,b = 3,2 #Asignacion en una única linea de 2 variables

print(f"""El valor de a es {a} y el valor de b es {b}.
Su suma es {a + b}""") #La "f" antes de la cadena de texto nos permite inyectar variables para poder mostrarlas dentro de un string mediante corchetes "{}"

#Operadores booleanos

print(5==6) #Compara el primer valor con el segundo, al no ser igual devolverá "False"

print(6==6) #Compara el primer valor con el segundo, al ser igual devolverá "True"

print(6<5) #Compara mayor/menor

print(6>5)

print(6<=5) #Compara mayor/menor o igual

print(5<=5)

#Listas, se definen con "[]" separando sus elementos por ","

lista = [1,2,3]
print(lista)

#Pueden contener distintos tipos de elementos, no tienen por que ser todos del mismo tipo, su tamaño es dinámico y no es predefinido

lista1 = ["hola", 2, "adios" , 3]
print(lista1)

#Operadores de listas

print([1,2,3] + [4,5]) #Añade al final de la primera lista, la segunda

print([1,2,3][0]) #Consulta el elemento en la posición indicada entre "[]"

lista[0] = 0
print(lista) #En este caso al estar cambiando un valor de la lista, no podemos hacerlo dentro de print, ya que solo puede imprimir valores, no puede realizar asignaciones.

lista.append(4)
print(lista) #Añadimos un elemento al final de la lista

lista.pop(3)
print(lista) #Eliminamos el elemento de la posicion indicada

print(2 in lista) #Comprobamos si "2" esta dentro de lista, devuelve booleano, "True" en este caso, ya que si que está

#Diccionarios, otra estructura de datos que en lugar de almacenar los datos de forma ordenada, los almacenan en formato clave-valor.
#Los diccionarios se definen usando llaves, { }, los dos puntos para separar el par clave-valor y la coma para separar elementos.

diccionario = {"a" : 1, "b" : 2 , "c" : 3}
print(diccionario)

#Los tipos de los valores usados como clave pueden ser de cualquier tipo, siempre y cuando su tipo sea hashable. Esto quiere decir que deben tener un espacio de memoria asignado
#concreto. Por ejemplo, los datos de tipo bool, int y str son hashables, mientras que las listas no lo son.
#Operadores con diccionarios

print(diccionario["b"]) #Imprimirá el valor de la clave "b", 2 en este caso

diccionario["d"] = 4 #Se añade una nueva clave - valor al diccionario
print(diccionario)

diccionario.pop("d") #Se elimina la clave-valor con la clave intorducida
print(diccionario)

print(diccionario.keys()) #Se consultan solo las claves del diccionario, no sus valores

print(diccionario.values()) #Se consultan solo los valores del diccionario, no sus claves

#Sets, este tipo de estructuras de datos sirven para almacenar un conjunto de elementos, pero sin que tengan ninguna ordenación y sin que permitan los duplicados. 

#Crear un set
mi_set = {1, 2, 3, 4, 5}

#Agregar elementos a un set
mi_set.add(6)
mi_set.add(7)

#Imprimir el set
print(mi_set)

#Eliminar un elemento del set
mi_set.remove(3)

#Imprimir el set actualizado
print(mi_set)

#Tuplas, similares a las listas, salvo que las tuplas son inmutables (por ejemplo, no se pueden cambiar los elementos que la componen).

#Crear una tupla
mi_tupla = (1, 2, 3, 4, 5)

#Imprimir la tupla
print(mi_tupla)

#Acceder a elementos individuales de la tupla
print("El primer elemento de la tupla es:", mi_tupla[0])
print("El segundo elemento de la tupla es:", mi_tupla[1])

#Intentar modificar un elemento de la tupla (esto dará un error)
#mi_tupla[0] = 0  # Esto dará un error

#Concatenar tuplas
otra_tupla = (6, 7, 8)
nueva_tupla = mi_tupla + otra_tupla
print("Tupla concatenada:", nueva_tupla)

#Obtener la longitud de la tupla
print("Longitud de la tupla:", len(nueva_tupla))

#Control de flujo

#If - Else

comp = 0

if comp == 1:                   
    print(f"Comp = {comp}")     #Si la variable "comp" es igual a 1, entrará en la primera ejecución ya que se cumple la condición, si es igual a 2, saltará la primera y
elif comp == 2:                 #entrará en la segunda. Si no cumple ninguna condición, saltará ambas ejecuciones y no ejecutará nada.
    print(f"Comp = {comp}")


if comp == 1:                   
    print(f"Comp = {comp}")     
elif comp == 2:                
    print(f"Comp = {comp}")         #En este caso, añadimos el "else" final para, que si no entra en ninguna de las dos condiciones, ejecute el else.
else:
    print("Comp no es 1 ni 2.")


if (comp > 1 and comp <= 3):        #Se pueden añadir operadores lógicos a las condiciones, para hacer varias comprobaciones al mismo tiempo y ahorrar líneas de código.
    print("Comp es 2 o 3.")         #Esta técnica es útil cuando las condiciones son independientes entre sí y deseas evaluarlas de manera conjunta.


if type(comp) == int:                           #Se pueden anidar "if" uno dentro del otro.
    if comp <= 3:                               #Esta técnica es útil cuando cada condición depende del resultado de la anterior y el flujo del programa debe seguir 
        print("Comp es un int menor que 3.")    #diferentes caminos en función de varias condiciones.


#Bucles for: sirven para recorrer un conjunto de elementos de una estructura de datos iterable (por ejemplo, las estructuras de datos formadas por elementos, como las listas).
#De modo que por cada elemento se ejecutará una sentencia de código.
#A nivel de sintaxis se definen con el comando for, seguido de un iterable y dos puntos.

for i in [1,2,3]:
    print(f"Elemento: {i}")         #Por cada iteración se ejecuta el código incluido en el "for", en este caso se ejecutará 3 veces, la "i" va aumentando cada vez que termina
                                    #una ejecución, para pasar a la siguiente.
    
#Si queremos ejecutar un código "n" veces, sin necesitar recorrer un iterable, como uina lista o un array, usaremos la función "range".
    
for i in range(11):                 #Este código se ejecutará 11 veces, recordemos que empezamos siempre en 0, por eso devolvera del 0 al 10, al ser 11 veces.
    print(i)

#Recorrer dos iterables a la vez usando la función "zip", que toma dos o más iterables (como listas, tuplas, etc.) y los combina en una secuencia de tuplas. 
#Cada tupla resultante contiene elementos tomados de los iterables originales, en el mismo índice.

nombres = ["Juan","Rosa","Pedro"]
edades = [23,45,67]

for nombre, edad in zip(nombres,edades):
    print(f"Nombre: {nombre} - Edad: {edad}")

#Recorrer diccionaros con un for, al ser un formato de clave-valor, si los recorres con un bucle for solo se recorren las claves.
#En el caso de que quieras también recorrer los valores, es necesario usar las funciones "values" o "items".
    
for clave in diccionario:
    print(clave)                            #Solo se imprimirán las claves.

for valor in diccionario.values():
    print(valor)                            #Solo se imprimirán los valores.

for clave, valor in diccionario.items():
    print(clave, valor)                     #Se imprimirá tanto clave como valor.

#Bucles anidados
    
dicc = {"a" : [1,2,3], "b" : [11,22,33]}

for clave, valor in dicc.items():           #Recorremos el diccionario.
    for elemento in valor:                  #Para cada valor, recorremos la lista.
        print(clave,elemento)

#List comprehension y dict comprehension.
#Tipo de construcción sintáctica que permite definir listas y diccionarios respectivamente sin tener que crearlos explícitamente a partir de un bucle for.

#List comprehension
                                        #"range(5)": Esto genera una secuencia de números desde 0 hasta 4.
lcomp = [i for i in range(5)]           #Para cada valor "i" en la secuencia generada por "range(5)"", crea un elemento en la lista con ese valor "i".                                
print(lcomp)                            #Por lo tanto, "lcomp" contendrá la lista [0, 1, 2, 3, 4], ya que para cada valor "i" en "range(5)"", se agrega a la lista.

#Dicc Comprehension

dcomp = {i:i*10 for i in range(5)}      #Esto es una comprensión de diccionario. Significa que para cada valor "i" en la secuencia generada por "range(5)", 
print(dcomp)                            #Crea una entrada en el diccionario donde la clave es "i" y el valor es "i * 10".


#Switch: Es una estructura de control similar a una estructura if. Solo que, en lugar de evaluar condiciones lógicas, se ejecuta un código u otro en función del valor que tome 
#una variable concreta definida en el switch. En python no existe "Switch" como tal, se usa la alternativa con el comando "match" que funciona igual.

comando = "Hola, mundo!"

match comando:
    case "Hola, mundo!":
        print("Hola a ti también!")                    #El comando "match" recorrerá todos los "case" hasta encontrar la condición correcta y ejecutará el código de esa condición
    case "Adiós, mundo!":
        print("Nos vemos!")
    case other:
        print("No se encontraron coincidencias.")

#Bucles While
#Al igual que un bucle for, repite la ejecución de una secuencia de código. La diferencia es que el bucle for lo repite para cada elemento de un iterable,
#mientras que el comando while lo repite de manera indefinida siempre que se cumpla una condición lógica.

x = 3
while x > 0:
    x = x-1
    print("Valor de X:", x)

#Try - Except
#Similar a una estructura if (ejecuta un código u otro según una condición) solo que, en este caso, sirve para controlar errores en la ejecución del programa.

value = 0
try:
    print(10 / value)
except ZeroDivisionError:                                                   #Aqui indicamos el mensaje que mostrará la consola en caso de que se intente dividir entre 0.
    print("Value no puede ser 0 porque no se puede dividir entre 0.")
except TypeError:                                                           #Aquí indicamos el mensaje que se mostrará si intentamos dividir un numero entre un tipo de valor no permitido.
    print("Value tiene que ser un tipo int o float.")

#Funciones
    
def sum_num_naturales():
    suma = sum([i+1 for i in range(5)])         #"sum()" suma los elementos de la lista.
    print(suma)

sum_num_naturales()                             #Llamamos a la funcion para que se ejecute. Se muestra directamente porque tiene "print()" dentro de la propia función.


def sum_num_naturales_var():
    suma = sum([i+1 for i in range(5)])         
    return suma                                 #En vez de imprimir por consola, retornamos una variable con el resultado.

resultado = sum_num_naturales_var()              #La asignamos a una variable y la imprimimos.

print(resultado)

#Asignando variables de entrada

def sum_num_naturales_in(n_numeros):
    suma = sum([i+1 for i in range(n_numeros)])     #Con esta sintaxis podemos definir que rango de numero vamos a sumar.
    return suma

print(sum_num_naturales_in(100))                      #Podemos imprimirlo directamente sin necesidad de asignarlo a una variable.

#Importante tener en cuenta que las variables usadas dentro de una función, solo se pueden usar dentro de dicha funcion, son variables locales.