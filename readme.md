# Decathlon application

Developed by Edvinas Baublys 03-01-2017

### Description

The application:

1. reads input file with athletes results
2. validates source file format
3. calculates scores
4. sorts and assigns places
5. writes formatted list of athletes to file in XML format.

### Installation

Run tests and create .jar file

```sh
$ mvn clean compile test
```

Run application

Go to root directory where decathlon.jar is located.

```sh
$ java -jar <applicationName>.jar <path/to/source/file> <path/to/output/file>
```

For example

```sh
$ java -jar Decathlon-1.0-SNAPSHOT.jar C:/Users/Edvis/Decathlon_input.txt C:/Users/Edvis/Decathlon_output.xml
```
