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
