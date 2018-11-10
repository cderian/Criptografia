#! /usr/bin/env python
#-*- coding: utf-8 -*-

import random
import Algebra
import SolovayStrassenPT

p = 0
q = 0
n = 0
phi = 0
e = 0
d = 0
bitlength = 150
blocksize = 256

def rsa():
	# 1. Dos números primos aleatorios
	p = random.getrandbits(bitlength)
	q = random.getrandbits(bitlength)

	while not( SolovayStrassenPT.solovay_strassen(p, 10) and SolovayStrassenPT.solovay_strassen(q, 10)):
		p = random.getrandbits(bitlength)
		q = random.getrandbits(bitlength)

	#print "p = " + str(p)
	#print "q = " + str(q)

	# 2. Se calcula n=p*q
	n = p*q;
	#print "n = p*q = " + str(n)

	# 3. Se calcula la función de Euler
	# Phi(n) = (p-1)*(q-1)
	phi = (p-1)*(q-1)
	#print "phi = (p-1)*(q-1) = " + str(phi)

	# 4. Entero positivo 'e' < phi y que sea coprimo
	e = random.getrandbits(bitlength/2)
	while not( SolovayStrassenPT.solovay_strassen(e, 10) ):
		e = random.getrandbits(bitlength/2)

	while not( Algebra.mcd(e, phi) == 1 ):
		e = e+1
	#print "e = " + str(e) + " < " + str(phi)

	# 5. Se determina 'd' que satisfaga la congruencia e*d=1(mod phi(n))
	#d = Algebra.inv_mult(e, phi)
	#print d

'''
 * c = c.pow(d) mod n
'''
#def encrypt(message):

'''
 * m = c.pow(d) mod n
'''
#def decrypt(message):

rsa()
