@echo off
title Student Management System
echo ===================================================
echo Compiling Student Management System...
echo ===================================================
"C:\Java\jdk-1.8\bin\javac.exe" *.java

if %ERRORLEVEL% NEQ 0 (
    echo.
    echo Compilation failed! Please check your code.
    pause
    exit /b %ERRORLEVEL%
)

echo.
echo Compilation successful! Launching application...
echo ===================================================
echo.
"C:\Java\jdk-1.8\bin\java.exe" Main

echo.
echo ===================================================
echo Application closed.
echo ===================================================
pause
