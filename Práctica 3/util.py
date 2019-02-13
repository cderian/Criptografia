#! /usr/bin/env python
#-*- coding: utf-8 -*-

def mcd(a,b):
	'''
 	 * Determina el máximo común divisor de a y b
	'''
	while  b != 0:
		t = b;
		b = a % b;
		a = t;
	return a

def modn(x, num):
	'''
	 * Esta funcion reduce modulo num.
	 * Queremos la clase de equivalencia cuyo representante sea positivo.
	'''
	return (x+num)%num

def inverso_modular(a, m):
    '''
     * Funcion que calcula el inverso multiplicativo de un numero (mod m)
    '''
    c1 = 1
    c2 = -(m // a)  # coeficiente de a y b respectivamente
    t1 = 0
    t2 = 1  # coeficientes penultima corrida
    r = m % a  # residuo, asignamos 1 como condicion de entrada
    x = a;
    y = r;
    while r != 0:
        c = x // y  # cociente
        r = x % y  # residuo
        # guardamos valores temporales de los coeficientes
        # multiplicamos los coeficiente por -1*cociente de la division
        c1 *= -c
        c2 *= -c
        # sumamos la corrida anterior
        c1 += t1
        c2 += t2
        # actualizamos corrida anterior
        t1 = -(c1 - t1) // c
        t2 = -(c2 - t2) // c
        x = y
        y = r

    if x != 1:  # residuo anterior es 1 , son primos relativos y el inverso existe
        return None

    return (m + t2) % m if t2 < 0 else t2