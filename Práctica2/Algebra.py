#! /usr/bin/env python
#-*- coding: utf-8 -*-

def mcdMod(n,m):
	m_aux = n%m

	if( m_aux == 0 ):
		return m
	elif( m_aux == 1 ):
		return 1
	else:
		return mcd(m,m_aux)

'''
 * Determina el Inverso Multiplicativo Modular de a mod n
'''
def inv_mult(a,n):
	if(mcdMod(a,n) != 1):
		return -1
	else:
		for i in range(1,n) :
			if( ( a*i-1 )%n == 0 ):
				return i