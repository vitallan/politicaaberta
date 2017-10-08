
exports.up = function(knex, Promise) {
    return Promise.all([
        knex.schema.table('deputy', function(table) {
            table.renameColumn('secondarySiteId', 'secondary_site_id');
        })
    ])
}

exports.down = function(knex, Promise) {
    return Promise.all([
        knex.schema.table('deputy', function(table) {
            table.renameColumn('secondary_site_id', 'secondarySiteId');
        })
    ])
}
