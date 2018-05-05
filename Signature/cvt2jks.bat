set name=app
set pass=12345678
C:\OpenSSL-Win64\bin\openssl pkcs8 -inform DER -nocrypt -in "%~dp0\%name%.pk8" -out "%~dp0\%name%.pem"
C:\OpenSSL-Win64\bin\openssl pkcs12 -export -in "%~dp0\%name%.x509.pem" -inkey "%~dp0\%name%.pem" -out "%~dp0\platform.p12" -password pass:%pass% -name %name%
D:\Java\jdk1.8.0_144\bin\keytool -importkeystore -deststorepass %pass% -destkeystore "%~dp0\%name%.jks" -srckeystore "%~dp0\platform.p12" -srcstoretype PKCS12 -srcstorepass %pass%

del "%~dp0\%name%.pem"
del "%~dp0\platform.p12"
pause