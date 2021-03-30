# spring-cloud-config-example

> 서버측 [keystore](https://github.com/godekdls/spring-cloud-config-example/blob/main/config-server-sample/src/main/resources/config-server.jks) 생성 명령어

```sh
keytool -genkeypair -alias config-server-key \
       -keyalg RSA -keysize 4096 -sigalg SHA512withRSA \
       -dname 'CN=Config Server,OU=Spring Cloud,O=godekdls' \
       -keypass torytoto -keystore config-server.jks \
       -storepass torytoto
```

## 컨피그 서버 실행하기

```sh
$ cd config-server-sample
$ ./mvnw clean package -Dmaven.test.skip=true
$ ./mvnw spring-boot:run
```

### 컨피그 서버로 secret 암호화하기

```sh
curl -X POST -s --data-urlencode mypasswd http://localhost:8888/encrypt
```

### 컨피그 서버로 sample 어플리케이션 설정을 조회하는 방법들

```shell
$ curl http://localhost:8888/sample/dev
$ curl http://localhost:8888/sample-dev.yml
$ curl http://localhost:8888/sample-dev.properties
$ curl http://localhost:8888/sample/dev/main/nginx.conf
$ curl http://localhost:8888/sample/dev/nginx.conf?useDefaultLabel
```

## 컨피그 클라이언트 실행하기

```sh
$ cd config-client-sample
$ ./mvnw clean package -Dmaven.test.skip=true
$ ./mvnw spring-boot:run
```

### 컨피그 클라이언트 설정 정보 확인하기

```sh
$ curl http://localhost:8080/actuator/env
$ curl http://localhost:8080/foo
$ curl http://localhost:8080/message
$ curl http://localhost:8080/secret
```
