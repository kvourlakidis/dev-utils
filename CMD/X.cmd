@echo off

SET xDir=C:\Dev\T\ProjectX_Server
SET hDir=C:\Dev\T\Disco_Hosted
SET xScripts=%xDir%\Projects\Deployment\build\toolkit\scripts

doskey xbuild=cd %xDir%\Build
doskey xmaster=cd %xDir%\Projects\master
doskey xscripts=cd %xScripts%
doskey hbuild=cd %hDir%\Build
doskey hmaster=cd %hDir%\Projects\master

CALL %xDir%\Build\Bootstrap.cmd -pathonly

CALL cmd.exe /K "cd %xScripts% && C:"
