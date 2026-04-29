# ACME technicaltest

Ejercicio práctico
API REST para el ciclo de abastecimiento de la tienda carrera 70 la cual debe enviar la información de los pedidos a través de esta API con mensajería xml

>  #spring #java #maven
---
## Contenido
- [Descripción](#descripción)
- [Funcionalidades](#funcionalidades)
- [Construido con](#construido-con)
- [Desarrollo](#desarrollo)
- [Endpoints](#endpoints)
---
## Descripción

API REST para el ciclo de abastecimiento de la tienda carrera 70 la cual debe enviar la información de los pedidos a través de esta API con mensajería xml

## Funcionalidades

Las siguientes son las funcionalidades de este repositorio:

##### REQUEST: 
```
{
	"enviarPedido": {
		"numPedido": "75630275",
		"cantidadPedido": "1",
		"codigoEAN": "00110000765191002104587",
		"nombreProducto": "Armario INVAL",
		"numDocumento": "1113987400",
		"direccion": "CR 72B 45 12 APT 301"
	}
}

```

##### RESPONSE: 
```
{
	"dataHeader": {
		"codigoRespuesta": 200
	},
	"data": {
		"enviarPedidoRespuesta": {
			"codigoEnvio": "80375472",
			"estado": "Entregado exitosamente al cliente"
		}
	}
}
```

- [registerorder]

---

## Construido con

Código implementado con Java 17, Spring boot 4.0.5 y Maven 3.9.14

## Desarrollo
1. Clone este repositorio.
2. Construya la imagen Docker (docker build -t seti-technicaltest-acme:1.0 .)
3. Levante el contenedor (docker run -d -p 8080:8080 --name acme-container seti-technicaltest-acme:1.0)

## Endpoints

- Registrar pedidos:  /seti-acme-technical-test/api/v1/registerorder
