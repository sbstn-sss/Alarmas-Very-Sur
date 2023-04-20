  <h1 align="center">🔥🚨Alarmas Very Sur🚨🔥</h1>
  <h1 align="center"><sub>🐮🐄Mucho sur🥛🥩 </sub></h1>

<p align="center">
  <img src="https://github.com/sbstn-sss/Alarmas_Very_Sur/blob/sub_main/very_sur_isaac.png?raw=true" width="256"  alt="Alarmas Very Sur"/>
</p>

# Nuestro Equipo⚛️👨‍💻:

-Nicolás Contreras:     202130555-6 

-Sebastián Osses  :     202130525-4 

-Felipe Zapata    :     202130515-7

-Yu Zhou          :     202130508-4



# ¿Quiénes somos?🌟🌟
<p align = "left">
Somos un equipo de estudiantes que actualmente cursa la asignatura Elo-329 "Diseño y programación orientados a objetos" de la USM. El grupo fue conformado para completar la primera entrega de la tarea. Su funcionamiento y detalle del programa se detallará a continuación.   
</p> 


# Nuestro Objetivo🚩:
<p align = "left">
Nuesto objetivo consiste en crear un programa que modele las funcionalidades de una alarma domiciliara, pues, intervienen distintos tipos de sensores distribuidos entre las puertas y ventanas. Ante una posible detección de un intruso en algún sensor, se activará una alarma. Además, contamos con un sistema de monitoreo que se encargará de enviar la información a una central y la guardará en su base de datos, por lo que nos brindará una retroalimentación del sistema.  
</p> 


# Ejecución y Compilación🤖💻:
El programa funciona en Java, por lo que es necesario una IDE que trabaje con dicho lenguaje. Recomendamos que fuese ejecutado en IntelliJ, ya que el código fue contruido con la versión más actual a la fecha "17.0.6+10-b829.5 amd64" ,y se ahorrará los pasos de compilación importando el proyecto y luego haciendo click en el botón `Run`. 


Al descargar el proyecto, notará que contiene distintas carpetas que van desde la stage uno a la stage cuatro. Cada una de estas carpetas contienen un archivo makefile que nos ayudará a compilar cada stage (Se explicará el funcionamiento de cada stage en "Funcionamiento").

Si desea compilar en aragorn o vía terminal, primero diríjase a la carpeta de alguna stage por comando. Como cada stage contiene su propio makefile, escriba el comando:

```
$make

```
así, el programa se compilará y como resultado entregará un ejecutable llamado "(por definir)".

si desea borrar el ejecutable, bastará con escribir:
```
$make clean

```
si desea ejecutar el programa tras compilar, bastará con escribir:
```
$make run

```


# Funcionamiento⚙️:
Dentro de los sensores que tenemos disponibles, son los PIR, que detecctan las personas dentro de su rango y los magnéticos que contiene un interruptor, en la cual detecta si las puertas y ventanas están abiertas o cerradas. Por lo general cada sensor magnético está atada a una puerta o una ventana.

La aplicación leerá desde el archivo `config.txt` de la carpeta `stage`. Esta posee las configuraciones de las cantidades de cada sensor, sus posiciones y rango de detección. Se especifica su formato a continuación:

```
<#Puertas><#Ventanas><#Pirs>
<x><y><dirección del ángulo><angulo de detección><área de detección>
...
siren.wav
```

No modifique la útltima línea, ya que especifíca el archivo del sonido de la alarma.
