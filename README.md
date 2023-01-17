Esta aplicación cuenta con dos componentes:

	- builder
	- api-builder

Para poder desplegar estos componentes es necesario tener docker instalado y un cluster de kubernetes. En el cluster de kubernetes se requiere tener instalado ingress-nginx.

En la carpeta de cada componente hay un directorio "k8s" con los ficheros necesarios para desplegar en kubernetes. Además hay un fichero kubescript.sh para unificar la aplicación de todos los ficheros

- Componente builder
Este componente levanta un jenkins en kubernetes con un job pre-configurado. Para levantarlo hay que seguir los siguientes pasos:
	1. Directorio /builder. 
		Primero hay que montar la imagen a partir del Dockerfile.

		docker build -t builder:1.0.0 .

	2. Directorio /builder/k8s
		*Ejecutar el script kubescript.sh, o bien aplicar los ficheros yamls con los comandos del script en el orden en que aparecen.

	*NOTA: En el fichero volume.yaml. Es necesario sustituir el valor "minikube" por un nodo del cluster donde se va a desplegar. Se está haciendo uso de un local storage que requiere de un selector de nodos.

	Acceso:
		En cuanto el despliegue finalice, se podrá acceder a jenkins a través de esta url: http://builder.localhost.com
		Aparecerá un job que lanza un pipeline en el que solo aparecen los stages vacíos.
		Se puede hacer login con el usuario admin:admin

- Componente api-builder
Este componente está formado por una aplicación spring-boot y una base de datos mysql. Este componente expone un servicio para realizar el build de los beans que se le pasen. Registra el bean en la base de datos y lanza una petición rest a la api de jenkins para ejecutar el job que hay preconfigurado. Para levantar hay que seguir los siguientes pasos:
	1. Directorio /api-builder
		Primero hay que montar la imagen a partir del Dockerfile

		docker build -t api-builder:1.0.0 .

	2. Directorio /api-builder/k8s
		Ejecutar el script kubescript.sh, o bien aplicar los ficheros yamls con los comandos del script en el orden en que aparecen.

	Acceso:
		En cuanto el despliegue finalice, se podrá acceder al swagger del servicio a través de esta url: http://api-builder.localhost.com/swagger-ui/index.html

		Para probarlo solo hay que añadir un body válido y ejecutar.

Para ambos componentes, es posible que sea necesario añadir los hostnames builder.localhost.com y api-builder.localhost.com al fichero hosts de la máquina donde lancéis las pruebas, asociándolo con la ip de acceso al cluster.