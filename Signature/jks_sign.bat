@echo off
jarsigner -verbose -storepass 12345678 -keystore "%~dp0\jifei.jks" -signedjar "%~dp0\jks_out.apk" "%~dp0\111.zip" jifei