########################## USUARIOS ##########################
Los usuarios son los brindados en los datos estáticos. Así,
los usuarios son los siguientes:
    1.  nickname: lgarcia
        EMail: lgarcia85@gmail.com 
        clave: awdrg543.
        
    2.  nickname: EcoTech,
        EMail: info@EcoTech.com  
        clave: qsxcdw43. 

########################## KEYWORDS ##########################
El filtrado por Keyword funciona para las siguientes keywords:
    1. Tiempo Completo
    2. Computación

Este filtrado lo dejamos funcionando únicamente en los index.
En el resto de archivos html habría que añadir el href
correspondiente, y hacer los html correspondientes a cada
vista.


########################## EMPRESA ##########################
El filtrado por empresa funciona para EcoTech y GlobalHealth.
En el resto de entradas no existen ofertas laborales asociad-
as.

No es case-sensitive.Sólo funciona en el index del visitante.
Sin embargo, sólo basta con colocar los id buscar-input en l-
-os lugares correspondientes para que funcione.

############# COMENTARIO SOBRE COSTO DEL PAQUETE ############# 
El paquete básico figura con un costo de 240 en los datos pro-
-porcionados. Sin embargo, al calcularlo con la fórmula esto
resulta en:

                    (4000*1+500*1+150*1)*0,8

donde:
       .4000*1 refiere al precio del paquete 'Premiun' por la 
       cantidad que contiene este tipo de paquete, que es 1.
       .500*1 refiere al precio del paquete 'Destacada' por
        la cantidad que contiene de este tipo el paquete, que
        es 1.
       .150*1 refiere al precio del paquete 'Estándar' por la
        la cantidad que contiene de este tipo el paquete, que
        es 1.
       .*0,8: refiere a la aplicación del descuento.