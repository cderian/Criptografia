def mcd(n,m):
	m_aux = n%m

	if( m_aux == 0 ):
		return m
	elif( m_aux == 1 ):
		return 1
	else:
		return mcd(m,m_aux)



def inv_mult(a,n):
	if(mcd(a,n) != 1):
		print(str(a) + " No tiene Inverso Multiplicativo en " + str(n))
	else:
		for i in range(1,n) :
			if( ( a*i-1 )%n == 0 ):
				print("Inverso multiplicativo de " + str(a) + " mod " + str(n) + ": " + str(i))

inv_mult(53, 255792)
