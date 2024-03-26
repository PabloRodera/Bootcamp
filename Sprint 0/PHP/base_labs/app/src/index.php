<!DOCTYPE html>
<html>
<head>
    <title>Formulario Lab0</title>
</head>
<body> 
    <h2>Introduce tus datos</h2>
    <form method="POST" action="index.php" >
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" required><br><br>

        <label for="edad">Edad:</label>
        <input type="number" id="edad" name="edad" required><br><br>

        <label for="descripcion">Descripción:</label>
        <textarea id="descripcion" name="descripcion" required></textarea><br><br>

        <input type="submit" value="Enviar"><br><br>
    </form>
</body>
</html>
<?php
    if ($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST['nombre']) && isset($_POST['edad']) && isset($_POST['descripcion'])) 
    {
        $nombre = trim(isset($_POST['nombre']) ? $_POST['nombre'] : '');
        $edad = trim(isset($_POST['edad']) ? $_POST['edad'] : '');
        $descripcion = trim(isset($_POST['descripcion']) ? $_POST['descripcion'] : '');

        if (!empty($nombre) && is_numeric($edad) && !empty($descripcion)) {
            file_put_contents('nombre.txt', "Nombre: $nombre, Descripción: $descripcion\n", FILE_APPEND);

            file_put_contents('edad.txt', "La edad de $nombre es: $edad\n", FILE_APPEND);

            function calcularMediaEdad() {
                $edades = file('edad.txt', FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);
                $totalEdades = 0;
                $cantidadUsuarios = 0;
                foreach ($edades as $linea) {
                    preg_match('/(\d+)/', $linea, $matches);
                    if (!empty($matches)) {
                        $totalEdades += intval($matches[0]);
                        $cantidadUsuarios++;
                    }
                }
            
                $media = $cantidadUsuarios > 0 ? $totalEdades / $cantidadUsuarios : 0;
                return intval($media);
            }

            $mediaEdad = calcularMediaEdad();
            echo "La media de edad de tus usuarios es: $mediaEdad";
        } 
        else {
            echo "Los datos introducidos no son válidos.";
        }
    }
?>
