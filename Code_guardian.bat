@echo off
echo -------------------Welcome to github auto backup bot-------------------
echo Current Configurations
echo Command wating: 10 sec
echo Backup frequency: 10Min
SET /A "index = 1"
SET /A "count = 10"
:while
if %index% leq %count% (
   git add .
   timeout 10
   git commit -m "Backup by bot"
   timeout 10
   git push
   timeout 600
   goto :while
)