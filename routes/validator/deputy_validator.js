var validator = require('is-my-json-valid');

var deputy_validator = validator({
  required: true,
  type: 'object',
  properties: {
    name: { type: 'string', required: true },
    uf: { type: 'string', required: true, maxLength: 2 },
    site_id: { type: 'number', required: true },
    secondary_site_id: { type: 'number', required: true },
    party: { type: 'string', required: true }
  },
  additionalProperties: false
});

module.exports = deputy_validator;