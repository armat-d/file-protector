<h1 align="center"> file-protector </h1> <br>

<p align="center">
file-protector is a client-server application which allows to securely upload and download files over HTTPS.
Encryption/decryption is done on the client side and the server has absolutely no knowledge about the content of the uploaded files.
</p>


## Features
* File encryption/decryption
* Secure file upload
* Secure file download

## Project structure
The project consists of 3 modules:
* client - a command-line application
* api - HTTP server
* security - a library which allows to encrypt and decrypt the files


## Requirements
### Local
* [Java 8 SDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Maven](https://maven.apache.org/download.cgi)

## Quick Start
TODO: Add instructions to start the application

TODO: Replace hardcoded values in the code (like directory paths) - make them configurable

TODO: Improve input arguments handling - it's not very intuitive

### Run Local
Currently the application can be run only using an IDE (Intellij IDEA, Eclipse, etc).

TODO: Add executable standalone JAR

## Possible improvements
This is a sample project and there are many possible improvements:
- Add more tests - currently only FileStreamer is tested
- Add Javadoc (at least to the interfaces)
- Add logs
- Use dependency injection library/framework if the project gets more complicated
- Add better error handling in order to have helpful error messages - currently all the exceptions are declared in the method declarations and that's all
- ...
