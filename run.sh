mvn clean compile package
cp --force ./target/lab2jaxrs.war ./wildfly-19.1.0.Final/standalone/deployments
bash ./wildfly-19.1.0.Final/bin/standalone.sh -Dssl_cert=spring.p12 -Dssl_pass=spring