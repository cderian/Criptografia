'''
 * Estructura que define un punto con coordenadas x,y
 * Se usara para manipular las sumas de puntos
 * El atributo lambda resulta de sumar dos puntos cuyo resultado es (x,y)
 * así como los atributos num (el numerador) y den (denomidador).
'''
class Punto:
 	def __init__(self):
	 	self.x = 1
	 	self.y = 1
	 	self.lamb = 1
	 	self.num = 1
	 	self.den = 1

 	def getX(self):
 		return self.x

 	def getY(self):
 		return self.y

 	def getLambda(self):
 		return self.lamb

 	def getNum(self):
 		return self.num

 	def getDen(self):
 		return self.den

 	def setX(self, x):
 		self.x = x

 	def setY(self, y):
 		self.y = y

 	def setLambda(self, l):
 		self.lamb = l

 	def setNum(self, num):
 		self.num = num

 	def setDen(self, den):
 		self.den = den
