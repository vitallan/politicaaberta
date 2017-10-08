var express = require('express');
var router = express.Router();
var deputy_validate = require('./validator/deputy_validator');
var expense_validate = require('./validator/expense_validator');

router.post('/deputy', function(req, res) {
    if(!deputy_validate(req.body)) {
      res.send(deputy_validate.errors, 400);
      return;
    }
    res.send("", 200);
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
