''''
Name: Qianyu Zhang

The project is to figure out whether double hashing allows us to use smaller tables than Quadratic Probing does.'''

import urllib.request

class quadratic:
    
    # constructor for hash table and its size
    def __init__(self):
        self.size = 2543  # table size
        self.data = [None] * self.size        
             
    # to read the string name on the website and insert it into hash table
    def readList(self, url):
        response = urllib.request.urlopen(url)
        html = response.readline() 
        steps = 0
        while len(html) != 0:
            k = html.decode('utf-8')
            hh = 1
            for x in k:
                hh *= ord(x)
            h = hh % self.size  # hash function to convert string into key values
            steps += self.qinsert(k, h)   # add the numbers of the inserting steps
            html = response.readline()
        avgSteps = steps / 2500  # calculate the average number of steps
        print('finish qinserting, the average of steps: ', avgSteps)

    # insert the string into hash table using quadratic probing                
    def qinsert(self, k, h):        
        i = 1   # the base number of steps       
        a = h   # index
        c1 = 3  # 1, 2, 3
        c2 = 0.3  # 1, 1/2, 1/3
        while (i < self.size) and (self.data[a] != None):             
             a = round(h + c1*i + c2*(i**2)) % self.size
             i += 1
        if (self.data[a] == None):
             self.data[a] = k
        else:
             print("insert failed")
        return i

    # search function
    def qsearch(self, k):
        i = 1
        hh = 1
        for x in k:
            hh *= ord(x)
        h = hh % self.size  #hash function
        a = h
        c1 = 3  # 1, 2, 3
        c2 = 0.3  # 1, 1/2, 1/3
        while (i < self.size) and (self.data[a] != None) and (self.data[a] != k):             
             a = round(h + c1*i + c2*(i**2)) % self.size
             i += 1             
        if (self.data[a] == k):
             print("found ", k)
        else:
             print("search failed")

class doubleHash:

    # constructor for hash table and its size
    def __init__(self):
        self.size = 2851
        # table size
        self.data = [None] * self.size

    # to read the string name on the website and insert it into hash table
    def readList(self, url):
        response = urllib.request.urlopen(url)
        html = response.readline() 
        steps = 0
        while len(html) != 0:          
            k = html.decode('utf-8')
            hh = 1
            for x in k:
                hh *= ord(x)
            h = hh % self.size  # the first hash function
            steps += self.dinsert(k,h)  # add the numbers of the inserting steps
            html = response.readline()
        avgSteps = steps / 2500
        print('finish dinserting, the average of steps: ', avgSteps)

    # insert the string into hash table using double hashing
    def dinsert(self,k, h):
        i = 1   # the number of steps        
        a = h   #index
        #g = ((5**0.5) -1)/2
        #h2 =  round(self.size *  (g * h - round(g*h)))
        h2 = 1 + (round(h/self.size) % (self.size - 1))
        # the 2nd function are found online.  round(self.size *  (g * h - round(g*h))),  3 - (h % 3),  1 + (round(h/self.size) % (self.size - 1)) found online
        while (i < self.size) and (self.data[a] != None):             
             a = round((h + h2*i) % self.size)
             i += 1
        if (self.data[a] == None):
             self.data[a] = k
        else:
             print("insert failed")
        return i

    # search function
    def dsearch(self, k):
        i = 1
        hh = 1
        for x in k:
            hh *= ord(x)
        h = hh % self.size     
        a = h
        g = ((5**0.5) -1)/2
        h2 =  round(self.size *  (g * h - round(g*h))) 
        while (i < self.size) and (self.data[a] != None) and (self.data[a] != k):             
             a = round((h + h2*i) % self.size)
             i += 1
        if (self.data[a] == k):
             print("found ", k)
        else:
             print("search failed")
    
T1 = quadratic()
T2 = doubleHash()
T1.readList('http://sites.cs.queensu.ca/courses/cisc235/Assignments/Assignment%203/HOTNCU_code_names_2018_4657.txt')
T2.readList('http://sites.cs.queensu.ca/courses/cisc235/Assignments/Assignment%203/HOTNCU_code_names_2018_4657.txt')
T1.qsearch('soundest\n')
T2.dsearch('poultice\n')
