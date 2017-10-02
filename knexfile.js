// Update with your config settings.

module.exports = {

  development: {
    client: 'mysql',
    connection: {
      database: 'politicaaberta_node',
      user:     'root',
      password: ''
    },
    pool: {
      min: 2,
      max: 10
    },
    migrations: {
      tableName: 'knex_migrations'
    }
  },

  production: {
    client: 'mysql',
    connection: {
      database: 'politicaaberta_node',
      user:     'politicaaberta',
      password: ''
    },
    pool: {
      min: 2,
      max: 10
    },
    migrations: {
      tableName: 'knex_migrations'
    }
  }

};
