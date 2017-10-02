
exports.up = function(knex, Promise) {
    return Promise.all([
        knex.schema.createTable('deputy', function(table){
            table.string('name');
            table.string('uf');
            table.integer('site_id');
            table.integer('secondarySiteId');
            table.timestamps();
        })
    ])
}

exports.down = function(knex, Promise) {
    return Promise.all([
        knex.schema.dropTable('deputy')
    ])
}
