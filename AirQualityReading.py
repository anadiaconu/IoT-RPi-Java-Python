import pyfirmata #Importing the module for establishing the firmata protocol
import time      #Importing the module for sleep
import MySQLdb   #Importing the module for sending the sensor readings to database


#Creating a variable called conn which basically connects to the desired Host Pc's database 
#We need to carefully write the IP address, username and password as well as the database name

conn = MySQLdb.connect(host="192.168.1.100",  
		       user="root",
		       passwd="",
		       db="projectsensorreading")

#We need to select the proper serial output port, in this case ACM0 of the raspberry pi 

board = pyfirmata.Arduino('/dev/ttyACM0')

#Designating the nature of the pin (analog or digital) as well as the pin number of the Arduino
#For Air Quality Sensor we actually selected the Analog pin A0 

analog_pin = board.get_pin('a:0:i')

#Start iterating the sensor values

it = pyfirmata.util.Iterator(board)
it.start()

#Enable reporting of the analog pin

analog_pin.enable_reporting()

#initiate a MySQL db function called cursor()

x = conn.cursor()


#initializing the variable readingFloat

readingFloat = float(0)

#Setting the While loop to True so that it repeats looping till execution of the script

while True:
    
    reading = analog_pin.read()
    
    #Providing a time stamp for each reading that is being sent to the database

    timeStamp = time.ctime()
    if reading != None:
   
   	
        print("Gas Reading = %f, Time = %s" % (reading , timeStamp))
        time.sleep(1)                   #setting sleep value for 1 second
        readingFloat = float(reading)
        
        
    #Providing the necessary database name as well as the Column names    

    query = """insert into `airqualityreading`(TimeStamp,Reading) values('%s',%f)""" % (timeStamp,readingFloat)

    #Executing a query

    x.execute(query)	

    #committing the connection 

    conn.commit()
 
	
  	
		  	
