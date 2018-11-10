#! /usr/bin/env python
#-*- coding: utf-8 -*-

import random
import Algebra
import SolovayStrassenPT

p = 0
q = 0
N = 0
phi = 0
e = 0
d = 0
bitlength = 150
blocksize = 256

'''
 * Genera las claves pública y privada para RSA
 * Llave pública = (N, e)
 * Llave privada = (N, d)
'''
def rsa():
	# 1. Dos números primos aleatorios
	p = random.getrandbits(bitlength)
	q = random.getrandbits(bitlength)

	while not( SolovayStrassenPT.solovay_strassen(p, 10) and SolovayStrassenPT.solovay_strassen(q, 10)):
		p = random.getrandbits(bitlength)
		q = random.getrandbits(bitlength)

	print "\nGenerando primos aleatorios:"
	print "p = ",p
	print "q = ",q

	# 2. Se calcula N = p*q
	N = p*q;
	print "\nGenerando N:"
	print "N = p*q = ",N

	# 3. Se calcula la función de Euler
	# Phi(N) = (p-1)*(q-1)
	phi = (p-1)*(q-1)
	print "\nCalculando la función de Euler:"
	print "phi(N) = (p-1)*(q-1) = ",phi

	# 4. Entero positivo 'e' < phi y que sea coprimo
	e = random.getrandbits(bitlength/2)
	while not( SolovayStrassenPT.solovay_strassen(e, 10) ):
		e = random.getrandbits(bitlength/2)

	while not( Algebra.mcd(e, phi) == 1 ):
		e = e+1
	print "\nGenerando e < phi:"
	print "e = ", e," < ", phi

	# 5. Se determina 'd' que satisfaga la congruencia e*d = 1 (mod phi(N))
	d = Algebra.invMultMod(e, phi)
	print "\nGenerando d:"
	print "d = ",d

	#Imprimiendo llaves al usuario
	print "Llave pública = (N, e) = (", N, ", ", e, ")"
	print "Llave privada = (N, d) = (", N, ", ", d, ")\n"

'''
 * c = c.pow(d) mod n
'''
#def encrypt(message):

'''
 * m = c.pow(d) mod n
'''
#def decrypt(message):

'''
 * Método principal
'''
if __name__ == '__main__':
	print "Algoritmo RSA"
	rsa()
