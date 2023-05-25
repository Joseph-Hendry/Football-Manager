To build and run Football Manager from the command line (Based off of the GUI example):
-----------------------------------------------------

1. Ensure you are in the root project directory. This directory contains this README, the src and doc directories.

2. Run the following command to compile the Java source code and place the resulting compiled classes into the
   bin directory:

     javac -d bin -cp src src/main/Main.java

3. To start Football Manager with a graphical user interface run:

     java -cp bin main.Main

To buid the source code in eclipse, import the project:
-----------------------------------------------------

1. Create a new java project in eclipse (File->New->Java Project)

2. On the the src folder right click and import (Import->General->File System)

3. Select the src folder in the downloaded zip.

4. The game will load into eclpise and run it through game enviroment.

5. To run the JUnit tests you will need to add JUnit 5 to the build path.