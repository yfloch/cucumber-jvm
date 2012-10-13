
Run the server:

```
mvn jetty:run
```

Debug server:

```
MAVEN_OPTS="-Xdebug -Xnoagent -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005" mvn jetty:run
```

Debug cukes:

```
MAVEN_OPTS="-Xdebug -Xnoagent -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005" mvn test -Dcucumber.options="--tags @focus"
```

http://blog.mitemitreski.com/2012_07_15_archive.html (try the other one)
http://static.springsource.org/spring/docs/3.1.2.RELEASE/spring-framework-reference/html/transaction.html

Transaction stuff

http://amitstechblog.wordpress.com/2011/05/31/supporting-custom-isolation-levels-with-jpa/
http://shahzad-mughal.blogspot.co.uk/2012/04/spring-jpa-hibernate-support-for-custom.html
http://stackoverflow.com/questions/5234240/hibernatespringjpaisolation-does-not-work