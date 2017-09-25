# Implementation-of-CRC-using-Sockets-Java-
Using Socket programming API's in java belonging to the (java.net.*) package to implement/show CRC Function.

Run the two files in Separate Terminals.

First run MyServer.java and then run MyClient.java

Here the data is of binary type (1,0) of length 1000 stored in an array.
This data is being divided into chunks of 100 and being appended with the Redundant bits.

This along with the Divisor is sent to the server which checks if the data received is error free or not.
