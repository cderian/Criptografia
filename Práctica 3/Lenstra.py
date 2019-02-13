#! /usr/bin/env python
#-*- coding: utf-8 -*-
import Punto as punto
from util import mcd
from util import modn
from util import inverso_modular

def son_iguales (p, q):
	'''
	 * Función que determina si dos puntos son iguales.
	'''
	if ( (p.getX() == q.getX()) and (p.getY() == q.getY()) ):
		return True;
	return False;

def suma_puntos_diferentes (p, q, num):
	'''
	 * Suma puntos de la curva eliptica y^2 = x^3 + ax + B con la operacion intuitiva
	 * en el caso que p != q , tiene 3 subcasos 
	 * a) si p = 0
	 * b) si q = 0
	 * c) si p = -q
	'''
	r = punto.Punto()

	if ((p.getX() == 0) and (p.getY() == 0)): return q

	if ((q.getX() == 0) and (q.getY() == 0)): return p;

	if ((p.getX() == q.getX() ) & (q.getY() == (-1 * p.getY() ))):
		r.setX(0);
		r.setY(0);
		return r;

	r.setLambda( modn ((q.getY() - p.getX() ) * inverso_modular (q.getX() - p.getX(), num), num))
	r.setNum ( modn (q.getY() - p.getY(), num ) )
	r.setDen ( modn (q.getX() - p.getX(), num ) )
	r.setX( modn (modn (r.getLambda() * r.getLambda(), num ) + modn ((-1 * p.getX() ), num) + modn ((-1 * q.getX() ), num), num) )
	r.setY( modn ((r.getLambda() * (p.getX() - r.getX() )) - p.getY(), num) )
	return r

def suma_puntos_iguales (p, num):
	'''
	 * Función que calcula R = P+P = 2P en la curva y^2 = x^3 + ax + B
	'''
	r = punto.Punto()
	r.setLambda( modn (((3 * (p.getX() * p.getX() )) + a) * inverso_modular (p.getY() * 2, num), num) )
	r.setNum( modn ((3 * (p.getX() * p.getX() ) + a), num) )
	r.setDen( modn (p.getY() * 2, num) )
	r.setX( modn (modn ((r.getLambda() * r.getLambda()), num) - modn (2 * p.getX(), num), num))
	r.setY( modn ((r.getLambda() * (p.getX() - r.getX() )) - p.getY(), num))
	return r

def suma_puntos (p, q, num):
	'''
	 * Función que calcula P+Q = R en y^2 + x^3 + ax + B
	'''
	if (son_iguales (p, q)):
		return suma_puntos_iguales (p, num)

	return suma_puntos_diferentes (p, q, num)

def mult_escalar (k, p, num):
	'''
	 * Función que calcula R = P+P+...+P (k veces), es decir, R = kP en y^2 = x^3 + ax + B
	'''
	r = punto.Punto()
	t = punto.Punto()
	r.setX(0)
	r.setY(0)

	for i in xrange(k):
		t = suma_puntos (p, r, num);
		r = t;

	return r

def lenstra(numero, k, a):
	'''
	 * Factoriza un número por el método de Lenstra en k iteraciones.
	'''
	inicial = punto.Punto()
	t = punto.Punto()
	inicial.setX(1)
	inicial.setY(1)

	i = 2
	for i in range(i, k):
		t = mult_escalar(i, inicial, numero)
		print "t = ",i,"*(1,1)= (",t.getX(),",",t.getY(),"), y^2 = x^3 + ",a,"x - ",a," mod ",numero

		# Calculamos el mcd del denominador de la pendiente y el numero
		mcd1 = mcd(t.getDen(), numero)
		print "Con ",i,"*(1,1) tenemos mcd(r = ",t.getDen(),", n = ",numero,") = ",mcd1,"\n"
		print "-----------------\n"

		# Caso donde son primos relativos
		if (mcd1 != 1):
			print "",mcd1," es factor de ",numero," usando a = ",a," en la iteración ",i,"!\n"

	print "No se encontró factor de ",numero," con a = ",a," y en k (k =",k,") iteraciones."
	print "Intenta con una k más grande u otro valor de a con y^2 = x^2 + ax - a\n"


if __name__ == '__main__':
	# Interactuando con el usuario
	print "Factorización por el método de Lenstra"
	numero = long(raw_input("Ingresa el número que quieres factorizar: "))
	k = int(raw_input("Ingresa la cota k: "))
	a = int(raw_input("Dada la curva y^2 = x^3 + ax - a, ingresa el parámetro a: "))
	lenstra(numero, k, a)