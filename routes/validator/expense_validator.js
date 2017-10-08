var validator = require('is-my-json-valid');

var expense_validator = validator({
  required: true,
  type: 'object',
  properties: {
    description: { type: 'string', required: true },
    url: { type: 'string', required: true },
    deputy_site_id: { type: 'number', required: true },
    value: { type: 'number', required: true },
    date: { type: 'number', required: true }
    
  },
  additionalProperties: false
});

module.exports = expense_validator;