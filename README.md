# spring-cloud-config-example

> 디렉토리 구성<br>
> -- spring-cloud-config-example<br>
> ---- [config-repo-sample](https://github.com/godekdls/spring-cloud-config-example/tree/main/config-repo-sample) : 설정 정보 보관용 레포지토리<br>
> ---- [config-server-sample](https://github.com/godekdls/spring-cloud-config-example/tree/main/config-server-sample) : 스프링 클라우드 컨피그 서버 어플리케이션<br>
> ---- [config-client-sample](https://github.com/godekdls/spring-cloud-config-example/tree/main/config-client-sample) : 스프링 클라우드 컨피그 클라이언트 어플리케이션<br>

> 서버측 [keystore](https://github.com/godekdls/spring-cloud-config-example/blob/main/config-server-sample/src/main/resources/config-server.jks) 생성 명령어
> 
> ```sh
> keytool -genkeypair -alias config-server-key \
>        -keyalg RSA -keysize 4096 -sigalg SHA512withRSA \
>        -dname 'CN=Config Server,OU=Spring Cloud,O=godekdls' \
>        -keypass torytoto -keystore config-server.jks \
>        -storepass torytoto
> ```

> 암호화 설정 예시
> [my.secret](https://github.com/godekdls/spring-cloud-config-example/blob/main/config-repo-sample/sample-dev.properties#L4)

## 컨피그 서버 실행하기

```sh
$ cd config-server-sample
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
$ ./mvnw spring-boot:run
```

[`spring.cloud.config.overrideNone=true`](https://github.com/godekdls/spring-cloud-config-example/blob/main/config-repo-sample/application.properties#L2) 설정 후 컨피그 서버의 overrides 프로퍼티를 재정의하고 싶으면

```shell
$ ./mvnw spring-boot:run -Dspring-boot.run.jvmArguments="-Dfriend=hi"
```

### 컨피그 클라이언트 설정 정보 확인하기

```sh
$ curl http://localhost:8080/actuator/env
$ curl http://localhost:8080/foo
$ curl http://localhost:8080/message
$ curl http://localhost:8080/secret
$ curl http://localhost:8080/friend
$ curl http://localhost:8080/app.friend
$ curl http://localhost:8080/app.enemy
```
