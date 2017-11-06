import math

def answer(area):
    # your code here
    i = area
    arr = []
    while i > 0:
    	elt = int(math.floor(math.sqrt(i)) ** 2)
    	arr.append(elt)
    	i -= elt
    return arr


# print answer(12)