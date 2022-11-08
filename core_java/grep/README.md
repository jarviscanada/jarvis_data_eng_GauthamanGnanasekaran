#Introduction

---

This project will recursively search for lines provided by the user using regular expressions (REGEX) in the directory. It will tbe return the lines that are matched.
The **Java.io**, and **Java.util** libraries are used to search the directories and are then logged into the output file. This application can be run two ways, docker
or jar. 

##Quick Start

---

####Java .class/.jar
To build the app use 

```
mvn clean compile
```
 or you can run the application using your ide. 
 To execute the file use:
 
```
java JavaGrepImp [regular expression] [root directory] [output file]
```
For docker use:

```
docker build -t [gauthaman123/grep]
docker run Gauthaman123/grep [regular expression] [root directory] [output file]
```

##Implementation
###PseudoCode(Precess)

---

```
matchedLines = new ArrayList<>();
for(list files in root path recursively){
    for(read lines in each file){
        if(regular expression){
            matchedLines.add()
        }
    }
}
writetofile(matchedlines)
```
###Performance Issues

---

If files/directories are large, and if the matched lines are large, it can lead to memory and performance issues. You can use **XMX** to limit
the amount of heap size. You can also use **BufferedReader** class to stream characters. This is more efficient than using FileReader. 

##Test

---

A sample file of Romeo and Juliet is provided. Regex patterns were used and were compared with the output file to check if the output is expected.

##Deployment

---

It was deployed into docker hub. You can follow the quick start guide for docker to use the application

##Improvement

---

The application can be improved by using JUNIT to test. The memory problem can also be addressed by streaming the characters. 
