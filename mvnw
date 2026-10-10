off`nset "JAVA_HOME=C:\Program Files\Java\jdk-17"

D:\mobile\users-management-spring-boot-hexagonal-ddd>setlocal enabledelayedexpansion 

D:\mobile\users-management-spring-boot-hexagonal-ddd>set "MAVEN_VERSION=3.9.6" 

D:\mobile\users-management-spring-boot-hexagonal-ddd>set "MAVEN_CACHE=C:\Users\sanbr\.mvn\wrapper" 

D:\mobile\users-management-spring-boot-hexagonal-ddd>set "MAVEN_HOME=C:\Users\sanbr\.mvn\wrapper\apache-maven-3.9.6" 

D:\mobile\users-management-spring-boot-hexagonal-ddd>set "MAVEN_CMD=C:\Users\sanbr\.mvn\wrapper\apache-maven-3.9.6\bin\mvn.cmd" 

D:\mobile\users-management-spring-boot-hexagonal-ddd>set "DIST_URL=https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/3.9.6/apache-maven-3.9.6-bin.zip" 

D:\mobile\users-management-spring-boot-hexagonal-ddd>set "DIST_ZIP=C:\Users\sanbr\.mvn\wrapper\mvn-dist-3.9.6.zip" 

D:\mobile\users-management-spring-boot-hexagonal-ddd>if exist "C:\Users\sanbr\.mvn\wrapper\apache-maven-3.9.6\bin\mvn.cmd" goto :run_maven 

D:\mobile\users-management-spring-boot-hexagonal-ddd>"C:\Users\sanbr\.mvn\wrapper\apache-maven-3.9.6\bin\mvn.cmd" spring-boot:runD:\mobile\users-management-spring-boot-hexagonal-ddd spring-boot:run 
[INFO] Scanning for projects...
Downloading from central: https://repo.maven.apache.org/maven2/org/springframework/boot/spring-boot-maven-plugin/runD/spring-boot-maven-plugin-runD.pom
[WARNING] The POM for org.springframework.boot:spring-boot-maven-plugin:jar:runD is missing, no dependency information available
Downloading from central: https://repo.maven.apache.org/maven2/org/springframework/boot/spring-boot-maven-plugin/runD/spring-boot-maven-plugin-runD.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  1.474 s
[INFO] Finished at: 2026-10-09T16:18:26-05:00
[INFO] ------------------------------------------------------------------------
[ERROR] Plugin org.springframework.boot:spring-boot-maven-plugin:runD or one of its dependencies could not be resolved: The following artifacts could not be resolved: org.springframework.boot:spring-boot-maven-plugin:jar:runD (absent): Could not find artifact org.springframework.boot:spring-boot-maven-plugin:jar:runD in central (https://repo.maven.apache.org/maven2) -> [Help 1]
[ERROR] 
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR] 
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/PluginResolutionException
