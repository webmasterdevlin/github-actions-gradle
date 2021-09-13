#### install and use Java 11 through SDKMAN

```zsh
sdk install java 11.0.11.hs-adpt
sdk use java 11.0.11.hs-adpt
```

##### test the project

```zsh
./gradlew test
```

##### build the project

```zsh
./gradlew build
```

##### build Docker image called java-gradle-demo. Execute from root

```zsh
docker build -t java-gradle-demo .
```

##### push image to repo

```zsh
docker tag java-gradle-demo java-gradle-demo:java-1.0
```

### Links

- https://docs.github.com/en/actions/reference/events-that-trigger-workflows
- https://github.com/actions
- https://github.com/marketplace/actions/docker-build-push-action
