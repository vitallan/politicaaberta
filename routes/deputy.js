var express = require('express');
var router = express.Router();
var validator = require('is-my-json-valid');

/* GET users listing. */
router.get('/', function(req, res) {
  res.send('respond with a resource');
});


var filter = validator.filter({
  required: true,
  type: 'object',
  properties: {
    name: { type: 'string', required: true },
    uf: { type: 'string', required: true },
    site_id: { type: 'number', required: true },
    secondary_site_id: { type: 'number', required: true }
  },
  additionalProperties: false
});

router.post('/', 

  function(req, res) {
    res.send(req.body);
  }
);

module.exports = router;
