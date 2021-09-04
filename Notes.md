#Serenity Project
Serenity BDD is an open source library that aims to make the 
idea of living documentation a reality.


###Steps to Create Project
1. Create a Maven project
   1. under 'pom.xml'
       1.add below property section
   ````
      <properties>
      <maven.compiler.source>8</maven.compiler.source>
      <maven.compiler.target>8</maven.compiler.target>
      <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
      </properties>

2. Add dependecies
      ````
      <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-core</artifactId>
            <version>2.4.4</version>
        </dependency>
        <!--        this is the dependency that wrap up rest assured with additional serenity support-->
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-rest-assured</artifactId>
            <version>2.4.4</version>
        </dependency>

        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>5.7.1</version>
            <scope>test</scope>
        </dependency>
        <!--Junit 5 support for serenity -->
        <dependency>
            <groupId>io.github.fabianlinz</groupId>
            <artifactId>serenity-junit5</artifactId>
            <version>1.6.0</version>
        </dependency>
        
   ````
   3. Add Build plugins
    <build>
      <plugins>
      <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-compiler-plugin</artifactId>
      <version>3.8.1</version>
      <configuration>
      <source>8</source>
      <target>8</target>
      </configuration>
      </plugin>
      <!--            This is where the report is being generated after the test run -->
      <plugin>
      <groupId>net.serenity-bdd.maven.plugins</groupId>
      <artifactId>serenity-maven-plugin</artifactId>
      <version>2.4.4</version>
      <executions>
      <execution>
      <id>serenity-reports</id>
      <phase>post-integration-test</phase>
      <goals>
      <goal>aggregate</goal>
      </goals>
      </execution>
      </executions>
      </plugin>
      <!--         We want to run all the tests then generate one report -->
      <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-surefire-plugin</artifactId>
      <version>3.0.0-M5</version>
      <configuration>
      <testFailureIgnore>true</testFailureIgnore>
      </configuration>
      </plugin>
      </plugins>
      </build>

