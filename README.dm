****** README: magneto-mutant-app *******

Api desarrollada con Spring Boot, Java8, Maven y con base de datos PosgreSQL

Repositorio de GitHub:
	https://github.com/adrianlemma/magneto-mutant-app

API_URL:
	*URL: https://magneto-mutant-app.herokuapp.com/
	ruta raiz de la api, se instaó en Heroku (servidores de AWS) por comodidad/compatibilidad
	
	*STATS: https://magneto-mutant-app.herokuapp.com/stats
	Se ejecuta un GET y devuelve un objeto indicando las cantidades de registros de cada tipo y un ratio
	Ejemplo: {"count_mutant_dna":11,"count_human_dna":10,"ratio":1.1}
	
	*MUTANT: https://magneto-mutant-app.herokuapp.com/mutant
	Recibe una matriz (array de strings) de ADN, lo clasifica ("mutnt"/"no mutant") y se almacena en la base de datos si no esta duplicado
	Ejemplo: {“dna”:["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]}
	
*Datos de conexion a la base de datos
**	DATOS BD
**	URL = postgres://esfpjnlaleitad:f09cc384a3cd3c463f50c3d304a0730d1b813aa59f0982ed4aab7d9af9b5b3ab@ec2-35-171-90-188.compute-1.amazonaws.com:5432/dfe55k63kmhveo
**	USER =  esfpjnlaleitad
**	PASS = f09cc384a3cd3c463f50c3d304a0730d1b813aa59f0982ed4aab7d9af9b5b3ab
**
**	HOST = ec2-35-171-90-188.compute-1.amazonaws.com
**	DATABASE = dfe55k63kmhveo
**	PORT = 5432

--------------------------------------------------------------------------------------------------------------------

Validacones:
	Para el metodo POST, se valida que el largo de los strings recibidos sean iguales entre se
	Se valida, también que cada string tenga tantos caracteres como string en el array (matriz cuadrada)
	Se validan los caracteres, solo son validos: a, c, g, t, A, C, G, T y se almacenan en mayuscula

--------------------------------------------------------------------------------------------------------------------

Test:
	Se realizaron test unitarios con JUnit y para simular respuestas de base de datos, Mockito
	Test Coverage > 90%

--------------------------------------------------------------------------------------------------------------------

Nota:
	Algunas validaciones se realizan en la propia api, si bien sería conveniente que se realicen sobre la BD
	(registros duplicados y calculo del Ratio) 