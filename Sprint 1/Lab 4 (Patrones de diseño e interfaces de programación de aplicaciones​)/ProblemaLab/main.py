from fastapi import FastAPI, HTTPException

app = FastAPI()

# Este es el Endpoint raíz que devuelve el mensaje de bienvenida
@app.get("/")
def read_root():
    return{"message" : "¡Bienvenido a la API de Pablo Rodera para el laboratorio 4 de Qualentum!"}

# Este es el Endpoint para la suma
@app.get("/suma/{num1}/{num2}")
def suma(num1: int, num2: int):
    return{"Resultado de la suma" : num1 + num2}

# Este es el Endpoint para la resta
@app.get("/resta/{num1}/{num2}")
def resta(num1: int, num2: int):
    return{"Resultado de la resta" : num1 - num2}

# Este es el Endpoint para la multiplicación
@app.get("/multiplicacion/{num1}/{num2}")
def multiplicacion(num1: int, num2: int):
    return{"Resultado de la multiplicación" : num1 * num2}

# Este es el Endpoint para la división
@app.get("/division/{num1}/{num2}")
def division(num1: int, num2: int):
    if num2 == 0:
        raise HTTPException(status_code=400, detail="No se puede dividir entre cero")
    return{"Resultado de la división" : num1 / num2}

# Explicación breve del codigo:

# Se importa la clase FastAPI del módulo fastapi.
# Se crea una instancia de FastAPI llamada app.
# Se crea un Endpoint raíz para dar la bienvenida cuando se accede a la FastAPI.
# Se crean los Endpoints correspondientes para cada operación aritmética con sus dos respectivos parámetros de entrada.
# Se realiza la operación correspondiente (+, -, *, /) y se devuelve el resultado en un diccionario JSON.
# Se valida el caso en que se intente dividir por cero en el endpoint de división.
# Se utiliza HTTPException para devolver un código de estado 400 (Bad Request) con un mensaje de error explicativo.