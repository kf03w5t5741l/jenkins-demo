# Jenkins continuous deployment demo with Spring Boot
This is a demo application to show how to set up continuous deployment for a Spring Boot application via Jenkins. All values in square brackets below are placeholder values that should be replaced by the user.

This setup requires the manual creation of a database and database user on the application server.

The following shell script will also need to be created and saved as `[APPLICATION_NAME].sh` in `/usr/local/bin/` on the application server:

```
#!/bin/sh
SERVICE_NAME=[APPLICATION_NAME]
PATH_TO_JAR=/apps/[APPLICATION_NAME].jar
PID_PATH_NAME=/tmp/[APPLICATION_NAME]-pid
LOG_FILE=/tmp/[APPLICATION_NAME].log
case $1 in
    start)
        echo "Starting $SERVICE_NAME ..."
        if [ ! -f $PID_PATH_NAME ]; then                    # Ignore the square brackets in this line
            nohup java -Dspring.profiles.active=prod -jar $PATH_TO_JAR /tmp 2>> $LOG_FILE >> $LOG_FILE &
                        echo $! > $PID_PATH_NAME
            echo "$SERVICE_NAME started ..."
        else
            echo "$SERVICE_NAME is already running ..."
        fi
    ;;
    stop)
        if [ -f $PID_PATH_NAME ]; then                      # Ignore the square brackets in this line
            PID=$(cat $PID_PATH_NAME);
            echo "$SERVICE_NAME stoping ..."
            kill $PID;
            echo "$SERVICE_NAME stopped ..."
            rm $PID_PATH_NAME
        else
            echo "$SERVICE_NAME is not running ..."
        fi
    ;;
    restart)
        if [ -f $PID_PATH_NAME ]; then                      # Ignore the square brackets in this line
            PID=$(cat $PID_PATH_NAME);
            echo "$SERVICE_NAME stopping ...";
            kill $PID;
            echo "$SERVICE_NAME stopped ...";
            rm $PID_PATH_NAME
        fi

        echo "$SERVICE_NAME starting ..."
        nohup java -Dspring.profiles.active=prod -jar $PATH_TO_JAR /tmp 2>> $LOG_FILE >> $LOG_FILE &
        echo $! > $PID_PATH_NAME
        echo "$SERVICE_NAME started ..."
    ;;
esac
```
Remember to run `chmod +x [APPLICATION_NAME].sh` to grant execution privileges.

## Jenkins basics for maven-based deployments via Git
For this method of deployment to work, Jenkins should have the `Git` plugin installed.

Select `Create new item` from the left-hand menu of the Jenkins dashboard, enter your application name and select `Maven project` to continue.

### Jenkins source code management steps
Under the header `Source Code Management`, enter the URL of your git repository. Decide if you would like to deploy from `master` or other branches in your repo.



### Jenkins pre-build  steps
Add the following shell command as a pre-build step:
```
echo "spring:
  jpa:
    show-sql: false
    hibernate:
      ddl-auto: update
  datasource:
    url: jdbc:mysql://localhost:3306/[DATABASE]
    username: [DATABASE_USERNAME]
    password: [DATABASE_PASSWORD]
server:
  port: [PORT]" > $WORKSPACE/src/main/resources/application-prod.yml;
  
  cp $WORKSPACE/src/main/resources/application-prod.yml $WORKSPACE/src/main/resources/application.yml
```

### Jenkins build steps
Add `package` to the `Goals and Options` field.

### Jenkins post-build steps
Add the following shell command as a post-build step:
```
cp target/[APPLICATION_NAME_STUB]*.jar /apps/[APPLICATION_NAME].jar
daemonize -E BUILD_ID=[APPLICATION_NAME]_${BUILD_NUMBER} /usr/local/bin/[APPLICATION_NAME].sh restart
```

Select `Run only if build succeeds` to ensure your application keeps running even when new builds fail.
