# Docker/Jetty/TDD/BDD/Java Example app

This is an ongoing learning project in learning about setting up a simple application, using best practices for creating enterprise software and using different types of technologies

## Instructions

1. start mysql database
2. connect to mysql database (might have to change the password in `DatabaseConnectionManager` class)
3. run sql file, best be in this directory, run `source test.sql;'
4. Run main and check 'localhost:8080/db/clients'

## Set up mysql container

From scratch
`docker run --name some-mysql -e MYSQL_ROOT_PASSWORD=password -d mysql`

// docker run -it --link some-mysql:mysql --rm mysql sh -c 'exec  mysql -h"172.17.0.3" -P"3306" -uroot -p' 

If already pulled image
`docker start <container name>`

Check if working
`docker ps`

start bash in container
`docker exec -it   <container name>  /bin/bash `

Start mysql
`mysql -u root -p` 

Connect to mysql
`mysql -h"172.17.0.3" -P"3306" -uroot -p `

## Other commands

Need to change the properties for port and url

netstat -tulpn | grep LISTEN