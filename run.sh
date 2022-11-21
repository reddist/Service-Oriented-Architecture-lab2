mvn clean compile package
cp --force ./target/lab2jaxrs.war ./wildfly-19.1.0.Final/standalone/deployments
bash ./wildfly-19.1.0.Final/bin/standalone.sh -Dservice1_url=https://localhost:26506 -Dssl_cert=helios.p12  -Dssl_pass=helios