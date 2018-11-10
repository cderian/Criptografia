#! /usr/bin/env python
#-*- coding: utf-8 -*-

'''
 * Determina el máximo común divisor de a y b
'''
def mcd(a,b):
	while b != 0:
		t = b;
		b = a % b;
		a = t;
	return a

'''
 * Determina el Inverso Multiplicativo Modular de a mod n
'''
def invMultMod(a, n):
    c1 = 1
    c2 = -(n//a)
    t1 = 0
    t2 = 1
    r = n%a
    x = a;
    y = r;
    
    while r != 0:
        c = x//y
        r = x%y
        
        c1 = c1*(-c)
        c2 = c2*(-c)
        
        c1 = c1 + t1
        c2 = c2 + t2

        t1 = -(c1 - t1)//c
        t2 = -(c2 - t2)//c
        x = y
        y = r

    # Si r = 1, existe el inverso y son primos relativos
    if x != 1:
        return None

    if t2 < 0:
    	return (n + t2)%n
    else: t2
