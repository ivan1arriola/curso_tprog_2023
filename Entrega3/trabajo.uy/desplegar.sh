#!/bin/bash
#### Variables ####



# Obtén el directorio de inicio del usuario
HOME_DIR="$HOME"

# Construye la ruta completa al archivo de propiedades
PROPERTIES_FILE="$HOME_DIR/.trabajoUy/.properties"


# Script wsimport.sh, se obtiene del properties
WSIMPORT_SCRIPT=$(grep "^wsimport.script" $PROPERTIES_FILE | cut -d'=' -f2 | tr -d '[:space:]')

# Leer el IP y el puerto del archivo properties
SERVIDOR_IP=$(grep "^servidor.ip" $PROPERTIES_FILE | cut -d'=' -f2 | tr -d '[:space:]')
SERVIDOR_PUERTO=$(grep "^servidor.puerto" $PROPERTIES_FILE | cut -d'=' -f2 | tr -d '[:space:]')

# Construir el URL del WSDL
WSDL_URL="http://${SERVIDOR_IP}:${SERVIDOR_PUERTO}/webservices?wsdl"

# Ruta del directorio del script
SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

# Ruta absoluta del directorio del proyecto
PROYECTO_SERVIDOR_CENTRAL_DIR="$SCRIPT_DIR/ServidorCentral"
PROYECTO_SERVIDOR_WEB_DIR="$SCRIPT_DIR/ServidorWeb"
PROYECTO_SERVIDOR_MOVIL_DIR="$SCRIPT_DIR/ServidorMovil"

# Ruta absoluta del directorio de salida
OUT_DIR="$SCRIPT_DIR/out"

# Nombre del archivo JAR
SERVIDOR_CENTRAL_JAR_NAME="ServidorCentral-1.0.0-jar-with-dependencies.jar"
SERVIDOR_CENTRAL_JAR_NAME_FINAL="ServidorCentral.jar"

# Nombre del archivo WAR
SERVIDOR_WEB_WAR_NAME="ServidorWeb-1.0.0.war"
SERVIDOR_WEB_WAR_NAME_FINAL="ServidorWeb.war"

# Nombre del archivo WAR
SERVIDOR_MOVIL_WAR_NAME="ServidorMovil-1.0.0.war"
SERVIDOR_MOVIL_WAR_NAME_FINAL="ServidorMovil.war"

# Ruta a tomcat, se lee de properties
TOMCAT_DIR=$(grep "^tomcat.dir" $PROPERTIES_FILE | cut -d'=' -f2 | tr -d '[:space:]')



# Funcion para ejecutar Servidor Central
function ejecutarServidorCentral() {
    # Puedes agregar aquí los comandos que deseas ejecutar al seleccionar la opción 1
    echo "Desplegando..."
    cd $OUT_DIR
    java -jar $OUT_DIR/$SERVIDOR_CENTRAL_JAR_NAME_FINAL &
    echo "Saliendo del servidor central..."
}


# Función para compilarServidorCentral
function compilarServidorCentral() {
    # Puedes agregar aquí los comandos que deseas ejecutar al seleccionar la opción 1
    echo "Compilando Servidor Central..."
    cd $PROYECTO_SERVIDOR_CENTRAL_DIR
    mvn clean package
    mv $PROYECTO_SERVIDOR_CENTRAL_DIR/target/$SERVIDOR_CENTRAL_JAR_NAME $OUT_DIR/$SERVIDOR_CENTRAL_JAR_NAME_FINAL
    echo "Servidor Central listo en carpeta out"
}

# Función para actualizarWSDL
function actualizarWSDL() {
    # Puedes agregar aquí los comandos que deseas ejecutar al seleccionar la opción 2
    echo "Actualizando WSDL..."
    cd $PROYECTO_SERVIDOR_WEB_DIR/src/main/java
    rm -rf logica
    $WSIMPORT_SCRIPT -keep $WSDL_URL
    cd $PROYECTO_SERVIDOR_MOVIL_DIR/src/main/java
    rm -rf logica
    $WSIMPORT_SCRIPT -keep $WSDL_URL
    echo "WSDL actualizado..."
}


# Función para compilarServidorWebWAR
function compilarServidorWebWAR() {
    # Puedes agregar aquí los comandos que deseas ejecutar al seleccionar la opción 2
    echo "Desplegando..."

    echo "Compilando servidor web..."
    cd $PROYECTO_SERVIDOR_WEB_DIR
    mvn clean package
    mv $PROYECTO_SERVIDOR_WEB_DIR/target/$SERVIDOR_WEB_WAR_NAME $TOMCAT_DIR/webapps/$SERVIDOR_WEB_WAR_NAME_FINAL

    echo "Servidor web compilado..."
    
}

# Función para compilarServidorMovilWAR
function compilarServidorMovilWAR() {
    # Puedes agregar aquí los comandos que deseas ejecutar al seleccionar la opción 2
    echo "Desplegando..."
    
    echo "Compilando servidor web..."
    cd $PROYECTO_SERVIDOR_MOVIL_DIR
    mvn clean package
    mv $PROYECTO_SERVIDOR_MOVIL_DIR/target/$SERVIDOR_MOVIL_WAR_NAME $TOMCAT_DIR/webapps/$SERVIDOR_MOVIL_WAR_NAME_FINAL

    echo "Servidor web compilado..."
    
}

function ejecutarTomcat() {
    # Puedes agregar aquí los comandos que deseas ejecutar al seleccionar la opción 2
    echo "Ejecutando tomcat..."
    cd $TOMCAT_DIR/bin
    # ejecutar tomcat en otra terminal
    ./startup.sh
    echo "Tomcat ejecutado..."
    
}



function cerrarApagar(){
    echo "Apagando..."
    # cerrar tomcat
    cd $TOMCAT_DIR/bin
    ./shutdown.sh
    # cerrar servidor central
    pkill java
    echo "Apagado..."
    exit
}

function reinciarTomcat(){
    echo "Reiniciando tomcat..."
    cd $TOMCAT_DIR/bin
    # ejecutar tomcat en otra terminal
    ./shutdown.sh

    # redeploy war
    rm -rf $TOMCAT_DIR/webapps/$SERVIDOR_WEB_WAR_NAME_FINAL
    rm -rf $TOMCAT_DIR/webapps/$SERVIDOR_MOVIL_WAR_NAME_FINAL

    actualizarWSDL
    compilarServidorWebWAR
    compilarServidorMovilWAR

    cd $TOMCAT_DIR/bin
    ./startup.sh
    echo "Tomcat reiniciado..."
    
}


# Menu de opciones
while true; do
    # crea la carpeta out si no existe
    mkdir -p $OUT_DIR
    echo "Seleccione una opción:"
    echo "1. Compilar Servidor Web JAR"
    echo "2. Ejectuar Servidor Central"
    echo "3. Actualizar WSDL (Requiere Servidor Central desplegado)"
    echo "4. Compilar Servidor Web WAR"
    echo "5. Compilar Servidor Movil WAR"
    echo "6. Ejecutar Tomcat"
    echo "7. Reiniciar Tomcat"
    echo "8. Apagar"
    echo "0. Salir"
    read -p "Opción: " opcion
    case $opcion in
        1 ) compilarServidorCentral;;
        2 ) ejecutarServidorCentral;;
        3 ) actualizarWSDL;;
        4 ) compilarServidorWebWAR;;
        5 ) compilarServidorMovilWAR;;
        6 ) ejecutarTomcat;;
        7 ) reinciarTomcat;;
        8 ) cerrarApagar;;
        0 ) exit;;
        * ) echo "Seleccione una opción válida.";;
    esac
done
