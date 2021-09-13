#Workflow name
name: CI/CD Pipeline
on:
  #Manually trigger workflow runs
  workflow_dispatch:
  #Trigger the workflow on push from the main branch
  push:
    branches:
      - master

jobs:
  # Unit tests job
  tests:
    name: Unit tests
    #Run on Ubuntu using the latest version
    runs-on: ubuntu-latest
    #Job's steps
    steps:
      #Check-out your repository under $GITHUB_WORKSPACE, so your workflow can access it
      - uses: actions/checkout@v2
      #Set up JDK 11
      - name: Set up JDK 11
        uses: actions/setup-java@v2
        with:
          java-version: "11"
          distribution: "adopt"
          cache: maven
      #Run Tests
      - name: Run Tests
        run: mvn -B test

  #Build's job
  build:
    #Depends on tests
    needs: tests
    name: Build
    #Run on Ubuntu using the latest version
    runs-on: ubuntu-latest
    steps:
      #Check-out your repository under $GITHUB_WORKSPACE, so your workflow can access it
      - uses: actions/checkout@v2
      #Set up JDK 11
      - name: Set up JDK 11
        uses: actions/setup-java@v2
        with:
          java-version: "11"
          distribution: "adopt"
          cache: maven
      #Build the application using Maven
      - name: Build with Maven
        run: mvn -B package -DskipTests --file pom.xml
      #Upload JAR
      - name: Upload JAR
        #This uploads artifacts from your workflow allowing you to share data between jobs and store data once a workflow is complete.
        uses: actions/upload-artifact@v2
        with:
          #Set artifact name
          name: artifact
          #From this path
          path: target/demo-0.0.1-SNAPSHOT.jar

  #Deploy's job
  deploy:
    needs: build
    name: Deploy
    runs-on: ubuntu-latest
    steps:
      - name: Download JAR
        uses: actions/download-artifact@v2
        with:
          name: artifact

      - name: Deploy to AWS EB
        uses: einaregilsson/beanstalk-deploy@v18
        with:
          aws_access_key: ${{ secrets.AWS_ACCESS_KEY_ID }}
          aws_secret_key: ${{ secrets.AWS_SECRET_ACCESS_KEY }}
          aws_session_token: ${{ secrets.AWS_SESSION_TOKEN }}
          use_existing_version_if_available: false
          application_name: demojavaman
          environment_name: Demojavaman-env
          version_label: ${{github.SHA}}
          region: us-east-2
          deployment_package: demo-0.0.1-SNAPSHOT.jar