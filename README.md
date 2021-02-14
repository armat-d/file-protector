# file-protector
file-protector is a client-server application which allows to:
- Encrypt files
- Upload encrypted files to the server via HTTP
- Download uploaded files
- Decrypt downloaded files

The project consists of 3 modules:
- client - a command-line application
- api - HTTP server
- security - a library which allows to encrypt and decrypt the files

Encryption/decryption is done on the client side and the server has absolutely no knowledge about the content of the uploaded files.

This is a sample project and there are many possible improvements:
- Add more tests - currently only FileStreamer is tested
- Add Javadoc (at least to the interfaces)
- Add logs
- Use dependency injection library/framework if the project gets more complicated
- There are hardcoded values in the code (like directory paths) - they can be configurable
- Add better error handling in order to have helpful error messages - currently all the exceptions are declared in the method declarations and that's all
- On the client side, input arguments handling can be improved - they are not very intuitive
- Add HTTPS
