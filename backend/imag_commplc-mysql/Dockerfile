From openjdk:8
WORKDIR /usr/local/bin/app
copy ./target/imag_commplc-1.0.jar ./imag_commplc-1.0.jar
copy ./src/main/resources/DataExcel ./src/main/resources/DataExcel
ENV TZ=Asia/Ho_Chi_Minh
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
RUN apt-get update
RUN apt-get install -y default-mysql-client
CMD ["java","-jar","imag_commplc-1.0.jar"]