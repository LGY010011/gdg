REPOSITORY=/home/ubuntu/action
cd $REPOSITORY

APP_NAME=gdg-0.0.1-SNAPSHOT   #빌드되는 jar파일의 명을 입력해주세요.
JAR_NAME=$(ls $REPOSITORY/build/libs/ | grep '.jar' | tail -n 1)
JAR_PATH=$REPOSITORY/build/libs/$JAR_NAME

CURRENT_PID=$(pgrep -f $APP_NAME)

# 실행되고 있다면 종료시키고 아니라면 스킵합니다.
if [ -z $CURRENT_PID ]
then
  echo "> 종료할것 없음."
else
  echo "> kill -9 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi


# jar파일을 실행시키는 명령어
echo "> $JAR_PATH 배포"
nohup java -jar $JAR_PATH > /home/ubuntu/action/nohup.out 2>&1 &