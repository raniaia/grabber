@echo off

@REM ####################################################################
@REM
@REM Grabber project init shell.
@REM
@REM ####################################################################

@echo Hello Grabber developer.
@echo Version: 1.0.0.DEV

@REM ---------------------------------------------------------------------
@REM
@REM Judge directory is exist. if not exist, then created.
@REM Your user directory for windows is %USERPROFILE%.
@REM
@REM ---------------------------------------------------------------------
if exist "%USERPROFILE%\.gradle" (
    mkdir "%USERPROFILE%/.gradle"
)

@REM ---------------------------------------------------------------------
@REM
@REM Set gradle compiled parameter, speed up compilation.
@REM
@REM ---------------------------------------------------------------------
@echo org.gradle.daemon=true>"%USERPROFILE%/.gradle/gradle.properties"

@echo off
