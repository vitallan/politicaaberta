
* Node (4.2.6)
* Knex (0.13.0)

knex migrate:latest
knex migrate:make step1  

knex migrate:latest --env test
and run tests

DEBUG=my-application ./bin/www


https://alexzywiak.github.io/running-migrations-with-knex/
http://www.dancorman.com/knex-your-sql-best-friend/
http://teknosains.com/i/simple-crud-nodejs-mysql
http://mherman.org/blog/2016/04/28/test-driven-development-with-node/#.WdlI3H7ytZU
