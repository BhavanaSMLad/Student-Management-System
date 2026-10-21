# PowerShell Runner Script
Write-Host "===================================================" -ForegroundColor Cyan
Write-Host "Compiling Student Management System..." -ForegroundColor Yellow
Write-Host "===================================================" -ForegroundColor Cyan

& "C:\Java\jdk-1.8\bin\javac.exe" *.java

if ($LASTEXITCODE -eq 0) {
    Write-Host "`nCompilation successful! Launching application...`n" -ForegroundColor Green
    & "C:\Java\jdk-1.8\bin\java.exe" Main
} else {
    Write-Host "`nCompilation failed!" -ForegroundColor Red
}
