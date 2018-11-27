@echo off
prompt [1;33;40m$p$g[0;37;40m

doskey notepad=notepad++.exe
doskey np=notepad++.exe $*
doskey cdHome=cd C:/
doskey gradleInit = cd C:\RTC\Apollo\ProjectX_Server\build 
doskey cdXScripts = cd C:\RTC\Apollo\ProjectX_Server\Projects\Deployment\build\toolkit\scripts
doskey cdXMaster = cd C:\RTC\Apollo\ProjectX_Server\Projects\master
doskey cdXBuild = cd C:\RTC\Apollo\ProjectX_Server\build

doskey cdDALBuild = cd C:\RTC\Apollo\Apollo_DAL\build
doskey cdDALMaster = cd C:\RTC\Apollo\Apollo_DAL\Projects\master
doskey cdAPTBuild = cd C:\RTC\Apollo\Apollo_Platform_Test\build
doskey cdAPTPerfTests = cd C:\RTC\Apollo\Apollo_Platform_Test\Projects\ApolloPerformanceTest
doskey cdAPTServiceTests = cd C:\RTC\Apollo\Apollo_Platform_Test\Projects\ApolloSeriveTest

doskey cdApollo = cd C:\RTC\Apollo
doskey cdDAOD= cd C:\RTC\DAOD
doskey cdDevEss = cd C:\RTC\devEssentials\Apollo_Dev_Examples
doskey cdIBase = cd C:\RTC\iBase

doskey cdLuke = cd C:\solr\Luke
doskey luke = java -jar C:\solr\Luke\luke-4.5.0-jar-with-dependencies.jar

doskey cdDevXSDK= cd C:\RTC\devEssentials\Apollo_Dev_Examples\working\SDK\sdk-projects\master

doskey cdBF = cd C:\BlueFusion\clarkma9_Cambridge.Minigame.2016-master

cmd