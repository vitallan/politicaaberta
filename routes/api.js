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

    var party = knex('party').where({name: req.body.party}).select('id', 'name');

    knex('deputy').returning('id').insert({
      name: req.body.name,
      uf: req.body.uf,
      secondary_site_id: req.body.secondary_site_id,
      party_id: party.id,
      site_id: req.body.site_id
    }).then(function(response){
      req.body.id = response[0];
      req.body.party = party;
      res.send(req.body, 200)
    });
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
