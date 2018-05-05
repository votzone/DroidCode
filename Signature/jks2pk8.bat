set name=app
set pass=12345678

D:\Java\jdk1.8.0_144\bin\keytool -importkeystore -srckeystore "%~dp0\%name%.jks" -destkeystore "%~dp0\%name%.p12" -srcstoretype JKS -deststoretype PKCS12 -srcstorepass %pass% -deststorepass %pass% -noprompt

C:\OpenSSL-Win64\bin\openssl  pkcs12 -in "%~dp0\%name%.p12" -nodes -out "%~dp0\%name%_all.rsa.pem" -password pass:%pass%

C:\OpenSSL-Win64\bin\openssl pkcs12 -in "%~dp0\%name%.p12" -nodes -nokeys -out "%~dp0\%name%.x509.pem" -password pass:%pass%
:: 导出x509

C:\OpenSSL-Win64\bin\openssl pkcs12 -in "%~dp0\%name%.p12" -nodes -cacerts -out "%~dp0\%name%.rsa.pem" -password pass:%pass%
:: 导出private key

C:\OpenSSL-Win64\bin\openssl pkcs8 -topk8 -outform DER -in "%~dp0\%name%.rsa.pem" -inform PEM -out "%~dp0\%name%.pk8" -nocrypt 

del "%~dp0\%name%.p12"
del "%~dp0\%name%_all.rsa.pem"
del "%~dp0\%name%.rsa.pem"
pause