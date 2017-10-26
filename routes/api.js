var express = require('express');
var router = express.Router();
var knex = require('../server/db');
var deputy_validate = require('./validator/deputy_validator');
var expense_validate = require('./validator/expense_validator');

router.post('/deputy', function(req, res) {
    if(!deputy_validate(req.body)) {
      res.send(deputy_validate.errors, 400);
      return;
    }

    var party = req.body.party;

    knex.transaction(trx => {
      trx('party').where('name', party).then(query_result => {
        if(query_result.length === 0) {
          console.log("inserindo");
          return trx('party').insert({name: party}).then(() => {
            return trx('party').where('name', party);
          });
        } else {
          console.log("ja ta la");
          return query_result;
        }
      });
    }).then(trx_result => {
      console.log(trx_result);
      console.log("PORRA");
      console.log("Party is " + trx_result[0].id + "  -  " + trx_result[0].name);
    });

    res.send(null, 200);

    /*knex('party').where({name: req.body.party}).select('id', 'name').then(function(party_id, party_name){

      if(party_name == undefined) {
        knex('party').returning('id')
      }

      knex('deputy').returning('id').insert({
        name: req.body.name,
        uf: req.body.uf,
        secondary_site_id: req.body.secondary_site_id,
        party_id: party_id,
        site_id: req.body.site_id
      }).then(function(response){
        req.body.id = response[0];
        req.body.party = {id: party_id, name: party_name};
        res.send(req.body, 200);
      });

    });*/

  }
);

router.post('/expense', function(req, res) {
  if(!expense_validate(req.body)){
    res.send(expense_validate.errors, 400);
    return;
  }
  res.send("", 200);
});

module.exports = router;
