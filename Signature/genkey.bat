set name=app
set pass=12345678
D:\Java\jdk1.8.0_144\bin\keytool -genkey -v -keystore "%~dp0\%name%.jks" -alias %name% -keyalg RSA -validity 999999 -keypass %pass% -storepass %pass%