#! /usr/bin/env python
#-*- coding: utf-8 -*-

'''
 Integrantes: 
 	Bernabé Gómez Luis Gerardo
 	Hidalgo López Diana Giselle
'''
import random
import Algebra
import SolovayStrassenPT

bitlength = 10

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

	return p, q, N, phi, e, d

'''
 * c = c.pow(e) mod n
'''
def encrypt(message, N, e):
    cipher = [int((ord(char) ** e) % N) for char in message]
    #cipher = [Algebra.expMod((ord(char)), e, N) for char in message]
    return cipher
'''
 * m = c.pow(d) mod n
'''
def decrypt(message, N, d):
	decipher = [ chr(int((char ** d) % N)) for char in message]
	#decipher = [chr( Algebra.expMod(char, d, N)) for char in message]
	return ''.join(decipher)

'''
 * Método principal
'''
if __name__ == '__main__':
	print "Algoritmo RSA"
	p, q, N, phi, e, d = rsa()

	message = "ESTNDESCIFRANDORSABUENASUERTE"
	print "Mensaje: ", message,"\n"

	m_cod = encrypt(message, N, e)
	print "Mensaje cifrado: ",m_cod, "\n"

	m_decod = decrypt(m_cod, N, d)
	print "Mensaje descifrado: ",m_decod, "\n"
