#!/bin/bash
#### Variables ####

# Script wsimport.sh
WSIMPORT_SCRIPT="/home/ivan1arriola/Descargas/jaxws-ri-4.0.0/jaxws-ri/bin/wsimport.sh"

# URL del WSDL
WSDL_URL="http://localhost:9128/webservices?wsdl"

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

# Ruta a tomcat
TOMCAT_DIR="/home/ivan1arriola/eclipse-workspace/apache-tomcat-10.1.8"




# Función para desplegarServidorCentral
function desplegarServidorCentral() {
    # Puedes agregar aquí los comandos que deseas ejecutar al seleccionar la opción 1
    echo "Desplegando..."
    cd $PROYECTO_SERVIDOR_CENTRAL_DIR
    mvn clean package
    mv $PROYECTO_SERVIDOR_CENTRAL_DIR/target/$SERVIDOR_CENTRAL_JAR_NAME $OUT_DIR/$SERVIDOR_CENTRAL_JAR_NAME_FINAL
    cd $OUT_DIR
    # Ejecutar usando gnomo-terminal
    gnome-terminal -e "java -jar $OUT_DIR/$SERVIDOR_CENTRAL_JAR_NAME_FINAL"
    echo "Saliendo del servidor central..."
}

# Función para compilarServidorWebWAR
function compilarServidorWebWAR() {
    # Puedes agregar aquí los comandos que deseas ejecutar al seleccionar la opción 2
    echo "Desplegando..."
    echo "Actualizando web services..."
    cd $PROYECTO_SERVIDOR_WEB_DIR/src/main/java
    rm -rf logica
    $WSIMPORT_SCRIPT -keep $WSDL_URL
    echo "Web services actualizados..."
    echo "Compilando servidor web..."
    cd $PROYECTO_SERVIDOR_WEB_DIR
    mvn clean package
    mv $PROYECTO_SERVIDOR_WEB_DIR/target/$SERVIDOR_WEB_WAR_NAME $TOMCAT_DIR/webapps/$SERVIDOR_WEB_WAR_NAME_FINAL
    cd $S
    echo "Servidor web compilado..."
    exit
}

# Función para compilarServidorMovilWAR
function compilarServidorMovilWAR() {
    # Puedes agregar aquí los comandos que deseas ejecutar al seleccionar la opción 2
    echo "Desplegando..."
    echo "Actualizando web services..."
    cd $PROYECTO_SERVIDOR_MOVIL_DIR/src/main/java
    rm -rf logica
    $WSIMPORT_SCRIPT -keep $WSDL_URL
    echo "Web services actualizados..."
    echo "Compilando servidor movil..."
    cd $PROYECTO_SERVIDOR_MOVIL_DIR
    mvn clean package
    cd $OUT_DIR
    mv $PROYECTO_SERVIDOR_MOVIL_DIR/target/$SERVIDOR_MOVIL_WAR_NAME $OUT_DIR/$SERVIDOR_MOVIL_WAR_NAME_FINAL
    echo "Servidor movil compilado..."
    exit
}





# Menu de opciones
while true; do
    echo "Seleccione una opción:"
    echo "1. Desplegar Servidor Central"
    echo "2. Compilar Servidor Web WAR"
    echo "3. Compilar Servidor Movil WAR"
    echo "4. Salir"
    read -p "Opción: " opcion
    case $opcion in
        [1]* ) desplegarServidorCentral;;
        [2]* ) compilarServidorWebWAR;;
        [3]* ) compilarServidorMovilWAR;;
        [4]* ) exit;;
        * ) echo "Seleccione una opción válida.";;
    esac
done
