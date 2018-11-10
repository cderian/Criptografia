#! /usr/bin/env python
#-*- coding: utf-8 -*-
'''
 * Title       : Test de Primalidad de Solovay Strassen
 * Language    : Python
 * By          : cderian
 * Description : Dado un número entero, determina si es primo o compuesto.
'''
import random

'''
 * Símbolo de Jacobi.
 * Se define a partir del símbolo de Legendre.
 * Dados dos enteros a,n con n impar, según el Teorema Fundamental de la Aritmética
 * podemos descomponerlo como producto de primos n = p1^(a1) ... pt^(at)
 *
'''
def jacobi(a, n):
	j = 1								# j es el símbolo Jacobi

	while(a != 0):
		while(a%2 == 0):				# Loop hasta encontrar un número par
			j = j*pow(-1, (n*n-1)/8)	# Si n=3(mod 8) OR n=5(mod 8) OR (-1)^((n^2-1)/2)==-1 entonces j = -j
			a = a/2

		if not ((a-3)%4 or (n-3)%4):	# Si a=3(mod 4) AND n=3(mod 4)
										#[Así que, (a-3)%4 AND (n-3)%4 = 0]
										#OR (-1)^((n 1)/2)==-1 entonces j = -j
			j = -j

		a,n = n,a 						#swap(a,n)
		a = a%n

	return j

'''
 * Test de Primalidad de Solovay Strassen.
 *
 * El algoritmo se compone de dos partes:
 *	1. Encontrar el valor de la fórmula del criterio de Euler.
 *	2. Encontrar el símbolo de Jacobi para un valor dado.
 *	3. Comparar los valores obtenidos en 1 y en 2:
 *		Si ambos son iguales, entonces el número es Primo
 *		Si ambos son diferentes, entonces el número es Compuesto.
'''
def solovay_strassen(n, k):
	
	if (n==2 or n==3):
		return True

	if not (n & 1):
		return False

	for i in xrange(k):
		a = random.randrange(2, n-1)	# Escoger aleatoriamente un número entre [1-(n-1)]
		x = jacobi(a,n)                 # Encontrar el número Jacobi de n
		y = pow(a, (n-1)/2, n)			# Calcular el símbolo de Legendre a partir de la fórmula del criterio de Euler

		if (y!=1 and y!=0):
			y = -1

		if (x==0 or y!=x):				# Si Euler y Jacobi no es el mismo (y! = x), entonces el número no es primo
			return False

	return True