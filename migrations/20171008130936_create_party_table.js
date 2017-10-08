
exports.up = function(knex, Promise) {
  return Promise.all([
    knex.schema.createTable('party', function(table){
      table.string('name').unique().notNullable();
      table.timestamps();
    })
  ])
};

exports.down = function(knex, Promise) {
  return Promise.all([
    knex.schema.dropTable('party')
  ])
};
