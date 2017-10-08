
exports.up = function(knex, Promise) {
  return Promise.all([
    knex.schema.table('deputy', function(table) {
      table.integer('party_id');
    })
  ])
};

exports.down = function(knex, Promise) {
  return Promise.all([
    knex.schema.table('deputy', function(table) {
      table.dropColumn('party_id');
    })
  ])
};
