@echo off
gcc -shared -o %1 -I"%JAVA_HOME%\include" -I"%JAVA_HOME%\include\win32" %2