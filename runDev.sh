echo "====================Setting Env To Java 17=========="
export JAVA_HOME=`/usr/libexec/java_home -v 17.0.2.`
echo JAVA_HOME
echo "====================Building Java App==============="
mvn spring-boot:run